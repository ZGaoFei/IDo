package com.example.baselibrary.sendobj;

import android.content.Context;

import java.util.HashMap;

public class Route {
    public interface IRoute {
        void action(Context context, String data, String url, CallBack callBack);
    }

    public interface CallBack {
        void run(Object obj);
    }

    private HashMap<String, IRoute> hashMap;

    public void register(String url, IRoute route) {
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }

        hashMap.put(url, route);
    }

    public void unregister(String url) {
        if (hashMap != null) {
            hashMap.remove(url);
        }
    }

    public void doRoute(Context context, String data, String url, CallBack callBack) {
        if (hashMap != null) {
            IRoute iRoute = hashMap.get(url);
            if (iRoute != null) {
                iRoute.action(context, data, url, callBack);
            }
        }
    }
}
