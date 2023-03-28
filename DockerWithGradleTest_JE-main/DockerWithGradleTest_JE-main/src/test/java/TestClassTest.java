import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestClassTest {

    @Test
    public void testPI(){
        TestClass testClass = new TestClass();
        assert Math.PI == testClass.returnPI():"Not PI";
        System.out.println(Math.PI == testClass.returnPI());
    }

}