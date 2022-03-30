package java_course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class A410877027_10_1 {

    public static void main(String[] argv) {

        //用到的的變數

        int bingoTime = 0;
        ;
        //Bingo的 5*5 表
        List<int[]> bingoList = new ArrayList<>();

        //加入 5個 {0,0,0,0,0}
        for (int bingoSize = 0; bingoSize < 5; bingoSize++)
            bingoList.add(new int[]{0, 0, 0, 0, 0});

        Scanner numberScanner = new Scanner(System.in);

        bingoTime = numberScanner.nextInt();

        while (bingoTime-- > 0) {
            //輸入5*5表的數值
            for (int inputRow = 0; inputRow < 5; inputRow++) {
                for (int inputCol = 0; inputCol < 5; inputCol++) {
                    if (inputRow == 2 && inputCol == 2) {
                        continue;
                    } else
                        bingoList.get(inputRow)[inputCol] = numberScanner.nextInt();
                }
            }

            //比對輸入的75個數字
            stopThis:
            {
                //喊到第幾個
                int whatNumBingo = 1;
                boolean checkFlag = false;
                int count = 0;
                for (int inputBingoNumbers = 0; inputBingoNumbers < 75; inputBingoNumbers++) {
                    int bingoNumbers = numberScanner.nextInt();
                    for (int checkRow = 0; checkRow < 5; checkRow++) {
                        for (int checkCol = 0; checkCol < 5; checkCol++) {
                            if (bingoList.get(checkRow)[checkCol] == bingoNumbers) {
                                bingoList.get(checkRow)[checkCol] = 0;
                            }
                            if (checkCol == 4) {
                                checkFlag = true;
                            }
                        }
                        //橫排Bingo
                        if (checkRow == 0 && checkFlag) {
                            if (Arrays.equals(bingoList.get(checkRow), new int[]{0, 0, 0, 0, 0})) {
                                System.out.println("BINGO after " + whatNumBingo + " numbers announced");
                                break stopThis;
                            }
                        }

                        //直排Bingo
                        if (checkRow == 4) {
                            for (int checkRowNum = 0; checkRowNum < 5; checkRowNum++) {
                                if (bingoList.get(0)[checkRowNum] == 0)
                                    count++;
                                if (bingoList.get(1)[checkRowNum] == 0)
                                    count++;
                                if (bingoList.get(2)[checkRowNum] == 0)
                                    count++;
                                if (bingoList.get(3)[checkRowNum] == 0)
                                    count++;
                                if (bingoList.get(4)[checkRowNum] == 0)
                                    count++;
                                if (count == 5) {
                                    System.out.println("BINGO after " + whatNumBingo + " numbers announced");
                                    break stopThis;
                                } else
                                    count = 0;
                            }
                        }
                    }
                    whatNumBingo++;
                }
            }
        }
    }
}


