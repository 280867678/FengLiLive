package com.slzhibo.library.utils;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;

/* loaded from: classes3.dex */
public class HandlerUtils {
    private Handler ioHandler;
    private HandlerThread ioHandlerThread;

    private HandlerUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class SingletonHolder {
        private static final HandlerUtils INSTANCE = new HandlerUtils();
    }

    public static HandlerUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Handler startIOThread(String str, Handler.Callback callback) {
        HandlerThread handlerThread = this.ioHandlerThread;
        if (handlerThread != null && handlerThread.isAlive()) {
            return null;
        }
        this.ioHandlerThread = new HandlerThread(str);
        this.ioHandlerThread.start();
        this.ioHandler = new Handler(this.ioHandlerThread.getLooper(), callback);
        return this.ioHandler;
    }

    public void stopIOThread() {
        Handler handler = this.ioHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.ioHandler = null;
        }
        HandlerThread handlerThread = this.ioHandlerThread;
        if (handlerThread != null) {
            try {
                try {
                    if (Build.VERSION.SDK_INT >= 18) {
                        handlerThread.quitSafely();
                    } else {
                        handlerThread.quit();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                this.ioHandlerThread = null;
            }
        }
    }
}
