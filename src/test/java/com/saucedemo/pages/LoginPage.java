package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(className = "login_logo")
    private WebElement loginLogo;
    @FindBy(id = "user-name")
    private WebElement username;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "login-button")
    private WebElement button;
    @FindBy(css = ".error-message-container")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }

    public LoginPage open(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(loginLogo));
        return this;
    }

    public ProductsPage loginValid(String username, String passwd) {
        this.username.sendKeys(username);
        password.sendKeys(passwd);
        button.click();
        return new ProductsPage(driver);
    }

    public LoginPage loginInvalid(String username, String passwd) {
        this.username.clear();
        this.username.sendKeys(username);
        password.clear();
        password.sendKeys(passwd);
        button.click();
        return this;
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }
}
