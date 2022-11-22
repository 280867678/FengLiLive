package com.slzhibo.library.utils.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.text.TextUtils;

import androidx.core.content.ContextCompat;

import com.slzhibo.library.utils.litepal.LitePalApplication;
import com.slzhibo.library.utils.litepal.exceptions.DatabaseGenerateException;
import com.slzhibo.library.utils.litepal.parser.LitePalAttr;
import com.slzhibo.library.utils.litepal.util.BaseUtility;
//import com.yanzhenjie.permission.Permission;

import java.io.File;

/* loaded from: classes12.dex */
public class Connector {
    private static LitePalOpenHelper mLitePalHelper;

    public static synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase writableDatabase;
        synchronized (Connector.class) {
            writableDatabase = buildConnection().getWritableDatabase();
        }
        return writableDatabase;
    }

    public static SQLiteDatabase getDatabase() {
        return getWritableDatabase();
    }

    private static LitePalOpenHelper buildConnection() {
        LitePalAttr instance = LitePalAttr.getInstance();
        instance.checkSelfValid();
        if (mLitePalHelper == null) {
            String dbName = instance.getDbName();
            if ("external".equalsIgnoreCase(instance.getStorage())) {
                dbName = LitePalApplication.getContext().getExternalFilesDir("") + "/databases/" + dbName;
            } else if (!"internal".equalsIgnoreCase(instance.getStorage()) && !TextUtils.isEmpty(instance.getStorage())) {
                String replace = (Environment.getExternalStorageDirectory().getPath() + "/" + instance.getStorage()).replace("//", "/");
                if (!BaseUtility.isClassAndMethodExist("androidx.core.content.ContextCompat", "checkSelfPermission") || ContextCompat.checkSelfPermission(LitePalApplication.getContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                    File file = new File(replace);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    dbName = replace + "/" + dbName;
                } else {
                    throw new DatabaseGenerateException(String.format(DatabaseGenerateException.EXTERNAL_STORAGE_PERMISSION_DENIED, replace));
                }
            }
            mLitePalHelper = new LitePalOpenHelper(dbName, instance.getVersion());
        }
        return mLitePalHelper;
    }

    public static void clearLitePalOpenHelperInstance() {
        LitePalOpenHelper litePalOpenHelper = mLitePalHelper;
        if (litePalOpenHelper != null) {
            litePalOpenHelper.getWritableDatabase().close();
            mLitePalHelper = null;
        }
    }
}
