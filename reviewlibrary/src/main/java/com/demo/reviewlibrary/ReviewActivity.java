package com.demo.reviewlibrary;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class ReviewActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, ReviewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void test() {
        new ArrayList<>();
        new LinkedList<>();

        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("a");
        for (String s : hashSet) {
            Log.e("test", s);
        }
        new TreeSet<>();

        new HashMap<>();

        new ArrayMap<>();
    }
}