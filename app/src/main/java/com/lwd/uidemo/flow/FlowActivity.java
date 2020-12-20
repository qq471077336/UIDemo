package com.lwd.uidemo.flow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lwd.uidemo.R;

public class FlowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);


        FlowLayout flowLayout = (FlowLayout) findViewById(R.id.flowLayout);
        String[] data = {"变体精灵", "暗影恶魔", "影魔", "龙骑士", "爱人火枪手", "黑暗游侠", "幻影长矛手", "召唤师", "虚空假面"};
        flowLayout.setData(data);
    }
}