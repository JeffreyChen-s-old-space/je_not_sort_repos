package java_course;

import java.util.Scanner;

public class A410877027_10_2 {

    static int divideNum = 1;
    static int[] numArray;

    public static void main(String[] argv) {

        int numberOfNumber = 1;

        Scanner numSacnner = new Scanner(System.in);

        while (true) {

            //幾個數字跟除 幾
            numberOfNumber = numSacnner.nextInt();
            divideNum = numSacnner.nextInt();

            numArray = new int[numberOfNumber];

            //中斷
            if (numberOfNumber == 0 && divideNum == 0)
                break;

            //輸入數字
            for (int addNum = 0; addNum < numberOfNumber; addNum++) {
                numArray[addNum] = numSacnner.nextInt();
            }

            //自定義排序
            sort(numArray);

            //印出一開始輸入的數字
            System.out.print(numberOfNumber + " " + divideNum + "\n");

            //印出排序後的數字
            for (int outputNum = 0; outputNum < numArray.length; outputNum++) {
                System.out.println(numArray[outputNum]);
            }

        }
    }

    //判斷是奇數還是偶數
    static boolean isOdd(int num) {
        return num % 2 != 0;
    }

    /*
    改過的泡沫排序 照規則排
    依照每個數字除M的餘數從小排到大
    如果兩個數字%M一樣的話:
    如果兩個數字都是基數的話 ⇒ 數字大的為優先
    如果兩個數字都是偶數的話 ⇒ 數字小的為優先
    如果兩個數字是一基一偶的話 ⇒ 基數為優先
     */
    static void sort(int[] numArray) {
        for (int sortStart1 = 0; sortStart1 < numArray.length; sortStart1++) {
            for (int sortStart = 0; sortStart < numArray.length - 1 - sortStart1; sortStart++) {
                int num1 = numArray[sortStart], num2 = numArray[sortStart + 1];
                if (num1 % divideNum == num2 % divideNum) {
                    if (isOdd(num1) && isOdd(num2)) {
                        if (num1 > num2) {
                            numArray[sortStart] = num1;
                            numArray[sortStart + 1] = num2;
                        } else {
                            numArray[sortStart] = num2;
                            numArray[sortStart + 1] = num1;
                        }
                    } else if (!isOdd(num1) && !isOdd(num2)) {
                        if (num1 > num2) {
                            numArray[sortStart] = num2;
                            numArray[sortStart + 1] = num1;
                        } else {
                            numArray[sortStart] = num1;
                            numArray[sortStart + 1] = num2;
                        }
                    } else {
                        if (isOdd(num1)) {
                            numArray[sortStart] = num1;
                            numArray[sortStart + 1] = num2;
                        } else {
                            numArray[sortStart] = num2;
                            numArray[sortStart + 1] = num1;
                        }
                    }
                } else {
                    if (num1 % divideNum > num2 % divideNum) {
                        numArray[sortStart] = num2;
                        numArray[sortStart + 1] = num1;
                    } else {
                        numArray[sortStart] = num1;
                        numArray[sortStart + 1] = num2;
                    }
                }
            }
        }
    }
}


