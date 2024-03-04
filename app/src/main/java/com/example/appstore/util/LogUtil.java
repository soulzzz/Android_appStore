package com.example.appstore.util;

import android.util.Log;

public class LogUtil {
    private static String TAG = "MyAppStore";
    // 开关
    private static boolean debug = true;

    private LogUtil() {
    }

    public static void setTAG(String TAG) {
        LogUtil.TAG = TAG;
    }

    public static void setDebug(boolean debug) {
        LogUtil.debug = debug;
    }

    public static void v(String msg) {
        if (debug)
            Log.v(TAG, msg);
    }

    public static void d(String msg) {
        if (debug)
            Log.d(TAG, msg);
    }

    public static void i(String msg) {
        if (debug)
            Log.i(TAG, msg);
    }

    public static void w(String msg) {
        if (debug)
            Log.w(TAG, msg);
    }

    public static void e(String msg) {
        if (debug)
            Log.e(TAG, msg);
    }
    public static void v(String appendTag,String msg) {
        if (debug)
            Log.v(TAG+"-"+appendTag, msg);
    }

    public static void d(String appendTag,String msg) {
        if (debug)
            Log.d(TAG+"-"+appendTag, msg);
    }

    public static void i(String appendTag,String msg) {
        if (debug)
            Log.i(TAG+"-"+appendTag, msg);
    }

    public static void w(String appendTag,String msg) {
        if (debug)
            Log.w(TAG+"-"+appendTag, msg);
    }

    public static void e(String appendTag,String msg) {
        if (debug)
            Log.e(TAG+"-"+appendTag, msg);
    }
}
