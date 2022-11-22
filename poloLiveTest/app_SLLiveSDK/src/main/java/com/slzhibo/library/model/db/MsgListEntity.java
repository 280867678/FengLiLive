package com.slzhibo.library.model.db;

import com.slzhibo.library.utils.DBUtils;

/* loaded from: classes8.dex */
public class MsgListEntity extends BaseDBEntity {
    public String appId;
    public String targetId;
    public String time;
    public String userId;

    public MsgDetailListEntity getLastMsgDetailListEntity() {
        return (MsgDetailListEntity) DBUtils.selectOneItemByOrder(MsgDetailListEntity.class, "time desc", "userId = ? and targetId = ?", this.userId, this.targetId);
    }
}
