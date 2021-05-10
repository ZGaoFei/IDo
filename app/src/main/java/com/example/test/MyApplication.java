package com.example.test;

import android.app.Application;

import com.example.alibrary.route.AHost;
import com.example.baselibrary.communication.RouteDispatch;
import com.example.blibrary.route.BHost;
import com.example.test.route.AppHost;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RouteDispatch.getInstance().addHost(new AppHost());
        RouteDispatch.getInstance().addHost(new AHost());
        RouteDispatch.getInstance().addHost(new BHost());
    }
}
