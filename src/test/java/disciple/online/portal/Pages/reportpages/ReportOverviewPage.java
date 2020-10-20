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
    }
    public boolean isReportDetailVisible(String startDate , String endDate){
        WebElement startElement;
        WebElement endElement;
        
        if(startDate.equals(endDate)){
            startElement = driver.findElement(By.xpath("(//TD[text()='"+ startDate + "'])[1]"));
            endElement = driver.findElement(By.xpath("(//TD[text()='"+ endDate + "'])[2]"));
        }else {
            startElement = driver.findElement(By.xpath("(//TD[text()='"+ startDate + "'])[1]"));
            endElement = driver.findElement(By.xpath("(//TD[text()='"+ endDate + "'])[1]"));
        }
        
        return startElement.isDisplayed() || endElement.isDisplayed();
    }
}
