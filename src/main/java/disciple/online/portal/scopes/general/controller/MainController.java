package disciple.online.portal.scopes.general.controller;

import disciple.online.portal.scopes.general.services.GraphService;
import disciple.online.portal.scopes.report.services.ReportGlobalService;
import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.services.UserService;
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

        JSONObject graph = new JSONObject();
        JSONObject bibleChapter = new JSONObject();
        JSONObject meditationMinutes = new JSONObject();
        JSONObject meditationNumber = new JSONObject();

        if(!year.isPresent()){
            model.addAttribute("year",2020);
            try {
                graph = graphService.buildJSonDataForPrayer(user.get());
                bibleChapter = graphService.buildJSonDataForBibleReading(user.get());
                meditationMinutes = graphService.buildJSonDataForMeditationMinutes(user.get());
                meditationNumber = graphService.buildJSonDataForMeditationNumber(user.get());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
         else{
             model.addAttribute("year",year.get());
            try {
                graph = graphService.buildJSonDataForPrayer(user.get(),year.get());
                bibleChapter = graphService.buildJSonDataForBibleReading(user.get(),year.get());
                meditationMinutes = graphService.buildJSonDataForMeditationMinutes(user.get(),year.get());
                meditationNumber = graphService.buildJSonDataForMeditationNumber(user.get() , year.get());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("graph",graph.toString());
        model.addAttribute("bibleChapter",bibleChapter.toString());
        model.addAttribute("meditationMinutes",meditationMinutes.toString());
        model.addAttribute("meditationNumber",meditationNumber.toString());
        model.addAttribute("reports", reportGlobalService.getGlobalTicketOwnerByMail(user.get().getEmail()));
        return "index";
    }
}
