package com.example.baselibrary.sendobj;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class RouteDispatch {
    private static final RouteDispatch instance = new RouteDispatch();

    private List<Host> hosts;

    private Route route;

    private RouteDispatch() {
        route = new Route();
    }

    public static RouteDispatch getInstance() {
        return instance;
    }

    public void addHost(Host host) {
        if (hosts == null) {
            hosts = new ArrayList<>();
        }
        if (hosts.contains(host)) {
            hosts.remove(host);
        }
        hosts.add(host);
    }

    public void dispatch(Context context, String data, String url, Route.CallBack callBack) {
        for (int i = 0; i < hosts.size(); i++) {
            Host host = hosts.get(i);
            Route route = new Route();
            host.init(route);
            route.doRoute(context, data, url, callBack);
        }
    }

}
