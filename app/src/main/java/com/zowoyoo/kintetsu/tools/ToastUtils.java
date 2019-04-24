package com.zowoyoo.kintetsu.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.zowoyoo.kintetsu.application.KintetsuApplication;

public class ToastUtils {
    private static Toast mToast;

    @SuppressLint("ShowToast")
    public static void showShortToast(String str) {
        if (mToast == null) {
            mToast = Toast.makeText(KintetsuApplication.getContext(), str, Toast.LENGTH_SHORT);
        }
        mToast.setText(str);
        mToast.show();
    }

    public static void showShortToast(Activity activity, final String str) {
        if (activity == null || TextUtils.isEmpty(str)) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showShortToast(str);
            }
        });
    }
}
