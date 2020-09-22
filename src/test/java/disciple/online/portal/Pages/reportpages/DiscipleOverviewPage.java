package disciple.online.portal.Pages.reportpages;

import disciple.online.portal.Pages.Header;
import disciple.online.portal.scopes.user.entities.TestUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DiscipleOverviewPage extends Header {

    public DiscipleOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean assertReport(TestUser testUser , String reportTitle){
        WebElement reportMail = driver.findElement(By.xpath("(//SPAN[@class='mail-desc'][text()='"+testUser.mailAddress+"'])[1]"));
        WebElement reportWeek = driver.findElement(By.xpath("//SPAN[@class='time pull-right'][text()='"+reportTitle+"']"));
        return reportMail.isDisplayed() && reportWeek.isDisplayed();
    }
}
