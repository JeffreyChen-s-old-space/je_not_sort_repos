import java.util.Scanner;

public class A410877027_1_1 {

    public static void main(String[] argv){
        Scanner stringScanner = new Scanner(System.in);
        String needToUpperString = stringScanner.nextLine();
        needToUpperString = needToUpperString.strip();
        StringBuilder upperStringBuilder = new StringBuilder();
        for(int index=0; index<needToUpperString.length(); index++){
            char originChar = needToUpperString.charAt(index);
            if ( 97 <= originChar && originChar <= 127){
                originChar -= 32;
                upperStringBuilder.append(originChar);
            }else{
                upperStringBuilder.append(originChar);
            }
        }
        System.out.println(upperStringBuilder);
    }
}
