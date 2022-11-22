package com.slzhibo.library.ui.view.iview;



import com.slzhibo.library.base.BaseView;
import com.slzhibo.library.model.LiveEntity;
import java.util.List;


/* renamed from: e.t.a.h.f.g.u */
/* loaded from: classes2.dex */
public interface IHomeAllView extends BaseView {
    /* renamed from: a */
//    void mo3027a(List<LiveEntity> list, boolean z, boolean z2, boolean z3);
//
//    /* renamed from: a */
//    void mo3026a(boolean z);

    void onDataListFail(boolean z);

    void onDataListSuccess(List<LiveEntity> list, boolean z, boolean z2, boolean z3);

}

