package com.slzhibo.library.utils;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.util.Date;

public class TimeUtils {

    /* renamed from: a */
    public static String m10219a(long j, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return dateFormat.format(new Date(j));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }


}
