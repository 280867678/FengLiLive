package com.blmvl.blvl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.blmvl.blvl.R;

public class CoinRechargeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_recharge);
    }

    /* renamed from: a */
    public static void m21240a(Context context) {
        context.startActivity(new Intent(context, CoinRechargeActivity.class));
    }


}