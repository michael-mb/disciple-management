package disciple.online.portal.scopes.report.entities;

import disciple.online.portal.scopes.user.entities.UserRole;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GlobalTicket {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @ElementCollection
    @Fetch(FetchMode.JOIN)
    @NotNull
    private Set<ReportStatus> status = new HashSet<>();

    @Column
    private String week;

    @Column
    private Long bibleChapter;

    @Column
    private Long bibleChapterMinutes;

    @Column
    private String book;

    @Column
    private Long prayerMinutesAlone;

    @Column
    private Long prayerMinutesTogether;

    @Column
    private Long meditationNumber;

    @Column
    private Long meditationMinutes;

    @Column
    private Long evangelizationMinutes;

    @Column
    private Long evangelizedPeople;

    @Column
    private long fast;

    @Column
    @Size(max = 5000)
    private String message;

    @Column
    private String ownerMail;

    @Column
    private String discipleMakerMail;

    @SuppressWarnings("unused")
    public GlobalTicket(){}

    public GlobalTicket(Set<ReportStatus>  status , String week , Long bibleChapter , Long bibleChapterMinutes ,
                        String book ,  Long prayerMinutesAlone ,  Long prayerMinutesTogether , Long meditationNumber ,
                        Long meditationMinutes , Long evangelizationMinutes , Long evangelizedPeople,  String message ,
                        Long fast, String ownerMail , String discipleMakerMail){
        this.status = status;
        this.week = week;

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

    public Long getEvangelizedPeople() {
        return evangelizedPeople;
    }

    public void setEvangelizedPeople(Long evangelizedPeople) {
        this.evangelizedPeople = evangelizedPeople;
    }

    public Long getEvangelizationMinutes() {
        return evangelizationMinutes;
    }

    public void setEvangelizationMinutes(Long evangelizationMinutes) {
        this.evangelizationMinutes = evangelizationMinutes;
    }

    public Long getMeditationMinutes() {
        return meditationMinutes;
    }

    public void setMeditationMinutes(Long meditationMinutes) {
        this.meditationMinutes = meditationMinutes;
    }

    public Long getMeditationNumber() {
        return meditationNumber;
    }

    public void setMeditationNumber(Long meditationNumber) {
        this.meditationNumber = meditationNumber;
    }

    public Long getPrayerMinutesTogether() {
        return prayerMinutesTogether;
    }

    public void setPrayerMinutesTogether(Long prayerMinutesTogether) {
        this.prayerMinutesTogether = prayerMinutesTogether;
    }

    public Long getPrayerMinutesAlone() {
        return prayerMinutesAlone;
    }

    public void setPrayerMinutesAlone(Long prayerMinutesAlone) {
        this.prayerMinutesAlone = prayerMinutesAlone;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public Long getBibleChapterMinutes() {
        return bibleChapterMinutes;
    }

    public void setBibleChapterMinutes(Long bibleChapterMinutes) {
        this.bibleChapterMinutes = bibleChapterMinutes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBibleChapter() {
        return bibleChapter;
    }

    public void setBibleChapter(Long bibleChapter) {
        this.bibleChapter = bibleChapter;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
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
        return "GlobalTicket{" +
                "id="+id +
                ", status='" + status + '\'' +
                ", week='" + week + '\'' +
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
