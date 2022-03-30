package java_course;

import java.util.HashMap;
import java.util.Scanner;

public class A410877027_12_1 {

    public static void main(String[] argv) {
        Scanner inputScanner = new Scanner(System.in);
        int inputNum = inputScanner.nextInt();
        for (int times = 1; times <= inputNum; times++) {
            HashMap<String, Integer> works = new HashMap<>();
            int lessons = inputScanner.nextInt();
            for (; lessons > 0; lessons--)
                works.put(inputScanner.next(), inputScanner.nextInt());
            int days = inputScanner.nextInt();
            String whatWork = inputScanner.next();
            System.out.print("Case " + times + ": ");
            if (works.get(whatWork) == null)
                System.out.println("Do your own homework!");
            else if (days >= works.get(whatWork))
                System.out.println("Yessss");
            else if (days + 5 >= works.get(whatWork))
                System.out.println("Late");
            else
                System.out.println("Do your own homework!");
        }
    }
}
