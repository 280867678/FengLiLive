package com.slzhibo.library.utils.litepal.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

//import com.amazonaws.mobileconnectors.s3.transferutility.TransferTable;

import com.slzhibo.library.utils.litepal.exceptions.DatabaseGenerateException;
import com.slzhibo.library.utils.litepal.tablemanager.model.ColumnModel;
import com.slzhibo.library.utils.litepal.tablemanager.model.TableModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes12.dex */
public class DBUtility {
    private static final String KEYWORDS_COLUMN_SUFFIX = "_lpcolumn";
    private static final String REG_COLLECTION = "\\s+(not\\s+)?(in)\\s*\\(";
    private static final String REG_FUZZY = "\\s+(not\\s+)?(like|between)\\s+";
    private static final String REG_OPERATOR = "\\s*(=|!=|<>|<|>)";
    private static final String SQLITE_KEYWORDS = ",abort,add,after,all,alter,and,as,asc,autoincrement,before,begin,between,by,cascade,check,collate,column,commit,conflict,constraint,create,cross,database,deferrable,deferred,delete,desc,distinct,drop,each,end,escape,except,exclusive,exists,foreign,from,glob,group,having,in,index,inner,insert,intersect,into,is,isnull,join,like,limit,match,natural,not,notnull,null,of,offset,on,or,order,outer,plan,pragma,primary,query,raise,references,regexp,reindex,release,rename,replace,restrict,right,rollback,row,savepoint,select,set,table,temp,temporary,then,to,transaction,trigger,union,unique,update,using,vacuum,values,view,virtual,when,where,";
    private static final String TAG = "DBUtility";

    private DBUtility() {
    }

    public static String getTableNameByClassName(String str) {
        if (TextUtils.isEmpty(str) || '.' == str.charAt(str.length() - 1)) {
            return null;
        }
        return str.substring(str.lastIndexOf(".") + 1);
    }

    public static List<String> getTableNameListByClassNameList(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (String str : list) {
                arrayList.add(getTableNameByClassName(str));
            }
        }
        return arrayList;
    }

    public static String getTableNameByForeignColumn(String str) {
        if (TextUtils.isEmpty(str) || !str.toLowerCase(Locale.US).endsWith("_id")) {
            return null;
        }
        return str.substring(0, str.length() - 3);
    }

    public static String getIntermediateTableName(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        if (str.toLowerCase(Locale.US).compareTo(str2.toLowerCase(Locale.US)) <= 0) {
            return str + "_" + str2;
        }
        return str2 + "_" + str;
    }

    public static String getGenericTableName(String str, String str2) {
        String tableNameByClassName = getTableNameByClassName(str);
        return BaseUtility.changeCase(tableNameByClassName + "_" + str2);
    }

    public static String getGenericValueIdColumnName(String str) {
        return BaseUtility.changeCase(getTableNameByClassName(str) + "_id");
    }

    public static String getM2MSelfRefColumnName(Field field) {
        return BaseUtility.changeCase(field.getName() + "_id");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x003d, code lost:
        if (r0.getInt(r0.getColumnIndexOrThrow("type")) != 1) goto L_0x004b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003f, code lost:
        if (r0 == null) goto L_0x0044;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0041, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0044, code lost:
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
//    public static boolean isIntermediateTable(String r9, SQLiteDatabase r10) {
//        /*
//            boolean r0 = android.text.TextUtils.isEmpty(r9)
//            if (r0 != 0) goto L_0x0060
//            java.lang.String r0 = "[0-9a-zA-Z]+_[0-9a-zA-Z]+"
//            boolean r0 = r9.matches(r0)
//            if (r0 == 0) goto L_0x0060
//            r0 = 0
//            java.lang.String r2 = "table_schema"
//            r3 = 0
//            r4 = 0
//            r5 = 0
//            r6 = 0
//            r7 = 0
//            r8 = 0
//            r1 = r10
//            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: all -> 0x004e, Exception -> 0x0050
//            boolean r10 = r0.moveToFirst()     // Catch: all -> 0x004e, Exception -> 0x0050
//            if (r10 == 0) goto L_0x004b
//        L_0x0022:
//            java.lang.String r10 = "name"
//            int r10 = r0.getColumnIndexOrThrow(r10)     // Catch: all -> 0x004e, Exception -> 0x0050
//            java.lang.String r10 = r0.getString(r10)     // Catch: all -> 0x004e, Exception -> 0x0050
//            boolean r10 = r9.equalsIgnoreCase(r10)     // Catch: all -> 0x004e, Exception -> 0x0050
//            if (r10 == 0) goto L_0x0045
//            java.lang.String r9 = "type"
//            int r9 = r0.getColumnIndexOrThrow(r9)     // Catch: all -> 0x004e, Exception -> 0x0050
//            int r9 = r0.getInt(r9)     // Catch: all -> 0x004e, Exception -> 0x0050
//            r10 = 1
//            if (r9 != r10) goto L_0x004b
//            if (r0 == 0) goto L_0x0044
//            r0.close()
//        L_0x0044:
//            return r10
//        L_0x0045:
//            boolean r10 = r0.moveToNext()     // Catch: all -> 0x004e, Exception -> 0x0050
//            if (r10 != 0) goto L_0x0022
//        L_0x004b:
//            if (r0 == 0) goto L_0x0060
//            goto L_0x0056
//        L_0x004e:
//            r9 = move-exception
//            goto L_0x005a
//        L_0x0050:
//            r9 = move-exception
//            r9.printStackTrace()     // Catch: all -> 0x004e
//            if (r0 == 0) goto L_0x0060
//        L_0x0056:
//            r0.close()
//            goto L_0x0060
//        L_0x005a:
//            if (r0 == 0) goto L_0x005f
//            r0.close()
//        L_0x005f:
//            throw r9
//        L_0x0060:
//            r9 = 0
//            return r9
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.utils.litepal.util.DBUtility.isIntermediateTable(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
//    }

    public static boolean isIntermediateTable(String str, SQLiteDatabase sQLiteDatabase) {
        if (TextUtils.isEmpty(str) || !str.matches("[0-9a-zA-Z]+_[0-9a-zA-Z]+")) {
            return false;
        }
        Cursor cursor = null;
        try {
            try {
                cursor = sQLiteDatabase.query("table_schema", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    while (true) {
                        if (!str.equalsIgnoreCase(cursor.getString(cursor.getColumnIndexOrThrow("name")))) {
                            if (!cursor.moveToNext()) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                if (cursor == null) {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (cursor == null) {
                    return false;
                }
            }
            cursor.close();
            return false;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x003d, code lost:
        if (r0.getInt(r0.getColumnIndexOrThrow("type")) != 2) goto L_0x004c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0040, code lost:
        if (r0 == null) goto L_0x0045;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0042, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0045, code lost:
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
//    public static boolean isGenericTable(String r9, SQLiteDatabase r10) {
//        /*
//            boolean r0 = android.text.TextUtils.isEmpty(r9)
//            if (r0 != 0) goto L_0x0061
//            java.lang.String r0 = "[0-9a-zA-Z]+_[0-9a-zA-Z]+"
//            boolean r0 = r9.matches(r0)
//            if (r0 == 0) goto L_0x0061
//            r0 = 0
//            java.lang.String r2 = "table_schema"
//            r3 = 0
//            r4 = 0
//            r5 = 0
//            r6 = 0
//            r7 = 0
//            r8 = 0
//            r1 = r10
//            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: all -> 0x004f, Exception -> 0x0051
//            boolean r10 = r0.moveToFirst()     // Catch: all -> 0x004f, Exception -> 0x0051
//            if (r10 == 0) goto L_0x004c
//        L_0x0022:
//            java.lang.String r10 = "name"
//            int r10 = r0.getColumnIndexOrThrow(r10)     // Catch: all -> 0x004f, Exception -> 0x0051
//            java.lang.String r10 = r0.getString(r10)     // Catch: all -> 0x004f, Exception -> 0x0051
//            boolean r10 = r9.equalsIgnoreCase(r10)     // Catch: all -> 0x004f, Exception -> 0x0051
//            if (r10 == 0) goto L_0x0046
//            java.lang.String r9 = "type"
//            int r9 = r0.getColumnIndexOrThrow(r9)     // Catch: all -> 0x004f, Exception -> 0x0051
//            int r9 = r0.getInt(r9)     // Catch: all -> 0x004f, Exception -> 0x0051
//            r10 = 2
//            if (r9 != r10) goto L_0x004c
//            r9 = 1
//            if (r0 == 0) goto L_0x0045
//            r0.close()
//        L_0x0045:
//            return r9
//        L_0x0046:
//            boolean r10 = r0.moveToNext()     // Catch: all -> 0x004f, Exception -> 0x0051
//            if (r10 != 0) goto L_0x0022
//        L_0x004c:
//            if (r0 == 0) goto L_0x0061
//            goto L_0x0057
//        L_0x004f:
//            r9 = move-exception
//            goto L_0x005b
//        L_0x0051:
//            r9 = move-exception
//            r9.printStackTrace()     // Catch: all -> 0x004f
//            if (r0 == 0) goto L_0x0061
//        L_0x0057:
//            r0.close()
//            goto L_0x0061
//        L_0x005b:
//            if (r0 == 0) goto L_0x0060
//            r0.close()
//        L_0x0060:
//            throw r9
//        L_0x0061:
//            r9 = 0
//            return r9
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.utils.litepal.util.DBUtility.isGenericTable(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
//    }

    public static boolean isGenericTable(String str, SQLiteDatabase sQLiteDatabase) {
        if (TextUtils.isEmpty(str) || !str.matches("[0-9a-zA-Z]+_[0-9a-zA-Z]+")) {
            return false;
        }
        Cursor cursor = null;
        try {
            try {
                cursor = sQLiteDatabase.query("table_schema", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    while (true) {
                        if (!str.equalsIgnoreCase(cursor.getString(cursor.getColumnIndexOrThrow("name")))) {
                            if (!cursor.moveToNext()) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                if (cursor == null) {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (cursor == null) {
                    return false;
                }
            }
            cursor.close();
            return false;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static boolean isTableExists(String str, SQLiteDatabase sQLiteDatabase) {
        try {
            return BaseUtility.containsIgnoreCases(findAllTableNames(sQLiteDatabase), str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        if (r0 == null) goto L_0x0056;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0056, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
//    public static boolean isColumnExists(String r4, String r5, SQLiteDatabase r6) {
//        /*
//            boolean r0 = android.text.TextUtils.isEmpty(r4)
//            r1 = 0
//            if (r0 != 0) goto L_0x005d
//            boolean r0 = android.text.TextUtils.isEmpty(r5)
//            if (r0 == 0) goto L_0x000e
//            goto L_0x005d
//        L_0x000e:
//            r0 = 0
//            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: all -> 0x004d, Exception -> 0x004f
//            r2.<init>()     // Catch: all -> 0x004d, Exception -> 0x004f
//            java.lang.String r3 = "pragma table_info("
//            r2.append(r3)     // Catch: all -> 0x004d, Exception -> 0x004f
//            r2.append(r5)     // Catch: all -> 0x004d, Exception -> 0x004f
//            java.lang.String r5 = ")"
//            r2.append(r5)     // Catch: all -> 0x004d, Exception -> 0x004f
//            java.lang.String r5 = r2.toString()     // Catch: all -> 0x004d, Exception -> 0x004f
//            android.database.Cursor r0 = r6.rawQuery(r5, r0)     // Catch: all -> 0x004d, Exception -> 0x004f
//            boolean r5 = r0.moveToFirst()     // Catch: all -> 0x004d, Exception -> 0x004f
//            if (r5 == 0) goto L_0x0047
//        L_0x002f:
//            java.lang.String r5 = "name"
//            int r5 = r0.getColumnIndexOrThrow(r5)     // Catch: all -> 0x004d, Exception -> 0x004f
//            java.lang.String r5 = r0.getString(r5)     // Catch: all -> 0x004d, Exception -> 0x004f
//            boolean r5 = r4.equalsIgnoreCase(r5)     // Catch: all -> 0x004d, Exception -> 0x004f
//            if (r5 == 0) goto L_0x0041
//            r1 = 1
//            goto L_0x0047
//        L_0x0041:
//            boolean r5 = r0.moveToNext()     // Catch: all -> 0x004d, Exception -> 0x004f
//            if (r5 != 0) goto L_0x002f
//        L_0x0047:
//            if (r0 == 0) goto L_0x0056
//        L_0x0049:
//            r0.close()
//            goto L_0x0056
//        L_0x004d:
//            r4 = move-exception
//            goto L_0x0057
//        L_0x004f:
//            r4 = move-exception
//            r4.printStackTrace()     // Catch: all -> 0x004d
//            if (r0 == 0) goto L_0x0056
//            goto L_0x0049
//        L_0x0056:
//            return r1
//        L_0x0057:
//            if (r0 == 0) goto L_0x005c
//            r0.close()
//        L_0x005c:
//            throw r4
//        L_0x005d:
//            return r1
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.utils.litepal.util.DBUtility.isColumnExists(java.lang.String, java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
//    }

    public static boolean isColumnExists(String str, String str2, SQLiteDatabase sQLiteDatabase) {
        boolean z = false;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        Cursor cursor = null;
        try {
            try {
                cursor = sQLiteDatabase.rawQuery("pragma table_info(" + str2 + ")", null);
                if (cursor.moveToFirst()) {
                    while (true) {
                        if (!str.equalsIgnoreCase(cursor.getString(cursor.getColumnIndexOrThrow("name")))) {
                            if (!cursor.moveToNext()) {
                                break;
                            }
                        } else {
                            z = true;
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0033 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
//    public static List<String> findAllTableNames(SQLiteDatabase r4) {
//        /*
//            java.util.ArrayList r0 = new java.util.ArrayList
//            r0.<init>()
//            r1 = 0
//            java.lang.String r2 = "select * from sqlite_master where type = ?"
//            java.lang.String r3 = "table"
//            java.lang.String[] r3 = new java.lang.String[]{r3}     // Catch: all -> 0x0037, Exception -> 0x0039
//            android.database.Cursor r1 = r4.rawQuery(r2, r3)     // Catch: all -> 0x0037, Exception -> 0x0039
//            boolean r4 = r1.moveToFirst()     // Catch: all -> 0x0037, Exception -> 0x0039
//            if (r4 == 0) goto L_0x0031
//        L_0x0018:
//            java.lang.String r4 = "tbl_name"
//            int r4 = r1.getColumnIndexOrThrow(r4)     // Catch: all -> 0x0037, Exception -> 0x0039
//            java.lang.String r4 = r1.getString(r4)     // Catch: all -> 0x0037, Exception -> 0x0039
//            boolean r2 = r0.contains(r4)     // Catch: all -> 0x0037, Exception -> 0x0039
//            if (r2 != 0) goto L_0x002b
//            r0.add(r4)     // Catch: all -> 0x0037, Exception -> 0x0039
//        L_0x002b:
//            boolean r4 = r1.moveToNext()     // Catch: all -> 0x0037, Exception -> 0x0039
//            if (r4 != 0) goto L_0x0018
//        L_0x0031:
//            if (r1 == 0) goto L_0x0036
//            r1.close()
//        L_0x0036:
//            return r0
//        L_0x0037:
//            r4 = move-exception
//            goto L_0x0047
//        L_0x0039:
//            r4 = move-exception
//            r4.printStackTrace()     // Catch: all -> 0x0037
//            com.slzhibo.library.utils.litepal.exceptions.DatabaseGenerateException r0 = new com.slzhibo.library.utils.litepal.exceptions.DatabaseGenerateException     // Catch: all -> 0x0037
//            java.lang.String r4 = r4.getMessage()     // Catch: all -> 0x0037
//            r0.<init>(r4)     // Catch: all -> 0x0037
//            throw r0     // Catch: all -> 0x0037
//        L_0x0047:
//            if (r1 == 0) goto L_0x004c
//            r1.close()
//        L_0x004c:
//            goto L_0x004e
//        L_0x004d:
//            throw r4
//        L_0x004e:
//            goto L_0x004d
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.utils.litepal.util.DBUtility.findAllTableNames(android.database.sqlite.SQLiteDatabase):java.util.List");
//    }

    public static List<String> findAllTableNames(SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                cursor = sQLiteDatabase.rawQuery("select * from sqlite_master where type = ?", new String[]{"table"});
                if (!cursor.moveToFirst()) {
                    return arrayList;
                }
                do {
                    String string = cursor.getString(cursor.getColumnIndexOrThrow("tbl_name"));
                    if (!arrayList.contains(string)) {
                        arrayList.add(string);
                    }
                } while (cursor.moveToNext());
                return arrayList;
            } catch (Exception e) {
                e.printStackTrace();
                throw new DatabaseGenerateException(e.getMessage());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x008d A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
//    public static com.slzhibo.library.utils.litepal.tablemanager.model.TableModel findPragmaTableInfo(String r7, SQLiteDatabase r8) {
//        /*
//            boolean r0 = isTableExists(r7, r8)
//            if (r0 == 0) goto L_0x00a7
//            java.util.List r0 = findUniqueColumns(r7, r8)
//            com.slzhibo.library.utils.litepal.tablemanager.model.TableModel r1 = new com.slzhibo.library.utils.litepal.tablemanager.model.TableModel
//            r1.<init>()
//            r1.setTableName(r7)
//            java.lang.StringBuilder r2 = new java.lang.StringBuilder
//            r2.<init>()
//            java.lang.String r3 = "pragma table_info("
//            r2.append(r3)
//            r2.append(r7)
//            java.lang.String r7 = ")"
//            r2.append(r7)
//            java.lang.String r7 = r2.toString()
//            r2 = 0
//            android.database.Cursor r2 = r8.rawQuery(r7, r2)     // Catch: all -> 0x0091, Exception -> 0x0093
//            boolean r7 = r2.moveToFirst()     // Catch: all -> 0x0091, Exception -> 0x0093
//            if (r7 == 0) goto L_0x008b
//        L_0x0033:
//            com.slzhibo.library.utils.litepal.tablemanager.model.ColumnModel r7 = new com.slzhibo.library.utils.litepal.tablemanager.model.ColumnModel     // Catch: all -> 0x0091, Exception -> 0x0093
//            r7.<init>()     // Catch: all -> 0x0091, Exception -> 0x0093
//            java.lang.String r8 = "name"
//            int r8 = r2.getColumnIndexOrThrow(r8)     // Catch: all -> 0x0091, Exception -> 0x0093
//            java.lang.String r8 = r2.getString(r8)     // Catch: all -> 0x0091, Exception -> 0x0093
//            java.lang.String r3 = "type"
//            int r3 = r2.getColumnIndexOrThrow(r3)     // Catch: all -> 0x0091, Exception -> 0x0093
//            java.lang.String r3 = r2.getString(r3)     // Catch: all -> 0x0091, Exception -> 0x0093
//            java.lang.String r4 = "notnull"
//            int r4 = r2.getColumnIndexOrThrow(r4)     // Catch: all -> 0x0091, Exception -> 0x0093
//            int r4 = r2.getInt(r4)     // Catch: all -> 0x0091, Exception -> 0x0093
//            r5 = 1
//            if (r4 == r5) goto L_0x005a
//            goto L_0x005b
//        L_0x005a:
//            r5 = 0
//        L_0x005b:
//            boolean r4 = r0.contains(r8)     // Catch: all -> 0x0091, Exception -> 0x0093
//            java.lang.String r6 = "dflt_value"
//            int r6 = r2.getColumnIndexOrThrow(r6)     // Catch: all -> 0x0091, Exception -> 0x0093
//            java.lang.String r6 = r2.getString(r6)     // Catch: all -> 0x0091, Exception -> 0x0093
//            r7.setColumnName(r8)     // Catch: all -> 0x0091, Exception -> 0x0093
//            r7.setColumnType(r3)     // Catch: all -> 0x0091, Exception -> 0x0093
//            r7.setNullable(r5)     // Catch: all -> 0x0091, Exception -> 0x0093
//            r7.setUnique(r4)     // Catch: all -> 0x0091, Exception -> 0x0093
//            java.lang.String r8 = ""
//            if (r6 == 0) goto L_0x007f
//            java.lang.String r3 = "'"
//            java.lang.String r8 = r6.replace(r3, r8)     // Catch: all -> 0x0091, Exception -> 0x0093
//        L_0x007f:
//            r7.setDefaultValue(r8)     // Catch: all -> 0x0091, Exception -> 0x0093
//            r1.addColumnModel(r7)     // Catch: all -> 0x0091, Exception -> 0x0093
//            boolean r7 = r2.moveToNext()     // Catch: all -> 0x0091, Exception -> 0x0093
//            if (r7 != 0) goto L_0x0033
//        L_0x008b:
//            if (r2 == 0) goto L_0x0090
//            r2.close()
//        L_0x0090:
//            return r1
//        L_0x0091:
//            r7 = move-exception
//            goto L_0x00a1
//        L_0x0093:
//            r7 = move-exception
//            r7.printStackTrace()     // Catch: all -> 0x0091
//            com.slzhibo.library.utils.litepal.exceptions.DatabaseGenerateException r8 = new com.slzhibo.library.utils.litepal.exceptions.DatabaseGenerateException     // Catch: all -> 0x0091
//            java.lang.String r7 = r7.getMessage()     // Catch: all -> 0x0091
//            r8.<init>(r7)     // Catch: all -> 0x0091
//            throw r8     // Catch: all -> 0x0091
//        L_0x00a1:
//            if (r2 == 0) goto L_0x00a6
//            r2.close()
//        L_0x00a6:
//            throw r7
//        L_0x00a7:
//            com.slzhibo.library.utils.litepal.exceptions.DatabaseGenerateException r8 = new com.slzhibo.library.utils.litepal.exceptions.DatabaseGenerateException
//            java.lang.StringBuilder r0 = new java.lang.StringBuilder
//            r0.<init>()
//            java.lang.String r1 = "Table doesn't exist when executing "
//            r0.append(r1)
//            r0.append(r7)
//            java.lang.String r7 = r0.toString()
//            r8.<init>(r7)
//            goto L_0x00bf
//        L_0x00be:
//            throw r8
//        L_0x00bf:
//            goto L_0x00be
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.utils.litepal.util.DBUtility.findPragmaTableInfo(java.lang.String, android.database.sqlite.SQLiteDatabase):com.slzhibo.library.utils.litepal.tablemanager.model.TableModel");
//    }

    public static TableModel findPragmaTableInfo(String str, SQLiteDatabase sQLiteDatabase) {
        if (isTableExists(str, sQLiteDatabase)) {
            List<String> findUniqueColumns = findUniqueColumns(str, sQLiteDatabase);
            TableModel tableModel = new TableModel();
            tableModel.setTableName(str);
            Cursor cursor = null;
            try {
                try {
                    cursor = sQLiteDatabase.rawQuery("pragma table_info(" + str + ")", null);
                    if (!cursor.moveToFirst()) {
                        return tableModel;
                    }
                    do {
                        ColumnModel columnModel = new ColumnModel();
                        String string = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                        String string2 = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                        boolean z = true;
                        if (cursor.getInt(cursor.getColumnIndexOrThrow("notnull")) == 1) {
                            z = false;
                        }
                        boolean contains = findUniqueColumns.contains(string);
                        String string3 = cursor.getString(cursor.getColumnIndexOrThrow("dflt_value"));
                        columnModel.setColumnName(string);
                        columnModel.setColumnType(string2);
                        columnModel.setNullable(z);
                        columnModel.setUnique(contains);
                        String str2 = "";
                        if (string3 != null) {
                            str2 = string3.replace("'", str2);
                        }
                        columnModel.setDefaultValue(str2);
                        tableModel.addColumnModel(columnModel);
                    } while (cursor.moveToNext());
                    return tableModel;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new DatabaseGenerateException(e.getMessage());
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        } else {
            throw new DatabaseGenerateException("Table doesn't exist when executing " + str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
//    public static List<String> findUniqueColumns(String r8, SQLiteDatabase r9) {
//        /*
//            java.lang.String r0 = "name"
//            java.lang.String r1 = ")"
//            java.util.ArrayList r2 = new java.util.ArrayList
//            r2.<init>()
//            r3 = 0
//            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: all -> 0x0085, Exception -> 0x0089
//            r4.<init>()     // Catch: all -> 0x0085, Exception -> 0x0089
//            java.lang.String r5 = "pragma index_list("
//            r4.append(r5)     // Catch: all -> 0x0085, Exception -> 0x0089
//            r4.append(r8)     // Catch: all -> 0x0085, Exception -> 0x0089
//            r4.append(r1)     // Catch: all -> 0x0085, Exception -> 0x0089
//            java.lang.String r8 = r4.toString()     // Catch: all -> 0x0085, Exception -> 0x0089
//            android.database.Cursor r8 = r9.rawQuery(r8, r3)     // Catch: all -> 0x0085, Exception -> 0x0089
//            boolean r4 = r8.moveToFirst()     // Catch: all -> 0x007e, Exception -> 0x0081
//            if (r4 == 0) goto L_0x0073
//            r4 = r3
//        L_0x0029:
//            java.lang.String r5 = "unique"
//            int r5 = r8.getColumnIndexOrThrow(r5)     // Catch: all -> 0x006f, Exception -> 0x0071
//            int r5 = r8.getInt(r5)     // Catch: all -> 0x006f, Exception -> 0x0071
//            r6 = 1
//            if (r5 != r6) goto L_0x0067
//            int r5 = r8.getColumnIndexOrThrow(r0)     // Catch: all -> 0x006f, Exception -> 0x0071
//            java.lang.String r5 = r8.getString(r5)     // Catch: all -> 0x006f, Exception -> 0x0071
//            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x006f, Exception -> 0x0071
//            r6.<init>()     // Catch: all -> 0x006f, Exception -> 0x0071
//            java.lang.String r7 = "pragma index_info("
//            r6.append(r7)     // Catch: all -> 0x006f, Exception -> 0x0071
//            r6.append(r5)     // Catch: all -> 0x006f, Exception -> 0x0071
//            r6.append(r1)     // Catch: all -> 0x006f, Exception -> 0x0071
//            java.lang.String r5 = r6.toString()     // Catch: all -> 0x006f, Exception -> 0x0071
//            android.database.Cursor r4 = r9.rawQuery(r5, r3)     // Catch: all -> 0x006f, Exception -> 0x0071
//            boolean r5 = r4.moveToFirst()     // Catch: all -> 0x006f, Exception -> 0x0071
//            if (r5 == 0) goto L_0x0067
//            int r5 = r4.getColumnIndexOrThrow(r0)     // Catch: all -> 0x006f, Exception -> 0x0071
//            java.lang.String r5 = r4.getString(r5)     // Catch: all -> 0x006f, Exception -> 0x0071
//            r2.add(r5)     // Catch: all -> 0x006f, Exception -> 0x0071
//        L_0x0067:
//            boolean r5 = r8.moveToNext()     // Catch: all -> 0x006f, Exception -> 0x0071
//            if (r5 != 0) goto L_0x0029
//            r3 = r4
//            goto L_0x0073
//        L_0x006f:
//            r9 = move-exception
//            goto L_0x009a
//        L_0x0071:
//            r9 = move-exception
//            goto L_0x0083
//        L_0x0073:
//            if (r8 == 0) goto L_0x0078
//            r8.close()
//        L_0x0078:
//            if (r3 == 0) goto L_0x007d
//            r3.close()
//        L_0x007d:
//            return r2
//        L_0x007e:
//            r9 = move-exception
//            r4 = r3
//            goto L_0x009a
//        L_0x0081:
//            r9 = move-exception
//            r4 = r3
//        L_0x0083:
//            r3 = r8
//            goto L_0x008b
//        L_0x0085:
//            r9 = move-exception
//            r8 = r3
//            r4 = r8
//            goto L_0x009a
//        L_0x0089:
//            r9 = move-exception
//            r4 = r3
//        L_0x008b:
//            r9.printStackTrace()     // Catch: all -> 0x0098
//            com.slzhibo.library.utils.litepal.exceptions.DatabaseGenerateException r8 = new com.slzhibo.library.utils.litepal.exceptions.DatabaseGenerateException     // Catch: all -> 0x0098
//            java.lang.String r9 = r9.getMessage()     // Catch: all -> 0x0098
//            r8.<init>(r9)     // Catch: all -> 0x0098
//            throw r8     // Catch: all -> 0x0098
//        L_0x0098:
//            r9 = move-exception
//            r8 = r3
//        L_0x009a:
//            if (r8 == 0) goto L_0x009f
//            r8.close()
//        L_0x009f:
//            if (r4 == 0) goto L_0x00a4
//            r4.close()
//        L_0x00a4:
//            goto L_0x00a6
//        L_0x00a5:
//            throw r9
//        L_0x00a6:
//            goto L_0x00a5
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.utils.litepal.util.DBUtility.findUniqueColumns(java.lang.String, android.database.sqlite.SQLiteDatabase):java.util.List");
//    }

    public static List<String> findUniqueColumns(String str, SQLiteDatabase sQLiteDatabase) {
        Exception e;
        Cursor cursor;
        Cursor cursor2;
        ArrayList arrayList = new ArrayList();
        Cursor cursor3 = null;

            cursor2 = sQLiteDatabase.rawQuery("pragma index_list(" + str + ")", null);

                if (cursor2.moveToFirst()) {
                    cursor = null;
                    do {
                        try {
                            if (cursor2.getInt(cursor2.getColumnIndexOrThrow("unique")) == 1) {
                                cursor = sQLiteDatabase.rawQuery("pragma index_info(" + cursor2.getString(cursor2.getColumnIndexOrThrow("name")) + ")", null);
                                if (cursor.moveToFirst()) {
                                    arrayList.add(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            cursor3 = cursor2;
                            try {
                                e.printStackTrace();
                                throw new DatabaseGenerateException(e.getMessage());
                            } catch (Throwable th) {
                                th = th;
                                cursor2 = cursor3;
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                if (cursor != null) {
                                    cursor.close();
                                }
//                                throw th;
                            }
                        }
                    } while (cursor2.moveToNext());
                    cursor3 = cursor;
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (cursor3 != null) {
                    cursor3.close();
                }
                return arrayList;


    }

    public static boolean isFieldNameConflictWithSQLiteKeywords(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return SQLITE_KEYWORDS.contains("," + str.toLowerCase(Locale.US) + ",");
    }

    public static String convertToValidColumnName(String str) {
        if (!isFieldNameConflictWithSQLiteKeywords(str)) {
            return str;
        }
        return str + KEYWORDS_COLUMN_SUFFIX;
    }

    public static String convertWhereClauseToColumnName(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                Matcher matcher = Pattern.compile("(\\w+\\s*(=|!=|<>|<|>)|\\w+\\s+(not\\s+)?(like|between)\\s+|\\w+\\s+(not\\s+)?(in)\\s*\\()").matcher(str);
                while (matcher.find()) {
                    String group = matcher.group();
                    String replaceAll = group.replaceAll("(\\s*(=|!=|<>|<|>)|\\s+(not\\s+)?(like|between)\\s+|\\s+(not\\s+)?(in)\\s*\\()", "");
                    String replace = group.replace(replaceAll, "");
                    String convertToValidColumnName = convertToValidColumnName(replaceAll);
                    matcher.appendReplacement(stringBuffer, convertToValidColumnName + replace);
                }
                matcher.appendTail(stringBuffer);
                return stringBuffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static String[] convertSelectClauseToValidNames(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr2[i] = convertToValidColumnName(strArr[i]);
        }
        return strArr2;
    }

    public static String convertOrderByClauseToValidName(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String lowerCase = str.trim().toLowerCase(Locale.US);
        if (!lowerCase.contains(",")) {
            return convertOrderByItem(lowerCase);
        }
        String[] split = lowerCase.split(",");
        StringBuilder sb = new StringBuilder();
        int length = split.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            String str2 = split[i];
            if (z) {
                sb.append(",");
            }
            sb.append(convertOrderByItem(str2));
            i++;
            z = true;
        }
        return sb.toString();
    }

    private static String convertOrderByItem(String str) {
        String str2 = "";
        if (str.endsWith("asc")) {
            str = str.replace("asc", str2).trim();
            str2 = " asc";
        } else if (str.endsWith("desc")) {
            str = str.replace("desc", str2).trim();
            str2 = " desc";
        }
        return convertToValidColumnName(str) + str2;
    }
}
