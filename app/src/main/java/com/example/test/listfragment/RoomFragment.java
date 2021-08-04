package com.example.test.listfragment;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * activity -> first level fragment -> second level fragment
 * 公共的操作放在这里
 * 1、初始化
 * 2、释放资源
 * 3、事件传递
 * 4、数据传递
 */
public abstract class RoomFragment extends Fragment {

    protected ListFragmentActivity listFragmentActivity;

    abstract void inRoom();

    abstract void outRoom();

    protected void updateCenterFragment(String data) {

    }

    protected void updateLeftFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listFragmentActivity = getListFragmentActivity();
    }

    // 获取根activity
    public ListFragmentActivity getListFragmentActivity() {
        FragmentActivity activity = getActivity();
        if (activity instanceof ListFragmentActivity) {
            return (ListFragmentActivity) activity;
        }
        return null;
    }

    /**
     * 获取父Fragment
     * 使用 getChildFragmentManager() 的时候返回父 Fragment
     * 使用 getSupportFragmentManager() 的时候返回的是 null
     */
    public ItemFragment getItemFragment() {
        Fragment fragment = getParentFragment();
        if (fragment instanceof ItemFragment) {
            return (ItemFragment) fragment;
        }
        return null;
    }
}
