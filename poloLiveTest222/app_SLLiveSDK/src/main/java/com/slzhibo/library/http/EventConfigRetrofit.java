package com.slzhibo.library.http;

import com.slzhibo.library.http.utils.CustomGsonConverterFactory;
import com.slzhibo.library.http.utils.HttpsUtils;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.rxjava2adapter.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* loaded from: classes6.dex */
public class EventConfigRetrofit {
    private ApiService mApiService;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$initApiService$0(String str, SSLSession sSLSession) {
        return true;
    }

    private EventConfigRetrofit() {
        this.mApiService = null;
        initApiService();
    }

    /* loaded from: classes6.dex */
    private static class SingletonHolder {
        private static final EventConfigRetrofit INSTANCE = new EventConfigRetrofit();

        private SingletonHolder() {
        }
    }

    public static EventConfigRetrofit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ApiService getApiService() {
        if (this.mApiService == null) {
            initApiService();
        }
        return this.mApiService;
    }

    public boolean isApiService() {
        return this.mApiService != null;
    }

    public String getBaseUrl() {
        return AppUtils.getDataReportConfigUrl();
    }

    private void initApiService() {
        try {
            HttpsUtils.SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory(null, null, null);
            this.mApiService = (ApiService) new Retrofit.Builder().baseUrl(getBaseUrl()).addConverterFactory(CustomGsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(new OkHttpClient.Builder().connectTimeout(6L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager).hostnameVerifier($$Lambda$EventConfigRetrofit$4mwsI8ip3Kpngitt53RPIVkC80Q.INSTANCE).build()).build().create(ApiService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
