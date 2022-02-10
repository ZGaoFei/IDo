package com.example.test.traceview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.test.R;

import java.util.Date;

public class TraceViewTestActivity extends AppCompatActivity implements View.OnClickListener {
    String a;

    public static void start(Context context) {
        context.startActivity(new Intent(context, TraceViewTestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace_view_test);

        initView();
    }

    private void initView() {
        findViewById(R.id.bt_trace_method1).setOnClickListener(this);
        findViewById(R.id.bt_trace_method2).setOnClickListener(this);
        findViewById(R.id.bt_trace_method3).setOnClickListener(this);
        findViewById(R.id.bt_trace_method4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_trace_method1:
                method1();
                break;
            case R.id.bt_trace_method2:
                method2();
                break;
            case R.id.bt_trace_method3:
                method3();
                break;
            case R.id.bt_trace_method4:
                method4();
                break;
        }
    }

    private void method1() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("========" + i);
        }
    }

    private void method2() {
        SystemClock.sleep(1000);
    }

    private void method3() {
        int i = 0;
        for (int j = 0; j < 10000; j++) {
            i += j;
        }
        System.out.println("========" + i);
    }

    private void method4() {
        Toast.makeText(this, new Date().toString(), Toast.LENGTH_SHORT).show();
        char c = a.charAt(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("zgf", "========onPause=====");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("zgf", "=======onStop======");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("zgf", "=======onDestroy======");
    }
}