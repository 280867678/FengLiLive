package com.slzhibo.library.http;

//import com.alipay.sdk.sys.a;
import com.slzhibo.library.utils.CommonTransferUtils;
import com.slzhibo.library.utils.MD5Utils;
import com.slzhibo.library.utils.UserInfoManager;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes6.dex */
public class MLRequestParams {
    public Map<String, Object> getMLInviterAddressParams(String str, String str2, String str3, String str4, String str5, String str6) {
        HashMap hashMap = new HashMap();
        hashMap.put("videoCallNo", str);
        hashMap.put("inviterId", str2);
        hashMap.put("inviteeId", str3);
        hashMap.put("inviterAvatar", str4);
        hashMap.put("inviterNickname", str5);
        return formatSignParams(hashMap);
    }

    public Map<String, Object> getMLBeInviterAddressParams(String str, String str2, String str3, String str4, String str5, String str6) {
        HashMap hashMap = new HashMap();
        hashMap.put("videoCallNo", str);
        hashMap.put("inviterId", str2);
        hashMap.put("inviteeId", str3);
        hashMap.put("inviteeAvatar", str4);
        hashMap.put("inviteeNickname", str5);
        return formatSignParams(hashMap);
    }

    public Map<String, Object> getKickParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("videoCallNo", str);
        hashMap.put("userId", UserInfoManager.getInstance().getUserId());
        return formatSignParams(hashMap);
    }

    public Map<String, Object> getDealCall(String str, String str2, String str3, boolean z, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("videoCallNo", str);
        hashMap.put("inviterId", str2);
        hashMap.put("inviteeId", str3);
        hashMap.put("isAccept", z ? "1" : "0");
        return formatSignParams(hashMap);
    }

    public Map<String, Object> getEnterMLRTCParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", UserInfoManager.getInstance().getUserId());
        hashMap.put("videoCallNo", str);
        return formatSignParams(hashMap);
    }

    private Map<String, Object> formatSignParams(Map<String, Object> map) {
        HashMap hashMap = new HashMap(map);
        hashMap.put("callSign", signRequest(map));
        return hashMap;
    }

    private String signRequest(Map<String, Object> map) {
        TreeMap treeMap = new TreeMap($$Lambda$TEfSBt3hRUlBSSARfPEHsJesTtE.INSTANCE);
        treeMap.putAll(map);
        StringBuilder sb = new StringBuilder();
//        for (Map.Entry entry : treeMap.entrySet()) {
//            Object value = entry.getValue();
//            sb.append((String) entry.getKey());
//            sb.append("=");
//            sb.append(value);
//            sb.append("&");
//        }
        if (sb.indexOf("&") != -1) {
            sb.deleteCharAt(sb.lastIndexOf("&"));
        }
        return MD5Utils.hash(CommonTransferUtils.getSingleton().CALL_SIGN_API_KEY + "_" + sb.toString());
    }
}
