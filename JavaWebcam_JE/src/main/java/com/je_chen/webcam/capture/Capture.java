package com.je_chen.webcam.capture;

import org.bytedeco.javacv.*;
import org.bytedeco.opencv.opencv_core.IplImage;

import javax.swing.*;
import java.io.File;

import static org.bytedeco.opencv.global.opencv_core.cvFlip;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvSaveImage;

public class Capture implements Runnable{

    private CanvasFrame canvasFrame;
    private final int interval = 100;
    private boolean running=true;

    public Capture(String captureTitle){
        canvasFrame = new CanvasFrame(captureTitle);
        canvasFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        new File("images").mkdir();
        FrameGrabber frameGrabber = new OpenCVFrameGrabber(0);
        OpenCVFrameConverter.ToIplImage cvFrameConverter = new OpenCVFrameConverter.ToIplImage();
        try {
            frameGrabber.start();
            IplImage iplImage;
            int count=0;
            while (running){
                Frame frame = frameGrabber.grab();
                iplImage = cvFrameConverter.convertToIplImage(frame);
                cvFlip(iplImage,iplImage,1);
                cvSaveImage("images"+File.separator+(count++)+".jpg",iplImage);
                canvasFrame.showImage(cvFrameConverter.convert(iplImage));
                Thread.sleep(interval);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
