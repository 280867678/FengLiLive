package com.slzhibo.library.utils.litepal.tablemanager.typechange;

/* loaded from: classes12.dex */
public class DecimalOrm extends OrmChange {
    @Override // com.slzhibo.library.utils.litepal.tablemanager.typechange.OrmChange
    public String object2Relation(String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("float") || str.equals("java.lang.Float") || str.equals("double") || str.equals("java.lang.Double")) {
            return "real";
        }
        return null;
    }
}
