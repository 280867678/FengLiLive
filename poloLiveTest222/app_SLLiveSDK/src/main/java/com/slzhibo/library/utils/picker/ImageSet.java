package com.slzhibo.library.utils.picker;

import com.slzhibo.library.model.HJProductContentEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ImageSet implements Serializable {
    public static final String ID_ALL_MEDIA = "-1";
    public static final String ID_ALL_VIDEO = "-2";
    public int count;
    public HJProductContentEntity cover;
    public String coverPath;
    public String id;
    public ArrayList<HJProductContentEntity> imageItems;
    public boolean isSelected = false;
    public String name;

    public boolean equals(Object obj) {
        String str;
        ImageSet imageSet = (ImageSet) obj;
        if (this == obj) {
            return true;
        }
        String str2 = this.id;
        if (str2 == null || imageSet == null || (str = imageSet.id) == null) {
            return super.equals(obj);
        }
        return str2.equals(str);
    }

    public ImageSet copy() {
        ImageSet imageSet = new ImageSet();
        imageSet.name = this.name;
        imageSet.coverPath = this.coverPath;
        imageSet.cover = this.cover;
        imageSet.isSelected = this.isSelected;
        imageSet.imageItems = new ArrayList<>();
        imageSet.imageItems.addAll(this.imageItems);
        return imageSet;
    }

    public ImageSet copy(boolean z) {
        ImageSet imageSet = new ImageSet();
        imageSet.name = this.name;
        imageSet.coverPath = this.coverPath;
        imageSet.cover = this.cover;
        imageSet.isSelected = this.isSelected;
        imageSet.imageItems = new ArrayList<>();
        ArrayList<HJProductContentEntity> arrayList = this.imageItems;
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<HJProductContentEntity> it2 = this.imageItems.iterator();
            while (it2.hasNext()) {
                HJProductContentEntity next = it2.next();
                if (!z || !next.isVideo()) {
                    imageSet.imageItems.add(next.copy());
                }
            }
        }
        return imageSet;
    }

    public static ImageSet allImageSet(String str) {
        ImageSet imageSet = new ImageSet();
        imageSet.id = ID_ALL_MEDIA;
        imageSet.name = str;
        return imageSet;
    }

    public boolean isAllMedia() {
        String str = this.id;
        return str == null || str.equals(ID_ALL_MEDIA);
    }

    public boolean isAllVideo() {
        String str = this.id;
        return str != null && str.equals("-2");
    }
}
