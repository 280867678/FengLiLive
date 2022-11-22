package com.slzhibo.library.analytics.data.persistent;

import android.content.SharedPreferences;

import com.slzhibo.library.analytics.data.PersistentLoader;
import com.slzhibo.library.analytics.data.persistent.PersistentIdentity;

import java.util.concurrent.Future;

/* loaded from: classes6.dex */
public class PersistentRemoteSDKConfig extends PersistentIdentity<String> {
    public PersistentRemoteSDKConfig(Future<SharedPreferences> future) {
        super(future, PersistentLoader.PersistentName.REMOTE_CONFIG, new PersistentIdentity.PersistentSerializer<String>() { // from class: com.slzhibo.library.analytics.data.persistent.PersistentRemoteSDKConfig.1
            @Override // com.slzhibo.library.analytics.data.persistent.PersistentIdentity.PersistentSerializer
            public String create() {
                return null;
            }

            @Override // com.slzhibo.library.analytics.data.persistent.PersistentIdentity.PersistentSerializer
            public String load(String str) {
                return str;
            }

            public String save(String str) {
                return str;
            }
        });
    }
}
