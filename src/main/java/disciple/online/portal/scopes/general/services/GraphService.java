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

    public JSONObject buildJSonDataForPrayer(User user) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("element","morris-area-chart2");
        jsonObject.put("xkey", "period");
        jsonObject.put("pointSize", 0);
        jsonObject.put("fillOpacity", 0.7);
        jsonObject.put("behaveLikeLine", true);
        jsonObject.put("gridLineColor", "#e0e0e0");
        jsonObject.put("lineWidth", 0);
        jsonObject.put("smooth", false);
        jsonObject.put("hideHover", "auto");
        jsonObject.put("resize",true);

        JSONArray pointStrokeColors = new JSONArray();
        pointStrokeColors.put("#ccc");
        jsonObject.put("pointStrokeColors", pointStrokeColors);
        jsonObject.put("lineColors", pointStrokeColors);

        JSONArray ykeys = new JSONArray();
        ykeys.put("prayer");
        jsonObject.put("ykeys", ykeys);
        jsonObject.put("labels", ykeys);

        JSONArray barColors = new JSONArray();
        barColors.put("#f4f4f4");
        jsonObject.put("barColors",barColors);

        JSONArray data = new JSONArray();
        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail());

        for(GlobalTicket ticket : tickets){
            JSONObject dataElem = new JSONObject();
            dataElem.put("period" , "W-" +ticket.getWeek().substring(6,8));
            dataElem.put("prayer", ticket.getPrayerMinutes());
            data.put(dataElem);
        }

        jsonObject.put("data",data);

        return jsonObject;
    }

    public JSONObject buildJSonDataForPrayer(User user , Long year) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("element","morris-area-chart2");
        jsonObject.put("xkey", "period");
        jsonObject.put("pointSize", 0);
        jsonObject.put("fillOpacity", 0.7);
        jsonObject.put("behaveLikeLine", true);
        jsonObject.put("gridLineColor", "#e0e0e0");
        jsonObject.put("lineWidth", 0);
        jsonObject.put("smooth", false);
        jsonObject.put("hideHover", "auto");
        jsonObject.put("resize",true);

        JSONArray pointStrokeColors = new JSONArray();
        pointStrokeColors.put("#ccc");
        jsonObject.put("pointStrokeColors", pointStrokeColors);
        jsonObject.put("lineColors", pointStrokeColors);

        JSONArray ykeys = new JSONArray();
        ykeys.put("prayer");
        jsonObject.put("ykeys", ykeys);
        jsonObject.put("labels", ykeys);

        JSONArray barColors = new JSONArray();
        barColors.put("#f4f4f4");
        jsonObject.put("barColors",barColors);

        JSONArray data = new JSONArray();
        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail(),year);

        for(GlobalTicket ticket : tickets){
            JSONObject dataElem = new JSONObject();
            dataElem.put("period" , "W-" +ticket.getWeek().substring(6,8));
            dataElem.put("prayer", ticket.getPrayerMinutes());
            data.put(dataElem);
        }

        jsonObject.put("data",data);

        return jsonObject;
    }

    public JSONObject buildJSonDataForBibleReading(User user) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("element","morris-area-chart3");
        jsonObject.put("xkey", "period");
        jsonObject.put("pointSize", 0);
        jsonObject.put("fillOpacity", 0.7);
        jsonObject.put("behaveLikeLine", true);
        jsonObject.put("gridLineColor", "#e0e0e0");
        jsonObject.put("lineWidth", 0);
        jsonObject.put("smooth", false);
        jsonObject.put("hideHover", "auto");
        jsonObject.put("resize",true);

        JSONArray pointStrokeColors = new JSONArray();
        pointStrokeColors.put("#ccc");
        jsonObject.put("pointStrokeColors", pointStrokeColors);
        jsonObject.put("lineColors", pointStrokeColors);

        JSONArray ykeys = new JSONArray();
        ykeys.put("chapters");
        jsonObject.put("ykeys", ykeys);
        jsonObject.put("labels", ykeys);

        JSONArray barColors = new JSONArray();
        barColors.put("#f4f4f4");
        jsonObject.put("barColors",barColors);

        JSONArray data = new JSONArray();
        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail());

        for(GlobalTicket ticket : tickets){
            JSONObject dataElem = new JSONObject();
            dataElem.put("period" , "W-" +ticket.getWeek().substring(6,8));
            dataElem.put("chapters", ticket.getBibleChapter());
            data.put(dataElem);
        }

        jsonObject.put("data",data);

        return jsonObject;
    }

    public JSONObject buildJSonDataForBibleReading(User user , Long year) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("element","morris-area-chart3");
        jsonObject.put("xkey", "period");
        jsonObject.put("pointSize", 0);
        jsonObject.put("fillOpacity", 0.7);
        jsonObject.put("behaveLikeLine", true);
        jsonObject.put("gridLineColor", "#e0e0e0");
        jsonObject.put("lineWidth", 0);
        jsonObject.put("smooth", false);
        jsonObject.put("hideHover", "auto");
        jsonObject.put("resize",true);

        JSONArray pointStrokeColors = new JSONArray();
        pointStrokeColors.put("#ccc");
        jsonObject.put("pointStrokeColors", pointStrokeColors);
        jsonObject.put("lineColors", pointStrokeColors);

        JSONArray ykeys = new JSONArray();
        ykeys.put("chapters");
        jsonObject.put("ykeys", ykeys);
        jsonObject.put("labels", ykeys);

        JSONArray barColors = new JSONArray();
        barColors.put("#f4f4f4");
        jsonObject.put("barColors",barColors);

        JSONArray data = new JSONArray();
        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail() , year);

        for(GlobalTicket ticket : tickets){
            JSONObject dataElem = new JSONObject();
            dataElem.put("period" , "W-" +ticket.getWeek().substring(6,8));
            dataElem.put("chapters", ticket.getBibleChapter());
            data.put(dataElem);
        }

        jsonObject.put("data",data);

        return jsonObject;
    }

    public JSONObject buildJSonDataForMeditationMinutes(User user) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("element","morris-area-chart4");
        jsonObject.put("xkey", "period");
        jsonObject.put("pointSize", 0);
        jsonObject.put("fillOpacity", 0.7);
        jsonObject.put("behaveLikeLine", true);
        jsonObject.put("gridLineColor", "#e0e0e0");
        jsonObject.put("lineWidth", 0);
        jsonObject.put("smooth", false);
        jsonObject.put("hideHover", "auto");
        jsonObject.put("resize",true);

        JSONArray pointStrokeColors = new JSONArray();
        pointStrokeColors.put("#ccc");
        jsonObject.put("pointStrokeColors", pointStrokeColors);
        jsonObject.put("lineColors", pointStrokeColors);

        JSONArray ykeys = new JSONArray();
        ykeys.put("minuten");
        jsonObject.put("ykeys", ykeys);
        jsonObject.put("labels", ykeys);

        JSONArray barColors = new JSONArray();
        barColors.put("#f4f4f4");
        jsonObject.put("barColors",barColors);

        JSONArray data = new JSONArray();
        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail());

        for(GlobalTicket ticket : tickets){
            JSONObject dataElem = new JSONObject();
            dataElem.put("period" , "W-" +ticket.getWeek().substring(6,8));
            dataElem.put("minuten", ticket.getMeditationMinutes());
            data.put(dataElem);
        }

        jsonObject.put("data",data);

        return jsonObject;
    }

    public JSONObject buildJSonDataForMeditationMinutes(User user , Long year) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("element","morris-area-chart4");
        jsonObject.put("xkey", "period");
        jsonObject.put("pointSize", 0);
        jsonObject.put("fillOpacity", 0.7);
        jsonObject.put("behaveLikeLine", true);
        jsonObject.put("gridLineColor", "#e0e0e0");
        jsonObject.put("lineWidth", 0);
        jsonObject.put("smooth", false);
        jsonObject.put("hideHover", "auto");
        jsonObject.put("resize",true);

        JSONArray pointStrokeColors = new JSONArray();
        pointStrokeColors.put("#ccc");
        jsonObject.put("pointStrokeColors", pointStrokeColors);
        jsonObject.put("lineColors", pointStrokeColors);

        JSONArray ykeys = new JSONArray();
        ykeys.put("minuten");
        jsonObject.put("ykeys", ykeys);
        jsonObject.put("labels", ykeys);

        JSONArray barColors = new JSONArray();
        barColors.put("#f4f4f4");
        jsonObject.put("barColors",barColors);

        JSONArray data = new JSONArray();
        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail() , year);

        for(GlobalTicket ticket : tickets){
            JSONObject dataElem = new JSONObject();
            dataElem.put("period" , "W-" +ticket.getWeek().substring(6,8));
            dataElem.put("minuten", ticket.getMeditationMinutes());
            data.put(dataElem);
        }

        jsonObject.put("data",data);

        return jsonObject;
    }

    public JSONObject buildJSonDataForMeditationNumber(User user) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("element","morris-area-chart5");
        jsonObject.put("xkey", "period");
        jsonObject.put("pointSize", 0);
        jsonObject.put("fillOpacity", 0.7);
        jsonObject.put("behaveLikeLine", true);
        jsonObject.put("gridLineColor", "#e0e0e0");
        jsonObject.put("lineWidth", 0);
        jsonObject.put("smooth", false);
        jsonObject.put("hideHover", "auto");
        jsonObject.put("resize",true);

        JSONArray pointStrokeColors = new JSONArray();
        pointStrokeColors.put("#ccc");
        jsonObject.put("pointStrokeColors", pointStrokeColors);
        jsonObject.put("lineColors", pointStrokeColors);

        JSONArray ykeys = new JSONArray();
        ykeys.put("Anzahl");
        jsonObject.put("ykeys", ykeys);
        jsonObject.put("labels", ykeys);

        JSONArray barColors = new JSONArray();
        barColors.put("#f4f4f4");
        jsonObject.put("barColors",barColors);

        JSONArray data = new JSONArray();
        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail());

        for(GlobalTicket ticket : tickets){
            JSONObject dataElem = new JSONObject();
            dataElem.put("period" , "W-" +ticket.getWeek().substring(6,8));
            dataElem.put("Anzahl", ticket.getMeditationNumber());
            data.put(dataElem);
        }

        jsonObject.put("data",data);

        return jsonObject;
    }

    public JSONObject buildJSonDataForMeditationNumber(User user , Long year) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("element","morris-area-chart5");
        jsonObject.put("xkey", "period");
        jsonObject.put("pointSize", 0);
        jsonObject.put("fillOpacity", 0.7);
        jsonObject.put("behaveLikeLine", true);
        jsonObject.put("gridLineColor", "#e0e0e0");
        jsonObject.put("lineWidth", 0);
        jsonObject.put("smooth", false);
        jsonObject.put("hideHover", "auto");
        jsonObject.put("resize",true);

        JSONArray pointStrokeColors = new JSONArray();
        pointStrokeColors.put("#ccc");
        jsonObject.put("pointStrokeColors", pointStrokeColors);
        jsonObject.put("lineColors", pointStrokeColors);

        JSONArray ykeys = new JSONArray();
        ykeys.put("Anzahl");
        jsonObject.put("ykeys", ykeys);
        jsonObject.put("labels", ykeys);

        JSONArray barColors = new JSONArray();
        barColors.put("#f4f4f4");
        jsonObject.put("barColors",barColors);

        JSONArray data = new JSONArray();
        List<GlobalTicket> tickets = reportGlobalService.getGlobalTicketOwnerByMail(user.getEmail() , year);

        for(GlobalTicket ticket : tickets){
            JSONObject dataElem = new JSONObject();
            dataElem.put("period" , "W-" +ticket.getWeek().substring(6,8));
            dataElem.put("Anzahl", ticket.getMeditationNumber());
            data.put(dataElem);
        }

        jsonObject.put("data",data);

        return jsonObject;
    }
}
