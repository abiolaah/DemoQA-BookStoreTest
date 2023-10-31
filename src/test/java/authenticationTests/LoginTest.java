package authenticationTests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.ProfilePage;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {
    @Test
    public void unSuccessfulLogin(){
        homePage.setUserName("tjefferson");
        homePage.setPassword("Thomas@Password");
        homePage.loginButton().click();
        assertEquals("Invalid username or password!", homePage.alertTextValue());
    }
    @Test
    public void successfulLogin(){
        homePage.setUserName("giver");
        homePage.setPassword("Password@12");
        //homePage.loginButton().click();
        ProfilePage profilePage = homePage.clickLogin();
        assertTrue(profilePage.userNameValue().contains("giver"), "Username value is incorrect");//assertEquals("Profile", profile.headerText());
    }
}
