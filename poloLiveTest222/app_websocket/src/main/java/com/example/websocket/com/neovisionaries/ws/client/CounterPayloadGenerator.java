package com.example.websocket.com.neovisionaries.ws.client;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class CounterPayloadGenerator implements PayloadGenerator {
    private long mCount;

    @Override // com.example.websocket.com.neovisionaries.ws.client.PayloadGenerator
    public byte[] generate() {
        return Misc.getBytesUTF8(String.valueOf(increment()));
    }

    private long increment() {
        this.mCount = Math.max(this.mCount + 1, 1L);
        return this.mCount;
    }
}
