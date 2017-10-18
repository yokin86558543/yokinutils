package com.lining.yokinlibrary.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;

/**
 * ================================================
 * 作    者：jianghao Emial:jiangh@80pm.com
 * 版    本：1.0
 * 创建日期：2017/6/21 0021
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class GlideConfiguration implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}