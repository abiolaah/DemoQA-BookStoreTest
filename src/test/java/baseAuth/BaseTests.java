package baseAuth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProfilePage;

public class BaseTests {
    private WebDriver driver;
    protected LoginPage homePage;
    protected ProfilePage profilePage;
    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        //instantiate with chromedriver as chrome is the driver to do
        driver = new ChromeDriver();
        //use the get method from driver class to launch browser and load the website
        driver.get("https://demoqa.com/login");

        //load the loginPage
        homePage = new LoginPage(driver);
    }

    @BeforeMethod
    public void signIn(){
        homePage.setUserName("giver");
        homePage.setPassword("Password@12");
        //homePage.loginButton().click();
        profilePage = homePage.clickLogin();
    }
    @AfterMethod
    public void signOut(){
        profilePage.clickLogout();
    }

    @AfterClass
    public void tearDown(){
        //use the quit() to close the application and browser.
        driver.quit();
    }
}
