package com.slzhibo.library.model;

import android.text.TextUtils;

import com.slzhibo.library.utils.UserInfoManager;

import java.util.List;

/* loaded from: classes8.dex */
public class HdDrawEndEntity {
    public List<WinningNamesEntity> winningUserList;
    public String userCount = "0";
    public String giftCount = "0";
    public String partFlag = "0";

    /* loaded from: classes8.dex */
    public static class WinningNamesEntity {
        public String userGrade;
        public String userId;
        public String userName;
        public String winningRecordId;
    }

    public boolean isOneselfWinning() {
        WinningNamesEntity winningNamesEntity;
        List<WinningNamesEntity> list = this.winningUserList;
        return list != null && !list.isEmpty() && (winningNamesEntity = this.winningUserList.get(0)) != null && TextUtils.equals(winningNamesEntity.userId, UserInfoManager.getInstance().getUserId());
    }

    public String getWinningRecordId() {
        WinningNamesEntity winningNamesEntity;
        List<WinningNamesEntity> list = this.winningUserList;
        return (list == null || list.isEmpty() || (winningNamesEntity = this.winningUserList.get(0)) == null || !TextUtils.equals(winningNamesEntity.userId, UserInfoManager.getInstance().getUserId())) ? "" : winningNamesEntity.winningRecordId;
    }

    public boolean isPartFlag() {
        return TextUtils.equals("1", this.partFlag);
    }

    public String getWinningUserNum() {
        List<WinningNamesEntity> list = this.winningUserList;
        return (list == null || list.isEmpty()) ? "0" : String.valueOf(this.winningUserList.size());
    }
}
