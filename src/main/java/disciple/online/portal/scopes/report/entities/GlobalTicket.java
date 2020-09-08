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
    private long id;

    @Column
    @ElementCollection
    @Fetch(FetchMode.JOIN)
    @NotNull
    private Set<ReportStatus> status = new HashSet<>();

    @Column
    private String week;

    @Column
    private long bibleChapter;

    @Column
    private long meditationNumber;

    @Column
    private long meditationMinutes;

    @Column
    private long prayerMinutes;

    @Column
    private String book;

    @Column
    @Size(max = 5000)
    private String message;

    @Column
    private String ownerMail;

    @Column
    private String discipleMakerMail;

    @SuppressWarnings("unused")
    public GlobalTicket(){}

    public GlobalTicket(Set<ReportStatus>  status , String week , long bibleChapter , long meditationNumber ,
                        long meditationMinutes , long prayerMinutes ,  String book , String message , String ownerMail , String discipleMakerMail){
        this.status = status;
        this.week = week;
        this.bibleChapter = bibleChapter;
        this.meditationNumber = meditationNumber;
        this.meditationMinutes = meditationMinutes;
        this.prayerMinutes = prayerMinutes;
        this.book = book;
        this.message = message;
        this.ownerMail = ownerMail;
        this.discipleMakerMail = discipleMakerMail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public long getMeditationNumber() {
        return meditationNumber;
    }

    public void setMeditationNumber(long meditationNumber) {
        this.meditationNumber = meditationNumber;
    }

    public long getBibleChapter() {
        return bibleChapter;
    }

    public void setBibleChapter(long bibleChapter) {
        this.bibleChapter = bibleChapter;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<ReportStatus> getStatus() {
        return status;
    }

    public void setStatus(Set<ReportStatus> status){
        this.status = status;
    }

    public String getOwnerMail() {
        return ownerMail;
    }

    public void setOwnerMail(String ownerMail) {
        this.ownerMail = ownerMail;
    }

    public String getDiscipleMakerMail() {
        return discipleMakerMail;
    }

    public void setDiscipleMakerMail(String discipleMakerMail) {
        this.discipleMakerMail = discipleMakerMail;
    }

    public long getMeditationMinutes() {
        return meditationMinutes;
    }

    public void setMeditationMinutes(long meditationMinutes) {
        this.meditationMinutes = meditationMinutes;
    }

    public long getPrayerMinutes() {
        return prayerMinutes;
    }

    public void setPrayerMinutes(long prayerMinutes) {
        this.prayerMinutes = prayerMinutes;
    }

    @Override
    public String toString(){
        return "GlobalTicket{" +
                "id="+id +
                ", status='" + status + '\'' +
                ", week='" + week + '\'' +
                ", bibleChapter='" + bibleChapter + '\'' +
                ", meditationNumber='" + meditationNumber + '\'' +
                ", meditationMinutes='" + meditationMinutes + '\'' +
                ", prayerMinutes='" + prayerMinutes + '\'' +
                ", book='" + book + '\'' +
                ", message'" + message + '\'' +
                '}';
    }
}
