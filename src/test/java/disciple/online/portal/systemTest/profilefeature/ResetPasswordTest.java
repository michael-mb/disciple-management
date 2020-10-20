package disciple.online.portal.systemTest.profilefeature;

import disciple.online.portal.Pages.standartpages.MainPage;
import disciple.online.portal.Pages.standartpages.ProfilePage;
import disciple.online.portal.SystemTest;
import disciple.online.portal.scopes.user.entities.TestUser;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class ResetPasswordTest extends SystemTest {
    @Test
    @Description("Test to change a password")
    public void resetPassword(){
        String oldPassword = TestUser.ADMIN.password;
        String newPassword = "newPass237+";
        MainPage mainPage = regularLogin(TestUser.ADMIN);
        ProfilePage profilePage = mainPage.goToProfilePage();
        profilePage.changePassword(newPassword , newPassword);
        logout(profilePage);
        assertThrows(IllegalStateException.class, () -> regularLogin(TestUser.ADMIN.mailAddress,oldPassword));
        mainPage = regularLogin(TestUser.ADMIN.mailAddress , newPassword);
        assertTrue(isLoggedIn(mainPage));
    }

    @Test
    @Description("Try to change password with Bad practice")
    public void unsuccessfulChangePassword(){

        String newPassword = "newPassxx";
        MainPage mainPage = regularLogin(TestUser.ADMIN);
        ProfilePage profilePage = mainPage.goToProfilePage();
        profilePage.changePassword(newPassword , newPassword);
        logout(profilePage);
        assertThrows(IllegalStateException.class, () -> regularLogin(TestUser.ADMIN.mailAddress,newPassword));
        assertFalse(isLoggedIn(mainPage));

        String newPasswordAgain = "newPassxx+";
        mainPage = regularLogin(TestUser.ADMIN);
        profilePage = mainPage.goToProfilePage();
        profilePage.changePassword(newPassword , newPasswordAgain);
        logout(profilePage);
        assertThrows(IllegalStateException.class, () -> regularLogin(TestUser.ADMIN.mailAddress,newPassword));
        assertThrows(IllegalStateException.class, () -> regularLogin(TestUser.ADMIN.mailAddress,newPasswordAgain));
        assertFalse(isLoggedIn(mainPage));
    }
}
