package com.slzhibo.library.utils.litepal.util;

import android.text.TextUtils;

import com.slzhibo.library.utils.litepal.LitePalApplication;
import com.slzhibo.library.utils.litepal.exceptions.LitePalSupportException;
import com.slzhibo.library.utils.litepal.parser.LitePalAttr;
import com.slzhibo.library.utils.litepal.util.Const;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Locale;

/* loaded from: classes12.dex */
public class BaseUtility {
    private BaseUtility() {
    }

    public static String changeCase(String str) {
        if (str == null) {
            return null;
        }
        String cases = LitePalAttr.getInstance().getCases();
        if (Const.Config.CASES_KEEP.equals(cases)) {
            return str;
        }
        if (Const.Config.CASES_UPPER.equals(cases)) {
            return str.toUpperCase(Locale.US);
        }
        return str.toLowerCase(Locale.US);
    }

    public static boolean containsIgnoreCases(Collection<String> collection, String str) {
        if (collection == null) {
            return false;
        }
        if (str == null) {
            return collection.contains(null);
        }
        for (String str2 : collection) {
            if (str.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        return false;
    }

    public static String capitalize(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.substring(0, 1).toUpperCase(Locale.US) + str.substring(1);
        } else if (str == null) {
            return null;
        } else {
            return "";
        }
    }

    public static int count(String str, String str2) {
        int i = 0;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            int indexOf = str.indexOf(str2);
            while (indexOf != -1) {
                i++;
                str = str.substring(indexOf + str2.length());
                indexOf = str.indexOf(str2);
            }
        }
        return i;
    }

    public static void checkConditionsCorrect(String... strArr) {
        int length;
        if (strArr != null && (length = strArr.length) > 0 && length != count(strArr[0], "?") + 1) {
            throw new LitePalSupportException(LitePalSupportException.UPDATE_CONDITIONS_EXCEPTION);
        }
    }

    public static boolean isFieldTypeSupported(String str) {
        return "boolean".equals(str) || "java.lang.Boolean".equals(str) || "float".equals(str) || "java.lang.Float".equals(str) || "double".equals(str) || "java.lang.Double".equals(str) || "int".equals(str) || "java.lang.Integer".equals(str) || "long".equals(str) || "java.lang.Long".equals(str) || "short".equals(str) || "java.lang.Short".equals(str) || "char".equals(str) || "java.lang.Character".equals(str) || "[B".equals(str) || "[Ljava.lang.Byte;".equals(str) || "java.lang.String".equals(str) || "java.util.Date".equals(str);
    }

    public static boolean isGenericTypeSupported(String str) {
        if (!"java.lang.String".equals(str) && !"java.lang.Integer".equals(str) && !"java.lang.Float".equals(str) && !"java.lang.Double".equals(str) && !"java.lang.Long".equals(str) && !"java.lang.Short".equals(str) && !"java.lang.Boolean".equals(str)) {
            return "java.lang.Character".equals(str);
        }
        return true;
    }

    public static boolean isLitePalXMLExists() {
        try {
            String[] list = LitePalApplication.getContext().getAssets().list("");
            if (list != null && list.length > 0) {
                for (String str : list) {
                    if (Const.Config.CONFIGURATION_FILE_NAME.equalsIgnoreCase(str)) {
                        return true;
                    }
                }
            }
        } catch (IOException unused) {
        }
        return false;
    }

    public static boolean isClassAndMethodExist(String str, String str2) {
        try {
            for (Method method : Class.forName(str).getMethods()) {
                if (str2.equals(method.getName())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
