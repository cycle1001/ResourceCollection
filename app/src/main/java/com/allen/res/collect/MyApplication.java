package com.allen.res.collect;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.allen.res.tools.LongLightUtils;

/**
 * .
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 屏幕常亮
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                LongLightUtils.keepScreenLongLight(activity, true);
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
