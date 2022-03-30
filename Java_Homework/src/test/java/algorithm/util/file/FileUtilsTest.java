package algorithm.util.file;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileUtilsTest {

    static FileUtils fileUtils;
    static FileOutput fileOutput;
    private static File tempFile;

    @BeforeClass
    public static void setUP() {
        fileUtils = new FileUtils();
        fileOutput = new FileOutput();
        tempFile = new File("test.txt");
        try {
            fileOutput.writeFile(tempFile, "123 111\n" +
                    "456 222\n" +
                    "789 333", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fileUtilsTest() {
        FileUtils fileUtils = new FileUtils();
        try {
            fileUtils.removeLine(tempFile, "123");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}