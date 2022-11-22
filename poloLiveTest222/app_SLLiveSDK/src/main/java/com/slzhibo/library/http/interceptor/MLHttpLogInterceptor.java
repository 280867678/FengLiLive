package com.slzhibo.library.http.interceptor;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.JsonParser;
import com.slzhibo.library.utils.GsonUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/* loaded from: classes6.dex */
public class MLHttpLogInterceptor implements Interceptor {
    private static final String TAG = "MLHttpLogInterceptor";
    private final Charset UTF8 = StandardCharsets.UTF_8;
    private JsonParser jsonParser = new JsonParser();

    @Override // okhttp3.Interceptor
    public Response intercept(Chain chain) throws IOException {
        String str;
        Request request = chain.request();
        RequestBody body = request.body();
        if (body != null) {
            Buffer buffer = new Buffer();
            body.writeTo(buffer);
            Charset charset = this.UTF8;
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(this.UTF8);
            }
            str = buffer.readString(charset);
        } else {
            str = null;
        }
        LogUtils.json(TAG, "发送请求: method：" + request.method() + "\nurl：" + request.url() + "\n请求头：" + request.headers() + "\n请求参数: " + str);
        long nanoTime = System.nanoTime();
        Response proceed = chain.proceed(request);
        TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
        ResponseBody body2 = proceed.body();
        BufferedSource source = body2.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer2 = source.buffer();
        Charset charset2 = this.UTF8;
        MediaType contentType2 = body2.contentType();
        if (contentType2 != null) {
            try {
                charset2 = contentType2.charset(this.UTF8);
            } catch (UnsupportedCharsetException e) {
                e.printStackTrace();
            }
        }
        String readString = buffer2.clone().readString(charset2);
        LogUtils.json(TAG, "\n    \n请求url: " + proceed.request().url() + "\n请求头部: " + request.headers() + "\n请求参数: " + str + "\nResponse Code:" + proceed.code() + "\nResponse: " + GsonUtils.toJson(readString) + "\n-----------------------------------------------------------------------------------------------");
        return proceed;
    }
}
