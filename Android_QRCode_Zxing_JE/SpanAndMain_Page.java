package com.je_chen.qrcode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.je_chen.qrcode.R;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class SpanAndMain_Page extends AppCompatActivity implements View.OnClickListener {

    final static int RequestCamera =1;


    public void getCode(){
        ImageView ivCode=findViewById(R.id.imageView);
        EditText etContent=findViewById(R.id.editText);
        BarcodeEncoder encoder = new BarcodeEncoder();
        try{
            Bitmap bit = encoder.encodeBitmap(etContent.getText()
                    .toString(), BarcodeFormat.QR_CODE,500,500);
            ivCode.setImageBitmap(bit);
        }catch (WriterException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button  Produce = findViewById(R.id.Produce);
        Button  Scan = findViewById(R.id.Scan);
        Button Get_Camera_Premission = findViewById(R.id.Get_Camera_Premission);
        Produce.setOnClickListener(this);
        Scan.setOnClickListener(this);
        Get_Camera_Premission.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, RequestCamera);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.Produce:
                getCode();
            break;

            case R.id.Scan:
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent change_activity = new Intent(SpanAndMain_Page.this, Scan_Page.class);
                    change_activity.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(change_activity);
                    SpanAndMain_Page.this.finish();
                }else{
                    new AlertDialog.Builder(this)
                            .setCancelable(false)
                            .setTitle(getString(R.string.Get_Camera_Permission_Title))
                            .setMessage(getString(R.string.Get_Camera_Permission_Negative_Message))
                            .setPositiveButton(getString(R.string.Get_Camera_Permission_Yes),null)
                            .show();
                }
                break;

            case R.id.Get_Camera_Premission:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, RequestCamera);
                }else if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    new AlertDialog.Builder(this)
                            .setCancelable(false)
                            .setTitle(getString(R.string.Get_Camera_Permission_Title))
                            .setMessage(getString(R.string.Get_Camera_Permission_Positive_Message))
                            .setPositiveButton(getString(R.string.Get_Camera_Permission_Yes),null)
                            .show();
                }
                break;
        }
    }
}