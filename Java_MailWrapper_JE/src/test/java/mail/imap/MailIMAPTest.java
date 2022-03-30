package mail.imap;

import mail.service.CheckEmail;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MailIMAPTest {

    private static String email, password,fileName;

    @BeforeClass
    public static void init(){
        fileName = new File("").getAbsolutePath() + "/testData.json";
        StringBuilder data = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                data.append(bufferedReader.readLine());
            }
            String tmp = data.toString();
            System.out.println(tmp);
            JSONObject jsonObject = new JSONObject(tmp);
            email = (String) jsonObject.get("email");
            password = (String) jsonObject.get("password");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Test
    public void testIMAP(){
        String host = "imap.gmail.com:993";
        CheckEmail checkEmail = new CheckEmail(email,password);

    }

}
