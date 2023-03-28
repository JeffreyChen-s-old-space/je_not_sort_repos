package input;

import org.junit.BeforeClass;
import org.junit.Test;
import output.FileOutput;

import java.io.File;
import java.io.IOException;

public class FileInputTest {

    static File file;

    @BeforeClass
    public static void setUp() {
        FileOutput fileOutput = new FileOutput();
        file = new File("test.txt");
        try {
            fileOutput.writeFile(file,"test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readFile(){
        FileInput fileInput = new FileInput();
        try {
            System.out.println(fileInput.readFile(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}