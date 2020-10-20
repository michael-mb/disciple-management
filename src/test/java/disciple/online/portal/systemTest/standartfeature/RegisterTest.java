package disciple.online.portal.systemTest.standartfeature;

import disciple.online.portal.Pages.standartpages.LoginPage;
import disciple.online.portal.Pages.standartpages.MainPage;
import disciple.online.portal.Pages.standartpages.RegisterPage;
import disciple.online.portal.SystemTest;
import disciple.online.portal.statics.RegisterUser;
import disciple.online.portal.statics.Url;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.Assert.assertThrows;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RegisterTest extends SystemTest {

    private RegisterPage registerPage;

    @BeforeEach
    void navigateToRegisterPage() {
        driver.get(Url.getUrlLoginpage());
        LoginPage loginPage = new LoginPage(driver);
        registerPage = loginPage.goToRegisterPage();
    }

    @Test
    @Description("Test successful registration")
    void doSuccessfulRegistration() {
        RegisterUser registerUser = new RegisterUser("saha" , "simo" , "sahasimo@yahoo.fr",
                "nathan007X+" , "nathan007X+" , "dresden" , "kamerunerstraße 42", "445223",
                "gooddisciplemaker@test.com");

        //doing the actual registration
        navigateToRegisterPage();
        MainPage mainPage = registerPage.register(registerUser);
        LoginPage loginPage = logout(mainPage);
        loginPage.doRegularLogin(registerUser.getEmail() ,registerUser.getPassword());
        assertTrue(mainPage.isInitialized() , "Expected to land on main page, after log in, but landed on another page");
    }

    @Test
    @Description("Test registration with invalid mail address")
    void doRegistrationWithWrongMailAddress() {
        RegisterUser registerUser = new RegisterUser("saha" , "simo" , "sahasimo",
                "nathan007X+" , "nathan007X+" , "dresden" , "kamerunerstraße 42", "445223",
                "gooddisciplemaker@test.com");

        navigateToRegisterPage();
        registerPage.register(registerUser);
        assertFalse(isLoggedIn(registerPage) , "The user must not be logged in");
    }

    @Test
    @Description("Test registration with two different passwords")
    void doRegistrationWithTwoDifferentPasswords() {
        RegisterUser registerUser = new RegisterUser("saha" , "simo" , "sahasimo@yahoo.fr",
                "nathan007X+" , "nathan007y" , "dresden" , "kamerunerstraße 42", "445223",
                "gooddisciplemaker@test.com");

        navigateToRegisterPage();
        registerPage.register(registerUser);
        assertFalse(isLoggedIn(registerPage) , "The user must not be logged in");
    }

    @Test
    @Description("Test registration without mail address")
    void doRegistrationWithoutMailAddress() {
        RegisterUser registerUser = new RegisterUser("saha" , "simo" , "",
                "nathan007X+" , "nathan007X+" , "dresden" , "kamerunerstraße 42", "445223",
                "gooddisciplemaker@test.com");
        navigateToRegisterPage();
        registerPage.register(registerUser);
        assertFalse(isLoggedIn(registerPage) , "The user must not be logged in");
    }

    @Test
    @Description("Test registration without first name")
    void doRegistrationWithoutFirstName() {
        RegisterUser registerUser = new RegisterUser("" , "simo" , "sahasimo@yahoo.fr",
                "nathan007X+" , "nathan007X+" , "dresden" , "kamerunerstraße 42", "445223",
                "gooddisciplemaker@test.com");
        navigateToRegisterPage();
        registerPage.register(registerUser);
        assertFalse(isLoggedIn(registerPage) , "The user must not be logged in");
    }

    @Test
    @Description("Test registration without last name")
    void doRegistrationWithoutLastName() {
        RegisterUser registerUser = new RegisterUser("saha" , "" , "sahasimo@yahoo.fr",
                "nathan007X+" , "nathan007X+" , "dresden" , "kamerunerstraße 42", "445223",
                "gooddisciplemaker@test.com");
        navigateToRegisterPage();
        registerPage.register(registerUser);
        assertFalse(isLoggedIn(registerPage) , "The user must not be logged in");
    }

    @Test
    @Description("Test registration without street")
    void doRegistrationWithoutStreet() {
        RegisterUser registerUser = new RegisterUser("saha" , "simo" , "sahasimo@yahoo.fr",
                "nathan007X+" , "nathan007X+" , "dresden" , "", "445223",
                "gooddisciplemaker@test.com");
        navigateToRegisterPage();
        registerPage.register(registerUser);
        assertFalse(isLoggedIn(registerPage) , "The user must not be logged in");
    }
    @Test
    @Description("Test registration without town")
    void doRegistrationWithoutTown() {
        RegisterUser registerUser = new RegisterUser("saha" , "simo" , "sahasimo@yahoo.fr",
                "nathan007X+" , "nathan007X+" , "" , "kamerunerstraße 42", "445223",
                "gooddisciplemaker@test.com");
        navigateToRegisterPage();
        registerPage.register(registerUser);
        assertFalse(isLoggedIn(registerPage) , "The user must not be logged in");
    }

    @Test
    @Description("Test registration without password")
    void doRegistrationWithoutPassword() {
        RegisterUser registerUser = new RegisterUser("saha" , "simo" , "sahasimo@yahoo.fr",
                "" , "nathan007X+" , "dresden" , "kamerunerstraße 42", "445223",
                "gooddisciplemaker@test.com");
        navigateToRegisterPage();
        registerPage.register(registerUser);
        assertFalse(isLoggedIn(registerPage) , "The user must not be logged in");
    }

    @Test
    @Description("Test registration without password")
    void doRegistrationFalsePasswordPattern() {
        RegisterUser registerUser = new RegisterUser("saha" , "simo" , "sahasimo@yahoo.fr",
                "nathan" , "nathan" , "dresden" , "kamerunerstraße 42", "445223",
                "gooddisciplemaker@test.com");
        navigateToRegisterPage();
        registerPage.register(registerUser);
        assertFalse(isLoggedIn(registerPage) , "The user must not be logged in");
    }

    @Test
    @Description("Test registration without discipleMaker mail")
    void doRegistrationWithoutDiscipleMakerMail() {
        RegisterUser registerUser = new RegisterUser("saha" , "simo" , "sahasimo@yahoo.fr",
                "nathan007X+" , "nathan007X+" , "dresden" , "kamerunerstraße 42", "445223",
                "");
        navigateToRegisterPage();
        assertThrows(NoSuchElementException.class, () -> registerPage.register(registerUser));
        assertFalse(isLoggedIn(registerPage) , "The user must not be logged in");
    }

    @Test
    @Description("Test registration with wrong disciple maker mail")
    void doRegistrationWithWrongDiscipleMakerMail() {
        RegisterUser registerUser = new RegisterUser("saha" , "simo" , "sahasimo@yahoo.fr",
                "" , "nathan007X+" , "dresden" , "kamerunerstraße 42", "445223",
                "sahasimo@yahoo.fr");
        navigateToRegisterPage();
        assertThrows(NoSuchElementException.class, () -> registerPage.register(registerUser));
        assertFalse(isLoggedIn(registerPage) , "The user must not be logged in");
    }

    @Test
    @Description("Test registration with an Mail Address that is already registered")
    void doRegistrationWithAlreadyTakenMailAddress() {
        RegisterUser registerUser = new RegisterUser("saha" , "simo" , "sahasimo@yahoo.fr",
                "nathan007X+" , "nathan007X+" , "dresden" , "kamerunerstraße 42", "445223",
                "gooddisciplemaker@test.com");
        navigateToRegisterPage();
        registerPage.register(registerUser);
        logout(registerPage);

        navigateToRegisterPage();
        registerPage.register(registerUser);
        assertFalse(isLoggedIn(registerPage) , "The user must not be logged in");
    }

}
