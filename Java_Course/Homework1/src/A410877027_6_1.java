import java.util.*;

// main class 讀取有幾個油井 x y 二維陣列 * 代表沒有 @ 代表油井
public class A410877027_6_1 {

    // 掃描測資用
    static Scanner dataScanner = new Scanner(System.in);
    //地圖尋找用 直接用Boolean 預先標記好油井位置
    static List<List<Boolean>> map = new ArrayList<>();
    //掃描周圍九宮格用
    static List<Integer> checkX = new ArrayList<>();
    static ArrayList<Integer> checkY = new ArrayList<>();
    //掃描地圖 x size
    static int mapYSize;
    //掃描地圖 y size
    static int mapXSize;

    // 遞迴檢查油井
    static void checkOil(int y, int x) {
        // 如果檢查過或是超出界線就中斷
        if (!isCanCheckOrOutOfMap(y, x) || !map.get(y).get(x))
            return;
        else {
            // 沒檢查過把這裡標記成false
            map.get(y).set(x, false);
            // 開始遞迴檢查九宮格
            for (int searchX : checkX) {
                for (int searchY : checkY)
                    checkOil(y + searchY, x + searchX);
            }
        }
    }

    // 有沒有超出地圖檢查
    static boolean isCanCheckOrOutOfMap(int y, int x) {
        return y >= 0 && y < mapYSize && x >= 0 && x < mapXSize;
    }


    public static void main(String[] argv) {

        // 要查找的座標 九宮格
        Collections.addAll(checkX, 0, 1, 1, 1, 0, -1, -1, -1);
        Collections.addAll(checkY, 1, 1, 0, -1, -1, -1, 0, 1);

        while (true) {
            //初始化map
            map = new ArrayList<>();
            //答案個數
            int answer = 0;
            //掃描地圖 x size
            mapYSize = dataScanner.nextInt();
            //掃描地圖 y size
            mapXSize = dataScanner.nextInt();
            //讀取下一行
            dataScanner.nextLine();
            // 都是 0 退出
            if (mapYSize == 0 && mapXSize == 0)
                break;

            //讀取油井 並放入map
            for (int createMapY = 0; createMapY < mapYSize; createMapY++) {
                String mapData = dataScanner.nextLine();
                ArrayList<Boolean> mapDataArrayList = new ArrayList<>();
                for (int createMapX = 0; createMapX < mapXSize; createMapX++) {
                    if (mapData.charAt(createMapX) == '*')
                        mapDataArrayList.add(false);
                    else
                        mapDataArrayList.add(true);
                }
                map.add(mapDataArrayList);
            }

            // 開始遞迴檢查每個
            for (int indexY = 0; indexY < mapYSize; indexY++) {
                for (int indexX = 0; indexX < mapXSize; indexX++) {
                    if (map.get(indexY).get(indexX)) {
                        answer += 1;
                        checkOil(indexY, indexX);
                    }
                }
            }
            System.out.println(answer);
        }
        //關閉輸入流
        dataScanner.close();
    }
}

