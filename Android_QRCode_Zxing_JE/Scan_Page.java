package com.je_chen.qrcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.je_chen.qrcode.R;

import java.io.IOException;

public class Scan_Page extends AppCompatActivity implements View.OnClickListener {
    SurfaceView surfaceView;
    TextView Scan_Result;
    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;
    Button back_to_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        surfaceView=findViewById(R.id.surfaceView);
        Scan_Result=findViewById(R.id.Scan_Result);
        back_to_main=findViewById(R.id.back_to_main);
        back_to_main.setOnClickListener(this);

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource=new CameraSource.Builder(this,barcodeDetector)
                .setRequestedPreviewSize(500,500).build();
        cameraSource = new CameraSource.Builder(this,barcodeDetector).setAutoFocusEnabled(true).build();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(Scan_Page.this,new String[]{Manifest.permission.CAMERA},1);
                try{
                    cameraSource.start(holder);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }

        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>(){

            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes=detections.getDetectedItems();
                if(qrCodes.size()!=0){
                    Scan_Result.post(new Runnable() {
                        @Override
                        public void run() {
                            Scan_Result.setText(qrCodes.valueAt(0).displayValue);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.back_to_main:
                Intent change_activity =new Intent(Scan_Page.this, SpanAndMain_Page.class);
                change_activity.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(change_activity);
                Scan_Page.this.finish();
                break;
        }
    }
}
