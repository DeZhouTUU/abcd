package com.dezhoutuu.mylibrary.FloatBall;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.WindowManager;


public class FloatBallUtil {
    public static boolean inSingleActivity;

    public static WindowManager.LayoutParams getLayoutParams(Context context) {
        return getLayoutParams(context, false);
    }

    public static WindowManager.LayoutParams getLayoutParams(Context context, boolean listenBackEvent) {
//        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//
//        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
//                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
//        if (listenBackEvent) {
//            layoutParams.flags = layoutParams.flags & ~WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        }
//        if (context == null || !(context instanceof Activity)) {
//            final int sdkInt = Build.VERSION.SDK_INT;
//            if (sdkInt < Build.VERSION_CODES.KITKAT) {
//                layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
//            } else if (sdkInt < Build.VERSION_CODES.N_MR1) {
//                layoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
//            } else if (sdkInt < Build.VERSION_CODES.O) {
//                layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
//            } else {//8.0以后
//                layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
//            }
//        } else {
//            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;
//        }
//        layoutParams.format = PixelFormat.RGBA_8888;
//        layoutParams.gravity = Gravity.TOP | Gravity.LEFT;
//        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        //新
        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
        //设置type.系统提示型窗口，一般都在应用程序窗口之上.
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        //设置效果为背景透明.
        mLayoutParams.format = PixelFormat.RGBA_8888;
        //设置flags.不可聚焦及不可使用按钮对悬浮窗进行操控.
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        //设置窗口初始停靠位置.
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;

        //设置悬浮窗口长宽数据.
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        return mLayoutParams;
    }

    public static WindowManager.LayoutParams getStatusBarLayoutParams(Context context) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = 0;
        layoutParams.height = 0;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        if (context == null || !(context instanceof Activity)) {
            final int sdkInt = Build.VERSION.SDK_INT;
            if (sdkInt < Build.VERSION_CODES.KITKAT) {
                layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            } else if (sdkInt < Build.VERSION_CODES.N_MR1) {
                layoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            } else if (sdkInt < Build.VERSION_CODES.O) {
                layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            } else {//8.0以后
                layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            }
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;
        }
        return layoutParams;
    }
}
