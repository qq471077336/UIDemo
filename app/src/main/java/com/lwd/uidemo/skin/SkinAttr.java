package com.lwd.uidemo.skin;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lwd.uidemo.skin.SkinViewSupport;
import com.lwd.uidemo.skin.utils.SkinResources;
import com.lwd.uidemo.skin.utils.SkinThemeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR lianwd
 * @TIME 12/20/20
 * @DESCRIPTION view的属性
 */
public class SkinAttr {
    public static final List<String> mAttrs = new ArrayList<>();
    static {
        mAttrs.add("background");
        mAttrs.add("src");
        mAttrs.add("textColor");
        mAttrs.add("drawableLeft");
        mAttrs.add("drawableTop");
        mAttrs.add("drawableRight");
        mAttrs.add("drawableBottom");
    }
    public List<SkinView> skinViews = new ArrayList();

    public void look(View view, AttributeSet attributeSet) {
        List<AttrPair> attrs = new ArrayList<>();
        for (int i = 0; i < attributeSet.getAttributeCount(); i++) {
            String attrName = attributeSet.getAttributeName(i);
            if (mAttrs.contains(attrName)) {
                String attributeValue = attributeSet.getAttributeValue(i);

                //写死的不用管
                if (attributeValue.startsWith("#")) {
                    continue;
                }

                int resId;
                if (attributeValue.startsWith("?")) {
                    //系统的
                    int attrId = Integer.parseInt(attributeValue.substring(1));
                    resId = SkinThemeUtil.getResId(view.getContext(), new int[]{attrId})[0];
                } else {
                    resId = Integer.parseInt(attributeValue.substring(1));
                }
                attrs.add(new AttrPair(attrName, resId));
            }
        }

        if (!attrs.isEmpty() || view instanceof SkinViewSupport) {
            SkinView skinView = new SkinView(view, attrs);
            skinView.applySkin();
            skinViews.add(skinView);
        }
    }

    public void applySkin() {
        for (SkinView skinView : skinViews) {
            skinView.applySkin();
        }
    }

    static class SkinView {
        View view;
        List<AttrPair> attrs;

        public SkinView(View view, List<AttrPair> attrs) {
            this.view = view;
            this.attrs = attrs;
        }

        public void applySkin() {
            applySkinSupport();
            for (int i = 0; i < attrs.size(); i++) {
                AttrPair attrPair = attrs.get(i);
                Drawable left = null, top = null, right = null, bottom = null;
                switch (attrPair.attrName) {
                    case "background":
                        Object bg = SkinResources.getInstance().getBackground(attrPair.resId);
                        if (bg instanceof Integer) {
                            view.setBackgroundColor((Integer) bg);
                        } else {
                            view.setBackground((Drawable) bg);
                        }
                        break;
                    case "src":
                        Object src = SkinResources.getInstance().getBackground(attrPair.resId);
                        if (src instanceof Integer) {
                            ((ImageView)view).setImageDrawable(new ColorDrawable((Integer) src));
                        } else {
                            ((ImageView)view).setImageDrawable((Drawable) src);
                        }
                        break;
                    case "textColor":
                        ((TextView)view).setTextColor(SkinResources.getInstance()
                                .getColorStateList(attrPair.resId));
                        break;
                    case "drawableLeft":
                        left = SkinResources.getInstance().getDrawable(attrPair.resId);
                        break;
                    case "drawableTop":
                        top = SkinResources.getInstance().getDrawable(attrPair.resId);
                        break;
                    case "drawableRight":
                        right = SkinResources.getInstance().getDrawable(attrPair.resId);
                        break;
                    case "drawableBottom":
                        bottom = SkinResources.getInstance().getDrawable(attrPair.resId);
                        break;
                }
                if (left != null || top != null || right != null || bottom != null) {
                    ((TextView)view).setCompoundDrawablesWithIntrinsicBounds(left, top, right
                            , bottom);
                }
            }
        }

        private void applySkinSupport() {
            if (view instanceof SkinViewSupport) {
                ((SkinViewSupport) view).applySkin();
            }
        }
    }

    static class AttrPair {
        String attrName;
        int resId;

        public AttrPair(String attrName, int resId) {
            this.attrName = attrName;
            this.resId = resId;
        }
    }
}
