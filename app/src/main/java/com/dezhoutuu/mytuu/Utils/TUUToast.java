package com.dezhoutuu.mytuu.Utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by fatJiang on 2018/4/20.
 */

public class TUUToast extends Toast{

    public TUUToast(Context context, String showText) {
        super(context);
        this.showText = showText;
        Toast.makeText(context, showText, Toast.LENGTH_LONG).show();
    }

    private String showText;
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public TUUToast(Context context) {
        super(context);
    }
}
