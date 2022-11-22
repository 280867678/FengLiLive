package com.slzhibo.library.model;

import com.slzhibo.library.http.bean.HttpResultPageModel;

/* loaded from: classes8.dex */
public class ReceiveGiftRecordPageEntity {
    public HttpResultPageModel<ReceiveGiftRecordEntity> page;
    public GiftStatisticsEntity statis;

    /* loaded from: classes8.dex */
    public static class GiftStatisticsEntity {
        public long payMemberCount;
        public String totalPracticalPrice;
    }
}
