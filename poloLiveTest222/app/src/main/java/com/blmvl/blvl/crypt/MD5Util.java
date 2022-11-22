package com.blmvl.blvl.crypt;


import java.security.MessageDigest;

/* renamed from: e.f.a.d.w */
/* loaded from: classes.dex */
public class MD5Util {

    /* renamed from: a */
    public static char[] f13212a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: a */
    public static String m9074a(String str) {
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bytes);
            byte[] digest = instance.digest();
            char[] cArr = new char[digest.length * 2];
            int i = 0;
            for (byte b : digest) {
                int i2 = i + 1;
                cArr[i] = f13212a[(b >>> 4) & 15];
                i = i2 + 1;
                cArr[i2] = f13212a[b & 15];
            }
            return new String(cArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

