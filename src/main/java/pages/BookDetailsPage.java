package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BookDetailsPage {
    // declaring the webdriver variable
    private WebDriver driver;
    //constructor that accepts driver as an argument
    public BookDetailsPage(WebDriver driver){
        this.driver = driver;
    }

    //locator for the bookstore button
    private By bookStoreLocator = By.cssSelector("div.text-left.fullButton button");

    //locator for the add to collection button
    private By addToCollectionLocator = By.cssSelector("div.text-right.fullButton button");

    //locator for the menu page
    private By profileMenuButtonLocator = By.cssSelector("div.element-list.collapse.show ul.menu-list li#item-3");

    //locator for title label
    private By titleLabelLocator = By.cssSelector("div#title-wrapper label#userName-value");

    //method to find profile menu button
    public WebElement profileMenuButton(){
        return driver.findElement(profileMenuButtonLocator);
    }

    //method to find bookstore button
    public  WebElement bookStoreButton(){
        return driver.findElement(bookStoreLocator);
    }
    //method to go to profile page.
    public ProfilePage profilePage(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        //presence in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(profileMenuButtonLocator));
        //scrolling
        WebElement profileButton = profileMenuButton();
        js.executeScript("arguments[0].scrollIntoView(true);", profileButton);
        //clickable
        wait.until(ExpectedConditions.elementToBeClickable(profileMenuButtonLocator));
        //click
        profileButton.click();
        return new ProfilePage(driver);
    }

    //method to go to bookstore
    public BookStorePage bookStorePage(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        //presence in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(bookStoreLocator));
        //scrolling
        WebElement bookStoreButton = bookStoreButton();
        js.executeScript("arguments[0].scrollIntoView(true);", bookStoreButton);
        //clickable
        wait.until(ExpectedConditions.elementToBeClickable(bookStoreLocator));
        //click
        bookStoreButton().click();
        return new BookStorePage(driver);
    }

    //method to add a book to user's collection
    public void addBookToCollection(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        //presence in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(addToCollectionLocator));
        //scrolling
        WebElement addBookButton = driver.findElement(addToCollectionLocator);
        js.executeScript("arguments[0].scrollIntoView(true);", addBookButton);
        //clickable
        wait.until(ExpectedConditions.elementToBeClickable(addToCollectionLocator));
        //click
        addBookButton.click();
    }

    //method to implicitly wait
    public void driverImplicitWait(int waitTime){
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
    }
    //method to explicitly wait
    public WebElement driverExplicitWait(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //method to get the title of book for confirmation
    public String bookTitleValue(){
        WebElement titleLabel = driverExplicitWait(titleLabelLocator);
        return titleLabel.getText();
    }

    //get alert text
    public String alert_getText(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
        //equals: "Book added to your collection"
    }

    //accept alert
    public void alert_clickToAccept(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

}
