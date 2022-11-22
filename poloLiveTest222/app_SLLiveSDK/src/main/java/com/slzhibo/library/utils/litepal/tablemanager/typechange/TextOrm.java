package com.slzhibo.library.utils.litepal.tablemanager.typechange;

/* loaded from: classes12.dex */
public class TextOrm extends OrmChange {
    @Override // com.slzhibo.library.utils.litepal.tablemanager.typechange.OrmChange
    public String object2Relation(String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("char") || str.equals("java.lang.Character") || str.equals("java.lang.String")) {
            return "text";
        }
        return null;
    }
}
