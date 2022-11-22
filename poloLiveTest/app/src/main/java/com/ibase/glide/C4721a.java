package com.ibase.glide;

//package p067e.p260n.p261a.p263j;

import java.security.MessageDigest;

/* compiled from: AesCfbUtil.java */
/* renamed from: e.n.a.j.a */
/* loaded from: classes.dex */
public class C4721a {
    /* renamed from: a */
    public static byte[] m4933a(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length % 2 == 1) {
            return null;
        }
        int i = length / 2;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 != i; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = (byte) Integer.parseInt(str.substring(i3, i3 + 2), 16);
        }
        return bArr;
    }

    /* renamed from: a */
    public static byte[][] m4934a(int i, int i2, byte[] bArr, byte[] bArr2, int i3) throws Exception {
        byte[] digest;
        MessageDigest instance = MessageDigest.getInstance("md5");
        byte[] bArr3 = new byte[i];
        byte[] bArr4 = new byte[i2];
        byte[][] bArr5 = {bArr3, bArr4};
        if (bArr2 == null) {
            return bArr5;
        }
        byte[] bArr6 = null;
        int i4 = i2;
        int i5 = 0;
        int i6 = 0;
        int i7 = i;
        int i8 = 0;
        while (true) {
            instance.reset();
            int i9 = i8 + 1;
            if (i8 > 0) {
                instance.update(bArr6);
            }
            instance.update(bArr2);
            if (bArr != null) {
                instance.update(bArr, 0, 8);
            }
            digest = instance.digest();
            for (int i10 = 1; i10 < i3; i10++) {
                instance.reset();
                instance.update(digest);
                digest = instance.digest();
            }
            int i11 = 0;
            if (i7 > 0) {
                while (i7 != 0 && i11 != digest.length) {
                    bArr3[i5] = digest[i11];
                    i7--;
                    i11++;
                    i5++;
                }
            }
            if (i4 > 0 && i11 != digest.length) {
                while (i4 != 0 && i11 != digest.length) {
                    bArr4[i6] = digest[i11];
                    i4--;
                    i11++;
                    i6++;
                }
            }
            if (i7 == 0 && i4 == 0) {
                break;
            }
            i8 = i9;
            bArr6 = digest;
        }
        for (int i12 = 0; i12 < digest.length; i12++) {
            digest[i12] = 0;
        }
        return bArr5;
    }
}

