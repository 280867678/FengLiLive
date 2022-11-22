package com.blmvl.blvl.crypt;



import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/* compiled from: StringUtil.java */
/* renamed from: e.f.a.d.h0 */
/* loaded from: classes.dex */
public class C4324h0 {

    /* renamed from: a */
    public static DecimalFormat f13199a = new DecimalFormat("#.#");

    /* renamed from: b */
    public static DecimalFormat f13200b = new DecimalFormat("#.##");

    static {
        f13199a.setRoundingMode(RoundingMode.HALF_UP);
        f13200b.setRoundingMode(RoundingMode.DOWN);
        Pattern.compile("^[-\\+]?[\\d]*$");
    }

    /* renamed from: a */
    public static boolean m9132a(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
}

