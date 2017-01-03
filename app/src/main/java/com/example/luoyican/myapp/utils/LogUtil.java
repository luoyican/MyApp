package com.example.luoyican.myapp.utils;

import android.util.Log;

import com.example.luoyican.myapp.model.ModelManager;

/**
 * Created by luoyican on 2016/12/28.
 */
public class LogUtil {
    private static final String TAG = "ToPleaseMyself_LYC";

    public static void tmd(String msg) {
        if (ModelManager.getInstance().isDebug()) {
            Log.d(TAG + "tmp", msg);
        }
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (ModelManager.getInstance().isDebug())
            Log.i(tag, msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (ModelManager.getInstance().isDebug())
            Log.d(tag, msg);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (ModelManager.getInstance().isDebug())
            Log.e(tag, msg);
    }
}
