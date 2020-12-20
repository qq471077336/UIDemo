package com.lwd.uidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lwd.uidemo.flow.FlowActivity;
import com.lwd.uidemo.flow.FlowLayout;
import com.lwd.uidemo.skin.SkinActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_flow).setOnClickListener(this);
        findViewById(R.id.btn_skin).setOnClickListener(this);
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
        }
    }
}