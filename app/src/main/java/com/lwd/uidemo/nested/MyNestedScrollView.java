package com.lwd.uidemo.nested;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

/**
 * @AUTHOR lianwd
 * @TIME 12/23/20
 * @DESCRIPTION TODO
 */
public class MyNestedScrollView extends NestedScrollView {

    private View contentView;
    private View headerView;

    public MyNestedScrollView(@NonNull Context context) {
        super(context);
    }

    public MyNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        headerView = ((ViewGroup) getChildAt(0)).getChildAt(0);
        contentView = ((ViewGroup) getChildAt(0)).getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        //把tablayout和viewpager的高度设置为屏幕scrollview高度，滑到底的时候就可以达到吸顶的假象
        layoutParams.height = getMeasuredHeight();
        contentView.setLayoutParams(layoutParams);
    }



    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        //滑动的距离比头部view的高度小，说明头部view可见，这时自己先消费一部分滑动距离
        if (dy > 0 && getScrollY() < headerView.getMeasuredHeight()) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }
}
