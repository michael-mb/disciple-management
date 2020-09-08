package disciple.online.portal.scopes.report.controller;

import disciple.online.portal.scopes.general.services.LanguageService;
import disciple.online.portal.scopes.report.entities.DetailTicket;
import disciple.online.portal.scopes.report.entities.GlobalTicket;
import disciple.online.portal.scopes.report.entities.ReportStatus;
import disciple.online.portal.scopes.report.forms.ReportDetailDto;
import disciple.online.portal.scopes.report.forms.ReportGlobalDto;
import disciple.online.portal.scopes.report.services.ReportDetailService;
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

import javax.swing.undo.AbstractUndoableEdit;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Controller
public class ReportDetailController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReportDetailService reportDetailService;

    @Autowired
    private LanguageService languageService;


    @GetMapping("/report-detail")
    public String handleReportDetailView(Authentication authentication , Model model){
        if(authentication == null || !authentication.isAuthenticated()){
            return "redirect:/";
        }

        model.addAttribute("reportDetailDto",new ReportDetailDto());
        return "report/reportDetail";
    }

    @PostMapping("/report-detail")
    public String handleReportDetailPost(Authentication authentication ,
                                         @ModelAttribute("reportDetailDto")@Valid final ReportDetailDto reportDetailDto,
                                         RedirectAttributes redirectAttributes)
    {
        if(authentication == null || !authentication.isAuthenticated())
            return "redirect:/";

        Optional<User> user = userService.findUserByEmail(((User)authentication.getPrincipal()).getEmail());

        if(!user.isPresent())
            return "redirect:/";

        if(!reportDetailDto.getMessage().isEmpty())
            reportDetailDto.setMessage(
                    user.get().getFirstname() + " " + user.get().getLastname() + ": " + reportDetailDto.getMessage()
            );

        reportDetailService.createDetailTicket(reportDetailDto , user.get());
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("text", languageService.getValue("report.global.creation.success"));
        return "redirect:/report-overview";
    }

    @GetMapping("/report-detail-update/edit/{id}")
    public String handleReportDetailView(Authentication authentication ,
                                         Model model,
                                         @PathVariable long id){

        if(authentication == null || !authentication.isAuthenticated())
            return "redirect:/";

        Optional<DetailTicket> ticket = reportDetailService.getDetailTicketById(id);
        Optional<User> user = userService.findUserByEmail(((User)authentication.getPrincipal()).getEmail());

        if(ticket.isEmpty())
            return "redirect:/";

        // if you are the disciple maker
        if(ticket.get().getStatus().contains(ReportStatus.NOTSEEN) &&
                ticket.get().getDiscipleMakerMail().equals(user.get().getEmail())){
            ticket.get().setStatus(new HashSet<>(Arrays.asList(ReportStatus.SEEN)));
            reportDetailService.saveDetailTicket(ticket.get());
        }

        model.addAttribute("ticket",ticket.get());
        model.addAttribute("reportDetailDto",new ReportDetailDto());
        model.addAttribute("disable", !(user.get().getEmail().equals(ticket.get().getOwnerMail())));
        return "report/detailTicketDetail";
    }

    @PostMapping("/report-detail-update/edit/{id}")
    public String handleReportDetailEditPost(Authentication authentication ,
                                             Model model,
                                             @PathVariable long id,
                                             @ModelAttribute("reportDetailDto") @Valid final ReportDetailDto reportDetailDto){

        if(authentication == null || !authentication.isAuthenticated())
            return "redirect:/";

        Optional<User> user = userService.findUserByEmail(((User)authentication.getPrincipal()).getEmail());

        if(!user.isPresent())
            return "redirect:/";

        Optional<DetailTicket> ticket = reportDetailService.getDetailTicketById(id);
        if(ticket.isEmpty())
            return "redirect:/";

        String newLine = System.getProperty("line.separator");
        // if you are not the owner
        if(!ticket.get().getOwnerMail().equals(user.get().getEmail())){
            reportDetailDto.setMessage(
                    ticket.get().getMessage() + newLine +
                            user.get().getFirstname() + " " + user.get().getLastname() + ": " + reportDetailDto.getMessage()
            );

            reportDetailService.updateDetailTicketMessage(reportDetailDto , ticket.get());
            return "redirect:/report-overview";
        }

        reportDetailDto.setMessage(
                ticket.get().getMessage() + newLine +
                        user.get().getFirstname() + " " + user.get().getLastname() + ": " + reportDetailDto.getMessage()
        );

        reportDetailService.updateDetailTicket(reportDetailDto , ticket.get());

        return "report/detailTicketDetail";
    }

    @GetMapping("/resume-report")
    public String handleResumeReport(Authentication authentication,
                                     RedirectAttributes redirectAttributes){

        if(authentication == null || !authentication.isAuthenticated())
            return "redirect:/";

        Optional<User> user = userService.findUserByEmail(((User)authentication.getPrincipal()).getEmail());

        if(!user.isPresent())
            return "redirect:/";

        reportDetailService.resumeReport(user.get());
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("text", languageService.getValue("report.detail.resume"));

        return "redirect:/report-overview";
    }
}
