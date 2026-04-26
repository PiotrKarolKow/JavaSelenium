import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Operacje {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options  = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test(testName = "testowanie jednego okna")
    public void oneWindowTest() {
        driver.get("https://letcode.in/window");
        String mainWindow = driver.getWindowHandle();
        String mainUrl = driver.getCurrentUrl();

        driver.findElement(By.id("home")).click();
        List<String> okna = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(okna.get(1));
        Assert.assertTrue(driver.getCurrentUrl().contains("test"));
        driver.close();
        driver.switchTo().window(mainWindow);
        Assert.assertTrue(mainUrl.contains("window"));
    }

    @AfterMethod
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }



