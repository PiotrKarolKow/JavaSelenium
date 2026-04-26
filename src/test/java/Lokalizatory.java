import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v145.page.model.WebAppManifest;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Lokalizatory {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--incognito");
//        options.addArguments("--disable-web-security");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void mainTest() {
        String tekst = "Kotek";
        driver.get("http://uitestingplayground.com/textinput");
        WebElement pole = driver.findElement(By.id("newButtonName"));
        WebElement button = driver.findElement(By.id("updatingButton"));

        pole.clear();
        pole.sendKeys(tekst);
        button.click();

        Assert.assertEquals(button.getText(), tekst);
    }

    @Test
    public void loginValid()
    {
        String inputText = "jakis tekst";
        driver.get("http://uitestingplayground.com/sampleapp");
        driver.findElement(By.name("UserName")).sendKeys(inputText);
        driver.findElement(By.name("Password")).sendKeys("pwd");
        driver.findElement(By.id("login")).click();
        String text = driver.findElement(By.id("loginstatus")).getText();
        Assert.assertEquals(text, "Welcome, "+inputText+"!");
        Assert.assertTrue(text.contains(inputText));
    }

    @Test
    public void invalidLoginTest()
    {
        String inputText = "cokolwiek";
        driver.get("http://uitestingplayground.com/sampleapp");
        driver.findElement(By.name("UserName")).sendKeys(inputText);
        driver.findElement(By.name("Password")).sendKeys("admin123");
        driver.findElement(By.id("login")).click();
        String text = driver.findElement(By.id("loginstatus")).getText();
        Assert.assertEquals(text, "Invalid username/password");
    }

    @Test
    public void bookTest() {
        driver.get("https://books.toscrape.com");
        driver.findElement(By.linkText("Romance")).click();
        List<WebElement> lista = driver.findElements(By.className("product_pod"));
        Assert.assertTrue(lista.size() > 0);
    }

    @Test
    public void relativeLocatorTest() {
        driver.get("https://automationbookstore.dev/");
        WebElement searchEl = driver.findElement(RelativeLocator.with(By.tagName("li")).toLeftOf(By.id("pid6_title")).below(By.id("pid1_thumb")));
        Assert.assertEquals(searchEl.findElement(By.tagName("h2")).getText(), "Java For Testers");
    }

    @Test
    public void ajaxTest() {
        driver.get("https://www.w3schools.com/js/js_ajax_intro.asp");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        String updatedText = driver.findElement(By.id("demo")).getText();
        Assert.assertTrue(updatedText.contains("AJAX is not a programming language."));
    }

    @Test
    public void ajaxTestt() {
        driver.get("https://www.w3schools.com/js/js_ajax_intro.asp");
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("fast-cmp-iframe")));
            wait.until(ExpectedConditions.elementToBeClickable(By.className("fast-cmp-button-primary")));
            driver.findElement(By.className("fast-cmp-button-primary")).click();
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            Reporter.log("Cookies not present");
        }
        driver.findElement(By.xpath("//button[@type='button']")).click();
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(By.id("demo"), "Let AJAX change this text")
        ));
        String updatedText = driver.findElement(By.id("demo")).getText();
        Reporter.log(updatedText, true);
        Assert.assertTrue(updatedText.contains("AJAX is not a programming language."));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}