package com.slzhibo.library.analytics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes6.dex */
public class TrackTaskManagerThread implements Runnable {
    private static final int POOL_SIZE = 1;
    private static final int SLEEP_TIME = 300;
    private boolean isStop = false;
    private ExecutorService mPool;
    private TrackTaskManager mTrackTaskManager;

    public TrackTaskManagerThread() {
        try {
            this.mTrackTaskManager = TrackTaskManager.getInstance();
            this.mPool = Executors.newFixedThreadPool(1);
        } catch (Exception unused) {
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (!this.isStop) {
            try {
                Runnable trackEventTask = this.mTrackTaskManager.getTrackEventTask();
                if (trackEventTask != null) {
                    this.mPool.execute(trackEventTask);
                } else {
                    try {
                        Thread.sleep(300L);
                    } catch (InterruptedException unused) {
                    }
                }
            } catch (Exception unused2) {
                return;
            }
        }
        if (this.isStop) {
            Runnable trackEventTask2 = this.mTrackTaskManager.getTrackEventTask();
            while (trackEventTask2 != null) {
                this.mPool.execute(trackEventTask2);
                trackEventTask2 = this.mTrackTaskManager.getTrackEventTask();
            }
            this.mPool.shutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStop(boolean z) {
        this.isStop = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isStopped() {
        return this.isStop;
    }
}
