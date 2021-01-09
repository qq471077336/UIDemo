package com.lwd.uidemo.cardslide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lwd.uidemo.R;
import com.lwd.uidemo.cardslide.adapter.UniversalAdapter;
import com.lwd.uidemo.cardslide.adapter.ViewHolder;

import java.util.List;

public class CardSlideActivity extends AppCompatActivity {

    private List<CardBean> mDatas;
    private UniversalAdapter<CardBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_slide);
        RecyclerView rvCardSlide = findViewById(R.id.rv_card_slide);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        rvCardSlide.setLayoutManager(linearLayoutManager);
        rvCardSlide.setLayoutManager(new CardLayoutManager());
        mDatas = CardBean.initDatas();
        adapter = new UniversalAdapter<CardBean>(this, mDatas, R.layout.item_card) {

            @Override
            public void convert(ViewHolder viewHolder, CardBean slideCardBean) {
                viewHolder.setText(R.id.tvName, slideCardBean.getName());
                viewHolder.setText(R.id.tvPrecent, slideCardBean.getPosition() + "/" + mDatas.size());
                Glide.with(CardSlideActivity.this)
                        .load(slideCardBean.getUrl())
                        .into((ImageView) viewHolder.getView(R.id.iv));
            }
        };
        rvCardSlide.setAdapter(adapter);

        CardConfig.initConfig(this);

        SlideCallback callback = new SlideCallback(rvCardSlide, adapter, mDatas);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rvCardSlide);
    }
}