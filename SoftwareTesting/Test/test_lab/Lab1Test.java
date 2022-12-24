package test_lab;

import lab.Lab1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lab1Test{

    @Test
    public void testRegular(){
        Lab1 lab1 = new Lab1();
        assertEquals(lab1.checkTriangle(3, 3, 3), "Regular");
    }

    @Test
    public void testIsosceles(){
        Lab1 lab1 = new Lab1();
        assertEquals(lab1.checkTriangle(3, 3, 2), "Isosceles");
    }


    @Test
    public void testNotTriangle(){
        Lab1 lab1 = new Lab1();
        assertEquals(lab1.checkTriangle(3, 2, 1), "NotTriangle");
    }


}
