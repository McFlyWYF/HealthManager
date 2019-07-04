package com.eric.cookbook.utils;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by 16500 on 2019/7/3.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("Herve")
                .methodCount(3)
                .hideThreadInfo();
    }
}
