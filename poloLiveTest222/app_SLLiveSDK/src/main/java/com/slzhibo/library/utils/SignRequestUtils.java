package com.slzhibo.library.utils;

import android.text.TextUtils;

import com.slzhibo.library.http.RequestParams;

import java.util.Map;
import java.util.TreeMap;

import okhttp3.Request;

public class SignRequestUtils {

    /* renamed from: a */
    public static String m1092a(Request request, TreeMap<String, Object> treeMap, String str) {
        TreeMap treeMap2 = new TreeMap(C5980e.f20062b);
        treeMap2.putAll(treeMap);
        treeMap2.put("timeStampStr", request.header("timeStampStr"));
        treeMap2.put("randomStr", request.header("randomStr"));
        treeMap2.put("deviceId", request.header("deviceId"));
        StringBuilder sb = new StringBuilder();
        for (Object entry1 : treeMap2.entrySet()) {
            Map.Entry entry = (Map.Entry) entry1;///////////////////////////////////////////
            String str2 = (String) entry.getKey();
            Object value = entry.getValue();
            if (!TextUtils.equals(RequestParams.PAGE_SIZE, str2) && !TextUtils.equals(RequestParams.PAGE_NUMBER, str2) && value != null) {
                sb.append(str2);
                sb.append("=");
                sb.append(value);
                sb.append("&");
            }
        }
        if (sb.indexOf("&") != -1) {
            sb.deleteCharAt(sb.lastIndexOf("&"));
        }
        return MD5Utils.m1143b(CommonTransferUtils.getSingleton().SIGN_API_KEY + "_" + sb.toString().toUpperCase());
    }

    /* renamed from: b */
    public static String m1091b() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /* renamed from: a */
    public static String m1093a() {
        return C5970b0.m1626a(16);
    }


}
