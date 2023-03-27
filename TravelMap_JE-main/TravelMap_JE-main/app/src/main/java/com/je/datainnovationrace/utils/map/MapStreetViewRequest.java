package com.je.datainnovationrace.utils.map;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MapStreetViewRequest {

    private final Context context;

    public MapStreetViewRequest(Context context) {
        this.context = context;
    }

    public void getPanoIDJson(String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("---getPanoIDJson---", response);
                        Log.e("PANOID", parseString(response));
                        Uri gmmIntentUri = Uri.parse("google.streetview:panoid=" + parseString(response));
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        try {
                            context.startActivity(mapIntent);
                        } catch (ActivityNotFoundException activityNotFoundException) {
                            Toast.makeText(context, "需要安裝GoogleMap", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }

    private String parseString(String jsonString) {
        String return_pano_id_String = "";
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return_pano_id_String = jsonObject.getString("pano_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return return_pano_id_String;
    }
}
