package com.slzhibo.library.http.utils;

//import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import com.blankj.utilcode.util.EncodeUtils;
import com.slzhibo.library.utils.CommonTransferUtils;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes6.dex */
public class EncryptUtil {



    public static final String CHARSET = "utf-8";

    /* renamed from: iv */
    public static final String f4610iv = "01234567";



    /**
     * 解密
     * @param p0  密钥
     * @param p1  数据
     * @return
     * @throws Exception
     */
    public static String DESDecrypt(String p0,String p1) throws Exception{
        return EncryptUtil.decode(p0, p1);
    }

    public static String decode(String p0,String p1) throws Exception{
        Cipher cInstance = Cipher.getInstance("desede/CBC/PKCS5Padding");
        cInstance.init(2, SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(p0.getBytes())), new IvParameterSpec("01234567".getBytes()));
        return new String(cInstance.doFinal(EncryptUtil.decode(p1)), "utf-8");
    }

    public static byte[] decode(String p0) throws Exception{
        return Base64Util.decode(p0, "GBK");
    }


    /**
     * 解密
     * @param str   密钥
     * @param str2   数据
     * @return
     * @throws Exception
     */
    public static String DESEncrypt(String str, String str2) throws Exception {
        return encode(str, str2);
    }



    public static String encode(String str, String str2) throws Exception {
        SecretKey generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(str.getBytes()));
        Cipher instance = Cipher.getInstance("desede/CBC/PKCS5Padding");
        instance.init(1, generateSecret, new IvParameterSpec(f4610iv.getBytes()));
        return encodeToString(instance.doFinal(str2.getBytes(CHARSET)));
    }

    public static String encodeToString(byte[] bArr) {
        return Base64Util.encode(bArr);
    }



    public static String RSADecrypt(String str, String str2) throws Exception {
        return new String(decrypt(decode(str2), getPraivateKey(str), 2048, 11, "RSA/ECB/PKCS1Padding"), CHARSET);
    }


    public static PrivateKey getPraivateKey(String str) throws Exception {
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decode(str)));
    }




//    public static byte[] decrypt(byte[] paramArrayOfbyte, PrivateKey paramPrivateKey, int paramInt1, int paramInt2, String paramString) throws Exception{
//        int i = paramInt1 / 8;
//        paramInt1 = paramArrayOfbyte.length / i;
//        byte[] arrayOfByte1 = null;
//        Exception exception3 = null;
//        Exception exception4 = exception3;
//        try {
//            Cipher cipher = Cipher.getInstance(paramString);
//            exception4 = exception3;
//            cipher.init(2, paramPrivateKey);
//            exception4 = exception3;
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            exception4 = exception3;
////            this(paramInt1 * (i - paramInt2));
//            paramInt1 = 0;
//            try {
//                while (paramInt1 < paramArrayOfbyte.length) {
//                    int j = paramArrayOfbyte.length - paramInt1;
//                    paramInt2 = j;
//                    if (j > i)
//                        paramInt2 = i;
//                    byteArrayOutputStream.write(cipher.doFinal(paramArrayOfbyte, paramInt1, paramInt2));
//                    paramInt1 += i;
//                }
//                byteArrayOutputStream.flush();
//                paramArrayOfbyte = byteArrayOutputStream.toByteArray();
//            } catch (Exception exception6) {
////                Exception exception5 = exception1;
//            } finally {
//                paramArrayOfbyte = null;
//            }
//        } catch (Exception exception1) {
//            paramArrayOfbyte = arrayOfByte1;
//        } finally {}
//        byte[] arrayOfByte2 = paramArrayOfbyte;
//        Exception exception2 = new Exception();
//        arrayOfByte2 = paramArrayOfbyte;
////        this("DEENCRYPT ERROR:", exception1);
//        arrayOfByte2 = paramArrayOfbyte;
//        throw exception2;
////        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.http.utils.EncryptUtil.decrypt(byte[], java.security.PrivateKey, int, int, java.lang.String):byte[]");
//    }


    public static byte[] decrypt(byte[] p0,PrivateKey p1,int p2,int p3,String p4) throws Exception{
        int vi = p2/8;
        int vi1 = vi-p3;
        int vi2 = p0.length/vi;
        int vi3 = 0;
        Cipher cInstance = Cipher.getInstance(p4);
        cInstance.init(2, p1);
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream((vi2*vi1));
        for (vi1 = 0; vi1 < p0.length; vi1 = vi1+vi) {
            vi2 = p0.length-vi1;
            if (vi2 > vi) {
                vi2 = vi;
            }
            byteArrayOut.write(cInstance.doFinal(p0, vi1, vi2));
        }
        byteArrayOut.flush();
        byte[] bArray = byteArrayOut.toByteArray();

        byteArrayOut.close();
        return bArray;
//        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.http.utils.EncryptUtil.decrypt(byte[], java.security.PrivateKey, int, int, java.lang.String):byte[]");
    }





//    public static final String CHARSET = "utf-8";
//    private static final String iv = "01234567";
//
//    private EncryptUtil() {
//    }
//
//    public static String DESEncrypt(String str, String str2) throws Exception {
//        return encode(str, str2);
//    }
//
//    public static String DESDecrypt(String str, String str2) throws Exception {
//        return decode(str, str2);
//    }
//
//    public static String AESEncrypt(String str, String str2) throws Exception {
//        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(CHARSET), "AES");
//        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
//        instance.init(1, secretKeySpec, new IvParameterSpec(CommonTransferUtils.getSingleton().ML_ENCRYPT_SOCKET_KEY.getBytes()));
//        return EncodeUtils.base64Encode2String(instance.doFinal(str2.getBytes(CHARSET)));
//    }
//
//    public static String AESDecrypt(String str, String str2) throws Exception {
//        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(CHARSET), "AES");
//        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
//        instance.init(2, secretKeySpec, new IvParameterSpec(CommonTransferUtils.getSingleton().ML_ENCRYPT_SOCKET_KEY.getBytes()));
//        return new String(instance.doFinal(EncodeUtils.base64Decode(str2)), CHARSET);
//    }
//
//    public static String RSAEncrypt(String str, String str2) throws Exception {
//        return encodeToString(encrypt(str2.getBytes(CHARSET), getPublicKey(str), 2048, 11, "RSA/ECB/PKCS1Padding"));
//    }
//
//    public static String RSADecrypt(String str, String str2) throws Exception {
//        return new String(decrypt(decode(str2), getPraivateKey(str), 2048, 11, "RSA/ECB/PKCS1Padding"), CHARSET);
//    }
//
//    private static String encode(String str, String str2) throws Exception {
//        SecretKey generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(str.getBytes()));
//        Cipher instance = Cipher.getInstance("desede/CBC/PKCS5Padding");
//        instance.init(1, generateSecret, new IvParameterSpec(iv.getBytes()));
//        return encodeToString(instance.doFinal(str2.getBytes(CHARSET)));
//    }
//
//    private static String decode(String str, String str2) throws Exception {
//        SecretKey generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(str.getBytes()));
//        Cipher instance = Cipher.getInstance("desede/CBC/PKCS5Padding");
//        instance.init(2, generateSecret, new IvParameterSpec(iv.getBytes()));
//        return new String(instance.doFinal(decode(str2)), CHARSET);
//    }
//
//    private static byte[] decrypt(byte[] bArr, PrivateKey privateKey, int i, int i2, String str) throws Exception {
//        Throwable th;
//        Exception e;
//        int i3 = i / 8;
//        int i4 = i3 - i2;
//        int length = bArr.length / i3;
//        ByteArrayOutputStream byteArrayOutputStream = null;
//        try {
//            try {
//                Cipher instance = Cipher.getInstance(str);
//                instance.init(2, privateKey);
//                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(length * i4);
//                for (int i5 = 0; i5 < bArr.length; i5 += i3) {
//                    try {
//                        int length2 = bArr.length - i5;
//                        if (length2 > i3) {
//                            length2 = i3;
//                        }
//                        byteArrayOutputStream2.write(instance.doFinal(bArr, i5, length2));
//                    } catch (Exception e2) {
//                        e = e2;
//                        byteArrayOutputStream = byteArrayOutputStream2;
//                        throw new Exception("DEENCRYPT ERROR:", e);
//                    } catch (Throwable th2) {
//                        th = th2;
//                        byteArrayOutputStream = byteArrayOutputStream2;
//                        if (byteArrayOutputStream != null) {
//                            try {
//                                byteArrayOutputStream.close();
//                            } catch (Exception unused) {
//                            }
//                        }
//                        throw th;
//                    }
//                }
//                byteArrayOutputStream2.flush();
//                byte[] byteArray = byteArrayOutputStream2.toByteArray();
//                try {
//                    byteArrayOutputStream2.close();
//                } catch (Exception unused2) {
//                }
//                return byteArray;
//            } catch (Exception e3) {
//                e = e3;
//            }
//        } catch (Throwable th3) {
//            th = th3;
//        }
//        return null;
//    }
//
//    private static byte[] encrypt(byte[] bArr, PublicKey publicKey, int i, int i2, String str) throws Exception {
//        Throwable th;
//        Exception e;
//        int i3 = i / 8;
//        int i4 = i3 - i2;
//        int length = bArr.length / i4;
//        if (bArr.length % i4 != 0) {
//            length++;
//        }
//        ByteArrayOutputStream byteArrayOutputStream = null;
//        try {
//            try {
//                Cipher instance = Cipher.getInstance(str);
//                instance.init(1, publicKey);
//                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(length * i3);
//                for (int i5 = 0; i5 < bArr.length; i5 += i4) {
//                    try {
//                        int length2 = bArr.length - i5;
//                        if (length2 > i4) {
//                            length2 = i4;
//                        }
//                        byteArrayOutputStream2.write(instance.doFinal(bArr, i5, length2));
//                    } catch (Exception e2) {
//                        e = e2;
//                        byteArrayOutputStream = byteArrayOutputStream2;
//                        throw new Exception("ENCRYPT ERROR:", e);
//                    } catch (Throwable th2) {
//                        th = th2;
//                        byteArrayOutputStream = byteArrayOutputStream2;
//                        if (byteArrayOutputStream != null) {
//                            try {
//                                byteArrayOutputStream.close();
//                            } catch (Exception unused) {
//                            }
//                        }
//                        throw th;
//                    }
//                }
//                byteArrayOutputStream2.flush();
//                byte[] byteArray = byteArrayOutputStream2.toByteArray();
//                try {
//                    byteArrayOutputStream2.close();
//                } catch (Exception unused2) {
//                }
//                return byteArray;
//            } catch (Exception e3) {
//                e = e3;
//            }
//        } catch (Throwable th3) {
//            th = th3;
//        }
//        return null;
//    }
//
//    private static PrivateKey getPraivateKey(String str) throws Exception {
//        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decode(str)));
//    }
//
//    private static PublicKey getPublicKey(String str) throws Exception {
//        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decode(str)));
//    }
//
//    private static String encodeToString(byte[] bArr) {
//        return Base64Util.encode(bArr);
//    }
//
//    private static byte[] decode(String str) throws Exception {
//        return Base64Util.decode(str, "GBK");
//    }
}
