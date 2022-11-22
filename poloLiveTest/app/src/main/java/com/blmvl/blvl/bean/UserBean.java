package com.blmvl.blvl.bean;

//package com.blmvl.blvl.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.comod.baselib.list.BaseListViewAdapter;

/* loaded from: classes.dex */
public class UserBean extends BaseListViewAdapter.C0890c implements Parcelable {
    public static final Parcelable.Creator<UserBean> CREATOR = new Parcelable.Creator<UserBean>() { // from class: com.blmvl.blvl.bean.UserBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserBean createFromParcel(Parcel parcel) {
            return new UserBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserBean[] newArray(int i) {
            return new UserBean[i];
        }
    };
    public int aff;
    public String aff_code;
    public int auth_level;
    public int auth_status;
    public String avatar_url;
    public String birthday;
    public String build_id;
    public int can_watch;
    public int coins;
    public int coins_total;
    public int consumption;
    public long expired_at;
    public String expired_str;
    public int fabulous_count;
    public int fans_count;
    public int followed_count;
    public String invite_by_code;
    public int invited_by;
    public int invited_num;
    public int is_attention;
    public int is_reg;
    public int is_vip;
    public int level;
    public int level_anchor;
    public int likes_count;
    public int live_count;
    public int live_supper;
    public int message_tip;
    public String nickname;
    public String person_signnatrue;
    public String phone;
    public int role_id;
    public int score;
    public int score_total;
    public int sexType;
    public String share_text;
    public String share_url;
    public String thumb;
    public int today_mv_score;
    public int today_tui_coins;
    public String token;
    public String total_tui_coins;
    public String tui_coins;
    public int uid;
    public String username;
    public int videos_count;
    public int vip_level;
    public String votes;
    public String votes_total;
    public int watch_count;

    public UserBean() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getAff() {
        return this.aff;
    }

    public String getAff_code() {
        return this.aff_code;
    }

    public int getAuth_level() {
        return this.auth_level;
    }

    public int getAuth_status() {
        return this.auth_status;
    }

    public String getAvatar_url() {
        return this.avatar_url;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public String getBuild_id() {
        return this.build_id;
    }

    public int getCan_watch() {
        return this.can_watch;
    }

    public int getCoins() {
        return this.coins;
    }

    public int getCoins_total() {
        return this.coins_total;
    }

    public int getConsumption() {
        return this.consumption;
    }

    public long getExpired_at() {
        return this.expired_at;
    }

    public String getExpired_str() {
        return this.expired_str;
    }

    public int getFabulous_count() {
        return this.fabulous_count;
    }

    public int getFans_count() {
        return this.fans_count;
    }

    public int getFollowed_count() {
        return this.followed_count;
    }

    public String getInvite_by_code() {
        return this.invite_by_code;
    }

    public int getInvited_by() {
        return this.invited_by;
    }

    public int getInvited_num() {
        return this.invited_num;
    }

    public int getIs_attention() {
        return this.is_attention;
    }

    public int getIs_reg() {
        return this.is_reg;
    }

    public int getIs_vip() {
        return this.is_vip;
    }

    public int getLevel() {
        return this.level;
    }

    public int getLevel_anchor() {
        return this.level_anchor;
    }

    public int getLikes_count() {
        return this.likes_count;
    }

    public int getLive_count() {
        return this.live_count;
    }

    public int getLive_supper() {
        return this.live_supper;
    }

    public int getMessage_tip() {
        return this.message_tip;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getPerson_signnatrue() {
        return this.person_signnatrue;
    }

    public String getPhone() {
        return this.phone;
    }

    public int getRole_id() {
        return this.role_id;
    }

    public int getScore() {
        return this.score;
    }

    public int getScore_total() {
        return this.score_total;
    }

    public int getSexType() {
        return this.sexType;
    }

    public String getShare_text() {
        return this.share_text;
    }

    public String getShare_url() {
        return this.share_url;
    }

    public String getThumb() {
        return this.thumb;
    }

    public int getToday_mv_score() {
        return this.today_mv_score;
    }

    public int getToday_tui_coins() {
        return this.today_tui_coins;
    }

    public String getToken() {
        return this.token;
    }

    public String getTotal_tui_coins() {
        return this.total_tui_coins;
    }

    public String getTui_coins() {
        return this.tui_coins;
    }

    public int getUid() {
        return this.uid;
    }

    public String getUsername() {
        return this.username;
    }

    public int getVideos_count() {
        return this.videos_count;
    }

    public int getVip_level() {
        return this.vip_level;
    }

    public String getVotes() {
        return this.votes;
    }

    public String getVotes_total() {
        return this.votes_total;
    }

    public int getWatch_count() {
        return this.watch_count;
    }

    public boolean isRealVip() {
        return getIs_vip() == 1 && getVip_level() > 0;
    }

    public void setAff(int i) {
        this.aff = i;
    }

    public void setAff_code(String str) {
        this.aff_code = str;
    }

    public void setAuth_level(int i) {
        this.auth_level = i;
    }

    public void setAuth_status(int i) {
        this.auth_status = i;
    }

    public void setAvatar_url(String str) {
        this.avatar_url = str;
    }

    public void setBirthday(String str) {
        this.birthday = str;
    }

    public void setBuild_id(String str) {
        this.build_id = str;
    }

    public void setCan_watch(int i) {
        this.can_watch = i;
    }

    public void setCoins(int i) {
        this.coins = i;
    }

    public void setCoins_total(int i) {
        this.coins_total = i;
    }

    public void setConsumption(int i) {
        this.consumption = i;
    }

    public void setExpired_at(long j) {
        this.expired_at = j;
    }

    public void setExpired_str(String str) {
        this.expired_str = str;
    }

    public void setFabulous_count(int i) {
        this.fabulous_count = i;
    }

    public void setFans_count(int i) {
        this.fans_count = i;
    }

    public void setFollowed_count(int i) {
        this.followed_count = i;
    }

    public void setInvite_by_code(String str) {
        this.invite_by_code = str;
    }

    public void setInvited_by(int i) {
        this.invited_by = i;
    }

    public void setInvited_num(int i) {
        this.invited_num = i;
    }

    public void setIs_attention(int i) {
        this.is_attention = i;
    }

    public void setIs_reg(int i) {
        this.is_reg = i;
    }

    public void setIs_vip(int i) {
        this.is_vip = i;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public void setLevel_anchor(int i) {
        this.level_anchor = i;
    }

    public void setLikes_count(int i) {
        this.likes_count = i;
    }

    public void setLive_count(int i) {
        this.live_count = i;
    }

    public void setLive_supper(int i) {
        this.live_supper = i;
    }

    public void setMessage_tip(int i) {
        this.message_tip = i;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public void setPerson_signnatrue(String str) {
        this.person_signnatrue = str;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public void setRole_id(int i) {
        this.role_id = i;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public void setScore_total(int i) {
        this.score_total = i;
    }

    public void setSexType(int i) {
        this.sexType = i;
    }

    public void setShare_text(String str) {
        this.share_text = str;
    }

    public void setShare_url(String str) {
        this.share_url = str;
    }

    public void setThumb(String str) {
        this.thumb = str;
    }

    public void setToday_mv_score(int i) {
        this.today_mv_score = i;
    }

    public void setToday_tui_coins(int i) {
        this.today_tui_coins = i;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setTotal_tui_coins(String str) {
        this.total_tui_coins = str;
    }

    public void setTui_coins(String str) {
        this.tui_coins = str;
    }

    public void setUid(int i) {
        this.uid = i;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public void setVideos_count(int i) {
        this.videos_count = i;
    }

    public void setVip_level(int i) {
        this.vip_level = i;
    }

    public void setVotes(String i) {
        this.votes = i;
    }

    public void setVotes_total(String i) {
        this.votes_total = i;
    }

    public void setWatch_count(int i) {
        this.watch_count = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.today_tui_coins);
        parcel.writeInt(this.is_attention);
        parcel.writeInt(this.live_count);
        parcel.writeInt(this.live_supper);
        parcel.writeString(this.aff_code);
        parcel.writeInt(this.score);
        parcel.writeInt(this.role_id);
        parcel.writeString(this.tui_coins);
        parcel.writeString(this.expired_str);
        parcel.writeInt(this.message_tip);
        parcel.writeInt(this.watch_count);
        parcel.writeInt(this.coins);
        parcel.writeInt(this.level);
        parcel.writeInt(this.is_vip);
        parcel.writeInt(this.today_mv_score);
        parcel.writeInt(this.can_watch);
        parcel.writeInt(this.fabulous_count);
        parcel.writeString(this.build_id);
        parcel.writeString(this.phone);
        parcel.writeString(this.share_url);
        parcel.writeInt(this.vip_level);
        parcel.writeString(this.birthday);
        parcel.writeString(this.thumb);
        parcel.writeInt(this.sexType);
        parcel.writeInt(this.score_total);
        parcel.writeLong(this.expired_at);
        parcel.writeString(this.total_tui_coins);
        parcel.writeInt(this.uid);
        parcel.writeInt(this.is_reg);
        parcel.writeString(this.nickname);
        parcel.writeInt(this.videos_count);
        parcel.writeInt(this.invited_num);
        parcel.writeInt(this.aff);
        parcel.writeString(this.share_text);
        parcel.writeInt(this.level_anchor);
        parcel.writeInt(this.consumption);
        parcel.writeInt(this.invited_by);
        parcel.writeInt(this.followed_count);
        parcel.writeString(this.token);
        parcel.writeInt(this.likes_count);
        parcel.writeInt(this.fans_count);
        parcel.writeString(this.avatar_url);
        parcel.writeString(this.votes_total);
        parcel.writeString(this.person_signnatrue);
        parcel.writeString(this.votes);
        parcel.writeInt(this.coins_total);
        parcel.writeString(this.username);
        parcel.writeString(this.invite_by_code);
        parcel.writeInt(this.auth_status);
        parcel.writeInt(this.auth_level);
    }

    public UserBean(Parcel parcel) {
        this.today_tui_coins = parcel.readInt();
        this.is_attention = parcel.readInt();
        this.live_count = parcel.readInt();
        this.live_supper = parcel.readInt();
        this.aff_code = parcel.readString();
        this.score = parcel.readInt();
        this.role_id = parcel.readInt();
        this.tui_coins = parcel.readString();
        this.expired_str = parcel.readString();
        this.message_tip = parcel.readInt();
        this.watch_count = parcel.readInt();
        this.coins = parcel.readInt();
        this.level = parcel.readInt();
        this.is_vip = parcel.readInt();
        this.today_mv_score = parcel.readInt();
        this.can_watch = parcel.readInt();
        this.fabulous_count = parcel.readInt();
        this.build_id = parcel.readString();
        this.phone = parcel.readString();
        this.share_url = parcel.readString();
        this.vip_level = parcel.readInt();
        this.birthday = parcel.readString();
        this.thumb = parcel.readString();
        this.sexType = parcel.readInt();
        this.score_total = parcel.readInt();
        this.expired_at = parcel.readLong();
        this.total_tui_coins = parcel.readString();
        this.uid = parcel.readInt();
        this.is_reg = parcel.readInt();
        this.nickname = parcel.readString();
        this.videos_count = parcel.readInt();
        this.invited_num = parcel.readInt();
        this.aff = parcel.readInt();
        this.share_text = parcel.readString();
        this.level_anchor = parcel.readInt();
        this.consumption = parcel.readInt();
        this.invited_by = parcel.readInt();
        this.followed_count = parcel.readInt();
        this.token = parcel.readString();
        this.likes_count = parcel.readInt();
        this.fans_count = parcel.readInt();
        this.avatar_url = parcel.readString();
        this.votes_total = parcel.readString();
        this.person_signnatrue = parcel.readString();
        this.votes = parcel.readString();
        this.coins_total = parcel.readInt();
        this.username = parcel.readString();
        this.invite_by_code = parcel.readString();
        this.auth_status = parcel.readInt();
        this.auth_level = parcel.readInt();
    }
}

