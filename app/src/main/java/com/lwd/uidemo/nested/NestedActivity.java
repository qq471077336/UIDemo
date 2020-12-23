package com.lwd.uidemo.nested;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lwd.uidemo.R;

import java.util.ArrayList;
import java.util.List;

public class NestedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested);
        RecyclerView rvHeader = (RecyclerView) findViewById(R.id.rv_header);
        setHeader(rvHeader);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_tab);
        ViewPager2 vp2 = (ViewPager2) findViewById(R.id.vp2);
        setViewPager(tabLayout, vp2);
    }

    private void setViewPager(TabLayout tabLayout, ViewPager2 vp2) {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(this, getPageFragments());
        vp2.setAdapter(pagerAdapter);
        new TabLayoutMediator(tabLayout, vp2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("tab-" + position);
            }
        }).attach();
    }

    private List<Fragment> getPageFragments() {
        List<Fragment> data = new ArrayList<>();
        data.add(new RecyclerViewFragment());
        data.add(new RecyclerViewFragment());
        data.add(new RecyclerViewFragment());
        return data;
    }

    private void setHeader(RecyclerView rvHeader) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvHeader.setLayoutManager(linearLayoutManager);
        ArrayList<String> data = new ArrayList<>();
        data.add("header item ");
        data.add("header item ");
        data.add("header item ");
        rvHeader.setAdapter(new RecyclerViewAdapter(this, data));
    }

    static class ViewPagerAdapter extends FragmentStateAdapter {

        private List<Fragment> data;

        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> data) {
            super(fragmentActivity);
            this.data = data;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return data.get(position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}