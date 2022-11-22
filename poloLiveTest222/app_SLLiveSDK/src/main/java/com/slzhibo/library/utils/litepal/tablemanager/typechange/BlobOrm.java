package com.slzhibo.library.utils.litepal.tablemanager.typechange;

/* loaded from: classes12.dex */
public class BlobOrm extends OrmChange {
    @Override // com.slzhibo.library.utils.litepal.tablemanager.typechange.OrmChange
    public String object2Relation(String str) {
        if (str == null || !str.equals("[B")) {
            return null;
        }
        return "blob";
    }
}
