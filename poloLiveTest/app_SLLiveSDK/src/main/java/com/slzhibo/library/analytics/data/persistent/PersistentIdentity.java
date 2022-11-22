package com.slzhibo.library.analytics.data.persistent;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SuppressLint({"CommitPrefEdits"})
/* loaded from: classes6.dex */
public abstract class PersistentIdentity<T> {
    private static final String TAG = "SA.PersistentIdentity";
    private T item;
    private final Future<SharedPreferences> loadStoredPreferences;
    private final String persistentKey;
    private final PersistentSerializer serializer;

    /* loaded from: classes6.dex */
    interface PersistentSerializer<T> {
        T create();

        T load(String str);

        String save(T t);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PersistentIdentity(Future<SharedPreferences> future, String str, PersistentSerializer<T> persistentSerializer) {
        this.loadStoredPreferences = future;
        this.serializer = persistentSerializer;
        this.persistentKey = str;
    }

    public T get() {
        if (this.item == null) {
            synchronized (this.loadStoredPreferences) {
                String str = null;
                try {
                    SharedPreferences sharedPreferences = this.loadStoredPreferences.get();
                    if (sharedPreferences != null) {
                        str = sharedPreferences.getString(this.persistentKey, null);
                    }
                } catch (InterruptedException | ExecutionException unused) {
                }
                if (str == null) {
                    this.item = (T) this.serializer.create();
                } else {
                    this.item = (T) this.serializer.load(str);
                }
            }
        }
        return this.item;
    }

    public void commit(T t) {
        this.item = t;
        synchronized (this.loadStoredPreferences) {
            SharedPreferences sharedPreferences = null;
            try {
                sharedPreferences = this.loadStoredPreferences.get();
            } catch (InterruptedException | ExecutionException unused) {
            }
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                if (this.item == null) {
                    this.item = (T) this.serializer.create();
                }
                edit.putString(this.persistentKey, this.serializer.save(this.item));
                edit.apply();
            }
        }
    }
}
