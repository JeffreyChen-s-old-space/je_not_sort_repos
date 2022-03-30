package java_course;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class A410877027_11_1 {

    static int numsCount;
    static String nums = "not Null";
    static int size = 1;
    static List<String> numsLine1;
    static List<String> numsLine2;
    static List<String> numsLine3;
    static List<String> numsLine4;
    static List<String> numsLine5;

    public static void main(String[] argv) {

        while (size != 0 && nums.charAt(0) != '0') {

            //初始的表 未依size重建
            numsLine1 = Arrays.asList(" - ", "   ", " - ", " - ", "   ", " - ", " - ", " - ", " - ", " - ");
            numsLine2 = Arrays.asList("| |", "  |", "  |", "  |", "| |", "|  ", "|  ", "  |", "| |", "| |");
            numsLine3 = Arrays.asList("   ", "   ", " - ", " - ", " - ", " - ", " - ", "   ", " - ", " - ");
            numsLine4 = Arrays.asList("| |", "  |", "|  ", "  |", "  |", "  |", "| |", "  |", "| |", "  |");
            numsLine5 = Arrays.asList(" - ", "   ", " - ", " - ", "   ", " - ", " - ", "   ", " - ", " - ");

            // 輸入
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String inputLine = inputReader.readLine();
                String[] input = inputLine.split(" ");
                size = Integer.parseInt(input[0]);
                nums = input[1];

                //0 0 中斷
                if (size == 0 && nums.charAt(0) == '0')
                    System.exit(0);

                //建表用
                StringBuilder[] stringBuilders = new StringBuilder[5];
                for (int initStringBuilder = 0; initStringBuilder < 5; initStringBuilder++)
                    stringBuilders[initStringBuilder] = new StringBuilder();

                //建表
                reFormat();

                //找到要放入那些數字
                for (numsCount = 0; numsCount < nums.length(); numsCount++) {
                    switch (nums.charAt(numsCount)) {
                        case '0':
                            getNum(0, stringBuilders);
                            break;
                        case '1':
                            getNum(1, stringBuilders);
                            break;
                        case '2':
                            getNum(2, stringBuilders);
                            break;
                        case '3':
                            getNum(3, stringBuilders);
                            break;
                        case '4':
                            getNum(4, stringBuilders);
                            break;
                        case '5':
                            getNum(5, stringBuilders);
                            break;
                        case '6':
                            getNum(6, stringBuilders);
                            break;
                        case '7':
                            getNum(7, stringBuilders);
                            break;
                        case '8':
                            getNum(8, stringBuilders);
                            break;
                        case '9':
                            getNum(9, stringBuilders);
                            break;
                    }
                }

                //輸出結果
                System.out.println(stringBuilders[0].toString());
                for (int printSize = 0; printSize < size; printSize++)
                    System.out.println(stringBuilders[1].toString());
                System.out.println(stringBuilders[2].toString());
                for (int printSize = 0; printSize < size; printSize++)
                    System.out.println(stringBuilders[3].toString());
                System.out.println(stringBuilders[4].toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    static void reFormat() {
        StringBuilder newVerticalSize = new StringBuilder();
        StringBuilder newSpaceSize = new StringBuilder();

        for (int newSize = 0; newSize < size; newSize++) {
            newVerticalSize.append("-");
            newSpaceSize.append(" ");
        }

        for (int reset = 0; reset < numsLine1.size(); reset++) {

            numsLine1.set(reset, numsLine1.get(reset).replace("-", newVerticalSize.toString()));
            numsLine1.set(reset, numsLine1.get(reset).replace("   ", "  " + newSpaceSize.toString()) + " ");

            numsLine3.set(reset, numsLine3.get(reset).replace("-", newVerticalSize.toString()));
            numsLine3.set(reset, numsLine3.get(reset).replace("   ", "  " + newSpaceSize.toString()) + " ");

            numsLine5.set(reset, numsLine5.get(reset).replace("-", newVerticalSize.toString()));
            numsLine5.set(reset, numsLine5.get(reset).replace("   ", "  " + newSpaceSize.toString()) + " ");
        }

        for (int reset = 0; reset < numsLine2.size(); reset++) {
            if (numsLine2.get(reset).equals("  |"))
                numsLine2.set(reset, numsLine2.get(reset).replace("  |", newSpaceSize.toString()) + " | ");
            if (numsLine2.get(reset).equals("|  "))
                numsLine2.set(reset, numsLine2.get(reset).replace("|  ", "| " + newSpaceSize.toString() + " "));
            if (numsLine2.get(reset).equals("| |"))
                numsLine2.set(reset, numsLine2.get(reset).replace("| |", "|" + newSpaceSize.toString()) + "| ");

            if (numsLine4.get(reset).equals("  |"))
                numsLine4.set(reset, numsLine4.get(reset).replace("  |", newSpaceSize.toString()) + " | ");
            if (numsLine4.get(reset).equals("|  "))
                numsLine4.set(reset, numsLine4.get(reset).replace("|  ", "| " + newSpaceSize.toString()) + " ");
            if (numsLine4.get(reset).equals("| |"))
                numsLine4.set(reset, numsLine4.get(reset).replace("| |", "|" + newSpaceSize.toString()) + "| ");
        }
    }

    static void getNum(int id, StringBuilder[] stringBuilders) {
        stringBuilders[0].append(numsLine1.get(id));
        stringBuilders[1].append(numsLine2.get(id));
        stringBuilders[2].append(numsLine3.get(id));
        stringBuilders[3].append(numsLine4.get(id));
        stringBuilders[4].append(numsLine5.get(id));
    }
}
