package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.baselibrary.sendobj.Route;
import com.example.baselibrary.sendobj.RouteDispatch;

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
                RouteDispatch.getInstance().dispatch(MainActivity.this, "data", "a", new Route.CallBack() {
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
                RouteDispatch.getInstance().dispatch(MainActivity.this, "data", "b", new Route.CallBack() {
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
                RouteDispatch.getInstance().dispatch(MainActivity.this, "data", "b_async", new Route.CallBack() {
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
                RouteDispatch.getInstance().dispatch(MainActivity.this, "data", "b_back", new Route.CallBack() {
                    @Override
                    public void run(Object str) {
                        tvShow.setText((String) str);
                    }
                });
            }
        });

        tvShow = findViewById(R.id.tv_app_show);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String str = data.getStringExtra("data");
        Log.e("zgf", "========" + str);
        tvShow.setText(str);
    }

}