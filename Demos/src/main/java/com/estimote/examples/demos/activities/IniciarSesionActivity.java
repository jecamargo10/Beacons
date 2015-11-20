package com.estimote.examples.demos.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.estimote.examples.demos.R;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.estimote.sdk.BeaconManager.MonitoringListener;

/**
 * Demo that shows how to use region monitoring. Two important steps are:
 * <ul>
 * <li>start monitoring region, in example in {@link #onResume()}</li>
 * <li>respond to monitoring changes by registering {@link MonitoringListener} in {@link BeaconManager}</li>
 * </ul>
 *
 * @author wiktor@estimote.com (Wiktor Gworek)
 */
public class IniciarSesionActivity extends BaseActivity {

  private static final int NOTIFICATION_ID = 123;

  private BeaconManager beaconManager;
  private NotificationManager notificationManager;
  private Region region;

  private Thread multiple;

  @Override protected int getLayoutResId() {
    return R.layout.reatil_demo;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);





     final Beacon beacon = getIntent().getParcelableExtra(ListBeaconsActivity.EXTRAS_BEACON);
    region = new Region("regionId", beacon.getProximityUUID(), beacon.getMajor(), beacon.getMinor());
    notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    beaconManager = new BeaconManager(this);


    // Default values are 5s of scanning and 25s of waiting time to save CPU cycles.
    // In order for this demo to be more responsive and immediate we lower down those values.
    beaconManager.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(1), 0);


      IniciarSesionActivity jesus = this;

    Log.e("My app", "inicio ciclo");

      beaconManager.setRangingListener(new BeaconManager.RangingListener() {
          @Override
          public void onBeaconsDiscovered(Region region, final List<Beacon> rangedBeacons) {
              // Note that results are not delivered on UI thread.
              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      // Just in case if there are multiple beacons with the same uuid, major, minor.
                      Beacon foundBeacon = null;
                      for (Beacon rangedBeacon : rangedBeacons) {
                          if (rangedBeacon.getMacAddress().equals(beacon.getMacAddress())) {
                              foundBeacon = rangedBeacon;
                          }
                      }
                      if (foundBeacon != null) {
                          final Beacon finalFoundBeacon = foundBeacon;
                          new Thread(new Runnable() {
                              public void run() {


                                  Beacon prubea = finalFoundBeacon;
                                  double x = Utils.computeAccuracy(prubea);
                                  Log.e("My app", String.valueOf(x));
                                  if (x < 1) {
                                      Log.e("My app", "area");


                                      runOnUiThread(new Runnable() {
                                          @Override
                                          public void run() {
                                              ImageView imgView = (ImageView) findViewById(R.id.imageView);
                                              imgView.setVisibility(View.VISIBLE);
                                              postNotification("Retail x te informa de descuentos en el area y");
                                              try {
                                                  this.finalize();
                                              } catch (Throwable throwable) {
                                                  throwable.printStackTrace();
                                              }

                                          }
                                      });



                                  } else {


                                      try {
                                          Log.e("My app", "muero");

                                          this.finalize();
                                      } catch (Throwable throwable) {
                                          throwable.printStackTrace();
                                              }

                                          }






                              }
                          }).start();
                      }
                  }
              });
          }
      });

      beaconManager.startRanging(region);








  }




    @Override
    protected void onStart() {
        super.onStart();

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

  @Override
  protected void onResume() {

    notificationManager.cancel(NOTIFICATION_ID);
      beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
          @Override
          public void onServiceReady() {
              beaconManager.startRanging(region);
          }
      });

      super.onResume();

  }

  @Override
  protected void onDestroy() {
    notificationManager.cancel(NOTIFICATION_ID);
    beaconManager.disconnect();
    super.onDestroy();
  }


    @Override
    protected void onPause() {

        super.onPause();
        Log.e("My app", "Background");


        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(region);
            }
        });
    }










  @Override
  protected void onStop() {

    super.onStop();


    beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
      @Override
      public void onServiceReady() {
        beaconManager.startMonitoring(region);
      }
    });
  }

  private void postNotification(String msg) {




    Intent notifyIntent = new Intent(IniciarSesionActivity.this, IniciarSesionActivity.class);
    notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    PendingIntent pendingIntent = PendingIntent.getActivities(
        IniciarSesionActivity.this,
        0,
        new Intent[]{notifyIntent},
        PendingIntent.FLAG_UPDATE_CURRENT);
    Notification notification = new Notification.Builder(IniciarSesionActivity.this)
        .setSmallIcon(R.drawable.beacon_gray)
        .setContentTitle("Notify Demo")
        .setContentText(msg)
        .setAutoCancel(true)
        .setContentIntent(pendingIntent)
        .build();
    notification.defaults |= Notification.DEFAULT_SOUND;
      notification.defaults |= Notification.DEFAULT_VIBRATE;
      notificationManager.notify(NOTIFICATION_ID, notification);




  }
}
