package com.slzhibo.library.analytics.data;

import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

//import com.alipay.sdk.util.C1047i;
//import com.amazonaws.mobileconnectors.p024s3.transferutility.TransferTable;
//import com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher;
//import com.seven.movie.commonres.utils.CommonUtils;
import com.slzhibo.library.analytics.AopConstants;
import com.slzhibo.library.analytics.DataEntity;
import com.slzhibo.library.analytics.SensorsDataAPI;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class DbAdapter {
    private static final String TAG = "SA.DbAdapter";
    private static DbAdapter instance;
    private ContentResolver contentResolver;
    private final Context mContext;
    private final File mDatabaseFile;
    private final DbParams mDbParams;
    private int mSessionTime = 30000;
    private long mAppEndTime = 0;

    private DbAdapter(Context context, String str) {
        this.mContext = context.getApplicationContext();
        this.contentResolver = this.mContext.getContentResolver();
        this.mDatabaseFile = context.getDatabasePath("sensorsdata");
        this.mDbParams = DbParams.getInstance(str);
    }

    public static DbAdapter getInstance(Context context, String str) {
        if (instance == null) {
            instance = new DbAdapter(context, str);
        }
        return instance;
    }

    public static DbAdapter getInstance() {
        DbAdapter dbAdapter = instance;
        if (dbAdapter != null) {
            return dbAdapter;
        }
        throw new IllegalStateException("The static method getInstance(Context context, String packageName) should be called before calling getInstance()");
    }

    private long getMaxCacheSize(Context context) {
        try {
            return SensorsDataAPI.sharedInstance(context).getMaxCacheSize();
        } catch (Exception unused) {
            return 33554432L;
        }
    }

    private boolean belowMemThreshold() {
        return this.mDatabaseFile.exists() && this.mDatabaseFile.length() >= getMaxCacheSize(this.mContext);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0076, code lost:
        if (r1 != null) goto L_0x0078;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0078, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0084, code lost:
        if (r1 == null) goto L_0x0087;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0087, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int addJSON(JSONObject jSONObject) {
        String[] generateDataString;
        int i = -1;
        Cursor cursor = null;
        try {
            if (belowMemThreshold() && ((generateDataString = generateDataString("events", 100)) == null || (i = cleanupEvents(generateDataString[0])) <= 0)) {
                return -2;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("data", jSONObject.toString() + "\t" + jSONObject.toString().hashCode());
            contentValues.put("created_at", Long.valueOf(System.currentTimeMillis()));
            this.contentResolver.insert(this.mDbParams.getEventUri(), contentValues);
            cursor = this.contentResolver.query(this.mDbParams.getEventUri(), null, null, null, null);
            if (cursor != null) {
                i = cursor.getCount();
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0092, code lost:
        if (r1 != null) goto L_0x0094;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0094, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00a0, code lost:
        if (r1 == null) goto L_0x00a3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00a3, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int addJSON(List<JSONObject> list) {
        String[] generateDataString;
        int i = -1;
        Cursor cursor = null;
        try {
            int i2 = 0;
            if (belowMemThreshold() && ((generateDataString = generateDataString("events", 100)) == null || (i = cleanupEvents(generateDataString[0])) <= 0)) {
                return -2;
            }
            ContentValues[] contentValuesArr = new ContentValues[list.size()];
            for (JSONObject jSONObject : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("data", jSONObject.toString() + "\t" + jSONObject.toString().hashCode());
                contentValues.put("created_at", Long.valueOf(System.currentTimeMillis()));
                i2++;
                contentValuesArr[i2] = contentValues;
            }
            this.contentResolver.bulkInsert(this.mDbParams.getEventUri(), contentValuesArr);
            cursor = this.contentResolver.query(this.mDbParams.getEventUri(), null, null, null, null);
            if (cursor != null) {
                i = cursor.getCount();
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return i;
    }

    public void deleteAllEvents() {
        try {
            this.contentResolver.delete(this.mDbParams.getEventUri(), null, null);
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0038, code lost:
        if (r0 != null) goto L_0x002d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003b, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x002b, code lost:
        if (r0 != null) goto L_0x002d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x002d, code lost:
        r0.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    @SuppressLint("Range")
    public int cleanupEvents(String str) {
        Cursor cursor = null;
        int i = -1;
        try {
            this.contentResolver.delete(this.mDbParams.getEventUri(), "_id <= ?", new String[]{str});
            cursor = this.contentResolver.query(this.mDbParams.getEventUri(), null, null, null, null);
            if (cursor != null) {
                i = cursor.getCount();
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0052, code lost:
        if (r11 != null) goto L_0x0054;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0065, code lost:
        if (r11 == null) goto L_0x0068;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    @SuppressLint("Range")
    public int cleanupEvents(List<String> list) {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        Iterator<String> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(ContentProviderOperation.newDelete(this.mDbParams.getEventUri()).withSelection("_id = ?", new String[]{it2.next()}).build());
        }
        Cursor cursor = null;
        int i = -1;
        try {
            try {
                try {
                    this.contentResolver.applyBatch(SensorsDataContentProvider.authority, arrayList);
                    cursor = this.contentResolver.query(this.mDbParams.getEventUri(), null, null, null, null);
                    if (cursor != null) {
                        i = cursor.getCount();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                    if (cursor != null) {
                        try {
                            cursor.close();
                        } catch (Exception unused) {
                            Log.i("meme", "数据库剩余：" + i);
                            return i;
                        }
                    }
                    Log.i("meme", "数据库剩余：" + i);
                    return i;
                }
            } catch (OperationApplicationException e2) {
                e2.printStackTrace();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
        return i;
    }

    public void commitActivityCount(int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("activity_started_count", Integer.valueOf(i));
        this.contentResolver.insert(this.mDbParams.getActivityStartCountUri(), contentValues);
    }

    public int getActivityCount() {
        Cursor query = this.contentResolver.query(this.mDbParams.getActivityStartCountUri(), null, null, null, null);
        int i = 0;
        if (query != null && query.getCount() > 0) {
            i = 0;
            while (query.moveToNext()) {
                i = query.getInt(0);
            }
        }
        if (query != null) {
            query.close();
        }
        return i;
    }

    public void commitAppStartTime(long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PersistentLoader.PersistentName.APP_START_TIME, Long.valueOf(j));
        this.contentResolver.insert(this.mDbParams.getAppStartTimeUri(), contentValues);
    }

    public long getAppStartTime() {
        Cursor query = this.contentResolver.query(this.mDbParams.getAppStartTimeUri(), null, null, null, null);
        long j = 0;
        if (query != null && query.getCount() > 0) {
            while (query.moveToNext()) {
                j = query.getLong(0);
            }
        }
        if (query != null) {
            query.close();
        }
        return j;
    }

    public void commitAppEndTime(long j) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(PersistentLoader.PersistentName.APP_PAUSED_TIME, Long.valueOf(j));
            this.contentResolver.insert(this.mDbParams.getAppPausedUri(), contentValues);
        } catch (Exception unused) {
        }
        this.mAppEndTime = j;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0035, code lost:
        if (r0 != null) goto L_0x0042;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0040, code lost:
        if (r0 == null) goto L_0x0045;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
        r0.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long getAppEndTime() {
        if (System.currentTimeMillis() - this.mAppEndTime > this.mSessionTime) {
            Cursor cursor = null;
            try {
                cursor = this.contentResolver.query(this.mDbParams.getAppPausedUri(), null, null, null, null);
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        this.mAppEndTime = cursor.getLong(0);
                    }
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return this.mAppEndTime;
    }

    public void commitAppEndData(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PersistentLoader.PersistentName.APP_END_DATA, str);
        this.contentResolver.insert(this.mDbParams.getAppEndDataUri(), contentValues);
    }

    public String getAppEndData() {
        Cursor query = this.contentResolver.query(this.mDbParams.getAppEndDataUri(), null, null, null, null);
        String str = "";
        if (query != null && query.getCount() > 0) {
            while (query.moveToNext()) {
                str = query.getString(0);
            }
        }
        if (query != null) {
            query.close();
        }
        return str;
    }

    public void commitLoginId(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PersistentLoader.PersistentName.LOGIN_ID, str);
        this.contentResolver.insert(this.mDbParams.getLoginIdUri(), contentValues);
    }

    public String getLoginId() {
        Cursor query = this.contentResolver.query(this.mDbParams.getLoginIdUri(), null, null, null, null);
        String str = "";
        if (query != null && query.getCount() > 0) {
            while (query.moveToNext()) {
                str = query.getString(0);
            }
        }
        if (query != null) {
            query.close();
        }
        return str;
    }

    public void commitSessionIntervalTime(int i) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(PersistentLoader.PersistentName.APP_SESSION_TIME, Integer.valueOf(i));
            this.contentResolver.insert(this.mDbParams.getSessionTimeUri(), contentValues);
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0027, code lost:
        if (r0 != null) goto L_0x0034;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0032, code lost:
        if (r0 == null) goto L_0x0037;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0034, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0039, code lost:
        return r7.mSessionTime;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int getSessionIntervalTime() {
        Cursor cursor = null;
        int i = 0;
        try {
            cursor = this.contentResolver.query(this.mDbParams.getSessionTimeUri(), null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    this.mSessionTime = cursor.getInt(0);
                    i = cursor.getInt(0);
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return i;

    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00cc A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    @SuppressLint("Range")
    public String[] generateDataString(String str, int i) {
        String str2 = null;
        String str3 = null;
        Cursor cursor;
        Throwable th;
        try {
            cursor = this.contentResolver.query(this.mDbParams.getEventUri(), null, null, null, "created_at ASC LIMIT " + i);
            if (cursor != null) {
                try {
                    try {
                        StringBuilder sb = new StringBuilder();
                        String str4 = ",";
                        sb.append("[");
                        str3 = null;
                        while (cursor.moveToNext()) {
                            if (cursor.isLast()) {
                                str4 = "]";
                                str3 = cursor.getString(cursor.getColumnIndex("_id"));
                            }
                            try {
                                @SuppressLint("Range") String string = cursor.getString(cursor.getColumnIndex("data"));
                                if (!TextUtils.isEmpty(string)) {
                                    int lastIndexOf = string.lastIndexOf("\t");
                                    if (lastIndexOf > -1) {
                                        String replaceFirst = string.substring(lastIndexOf).replaceFirst("\t", "");
                                        string = string.substring(0, lastIndexOf);
                                        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(replaceFirst) && replaceFirst.equals(String.valueOf(string.hashCode()))) {
                                        }
                                    }
                                    sb.append((CharSequence) string, 0, string.length() - 1);
                                    sb.append("}");
                                    sb.append(str4);
                                }
                            } catch (Exception unused) {
                            }
                        }
                        str2 = sb.toString();
                    } catch (SQLiteException unused2) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        str2 = null;
                        str3 = null;
                        if (str3 == null) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } else {
                str2 = null;
                str3 = null;
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLiteException unused3) {
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
        if (str3 == null) {
            return new String[]{str3, str2};
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a7 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    @SuppressLint("Range")
    public DataEntity generateDataEntity(int i) {
        String str = null;
        Cursor cursor;
        Throwable th;
        ArrayList arrayList = new ArrayList();
        try {
            cursor = this.contentResolver.query(this.mDbParams.getEventUri(), null, null, null, "created_at ASC LIMIT " + i);
            str = null;
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    try {
                        try {
                            if (cursor.isLast()) {
                                str = cursor.getString(cursor.getColumnIndex("_id"));
                            }
                            try {
                                String string = cursor.getString(cursor.getColumnIndex("data"));
                                if (!TextUtils.isEmpty(string)) {
                                    int lastIndexOf = string.lastIndexOf("\t");
                                    if (lastIndexOf > -1) {
                                        String replaceFirst = string.substring(lastIndexOf).replaceFirst("\t", "");
                                        string = string.substring(0, lastIndexOf);
                                        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(replaceFirst) && replaceFirst.equals(String.valueOf(string.hashCode()))) {
                                        }
                                    }
                                    arrayList.add(string);
                                }
                            } catch (Exception unused) {
                            }
                        } catch (SQLiteException unused2) {
                            if (cursor != null) {
                                cursor.close();
                            }
                            str = null;
                            if (str == null) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLiteException unused3) {
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
        if (str == null) {
            return new DataEntity(str, arrayList);
        }
        return null;
    }

    public List<JSONObject> generateDataEntity() {
        Cursor cursor;
        Throwable th;
        JSONObject jSONObject = null;
        ArrayList arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            cursor = this.contentResolver.query(this.mDbParams.getEventUri(), null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    try {
                        try {
                            try {
                                @SuppressLint("Range") String string = cursor.getString(cursor.getColumnIndex("data"));
                                @SuppressLint("Range") String string2 = cursor.getString(cursor.getColumnIndex("_id"));
                                if (!TextUtils.isEmpty(string)) {
                                    int lastIndexOf = string.lastIndexOf("\t");
                                    if (lastIndexOf > -1) {
                                        String replaceFirst = string.substring(lastIndexOf).replaceFirst("\t", "");
                                        String substring = string.substring(0, lastIndexOf);
                                        if (!TextUtils.isEmpty(substring) && !TextUtils.isEmpty(replaceFirst) && replaceFirst.equals(String.valueOf(substring.hashCode()))) {
                                            jSONObject = new JSONObject(substring);
                                            jSONObject.put(AopConstants.DB_ID_KEY, string2);
                                        }
                                    } else {
                                        jSONObject = null;
                                    }
                                    arrayList.add(jSONObject);
                                }
                            } catch (Exception unused) {
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    } catch (SQLiteException unused2) {
                        cursor2 = cursor;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        return arrayList;
                    }
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLiteException unused3) {
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
        return arrayList;
    }
}
