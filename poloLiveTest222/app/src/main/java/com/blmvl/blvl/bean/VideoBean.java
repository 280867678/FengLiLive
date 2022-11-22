package com.blmvl.blvl.bean;

//package com.blmvl.blvl.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.comod.baselib.list.BaseListViewAdapter;
import java.util.List;

/* loaded from: classes.dex */
public class VideoBean extends BaseListViewAdapter.C0890c implements Parcelable {
    public static final Parcelable.Creator<VideoBean> CREATOR = new Parcelable.Creator<VideoBean>() { // from class: com.blmvl.blvl.bean.VideoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VideoBean createFromParcel(Parcel parcel) {
            return new VideoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VideoBean[] newArray(int i) {
            return new VideoBean[i];
        }
    };
    public int coins;
    public int comment;
    public int count_pay;
    public String cover_thumb_url;
    public int created_at;
    public String created_str;
    public String directors;
    public int duration;
    public String duration_str;
    public int gif_height;
    public String gif_thumb;
    public String gif_thumb_url;
    public int gif_width;
    public boolean hasLongVideo;

    /* renamed from: id */
    public int f1057id;
    public boolean isSelected;
    public int is_feature;
    public int is_free;
    public int is_hide;
    public int is_like;
    public int is_pay;
    public int is_recommend;
    public int is_top;
    public int like;
    public int my_ticket_number;
    public String play_url;
    public int rating;
    public int refresh_at;
    public int status;
    public List<String> tags_list;
    public int thumb_height;
    public int thumb_width;
    public String title;
    public int uid;
    public UserBean user;
    public VideoCollectInfoBean user_topic;
    public String v_ext;
    public int vip_coins;
    public String y_cover_url;

    public VideoBean() {
        this.isSelected = false;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getCoins() {
        return this.coins;
    }

    public int getComment() {
        return this.comment;
    }

    public int getCount_pay() {
        return this.count_pay;
    }

    public String getCover_thumb_url() {
        return this.cover_thumb_url;
    }

    public int getCreated_at() {
        return this.created_at;
    }

    public String getCreated_str() {
        return this.created_str;
    }

    public String getDirectors() {
        return this.directors;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getDuration_str() {
        return this.duration_str;
    }

    public int getGif_height() {
        return this.gif_height;
    }

    public String getGif_thumb() {
        return this.gif_thumb;
    }

    public String getGif_thumb_url() {
        return this.gif_thumb_url;
    }

    public int getGif_width() {
        return this.gif_width;
    }

    public int getId() {
        return this.f1057id;
    }

    public int getIs_feature() {
        return this.is_feature;
    }

    public int getIs_free() {
        return this.is_free;
    }

    public int getIs_hide() {
        return this.is_hide;
    }

    public int getIs_like() {
        return this.is_like;
    }

    public int getIs_pay() {
        return this.is_pay;
    }

    public int getIs_recommend() {
        return this.is_recommend;
    }

    public int getIs_top() {
        return this.is_top;
    }

    public int getLike() {
        return this.like;
    }

    public int getMy_ticket_number() {
        return this.my_ticket_number;
    }

    public String getPlay_url() {
        return this.play_url;
    }

    public int getRating() {
        return this.rating;
    }

    public int getRefresh_at() {
        return this.refresh_at;
    }

    public int getStatus() {
        return this.status;
    }

    public List<String> getTags_list() {
        return this.tags_list;
    }

    public int getThumb_height() {
        return this.thumb_height;
    }

    public int getThumb_width() {
        return this.thumb_width;
    }

    public String getTitle() {
        return this.title;
    }

    public int getUid() {
        return this.uid;
    }

    public UserBean getUser() {
        return this.user;
    }

    public VideoCollectInfoBean getUser_topic() {
        return this.user_topic;
    }

    public String getV_ext() {
        return this.v_ext;
    }

    public int getVip_coins() {
        return this.vip_coins;
    }

    public String getY_cover_url() {
        return this.y_cover_url;
    }

    public boolean isHasLongVideo() {
        return this.hasLongVideo;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setCoins(int i) {
        this.coins = i;
    }

    public void setComment(int i) {
        this.comment = i;
    }

    public void setCount_pay(int i) {
        this.count_pay = i;
    }

    public void setCover_thumb_url(String str) {
        this.cover_thumb_url = str;
    }

    public void setCreated_at(int i) {
        this.created_at = i;
    }

    public void setCreated_str(String str) {
        this.created_str = str;
    }

    public void setDirectors(String str) {
        this.directors = str;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setDuration_str(String str) {
        this.duration_str = str;
    }

    public void setGif_height(int i) {
        this.gif_height = i;
    }

    public void setGif_thumb(String str) {
        this.gif_thumb = str;
    }

    public void setGif_thumb_url(String str) {
        this.gif_thumb_url = str;
    }

    public void setGif_width(int i) {
        this.gif_width = i;
    }

    public void setHasLongVideo(boolean z) {
        this.hasLongVideo = z;
    }

    public void setId(int i) {
        this.f1057id = i;
    }

    public void setIs_feature(int i) {
        this.is_feature = i;
    }

    public void setIs_free(int i) {
        this.is_free = i;
    }

    public void setIs_hide(int i) {
        this.is_hide = i;
    }

    public void setIs_like(int i) {
        this.is_like = i;
    }

    public void setIs_pay(int i) {
        this.is_pay = i;
    }

    public void setIs_recommend(int i) {
        this.is_recommend = i;
    }

    public void setIs_top(int i) {
        this.is_top = i;
    }

    public void setLike(int i) {
        this.like = i;
    }

    public void setMy_ticket_number(int i) {
        this.my_ticket_number = i;
    }

    public void setPlay_url(String str) {
        this.play_url = str;
    }

    public void setRating(int i) {
        this.rating = i;
    }

    public void setRefresh_at(int i) {
        this.refresh_at = i;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setTags_list(List<String> list) {
        this.tags_list = list;
    }

    public void setThumb_height(int i) {
        this.thumb_height = i;
    }

    public void setThumb_width(int i) {
        this.thumb_width = i;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUid(int i) {
        this.uid = i;
    }

    public void setUser(UserBean userBean) {
        this.user = userBean;
    }

    public void setUser_topic(VideoCollectInfoBean videoCollectInfoBean) {
        this.user_topic = videoCollectInfoBean;
    }

    public void setV_ext(String str) {
        this.v_ext = str;
    }

    public void setVip_coins(int i) {
        this.vip_coins = i;
    }

    public void setY_cover_url(String str) {
        this.y_cover_url = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.vip_coins);
        parcel.writeInt(this.thumb_width);
        parcel.writeInt(this.refresh_at);
        parcel.writeInt(this.thumb_height);
        parcel.writeString(this.directors);
        parcel.writeInt(this.rating);
        parcel.writeInt(this.created_at);
        parcel.writeString(this.created_str);
        parcel.writeString(this.title);
        parcel.writeInt(this.duration);
        parcel.writeInt(this.uid);
        parcel.writeString(this.gif_thumb);
        parcel.writeInt(this.is_like);
        parcel.writeInt(this.f1057id);
        parcel.writeString(this.cover_thumb_url);
        parcel.writeInt(this.coins);
        parcel.writeInt(this.like);
        parcel.writeInt(this.is_feature);
        parcel.writeInt(this.is_top);
        parcel.writeString(this.duration_str);
        parcel.writeInt(this.count_pay);
        parcel.writeInt(this.gif_width);
        parcel.writeString(this.v_ext);
        parcel.writeInt(this.is_free);
        parcel.writeInt(this.is_recommend);
        parcel.writeString(this.gif_thumb_url);
        parcel.writeInt(this.comment);
        parcel.writeInt(this.gif_height);
        parcel.writeInt(this.is_pay);
        parcel.writeParcelable(this.user, i);
        parcel.writeString(this.y_cover_url);
        parcel.writeStringList(this.tags_list);
        parcel.writeInt(this.status);
        parcel.writeInt(this.is_hide);
        parcel.writeString(this.play_url);
        parcel.writeByte(this.hasLongVideo ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.user_topic, i);
        parcel.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.my_ticket_number);
    }

    public VideoBean(Parcel parcel) {
        boolean z = false;
        this.isSelected = false;
        this.vip_coins = parcel.readInt();
        this.thumb_width = parcel.readInt();
        this.refresh_at = parcel.readInt();
        this.thumb_height = parcel.readInt();
        this.directors = parcel.readString();
        this.rating = parcel.readInt();
        this.created_at = parcel.readInt();
        this.created_str = parcel.readString();
        this.title = parcel.readString();
        this.duration = parcel.readInt();
        this.uid = parcel.readInt();
        this.gif_thumb = parcel.readString();
        this.is_like = parcel.readInt();
        this.f1057id = parcel.readInt();
        this.cover_thumb_url = parcel.readString();
        this.coins = parcel.readInt();
        this.like = parcel.readInt();
        this.is_feature = parcel.readInt();
        this.is_top = parcel.readInt();
        this.duration_str = parcel.readString();
        this.count_pay = parcel.readInt();
        this.gif_width = parcel.readInt();
        this.v_ext = parcel.readString();
        this.is_free = parcel.readInt();
        this.is_recommend = parcel.readInt();
        this.gif_thumb_url = parcel.readString();
        this.comment = parcel.readInt();
        this.gif_height = parcel.readInt();
        this.is_pay = parcel.readInt();
        this.user = parcel.readParcelable(UserBean.class.getClassLoader());
        this.y_cover_url = parcel.readString();
        this.tags_list = parcel.createStringArrayList();
        this.status = parcel.readInt();
        this.is_hide = parcel.readInt();
        this.play_url = parcel.readString();
        this.hasLongVideo = parcel.readByte() != 0;
        this.user_topic = parcel.readParcelable(VideoCollectInfoBean.class.getClassLoader());
        this.isSelected = parcel.readByte() != 0 || z;
        this.my_ticket_number = parcel.readInt();
    }
}

