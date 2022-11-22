package com.slzhibo.library.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class C5970b0 {





    /* renamed from: c */
    public static List<String> m1617c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (str.contains(",")) {
            for (String str2 : TextUtils.split(str, ",")) {
                arrayList.add(str2);
            }
        } else {
            arrayList.add(str);
        }
        return arrayList;
    }



    /* renamed from: a */
    public static String m1622a(String str, int i) {
        if (TextUtils.isEmpty(str) || str.length() <= i) {
            return str;
        }
        String substring = str.substring(0, i);
        return substring + "...";
    }


    /* renamed from: a */
    public static String m1626a(int i) {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62)));
        }
        return sb.toString();
    }



}
