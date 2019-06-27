package com.vport.vport_frontend.task;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class CoordinatePoster extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... params) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("x", Float.parseFloat(params[1]));
            jsonObject.put("y", Float.parseFloat(params[2]));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String data = jsonObject.toString();

        Log.d("Var Values", "URL: " + params[0] + ", Data: " + data);

        // HttpClient
        HttpClient httpClient = new DefaultHttpClient();

        // post header
        HttpPost httpPost = new HttpPost(params[0]);
        httpPost.setHeader("Content-type", "application/json");
//        try {
//            httpPost.setEntity(new StringEntity(data, Charset.forName("UTF-8")));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        // execute HTTP post request
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity resEntity = response.getEntity();

        if (resEntity != null) {

            String responseStr = null;
            try {
                responseStr = EntityUtils.toString(resEntity).trim();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("POSTCoorResponse", "Response: " +  responseStr);

            // you can add an if statement here and do other actions based on the response
        }

        Log.d("Success", "Coordinates Posted Successfully!");
        return "Success";
    }
}
