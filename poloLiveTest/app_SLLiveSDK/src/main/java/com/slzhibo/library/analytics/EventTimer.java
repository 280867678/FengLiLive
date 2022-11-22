package com.slzhibo.library.analytics;

import android.os.SystemClock;

import com.slzhibo.library.utils.DateUtils;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class EventTimer {
    private long startTime;
    private final TimeUnit timeUnit;
    private boolean isPaused = false;
    private long eventAccumulatedDuration = 0;
    private long endTime = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventTimer(TimeUnit timeUnit, long j) {
        this.startTime = j;
        this.timeUnit = timeUnit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005a A[Catch: Exception -> 0x0076, TryCatch #0 {Exception -> 0x0076, blocks: (B:13:0x002c, B:15:0x0032, B:16:0x0034, B:18:0x003c, B:19:0x003f, B:21:0x0047, B:22:0x0049, B:23:0x004b, B:25:0x0051, B:28:0x005a, B:29:0x005f, B:31:0x0071), top: B:35:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005f A[Catch: Exception -> 0x0076, TryCatch #0 {Exception -> 0x0076, blocks: (B:13:0x002c, B:15:0x0032, B:16:0x0034, B:18:0x003c, B:19:0x003f, B:21:0x0047, B:22:0x0049, B:23:0x004b, B:25:0x0051, B:28:0x005a, B:29:0x005f, B:31:0x0071), top: B:35:0x0022 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String duration() {
        float f;
        float f2 = 0;
        if (this.isPaused) {
            this.endTime = this.startTime;
        } else {
            long j = this.endTime;
            if (j < 0) {
                j = SystemClock.elapsedRealtime();
            }
            this.endTime = j;
        }
        long j2 = (this.endTime - this.startTime) + this.eventAccumulatedDuration;
        try {
            if (j2 < 0 || j2 > DateUtils.ONE_DAY_MILLIONS) {
                return String.valueOf(0);
            }
            if (this.timeUnit != TimeUnit.MILLISECONDS) {
                if (this.timeUnit == TimeUnit.SECONDS) {
                    f = ((float) j2) / 1000.0f;
                } else {
                    if (this.timeUnit == TimeUnit.MINUTES) {
                        f2 = ((float) j2) / 1000.0f;
                    } else if (this.timeUnit == TimeUnit.HOURS) {
                        f2 = (((float) j2) / 1000.0f) / 60.0f;
                    }
                    f = f2 / 60.0f;
                }
                return f >= 0.0f ? String.valueOf(0) : String.format(Locale.CHINA, "%.3f", Float.valueOf(f));
            }
            f = (float) j2;
            if (f >= 0.0f) {
            }
        } catch (Exception unused) {
            return String.valueOf(0);
        }
        return String.valueOf(f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getStartTime() {
        return this.startTime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStartTime(long j) {
        this.startTime = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEndTime(long j) {
        this.endTime = j;
    }

    long getEndTime() {
        return this.endTime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getEventAccumulatedDuration() {
        return this.eventAccumulatedDuration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEventAccumulatedDuration(long j) {
        this.eventAccumulatedDuration = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTimerState(boolean z, long j) {
        this.isPaused = z;
        if (z) {
            this.eventAccumulatedDuration = (this.eventAccumulatedDuration + j) - this.startTime;
        }
        this.startTime = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isPaused() {
        return this.isPaused;
    }
}
