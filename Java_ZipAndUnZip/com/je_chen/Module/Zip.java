package com.je_chen.Module;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    /***
     * @param zipFilePath
     * @param zipFileName
     * @throws IOException
     *
     */
    public void zipFile(String zipFilePath,String zipFileName)throws IOException {
        FileOutputStream fos = new FileOutputStream(zipFileName);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(zipFilePath);
        FileInputStream fileInputStream = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024 * 1024];
        int length;
        while((length = fileInputStream.read(bytes)) != -1) {
            zipOut.write(bytes, 0, length);
        }
        zipOut.close();
        fileInputStream.close();
        fos.close();
    }

    /***
     * @param zipFiles
     * @param zipFileName
     * @throws IOException
     *
     */
    public void zipFile(List<String> zipFiles, String zipFileName)throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(zipFileName);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        for (String srcFile : zipFiles) {
            File fileToZip = new File(srcFile);
            FileInputStream fileInputStream = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOutputStream.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024 * 1024];
            int length;
            while((length = fileInputStream.read(bytes)) != -1) {
                zipOutputStream.write(bytes, 0, length);
            }
            fileInputStream.close();
        }
        zipOutputStream.close();
        fileOutputStream.close();
    }

    /***
     * @param file
     * @param fileName
     * @throws IOException
     *
     */
    private void zipDirectory(File file,String fileName,ZipOutputStream zipOutputStream)throws IOException{
        if(file.isHidden()){
            return;
        }
        if(file.isDirectory()){
            if(fileName.endsWith("/")){
                zipOutputStream.putNextEntry(new ZipEntry(fileName));
                zipOutputStream.closeEntry();
            }else {
                zipOutputStream.putNextEntry(new ZipEntry(fileName+"/"));
                zipOutputStream.closeEntry();
            }
            File[] childFiles = file.listFiles();
            for(File files : childFiles){
                zipDirectory(files,fileName + "/" + files.getName(),zipOutputStream);
            }
            return;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOutputStream.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fileInputStream.read(bytes)) != -1 ){
            zipOutputStream.write(bytes,0,length);
        }
        fileInputStream.close();
    }

    /***
     * @param fileName
     * @throws IOException
     */
    public void ZipDirectory(String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        File file = new File(fileName);
        zipDirectory(file,fileName,zipOutputStream);
    }

}
