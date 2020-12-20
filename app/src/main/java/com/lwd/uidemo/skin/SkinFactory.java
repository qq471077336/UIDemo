package com.lwd.uidemo.skin;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lwd.uidemo.skin.utils.SkinThemeUtil;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * @AUTHOR lianwd
 * @TIME 12/20/20
 * @DESCRIPTION 自定义factory2来控制创建view的过程
 */
public class SkinFactory implements LayoutInflater.Factory2, Observer {

    private static final String[] mClassPrefixList = {
            "android.widget.",
            "android.webkit.",
            "android.app.",
            "android.view."
    };

    static final Class<?>[] mConstructorSignature = new Class[] {
            Context.class, AttributeSet.class};

    private static final HashMap<String, Constructor<? extends View>> sConstructorMap =
            new HashMap<String, Constructor<? extends View>>();

    private SkinAttr mSkinAttr;
    private Activity mActivity;

    public SkinFactory(Activity activity) {
        mActivity = activity;
        mSkinAttr = new SkinAttr();
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        View view = createSDKView(name, context, attrs);
        if (view == null) {
            view = createView(name, context, attrs);
        }
        if (view != null) {
           mSkinAttr.look(view, attrs);
        }

        return view;
    }

    private View createSDKView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        //包含.说明是自定义view，不需要添加前缀
        if (-1 != name.indexOf('.')) {
            return null;
        }
        for (int i = 0; i < mClassPrefixList.length; i++) {
            View view = createView(mClassPrefixList[i] + name, context, attrs);
            if (view != null) {
                return view;
            }
        }
        return null;
    }

    private View createView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        Constructor<? extends View> constructor = findConstructor(name, context);

        try {
            return constructor.newInstance(context, attrs);
        } catch (Exception e) {
        }

        return null;
    }

    private Constructor<? extends View> findConstructor(String name, Context context) {
        Constructor<? extends View> constructor = sConstructorMap.get(name);
        if (constructor == null) {
            try {
                Class<? extends View> clazz = context.getClassLoader().loadClass(name).asSubclass(View.class);
                constructor = clazz.getConstructor(mConstructorSignature);
                constructor.setAccessible(true);
                sConstructorMap.put(name, constructor);
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return constructor;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        SkinThemeUtil.updateStatusBarColor(mActivity);
        mSkinAttr.applySkin();
    }
}
