package disciple.online.portal.Pages.standartpages;

import disciple.online.portal.Pages.Header;
import disciple.online.portal.statics.RegisterUser;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends Header {

    @FindBy(id = "email")
    private WebElement fieldMail;

    @FindBy(id = "firstName")
    private WebElement fieldFirstName;

    @FindBy(id = "lastName")
    private WebElement fieldLastName;

    @FindBy(id = "street")
    private WebElement fieldStreet;

    @FindBy(id = "city")
    private WebElement fieldCity;

    @FindBy(id = "phone")
    private WebElement fieldPhone;

    @FindBy(id = "password")
    private WebElement fieldPassword;

    @FindBy(id = "passwordAgain")
    private WebElement fieldPasswordAgain;

    @FindBy(id = "register-button")
    private WebElement buttonRegister;

    @FindBy(id = "discipleMakerMail")
    private WebElement discipleMakerSelect;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter register data and select register button")
    public MainPage register(RegisterUser registerUser){

        sendKeysToElement(fieldMail, registerUser.getEmail());
        sendKeysToElement(fieldFirstName, registerUser.getFirstname());
        sendKeysToElement(fieldLastName, registerUser.getLastname());
        sendKeysToElement(fieldStreet, registerUser.getStreet());
        sendKeysToElement(fieldCity, registerUser.getCity());
        sendKeysToElement(fieldPhone, registerUser.getPhone());
        sendKeysToElement(fieldPassword, registerUser.getPassword());
        sendKeysToElement(fieldPasswordAgain, registerUser.getPasswordAgain());

        Select select = new Select(discipleMakerSelect);
        select.selectByValue(registerUser.getDiscipleMakerMail());

        clickElement(buttonRegister);

        return new MainPage(driver);
    }
}
