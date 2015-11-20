package com.estimote.examples.demos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(getTitle());

    findViewById(R.id.login).setOnClickListener(new View.OnClickListener()
    {
      @Override public void onClick(View v)
      {
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
