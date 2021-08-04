package com.example.test.listfragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;

public class CenterFragment extends RoomFragment {
    private static final String CONTENT = "content";
    private static final String COLOR = "color";
    private String content;
    private int color;

    public CenterFragment() {
    }

    public static CenterFragment newInstance(String content, int color) {
        CenterFragment fragment = new CenterFragment();
        Bundle args = new Bundle();
        args.putString(CONTENT, content);
        args.putInt(COLOR, color);
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_center, container, false);
        TextView textView = view.findViewById(R.id.tv_center);
        textView.setText(content);
        textView.setBackgroundColor(color);
        return view;
    }

    @Override
    void inRoom() {
    }

    @Override
    void outRoom() {
    }


}