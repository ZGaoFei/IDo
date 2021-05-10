package com.example.baselibrary.sendobj;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class RouteDispatch {
    private static final RouteDispatch instance = new RouteDispatch();

    private final Route route;

    private RouteDispatch() {
        route = new Route();
    }

    public static RouteDispatch getInstance() {
        return instance;
    }

    public void addHost(Host host) {
        if (host != null) {
            host.init(route);
        }
    }

    public void dispatch(Context context, String url, String data, Route.CallBack callBack) {
        route.doRoute(context, url, data, callBack);
    }

}
