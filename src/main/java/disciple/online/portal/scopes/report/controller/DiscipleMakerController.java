package disciple.online.portal.scopes.report.controller;

import disciple.online.portal.scopes.register.services.RegisterService;
import disciple.online.portal.scopes.report.entities.ReportStatus;
import disciple.online.portal.scopes.report.services.ReportGlobalService;
import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class DiscipleMakerController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReportGlobalService reportGlobalService;


    @GetMapping("/disciple-overview")
    public String handleDiscipleMakerView(Authentication authentication , Model model){
        if(authentication == null || !authentication.isAuthenticated())
            return "redirect:/";

        Optional<User> user = userService.findUserByEmail(((User)authentication.getPrincipal()).getEmail());

        if(!user.isPresent())
            return "redirect:/";

        model.addAttribute("disciples",userService.getAllDiscipleFromDiscipleMaker(user.get().getEmail()));
        model.addAttribute("reports",reportGlobalService.getGlobalTicketForDiscipleMaker(user.get().getEmail()));
        model.addAttribute("userService",userService);
        model.addAttribute("status", ReportStatus.NOTSEEN);
        model.addAttribute("archivedByAdmin" , ReportStatus.DISCIPLEMAKERARCHIVED);


        return "report/discipleMakerView";
    }
}
