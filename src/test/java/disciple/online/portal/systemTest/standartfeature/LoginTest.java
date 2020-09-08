package disciple.online.portal.systemTest.standartfeature;

import disciple.online.portal.Pages.standartpages.MainPage;
import disciple.online.portal.SystemTest;
import disciple.online.portal.scopes.user.entities.TestUser;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

public class LoginTest extends SystemTest {

    @Test
    @Description("Test login with valid access data")
    public void doSuccessfulLogin(){
        MainPage mainPage = regularLogin(TestUser.ADMIN.mailAddress , TestUser.ADMIN.password);
        assertTrue(mainPage.isInitialized() , "Expected to land on main page, after log in, but landed on another page");
    }

    @Test
    @Description("Test login with invalid access data")
    void doUnsuccessfulLogin() {
        assertThrows(IllegalStateException.class, () -> regularLogin("dada@dada.de","dada"));
    }

}
