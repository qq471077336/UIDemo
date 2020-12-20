package com.lwd.uidemo.flow;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR lianwd
 * @TIME 12/17/20
 * @DESCRIPTION TODO
 */
public class FlowLayout extends ViewGroup {

    private List<List<View>> listView = new ArrayList<>();
    private List<Integer> lineHeights = new ArrayList<>();
    private int horizontalSpacing = dp2Px(16);
    private int verticalSpacing = dp2Px(8);

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void clearList() {
        listView.clear();
        lineHeights.clear();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        clearList();

        //先度量孩子
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);

        int usedWidth = 0;
        int lineHeight = 0;
        int neededWidth = 0;
        int neededHeight = 0;

        List<View> list = new ArrayList<>();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams layoutParams = child.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,
                    paddingLeft + paddingRight, layoutParams.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,
                    paddingTop + paddingBottom, layoutParams.height);
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            int childMeasuredWidth = child.getMeasuredWidth();
            int childMeasuredHeight = child.getMeasuredHeight();

            //加入下一个子view超过自身宽度
            if (usedWidth + childMeasuredWidth + horizontalSpacing > selfWidth) {
                listView.add(list);
                lineHeights.add(lineHeight);

                neededWidth = Math.max(neededWidth, usedWidth);
                neededHeight = neededHeight + lineHeight + verticalSpacing;

                list = new ArrayList<>();
                usedWidth = 0;
                lineHeight = 0;
            }
            usedWidth = usedWidth + childMeasuredWidth + horizontalSpacing;
            lineHeight = Math.max(lineHeight, childMeasuredHeight);

            list.add(child);

            if (i == childCount - 1) {
                listView.add(list);
                lineHeights.add(lineHeight);

                neededWidth = Math.max(neededWidth, usedWidth);
                neededHeight = neededHeight + lineHeight + verticalSpacing;
            }
        }

        //再度量自己
        int measuredWidth, measuredHeight;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        measuredWidth = widthMode == MeasureSpec.EXACTLY?selfWidth:neededWidth;
        measuredHeight = heightMode == MeasureSpec.EXACTLY?selfHeight:neededHeight;

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineCount = lineHeights.size();

        int curTop = getPaddingTop();
        int curLeft = getPaddingLeft();

        for (int i = 0; i < lineCount; i++) {

            List<View> views = listView.get(i);

            for (int j = 0; j < views.size(); j++) {
                View view = views.get(j);

                int left, top, right, bottom;
                left = curLeft;
                top = curTop;
                right = left + view.getMeasuredWidth();
                bottom = top + view.getMeasuredHeight();
                view.layout(left, top, right, bottom);

                curLeft = right + horizontalSpacing;
            }

            curLeft = getPaddingLeft();
            curTop = curTop + lineHeights.get(i) + verticalSpacing;
        }
    }

    public void setData(String[] data) {
        for (int i = 0; i < data.length; i++) {
            TextView tv = new TextView(getContext());
            tv.setText(data[i]);
            addView(tv);
        }
        invalidate();
    }

    private int dp2Px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
}
