package com.slzhibo.library.utils.gsonconverter;



import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/* loaded from: classes12.dex */
final class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GsonRequestBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.adapter = typeAdapter;
    }

    @Override // retrofit2.Converter
    /* renamed from: convert  reason: avoid collision after fix types in other method */
    public RequestBody convert(T t) throws IOException {
        Buffer buffer = new Buffer();
        JsonWriter newJsonWriter = this.gson.newJsonWriter(new OutputStreamWriter(buffer.outputStream(), UTF_8));
        this.adapter.write(newJsonWriter, t);
        newJsonWriter.close();
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }


}
