package com.slzhibo.library.analytics.data.persistent;

import android.content.SharedPreferences;

import com.slzhibo.library.analytics.data.PersistentLoader;

import java.util.concurrent.Future;

/* loaded from: classes6.dex */
public class PersistentAppStartTime extends PersistentIdentity<Long> {
    public PersistentAppStartTime(Future<SharedPreferences> future) {
        super(future, PersistentLoader.PersistentName.APP_START_TIME, new PersistentSerializer<Long>() { // from class: com.slzhibo.library.analytics.data.persistent.PersistentAppStartTime.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.slzhibo.library.analytics.data.persistent.PersistentIdentity.PersistentSerializer
            public Long load(String str) {
                return Long.valueOf(str);
            }

            public String save(Long l) {
                return l == null ? create().toString() : String.valueOf(l);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.slzhibo.library.analytics.data.persistent.PersistentIdentity.PersistentSerializer
            public Long create() {
                return 0L;
            }
        });
    }
}
