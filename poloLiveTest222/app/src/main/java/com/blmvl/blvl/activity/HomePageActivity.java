package com.blmvl.blvl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.blmvl.blvl.R;
import com.blmvl.blvl.util.IntentUtil;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    /* renamed from: a */
    public static void m21102a(Context context, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("uid", i);
        IntentUtil.m9080a(context, HomePageActivity.class, bundle);
    }

}