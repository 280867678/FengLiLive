package com.blmvl.blvl.bean;

//package com.blmvl.blvl.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.comod.baselib.list.BaseListViewAdapter;

/* loaded from: classes.dex */
public class VideoCollectInfoBean extends BaseListViewAdapter.C0890c implements Parcelable {
    public static final Parcelable.Creator<VideoCollectInfoBean> CREATOR = new Parcelable.Creator<VideoCollectInfoBean>() { // from class: com.blmvl.blvl.bean.VideoCollectInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VideoCollectInfoBean createFromParcel(Parcel parcel) {
            return new VideoCollectInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VideoCollectInfoBean[] newArray(int i) {
            return new VideoCollectInfoBean[i];
        }
    };
    public String desp;

    /* renamed from: id */
    public int f1058id;
    public String image_url;
    public int is_like;
    public int is_top;
    public int like_count;
    public String title;
    public UserBean user;
    public int video_count;

    public VideoCollectInfoBean() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDesp() {
        return this.desp;
    }

    public int getId() {
        return this.f1058id;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public int getIs_like() {
        return this.is_like;
    }

    public int getIs_top() {
        return this.is_top;
    }

    public int getLike_count() {
        return this.like_count;
    }

    public String getTitle() {
        return this.title;
    }

    public UserBean getUser() {
        return this.user;
    }

    public int getVideo_count() {
        return this.video_count;
    }

    public void setDesp(String str) {
        this.desp = str;
    }

    public void setId(int i) {
        this.f1058id = i;
    }

    public void setImage_url(String str) {
        this.image_url = str;
    }

    public void setIs_like(int i) {
        this.is_like = i;
    }

    public void setIs_top(int i) {
        this.is_top = i;
    }

    public void setLike_count(int i) {
        this.like_count = i;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUser(UserBean userBean) {
        this.user = userBean;
    }

    public void setVideo_count(int i) {
        this.video_count = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f1058id);
        parcel.writeString(this.title);
        parcel.writeString(this.image_url);
        parcel.writeInt(this.video_count);
        parcel.writeInt(this.like_count);
        parcel.writeString(this.desp);
        parcel.writeInt(this.is_like);
        parcel.writeInt(this.is_top);
        parcel.writeParcelable(this.user, i);
    }

    public VideoCollectInfoBean(Parcel parcel) {
        this.f1058id = parcel.readInt();
        this.title = parcel.readString();
        this.image_url = parcel.readString();
        this.video_count = parcel.readInt();
        this.like_count = parcel.readInt();
        this.desp = parcel.readString();
        this.is_like = parcel.readInt();
        this.is_top = parcel.readInt();
        this.user = parcel.readParcelable(UserBean.class.getClassLoader());
    }
}

