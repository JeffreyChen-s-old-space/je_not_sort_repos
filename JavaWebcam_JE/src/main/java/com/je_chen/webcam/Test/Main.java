package com.je_chen.webcam.Test;

import com.je_chen.webcam.capture.Capture;

public class Main {

    public static void main(String[] args) {
        Capture capture = new Capture("Test");
        new Thread(capture).start();
    }
}
