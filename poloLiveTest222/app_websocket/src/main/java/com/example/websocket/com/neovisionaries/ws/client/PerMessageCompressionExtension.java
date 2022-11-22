package com.example.websocket.com.neovisionaries.ws.client;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class PerMessageCompressionExtension extends WebSocketExtension {
    public abstract byte[] compress(byte[] bArr) throws WebSocketException;

    public abstract byte[] decompress(byte[] bArr) throws WebSocketException;

    public PerMessageCompressionExtension(String str) {
        super(str);
    }

    public PerMessageCompressionExtension(WebSocketExtension webSocketExtension) {
        super(webSocketExtension);
    }
}
