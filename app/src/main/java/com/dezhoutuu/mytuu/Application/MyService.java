package com.dezhoutuu.mytuu.Application;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.dezhoutuu.mytuu.Utils.MyLog;

/**
 * Created by fatJiang on 2018/4/20.
 */

public class MyService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyLog.ej("MyService.onCreate");
    }



}
