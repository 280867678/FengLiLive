package com.slzhibo.library.utils.rxjava2adapter;



//import io.reactivex.annotations.Nullable;
import androidx.annotation.Nullable;

import retrofit2.Response;

/* loaded from: classes12.dex */
public final class Result<T> {
    @Nullable
    private final Throwable error;
    @Nullable
    private final Response<T> response;

    public static <T> Result<T> error(Throwable th) {
        if (th != null) {
            return new Result<>(null, th);
        }
        throw new NullPointerException("error == null");
    }

    public static <T> Result<T> response(Response<T> response) {
        if (response != null) {
            return new Result<>(response, null);
        }
        throw new NullPointerException("response == null");
    }

    private Result(@Nullable Response<T> response, @Nullable Throwable th) {
        this.response = response;
        this.error = th;
    }

    @Nullable
    public Response<T> response() {
        return this.response;
    }

    @Nullable
    public Throwable error() {
        return this.error;
    }

    public boolean isError() {
        return this.error != null;
    }
}
