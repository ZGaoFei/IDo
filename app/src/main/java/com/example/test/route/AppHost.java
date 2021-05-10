package com.example.test.route;

import android.content.Context;
import android.util.Log;

import com.example.baselibrary.sendobj.Host;
import com.example.baselibrary.sendobj.Route;

public class AppHost implements Host {

    @Override
    public void init(Route route) {
        route.register("app", app);
    }

    Route.IRoute app = new Route.IRoute() {
        @Override
        public void action(Context context, String data, String url, Route.CallBack callBack) {
            Log.e("zgf", "===app====" + data + "====" + url);
            callBack.run(data + "=app=" + url);
        }
    };
}
