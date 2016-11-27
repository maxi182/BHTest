package com.babyloncodetest;

import android.app.Application;

import com.babyloncodetest.api.RestClient;

/**
 * Created by maximiliano.ferraiuolo on 23/11/2016.
 */

public class BHApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RestClient.init(this);
    }
}