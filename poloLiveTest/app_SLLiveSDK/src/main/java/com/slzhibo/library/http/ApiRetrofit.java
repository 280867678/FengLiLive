package com.slzhibo.library.http;

import android.util.Log;

import com.slzhibo.library.C4833a;
import com.slzhibo.library.http.interceptor.AddHeaderInterceptor;
import com.slzhibo.library.http.interceptor.BaseUrlManagerInterceptor;
import com.slzhibo.library.http.interceptor.HttpLogInterceptor;
import com.slzhibo.library.http.interceptor.SignRequestInterceptor;
import com.slzhibo.library.http.utils.CustomGsonConverterFactory;
import com.slzhibo.library.http.utils.HttpsUtils;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.rxjava2adapter.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;



import com.slzhibo.library.http.utils.CustomGsonConverterFactory;
import com.slzhibo.library.http.utils.HttpsUtils;
import com.slzhibo.library.utils.AppUtils;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import okhttp3.OkHttpClient;

import retrofit2.Retrofit;

/* loaded from: classes2.dex */
public class ApiRetrofit {
    public ApiService mApiService;

    /* renamed from: com.slzhibo.library.http.ApiRetrofit$b */
    /* loaded from: classes2.dex */
    public static class C1936b {

        /* renamed from: a */
        public static final ApiRetrofit f4619a = new ApiRetrofit();
    }

    /* renamed from: a */
    public static /* synthetic */ boolean m18390a(String str, SSLSession sSLSession) {
        return true;
    }

    public static ApiRetrofit getInstance() {
        return C1936b.f4619a;
    }

    public ApiService getApiService() {
        return this.mApiService;
    }

    public boolean isApiService() {
        return this.mApiService != null;
    }

    public ApiRetrofit() {
        this.mApiService = null;
        try {

            HttpsUtils.SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory(null, null, null);
            Log.e("ApiRetrofit", String.valueOf(sslSocketFactory.sSLSocketFactory));
            Log.e("ApiRetrofit", String.valueOf(sslSocketFactory.trustManager.toString()));

//            this.mApiService =
//                    (ApiService) new Retrofit.Builder()
//                    .baseUrl(AppUtils.getApiURl())
//                            .addConverterFactory(CustomGsonConverterFactory.create())
//                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                            .client(new OkHttpClient.Builder()
//                                    .addInterceptor(new BaseUrlManagerInterceptor())
//                                    .addInterceptor(new AddHeaderInterceptor())
//                                    .addInterceptor(new SignRequestInterceptor())
//                                    .connectTimeout(6L, TimeUnit.SECONDS)
//                                    .readTimeout(30L, TimeUnit.SECONDS)
//                                    .writeTimeout(30L, TimeUnit.SECONDS)
//                                    .sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager)
//                                    .hostnameVerifier(C4833a.f17645a).build())
//                            .build().create(ApiService.class);


            this.mApiService = (ApiService) new Retrofit.Builder().baseUrl(AppUtils.getApiURl()).addConverterFactory(CustomGsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new BaseUrlManagerInterceptor()).addInterceptor(new AddHeaderInterceptor()).addInterceptor(new SignRequestInterceptor()).connectTimeout(6L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager).hostnameVerifier(C4833a.f17645a).build()).build().create(ApiService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
