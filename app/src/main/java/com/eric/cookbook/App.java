package com.eric.cookbook;

import android.app.Application;
import android.content.Context;

import cn.sharesdk.framework.ShareSDK;

/**
 * 分享
 */
public class App extends Application {

    private static App INSTANCE;

    public static Context getContext() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        ShareSDK.initSDK(this);
    }

}
