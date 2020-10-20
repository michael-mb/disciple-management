package disciple.online.portal.scopes.report.services;

import disciple.online.portal.scopes.report.entities.DetailTicket;
import disciple.online.portal.scopes.report.entities.GlobalTicket;
import disciple.online.portal.scopes.report.entities.ReportStatus;
import disciple.online.portal.scopes.report.forms.ReportDetailDto;
import disciple.online.portal.scopes.report.repositories.ReportDetailRepository;
import disciple.online.portal.scopes.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

@Service
public class ReportDetailService {
    @Autowired
    private ReportDetailRepository reportDetailRepository;

    @Autowired
    private ReportGlobalService reportGlobalService;

    public void createDetailTicket(ReportDetailDto reportDetailDto, User user){
        if(reportDetailDto == null) throw new NullPointerException("reportDetailDto should not be null");
        if(user == null) throw new NullPointerException("user should not be null");

        DetailTicket detailTicket = new DetailTicket(new HashSet<ReportStatus>(Arrays.asList(ReportStatus.NOTSEEN)),
                reportDetailDto.startDate , reportDetailDto.endDate ,reportDetailDto.bibleChapter,
                reportDetailDto.bibleChapterMinutes, reportDetailDto.book, reportDetailDto.prayerMinutesAlone,
                reportDetailDto.prayerMinutesTogether, reportDetailDto.meditationNumber, reportDetailDto.meditationMinutes,
                reportDetailDto.evangelizationMinutes, reportDetailDto.evangelizedPeople, reportDetailDto.message, reportDetailDto.fast,
                user.getEmail() , user.getDiscipleMakerMail());

        reportDetailRepository.save(detailTicket);
    }

    public void updateDetailTicket(ReportDetailDto reportDetailDto , DetailTicket ticket){
        if(reportDetailDto == null) throw new NullPointerException("reportDetailDto should not be null");


        ticket.setStartDate(reportDetailDto.startDate);
        ticket.setEndDate(reportDetailDto.endDate);
        ticket.setBibleChapter(reportDetailDto.bibleChapter);
        ticket.setBibleChapterMinutes(reportDetailDto.bibleChapterMinutes);
        ticket.setBook(reportDetailDto.book);
        ticket.setPrayerMinutesAlone(reportDetailDto.getPrayerMinutesAlone());
        ticket.setPrayerMinutesTogether(reportDetailDto.getPrayerMinutesTogether());
        ticket.setMeditationNumber(reportDetailDto.meditationNumber);
        ticket.setMeditationMinutes(reportDetailDto.getMeditationMinutes());
        ticket.setEvangelizationMinutes(reportDetailDto.evangelizationMinutes);
        ticket.setEvangelizedPeople(reportDetailDto.evangelizedPeople);
        ticket.setMessage(reportDetailDto.message);
        ticket.setFast(reportDetailDto.fast);

        reportDetailRepository.save(ticket);
    }

    public void updateDetailTicketMessage(ReportDetailDto reportDetailDto , DetailTicket ticket){
        ticket.setMessage(reportDetailDto.message);
        reportDetailRepository.save(ticket);
    }

    public void saveDetailTicket(DetailTicket ticket){
        reportDetailRepository.save(ticket);
    }

    public Streamable<DetailTicket> findAll(){
        return reportDetailRepository.findAll();
    }

    public List<DetailTicket> getDetailTicketOwnerByMail(String email){
        List<DetailTicket> tickets = new ArrayList<>();

        for(DetailTicket ticket : reportDetailRepository.findAllByOrderByEndDateDesc()){
            if(ticket.getOwnerMail().equals(email))
                tickets.add(ticket);
        }
        return tickets;
    }

    public List<DetailTicket> getDetailTicketForDiscipleMaker(String email){
        List<DetailTicket> tickets = new ArrayList<>();

        for(DetailTicket ticket : reportDetailRepository.findAllByOrderByEndDateDesc()){
            if(ticket.getDiscipleMakerMail().equals(email))
                tickets.add(ticket);
        }
        return tickets;
    }

    public void resumeReport(User user){
        List<DetailTicket> tickets = getDetailTicketOwnerByMail(user.getEmail());

        if(tickets.isEmpty()){
            return;
        }

        Calendar calendar = Calendar.getInstance(Locale.GERMAN);
        String endDate;
        long week = 0;
        long bibleChapter = 0;
        long bibleChapterMinutes = 0;
        String book = "";
        long prayerMinutesAlone = 0;
        long prayerMinutesTogether = 0;
        long meditationNumber = 0;
        long meditationMinutes = 0;
        long evangelizationMinutes = 0;
        long evangelizedPeople = 0;
        String message = "";
        long fast = 0;

        String ownerMail = user.getEmail();
        String discipleMakerMail = user.getDiscipleMakerMail();

        String newLine = System.getProperty("line.separator");

        for(DetailTicket detailTicket : tickets){
            endDate = detailTicket.getEndDate();

            calendar.set(Integer.parseInt(endDate.substring(0,4)) ,
                Integer.parseInt(endDate.substring(5,7)) , Integer.parseInt(endDate.substring(8,10)));

            LocalDate date = LocalDate.of(Integer.parseInt(endDate.substring(0,4)) ,
                    Integer.parseInt(endDate.substring(5,7)) , Integer.parseInt(endDate.substring(8,10)));

            if(week < date.get(WeekFields.of(Locale.GERMANY).weekOfYear())){
                week = date.get(WeekFields.of(Locale.GERMANY).weekOfYear());
            }

            bibleChapter += detailTicket.getBibleChapter();
            bibleChapterMinutes += detailTicket.getBibleChapterMinutes();
            prayerMinutesAlone += detailTicket.getPrayerMinutesAlone();
            prayerMinutesTogether += detailTicket.getPrayerMinutesTogether();
            meditationNumber += detailTicket.getMeditationNumber();
            meditationMinutes += detailTicket.getMeditationMinutes();
            evangelizationMinutes += detailTicket.getEvangelizationMinutes();
            evangelizedPeople += detailTicket.getEvangelizedPeople();

            book = book + " ; " + detailTicket.getBook() + " ; ";
            message = message + newLine + detailTicket.getMessage() + newLine;
            fast += detailTicket.getFast();
        }

        GlobalTicket globalTicket = new GlobalTicket(new HashSet<ReportStatus>(Arrays.asList(ReportStatus.NOTSEEN)) ,
                "" , bibleChapter , bibleChapterMinutes , book , prayerMinutesAlone , prayerMinutesTogether ,
                meditationNumber , meditationMinutes , evangelizationMinutes , evangelizedPeople , message , fast , ownerMail ,
                discipleMakerMail);

        globalTicket.setWeek((calendar.getTime().getYear() + 1900) + "-W" + week);
        reportGlobalService.saveGlobalTicket(globalTicket);

        for(DetailTicket detailTicket : tickets){
            reportDetailRepository.deleteById(detailTicket.getId());
        }
    }

    public void deleteAllTicketFromUser(User user){
        if (user == null) throw new NullPointerException("User must not be null.");
        for (DetailTicket ticket : getDetailTicketOwnerByMail(user.getEmail())){
            reportDetailRepository.delete(ticket);
        }
    }

    public Optional<DetailTicket> getDetailTicketById(long id){
        return reportDetailRepository.findById(id);
    }

}
