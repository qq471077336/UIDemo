package com.lwd.uidemo.cardslide;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.lwd.uidemo.cardslide.adapter.UniversalAdapter;

import java.util.List;

/**
 * @AUTHOR lianwd
 * @TIME 1/9/21
 * @DESCRIPTION TODO
 */
public class SlideCallback extends ItemTouchHelper.SimpleCallback {

    private final RecyclerView rv;
    private final UniversalAdapter<CardBean> adapter;
    private final List<CardBean> datas;

    public SlideCallback(RecyclerView rvCardSlide, UniversalAdapter<CardBean> adapter, List<CardBean> datas) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                | ItemTouchHelper.UP | ItemTouchHelper.DOWN);
        this.rv = rvCardSlide;
        this.adapter = adapter;
        this.datas = datas;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        CardBean cardBean = datas.remove(viewHolder.getLayoutPosition());
        datas.add(0, cardBean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        //移动时，下面的3个根据比例进行缩放

        //最远滑动距离
        float maxDistance = recyclerView.getWidth() * 0.1f;
        //滑动的距离
        double distance = Math.sqrt(dX * dX + dY * dY);
        //滑动距离比例
        double fraction = distance / maxDistance;

        if (fraction > 1) {
            fraction = 1;
        }

        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            int level = childCount - 1 - i;
            View view = recyclerView.getChildAt(i);

            if (level > 0) {
                if (level < CardConfig.MAX_SHOW_COUNT - 1) {
                    //缩放
                    view.setTranslationY((float) (CardConfig.TRANS_Y_GAP * level -
                            fraction * CardConfig.TRANS_Y_GAP));
                    view.setScaleX((float) (1 - CardConfig.SCALE_GAP * level +
                            fraction * CardConfig.SCALE_GAP));
                    view.setScaleY((float) (1 - CardConfig.SCALE_GAP * level +
                            fraction * CardConfig.SCALE_GAP));
                }
            }
        }

    }

    @Override
    public long getAnimationDuration(@NonNull RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
        return 400;
    }
}
