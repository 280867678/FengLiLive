package com.slzhibo.library.http.function;

import android.util.Log;

import androidx.annotation.NonNull;

import com.slzhibo.library.http.bean.HttpResultModel;
import com.slzhibo.library.http.exception.ServerException;


import io.reactivex.functions.Function;

/* loaded from: classes6.dex */
public class ServerResultFunction<T> implements Function<HttpResultModel<T>, T> {
//    @Override // io.reactivex.functions.Function
//    public /* bridge */ /* synthetic */ Object apply(@NonNull Object obj) throws Exception {
//        return apply((HttpResultModel) ((HttpResultModel) obj));
//    }

    @Override
    public T apply(@NonNull HttpResultModel<T> httpResultModel) {
        if (httpResultModel.isSuccess()) {
//            Log.e("ServerResultFunction::", String.valueOf(httpResultModel.getData()));
            return httpResultModel.getData();
        }
        Log.e("ServerResultFunction::","报错啦！！XXXXXXXXXXServerException(httpResultModel.getCode(), httpResultModel.getMessage()");
        throw new ServerException(httpResultModel.getCode(), httpResultModel.getMessage());
    }
}
