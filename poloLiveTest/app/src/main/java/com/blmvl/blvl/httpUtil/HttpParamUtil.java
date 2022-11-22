package com.blmvl.blvl.httpUtil;

//package p067e.p103c.p104a.p111i;

import com.blmvl.blvl.crypt.Cfb_256crypt;
import com.blmvl.blvl.crypt.MD5Util;
import com.lzy.okgo.cache.CacheEntity;
import org.json.JSONException;
import org.json.JSONObject;
//import p067e.p130f.p131a.p135d.Cfb_256crypt;
//import p067e.p130f.p131a.p135d.MD5Util;

/* renamed from: e.c.a.i.c */
/* loaded from: classes.dex */
public class HttpParamUtil {
    /* renamed from: a */
    public static String m9791a(String str) throws JSONException {
        long currentTimeMillis = System.currentTimeMillis();
        String b = Cfb_256crypt.m9205b("ljhlksslgkjfhlksuo8472rju6p2od03", str);
        String a = m9790a(b, currentTimeMillis);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("timestamp", currentTimeMillis);
        jSONObject.put(CacheEntity.DATA, b);
        jSONObject.put("sign", a);
        return jSONObject.toString();
    }

    /* renamed from: a */
    public static String m9790a(String str, long j) {
        return MD5Util.m9074a(Cfb_256crypt.m9210a("data=" + str + "&timestamp=" + j + "kihfks3kjdhfksjh3kdjfs745dkslfh4"));
    }
}

