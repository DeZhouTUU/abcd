package com.dezhoutuu.mytuu.Application;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.dezhoutuu.mytuu.Utils.ActUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by fatJiang on 2018/4/20.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getContentViewId();
    public abstract void initView();
    protected boolean SCREEN_ORIENTATION_PORTRAIT = true;// 是否禁止横竖屏切换

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActUtil.getInstance().addActivity(this);
        if (SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 禁止横屏
        }
        setContentView(getContentViewId());
        mUnbinder = ButterKnife.bind(this);
        initView();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onDestroy() {
        ActUtil.getInstance().removeActivity(this);
        mUnbinder.unbind();
        super.onDestroy();
    }
}
