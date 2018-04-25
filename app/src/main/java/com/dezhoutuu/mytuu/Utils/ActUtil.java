package com.dezhoutuu.mytuu.Utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;


import com.dezhoutuu.mytuu.Application.BaseActivity;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;


public class ActUtil {
    private static Stack<Activity> mActivityStack;
    private static ActUtil mActUtil;

    private ActUtil() {
    }

    /**
     * 单一实例
     */
    public static ActUtil getInstance() {
        if (mActUtil == null) {
            mActUtil = new ActUtil();
        }
        return mActUtil;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }
        mActivityStack.add(activity);
    }

    /**
     * 获取栈顶Activity（堆栈中最后一个压入的）
     */
    public Activity getTopActivity() {
        if (mActivityStack != null) {
            if (mActivityStack.size() != 0) {
                Activity activity = mActivityStack.lastElement();
                return activity;
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * 结束栈顶Activity（堆栈中最后一个压入的）
     */
    public void killTopActivity() {
        Activity activity = mActivityStack.lastElement();
        killActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void killActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
        }
    }
//    public void

    /**
     * 结束指定的Activity并且判断mainActivity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
        }
    }

    /**
     * 移除指定的Activity
     */
    public void RemoveActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void killActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                killActivity(activity);
            }
        }
    }

//    public void

    public boolean isMain(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    public String getTopActivity(Activity context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if (runningTaskInfos != null)
            return (runningTaskInfos.get(0).topActivity).toString();
        else
            return null;
    }

    public void exitActivityByAppoint(Class<?> cls) {
        Iterator<Activity> iterator = mActivityStack.iterator();
        while (iterator.hasNext()) {
            Activity activity = (Activity) iterator.next();
            if (activity != null) {
                if (activity.getClass().equals(cls)) {
                    return;
                }
                activity.finish();
            }
        }
    }


    /**
     * 结束所有Activity
     */
    public void killAllActivity() {
        try {
            while (mActivityStack.size() != 0) {
                mActivityStack.pop().finish();
            }
            mActivityStack.clear();
        } catch (Exception e) {

        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            killAllActivity();
            System.exit(0);
        } catch (Exception e) {
        }
    }

    public void exitBytokentoClass(Class<BaseActivity> c) {
        Activity activity = getTopActivity();
        Intent intent = new Intent(activity, c);
        activity.startActivity(intent);
        for (int i = 0; i < mActivityStack.size() - 1; i++) {
            mActivityStack.get(i).finish();
        }
    }


    /**
     * 判断当前应用程序处于前台还是后台
     */
    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

}
