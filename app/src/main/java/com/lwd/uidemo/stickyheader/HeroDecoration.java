package com.lwd.uidemo.stickyheader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lwd.uidemo.R;

/**
 * @AUTHOR lianwd
 * @TIME 1/3/21
 * @DESCRIPTION 绘制分割线以及分组头部显示
 */
public class HeroDecoration extends RecyclerView.ItemDecoration {

    private final int groupHeaderHeight;
    private final Paint mHeaderPaint;
    private final Paint mDividerPaint;
    private final TextPaint mTextPaint;
    private final Rect mTextRect;

    public HeroDecoration(Context context) {
        groupHeaderHeight = dp2px(context, 100);

        mHeaderPaint = new Paint();
        mHeaderPaint.setColor(context.getResources().getColor(R.color.teal_700));

        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(50);

        mTextRect = new Rect();

        mDividerPaint = new Paint();
        mDividerPaint.setColor(Color.BLUE);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent,
                       @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if (parent.getAdapter() instanceof HeroAdapter) {
            HeroAdapter adapter = (HeroAdapter) parent.getAdapter();
            int count = parent.getChildCount();
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            for (int i = 0; i < count; i++) {
                View view = parent.getChildAt(i);
                int position = parent.getChildLayoutPosition(view);

                //padding里面的内容不绘制
                if (adapter.isGroupHeader(position) &&
                        view.getTop() - groupHeaderHeight - parent.getPaddingTop() >= 0) {
                    c.drawRect(left, view.getTop() - groupHeaderHeight, right, view.getTop(),
                            mHeaderPaint);
                    String groupName = adapter.getGroupName(position);
                    mTextPaint.getTextBounds(groupName, 0, groupName.length(), mTextRect);
                    c.drawText(groupName, left + dp2px(parent.getContext(), 10), view.getTop() -
                            groupHeaderHeight/2 + mTextRect.height()/2, mTextPaint);
                } else if (view.getTop() - groupHeaderHeight - parent.getPaddingTop() >= 0){
                    c.drawRect(left, view.getTop() - 1, right, view.getTop(), mDividerPaint);
                }
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent,
                           @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getAdapter() instanceof HeroAdapter) {
            HeroAdapter adapter = (HeroAdapter) parent.getAdapter();

            int firstVisibleItemPosition = ((LinearLayoutManager) parent.getLayoutManager())
                    .findFirstVisibleItemPosition();
            View view = parent.findViewHolderForAdapterPosition(firstVisibleItemPosition).itemView;

            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            int top = parent.getPaddingTop();

            String groupName = adapter.getGroupName(firstVisibleItemPosition);

            boolean groupHeader = adapter.isGroupHeader(firstVisibleItemPosition + 1);
            if (groupHeader) {
                int bottom = Math.min(groupHeaderHeight, view.getBottom() - parent.getPaddingTop());

                //限制绘制区域
                c.clipRect(left, top, right, top + bottom);

                c.drawRect(left, top, right, top + bottom, mHeaderPaint);
                mTextPaint.getTextBounds(groupName, 0, groupName.length(), mTextRect);
                c.drawText(groupName, left + dp2px(parent.getContext(), 10),
                        top + bottom - groupHeaderHeight/2 + mTextRect.height()/2, mTextPaint);
            } else {
                c.drawRect(left, top, right, top + groupHeaderHeight, mHeaderPaint);
                mTextPaint.getTextBounds(groupName, 0, groupName.length(), mTextRect);
                c.drawText(groupName, left + dp2px(parent.getContext(), 10),
                        top + groupHeaderHeight/2 + mTextRect.height()/2, mTextPaint);
            }

        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getAdapter() instanceof HeroAdapter) {
            HeroAdapter adapter = (HeroAdapter) parent.getAdapter();
            boolean isGroupHeader = adapter.isGroupHeader(parent.getChildLayoutPosition(view));
            if (isGroupHeader) {
                outRect.set(0, groupHeaderHeight, 0, 0);
            } else {
                outRect.set(0, 1, 0, 0);
            }
        }
    }

    private int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale * 0.5f);
    }
}
