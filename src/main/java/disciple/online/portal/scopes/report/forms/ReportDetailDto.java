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

    public long bibleChapterMinutes;

    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    @Value("")
    public String book;

    public long prayerMinutesAlone;

    public long prayerMinutesTogether;

    public long meditationNumber;

    public long meditationMinutes;

    public long evangelizationMinutes;

    public long evangelizedPeople;


    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    @Value("")
    public String message;

    public long fast;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getFast() {
        return fast;
    }

    public void setFast(long fast) {
        this.fast = fast;
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


    public long getBibleChapter() {
        return bibleChapter;
    }

    public void setBibleChapter(long bibleChapter) {
        this.bibleChapter = bibleChapter;
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

    @Override
    public String toString(){
        return "ReportDetailDto{" +
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
