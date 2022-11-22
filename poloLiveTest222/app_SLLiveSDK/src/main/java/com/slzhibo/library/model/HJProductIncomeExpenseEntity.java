package com.slzhibo.library.model;

import android.content.Context;
import android.text.Spanned;

import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.DateUtils;

/* loaded from: classes8.dex */
public class HJProductIncomeExpenseEntity extends IncomeEntity {
    private String avatar = "";
    private String userName = "";
    private String productName = "";
    private String createTime = "";
    private String shopIncomePrice = "0";
    private String tag = "";

    @Override // com.slzhibo.library.model.IncomeEntity
    public int getIconImg() {
        return 0;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public String getAnchorIncomePrice() {
        return this.anchorIncomePrice;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public String getShopIncomePrice() {
        return this.shopIncomePrice;
    }

    public void setShopIncomePrice(String str) {
        this.shopIncomePrice = str;
    }

    @Override // com.slzhibo.library.model.IncomeEntity
    public String getImgUrl() {
        return this.avatar;
    }

    @Override // com.slzhibo.library.model.IncomeEntity
    public Spanned getFirstLine(Context context, boolean z) {
        return getHtmlSpanned(this.productName);
    }

    @Override // com.slzhibo.library.model.IncomeEntity
    public String getRecordTime() {
        return this.createTime;
    }

    @Override // com.slzhibo.library.model.IncomeEntity
    public String getCount(boolean z) {
        return z ? this.price : this.shopIncomePrice;
    }

    @Override // com.slzhibo.library.model.IncomeEntity
    public String formatRecordTime(Context context, boolean z) {
        String str;
        StringBuilder sb = new StringBuilder();
        if (z) {
            str = this.tag;
        } else {
            str = this.userName;
        }
        sb.append(str);
        sb.append(ConstantUtils.PLACEHOLDER_STR_ONE);
        sb.append(DateUtils.formatSecondToDateFormat(getRecordTime(), DateUtils.C_DATE_PATTON_DATE_CHINA_11));
        return sb.toString();
    }
}
