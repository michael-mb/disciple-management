package disciple.online.portal.systemTest.reportfeature;

import disciple.online.portal.Pages.reportpages.DiscipleOverviewPage;
import disciple.online.portal.Pages.reportpages.ReportGlobalPage;
import disciple.online.portal.Pages.reportpages.ReportOverviewPage;
import disciple.online.portal.Pages.standartpages.MainPage;
import disciple.online.portal.SystemTest;
import disciple.online.portal.scopes.report.forms.ReportGlobalDto;
import disciple.online.portal.scopes.user.entities.TestUser;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static org.testng.Assert.assertTrue;

public class ReportGlobalTest extends SystemTest {
    @Test
    @Description("Report Global with a disciple")
    public void reportGlobalWithDisciple(){
        MainPage mainPage = regularLogin(TestUser.DISCIPLE_ONE.mailAddress, TestUser.DISCIPLE_ONE.password);
        ReportGlobalPage reportGlobalPage = mainPage.goToReportGlobal();

        ReportGlobalDto reportGlobalDto = new ReportGlobalDto();
        reportGlobalDto.setWeek("W34-2020");
        reportGlobalDto.setBibleChapter(20L);
        reportGlobalDto.setBibleChapterMinutes(20L);
        reportGlobalDto.setBook("le chemin de l'obeissance");
        reportGlobalDto.setPrayerMinutesAlone(20L);
        reportGlobalDto.setPrayerMinutesTogether(20L);
        reportGlobalDto.setMeditationNumber(7L);
        reportGlobalDto.setMeditationMinutes(120L);
        reportGlobalDto.setEvangelizationMinutes(20L);
        reportGlobalDto.setEvangelizedPeople(20L);
        reportGlobalDto.setMessage("Hallo");
        reportGlobalDto.setFast(15L);

        ReportOverviewPage reportOverviewPage = reportGlobalPage.report(reportGlobalDto);

        // Because of the pattern
        assertTrue(reportOverviewPage.isReportGlobalVisible("2020-W34"), "unable to locate the report");

        // check if the Maker disciple has received this report
        logout(reportGlobalPage);

        mainPage = regularLogin(TestUser.DISCIPLEMAKER_ONE.mailAddress,TestUser.DISCIPLE_ONE.password);
        DiscipleOverviewPage discipleOverviewPage = mainPage.goToDiscipleOverview();
        assertTrue(discipleOverviewPage.assertReport(TestUser.DISCIPLE_ONE , "2020-W34"), "unable to locate the report by the disciplemaker");
    }

}
