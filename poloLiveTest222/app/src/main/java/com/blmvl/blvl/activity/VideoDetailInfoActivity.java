package com.blmvl.blvl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.blmvl.blvl.R;
import com.blmvl.blvl.util.IntentUtil;

public class VideoDetailInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail_info);
    }

    /* renamed from: a */
    public static void m20690a(Context context) {
        IntentUtil.m9081a(context, VideoDetailInfoActivity.class);
    }
}