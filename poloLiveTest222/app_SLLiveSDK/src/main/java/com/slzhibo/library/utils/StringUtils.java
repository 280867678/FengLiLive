package com.slzhibo.library.utils;
//package com.slzhibo.library.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
//import android.support.annotation.ColorRes;
//import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

//import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
//import com.microsoft.appcenter.Constants;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import kotlin.UByte;

public class StringUtils {
    private static final String COMMA_STR = ",";
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private StringUtils() {
    }

    public static String getRandomString(int i) {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

    public static String makeGUID() {
        return UUID.randomUUID().toString();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String[] split(String str, String str2) {
        int i = 0;
        if (str == null || str2 == null || str.trim().equals("") || str2.trim().equals("")) {
            return new String[]{str};
        }
        ArrayList arrayList = new ArrayList();
        int length = str2.length();
        while (true) {
            if (i < str.length()) {
                int indexOf = str.indexOf(str2, i);
                if (indexOf < 0) {
                    arrayList.add(str.substring(i));
                    break;
                }
                arrayList.add(str.substring(i, indexOf));
                i = indexOf + length;
            } else if (i == str.length()) {
                arrayList.add("");
            }
        }
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }

    public static String modifyUrl(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\\') {
                charAt = '/';
            }
            if (charAt > 256 || charAt == ' ' || charAt == '[' || charAt == ']' || charAt == '.' || charAt == '(' || charAt == ')') {
                sb.append(UrlEncoderUtils.encode("" + charAt, "UTF-8"));
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    public static String errEncode(String str) {
        if (TextUtils.isEmpty(str) || Pattern.compile("[\\u4e00-\\u9fa5\\u0800-\\u4e00]+").matcher(str).find()) {
            return str;
        }
        try {
            return new String(str.getBytes(StandardCharsets.ISO_8859_1), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static boolean isErrCode(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return !Pattern.compile("[\\u4e00-\\u9fa5]+").matcher(str).find();
    }

    public static String add0IfLgTen(int i) {
        if (i <= 0 || i >= 10) {
            return i + ".";
        }
        return "0" + i + ".";
    }

    @SuppressLint({"DefaultLocale"})
    public static String getSizeText(long j) {
        if (j <= 0) {
            return "0.0M";
        }
        if (j < 102400) {
            return "0.1M";
        }
        return String.format("%.1f", Float.valueOf((((float) j) / 1024.0f) / 1024.0f)) + "M";
    }

    public static String getSizeText(Context context, long j) {
        return j < 0 ? "" : Formatter.formatFileSize(context, j);
    }

    public static String spiltImageName(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String lowerCase = str.toLowerCase();
        int lastIndexOf = lowerCase.lastIndexOf(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME);
        if (lastIndexOf == -1) {
            int lastIndexOf2 = lowerCase.lastIndexOf("/");
            if (lastIndexOf2 == -1) {
                return null;
            }
            i = lastIndexOf2 + 1;
        } else {
            i = lastIndexOf + 9;
        }
        int indexOf = lowerCase.indexOf(".jpg", i);
        if (indexOf == -1 && (indexOf = lowerCase.indexOf(".png", i)) == -1) {
            return null;
        }
        return lowerCase.substring(i, indexOf + 4);
    }

    public static String hashImageName(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        String lowerCase = str.toLowerCase();
        String lowerCase2 = str2.toLowerCase();
        int indexOf = lowerCase.indexOf(".jpg");
        if (indexOf == -1) {
            indexOf = lowerCase.indexOf(".png");
        }
        return lowerCase2 + lowerCase.substring(indexOf);
    }

    public static String getExceptionString(Exception exc) {
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString().replace("\n", "<br />");
    }

    public static String imeiToBigInteger(String str) {
        BigInteger bigInteger = new BigInteger("0");
        try {
            BigInteger bigInteger2 = new BigInteger("16");
            String md5 = MD5Utils.getMd5(str);
            int length = md5.length();
            for (int i = 0; i < length; i++) {
                bigInteger = bigInteger.add(new BigInteger("" + md5.charAt(i), 16).multiply(bigInteger2.pow((length - 1) - i)));
            }
            return bigInteger.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return bigInteger.toString();
        }
    }

    public static BigInteger imeiTolong(String str) {
        BigInteger bigInteger = new BigInteger("0");
        try {
            BigInteger bigInteger2 = new BigInteger("16");
            String md5 = MD5Utils.getMd5(str);
            int length = md5.length();
            for (int i = 0; i < length; i++) {
                bigInteger = bigInteger.add(new BigInteger("" + md5.charAt(i), 16).multiply(bigInteger2.pow((length - 1) - i)));
            }
            return bigInteger;
        } catch (Exception e) {
            e.printStackTrace();
            return bigInteger;
        }
    }

    public static int countWords(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return str.getBytes("GBK").length;
            }
            return 0;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean versionOver(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        if (str.startsWith("v")) {
            str = str.substring(1);
        }
        if (str2.startsWith("v")) {
            str2 = str2.substring(1);
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int length = split.length;
        if (length >= split2.length) {
            length = split2.length;
        }
        for (int i = 0; i < length; i++) {
            try {
                int parseInt = Integer.parseInt(split[i]);
                int parseInt2 = Integer.parseInt(split2[i]);
                if (parseInt < parseInt2) {
                    return false;
                }
                if (parseInt > parseInt2) {
                    return true;
                }
            } catch (NumberFormatException unused) {
                return false;
            }
        }
        if (split.length >= split2.length) {
            return true;
        }
        return false;
    }

    public static String formatTime(DateFormat dateFormat, long j) {
        if (String.valueOf(j).length() < 13) {
            j *= 1000;
        }
        return dateFormat.format(new Date(j));
    }

    public static ArrayList<String> splitString(String str, String str2) {
        String[] split;
        if (TextUtils.isEmpty(str) || (split = split(str, str2)) == null || split.length == 0) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, split);
        return arrayList;
    }

    public static String mergeString(List<String> list, String str) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append(list.get(i));
            if (i < size - 1) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String bytesToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static String formatFilePath(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.replace("\\", "").replace("/", "").replace("*", "").replace("?", "").replace(":", "").replace("\"", "").replace("<", "").replace(">", "").replace("|", "");
    }

    public static String hashKeyForDisk(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String md5 = MD5Utils.getMd5(str);
        return md5 == null ? String.valueOf(str.hashCode()) : md5;
    }

    public static String getFoldStringByLength(String str, int i) {
        if (TextUtils.isEmpty(str) || i == 0) {
            return "";
        }
        if (i >= str.length()) {
            return str;
        }
        return str.substring(0, i) + "…";
    }

    public static double getLength(String str) {
        double d = 0.0d;
        if (TextUtils.isEmpty(str)) {
            return 0.0d;
        }
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 1;
            d += str.substring(i, i2).matches("[一-龥]") ? 2.0d : 1.0d;
            i = i2;
        }
        return Math.ceil(d);
    }

    public static String substring(String str, int i, int i2) {
        int i3 = i;
        int i4 = 0;
        while (i3 < str.length()) {
            int i5 = i3 + 1;
            i4 = str.substring(i3, i5).matches("[一-龥]") ? i4 + 2 : i4 + 1;
            if (i4 == i2) {
                return str.substring(i, i5);
            }
            if (i4 > i2) {
                return str.substring(i, i3);
            }
            i3 = i5;
        }
        return str;
    }

    public static String substring(String str, int i, boolean z) {
        String substring = substring(str, 0, i);
        if (substring.equals(str)) {
            return str;
        }
        return substring + "...";
    }

    public static String formatStrLen(String str, int i) {
        if (TextUtils.isEmpty(str) || str.length() <= i) {
            return str;
        }
        String substring = str.substring(0, i);
        return substring + "...";
    }

    public static void modifyTextViewDrawable(TextView textView, Drawable drawable, int i) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        if (i == 0) {
            textView.setCompoundDrawables(drawable, null, null, null);
        } else if (i == 1) {
            textView.setCompoundDrawables(null, drawable, null, null);
        } else if (i == 2) {
            textView.setCompoundDrawables(null, null, drawable, null);
        } else {
            textView.setCompoundDrawables(null, null, null, drawable);
        }
    }

    public static String formatPhoneRemoveSpaces(String str) {
        return str.replaceAll(ConstantUtils.PLACEHOLDER_STR_ONE, "").replaceAll("-", "");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00a9, code lost:
        r12 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00aa, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00ac, code lost:
        r12 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00ad, code lost:
        r7 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x00a9 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c3 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c8 A[SYNTHETIC, Splitter:B:31:0x00c8] */
    public static String getFileMD5(File file) {
        StringBuffer stringBuffer = null;
        Exception e;
        FileInputStream fileInputStream = null;
        try {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                MappedByteBuffer map = fileInputStream2.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(map);
                byte[] digest = instance.digest();
                stringBuffer = new StringBuffer(digest.length * 2);
                for (byte b : digest) {
                    char c = cArr[(b & 240) >> 4];
                    char c2 = cArr[b & 15];
                    stringBuffer.append(c);
                    stringBuffer.append(c2);
                }
                try {
                    fileInputStream2.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } catch (Exception e3) {
                e = e3;
                fileInputStream = fileInputStream2;
                try {
                    e.printStackTrace();
                    if (fileInputStream != null) {
                    }
                    if (stringBuffer == null) {
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    try {
                        throw th2;
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
            }
        } catch (Exception e5) {
            e = e5;
            stringBuffer = null;
            e.printStackTrace();
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (stringBuffer == null) {
            }
        }
        if (stringBuffer == null) {
            return stringBuffer.toString();
        }
        return "";
    }

    public static byte[] toByteArray(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String lowerCase = str.toLowerCase();
        byte[] bArr = new byte[(lowerCase.length() / 2)];
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) ((((byte) (Character.digit(lowerCase.charAt(i), 16) & 255)) << 4) | ((byte) (Character.digit(lowerCase.charAt(i + 1), 16) & 255)));
            i += 2;
        }
        return bArr;
    }

    public static String toHexString(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & UByte.MAX_VALUE) < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(bArr[i] & UByte.MAX_VALUE));
        }
        return sb.toString().toLowerCase();
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0049  */
    public static String compress(String str) throws IOException {
        GZIPOutputStream gZIPOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(str.getBytes());
                    gZIPOutputStream.close();
                    String byteArrayOutputStream2 = byteArrayOutputStream.toString("ISO-8859-1");
                    gZIPOutputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream2;
                } catch (Exception unused) {
                    if (gZIPOutputStream != null) {
                    }
                    if (byteArrayOutputStream != null) {
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (gZIPOutputStream != null) {
                    }
                    if (byteArrayOutputStream != null) {
                    }
                    throw th;
                }
            } catch (Exception unused2) {
                gZIPOutputStream = null;
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return str;
            } catch (Throwable th3) {
                th = th3;
                gZIPOutputStream = null;
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        } catch (Exception unused3) {
            byteArrayOutputStream = null;
            gZIPOutputStream = null;
            if (gZIPOutputStream != null) {
            }
            if (byteArrayOutputStream != null) {
            }
            return str;
        } catch (Throwable th4) {
            th = th4;
            byteArrayOutputStream = null;
            gZIPOutputStream = null;
            if (gZIPOutputStream != null) {
            }
            if (byteArrayOutputStream != null) {
            }
            try {
                throw th;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0069  */
    public static String uncompress(String str) throws IOException {
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        GZIPInputStream gZIPInputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayInputStream = new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1));
            } catch (Exception unused) {
                byteArrayInputStream = null;
                if (byteArrayOutputStream != null) {
                }
                if (gZIPInputStream != null) {
                }
                if (byteArrayInputStream != null) {
                }
                return str;
            } catch (Throwable th2) {
                th = th2;
                byteArrayInputStream = null;
                if (byteArrayOutputStream != null) {
                }
                if (gZIPInputStream != null) {
                }
                if (byteArrayInputStream != null) {
                }
                throw th;
            }
            try {
                GZIPInputStream gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream);
                try {
                    byte[] bArr = new byte[256];
                    while (true) {
                        int read = gZIPInputStream2.read(bArr);
                        if (read >= 0) {
                            byteArrayOutputStream.write(bArr, 0, read);
                        } else {
                            String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                            byteArrayOutputStream.close();
                            gZIPInputStream2.close();
                            byteArrayInputStream.close();
                            return byteArrayOutputStream2;
                        }
                    }
                } catch (Exception unused2) {
                    gZIPInputStream = gZIPInputStream2;
                    if (byteArrayOutputStream != null) {
                    }
                    if (gZIPInputStream != null) {
                    }
                    if (byteArrayInputStream != null) {
                    }
                    return str;
                } catch (Throwable th3) {
                    th = th3;
                    gZIPInputStream = gZIPInputStream2;
                    if (byteArrayOutputStream != null) {
                    }
                    if (gZIPInputStream != null) {
                    }
                    if (byteArrayInputStream != null) {
                    }
                    throw th;
                }
            } catch (Exception unused3) {
                if (byteArrayOutputStream != null) {
                }
                if (gZIPInputStream != null) {
                }
                if (byteArrayInputStream != null) {
                }
                return str;
            } catch (Throwable th4) {
                th = th4;
                if (byteArrayOutputStream != null) {
                }
                if (gZIPInputStream != null) {
                }
                if (byteArrayInputStream != null) {
                }
                throw th;
            }
        } catch (Exception unused4) {
            byteArrayOutputStream = null;
            byteArrayInputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (gZIPInputStream != null) {
                gZIPInputStream.close();
            }
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
            return str;
        } catch (Throwable th5) {
            th = th5;
            byteArrayOutputStream = null;
            byteArrayInputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (gZIPInputStream != null) {
                gZIPInputStream.close();
            }
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
            try {
                throw th;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return str;
    }

    public static String bytes2HexString(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return "";
        }
        char[] cArr = new char[(length << 1)];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i] = cArr2[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    public static byte[] hexString2Bytes(String str) {
        if (isSpace(str)) {
            return null;
        }
        int length = str.length();
        if (length % 2 != 0) {
            str = "0" + str;
            length++;
        }
        char[] charArray = str.toUpperCase().toCharArray();
        byte[] bArr = new byte[(length >> 1)];
        for (int i = 0; i < length; i += 2) {
            bArr[i >> 1] = (byte) ((hex2Dec(charArray[i]) << 4) | hex2Dec(charArray[i + 1]));
        }
        return bArr;
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static int hex2Dec(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 'A') + 10;
        }
        throw new IllegalArgumentException();
    }

    public static SpannableString getHighLightText(Context context, String str, String str2, @ColorRes int i) {
        SpannableString spannableString = new SpannableString(str);
        try {
            Matcher matcher = Pattern.compile(str2).matcher(str);
            while (matcher.find()) {
                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, i)), matcher.start(), matcher.end(), 33);
            }
        } catch (Exception unused) {
        }
        return spannableString;
    }

    public static SpannableString getHighLightTextOfStarLength(Context context, String str, String str2, @ColorRes int i) {
        SpannableString spannableString = new SpannableString(str);
        try {
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, i)), 0, str2.length(), 33);
        } catch (Exception unused) {
        }
        return spannableString;
    }

    public static String getCommaSpliceStrByList(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
            sb.append(COMMA_STR);
        }
        if (sb.length() == 0) {
            return "";
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static List<String> getListByCommaSplit(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (str.contains(COMMA_STR)) {
            for (String str2 : TextUtils.split(str, COMMA_STR)) {
                arrayList.add(str2);
            }
        } else {
            arrayList.add(str);
        }
        return arrayList;
    }

    public static List<Integer> getListByCommaSplitToInteger(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (str.contains(COMMA_STR)) {
            for (String str2 : TextUtils.split(str, COMMA_STR)) {
                arrayList.add(new Integer(str2));
            }
        } else {
            arrayList.add(new Integer(str));
        }
        return arrayList;
    }
}























//package com.tomatolive.library.utils;

//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.drawable.Drawable;
////import android.support.annotation.ColorRes;
////import android.support.v4.content.ContextCompat;
//import android.text.SpannableString;
//import android.text.TextUtils;
//import android.text.format.Formatter;
//import android.text.style.ForegroundColorSpan;
//import android.widget.TextView;
//
//import androidx.annotation.ColorRes;
//import androidx.core.content.ContextCompat;
//
////import com.j256.ormlite.stmt.query.SimpleComparison;
//import com.tencent.smtt.sdk.TbsVideoCacheTask;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.io.UnsupportedEncodingException;
//import java.math.BigInteger;
//import java.nio.MappedByteBuffer;
//import java.nio.channels.FileChannel;
//import java.security.MessageDigest;
//import java.text.DateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//import java.util.UUID;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.zip.GZIPInputStream;
//import java.util.zip.GZIPOutputStream;
//import org.slf4j.Marker;
//
///* loaded from: classes4.dex */
//public class StringUtils {
//    private static final String COMMA_STR = ",";
//    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
//
//    private StringUtils() {
//    }
//
//    public static String getRandomString(int i) {
//        Random random = new Random(System.currentTimeMillis());
//        StringBuilder sb = new StringBuilder();
//        for (int i2 = 0; i2 < i; i2++) {
//            sb.append("abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62)));
//        }
//        return sb.toString();
//    }
//
//    public static String makeGUID() {
//        return UUID.randomUUID().toString();
//    }
//
//    public static String getUUID() {
//        return UUID.randomUUID().toString().replaceAll("-", "");
//    }
//
//    public static String[] split(String str, String str2) {
//        int i = 0;
//        if (str == null || str2 == null || str.trim().equals("") || str2.trim().equals("")) {
//            return new String[]{str};
//        }
//        ArrayList arrayList = new ArrayList();
//        int length = str2.length();
//        while (true) {
//            if (i < str.length()) {
//                int indexOf = str.indexOf(str2, i);
//                if (indexOf < 0) {
//                    arrayList.add(str.substring(i));
//                    break;
//                }
//                arrayList.add(str.substring(i, indexOf));
//                i = indexOf + length;
//            } else if (i == str.length()) {
//                arrayList.add("");
//            }
//        }
//        String[] strArr = new String[arrayList.size()];
//        arrayList.toArray(strArr);
//        return strArr;
//    }
//
//    public static String modifyUrl(String str) {
//        if (str == null) {
//            return null;
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < str.length(); i++) {
//            char charAt = str.charAt(i);
//            if (charAt == '\\') {
//                charAt = '/';
//            }
//            if (charAt > 256 || charAt == ' ' || charAt == '[' || charAt == ']' || charAt == '.' || charAt == '(' || charAt == ')') {
//                sb.append(UrlEncoderUtils.encode("" + charAt, "UTF-8"));
//            } else {
//                sb.append(charAt);
//            }
//        }
//        return sb.toString();
//    }
//
//    public static String errEncode(String str) {
//        if (TextUtils.isEmpty(str) || Pattern.compile("[\\u4e00-\\u9fa5\\u0800-\\u4e00]+").matcher(str).find()) {
//            return str;
//        }
//        try {
//            return new String(str.getBytes("iso-8859-1"), "GBK");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            return str;
//        }
//    }
//
//    public static boolean isErrCode(String str) {
//        if (TextUtils.isEmpty(str)) {
//            return false;
//        }
//        return !Pattern.compile("[\\u4e00-\\u9fa5]+").matcher(str).find();
//    }
//
//    public static String add0IfLgTen(int i) {
//        if (i <= 0 || i >= 10) {
//            return i + ".";
//        }
//        return "0" + i + ".";
//    }
//
//    @SuppressLint({"DefaultLocale"})
//    public static String getSizeText(long j) {
//        if (j <= 0) {
//            return "0.0M";
//        }
//        if (j < 102400) {
//            return "0.1M";
//        }
//        return String.format("%.1f", Float.valueOf((((float) j) / 1024.0f) / 1024.0f)) + "M";
//    }
//
//    public static String getSizeText(Context context, long j) {
//        return j < 0 ? "" : Formatter.formatFileSize(context, j);
//    }
//
//    public static String spiltImageName(String str) {
//        int i;
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        String lowerCase = str.toLowerCase();
//        int lastIndexOf = lowerCase.lastIndexOf(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME);
//        if (lastIndexOf == -1) {
//            int lastIndexOf2 = lowerCase.lastIndexOf("/");
//            if (lastIndexOf2 == -1) {
//                return null;
//            }
//            i = lastIndexOf2 + 1;
//        } else {
//            i = lastIndexOf + 9;
//        }
//        int indexOf = lowerCase.indexOf(".jpg", i);
//        if (indexOf == -1 && (indexOf = lowerCase.indexOf(".png", i)) == -1) {
//            return null;
//        }
//        return lowerCase.substring(i, indexOf + 4);
//    }
//
//    public static String hashImageName(String str, String str2) {
//        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
//            return null;
//        }
//        String lowerCase = str.toLowerCase();
//        String lowerCase2 = str2.toLowerCase();
//        int indexOf = lowerCase.indexOf(".jpg");
//        if (indexOf == -1) {
//            indexOf = lowerCase.indexOf(".png");
//        }
//        return lowerCase2 + lowerCase.substring(indexOf);
//    }
//
//    public static String getExceptionString(Exception exc) {
//        StringWriter stringWriter = new StringWriter();
//        exc.printStackTrace(new PrintWriter(stringWriter));
//        return stringWriter.toString().replace("\n", "<br />");
//    }
//
//    public static String imeiToBigInteger(String str) {
//        BigInteger bigInteger = new BigInteger("0");
//        try {
//            BigInteger bigInteger2 = new BigInteger("16");
//            String md5 = MD5Utils.getMd5(str);
//            int length = md5.length();
//            for (int i = 0; i < length; i++) {
//                bigInteger = bigInteger.add(new BigInteger("" + md5.charAt(i), 16).multiply(bigInteger2.pow((length - 1) - i)));
//            }
//            return bigInteger.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return bigInteger.toString();
//        }
//    }
//
//    public static BigInteger imeiTolong(String str) {
//        BigInteger bigInteger = new BigInteger("0");
//        try {
//            BigInteger bigInteger2 = new BigInteger("16");
//            String md5 = MD5Utils.getMd5(str);
//            int length = md5.length();
//            for (int i = 0; i < length; i++) {
//                bigInteger = bigInteger.add(new BigInteger("" + md5.charAt(i), 16).multiply(bigInteger2.pow((length - 1) - i)));
//            }
//            return bigInteger;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return bigInteger;
//        }
//    }
//
//    public static int countWords(String str) {
//        try {
//            if (!TextUtils.isEmpty(str)) {
//                return str.getBytes("GBK").length;
//            }
//            return 0;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//    public static boolean versionOver(String str, String str2) {
//        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
//            return false;
//        }
//        if (str.startsWith("v")) {
//            str = str.substring(1);
//        }
//        if (str2.startsWith("v")) {
//            str2 = str2.substring(1);
//        }
//        String[] split = str.split("\\.");
//        String[] split2 = str2.split("\\.");
//        int length = split.length;
//        if (length >= split2.length) {
//            length = split2.length;
//        }
//        for (int i = 0; i < length; i++) {
//            try {
//                int parseInt = Integer.parseInt(split[i]);
//                int parseInt2 = Integer.parseInt(split2[i]);
//                if (parseInt < parseInt2) {
//                    return false;
//                }
//                if (parseInt > parseInt2) {
//                    return true;
//                }
//            } catch (NumberFormatException unused) {
//                return false;
//            }
//        }
//        return split.length >= split2.length;
//    }
//
//    public static String formatTime(DateFormat dateFormat, long j) {
//        if (String.valueOf(j).length() < 13) {
//            j *= 1000;
//        }
//        return dateFormat.format(new Date(j));
//    }
//
//    public static ArrayList<String> splitString(String str, String str2) {
//        String[] split;
//        if (TextUtils.isEmpty(str) || (split = split(str, str2)) == null || split.length == 0) {
//            return null;
//        }
//        ArrayList<String> arrayList = new ArrayList<>();
//        Collections.addAll(arrayList, split);
//        return arrayList;
//    }
//
//    public static String mergeString(List<String> list, String str) {
//        StringBuilder sb = new StringBuilder();
//        int size = list.size();
//        for (int i = 0; i < size; i++) {
//            sb.append(list.get(i));
//            if (i < size - 1) {
//                sb.append(str);
//            }
//        }
//        return sb.toString();
//    }
//
//    public static String bytesToHex(byte[] bArr) {
//        StringBuilder sb = new StringBuilder();
//        for (byte b2 : bArr) {
//            String hexString = Integer.toHexString(b2 & 255);
//            if (hexString.length() == 1) {
//                sb.append('0');
//            }
//            sb.append(hexString);
//        }
//        return sb.toString();
//    }
//
//    public static boolean isEmpty(CharSequence charSequence) {
//        return charSequence == null || charSequence.length() == 0;
//    }
//
////    public static String formatFilePath(String str) {
////        if (TextUtils.isEmpty(str)) {
////            return null;
////        }
////        return str.replace("\\", "").replace("/", "").replace(Marker.ANY_MARKER, "").replace("?", "").replace(":", "").replace("\"", "").replace(SimpleComparison.LESS_THAN_OPERATION, "").replace(SimpleComparison.GREATER_THAN_OPERATION, "").replace("|", "");
////    }
//
//    public static String hashKeyForDisk(String str) {
//        if (TextUtils.isEmpty(str)) {
//            return str;
//        }
//        String md5 = MD5Utils.getMd5(str);
//        return md5 == null ? String.valueOf(str.hashCode()) : md5;
//    }
//
//    public static String getFoldStringByLength(String str, int i) {
//        if (TextUtils.isEmpty(str) || i == 0) {
//            return "";
//        }
//        if (i >= str.length()) {
//            return str;
//        }
//        return str.substring(0, i) + "…";
//    }
//
//    public static double getLength(String str) {
//        double d = 0.0d;
//        if (TextUtils.isEmpty(str)) {
//            return 0.0d;
//        }
//        int i = 0;
//        while (i < str.length()) {
//            int i2 = i + 1;
//            d += str.substring(i, i2).matches("[一-龥]") ? 2.0d : 1.0d;
//            i = i2;
//        }
//        return Math.ceil(d);
//    }
//
//    public static String substring(String str, int i, int i2) {
//        int i3 = i;
//        int i4 = 0;
//        while (i3 < str.length()) {
//            int i5 = i3 + 1;
//            i4 = str.substring(i3, i5).matches("[一-龥]") ? i4 + 2 : i4 + 1;
//            if (i4 == i2) {
//                return str.substring(i, i5);
//            }
//            if (i4 > i2) {
//                return str.substring(i, i3);
//            }
//            i3 = i5;
//        }
//        return str;
//    }
//
//    public static String substring(String str, int i, boolean z) {
//        String substring = substring(str, 0, i);
//        if (substring.equals(str)) {
//            return str;
//        }
//        return substring + "...";
//    }
//
//    public static String formatStrLen(String str, int i) {
//        if (TextUtils.isEmpty(str) || str.length() <= i) {
//            return str;
//        }
//        String substring = str.substring(0, i);
//        return substring + "...";
//    }
//
//    public static void modifyTextViewDrawable(TextView textView, Drawable drawable, int i) {
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//        if (i == 0) {
//            textView.setCompoundDrawables(drawable, null, null, null);
//        } else if (i == 1) {
//            textView.setCompoundDrawables(null, drawable, null, null);
//        } else if (i == 2) {
//            textView.setCompoundDrawables(null, null, drawable, null);
//        } else {
//            textView.setCompoundDrawables(null, null, null, drawable);
//        }
//    }
//
//    public static String formatPhoneRemoveSpaces(String str) {
//        return str.replaceAll(ConstantUtils.PLACEHOLDER_STR_ONE, "").replaceAll("-", "");
//    }
//
//    /* JADX WARN: Removed duplicated region for block: B:25:0x00be  */
//    /* JADX WARN: Removed duplicated region for block: B:26:0x00c3 A[ORIG_RETURN, RETURN] */
//    /*
//        Code decompiled incorrectly, please refer to instructions dump.
//    */
//    public static String getFileMD5(File file) {
//        StringBuffer stringBuffer = null;
//        Exception e;
//        char[] cArr = new char[0];
//        int i;
//        FileInputStream fileInputStream = null;
//        byte[] digest = new byte[0];
//        FileInputStream fileInputStream2 = null;
//        fileInputStream2 = null;
//        fileInputStream2 = null;
//        try {
//            try {
//                try {
//                    cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
//                    fileInputStream = new FileInputStream(file);
//                } catch (Throwable th) {
//                    th = th;
//                }
//                try {
//                    try {
//                        MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
//                        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//                        messageDigest.update(map);
//                        digest = messageDigest.digest();
//                        stringBuffer = new StringBuffer(digest.length * 2);
//                    } catch (Exception e2) {
//                        e = e2;
//                        stringBuffer = null;
//                    }
//                    try {
//                        int length = digest.length;
//                        for (byte b2 : digest) {
//                            char c = cArr[(b2 & 240) >> 4];
//                            char c2 = cArr[(b2 & 15) == 1 ? 1 : 0];
//                            stringBuffer.append(c);
//                            stringBuffer.append(c2);
//                        }
//                        fileInputStream.close();
////                        fileInputStream2 = length;
//                    } catch (Exception e3) {
//                        e = e3;
//                        fileInputStream2 = fileInputStream;
//                        e.printStackTrace();
//                        if (fileInputStream2 != null) {
//                            fileInputStream2.close();
//                            fileInputStream2 = fileInputStream2;
//                        }
//                        if (stringBuffer == null) {
//                        }
//                    }
//                } catch (Throwable th2) {
////                    th = th2;
//                    fileInputStream2 = fileInputStream;
//                    if (fileInputStream2 != null) {
//                        try {
//                            fileInputStream2.close();
//                        } catch (Exception e4) {
//                            e4.printStackTrace();
//                        }
//                    }
////                    throw th;
//                }
//            } catch (Exception e5) {
//                e = e5;
//                stringBuffer = null;
//            }
//        } catch (Exception e6) {
//            e6.printStackTrace();
//        }
//        return stringBuffer == null ? stringBuffer.toString() : "";
//    }
//
//    public static byte[] toByteArray(String str) {
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        String lowerCase = str.toLowerCase();
//        byte[] bArr = new byte[lowerCase.length() / 2];
//        int i = 0;
//        for (int i2 = 0; i2 < bArr.length; i2++) {
//            bArr[i2] = (byte) ((((byte) (Character.digit(lowerCase.charAt(i), 16) & 255)) << 4) | ((byte) (Character.digit(lowerCase.charAt(i + 1), 16) & 255)));
//            i += 2;
//        }
//        return bArr;
//    }
//
//    public static String toHexString(byte[] bArr) {
//        if (bArr == null || bArr.length < 1) {
//            return "";
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < bArr.length; i++) {
//            if ((bArr[i] & 255) < 16) {
//                sb.append("0");
//            }
//            sb.append(Integer.toHexString(bArr[i] & 255));
//        }
//        return sb.toString().toLowerCase();
//    }
//
//    public static String compress(String str) throws IOException {
//        GZIPOutputStream gZIPOutputStream;
//        ByteArrayOutputStream byteArrayOutputStream;
//        Throwable th;
//        if (TextUtils.isEmpty(str)) {
//            return str;
//        }
//        try {
//            byteArrayOutputStream = new ByteArrayOutputStream();
//            try {
//                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
//            } catch (Exception unused) {
//                gZIPOutputStream = null;
//            } catch (Throwable th2) {
//                th = th2;
//                gZIPOutputStream = null;
//            }
//        } catch (Exception unused2) {
//            byteArrayOutputStream = null;
//            gZIPOutputStream = null;
//        } catch (Throwable th3) {
//            th = th3;
//            byteArrayOutputStream = null;
//            gZIPOutputStream = null;
//        }
//        try {
//            gZIPOutputStream.write(str.getBytes());
//            gZIPOutputStream.close();
//            String byteArrayOutputStream2 = byteArrayOutputStream.toString("ISO-8859-1");
//            gZIPOutputStream.close();
//            byteArrayOutputStream.close();
//            return byteArrayOutputStream2;
//        } catch (Exception unused3) {
//            if (gZIPOutputStream != null) {
//                gZIPOutputStream.close();
//            }
//            if (byteArrayOutputStream != null) {
//                byteArrayOutputStream.close();
//            }
//            return str;
//        } catch (Throwable th4) {
//            th = th4;
//            if (gZIPOutputStream != null) {
//                gZIPOutputStream.close();
//            }
//            if (byteArrayOutputStream != null) {
//                byteArrayOutputStream.close();
//            }
//            try {
//                throw th;
//            } catch (Throwable throwable) {
//                throwable.printStackTrace();
//            }
//        }
//        return str;
//    }
//
//    public static String uncompress(String str) throws IOException {
//        ByteArrayInputStream byteArrayInputStream;
//        ByteArrayOutputStream byteArrayOutputStream;
//        Throwable th;
//        if (TextUtils.isEmpty(str)) {
//            return str;
//        }
//        GZIPInputStream gZIPInputStream = null;
//        try {
//            byteArrayOutputStream = new ByteArrayOutputStream();
//            try {
//                byteArrayInputStream = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
//                try {
//                    GZIPInputStream gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream);
//                    try {
//                        byte[] bArr = new byte[256];
//                        while (true) {
//                            int read = gZIPInputStream2.read(bArr);
//                            if (read >= 0) {
//                                byteArrayOutputStream.write(bArr, 0, read);
//                            } else {
//                                String byteArrayOutputStream2 = byteArrayOutputStream.toString();
//                                byteArrayOutputStream.close();
//                                gZIPInputStream2.close();
//                                byteArrayInputStream.close();
//                                return byteArrayOutputStream2;
//                            }
//                        }
//                    } catch (Exception unused) {
//                        gZIPInputStream = gZIPInputStream2;
//                        if (byteArrayOutputStream != null) {
//                            byteArrayOutputStream.close();
//                        }
//                        if (gZIPInputStream != null) {
//                            gZIPInputStream.close();
//                        }
//                        if (byteArrayInputStream != null) {
//                            byteArrayInputStream.close();
//                        }
//                        return str;
//                    } catch (Throwable th2) {
//                        th = th2;
//                        gZIPInputStream = gZIPInputStream2;
//                        if (byteArrayOutputStream != null) {
//                            byteArrayOutputStream.close();
//                        }
//                        if (gZIPInputStream != null) {
//                            gZIPInputStream.close();
//                        }
//                        if (byteArrayInputStream != null) {
//                            byteArrayInputStream.close();
//                        }
//                        throw th;
//                    }
//                } catch (Exception unused2) {
//                } catch (Throwable th3) {
//                    th = th3;
//                }
//            } catch (Exception unused3) {
//                byteArrayInputStream = null;
//            } catch (Throwable th4) {
//                th = th4;
//                byteArrayInputStream = null;
//            }
//        } catch (Exception unused4) {
//            byteArrayOutputStream = null;
//            byteArrayInputStream = null;
//        } catch (Throwable th5) {
//            th = th5;
//            byteArrayOutputStream = null;
//            byteArrayInputStream = null;
//        }
//        return str;
//    }
//
//    public static String bytes2HexString(byte[] bArr) {
//        int length;
//        if (bArr == null || (length = bArr.length) <= 0) {
//            return "";
//        }
//        char[] cArr = new char[length << 1];
//        int i = 0;
//        for (int i2 = 0; i2 < length; i2++) {
//            int i3 = i + 1;
//            char[] cArr2 = HEX_DIGITS;
//            cArr[i] = cArr2[(bArr[i2] >> 4) & 15];
//            i = i3 + 1;
//            cArr[i3] = cArr2[bArr[i2] & 15];
//        }
//        return new String(cArr);
//    }
//
//    public static byte[] hexString2Bytes(String str) {
//        if (isSpace(str)) {
//            return null;
//        }
//        int length = str.length();
//        if (length % 2 != 0) {
//            str = "0" + str;
//            length++;
//        }
//        char[] charArray = str.toUpperCase().toCharArray();
//        byte[] bArr = new byte[length >> 1];
//        for (int i = 0; i < length; i += 2) {
//            bArr[i >> 1] = (byte) ((hex2Dec(charArray[i]) << 4) | hex2Dec(charArray[i + 1]));
//        }
//        return bArr;
//    }
//
//    private static boolean isSpace(String str) {
//        if (str == null) {
//            return true;
//        }
//        int length = str.length();
//        for (int i = 0; i < length; i++) {
//            if (!Character.isWhitespace(str.charAt(i))) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static int hex2Dec(char c) {
//        if (c >= '0' && c <= '9') {
//            return c - '0';
//        }
//        if (c >= 'A' && c <= 'F') {
//            return (c - 'A') + 10;
//        }
//        throw new IllegalArgumentException();
//    }
//
//    public static SpannableString getHighLightText(Context context, String str, String str2, @ColorRes int i) {
//        SpannableString spannableString = new SpannableString(str);
//        Matcher matcher = Pattern.compile(str2).matcher(str);
//        while (matcher.find()) {
//            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, i)), matcher.start(), matcher.end(), 33);
//        }
//        return spannableString;
//    }
//
//    public static String getCommaSpliceStrByList(List<String> list) {
//        if (list == null || list.isEmpty()) {
//            return "";
//        }
//        StringBuilder sb = new StringBuilder();
//        for (String str : list) {
//            sb.append(str);
//            sb.append(COMMA_STR);
//        }
//        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
//    }
//
//    public static List<String> getListByCommaSplit(String str) {
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        ArrayList arrayList = new ArrayList();
//        if (str.contains(COMMA_STR)) {
//            for (String str2 : TextUtils.split(str, COMMA_STR)) {
//                arrayList.add(str2);
//            }
//        } else {
//            arrayList.add(str);
//        }
//        return arrayList;
//    }
//}









//import android.content.Context;
//import android.text.SpannableString;
//import android.text.TextUtils;
//import android.text.style.ForegroundColorSpan;
//
//import androidx.annotation.ColorRes;
//import androidx.core.content.ContextCompat;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.nio.MappedByteBuffer;
//import java.nio.channels.FileChannel;
//import java.security.MessageDigest;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.zip.GZIPInputStream;
//import java.util.zip.GZIPOutputStream;
//
///* loaded from: classes3.dex */
//public class StringUtils {
//    public static String getRandomString(int i) {
//        Random random = new Random(System.currentTimeMillis());
//        StringBuilder sb = new StringBuilder();
//        for (int i2 = 0; i2 < i; i2++) {
//            sb.append("abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62)));
//        }
//        return sb.toString();
//    }
//
//    public static String bytesToHex(byte[] bArr) {
//        StringBuilder sb = new StringBuilder();
//        for (byte b2 : bArr) {
//            String hexString = Integer.toHexString(b2 & 255);
//            if (hexString.length() == 1) {
//                sb.append('0');
//            }
//            sb.append(hexString);
//        }
//        return sb.toString();
//    }
//
//    public static String formatStrLen(String str, int i) {
//        if (TextUtils.isEmpty(str) || str.length() <= i) {
//            return str;
//        }
//        String substring = str.substring(0, i);
//        return substring + "...";
//    }
//
//    public static String formatPhoneRemoveSpaces(String str) {
//        return str.replaceAll(ConstantUtils.PLACEHOLDER_STR_ONE, "").replaceAll("-", "");
//    }
//
//    /* JADX WARN: Removed duplicated region for block: B:25:0x00bf  */
//    /* JADX WARN: Removed duplicated region for block: B:26:0x00c4 A[ORIG_RETURN, RETURN] */
//    /*
//        Code decompiled incorrectly, please refer to instructions dump.
//    */
//    public static String getFileMD5(File file) {
//        StringBuffer stringBuffer = null;
//        Exception e;
//        char[] cArr = new char[0];
//        int i;
//        FileInputStream fileInputStream = null;
//        byte[] digest = new byte[0];
//        FileInputStream fileInputStream2 = null;
//        fileInputStream2 = null;
//        fileInputStream2 = null;
//        try {
//            try {
//                try {
//                    cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
//                    fileInputStream = new FileInputStream(file);
//                } catch (Throwable th) {
//                    th = th;
//                }
//                try {
//                    try {
//                        MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
//                        MessageDigest instance = MessageDigest.getInstance("MD5");
//                        instance.update(map);
//                        digest = instance.digest();
//                        stringBuffer = new StringBuffer(digest.length * 2);
//                    } catch (Exception e2) {
//                        e = e2;
//                        stringBuffer = null;
//                    }
//                    try {
//                        int length = digest.length;
//                        for (byte b2 : digest) {
//                            char c = cArr[(b2 & 240) >> 4];
//                            char c2 = cArr[(b2 & 15) == 1 ? 1 : 0];
//                            stringBuffer.append(c);
//                            stringBuffer.append(c2);
//                        }
//                        fileInputStream.close();
////                        fileInputStream2 = length;
//                    } catch (Exception e3) {
//                        e = e3;
//                        fileInputStream2 = fileInputStream;
//                        e.printStackTrace();
//                        if (fileInputStream2 != null) {
//                            fileInputStream2.close();
//                            fileInputStream2 = fileInputStream2;
//                        }
//                        if (stringBuffer == null) {
//                        }
//                    }
//                } catch (Throwable th2) {
////                    th = th2;
//                    fileInputStream2 = fileInputStream;
//                    if (fileInputStream2 != null) {
//                        try {
//                            fileInputStream2.close();
//                        } catch (Exception e4) {
//                            e4.printStackTrace();
//                        }
//                    }
////                    throw th;
//                }
//            } catch (Exception e5) {
//                e = e5;
//                stringBuffer = null;
//            }
//        } catch (Exception e6) {
//            e6.printStackTrace();
//        }
//        return stringBuffer == null ? stringBuffer.toString() : "";
//    }
//
//    public static String toHexString(byte[] bArr) {
//        if (bArr == null || bArr.length < 1) {
//            return "";
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < bArr.length; i++) {
//            if ((bArr[i] & 255) < 16) {
//                sb.append("0");
//            }
//            sb.append(Integer.toHexString(bArr[i] & 255));
//        }
//        return sb.toString().toLowerCase();
//    }
//
//    public static String compress(String str) throws IOException {
//        GZIPOutputStream gZIPOutputStream;
//        ByteArrayOutputStream byteArrayOutputStream;
//        Throwable th;
//        if (TextUtils.isEmpty(str)) {
//            return str;
//        }
//        try {
//            byteArrayOutputStream = new ByteArrayOutputStream();
//            try {
//                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
//                try {
//                    gZIPOutputStream.write(str.getBytes());
//                    gZIPOutputStream.close();
//                    String byteArrayOutputStream2 = byteArrayOutputStream.toString("ISO-8859-1");
//                    gZIPOutputStream.close();
//                    byteArrayOutputStream.close();
//                    return byteArrayOutputStream2;
//                } catch (Exception unused) {
//                    if (gZIPOutputStream != null) {
//                        gZIPOutputStream.close();
//                    }
//                    if (byteArrayOutputStream != null) {
//                        byteArrayOutputStream.close();
//                    }
//                    return str;
//                } catch (Throwable th2) {
//                    th = th2;
//                    if (gZIPOutputStream != null) {
//                        gZIPOutputStream.close();
//                    }
//                    if (byteArrayOutputStream != null) {
//                        byteArrayOutputStream.close();
//                    }
//                    throw th;
//                }
//            } catch (Exception unused2) {
//                gZIPOutputStream = null;
//            } catch (Throwable th3) {
//                th = th3;
//                gZIPOutputStream = null;
//            }
//        } catch (Exception unused3) {
//            byteArrayOutputStream = null;
//            gZIPOutputStream = null;
//        } catch (Throwable th4) {
//            th = th4;
//            byteArrayOutputStream = null;
//            gZIPOutputStream = null;
//        }
//
//        return str;
//    }
//
//    public static String uncompress(String str) throws IOException {
//        ByteArrayInputStream byteArrayInputStream;
//        ByteArrayOutputStream byteArrayOutputStream;
//        Throwable th;
//        GZIPInputStream gZIPInputStream = null;
//        if (TextUtils.isEmpty(str)) {
//            return str;
//        }
//        GZIPInputStream gZIPInputStream2 = null;
//        try {
//            byteArrayOutputStream = new ByteArrayOutputStream();
//            try {
//                byteArrayInputStream = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
//                try {
//                    gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
//                } catch (Exception unused) {
//                } catch (Throwable th2) {
//                    th = th2;
//                }
//                try {
//                    byte[] bArr = new byte[256];
//                    while (true) {
//                        int read = gZIPInputStream.read(bArr);
//                        if (read >= 0) {
//                            byteArrayOutputStream.write(bArr, 0, read);
//                        } else {
//                            String byteArrayOutputStream2 = byteArrayOutputStream.toString();
//                            byteArrayOutputStream.close();
//                            gZIPInputStream.close();
//                            byteArrayInputStream.close();
//                            return byteArrayOutputStream2;
//                        }
//                    }
//                } catch (Exception unused2) {
//                    gZIPInputStream2 = gZIPInputStream;
//                    if (byteArrayOutputStream != null) {
//                        byteArrayOutputStream.close();
//                    }
//                    if (gZIPInputStream2 != null) {
//                        gZIPInputStream2.close();
//                    }
//                    if (byteArrayInputStream != null) {
//                        byteArrayInputStream.close();
//                    }
//                    return str;
//                } catch (Throwable th3) {
//                    th = th3;
//                    gZIPInputStream2 = gZIPInputStream;
//                    if (byteArrayOutputStream != null) {
//                        byteArrayOutputStream.close();
//                    }
//                    if (gZIPInputStream2 != null) {
//                        gZIPInputStream2.close();
//                    }
//                    if (byteArrayInputStream != null) {
//                        byteArrayInputStream.close();
//                    }
//                    throw th;
//                }
//            } catch (Exception unused3) {
//                byteArrayInputStream = null;
//            } catch (Throwable th4) {
//                th = th4;
//                byteArrayInputStream = null;
//            }
//        } catch (Exception unused4) {
//            byteArrayOutputStream = null;
//            byteArrayInputStream = null;
//        } catch (Throwable th5) {
//            th = th5;
//            byteArrayOutputStream = null;
//            byteArrayInputStream = null;
//        } return str;
//    }
//
//    public static SpannableString getHighLightText(Context context, String str, String str2, @ColorRes int i) {
//        SpannableString spannableString = new SpannableString(str);
//        try {
//            Matcher matcher = Pattern.compile(str2).matcher(str);
//            while (matcher.find()) {
//                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, i)), matcher.start(), matcher.end(), 33);
//            }
//        } catch (Exception unused) {
//        }
//        return spannableString;
//    }
//
//    public static String getCommaSpliceStrByList(List<String> list) {
//        if (list == null || list.isEmpty()) {
//            return "";
//        }
//        StringBuilder sb = new StringBuilder();
//        for (String str : list) {
//            sb.append(str);
//            sb.append(",");
//        }
//        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
//    }
//
//    public static List<String> getListByCommaSplit(String str) {
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        ArrayList arrayList = new ArrayList();
//        if (str.contains(",")) {
//            for (String str2 : TextUtils.split(str, ",")) {
//                arrayList.add(str2);
//            }
//        } else {
//            arrayList.add(str);
//        }
//        return arrayList;
//    }
//
//
//
//
//}

//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.text.SpannableString;
//import android.text.TextUtils;
//import android.text.format.Formatter;
//import android.text.style.ForegroundColorSpan;
//import android.widget.TextView;
//
//import androidx.annotation.ColorRes;
//import androidx.core.content.ContextCompat;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.io.UnsupportedEncodingException;
//import java.math.BigInteger;
//import java.nio.MappedByteBuffer;
//import java.nio.channels.FileChannel;
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.text.DateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//import java.util.UUID;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.zip.GZIPInputStream;
//import java.util.zip.GZIPOutputStream;
//
//import kotlin.UByte;
////import org.apache.commons.cli.HelpFormatter;
//
///* loaded from: classes12.dex */
//public class StringUtils {
//    private static final String COMMA_STR = ",";
//    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
//
//    private StringUtils() {
//    }
//
//    public static String getRandomString(int i) {
//        Random random = new Random(System.currentTimeMillis());
//        StringBuilder sb = new StringBuilder();
//        for (int i2 = 0; i2 < i; i2++) {
//            sb.append("abcdefghijklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62)));
//        }
//        return sb.toString();
//    }
//
//    public static String makeGUID() {
//        return UUID.randomUUID().toString();
//    }
//
//    public static String getUUID() {
//        return UUID.randomUUID().toString().replaceAll("-", "");
//    }
//
//    public static String[] split(String str, String str2) {
//        int i = 0;
//        if (str == null || str2 == null || str.trim().equals("") || str2.trim().equals("")) {
//            return new String[]{str};
//        }
//        ArrayList arrayList = new ArrayList();
//        int length = str2.length();
//        while (true) {
//            if (i < str.length()) {
//                int indexOf = str.indexOf(str2, i);
//                if (indexOf < 0) {
//                    arrayList.add(str.substring(i));
//                    break;
//                }
//                arrayList.add(str.substring(i, indexOf));
//                i = indexOf + length;
//            } else if (i == str.length()) {
//                arrayList.add("");
//            }
//        }
//        String[] strArr = new String[arrayList.size()];
//        arrayList.toArray(strArr);
//        return strArr;
//    }
//
//    public static String modifyUrl(String str) {
//        if (str == null) {
//            return null;
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < str.length(); i++) {
//            char charAt = str.charAt(i);
//            if (charAt == '\\') {
//                charAt = '/';
//            }
//            if (charAt > 256 || charAt == ' ' || charAt == '[' || charAt == ']' || charAt == '.' || charAt == '(' || charAt == ')') {
//                sb.append(UrlEncoderUtils.encode("" + charAt, "UTF-8"));
//            } else {
//                sb.append(charAt);
//            }
//        }
//        return sb.toString();
//    }
//
//    public static String errEncode(String str) {
//        if (TextUtils.isEmpty(str) || Pattern.compile("[\\u4e00-\\u9fa5\\u0800-\\u4e00]+").matcher(str).find()) {
//            return str;
//        }
//        try {
//            return new String(str.getBytes(StandardCharsets.ISO_8859_1), "GBK");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            return str;
//        }
//    }
//
//    public static boolean isErrCode(String str) {
//        if (TextUtils.isEmpty(str)) {
//            return false;
//        }
//        return !Pattern.compile("[\\u4e00-\\u9fa5]+").matcher(str).find();
//    }
//
//    public static String add0IfLgTen(int i) {
//        if (i <= 0 || i >= 10) {
//            return i + ".";
//        }
//        return "0" + i + ".";
//    }
//
//    @SuppressLint({"DefaultLocale"})
//    public static String getSizeText(long j) {
//        if (j <= 0) {
//            return "0.0M";
//        }
//        if (j < 102400) {
//            return "0.1M";
//        }
//        return String.format("%.1f", Float.valueOf((((float) j) / 1024.0f) / 1024.0f)) + "M";
//    }
//
//    public static String getSizeText(Context context, long j) {
//        return j < 0 ? "" : Formatter.formatFileSize(context, j);
//    }
//
//    public static String spiltImageName(String str) {
//        int i;
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        String lowerCase = str.toLowerCase();
//        int lastIndexOf = lowerCase.lastIndexOf("filename");
//        if (lastIndexOf == -1) {
//            int lastIndexOf2 = lowerCase.lastIndexOf("/");
//            if (lastIndexOf2 == -1) {
//                return null;
//            }
//            i = lastIndexOf2 + 1;
//        } else {
//            i = lastIndexOf + 9;
//        }
//        int indexOf = lowerCase.indexOf(".jpg", i);
//        if (indexOf == -1 && (indexOf = lowerCase.indexOf(".png", i)) == -1) {
//            return null;
//        }
//        return lowerCase.substring(i, indexOf + 4);
//    }
//
//    public static String hashImageName(String str, String str2) {
//        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
//            return null;
//        }
//        String lowerCase = str.toLowerCase();
//        String lowerCase2 = str2.toLowerCase();
//        int indexOf = lowerCase.indexOf(".jpg");
//        if (indexOf == -1) {
//            indexOf = lowerCase.indexOf(".png");
//        }
//        return lowerCase2 + lowerCase.substring(indexOf);
//    }
//
//    public static String getExceptionString(Exception exc) {
//        StringWriter stringWriter = new StringWriter();
//        exc.printStackTrace(new PrintWriter(stringWriter));
//        return stringWriter.toString().replace("\n", "<br />");
//    }
//
//    public static String imeiToBigInteger(String str) {
//        BigInteger bigInteger = new BigInteger("0");
//        try {
//            BigInteger bigInteger2 = new BigInteger("16");
//            String md5 = MD5Utils.getMd5(str);
//            int length = md5.length();
//            for (int i = 0; i < length; i++) {
//                bigInteger = bigInteger.add(new BigInteger("" + md5.charAt(i), 16).multiply(bigInteger2.pow((length - 1) - i)));
//            }
//            return bigInteger.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return bigInteger.toString();
//        }
//    }
//
//    public static BigInteger imeiTolong(String str) {
//        BigInteger bigInteger = new BigInteger("0");
//        try {
//            BigInteger bigInteger2 = new BigInteger("16");
//            String md5 = MD5Utils.getMd5(str);
//            int length = md5.length();
//            for (int i = 0; i < length; i++) {
//                bigInteger = bigInteger.add(new BigInteger("" + md5.charAt(i), 16).multiply(bigInteger2.pow((length - 1) - i)));
//            }
//            return bigInteger;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return bigInteger;
//        }
//    }
//
//    public static int countWords(String str) {
//        try {
//            if (!TextUtils.isEmpty(str)) {
//                return str.getBytes("GBK").length;
//            }
//            return 0;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//    public static boolean versionOver(String str, String str2) {
//        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
//            return false;
//        }
//        if (str.startsWith("v")) {
//            str = str.substring(1);
//        }
//        if (str2.startsWith("v")) {
//            str2 = str2.substring(1);
//        }
//        String[] split = str.split("\\.");
//        String[] split2 = str2.split("\\.");
//        int length = split.length;
//        if (length >= split2.length) {
//            length = split2.length;
//        }
//        for (int i = 0; i < length; i++) {
//            try {
//                int parseInt = Integer.parseInt(split[i]);
//                int parseInt2 = Integer.parseInt(split2[i]);
//                if (parseInt < parseInt2) {
//                    return false;
//                }
//                if (parseInt > parseInt2) {
//                    return true;
//                }
//            } catch (NumberFormatException unused) {
//                return false;
//            }
//        }
//        return split.length >= split2.length;
//    }
//
//    public static String formatTime(DateFormat dateFormat, long j) {
//        if (String.valueOf(j).length() < 13) {
//            j *= 1000;
//        }
//        return dateFormat.format(new Date(j));
//    }
//
//    public static ArrayList<String> splitString(String str, String str2) {
//        String[] split;
//        if (TextUtils.isEmpty(str) || (split = split(str, str2)) == null || split.length == 0) {
//            return null;
//        }
//        ArrayList<String> arrayList = new ArrayList<>();
//        Collections.addAll(arrayList, split);
//        return arrayList;
//    }
//
//    public static String mergeString(List<String> list, String str) {
//        StringBuilder sb = new StringBuilder();
//        int size = list.size();
//        for (int i = 0; i < size; i++) {
//            sb.append(list.get(i));
//            if (i < size - 1) {
//                sb.append(str);
//            }
//        }
//        return sb.toString();
//    }
//
//    public static String bytesToHex(byte[] bArr) {
//        StringBuilder sb = new StringBuilder();
//        for (byte b : bArr) {
//            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
//            if (hexString.length() == 1) {
//                sb.append('0');
//            }
//            sb.append(hexString);
//        }
//        return sb.toString();
//    }
//
//    public static boolean isEmpty(CharSequence charSequence) {
//        return charSequence == null || charSequence.length() == 0;
//    }
//
//    public static String formatFilePath(String str) {
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        return str.replace("\\", "").replace("/", "").replace("*", "").replace("?", "").replace(":", "").replace("\"", "").replace("<", "").replace(">", "").replace("|", "");
//    }
//
//    public static String hashKeyForDisk(String str) {
//        if (TextUtils.isEmpty(str)) {
//            return str;
//        }
//        String md5 = MD5Utils.getMd5(str);
//        return md5 == null ? String.valueOf(str.hashCode()) : md5;
//    }
//
//    public static String getFoldStringByLength(String str, int i) {
//        if (TextUtils.isEmpty(str) || i == 0) {
//            return "";
//        }
//        if (i >= str.length()) {
//            return str;
//        }
//        return str.substring(0, i) + "…";
//    }
//
//    public static double getLength(String str) {
//        double d = 0.0d;
//        if (TextUtils.isEmpty(str)) {
//            return 0.0d;
//        }
//        int i = 0;
//        while (i < str.length()) {
//            int i2 = i + 1;
//            d += str.substring(i, i2).matches("[一-龥]") ? 2.0d : 1.0d;
//            i = i2;
//        }
//        return Math.ceil(d);
//    }
//
//    public static String substring(String str, int i, int i2) {
//        int i3 = i;
//        int i4 = 0;
//        while (i3 < str.length()) {
//            int i5 = i3 + 1;
//            i4 = str.substring(i3, i5).matches("[一-龥]") ? i4 + 2 : i4 + 1;
//            if (i4 == i2) {
//                return str.substring(i, i5);
//            }
//            if (i4 > i2) {
//                return str.substring(i, i3);
//            }
//            i3 = i5;
//        }
//        return str;
//    }
//
//    public static String substring(String str, int i, boolean z) {
//        String substring = substring(str, 0, i);
//        if (substring.equals(str)) {
//            return str;
//        }
//        return substring + "...";
//    }
//
//    public static String formatStrLen(String str, int i) {
//        if (TextUtils.isEmpty(str) || str.length() <= i) {
//            return str;
//        }
//        String substring = str.substring(0, i);
//        return substring + "...";
//    }
//
//    public static void modifyTextViewDrawable(TextView textView, Drawable drawable, int i) {
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//        if (i == 0) {
//            textView.setCompoundDrawables(drawable, null, null, null);
//        } else if (i == 1) {
//            textView.setCompoundDrawables(null, drawable, null, null);
//        } else if (i == 2) {
//            textView.setCompoundDrawables(null, null, drawable, null);
//        } else {
//            textView.setCompoundDrawables(null, null, null, drawable);
//        }
//    }
//
//    public static String formatPhoneRemoveSpaces(String str) {
//        return str.replaceAll(ConstantUtils.PLACEHOLDER_STR_ONE, "").replaceAll("-", "");
//    }
//
//    /* JADX WARN: Removed duplicated region for block: B:25:0x00be  */
//    /* JADX WARN: Removed duplicated region for block: B:26:0x00c3 A[ORIG_RETURN, RETURN] */
//    /*
//        Code decompiled incorrectly, please refer to instructions dump.
//        To view partially-correct code enable 'Show inconsistent code' option in preferences
//    */
////    public static java.lang.String getFileMD5(java.io.File r12) {
////        /*
////            Method dump skipped, instructions count: 211
////            To view this dump change 'Code comments level' option to 'DEBUG'
////        */
////        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.utils.StringUtils.getFileMD5(java.io.File):java.lang.String");
////    }
//
//    public static String getFileMD5(File file) {
//        StringBuffer stringBuffer = null;
//        char[] cArr;
//        FileInputStream fileInputStream;
//        byte[] digest;
//        try {
//            cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
//            fileInputStream = new FileInputStream(file);
//            MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
//            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//            messageDigest.update(map);
//            digest = messageDigest.digest();
//            stringBuffer = new StringBuffer(digest.length * 2);
//
//            for (byte b2 : digest) {
//                char c = cArr[(b2 & 240) >> 4];
//                char c2 = cArr[(b2 & 15) == 1 ? 1 : 0];
//                stringBuffer.append(c);
//                stringBuffer.append(c2);
//            }
//            fileInputStream.close();
//
//        } catch (FileNotFoundException fileNotFoundException) {
//
//            fileNotFoundException.printStackTrace();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
//            noSuchAlgorithmException.printStackTrace();
//        }
//
//
//        return stringBuffer == null ? stringBuffer.toString() : "";
//    }
//
//    public static byte[] toByteArray(String str) {
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        String lowerCase = str.toLowerCase();
//        byte[] bArr = new byte[lowerCase.length() / 2];
//        int i = 0;
//        for (int i2 = 0; i2 < bArr.length; i2++) {
//            bArr[i2] = (byte) ((((byte) (Character.digit(lowerCase.charAt(i), 16) & 255)) << 4) | ((byte) (Character.digit(lowerCase.charAt(i + 1), 16) & 255)));
//            i += 2;
//        }
//        return bArr;
//    }
//
//    public static String toHexString(byte[] bArr) {
//        if (bArr == null || bArr.length < 1) {
//            return "";
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < bArr.length; i++) {
//            if ((bArr[i] & UByte.MAX_VALUE) < 16) {
//                sb.append("0");
//            }
//            sb.append(Integer.toHexString(bArr[i] & UByte.MAX_VALUE));
//        }
//        return sb.toString().toLowerCase();
//    }
//
//    public static String compress(String str) throws Throwable {
//        GZIPOutputStream gZIPOutputStream;
//        ByteArrayOutputStream byteArrayOutputStream;
//        Throwable th;
//        if (TextUtils.isEmpty(str)) {
//            return str;
//        }
//        try {
//            byteArrayOutputStream = new ByteArrayOutputStream();
//            try {
//                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
//            } catch (Exception unused) {
//                gZIPOutputStream = null;
//            } catch (Throwable th2) {
//                th = th2;
//                gZIPOutputStream = null;
//            }
//        } catch (Exception unused2) {
//            byteArrayOutputStream = null;
//            gZIPOutputStream = null;
//        } catch (Throwable th3) {
//            th = th3;
//            byteArrayOutputStream = null;
//            gZIPOutputStream = null;
//        }
//        try {
//            gZIPOutputStream.write(str.getBytes());
//            gZIPOutputStream.close();
//            String byteArrayOutputStream2 = byteArrayOutputStream.toString("ISO-8859-1");
//            gZIPOutputStream.close();
//            byteArrayOutputStream.close();
//            return byteArrayOutputStream2;
//        } catch (Exception unused3) {
//            if (gZIPOutputStream != null) {
//                gZIPOutputStream.close();
//            }
//            if (byteArrayOutputStream != null) {
//                byteArrayOutputStream.close();
//            }
//            return str;
//        } catch (Throwable th4) {
//            th = th4;
//            if (gZIPOutputStream != null) {
//                gZIPOutputStream.close();
//            }
//            if (byteArrayOutputStream != null) {
//                byteArrayOutputStream.close();
//            }
//            throw th;
//        }
//    }
//
//    public static String uncompress(String str) throws IOException {
//        ByteArrayInputStream byteArrayInputStream;
//        ByteArrayOutputStream byteArrayOutputStream;
//        Throwable th;
//        if (TextUtils.isEmpty(str)) {
//            return str;
//        }
//        GZIPInputStream gZIPInputStream = null;
//        try {
//            byteArrayOutputStream = new ByteArrayOutputStream();
//            try {
//                byteArrayInputStream = new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1));
//                try {
//                    GZIPInputStream gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream);
//                    try {
//                        byte[] bArr = new byte[256];
//                        while (true) {
//                            int read = gZIPInputStream2.read(bArr);
//                            if (read >= 0) {
//                                byteArrayOutputStream.write(bArr, 0, read);
//                            } else {
//                                String byteArrayOutputStream2 = byteArrayOutputStream.toString();
//                                byteArrayOutputStream.close();
//                                gZIPInputStream2.close();
//                                byteArrayInputStream.close();
//                                return byteArrayOutputStream2;
//                            }
//                        }
//                    } catch (Exception unused) {
//                        gZIPInputStream = gZIPInputStream2;
//                        if (byteArrayOutputStream != null) {
//                            byteArrayOutputStream.close();
//                        }
//                        if (gZIPInputStream != null) {
//                            gZIPInputStream.close();
//                        }
//                        if (byteArrayInputStream != null) {
//                            byteArrayInputStream.close();
//                        }
//                        return str;
//                    } catch (Throwable th2) {
//                        th = th2;
//                        gZIPInputStream = gZIPInputStream2;
//                        if (byteArrayOutputStream != null) {
//                            byteArrayOutputStream.close();
//                        }
//                        if (gZIPInputStream != null) {
//                            gZIPInputStream.close();
//                        }
//                        if (byteArrayInputStream != null) {
//                            byteArrayInputStream.close();
//                        }
//                        throw th;
//                    }
//                } catch (Exception unused2) {
//                } catch (Throwable th3) {
//                    th = th3;
//                }
//            } catch (Exception unused3) {
//                byteArrayInputStream = null;
//            } catch (Throwable th4) {
//                th = th4;
//                byteArrayInputStream = null;
//            }
//        } catch (Exception unused4) {
//            byteArrayOutputStream = null;
//            byteArrayInputStream = null;
//        } catch (Throwable th5) {
//            th = th5;
//            byteArrayOutputStream = null;
//            byteArrayInputStream = null;
//        }
//        return null;
//    }
//
//    public static String bytes2HexString(byte[] bArr) {
//        int length;
//        if (bArr == null || (length = bArr.length) <= 0) {
//            return "";
//        }
//        char[] cArr = new char[length << 1];
//        int i = 0;
//        for (int i2 = 0; i2 < length; i2++) {
//            int i3 = i + 1;
//            char[] cArr2 = HEX_DIGITS;
//            cArr[i] = cArr2[(bArr[i2] >> 4) & 15];
//            i = i3 + 1;
//            cArr[i3] = cArr2[bArr[i2] & 15];
//        }
//        return new String(cArr);
//    }
//
//    public static byte[] hexString2Bytes(String str) {
//        if (isSpace(str)) {
//            return null;
//        }
//        int length = str.length();
//        if (length % 2 != 0) {
//            str = "0" + str;
//            length++;
//        }
//        char[] charArray = str.toUpperCase().toCharArray();
//        byte[] bArr = new byte[length >> 1];
//        for (int i = 0; i < length; i += 2) {
//            bArr[i >> 1] = (byte) ((hex2Dec(charArray[i]) << 4) | hex2Dec(charArray[i + 1]));
//        }
//        return bArr;
//    }
//
//    private static boolean isSpace(String str) {
//        if (str == null) {
//            return true;
//        }
//        int length = str.length();
//        for (int i = 0; i < length; i++) {
//            if (!Character.isWhitespace(str.charAt(i))) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static int hex2Dec(char c) {
//        if (c >= '0' && c <= '9') {
//            return c - '0';
//        }
//        if (c >= 'A' && c <= 'F') {
//            return (c - 'A') + 10;
//        }
//        throw new IllegalArgumentException();
//    }
//
//    public static SpannableString getHighLightText(Context context, String str, String str2, @ColorRes int i) {
//        SpannableString spannableString = new SpannableString(str);
//        try {
//            Matcher matcher = Pattern.compile(str2).matcher(str);
//            while (matcher.find()) {
//                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, i)), matcher.start(), matcher.end(), 33);
//            }
//        } catch (Exception unused) {
//        }
//        return spannableString;
//    }
//
//    public static SpannableString getHighLightTextOfStarLength(Context context, String str, String str2, @ColorRes int i) {
//        SpannableString spannableString = new SpannableString(str);
//        try {
//            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, i)), 0, str2.length(), 33);
//        } catch (Exception unused) {
//        }
//        return spannableString;
//    }
//
//    public static String getCommaSpliceStrByList(List<String> list) {
//        if (list == null || list.isEmpty()) {
//            return "";
//        }
//        StringBuilder sb = new StringBuilder();
//        for (String str : list) {
//            sb.append(str);
//            sb.append(COMMA_STR);
//        }
//        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
//    }
//
//    public static List<String> getListByCommaSplit(String str) {
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        ArrayList arrayList = new ArrayList();
//        if (str.contains(COMMA_STR)) {
//            for (String str2 : TextUtils.split(str, COMMA_STR)) {
//                arrayList.add(str2);
//            }
//        } else {
//            arrayList.add(str);
//        }
//        return arrayList;
//    }
//
//    public static List<Integer> getListByCommaSplitToInteger(String str) {
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        ArrayList arrayList = new ArrayList();
//        if (str.contains(COMMA_STR)) {
//            for (String str2 : TextUtils.split(str, COMMA_STR)) {
//                arrayList.add(new Integer(str2));
//            }
//        } else {
//            arrayList.add(new Integer(str));
//        }
//        return arrayList;
//    }
//}
