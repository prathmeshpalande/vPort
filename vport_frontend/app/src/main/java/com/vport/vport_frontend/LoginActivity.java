package com.vport.vport_frontend;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

//    @BindView(R.id.input_email)
      EditText _emailText;
//    @BindView(R.id.input_password)
      EditText _passwordText;
//    @BindView(R.id.btn_login)
      Button _loginButton;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        ButterKnife.bind(this);

        _emailText=(EditText) findViewById(R.id.input_email);
        _passwordText=(EditText) findViewById(R.id.input_password);
        _loginButton=(Button) findViewById(R.id.btn_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    login();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void login() throws ExecutionException, InterruptedException {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

//        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Authenticating...");
//        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.




//        final String POST_PARAMS = "{\n" + "\"userName\":"+ email+"," +
//                "    \"password\":"+password+"}";
//
//
//
//        try {
////           URL obj = new URL("http://192.168.43.249:8080/login");
//            URL obj = new URL("http://localhost:8080/login");
//            HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
//            postConnection.setRequestMethod("POST");
//            postConnection.setRequestProperty("Content-Type", "application/json");
//            postConnection.setDoOutput(true);
////        postConnection.setRequestProperty("userName", email);
////        postConnection.setRequestProperty("password", password);
//            OutputStream os = postConnection.getOutputStream();
//            os.write(POST_PARAMS.getBytes());
//            os.flush();
//            os.close();
//            int responseCode = postConnection.getResponseCode();
//            System.out.println("POST Response Code :  " + responseCode);
//            System.out.println("POST Response Message : " + postConnection.getResponseMessage());
//
//            if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
//                BufferedReader in = null;
//                in = new BufferedReader(new InputStreamReader(
//                        postConnection.getInputStream()));
//                String inputLine = null;
//                StringBuffer response = new StringBuffer();
//                while (true) {
//                    try {
//                        if (!((inputLine = in.readLine()) != null)) break;
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    response.append(inputLine);
//                }
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                // print result
//                System.out.println(response.toString());
//            } else {
//                System.out.println("POST NOT WORKED");
//            }
//
////            progressDialog.dismiss();
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }


//        final AsyncTask<String, String, String> response =  new LoginPoster().execute("http://192.168.43.249:8080/login", email, password);
//        final AsyncTask<String, String, String> response =  new LoginPoster().execute("http://localhost:8080/login", email, password);
        final AsyncTask<String, String, String> response =  new LoginPoster().execute("http://192.168.43.73:8080/login", email, password);



        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
//                        progressDialog.dismiss();
                        try {
                            Log.i("response_log", response.get());
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            if(response.get().equals("success")) {
                                onLoginSuccess();
    //                            final ProgressDialog dialog = new ProgressDialog(LoginActivity.this,
    //                                    R.style.AppTheme_Dark_Dialog);
    //                            dialog.setIndeterminate(true);
    //                            dialog.setMessage("Authenticated");
    //                            dialog.show();
    //                            dialog.dismiss();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                onLoginFailed();
                                Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                            }
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }, 3000);
    }


    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
