package com.slzhibo.library.http;

import com.slzhibo.library.http.interceptor.BaseUrlManagerInterceptor;
import com.slzhibo.library.http.utils.HttpsUtils;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.gsonconverter.GsonConverterFactory;
import com.slzhibo.library.utils.rxjava2adapter.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* loaded from: classes6.dex */
public class CacheApiRetrofit {
    private ApiService mApiService;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$initApiService$0(String str, SSLSession sSLSession) {
        return true;
    }

    private CacheApiRetrofit() {
        initApiService();
    }

    /* loaded from: classes6.dex */
    private static class SingletonHolder {
        private static final CacheApiRetrofit INSTANCE = new CacheApiRetrofit();

        private SingletonHolder() {
        }
    }

    public static CacheApiRetrofit getInstance() {
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

    private void initApiService() {
        try {
            HttpsUtils.SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory(null, null, null);
            this.mApiService = (ApiService) new Retrofit.Builder().baseUrl(AppUtils.getApiURl()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new BaseUrlManagerInterceptor()).connectTimeout(10L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager).hostnameVerifier($$Lambda$CacheApiRetrofit$bmEQH3a0T7wUnxH4UI8wOBg_bmA.INSTANCE).build()).build().create(ApiService.class);
        } catch (Exception unused) {
        }
    }
}
