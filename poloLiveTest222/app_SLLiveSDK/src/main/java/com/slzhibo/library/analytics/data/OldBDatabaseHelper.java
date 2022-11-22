package com.slzhibo.library.analytics.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class OldBDatabaseHelper extends SQLiteOpenHelper {
    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OldBDatabaseHelper(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x005c, code lost:
        if (r3 == null) goto L_0x0061;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x005e, code lost:
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0061, code lost:
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x004b, code lost:
        if (r3 != null) goto L_0x005e;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */

    @SuppressLint("Range")
    public JSONArray getAllEvents() {
        JSONArray jSONArray = new JSONArray();
        Cursor cursor = null;
        try {
            cursor = getReadableDatabase().rawQuery(String.format("SELECT * FROM %s ORDER BY %s", "events", "created_at"), null);
            while (cursor.moveToNext()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("created_at", cursor.getString(cursor.getColumnIndex("created_at")));
                jSONObject.put("data", cursor.getString(cursor.getColumnIndex("data")));
                jSONArray.put(jSONObject);
            }
            close();
        } catch (Exception unused) {
            close();
        } catch (Throwable th) {
            close();
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return jSONArray;
    }
}
