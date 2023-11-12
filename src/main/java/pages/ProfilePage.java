package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private By deleteAllLocator = By.cssSelector("div.text-right.button.di button");

    //trash icon Locator in table (xpath= //div[@class='rt-tbody']//div[1]//div[1]//div[5]//div[1]//span[1])
    // or xpath = //div[@class='rt-tbody']//div[1]//div[1]//div[5]//div[1]
    //or xpath = div[@role='grid']//div[1]//div[1]//div[5]//div[1]//span[1]//*[name()='svg']//*[name()='path' and contains(@d,'M864 256H7')]
    //or id = div.action-buttons span#delete-record-undefined
    private By trashIconLocator = By.cssSelector("div.action-buttons span#delete-record-undefined"); //locator is fine
    //locator for logout button(css Selector="div.text-right.col-md-5.col-sm-12 button")
    private By logoutLocator = By.cssSelector("div.text-right.col-md-5.col-sm-12 button");
    //locator for popup modal when the delete button are clicked
    private By modalContentLocator = By.className("modal-content");
    //locator for popup modal header when the delete button are clicked
    private By modalContentHeaderLocator = By.className("modal-header");
    //locator for popup modal body when the delete button are clicked
    private By modalContentBodyLocator = By.className("modal-body");

    //locator for popup modal footer when the delete button are clicked
    private By modalContentFooterLocator = By.className("modal-footer");

    //method to get the username value for confirmation
    public String userNameValue(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameLocator));
        String text = header.getText();
        return text;
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
        clickTrashIcon();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalContentLocator));
        return driver.findElement(modalContentLocator);
    }

    //method to get the header of the modal
    private WebElement modalHeader(){
        WebElement modalContent = modalDialog();
        return modalContent.findElement(modalContentHeaderLocator);
    }
    //method to get the text in the modal's header
    public String modalHeaderText(){
        WebElement modalContent = modalDialog();
        WebElement modalTitle = modalContent.findElement(By.id("example-modal-sizes-title-sm"));
        return modalTitle.getText();
    }
    //method to get the body of the modal
    private WebElement modalBody(){
        WebElement modalContent = modalDialog();
        return modalContent.findElement(modalContentBodyLocator);
    }
    public String modalBodyText(){
        return modalBody().getText();
    }
    //method to get the footer of the modal
    private WebElement modalFooter(){
        WebElement modalContent = modalDialog();
        return modalContent.findElement(modalContentFooterLocator);
    }
    //method to delete the first book and confirm it has been deleted
    public void deleteFirstBook(){
        clickTrashIcon();
        WebElement modalDialog = modalDialog();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("closeSmallModal-ok")));
        WebElement modalAcceptButton = modalDialog.findElement(By.id("closeSmallModal-ok"));
        wait.until(ExpectedConditions.elementToBeClickable(modalAcceptButton));
        modalAcceptButton.click();
    }

    //method to close the modal pop up
    public void closeModalContent(){
        WebElement modalContainer = modalDialog();
        driver.switchTo().frame(modalContainer);
        WebElement modalRejectButton = modalContainer.findElement(By.id("closeSmallModal-ok"));
        modalRejectButton.click();
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

    private WebElement alertText(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(modalContentLocator));
    }

    public String alertTextValue(){
        return alertText().getText();
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
