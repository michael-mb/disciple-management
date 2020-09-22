package disciple.online.portal.scopes.login.controller;

import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.services.UserService;
import disciple.online.portal.scopes.general.services.LanguageService;
import disciple.online.portal.scopes.login.forms.LoginDto;
import disciple.online.portal.scopes.security.StaticUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public UserService userService;

    @Autowired
    private  LanguageService languageService;

    @GetMapping("/login")
    public String handleLogin(final Model model , Authentication authentication) {
        if(authentication != null && authentication.isAuthenticated()){
            return "redirect:/";
        }
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String handleLoginPost(@ModelAttribute("loginDto") @Valid final LoginDto loginDto,
                                  HttpServletRequest httpServletRequest,
                                  RedirectAttributes redirectAttributes){
        try {
            httpServletRequest.login(loginDto.email.toLowerCase().trim(), loginDto.password);
        } catch (ServletException e){
            logger.error("Exception on logout after attempted login with bad credentials.");
            try {
                // invalidate session to clean up context in tomcat
                StaticUtils.logoutAndInvalidateSession();
            } catch (ServletException e2) {
                logger.error("Exception on logout after attempted login with bad credentials.", e2);
            }

            redirectAttributes.addFlashAttribute("type", "warning");
            redirectAttributes.addFlashAttribute("text", languageService.getValue("login.error.checkdata"));
            return "redirect:/login";
        }
        /* Here login was successful */
        final Optional<User> user = userService.findUserByEmail(loginDto.email);
        if (!user.isPresent()) {
            throw new RuntimeException("Login succeeded but user could not be found in repository.");
        }
        userService.rehashPassword(loginDto.password, user.get());

        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("text", languageService.getValue("login.success"));
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String handleLogout(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            try {
                StaticUtils.logoutAndInvalidateSession();
            } catch (ServletException e) {
                logger.error("Error at log out of '" + authentication.getName() + "'.", e);
            }
        }
        return "redirect:/";
    }
}
