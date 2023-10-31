package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;

public class BaseTest {
    private WebDriver driver;
    protected LoginPage homePage;
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

    @AfterClass
    public void tearDown(){
        //use the quit() to close the application and browser.
        driver.quit();
    }
}
