package com.slzhibo.library.ui.view.iview;

import com.slzhibo.library.base.BaseView;
import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.model.BoomStatusEntity;
import com.slzhibo.library.model.CheckTicketEntity;
import com.slzhibo.library.model.GiftDownloadItemEntity;
import com.slzhibo.library.model.GuardItemEntity;
import com.slzhibo.library.model.LiveInitInfoEntity;
import com.slzhibo.library.model.MyAccountEntity;
import com.slzhibo.library.model.OnLineUsersEntity;
import com.slzhibo.library.model.PKRecordEntity;
import com.slzhibo.library.model.PropConfigEntity;
import com.slzhibo.library.model.QMInteractTaskEntity;
import com.slzhibo.library.model.TaskBoxEntity;
import com.slzhibo.library.model.TrumpetStatusEntity;
import com.slzhibo.library.model.UserEntity;
import com.slzhibo.library.model.WSAddressEntity;
import com.slzhibo.library.model.db.GiftBoxEntity;

import java.util.List;

public interface ISLLiveView extends BaseView {
    void onAnchorInfoFail();

    void onAnchorInfoSuccess(AnchorEntity anchorEntity);

    void onAttentionSuccess();

    void onBoomStatusFail();

    void onBoomStatusSuccess(BoomStatusEntity boomStatusEntity);

    void onFragmentConfigSuccess(PropConfigEntity propConfigEntity);

    void onGiftBoxListSuccess(List<GiftBoxEntity> list);

    void onGiftListSuccess(List<GiftDownloadItemEntity> list);

    void onLiveAdListFail(String str);

    void onLiveAdListSuccess(String str, List<BannerEntity> list);

    void onLiveAdNoticeSuccess(BannerEntity bannerEntity);

    void onLiveAudiencesSuccess(OnLineUsersEntity onLineUsersEntity);

    void onLiveInitInfoFail(int i, String str);

    void onLiveInitInfoSuccess(String str, String str2, LiveInitInfoEntity liveInitInfoEntity, boolean z, boolean z2);

    void onLivePopularitySuccess(long j);

    void onPKLiveRoomFPSuccess(boolean z, boolean z2, PKRecordEntity pKRecordEntity);

    void onPersonalGuardInfoFail();

    void onPersonalGuardInfoSuccess(GuardItemEntity guardItemEntity);

    void onQMInteractShowTaskFail();

    void onQMInteractShowTaskSuccess(List<QMInteractTaskEntity> list);

    void onTaskChangeFail(TaskBoxEntity taskBoxEntity);

    void onTaskChangeSuccess(TaskBoxEntity taskBoxEntity);

    void onTaskListFail();

    void onTaskListSuccess(List<TaskBoxEntity> list, boolean z);

    void onTaskTakeFail();

    void onTaskTakeSuccess(TaskBoxEntity taskBoxEntity);

    void onTrumpetSendFail(int i);

    void onTrumpetSendSuccess();

    void onTrumpetStatusFail();

    void onTrumpetStatusSuccess(TrumpetStatusEntity trumpetStatusEntity);

    void onUseFragmentSuccess(PropConfigEntity propConfigEntity);

    void onUserCardInfoFail(int i, String str);

    void onUserCardInfoSuccess(UserEntity userEntity);

    void onUserCheckTicketFail(int i, String str);

    void onUserCheckTicketSuccess(CheckTicketEntity checkTicketEntity);

    void onUserOverFail();

    void onUserOverSuccess(MyAccountEntity myAccountEntity);

    void onWebSocketAddressFail();

    void onWebSocketAddressSuccess(WSAddressEntity wSAddressEntity);
}
