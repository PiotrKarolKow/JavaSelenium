package utils;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver(String browser) {
        if ("firefox".equalsIgnoreCase(browser)) {
            return new FirefoxDriver();
        }

        return new ChromeDriver(buildChromeOptions());
    }

    private static ChromeOptions buildChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-features=site-per-process");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-logging");
        options.addArguments("--disable-cookie-encryption");
        options.addArguments("--incognito");
        options.addArguments("--disable-actor-safety-checks");
        options.setExperimentalOption("useAutomationExtension", true);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
        return options;
    }
}