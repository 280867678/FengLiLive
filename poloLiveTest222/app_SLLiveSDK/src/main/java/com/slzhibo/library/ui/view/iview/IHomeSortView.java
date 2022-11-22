package com.slzhibo.library.ui.view.iview;

import com.slzhibo.library.base.BaseView;
import com.slzhibo.library.model.LiveEntity;

import java.util.List;

public interface IHomeSortView extends BaseView {
    void onDataListFail(boolean z);

    void onDataListSuccess(List<LiveEntity> list, boolean z, boolean z2);
}
