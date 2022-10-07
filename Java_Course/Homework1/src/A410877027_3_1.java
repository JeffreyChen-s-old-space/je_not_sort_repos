import java.util.Scanner;

public class A410877027_3_1 {

    public static void main(String[] argv) {
        Scanner dataScanner = new Scanner(System.in);
        int mapSizeX = dataScanner.nextInt();
        int mapSizeY = dataScanner.nextInt();
        boolean[][] mapCheck = new boolean[mapSizeX + 1][mapSizeY + 1];
        while (dataScanner.hasNext()) {
            boolean isRobotLost = false;
            int robotCurrentX = dataScanner.nextInt();
            int robotCurrentY = dataScanner.nextInt();
            String nowRobotDirection = dataScanner.next();
            String robotCommand = dataScanner.next();
            for (int index = 0; index < robotCommand.length(); index++) {
                switch (robotCommand.charAt(index)) {
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
                        if (tempX < 0 || tempY < 0 || tempX > mapSizeX || tempY > mapSizeY) {
                            if (!mapCheck[robotCurrentX][robotCurrentY]) {
                                mapCheck[robotCurrentX][robotCurrentY] = true;
                                System.out.println(robotCurrentX + " " + robotCurrentY + " " + nowRobotDirection + " LOST");
                                isRobotLost = true;
                                break;
                            }
                        } else {
                            robotCurrentX = tempX;
                            robotCurrentY = tempY;
                        }
                        break;
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
                if (index == robotCommand.length() - 1 && !isRobotLost)
                    System.out.println(robotCurrentX + " " + robotCurrentY + " " + nowRobotDirection);
            }
        }
    }
}
