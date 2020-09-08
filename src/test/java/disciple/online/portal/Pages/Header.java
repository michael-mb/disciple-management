package disciple.online.portal.Pages;

import disciple.online.portal.Pages.standartpages.LoginPage;
import io.qameta.allure.Step;
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

    /*
     * Just displayed, when not logged in
     */


    @FindBy(id = "logout")
    private WebElement linkLogout;

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
}
