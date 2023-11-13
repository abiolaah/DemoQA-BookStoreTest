package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class ProfilePage {
    // declaring the web-driver variable
    private final WebDriver driver;
    //constructor that accepts driver as an argument
    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }

    //private By headerLocator = By.cssSelector("div.main-header");
    private final By userNameLocator = By.id("userName-value");
    //locator for Go To Bookstore button (id = gotoStore)
    private final By bookStoreLocator = By.id("gotoStore");
    //locator for Delete Account (cssSelector = div.text-center button)
    private final By deleteAccountLocator = By.cssSelector("div.text-center button");
    //locator for Delete All Books and Logout (cssSelector = div.do button) //need to find proper locator
    private final By deleteAllLocator = By.cssSelector("div.text-right.button.di button");

    //trash icon Locator in table
    private final By trashIconLocator = By.cssSelector("div.action-buttons span#delete-record-undefined"); //locator is fine
    //locator for logout button(css Selector="div.text-right.col-md-5.col-sm-12 button")
    private final By logoutLocator = By.cssSelector("div.text-right.col-md-5.col-sm-12 button");
    //locator for popup modal when the delete button are clicked
    private final By modalContentLocator = By.className("modal-content");

    //method to get the username value for confirmation
    public String userNameValue(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameLocator));
        return header.getText();
    }

    //method to click the delete button of the first book in the user's collection list.
    public void clickTrashIcon(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(trashIconLocator));
        WebElement trashBook = driver.findElement(trashIconLocator);
        trashBook.click();
    }

    //method to get the modal Dialog
    private WebElement modalDialog(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalContentLocator));
        return driver.findElement(modalContentLocator);
    }

    //method to get the text in the modal's header
    public String modalHeaderText(){
        WebElement modalContent = modalDialog();
        WebElement modalTitle = modalContent.findElement(By.id("example-modal-sizes-title-sm"));
        return modalTitle.getText();
    }

    //method to click ok in the modal
    public void acceptModalContent(){
        WebElement modalDialog = modalDialog();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("closeSmallModal-ok")));
        WebElement modalAcceptButton = modalDialog.findElement(By.id("closeSmallModal-ok"));
        wait.until(ExpectedConditions.elementToBeClickable(modalAcceptButton));
        modalAcceptButton.click();
    }

    //method to click close in the modal
    public void closeModalContent(){
        WebElement modalContainer = modalDialog();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='document']//div[@class='modal-content']//div[@class='modal-footer']//button[@id='closeSmallModal-cancel']")));
        //WebElement modalRejectButton = modalContainer.findElement(By.id("closeSmallModal-ok")); ////div[@role='document']//div[@class='modal-content']//div[@class='modal-footer']//button[@id='closeSmallModal-cancel']
        WebElement modalRejectButton = modalContainer.findElement(By.xpath("//div[@role='document']//div[@class='modal-content']//div[@class='modal-footer']//button[@id='closeSmallModal-cancel']"));
        //clickable
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='document']//div[@class='modal-content']//div[@class='modal-footer']//button[@id='closeSmallModal-cancel']")));
        modalRejectButton.click();
    }

    //method to ensure proper deleting
    public  void confirmDelete(String headerTitle){
        String modalHeaderText = modalHeaderText();
        if(modalHeaderText.equals(headerTitle)){
            acceptModalContent();
        }else {
            closeModalContent();
        }
    }

    //method to delete all books in the user's collection
    public void clickDeleteAllButton(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        JavascriptExecutor js = ((JavascriptExecutor)driver);

        //presence in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(deleteAllLocator));

        //scrolling
        WebElement deleteAllBtn =  driver.findElement(deleteAllLocator);
        js.executeScript("arguments[0].scrollIntoView(true);", deleteAllBtn);

        //clickable
        wait.until(ExpectedConditions.elementToBeClickable(deleteAllLocator));
        deleteAllBtn.click();
    }

    //method to find the delete account button and click
    public void clickDeleteAccountButton(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        JavascriptExecutor js = ((JavascriptExecutor)driver);

        //presence in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(deleteAccountLocator));

        //scrolling
        WebElement deleteAccount = driver.findElement(deleteAccountLocator);
        js.executeScript("arguments[0].scrollIntoView(true);", deleteAccount);

        //clickable
        wait.until(ExpectedConditions.elementToBeClickable(deleteAccountLocator));
        deleteAccount.click();
    }

    public void clickLogout(){
        WebElement logoutBtn = driver.findElement(logoutLocator);
        logoutBtn.click();
    }

    //method to click the book store button: to be moved
    public BookStorePage clickBookStore(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        JavascriptExecutor js = ((JavascriptExecutor)driver);

        //presence in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(bookStoreLocator));

        //scrolling
        WebElement bookStoreButton = driver.findElement(bookStoreLocator);
        js.executeScript("arguments[0].scrollIntoView(true);", bookStoreButton);

        //clickable
        wait.until(ExpectedConditions.elementToBeClickable(bookStoreLocator));

        bookStoreButton.click();
        return new BookStorePage(driver);
    }

    //method to implicitly wait
    public void driverImplicitWait(int waitTime){
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
    }
}
