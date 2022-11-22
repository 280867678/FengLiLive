package com.slzhibo.library.utils.litepal.tablemanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

//import com.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.litepal.exceptions.DatabaseGenerateException;
import com.slzhibo.library.utils.litepal.tablemanager.model.AssociationsModel;
import com.slzhibo.library.utils.litepal.tablemanager.model.ColumnModel;
import com.slzhibo.library.utils.litepal.tablemanager.model.GenericModel;
import com.slzhibo.library.utils.litepal.util.BaseUtility;
import com.slzhibo.library.utils.litepal.util.Const;
import com.slzhibo.library.utils.litepal.util.DBUtility;
import com.slzhibo.library.utils.litepal.util.LitePalLog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/* loaded from: classes12.dex */
public abstract class AssociationCreator extends Generator {
    @Override // com.slzhibo.library.utils.litepal.tablemanager.Generator
    protected abstract void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.slzhibo.library.utils.litepal.tablemanager.Generator
    public void addOrUpdateAssociation(SQLiteDatabase sQLiteDatabase, boolean z) {
        try {
            addAssociations(getAllAssociations(), sQLiteDatabase, z);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String generateCreateTableSQL(String str, List<ColumnModel> list, boolean z) {
        StringBuilder sb = new StringBuilder("create table ");
        sb.append(str);
        sb.append(" (");
        if (z) {
            sb.append("id integer primary key autoincrement,");
        }
        if (isContainsOnlyIdField(list)) {
            sb.deleteCharAt(sb.length() - 1);
        }
        boolean z2 = false;
        for (ColumnModel columnModel : list) {
            if (!columnModel.isIdColumn()) {
                if (z2) {
                    sb.append(", ");
                }
                sb.append(columnModel.getColumnName());
                sb.append(ConstantUtils.PLACEHOLDER_STR_ONE);
                sb.append(columnModel.getColumnType());
                if (!columnModel.isNullable()) {
                    sb.append(" not null");
                }
                if (columnModel.isUnique()) {
                    sb.append(" unique");
                }
                String defaultValue = columnModel.getDefaultValue();
                if (!TextUtils.isEmpty(defaultValue)) {
                    sb.append(" default ");
                    sb.append(defaultValue);
                }
                z2 = true;
            }
        }
        sb.append(")");
        LitePalLog.d(Generator.TAG, "create table sql is >> " + ((Object) sb));
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String generateDropTableSQL(String str) {
        return "drop table if exists " + str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String generateAddColumnSQL(String str, ColumnModel columnModel) {
        StringBuilder sb = new StringBuilder();
        sb.append("alter table ");
        sb.append(str);
        sb.append(" add column ");
        sb.append(columnModel.getColumnName());
        sb.append(ConstantUtils.PLACEHOLDER_STR_ONE);
        sb.append(columnModel.getColumnType());
        if (!columnModel.isNullable()) {
            sb.append(" not null");
        }
        if (columnModel.isUnique()) {
            sb.append(" unique");
        }
        String defaultValue = columnModel.getDefaultValue();
        if (!TextUtils.isEmpty(defaultValue)) {
            sb.append(" default ");
            sb.append(defaultValue);
        } else if (!columnModel.isNullable()) {
            if ("integer".equalsIgnoreCase(columnModel.getColumnType())) {
                defaultValue = "0";
            } else if ("text".equalsIgnoreCase(columnModel.getColumnType())) {
                defaultValue = "''";
            } else if ("real".equalsIgnoreCase(columnModel.getColumnType())) {
                defaultValue = "0.0";
            }
            sb.append(" default ");
            sb.append(defaultValue);
        }
        LitePalLog.d(Generator.TAG, "add column sql is >> " + ((Object) sb));
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isForeignKeyColumnFormat(String str) {
        return !TextUtils.isEmpty(str) && str.toLowerCase(Locale.US).endsWith("_id") && !str.equalsIgnoreCase("_id");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void giveTableSchemaACopy(String str, int i, SQLiteDatabase sQLiteDatabase) throws Throwable {
        Throwable th;
        Exception e;
        StringBuilder sb = new StringBuilder("select * from ");
        sb.append(Const.TableSchema.TABLE_NAME);
        LitePalLog.d(Generator.TAG, "giveTableSchemaACopy SQL is >> " + ((Object) sb));
        Cursor cursor = null;
        try {
            try {
                cursor = sQLiteDatabase.rawQuery(sb.toString(), null);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        try {
            if (isNeedtoGiveACopy(cursor, str)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", BaseUtility.changeCase(str));
                contentValues.put("type", Integer.valueOf(i));
                sQLiteDatabase.insert(Const.TableSchema.TABLE_NAME, null, contentValues);
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e3) {
            e = e3;
            cursor = cursor;
            e.printStackTrace();
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private boolean isNeedtoGiveACopy(Cursor cursor, String str) {
        return !isValueExists(cursor, str) && !isSpecialTable(str);
    }

    private boolean isValueExists(Cursor cursor, String str) {
        if (!cursor.moveToFirst()) {
            return false;
        }
        while (!cursor.getString(cursor.getColumnIndexOrThrow("name")).equalsIgnoreCase(str)) {
            if (!cursor.moveToNext()) {
                return false;
            }
        }
        return true;
    }

    private boolean isSpecialTable(String str) {
        return Const.TableSchema.TABLE_NAME.equalsIgnoreCase(str);
    }

    private void addAssociations(Collection<AssociationsModel> collection, SQLiteDatabase sQLiteDatabase, boolean z) throws Throwable {
        for (AssociationsModel associationsModel : collection) {
            if (2 == associationsModel.getAssociationType() || 1 == associationsModel.getAssociationType()) {
                addForeignKeyColumn(associationsModel.getTableName(), associationsModel.getAssociatedTableName(), associationsModel.getTableHoldsForeignKey(), sQLiteDatabase);
            } else if (3 == associationsModel.getAssociationType()) {
                createIntermediateTable(associationsModel.getTableName(), associationsModel.getAssociatedTableName(), sQLiteDatabase, z);
            }
        }
        for (GenericModel genericModel : getGenericModels()) {
            createGenericTable(genericModel, sQLiteDatabase, z);
        }
    }

    private void createIntermediateTable(String str, String str2, SQLiteDatabase sQLiteDatabase, boolean z) throws Throwable {
        ArrayList arrayList = new ArrayList();
        ColumnModel columnModel = new ColumnModel();
        columnModel.setColumnName(str + "_id");
        columnModel.setColumnType("integer");
        ColumnModel columnModel2 = new ColumnModel();
        columnModel2.setColumnName(str2 + "_id");
        columnModel2.setColumnType("integer");
        arrayList.add(columnModel);
        arrayList.add(columnModel2);
        String intermediateTableName = DBUtility.getIntermediateTableName(str, str2);
        ArrayList arrayList2 = new ArrayList();
        if (!DBUtility.isTableExists(intermediateTableName, sQLiteDatabase)) {
            arrayList2.add(generateCreateTableSQL(intermediateTableName, arrayList, false));
        } else if (z) {
            arrayList2.add(generateDropTableSQL(intermediateTableName));
            arrayList2.add(generateCreateTableSQL(intermediateTableName, arrayList, false));
        }
        execute(arrayList2, sQLiteDatabase);
        giveTableSchemaACopy(intermediateTableName, 1, sQLiteDatabase);
    }

    private void createGenericTable(GenericModel genericModel, SQLiteDatabase sQLiteDatabase, boolean z) {
        String tableName = genericModel.getTableName();
        String valueColumnName = genericModel.getValueColumnName();
        String valueColumnType = genericModel.getValueColumnType();
        String valueIdColumnName = genericModel.getValueIdColumnName();
        ArrayList arrayList = new ArrayList();
        ColumnModel columnModel = new ColumnModel();
        columnModel.setColumnName(valueColumnName);
        columnModel.setColumnType(valueColumnType);
        ColumnModel columnModel2 = new ColumnModel();
        columnModel2.setColumnName(valueIdColumnName);
        columnModel2.setColumnType("integer");
        arrayList.add(columnModel);
        arrayList.add(columnModel2);
        ArrayList arrayList2 = new ArrayList();
        if (!DBUtility.isTableExists(tableName, sQLiteDatabase)) {
            arrayList2.add(generateCreateTableSQL(tableName, arrayList, false));
        } else if (z) {
            arrayList2.add(generateDropTableSQL(tableName));
            arrayList2.add(generateCreateTableSQL(tableName, arrayList, false));
        }
        execute(arrayList2, sQLiteDatabase);
        try {
            giveTableSchemaACopy(tableName, 2, sQLiteDatabase);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addForeignKeyColumn(String str, String str2, String str3, SQLiteDatabase sQLiteDatabase) {
        if (!DBUtility.isTableExists(str, sQLiteDatabase)) {
            throw new DatabaseGenerateException(DatabaseGenerateException.TABLE_DOES_NOT_EXIST + str);
        } else if (DBUtility.isTableExists(str2, sQLiteDatabase)) {
            String str4 = null;
            if (str.equals(str3)) {
                str4 = getForeignKeyColumnName(str2);
            } else if (str2.equals(str3)) {
                str4 = getForeignKeyColumnName(str);
            }
            if (!DBUtility.isColumnExists(str4, str3, sQLiteDatabase)) {
                ColumnModel columnModel = new ColumnModel();
                columnModel.setColumnName(str4);
                columnModel.setColumnType("integer");
                ArrayList arrayList = new ArrayList();
                arrayList.add(generateAddColumnSQL(str3, columnModel));
                execute(arrayList, sQLiteDatabase);
                return;
            }
            LitePalLog.d(Generator.TAG, "column " + str4 + " is already exist, no need to add one");
        } else {
            throw new DatabaseGenerateException(DatabaseGenerateException.TABLE_DOES_NOT_EXIST + str2);
        }
    }

    private boolean isContainsOnlyIdField(List<ColumnModel> list) {
        return list.size() == 0 || (list.size() == 1 && isIdColumn(list.get(0).getColumnName())) || (list.size() == 2 && isIdColumn(list.get(0).getColumnName()) && isIdColumn(list.get(1).getColumnName()));
    }
}
