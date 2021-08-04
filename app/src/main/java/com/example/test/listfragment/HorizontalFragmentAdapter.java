package com.example.test.listfragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class HorizontalFragmentAdapter<T extends Fragment> extends FragmentStateAdapter {
    private List<T> fragments;

    // 在Activity中调用
    public HorizontalFragmentAdapter(@NonNull FragmentActivity fragmentActivity, List<T> list) {
        super(fragmentActivity);

        init(list);
    }

    // 在Fragment中调用
    public HorizontalFragmentAdapter(@NonNull Fragment Fragment, List<T> list) {
        super(Fragment);

        init(list);
    }

    private void init(List<T> list) {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.addAll(list);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments == null ? null : (Fragment) fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
