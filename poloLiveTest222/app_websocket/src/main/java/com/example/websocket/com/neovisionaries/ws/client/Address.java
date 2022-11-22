package com.example.websocket.com.neovisionaries.ws.client;

import java.net.InetSocketAddress;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class Address {
    private final String mHost;
    private final int mPort;
    private transient String mString;

    public Address(String str, int i) {
        this.mHost = str;
        this.mPort = i;
    }

    public InetSocketAddress toInetSocketAddress() {
        return new InetSocketAddress(this.mHost, this.mPort);
    }

    public String getHostname() {
        return this.mHost;
    }

    public String toString() {
        if (this.mString == null) {
            this.mString = String.format("%s:%d", this.mHost, Integer.valueOf(this.mPort));
        }
        return this.mString;
    }
}
