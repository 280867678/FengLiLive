package com.slzhibo.library.analytics;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public interface ScreenAutoTracker {
    String getScreenUrl();

    JSONObject getTrackProperties() throws JSONException;
}
