package com.je_chen.Module;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class IODownload {

    public void Download(String fileURL, String fileName ) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(fileURL).openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        byte dataBuffer[] = new byte[1024];
        int length;
        while ((length = bufferedInputStream.read(dataBuffer,0,1024)) != -1){
            fileOutputStream.write(dataBuffer,0,length);
        }
        bufferedInputStream.close();
        fileOutputStream.close();
    }

    public void DownloadCopy(String fileURL,String fileName) throws  IOException{
        InputStream inputStream = new URL(fileURL).openStream();
        Files.copy(inputStream, Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
        inputStream.close();
    }
}
