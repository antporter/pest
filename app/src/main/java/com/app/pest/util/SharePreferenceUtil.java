package com.app.pest.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 保存登录状态，配置参数等
 * Android平台为我们提供了一个SharedPreferences接口，它是一个轻量级的存储类，特别适合用于保存软件配置参数。
 * 使用SharedPreferences保存数据，比如登录状态，每次启动app时就可以读取这里的存储的登录状态
 */
public class SharePreferenceUtil {
    public static final String IS_LOGIN = "isLogin";

    //获取
    public static boolean getBooleanSp(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        //获取一个boolean值，如果key为空，则返回false
        return preferences.getBoolean(key, false);
    }

    //清除
    public static void clearSharePref(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }

    //保存
    public static void setBooleanSp(String key, Boolean value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

}
