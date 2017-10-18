package com.lining.yokinlibrary.utils;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * ================================================
 * 作    者：Cate Emial:liuh@80pm.com
 * 版    本：1.0
 * 创建日期：2017/3/8.
 * 描    述：配置数据本地持有类
 * 修订历史：
 * ================================================
 */

public class AppDataUtils {

    // 当前登录的用户key
    private String CurrentKey;
    //当前用户id
    private String CurrentMemberId;
    //当前店铺id
    private String CurrentShopId;
    // 当前登录的用户名称
    private String CurrentUserName;
    //当前用户昵称
    private String CurrentNickName;
    //当前用户手机号
    private String CurrentMobile;
    //当前用户头像
    private String CurrentHead;
    //当前用户是否绑定微信
    private String CurrentIsWeiXin;
    //当前用户是否绑定QQ
    private String CurrentIsQQ;
    //当前用户是否绑定微博
    private String CurrentIsWeiBo;
    //短网址的token
    private String ShortUrlToken;

    private String FullName;


    private boolean CurrentIsAuth;
    private int isUnreadSysMsg = -1;//是否有未读系统消息 0没有，1有未读，-1初始值
    private int isUnreadOrderMsg = -1;//是否有未读订单消息
    private int isUnreadRemindMsg = -1;//是否有未读提醒消息


    private static AppDataUtils instance;
    private static Context applicationContext;


    private static SharedPreferences pePreferences;

    public static void init(Application context) {
        if (instance == null) {
            instance = new AppDataUtils();
        }
        applicationContext = context;
        // 初始化极光推送




        //pePreferences = applicationContext.getSharedPreferences(Config.USER_INFO, Context.MODE_PRIVATE);
    }


    public Context getApplicationContext() {
        return applicationContext;
    }

    public static AppDataUtils getInstance() {
        if (instance == null) {
            instance = new AppDataUtils();
        }
        return instance;
    }


    public String getShortUrlToken() {
        ShortUrlToken = applicationContext.getSharedPreferences("SHORTURLTOKEN", Context.MODE_PRIVATE).getString("TOKEN", "");
        return ShortUrlToken;
    }

    public void setShortUrlToken(String shortUrlToken) {
        ShortUrlToken = shortUrlToken;
        applicationContext.getSharedPreferences("SHORTURLTOKEN", Context.MODE_PRIVATE)
                .edit().putString("TOKEN", ShortUrlToken).commit();
    }

    public String getCurrentShopId() {
        if (TextUtils.isEmpty(CurrentMemberId)) {
            CurrentShopId = pePreferences.getString("CurrentShopId", "");
        }
        return CurrentShopId;
    }

    public void setCurrentShopId(String currentShopId) {
        CurrentShopId = currentShopId;
        pePreferences.edit().putString("CurrentShopId", CurrentShopId).commit();
    }

    public String getCurrentKey() {
        if (TextUtils.isEmpty(CurrentKey)) {
            CurrentKey = pePreferences.getString("CurrentKey", "");
        }
        return CurrentKey;
    }

    public void setCurrentMemberId(String currentMemberId) {
        this.CurrentMemberId = currentMemberId;
        pePreferences.edit().putString("CurrentMemberId", currentMemberId).commit();
    }

    public String getCurrentMemberId() {
        if (TextUtils.isEmpty(CurrentMemberId)) {
            CurrentMemberId = pePreferences.getString("CurrentMemberId", "");
        }
        return CurrentMemberId;
    }

    public String getFullName() {
        if (TextUtils.isEmpty(FullName)) {
            FullName = pePreferences.getString("FullName", "");
        }
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
        pePreferences.edit().putString("FullName", fullName).commit();
    }


    /**
     * 获取当前登录用户名
     *
     * @return
     */
    public String getCurrentUserName() {
        if (TextUtils.isEmpty(CurrentUserName)) {
            CurrentUserName = pePreferences.getString("CurrentUserName", "");
        }
        return CurrentUserName;
    }

    /**
     * 保存当前用户名
     *
     * @param currentUserName
     */
    public void setCurrentUserName(String currentUserName) {

        CurrentUserName = currentUserName;
        pePreferences.edit()
                .putString("CurrentUserName", currentUserName).apply();
    }

    /**
     * 获取当前用户昵称
     *
     * @return
     */
    public String getCurrentNickName() {
        if (TextUtils.isEmpty(CurrentNickName)) {
            CurrentNickName = pePreferences.getString("CurrentNickName", "");
        }
        return CurrentNickName;
    }

    /**
     * 保存当前用户昵称
     *
     * @param currentUserName
     */
    public void setCurrentNickName(String currentUserName) {
        CurrentNickName = currentUserName;
        pePreferences.edit()
                .putString("CurrentNickName", currentUserName).apply();
    }


    /**
     * 获取当前用户头像
     *
     * @return
     */
    public String getCurrentHead() {
        if (TextUtils.isEmpty(CurrentHead)) {
            CurrentHead = pePreferences.getString("CurrentHead", "");
        }
        return CurrentHead;
    }

    /**
     * 保存当前用户昵称
     *
     * @param currentHead
     */
    public void setCurrentHead(String currentHead) {

        CurrentHead = currentHead;
        pePreferences.edit()
                .putString("CurrentHead", currentHead).apply();
    }

    /**
     * 保存当前用户绑定微信状态
     *
     * @param currentIsWeiXin
     */
    public void setCurrentIsWeiXin(String currentIsWeiXin) {
        CurrentIsWeiXin = currentIsWeiXin;
        pePreferences.edit()
                .putString("CurrentIsWeiXin", currentIsWeiXin).apply();

    }

    /**
     * 获取当前用户绑定微信状态
     * 0：未绑定；
     */
    public String getCurrentIsWeiXin() {
        if (TextUtils.isEmpty(CurrentIsWeiXin)) {
            CurrentIsWeiXin = pePreferences.getString("CurrentIsWeiXin", "");
        }
        return CurrentIsWeiXin;

    }

    /**
     * 保存当前用户绑定QQ状态
     *
     * @param currentIsQQ
     */
    public void setCurrentIsQQ(String currentIsQQ) {
        CurrentIsQQ = currentIsQQ;
        pePreferences.edit()
                .putString("CurrentIsQQ", currentIsQQ).apply();

    }

    /**
     * 获取当前用户绑定QQ状态
     * 0：未绑定；
     */
    public String getCurrentIsQQ() {
        if (TextUtils.isEmpty(CurrentIsQQ)) {
            CurrentIsQQ = pePreferences.getString("CurrentIsQQ", "");
        }
        return CurrentIsQQ;

    }

    /**
     * 保存当前用户绑定微博状态
     *
     * @param currentIsWeiBo
     */
    public void setCurrentIsWeiBo(String currentIsWeiBo) {
        CurrentIsWeiBo = currentIsWeiBo;
        pePreferences.edit()
                .putString("CurrentIsWeiBo", currentIsWeiBo).apply();

    }

    /**
     * 获取当前用户绑定微博状态
     * 0：未绑定；
     */
    public String getCurrentIsWeiBo() {
        if (TextUtils.isEmpty(CurrentIsWeiBo)) {
            CurrentIsWeiBo = pePreferences.getString("CurrentIsWeiBo", "");
        }
        return CurrentIsWeiBo;

    }


    //设置登录人账号（电话）
    public boolean setCurrentMobile(String currentMobile) {
        CurrentMobile = currentMobile;

        return pePreferences.edit().putString("CurrentMobile", currentMobile)
                .commit();
    }

    public String getCurrentMobile() {
        if (TextUtils.isEmpty(CurrentMobile)) {
            CurrentMobile = pePreferences.getString("CurrentMobile", "");
        }
        return CurrentMobile;
    }

    public void setCurrentIsAuth(boolean isAuth) {
        CurrentIsAuth = isAuth;
        pePreferences.edit().putBoolean("CurrentIsAuth", isAuth).commit();
    }

    public boolean getCurrentIsAuth() {
        CurrentIsAuth = pePreferences.getBoolean("CurrentIsAuth", false);
        return CurrentIsAuth;
    }


    /**
     * 当前是否登录
     *
     * @return
     */
    public boolean isLogin() {
        return !TextUtils.isEmpty(getCurrentKey());
    }

    //退出登录
    public boolean logout() {

//      清除当前登录用户所有信息
        SharedPreferences.Editor edit = pePreferences.edit();
        edit.clear().apply();
        //更新消息状态
        return true;
    }

}
