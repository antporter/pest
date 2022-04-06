package com.app.pest.aop;

import android.content.Context;

/**
 * 单例模式
 */

public class LoginAssistant {
    private LoginAssistant() {
    }

    private static LoginAssistant instance;

    public static LoginAssistant getInstance() {
        if (instance == null) {
            synchronized (LoginAssistant.class) {
                if (null == instance) {
                    instance = new LoginAssistant();
                }
            }
        }
        return instance;
    }

    private Context applicationContext;
    private ILoginFilter iLoginFilter;

    public ILoginFilter getILoginFilter() {
        return iLoginFilter;
    }

    public LoginAssistant setILoginFilter(ILoginFilter iLoginFilter) {
        this.iLoginFilter = iLoginFilter;
        return this;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public LoginAssistant setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
        return this;
    }


}
