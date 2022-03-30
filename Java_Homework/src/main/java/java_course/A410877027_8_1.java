package java_course;

import java.util.Scanner;

public class A410877027_8_1 {

    public static void main(String[] argv) {

        Scanner rcScanner = new Scanner(System.in);

        int row = 1, column = 1, answer;

        while (row != 0 && column != 0) {

            //讀值
            row = rcScanner.nextInt();
            column = rcScanner.nextInt();

            //0 0 終止
            if (row == 0 && column == 0)
                break;


            int minNum = Math.min(row, column), maxNum = Math.max(row, column);

            //只有一列或一行 直接填滿 所以 = max(row,column)
            if (minNum == 1) {
                System.out.printf("%d knights may be placed on a %d row %d column board.\n", Math.max(row, column), row, column);
                continue;
            }

            //只有2列或2行
            if (minNum == 2) {
                //是否可以擺4個騎士 決定算式
                int count = maxNum % 4;
                if (count > 0) {
                    answer = (maxNum / 4) * 4 + (count > 1 ? 4 : 2);
                } else {
                    answer = (maxNum / 4) * 4;
                }
            } // 大於 2列或2行 算式一樣
            else
                answer = (minNum * maxNum + 1) / 2;
            System.out.printf("%d knights may be placed on a %d row %d column board.\n", answer, row, column);
        }
    }
}

