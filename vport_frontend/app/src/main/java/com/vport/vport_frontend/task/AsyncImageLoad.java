package com.vport.vport_frontend.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncImageLoad extends AsyncTask<String, String, Bitmap> {

    public AsyncResponse delegate = null;

    @Override
    protected Bitmap doInBackground(String... strings) {

        Bitmap bmImg = null;
        try {
            URL ImageUrl = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) ImageUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            bmImg = BitmapFactory.decodeStream(is, null, options);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmImg;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        delegate.processFinish(result);
    }

}
