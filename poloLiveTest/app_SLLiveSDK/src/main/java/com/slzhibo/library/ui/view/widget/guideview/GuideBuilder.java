package com.slzhibo.library.ui.view.widget.guideview;

import android.graphics.Bitmap;
import android.view.View;

import androidx.annotation.IntRange;

import java.util.ArrayList;
import java.util.List;

public class GuideBuilder {
    private boolean mBuilt;
    private List<Component> mComponents = new ArrayList();
    private Configuration mConfiguration = new Configuration();
    private OnSlideListener mOnSlideListener;
    private OnVisibilityChangedListener mOnVisibilityChangedListener;

    public interface OnSlideListener {
        void onSlideListener(SlideState slideState);
    }

    public interface OnVisibilityChangedListener {
        void onDismiss();

        void onShown();
    }

    public enum SlideState {
        UP,
        DOWN
    }

    public GuideBuilder setAlpha(@IntRange(from = 0, to = 255) int i) {
        if (!this.mBuilt) {
            if (i < 0 || i > 255) {
                i = 0;
            }
            this.mConfiguration.mAlpha = i;
            return this;
        }
        throw new BuildException("Already created. rebuild a new one.");
    }

    public GuideBuilder setTargetView(View view) {
        if (!this.mBuilt) {
            this.mConfiguration.mTargetView = view;
            return this;
        }
        throw new BuildException("Already created. rebuild a new one.");
    }

    public GuideBuilder setHighlightBitmap(Bitmap bitmap) {
        this.mConfiguration.mBitmap = bitmap;
        return this;
    }

    public GuideBuilder setHighTargetGraphStyle(int i) {
        if (!this.mBuilt) {
            this.mConfiguration.mGraphStyle = i;
            return this;
        }
        throw new BuildException("Already created. rebuild a new one.");
    }

    public GuideBuilder addComponent(Component component) {
        if (!this.mBuilt) {
            this.mComponents.add(component);
            return this;
        }
        throw new BuildException("Already created, rebuild a new one.");
    }

    public GuideBuilder setOnVisibilityChangedListener(OnVisibilityChangedListener onVisibilityChangedListener) {
        if (!this.mBuilt) {
            this.mOnVisibilityChangedListener = onVisibilityChangedListener;
            return this;
        }
        throw new BuildException("Already created, rebuild a new one.");
    }

    public Guide createGuide() {
        Guide guide = new Guide();
        guide.setComponents((Component[]) this.mComponents.toArray(new Component[this.mComponents.size()]));
        guide.setConfiguration(this.mConfiguration);
        guide.setCallback(this.mOnVisibilityChangedListener);
        guide.setOnSlideListener(this.mOnSlideListener);
        this.mComponents = null;
        this.mConfiguration = null;
        this.mOnVisibilityChangedListener = null;
        this.mBuilt = true;
        return guide;
    }
}
