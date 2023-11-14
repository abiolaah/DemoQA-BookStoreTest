package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class RegisterPage {
    // declaring the webdriver variable
    private final WebDriver driver;
    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    //declaring element for login button (locator is id = gotologin)
    private final By loginButtonLocator = By.id("gotologin");

    //declaring locator for register (id= register)
    private final By registerButtonLocator = By.id("register");

    //declaring locator for  first name (id = firstname)
    private final By firstnameLocator = By.id("firstname");

    //declaring locator for  last name (id = lastname)
    private final By lastnameLocator = By.id("lastname");

    //finding locator for  username (id = userName )
    private final By userNameLocator = By.id("userName");

    //declaring locator for  password (id = password)
    private final By passwordLocator = By.id("password");

    //declaring locator for the alert Text
    private final By alertTextLocator = By.id("name");

    //declaring locator for  recaptcha frame (xpath = //iframe[@title='reCAPTCHA'])
    private final By recaptchaFrameLocator = By.xpath("//iframe[@title='reCAPTCHA']");

    //declaring locator for recaptcha checkbox (xpath = //div[@class='recaptcha-checkbox-border'])
    private final By recaptchaCheckboxLocator = By.xpath("//div[@class='recaptcha-checkbox-border']");
    //declaring locator for recaptcha check mark (xpath = //div[@class='recaptcha-checkbox-checkmark'])
    private final By recaptchaCheckMarkLocator = By.xpath("//div[@class='recaptcha-checkbox-checkmark']");

    //finding the web element for login button
    public WebElement loginButton(){
        return driver.findElement(loginButtonLocator);
    }

    //finding the web element for register button
    public WebElement registerButton(){
        return driver.findElement(registerButtonLocator);
    }

    //method to click register button
    public void clickRegisterButton(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        //presence in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(registerButtonLocator));
        //scrolling
        WebElement registerButton = registerButton();
        js.executeScript("arguments[0].scrollIntoView(true);", registerButton);
        //clickable
        wait.until(ExpectedConditions.elementToBeClickable(registerButtonLocator));
        //click
        registerButton.click();
    }

    //finding the web element for firstname text field
    private WebElement firstnameField(){
        return driver.findElement(firstnameLocator);
    }
    //declaring method to input value to firstname field
    public void setFirstName(String firstName){
        firstnameField().sendKeys(firstName);
    }
    //finding the web element for lastname text field
    private WebElement lastnameField(){
        return driver.findElement(lastnameLocator);
    }
    //declaring method to input value to lastName field
    public void setLastName(String lastName){
        lastnameField().sendKeys(lastName);
    }

    //declaring the web element for username
    private WebElement userNameField(){
        return driver.findElement(userNameLocator);
    }
    //declaring method to input value to username field
    public void setUserName(String userName){
        userNameField().sendKeys(userName);
    }

    //declaring the web element for password
    private WebElement passwordField(){
        return driver.findElement(passwordLocator);
    }
    //declaring method to input value to password field
    public void setPassword(String password){
        passwordField().sendKeys(password);
    }

    //declaring the web element for password
    public void recaptchaFrame(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(recaptchaFrameLocator));
    }
    //declaring the web element for password
    public WebElement recaptchaCheckbox(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        return wait.until(ExpectedConditions.elementToBeClickable(recaptchaCheckboxLocator));
    }

    //method to go to the login page
    public LoginPage clickLogin(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        //presence in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(loginButtonLocator));
        //scrolling
        WebElement loginButton = loginButton();
        js.executeScript("arguments[0].scrollIntoView(true);", loginButton);
        //clickable
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonLocator));
        //click
        loginButton.click();
        return new LoginPage(driver);
    }

    //method to implicitly wait for a minute
    public void driverWait(){
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    //method to switch from the recaptcha frame to the main windows
    public void mainContent(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(recaptchaCheckMarkLocator));
        driver.switchTo().defaultContent();
    }
    //method to get the alert
    private WebElement alertText(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alertTextLocator));
    }

    //method to get text from the text
    public String alertTextValue(){
        return alertText().getText();
    }

    //alert to accept the alert
    public void alert_clickToAccept(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}
