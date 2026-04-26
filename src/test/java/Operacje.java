import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
        ChromeOptions options = new ChromeOptions();
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

    @Test
    public void multiplyWindowTest() {
        driver.get("https://letcode.in/window");
        String mainWindow = driver.getWindowHandle();
        String mainUrl = driver.getCurrentUrl();
        int verifiedWindows = 0;

        driver.findElement(By.id("multi")).click();
        List<String> allWindows = new ArrayList<>(driver.getWindowHandles());
        for (String windowHandle : allWindows) {
            if (windowHandle.equals(mainWindow)) {
                continue;
            }
            driver.switchTo().window(windowHandle);
            String currentUrl = driver.getCurrentUrl().toLowerCase();
            String what = driver.findElement(By.cssSelector("h1.title")).getText();

            if (currentUrl.contains("/dropdowns")) {
                Assert.assertEquals(what, "Dropdown");
                verifiedWindows++;
                driver.close();
                continue;
            }

            if (currentUrl.contains("/alert")) {
                Assert.assertEquals(what, "Alert");
                verifiedWindows++;
                driver.close();
                continue;
            }
        }

        driver.switchTo().window(mainWindow);
        Assert.assertEquals(verifiedWindows, 2);
    }

    @Test
    public void selectListTest() {
        driver.get("https://letcode.in/dropdowns");
        WebElement listaId = driver.findElement(By.id("fruits"));
        wait.until(ExpectedConditions.elementToBeClickable(listaId));
        Select listaSelect = new Select(listaId);
        listaSelect.selectByVisibleText("Orange");
        WebElement tekst = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='subtitle']")));
    }



        @AfterMethod
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }





