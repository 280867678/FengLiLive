package com.slzhibo.library.utils.rxjava2adapter;



import retrofit2.Response;

@Deprecated
/* loaded from: classes12.dex */
public final class HttpException extends retrofit2.HttpException {
    public HttpException(Response<?> response) {
        super(response);
    }
}
