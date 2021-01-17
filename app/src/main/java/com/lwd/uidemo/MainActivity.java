package com.lwd.uidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lwd.uidemo.cardslide.CardSlideActivity;
import com.lwd.uidemo.fish.FishActivity;
import com.lwd.uidemo.flow.FlowActivity;
import com.lwd.uidemo.flow.FlowLayout;
import com.lwd.uidemo.nested.NestedActivity;
import com.lwd.uidemo.skin.SkinActivity;
import com.lwd.uidemo.stickyheader.StickyHeaderActivity;
import com.lwd.uidemo.text.ColorChangeTextViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_flow).setOnClickListener(this);
        findViewById(R.id.btn_skin).setOnClickListener(this);
        findViewById(R.id.btn_nested).setOnClickListener(this);
        findViewById(R.id.btn_fish).setOnClickListener(this);
        findViewById(R.id.btn_sticky).setOnClickListener(this);
        findViewById(R.id.btn_card).setOnClickListener(this);
        findViewById(R.id.btn_color_change).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_flow:
                startActivity(new Intent(this, FlowActivity.class));
                break;
            case R.id.btn_skin:
                startActivity(new Intent(this, SkinActivity.class));
                break;
            case R.id.btn_nested:
                startActivity(new Intent(this, NestedActivity.class));
                break;
            case R.id.btn_fish:
                startActivity(new Intent(this, FishActivity.class));
                break;
            case R.id.btn_sticky:
                startActivity(new Intent(this, StickyHeaderActivity.class));
                break;
            case R.id.btn_card:
                startActivity(new Intent(this, CardSlideActivity.class));
                break;
            case R.id.btn_color_change:
                startActivity(new Intent(this, ColorChangeTextViewActivity.class));
                break;
        }
    }
}