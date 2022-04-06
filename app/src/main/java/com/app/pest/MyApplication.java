package com.app.pest;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.app.pest.aop.ILoginFilter;
import com.app.pest.aop.LoginAssistant;
import com.app.pest.util.SharePreferenceUtil;

/**
 * 一定要在AndroidManifest.xml的application标签上添加<application android:name=".MyApplication"></application>
 * 在Android中，可以通过继承Application类来实现应用程序级的全局变量，这种全局变量方法相对静态类更有保障，直到应用的所有Activity全部被destory掉之后才会被释放掉。
 * 当Android程序启动时系统会创建一个Application对象，用来存储系统的一些信息。
 * Android系统自动会为每个程序运行时创建一个Application类的对象且只创建一个，所以Application可以说是单例（singleton）模式的一个类。
 * <p>
 * 项目启动时就运行。
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LoginAssistant.getInstance().setApplicationContext(this).setILoginFilter(iLoginFilter);// 初始化全局变量
    }

    ILoginFilter iLoginFilter = new ILoginFilter() {

        //未登录，处理方式
        //1.如果loginDefine为0，跳转到登录页面
        //2.如果loginDefine为1，提示，未登录
        //2.如果loginDefine为2，提示，执行失败
        @Override
        public void login(Context applicationContext, int loginDefine) {
            switch (loginDefine) {
                case 0:
                    Intent intent = new Intent(applicationContext, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    break;
                case 1:
                    Toast.makeText(applicationContext, "您还没有登录，请登陆后执行", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(applicationContext, "执行失败，因为您还没有登录！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        //是否登录
        @Override
        public boolean isLogin(Context applicationContext) {
            return SharePreferenceUtil.getBooleanSp(SharePreferenceUtil.IS_LOGIN, applicationContext);
        }
    };
}
