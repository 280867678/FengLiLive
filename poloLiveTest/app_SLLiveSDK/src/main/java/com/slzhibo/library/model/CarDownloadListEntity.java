package com.slzhibo.library.model;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes8.dex */
public class CarDownloadListEntity implements Serializable {
    public List<CarDownloadEntity> carList;

    public String toString() {
        return "CarDownloadListEntity{carList=" + this.carList + '}';
    }
}
