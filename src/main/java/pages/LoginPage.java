package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    //constructor that accepts driver as an argument
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    // declaring the webdriver variable
    private WebDriver driver;

    //declaring the elements to be used to route to other pages
    private By newUserButtonLocator = By.id("newUser");

    public WebElement newUSerButton(){
        return driver.findElement(newUserButtonLocator);
    }
    //declaring locator for login (id= login)
    private By loginButtonLocator = By.id("login");
    //declaring the web element for login
    public WebElement loginButton(){
        return driver.findElement(loginButtonLocator);
    }

    //declaring locator for  username (id = userName)
    private By userNameLocator = By.id("userName");
    //declaring the web element for username
    private WebElement userNameField(){
        return driver.findElement(userNameLocator);
    }
    //declaring method to input value to username field
    public void setUserName(String userName){
        userNameField().sendKeys(userName);
    }

    //declaring locator for  password (id = password )
    private By passwordLocator = By.id("password");
    //declaring the web element for password
    private WebElement passwordField(){
        return driver.findElement(passwordLocator);
    }
    //declaring method to input value to password field
    public void setPassword(String password){
        passwordField().sendKeys(password);
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

    //method to interact with the new user button
    public RegisterPage clickNewUser(){
        newUSerButton().click();
        return new RegisterPage(driver);
    }

    //private By headerLocator = By.cssSelector("div.main-header");
    public ProfilePage clickLogin(){
        loginButton().click();
        return new ProfilePage(driver);
    }

}
