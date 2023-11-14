package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BookStorePage {
    // declaring the webdriver variable
    private final WebDriver driver;
    //constructor that accepts driver as an argument
    public BookStorePage(WebDriver driver){
        this.driver = driver;
    }

    //locator for the menu page
    private final By profileMenuButtonLocator = By.cssSelector("div.element-list.collapse.show ul.menu-list li#item-3");
    //method to find profile menu button
    public WebElement profileMenuButton(){
        return driver.findElement(profileMenuButtonLocator);
    }

    //method to confirm that the profile button is clickable.
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

    //method to open book details page
    public BookDetailsPage bookDetailsPage(){
        clickBookLink("You Don't Know JS");
        return new BookDetailsPage(driver);
    }

    //method to implicitly wait
    public void driverImplicitWait(int waitTime){
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
    }

    //method to click the books link to route to the
    private void clickBookLink(String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }
}