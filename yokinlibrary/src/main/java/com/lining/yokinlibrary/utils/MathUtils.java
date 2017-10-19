package com.lining.yokinlibrary.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 作者：yokin.li on 2017/10/19 11:36
 * 邮箱：86558543@qq.com
 * Math函数的工具类
 */

public class MathUtils {
    private static final int DEF_DIV_SCALE = 10;

    public MathUtils() {
    }

    public static double add(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.add(b2).doubleValue();
    }

    public static double sub(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.subtract(b2).doubleValue();
    }

    public static double mul(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.multiply(b2).doubleValue();
    }

    public static double div(double d1, double d2) {
        return div(d1, d2, 10);
    }

    public static double div(double d1, double d2, int scale) {
        if(scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            BigDecimal b1 = new BigDecimal(Double.toString(d1));
            BigDecimal b2 = new BigDecimal(Double.toString(d2));
            return b1.divide(b2, scale, 4).doubleValue();
        }
    }

    public static double twolittercount(double point) {
        DecimalFormat df = new DecimalFormat("######0.00");
        point = Double.parseDouble(df.format(point));
        return point;
    }

    public static String twolittercountString(double point) {
        String result = String.format("%.2f", new Object[]{Double.valueOf(point)});
        return result;
    }

    public static double round(double v, int scale) {
        if(scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            BigDecimal b = new BigDecimal(Double.toString(v));
            BigDecimal ne = new BigDecimal("1");
            return b.divide(ne, scale, 4).doubleValue();
        }
    }
}
