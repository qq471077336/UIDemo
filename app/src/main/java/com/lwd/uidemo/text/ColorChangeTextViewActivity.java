package com.lwd.uidemo.text;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.lwd.uidemo.R;

public class ColorChangeTextViewActivity extends AppCompatActivity {

    private ColorChangeTextView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_change_text_view);

        mView = findViewById(R.id.tv_xyjh);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                start();
            }
        }, 2000);
    }

    private void start() {
        ObjectAnimator.ofFloat(mView, "percent", 0, 1).setDuration(3000).start();
    }
}