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
import org.json.JSONArray;
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
import java.util.*;


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

        Optional<User> user = userService.findUserById(id);
        Optional<User> authUser = userService.findUserByEmail(((User)authentication.getPrincipal()).getEmail());

        if(user.isEmpty())
            return "redirect:/";

        if(authUser.isEmpty())
            return "redirect:/";

        for (Object auth : authentication.getAuthorities()){
            if(auth.toString().equals(UserRole.ADMIN.toString()) || authUser.get().getDiscipleMakerMail().equals(user.get().getEmail())
            || user.get().getDiscipleMakerMail().equals(authUser.get().getEmail())) {

                model.addAttribute("user" , user.get());
                model.addAttribute("discipleMakers" , userService.getAllDiscipleMaker());
                model.addAttribute("userAuthDto",new UserAuthDto());
                model.addAttribute("reports", reportGlobalService.getGlobalTicketOwnerByMail(user.get().getEmail()));
                model.addAttribute("userService",userService);
                model.addAttribute("disciples",userService.getAllDiscipleFromDiscipleMaker(user.get().getEmail()));
                if(error.isPresent())
                    model.addAttribute("error" , languageService.getValue("error.checkdata"));

                JSONArray categories = new JSONArray();
                JSONArray fastData = new JSONArray();
                JSONArray prayerAloneData = new JSONArray();
                JSONArray prayerTogetherData = new JSONArray();
                JSONArray meditationMinutesData = new JSONArray();
                JSONArray meditationNumber = new JSONArray();
                JSONArray bibleChapter = new JSONArray();
                JSONArray bibleChapterMinutes = new JSONArray();
                JSONArray evangelizedPeople = new JSONArray();
                JSONArray evangelizationMinutes = new JSONArray();

                if(!year.isPresent()){
                    model.addAttribute("year",2020);
                    try {
                        fastData = graphService.buildJSonDataForFast(user.get());
                        categories = graphService.buildJSonCategories(user.get());

                        prayerAloneData = graphService.buildJSonDataForPrayerAlone(user.get());
                        prayerTogetherData = graphService.buildJSonDataForPrayerTogether(user.get());

                        meditationMinutesData = graphService.buildJsonDataForMeditationMinutes(user.get());
                        meditationNumber = graphService.buildJsonDataForMeditationNumber(user.get());

                        bibleChapter = graphService.buildJsonDataForBibleChapter(user.get());
                        bibleChapterMinutes = graphService.buildJsonDataForBibleChapterMinutes(user.get());

                        evangelizedPeople = graphService.buildJsonDataForEvangelizedPeople(user.get());
                        evangelizationMinutes = graphService.buildJsonDataForEvangelizationMinutes(user.get());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    model.addAttribute("year",year.get());
                    try {
                        fastData = graphService.buildJSonDataForFast(user.get() , year.get());
                        categories = graphService.buildJSonCategories(user.get() , year.get());

                        prayerAloneData = graphService.buildJSonDataForPrayerAlone(user.get() , year.get());
                        prayerTogetherData = graphService.buildJSonDataForPrayerTogether(user.get() , year.get());

                        meditationMinutesData = graphService.buildJsonDataForMeditationMinutes(user.get(), year.get());
                        meditationNumber = graphService.buildJsonDataForMeditationNumber(user.get(), year.get());

                        bibleChapter = graphService.buildJsonDataForBibleChapter(user.get(), year.get());
                        bibleChapterMinutes = graphService.buildJsonDataForBibleChapterMinutes(user.get() , year.get());

                        evangelizedPeople = graphService.buildJsonDataForEvangelizedPeople(user.get(), year.get());
                        evangelizationMinutes = graphService.buildJsonDataForEvangelizationMinutes(user.get(), year.get());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                model.addAttribute("fastData" , fastData.toString());
                model.addAttribute("prayerAloneData", prayerAloneData.toString());
                model.addAttribute("prayerTogetherData",prayerTogetherData.toString());
                model.addAttribute("meditationMinutes",meditationMinutesData.toString());
                model.addAttribute("meditationNumber",meditationNumber.toString());
                model.addAttribute("categories",categories.toString());
                model.addAttribute("bibleChapter",bibleChapter.toString());
                model.addAttribute("bibleChapterMinutes",bibleChapterMinutes.toString());
                model.addAttribute("evangelizedPeople",evangelizedPeople.toString());
                model.addAttribute("evangelizationMinutes",evangelizationMinutes.toString());

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

    @GetMapping("/users/search/{text}")
    public String handleSearchUser(@PathVariable String text , Model model){
        Set<User> users = new HashSet<>();;

        if(!"empty".equals(text))
            users = userService.searchAllUsersWith(text);
        
        model.addAttribute("users",users);
        return "administration/users";
    };
}
