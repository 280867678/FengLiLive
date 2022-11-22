package com.slzhibo.library.analytics.data.persistent;

import android.content.SharedPreferences;


import com.slzhibo.library.analytics.data.PersistentLoader;

import java.util.concurrent.Future;

/* loaded from: classes6.dex */
public class PersistentSessionIntervalTime extends PersistentIdentity<Integer> {
    public PersistentSessionIntervalTime(Future<SharedPreferences> future) {
        super(future, PersistentLoader.PersistentName.APP_SESSION_TIME, new PersistentSerializer<Integer>() { // from class: com.slzhibo.library.analytics.data.persistent.PersistentSessionIntervalTime.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.slzhibo.library.analytics.data.persistent.PersistentIdentity.PersistentSerializer
            public Integer load(String str) {
                return Integer.valueOf(str);
            }

            public String save(Integer num) {
                return num == null ? "" : num.toString();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.slzhibo.library.analytics.data.persistent.PersistentIdentity.PersistentSerializer
            public Integer create() {
                return Integer.valueOf((int)30000);
            }
        });
    }
}
