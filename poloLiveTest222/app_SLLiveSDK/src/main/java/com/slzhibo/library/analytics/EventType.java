package com.slzhibo.library.analytics;

/* loaded from: classes6.dex */
public enum EventType {
    TRACK("track", true, false);
    
    private String eventType;
    private boolean profile;
    private boolean track;

    EventType(String str, boolean z, boolean z2) {
        this.eventType = str;
        this.track = z;
        this.profile = z2;
    }

    public String getEventType() {
        return this.eventType;
    }

    public boolean isTrack() {
        return this.track;
    }

    public boolean isProfile() {
        return this.profile;
    }
}
