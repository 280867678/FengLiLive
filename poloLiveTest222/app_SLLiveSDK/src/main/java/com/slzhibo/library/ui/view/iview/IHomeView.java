package com.slzhibo.library.ui.view.iview;



import com.slzhibo.library.base.BaseView;
import com.slzhibo.library.model.LabelEntity;
import java.util.List;

/* renamed from: com.slzhibo.library.ui.view.iview.IHomeView */
/* loaded from: classes11.dex */
public interface IHomeView extends BaseView {
    void onTagListFail();

    void onTagListSuccess(List<LabelEntity> list);
}
