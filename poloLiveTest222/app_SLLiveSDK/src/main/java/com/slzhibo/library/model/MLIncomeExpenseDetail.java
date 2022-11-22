package com.slzhibo.library.model;

import android.content.Context;
import android.text.Spanned;

//import com.slzhibo.library.R;
//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.R;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.DateUtils;
import com.slzhibo.library.utils.NumberUtils;

/* loaded from: classes8.dex */
public class MLIncomeExpenseDetail extends IncomeEntity {
    public String userName = "";
    public String userAvatar = "";
    public String startTime = "";
    public String anchorName = "";
    public String anchorAvatar = "";
    public String expense = "";
    public String incomeSum = "";
    public String callTime = "0";

    @Override // com.slzhibo.library.model.IncomeEntity
    public int getIconImg() {
        return 0;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getAnchorIncomePrice() {
        return this.anchorIncomePrice;
    }

    public void setAnchorIncomePrice(String str) {
        this.anchorIncomePrice = str;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public String getAnchorName() {
        return this.anchorName;
    }

    public void setAnchorName(String str) {
        this.anchorName = str;
    }

    @Override // com.slzhibo.library.model.IncomeEntity
    public String getImgUrl() {
        return this.userAvatar;
    }

    @Override // com.slzhibo.library.model.IncomeEntity
    public String getImgUrl(boolean z) {
        return z ? this.anchorAvatar : this.userAvatar;
    }

    @Override // com.slzhibo.library.model.IncomeEntity
    public Spanned getFirstLine(Context context, boolean z) {
        return getHtmlSpanned(context, R.string.fq_ml_call_duration, DateUtils.secondToStringByDefault(NumberUtils.string2long(this.callTime)));
    }

    @Override // com.slzhibo.library.model.IncomeEntity
    public String getRecordTime() {
        return this.startTime;
    }

    @Override // com.slzhibo.library.model.IncomeEntity
    public String formatRecordTime(Context context, boolean z) {
        String str;
        StringBuilder sb = new StringBuilder();
        if (z) {
            str = this.anchorName;
        } else {
            str = this.userName;
        }
        sb.append(str);
        sb.append(ConstantUtils.PLACEHOLDER_STR_ONE);
        sb.append(DateUtils.formatSecondToDateFormat(getRecordTime(), DateUtils.C_DATE_PATTON_DATE_CHINA_11));
        return sb.toString();
    }

    @Override // com.slzhibo.library.model.IncomeEntity
    public String getCount(boolean z) {
        return z ? this.expense : this.incomeSum;
    }
}
