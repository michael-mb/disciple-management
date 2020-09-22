package disciple.online.portal.scopes.general.services;


import disciple.online.portal.scopes.report.entities.GlobalTicket;
import disciple.online.portal.scopes.report.services.ReportGlobalService;
import disciple.online.portal.scopes.user.entities.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphService {

    @Autowired
    private ReportGlobalService reportGlobalService;

    public JSONArray buildJSonDataForFast(User user) throws JSONException{
        JSONArray data = new JSONArray();
        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail());

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getFast());
        }
        return data;
    }

    public JSONArray buildJSonDataForFast(User user , Long year) throws JSONException{
        JSONArray data = new JSONArray();
        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail() ,year);

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getFast());
        }
        return data;
    }

    public JSONArray buildJSonCategories(User user) throws JSONException{
        JSONArray categories = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail());

        for(GlobalTicket ticket : tickets){
            categories.put("W-" +ticket.getWeek().substring(6,8));
        }
        return categories;
    }

    public JSONArray buildJSonCategories(User user , Long year) throws JSONException{
        JSONArray categories = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail() , year);

        for(GlobalTicket ticket : tickets){
            categories.put("W-" +ticket.getWeek().substring(6,8));
        }
        return categories;
    }

    public JSONArray buildJSonDataForPrayerAlone(User user) throws JSONException{
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail());

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getPrayerMinutesAlone());
        }
        return data;
    }

    public JSONArray buildJSonDataForPrayerAlone(User user , Long year) throws JSONException{
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail() , year);

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getPrayerMinutesAlone());
        }
        return data;
    }

    public JSONArray buildJSonDataForPrayerTogether(User user) throws JSONException{
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail());

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getPrayerMinutesTogether());
        }
        return data;
    }

    public JSONArray buildJSonDataForPrayerTogether(User user , Long year) throws JSONException{
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail(), year);

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getPrayerMinutesTogether());
        }
        return data;
    }

    public JSONArray buildJsonDataForMeditationNumber(User user){
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail());

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getMeditationNumber());
        }
        return data;
    }

    public JSONArray buildJsonDataForMeditationNumber(User user , Long year){
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail(), year);

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getMeditationNumber());
        }
        return data;
    }

    public JSONArray buildJsonDataForMeditationMinutes(User user){
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail());

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getMeditationMinutes());
        }
        return data;
    }

    public JSONArray buildJsonDataForMeditationMinutes(User user , Long year){
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail() , year);

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getMeditationMinutes());
        }
        return data;
    }

    public JSONArray buildJsonDataForBibleChapter(User user) {
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail());

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getBibleChapter());
        }
        return data;
    }

    public JSONArray buildJsonDataForBibleChapter(User user , Long year) {
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail(), year);

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getBibleChapter());
        }
        return data;
    }

    public JSONArray buildJsonDataForBibleChapterMinutes(User user) {
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail());

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getBibleChapterMinutes());
        }
        return data;
    }

    public JSONArray buildJsonDataForBibleChapterMinutes(User user , Long year) {
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail() , year);

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getBibleChapterMinutes());
        }
        return data;
    }

    public JSONArray buildJsonDataForEvangelizationMinutes(User user) {
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail());

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getEvangelizationMinutes());
        }
        return data;
    }

    public JSONArray buildJsonDataForEvangelizationMinutes(User user , Long year) {
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail() , year);

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getEvangelizationMinutes());
        }
        return data;
    }

    public JSONArray buildJsonDataForEvangelizedPeople(User user) {
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail());

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getEvangelizedPeople());
        }
        return data;
    }

    public JSONArray buildJsonDataForEvangelizedPeople(User user , Long year) {
        JSONArray data = new JSONArray();

        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail() , year);

        for(GlobalTicket ticket : tickets){
            data.put(ticket.getEvangelizedPeople());
        }
        return data;
    }
}
