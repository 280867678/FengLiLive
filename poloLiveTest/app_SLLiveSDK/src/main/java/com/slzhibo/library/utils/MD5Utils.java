package com.slzhibo.library.utils;



import android.text.TextUtils;

import com.slzhibo.library.utils.litepal.crud.LitePalSupport;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import kotlin.UByte;

/* loaded from: classes12.dex */
public class MD5Utils {
    private static char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private MD5Utils() {
    }

    public static String getMd5(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            return StringUtils.bytesToHex(instance.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMd5(String str) {
        return getMd5(str.getBytes());
    }

    public static String getMd5(File file) throws Throwable {
        Throwable th;
        FileInputStream fileInputStream = null;
        Exception e;
        String str = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    instance.update(map);
                    str = StringUtils.bytesToHex(instance.digest());
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    IOUtils.closeQuietly(fileInputStream);
                    return str;
                }
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly(fileInputStream);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            IOUtils.closeQuietly(fileInputStream);
            throw th;
        }
        IOUtils.closeQuietly(fileInputStream);
        return str;
    }

    public static String getMessageDigest(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            byte[] digest = instance.digest();
            char[] cArr2 = new char[digest.length * 2];
            int i = 0;
            for (byte b : digest) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b & 15];
            }
            return new String(cArr2);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String byteHEX(byte b) {
        char[] cArr = Digit;
        return new String(new char[]{cArr[(b >>> 4) & 15], cArr[b & 15]});
    }

    private static byte[] md5(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(str.getBytes(StandardCharsets.UTF_8));
            return instance.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String toHex(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & UByte.MAX_VALUE) < 16) {
                sb.append("0");
            }
            sb.append(Long.toString(bArr[i] & UByte.MAX_VALUE, 16));
        }
        return sb.toString();
    }

    public static String hash(String str) {
        try {
            return TextUtils.isEmpty(toHex(md5(str))) ? str : new String(toHex(md5(str)).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String encryptPassword(String str, String str2, String str3) {
        return hash(str + str2 + str3);
    }

    public static String encryptPassword(String str, String str2) {
        return hash(str + str2);
    }

    public static String encryptPassword(String str) {
        return hash(str);
    }

    public static String getDefaultPassword() {
        return hash("123456");
    }





    /* renamed from: b */
    public static String m1143b(String str) {
        try {
            return TextUtils.isEmpty(m1142b(m1141c(str))) ? str : new String(m1142b(m1141c(str)).getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: c */
    public static byte[] m1141c(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(str.getBytes("UTF-8"));
            return instance.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /* renamed from: b */
    public static String m1142b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & 0xff) < 16) {
                sb.append("0");
            }
            sb.append(Long.toString(bArr[i] & 0xff, 16));
        }
        return sb.toString();
    }


}

