package com.example.test.listfragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListFragmentAdapter<T extends Fragment> extends FragmentStateAdapter {
    private List<T> fragments;

    // 在Activity中调用
    public ListFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    // 在Fragment中调用
    public ListFragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public void update(List<T> list) {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (fragments == null) {
            return null;
        }
        position = position % fragments.size();
        return (Fragment) fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments == null ? 0 : fragments.size() * 10000; // Integer.MAX_VALUE
    }
}
