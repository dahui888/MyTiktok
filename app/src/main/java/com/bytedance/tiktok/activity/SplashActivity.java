package com.bytedance.tiktok.activity;

import android.content.Intent;
import android.os.CountDownTimer;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseActivity;

/**
 * createi小灰
 * create on 2020/5/19
 * description App启动页面
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init( ) {
        setFullScreen();

        new CountDownTimer(500, 500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }.start();
    }
}
