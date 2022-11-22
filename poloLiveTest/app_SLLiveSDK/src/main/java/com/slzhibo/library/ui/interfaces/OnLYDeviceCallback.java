package com.slzhibo.library.ui.interfaces;

import com.slzhibo.library.model.LYModelDataEntity;

public interface OnLYDeviceCallback {
    void onClose();

    void onFreeCountdownCompleteCallback();

    void onModelSelectedCallback(int i, LYModelDataEntity.Data data);

    void onShowControlWindowView();

    void onShowModelDataDialog();
}
