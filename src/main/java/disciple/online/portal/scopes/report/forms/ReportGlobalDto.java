package disciple.online.portal.scopes.report.forms;

import disciple.online.portal.util.validation.Regexes;
import disciple.online.portal.util.validation.ValidEmail;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ReportGlobalDto {
    @NotBlank
    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    public String week;

    public long bibleChapter;

    public long meditationNumber;

    public long meditationMinutes;

    public long prayerMinutes;

    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    @Value("")
    public String book;

    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    @Value("")
    public String message;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public long getBibleChapter() {
        return bibleChapter;
    }

    public void setBibleChapter(long bibleChapter) {
        this.bibleChapter = bibleChapter;
    }

    public long getMeditationNumber() {
        return meditationNumber;
    }

    public void setMeditationNumber(long meditationNumber) {
        this.meditationNumber = meditationNumber;
    }

    public long getMeditationMinutes() {
        return meditationMinutes;
    }

    public void setMeditationMinutes(long meditationMinutes) {
        this.meditationMinutes = meditationMinutes;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getPrayerMinutes() {
        return prayerMinutes;
    }

    public void setPrayerMinutes(long prayerMinutes) {
        this.prayerMinutes = prayerMinutes;
    }

    @Override
    public String toString(){
        return "ReportGlobalDto{"+
                "week='" + week + '\'' +
                ", bibleChapter='" + bibleChapter + '\'' +
                ", meditationNumber='" + meditationNumber + '\'' +
                ", meditationMinutes='" + meditationMinutes + '\'' +
                ", prayerMinutes='" + prayerMinutes + '\'' +
                ", book='" + book + '\'' +
                ", message='" + message +
                '}' ;
    }


}
