import java.util.Scanner;

// main class 主類別 只包含main 讀取測資及輸出答案
public class A410877027_3_1 {

    public static void main(String[] argv) {
        // 掃描測資用
        Scanner dataScanner = new Scanner(System.in);
        // 讀取測資需要的變數
        int left, right, height, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        // 最多 10000個
        int[] buildingHeight = new int[10001];
        // 讀取直到沒測資
        while (dataScanner.hasNext()) {
            // 讀左邊
            left = dataScanner.nextInt();
            // 讀高度
            height = dataScanner.nextInt();
            // 讀右邊
            right = dataScanner.nextInt();
            // 記錄每個最高高度
            for (int buildingStart = left; buildingStart < right; buildingStart++) {
                buildingHeight[buildingStart] = Math.max(buildingHeight[buildingStart], height);
            }
            // 左邊永遠保持最小
            min = Math.min(left, min);
            // 右邊保持最大
            max  = Math.max(right, max);
        }
        // 現在的左邊
        int current = buildingHeight[min];
        // 輸出最左邊 及最左邊高度
        System.out.print(min + " " + current);
        // 由最左至最右 輸出比目前高的位置
        for(int checkIndex = min; checkIndex <= max; checkIndex++){
            if(buildingHeight[checkIndex] != current) {
                current = buildingHeight[checkIndex];
                System.out.print( " " + checkIndex + " " + current);
            }
        }
        System.out.println();
    }
}
