package com.slzhibo.library.http;

import com.slzhibo.library.http.interceptor.BaseUrlManagerInterceptor;
import com.slzhibo.library.http.interceptor.SignRequestInterceptor;
import com.slzhibo.library.http.interceptor.StatisticsHeaderInterceptor;
import com.slzhibo.library.http.utils.HttpsUtils;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.gsonconverter.GsonConverterFactory;
import com.slzhibo.library.utils.rxjava2adapter.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* loaded from: classes6.dex */
public class StatisticsApiRetrofit {
    private ApiService mApiService;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$new$0(String str, SSLSession sSLSession) {
        return true;
    }

    private StatisticsApiRetrofit() {
        try {
            HttpsUtils.SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory(null, null, null);
            this.mApiService = (ApiService) new Retrofit.Builder().baseUrl(AppUtils.getApiURl()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new BaseUrlManagerInterceptor()).addInterceptor(new StatisticsHeaderInterceptor()).addInterceptor(new SignRequestInterceptor()).connectTimeout(10L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager).hostnameVerifier($$Lambda$StatisticsApiRetrofit$6xUMh0QTYm6XTv874NZ4hx1jDfg.INSTANCE).build()).build().create(ApiService.class);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class SingletonHolder {
        private static final StatisticsApiRetrofit INSTANCE = new StatisticsApiRetrofit();

        private SingletonHolder() {
        }
    }

    public static StatisticsApiRetrofit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ApiService getApiService() {
        return this.mApiService;
    }

    public boolean isApiService() {
        return this.mApiService != null;
    }
}
