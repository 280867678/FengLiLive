package com.blmvl.blvl;

//package p067e.p103c.p104a;

import android.os.Environment;
import com.blmvl.blvl.AppContext;
//import com.luck.picture.lib.config.PictureConfig;
import java.io.File;

/* renamed from: e.c.a.b */
/* loaded from: classes.dex */
public class Config {

    /* renamed from: a */
    public static String f12180a = "http://api.myb6api.com:8080/api.php";

    /* renamed from: b */
    public static String f12181b = "http://api.myb6api.org:8080/api.php";

    /* renamed from: c */
    public static String f12182c = "http://api.myb6api.xyz:8080/api.php";

    /* renamed from: d */
    public static final String f12183d = AppContext.m21299a().getFilesDir().getAbsolutePath();

    /* renamed from: e */
    public static final String f12184e = f12183d + "/apk/";

    /* renamed from: f */
    public static final String f12185f = File.separator + "boluovl";

    /* renamed from: g */
    public static final String f12186g = Environment.getExternalStorageDirectory().getAbsolutePath() + f12185f;

    /* renamed from: h */
    public static final String f12187h = f12186g + File.separator + "share";

    /* renamed from: i */
    public static final String f12188i = f12186g + File.separator + "image";

    /* renamed from: j */
    public static final String f12189j = f12186g + File.separator + "image_cache";
}

