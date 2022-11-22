package com.slzhibo.library.analytics;

import java.util.LinkedList;

/* loaded from: classes6.dex */
public class TrackTaskManager {
    private static TrackTaskManager trackTaskManager;
    private final LinkedList<Runnable> mTrackEventTasks = new LinkedList<>();

    private TrackTaskManager() {
    }

    public static synchronized TrackTaskManager getInstance() {
        TrackTaskManager trackTaskManager2;
        synchronized (TrackTaskManager.class) {
            try {
                if (trackTaskManager == null) {
                    trackTaskManager = new TrackTaskManager();
                }
            } catch (Exception unused) {
            }
            trackTaskManager2 = trackTaskManager;
        }
        return trackTaskManager2;
    }

    public void addTrackEventTask(Runnable runnable) {
        try {
            synchronized (this.mTrackEventTasks) {
                this.mTrackEventTasks.addLast(runnable);
            }
        } catch (Exception unused) {
        }
    }

    public Runnable getTrackEventTask() {
        try {
            synchronized (this.mTrackEventTasks) {
                if (this.mTrackEventTasks.size() <= 0) {
                    return null;
                }
                return this.mTrackEventTasks.removeFirst();
            }
        } catch (Exception unused) {
            return null;
        }
    }
}
