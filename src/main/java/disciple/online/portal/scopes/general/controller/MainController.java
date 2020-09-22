package disciple.online.portal.scopes.general.controller;

import disciple.online.portal.scopes.general.services.GraphService;
import disciple.online.portal.scopes.report.services.ReportGlobalService;
import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.services.UserService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private ReportGlobalService reportGlobalService;

    @Autowired
    private UserService userService;

    @Autowired
    private GraphService graphService;

    @GetMapping("/")
    public String handleIndex(Authentication authentication , Model model , @RequestParam("year") Optional<Long> year){
        if(authentication == null || !authentication.isAuthenticated()){
            return "redirect:/login";
        }
        Optional<User> user = userService.findUserByEmail(((User)authentication.getPrincipal()).getEmail());

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

         model.addAttribute("reports", reportGlobalService.getGlobalTicketOwnerByMail(user.get().getEmail()));
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

         return "index";
    }

    @GetMapping("/impressum")
    public String handleImpressum(){
        return "impressum";
    }
}
