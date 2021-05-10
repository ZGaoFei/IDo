package com.example.alibrary.route;

import android.content.Context;
import android.content.Intent;

import com.example.alibrary.AFirstActivity;
import com.example.baselibrary.communication.Host;
import com.example.baselibrary.communication.Route;

public class AHost implements Host {

    @Override
    public void init(Route route) {
        route.register("a", a);
    }

    Route.IRoute a = new Route.IRoute() {
        @Override
        public void action(Context context, String url, String data, Route.CallBack callBack) {
            Intent intent = new Intent(context, AFirstActivity.class);
            context.startActivity(intent);
        }
    };
}
