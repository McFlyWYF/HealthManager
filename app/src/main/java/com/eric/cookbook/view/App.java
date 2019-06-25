package com.eric.cookbook.view;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;


/**
 * Created by 16500 on 2019/6/16.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
