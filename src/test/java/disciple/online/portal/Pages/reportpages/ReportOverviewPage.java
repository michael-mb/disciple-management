package disciple.online.portal.Pages.reportpages;

import disciple.online.portal.Pages.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReportOverviewPage extends Header {
    public ReportOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isReportGlobalVisible(String reportWeek){

        WebElement element = driver.findElement(By.xpath("(//TD[text()='"+ reportWeek + "'])[1]"));
        return element.isDisplayed();
    };
}
