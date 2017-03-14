package com.samyotech.testofindia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.samyotech.testofindia.R;

public class SplaseActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();


    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splase);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent in = new Intent(SplaseActivity.this, MainActivity.class);
                startActivity(in);
                finish();

            }
        }, SPLASH_TIME_OUT);
    }
}
