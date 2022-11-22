package com.ibase.glide;

//package p067e.p260n.p261a;

import android.graphics.BitmapFactory;
import android.util.Log;
//import com.amazonaws.services.p010s3.model.InstructionFileId;
//import com.slzhibo.library.utils.litepal.crud.LitePalSupport;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
//import p067e.p260n.p261a.p263j.C4721a;

/* compiled from: EncryptManager.java */
/* renamed from: e.n.a.a */
/* loaded from: classes.dex */
public class C4712a {

    /* renamed from: b */
    public static volatile C4712a f17157b;

    /* renamed from: a */
    public boolean f17158a;

    public C4712a() {
        try {
            Cipher.getInstance("AES/CFB/NoPadding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: b */
    public static C4712a m4955b() {
        if (f17157b == null) {
            synchronized (C4712a.class) {
                if (f17157b == null) {
                    f17157b = new C4712a();
                }
            }
        }
        return f17157b;
    }

    /* renamed from: a */
    public boolean m4959a() {
        return this.f17158a;
    }

    /* renamed from: a */
    public static boolean m4957a(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return options.outMimeType == null;
    }

    /* renamed from: a */
    public static byte[] m4956a(String str, byte[] bArr) {
        try {
            byte[] a = C4721a.m4933a(new String(bArr));
            if (a != null && a.length > 0) {
                byte[] copyOfRange = Arrays.copyOfRange(a, 0, 16);
                byte[] copyOfRange2 = Arrays.copyOfRange(a, 16, a.length);
                byte[][] a2 = C4721a.m4934a(32, 16, null, str.getBytes("UTF-8"), 0);
                IvParameterSpec ivParameterSpec = new IvParameterSpec(copyOfRange);
                SecretKeySpec secretKeySpec = new SecretKeySpec(a2[0], "AES");
                Cipher instance = Cipher.getInstance("AES/CFB/NoPadding");
                instance.init(2, secretKeySpec, ivParameterSpec);
                return instance.doFinal(copyOfRange2);
            }
            Log.e("TAG", "----encrypt_data--->");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public String m4958a(File file) throws IOException {
        String absolutePath = file.getParentFile().getAbsolutePath();
        String name = file.getName();
        String substring = name.substring(name.lastIndexOf(".") + 1);
        String str = absolutePath + "/" + (name.substring(0, name.lastIndexOf(46)) + "_temp." + substring);
        FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
        FileOutputStream fileOutputStream = new FileOutputStream(str);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        byte[] a = m4956a("e79465cfbbimgkcusimcuekd3b066a6e", byteArrayOutputStream.toByteArray());
        if (a == null) {
            return null;
        }
        fileOutputStream.write(a);
        fileOutputStream.flush();
        fileOutputStream.close();
        file.delete();
        return str;
    }
}

