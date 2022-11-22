package com.slzhibo.library.utils;

import java.io.File;

public class C6014k {

    /* renamed from: e */
    public static File m1341e(String str) {
        if (m1335k(str)) {
            return null;
        }
        return new File(str);
    }

    /* renamed from: k */
    public static boolean m1335k(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }




}
