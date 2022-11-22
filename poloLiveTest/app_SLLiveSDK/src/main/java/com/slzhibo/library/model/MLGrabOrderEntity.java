package com.slzhibo.library.model;

import java.util.List;

/* loaded from: classes8.dex */
public class MLGrabOrderEntity {
    public OrderQuotaEntity quota;
    public List<MLAnchorEntity> userList;

    /* loaded from: classes8.dex */
    public static class OrderQuotaEntity {
        public String dailyQuota = "0";
        public String expendQuota = "0";
    }

    public boolean isEmptyUserList() {
        List<MLAnchorEntity> list = this.userList;
        return list == null || list.isEmpty();
    }

    public String getDailyQuota() {
        OrderQuotaEntity orderQuotaEntity = this.quota;
        return orderQuotaEntity == null ? "0" : orderQuotaEntity.dailyQuota;
    }

    public String getExpendQuota() {
        return (this.quota == null || isEmptyUserList()) ? "0" : this.quota.expendQuota;
    }
}
