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
public class AllDemosActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.all_demos);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(getTitle());

    findViewById(R.id.distance_demo_button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startListBeaconsActivity(DistanceBeaconActivity.class.getName());
      }
    });

    findViewById(R.id.retail_button).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startListBeaconsActivity(RetailDemo.class.getName());
      }
    });


    findViewById(R.id.characteristics_demo_button).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startListBeaconsActivity(CharacteristicsDemoActivity.class.getName());
      }
    });

    findViewById(R.id.update_demo_button).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startListBeaconsActivity(UpdateDemoActivity.class.getName());
      }
    });







  }

  private void startListBeaconsActivity(String extra) {
    Intent intent = new Intent(AllDemosActivity.this, ListBeaconsActivity.class);
    intent.putExtra(ListBeaconsActivity.EXTRAS_TARGET_ACTIVITY, extra);
    startActivity(intent);
  }
}
