import java.util.Scanner;

public class A410877027_3_1 {

    public static void main(String[] argv) {
        // 掃描測資用
        Scanner dataScanner = new Scanner(System.in);
        // 地圖的x大小
        int mapSizeX = dataScanner.nextInt();
        // 地圖的y大小
        int mapSizeY = dataScanner.nextInt();
        // 檢查地圖 (有機器人遺失過就能正常走)
        boolean[][] mapCheck = new boolean[mapSizeX + 1][mapSizeY + 1];
        // 掃描直到測資結束
        while (dataScanner.hasNext()) {
            // 判斷機器人是否遺失
            boolean isRobotLost = false;
            // 現在機器人位置x
            int robotCurrentX = dataScanner.nextInt();
            // 現在機器人位置y
            int robotCurrentY = dataScanner.nextInt();
            // 現在方向
            String nowRobotDirection = dataScanner.next();
            // 機器人指令
            String robotCommand = dataScanner.next();
            // 讀取全部指令
            for (int index = 0; index < robotCommand.length(); index++) {
                switch (robotCommand.charAt(index)) {
                    // 前進指令 不改變方向 改變座標
                    case 'F':
                        int tempX = robotCurrentX;
                        int tempY = robotCurrentY;
                        switch (nowRobotDirection) {
                            case "N":
                                tempY++;
                                break;
                            case "S":
                                tempY--;
                                break;
                            case "W":
                                tempX--;
                                break;
                            case "E":
                                tempX++;
                                break;
                        }
                        //如果超出地圖範圍則遺失
                        if (tempX < 0 || tempY < 0 || tempX > mapSizeX || tempY > mapSizeY) {
                            if (!mapCheck[robotCurrentX][robotCurrentY]) {
                                //遺失之後改變路徑為可走
                                mapCheck[robotCurrentX][robotCurrentY] = true;
                                //印出遺失位置
                                System.out.println(robotCurrentX + " " + robotCurrentY + " " + nowRobotDirection + " LOST");
                                //遺失標籤為真
                                isRobotLost = true;
                                break;
                            }
                        } else {//否則改變座標
                            robotCurrentX = tempX;
                            robotCurrentY = tempY;
                        }
                        break;
                    //右轉 只改變方向
                    case 'R':
                        switch (nowRobotDirection) {
                            case "N":
                                nowRobotDirection = "E";
                                break;
                            case "S":
                                nowRobotDirection = "W";
                                break;
                            case "W":
                                nowRobotDirection = "N";
                                break;
                            case "E":
                                nowRobotDirection = "S";
                                break;
                        }
                        break;
                    //左轉 只改變方向
                    case 'L':
                        switch (nowRobotDirection) {
                            case "N":
                                nowRobotDirection = "W";
                                break;
                            case "S":
                                nowRobotDirection = "E";
                                break;
                            case "W":
                                nowRobotDirection = "S";
                                break;
                            case "E":
                                nowRobotDirection = "N";
                                break;
                        }
                        break;
                }
                // 如果都沒問題則印出位置跟方向
                if (index == robotCommand.length() - 1 && !isRobotLost)
                    System.out.println(robotCurrentX + " " + robotCurrentY + " " + nowRobotDirection);
            }
        }
    }
}
