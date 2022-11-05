import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// main class 需要讀取word來判斷有幾個符合的word 並輸出符合的word 如沒有符合的word 也要輸出
public class A410877027_4_1 {

    public static void main(String[] argv) {
        // 掃描測資用
        Scanner dataScanner = new Scanner(System.in);
        // 多少測資
        int wordNum = dataScanner.nextInt();
        // 掃掉空行
        dataScanner.nextLine();
        while (wordNum > 0) {
            // 幾個單字
            int howMuchWord = dataScanner.nextInt();
            // 儲存掃描並處理的單字
            List<char[]> wordList = new ArrayList<>();
            //儲存未改變的單字以印出
            List<char[]> notChangeWordList = new ArrayList<>();
            // 讀測試用word
            while (howMuchWord > 0) {
                String notChangeWord = dataScanner.next();
                // 轉成字元陣列
                char[] tempToChange = notChangeWord.toCharArray();
                // 儲存未改變陣列
                notChangeWordList.add(notChangeWord.toCharArray());
                // 排序陣列 單字一樣的話排序一定一樣
                Arrays.sort(tempToChange);
                // 儲存改變後的用來比對
                wordList.add(tempToChange);
                // 每讀一筆-1
                howMuchWord -= 1;
            }
            //開始讀取要判斷的
            while (true) {
                //要判斷的單詞
                String word = dataScanner.next();
                //用來記錄幾個符合的
                int count = 1;
                //用來記錄是否都未符合
                boolean checkFlag = false;
                // 如果END就退出
                if (word.equals("END")) {
                    wordNum -= 1;
                    break;
                }
                // 印出判斷的單詞
                System.out.println("Anagrams for: " + word);
                // 開始跟之前的比對
                for (int index = 0; index < wordList.size(); index++) {
                    char[] compareChar = word.toCharArray();
                    Arrays.sort(compareChar);
                    //如果比對一樣則符合並印出
                    if (Arrays.equals(wordList.get(index), compareChar)) {
                        System.out.println("  " + count + ") " + new String(notChangeWordList.get(index)));
                        count += 1;
                        checkFlag = true;
                    }
                }
                // 完全沒有符合的印出
                if (!checkFlag)
                    System.out.println("No anagrams for: " + word);
            }
            System.out.println();
        }
    }
}
