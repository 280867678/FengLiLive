package com.example.websocket.com.neovisionaries.ws.client;

/* loaded from: classes2.dex */
public class FinishThread extends WebSocketThread {
    public FinishThread(WebSocket webSocket) {
        super("FinishThread", webSocket, ThreadType.FINISH_THREAD);
    }

    @Override // com.example.websocket.com.neovisionaries.ws.client.WebSocketThread
    public void runMain() {
        this.mWebSocket.finish();
    }
}
