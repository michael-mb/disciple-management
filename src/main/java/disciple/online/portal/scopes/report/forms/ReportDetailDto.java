package disciple.online.portal.scopes.report.forms;

import disciple.online.portal.util.validation.Regexes;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ReportDetailDto {
    @NotBlank
    @NotNull
    public String startDate;

    @NotBlank
    @NotNull
    public String endDate;

    public long bibleChapter;

    public long meditationNumber;

    private long meditationMinutes;

    private long prayerMinutes;

    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    @Value("")
    public String book;

    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    @Value("")
    public String message;


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
        return this.meditationMinutes;
    }

    public long getPrayerMinutes() {
        return this.prayerMinutes;
    }

    public void setMeditationMinutes(long meditationMinutes) {
        this.meditationMinutes = meditationMinutes;
    }

    public void setPrayerMinutes(long prayerMinutes) {
        this.prayerMinutes = prayerMinutes;
    }

    @Override
    public String toString(){
        return "ReportDetailDto{"+
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", bibleChapter='" + bibleChapter + '\'' +
                ", meditationNumber='" + meditationNumber + '\'' +
                ", meditationMinutes='" + meditationMinutes + '\'' +
                ", prayerMinutes='" + prayerMinutes + '\'' +
                ", book='" + book + '\'' +
                ", message='" + message +
                '}' ;
    }
}
