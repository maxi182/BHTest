package com.babyloncodetest.ui;

import android.content.Context;
import android.content.Intent;

import com.babyloncodetest.ui.base.BaseSplashScreenActivity;

/**
 * Created by mac on 23/11/2016.
 */

public class SplashScreenActivity extends BaseSplashScreenActivity {

    public static Intent getNewIntent(Context context) {
        Intent intent = new Intent(context, SplashScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
    @Override
    protected Intent proceedLoading() {

        return PostListActivity.getNewIntent(this);

    }
}
