package com.slzhibo.library.ui.view.widget.guideview;

import android.view.LayoutInflater;
import android.view.View;

public interface Component {
    int getAnchor();

    int getFitPosition();

    View getView(LayoutInflater layoutInflater);

    int getXOffset();

    int getYOffset();
}
