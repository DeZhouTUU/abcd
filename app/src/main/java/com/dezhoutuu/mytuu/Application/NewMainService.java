package com.dezhoutuu.mytuu.Application;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dezhoutuu.mytuu.R;

public class NewMainService extends Service {

    private static final String TAG = "MainService";

    WindowManager.LayoutParams params;
    WindowManager windowManager;
//    FloatingActionButton mFloatingActionButton;
    Button button;

    //不与Activity进行绑定.
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i(TAG,"MainService Created");
        createToucher();
    }

    private void createToucher()
    {
        //赋值WindowManager&LayoutParam.
        params = new WindowManager.LayoutParams();
        windowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        //设置type.系统提示型窗口，一般都在应用程序窗口之上.
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        //设置效果为背景透明.
        params.format = PixelFormat.RGBA_8888;
        //设置flags.不可聚焦及不可使用按钮对悬浮窗进行操控.
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        //设置窗口初始停靠位置.
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = 0;
        params.y = 0;

        //设置悬浮窗口长宽数据.
        params.width = 300;
        params.height = 300;

//        mFloatingActionButton = new FloatingActionButton(NewMainService.this);
//        mFloatingActionButton.setBackgroundColor(getResources().getColor(R.color.colorAccent,null));
//        windowManager.addView(mFloatingActionButton,params);
        button = new Button(NewMainService.this);
        windowManager.addView(button,params);
    }

    @Override
    public void onDestroy()
    {
        if (button != null)
        {
            windowManager.removeView(button);
        }
        super.onDestroy();
    }
}