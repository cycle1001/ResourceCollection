package com.allen.res.collect.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * Handler软引用的使用
 * @author _Smile
 * Created by _Smile on 2018/1/25 025.
 */
public class HandlerActivity extends AppCompatActivity {

    private MyHandler mMyHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMyHandler = new MyHandler(this);
        start();
    }

    private void start() {
        Message msg = Message.obtain();
        msg.what = 1;
        mMyHandler.sendMessage(msg);
    }

    private static class MyHandler extends Handler {

        private WeakReference<HandlerActivity> activityWeakReference;

        MyHandler(HandlerActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerActivity activity = activityWeakReference.get();
            if (activity != null) {
                if (msg.what == 1) {
                    // 做相应逻辑
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMyHandler.removeCallbacksAndMessages(null);
    }
}
