package com.slzhibo.library.ui.view.custom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slzhibo.library.R;
import com.slzhibo.library.ui.view.widget.guideview.Component;

public class GuideRatingComponent implements Component {
    @Override // com.slzhibo.library.ui.view.widget.guideview.Component
    public int getAnchor() {
        return 4;
    }

    @Override // com.slzhibo.library.ui.view.widget.guideview.Component
    public int getFitPosition() {
        return 48;
    }

    @Override // com.slzhibo.library.ui.view.widget.guideview.Component
    public int getXOffset() {
        return -10;
    }

    @Override // com.slzhibo.library.ui.view.widget.guideview.Component
    public int getYOffset() {
        return 20;
    }

    @Override // com.slzhibo.library.ui.view.widget.guideview.Component
    public View getView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fq_view_guide_rate, (ViewGroup) null);
    }
}
