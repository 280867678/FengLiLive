package com.blmvl.blvl.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blmvl.blvl.R;
import com.bumptech.glide.Glide;


public class HomeFragment extends AbsLazyFragment {
    public ImageView f1189f;


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int mo20158r() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        this.f1189f =  view.findViewById(R.id.imageView);
    }

    @Override
    public void onLazyLoad() {
//        Glide.with(getContext()).load("http://pic0.iqiyipic.com/image/20220418/0c/c4/a_100453685_m_601_m4_480_270.jpg").into(this.f1189f);
    }
}