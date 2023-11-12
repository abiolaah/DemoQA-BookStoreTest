package profile;

import baseAuth.BaseTests;
import org.testng.annotations.Test;
import pages.BookDetailsPage;
import pages.BookStorePage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProfileTest extends BaseTests {
    protected BookStorePage bookStorePage;
    protected BookDetailsPage bookDetailsPage;
    @Test
    public void confirmUser(){
        assertTrue(profilePage.userNameValue().contains("giver"), "Username value is incorrect");
    }

    //add scroll to the bottom and waiting for all the method below
//    @Test
//    public void testDeleteBook(){
//        profilePage.clickTrashIcon();
//    }
//
//    @Test
//    public void confirmModalHeaderText(){
//        String modalHeaderText = profilePage.modalHeaderText();
//        assertEquals(modalHeaderText, "Delete Book", "Verify Modal Header message");
//        profilePage.closeModalContent();
//    }
//
//    @Test
//    public void confirmModalBodyText(){
//        String modalBodyText = profilePage.modalBodyText();
//        assertEquals(modalBodyText, "Delete Book", "Verify Modal Header message");
//    }
//
//    @Test
//    public void testDeleteAllBook(){
//        profilePage.clickDeleteAllButton();
//    }
//
//    @Test
//    public void testDeleteAccount(){
//        profilePage.clickDeleteAccountButton();
//    }

    //Bookstore page tests
    @Test
    public void testGoToBookStoreButtonFromProfilePage(){
        bookStorePage = profilePage.clickBookStore(); //move to bookstore page from profile page
        bookStorePage.driverImplicitWait(30); // wait for 30ecs to load new page
        bookStorePage.profilePage(); //move to profile page from bookstore page
        bookStorePage.driverImplicitWait(60); // wait for 60ecs to load new page
    }

    //Book Details page tests
    @Test
    public void testGoToBookDetails(){
        bookStorePage = profilePage.clickBookStore(); //move to bookstore page from profile page
        bookStorePage.driverImplicitWait(30); // wait for 30ecs to load new page
        bookDetailsPage= bookStorePage.bookDetailsPage(); // move to book details page from bookstore page
        assertTrue(bookDetailsPage.bookTitleValue().contains("Git Pocket Guide"), "Book Title value is incorrect"); //confirm title is correct
        bookDetailsPage.driverImplicitWait(30); // wait for 30ecs to load new page
        bookDetailsPage.profilePage(); //move to profile page from book details' page
        bookDetailsPage.driverImplicitWait(60); // wait for 60ecs to load new page
    }

    @Test
    public void testAddBookToCollectionButton(){
        bookStorePage = profilePage.clickBookStore(); //move to bookstore page from profile page
        bookStorePage.driverImplicitWait(30); // wait for 30ecs to load new page
        bookDetailsPage= bookStorePage.bookDetailsPage(); // move to book details page from bookstore page
        bookDetailsPage.driverImplicitWait(30); // wait for 30ecs to load new page
        bookDetailsPage.addBookToCollection();
        String alertMessage = bookDetailsPage.alert_getText();
        assertEquals(alertMessage,"Book added to your collection.", "Verify Alert Message");
        bookDetailsPage.alert_clickToAccept();
        bookDetailsPage.profilePage(); //move to profile page from book details' page
        bookDetailsPage.driverImplicitWait(60); // wait for 60ecs to load new page
    }

    @Test
    public void testBackToBookStoreButtonFromBookDetailsPage(){
        bookStorePage = profilePage.clickBookStore(); //move to bookstore page from profile page
        bookStorePage.driverImplicitWait(30); // wait for 30ecs to load new page
        bookDetailsPage= bookStorePage.bookDetailsPage(); // move to book details page from bookstore page
        bookDetailsPage.driverImplicitWait(30); // wait for 30ecs to load new page
        bookStorePage = bookDetailsPage.bookStorePage(); //move to bookstore page from book details page
        bookStorePage.profilePage(); //move to profile page from bookstore page
        bookStorePage.driverImplicitWait(60); // wait for 60ecs to load new page
    }
}
