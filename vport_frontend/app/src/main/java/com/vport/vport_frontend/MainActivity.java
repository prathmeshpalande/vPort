package com.vport.vport_frontend;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.vport.vport_frontend.task.AsyncImageLoad;
import com.vport.vport_frontend.task.AsyncResponse;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    private ImageView imageView;

    AsyncImageLoad asyncImageLoad = new AsyncImageLoad();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        //End of Full Screen Code

        imageView = findViewById(R.id.imageView);
        Log.d("Thread", Thread.currentThread().toString());
        asyncImageLoad.delegate = this;
//        asyncImageLoad.execute("http://192.168.43.73:8080/grab_screen");
        asyncImageLoad.execute("https://usatmmajunkie.files.wordpress.com/2017/09/benson-henderson-bellator-183-portrait.jpg");
    }

    //this override the implemented method from asyncTask
    @Override
    public void processFinish(Bitmap bitmap){
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
        Log.d("Thread", Thread.currentThread().toString());
        imageView.setImageBitmap(bitmap);
    }
}
