package com.example.test.keyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.baselibrary.view.InputDialog;
import com.example.test.R;

public class KeyBoardActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, KeyBoardActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_board);

        initView();
    }

    private void initView() {
        findViewById(R.id.bt_input).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyBoard();
            }
        });
    }

    private void openKeyBoard() {
        InputDialog dialog = new InputDialog(this);
        dialog.setOwnerActivity(this);
        dialog.show();
    }
}