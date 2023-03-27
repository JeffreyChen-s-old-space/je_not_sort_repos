package com.je.datainnovationrace.layout.map;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.je.datainnovationrace.R;
import com.je.datainnovationrace.utils.map.MapIntent;

public class MapNavigationActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    private final MapIntent mapIntent = new MapIntent();

    private RadioGroup navigationModeRadioGroup;
    private RadioButton navigationModeD, navigationModeB, navigationModeL, navigationModeW;

    private EditText navigationEditText;

    private CheckBox navigationAvoidT, navigationAvoidH, navigationAvoidF;


    private String mode = "";
    private StringBuilder avoid = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_navigation);
        navigationModeRadioGroup = findViewById(R.id.hotelLocationRadioGroup);
        navigationModeRadioGroup.setOnCheckedChangeListener(this);
        navigationModeD = findViewById(R.id.navigationModeD);
        navigationModeB = findViewById(R.id.navigationModeB);
        navigationModeL = findViewById(R.id.navigationModeL);
        navigationModeW = findViewById(R.id.navigationModeW);
        navigationEditText = findViewById(R.id.navigationEditText);
        navigationAvoidT = findViewById(R.id.navigationAvoidT);
        navigationAvoidT.setOnCheckedChangeListener(this);
        navigationAvoidH = findViewById(R.id.navigationAvoidH);
        navigationAvoidH.setOnCheckedChangeListener(this);
        navigationAvoidF = findViewById(R.id.navigationAvoidF);
        navigationAvoidF.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.startNavigation) {
            String getLocationText = navigationEditText.getText().toString();
            if (getLocationText.isEmpty()) {
                navigationEditText.setError(getResources().getString(R.string.map_search_error));
                return;
            }
            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + getLocationText
                    + "&avoid=" + avoid.toString()
                    + "&mode=" + mode);
            Log.d("DEBUG Navigation URL", gmmIntentUri.toString());
            mapIntent.startMapIntent(gmmIntentUri, this);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (checkedId == R.id.navigationModeD)
            mode = "d";
        else if (checkedId == R.id.navigationModeB)
            mode = "b";
        else if (checkedId == R.id.navigationModeL)
            mode = "l";
        else if (checkedId == R.id.navigationModeW)
            mode = "w";
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        int id = compoundButton.getId();
        if (id == R.id.navigationAvoidT) {
            if (navigationAvoidT.isChecked()) {
                avoid.append("t");
            } else {
                avoid.deleteCharAt(avoid.indexOf("t"));
            }
        } else if (id == R.id.navigationAvoidH) {
            if (navigationAvoidH.isChecked()) {
                avoid.append("h");
            } else {
                avoid.deleteCharAt(avoid.indexOf("h"));
            }
        } else if (id == R.id.navigationAvoidF) {
            if (navigationAvoidF.isChecked()) {
                avoid.append("f");
            } else {
                avoid.deleteCharAt(avoid.indexOf("f"));
            }
        }
    }
}