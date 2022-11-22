package com.slzhibo.library.download;



import androidx.annotation.NonNull;
import com.slzhibo.library.utils.AppUtils;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes6.dex */
public class DownBaseUrlManagerInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url();
        Request.Builder newBuilder = request.newBuilder();
        HttpUrl parse = HttpUrl.parse(AppUtils.getImgDownloadURl());
        if (parse == null) {
            return chain.proceed(chain.request());
        }
        return chain.proceed(newBuilder.url(url.newBuilder().scheme(parse.scheme()).host(parse.host()).port(parse.port()).build()).build());
    }
}