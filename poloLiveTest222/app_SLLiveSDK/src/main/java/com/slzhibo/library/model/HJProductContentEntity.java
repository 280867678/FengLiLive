package com.slzhibo.library.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.slzhibo.library.utils.DateUtils;
import com.slzhibo.library.utils.picker.PBitmapUtils;
//import com.slzhibo.library.utils.picker.PBitmapUtils;

import java.io.Serializable;

/* loaded from: classes8.dex */
public class HJProductContentEntity implements Serializable, Parcelable {
    public static final Creator<HJProductContentEntity> CREATOR = new Creator<HJProductContentEntity>() { // from class: com.slzhibo.library.model.HJProductContentEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HJProductContentEntity createFromParcel(Parcel parcel) {
            return new HJProductContentEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HJProductContentEntity[] newArray(int i) {
            return new HJProductContentEntity[i];
        }
    };
    public String bucket;
    public long cursor_id;
    public String displayName;
    public long duration;
    public String fileMD5;
    public String fileName;
    public long fileSize;
    public boolean hasUploadS3Complete;
    public int height;
    public String id;
    public String imgUrl;
    public int index;
    public boolean isPreUpLoadSuc;
    public boolean isUploadBtn;
    public boolean isUploadComplete;
    private boolean isVideo;
    public String mimeType;
    public String objectKey;
    public String onLineStatus;
    public String path;
    public String processStatus;
    public float progress;
    public int s3TaskId;
    public int selectStatus;
    public long time;
    public String uploadId;
    public int uploadStatus;
    private String uriPath;
    public int width;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean hasUploadS3CompleteBoolean() {
        return this.hasUploadS3Complete;
    }

    public boolean isPreUpLoadSucBoolean() {
        return this.isPreUpLoadSuc;
    }

    public boolean isUploadCompleteBoolean() {
        return this.isUploadComplete;
    }

    public boolean isFastUpload() {
        return TextUtils.equals(this.onLineStatus, "1");
    }

    public HJProductContentEntity() {
        this.cursor_id = -1L;
        this.s3TaskId = -1;
        this.progress = 0.0f;
        this.selectStatus = 0;
        this.uploadStatus = 2;
        this.isVideo = false;
    }

    public HJProductContentEntity(boolean z) {
        this.cursor_id = -1L;
        this.s3TaskId = -1;
        this.progress = 0.0f;
        this.selectStatus = 0;
        this.uploadStatus = 2;
        this.isVideo = false;
        this.isUploadBtn = z;
    }

    public boolean isOnLineFileTranscodeSuc() {
        return TextUtils.equals(this.processStatus, "4");
    }

    public boolean isOnLineFileTranscoding() {
        return TextUtils.equals(this.processStatus, "1") || TextUtils.equals(this.processStatus, "3");
    }

    public boolean isOnLineFileTranscodeFail() {
        return TextUtils.equals(this.processStatus, "2") || TextUtils.equals(this.processStatus, "5");
    }

    public int getUploadStatus() {
        if (isOnLineFileTranscodeSuc()) {
            this.uploadStatus = 11;
        } else if (isOnLineFileTranscodeFail()) {
            this.uploadStatus = 13;
        } else if (isOnLineFileTranscoding()) {
            this.uploadStatus = 12;
        }
        return this.uploadStatus;
    }

    public boolean isS3UploadSucBoolean() {
        return this.uploadStatus == 9;
    }

    public boolean isOnlineTranscodingSucBoolean() {
        return this.uploadStatus == 11;
    }

    public boolean isS3UpLoadingBoolean() {
        return this.uploadStatus == 6;
    }

    public boolean isS3UpLoadFailBoolean() {
        return this.uploadStatus == 8;
    }

    public boolean isS3UpLoadPauseBoolean() {
        return this.uploadStatus == 7;
    }

    public boolean isPreUpLoadPauseBoolean() {
        return this.uploadStatus == 4;
    }

    public boolean isPreUpLoadFailBoolean() {
        return this.uploadStatus == 5;
    }

    public boolean isPreUpLoadingBoolean() {
        return this.uploadStatus == 3;
    }

    public void setVideo(boolean z) {
        this.isVideo = z;
    }

    public boolean isVideo() {
        return this.isVideo;
    }

    public Uri getUri() {
        String str = this.uriPath;
        if (str != null && str.length() > 0) {
            return Uri.parse(this.uriPath);
        }
        if (isUriPath()) {
            return Uri.parse(this.path);
        }
        return PBitmapUtils.getContentUri(this.mimeType, this.cursor_id);
    }

    public boolean isUriPath() {
        String str = this.path;
        return str != null && str.contains("content://");
    }

    public void setUriPath(String str) {
        this.uriPath = str;
    }

    public HJProductContentEntity copy() {
        HJProductContentEntity hJProductContentEntity = new HJProductContentEntity();
        hJProductContentEntity.path = this.path;
        hJProductContentEntity.isVideo = this.isVideo;
        hJProductContentEntity.duration = this.duration;
        hJProductContentEntity.height = this.height;
        hJProductContentEntity.width = this.width;
        hJProductContentEntity.cursor_id = this.cursor_id;
        hJProductContentEntity.selectStatus = 0;
        return hJProductContentEntity;
    }

    public String getDurationStr() {
        return DateUtils.secondToString(this.duration / 1000);
    }

    public String toString() {
        return "ProductContentEntity{, path='" + this.path + "', cursor_id='" + this.cursor_id + "'}";
    }

    public boolean isSelect() {
        return this.selectStatus == 1;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.objectKey);
        parcel.writeString(this.bucket);
        parcel.writeString(this.uploadId);
        parcel.writeInt(this.index);
        parcel.writeLong(this.cursor_id);
        parcel.writeString(this.id);
        parcel.writeString(this.fileName);
        parcel.writeString(this.imgUrl);
        parcel.writeString(this.fileMD5);
        parcel.writeString(this.processStatus);
        parcel.writeInt(this.s3TaskId);
        parcel.writeFloat(this.progress);
        parcel.writeString(this.onLineStatus);
        parcel.writeByte(this.isPreUpLoadSuc ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isUploadComplete ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.fileSize);
        parcel.writeByte(this.hasUploadS3Complete ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isUploadBtn ? (byte) 1 : (byte) 0);
        parcel.writeString(this.displayName);
        parcel.writeInt(this.selectStatus);
        parcel.writeInt(this.uploadStatus);
        parcel.writeString(this.path);
        parcel.writeString(this.uriPath);
        parcel.writeString(this.mimeType);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeByte(this.isVideo ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.duration);
        parcel.writeLong(this.time);
    }

    protected HJProductContentEntity(Parcel parcel) {
        this.cursor_id = -1L;
        this.s3TaskId = -1;
        this.progress = 0.0f;
        boolean z = false;
        this.selectStatus = 0;
        this.uploadStatus = 2;
        this.isVideo = false;
        this.objectKey = parcel.readString();
        this.bucket = parcel.readString();
        this.uploadId = parcel.readString();
        this.index = parcel.readInt();
        this.cursor_id = parcel.readLong();
        this.id = parcel.readString();
        this.fileName = parcel.readString();
        this.imgUrl = parcel.readString();
        this.fileMD5 = parcel.readString();
        this.processStatus = parcel.readString();
        this.s3TaskId = parcel.readInt();
        this.progress = parcel.readFloat();
        this.onLineStatus = parcel.readString();
        this.isPreUpLoadSuc = parcel.readByte() != 0;
        this.isUploadComplete = parcel.readByte() != 0;
        this.fileSize = parcel.readLong();
        this.hasUploadS3Complete = parcel.readByte() != 0;
        this.isUploadBtn = parcel.readByte() != 0;
        this.displayName = parcel.readString();
        this.selectStatus = parcel.readInt();
        this.uploadStatus = parcel.readInt();
        this.path = parcel.readString();
        this.uriPath = parcel.readString();
        this.mimeType = parcel.readString();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.isVideo = parcel.readByte() != 0 ? true : z;
        this.duration = parcel.readLong();
        this.time = parcel.readLong();
    }
}
