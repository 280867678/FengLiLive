package com.blmvl.blvl.ijkplayer;

import android.view.View;

import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public final class MeasureHelper {
    private int mCurrentAspectRatio = 0;
    private int mMeasuredHeight;
    private int mMeasuredWidth;
    private int mVideoHeight;
    private int mVideoRotationDegree;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private int mVideoWidth;

    public MeasureHelper(View view) {
        new WeakReference(view);
    }

    public void setVideoSize(int i, int i2) {
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
    }

    public void setVideoSampleAspectRatio(int i, int i2) {
        this.mVideoSarNum = i;
        this.mVideoSarDen = i2;
    }

    public void setVideoRotation(int i) {
        this.mVideoRotationDegree = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ae, code lost:
        if (r4 != false) goto L_0x00b7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b1, code lost:
        if (r4 != false) goto L_0x00b3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b3, code lost:
        r12 = (int) (r0 / r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b7, code lost:
        r11 = (int) (r3 * r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00f7, code lost:
        if (r1 > r11) goto L_0x0117;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void doMeasure(int i, int i2) {
        float f;
        int i3;
        int i4 = this.mVideoRotationDegree;
        if (i4 == 90 || i4 == 270) {
            i2 = i;
            i = i2;
        }
        i = View.getDefaultSize(this.mVideoWidth, i);
        i2 = View.getDefaultSize(this.mVideoHeight, i2);
        if (this.mCurrentAspectRatio != 3) {
            if (this.mVideoWidth > 0 && this.mVideoHeight > 0) {
                int mode = View.MeasureSpec.getMode(i);
                i = View.MeasureSpec.getSize(i);
                int mode2 = View.MeasureSpec.getMode(i2);
                i2 = View.MeasureSpec.getSize(i2);
                if (mode == Integer.MIN_VALUE && mode2 == Integer.MIN_VALUE) {
                    float f2 = i;
                    float f3 = i2;
                    float f4 = f2 / f3;
                    int i5 = this.mCurrentAspectRatio;
                    if (i5 == 4) {
                        int i6 = this.mVideoRotationDegree;
                        f = (i6 == 90 || i6 == 270) ? 0.5625f : 1.7777778f;
                    } else if (i5 != 5) {
                        f = this.mVideoWidth / this.mVideoHeight;
                        int i7 = this.mVideoSarNum;
                        if (i7 > 0 && (i3 = this.mVideoSarDen) > 0) {
                            f = (f * i7) / i3;
                        }
                    } else {
                        int i8 = this.mVideoRotationDegree;
                        f = (i8 == 90 || i8 == 270) ? 0.75f : 1.3333334f;
                    }
                    boolean z = f > f4;
                    int i9 = this.mCurrentAspectRatio;
                    if (i9 != 0) {
                        if (i9 != 1) {
                            if (!(i9 == 4 || i9 == 5)) {
                                if (z) {
                                    i = Math.min(this.mVideoWidth, i);
                                    i2 = (int) (i / f);
                                } else {
                                    int min = Math.min(this.mVideoHeight, i2);
                                    i = (int) (min * f);
                                    i2 = min;
                                }
                            }
                        }
                    }
                } else if (mode == 1073741824 && mode2 == 1073741824) {
                    int i10 = this.mVideoWidth;
                    int i11 = i10 * i2;
                    int i12 = this.mVideoHeight;
                    if (i11 < i * i12) {
                        i = (i10 * i2) / i12;
                    } else if (i10 * i2 > i * i12) {
                        i2 = (i12 * i) / i10;
                    }
                } else if (mode == 1073741824) {
                    int i13 = (this.mVideoHeight * i) / this.mVideoWidth;
                    if (mode2 != Integer.MIN_VALUE || i13 <= i2) {
                        i2 = i13;
                    }
                } else if (mode2 == 1073741824) {
                    i = (this.mVideoWidth * i2) / this.mVideoHeight;
                    if (mode == Integer.MIN_VALUE) {
                    }
                } else {
                    i = this.mVideoWidth;
                    int i14 = this.mVideoHeight;
                    if (mode2 != Integer.MIN_VALUE || i14 <= i2) {
                        i2 = i14;
                    } else {
                        i = (i * i2) / i14;
                    }
                    if (mode == Integer.MIN_VALUE && i > i) {
                        i2 = (this.mVideoHeight * i) / this.mVideoWidth;
                    }
                }
            }
        }
        this.mMeasuredWidth = i;
        this.mMeasuredHeight = i2;
    }

    public int getMeasuredWidth() {
        return this.mMeasuredWidth;
    }

    public int getMeasuredHeight() {
        return this.mMeasuredHeight;
    }

    public void setAspectRatio(int i) {
        this.mCurrentAspectRatio = i;
    }
}
