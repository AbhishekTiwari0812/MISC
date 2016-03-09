package com.example.abhishek.facebookloginsignup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {
    //TODO: GET THE HASH KEY!!!!
    // Hash key is : **************************
    AccessToken accessToken;
    Profile profile;
    private CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_main);
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        //TODO: change these permissions
        loginButton.setReadPermissions("user_friends");
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                _("Logged in successfully");
                accessToken = loginResult.getAccessToken();
                profile = Profile.getCurrentProfile();
                if (profile != null) {
                    Log.d("Response", "Woohhooo...!" + profile.getFirstName());
                }
            }

            @Override
            public void onCancel() {
                _("Logging cancelled");
            }

            @Override
            public void onError(FacebookException error) {
                _("some error occured");
                _("Error is" + error.toString());
            }
        });
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

            }
        };
        //TODO: need to be stopped in the onStop method
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (accessTokenTracker != null)
            accessTokenTracker.stopTracking();
        if (profileTracker != null)
            profileTracker.stopTracking();
    }

    //for debugging purpose only..
    void _(Object obj) {
        Log.d("Status:", "" + obj);
    }
}
