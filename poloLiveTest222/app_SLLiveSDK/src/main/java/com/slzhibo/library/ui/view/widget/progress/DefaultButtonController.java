package com.slzhibo.library.ui.view.widget.progress;

import android.graphics.Color;

public class DefaultButtonController implements ButtonController {
    private boolean enableGradient;
    private boolean enablePress;

    @Override // com.slzhibo.library.ui.view.widget.progress.ButtonController
    public int getPressedColor(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        fArr[2] = fArr[2] - 0.1f;
        return Color.HSVToColor(fArr);
    }

    @Override // com.slzhibo.library.ui.view.widget.progress.ButtonController
    public int getLighterColor(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        fArr[1] = fArr[1] - 0.3f;
        fArr[2] = fArr[2] + 0.3f;
        return Color.HSVToColor(fArr);
    }

    @Override // com.slzhibo.library.ui.view.widget.progress.ButtonController
    public boolean enablePress() {
        return this.enablePress;
    }

    @Override // com.slzhibo.library.ui.view.widget.progress.ButtonController
    public boolean enableGradient() {
        return this.enableGradient;
    }

    public DefaultButtonController setEnablePress(boolean z) {
        this.enablePress = z;
        return this;
    }

    public DefaultButtonController setEnableGradient(boolean z) {
        this.enableGradient = z;
        return this;
    }
}
