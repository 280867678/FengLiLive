package com.slzhibo.library.utils;

import android.text.TextUtils;
import com.blankj.utilcode.util.ZipUtils;
import com.bumptech.glide.load.Key;
import com.slzhibo.library.http.utils.EncryptUtil;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import okhttp3.ResponseBody;

public class FileUtils {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    static {
        System.getProperty("line.separator");
//        StandardCharsets.UTF_8;
    }

    public static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    public static boolean isFileExists(String str) {
        return isFileExists(getFileByPath(str));
    }

    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    public static boolean isFile(String str) {
        return isFile(getFileByPath(str));
    }

    public static boolean isFile(File file) {
        return file != null && file.exists() && file.isFile();
    }

    public static boolean deleteFile(String str) {
        return deleteFile(getFileByPath(str));
    }

    public static boolean deleteFile(File file) {
        return file != null && (!file.exists() || (file.isFile() && file.delete()));
    }

    public static String getFileMD5ToString(File file) {
        return bytes2HexString(getFileMD5(file));
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x004b A[SYNTHETIC, Splitter:B:30:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0053 A[Catch:{ IOException -> 0x004f }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x005f A[SYNTHETIC, Splitter:B:41:0x005f] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0067 A[Catch:{ IOException -> 0x0063 }] */
    public static byte[] getFileMD5(File file) {
        Throwable th;
        FileInputStream fileInputStream;
        Exception e;
        DigestInputStream digestInputStream;
        DigestInputStream digestInputStream2 = null;
        if (file == null) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                digestInputStream = new DigestInputStream(fileInputStream, MessageDigest.getInstance("MD5"));
            } catch (NoSuchAlgorithmException e2) {
                e = e2;
                digestInputStream = null;
                try {
                    e.printStackTrace();
                    if (digestInputStream != null) {
                        try {
                            digestInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            return null;
                        }
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    digestInputStream2 = digestInputStream;
                    if (digestInputStream2 != null) {
                    }
                    if (fileInputStream != null) {
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (digestInputStream2 != null) {
                    try {
                        digestInputStream2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                        throw th;
                    }
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
            try {
                do {
                } while (digestInputStream.read(new byte[262144]) > 0);
                byte[] digest = digestInputStream.getMessageDigest().digest();
                try {
                    digestInputStream.close();
                    fileInputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return digest;
            } catch (IOException e6) {
                e = e6;
                e.printStackTrace();
                if (digestInputStream != null) {
                }
                if (fileInputStream != null) {
                }
                return null;
            }
        } catch (IOException | NoSuchAlgorithmException e7) {
            e = e7;
            fileInputStream = null;
            digestInputStream = null;
            e.printStackTrace();
            if (digestInputStream != null) {
            }
            if (fileInputStream != null) {
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            if (digestInputStream2 != null) {
            }
            if (fileInputStream != null) {
            }
            try {
                throw th;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }

    public static String getFileExtension(String str) {
        if (isSpace(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf == -1 || lastIndexOf2 >= lastIndexOf) {
            return "";
        }
        return str.substring(lastIndexOf + 1);
    }

    public static InputStream getSVGAFileInputStream(String str) throws FileNotFoundException {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        return new FileInputStream(file);
    }

    private static String bytes2HexString(byte[] bArr) {
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

    public static boolean delAllFile(String str) {
        File file;
        TextUtils.isEmpty(str);
        File file2 = new File(str);
        file2.exists();
        file2.isDirectory();
        String[] list = file2.list();
        boolean z = false;
        for (String str2 : list) {
            if (str.endsWith(File.separator)) {
                file = new File(str + str2);
            } else {
                file = new File(str + File.separator + str2);
            }
            if (file.isFile()) {
                file.delete();
            }
            if (file.isDirectory()) {
                delAllFile(str + "/" + str2);
                delFolder(str + "/" + str2);
                z = true;
            }
        }
        return z;
    }

    public static boolean delFolder(String str) {
        try {
            delAllFile(str);
            return new File(str).delete();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isExist(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0048 A[SYNTHETIC, Splitter:B:22:0x0048] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0053 A[SYNTHETIC, Splitter:B:28:0x0053] */
    public static boolean writByEncrypt(String str, String str2) {
        Throwable th;
        Exception e;
        BufferedWriter bufferedWriter = null;
        try {
            String DESEncrypt = EncryptUtil.DESEncrypt(ConstantUtils.ENCRYPT_FILE_KEY, str);
            File parentFile = new File(str2).getParentFile();
            if (!parentFile.isDirectory()) {
                parentFile.mkdir();
            }
            BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(str2), EncryptUtil.CHARSET));
            try {
                bufferedWriter2.write(DESEncrypt);
                try {
                    bufferedWriter2.close();
                    return true;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return true;
                }
            } catch (Exception e3) {
                e = e3;
                bufferedWriter = bufferedWriter2;
                try {
                    e.printStackTrace();
                    if (bufferedWriter != null) {
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedWriter != null) {
                    }
                    try {
                        throw th;
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedWriter = bufferedWriter2;
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                try {
                    throw th;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        } catch (Exception e5) {
            e = e5;
            e.printStackTrace();
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            return false;
        }
        return false;
    }

    public static boolean unZip(String str, String str2) {
        try {
            List<File> unzipFile = ZipUtils.unzipFile(str, str2);
            return unzipFile != null && !unzipFile.isEmpty();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static String formatSVGAFileName(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(".svga");
        return stringBuffer.toString();
    }

    public static File saveFile(ResponseBody responseBody, String str, String str2) throws IOException {
        BufferedOutputStream bufferedOutputStream;
        InputStream inputStream;
        Throwable th;
        BufferedOutputStream bufferedOutputStream2 = null;
        if (responseBody == null) {
            return null;
        }
        byte[] bArr = new byte[8192];
        try {
            inputStream = responseBody.byteStream();
            try {
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, str2);
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                while (true) {
                    try {
                        int read = inputStream.read(bArr);
                        if (read != -1) {
                            bufferedOutputStream.write(bArr, 0, read);
                        } else {
                            bufferedOutputStream.flush();
                            IOUtils.closeQuietly(inputStream);
                            IOUtils.closeQuietly(bufferedOutputStream);
                            return file2;
                        }
                    } catch (Exception unused) {
                        IOUtils.closeQuietly(inputStream);
                        IOUtils.closeQuietly(bufferedOutputStream);
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream2 = bufferedOutputStream;
                        IOUtils.closeQuietly(inputStream);
                        IOUtils.closeQuietly(bufferedOutputStream2);
                        throw th;
                    }
                }
            } catch (Exception unused2) {
                bufferedOutputStream = null;
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly(bufferedOutputStream);
                return null;
            } catch (Throwable th3) {
                th = th3;
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly(bufferedOutputStream2);
                throw th;
            }
        } catch (Exception unused3) {
            inputStream = null;
            bufferedOutputStream = null;
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(bufferedOutputStream);
            return null;
        } catch (Throwable th4) {
            th = th4;
            inputStream = null;
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(bufferedOutputStream2);
            try {
                throw th;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }
}
