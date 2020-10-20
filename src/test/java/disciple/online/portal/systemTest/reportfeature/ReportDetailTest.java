package disciple.online.portal.systemTest.reportfeature;

import disciple.online.portal.Pages.reportpages.DiscipleOverviewPage;
import disciple.online.portal.Pages.reportpages.ReportDetailPage;
import disciple.online.portal.Pages.reportpages.ReportOverviewPage;
import disciple.online.portal.Pages.standartpages.MainPage;
import disciple.online.portal.SystemTest;
import disciple.online.portal.scopes.report.forms.ReportDetailDto;
import disciple.online.portal.scopes.user.entities.TestUser;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static org.testng.Assert.assertTrue;

public class ReportDetailTest extends SystemTest {
    @Test
    @Description("Report Detail with a disciple")
    public void reportDetailWithDisciple(){
        MainPage mainPage = regularLogin(TestUser.DISCIPLE_ONE.mailAddress, TestUser.DISCIPLE_ONE.password);
        ReportDetailPage reportDetailPage = mainPage.goToReportDetail();

        ReportDetailDto reportDetailDto = new ReportDetailDto();
        reportDetailDto.setStartDate("30-10-2020");
        reportDetailDto.setEndDate("02-11-2020");
        reportDetailDto.setBibleChapter(20L);
        reportDetailDto.setBibleChapterMinutes(20L);
        reportDetailDto.setBook("le chemin de l'obeissance");
        reportDetailDto.setPrayerMinutesAlone(20L);
        reportDetailDto.setPrayerMinutesTogether(20L);
        reportDetailDto.setMeditationNumber(7L);
        reportDetailDto.setMeditationMinutes(120L);
        reportDetailDto.setEvangelizationMinutes(20L);
        reportDetailDto.setEvangelizedPeople(20L);
        reportDetailDto.setMessage("Hallo");
        reportDetailDto.setFast(15L);

        ReportOverviewPage reportOverviewPage = reportDetailPage.report(reportDetailDto);
        assertTrue(reportOverviewPage.isReportDetailVisible("2020-10-30","2020-11-02"), "unable to locate the report");
        logout(reportDetailPage);
    }
}
