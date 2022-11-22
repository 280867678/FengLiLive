package com.slzhibo.library.model;

import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.NumberUtils;
import com.slzhibo.library.utils.UserInfoManager;
//import com.slzhibo.library.websocket.nvwebsocket.ConnectSocketParams;

import java.util.List;

/* loaded from: classes8.dex */
public class AnchorStartLiveEntity extends LiveEntity {
    public List<String> markUrls;
    public EnterMessageEntity myselfEnterMessageDto;

    public SocketMessageEvent formatMyselfEnterMessageEvent() {
        SocketMessageEvent socketMessageEvent = new SocketMessageEvent();
        socketMessageEvent.code = "1";
        socketMessageEvent.messageType = "entry";
        SocketMessageEvent.ResultData resultData = new SocketMessageEvent.ResultData();
        resultData.userId = UserInfoManager.getInstance().getUserId();
        resultData.userName = UserInfoManager.getInstance().getUserNickname();
        resultData.avatar = UserInfoManager.getInstance().getAvatar();
        resultData.sex = UserInfoManager.getInstance().getUserSex();
        resultData.appId = UserInfoManager.getInstance().getAppId();
        resultData.openId = UserInfoManager.getInstance().getAppOpenId();
        resultData.isEnterHide = UserInfoManager.getInstance().isEnterHide() ? 1 : 0;
        resultData.role = "1";
        resultData.expGrade = this.expGrade;
        resultData.markUrls = this.markUrls;
        EnterMessageEntity enterMessageEntity = this.myselfEnterMessageDto;
        if (enterMessageEntity != null) {
            resultData.userRole = enterMessageEntity.userRole;
            resultData.nobilityType = NumberUtils.string2int(enterMessageEntity.nobilityType);
            EnterMessageEntity enterMessageEntity2 = this.myselfEnterMessageDto;
            resultData.isPlayNobilityEnterAnimation = enterMessageEntity2.isPlayNobilityEnterAnimation;
            if (AppUtils.hasCar(enterMessageEntity2.carId)) {
                EnterMessageEntity enterMessageEntity3 = this.myselfEnterMessageDto;
                resultData.carId = enterMessageEntity3.carId;
                resultData.carName = enterMessageEntity3.carName;
                resultData.carOnlineUrl = enterMessageEntity3.carOnlineUrl;
                resultData.carIcon = enterMessageEntity3.carIcon;
                resultData.isPlayCarAnim = enterMessageEntity3.isPlayCarAnim;
            }
        }
        socketMessageEvent.resultData = resultData;
        return socketMessageEvent;
    }
}
