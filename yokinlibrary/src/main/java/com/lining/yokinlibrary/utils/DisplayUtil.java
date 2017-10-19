package com.lining.yokinlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;

/**
 * 作者：yokin.li on 2017/10/19 15:06
 * 邮箱：86558543@qq.com
 */

public class DisplayUtil {
    public DisplayUtil() {
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue / fontScale + 0.5F);
    }

    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue * fontScale + 0.5F);
    }

    public static int getDialogW(Activity aty) {
        new DisplayMetrics();
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        int w = dm.widthPixels - 100;
        return w;
    }

    public static int getScreenW(Activity aty) {
        DisplayMetrics dm = getScreenDM(aty);
        int w = dm.widthPixels;
        return w;
    }

    public static int getScreenH(Activity aty) {
        DisplayMetrics dm = getScreenDM(aty);
        int h = dm.heightPixels;
        return h;
    }

    public static String getScreenWH(Activity a) {
        DisplayMetrics metrics = getScreenDM(a);
        int w = metrics.widthPixels;
        int h = metrics.heightPixels;
        return w + "x" + h;
    }

    public static DisplayMetrics getScreenDM(Activity aty) {
        new DisplayMetrics();
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        return dm;
    }

    public static int getStatuBarH(Activity a) {
        Rect frame = new Rect();
        a.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }
}
