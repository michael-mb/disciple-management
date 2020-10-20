package disciple.online.portal.Pages.reportpages;

import disciple.online.portal.Pages.Header;
import disciple.online.portal.scopes.report.forms.ReportDetailDto;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReportDetailPage extends Header {
    @FindBy(id = "startDate")
    private WebElement fieldStartDate;

    @FindBy(id = "endDate")
    private WebElement fieldEndDate;

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
    
    public ReportDetailPage(WebDriver driver) {
        super(driver);
    }

    public ReportOverviewPage report(ReportDetailDto reportDetailDto){
        sendKeysToElement(fieldStartDate , reportDetailDto.startDate);
        sendKeysToElement(fieldEndDate , reportDetailDto.endDate);
        sendKeysToElement(fieldBibleChapter , reportDetailDto.bibleChapter.toString());
        sendKeysToElement(fieldBibleChapterMinutes , reportDetailDto.bibleChapterMinutes.toString());
        sendKeysToElement(fieldBook,reportDetailDto.book);
        sendKeysToElement(fieldPrayerMinutesAlone , reportDetailDto.prayerMinutesAlone.toString());
        sendKeysToElement(fieldPrayerMinutesTogether , reportDetailDto.prayerMinutesTogether.toString());
        sendKeysToElement(fieldMeditationNumber, reportDetailDto.meditationNumber.toString());
        sendKeysToElement(fieldMeditationMinutes, reportDetailDto.meditationMinutes.toString());
        sendKeysToElement(fieldEvangelizationMinutes, reportDetailDto.evangelizationMinutes.toString());
        sendKeysToElement(fieldEvangelizedPeople, reportDetailDto.evangelizedPeople.toString());
        sendKeysToElement(fieldFast, reportDetailDto.fast.toString());
        sendKeysToElement(fieldMessage,reportDetailDto.message);
        clickElement(reportBtn);
        return new ReportOverviewPage(driver);
    }
    
}
