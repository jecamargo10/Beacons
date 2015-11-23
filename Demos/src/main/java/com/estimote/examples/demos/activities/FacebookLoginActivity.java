package com.estimote.examples.demos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estimote.examples.demos.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Shows all available demos.
 *
 * @author wiktor@estimote.com (Wiktor Gworek)
 */
public class FacebookLoginActivity extends Fragment {

  private CallbackManager callbackManager;
  private TextView textDetails;
  private ProfileTracker profileTracker;
  private  AccessTokenTracker tracker;
 private Profile profile;



    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>()
  {
    @Override
    public void onSuccess(LoginResult loginResult) {
        AccessToken accessToken = loginResult.getAccessToken();
       profile = Profile.getCurrentProfile();
      if(profile != null)
      {

        textDetails.setText("Bienvenido: " + profile);
      }

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }
  };


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        setupLoginButton(view);

    }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    callbackManager = CallbackManager.Factory.create();
     tracker = new AccessTokenTracker() {
      @Override
      protected void onCurrentAccessTokenChanged(AccessToken old, AccessToken newToken)
      {

      }
      };
     profileTracker = new ProfileTracker() {
      @Override
      protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile)
        {

      }
    };

    tracker.startTracking();
    profileTracker.startTracking();



  }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.com_facebook_login_fragment, container, false);
    }

    // Other app specific specialization




    private void setupLoginButton(View view) {

        View p = (View) view.getRootView();
        LoginButton mButtonLogin = (LoginButton) p.findViewById(R.id.login_button);


        Log.e("Acceso facebook =", view.toString());


        Log.e("ex =", mButtonLogin.toString());



        mButtonLogin.setFragment(this);
        mButtonLogin.setReadPermissions("user_friends");
        mButtonLogin.registerCallback(callbackManager, callback);

    }



  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    super.onActivityResult(requestCode, resultCode, data);
    callbackManager.onActivityResult(requestCode, resultCode, data);


  }



  @Override
  public void onStop()
  {
    super.onStop();
    tracker.stopTracking();
    profileTracker.stopTracking();

  }

    public void setID()
    {



    }




}
