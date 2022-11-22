package com.blmvl.blvl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.blmvl.blvl.R;

public class AppCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_center);
    }


    /* renamed from: a */
    public static void m21268a(Context context) {
        context.startActivity(new Intent(context, AppCenterActivity.class));
    }


}