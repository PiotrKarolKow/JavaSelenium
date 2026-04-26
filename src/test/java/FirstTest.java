import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
//        driver.manage().
    }

    @Test
    public void browserTest() {
        driver.get("http://uitestingplayground.com/textinput");
    }

    @AfterClass
    public void afterClass() {
        if (driver != null){
            driver.quit();
        }

    }
}
