package com.eric.cookbook.view;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by 16500 on 2019/6/15.
 */

public class DisplayUtil {
    public static final String TAG = "DisplayUtil";

    /**
     * get screen height of this cellphone
     *
     * @param context
     * @return
     */
    public static int getMobileHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels; // 得到高度
        return height;
    }

    /**
     * get screen width of this cellphone
     *
     * @param context
     * @return
     */
    public static int getMobileWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels; // 得到宽度
        return width;

    }

    /**
     * 根据手机的分辨率dp 转成px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率px(像素) 转成dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static float sp2px(Context context, float sp){
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

}
