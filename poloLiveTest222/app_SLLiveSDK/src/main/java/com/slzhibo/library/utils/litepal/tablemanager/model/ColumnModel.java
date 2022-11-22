package com.slzhibo.library.utils.litepal.tablemanager.model;

import android.text.TextUtils;

//import com.amazonaws.mobileconnectors.s3.transferutility.TransferTable;

/* loaded from: classes12.dex */
public class ColumnModel {
    private String columnName;
    private String columnType;
    private boolean isNullable = true;
    private boolean isUnique = false;
    private String defaultValue = "";

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String str) {
        this.columnName = str;
    }

    public String getColumnType() {
        return this.columnType;
    }

    public void setColumnType(String str) {
        this.columnType = str;
    }

    public boolean isNullable() {
        return this.isNullable;
    }

    public void setNullable(boolean z) {
        this.isNullable = z;
    }

    public boolean isUnique() {
        return this.isUnique;
    }

    public void setUnique(boolean z) {
        this.isUnique = z;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String str) {
        if (!"text".equalsIgnoreCase(this.columnType)) {
            this.defaultValue = str;
        } else if (!TextUtils.isEmpty(str)) {
            this.defaultValue = "'" + str + "'";
        }
    }

    public boolean isIdColumn() {
        return "_id".equalsIgnoreCase(this.columnName) || "id".equalsIgnoreCase(this.columnName);
    }
}
