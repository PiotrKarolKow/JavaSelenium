import org.example.Calculator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CalculatorTest {
    private Calculator calc;


    @BeforeClass
    public void setUp() {
        calc = new Calculator();

    }

    @Test
    public void addTest() {
        int x = 3;
        int y = 4;
        int wynik = calc.add(x,y);
        Assert.assertEquals(wynik, 7);

    }
    @Test
    public void multiplyTest() {
        int x = 3;
        int y = 2;
        int wynik = calc.multiply(x, y);
        Assert.assertEquals(wynik, 6);
    }

    @AfterClass
        public void teatDown() {
        System.out.println("Koniec");
    }
}