package com.slzhibo.library.model;

import com.slzhibo.library.utils.adapter.entity.SectionEntity;

/* loaded from: classes8.dex */
public class HJProductSectionEntity extends SectionEntity<HJProductEntity> {
    public boolean isMorePage;
    public long sectionCreateTime;

    public HJProductSectionEntity(boolean z, String str) {
        super(z, str);
    }

    public HJProductSectionEntity(boolean z, String str, boolean z2, long j) {
        super(z, str);
        this.isMorePage = z2;
        this.sectionCreateTime = j;
    }

    public HJProductSectionEntity(HJProductEntity hJProductEntity) {
        super(hJProductEntity);
    }
}
