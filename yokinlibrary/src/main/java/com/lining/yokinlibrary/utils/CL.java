package com.lining.yokinlibrary.utils;


import android.util.Log;

/**
 * Created by ning_gg on 2017/5/5.
 *
 * 日志打印类
 *
 * version 1.0.1
 */
public class CL {
    /**
     * 是否需要打印bug，可以在application的onCreate函数里面初始化
     */
    private static boolean isDebug = true;
    /**
     * 默认的TAG
     */
    private static final String TAG = "PXLog";

    /**
     * 空的构造函数
     */
    private CL() {
    }

    /**
     * 设置当前log模式是否显示
     * @return isDebug
     */
    public static boolean isDebug() {
        return isDebug;
    }

    /**
     * 设置当前是否显示LOG的标识
     * @param _isDebug 设置当前是否显示Log的标识
     */
    public static void setIsDebug(final boolean _isDebug) {
        CL.isDebug = _isDebug;
    }

    /**
     * 下面四个是默认tag的函数
     * @param msg 输出的字符串
     */
    public static void i(final String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }
    /**
     * 下面四个是默认tag的函数
     * @param msg 输出的字符串
     */
    public static void d(final String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    /**
     * 下面四个是默认tag的函数
     * @param msg 输出的字符串
     */
    public static void e(final String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    /**
     * 下面四个是默认tag的函数
     * @param msg 输出的字符串
     */
    public static void v(final String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }

    }

    /**
     * 下面是传入自定义tag的函数
     * @param tag 自定义tag的函数
     * @param msg 输出的字符串
     */
    public static void i(final String tag, final String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    /**
     * 下面是传入自定义tag的函数
     * @param tag 自定义tag的函数
     * @param msg 输出的字符串
     */
    public static void d(final String tag, final String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    /**
     * 下面是传入自定义tag的函数
     * @param tag 自定义tag的函数
     * @param msg 输出的字符串
     */
    public static void e(final String tag, final String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    /**
     * 下面是传入自定义tag的函数
     * @param tag 自定义tag的函数
     * @param msg 输出的字符串
     */
    public static void v(final String tag, final String msg) {
        if (isDebug) {
            Log.v(tag, msg);
        }
    }
}
