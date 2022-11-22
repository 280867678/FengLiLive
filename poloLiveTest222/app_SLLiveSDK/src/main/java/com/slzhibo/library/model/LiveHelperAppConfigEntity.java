package com.slzhibo.library.model;

import java.util.List;

/* loaded from: classes8.dex */
public class LiveHelperAppConfigEntity {
    public String androidPackageName;
    public List<ChannelConfigEntity> customerChannelConfigs;
    public String startLiveAppDownloadUrl;

    /* loaded from: classes8.dex */
    public static class ChannelConfigEntity {
        public String channelName;
        public String channelType;
        public String channelUrl;
    }
}
