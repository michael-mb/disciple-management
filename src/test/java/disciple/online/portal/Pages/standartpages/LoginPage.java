package disciple.online.portal.Pages.standartpages;

import disciple.online.portal.Pages.Header;
import disciple.online.portal.statics.Config;
import disciple.online.portal.statics.Url;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.logging.Logger;

public class LoginPage extends Header {

    private final Logger log = Logger.getLogger(LoginPage.class.getName());
    private int loginTries = 0;

    @FindBy(id = "email")
    private WebElement fieldEMail;

    @FindBy(id = "password")
    private WebElement fieldPassword;

    @FindBy(id = "login-button")
    private WebElement buttonLogin;

    @FindBy(id = "register")
    private WebElement linkRegister;

    @FindBy(id = "request_password")
    private WebElement linkNewPassword;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MainPage doRegularLogin(String email , String password){
        loginTries++;

        driver.get(Url.getUrlLoginpage());
        LoginPage loginPage = new LoginPage(driver);
        if (!loginPage.isInitialized()) {
            throw new IllegalStateException("Can't login, because login page isn't initialized");
        }
        enterLoginData(email, password);

        clickElement(buttonLogin);

        log.info("Wait max " + Config.MAX_TIME_OUT_LOGIN + "s for login to be done");
        MainPage mainPage = new MainPage(driver);

        if (!isLoggedIn()) {
            if (loginTries < 5) {
                doRegularLogin(email, password);
            } else {
                throw new IllegalStateException("Login wasn't successful");
            }
        }
        log.info("Login was successful, got on main page");
        return mainPage;
    }

    @Step("go to register page")
    public RegisterPage goToRegisterPage(){
        clickElement(linkRegister);
        return new RegisterPage(driver);
    }

    @Step("Enter login data")
    public void enterLoginData(String mailAddress, String password) {
        sendKeysToElement(fieldEMail, mailAddress);
        sendKeysToElement(fieldPassword, password);
    }
}
