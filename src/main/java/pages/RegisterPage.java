package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class RegisterPage {
    // declaring the webdriver variable
    private WebDriver driver;
    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    //declaring element for login button (locator is id = gotologin)
    private By loginButtonLocator = By.id("gotologin");
    //declaring the web element for login
    public WebElement loginButton(){
        return driver.findElement(loginButtonLocator);
    }
    //declaring locator for register (id= register)
    private By registerButtonLocator = By.id("register");
    //private By registerButtonLocator = By.cssSelector("button#register");
    //declaring the web element for register
    public WebElement registerButton(){
        return driver.findElement(registerButtonLocator);
    }

    public void clickRegisterButton(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(registerButton()));
        button.click();
    }

    //declaring locator for  first name (id = firstname)
    private By firstnameLocator = By.id("firstname");
    //declaring the web element for firstname
    private WebElement firstnameField(){
        return driver.findElement(firstnameLocator);
    }
    //declaring method to input value to firstname field
    public void setFirstName(String firstName){
        firstnameField().sendKeys(firstName);
    }

    //declaring locator for  last name (id = lastname)
    private By lastnameLocator = By.id("lastname");
    //declaring the web element for lastname
    private WebElement lastnameField(){
        return driver.findElement(lastnameLocator);
    }
    //declaring method to input value to lastName field
    public void setLastName(String lastName){
        lastnameField().sendKeys(lastName);
    }

    //declaring locator for  username (id = userName )
    private By userNameLocator = By.id("userName");
    //declaring the web element for username
    private WebElement userNameField(){
        return driver.findElement(userNameLocator);
    }
    //declaring method to input value to username field
    public void setUserName(String userName){
        userNameField().sendKeys(userName);
    }

    //declaring locator for  password (id = password)
    private By passwordLocator = By.id("password");
    //declaring the web element for password
    private WebElement passwordField(){
        return driver.findElement(passwordLocator);
    }
    //declaring method to input value to password field
    public void setPassword(String password){
        passwordField().sendKeys(password);
    }

    //declaring locator for  recaptcha frame (xpath = //iframe[@title='reCAPTCHA'])
    private By recaptchaFrameLocator = By.xpath("//iframe[@title='reCAPTCHA']");
    //declaring the web element for password
    public void recaptchaFrame(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(recaptchaFrameLocator));
    }
    //declaring locator for recaptcha checkbox (xpath = //div[@class='recaptcha-checkbox-border'])
    private By recaptchaCheckboxLocator = By.xpath("//div[@class='recaptcha-checkbox-border']");
    //declaring the web element for password
    public WebElement recaptchaCheckbox(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement reCheckbox = wait.until(ExpectedConditions.elementToBeClickable(recaptchaCheckboxLocator));
        return reCheckbox;
    }

    private By recaptchaCheckMarkLocator = By.xpath("//div[@class='recaptcha-checkbox-checkmark']");

    public LoginPage clickLogin(){
        loginButton().click();
        return new LoginPage(driver);
    }

    public void driverWait(){
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    public void mainContent(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(recaptchaCheckMarkLocator));
        driver.switchTo().defaultContent();
    }

    private By alertTextLocator = By.id("name");
    private WebElement alertText(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(alertTextLocator));
        return alert;
    }

    public String alertTextValue(){
        String text =  alertText().getText();
        return text;
    }

    public void alert_clickToAccept(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public String alert_getText(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

}
