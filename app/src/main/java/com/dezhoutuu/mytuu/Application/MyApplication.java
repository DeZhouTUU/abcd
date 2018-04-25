package com.dezhoutuu.mytuu.Application;

import android.app.Application;
import android.view.animation.BounceInterpolator;

import com.dezhoutuu.mytuu.Widget.PopLayout;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.Screen;

/**
 * Created by fatJiang on 2018/4/20.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        //效果图1
        FloatWindow
                .with(getApplicationContext())
                .setView(new PopLayout(getApplicationContext()))
                .setWidth(Screen.width,0.2f)
                .setHeight(Screen.width,0.2f)
                .setX(Screen.width,0.8f)
                .setY(Screen.height,0.3f)
                .setMoveType(MoveType.slide)
                .setMoveStyle(500,new BounceInterpolator())
                .setDesktopShow(true)
                .build();

    }
}
