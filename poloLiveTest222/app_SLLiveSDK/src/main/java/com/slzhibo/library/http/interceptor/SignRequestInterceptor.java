package com.slzhibo.library.http.interceptor;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.slzhibo.library.http.utils.EncryptUtil;
import com.slzhibo.library.utils.SignRequestUtils;
//import com.slzhibo.library.utils.SignRequestUtils;
//
//import org.eclipse.jetty.http.HttpMethods;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/* loaded from: classes6.dex */
public class SignRequestInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();
        if (TextUtils.equals(method, "GET") || TextUtils.equals(method, "PUT")) {
            HttpUrl url = request.url();
            Set<String> queryParameterNames = url.queryParameterNames();
            TreeMap treeMap = new TreeMap();
            for (String str : queryParameterNames) {
                treeMap.put(str, url.queryParameter(str));
            }
            return chain.proceed(request.newBuilder().url(url.newBuilder().addQueryParameter("sign", SignRequestUtils.m1092a(request, treeMap, EncryptUtil.CHARSET)).build()).build());
        } else if (!TextUtils.equals(method, "POST")) {
            return chain.proceed(request);
        } else {
            RequestBody body = request.body();
            if (body instanceof MultipartBody) {
                return chain.proceed(request);
            }
            Gson gson = new Gson();
            Buffer buffer = new Buffer();
            ((RequestBody) Objects.requireNonNull(body)).writeTo(buffer);
            return chain.proceed(request.newBuilder().header("sign", SignRequestUtils.m1092a(request, (TreeMap) gson.fromJson(buffer.readUtf8(),  TreeMap.class), EncryptUtil.CHARSET)).build());
        }
    }
}
