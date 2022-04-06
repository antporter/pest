package com.app.pest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.pest.anno.LoginFilter;
import com.app.pest.util.SharePreferenceUtil;

/**
 * app icon 放 mipmap 下面，其他图片都放 drawable 下面
 */


//AppCompatActivity默认带标题 Activity不带
public class MainActivity extends Activity implements View.OnClickListener {
    private Toast pressBackToast;
    private long mLastBackPress;
    private static final long mBackPressThreshold = 3500;


    //状态栏：电量、时间等那一栏
    //标题栏：状态栏下面，有点像页面title，一般用来说明activity的标题，比如微信。
    //导航栏(Toolbar)：标题栏。
    //标题栏actionbar或toorbar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置顶部状态栏的字体颜色为黑色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        // 去掉顶部标题，activity继承的是AppCompatActivity时无效
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉顶部标题,当前activity生效，全部activity生效：在AndroidManifest.xml文件中定义<application android:theme="@android:style/Theme.NoTitleBar">
        //getSupportActionBar().hide();
        //设置窗体全屏,看不见顶部时间那一栏
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //清除窗体全屏
        //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        findViewById(R.id.skip).setOnClickListener(this);

        pressBackToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
    }


    @LoginFilter(loginDefine = 0)
    @Override
    //跳转到需要拦截登录的Activity
    public void onClick(View v) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    //跳转到不需要拦截登录的Activity
    public void skipNoLogin(View view) {
        startActivity(new Intent(this, ThridActivity.class));
    }


    public void clearLoginInfo(View view) {
        SharePreferenceUtil.clearSharePref(SharePreferenceUtil.IS_LOGIN, this);
        Toast.makeText(this, "清除登录信息成功！", Toast.LENGTH_SHORT).show();
    }


    //结果处理函数，当从secondActivity中返回时调用此函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String text = null;
            if (bundle != null) {
                text = bundle.getString("second");
            }

        }
    }

    /**
     * 防止应用程序在意外按下后退按钮时关闭。
     * 返回按钮指定两个连续的反压到之间的最大延迟（毫秒）
     * 退出应用程序。
     */

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (Math.abs(currentTime - mLastBackPress) > mBackPressThreshold) {
            //小米手机新系统，应用 Toast自带应用名，如：“应用名：Toast信息”（Toast会显示app的名称+显示的内容）
            // 需要先给Toast的message设置为空，然后再设置需要提示的message
            //pressBackToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
            pressBackToast.setText("再按一次返回退出");
            pressBackToast.show();
            mLastBackPress = currentTime;
        } else {
            pressBackToast.cancel();
            super.onBackPressed();
        }
    }
}
