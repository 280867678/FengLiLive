package com.slzhibo.library.http.utils;

//import com.facebook.stetho.dumpapp.Framer;
//import com.seven.movie.common.utils.IOUtils;
//
//import org.apache.commons.compress.archivers.tar.TarConstants;
//import org.bouncycastle.math.ec.Tnaf;
//import org.bouncycastle.pqc.math.linearalgebra.Matrix;
//import org.eclipse.jetty.http.HttpTokens;

import android.util.Base64;

//import kotlin.UByte;
import okio.Utf8;
//
///* loaded from: classes6.dex */
//public class Base64Util {
//    private static final char[] base64EncodeChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
//    private static byte[] base64DecodeChars = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};
//
//    private Base64Util() {
//    }
//
//    public static String encode(byte[] bArr) {
//        StringBuilder sb = new StringBuilder();
//        int length = bArr.length;
//        int i = 0;
//        while (true) {
//            if (i >= length) {
//                break;
//            }
//            int i2 = i + 1;
//            int i3 = bArr[i] & UByte.MAX_VALUE;
//            if (i2 == length) {
//                sb.append(base64EncodeChars[i3 >>> 2]);
//                sb.append(base64EncodeChars[(i3 & 3) << 4]);
//                sb.append("==");
//                break;
//            }
//            int i4 = i2 + 1;
//            int i5 = bArr[i2] & UByte.MAX_VALUE;
//            if (i4 == length) {
//                sb.append(base64EncodeChars[i3 >>> 2]);
//                sb.append(base64EncodeChars[((i3 & 3) << 4) | ((i5 & 240) >>> 4)]);
//                sb.append(base64EncodeChars[(i5 & 15) << 2]);
//                sb.append("=");
//                break;
//            }
//            i = i4 + 1;
//            int i6 = bArr[i4] & UByte.MAX_VALUE;
//            sb.append(base64EncodeChars[i3 >>> 2]);
//            sb.append(base64EncodeChars[((i3 & 3) << 4) | ((i5 & 240) >>> 4)]);
//            sb.append(base64EncodeChars[((i5 & 15) << 2) | ((i6 & 192) >>> 6)]);
//            sb.append(base64EncodeChars[i6 & 63]);
//        }
//        return sb.toString();
//    }
//
//    /* JADX WARN: Code restructure failed: missing block: B:33:0x007a, code lost:
//        if (r1 != (-1)) goto L_0x007d;
//     */
//    /* JADX WARN: Code restructure failed: missing block: B:34:0x007d, code lost:
//        r0.write(r1 | ((r4 & 3) << 6));
//        r1 = r3;
//     */
//    /*
//        Code decompiled incorrectly, please refer to instructions dump.
//        To view partially-correct code enable 'Show inconsistent code' option in preferences
//    */
//    public static byte[] decode(String r7, String r8) throws Exception {
//        /*
//            byte[] r7 = r7.getBytes(r8)
//            int r8 = r7.length
//            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
//            r0.<init>(r8)
//            r1 = 0
//        L_0x000b:
//            if (r1 >= r8) goto L_0x0087
//        L_0x000d:
//            byte[] r2 = com.slzhibo.library.http.utils.Base64Util.base64DecodeChars
//            int r3 = r1 + 1
//            r1 = r7[r1]
//            r1 = r2[r1]
//            r2 = -1
//            if (r3 >= r8) goto L_0x001d
//            if (r1 == r2) goto L_0x001b
//            goto L_0x001d
//        L_0x001b:
//            r1 = r3
//            goto L_0x000d
//        L_0x001d:
//            if (r1 != r2) goto L_0x0021
//            goto L_0x0087
//        L_0x0021:
//            byte[] r4 = com.slzhibo.library.http.utils.Base64Util.base64DecodeChars
//            int r5 = r3 + 1
//            r3 = r7[r3]
//            r3 = r4[r3]
//            if (r5 >= r8) goto L_0x0030
//            if (r3 == r2) goto L_0x002e
//            goto L_0x0030
//        L_0x002e:
//            r3 = r5
//            goto L_0x0021
//        L_0x0030:
//            if (r3 != r2) goto L_0x0033
//            goto L_0x0087
//        L_0x0033:
//            int r1 = r1 << 2
//            r4 = r3 & 48
//            int r4 = r4 >>> 4
//            r1 = r1 | r4
//            r0.write(r1)
//        L_0x003d:
//            int r1 = r5 + 1
//            r4 = r7[r5]
//            r5 = 61
//            if (r4 != r5) goto L_0x004a
//            byte[] r7 = r0.toByteArray()
//            return r7
//        L_0x004a:
//            byte[] r6 = com.slzhibo.library.http.utils.Base64Util.base64DecodeChars
//            r4 = r6[r4]
//            if (r1 >= r8) goto L_0x0055
//            if (r4 == r2) goto L_0x0053
//            goto L_0x0055
//        L_0x0053:
//            r5 = r1
//            goto L_0x003d
//        L_0x0055:
//            if (r4 != r2) goto L_0x0058
//            goto L_0x0087
//        L_0x0058:
//            r3 = r3 & 15
//            int r3 = r3 << 4
//            r6 = r4 & 60
//            int r6 = r6 >>> 2
//            r3 = r3 | r6
//            r0.write(r3)
//        L_0x0064:
//            int r3 = r1 + 1
//            r1 = r7[r1]
//            if (r1 != r5) goto L_0x006f
//            byte[] r7 = r0.toByteArray()
//            return r7
//        L_0x006f:
//            byte[] r6 = com.slzhibo.library.http.utils.Base64Util.base64DecodeChars
//            r1 = r6[r1]
//            if (r3 >= r8) goto L_0x007a
//            if (r1 == r2) goto L_0x0078
//            goto L_0x007a
//        L_0x0078:
//            r1 = r3
//            goto L_0x0064
//        L_0x007a:
//            if (r1 != r2) goto L_0x007d
//            goto L_0x0087
//        L_0x007d:
//            r2 = r4 & 3
//            int r2 = r2 << 6
//            r1 = r1 | r2
//            r0.write(r1)
//            r1 = r3
//            goto L_0x000b
//        L_0x0087:
//            byte[] r7 = r0.toByteArray()
//            return r7
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.http.utils.Base64Util.decode(java.lang.String, java.lang.String):byte[]");
//    }
//}







//
//package com.slzhibo.library.http.utils;
//
//import com.facebook.stetho.dumpapp.Framer;
//import com.seven.movie.common.utils.IOUtils;
import java.io.ByteArrayOutputStream;
//import kotlin.UByte;
//import okio.Utf8;
//import org.apache.commons.compress.archivers.tar.TarConstants;
//import org.eclipse.jetty.http.HttpTokens;

/* loaded from: classes6.dex */
public class Base64Util {




    public static char[] base64EncodeChars = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', '+', '/' };
    public static final byte[] base64DecodeChars = new byte[]{
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, 62, -1, -1, -1, 63, 52, 53,
            54, 55, 56, 57, 58, 59, 60, 61, -1, -1,
            -1, -1, -1, -1, -1, 0, 1, 2, 3, 4,
            5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
            25, -1, -1, -1, -1, -1, -1, 26, 27, 28,
            29, 30, 31, 32, 33, 34, 35, 36, 37, 38,
            39, 40, 41, 42, 43, 44, 45, 46, 47, 48,
            49, 50, 51, -1, -1, -1, -1, -1 };


    /**
     * 加密
     * @param arg7
     * @param arg8
     * @return
     * @throws Exception
     */
    public static byte[] decode(String arg7, String arg8) throws Exception {

        return Base64.decode(arg7, Base64.DEFAULT);


//        int v4;
//        int v5;
//        int v3;
//        byte[] v7 = arg7.getBytes(arg8);
//        int v8 = v7.length;
//        ByteArrayOutputStream v0 = new ByteArrayOutputStream(v8);
//        int v1 = 0;
//        while(true) {
//            if(v1 < v8) {
//                int v2 = 0xff;
//                while(true) {
//                    v3 = v1 + 1;
//                    v1 = Base64Util.base64DecodeChars[v7[v1]];
//
//                    if(v3 < v8) {
//                        if(v1 != v2) {
//                        }
//                        else {
//                            v1 = v3;
//                            continue;
//                        }
//                    }
//
//                    break;
//                }
//
//                if(v1 == v2) {
//                    return v0.toByteArray();
//                }
//
//                while(true) {
//                    v5 = v3 + 1;
//                    v3 = Base64Util.base64DecodeChars[v7[v3]];
//                    if(v5 < v8) {
//                        if(v3 != v2) {
//                        }
//                        else {
//                            v3 = v5;
//                            continue;
//                        }
//                    }
//
//                    break;
//                }
//
//                if(v3 == v2) {
//                    return v0.toByteArray();
//                }
//
//                v0.write(v1 << 2 | (v3 & 0x30) >>> 4);
//                while(true) {
//                    v1 = v5 + 1;
//                    v4 = v7[v5];
//                    v5 = 61;
//                    if(v4 == v5) {
//                        v0.toByteArray();
//                    }
//
//                    v4 = Base64Util.base64DecodeChars[v4];
//                    if(v1 < v8) {
//                        if(v4 != v2) {
//                        }
//                        else {
//                            v5 = v1;
//                            continue;
//                        }
//                    }
//
//                    break;
//                }
//
//                if(v4 == v2) {
//                    return v0.toByteArray();
//                }
//
//                v0.write((v3 & 15) << 4 | (v4 & 60) >>> 2);
//
//
//                while(true) {
//
//                    v3 = v1 + 1;
//                    v1 = v7[v1];
//                    if(v1 == v5) {
//                        return v0.toByteArray();
//                    }
//
//                    v1 = Base64Util.base64DecodeChars[v1];
//                    if(v3 < v8) {
//                        if(v1 != v2) {
//                        }
//                        else {
//                            v1 = v3;
//                            continue;
//                        }
//                    }
//
//                    break;
//                }
//
//                if(v1 == v2) {
//                    return v0.toByteArray();
//                }
//
//                v0.write(v1 | (v4 & 3) << 6);
//                v1 = v3;
//                continue;
//            }
//
//            return v0.toByteArray();
//        }

//     label_58:
//         return v0.toByteArray();
//     label_75:
//         return v0.toByteArray();
//





























//        byte[] arrayOfByte = paramString1.getBytes(paramString2);
//        int i = arrayOfByte.length;
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i);
//        int j = 0;
//        while (j < i) {
//          byte b1;
//          byte b2;
//          int k;
//          for (k = j;; k = j) {
//            byte[] arrayOfByte1 = base64DecodeChars;
//            j = k + 1;
//            b1 = arrayOfByte1[arrayOfByte[k]];
//            if (j >= i || b1 != -1)
//              break;
//          }
//          k = j;
//          if (b1 == -1)
//            break;
//          while (true) {
//            byte[] arrayOfByte1 = base64DecodeChars;
//            j = k + 1;
//            b2 = arrayOfByte1[arrayOfByte[k]];
//            if (j >= i || b2 != -1)
//              break;
//            k = j;
//          }
//          if (b2 == -1)
//            break;
//          byteArrayOutputStream.write(b1 << 2 | (b2 & 0x30) >>> 4);
//          for (k = j;; k = j) {
//            j = k + 1;
//            k = arrayOfByte[k];
//            if (k == 61)
//              return byteArrayOutputStream.toByteArray();
//            b1 = base64DecodeChars[k];
//            if (j >= i || b1 != -1)
//              break;
//          }
//          if (b1 == -1)
//            break;
//          byteArrayOutputStream.write((b2 & 0xF) << 4 | (b1 & 0x3C) >>> 2);
//          for (k = j;; k = j) {
//            j = k + 1;
//            k = arrayOfByte[k];
//            if (k == 61)
//              return byteArrayOutputStream.toByteArray();
//            k = base64DecodeChars[k];
//            if (j >= i || k != -1)
//              break;
//          }
//          if (k == -1)
//            break;
//          byteArrayOutputStream.write(k | (b1 & 0x3) << 6);
//        }
//        return byteArrayOutputStream.toByteArray();
    }







































    /**
     * 解密
     * @param bArr
     * @return
     */
    public static String encode(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            int i2 = i + 1;
            int i3 = bArr[i] & 0xff;
            if (i2 == length) {
                sb.append(base64EncodeChars[i3 >>> 2]);
                sb.append(base64EncodeChars[(i3 & 3) << 4]);
                sb.append("==");
                break;
            }
            int i4 = i2 + 1;
            int i5 = bArr[i2] & 0xff;
            if (i4 == length) {
                sb.append(base64EncodeChars[i3 >>> 2]);
                sb.append(base64EncodeChars[((i3 & 3) << 4) | ((i5 & 240) >>> 4)]);
                sb.append(base64EncodeChars[(i5 & 15) << 2]);
                sb.append("=");
                break;
            }
            int i6 = i4 + 1;
            int i7 = bArr[i4] & 0xff;
            sb.append(base64EncodeChars[i3 >>> 2]);
            sb.append(base64EncodeChars[((i3 & 3) << 4) | ((i5 & 240) >>> 4)]);
            sb.append(base64EncodeChars[((i5 & 15) << 2) | ((i7 & 192) >>> 6)]);
            sb.append(base64EncodeChars[i7 & 63]);
            i = i6;
        }
        return sb.toString();
    }












//    private static final char[] base64EncodeChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', IOUtils.DIR_SEPARATOR_UNIX};
//    private static byte[] base64DecodeChars = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, Utf8.REPLACEMENT_BYTE, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, HttpTokens.COLON, HttpTokens.SEMI_COLON, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, HttpTokens.CARRIAGE_RETURN, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, HttpTokens.SPACE, Framer.ENTER_FRAME_PREFIX, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, Framer.STDIN_FRAME_PREFIX, 46, 47, TarConstants.LF_NORMAL, 49, 50, TarConstants.LF_CHR, -1, -1, -1, -1, -1};

//        private static final char[] base64EncodeChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
//    private static byte[] base64DecodeChars = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};
//
//
//    private Base64Util() {
//    }
//
//    public static String encode(byte[] bArr) {
//        StringBuilder sb = new StringBuilder();
//        int length = bArr.length;
//        int i = 0;
//        while (true) {
//            if (i >= length) {
//                break;
//            }
//            int i2 = i + 1;
//            int i3 = bArr[i] & UByte.MAX_VALUE;
//            if (i2 == length) {
//                sb.append(base64EncodeChars[i3 >>> 2]);
//                sb.append(base64EncodeChars[(i3 & 3) << 4]);
//                sb.append("==");
//                break;
//            }
//            int i4 = i2 + 1;
//            int i5 = bArr[i2] & UByte.MAX_VALUE;
//            if (i4 == length) {
//                sb.append(base64EncodeChars[i3 >>> 2]);
//                sb.append(base64EncodeChars[((i3 & 3) << 4) | ((i5 & 240) >>> 4)]);
//                sb.append(base64EncodeChars[(i5 & 15) << 2]);
//                sb.append("=");
//                break;
//            }
//            i = i4 + 1;
//            int i6 = bArr[i4] & UByte.MAX_VALUE;
//            sb.append(base64EncodeChars[i3 >>> 2]);
//            sb.append(base64EncodeChars[((i3 & 3) << 4) | ((i5 & 240) >>> 4)]);
//            sb.append(base64EncodeChars[((i5 & 15) << 2) | ((i6 & 192) >>> 6)]);
//            sb.append(base64EncodeChars[i6 & 63]);
//        }
//        return sb.toString();
//    }
//
//    /* JADX WARN: Code restructure failed: missing block: B:33:0x007a, code lost:
//        if (r1 != (-1)) goto L_0x007d;
//     */
//    /* JADX WARN: Code restructure failed: missing block: B:34:0x007d, code lost:
//        r0.write(r1 | ((r4 & 3) << 6));
//        r1 = r3;
//     */
//    /*
//        Code decompiled incorrectly, please refer to instructions dump.
//    */
//    public static byte[] decode(String str, String str2) throws Exception {
//        int i;
//        byte b;
//        int i2;
//        byte b2;
//        int i3;
//        byte b3;
//        byte[] bytes = str.getBytes(str2);
//        int length = bytes.length;
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
//        int i4 = 0;
//        while (i4 < length) {
//            while (true) {
//                i = i4 + 1;
//                b = base64DecodeChars[bytes[i4]];
//                if (i >= length || b != -1) {
//                    break;
//                }
//                i4 = i;
//            }
//            if (b == -1) {
//                break;
//            }
//            while (true) {
//                i2 = i + 1;
//                b2 = base64DecodeChars[bytes[i]];
//                if (i2 >= length || b2 != -1) {
//                    break;
//                }
//                i = i2;
//            }
//            if (b2 == -1) {
//                break;
//            }
//            byteArrayOutputStream.write((b << 2) | ((b2 & 48) >>> 4));
//            while (true) {
//                i3 = i2 + 1;
//                byte b4 = bytes[i2];
//                if (b4 == 61) {
//                    return byteArrayOutputStream.toByteArray();
//                }
//                b3 = base64DecodeChars[b4];
//                if (i3 >= length || b3 != -1) {
//                    break;
//                }
//                i2 = i3;
//            }
//            if (b3 == -1) {
//                break;
//            }
//            byteArrayOutputStream.write(((b2 & 15) << 4) | ((b3 & 60) >>> 2));
//            while (true) {
//                int i5 = i3 + 1;
//                byte b5 = bytes[i3];
//                if (b5 == 61) {
//                    return byteArrayOutputStream.toByteArray();
//                }
//                byte b6 = base64DecodeChars[b5];
//                if (i5 >= length || b6 != -1) {
//                    break;
//                }
//                i3 = i5;
//            }
//        }
//        return byteArrayOutputStream.toByteArray();
//    }
}

