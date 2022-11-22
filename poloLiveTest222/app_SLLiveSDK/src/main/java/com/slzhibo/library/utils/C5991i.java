package com.slzhibo.library.utils;

import android.text.format.DateFormat;

import java.util.Date;


import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.DateFormat;
import com.slzhibo.library.R;
//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.utils.NumberUtils;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;


/* compiled from: DateUtils.java */
/* renamed from: e.t.a.i.i */
/* loaded from: classes2.dex */
public class C5991i {

    /* renamed from: a */
    public static String f20089a = "yyyy-MM-dd";

    /* renamed from: b */
    public static String f20090b = "yyyy-MM-dd HH:mm:ss";

    /* renamed from: c */
    public static StringBuilder f20091c = new StringBuilder();

    /* renamed from: d */
    public static Formatter f20092d = new Formatter(f20091c, Locale.getDefault());

    /* renamed from: a */
    public static String m1489a(Date date, String str) {
        return new SimpleDateFormat(str).format(date);
    }

    /* renamed from: b */
    public static String m1484b(String str) {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(f20090b);
        instance.add(13, NumberUtils.string2int(str));
        return simpleDateFormat.format(instance.getTime());
    }

    /* renamed from: c */
    public static String m1481c(long j) {
        long j2 = j / 1000;
        long j3 = j2 % 60;
        long j4 = (j2 / 60) % 60;
        long j5 = j2 / 3600;
        f20091c.setLength(0);
        return j5 > 0 ? f20092d.format("%d'%02d'%02d'", Long.valueOf(j5), Long.valueOf(j4), Long.valueOf(j3)).toString() : f20092d.format("%02d'%02d'", Long.valueOf(j4), Long.valueOf(j3)).toString();
    }

    /* renamed from: d */
    public static String m1478d(long j) {
        f20091c.setLength(0);
        return f20092d.format("%02d:%02d", Long.valueOf(j / 60), Long.valueOf(j % 60)).toString();
    }

    /* renamed from: e */
    public static String m1475e(long j) {
        long j2 = j % 60;
        long j3 = (j / 60) % 60;
        long j4 = j / 3600;
        f20091c.setLength(0);
        return j4 > 0 ? f20092d.format("%d:%02d:%02d", Long.valueOf(j4), Long.valueOf(j3), Long.valueOf(j2)).toString() : f20092d.format("%02d:%02d", Long.valueOf(j3), Long.valueOf(j2)).toString();
    }

    /* renamed from: f */
    public static String m1473f(long j) {
        long j2 = j / 1000;
        long j3 = j2 % 60;
        long j4 = (j2 / 60) % 60;
        long j5 = j2 / 3600;
        f20091c.setLength(0);
        return j5 > 0 ? f20092d.format("%d:%02d:%02d", Long.valueOf(j5), Long.valueOf(j4), Long.valueOf(j3)).toString() : f20092d.format("%02d:%02d", Long.valueOf(j4), Long.valueOf(j3)).toString();
    }

    /* renamed from: a */
    public static Date m1490a(Date date, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(5, i);
        return instance.getTime();
    }

    /* renamed from: d */
    public static Date m1476d(String str, String str2) {
        Date date = new Date();
        try {
            return new SimpleDateFormat(str2).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }

    /* renamed from: b */
    public static String m1487b(long j) {
        if (j == 0) {
            return "";
        }
        Date date = new Date(j);
        Date date2 = new Date();
        long time = date2.getTime() - date.getTime();
        int a = m1488a(date, new Date());
        if (time <= 600000) {
            return SystemUtils.m1599a(R.string.fq_just_now);
        }
        if (time < 3600000) {
            return (time / 60000) + SystemUtils.m1599a(R.string.fq_minute_ago);
        } else if (a == 0) {
            return (time / 3600000) + SystemUtils.m1599a(R.string.fq_hour_ago);
        } else if (a == -1) {
            return (time / 3600000) + SystemUtils.m1599a(R.string.fq_hour_ago);
        } else if (!m1482b(date, date2) || a >= -1) {
            return DateFormat.format("yyyy-MM-dd", date).toString();
        } else {
            return (time / 86400000) + SystemUtils.m1599a(R.string.fq_day_ago);
        }
    }

    /* renamed from: a */
    public static long m1493a(Object obj, Object obj2) {
        Date a = m1494a(obj);
        Date a2 = m1494a(obj2);
        return (a2.getTime() - a.getTime()) / 86400000;
    }

    /* renamed from: e */
    public static String m1474e(String str) {
        long string2long = NumberUtils.string2long(str) * 1000;
        if (string2long == 0) {
            return "";
        }
        return new SimpleDateFormat(f20090b).format(new Date(string2long));
    }

    /* renamed from: c */
    public static String m1480c(String str) {
        return new SimpleDateFormat(str).format(new Date());
    }

    /* renamed from: f */
    public static Date m1472f(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        try {
            return new SimpleDateFormat(f20089a).parse(str);
        } catch (ParseException unused) {
            return m1476d(str, "yyyyMMdd");
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    /* renamed from: d */
    public static SimpleDateFormat m1477d(String str) {
        if (str == null) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return new SimpleDateFormat(str);
    }

    /* renamed from: c */
    public static String m1479c(String str, String str2) {
        return m1491a(str, str2);
    }

    /* renamed from: a */
    public static Date m1494a(Object obj) {
        if (obj instanceof Date) {
            return (Date) obj;
        }
        if (obj instanceof String) {
            return m1472f((String) obj);
        }
        return new Date();
    }

    /* renamed from: a */
    public static String m1497a(long j) {
        String str;
        long j2 = j / TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC;
        long j3 = (j % TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC) / 3600;
        long j4 = (j % 3600) / 60;
        long j5 = j % 60;
        if (j2 > 0) {
            str = j2 + "天";
        } else if (j3 > 0) {
            str = j3 + "小时";
        } else if (j4 > 0) {
            str = j4 + "分钟";
        } else {
            str = j5 + "秒";
        }
        return "剩余" + str;
    }

    /* renamed from: b */
    public static boolean m1482b(Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(1);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return i == instance2.get(1);
    }

    /* renamed from: b */
    public static Date m1483b(String str, String str2) {
        if (str == null) {
            return null;
        }
        if (str2 == null || str2.equals("")) {
            str2 = "yyyy-MM-dd HH:mm:ss";
        }
        return m1477d(str2).parse(str, new ParsePosition(0));
    }

    /* renamed from: a */
    public static int m1488a(Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(6);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return i - instance2.get(6);
    }

    /* renamed from: b */
    public static String m1485b(Context context, long j) {
        if (context == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        long timeInMillis = instance.getTimeInMillis();
        String[] stringArray = context.getResources().getStringArray(R.array.fq_date_time_format_label_tips);
        if (j >= timeInMillis) {
            return stringArray[0];
        }
        if (j >= timeInMillis - 86400000) {
            return stringArray[1];
        }
        if (m1482b(new Date(timeInMillis), new Date(j))) {
            return new SimpleDateFormat("MM月dd日").format(new Date(j));
        }
        return simpleDateFormat.format(new Date(j));
    }

    /* renamed from: a */
    public static Calendar m1492a(String str) {
        Date b = m1483b(str, "yyyy-MM-dd");
        Calendar instance = Calendar.getInstance();
        instance.setTime(b);
        return instance;
    }

    /* renamed from: a */
    public static String m1495a(Context context, long j) {
        if (context == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        long timeInMillis = instance.getTimeInMillis();
        String[] stringArray = context.getResources().getStringArray(R.array.fq_date_time_format_tips);
        if (j >= timeInMillis) {
            return String.format(stringArray[0], Long.valueOf(j));
        }
        if (j >= timeInMillis - 86400000) {
            return String.format(stringArray[1], Long.valueOf(j));
        }
        long j2 = timeInMillis - 172800000;
        return j >= j2 ? String.format(stringArray[2], Long.valueOf(j)) : (j >= j2 || j < timeInMillis - 1471228928) ? String.format(stringArray[3], Long.valueOf(j)) : simpleDateFormat.format(new Date(j));
    }

    /* renamed from: b */
    public static String m1486b(long j, String str) {
        return m1496a(j * 1000, str);
    }

    /* renamed from: a */
    public static String m1491a(String str, String str2) {
        return m1486b(NumberUtils.string2long(str), str2);
    }

    /* renamed from: a */
    public static String m1496a(long j, String str) {
        return TimeUtils.m10219a(j, new SimpleDateFormat(str));
    }
}

