package com.slzhibo.library.utils.picker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.View;

//import com.amazonaws.mobileconnectors.s3.transferutility.TransferTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;

/* loaded from: classes12.dex */
public class PBitmapUtils {
    public static int[] getImageWidthHeight(Context context, Uri uri) {
        try {
            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            if (openFileDescriptor != null) {
                Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(openFileDescriptor.getFileDescriptor());
                openFileDescriptor.close();
                return new int[]{decodeFileDescriptor.getWidth(), decodeFileDescriptor.getHeight()};
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[]{0, 0};
    }

    public static Bitmap getBitmapFromUri(Context context, Uri uri) {
        try {
            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            if (openFileDescriptor == null) {
                return null;
            }
            Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(openFileDescriptor.getFileDescriptor());
            openFileDescriptor.close();
            return decodeFileDescriptor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int[] getImageWidthHeight(String str) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            return new int[]{options.outWidth, options.outHeight};
        } catch (Exception e) {
            e.printStackTrace();
            return new int[]{0, 0};
        }
    }

    public static File getPickerFileDirectory(Context context) {
        File file = new File(context.getExternalFilesDir(null), "imagePicker");
        if (file.exists() || file.mkdirs()) {
        }
        return file;
    }

    public static File getDCIMDirectory() {
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        return !externalStoragePublicDirectory.exists() ? Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) : externalStoragePublicDirectory;
    }

    public static String saveBitmapToFile(Context context, Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat) {
        File pickerFileDirectory = getPickerFileDirectory(context);
        File file = new File(pickerFileDirectory, str + "." + compressFormat.toString().toLowerCase());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(compressFormat, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            if (file.exists()) {
                file.delete();
            }
            return "Exception:" + e.getMessage();
        }
    }

    public static Uri saveBitmapToDCIM(Context context, Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str);
        contentValues.put("mime_type", "image/" + compressFormat.toString());
        contentValues.put("width", Integer.valueOf(bitmap.getWidth()));
        contentValues.put("height", Integer.valueOf(bitmap.getHeight()));
        try {
            contentValues.put("_data", getDCIMDirectory().getAbsolutePath() + File.separator + str + ("." + compressFormat.toString().toLowerCase()));
        } catch (Exception unused) {
        }
        Uri insert = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        if (insert != null) {
            try {
                OutputStream openOutputStream = context.getContentResolver().openOutputStream(insert);
                if (openOutputStream != null) {
                    bitmap.compress(compressFormat, 90, openOutputStream);
                    openOutputStream.flush();
                    openOutputStream.close();
                    return insert;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return insert;
    }

    public static UriPathInfo copyFileToDCIM(Context context, String str, String str2, MimeType mimeType) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str2);
        contentValues.put("mime_type", mimeType.toString());
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
        }
        boolean isImage = MimeType.isImage(mimeType.toString());
        if (isImage) {
            int[] imageWidthHeight = getImageWidthHeight(str);
            contentValues.put("width", Integer.valueOf(imageWidthHeight[0]));
            contentValues.put("height", Integer.valueOf(imageWidthHeight[1]));
        } else {
            contentValues.put("duration", Long.valueOf(getLocalVideoDuration(str)));
        }
        String str3 = getDCIMDirectory().getAbsolutePath() + File.separator + str2 + ("." + mimeType.getSuffix());
        try {
            contentValues.put("_data", str3);
        } catch (Exception unused) {
        }
        Uri insert = context.getContentResolver().insert(isImage ? MediaStore.Images.Media.EXTERNAL_CONTENT_URI : MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
        copyFile(context, str, insert);
        return new UriPathInfo(insert, str3);
    }

    private static boolean copyFile(Context context, String str, Uri uri) {
        Throwable th;
        OutputStream outputStream;
        Exception e;
        if (uri == null) {
            return false;
        }
        FileInputStream fileInputStream = null;
        try {
            outputStream = context.getContentResolver().openOutputStream(uri);
            if (outputStream == null) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return false;
            }
            try {
                try {
                    File file = new File(str);
                    if (file.exists()) {
                        FileInputStream fileInputStream2 = new FileInputStream(file);
                        try {
                            boolean copyFileWithStream = copyFileWithStream(outputStream, fileInputStream2);
                            try {
                                fileInputStream2.close();
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            return copyFileWithStream;
                        } catch (Exception e4) {
                            e = e4;
                            fileInputStream = fileInputStream2;
                            e.printStackTrace();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                    return false;
                                }
                            }
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e6) {
                                    e6.printStackTrace();
                                    throw th;
                                }
                            }
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            throw th;
                        }
                    } else {
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        return false;
                    }
                } catch (Exception e8) {
                    e = e8;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e9) {
            e = e9;
            outputStream = null;
        } catch (Throwable th4) {
            th = th4;
            outputStream = null;
        }
        return false;
    }

    private static boolean copyFileWithStream(OutputStream outputStream, InputStream inputStream) {
        if (outputStream == null || inputStream == null) {
            return false;
        }
        try {
            try {
                byte[] bArr = new byte[1444];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    outputStream.flush();
                }
                try {
                    outputStream.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
                try {
                    outputStream.close();
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return false;
            }
        } catch (Throwable th) {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    public static Bitmap getViewBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), View.MeasureSpec.EXACTLY));
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        return view.getDrawingCache(true);
    }

    public static Bitmap getVideoThumb(String str) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(str);
        return mediaMetadataRetriever.getFrameAtTime();
    }

    public static long getLocalVideoDuration(String str) {
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            return Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static void refreshGalleryAddPic(Context context, Uri uri) {
        if (context != null) {
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(uri);
            context.sendBroadcast(intent);
        }
    }

    public static String getMimeTypeFromUri(Activity activity, Uri uri) {
        return activity.getContentResolver().getType(uri);
    }

    public static String getMimeTypeFromPath(String str) {
        return URLConnection.getFileNameMap().getContentTypeFor(new File(str).getName());
    }

    public static Uri getImageContentUri(Context context, String str) {
        Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{str}, null);
        if (query == null || !query.moveToFirst()) {
            return null;
        }
        @SuppressLint("Range") int i = query.getInt(query.getColumnIndex("_id"));
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        query.close();
        return Uri.withAppendedPath(uri, "" + i);
    }

    public static Uri getVideoContentUri(Context context, String str) {
        Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{str}, null);
        if (query == null || !query.moveToFirst()) {
            return null;
        }
        @SuppressLint("Range") int i = query.getInt(query.getColumnIndex("_id"));
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        query.close();
        return Uri.withAppendedPath(uri, "" + i);
    }

    public static Uri getContentUri(String str, long j) {
        Uri uri;
        if (j <= 0) {
            return null;
        }
        if (MimeType.isImage(str)) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if (MimeType.isVideo(str)) {
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else {
            uri = MediaStore.Files.getContentUri("external");
        }
        return ContentUris.withAppendedId(uri, j);
    }
}
