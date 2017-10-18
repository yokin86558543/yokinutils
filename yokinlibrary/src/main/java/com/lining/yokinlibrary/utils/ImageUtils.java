package com.lining.yokinlibrary.utils;

/**
 * 作者：yokin.li on 2017/10/18 10:41
 * 邮箱：86558543@qq.com
 */


import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
/**
 * ================================================
 * 作    者：Cate Emial:liuh@80pm.com
 * 版    本：1.0
 * 创建日期：2017/3/8.
 * 描    述：
 * 修订历史：
 * ================================================
 */


public class ImageUtils {
    /**
     * 加载图片
     * @param mImg 加载的控件
     * @param url 加载的url
     * @param defaultImg 默认图片
     */
    public static void loadImage(View mImg, String url,int defaultImg) {
        if (mImg.getContext() != null) {
            if (mImg.getContext() instanceof AppCompatActivity) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (((AppCompatActivity) mImg.getContext()).isDestroyed()) {
                        return;
                    }
                }
            }
        } else {
            return;
        }

        if (Build.VERSION.SDK_INT >= 23) {
            ((ImageView) mImg).setImageResource(defaultImg);
            Glide.with(mImg.getContext()).load(url)
                    .placeholder(defaultImg).dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into((ImageView) mImg);
        } else {
            Glide.with(mImg.getContext()).load(url)
                    .placeholder(defaultImg).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into((ImageView) mImg);
        }
    }


    /**
     * 加载圆形的图片
     *
     * @param mImg
     * @param url
     */
    public static void loadCicleImage(View mImg, String url,int defaultImg) {
        if (mImg.getContext() != null) {
            if (url != null) {
                Glide.with(mImg.getContext()).load(url)
                        .placeholder(defaultImg)
                        .transform(new GlideCircleTransform(mImg.getContext()))
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into((ImageView) mImg);
            } else {
                ((ImageView) mImg).setImageResource(defaultImg);
            }
        }
    }

    public static void loadHeadImage(View mImg, String url,int defaultImg) {
        if (mImg.getContext() != null) {
            if (mImg.getContext() instanceof AppCompatActivity) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (((AppCompatActivity) mImg.getContext()).isDestroyed()) {
                        return;
                    }
                }
            }
        } else {
            return;
        }
        if (url != null) {
            Glide.with(mImg.getContext()).load(url)
                    .placeholder(defaultImg)
                    .transform(new GlideCircleTransform(mImg.getContext()))
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into((ImageView) mImg);

        } else {
            ((ImageView) mImg).setImageResource(defaultImg);
        }

    }


    /**
     * 加载本地圆形图
     */
    public static void loadLocalCicleImage(View mImg, String url,int defaultImg) {
        if (mImg.getContext() != null) {
            if (url != null) {
                Glide.with(mImg.getContext()).load(url)
                        .placeholder(defaultImg)
                        .transform(new GlideCircleTransform(mImg.getContext()))
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into((ImageView) mImg);
            } else {
                ((ImageView) mImg).setImageResource(defaultImg);
            }
        }
    }


}
