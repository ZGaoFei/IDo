package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.baselibrary.communication.Route;
import com.example.baselibrary.communication.RouteDispatch;

public class MainActivity extends AppCompatActivity {

    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String str = data.getStringExtra("data");
        Log.e("zgf", "========" + str);
        tvShow.setText(str);
    }

}