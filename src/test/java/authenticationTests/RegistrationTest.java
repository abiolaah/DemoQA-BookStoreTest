package authenticationTests;

import base.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RegistrationTest extends BaseTest {

    @Test
    public void testLoginButton(){
        var registrationPage = homePage.clickNewUser();
        registrationPage.clickLogin();

    }
    @Test
    public void testSuccessfulRegistration(){
        var registrationPage = homePage.clickNewUser();
        registrationPage.setFirstName("Mary");
        registrationPage.setLastName("Smith");
        registrationPage.setUserName("msmith22");
        registrationPage.setPassword("Pa$$word1234");
        registrationPage.recaptchaFrame();
        registrationPage.recaptchaCheckbox().click();
        registrationPage.driverWait();
        registrationPage.mainContent();
        registrationPage.clickRegisterButton();
//        String text = registrationPage.alert_getText();
//        registrationPage.alert_clickToAccept();
//        assertEquals(text, "User Register successfully", "Alert text incorrect");
    }

    @Test
    public void unSuccessfulRegistration(){
        var registrationPage = homePage.clickNewUser();
        registrationPage.setFirstName("Thomas");
        registrationPage.setLastName("Jefferson");
        registrationPage.setUserName("tjefferson");
        registrationPage.setPassword("Thomas@Password");
        registrationPage.clickRegisterButton();
        assertEquals("Please verify reCaptcha to register!", registrationPage.alertTextValue());
    }
}
