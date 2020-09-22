package disciple.online.portal.scopes.profile.controller;

import disciple.online.portal.scopes.general.services.LanguageService;
import disciple.online.portal.scopes.register.services.RegisterService;
import disciple.online.portal.scopes.report.services.ReportGlobalService;
import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.forms.UserRegistrationDto;
import disciple.online.portal.scopes.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private ReportGlobalService globalService;

    @GetMapping("/profile")
    public String handleProfileView(Authentication authentication , Model model){
        if(authentication == null || !authentication.isAuthenticated()){
            return "redirect:/";
        }

        Optional<User> user = userService.findUserByEmail(((User)authentication.getPrincipal()).getEmail());

        if(user.isEmpty())
            return "redirect:/";

        if(!((User)authentication.getPrincipal()).getEmail().equals(user.get().getEmail())) {
            return "redirect:/";
        }

        Optional<User> discipleMaker = userService.findUserByEmail(user.get().getDiscipleMakerMail());

        if(discipleMaker.isEmpty())
            return "redirect:/";

        model.addAttribute("user",user.get());
        model.addAttribute("discipleMaker", discipleMaker.get());
        model.addAttribute("userRegistrationDto" , new UserRegistrationDto());
        model.addAttribute("discipleMakers", userService.getAllDiscipleMaker());
        model.addAttribute("disciples",userService.getAllDiscipleFromDiscipleMaker(user.get().getEmail()));
        model.addAttribute("reports", globalService.getGlobalTicketOwnerByMail(user.get().getEmail()));

        return "profile/profile";
    }

    @PostMapping("/profile/{userId}")
    public String handleProfilePost(final Model model,
                                    Authentication authentication,
                                    @ModelAttribute("userRegistrationDto") @Valid final UserRegistrationDto userRegistrationDto,
                                    final BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes){

        Optional<User> user = userService.findUserByEmail(((User)authentication.getPrincipal()).getEmail());

        if (userRegistrationDto != null) {
            if (!userRegistrationDto.password.equals(userRegistrationDto.passwordAgain)) {
                bindingResult.rejectValue("passwordAgain", "registration.password.matcherror");
            }
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("type", "warning");
            redirectAttributes.addFlashAttribute("text", languageService.getValue("error.checkdata"));
            return "redirect:/profile";
        }

        registerService.updateUser(userRegistrationDto , user.get());

        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("text", languageService.getValue("profile.update.success"));
        return "redirect:/profile";
    }
}
