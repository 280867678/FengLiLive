package com.slzhibo.library.ui.presenter;

import com.slzhibo.library.http.bean.HttpResultPageModel;
import com.slzhibo.library.utils.AppUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
/* compiled from: lambda */
/* renamed from: e.t.a.h.e.f */
/* loaded from: classes2.dex */
public final /* synthetic */ class C5352f implements Function {

    /* renamed from: b */
    public static final /* synthetic */ C5352f f18267b = new C5352f();

    private /* synthetic */ C5352f() {
    }

    @Override // p481f.p482a.p483a0.Function
    public final Object apply(Object obj) {
        ObservableSource a;
        a = Observable.just(AppUtils.formatHttpResultPageModel((HttpResultPageModel) obj));
        return a;
    }
}
