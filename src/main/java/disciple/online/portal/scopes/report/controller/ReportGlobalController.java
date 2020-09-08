package disciple.online.portal.scopes.report.controller;

import disciple.online.portal.scopes.general.services.LanguageService;
import disciple.online.portal.scopes.mail.service.MailService;
import disciple.online.portal.scopes.report.entities.GlobalTicket;
import disciple.online.portal.scopes.report.entities.ReportStatus;
import disciple.online.portal.scopes.report.forms.ReportGlobalDto;
import disciple.online.portal.scopes.report.services.ReportGlobalService;
import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Controller
public class ReportGlobalController {

    @Autowired
    private ReportGlobalService reportGlobalService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private LanguageService languageService;

    @GetMapping("/report/edit/{id}")
    public String handleReportGlobal(Authentication authentication , @PathVariable long id , Model model){
        if(authentication == null || !authentication.isAuthenticated()){
            return "redirect:/";
        }

        Optional<GlobalTicket> ticket = reportGlobalService.getGlobalTicketById(id);
        Optional<User> user = userService.findUserByEmail(((User)authentication.getPrincipal()).getEmail());

        if(ticket.isEmpty())
            return "redirect:/";

        // if you are the disciple maker
        if(ticket.get().getStatus().contains(ReportStatus.NOTSEEN) &&
                ticket.get().getDiscipleMakerMail().equals(user.get().getEmail())){
            ticket.get().setStatus(new HashSet<>(Arrays.asList(ReportStatus.SEEN)));
            reportGlobalService.saveGlobalTicket(ticket.get());
        }

        model.addAttribute("ticket",ticket.get());
        model.addAttribute("reportGlobalDto",new ReportGlobalDto());
        model.addAttribute("disable", !(user.get().getEmail().equals(ticket.get().getOwnerMail())) );
        return "report/globalTicketDetail";
    }


    @GetMapping("/report-global")
    public String handleReportGlobalView(Authentication authentication , Model model){
        if(authentication == null || !authentication.isAuthenticated()){
            return "redirect:/";
        }

        model.addAttribute("reportGlobalDto",new ReportGlobalDto());

        return "report/reportGlobal";
    }

    @GetMapping("/report/archiv/discipleMaker/{id}")
    public String handleArchivProcess(Authentication authentication,
                                      @PathVariable long id,
                                      RedirectAttributes redirectAttributes){

        if(authentication == null || !authentication.isAuthenticated()){
            return "redirect:/";
        }

        Optional<GlobalTicket> ticket = reportGlobalService.getGlobalTicketById(id);
        ticket.get().getStatus().add(ReportStatus.DISCIPLEMAKERARCHIVED);
        reportGlobalService.saveGlobalTicket(ticket.get());

        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("text", languageService.getValue("report.global.archiv.success"));

        return "redirect:/disciple-overview";
    }

    @PostMapping("/report-global-update/{id}")
    public String handleReportGlobalPost(Authentication authentication ,
                                         @PathVariable long id,
                                         Model model,
                                         @ModelAttribute("reportGlobalDto") @Valid final ReportGlobalDto reportGlobalDto){
        if(authentication == null || !authentication.isAuthenticated())
            return "redirect:/";

        Optional<User> user = userService.findUserByEmail(((User)authentication.getPrincipal()).getEmail());

        if(!user.isPresent())
            return "redirect:/";

        Optional<GlobalTicket> ticket = reportGlobalService.getGlobalTicketById(id);
        if(ticket.isEmpty())
            return "redirect:/";

        String newLine = System.getProperty("line.separator");
        // if you are not the owner
        if(!ticket.get().getOwnerMail().equals(user.get().getEmail())){
            reportGlobalDto.setMessage(
                    ticket.get().getMessage() + newLine +
                            user.get().getFirstname() + " " + user.get().getLastname() + ": " + reportGlobalDto.getMessage() + ";"
            );

            try {
                mailService.notifyDiscipleWhenRecieveAMessage(ticket.get().getOwnerMail(),
                        user.get().getDiscipleMakerMail() , ticket.get());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            reportGlobalService.updateGlobalTicketMessage(reportGlobalDto , ticket.get());
            return "redirect:/report-overview";
        }

        reportGlobalDto.setMessage(
                ticket.get().getMessage() + newLine +
                        user.get().getFirstname() + " " + user.get().getLastname() + ": " + reportGlobalDto.getMessage() + ";"
        );

        reportGlobalService.updateGlobalTicket(reportGlobalDto , ticket.get());
        return "redirect:/report-overview";
    }

    @PostMapping("/report-global")
    public String handleReportGlobalPost(Authentication authentication ,
                                         @ModelAttribute("reportGlobalDto") @Valid final ReportGlobalDto reportGlobalDto,
                                         RedirectAttributes redirectAttributes){
        if(authentication == null || !authentication.isAuthenticated())
            return "redirect:/";

        Optional<User> user = userService.findUserByEmail(((User)authentication.getPrincipal()).getEmail());

        if(!user.isPresent())
            return "redirect:/";

        if(!reportGlobalDto.getMessage().isEmpty())
            reportGlobalDto.setMessage(
                    user.get().getFirstname() + " " + user.get().getLastname() + ": " + reportGlobalDto.getMessage()
            );

        reportGlobalService.createGlobalTicket(reportGlobalDto , user.get());
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("text", languageService.getValue("report.global.creation.success"));

        try {
            mailService.notifyDiscipleMakerWhenRecieveAReport(user.get().getEmail(),user.get().getDiscipleMakerMail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return "redirect:/report-overview";
    }

}
