package com.eric.cookbook;

import android.app.Application;
import android.content.Context;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2017/3/26.
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
