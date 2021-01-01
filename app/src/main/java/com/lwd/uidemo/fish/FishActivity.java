package com.lwd.uidemo.fish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.lwd.uidemo.R;

public class FishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish);
//        ImageView ivFish = findViewById(R.id.iv_fish);
//        ivFish.setImageDrawable(new FishDrawable());
    }
}