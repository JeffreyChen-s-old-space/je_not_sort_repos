import java.util.HashSet;
import java.util.Scanner;

// hashset 來確保沒有重複資料 限制字串長度為3 main只負責讀資料
public class A410877027_7_1 {

    static HashSet<String> hashSet;

    // 取得不重複的所有組合
    static void getPermutation(String stringToGetPermutation) {
        //如果空字串 或是長度小於3 不需要進行
        if (stringToGetPermutation == null || stringToGetPermutation.length() < 3)
            return;
        // 收集字串用
        StringBuilder stringBuilder;
        for (int strLength = 0; strLength < (1 << stringToGetPermutation.length()); ++strLength) {
            //每輪收集完都要重設
            stringBuilder = new StringBuilder();
            //判斷字串是否是正確長度
            if (countSizeOfString(strLength) == 3) {
                for (int collectIndex = 0; collectIndex < stringToGetPermutation.length(); ++collectIndex) {
                    //把字元加進去
                    if ((strLength & (1 << collectIndex)) > 0) {
                        stringBuilder.append(stringToGetPermutation.charAt(collectIndex));
                    }
                }
                // hashset 添加完整字串
                hashSet.add(stringBuilder.toString());
            }
        }
    }

    //計算有多少長度的字串需要組合 用位元運算去算
    static int countSizeOfString(int length) {
        int countSize = 0;
        while (length > 0) {
            length = length & (length - 1);
            countSize++;
        }
        return countSize;
    }

    public static void main(String[] args) {
        //掃描資料用
        Scanner dataScanner = new Scanner(System.in);
        //多少測資
        int howManyData = dataScanner.nextInt();
        //讀取空行
        dataScanner.nextLine();
        //依照測資數量進行取得組合 每次都要重設hashset 最後印出hashset的size就是答案
        for (int dataIndex = 0; dataIndex < howManyData; dataIndex++) {
            // reset
            hashSet = new HashSet<>();
            // 取得組合
            getPermutation(dataScanner.nextLine());
            //印出大小
            System.out.println(hashSet.size());
        }

        dataScanner.close();
    }

}