package com.je_chen.Module;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnZip {

    /***
     * @param zipFile
     * @param folderPath
     * @throws IOException
     *  壓縮一檔案
     */
    public void unZipFile(File zipFile, String folderPath)throws IOException{
        File desDir = new File(folderPath);
        if (!desDir.exists()) {
            desDir.mkdirs();
        }
        ZipFile zf = new ZipFile(zipFile);
        for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) {
            ZipEntry entry = ((ZipEntry) entries.nextElement());
            InputStream inputStream = zf.getInputStream(entry);
            String str = folderPath;
            File desFile = new File(str, java.net.URLEncoder.encode(entry.getName(), StandardCharsets.UTF_8));
            if (!desFile.exists()) {
                File fileParentDir = desFile.getParentFile();
                if (!fileParentDir.exists()) {
                    fileParentDir.mkdirs();
                }
            }
            OutputStream outputStream = new FileOutputStream(desFile);
            byte[] buffer = new byte[1024 * 1024];
            int length = inputStream.read(buffer);
            while (length != -1) {
                outputStream.write(buffer, 0, length);
                length = inputStream.read(buffer);
            }
            outputStream.close();
            inputStream.close();
        }
    }
}
