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
    public DetailTicket(){}

    public DetailTicket(Set<ReportStatus>  status , String startDate , String endDate , long bibleChapter , long meditationNumber ,
                        long meditationMinutes , long prayerMinutes , String book , String message , String ownerMail , String discipleMakerMail){
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
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
                ", startDate='" + startDate+ '\'' +
                ", endDate='" + endDate+ '\'' +
                ", bibleChapter='" + bibleChapter + '\'' +
                ", meditationNumber='" + meditationNumber + '\'' +
                ", meditationNumber='" + meditationNumber + '\'' +
                ", meditationMinutes='" + meditationMinutes + '\'' +
                ", prayerMinutes='" + prayerMinutes + '\'' +
                ", book='" + book + '\'' +
                ", message'" + message + '\'' +
                '}';
    }
}
