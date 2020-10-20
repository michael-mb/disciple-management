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

    public Long getFast() {
        return fast;
    }

    public void setFast(Long fast) {
        this.fast = fast;
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
