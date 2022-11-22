package com.example.websocket.com.neovisionaries.ws.client;

import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
public class Connectable implements Callable<WebSocket> {
    private final WebSocket mWebSocket;

    public Connectable(WebSocket webSocket) {
        this.mWebSocket = webSocket;
    }

    @Override // java.util.concurrent.Callable
    public WebSocket call() throws WebSocketException {
        return this.mWebSocket.connect();
    }
}
