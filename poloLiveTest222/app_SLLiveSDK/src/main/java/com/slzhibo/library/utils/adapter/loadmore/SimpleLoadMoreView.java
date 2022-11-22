package com.slzhibo.library.utils.adapter.loadmore;


//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.R;
/* loaded from: classes6.dex */
public final class SimpleLoadMoreView extends LoadMoreView {
    @Override // com.slzhibo.library.utils.adapter.loadmore.LoadMoreView
    public int getLayoutId() {
        return R.layout.fq_brvah_quick_view_load_more;
    }

    @Override // com.slzhibo.library.utils.adapter.loadmore.LoadMoreView
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override // com.slzhibo.library.utils.adapter.loadmore.LoadMoreView
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override // com.slzhibo.library.utils.adapter.loadmore.LoadMoreView
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}

