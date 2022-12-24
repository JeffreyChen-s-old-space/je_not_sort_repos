package lab;

public class lab2 extends lab1 {

    public String checkRightTriangle(int a, int b, int c) {
        if (a < 0 || b < 0 || c < 0)
            return "NotRightTriangle";
        else {
            if ((a*a) == (c*c) + (b*b) || (b*b) == (a*a) + (c*c) || (c*c) == (a*a) + (b*b))
                return "RightTriangle";
        }
        return "NotRightTriangle";
    }

}
