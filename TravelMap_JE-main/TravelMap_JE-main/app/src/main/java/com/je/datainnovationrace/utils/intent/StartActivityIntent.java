package com.je.datainnovationrace.utils.intent;

import android.content.Context;
import android.content.Intent;

public class StartActivityIntent {

    public void startActivityIntent(Intent intent, Context context){
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

}
