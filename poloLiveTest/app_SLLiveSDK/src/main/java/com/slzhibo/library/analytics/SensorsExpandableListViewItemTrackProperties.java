package com.slzhibo.library.analytics;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public interface SensorsExpandableListViewItemTrackProperties {
    JSONObject getSensorsChildItemTrackProperties(int i, int i2) throws JSONException;

    JSONObject getSensorsGroupItemTrackProperties(int i) throws JSONException;
}
