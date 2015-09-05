package com.example.abhishektiwari.login_signup;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private static final String NEW_USER_REQUEST_URL = "http://www.hostelers.in/php/register.php";
    private static final String LOGIN_URL="http://www.hostelers.in/php/login.php";
    EditText NewUser, NewPassword, ConNewPassword;
    EditText userName, Password;
    public static TextView response;
    Button SignUpButton, LoginButton;



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
                    new CreatingJson().execute(NewUser.getText().toString(),NewPassword.getText().toString(),NEW_USER_REQUEST_URL);
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
                    new CreatingJson().execute(userName.getText().toString(),Password.getText().toString(),LOGIN_URL);
                }
            }
        });
    }

    public void someRandom(String s) {
        TextView response = (TextView) findViewById(R.id.tv_response);
        response.setText(s);
    }
}

