package com.slzhibo.library.model;

import android.content.Context;
import android.text.Spanned;

//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.R;
import com.slzhibo.library.R;
import com.slzhibo.library.utils.NumberUtils;

/* loaded from: classes8.dex */
public class ScoreGiftIncomeExpenseDetail extends GiftIncomeExpenseDetail {
    public String giftScore = "";
    public String popularValue = "";
    public String totalGold;

    @Override // com.slzhibo.library.model.GiftIncomeExpenseDetail, com.slzhibo.library.model.IncomeEntity
    public int getIconImg() {
        return 0;
    }

    @Override // com.slzhibo.library.model.GiftIncomeExpenseDetail, com.slzhibo.library.model.IncomeEntity
    public String getImgUrl() {
        return "";
    }

    @Override // com.slzhibo.library.model.GiftIncomeExpenseDetail, com.slzhibo.library.model.IncomeEntity
    public Spanned getFirstLine(Context context, boolean z) {
        String str = "x" + this.giftNum;
        if (NumberUtils.string2int(this.giftNum) <= 1) {
            str = "";
        }
        return z ? getHtmlSpanned(context, R.string.fq_score_gift_expend_tips, formatNickName(this.anchorName), this.giftName, str) : getHtmlSpanned(context, R.string.fq_score_gift_income_tips, formatNickName(this.userName), this.giftName, str);
    }

    @Override // com.slzhibo.library.model.GiftIncomeExpenseDetail, com.slzhibo.library.model.IncomeEntity
    public String getRecordTime() {
        return this.createTime;
    }

    @Override // com.slzhibo.library.model.GiftIncomeExpenseDetail, com.slzhibo.library.model.IncomeEntity
    public String getCount(boolean z) {
        return z ? String.valueOf(NumberUtils.string2long(this.giftScore) * NumberUtils.string2long(this.giftNum, 1L)) : this.popularValue;
    }
}
