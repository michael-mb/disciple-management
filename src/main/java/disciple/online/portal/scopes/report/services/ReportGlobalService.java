package disciple.online.portal.scopes.report.services;

import disciple.online.portal.scopes.report.entities.GlobalTicket;
import disciple.online.portal.scopes.report.entities.ReportStatus;
import disciple.online.portal.scopes.report.forms.ReportGlobalDto;
import disciple.online.portal.scopes.report.repositories.ReportGlobalRepository;
import disciple.online.portal.scopes.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportGlobalService {

    @Autowired
    private ReportGlobalRepository reportGlobalRepository;

    public void createGlobalTicket(ReportGlobalDto reportGlobalDto , User user){
        if(reportGlobalDto == null) throw new NullPointerException("reportGlobalDto should not be null");
        if(user == null) throw new NullPointerException("user should not be null");

        GlobalTicket globalTicket = new GlobalTicket(new HashSet<ReportStatus>(Arrays.asList(ReportStatus.NOTSEEN)),
                reportGlobalDto.week ,reportGlobalDto.bibleChapter, reportGlobalDto.bibleChapterMinutes,
                reportGlobalDto.book, reportGlobalDto.prayerMinutesAlone, reportGlobalDto.prayerMinutesTogether,
                reportGlobalDto.meditationNumber, reportGlobalDto.meditationMinutes, reportGlobalDto.evangelizationMinutes,
                reportGlobalDto.evangelizedPeople , reportGlobalDto.message,reportGlobalDto.fast,
                user.getEmail() , user.getDiscipleMakerMail());

        reportGlobalRepository.save(globalTicket);
    }

    public void updateGlobalTicket(ReportGlobalDto reportGlobalDto , GlobalTicket ticket){
        if(reportGlobalDto == null) throw new NullPointerException("reportGlobalDto should not be null");

        ticket.setWeek(reportGlobalDto.week);
        ticket.setBibleChapter(reportGlobalDto.bibleChapter);
        ticket.setBibleChapterMinutes(reportGlobalDto.bibleChapterMinutes);
        ticket.setBook(reportGlobalDto.book);
        ticket.setPrayerMinutesAlone(reportGlobalDto.getPrayerMinutesAlone());
        ticket.setPrayerMinutesTogether(reportGlobalDto.getPrayerMinutesTogether());
        ticket.setMeditationNumber(reportGlobalDto.meditationNumber);
        ticket.setMeditationMinutes(reportGlobalDto.getMeditationMinutes());
        ticket.setEvangelizationMinutes(reportGlobalDto.evangelizationMinutes);
        ticket.setEvangelizedPeople(reportGlobalDto.evangelizedPeople);
        ticket.setMessage(reportGlobalDto.message);

        reportGlobalRepository.save(ticket);
    }

    public void updateGlobalTicketMessage(ReportGlobalDto reportGlobalDto , GlobalTicket ticket){
        ticket.setMessage(reportGlobalDto.message);
        reportGlobalRepository.save(ticket);
    }

    public void saveGlobalTicket(GlobalTicket ticket){
        reportGlobalRepository.save(ticket);
    }

    public Streamable<GlobalTicket> findAll(){
        return reportGlobalRepository.findAll();
    }

    public List<GlobalTicket> getGlobalTicketOwnerByMail(String email){
        List<GlobalTicket> tickets = new ArrayList<>();

        for(GlobalTicket ticket : reportGlobalRepository.findAllByOrderByWeekDesc()){
            if(ticket.getOwnerMail().equals(email))
                tickets.add(ticket);
        }

        return tickets;
    }

    public List<GlobalTicket> getGlobalTicketOwnerByMail(String email , Long year){
        List<GlobalTicket> tickets = new ArrayList<>();
        for(GlobalTicket ticket : reportGlobalRepository.findAllByOrderByWeekDesc()){
            if(ticket.getOwnerMail().equals(email)){
                if(ticket.getWeek().contains(year.toString()))
                    tickets.add(ticket);
            }
        }
        return tickets;
    }

    public List<GlobalTicket> getGlobalTicketForDiscipleMaker(String email){
        List<GlobalTicket> tickets = new ArrayList<>();

        for(GlobalTicket ticket : reportGlobalRepository.findAllByOrderByWeekDesc()){
            if(ticket.getDiscipleMakerMail().equals(email))
                tickets.add(ticket);
        }
        return tickets;
    }


    public int getTotalPrayerMinutesAlone(User user , Long year){
        List<GlobalTicket> tickets = getGlobalTicketOwnerByMail(user.getEmail(),year);
        int prayerMinutesAlone = 0;

        for(GlobalTicket ticket : tickets){
            prayerMinutesAlone += ticket.getPrayerMinutesAlone();
        }
        return prayerMinutesAlone;
    }

    public int getTotalPrayerMinutesTogether(User user , Long year){
        List<GlobalTicket> tickets = getGlobalTicketOwnerByMail(user.getEmail(),year);
        int prayerMinutesTogether = 0;

        for(GlobalTicket ticket : tickets){
            prayerMinutesTogether += ticket.getPrayerMinutesTogether();
        }
        return prayerMinutesTogether;
    }

    public int getTotalMeditationMinutes(User user , Long year){
        List<GlobalTicket> tickets = getGlobalTicketOwnerByMail(user.getEmail(),year);
        int meditationMinutes = 0;

        for(GlobalTicket ticket : tickets){
            meditationMinutes += ticket.getMeditationMinutes();
        }
        return meditationMinutes;
    }

    public int getTotalMeditationNumber(User user , Long year){
        List<GlobalTicket> tickets = getGlobalTicketOwnerByMail(user.getEmail(),year);
        int meditation = 0;

        for(GlobalTicket ticket : tickets){
            meditation += ticket.getMeditationNumber();
        }
        return meditation;
    }

    public int getTotalBibleChapter(User user , Long year){
        List<GlobalTicket> tickets = getGlobalTicketOwnerByMail(user.getEmail(),year);
        int chapters = 0;

        for(GlobalTicket ticket : tickets){
            chapters += ticket.getBibleChapter();
        }
        return chapters;
    }

    public int getTotalBibleReadingMinutes(User user , Long year){
        List<GlobalTicket> tickets = getGlobalTicketOwnerByMail(user.getEmail(),year);
        int minutes = 0;

        for(GlobalTicket ticket : tickets){
            minutes += ticket.getBibleChapterMinutes();
        }
        return minutes;
    }

    public int getTotalEvangelizationMinutes(User user , Long year){
        List<GlobalTicket> tickets = getGlobalTicketOwnerByMail(user.getEmail(),year);
        int minutes = 0;

        for(GlobalTicket ticket : tickets){
            minutes += ticket.getEvangelizationMinutes();
        }
        return minutes;
    }

    public int getTotalEvangelizedPeople(User user , Long year){
        List<GlobalTicket> tickets = getGlobalTicketOwnerByMail(user.getEmail(),year);
        int minutes = 0;

        for(GlobalTicket ticket : tickets){
            minutes += ticket.getEvangelizedPeople();
        }
        return minutes;
    }

    public int getTotalFastDays(User user , Long year){
        List<GlobalTicket> tickets = getGlobalTicketOwnerByMail(user.getEmail(),year);
        int days = 0;

        for(GlobalTicket ticket : tickets){
            days += ticket.getFast();
        }
        return days;
    }

    public Optional<GlobalTicket> getGlobalTicketById(long id){
        return reportGlobalRepository.findById(id);
    }

}
