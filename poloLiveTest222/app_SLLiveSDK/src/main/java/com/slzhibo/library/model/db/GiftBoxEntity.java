package com.slzhibo.library.model.db;

/* loaded from: classes8.dex */
public class GiftBoxEntity extends BaseDBEntity {
    public long expirationTime;
    public String giftBoxUniqueCode;
    public volatile long incrementTime;
    public String liveId;
    public long openTime;
    public String presenterAvatar;
    public String presenterId;
    public String presenterName;
    public String userId;

    public String toString() {
        return "GiftBoxEntity{, giftBoxUniqueCode='" + this.giftBoxUniqueCode + '}';
    }
}
