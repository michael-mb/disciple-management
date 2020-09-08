package disciple.online.portal.scopes.resetPassword.controller;

import disciple.online.portal.scopes.general.services.LanguageService;
import disciple.online.portal.scopes.login.forms.LoginDto;
import disciple.online.portal.scopes.resetPassword.forms.ResetDto;
import disciple.online.portal.scopes.resetPassword.service.ResetPasswordService;
import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.entities.UserRole;
import disciple.online.portal.scopes.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ResetPasswordController {

    @Autowired
    private ResetPasswordService resetPasswordService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private UserService userService;

    @GetMapping("/reset-password")
    public String handleResetPasswordView(Model model){
        model.addAttribute(new LoginDto());
        return "reset/resetIndex";
    };

    @PostMapping("/reset-password")
    public String handleResetPasswordPost(@ModelAttribute("login") final LoginDto loginDto,
                                          RedirectAttributes redirectAttributes){
        resetPasswordService.resetPassword(loginDto.getEmail());

        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("text", languageService.getValue("password.reset.success"));
        return "redirect:/";
    }

    @GetMapping("/renew-password")
    public String handleRenewPasswordView(Model model){
        model.addAttribute("resetDto",new ResetDto());
        return "reset/renewPassword";
    }

    @PostMapping("/renew-password")
    public String handleRenewPasswordPost(Model model ,
                                          @ModelAttribute("resetDto") final ResetDto resetDto ,
                                          final BindingResult bindingResult,
                                          @RequestParam("token") Optional<String> token,
                                          RedirectAttributes redirectAttributes){

        if (resetDto != null) {
            if (!resetDto.password.equals(resetDto.passwordAgain)) {
                bindingResult.rejectValue("passwordAgain", "registration.password.matcherror");
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", languageService.getValue("error.checkdata"));
            model.addAttribute("resetDto", resetDto);
            redirectAttributes.addFlashAttribute("type", "warning");
            redirectAttributes.addFlashAttribute("text", languageService.getValue("error.checkdata"));
            return "redirect:/renew-password";
        }

        resetPasswordService.changePassword(resetDto.email,token.get(), resetDto.password);
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("text", languageService.getValue("password.update.success"));
        return "redirect:/";
    }

    @GetMapping("/reset-password-admin/{id}")
    public String handleRenewPasswordByAdmin(Model model ,
                                             Authentication authentication,
                                             @PathVariable Long id,
                                             RedirectAttributes redirectAttributes){

        if(authentication == null || !authentication.isAuthenticated()){
            return "redirect:/";
        }
        Optional<User> user = userService.findUserById(id);
        if(!user.isPresent())
            return "redirect:/";

        resetPasswordService.resetPassword(user.get().getEmail());
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("text", languageService.getValue("password.reset.success"));
        return "redirect:/users/edit/"+id;
    }
}
