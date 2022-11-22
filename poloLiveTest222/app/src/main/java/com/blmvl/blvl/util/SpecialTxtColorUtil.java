package com.blmvl.blvl.util;

//package p067e.p103c.p104a.p113k;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import com.blmvl.blvl.bean.SpecialTxtIndexBean;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: e.c.a.k.c0 */
/* loaded from: classes.dex */
public class SpecialTxtColorUtil {
    /* renamed from: a */
    public static SpannableStringBuilder m9513a(int i, String str, String str2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        Matcher matcher = Pattern.compile("(?i)" + str2).matcher(spannableStringBuilder);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            spannableStringBuilder.setSpan(new ForegroundColorSpan(i), start, end, 33);
            spannableStringBuilder.setSpan(new StyleSpan(1), start, end, 34);
        }
        return spannableStringBuilder;
    }

    /* renamed from: a */
    public static SpannableStringBuilder m9512a(String str, int i) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        try {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < str.length(); i2++) {
                if (String.valueOf(str.charAt(i2)).equals("【") || String.valueOf(str.charAt(i2)).equals("】")) {
                    arrayList.add(Integer.valueOf(i2));
                }
            }
            ArrayList arrayList2 = new ArrayList();
            if (!arrayList.isEmpty() && arrayList.size() % 2 == 0) {
                for (int i3 = 0; i3 < arrayList.size(); i3 += 2) {
                    SpecialTxtIndexBean specialTxtIndexBean = new SpecialTxtIndexBean();
                    specialTxtIndexBean.setStart(((Integer) arrayList.get(i3)).intValue());
                    specialTxtIndexBean.setEnd(((Integer) arrayList.get(i3 + 1)).intValue());
                    arrayList2.add(specialTxtIndexBean);
                }
            }
            if (!arrayList2.isEmpty()) {
                for (int i4 = 0; i4 < arrayList2.size(); i4++) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(i), ((SpecialTxtIndexBean) arrayList2.get(i4)).getStart(), ((SpecialTxtIndexBean) arrayList2.get(i4)).getEnd() + 1, 33);
                    spannableStringBuilder.setSpan(new StyleSpan(1), ((SpecialTxtIndexBean) arrayList2.get(i4)).getStart(), ((SpecialTxtIndexBean) arrayList2.get(i4)).getEnd() + 1, 34);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spannableStringBuilder;
    }
}

