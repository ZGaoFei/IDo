package com.example.blibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;


public class BFirstActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_first);

        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        String url = intent.getStringExtra("url");
        TextView textView = findViewById(R.id.tv_show);
        textView.setText("传过来的数据为：data=" + data + " url=" + url);

        findViewById(R.id.bt_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zgf", "======回传值======");
//                Intent result = new Intent();
//                result.putExtra("data", "hello world!");
//                setResult(RESULT_OK, result);
//
//                finish();
                frameLayout.setVisibility(View.VISIBLE);
                test();
            }
        });

        frameLayout = findViewById(R.id.fl_animation);
    }

    private void test() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.annual_ceremony_start);
        frameLayout.startAnimation(animation);
    }
}