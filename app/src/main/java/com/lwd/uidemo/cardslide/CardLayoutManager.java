package com.lwd.uidemo.cardslide;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @AUTHOR lianwd
 * @TIME 1/9/21
 * @DESCRIPTION TODO
 */
public class CardLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //回收复用
        detachAndScrapAttachedViews(recycler);

        int bottomPosition;
        int count = getItemCount();
        if (count < CardConfig.MAX_SHOW_COUNT) {
            bottomPosition = 0;
        } else {
            bottomPosition = count - CardConfig.MAX_SHOW_COUNT;
        }

        for (int i = bottomPosition; i < count; i++) {
            //复用
            View view = recycler.getViewForPosition(i);
            addView(view);

            measureChildWithMargins(view, 0, 0);

            int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
            int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);
            layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2,
                    widthSpace / 2 + getDecoratedMeasuredWidth(view),
                    heightSpace / 2 + getDecoratedMeasuredHeight(view));

            int level = count - 1 - i;
            if (level > 0) {
                if (level >= CardConfig.MAX_SHOW_COUNT - 1) {
                    //只显示max-1数量的item，最下面的布局和倒数第二个一样
                    level -= 1;
                }
                view.setTranslationY(CardConfig.TRANS_Y_GAP * level);
                view.setScaleX(1 - CardConfig.SCALE_GAP * level);
                view.setScaleY(1 - CardConfig.SCALE_GAP * level);
            }
        }
    }
}
