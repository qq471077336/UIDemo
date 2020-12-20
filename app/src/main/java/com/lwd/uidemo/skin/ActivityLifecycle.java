package com.lwd.uidemo.skin;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.LayoutInflaterCompat;

import com.lwd.uidemo.skin.utils.SkinThemeUtil;

import java.lang.reflect.Field;
import java.util.Observable;
import java.util.Observer;

/**
 * @AUTHOR lianwd
 * @TIME 12/20/20
 * @DESCRIPTION 生命周期管理
 */
public class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    private Observable mObservable;

    private ArrayMap<Activity, Observer> map = new ArrayMap<>();

    public ActivityLifecycle(Observable observable) {
        mObservable = observable;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        //更新状态栏
        SkinThemeUtil.updateStatusBarColor(activity);

        //更新布局
        //1反射把mFactorySet改为false
        //10.0不能反射
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        try {
            Field field = LayoutInflater.class.getDeclaredField("mFactorySet");
            field.setAccessible(true);
            field.setBoolean(layoutInflater, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //2设置SkinFactory为factory2
        SkinFactory skinFactory = new SkinFactory(activity);
        LayoutInflaterCompat.setFactory2(layoutInflater, skinFactory);
        map.put(activity, skinFactory);

        mObservable.addObserver(skinFactory);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        Observer observer = map.remove(activity);
        SkinObservable.getInstance().deleteObserver(observer);
    }
}
