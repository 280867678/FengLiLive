package com.slzhibo.library.http;

import android.text.TextUtils;

import com.slzhibo.library.http.interceptor.GzipHeaderInterceptor;
import com.slzhibo.library.http.utils.CustomGsonConverterFactory;
import com.slzhibo.library.http.utils.HttpsUtils;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.rxjava2adapter.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* loaded from: classes6.dex */
public class EventReportRetrofit {
    private ApiService mApiService;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$initApiService$0(String str, SSLSession sSLSession) {
        return true;
    }

    private EventReportRetrofit() {
        this.mApiService = null;
        initApiService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class SingletonHolder {
        private static final EventReportRetrofit INSTANCE = new EventReportRetrofit();

        private SingletonHolder() {
        }
    }

    public static EventReportRetrofit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ApiService getApiService() {
        if (this.mApiService == null) {
            initApiService();
        }
        return this.mApiService;
    }

    public boolean isApiService() {
        return !TextUtils.isEmpty(getBaseUrl()) && this.mApiService != null;
    }

    public String getBaseUrl() {
        return AppUtils.getDataReportUrl();
    }

    private void initApiService() {
        try {
            HttpsUtils.SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory(null, null, null);
            this.mApiService = (ApiService) new Retrofit.Builder().baseUrl(getBaseUrl()).addConverterFactory(CustomGsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new GzipHeaderInterceptor()).connectTimeout(6L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager).hostnameVerifier($$Lambda$EventReportRetrofit$nVCqH62vNWLUIQerIsTvDVVsY.INSTANCE).build()).build().create(ApiService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
