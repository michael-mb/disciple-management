package disciple.online.portal.Pages;

import disciple.online.portal.Pages.reportpages.DiscipleOverviewPage;
import disciple.online.portal.Pages.reportpages.ReportGlobalPage;
import disciple.online.portal.Pages.standartpages.LoginPage;
import disciple.online.portal.Pages.standartpages.MainPage;
import disciple.online.portal.Pages.standartpages.ProfilePage;
import disciple.online.portal.statics.Url;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Header for all pages
 */
public abstract class Header extends PageObject {

    @FindBy(id = "login-li")
    private WebElement linkLogin;

    /* Just displayed, when not logged in */
    @FindBy(id = "logout")
    private WebElement linkLogout;

    @FindBy(id = "linkprofile")
    private WebElement linkProfilePage;

    @FindBy (id = "linkreport")
    private WebElement linkReportPage;

    @FindBy (id = "linkreportoverview")
    private WebElement linkReportOverviewPage;

    @FindBy (id = "linkdiscipleoverview")
    private WebElement linkDiscipleOverviewPage;

    @FindBy (id = "users")
    private WebElement linkUsersPage;


    public Header(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        try {
            return linkLogin.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Go to login page")
    public LoginPage selectLoginLink() {
        if (isLoggedIn()) {
            throw new IllegalStateException("There is no login button, because user is already logged in.");
        } else {
            clickElement(linkLogin);
            return new LoginPage(driver);
        }
    }

    @Step("Logout")
    public LoginPage selectLogoutLink() {
        if (isLoggedIn()) {
            clickElement(linkLogout);
        } else {
            throw new IllegalStateException("There is no logout button, because user is not logged in.");
        }
        return new LoginPage(driver);
    }

    /*
    Checks whether the user is logged in or not.
    The header is different in both states.
     */
    public boolean isLoggedIn() {
        try {
            linkLogin.isDisplayed();
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public WebElement getLinkLogin() {
        return linkLogin;
    }

    public WebElement getLinkLogout() {
        return linkLogout;
    }


    @Step("Go to Main Page")
    public MainPage goToMainPage(){
        driver.get(Url.getCurrentHost());
        return new MainPage(driver);
    }

    @Step("Go to Profile Page")
    public ProfilePage goToProfilePage(){
        clickElement(linkProfilePage);
        return new ProfilePage(driver);
    }

    @Step("Go to Report Global Page")
    public ReportGlobalPage goToReportGlobal(){
        clickElement(linkReportPage);
        clickElement(driver.findElement(By.id("linkreportglobal")));
        return new ReportGlobalPage(driver);
    }

    @Step("Go to Disciple Overview Page")
    public DiscipleOverviewPage goToDiscipleOverview(){
        clickElement(linkDiscipleOverviewPage);
        return new DiscipleOverviewPage(driver);
    }

}
