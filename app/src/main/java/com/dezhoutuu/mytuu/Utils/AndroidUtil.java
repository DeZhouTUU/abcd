package com.dezhoutuu.mytuu.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


/**
 * 各类杂七杂八方法
 * <p> getScreenSize 获取屏幕尺寸
 * <p>
 *
 * @author M.c
 */
public class AndroidUtil {

    /**
     * 获取屏幕尺寸
     *
     * @param context
     * @param type    1为宽度 2为高度
     * @return
     */
    public static int getScreenSize(Context context, int type) {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
//		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        if (type == 1) {
            return dm.widthPixels;
        } else {
            return dm.heightPixels;
        }

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /*
     * 获取屏幕分辨率
     */
    public static int getDisplay(Activity context, int type) {//1 宽 2高
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        return type == 1 ? widthPixels : heightPixels;
    }

    /**
     * OnCreate中获得组件大小
     * params2: 1.宽 2.高
     */
    public static int getViewSize(View view, int type) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        int width = view.getMeasuredWidth();
        if (type == 1) {
            return width;
        } else {
            return height;
        }
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    private static int statusBarHeight = -1;

    public static int getStateBarHeight() {

        if (statusBarHeight == -1) {
            int resourceId = Resources.getSystem().getIdentifier(
                    "status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = Resources.getSystem().getDimensionPixelSize(resourceId);
            }
        }
        return statusBarHeight;
    }



    /**
     * 隐藏软件盘
     *
     * @param activity
     */
    public static void hideMethod(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 打卡软键盘
     */
    public static void openKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    /**
     * 获取屏幕分辨率
     *
     * @param context
     * @return
     */
    public static int[] getScreenDispaly(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();// 手机屏幕的宽度
        int height = windowManager.getDefaultDisplay().getHeight();// 手机屏幕的高度
        int result[] = {width, height};
        return result;
    }


    /**
     * 检查当前网络是否可用
     *
     * @return
     */

    public static boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }








}
