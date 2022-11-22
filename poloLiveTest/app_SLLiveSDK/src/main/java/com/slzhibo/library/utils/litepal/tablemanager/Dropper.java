package com.slzhibo.library.utils.litepal.tablemanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.slzhibo.library.utils.litepal.tablemanager.model.TableModel;
import com.slzhibo.library.utils.litepal.util.BaseUtility;
import com.slzhibo.library.utils.litepal.util.LitePalLog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes12.dex */
public class Dropper extends AssociationUpdater {
    private Collection<TableModel> mTableModels;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.slzhibo.library.utils.litepal.tablemanager.AssociationUpdater, com.slzhibo.library.utils.litepal.tablemanager.Creator, com.slzhibo.library.utils.litepal.tablemanager.AssociationCreator, com.slzhibo.library.utils.litepal.tablemanager.Generator
    public void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        this.mTableModels = getAllTableModels();
        this.mDb = sQLiteDatabase;
        dropTables();
    }

    private void dropTables() {
        List<String> findTablesToDrop = findTablesToDrop();
        dropTables(findTablesToDrop, this.mDb);
        clearCopyInTableSchema(findTablesToDrop);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0053, code lost:
        if (r1 == null) goto L_0x0061;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x005c, code lost:
        if (r1 == null) goto L_0x0061;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x005e, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0061, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
//    private List<String> findTablesToDrop() {
//        /*
//            r10 = this;
//            java.util.ArrayList r0 = new java.util.ArrayList
//            r0.<init>()
//            r1 = 0
//            android.database.sqlite.SQLiteDatabase r2 = r10.mDb     // Catch: all -> 0x0056, Exception -> 0x0058
//            java.lang.String r3 = "table_schema"
//            r4 = 0
//            r5 = 0
//            r6 = 0
//            r7 = 0
//            r8 = 0
//            r9 = 0
//            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: all -> 0x0056, Exception -> 0x0058
//            boolean r2 = r1.moveToFirst()     // Catch: all -> 0x0056, Exception -> 0x0058
//            if (r2 == 0) goto L_0x0053
//        L_0x001a:
//            java.lang.String r2 = "name"
//            int r2 = r1.getColumnIndexOrThrow(r2)     // Catch: all -> 0x0056, Exception -> 0x0058
//            java.lang.String r2 = r1.getString(r2)     // Catch: all -> 0x0056, Exception -> 0x0058
//            java.lang.String r3 = "type"
//            int r3 = r1.getColumnIndexOrThrow(r3)     // Catch: all -> 0x0056, Exception -> 0x0058
//            int r3 = r1.getInt(r3)     // Catch: all -> 0x0056, Exception -> 0x0058
//            boolean r3 = r10.shouldDropThisTable(r2, r3)     // Catch: all -> 0x0056, Exception -> 0x0058
//            if (r3 == 0) goto L_0x004d
//            java.lang.String r3 = "AssociationUpdater"
//            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: all -> 0x0056, Exception -> 0x0058
//            r4.<init>()     // Catch: all -> 0x0056, Exception -> 0x0058
//            java.lang.String r5 = "need to drop "
//            r4.append(r5)     // Catch: all -> 0x0056, Exception -> 0x0058
//            r4.append(r2)     // Catch: all -> 0x0056, Exception -> 0x0058
//            java.lang.String r4 = r4.toString()     // Catch: all -> 0x0056, Exception -> 0x0058
//            com.slzhibo.library.utils.litepal.util.LitePalLog.d(r3, r4)     // Catch: all -> 0x0056, Exception -> 0x0058
//            r0.add(r2)     // Catch: all -> 0x0056, Exception -> 0x0058
//        L_0x004d:
//            boolean r2 = r1.moveToNext()     // Catch: all -> 0x0056, Exception -> 0x0058
//            if (r2 != 0) goto L_0x001a
//        L_0x0053:
//            if (r1 == 0) goto L_0x0061
//            goto L_0x005e
//        L_0x0056:
//            r0 = move-exception
//            goto L_0x0062
//        L_0x0058:
//            r2 = move-exception
//            r2.printStackTrace()     // Catch: all -> 0x0056
//            if (r1 == 0) goto L_0x0061
//        L_0x005e:
//            r1.close()
//        L_0x0061:
//            return r0
//        L_0x0062:
//            if (r1 == 0) goto L_0x0067
//            r1.close()
//        L_0x0067:
//            goto L_0x0069
//        L_0x0068:
//            throw r0
//        L_0x0069:
//            goto L_0x0068
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.utils.litepal.tablemanager.Dropper.findTablesToDrop():java.util.List");
//    }

    private List<String> findTablesToDrop() {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                cursor = this.mDb.query("table_schema", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String string = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                        if (shouldDropThisTable(string, cursor.getInt(cursor.getColumnIndexOrThrow("type")))) {
                            LitePalLog.d("AssociationUpdater", "need to drop " + string);
                            arrayList.add(string);
                        }
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return arrayList;
    }


    private List<String> pickTableNamesFromTableModels() {
        ArrayList arrayList = new ArrayList();
        for (TableModel tableModel : this.mTableModels) {
            arrayList.add(tableModel.getTableName());
        }
        return arrayList;
    }

    private boolean shouldDropThisTable(String str, int i) {
        return !BaseUtility.containsIgnoreCases(pickTableNamesFromTableModels(), str) && i == 0;
    }
}
