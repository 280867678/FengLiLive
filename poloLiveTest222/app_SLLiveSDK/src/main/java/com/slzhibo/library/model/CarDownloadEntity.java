package com.slzhibo.library.model;

import com.blankj.utilcode.util.FileUtils;
import com.slzhibo.library.utils.GlideUtils;

import java.io.Serializable;

/* loaded from: classes8.dex */
public class CarDownloadEntity implements Serializable {
    public String animLocalPath;
    public String animalUrl;
    public String id;
    public String imgUrl;
    public String name;
    public String versionCode = "0";

    public String getCarFileName() {
        return FileUtils.getFileNameNoExtension(this.animalUrl);
    }

    public String getAnimalUrl() {
        return GlideUtils.formatDownUrl(this.animalUrl);
    }

    public String toString() {
        return "CarDownloadEntity{id='" + this.id + "', name='" + this.name + "', animLocalPath='" + this.animLocalPath + "'}";
    }
}
