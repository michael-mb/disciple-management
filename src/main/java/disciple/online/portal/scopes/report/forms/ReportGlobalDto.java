package disciple.online.portal.scopes.report.forms;

import disciple.online.portal.scopes.report.entities.ReportStatus;
import disciple.online.portal.util.validation.Regexes;
import disciple.online.portal.util.validation.ValidEmail;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

public class ReportGlobalDto {
    
    
    @NotBlank
    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    public String week;

    public Long bibleChapter;

    public Long bibleChapterMinutes;

    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    @Value("")
    public String book;

    public Long prayerMinutesAlone;

    public Long prayerMinutesTogether;

    public Long meditationNumber;
    
    public Long meditationMinutes;

    public Long evangelizationMinutes;

    public Long evangelizedPeople;


    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    @Value("")
    public String message;

    public Long fast;

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

    public long getFast() {
        return fast;
    }

    public void setFast(long fast) {
        this.fast = fast;
    }

    @Override
    public String toString(){
        return "ReportGlobalDto{" +
                " week='" + week + '\'' +
                ", bibleChapter='" + bibleChapter + '\'' +
                ", bibleChapterMinutes='" + bibleChapterMinutes + '\'' +
                ", book='" + book + '\'' +
                ", prayerMinutesAlone='" + prayerMinutesAlone + '\'' +
                ", prayerMinutesTogether='" + prayerMinutesTogether + '\'' +
                ", meditationNumber='" + meditationNumber + '\'' +
                ", meditationMinutes='" + meditationMinutes + '\'' +
                ", evangelizationMinutes='" + evangelizationMinutes + '\'' +
                ", evangelizedPeople='" + evangelizedPeople + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
