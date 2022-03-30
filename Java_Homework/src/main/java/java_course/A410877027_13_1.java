package java_course;

import java.util.Scanner;

public class A410877027_13_1 {

    public static void main(String[] argv) {
        A410877027_13_1 hw = new A410877027_13_1();
        Scanner numScanner = new Scanner(System.in);
        int num = -1;
        //輸入 輾轉相除計算距離 輸出
        while (num != 0) {
            num = numScanner.nextInt();
            int leftDistance = num, rightDistance = num;
            while (!hw.isPrime(leftDistance))
                leftDistance--;
            while (!hw.isPrime(rightDistance))
                rightDistance++;
            if (num != 0)
                System.out.println((rightDistance - leftDistance));
        }

    }

    //輾轉相除
    boolean isPrime(int num) {
        int temp;
        int num1 = (int) Math.sqrt(num);
        for (temp = 2; temp <= num1; temp++)
            if (num % temp == 0)
                break;
        return temp >= (num1 + 1);
    }
}
