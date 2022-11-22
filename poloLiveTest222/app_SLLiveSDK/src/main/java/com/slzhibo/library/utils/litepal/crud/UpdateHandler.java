package com.slzhibo.library.utils.litepal.crud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import com.slzhibo.library.utils.litepal.Operator;
import com.slzhibo.library.utils.litepal.annotation.Encrypt;
import com.slzhibo.library.utils.litepal.exceptions.LitePalSupportException;
import com.slzhibo.library.utils.litepal.util.BaseUtility;
import com.slzhibo.library.utils.litepal.util.DBUtility;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes12.dex */
public class UpdateHandler extends DataHandler {
    public UpdateHandler(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int onUpdate(LitePalSupport litePalSupport, long j) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Field> supportedFields = getSupportedFields(litePalSupport.getClassName());
        updateGenericTables(litePalSupport, getSupportedGenericFields(litePalSupport.getClassName()), j);
        ContentValues contentValues = new ContentValues();
        putFieldsValue(litePalSupport, supportedFields, contentValues);
        putFieldsToDefaultValue(litePalSupport, contentValues, j);
        if (contentValues.size() <= 0) {
            return 0;
        }
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        String tableName = litePalSupport.getTableName();
        return sQLiteDatabase.update(tableName, contentValues, "id = " + j, null);
    }

    public int onUpdate(Class<?> cls, long j, ContentValues contentValues) {
        if (contentValues.size() <= 0) {
            return 0;
        }
        convertContentValues(contentValues);
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        String tableName = getTableName(cls);
        return sQLiteDatabase.update(tableName, contentValues, "id = " + j, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int onUpdateAll(LitePalSupport litePalSupport, String... strArr) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        List<Field> supportedFields = getSupportedFields(litePalSupport.getClassName());
        List<Field> supportedGenericFields = getSupportedGenericFields(litePalSupport.getClassName());
        long[] jArr = null;
        if (!supportedGenericFields.isEmpty()) {
            List find = Operator.select("id").where(strArr).find(litePalSupport.getClass());
            if (find.size() > 0) {
                jArr = new long[find.size()];
                for (int i = 0; i < jArr.length; i++) {
                    jArr[i] = ((LitePalSupport) find.get(i)).getBaseObjId();
                }
                updateGenericTables(litePalSupport, supportedGenericFields, jArr);
            }
        }
        ContentValues contentValues = new ContentValues();
        putFieldsValue(litePalSupport, supportedFields, contentValues);
        putFieldsToDefaultValue(litePalSupport, contentValues, jArr);
        return doUpdateAllAction(litePalSupport.getTableName(), contentValues, strArr);
    }

    public int onUpdateAll(String str, ContentValues contentValues, String... strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        convertContentValues(contentValues);
        return doUpdateAllAction(str, contentValues, strArr);
    }

    private int doUpdateAllAction(String str, ContentValues contentValues, String... strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (contentValues.size() > 0) {
//            Log.e("UpdateHandler666666:",str);
//            Log.e("UpdateHandler666666:",contentValues.toString());
//            Log.e("UpdateHandler666666:",getWhereClause(strArr));
//            Log.e("UpdateHandler666666:", String.valueOf(getWhereArgs(strArr)));
            return this.mDatabase.update(str, contentValues, getWhereClause(strArr), getWhereArgs(strArr));
        }
        return 0;
    }

    private void putFieldsToDefaultValue(LitePalSupport litePalSupport, ContentValues contentValues, long... jArr) {
        String str;
        NoSuchFieldException e;
        try {
            LitePalSupport emptyModel = getEmptyModel(litePalSupport);
            Class<?> cls = emptyModel.getClass();
            Iterator<String> it2 = litePalSupport.getFieldsToSetToDefault().iterator();
            String str2 = null;
            while (it2.hasNext()) {
                str = it2.next();
                if (!isIdColumn(str)) {
                    try {
                        Field declaredField = cls.getDeclaredField(str);
                        if (!isCollection(declaredField.getType())) {
                            putContentValuesForUpdate(emptyModel, declaredField, contentValues);
                        } else if (jArr != null && jArr.length > 0 && BaseUtility.isGenericTypeSupported(getGenericTypeName(declaredField))) {
                            String genericTableName = DBUtility.getGenericTableName(litePalSupport.getClassName(), declaredField.getName());
                            String genericValueIdColumnName = DBUtility.getGenericValueIdColumnName(litePalSupport.getClassName());
                            StringBuilder sb = new StringBuilder();
                            int length = jArr.length;
                            int i = 0;
                            boolean z = false;
                            while (i < length) {
                                long j = jArr[i];
                                if (z) {
                                    sb.append(" or ");
                                }
                                sb.append(genericValueIdColumnName);
                                sb.append(" = ");
                                sb.append(j);
                                i++;
                                z = true;
                            }
                            this.mDatabase.delete(genericTableName, sb.toString(), null);
                        }
                        str2 = str;
                    } catch (NoSuchFieldException e2) {
                        e = e2;
                        throw new LitePalSupportException(LitePalSupportException.noSuchFieldExceptioin(litePalSupport.getClassName(), str), e);
                    }
                }
            }
        } catch (Exception e5) {
            throw new LitePalSupportException(e5.getMessage(), e5);
        }
    }

    private int doUpdateAssociations(LitePalSupport litePalSupport, long j, ContentValues contentValues) {
        analyzeAssociations(litePalSupport);
        updateSelfTableForeignKey(litePalSupport, contentValues);
        return updateAssociatedTableForeignKey(litePalSupport, j) + 0;
    }

    private void analyzeAssociations(LitePalSupport litePalSupport) {
        try {
            analyzeAssociatedModels(litePalSupport, getAssociationInfo(litePalSupport.getClassName()));
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        }
    }

    private void updateSelfTableForeignKey(LitePalSupport litePalSupport, ContentValues contentValues) {
        Map<String, Long> associatedModelsMapWithoutFK = litePalSupport.getAssociatedModelsMapWithoutFK();
        for (String str : associatedModelsMapWithoutFK.keySet()) {
            contentValues.put(getForeignKeyColumnName(str), associatedModelsMapWithoutFK.get(str));
        }
    }

    private int updateAssociatedTableForeignKey(LitePalSupport litePalSupport, long j) {
        Map<String, Set<Long>> associatedModelsMapWithFK = litePalSupport.getAssociatedModelsMapWithFK();
        ContentValues contentValues = new ContentValues();
        for (String str : associatedModelsMapWithFK.keySet()) {
            contentValues.clear();
            contentValues.put(getForeignKeyColumnName(litePalSupport.getTableName()), Long.valueOf(j));
            Set<Long> set = associatedModelsMapWithFK.get(str);
            if (!(set == null || set.isEmpty())) {
                return this.mDatabase.update(str, contentValues, getWhereOfIdsWithOr(set), null);
            }
        }
        return 0;
    }

    private void updateGenericTables(LitePalSupport litePalSupport, List<Field> list, long... jArr) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        long[] jArr2 = jArr;
        if (jArr2 != null && jArr2.length > 0) {
            Iterator<Field> it2 = list.iterator();
            while (it2.hasNext()) {
                Field next = it2.next();
                Encrypt encrypt = (Encrypt) next.getAnnotation(Encrypt.class);
                String genericTypeName = getGenericTypeName(next);
                String algorithm = (encrypt == null || !"java.lang.String".equals(genericTypeName)) ? null : encrypt.algorithm();
                next.setAccessible(true);
                Collection collection = (Collection) next.get(litePalSupport);
                if (collection != null && !collection.isEmpty()) {
                    String genericTableName = DBUtility.getGenericTableName(litePalSupport.getClassName(), next.getName());
                    String genericValueIdColumnName = DBUtility.getGenericValueIdColumnName(litePalSupport.getClassName());
                    int length = jArr2.length;
                    int i = 0;
                    while (i < length) {
                        long j = jArr2[i];
                        SQLiteDatabase sQLiteDatabase = this.mDatabase;
                        sQLiteDatabase.delete(genericTableName, genericValueIdColumnName + " = ?", new String[]{String.valueOf(j)});
                        Iterator it3 = collection.iterator();
                        while (it3.hasNext()) {
                            Object next2 = it3.next();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(genericValueIdColumnName, Long.valueOf(j));
                            Object encryptValue = encryptValue(algorithm, next2);
                            if (litePalSupport.getClassName().equals(genericTypeName)) {
                                LitePalSupport litePalSupport2 = (LitePalSupport) encryptValue;
                                if (litePalSupport2 != null) {
                                    long baseObjId = litePalSupport2.getBaseObjId();
                                    if (baseObjId > 0) {
                                        contentValues.put(DBUtility.getM2MSelfRefColumnName(next), Long.valueOf(baseObjId));
                                        it3 = it3;
                                        it2 = it2;
                                    }
                                }
                            } else {
                                it3 = it3;
                                it2 = it2;
                                DynamicExecutor.send(contentValues, "put", new Object[]{DBUtility.convertToValidColumnName(BaseUtility.changeCase(next.getName())), encryptValue}, contentValues.getClass(), new Class[]{String.class, getGenericTypeClass(next)});
                            }
                            this.mDatabase.insert(genericTableName, null, contentValues);
                        }
                        i++;
                        jArr2 = jArr;
                        it2 = it2;
                    }
                }
                jArr2 = jArr;
            }
        }
    }

    private void convertContentValues(ContentValues contentValues) {
        if (Build.VERSION.SDK_INT >= 11) {
            HashMap hashMap = new HashMap();
            for (String str : contentValues.keySet()) {
                if (DBUtility.isFieldNameConflictWithSQLiteKeywords(str)) {
                    hashMap.put(str, contentValues.get(str));
                }
            }
            for (Object str2 : hashMap.keySet()) {
                String convertToValidColumnName = DBUtility.convertToValidColumnName(str2.toString());
                Object obj = contentValues.get(str2.toString());
                contentValues.remove(str2.toString());
                if (obj == null) {
                    contentValues.putNull(convertToValidColumnName);
                } else {
                    String name = obj.getClass().getName();
                    if ("java.lang.Byte".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Byte) obj);
                    } else if ("[B".equals(name)) {
                        contentValues.put(convertToValidColumnName, (byte[]) obj);
                    } else if ("java.lang.Boolean".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Boolean) obj);
                    } else if ("java.lang.String".equals(name)) {
                        contentValues.put(convertToValidColumnName, (String) obj);
                    } else if ("java.lang.Float".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Float) obj);
                    } else if ("java.lang.Long".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Long) obj);
                    } else if ("java.lang.Integer".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Integer) obj);
                    } else if ("java.lang.Short".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Short) obj);
                    } else if ("java.lang.Double".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Double) obj);
                    }
                }
            }
        }
    }
}
