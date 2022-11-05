import java.util.*;

// main class 需要讀取兩個字串來比對相同的字元 並排序輸出 可能包含空字串 遇到空字串時輸出空行
public class A410877027_5_1 {

    public static void main(String[] argv) {
        // 掃描測資用
        Scanner dataScanner = new Scanner(System.in);

        HashMap<String, Integer> wordCountHashMap1 = null;
        HashMap<String, Integer> wordCountHashMap2 = null;
        //讀到沒資料
        while (dataScanner.hasNextLine()) {

            // 創建一個HashMap 儲存第一個字串的字母數 初始就填入 a-z
            wordCountHashMap1 = new HashMap<>();
            for (int letter = 'a'; letter <= 'z'; letter++)
                wordCountHashMap1.put(((String.valueOf((char) letter))), 0);

            // 創建一個HashMap 儲存第二個字串的字母數 初始就填入 a-z
            wordCountHashMap2 = new HashMap<>();
            for (int letter = 'a'; letter <= 'z'; letter++)
                wordCountHashMap2.put(((String.valueOf((char) letter))), 0);


            //如果其中為空行印出空行並繼續
            makeHashMap(dataScanner, wordCountHashMap1, wordCountHashMap2);

            // 循環檢查 並印出答案
            for (int letter = 'a'; letter <= 'z'; letter++) {
                if (wordCountHashMap1.get(String.valueOf((char) letter)) > 0 && wordCountHashMap2.get(String.valueOf((char) letter)) > 0)
                    for (int printCount = 0;
                         printCount < Math.min(
                                 wordCountHashMap1.get(String.valueOf((char) letter)),
                                 wordCountHashMap2.get(String.valueOf((char) letter))
                         );
                         printCount++) {
                        List<String> key = new ArrayList<>(wordCountHashMap1.keySet());
                        // 用 -'a'(97) 取得list的位置 0~25 來取得字母
                        System.out.print(key.get(letter - 97));
                    }
            }
            System.out.println();
        }
    }

    //讀取字串並把字元出現次數儲存進HashMap用
    private static void makeHashMap(Scanner dataScanner, HashMap<String, Integer> wordCountHashMap1, HashMap<String, Integer> wordCountHashMap2) {
        String wordString1 = dataScanner.nextLine();
        String wordString2 = dataScanner.nextLine();
        //遇到空行就輸出空行
        for (int wordStringIndex = 0; wordStringIndex < wordString1.length(); wordStringIndex++) {
            wordCountHashMap1.put(
                    String.valueOf(wordString1.charAt(wordStringIndex))
                    , wordCountHashMap1.get(String.valueOf(wordString1.charAt(wordStringIndex))) + 1);
        }
        for (int wordStringIndex = 0; wordStringIndex < wordString2.length(); wordStringIndex++) {
            wordCountHashMap2.put(
                    String.valueOf(wordString2.charAt(wordStringIndex))
                    , wordCountHashMap2.get(String.valueOf(wordString2.charAt(wordStringIndex))) + 1);
        }
    }
}
