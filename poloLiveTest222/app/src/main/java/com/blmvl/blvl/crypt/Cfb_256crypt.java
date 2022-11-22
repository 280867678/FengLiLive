package com.blmvl.blvl.crypt;



import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Cfb_256crypt {

    /* renamed from: b */

    /**
     * 加密
     * @param str
     * @param str2
     * @return
     */
    public static String m9205b(String str, String str2) {
        try {
            Cipher instance = Cipher.getInstance("AES/CFB/NoPadding");
            byte[][] a = m9211a(32, 16, null, str.getBytes(StandardCharsets.UTF_8), 0);
            instance.init(1, new SecretKeySpec(a[0], "AES"), new IvParameterSpec(a[1]));
            return m9204b(m9207a(instance.getIV(), instance.doFinal(str2.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    /* renamed from: a */
    public static byte[][] m9211a(int i, int i2, byte[] bArr, byte[] bArr2, int i3) throws Exception {
        byte[] digest;
        MessageDigest instance = MessageDigest.getInstance("md5");
        byte[] bArr3 = new byte[i];
        byte[] bArr4 = new byte[i2];
        byte[][] bArr5 = {bArr3, bArr4};
        if (bArr2 == null) {
            return bArr5;
        }
        byte[] bArr6 = null;
        int i4 = i2;
        int i5 = 0;
        int i6 = 0;
        int i7 = i;
        int i8 = 0;
        while (true) {
            instance.reset();
            int i9 = i8 + 1;
            if (i8 > 0) {
                instance.update(bArr6);
            }
            instance.update(bArr2);
            if (bArr != null) {
                instance.update(bArr, 0, 8);
            }
            digest = instance.digest();
            for (int i10 = 1; i10 < i3; i10++) {
                instance.reset();
                instance.update(digest);
                digest = instance.digest();
            }
            int i11 = 0;
            if (i7 > 0) {
                while (i7 != 0 && i11 != digest.length) {
                    bArr3[i5] = digest[i11];
                    i7--;
                    i11++;
                    i5++;
                }
            }
            if (i4 > 0 && i11 != digest.length) {
                while (i4 != 0 && i11 != digest.length) {
                    bArr4[i6] = digest[i11];
                    i4--;
                    i11++;
                    i6++;
                }
            }
            if (i7 == 0 && i4 == 0) {
                break;
            }
            i8 = i9;
            bArr6 = digest;
        }
        for (int i12 = 0; i12 < digest.length; i12++) {
            digest[i12] = 0;
        }
        return bArr5;
    }


    /* renamed from: b */
    public static String m9204b(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 0xff);    //Constants.UNKNOWN  -1
            str = hexString.length() == 1 ? str + "0" + hexString : str + hexString;
        }
        return str.toUpperCase();
    }

    /* renamed from: a */
    public static byte[] m9207a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }


























    /* renamed from: a */
    public static String m9210a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(str.getBytes(StandardCharsets.UTF_8));
            return m9208a(instance.digest());
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }


    /* renamed from: a */
    public static String m9208a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 0xff);       //Constants.UNKNOWN
            if (hexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }
















    /* renamed from: a */

    /**
     * 解密
     * @param str
     * @param str2
     * @return
     */
    public static String m9209a(String str, String str2) {
        try {
            byte[] b = m9206b(str2);
            byte[] copyOfRange = Arrays.copyOfRange(b, 0, 16);
            byte[] copyOfRange2 = Arrays.copyOfRange(b, 16, b.length);
            byte[][] a = m9211a(32, 16, null, str.getBytes(StandardCharsets.UTF_8), 0);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(copyOfRange);
            SecretKeySpec secretKeySpec = new SecretKeySpec(a[0], "AES");
            Cipher instance = Cipher.getInstance("AES/CFB/NoPadding");
            instance.init(2, secretKeySpec, ivParameterSpec);
            return new String(instance.doFinal(copyOfRange2), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m9206b(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length % 2 == 1) {
            return null;
        }
        int i = length / 2;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 != i; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = (byte) Integer.parseInt(str.substring(i3, i3 + 2), 16);
        }
        return bArr;
    }



}

