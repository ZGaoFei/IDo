package com.example.test.listfragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class ItemFragment extends RoomFragment {
    private static final String CONTENT = "content";
    private static final String COLOR = "color";
    private String content;
    private int color;

    private ViewPager2 viewPager2;
    private List<RoomFragment> list;
    private int currentIndex = 1;

    public static ItemFragment newInstance(String content, int color) {
        Bundle bundle = new Bundle();
        bundle.putString(CONTENT, content);
        bundle.putInt(COLOR, color);
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            content = bundle.getString(CONTENT);
            color = bundle.getInt(COLOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        viewPager2 = view.findViewById(R.id.vp_horizontal_fragment);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    private void initData() {
        list = new ArrayList<>();
        list.add(LeftFragment.newInstance(content, color));
        list.add(CenterFragment.newInstance(content, color));
        list.add(RightFragment.newInstance(content, color));

        HorizontalFragmentAdapter<RoomFragment> adapter = new HorizontalFragmentAdapter<>(this, list);
        viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(2);
        viewPager2.setSaveEnabled(false);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                RoomFragment fragment = list.get(position);
                fragment.inRoom();
                if (currentIndex != position) { // new page
                    RoomFragment beforeFragment = list.get(currentIndex);
                    beforeFragment.outRoom();
                    currentIndex = position;
                }
            }
        });
        viewPager2.setCurrentItem(1, false);
    }

    @Override
    void inRoom() {
        Log.e("zgf", "=======inRoom======");
    }

    @Override
    void outRoom() {
        Log.e("zgf", "=======outRoom======");
        RoomFragment fragment = list.get(currentIndex);
        fragment.outRoom();
    }

}