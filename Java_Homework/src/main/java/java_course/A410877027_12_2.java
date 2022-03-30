package java_course;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class A410877027_12_2 {

    public static void main(String[] argv) {

        List<String> listDNA = new ArrayList<>();
        Scanner inputScanner = new Scanner(System.in);
        int numberOfInput = inputScanner.nextInt();

        for (; numberOfInput > 0; numberOfInput--) {

            int numOfDNA = inputScanner.nextInt();
            int lengthOfDNA = inputScanner.nextInt();
            listDNA.clear();
            int ans = 0;

            for (int inputOfListDNA = 0; inputOfListDNA < numOfDNA; inputOfListDNA++) {
                listDNA.add(inputScanner.next());
            }

            for (int length = 0; length < lengthOfDNA; length++) {
                int a = 0, c = 0, g = 0, t = 0;
                for (String s : listDNA) {
                    switch (s.charAt(length)) {
                        case 'A':
                            a++;
                            break;
                        case 'C':
                            c++;
                            break;
                        case 'G':
                            g++;
                            break;
                        case 'T':
                            t++;
                            break;
                    }
                }
                int max = Math.max(Math.max(a, c), Math.max(g, t));
                if (max == a) {
                    System.out.print('A');
                } else if (max == c) {
                    System.out.print('C');
                } else if (max == g) {
                    System.out.print('G');
                } else {
                    System.out.print('T');
                }
                ans += numOfDNA - max;
            }
            System.out.println();
            System.out.println(ans);
        }
    }
}
