package disciple.online.portal.Pages.standartpages;

import disciple.online.portal.Pages.Header;
import disciple.online.portal.scopes.user.entities.TestUser;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsersPage extends Header {
    public UsersPage(WebDriver driver) {
        super(driver);
    }

    public void deleteUser(TestUser user){
        WebElement deleteBtn = driver.findElement(By.id(user.mailAddress+".delete"));
        clickElement(deleteBtn);
        WebDriverWait wait = new WebDriverWait(driver,0);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void deleteUser(String email){
        WebElement deleteBtn = driver.findElement(By.id(email+".delete"));
        clickElement(deleteBtn);
        WebDriverWait wait = new WebDriverWait(driver,0);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void issetUser(TestUser user){
        driver.findElement(By.id(user.mailAddress+".edit"));
    }

    public void issetUser(String email){
        driver.findElement(By.id(email+".edit"));
    }
}
