package com.slzhibo.library.model;

import android.text.TextUtils;

/* loaded from: classes8.dex */
public class IncomeMenuEntity {
    private String totalIncomePrice = "";
    private String giftIncomePrice = "";
    private String guardIncomePrice = "";
    private String propIncomePrice = "";
    private String nobilityIncomePrice = "";
    private String luckyGiftIncomePrice = "";
    private String scoreGiftIncomePrice = "";
    private String liveTicketIncomePrice = "";
    private String shelfProductIncomePrice = "";
    private String videoCallIncomePrice = "";

    public String getTotalIncomePrice() {
        return TextUtils.isEmpty(this.totalIncomePrice) ? "0" : this.totalIncomePrice;
    }

    public void setTotalIncomePrice(String str) {
        this.totalIncomePrice = str;
    }

    public String getGiftIncomePrice() {
        return TextUtils.isEmpty(this.giftIncomePrice) ? "0" : this.giftIncomePrice;
    }

    public void setGiftIncomePrice(String str) {
        this.giftIncomePrice = str;
    }

    public String getGuardIncomePrice() {
        return TextUtils.isEmpty(this.guardIncomePrice) ? "0" : this.guardIncomePrice;
    }

    public void setGuardIncomePrice(String str) {
        this.guardIncomePrice = str;
    }

    public String getPropsIncome() {
        return TextUtils.isEmpty(this.propIncomePrice) ? "0" : this.propIncomePrice;
    }

    public void setPropsIncome(String str) {
        this.propIncomePrice = str;
    }

    public String getNobilityIncomePrice() {
        return this.nobilityIncomePrice;
    }

    public String getLuckyGiftIncomePrice() {
        return TextUtils.isEmpty(this.luckyGiftIncomePrice) ? "0" : this.luckyGiftIncomePrice;
    }

    public String getScoreGiftIncomePrice() {
        return TextUtils.isEmpty(this.scoreGiftIncomePrice) ? "0" : this.scoreGiftIncomePrice;
    }

    public String getPaidLiveIncomePrice() {
        return TextUtils.isEmpty(this.liveTicketIncomePrice) ? "0" : this.liveTicketIncomePrice;
    }

    public String getProductIncomePrice() {
        return TextUtils.isEmpty(this.shelfProductIncomePrice) ? "0" : this.shelfProductIncomePrice;
    }

    public String getVideoCallIncomePrice() {
        return TextUtils.isEmpty(this.videoCallIncomePrice) ? "0" : this.videoCallIncomePrice;
    }

    public String toString() {
        return "IncomeMenuEntity{totalIncome='" + this.totalIncomePrice + "', giftIncome='" + this.giftIncomePrice + "', guardIncome='" + this.guardIncomePrice + "'}";
    }
}
