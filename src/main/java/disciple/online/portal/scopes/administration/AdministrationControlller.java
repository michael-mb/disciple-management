package disciple.online.portal.scopes.administration;

import disciple.online.portal.scopes.general.services.GraphService;
import disciple.online.portal.scopes.general.services.LanguageService;
import disciple.online.portal.scopes.mail.service.MailService;
import disciple.online.portal.scopes.register.services.RegisterService;
import disciple.online.portal.scopes.report.services.ReportGlobalService;
import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.entities.UserRole;
import disciple.online.portal.scopes.user.forms.UserAuthDto;
import disciple.online.portal.scopes.user.forms.UserRegistrationDto;
import disciple.online.portal.scopes.user.services.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Optional;


@Controller
public class AdministrationControlller {

    @Autowired
    private UserService userService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private ReportGlobalService reportGlobalService;

    @Autowired
    private GraphService graphService;

    @Autowired
    private MailService mailService;

    @GetMapping("/users")
    public String handleUsersView(Model model , Authentication authentication){

        if(authentication == null || !authentication.isAuthenticated()){
            return "redirect:/";
        }
        for (Object auth : authentication.getAuthorities()){
            if(auth.toString().equals(UserRole.ADMIN.toString())){
                model.addAttribute("users" ,userService.getAllDisciple());
                return "administration/users";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/users/edit/{id}")
    public String handleEditUserView(@PathVariable long id , Model model ,
                                 Authentication authentication,
                                 @RequestParam("error") Optional<Boolean> error,
                                 @RequestParam("year") Optional<Long> year){

        if(authentication == null || !authentication.isAuthenticated()){
            return "redirect:/";
        }

        for (Object auth : authentication.getAuthorities()){
            if(auth.toString().equals(UserRole.ADMIN.toString()) || auth.toString().equals(UserRole.DISCIPLEMAKER.toString())){

                User user = userService.findUserById(id).get();
                model.addAttribute("user" , user);
                model.addAttribute("discipleMakers" , userService.getAllDiscipleMaker());
                model.addAttribute("userAuthDto",new UserAuthDto());
                model.addAttribute("reports", reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail()));
                model.addAttribute("userService",userService);
                model.addAttribute("disciples",userService.getAllDiscipleFromDiscipleMaker(user.getEmail()));
                if(error.isPresent())
                    model.addAttribute("error" , languageService.getValue("error.checkdata"));


                JSONObject graph = new JSONObject();
                JSONObject bibleChapter = new JSONObject();
                JSONObject meditationMinutes = new JSONObject();
                JSONObject meditationNumber = new JSONObject();

                if(!year.isPresent()){
                    model.addAttribute("year",2020);
                    try {
                        graph = graphService.buildJSonDataForPrayer(user);
                        bibleChapter = graphService.buildJSonDataForBibleReading(user);
                        meditationMinutes = graphService.buildJSonDataForMeditationMinutes(user);
                        meditationNumber = graphService.buildJSonDataForMeditationNumber(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    model.addAttribute("year",year.get());
                    try {
                        graph = graphService.buildJSonDataForPrayer(user , year.get());
                        bibleChapter = graphService.buildJSonDataForBibleReading(user ,year.get());
                        meditationMinutes = graphService.buildJSonDataForMeditationMinutes(user, year.get());
                        meditationNumber = graphService.buildJSonDataForMeditationNumber(user, year.get());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                model.addAttribute("graph",graph.toString());
                model.addAttribute("bibleChapter",bibleChapter.toString());
                model.addAttribute("meditationMinutes",meditationMinutes.toString());
                model.addAttribute("meditationNumber",meditationNumber.toString());
                return "administration/profile";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/users/delete/{id}")
    public String handleDeleteUser(@PathVariable long id , Model model ,
                                     Authentication authentication,
                                   RedirectAttributes redirectAttributes){

        if(authentication == null || !authentication.isAuthenticated()){
            return "redirect:/";
        }
        Optional<User> user = userService.findUserById(id);

        if(!user.isPresent())
            return "redirect:/";

        userService.deleteUser(user.get());
        try {
            mailService.notifyOnAccountDeleted(user.get());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("text", languageService.getValue("administration.deleteUser.success"));

        return "redirect:/users";
    }

    @PostMapping("/users/edit/{id}")
    public String handleEditUserPost(@PathVariable long id , Model model ,
                                     Authentication authentication,
                                     @ModelAttribute("userAuthDto") @Valid final UserAuthDto userAuthDto,
                                     RedirectAttributes redirectAttributes){

        if(authentication == null || !authentication.isAuthenticated()){
            return "redirect:/";
        }

        if(userAuthDto == null)
            return "redirect:/";

        Optional<User> user = userService.findUserById(id);
        registerService.updateUser(userAuthDto , user.get());
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("text", languageService.getValue("administration.updateUser.success"));

        return "redirect:/users/edit/"+id;
    }
}
