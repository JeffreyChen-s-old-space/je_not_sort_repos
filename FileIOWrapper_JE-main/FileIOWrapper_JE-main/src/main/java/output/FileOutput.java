package output;

import java.io.*;

public class FileOutput {

    public void writeFile(File writeFile, String writeData) throws IOException {
        assert writeFile.exists() || writeFile.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writeFile));
        bufferedWriter.write(writeData);
        bufferedWriter.close();
    }

}
