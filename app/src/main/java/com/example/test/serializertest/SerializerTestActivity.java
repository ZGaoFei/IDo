package com.example.test.serializertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.baselibrary.serializer.Serializer;
import com.example.test.R;

public class SerializerTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializer_test);

        initView();
    }

    private void initView() {
        findViewById(R.id.bt_serializer_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serializerTest();
            }
        });
    }

    private void serializerTest() {
        Serializer.op(new Serializer.Op<String>() {
            @Override
            public void onNext(Serializer serializer, String str) {
                Log.e("zgf", "======111=====" + str + "===" + Thread.currentThread());
                serializer.next();
            }
        }).addOp(new Serializer.AsyncOp() {
            @Override
            public void onNext(Serializer serializer, Object o) {
                Log.e("zgf", "======222=====" + Thread.currentThread());
                serializer.next("我爱这个国，可是谁爱我呢！");
            }
        }).addOp(new Serializer.Op<String>() {
            @Override
            public void onNext(Serializer serializer, String str) {
                Log.e("zgf", "======222-111=====" + str + "====" + Thread.currentThread());
                serializer.next(str);
            }
        }).addOp(new Serializer.MainOp<String>() {
            @Override
            public void onNext(Serializer serializer, String str) {
                Log.e("zgf", "======333=====" + str + "====" + Thread.currentThread());
                serializer.next(str, "no ", "people");
            }
        }).addOp(new Serializer.Op<Object[]>() {
            @Override
            public void onNext(Serializer serializer, Object[] o) {
                Log.e("zgf", "======333-111=====" + Thread.currentThread());
                serializer.next(o);
            }
        }).addOp(new Serializer.AsyncOp<Object[]>() {
            @Override
            public void onNext(Serializer serializer, Object[] o) {
                Log.e("zgf", "======444-111=====" + Thread.currentThread());
                String result = "";
                for (int i = 0; i < o.length; i++) {
                    Object oi = o[i];
                    result += oi;
                }
                Log.e("zgf", "======444-222=====" + result);
            }
        }).next("hello world!");
    }
}