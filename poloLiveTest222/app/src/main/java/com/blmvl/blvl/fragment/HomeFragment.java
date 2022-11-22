package com.blmvl.blvl.fragment;

import android.view.View;

import com.blmvl.blvl.R;
import com.blmvl.blvl.ijkplayer.IjkVideoView;
import com.comod.baselib.fragment.AbsLazyFragment;
import com.slzhibo.library.utils.live.PlayManager;


public class HomeFragment extends AbsLazyFragment {
    IjkVideoView ijkVideo;
    private PlayManager playManager;
    @Override
    public int mo20158r() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        m20449b(view);
    }

    /* renamed from: b */
    public final void m20449b(View view) {
//        this.ijkVideo = view.findViewById(R.id.ijkVideo);
//        this.playManager = new PlayManager(view.getContext());
        IjkVideoView mIjkplayerView = new IjkVideoView(view.getContext());
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onLazyLoad() {

//        ijkVideo.switchStream("rtmp://zmpull2.wfxtbwgs.com/64839219/20220525124711433zvuumf?auth_key=1653454031-0-0-a87cdac17c11434395910fb1ee8392f6");
//        playManager.startPlayWithListener("rtmp://zmpull2.wfxtbwgs.com/64839219/20220525124711433zvuumf?auth_key=1653454031-0-0-a87cdac17c11434395910fb1ee8392f6");
//        ijkVideo.start();
    }
}