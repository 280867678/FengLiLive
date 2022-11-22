package com.slzhibo.library.model;

/* loaded from: classes8.dex */
public class TestEntity {
    public String code;
    public Holder data;
    public String msg;

    /* loaded from: classes8.dex */
    public static class Holder {
        public String appId;
        public String channelName;
        public String token;
        public String uid;

        public String toString() {
            return "Holder{channelName='" + this.channelName + "', appId='" + this.appId + "', token='" + this.token + "', uid='" + this.uid + "'}";
        }
    }

    public String toString() {
        return "TestEntity{code='" + this.code + "', msg='" + this.msg + "', data=" + this.data + '}';
    }
}
