package disciple.online.portal.Pages.standartpages;

import disciple.online.portal.Pages.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends Header {

    @FindBy(id = "lastName")
    private WebElement fieldName;

    @FindBy(id = "firstName")
    private WebElement fieldFirstName;

    @FindBy(id = "password")
    private WebElement fieldPassword;

    @FindBy(id = "passwordAgain")
    private WebElement fieldPasswordAgain;

    @FindBy(id = "phone")
    private WebElement fieldPhone;

    @FindBy(id = "city")
    private WebElement fieldCity;

    @FindBy(id = "street")
    private WebElement fieldStreet;

    @FindBy(id = "discipleMakerMail")
    private WebElement fieldDiscipleMakerMail;

    @FindBy(id = "updateBtn")
    private WebElement updateBtn;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void changePassword(String pass , String passAgain){
        sendKeysToElement(fieldPassword , pass);
        sendKeysToElement(fieldPasswordAgain , passAgain);
        clickElement(updateBtn);
    }
}
