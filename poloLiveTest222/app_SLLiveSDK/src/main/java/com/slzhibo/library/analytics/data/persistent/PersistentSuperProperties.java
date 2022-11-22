package com.slzhibo.library.analytics.data.persistent;

import android.content.SharedPreferences;

import com.slzhibo.library.analytics.data.PersistentLoader;
import com.slzhibo.library.analytics.data.persistent.PersistentIdentity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Future;

/* loaded from: classes6.dex */
public class PersistentSuperProperties extends PersistentIdentity<JSONObject> {
    public PersistentSuperProperties(Future<SharedPreferences> future) {
        super(future, PersistentLoader.PersistentName.SUPER_PROPERTIES, new PersistentIdentity.PersistentSerializer<JSONObject>() { // from class: com.slzhibo.library.analytics.data.persistent.PersistentSuperProperties.1
            @Override // com.slzhibo.library.analytics.data.persistent.PersistentIdentity.PersistentSerializer
            public JSONObject load(String str) {
                try {
                    return new JSONObject(str);
                } catch (JSONException unused) {
                    return new JSONObject();
                }
            }

            public String save(JSONObject jSONObject) {
                if (jSONObject == null) {
                    jSONObject = create();
                }
                return jSONObject.toString();
            }

            @Override // com.slzhibo.library.analytics.data.persistent.PersistentIdentity.PersistentSerializer
            public JSONObject create() {
                return new JSONObject();
            }
        });
    }
}
