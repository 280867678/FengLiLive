package com.slzhibo.library.download;



import com.slzhibo.library.http.ApiService;
import com.slzhibo.library.http.interceptor.AddHeaderInterceptor;
import com.slzhibo.library.http.utils.HttpsUtils;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.gsonconverter.GsonConverterFactory;
import com.slzhibo.library.utils.rxjava2adapter.RxJava2CallAdapterFactory;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* loaded from: classes6.dex */
public class DownLoadRetrofit {
    private ApiService mApiService;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$new$0(String str, SSLSession sSLSession) {
        return true;
    }

    private DownLoadRetrofit() {
        this.mApiService = null;
        try {
            HttpsUtils.SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory(null, null, null);
            this.mApiService = (ApiService) new Retrofit.Builder().baseUrl(AppUtils.getImgDownloadURl()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new DownBaseUrlManagerInterceptor()).addInterceptor(new AddHeaderInterceptor()).connectTimeout(10L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager).hostnameVerifier($$Lambda$DownLoadRetrofit$Yyr5h0fyoSQrAwNbvL9pkscK0GE.INSTANCE).build()).build().create(ApiService.class);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class SingletonHolder {
        private static final DownLoadRetrofit INSTANCE = new DownLoadRetrofit();

        private SingletonHolder() {
        }
    }

    public static DownLoadRetrofit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ApiService getApiService() {
        return this.mApiService;
    }

    public boolean isApiService() {
        return this.mApiService != null;
    }
}
