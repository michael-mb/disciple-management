package disciple.online.portal.scopes.register.controller;

import disciple.online.portal.scopes.mail.service.MailService;
import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.forms.UserRegistrationDto;
import disciple.online.portal.scopes.user.services.UserService;
import disciple.online.portal.scopes.general.services.LanguageService;
import disciple.online.portal.scopes.register.services.RegisterService;
import disciple.online.portal.scopes.security.StaticUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);


    @Autowired
    private UserService userService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private MailService mailService;

    @GetMapping("/register")
    public String handleRegisterView(final Model model , Authentication authentication){
        if(authentication != null && authentication.isAuthenticated()){
            return "redirect:/";
        }

        model.addAttribute("userRegistrationDto" , new UserRegistrationDto());
        model.addAttribute("discipleMakers", userService.getAllDiscipleMaker());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(final Model model,
                                 @ModelAttribute("userRegistrationDto") @Valid final UserRegistrationDto userRegistrationDto,
                                 final BindingResult bindingResult,
                                 HttpServletRequest httpServletRequest,
                                 RedirectAttributes redirectAttributes){

        if (userRegistrationDto != null) {
            if (!userRegistrationDto.password.equals(userRegistrationDto.passwordAgain)) {
                bindingResult.rejectValue("passwordAgain", "registration.password.matcherror");
            }
        }

        if (userRegistrationDto.email != null && userService.doesEmailAlreadyExists(userRegistrationDto)) {
            bindingResult.rejectValue("email", "registration.email.alreadyexists");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", languageService.getValue("error.checkdata"));
            model.addAttribute("userRegistrationDto", userRegistrationDto);
            redirectAttributes.addFlashAttribute("type", "warning");
            redirectAttributes.addFlashAttribute("text", languageService.getValue("error.checkdata"));
            return "redirect:/register";
        }
        registerService.addUser(userRegistrationDto);

        try {
            mailService.notifyUserRegistration(userRegistrationDto.email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        //AUTOLOGIN after registration
        return autologin(httpServletRequest, userRegistrationDto , redirectAttributes);
    }

    private String autologin(HttpServletRequest httpServletRequest , UserRegistrationDto userRegistrationDto ,
                             RedirectAttributes redirectAttributes){
        try {
            httpServletRequest.login(userRegistrationDto.email, userRegistrationDto.password);
        } catch (ServletException e){
            logger.error("Exception on logout after attempted login with bad credentials.");
            try {
                // invalidate session to clean up context in tomcat
                StaticUtils.logoutAndInvalidateSession();
            } catch (ServletException e2) {
                logger.error("Exception on logout after attempted login with bad credentials.", e2);
            }
            return "redirect:/login";
        }
        /* Here login was successful */
        final Optional<User> user = userService.findUserByEmail(userRegistrationDto.email);
        if (!user.isPresent()) {
            throw new RuntimeException("Login succeeded but user could not be found in repository.");
        }
        userService.rehashPassword(userRegistrationDto.password, user.get());

        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("text", languageService.getValue("registration.success"));

        return "redirect:/";
    }
}
