package com.lwd.uidemo.skin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;

/**
 * @AUTHOR lianwd
 * @TIME 12/20/20
 * @DESCRIPTION 主题状态栏颜色
 */
public class SkinThemeUtil {
    private static int[] COLOR_PRIMARY_DARK_ATTRS = {android.R.attr.colorPrimaryDark};
    private static int[] STATUSBAR_COLOR_ATTRS = {android.R.attr.statusBarColor, android.R.attr.navigationBarColor};

    public static int[] getResId(Context context, int[] attrs) {
        int[] resIds = new int[attrs.length];
        TypedArray ta = context.obtainStyledAttributes(attrs);
        for (int i = 0; i < attrs.length; i++) {
            int resId = ta.getResourceId(i, 0);
            resIds[i] = resId;
        }
        ta.recycle();
        return resIds;
    }

    public static void updateStatusBarColor(Activity context) {
        if (Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        int[] resIds = getResId(context, STATUSBAR_COLOR_ATTRS);
        int statusBarColorResId = resIds[0];
        int navigationBarColorResId = resIds[1];

        setStatusBarColor(context, statusBarColorResId);
        setNavigationBarColor(context, navigationBarColorResId);
    }

    private static void setNavigationBarColor(Activity context, int navigationBarColorResId) {
        if (navigationBarColorResId != 0) {
            int color = SkinResources.getInstance().getColor(navigationBarColorResId);
            context.getWindow().setNavigationBarColor(color);
        }
    }

    private static void setStatusBarColor(Activity context, int statusBarColorResId) {
        if (statusBarColorResId != 0) {
            int color = SkinResources.getInstance().getColor(statusBarColorResId);
            context.getWindow().setStatusBarColor(color);
        } else {
            //写死颜色返回0
            int[] resId = getResId(context, COLOR_PRIMARY_DARK_ATTRS);
            int color = SkinResources.getInstance().getColor(resId[0]);
            context.getWindow().setStatusBarColor(color);
        }
    }
}
