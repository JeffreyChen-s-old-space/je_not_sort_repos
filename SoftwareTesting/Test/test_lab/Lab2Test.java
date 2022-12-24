package test_lab;

import lab.Lab2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lab2Test {

    @Test
    public void testRegular(){
        Lab2 lab2 = new Lab2();
        assertEquals(lab2.checkTriangle(3, 3, 3), "Regular");
    }

    @Test
    public void testIsosceles(){
        Lab2 lab2 = new Lab2();
        assertEquals(lab2.checkTriangle(3, 3, 2), "Isosceles");
    }


    @Test
    public void testNotTriangle(){
        Lab2 lab2 = new Lab2();
        assertEquals(lab2.checkTriangle(3, 2, 1), "NotTriangle");
    }

    @Test
    public void testValueMoreThanZero(){
        Lab2 lab2 = new Lab2();
        assertEquals(lab2.checkRightTriangle(-1, 2, 1), "Value Need > 0");
        assertEquals(lab2.checkRightTriangle(2, -1, 3), "Value Need > 0");
        assertEquals(lab2.checkRightTriangle(2, 2, -1), "Value Need > 0");
        assertEquals(lab2.checkRightTriangle(-1, -1, -1), "Value Need > 0");
        assertEquals(lab2.checkRightTriangle(-1, 2, -1), "Value Need > 0");
    }

    @Test
    public void testRightTriangle(){
        Lab2 lab2 = new Lab2();
        assertEquals(lab2.checkRightTriangle(3, 4, 5), "RightTriangle");
    }

    @Test
    public void testNotRightTriangle(){
        Lab2 lab2 = new Lab2();
        assertEquals(lab2.checkRightTriangle(2, 2, 5), "NotRightTriangle");
    }


}
