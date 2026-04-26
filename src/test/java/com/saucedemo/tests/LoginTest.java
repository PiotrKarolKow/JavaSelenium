package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.tests.SauceDemoBaseTest;
import org.testng.annotations.Test;

public class LoginTest extends SauceDemoBaseTest {

    @Test
    public void loginValid() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(baseUrl);
    }
}