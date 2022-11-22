package com.ibase.glide.widget;

import android.graphics.Point;

import java.io.Serializable;

//import p067e.p260n.p261a.p264k.LayoutHelper;

/* loaded from: classes.dex */
public class ImageData implements Serializable {
    public int height;
    public int realHeight;
    public int realWidth;
    public int startX;
    public int startY;
    public String text;
    public String url;
    public int width;

    public ImageData(String str) {
        this.url = str;
    }

    public ImageData from(ImageData imageData, LayoutHelper bVar, int i) {
        if (!(imageData == null || bVar == null)) {
            Point b = bVar.mo4922b(i);
            if (b != null) {
                imageData.startX = b.x;
                imageData.startY = b.y;
            }
            Point a = bVar.mo4923a(i);
            if (a != null) {
                imageData.width = a.x;
                imageData.height = a.y;
            }
        }
        return imageData;
    }
}
