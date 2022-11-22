package com.slzhibo.library.http.interceptor;

import androidx.annotation.NonNull;

//import com.eclipsesource.v8.Platform;
//import com.slzhibo.library.utils.LogConstants;
import com.slzhibo.library.utils.UserInfoManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/* loaded from: classes6.dex */
public class StatisticsHeaderInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(@NonNull Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().header("deviceType", "android").header("appId", UserInfoManager.getInstance().getAppId()).build());
    }
}
