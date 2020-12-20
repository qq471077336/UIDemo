package com.lwd.uidemo.skin.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

/**
 * @AUTHOR lianwd
 * @TIME 12/20/20
 * @DESCRIPTION TODO
 */
public class SkinResources {

    private boolean isDefaultSkin = true;
    private String mSkinPkgName;

    private Resources mAppResources;
    private Resources mSkinResources;

    private static volatile SkinResources instance;

    private SkinResources(Context context){
        mAppResources = context.getResources();
    }

    public static void init(Context context) {
        if (instance == null) {
            synchronized (SkinResources.class){
                if (instance == null) {
                    instance = new SkinResources(context);
                }
            }
        }
    }

    public static SkinResources getInstance() {
        return instance;
    }

    public void reset() {
        isDefaultSkin = true;
        mSkinPkgName = null;
        mSkinResources = null;
    }

    public void applySkin(Resources resources, String skinPkgName) {
        mSkinResources = resources;
        this.mSkinPkgName = skinPkgName;
        isDefaultSkin = TextUtils.isEmpty(skinPkgName) || resources == null;
    }

    private int getIdentifier(int resId) {
        if (isDefaultSkin) {
            return resId;
        }
        String name = mAppResources.getResourceEntryName(resId);
        String type = mAppResources.getResourceTypeName(resId);
        return mSkinResources.getIdentifier(name, type, mSkinPkgName);
    }

    public int getColor(int resId) {
        if (isDefaultSkin) {
            return mAppResources.getColor(resId);
        }
        int skinResId = getIdentifier(resId);
        if (skinResId == 0) {
            return mAppResources.getColor(resId);
        }
        return mSkinResources.getColor(skinResId);
    }

    public ColorStateList getColorStateList(int resId) {
        if (isDefaultSkin) {
            return mAppResources.getColorStateList(resId);
        }
        int skinResId = getIdentifier(resId);
        if (skinResId == 0) {
            return mAppResources.getColorStateList(resId);
        }
        return mSkinResources.getColorStateList(skinResId);
    }

    public Drawable getDrawable(int resId) {
        if (isDefaultSkin) {
            return mAppResources.getDrawable(resId);
        }
        int skinResId = getIdentifier(resId);
        if (skinResId == 0) {
            return mAppResources.getDrawable(resId);
        }
        return mSkinResources.getDrawable(skinResId);
    }

    public Object getBackground(int resId) {
        String resTypeName = mAppResources.getResourceTypeName(resId);
        if ("color".equals(resTypeName)) {
            return getColor(resId);
        } else {
            return getDrawable(resId);
        }
    }
}
