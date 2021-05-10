package com.example.alibrary;

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

public class AFirstActivity extends AppCompatActivity {

    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_first);

        initView();
    }

    private void initView() {
        findViewById(R.id.bt_get_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteDispatch.getInstance().dispatch(AFirstActivity.this, "b", "a first activity", new Route.CallBack() {
                    @Override
                    public void run(Object str) {
                        Log.e("zgf", "====str=====" + str);
                    }
                });
            }
        });

        findViewById(R.id.bt_get_obj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteDispatch.getInstance().dispatch(AFirstActivity.this, "b_obj","data",  new Route.CallBack() {
                    @Override
                    public void run(Object obj) {
                        if (obj instanceof Fragment) {
                            Fragment fragment = (Fragment) obj;
                            getSupportFragmentManager().beginTransaction().add(R.id.fl_container, fragment).commitNow();
                        }
                    }
                });
            }
        });

        findViewById(R.id.bt_b_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteDispatch.getInstance().dispatch(AFirstActivity.this, "b_back","data",  null);
            }
        });

        tvShow = findViewById(R.id.tv_a_show);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String str = data.getStringExtra("data");
        Log.e("zgf", "========" + str);
        tvShow.setText(str);
    }
}