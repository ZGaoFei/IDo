package com.example.blibrary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BFirstFragment extends Fragment {

    public BFirstFragment() {
    }

    public static BFirstFragment newInstance() {
        BFirstFragment fragment = new BFirstFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b_first, container, false);
    }
}