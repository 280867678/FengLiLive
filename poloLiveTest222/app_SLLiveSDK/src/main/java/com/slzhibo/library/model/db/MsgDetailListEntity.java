package com.slzhibo.library.model.db;

import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/* loaded from: classes8.dex */
public class MsgDetailListEntity extends BaseDBEntity implements MultiItemEntity {
    public String messageId;
    public String msg;
    public int status;
    public String targetAvatar;
    public String targetId;
    public String targetName;
    public String time;
    public int type;
    public String userId;
    public String winningFlag = "0";
    public String flagContent = "";

    @Override // com.slzhibo.library.utils.adapter.entity.MultiItemEntity
    public int getItemType() {
        return this.type;
    }

    public boolean isRedLabelFlag() {
        return TextUtils.equals(this.winningFlag, "1");
    }

    public String toString() {
        return "MsgDetailListEntity{msg='" + this.msg + "', messageId='" + this.messageId + "', status=" + this.status + '}';
    }
}
