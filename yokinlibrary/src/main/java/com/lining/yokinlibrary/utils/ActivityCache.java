package com.lining.yokinlibrary.utils;


import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * ================================================
 * 作    者：yokin Emial:86558543@qq.com
 * 版    本：1.0
 * 创建日期：2017/6/2
 * 描    述：用于同时销毁多个activity
 * 修订历史：
 * ================================================
 */
public class ActivityCache {
    public static List<Activity> activityList = new LinkedList<>();

    public ActivityCache() {

    }

    /**
     * 添加到Activity容器中
     */
    public static void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    /**
     * 便利所有Activigty并finish
     */
    public static void finishActivity() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }

    /**
     * 结束指定的Activity
     */
    public static void finishSingleActivity(Activity activity) {
        if (activity != null) {
            if (activityList.contains(activity)) {
                activityList.remove(activity);
            }
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity 在遍历一个列表的时候不能执行删除操作，所有我们先记住要删除的对象，遍历之后才去删除。
     */
    public static void finishSingleActivityByClass(Class<?> cls) {
        Activity tempActivity = null;
        for (Activity activity : activityList) {
            if (activity.getClass().equals(cls)) {
                tempActivity = activity;
            }
        }

        finishSingleActivity(tempActivity);
    }


}
