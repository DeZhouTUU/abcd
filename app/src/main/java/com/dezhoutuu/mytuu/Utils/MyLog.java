package com.dezhoutuu.mytuu.Utils;

import android.util.Log;

/**
 * Created by fatJiang on 2018/4/20.
 */

public class MyLog {
    static final boolean isLogOpen = true;

    public static void e(String tag,String text){
        if(isLogOpen){
            Log.e(tag,text);
        }
    }
    public static void ej(String text){
        if(isLogOpen){
            Log.e("fatJiang",text);
        }
    }
}
