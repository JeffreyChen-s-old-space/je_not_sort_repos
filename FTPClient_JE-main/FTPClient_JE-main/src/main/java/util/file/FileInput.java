package util.file;

import java.io.*;

public class FileInput {

    public String readFileBufferReader(File readFile) throws IOException {
        StringBuilder readDataStringBuilder = new StringBuilder();
        String temp;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(readFile));
        while ((temp = bufferedReader.readLine()) != null) {
            readDataStringBuilder.append(temp).append("\n\r");
        }
        bufferedReader.close();
        return readDataStringBuilder.toString();
    }

    public FileInputStream inputFileStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    public FileInputStream inputFileStream(String fileName) throws FileNotFoundException {
        return new FileInputStream(fileName);
    }

}
