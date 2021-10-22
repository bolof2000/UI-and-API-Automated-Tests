package alltests.login;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecureAreaPage;

import static org.testng.Assert.*;

public class LoginTests extends BaseTests {

    @Test
    public void testSuccessfulLogin(){
        LoginPage loginPage = homePage.clickFormAuthentication();
        loginPage.setUsername("tomsmith");
        loginPage.setPassword("SuperSecretPassword!");
        SecureAreaPage secureAreaPage = loginPage.clickLoginButton();
        assertTrue(secureAreaPage.getAlertText()
                .contains("You logged into a secure area!"),
                "Alert text is incorrect");
    }

    @Test
    public void testUnsuccessfulLogin(){
        LoginPage loginPage = homePage.clickFormAuthentication();
        loginPage.setUsername("tomsmi");
        loginPage.setPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();
        assertTrue(loginPage.verifyInvalidLogin().contains("Your username is invalid"));

    }
    @Test
    public void testUnsuccessfulLogin91(){
        LoginPage loginPage = homePage.clickFormAuthentication();
        loginPage.setUsername("tomsmi");
        loginPage.setPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();
        assertTrue(loginPage.verifyInvalidLogin().contains("Your username is invalid"));

    }
    @Test
    public void testUnsuccessfulLogin02(){
        LoginPage loginPage = homePage.clickFormAuthentication();
        loginPage.setUsername("tomsmi");
        loginPage.setPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();
        assertTrue(loginPage.verifyInvalidLogin().contains("Your username is invalid"));

    }
}
