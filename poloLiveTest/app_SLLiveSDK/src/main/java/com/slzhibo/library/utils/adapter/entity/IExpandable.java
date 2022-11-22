package com.slzhibo.library.utils.adapter.entity;



import java.util.List;

/* loaded from: classes6.dex */
public interface IExpandable<T> {
    int getLevel();

    List<T> getSubItems();

    boolean isExpanded();

    void setExpanded(boolean z);
}

