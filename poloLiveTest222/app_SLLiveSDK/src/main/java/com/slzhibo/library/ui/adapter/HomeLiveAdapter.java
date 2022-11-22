package com.slzhibo.library.ui.adapter;

//import android.support.v4.app.Fragment;
import androidx.fragment.app.Fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.utils.live.HomeLiveAdapterUtils;

public class HomeLiveAdapter extends BaseQuickAdapter<LiveEntity, BaseViewHolder> {
    public HomeLiveAdapter(int i) {
        super(i);
    }

    public HomeLiveAdapter(Fragment fragment, int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, LiveEntity liveEntity) {
        HomeLiveAdapterUtils.convert(this.mContext, baseViewHolder, liveEntity);
    }
}
