package com.eric.cookbook.utils;

import android.content.Context;
import android.widget.Toast;


public class ToastUtil {
    private static Toast mToast;

    /**
     * 显示对话框
     *
     * @param content 要显示的内容
     */
    public static void showToast(Context context, String content) {
        if (mToast == null) {
            mToast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
        }
        mToast.show();
    }
}
