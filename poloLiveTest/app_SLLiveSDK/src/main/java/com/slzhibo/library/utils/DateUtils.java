package com.slzhibo.library.utils;

import android.text.format.DateFormat;

import com.blankj.utilcode.util.TimeUtils;
//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

public class DateUtils {


    public static final String C_DATE_PATTON_DATE_CHINA_1 = "yyyy年MM月";
    public static final String C_DATE_PATTON_DATE_CHINA_10 = "MM-dd";
    public static final String C_DATE_PATTON_DATE_CHINA_11 = "MM-dd HH:mm";
    public static final String C_DATE_PATTON_DATE_CHINA_12 = "HH:mm:ss";
    public static final String C_DATE_PATTON_DATE_CHINA_2 = "yyyy年MM月dd日";
    public static final String C_DATE_PATTON_DATE_CHINA_3 = "yyyy年MM月dd日 HH:mm";
    public static final String C_DATE_PATTON_DATE_CHINA_4 = "dd";
    public static final String C_DATE_PATTON_DATE_CHINA_5 = "mm:ss";
    public static final String C_DATE_PATTON_DATE_CHINA_6 = "MM月dd日";
    public static final String C_DATE_PATTON_DATE_CHINA_7 = "MM月dd日 HH:mm";
    public static final String C_DATE_PATTON_DATE_CHINA_8 = "MM-dd HH:mm:ss";
    public static final String C_DATE_PATTON_DATE_CHINA_9 = "yyyyMMdd";
    public static final String C_DATE_PATTON_DEFAULT = "yyyy-MM-dd";
    public static final String C_DATE_PATTON_DEFAULT_1 = "yyyy.MM.dd";
    public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String C_TIME_PATTON_DEFAULT_2 = "yyyy-MM-dd HH:mm";
    private static String DATE_FORMAT = "yyyy-MM-dd";
    private static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final long ONE_DAY_MILLIONS = 86400000;
    public static final long ONE_HOUR_MILLIONS = 3600000;
    public static final long ONE_MINUTE_MILLIONS = 60000;
    private static StringBuilder mFormatBuilder = new StringBuilder();
    private static Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());



    public static String secondToStringByDefault(long j) {
        long j2 = j % 60;
        long j3 = (j / 60) % 60;
        long j4 = j / 3600;
        mFormatBuilder.setLength(0);
        if (j4 > 0) {
            return mFormatter.format("%d:%02d:%02d", Long.valueOf(j4), Long.valueOf(j3), Long.valueOf(j2)).toString();
        }
        return "00:" + mFormatter.format("%02d:%02d", Long.valueOf(j3), Long.valueOf(j2)).toString();
    }



    public static String getCurrentDateTime(String str) {
        return new SimpleDateFormat(str).format(new Date());
    }









    public static String formatSecondToDateFormat(long j, String str) {
        return formatMillisecondToDateFormat(j * 1000, str);
    }

    public static String formatSecondToDateFormat(String str, String str2) {
        return formatSecondToDateFormat(NumberUtils.string2long(str), str2);
    }

    public static String formatMillisecondToDateFormat(long j, String str) {
        return TimeUtils.millis2String(j, new SimpleDateFormat(str));
    }

    public static String formatMillisecondToDateFormat(String str, String str2) {
        return formatMillisecondToDateFormat(NumberUtils.string2long(str), str2);
    }

    public static String getTimeStrFromLongSecond(String str, String str2) {
        return formatSecondToDateFormat(str, str2);
    }




    public static String getShortTime(long j) {
        if (j == 0) {
            return "";
        }
        Date date = new Date(j);
        Date date2 = new Date();
        long time = date2.getTime() - date.getTime();
        int calculateDayStatus = calculateDayStatus(date, new Date());
        if (time <= 600000) {
            return SystemUtils.getResString(R.string.fq_just_now);
        }
        if (time < ONE_HOUR_MILLIONS) {
            return (time / 60000) + SystemUtils.getResString(R.string.fq_minute_ago);
        } else if (calculateDayStatus == 0) {
            return (time / ONE_HOUR_MILLIONS) + SystemUtils.getResString(R.string.fq_hour_ago);
        } else if (calculateDayStatus == -1) {
            return (time / ONE_HOUR_MILLIONS) + SystemUtils.getResString(R.string.fq_hour_ago);
        } else if (!isSameYear(date, date2) || calculateDayStatus >= -1) {
            return DateFormat.format("yyyy-MM-dd", date).toString();
        } else {
            return (time / ONE_DAY_MILLIONS) + SystemUtils.getResString(R.string.fq_day_ago);
        }
    }


    public static int calculateDayStatus(Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(6);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return i - instance2.get(6);
    }

    public static boolean isSameYear(Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(1);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return i == instance2.get(1);
    }

    public static String secondToString(long j) {
        long j2 = j % 60;
        long j3 = (j / 60) % 60;
        long j4 = j / 3600;
        mFormatBuilder.setLength(0);
        return j4 > 0 ? mFormatter.format("%d:%02d:%02d", Long.valueOf(j4), Long.valueOf(j3), Long.valueOf(j2)).toString() : mFormatter.format("%02d:%02d", Long.valueOf(j3), Long.valueOf(j2)).toString();
    }


    public static String secondToMinutesString(long j) {
        mFormatBuilder.setLength(0);
        return mFormatter.format("%02d:%02d", Long.valueOf(j / 60), Long.valueOf(j % 60)).toString();
    }

}
