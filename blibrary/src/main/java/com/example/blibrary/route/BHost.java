package com.example.blibrary.route;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.baselibrary.sendobj.Host;
import com.example.baselibrary.sendobj.Route;
import com.example.blibrary.A;
import com.example.blibrary.BFirstActivity;
import com.example.blibrary.BFirstFragment;

public class BHost implements Host {

    @Override
    public void init(Route route) {
        route.register("b", b);
        route.register("b_async", bAsync);
        route.register("b_back", bBack);
        route.register("b_obj", bObj);
    }

    Route.IRoute b = new Route.IRoute() {
        @Override
        public void action(Context context, String url, String data, Route.CallBack callBack) {
            Log.e("zgf", "===b====" + data + "====" + url);
            callBack.run(A.add(data, url));
        }
    };

    Route.IRoute bAsync = new Route.IRoute() {
        @Override
        public void action(Context context, String url, String data, Route.CallBack callBack) {
            Log.e("zgf", "===b====" + data + "====" + url);

            Thread thread = new Thread(new Runnable() {

                @RequiresApi(api = Build.VERSION_CODES.P)
                @Override
                public void run() {
                    try {
                        Log.e("zgf", "====sleeping===");
                        Thread.sleep(10000);
                        Log.e("zgf", "====sleep end===");

                        context.getMainExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("zgf", "====back===");
                                callBack.run("hello world!");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    };

    Route.IRoute bBack = new Route.IRoute() {
        @Override
        public void action(Context context, String url, String data, Route.CallBack callBack) {
            Intent intent = new Intent(context, BFirstActivity.class);
            intent.putExtra("data", data);
            intent.putExtra("url", url);
            ((Activity) context).startActivityForResult(intent, 0);
        }
    };

    Route.IRoute bObj = new Route.IRoute() {
        @Override
        public void action(Context context, String url, String data, Route.CallBack callBack) {
            BFirstFragment fragment = BFirstFragment.newInstance();
            callBack.run(fragment);
        }
    };
}
