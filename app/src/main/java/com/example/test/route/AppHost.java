package com.example.test.route;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.baselibrary.communication.Host;
import com.example.baselibrary.communication.Route;
import com.example.test.scheduletest.ScheduleTestActivity;
import com.example.test.serializertest.SerializerTestActivity;

public class AppHost implements Host {

    @Override
    public void init(Route route) {
        route.register("app", app);
        route.register("schedule_test", scheduleTest);
        route.register("serializer_test", serializerTest);
    }

    Route.IRoute app = new Route.IRoute() {
        @Override
        public void action(Context context, String url, String data, Route.CallBack callBack) {
            Log.e("zgf", "===app====" + data + "====" + url);
            callBack.run(data + "=app=" + url);
        }
    };

    Route.IRoute scheduleTest = new Route.IRoute() {
        @Override
        public void action(Context context, String url, String data, Route.CallBack callBack) {
            context.startActivity(new Intent(context, ScheduleTestActivity.class));
        }
    };

    Route.IRoute serializerTest = new Route.IRoute() {
        @Override
        public void action(Context context, String url, String data, Route.CallBack callBack) {
            context.startActivity(new Intent(context, SerializerTestActivity.class));
        }
    };
}
