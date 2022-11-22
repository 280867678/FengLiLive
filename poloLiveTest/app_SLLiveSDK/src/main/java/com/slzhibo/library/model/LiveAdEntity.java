package com.slzhibo.library.model;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes8.dex */
public class LiveAdEntity implements Serializable {
    public AdEntity adv;
    public LiveEntity live;
    public int type;

    public LiveEntity getNewStarLiveEntity() {
        LiveEntity liveEntity;
        if (this.type == 2 && this.adv != null) {
            LiveEntity liveEntity2 = new LiveEntity();
            liveEntity2.isAd = true;
            AdEntity adEntity = this.adv;
            liveEntity2.id = adEntity.id;
            liveEntity2.topic = adEntity.profiles;
            liveEntity2.nickname = adEntity.name;
            liveEntity2.liveCoverUrl = adEntity.img;
            liveEntity2.tag = adEntity.url;
            liveEntity2.liveType = adEntity.method;
            liveEntity2.role = adEntity.forwardScope;
            return liveEntity2;
        } else if (this.type != 8 || (liveEntity = this.live) == null) {
            return this.live;
        } else {
            List<LiveListItemEntity> list = liveEntity.liveCarousel;
            if (list == null || list.isEmpty()) {
                return this.live;
            }
            if (list.size() >= 2) {
                this.live.itemType = 5;
            }
            return this.live;
        }
    }
}
