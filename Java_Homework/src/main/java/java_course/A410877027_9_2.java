package java_course;

import java.util.Scanner;

// Rockabye Tobby
public class A410877027_9_2 {

    public static void main(String[] argv) {
        Scanner scanner = new Scanner(System.in);
        int times = Integer.parseInt(scanner.nextLine());

        //數據都讀進來
        for (int i = 0; i < times; i++) {
            String[] buffer = scanner.nextLine().split(" ");
            int pillNum = Integer.parseInt(buffer[0]);
            int pillTime = Integer.parseInt(buffer[1]);
            //有幾棵藥
            Pill[] pills = new Pill[pillNum];
            for (int j = 0; j < pillNum; j++) {
                String[] buffer2 = scanner.nextLine().split(" ");
                String pillName = buffer2[0];
                int frequency = Integer.parseInt(buffer2[1]);
                //依照數量產生藥
                pills[j] = new Pill(pillName, frequency, frequency);
            }
            for (int j = 0; j < pillTime; j++) {
                int temp = Integer.MAX_VALUE, index = 0;
                //取得最快要吃的
                for (int k = 0; k < pillNum; k++) {
                    if (pills[k].moment < temp) {
                        temp = pills[k].moment;
                        index = k;
                    }
                }
                //吃藥並疊加時間
                pills[index].eat();
                pills[index].moment += pills[index].frequency;
            }
        }
        scanner.close();
    }

    static class Pill {
        String pillName;
        int frequency;
        int moment;

        public Pill(String pillName, int frequency, int moment) {
            super();
            this.pillName = pillName;
            this.frequency = frequency;
            this.moment = moment;
        }

        void eat() {
            System.out.println(this.moment + " " + this.pillName);
        }
    }
}


