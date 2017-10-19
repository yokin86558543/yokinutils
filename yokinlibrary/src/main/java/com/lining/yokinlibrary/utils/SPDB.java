package com.lining.yokinlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 作者：yokin.li on 2017/10/19 09:59
 * 邮箱：86558543@qq.com
 * SharedPreferences的工具类
 * 使用
 * 第一步  在Application中初始化
 *          SPDB.init() 如果要更改SPDB_NAME的话 在初始化之前setSPName设置名称
 * 第二步
 *          SPDB.getInstance().method();
 */

public class SPDB {
    /** 配置数据库名称 */
    private static  String SPDB_NAME = "spdb_yokin";
    private static SharedPreferences mySharedPreference;
    private static SPDB instance;
    SharedPreferences.Editor editor;

    /**
     * 如果想重命名的话就要在获取单列之前命名
     * @param name
     */
    public static void setSPName(String name){
        if (name!=null&&!"".equals(name)){
            SPDB_NAME=name;
        }
    }

    private SPDB(Context context){
        mySharedPreference=context.getSharedPreferences(SPDB_NAME,context.MODE_PRIVATE);
        editor=mySharedPreference.edit();
    }
    /**
     * 使用同步锁避免多线程的同步问题
     */
    public  static synchronized void init(Context context){
        if(instance==null){
            instance=new SPDB(context);
        }
    }
    public static SPDB getInstance(){
        if(instance==null){
            throw new RuntimeException("class should init!");
        }
        return instance;
    }
    public void saveData(String key,Object data){
        String type=data.getClass().getSimpleName();

        if ("Integer".equals(type)){
            editor.putInt(key,(Integer)data);
        }else if("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean) data);
        }else if ("String".equals(type)){
            editor.putString(key, (String) data);
        }else if ("Float".equals(type)){
            editor.putFloat(key, (Float) data);
        }else if ("Long".equals(type)){
            editor.putLong(key, (Long) data);
        }
        editor.commit();
    }
    public Object getData(String key,Object defValue ){
        String type=defValue.getClass().getSimpleName();
        if ("Integer".equals(type)){
            return mySharedPreference.getInt(key, (Integer) defValue);
        }else if("Boolean".equals(type)){
            return mySharedPreference.getBoolean(key, (Boolean) defValue);
        }else if ("String".equals(type)){
            return mySharedPreference.getString(key, (String) defValue);
        }else if("Float".equals(type)){
            return mySharedPreference.getFloat(key, (Float) defValue);
        }else if ("Long".equals(type)){
            return mySharedPreference.getLong(key, (Long) defValue);
        }
        return null;
    }
    public void clearData(){
        editor.clear();
        editor.commit();
    }

    /**
     * 储存图片
     *
     * @param bitmap
     */
    public void disposeImage(String userPhonenum, Bitmap bitmap) {
        ByteArrayOutputStream outputStream = null;
        try {
            SharedPreferences.Editor editor = mySharedPreference.edit();
            outputStream = new ByteArrayOutputStream();
			/*
			 * 读取和压缩图片资源 并将其保存在 ByteArrayOutputStream对象中
			 */
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
            String imgBase64 = new String(Base64.encode(
                    outputStream.toByteArray(), Base64.DEFAULT));
            editor.putString(userPhonenum, imgBase64);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取图片
     *
     * @return
     */
    public Drawable readImage(String userPhonenum) {
        ByteArrayInputStream inputStream = null;
        Drawable drawable = null;
        try {
            String imgbase64 = mySharedPreference.getString(userPhonenum, "");
            byte[] imgbyte = Base64
                    .decode(imgbase64.getBytes(), Base64.DEFAULT);
            inputStream = new ByteArrayInputStream(imgbyte);
            drawable = Drawable.createFromStream(inputStream, "image");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return drawable;
    }
}
