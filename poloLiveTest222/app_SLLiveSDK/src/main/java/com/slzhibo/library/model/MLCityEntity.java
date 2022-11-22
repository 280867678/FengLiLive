package com.slzhibo.library.model;



import com.slzhibo.library.ui.view.widget.indexablerv.IndexableEntity;

/* loaded from: classes8.dex */
public class MLCityEntity implements IndexableEntity {
    private String id;
    private String name;
    private String pinyin;
    private String short_name;

    public MLCityEntity() {
    }

    public MLCityEntity(String str) {
        this.name = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return getShort_name();
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String str) {
        this.pinyin = str;
    }

    @Override // com.slzhibo.library.ui.view.widget.indexablerv.IndexableEntity
    public String getFieldIndexBy() {
        return this.name;
    }

    @Override // com.slzhibo.library.ui.view.widget.indexablerv.IndexableEntity
    public void setFieldIndexBy(String str) {
        this.name = str;
    }

    @Override // com.slzhibo.library.ui.view.widget.indexablerv.IndexableEntity
    public void setFieldPinyinIndexBy(String str) {
        this.pinyin = str;
    }

    public String getShort_name() {
        return this.short_name;
    }

    public void setShort_name(String str) {
        this.short_name = str;
    }
}

