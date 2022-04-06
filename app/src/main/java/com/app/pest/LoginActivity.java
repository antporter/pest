package com.app.pest;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.pest.util.SharePreferenceUtil;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置顶部状态栏的字体颜色为黑色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_login);
    }


    public void loginEvent(View view) {
        SharePreferenceUtil.setBooleanSp(SharePreferenceUtil.IS_LOGIN, true, this);
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        finish();
    }


    public void quitLogin(View view) {
//        LoginAssistant.getInstance().getILoginFilter().clearLoginStatus(this);
        SharePreferenceUtil.clearSharePref(SharePreferenceUtil.IS_LOGIN, this);
        Toast.makeText(this, "退出登录成功", Toast.LENGTH_SHORT).show();
    }
}
