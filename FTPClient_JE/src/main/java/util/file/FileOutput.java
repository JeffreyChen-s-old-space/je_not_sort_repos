package util.file;

import java.io.*;

public class FileOutput {

    public void writeFile(File writeFile, String writeData, boolean mode) throws IOException {
        fileWriter(writeFile, writeData, false);
    }

    public void appendFile(File writeFile, String writeData, boolean mode) throws IOException {
        fileWriter(writeFile, writeData, true);
    }

    public FileOutputStream outputFileStream(File file) throws FileNotFoundException {
        return new FileOutputStream(file);
    }

    private void fileWriter(File writeFile, String writeData, boolean mode) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writeFile, mode));
        bufferedWriter.write(writeData);
        bufferedWriter.close();
    }


}
