package java_course;

import java.util.Scanner;

//Wine trading in Gergovia
public class A410877027_9_1 {

    static long persons;
    static long[] bottles;
    static long sum;


    public static void main(String[] argv) {
        //讀資料
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            //人
            persons = scanner.nextLong();
            //沒人就跳出
            if (persons == 0)
                break;
            //幾人幾瓶
            bottles = new long[Math.toIntExact(persons)];

            for (int i = 0; i < persons; i++) {
                bottles[i] = scanner.nextLong();
            }

            long result = 0;

            //計算出來 sum = 前一個  前一個 = 自己 + 前一個 如果sum是負的取絕對值 不然就原本的sum
            for (int i = 1; i < persons; i++) {
                sum = bottles[i - 1];
                bottles[i] += bottles[i - 1];
                result += (sum < 0) ? (sum * -1) : sum;
            }
            System.out.println(result);
        }
    }
}

