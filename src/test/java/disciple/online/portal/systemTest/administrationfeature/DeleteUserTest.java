package disciple.online.portal.systemTest.administrationfeature;

import disciple.online.portal.Pages.reportpages.DiscipleOverviewPage;
import disciple.online.portal.Pages.reportpages.ReportGlobalPage;
import disciple.online.portal.Pages.reportpages.ReportOverviewPage;
import disciple.online.portal.Pages.standartpages.LoginPage;
import disciple.online.portal.Pages.standartpages.MainPage;
import disciple.online.portal.Pages.standartpages.RegisterPage;
import disciple.online.portal.Pages.standartpages.UsersPage;
import disciple.online.portal.SystemTest;
import disciple.online.portal.scopes.report.forms.ReportGlobalDto;
import disciple.online.portal.scopes.user.entities.TestUser;
import disciple.online.portal.statics.RegisterUser;
import disciple.online.portal.statics.Url;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

public class DeleteUserTest extends SystemTest {
    @Test
    @Description("Try to delete a disciple")
    public void successfulDeleteDisciple(){
        MainPage mainPage = regularLogin(TestUser.ADMIN.mailAddress, TestUser.ADMIN.password);
        UsersPage usersPage = mainPage.goToUsersPage();
        usersPage.deleteUser(TestUser.DISCIPLE_ONE);
        assertThrows(NoSuchElementException.class, () -> usersPage.issetUser(TestUser.DISCIPLE_ONE));
    }

    @Test
    @Description("delete a user, who reported")
    public void successfulDeleteDiscipleWhoReported(){
        RegisterUser registerUser = new RegisterUser("saha" , "simo" , "sahasimo@yahoo.fr",
                "nathan007X+" , "nathan007X+" , "dresden" , "kamerunerstraße 42", "445223",
                "admin@admin.com");

        //doing the actual registration
        driver.get(Url.getUrlLoginpage());
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = loginPage.goToRegisterPage();
        MainPage mainPage = registerPage.register(registerUser);
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
        logout(reportOverviewPage);

        mainPage = regularLogin(TestUser.ADMIN);
        UsersPage usersPage = mainPage.goToUsersPage();
        usersPage.deleteUser("sahasimo@yahoo.fr");
        assertThrows(NoSuchElementException.class, () -> usersPage.issetUser("sahasimo@yahoo.fr"));
        DiscipleOverviewPage discipleOverviewPage = mainPage.goToDiscipleOverview();
        assertThrows(NoSuchElementException.class, () -> discipleOverviewPage.assertReport("sahasimo@yahoo.fr" , "2020-W34"));
    }

    @Test
    @Description("Try to delete an discipleMaker / admin")
    public void deleteDiscipleMaker(){
        MainPage mainPage = regularLogin(TestUser.ADMIN.mailAddress, TestUser.ADMIN.password);
        UsersPage usersPage = mainPage.goToUsersPage();
        assertThrows(NoSuchElementException.class, () -> usersPage.deleteUser(TestUser.DISCIPLEMAKER_ONE));
    }
}
