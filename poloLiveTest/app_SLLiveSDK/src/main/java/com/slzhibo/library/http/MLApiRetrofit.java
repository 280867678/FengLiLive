package com.slzhibo.library.http;

import com.slzhibo.library.http.interceptor.MLBaseUrlManagerInterceptor;
import com.slzhibo.library.http.interceptor.MLHttpLogInterceptor;
import com.slzhibo.library.http.utils.HttpsUtils;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.gsonconverter.GsonConverterFactory;
import com.slzhibo.library.utils.rxjava2adapter.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* loaded from: classes6.dex */
public class MLApiRetrofit {
    private MLApiService mApiService;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$initApiService$0(String str, SSLSession sSLSession) {
        return true;
    }

    private MLApiRetrofit() {
        this.mApiService = null;
        initApiService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class SingletonHolder {
        private static final MLApiRetrofit INSTANCE = new MLApiRetrofit();

        private SingletonHolder() {
        }
    }

    public static MLApiRetrofit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public MLApiService getApiService() {
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
            this.mApiService = (MLApiService) new Retrofit.Builder().baseUrl(AppUtils.getVideoCallUrl()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new MLHttpLogInterceptor()).addInterceptor(new MLBaseUrlManagerInterceptor()).connectTimeout(6L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager).hostnameVerifier($$Lambda$MLApiRetrofit$nDVsg4NFJkPsPNx4KCla05x7XY.INSTANCE).build()).build().create(MLApiService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
