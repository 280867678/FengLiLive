package com.slzhibo.library.utils.litepal;

import com.slzhibo.library.utils.litepal.parser.LitePalConfig;
import com.slzhibo.library.utils.litepal.parser.LitePalParser;
import com.slzhibo.library.utils.litepal.util.Const;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class LitePalDB {
    private List<String> classNames;
    private String dbName;
    private boolean isExternalStorage = false;
    private String storage;
    private int version;

    public static LitePalDB fromDefault(String str) {
        LitePalConfig parseLitePalConfiguration = LitePalParser.parseLitePalConfiguration();
        LitePalDB litePalDB = new LitePalDB(str, parseLitePalConfiguration.getVersion());
        litePalDB.setStorage(parseLitePalConfiguration.getStorage());
        litePalDB.setClassNames(parseLitePalConfiguration.getClassNames());
        return litePalDB;
    }

    public LitePalDB(String str, int i) {
        this.dbName = str;
        this.version = i;
    }

    public int getVersion() {
        return this.version;
    }

    public String getDbName() {
        return this.dbName;
    }

    public String getStorage() {
        return this.storage;
    }

    public void setStorage(String str) {
        this.storage = str;
    }

    public boolean isExternalStorage() {
        return this.isExternalStorage;
    }

    public void setExternalStorage(boolean z) {
        this.isExternalStorage = z;
    }

    public List<String> getClassNames() {
        List<String> list = this.classNames;
        if (list == null) {
            this.classNames = new ArrayList();
            this.classNames.add(Const.Utils.TABLE_SCHEMA_CLASS_NAME);
        } else if (list.isEmpty()) {
            this.classNames.add(Const.Utils.TABLE_SCHEMA_CLASS_NAME);
        }
        return this.classNames;
    }

    public void addClassName(String str) {
        getClassNames().add(str);
    }

    void setClassNames(List<String> list) {
        this.classNames = list;
    }
}
