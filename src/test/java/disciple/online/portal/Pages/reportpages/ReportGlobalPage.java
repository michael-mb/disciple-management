package disciple.online.portal.Pages.reportpages;

import disciple.online.portal.Pages.Header;
import disciple.online.portal.scopes.report.forms.ReportGlobalDto;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReportGlobalPage extends Header {

    @FindBy(id = "week")
    private WebElement fieldWeek;

    @FindBy(id = "bibleChapter")
    private WebElement fieldBibleChapter;

    @FindBy(id = "bibleChapterMinutes")
    private WebElement fieldBibleChapterMinutes;

    @FindBy(id = "book")
    private WebElement fieldBook;

    @FindBy(id = "prayerMinutesAlone")
    private WebElement fieldPrayerMinutesAlone;

    @FindBy(id = "prayerMinutesTogether")
    private WebElement fieldPrayerMinutesTogether;

    @FindBy(id = "meditationNumber")
    private WebElement fieldMeditationNumber;

    @FindBy(id = "meditationMinutes")
    private WebElement fieldMeditationMinutes;

    @FindBy(id = "evangelizationMinutes")
    private WebElement fieldEvangelizationMinutes;

    @FindBy(id = "evangelizedPeople")
    private WebElement fieldEvangelizedPeople;

    @FindBy(id = "fast")
    private WebElement fieldFast;

    @FindBy(id = "message")
    private WebElement fieldMessage;

    @FindBy(id = "report-btn")
    private WebElement reportBtn;

    public ReportGlobalPage(WebDriver driver) {
        super(driver);
    }

    public ReportOverviewPage report(ReportGlobalDto reportGlobalDto){
        sendKeysToElement(fieldWeek , reportGlobalDto.week);
        sendKeysToElement(fieldBibleChapter , reportGlobalDto.bibleChapter.toString());
        sendKeysToElement(fieldBibleChapterMinutes , reportGlobalDto.bibleChapterMinutes.toString());
        sendKeysToElement(fieldBook,reportGlobalDto.book);
        sendKeysToElement(fieldPrayerMinutesAlone , reportGlobalDto.prayerMinutesAlone.toString());
        sendKeysToElement(fieldPrayerMinutesTogether , reportGlobalDto.prayerMinutesTogether.toString());
        sendKeysToElement(fieldMeditationNumber, reportGlobalDto.meditationNumber.toString());
        sendKeysToElement(fieldMeditationMinutes, reportGlobalDto.meditationMinutes.toString());
        sendKeysToElement(fieldEvangelizationMinutes, reportGlobalDto.evangelizationMinutes.toString());
        sendKeysToElement(fieldEvangelizedPeople, reportGlobalDto.evangelizedPeople.toString());
        sendKeysToElement(fieldFast, reportGlobalDto.fast.toString());
        sendKeysToElement(fieldMessage,reportGlobalDto.message);
        clickElement(reportBtn);
        return new ReportOverviewPage(driver);
    }
}
