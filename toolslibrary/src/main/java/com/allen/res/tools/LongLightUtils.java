package com.allen.res.tools;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * 屏幕常亮
 */
public class LongLightUtils {

    /**
     * 是否使屏幕常亮
     *
     * @param activity
     */
    public static void keepScreenLongLight(Activity activity, boolean isOpenLight) {
        Window window = activity.getWindow();
        if (isOpenLight) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

    }
}
