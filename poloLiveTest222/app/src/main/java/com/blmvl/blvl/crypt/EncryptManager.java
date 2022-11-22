package com.blmvl.blvl.crypt;

//package p067e.p103c.p104a.p113k;

import android.text.TextUtils;
import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
//import p067e.p130f.p131a.p135d.AesCfbUtil;
//import p067e.p130f.p131a.p135d.C4324h0;
//import p067e.p130f.p131a.p135d.MD5Util;

/* renamed from: e.c.a.k.q */
/* loaded from: classes.dex */
public class EncryptManager {

    /* renamed from: a */
    public static final String f13051a = "q";

    /* renamed from: b */
    public static volatile EncryptManager f13052b;

    public EncryptManager() {
        try {
            Cipher.getInstance("AES/CFB/NoPadding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: a */
    public static EncryptManager m9436a() {
        if (f13052b == null) {
            synchronized (EncryptManager.class) {
                if (f13052b == null) {
                    f13052b = new EncryptManager();
                }
            }
        }
        return f13052b;
    }

    /* renamed from: b */
    public final void m9434b(String str) {
        PrintStream printStream = System.out;
        printStream.print(f13051a + " log=" + str);
    }

    /* renamed from: c */
    public String m9433c(String str) {
        return TextUtils.isEmpty(str) ? "" : m9435a(AesCfbUtil.m9223a(str));
    }

    /* renamed from: a */
    public String m9435a(String str) {
        if (C4324h0.m9132a(str)) {
            return null;
        }
        m9434b(str);
        return MD5Util.m9074a(str);
    }
}

