package com.slzhibo.library.analytics;

import org.json.JSONObject;

/* loaded from: classes6.dex */
public interface CustomProperties {
    void dealRawProperties(String str, JSONObject jSONObject);

    JSONObject getAppPublicProperties(String str);

    String getRuleString();
}
