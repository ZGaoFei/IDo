package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.baselibrary.communication.Route;
import com.example.baselibrary.communication.RouteDispatch;
import com.example.test.keyboard.KeyBoardActivity;
import com.example.test.listfragment.ListFragmentActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /**
     * 跳转到应用市场
     * OPPO
     * 华为
     * 小米
     * 三星
     */
    private void test() {
        if (checkDeviceByManufacturer("samsung")) {
            goToSamsungAppsMarket(this, "com.xunlei.tdlive");
        } else {
            Uri uri = Uri.parse("market://details?id=com.xunlei.tdlive");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        if (!TextUtils.isEmpty(marketPkg)) {
//            intent.setPackage(marketPkg);
//        }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private static void goToSamsungAppsMarket(Context context, String packageName) {
        Uri uri = Uri.parse("http://www.samsungapps.com/appquery/appDetail.as?appId=" + packageName);
        Intent goToMarket = new Intent();
        goToMarket.setClassName("com.sec.android.app.samsungapps", "com.sec.android.app.samsungapps.Main");
        goToMarket.setData(uri);
        context.startActivity(goToMarket);
    }

    public static boolean checkDeviceByManufacturer(String manufacturerName) {
        return !TextUtils.isEmpty(android.os.Build.MANUFACTURER) && android.os.Build.MANUFACTURER.equalsIgnoreCase(manufacturerName);
    }

    private void initView() {
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteDispatch.getInstance().dispatch(MainActivity.this, "a", "data", new Route.CallBack() {
                    @Override
                    public void run(Object str) {
                        Log.e("zgf", "=======onClick=====str==" + str);
                    }
                });
            }
        });

        findViewById(R.id.bt_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteDispatch.getInstance().dispatch(MainActivity.this, "b", "data", new Route.CallBack() {
                    @Override
                    public void run(Object str) {
                        Log.e("zgf", "=======onClick=====str==" + str);
                    }
                });
            }
        });

        findViewById(R.id.bt_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteDispatch.getInstance().dispatch(MainActivity.this, "b_async", "data", new Route.CallBack() {
                    @Override
                    public void run(Object str) {
                        Log.e("zgf", "=======onClick=====str==" + str);
                    }
                });
            }
        });

        findViewById(R.id.bt_b_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteDispatch.getInstance().dispatch(MainActivity.this, "b_back", "data", new Route.CallBack() {
                    @Override
                    public void run(Object str) {
                        tvShow.setText((String) str);
                    }
                });
            }
        });

        tvShow = findViewById(R.id.tv_app_show);

        findViewById(R.id.bt_schedule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteDispatch.getInstance().dispatch(MainActivity.this, "schedule_test", "", null);
            }
        });

        findViewById(R.id.bt_serializer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteDispatch.getInstance().dispatch(MainActivity.this, "serializer_test", "", null);
            }
        });

        findViewById(R.id.bt_key_board).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyBoardActivity.start(MainActivity.this);
            }
        });

        findViewById(R.id.bt_list_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListFragmentActivity.start(MainActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String str = data.getStringExtra("data");
        Log.e("zgf", "========" + str);
        tvShow.setText(str);
    }

}