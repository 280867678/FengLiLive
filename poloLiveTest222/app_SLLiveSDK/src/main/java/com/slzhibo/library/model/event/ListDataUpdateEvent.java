package com.slzhibo.library.model.event;



/* loaded from: classes6.dex */
public class ListDataUpdateEvent extends BaseEvent {
    public boolean isAutoRefresh;

    public ListDataUpdateEvent() {
        this.isAutoRefresh = false;
    }

    public ListDataUpdateEvent(boolean z) {
        this.isAutoRefresh = false;
        this.isAutoRefresh = z;
    }
}

