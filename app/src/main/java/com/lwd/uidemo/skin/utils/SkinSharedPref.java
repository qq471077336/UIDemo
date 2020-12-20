package com.lwd.uidemo.skin.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @AUTHOR lianwd
 * @TIME 12/20/20
 * @DESCRIPTION SharedPreferences保存皮肤包路径
 */
public class SkinSharedPref {

    private static final String SHARED_NAME = "skins";
    private final String SKIN_PATH = "skin_path";

    private volatile static SkinSharedPref instance;
    private final SharedPreferences mSp;

    private SkinSharedPref(Context context){
        mSp = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (instance == null) {
            synchronized (SkinSharedPref.class) {
                if (instance == null) {
                    instance = new SkinSharedPref(context);
                }
            }
        }
    }

    public static SkinSharedPref getInstance() {
        return instance;
    }

    public void setSkin(String path) {
        SharedPreferences.Editor edit = mSp.edit();
        edit.putString(SKIN_PATH, path);
        edit.apply();
    }

    public String getSkinPath() {
        return mSp.getString(SKIN_PATH, null);
    }

    public void reset() {
        mSp.edit().remove(SKIN_PATH).apply();
    }
}
