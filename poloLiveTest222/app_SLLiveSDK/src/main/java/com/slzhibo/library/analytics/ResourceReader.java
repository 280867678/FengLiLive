package com.slzhibo.library.analytics;

import android.R;
import android.content.Context;
import android.util.SparseArray;



import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public abstract class ResourceReader implements ResourceIds {
    private static final String TAG = "SA.ResourceReader";
    private final Context mContext;
    private final Map<String, Integer> mIdNameToId = new HashMap();
    private final SparseArray<String> mIdToIdName = new SparseArray<>();

    protected abstract String getLocalClassName(Context context);

    protected abstract Class<?> getSystemClass();

    protected ResourceReader(Context context) {
        this.mContext = context;
    }

    private static void readClassIds(Class<?> cls, String str, Map<String, Integer> map) {
        Field[] fields;
        try {
            for (Field field : cls.getFields()) {
                if (Modifier.isStatic(field.getModifiers()) && field.getType() == Integer.TYPE) {
                    String name = field.getName();
                    int i = field.getInt(null);
                    if (str != null) {
                        name = str + ":" + name;
                    }
                    map.put(name, Integer.valueOf(i));
                }
            }
        } catch (IllegalAccessException unused) {
        }
    }

    @Override // com.slzhibo.library.analytics.ResourceIds
    public boolean knownIdName(String str) {
        return this.mIdNameToId.containsKey(str);
    }

    @Override // com.slzhibo.library.analytics.ResourceIds
    public int idFromName(String str) {
        return this.mIdNameToId.get(str).intValue();
    }

    @Override // com.slzhibo.library.analytics.ResourceIds
    public String nameForId(int i) {
        return this.mIdToIdName.get(i);
    }

    protected void initialize() {
        this.mIdNameToId.clear();
        this.mIdToIdName.clear();
        readClassIds(getSystemClass(), "android", this.mIdNameToId);
        try {
            readClassIds(Class.forName(getLocalClassName(this.mContext)), null, this.mIdNameToId);
        } catch (ClassNotFoundException unused) {
        }
        for (Map.Entry<String, Integer> entry : this.mIdNameToId.entrySet()) {
            this.mIdToIdName.put(entry.getValue().intValue(), entry.getKey());
        }
    }

    /* loaded from: classes6.dex */
    public static class Ids extends ResourceReader {
        private final String mResourcePackageName;

        public Ids(String str, Context context) {
            super(context);
            this.mResourcePackageName = str;
            initialize();
        }

        @Override // com.slzhibo.library.analytics.ResourceReader
        protected Class<?> getSystemClass() {
            return R.id.class;
        }

        @Override // com.slzhibo.library.analytics.ResourceReader
        protected String getLocalClassName(Context context) {
            return this.mResourcePackageName + ".R$id";
        }
    }

    /* loaded from: classes6.dex */
    public static class Drawables extends ResourceReader {
        private final String mResourcePackageName;

        protected Drawables(String str, Context context) {
            super(context);
            this.mResourcePackageName = str;
            initialize();
        }

        @Override // com.slzhibo.library.analytics.ResourceReader
        protected Class<?> getSystemClass() {
            return R.drawable.class;
        }

        @Override // com.slzhibo.library.analytics.ResourceReader
        protected String getLocalClassName(Context context) {
            return this.mResourcePackageName + ".R$drawable";
        }
    }
}
