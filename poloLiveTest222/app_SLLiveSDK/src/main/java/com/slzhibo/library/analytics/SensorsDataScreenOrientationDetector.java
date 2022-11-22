package com.slzhibo.library.analytics;

import android.content.Context;
import android.view.OrientationEventListener;

/* loaded from: classes6.dex */
public class SensorsDataScreenOrientationDetector extends OrientationEventListener {
    private int mCurrentOrientation;

    public SensorsDataScreenOrientationDetector(Context context, int i) {
        super(context, i);
    }

    public String getOrientation() {
        int i = this.mCurrentOrientation;
        if (i == 0 || i == 180) {
            return "portrait";
        }
        if (i == 90 || i == 270) {
            return "landscape";
        }
        return null;
    }

    @Override // android.view.OrientationEventListener
    public void onOrientationChanged(int i) {
        if (i != -1) {
            if (i < 45 || i > 315) {
                this.mCurrentOrientation = 0;
            } else if (i > 45 && i < 135) {
                this.mCurrentOrientation = 90;
            } else if (i > 135 && i < 225) {
                this.mCurrentOrientation = 180;
            } else if (i > 225 && i < 315) {
                this.mCurrentOrientation = 270;
            }
        }
    }
}
