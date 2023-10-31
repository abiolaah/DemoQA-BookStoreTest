package profile;

import baseAuth.BaseTests;
import org.testng.annotations.Test;
import pages.BookStorePage;

import static org.testng.Assert.assertTrue;

public class ProfileTest extends BaseTests {
    @Test
    public void confirmUser(){
        String username = profilePage.userNameValue();
        assertTrue(profilePage.userNameValue().contains("giver"), "Username value is incorrect");
    }

    //add scroll to the bottom and waiting for all the method below
    @Test
    public void goToBookStore(){
        BookStorePage bookStore = profilePage.clickBookStore();
    }

    @Test
    public void deleteBook(){
        profilePage.clickTrashIcon(1);
    }

    @Test
    public void deleteAllBook(){
        profilePage.clickDeleteAllButton();
    }

    @Test
    public void deleteAccount(){
        profilePage.clickDeleteAccountButton();
    }
}
