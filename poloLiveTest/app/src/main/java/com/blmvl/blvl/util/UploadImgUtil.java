package com.blmvl.blvl.util;

//package p067e.p103c.p104a.p113k;

//import android.support.transition.Transition;
import android.text.TextUtils;
import com.blmvl.blvl.bean.AppConfig;
import com.blmvl.blvl.crypt.EncryptManager;
//import com.luck.picture.lib.config.PictureConfig;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.model.HttpParams;
import java.io.File;

/* renamed from: e.c.a.k.e0 */
/* loaded from: classes.dex */
public class UploadImgUtil {
    /* renamed from: a */
    public static HttpParams m9507a(File file) {
        return m9506a(CacheEntity.HEAD, "", file);
    }

    /* renamed from: b */
    public static String m9505b() {
        String imgUploadUrl = AppConfig.getInstance().getConfig().getImgUploadUrl();
        if (imgUploadUrl.contains("imgUpload.php")) {
            return imgUploadUrl;
        }
        return imgUploadUrl + "imgUpload.php";
    }

    /* renamed from: a */
    public static HttpParams m9506a(String str, String str2, File file) {
        StringBuilder sb = new StringBuilder("id=");
        if (TextUtils.isEmpty(str2)) {
            str2 = m9508a();
        }
        sb.append(str2);
        sb.append("&position=");
        sb.append(str);
        sb.append("132f1537f85scxpcm59f7e318b9epa51");
        String c = EncryptManager.m9436a().m9433c(sb.toString());
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", str2, new boolean[0]);
        httpParams.put("position", str, new boolean[0]);
        httpParams.put("cover", file);
        httpParams.put("sign", c, new boolean[0]);
        return httpParams;
    }

    /* renamed from: b */
    public static HttpParams m9504b(File file) {
        return m9506a("xiao", "", file);
    }

    /* renamed from: a */
    public static String m9508a() {
        return "boluo_" + System.currentTimeMillis() + "_" + ((int) (((Math.random() * 9.0d) + 1.0d) * 1000.0d));
    }
}

