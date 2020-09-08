package disciple.online.portal.scopes.report.controller;

import disciple.online.portal.scopes.report.entities.ReportStatus;
import disciple.online.portal.scopes.report.services.ReportDetailService;
import disciple.online.portal.scopes.report.services.ReportGlobalService;
import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ReportController {

    @Autowired
    private ReportGlobalService reportGlobalService;

    @Autowired
    private ReportDetailService reportDetailService;

    @Autowired
    private UserService userService;

    @GetMapping("/report")
    public String handleReportView(Authentication authentication , Model model){
        if(authentication == null || !authentication.isAuthenticated()){
            return "redirect:/";
        }

        Optional<User> user = userService.findUserByEmail(((User)authentication.getPrincipal()).getEmail());

        model.addAttribute("reportGlobal",reportGlobalService.getGlobalTicketOwnerByMail(user.get().getEmail()));
        model.addAttribute("reportDetail",reportDetailService.getDetailTicketOwnerByMail(user.get().getEmail()));

        return "report/report";
    }

    @GetMapping("/report-overview")
    public String handleReportOverview(Authentication authentication , Model model , @RequestParam("year") Optional<Long> year){
        if(authentication == null || !authentication.isAuthenticated()){
            return "redirect:/";
        }
        Optional<User> user = userService.findUserByEmail(((User)authentication.getPrincipal()).getEmail());

        if(year.isPresent()){
            model.addAttribute("tickets", reportGlobalService.getGlobalTicketOwnerByMail(user.get().getEmail() , year.get()));
            model.addAttribute("year",year.get());
        }
        else{
            model.addAttribute("tickets", reportGlobalService.getGlobalTicketOwnerByMail(user.get().getEmail()));
            model.addAttribute("year",2020);
        }

        model.addAttribute("ticketsDetail" , reportDetailService.getDetailTicketOwnerByMail(user.get().getEmail()));
        model.addAttribute("statusSeen", ReportStatus.SEEN);
        return "report/reportOverview";
    }


}
