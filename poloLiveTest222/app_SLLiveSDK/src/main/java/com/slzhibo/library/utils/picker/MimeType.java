package com.slzhibo.library.utils.picker;



import androidx.collection.ArraySet;
//import com.google.android.exoplayer2.util.MimeTypes;
import com.luck.picture.lib.config.PictureMimeType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
//import org.fourthline.cling.support.model.dlna.DLNAProfiles;

/* loaded from: classes12.dex */
public enum MimeType {
    JPEG("image/jpeg", arraySetOf("jpg", "jpeg")),
    PNG("image/png", arraySetOf("png")),
    GIF("image/gif", arraySetOf("gif")),
    BMP("image/x-ms-bmp", arraySetOf("bmp", "x-ms-bmp")),
    WEBP("image/webp", arraySetOf("webp")),
    MPEG("video/mpeg", arraySetOf("mpeg", "mpg")),
    MP4("video/mp4", arraySetOf("mp4")),
    QUICKTIME("video/quicktime", arraySetOf("mov", "quicktime")),
    THREEGPP("video/3gpp", arraySetOf("3gp", "3gpp")),
    THREEGPP2("video/3gpp2", arraySetOf("3g2", "3gpp2")),
    MKV("video/x-matroska", arraySetOf("mkv", "x-matroska")),
    WEBM("/webm", arraySetOf("webm")),
    TS("video/mp2ts", arraySetOf("ts", "mp2ts")),
    AVI("video/avi", arraySetOf("avi")),
    FLV("video/flv", arraySetOf("flv"));

    private final Set<String> mExtensions;
    private final String mMimeTypeName;

    MimeType(String str, Set set) {
        this.mMimeTypeName = str;
        this.mExtensions = set;
    }

    public Set<String> getExtensions() {
        return this.mExtensions;
    }

    public String getSuffix() {
        return (String) new ArrayList(this.mExtensions).get(0);
    }

    public static Set<MimeType> ofAll() {
        return EnumSet.allOf(MimeType.class);
    }

    public static Set<MimeType> of(MimeType mimeType, MimeType... mimeTypeArr) {
        return EnumSet.of(mimeType, mimeTypeArr);
    }

    public static Set<MimeType> ofImage() {
        return EnumSet.of(JPEG, PNG, GIF, BMP, WEBP);
    }

    public static Set<MimeType> ofVideo() {
        return EnumSet.of(MPEG, MP4, QUICKTIME, THREEGPP, THREEGPP2, MKV, WEBM, TS, AVI, FLV);
    }

    public static boolean isImage(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("image");
    }

    public static boolean isVideo(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("video");
    }

    public static boolean isGif(String str) {
        if (str == null) {
            return false;
        }
        return str.equals(GIF.toString());
    }

    private static Set<String> arraySetOf(String... strArr) {
        return new ArraySet(Arrays.asList(strArr));
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mMimeTypeName;
    }

    public static ArrayList<String> getMimeTypeList(Set<MimeType> set) {
        if (set == null) {
            return new ArrayList<>();
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (MimeType mimeType : set) {
            Set<String> set2 = mimeType.mExtensions;
            if (set2 != null) {
                for (String str : set2) {
                    if (isImage(String.valueOf(mimeType))) {
                        arrayList.add("image/" + str);
                    } else if (isVideo(String.valueOf(mimeType))) {
                        arrayList.add("video/" + str);
                    }
                }
            }
        }
        return arrayList;
    }
}

