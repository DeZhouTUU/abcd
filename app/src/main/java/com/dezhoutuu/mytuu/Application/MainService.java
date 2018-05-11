package com.dezhoutuu.mytuu.Application;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dezhoutuu.mylibrary.FloatBall.floatball.FloatBallCfg;
import com.dezhoutuu.mylibrary.FloatBall.menu.FloatMenuCfg;
import com.dezhoutuu.mylibrary.FloatBall.utils.DensityUtil;
import com.dezhoutuu.mytuu.R;
import com.dezhoutuu.mytuu.Utils.MyLog;
import com.dezhoutuu.mytuu.Widget.MyPopLayoutManager;

public class MainService extends Service {

    private static final String TAG = "MainService";
    private MyPopLayoutManager mMyPopLayoutManager;

    ConstraintLayout toucherLayout;
    WindowManager.LayoutParams params;
    WindowManager windowManager;

    ImageButton imageButton1;

    //状态栏高度.
    int statusBarHeight = -1;

    private boolean isStart = false;

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
//        createToucher();
        initSinglePageFloatball(false);
    }

    private void initSinglePageFloatball(boolean showMenu) {
        //1 初始化悬浮球配置，定义好悬浮球大小和icon的drawable
        int ballSize = DensityUtil.dip2px(this, 45);
//        Drawable ballIcon = BackGroudSeletor.getdrawble("ic_floatball", this);
        Drawable ballIcon = getDrawable(R.drawable.ic_floatball);
        FloatBallCfg ballCfg = new FloatBallCfg(ballSize, ballIcon, FloatBallCfg.Gravity.RIGHT_CENTER);
        //设置悬浮球不半隐藏
//        ballCfg.setHideHalfLater(false);
        if (showMenu) {
            //2 需要显示悬浮菜单
            //2.1 初始化悬浮菜单配置，有菜单item的大小和菜单item的个数
            int menuSize = DensityUtil.dip2px(this, 180);
            int menuItemSize = DensityUtil.dip2px(this, 40);
            FloatMenuCfg menuCfg = new FloatMenuCfg(menuSize, menuItemSize);
            //3 生成floatballManager
            //必须传入Activity
            mMyPopLayoutManager = new MyPopLayoutManager(this, ballCfg, menuCfg);
//            addFloatMenuItem();
        } else {
            //必须传入Activity
            mMyPopLayoutManager = new MyPopLayoutManager(this, ballCfg);
        }
        mMyPopLayoutManager.setOnFloatBallClickListener(new MyPopLayoutManager.OnFloatBallClickListener() {
            @Override
            public void onFloatBallClick() {
                if(!isStart){
                    mMyPopLayoutManager.setFloatBallImage(getDrawable(R.drawable.ic_email));
                }else {
                    mMyPopLayoutManager.setFloatBallImage(getDrawable(R.drawable.ic_floatball));
                }
                isStart = !isStart;
            }
        });
        mMyPopLayoutManager.show();
    }

//    private void addFloatMenuItem() {
//        MenuItem personItem = new MenuItem(getDrawable(R.drawable.ic_weixin)) {
//            @Override
//            public void action() {
//                new TUUToast(MainService.this,"打开微信");
//                mMyPopLayoutManager.closeMenu();
//            }
//        };
//        MenuItem walletItem = new MenuItem(getDrawable(R.drawable.ic_weibo)) {
//            @Override
//            public void action() {
//                new TUUToast(MainService.this,"打开微博");
//            }
//        };
//        MenuItem settingItem = new MenuItem(getDrawable(R.drawable.ic_email)) {
//            @Override
//            public void action() {
//                new TUUToast(MainService.this,"打开邮箱");
//                mMyPopLayoutManager.closeMenu();
//            }
//        };
//        mMyPopLayoutManager.addMenuItem(personItem)
//                .addMenuItem(walletItem)
//                .addMenuItem(personItem)
//                .addMenuItem(walletItem)
//                .addMenuItem(settingItem)
//                .buildMenu();
//    }

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

        LayoutInflater inflater = LayoutInflater.from(getApplication());
        //获取浮动窗口视图所在布局.
        toucherLayout = (ConstraintLayout) inflater.inflate(R.layout.toucherlayout,null);
        //添加toucherlayout
        windowManager.addView(toucherLayout,params);

        Log.i(TAG,"toucherlayout-->left:" + toucherLayout.getLeft());
        Log.i(TAG,"toucherlayout-->right:" + toucherLayout.getRight());
        Log.i(TAG,"toucherlayout-->top:" + toucherLayout.getTop());
        Log.i(TAG,"toucherlayout-->bottom:" + toucherLayout.getBottom());

        //主动计算出当前View的宽高信息.
        toucherLayout.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);

        //用于检测状态栏高度.
        int resourceId = getResources().getIdentifier("status_bar_height","dimen","android");
        if (resourceId > 0)
        {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        Log.i(TAG,"状态栏高度为:" + statusBarHeight);

        //浮动窗口按钮.
        imageButton1 = (ImageButton) toucherLayout.findViewById(R.id.imageButton1);

        imageButton1.setOnClickListener(new View.OnClickListener() {
            long[] hints = new long[2];
            @Override
            public void onClick(View v) {
                Log.i(TAG,"点击了");
                System.arraycopy(hints,1,hints,0,hints.length -1);
                hints[hints.length -1] = SystemClock.uptimeMillis();
                if (SystemClock.uptimeMillis() - hints[0] >= 700)
                {
                    Log.i(TAG,"要执行");
                    Toast.makeText(MainService.this,"连续点击两次以退出",Toast.LENGTH_SHORT).show();
                }else
                {
                    Log.i(TAG,"即将关闭");
                    stopSelf();
                }
            }
        });

        toucherLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                params.x = (int) event.getRawX() - 150;
                params.y = (int) event.getRawY() - 150 - statusBarHeight;
                windowManager.updateViewLayout(toucherLayout,params);
                return false;
            }
        });
    }

    @Override
    public void onDestroy()
    {
        if (imageButton1 != null)
        {
            windowManager.removeView(toucherLayout);
        }
        stopForeground(true);
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        MyLog.ej("onStartCommand");
        Notification noti = new Notification.Builder(MainService.this.getApplicationContext())
                .setContentTitle(getText(R.string.ticker_text))
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        startForeground(1, noti);
        flags = START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

}