package disciple.online.portal.Pages.standartpages;

import disciple.online.portal.Pages.Header;
import org.openqa.selenium.WebDriver;

public class MainPage extends Header {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isInitialized() {
        return getLinkLogout().isDisplayed();
    }
}
