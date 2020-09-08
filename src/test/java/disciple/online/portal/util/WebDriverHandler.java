package disciple.online.portal.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebDriverHandler {
    private static final Logger log = Logger.getGlobal();
    private WebDriverHandler() {
        //suppress standard constructor, so that this class can't be instantiated
        throw new AssertionError("WebDriverHandler should not be instantiated");
    }

    public static ChromeDriver setupNewChromeDriver() {
        ChromeDriver driver = getChromeDriver();
        configureChromeDriver(driver);
        return driver;
    }

    public static void tearDownWebDriver(WebDriver driver) {
        driver.quit();
    }

    private static ChromeDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        return new ChromeDriver(options);
    }

    private static void configureChromeDriver(ChromeDriver driver) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
