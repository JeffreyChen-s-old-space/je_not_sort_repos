import lab.MyPrimeNumber;
import org.junit.*;

public class TestPrimeNumber {

    private MyPrimeNumber primeNumChecker;

    @Before
    public void setup(){
        primeNumChecker = new MyPrimeNumber();
    }

    @After
    public void tearDown(){

    }

    @Test
    public void testlsPrimeNumberTC1(){
        Assert.assertFalse(primeNumChecker.isPrimeNumber(1));
    }

    @Test
    public void testlsPrimeNumberTC2(){
        Assert.assertFalse(primeNumChecker.isPrimeNumber(2));
    }

    @Test
    public void testlsPrimeNumberTC3(){
        Assert.assertFalse(primeNumChecker.isPrimeNumber(4));
    }

}
