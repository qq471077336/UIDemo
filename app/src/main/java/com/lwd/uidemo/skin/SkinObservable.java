package com.lwd.uidemo.skin;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.text.TextUtils;

import com.lwd.uidemo.skin.utils.SkinResources;
import com.lwd.uidemo.skin.utils.SkinSharedPref;
import com.lwd.uidemo.skin.utils.SkinThemeUtil;

import java.lang.reflect.Method;
import java.util.Observable;

/**
 * @AUTHOR lianwd
 * @TIME 12/20/20
 * @DESCRIPTION 皮肤管理
 */
public class SkinObservable extends Observable {
    private Application context;
    private static SkinObservable instance;

    private SkinObservable(Application application) {
        context = application;
        SkinSharedPref.init(application);
        SkinResources.init(application);
        ActivityLifecycle lifecycle = new ActivityLifecycle(this);
        application.registerActivityLifecycleCallbacks(lifecycle);
        loadSkin(SkinSharedPref.getInstance().getSkinPath());
    }

    public void loadSkin(String skinPath) {
        if (TextUtils.isEmpty(skinPath)) {
            SkinSharedPref.getInstance().reset();
            SkinResources.getInstance().reset();
        } else {
            try {
                //宿主Resources
                Resources appResources = context.getResources();
                //皮肤的AssetManager
                AssetManager assetManager = AssetManager.class.newInstance();
                //设置皮肤包的资源路径
                Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
                addAssetPath.invoke(assetManager, skinPath);
                //皮肤Resources
                Resources skinResources = new Resources(assetManager, appResources.getDisplayMetrics(),
                        appResources.getConfiguration());

                //获取皮肤包包名
                PackageManager packageManager = context.getPackageManager();
                PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(skinPath,
                        PackageManager.GET_ACTIVITIES);
                String packageName = packageArchiveInfo.packageName;

                SkinResources.getInstance().applySkin(skinResources, packageName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setChanged();
        notifyObservers();
    }

    public static void init(Application application) {
        if (instance == null) {
            synchronized (SkinObservable.class) {
                if (instance == null) {
                    instance = new SkinObservable(application);
                }
            }
        }
    }

    public static SkinObservable getInstance() {
        return instance;
    }
}
