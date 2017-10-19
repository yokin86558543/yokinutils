package com.lining.yokinlibrary.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * 作者：yokin.li on 2017/10/19 17:01
 * 邮箱：86558543@qq.com
 */

public class ViewUtils {
    public ViewUtils() {
    }

    public static Bitmap captureView(View v) {
        v.destroyDrawingCache();
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        return v.getDrawingCache();
    }

    public void createDeskShortCut(Context cxt, int icon, String title, Class<?> cls) {
        Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortcutIntent.putExtra("duplicate", false);
        shortcutIntent.putExtra("android.intent.extra.shortcut.NAME", title);
        Intent.ShortcutIconResource ico = Intent.ShortcutIconResource.fromContext(cxt.getApplicationContext(), icon);
        shortcutIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", ico);
        Intent intent = new Intent(cxt, cls);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        shortcutIntent.putExtra("android.intent.extra.shortcut.INTENT", intent);
        cxt.sendBroadcast(shortcutIntent);
    }

    public static Bitmap snapShotWithStatusBar(Activity aty) {
        View view = aty.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = DisplayUtil.getScreenW(aty);
        int height = DisplayUtil.getScreenH(aty);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    public static Bitmap snapShotWithoutStatusBar(Activity aty) {
        View view = aty.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        aty.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = DisplayUtil.getScreenW(aty);
        int height = DisplayUtil.getScreenH(aty);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

    public static void shakeView(Context context, View view) {
        if(view != null) {
            TranslateAnimation shakeAnimation = new TranslateAnimation(0.0F, 10.0F, 0.0F, 0.0F);
            shakeAnimation.setDuration(500L);
            shakeAnimation.setInterpolator(new CycleInterpolator(4.0F));
            view.startAnimation(shakeAnimation);
        }

    }

    public static void shakeViewAndToast(Context context, View view, String msg) {
        shakeView(context, view);
        view.requestFocus();
        ToastUtils.showLongToast(msg);
    }

    public static void alphaAndScale(View view, Animator.AnimatorListener listener) {
        alphaAndScale(view, 500L, listener);
    }

    public static void alphaAndScale(View view, long duration) {
        alphaAndScale(view, duration, (Animator.AnimatorListener)null);
    }

    public static void alphaAndScale(View view) {
        alphaAndScale(view, 500L);
    }

    public static void alphaAndScale(View view, long duration, Animator.AnimatorListener listener) {
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", new float[]{1.0F, 0.5F, 1.0F});
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", new float[]{1.0F, 1.4F, 1.0F});
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", new float[]{1.0F, 1.4F, 1.0F});
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[]{alpha, pvhY, pvhZ});
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(duration).start();
        if(listener != null) {
            animator.addListener(listener);
        }

    }

    public static StateListDrawable getStateDrawable(Context context, int normal, int active, int disable) {
        StateListDrawable states = new StateListDrawable();
        if(active != -1) {
            states.addState(new int[]{16842919}, context.getResources().getDrawable(active));
            states.addState(new int[]{16842908}, context.getResources().getDrawable(active));
        }

        if(disable != -1) {
            states.addState(new int[]{-16842910}, context.getResources().getDrawable(disable));
        }

        if(normal != -1) {
            states.addState(new int[0], context.getResources().getDrawable(normal));
        }

        return states;
    }

    public static Bitmap fromView(Context context, View paramView) {
        paramView.destroyDrawingCache();
        paramView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED));
        paramView.layout(0, 0, paramView.getMeasuredWidth(), paramView.getMeasuredHeight());
        paramView.setDrawingCacheEnabled(true);
        Bitmap localBitmap = paramView.getDrawingCache(true);
        return localBitmap;
    }

    public static void setBackground(@NonNull View view, Drawable drawable) {
        if(Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }

    }
}
