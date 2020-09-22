package disciple.online.portal.scopes.report.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class DetailTicket {
    @Id
    @GeneratedValue
    private long id;

    @Column
    @ElementCollection
    @Fetch(FetchMode.JOIN)
    @NotNull
    private Set<ReportStatus> status = new HashSet<>();

    @Column
    private String startDate;

    @Column
    private String endDate;

    @Column
    private long bibleChapter;

    @Column
    private long bibleChapterMinutes;

    @Column
    private String book;

    @Column
    private long prayerMinutesAlone;

    @Column
    private long prayerMinutesTogether;

    @Column
    private long meditationNumber;

    @Column
    private long meditationMinutes;

    @Column
    private long evangelizationMinutes;

    @Column
    private long evangelizedPeople;


    @Column
    @Size(max = 5000)
    private String message;

    @Column
    private String ownerMail;

    @Column
    private String discipleMakerMail;

    @Column
    private long fast;

    @SuppressWarnings("unused")
    public DetailTicket(){}

    public DetailTicket(Set<ReportStatus>  status , String startDate , String endDate , long bibleChapter , long bibleChapterMinutes ,
                        String book ,  long prayerMinutesAlone ,  long prayerMinutesTogether , long meditationNumber ,
                        long meditationMinutes , long evangelizationMinutes , long evangelizedPeople,  String message , long fast,
                        String ownerMail , String discipleMakerMail){
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;

        this.bibleChapter = bibleChapter;
        this.bibleChapterMinutes = bibleChapterMinutes;
        this.book = book;

        this.prayerMinutesAlone = prayerMinutesAlone;
        this.prayerMinutesTogether = prayerMinutesTogether;

        this.meditationNumber = meditationNumber;
        this.meditationMinutes = meditationMinutes;

        this.evangelizationMinutes = evangelizationMinutes;
        this.evangelizedPeople = evangelizedPeople;

        this.message = message;
        this.fast = fast;

        this.ownerMail = ownerMail;
        this.discipleMakerMail = discipleMakerMail;
    }

    public long getFast() {
        return fast;
    }

    public void setFast(long fast) {
        this.fast = fast;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Set<ReportStatus> getStatus() {
        return status;
    }

    public void setStatus(Set<ReportStatus> status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getEvangelizedPeople() {
        return evangelizedPeople;
    }

    public void setEvangelizedPeople(long evangelizedPeople) {
        this.evangelizedPeople = evangelizedPeople;
    }

    public long getEvangelizationMinutes() {
        return evangelizationMinutes;
    }

    public void setEvangelizationMinutes(long evangelizationMinutes) {
        this.evangelizationMinutes = evangelizationMinutes;
    }

    public long getMeditationMinutes() {
        return meditationMinutes;
    }

    public void setMeditationMinutes(long meditationMinutes) {
        this.meditationMinutes = meditationMinutes;
    }

    public long getMeditationNumber() {
        return meditationNumber;
    }

    public void setMeditationNumber(long meditationNumber) {
        this.meditationNumber = meditationNumber;
    }

    public long getPrayerMinutesTogether() {
        return prayerMinutesTogether;
    }

    public void setPrayerMinutesTogether(long prayerMinutesTogether) {
        this.prayerMinutesTogether = prayerMinutesTogether;
    }

    public long getPrayerMinutesAlone() {
        return prayerMinutesAlone;
    }

    public void setPrayerMinutesAlone(long prayerMinutesAlone) {
        this.prayerMinutesAlone = prayerMinutesAlone;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public long getBibleChapterMinutes() {
        return bibleChapterMinutes;
    }

    public void setBibleChapterMinutes(long bibleChapterMinutes) {
        this.bibleChapterMinutes = bibleChapterMinutes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBibleChapter() {
        return bibleChapter;
    }

    public void setBibleChapter(long bibleChapter) {
        this.bibleChapter = bibleChapter;
    }

    public String getDiscipleMakerMail() {
        return discipleMakerMail;
    }

    public void setDiscipleMakerMail(String discipleMakerMail) {
        this.discipleMakerMail = discipleMakerMail;
    }

    public String getOwnerMail() {
        return ownerMail;
    }

    public void setOwnerMail(String ownerMail) {
        this.ownerMail = ownerMail;
    }


    @Override
    public String toString(){
        return "DetailTicket{" +
                "id="+id +
                ", status='" + status + '\'' +
                ", startDate='" + startDate+ '\'' +
                ", endDate='" + endDate+ '\'' +
                ", bibleChapter='" + bibleChapter + '\'' +
                ", bibleChapterMinutes='" + bibleChapterMinutes + '\'' +
                ", book='" + book + '\'' +
                ", prayerMinutesAlone='" + prayerMinutesAlone + '\'' +
                ", prayerMinutesAlone='" + prayerMinutesTogether + '\'' +
                ", meditationNumber='" + meditationNumber + '\'' +
                ", meditationMinutes='" + meditationMinutes + '\'' +
                ", evangelizationMinutes='" + evangelizationMinutes + '\'' +
                ", evangelizedPeople='" + evangelizedPeople + '\'' +
                ", message'" + message + '\'' +
                '}';
    }
}
