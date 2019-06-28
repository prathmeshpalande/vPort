package com.vport.vport_frontend;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.vport.vport_frontend.model.Coordinate;
import com.vport.vport_frontend.task.AsyncImageLoad;
import com.vport.vport_frontend.task.AsyncResponse;
import com.vport.vport_frontend.task.CoordinatePoster;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    private Coordinate deviceResolution;
    private ImageView imageView;

    private float x1, x2;
    final int MIN_DISTANCE = 150;

//    AsyncImageLoad asyncImageLoad = new AsyncImageLoad();

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
        AsyncImageLoad asyncImageLoad = new AsyncImageLoad();
        asyncImageLoad.delegate = this;
        asyncImageLoad.execute("http://192.168.43.73:8080/grab_screen");
    }

    //this override the implemented method from asyncTask
    @Override
    public void processFinish(Bitmap bitmap){
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
        Log.d("Thread", Thread.currentThread().toString());

        setDeviceResolution();
//        imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, deviceResolution.getWidth(), deviceResolution.getHeight(), false));
        imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 1440, 3120, false));

    }

    public void setDeviceResolution() {

        Integer measuredWidth = 0;
        Integer measuredHeight = 0;
        Point size = new Point();
        WindowManager w = getWindowManager();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)    {
            w.getDefaultDisplay().getSize(size);
            measuredWidth = size.x;
            measuredHeight = size.y;
        }else{
            Display d = w.getDefaultDisplay();
            measuredWidth = d.getWidth();
            measuredHeight = d.getHeight();
        }

        deviceResolution = new Coordinate(measuredWidth, measuredHeight);
//        return new Coordinate(measuredWidth, measuredHeight);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    Toast.makeText(this, "left2right swipe", Toast.LENGTH_SHORT).show ();
                }
                else
                {
                    // consider as something else - a screen tap for example
                    Toast.makeText(this, "Touched", Toast.LENGTH_SHORT).show ();
//                    new CoordinatePoster().execute("http://192.168.43.73:8080/touch", "" + (event.getX() / deviceResolution.getWidth()), "" + (event.getY() / deviceResolution.getHeight()));
//                    try {
                    try {
                        new CoordinatePoster().execute("http://192.168.43.73:8080/touch", "" + (event.getX() / 1440), "" + (event.getY() / 3120)).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
//                new AsyncImageLoad().execute("http://192.168.43.73:8080/grab_screen");
                Log.d("Thread", "Executing grab Image Again");
                AsyncImageLoad asyncImageLoad = new AsyncImageLoad();
                asyncImageLoad.delegate = this;
                asyncImageLoad.execute("http://192.168.43.73:8080/grab_screen");
                break;
        }
        return super.onTouchEvent(event);
    }
}
