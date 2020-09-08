package disciple.online.portal;

import disciple.online.portal.Pages.Header;
import disciple.online.portal.Pages.standartpages.LoginPage;
import disciple.online.portal.Pages.standartpages.MainPage;
import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.statics.Url;
import disciple.online.portal.util.PropertiesHandler;
import disciple.online.portal.util.WebDriverHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.logging.Logger;

/**
 * Super Class for all tests.
 * Implements some standard test methods
 * Sets up the WebDriver
 */
public class SystemTest {
    public final Logger log = Logger.getLogger(String.valueOf(getClass()));
    public WebDriver driver;
    public static String activeSpringProfile = PropertiesHandler.getActiveSpringProfileValue();
    public ApplicationContext context;
    @BeforeEach
    void setUp() {

        context = SpringApplication.run(PortalApplication.class);
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        switch (activeSpringProfile) {
            case "test":
                log.info("Setup Chrome Driver");
                driver = WebDriverHandler.setupNewChromeDriver();
                driver.get(Url.getUrlLoginpage());
                break;
            default:
                throw new RuntimeException("No valid active spring profile set");
        }
    }

    @AfterEach
    void tearDown(){
        WebDriverHandler.tearDownWebDriver(driver);
        SpringApplication.exit(context);
    }

    public MainPage regularLogin(User user) {
        return regularLogin(user.getEmail(), user.getPassword());
    }

    protected MainPage regularLogin(String email, String password) {
        log.info("Navigate to login page, enter data and click login");
        driver.get(Url.getUrlLoginpage());
        LoginPage loginPage = new LoginPage(driver);

        if (loginPage.isInitialized()) {
            return loginPage.doRegularLogin(email, password);
        } else {
            throw new IllegalStateException("Can't login, because login page isn't initialized");
        }
    }

    public LoginPage logout(Header header) {
        if (isLoggedIn(header)) {
            log.info("Click the logout link");
            return header.selectLogoutLink();
        } else {
            throw new IllegalStateException("Can't logout, because page state is logged out");
        }
    }

    protected boolean isLoggedIn(Header header) {
        return header.isLoggedIn();
    }

}
