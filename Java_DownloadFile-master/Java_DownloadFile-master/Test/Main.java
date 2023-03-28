package com.je_chen.Test;

import com.je_chen.Module.IODownload;
import com.je_chen.Module.NioDownload;

import java.io.IOException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        /*
        IODownload IODownload = new IODownload();
        try {
            IODownload.DownloadCopy("https://www.youtube.com/watch?v=j9V78UbdzWI&ab_channel=DigiNeko","Coffin_Dance.mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
        NioDownload NioDownload = new NioDownload();
        try {
            NioDownload.Download(new URL("https://www.youtube.com/watch?v=j9V78UbdzWI&ab_channel=DigiNeko"),"Coffin_Dance.mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
