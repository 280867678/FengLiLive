package com.slzhibo.library.utils.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;

import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.litepal.parser.LitePalAttr;
import com.slzhibo.library.utils.litepal.tablemanager.model.AssociationsModel;
import com.slzhibo.library.utils.litepal.tablemanager.model.ColumnModel;
import com.slzhibo.library.utils.litepal.tablemanager.model.GenericModel;
import com.slzhibo.library.utils.litepal.tablemanager.model.TableModel;
import com.slzhibo.library.utils.litepal.util.BaseUtility;
import com.slzhibo.library.utils.litepal.util.Const;
import com.slzhibo.library.utils.litepal.util.DBUtility;
import com.slzhibo.library.utils.litepal.util.LitePalLog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes12.dex */
public abstract class AssociationUpdater extends Creator {
    public static final String TAG = "AssociationUpdater";
    private Collection<AssociationsModel> mAssociationModels;
    protected SQLiteDatabase mDb;

    @Override // com.slzhibo.library.utils.litepal.tablemanager.Creator, com.slzhibo.library.utils.litepal.tablemanager.AssociationCreator, com.slzhibo.library.utils.litepal.tablemanager.Generator
    public abstract void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.slzhibo.library.utils.litepal.tablemanager.AssociationCreator, com.slzhibo.library.utils.litepal.tablemanager.Generator
    public void addOrUpdateAssociation(SQLiteDatabase sQLiteDatabase, boolean z) {
        this.mAssociationModels = getAllAssociations();
        this.mDb = sQLiteDatabase;
        removeAssociations();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<String> getForeignKeyColumns(TableModel tableModel) {
        ArrayList arrayList = new ArrayList();
        for (ColumnModel columnModel : getTableModelFromDB(tableModel.getTableName()).getColumnModels()) {
            String columnName = columnModel.getColumnName();
            if (isForeignKeyColumnFormat(columnModel.getColumnName()) && !tableModel.containsColumn(columnName)) {
                LitePalLog.d(TAG, "getForeignKeyColumnNames >> foreign key column is " + columnName);
                arrayList.add(columnName);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isForeignKeyColumn(TableModel tableModel, String str) {
        return BaseUtility.containsIgnoreCases(getForeignKeyColumns(tableModel), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TableModel getTableModelFromDB(String str) {
        return DBUtility.findPragmaTableInfo(str, this.mDb);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void dropTables(List<String> list, SQLiteDatabase sQLiteDatabase) {
        if (!(list == null || list.isEmpty())) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(generateDropTableSQL(list.get(i)));
            }
            execute(arrayList, sQLiteDatabase);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeColumns(Collection<String> collection, String str) {
        if (collection != null && !collection.isEmpty()) {
            execute(getRemoveColumnSQLs(collection, str), this.mDb);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void clearCopyInTableSchema(List<String> list) {
        if (!(list == null || list.isEmpty())) {
            StringBuilder sb = new StringBuilder("delete from ");
            sb.append(Const.TableSchema.TABLE_NAME);
            sb.append(" where");
            boolean z = false;
            for (String str : list) {
                if (z) {
                    sb.append(" or ");
                }
                z = true;
                sb.append(" lower(");
                sb.append("name");
                sb.append(") ");
                sb.append("=");
                sb.append(" lower('");
                sb.append(str);
                sb.append("')");
            }
            LitePalLog.d(TAG, "clear table schema value sql is " + ((Object) sb));
            ArrayList arrayList = new ArrayList();
            arrayList.add(sb.toString());
            execute(arrayList, this.mDb);
        }
    }

    private void removeAssociations() {
        removeForeignKeyColumns();
        removeIntermediateTables();
        removeGenericTables();
    }

    private void removeForeignKeyColumns() {
        for (String str : LitePalAttr.getInstance().getClassNames()) {
            TableModel tableModel = getTableModel(str);
            removeColumns(findForeignKeyToRemove(tableModel), tableModel.getTableName());
        }
    }

    private void removeIntermediateTables() {
        List<String> findIntermediateTablesToDrop = findIntermediateTablesToDrop();
        dropTables(findIntermediateTablesToDrop, this.mDb);
        clearCopyInTableSchema(findIntermediateTablesToDrop);
    }

    private void removeGenericTables() {
        List<String> findGenericTablesToDrop = findGenericTablesToDrop();
        dropTables(findGenericTablesToDrop, this.mDb);
        clearCopyInTableSchema(findGenericTablesToDrop);
    }

    private List<String> findForeignKeyToRemove(TableModel tableModel) {
        ArrayList arrayList = new ArrayList();
        List<String> foreignKeyColumns = getForeignKeyColumns(tableModel);
        String tableName = tableModel.getTableName();
        for (String str : foreignKeyColumns) {
            if (shouldDropForeignKey(tableName, DBUtility.getTableNameByForeignColumn(str))) {
                arrayList.add(str);
            }
        }
        LitePalLog.d(TAG, "findForeignKeyToRemove >> " + tableModel.getTableName() + ConstantUtils.PLACEHOLDER_STR_ONE + arrayList);
        return arrayList;
    }

    private List<String> findIntermediateTablesToDrop() {
        ArrayList arrayList = new ArrayList();
        for (String str : DBUtility.findAllTableNames(this.mDb)) {
            if (DBUtility.isIntermediateTable(str, this.mDb)) {
                boolean z = true;
                for (AssociationsModel associationsModel : this.mAssociationModels) {
                    if (associationsModel.getAssociationType() == 3 && str.equalsIgnoreCase(DBUtility.getIntermediateTableName(associationsModel.getTableName(), associationsModel.getAssociatedTableName()))) {
                        z = false;
                    }
                }
                if (z) {
                    arrayList.add(str);
                }
            }
        }
        LitePalLog.d(TAG, "findIntermediateTablesToDrop >> " + arrayList);
        return arrayList;
    }

    private List<String> findGenericTablesToDrop() {
        ArrayList arrayList = new ArrayList();
        for (String str : DBUtility.findAllTableNames(this.mDb)) {
            if (DBUtility.isGenericTable(str, this.mDb)) {
                boolean z = true;
                for (GenericModel genericModel : getGenericModels()) {
                    if (str.equalsIgnoreCase(genericModel.getTableName())) {
                        z = false;
                    }
                }
                if (z) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String generateAlterToTempTableSQL(String str) {
        return "alter table " + str + " rename to " + getTempTableName(str);
    }

    private String generateCreateNewTableSQL(Collection<String> collection, TableModel tableModel) {
        for (String str : collection) {
            tableModel.removeColumnModelByName(str);
        }
        return generateCreateTableSQL(tableModel);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String generateDataMigrationSQL(TableModel tableModel) {
        String tableName = tableModel.getTableName();
        List<ColumnModel> columnModels = tableModel.getColumnModels();
        if (columnModels.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ");
        sb.append(tableName);
        sb.append("(");
        boolean z = false;
        boolean z2 = false;
        for (ColumnModel columnModel : columnModels) {
            if (z2) {
                sb.append(", ");
            }
            sb.append(columnModel.getColumnName());
            z2 = true;
        }
        sb.append(") ");
        sb.append("select ");
        for (ColumnModel columnModel2 : columnModels) {
            if (z) {
                sb.append(", ");
            }
            sb.append(columnModel2.getColumnName());
            z = true;
        }
        sb.append(" from ");
        sb.append(getTempTableName(tableName));
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String generateDropTempTableSQL(String str) {
        return generateDropTableSQL(getTempTableName(str));
    }

    protected String getTempTableName(String str) {
        return str + "_temp";
    }

    private List<String> getRemoveColumnSQLs(Collection<String> collection, String str) {
        TableModel tableModelFromDB = getTableModelFromDB(str);
        String generateAlterToTempTableSQL = generateAlterToTempTableSQL(str);
        LitePalLog.d(TAG, "generateRemoveColumnSQL >> " + generateAlterToTempTableSQL);
        String generateCreateNewTableSQL = generateCreateNewTableSQL(collection, tableModelFromDB);
        LitePalLog.d(TAG, "generateRemoveColumnSQL >> " + generateCreateNewTableSQL);
        String generateDataMigrationSQL = generateDataMigrationSQL(tableModelFromDB);
        LitePalLog.d(TAG, "generateRemoveColumnSQL >> " + generateDataMigrationSQL);
        String generateDropTempTableSQL = generateDropTempTableSQL(str);
        LitePalLog.d(TAG, "generateRemoveColumnSQL >> " + generateDropTempTableSQL);
        ArrayList arrayList = new ArrayList();
        arrayList.add(generateAlterToTempTableSQL);
        arrayList.add(generateCreateNewTableSQL);
        arrayList.add(generateDataMigrationSQL);
        arrayList.add(generateDropTempTableSQL);
        return arrayList;
    }

    private boolean shouldDropForeignKey(String str, String str2) {
        for (AssociationsModel associationsModel : this.mAssociationModels) {
            if (associationsModel.getAssociationType() == 1) {
                if (!str.equalsIgnoreCase(associationsModel.getTableHoldsForeignKey())) {
                    continue;
                } else if (associationsModel.getTableName().equalsIgnoreCase(str)) {
                    if (isRelationCorrect(associationsModel, str, str2)) {
                        return false;
                    }
                } else if (associationsModel.getAssociatedTableName().equalsIgnoreCase(str) && isRelationCorrect(associationsModel, str2, str)) {
                    return false;
                }
            } else if (associationsModel.getAssociationType() == 2 && isRelationCorrect(associationsModel, str2, str)) {
                return false;
            }
        }
        return true;
    }

    private boolean isRelationCorrect(AssociationsModel associationsModel, String str, String str2) {
        return associationsModel.getTableName().equalsIgnoreCase(str) && associationsModel.getAssociatedTableName().equalsIgnoreCase(str2);
    }
}
