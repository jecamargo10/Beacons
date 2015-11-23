package com.estimote.examples.demos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.estimote.examples.demos.R;

/**
 * Shows all available demos.
 *
 * @author wiktor@estimote.com (Wiktor Gworek)
 */
public class LoginActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login);




    findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startListBeaconsActivity(UpdateDemoActivity.class.getName());
        Intent intent = new Intent(v.getContext(), AllDemosActivity.class);
        startActivity(intent);

      }
    });



    final FacebookLoginActivity asd = new FacebookLoginActivity();
    findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Log.e("Acceso facebook =", "Leego");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft.replace(R.id.face_layout, asd);
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();





        startListBeaconsActivity(UpdateDemoActivity.class.getName());
        Intent intent = new Intent(v.getContext(), AllDemosActivity.class);
        startActivity(intent);






      }
    });





  }



  private void startListBeaconsActivity(String extra) {
    Intent intent = new Intent(LoginActivity.this, ListBeaconsActivity.class);
    intent.putExtra(ListBeaconsActivity.EXTRAS_TARGET_ACTIVITY, extra);
    startActivity(intent);
  }
}
