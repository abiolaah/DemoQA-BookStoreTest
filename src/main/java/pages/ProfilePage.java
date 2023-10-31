package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProfilePage {
    // declaring the webdriver variable
    private WebDriver driver;
    //constructor that accepts driver as an argument
    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }

    //private By headerLocator = By.cssSelector("div.main-header");
    private By userNameLocator = By.id("userName-value");
    //locator for Go To Bookstore button (id = gotoStore)
    private By bookStoreLocator = By.id("gotoStore");
    //locator for Delete Account (cssSelector = div.text-center button)
    private By deleteAccountLocator = By.cssSelector("div.text-center button");
    //locator for Delete All Books and Logout (cssSelector = div.do button) //need to find proper locator
    private By deleteAllLocator = By.cssSelector("div.do button");
    //locator for trash  icon in table (xpath = //span[@id='delete-record-undefined'])
    //trash icon Locator in table (xpath= //div[@class='rt-tbody']//div[1]//div[1]//div[5]//div[1])
    private By trashIconLocator = By.xpath("//span[@id='delete-record-undefined']"); //locator is fine
    //locator for logout button(css Selector="div.text-right.col-md-5.col-sm-12 button")
    private By logoutLocator = By.cssSelector("div.text-right.col-md-5.col-sm-12 button");
    private By modalContentLocator = By.className("modal-content");

    public String userNameValue(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameLocator));
        String text = header.getText();
        return text;
    }

    public void clickDeleteAccountButton(){
        WebElement deleteAccount = driver.findElement(deleteAccountLocator);
        deleteAccount.click();
    }

    public void clickLogout(){
        WebElement logoutBtn = driver.findElement(logoutLocator);
        logoutBtn.click();
    }

    public void clickDeleteAllButton(){
        WebElement deleteAllBtn =  driver.findElement(deleteAllLocator);
        deleteAllBtn.click();
    }

    private List<WebElement> trashIcon(){
        List<WebElement> buttonList = driver.findElements(trashIconLocator);
        return buttonList;
    }
    public void clickTrashIcon(int i){
        List<WebElement> list = trashIcon();
        list.get(i).click();
    }
    public void clickTrashIcon(){

    }
    private WebElement bookStoreButton(){
        //add wait and look for method.
        return driver.findElement(bookStoreLocator);
    }

    public BookStorePage clickBookStore(){
        bookStoreButton().click();
        return new BookStorePage(driver);
    }

    private WebElement alertText(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(modalContentLocator));
        return modal;
    }

    public String alertTextValue(){
        String text =  alertText().getText();
        return text;
    }

    public void modal_clickToAccept(){
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
