package com.slzhibo.library.utils.picker;



import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes12.dex */
public class UriPathInfo implements Serializable, Parcelable {
    public static final Parcelable.Creator<UriPathInfo> CREATOR = new Parcelable.Creator<UriPathInfo>() { // from class: com.slzhibo.library.utils.picker.UriPathInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UriPathInfo createFromParcel(Parcel parcel) {
            return new UriPathInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UriPathInfo[] newArray(int i) {
            return new UriPathInfo[i];
        }
    };
    public String absolutePath;
    public Uri uri;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UriPathInfo(Uri uri, String str) {
        this.uri = uri;
        this.absolutePath = str;
    }

    protected UriPathInfo(Parcel parcel) {
        this.uri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.absolutePath = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.uri, i);
        parcel.writeString(this.absolutePath);
    }
}

