package disciple.online.portal.scopes.report.controller;

import disciple.online.portal.scopes.report.entities.GlobalTicket;
import disciple.online.portal.scopes.report.services.ReportGlobalService;
import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class ReportAnnualController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReportGlobalService reportGlobalService;

    @GetMapping("/annual-report/{year}")
    public String handleAnnualReportView(Authentication authentication,
                                         @PathVariable Optional<Long> year,
                                         Model model){
        if(authentication == null || !authentication.isAuthenticated()){
            return "redirect:/";
        }
        if(!year.isPresent())
            return "redirect:/report-overview";

        Optional<User> user = userService.findUserByEmail(((User)authentication.getPrincipal()).getEmail());
        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.get().getEmail() , year.get());

        model.addAttribute("user",user.get());
        model.addAttribute("tickets",tickets);
        model.addAttribute("year" , year.get());

        model.addAttribute("chapters",reportGlobalService.getTotalBibleChapter(user.get(),year.get()));
        model.addAttribute("bibleReadingHours",
                convertMinutesInHours(reportGlobalService.getTotalBibleReadingMinutes(user.get(), year.get())));
        model.addAttribute("totalPrayerAlone",
                convertMinutesInHours(reportGlobalService.getTotalPrayerMinutesAlone(user.get(), year.get())));
        model.addAttribute("totalPrayerTogether",
                convertMinutesInHours(reportGlobalService.getTotalPrayerMinutesTogether(user.get(), year.get())));
        model.addAttribute("prayerTime",
                convertMinutesInHours(reportGlobalService.getTotalPrayerMinutesAlone(user.get(),year.get())));
        model.addAttribute("meditationNumber",reportGlobalService.getTotalMeditationNumber(user.get(),year.get()));
        model.addAttribute("meditationTime",
                convertMinutesInHours(reportGlobalService.getTotalMeditationMinutes(user.get(),year.get())));
        model.addAttribute("evangelizationHours",
                convertMinutesInHours(reportGlobalService.getTotalEvangelizationMinutes(user.get(), year.get())));
        model.addAttribute("evangelizedPeople", reportGlobalService.getTotalEvangelizedPeople(user.get(), year.get()));
        model.addAttribute("fastDays", reportGlobalService.getTotalFastDays(user.get(), year.get()));

        return "report/reportAnnual";
    }

    private String convertMinutesInHours(int minutes){
        return minutes/60 +" Std " + minutes % 60;
    }
}
