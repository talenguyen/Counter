package vn.tale.counter;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by Giang Nguyen at Tiki on 4/29/16.
 */
public class CounterApp extends Application {

  @Override public void onCreate() {
    super.onCreate();
    FacebookSdk.sdkInitialize(this);
    FacebookSdk.setIsDebugEnabled(true);
    FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
    AppEventsLogger.activateApp(this);
  }
}
