package com.lwd.uidemo;

import android.app.Application;

import com.lwd.uidemo.skin.SkinObservable;

/**
 * @AUTHOR lianwd
 * @TIME 12/20/20
 * @DESCRIPTION TODO
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SkinObservable.init(this);
    }
}
