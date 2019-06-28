package com.vport.vport_frontend;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class LoginPoster extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("Threaded", "Inside Login Thread");
        JSONObject jsonObject = new JSONObject();

        URL url = null;
        try {
            url = new URL(params[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection con = null;
        try {
            con = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpURLConnection http = (HttpURLConnection) con;
        try {
            http.setRequestMethod("POST");
            http.setRequestProperty("userName", (params[1]));
            http.setRequestProperty("password", params[2]);
//            http.// PUT is another valid option
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        http.setDoOutput(true);

        try {
            jsonObject.put("userName", (params[1]));
            jsonObject.put("password", (params[2]));

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
        try {
            httpPost.setEntity(new StringEntity(data, Charset.forName("UTF-8").toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            HttpResponse response = httpClient.execute(httpPost);
            Log.i("Response captured: ", response.toString());
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {

                String jsonString = null;
                try {
                    jsonString = EntityUtils.toString(resEntity);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("Success", " Login Successfull!");
                Log.i("JSON RESPONSE", jsonString);
                return jsonString;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("Failed", "User name or Password is incorrect");
        return null;
    }
}
