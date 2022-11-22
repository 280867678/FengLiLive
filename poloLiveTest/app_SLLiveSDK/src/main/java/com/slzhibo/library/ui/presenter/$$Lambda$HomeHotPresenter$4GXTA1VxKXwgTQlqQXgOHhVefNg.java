package com.slzhibo.library.ui.presenter;

import com.slzhibo.library.http.bean.HttpResultPageModel;
import com.slzhibo.library.utils.AppUtils;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/* renamed from: com.slzhibo.library.ui.presenter.-$$Lambda$HomeHotPresenter$4GXTA1VxKXwgTQlqQXgOHhVefNg  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$HomeHotPresenter$4GXTA1VxKXwgTQlqQXgOHhVefNg implements Function {
    public static final /* synthetic */ $$Lambda$HomeHotPresenter$4GXTA1VxKXwgTQlqQXgOHhVefNg INSTANCE = new $$Lambda$HomeHotPresenter$4GXTA1VxKXwgTQlqQXgOHhVefNg();

    private /* synthetic */ $$Lambda$HomeHotPresenter$4GXTA1VxKXwgTQlqQXgOHhVefNg() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        return Observable.just(AppUtils.formatHttpResultPageModel((HttpResultPageModel) obj));
    }
}
