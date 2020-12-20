package com.lwd.uidemo.skin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lwd.uidemo.R;

public class SkinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);

        findViewById(R.id.tv_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //需要先导入皮肤包，然后设置皮肤包到对应目录
                SkinObservable.getInstance().loadSkin("/data/data/com.lwd.uidemo/skin-debug.apk");
            }
        });

        findViewById(R.id.tv_restore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinObservable.getInstance().loadSkin(null);
            }
        });
    }
}