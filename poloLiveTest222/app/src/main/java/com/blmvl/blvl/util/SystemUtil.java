package com.blmvl.blvl.util;

//package p067e.p130f.p131a.p135d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.telephony.TelephonyManager;
//import com.slzhibo.library.utils.litepal.crud.LitePalSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

/* renamed from: e.f.a.d.j0 */
/* loaded from: classes.dex */
public class SystemUtil {
    /* renamed from: a */
    public static String m9125a(Context context) {
        String e = m9118e(context);
        StringBuilder sb = new StringBuilder();
        if (e != null && !"".equals(e)) {
            return e;
        }
        try {
            sb.append(m9120c(context));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            sb.append(m9119d(context).replace(":", ""));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (sb.length() <= 0) {
            sb.append(UUID.randomUUID().toString().replace("-", ""));
            sb.append(System.currentTimeMillis());
        }
        String a = m9123a(sb.toString(), false);
        if (sb.length() > 0) {
            m9124a(a, context);
        }
        return a;
    }

    /* renamed from: b */
    public static File m9121b(Context context) throws IOException {
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(Environment.getExternalStorageDirectory(), "cache/device");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, ".info");
            if (!file2.exists()) {
                file2.createNewFile();
            }
            return file2;
        }
        File file3 = new File(context.getFilesDir(), "cache/device");
        if (!file3.exists()) {
            file3.mkdirs();
        }
        File file4 = new File(file3, ".info");
        if (file4.exists()) {
            return file4;
        }
        file4.createNewFile();
        return file4;
    }

    /* renamed from: c */
    @SuppressLint("MissingPermission")
    public static String m9120c(Context context) {
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    /* renamed from: d */
    public static String m9119d(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            NetworkInterface byName = NetworkInterface.getByName("eth1");
            if (byName == null) {
                byName = NetworkInterface.getByName("wlan0");
            }
            if (byName == null) {
                return "";
            }
            byte[] hardwareAddress = byName.getHardwareAddress();
            int length = hardwareAddress.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02X:", Byte.valueOf(hardwareAddress[i])));
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: e */
    public static String m9118e(Context context) {
        try {
            File b = m9121b(context);
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(b), StandardCharsets.UTF_8));
            while (true) {
                int read = bufferedReader.read();
                if (read > -1) {
                    sb.append((char) read);
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static void m9124a(String str, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(m9121b(context)), StandardCharsets.UTF_8);
            outputStreamWriter.write(str);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static String m9123a(String str, boolean z) {
        try {
            return m9122a(MessageDigest.getInstance("MD5").digest(str.getBytes()), z);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public static String m9122a(byte[] bArr, boolean z) {
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            int i2 = bArr[i];
            if (i2 < 0) {
                i2 += 256;
            }
            if (i2 < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(i2));
        }
        if (z) {
            return sb.toString().toUpperCase();
        }
        return sb.toString().toLowerCase();
    }
}

