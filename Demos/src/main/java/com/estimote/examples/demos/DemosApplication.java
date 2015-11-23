package com.estimote.examples.demos;

import android.app.Application;
import android.app.PendingIntent;

import com.estimote.sdk.EstimoteSDK;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;

/**
 * Main {@link Application} object for Demos. It configures EstimoteSDK.
 *
 * @author wiktor@estimote.com (Wiktor Gworek)
 */
public class DemosApplication extends Application {



  @Override
  public void onCreate() {
    super.onCreate();

    // Initializes Estimote SDK with your App ID and App Token from Estimote Cloud.
    // You can find your App ID and App Token in the
    // Apps section of the Estimote Cloud (http://cloud.estimote.com).
    EstimoteSDK.initialize(this, "javierecam22-gmail-com-s-y-lcn", "6cd68579c278d0c8e7d3d76086644cb9");
    FacebookSdk.sdkInitialize(getApplicationContext());








    // Configure verbose debug logging.
    EstimoteSDK.enableDebugLogging(true);
  }
}
