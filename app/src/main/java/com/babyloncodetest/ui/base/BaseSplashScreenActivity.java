package com.babyloncodetest.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by maximiliano.ferraiuolo on 07/11/2016.
 */

public abstract class BaseSplashScreenActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_TIME_OUT = 500;

    private long mSplashScreenTimeout = SPLASH_SCREEN_TIME_OUT;

    protected abstract Intent proceedLoading();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent newIntent = proceedLoading();
                if (newIntent != null) {
                    startActivity(newIntent);
                }
                finish();
            }
        }, mSplashScreenTimeout);

    }

    protected void setSplashScreenTimeout(long splashScreenTimeout) {
        mSplashScreenTimeout = splashScreenTimeout;
    }
}
