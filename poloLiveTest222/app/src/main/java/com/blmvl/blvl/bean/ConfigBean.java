package com.blmvl.blvl.bean;

//package com.blmvl.blvl.bean;

import com.alibaba.fastjson.annotation.JSONField;
//import com.slzhibo.library.utils.litepal.parser.LitePalParser;
import java.util.List;

/* loaded from: classes.dex */
public class ConfigBean {
    public String activityImg;
    public int activityType;
    public String activityUrl;
    public GameFloat game_float;
    public String imgUploadUrl;
    public int maintainSwitch;
    public String maintainTips;
    public String openScreenAdImg;
    public int openScreenAdType;
    public String openScreenAdUrl;
    public List<PaySortBean> paySort;
    public PlayerConfig player_cfg;
    public String spareUrls;

    /* renamed from: tg */
    public String f1037tg;
    public String uploadKey;
    public VersionBean version;
    public String videoUploadUrl;

    @JSONField(name = "activity_thumb")
    public String getActivityImg() {
        return this.activityImg;
    }

    @JSONField(name = "activity_type")
    public int getActivityType() {
        return this.activityType;
    }

    @JSONField(name = "activity_url")
    public String getActivityUrl() {
        return this.activityUrl;
    }

    public GameFloat getGame_float() {
        return this.game_float;
    }

    public String getImgUploadUrl() {
        return this.imgUploadUrl;
    }

    @JSONField(name = "maintain_switch")
    public int getMaintainSwitch() {
        return this.maintainSwitch;
    }

    @JSONField(name = "maintain_tips")
    public String getMaintainTips() {
        return this.maintainTips;
    }

    @JSONField(name = "index_ads_thumb")
    public String getOpenScreenAdImg() {
        return this.openScreenAdImg;
    }

    @JSONField(name = "index_ads_type")
    public int getOpenScreenAdType() {
        return this.openScreenAdType;
    }

    @JSONField(name = "index_ads_url")
    public String getOpenScreenAdUrl() {
        return this.openScreenAdUrl;
    }

    @JSONField(name = "pay_sort")
    public List<PaySortBean> getPaySort() {
        return this.paySort;
    }

    @JSONField(name = "player_cfg")
    public PlayerConfig getPlayer_cfg() {
        return this.player_cfg;
    }

    @JSONField(name = "domain_name")
    public String getSpareUrls() {
        return this.spareUrls;
    }

    public String getTg() {
        return this.f1037tg;
    }

    public String getUploadKey() {
        return this.uploadKey;
    }

    @JSONField(name = "version")
    public VersionBean getVersion() {
        return this.version;
    }

    public String getVideoUploadUrl() {
        return this.videoUploadUrl;
    }

    @JSONField(name = "activity_thumb")
    public void setActivityImg(String str) {
        this.activityImg = str;
    }

    @JSONField(name = "activity_type")
    public void setActivityType(int i) {
        this.activityType = i;
    }

    @JSONField(name = "activity_url")
    public void setActivityUrl(String str) {
        this.activityUrl = str;
    }

    public void setGame_float(GameFloat gameFloat) {
        this.game_float = gameFloat;
    }

    public void setImgUploadUrl(String str) {
        this.imgUploadUrl = str;
    }

    @JSONField(name = "maintain_switch")
    public void setMaintainSwitch(int i) {
        this.maintainSwitch = i;
    }

    @JSONField(name = "maintain_tips")
    public void setMaintainTips(String str) {
        this.maintainTips = str;
    }

    @JSONField(name = "index_ads_thumb")
    public void setOpenScreenAdImg(String str) {
        this.openScreenAdImg = str;
    }

    @JSONField(name = "index_ads_type")
    public void setOpenScreenAdType(int i) {
        this.openScreenAdType = i;
    }

    @JSONField(name = "index_ads_url")
    public void setOpenScreenAdUrl(String str) {
        this.openScreenAdUrl = str;
    }

    @JSONField(name = "pay_sort")
    public void setPaySort(List<PaySortBean> list) {
        this.paySort = list;
    }

    @JSONField(name = "player_cfg")
    public void setPlayer_cfg(PlayerConfig playerConfig) {
        this.player_cfg = playerConfig;
    }

    @JSONField(name = "domain_name")
    public void setSpareUrls(String str) {
        this.spareUrls = str;
    }

    public void setTg(String str) {
        this.f1037tg = str;
    }

    public void setUploadKey(String str) {
        this.uploadKey = str;
    }

    @JSONField(name = "version")
    public void setVersion(VersionBean versionBean) {
        this.version = versionBean;
    }

    public void setVideoUploadUrl(String str) {
        this.videoUploadUrl = str;
    }
}

