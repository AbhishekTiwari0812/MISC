package com.example.abhishektiwari.login_signup;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class PostRecieveJson extends ActionBarActivity {
    private static final String MY_NEW_USER_URL = "http://www.hostelers.in/php/register.php";
    EditText NewUser, NewPassword, ConNewPassword;
    EditText userName, Password;
    TextView response;
    Button SignUpButton, LoginButton;
    URLConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewUser = (EditText) findViewById(R.id.new_user);
        NewPassword = (EditText) findViewById(R.id.new_password);
        ConNewPassword = (EditText) findViewById(R.id.confirmed_password);
        userName = (EditText) findViewById(R.id.user_name);
        Password = (EditText) findViewById(R.id.login_password);
        SignUpButton = (Button) findViewById(R.id.sign_up_button);
        TextView response = (TextView) findViewById(R.id.tv_response);

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConNewPassword.getText().toString().equals(NewPassword.getText().toString())) {
                    postNewUserInfo();
                    try {
                        postNewUserInfo();
                        seeNewUserSignUpResponse();
                    } catch (IOException e) {
                        PrintToDebugger("IO ERROR in seeing the response of new user");
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Passwords don't match!\nTry again.\nAnd type carefully you dumb fuck.(nothing personal)", Toast.LENGTH_LONG).show();

                }
            }
        });

        LoginButton = (Button) findViewById(R.id.login_button);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().length() == 0 || Password.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(),
                            "One or more required fields are left empty.\nFill those boxes,you ignorant bastard.(nothing personal)", Toast.LENGTH_LONG).show();
                } else {
                    postUserLoginInfo();
                    SeeUserLoginResponse();
                }
            }
        });
    }

    private void postUserLoginInfo() {

        String data = null;
        try {
            data = URLEncoder.encode("email", "UTF-8")
                    + "=" + URLEncoder.encode(userName.getText().toString(), "UTF-8");

            data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                    + URLEncoder.encode(Password.getText().toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            PrintToDebugger("Encoding error happened");
        }


        BufferedReader reader = null;

        // Defined URL  where to send data
        URL url = null;
        try {
            url = new URL(MY_NEW_USER_URL);

            // Send POST data request

            conn = url.openConnection();
            conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
        } catch (MalformedURLException e) {
            PrintToDebugger("wrong url problemo!");
        } catch (IOException e) {
            PrintToDebugger("IO Exceptions :P Say wut");
        }


    }

    private void postNewUserInfo() {

        // Create data variable for sent values to server

        String data = null;
        try {
            data = URLEncoder.encode("name", "UTF-8")
                    + "=" + URLEncoder.encode(NewUser.getText().toString(), "UTF-8");

            data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                    + URLEncoder.encode(NewPassword.getText().toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            PrintToDebugger("Encoding error happened");
        }


        BufferedReader reader = null;

        // Defined URL  where to send data
        URL url = null;
        try {
            url = new URL(MY_NEW_USER_URL);

            // Send POST data request

            conn = url.openConnection();
            conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
        } catch (MalformedURLException e) {
            PrintToDebugger("wrong url problemo!");
        } catch (IOException e) {
            PrintToDebugger("IO Exceptions :P Say wut");
        }

    }


    private void seeNewUserSignUpResponse() throws IOException {

        // Get the server response

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            // Append server response in string
            sb.append(line + "\n");
        }


        String text = sb.toString();        //this is response
        response.setText(text);

    }

    private void SeeUserLoginResponse() {
        // Get the server response

        BufferedReader reader = null;
        StringBuilder sb=null;
        try {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = sb.toString();        //this is response
        response.setText(text);


    }


    private void PrintToDebugger(String error) {
        Log.d("Printing error........", error);
    }
}
