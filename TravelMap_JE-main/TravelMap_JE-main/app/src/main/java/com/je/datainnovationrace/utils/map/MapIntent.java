package com.je.datainnovationrace.utils.map;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class MapIntent {

    public void startMapIntent(Uri mapIntentUri, Context context){
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        try {
            context.startActivity(mapIntent);
        } catch (ActivityNotFoundException activityNotFoundException) {
            Toast.makeText(context, "需要安裝GoogleMap", Toast.LENGTH_LONG).show();
        }
    }

}
