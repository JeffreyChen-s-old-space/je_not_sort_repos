package datastructure.exam2;

public class DsTest2 {
    public static void main(String[] argv) {
        System.out.println(power2(2, -1));
        System.out.println(power(2, -1));
    }

    public static double power2(double x, int n) {
        if (n < 0)
            return 1.0 / power2(x, -n);
        if (n == 0)
            return 1;
        else
            return x * power2(x, n - 1);
    }

    public static double power(int x, int n) {
        double temp = x;
        if (n == 0)
            return 1;
        for (int j = Math.abs(n); j > 1; j--)
            temp *= x;
        if (n < 0)
            temp = 1.0 / temp;
        return temp;
    }
}
