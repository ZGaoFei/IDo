package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.JobIntentService;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.baselibrary.communication.Route;
import com.example.baselibrary.communication.RouteDispatch;
import com.example.test.keyboard.KeyBoardActivity;
import com.example.test.listfragment.ListFragmentActivity;
import com.example.test.traceview.TraceViewTestActivity;
import com.example.test.wrapview.WrapLayout;

import java.io.File;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView tvShow;

    private WrapLayout wrapLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
//        test2();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        test2();
    }

    @Override
    protected void onResume() {
        super.onResume();
        test2();
    }

    private void test2() {
        Log.e("zgf", "=======test2========" + Thread.currentThread().toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("zgf", "=======test2===onResume===12==" + Thread.currentThread().toString());
                tvShow.setText("hahahahahahaha");
            }
        }).start();
    }

    private void test3() {
        getScore("10000");
        getScore("100");
        getScore("99999");
        getScore("10001");
        getScore("10010");
        getScore("10030");
        getScore("10400");
        getScore("10500");
        getScore("16000");
        getScore("17000");
        getScore("19000");
        getScore("90000");
        getScore("199000");
    }

    private void test5() {
        String url = "http://www.baidu.con";
        if (url.startsWith("http:")) {
            url = "https" + url.substring(4);
        }
        Log.e("zgf", "========" + url);
    }

    private String getScore(String score) {
        Log.e("zgf", "==score==" + score);
        if (TextUtils.isEmpty(score)) {
            return "0";
        }
        double v = Double.parseDouble(score);
        Log.e("zgf", "===v===" + v);
        if (v >= 10000) {
            double r = v / 10000;
            Log.e("zgf", "=====r====" + r);
            String result = new BigDecimal(r).setScale(1, BigDecimal.ROUND_HALF_UP) + "";
            Log.e("zgf", "=======result===111====" + result);
            if (result.endsWith("0")) {
                result = result.substring(0, result.length() - 2);
                Log.e("zgf", "=======result==222=====" + result);
            }
            return result;
        } else {
            return score;
        }
    }

    private void test4() {
        File filesDir = getApplicationContext().getFilesDir(); // /data/user/0/com.example.test/files
        Log.e("zgf", "=====filesDir======" + filesDir.getAbsolutePath());

        String log = getApplicationContext().getExternalFilesDir("log").getAbsolutePath(); // /storage/emulated/0/Android/data/com.example.test/files/log
        Log.e("zgf", "=======log=========" + log);

        File externalStorageDirectory = Environment.getExternalStorageDirectory(); // /storage/emulated/0
        Log.e("zgf", "=====externalStorageDirectory======" + externalStorageDirectory.getAbsolutePath());

        File filesDir1 = getFilesDir(); // /data/user/0/com.example.test/files
        Log.e("zgf", "=====filesDir1======" + filesDir1.getAbsolutePath());

        File emoji = getExternalFilesDir("emoji"); // /storage/emulated/0/Android/data/com.example.test/files/emoji
        Log.e("zgf", "=====emoji======" + emoji.getAbsolutePath());

        File res = getExternalFilesDir("res"); // /storage/emulated/0/Android/data/com.example.test/files/res
        Log.e("zgf", "=====res======" + res.getAbsolutePath());

        String absolutePath = getApplicationContext().getExternalCacheDir().getAbsolutePath(); // /storage/emulated/0/Android/data/com.example.test/cache
        Log.e("zgf", "=====absolutePath======" + absolutePath);

        String absolutePath1 = Environment.getExternalStorageDirectory().getAbsolutePath(); // /storage/emulated/0
        Log.e("zgf", "=====absolutePath1======" + absolutePath1);

        String path = Environment.getExternalStorageDirectory().getPath(); // /storage/emulated/0
        Log.e("zgf", "=====path======" + path);

        boolean b = Environment.MEDIA_MOUNTED.equalsIgnoreCase(Environment.getExternalStorageState()); // true
        Log.e("zgf", "=====b======" + b);

        String externalStorageState = Environment.getExternalStorageState(); // mounted
        Log.e("zgf", "=====externalStorageState======" + externalStorageState);

        File storageDirectory = Environment.getStorageDirectory(); // /storage
        Log.e("zgf", "=====storageDirectory======" + storageDirectory);

        String picturePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        Log.e("zgf", "=====picturePath======" + picturePath);

        String s = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xllive";
        File file = new File(s);
        Log.e("zgf", "========exists=========" + file.exists());

        File externalStorageDirectory1 = Environment.getExternalStorageDirectory();
        File storageDirectory1 = Environment.getStorageDirectory();
        File dataDirectory = Environment.getDataDirectory();
        File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
        String externalStorageState1 = Environment.getExternalStorageState();
        File rootDirectory = Environment.getRootDirectory();
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory("cache");
        Log.e("zgf", "=========" + externalStorageDirectory1
        + "=====" + storageDirectory1
        + "=====" + dataDirectory
        + "=====" + downloadCacheDirectory
        + "=====" + externalStorageState1
        + "=====" + rootDirectory
        + "=====" + externalStoragePublicDirectory
                ); // /storage/emulated/0=====/storage=====/data=====/data/cache=====mounted=====/system=====/storage/emulated/0/cache
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

    int num = 2;
    private void initView() {
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num ++;
                Log.e("zgf", "======num======" + num);
                wrapLayout.showChildrenNum(num);

//                test5();
//                RouteDispatch.getInstance().dispatch(MainActivity.this, "a", "data", new Route.CallBack() {
//                    @Override
//                    public void run(Object str) {
//                        Log.e("zgf", "=======onClick=====str==" + str);
//                    }
//                });
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

        findViewById(R.id.bt_trace_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TraceViewTestActivity.start(MainActivity.this);
            }
        });

        wrapLayout = findViewById(R.id.wrap_layout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String str = data.getStringExtra("data");
        Log.e("zgf", "========" + str);
        tvShow.setText(str);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("zgf", "====MainActivity====onPause=====");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("zgf", "===MainActivity====onStop======");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("zgf", "====MainActivity===onDestroy======");
    }

}