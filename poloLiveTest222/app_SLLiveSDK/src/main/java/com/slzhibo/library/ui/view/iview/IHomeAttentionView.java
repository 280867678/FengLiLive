package com.slzhibo.library.ui.view.iview;

import com.slzhibo.library.base.BaseView;
import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.model.LiveEntity;

import java.util.List;

public interface IHomeAttentionView extends BaseView {
    void onAttentionListFail(boolean z);

    void onAttentionListSuccess(List<LiveEntity> list, boolean z, boolean z2, boolean z3, int i);

    void onAttentionSuccess();

    void onRecommendListFail();

    void onRecommendListSuccess(List<AnchorEntity> list);
}
