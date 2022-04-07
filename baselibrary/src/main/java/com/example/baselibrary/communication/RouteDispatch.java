package com.example.baselibrary.communication;

import android.content.Context;

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

    public void dispatch(String url) {
        route.doRoute(null, url, null, null);
    }

    public void dispatch(Context context, String url) {
        route.doRoute(context, url, null, null);
    }

    public void dispatch(Context context, String url, String data) {
        route.doRoute(context, url, data, null);
    }

    public void dispatch(Context context, String url, String data, Route.CallBack callBack) {
        route.doRoute(context, url, data, callBack);
    }

}
