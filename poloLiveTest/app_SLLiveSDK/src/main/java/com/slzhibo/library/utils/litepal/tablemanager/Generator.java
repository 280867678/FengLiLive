package com.slzhibo.library.utils.litepal.tablemanager;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.slzhibo.library.utils.litepal.LitePalBase;
import com.slzhibo.library.utils.litepal.exceptions.DatabaseGenerateException;
import com.slzhibo.library.utils.litepal.parser.LitePalAttr;
import com.slzhibo.library.utils.litepal.tablemanager.model.AssociationsModel;
import com.slzhibo.library.utils.litepal.tablemanager.model.TableModel;
import com.slzhibo.library.utils.litepal.util.BaseUtility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes12.dex */
public abstract class Generator extends LitePalBase {
    public static final String TAG = "Generator";
    private Collection<AssociationsModel> mAllRelationModels;
    private Collection<TableModel> mTableModels;

    protected abstract void addOrUpdateAssociation(SQLiteDatabase sQLiteDatabase, boolean z);

    protected abstract void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z);

    /* JADX INFO: Access modifiers changed from: protected */
    public Collection<TableModel> getAllTableModels() {
        if (this.mTableModels == null) {
            this.mTableModels = new ArrayList();
        }
        if (!canUseCache()) {
            this.mTableModels.clear();
            for (String str : LitePalAttr.getInstance().getClassNames()) {
                this.mTableModels.add(getTableModel(str));
            }
        }
        return this.mTableModels;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Collection<AssociationsModel> getAllAssociations() {
        Collection<AssociationsModel> collection = this.mAllRelationModels;
        if (collection == null || collection.isEmpty()) {
            this.mAllRelationModels = getAssociations(LitePalAttr.getInstance().getClassNames());
        }
        return this.mAllRelationModels;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void execute(List<String> list, SQLiteDatabase sQLiteDatabase) {
        String str = "";
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    for (String str2 : list) {
                        if (!TextUtils.isEmpty(str2)) {
                            str = BaseUtility.changeCase(str2);
                            sQLiteDatabase.execSQL(str);
                        }
                    }
                }
            } catch (SQLException unused) {
                throw new DatabaseGenerateException(DatabaseGenerateException.SQL_ERROR + str);
            }
        }
    }

    private static void addAssociation(SQLiteDatabase sQLiteDatabase, boolean z) {
        new Creator().addOrUpdateAssociation(sQLiteDatabase, z);
    }

    private static void updateAssociations(SQLiteDatabase sQLiteDatabase) {
        new Upgrader().addOrUpdateAssociation(sQLiteDatabase, false);
    }

    private static void upgradeTables(SQLiteDatabase sQLiteDatabase) {
        new Upgrader().createOrUpgradeTable(sQLiteDatabase, false);
    }

    private static void create(SQLiteDatabase sQLiteDatabase, boolean z) {
        new Creator().createOrUpgradeTable(sQLiteDatabase, z);
    }

    private static void drop(SQLiteDatabase sQLiteDatabase) {
        new Dropper().createOrUpgradeTable(sQLiteDatabase, false);
    }

    private boolean canUseCache() {
        Collection<TableModel> collection = this.mTableModels;
        return collection != null && collection.size() == LitePalAttr.getInstance().getClassNames().size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void create(SQLiteDatabase sQLiteDatabase) {
        create(sQLiteDatabase, true);
        addAssociation(sQLiteDatabase, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void upgrade(SQLiteDatabase sQLiteDatabase) {
        drop(sQLiteDatabase);
        create(sQLiteDatabase, false);
        updateAssociations(sQLiteDatabase);
        upgradeTables(sQLiteDatabase);
        addAssociation(sQLiteDatabase, false);
    }
}
