package com.saucedemo.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Core;

public class SauceDemoBaseTest extends Core {
    protected String baseUrl;
    protected String username;
    protected String password;

    @BeforeMethod
    public void setUp() {
        this.baseUrl = "https://www.saucedemo.com/";
        this.username = "standard_user";
        this.password = "secret_sauce";
        driver = createDriver("chrome");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        quitDriver();
    }
}
