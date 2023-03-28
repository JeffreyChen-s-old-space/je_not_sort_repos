package com.je_chen.Test;

import com.je_chen.Module.UnZip;
import com.je_chen.Module.Zip;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String cwdPath = new File("").getAbsolutePath();
        String filePath = new File("").getAbsolutePath() + "/src/resource/test.txt";
        String DirectoryPath = new File("").getAbsolutePath() + "/src/resource";

        System.out.println(new File(filePath).exists());
        System.out.println(new File(DirectoryPath).isDirectory());

        Zip zip = new Zip();
        UnZip unZip = new UnZip();

        try {
            zip.zipFile(filePath,"test.zip");
            unZip.unZipFile(new File(cwdPath+"/test.zip"),cwdPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
