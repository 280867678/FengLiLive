package com.slzhibo.library.analytics.data.persistent;

import android.content.SharedPreferences;

import com.slzhibo.library.analytics.data.PersistentLoader;

import java.util.concurrent.Future;

/* loaded from: classes6.dex */
public class PersistentAppEndData extends PersistentIdentity<String> {
    public PersistentAppEndData(Future<SharedPreferences> future) {
        super(future, PersistentLoader.PersistentName.APP_END_DATA, new PersistentSerializer<String>() { // from class: com.slzhibo.library.analytics.data.persistent.PersistentAppEndData.1
            @Override // com.slzhibo.library.analytics.data.persistent.PersistentIdentity.PersistentSerializer
            public String create() {
                return "";
            }

            @Override // com.slzhibo.library.analytics.data.persistent.PersistentIdentity.PersistentSerializer
            public String load(String str) {
                return str;
            }

            public String save(String str) {
                return str == null ? create() : str;
            }
        });
    }
}
