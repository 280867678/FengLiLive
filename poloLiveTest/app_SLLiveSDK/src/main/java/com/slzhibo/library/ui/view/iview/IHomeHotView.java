package com.slzhibo.library.ui.view.iview;

import com.slzhibo.library.base.BaseView;
import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.model.IndexRankEntity;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.model.LiveHelperAppConfigEntity;

import java.util.List;

/* renamed from: com.slzhibo.library.ui.view.iview.IHomeHotView */
/* loaded from: classes6.dex */
public interface IHomeHotView extends BaseView {
    void onAnchorAuthSuccess(AnchorEntity anchorEntity);

    void onBannerListSuccess(List<BannerEntity> list);

    void onDataListFail(boolean z);

    void onDataListSuccess(List<LiveEntity> list, boolean z, boolean z2);

    void onLiveHelperAppConfigFail();

    void onLiveHelperAppConfigSuccess(LiveHelperAppConfigEntity liveHelperAppConfigEntity);

    void onTopListSuccess(List<IndexRankEntity> list);




    /* renamed from: a */

//    /**
//     * onAnchorAuthSuccess
//     * @param anchorEntity
//     */
//    void mo2978a(AnchorEntity anchorEntity);
//
//    /* renamed from: a */
//
//    /**
//     * onLiveHelperAppConfigSuccess
//     * @param liveHelperAppConfigEntity
//     */
//    void mo2977a(LiveHelperAppConfigEntity liveHelperAppConfigEntity);
//
//    /* renamed from: a */
//
//    /**
//     * onDataListSuccess
//     * @param list
//     * @param z
//     * @param z2
//     */
//    void mo2976a(List<LiveEntity> list, boolean z, boolean z2);
//
//    /* renamed from: a */
//
//    /**
//     * onDataListFail
//     * @param z
//     */
//    void mo2975a(boolean z);
//
//    /* renamed from: e */
//
//    /**
//     * onLiveHelperAppConfigFail
//     */
//    void mo2974e();
//
//    /* renamed from: o */
//
//    /**
//     * onBannerListSuccess
//     * @param list
//     */
//    void mo2973o(List<BannerEntity> list);
//
//    /* renamed from: p */
//
//    /**
//     * onTopListSuccess
//     * @param list
//     */
//    void mo2972p(List<IndexRankEntity> list);


}
