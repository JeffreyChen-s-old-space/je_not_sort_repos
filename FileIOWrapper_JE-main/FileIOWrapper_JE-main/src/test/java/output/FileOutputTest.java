package output;


import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileOutputTest {

    @Test
    public void writeFile() {
        FileOutput fileOutput = new FileOutput();
        File file = new File("test.txt");
        try {
            fileOutput.writeFile(file,"Test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}