package com.lining.yokinlibrary.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者：yokin.li on 2017/10/19 14:23
 * 邮箱：86558543@qq.com
 * Toast工具类
 * 使用
 * 第一步  ToastUtils.init()初始化
 */

public class ToastUtils {

    private static Toast mToast;
    private static Context mContext;
    private static boolean isInint = false;
    private static final String Tag = "ToastUtils";

    public ToastUtils() {
    }

    public static void init(Context pContext) {
        mContext = pContext;
        isInint = true;
    }

    public static void showLongToast(int msg) {
        showLongToast(mContext.getResources().getString(msg));
    }

    public static void showLongToast(CharSequence msg) {
        if(isInint) {
            if(mToast == null) {
                mToast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
            } else {
                mToast.setDuration(Toast.LENGTH_LONG);
                mToast.setText(msg);
            }

            mToast.show();
        } else {
            printLog();
        }

    }

    public static void showShortToast(int msg) {
        showShortToast(mContext.getResources().getString(msg));
    }

    public static void showShortToast(String msg) {
        if(isInint) {
            if(mToast == null) {
                mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
            } else {
                mToast.setDuration(Toast.LENGTH_SHORT);
                mToast.setText(msg);
            }

            mToast.show();
        } else {
            printLog();
        }

    }

    public static void showToast(String msg, int duration) {
        if(isInint) {
            if(mToast == null) {
                mToast = Toast.makeText(mContext, msg, duration);
            } else {
                mToast.setDuration(duration);
                mToast.setText(msg);
            }

            mToast.show();
        } else {
            printLog();
        }

    }

    public static void canlce() {
        if(isInint) {
            mToast.cancel();
        }

    }

    private static void printLog() {
        CL.e("ToastUtils", "Toast还未初始化，请在Application中调用ToastUtils.init()进行初始化");
    }
}
