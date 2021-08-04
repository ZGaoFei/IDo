package com.example.test.listfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class ListFragmentActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private ListFragmentAdapter<RoomFragment> adapter;

    private List<RoomFragment> list;
    private int currentIndex = 0;

    public static void start(Context context) {
        context.startActivity(new Intent(context, ListFragmentActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fragment);

        initView();
        initData();
    }

    private void initView() {
        viewPager = findViewById(R.id.vp_fragment);

        adapter = new ListFragmentAdapter<>(this);
        viewPager.setAdapter(adapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.e("zgf", "======onPageSelected======" + position + "====" + currentIndex);

                if (list == null) {
                    return;
                }
                position = position % list.size();
                RoomFragment currentFragment = list.get(position);
                currentFragment.setUserVisibleHint(true);
                currentFragment.inRoom();
                if (currentIndex != position) { // new page
                    RoomFragment beforeFragment = list.get(currentIndex);
                    beforeFragment.setUserVisibleHint(false);
                    beforeFragment.outRoom();
                    currentIndex = position;
                }
            }
        });
    }

    private void initData() {
        int[] colors = new int[]{Color.GRAY, Color.GREEN, Color.YELLOW, Color.RED, Color.BLUE};
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(ItemFragment.newInstance("item: " + i, colors[i]));
        }
        adapter.update(list);
        viewPager.setCurrentItem(list.size() / 2 * 1000, false);
    }
}