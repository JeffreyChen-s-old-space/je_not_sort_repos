package algorithm.util.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutput {

    public void writeFile(File writeFile, String writeData, boolean mode) throws IOException {
        fileWriter(writeFile, writeData,false);
    }

    public void appendFile(File writeFile, String writeData, boolean mode) throws IOException {
        fileWriter(writeFile, writeData,true);
    }

    private void fileWriter(File writeFile, String writeData, boolean mode) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writeFile, mode));
        bufferedWriter.write(writeData);
        bufferedWriter.close();
    }

}
