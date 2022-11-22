package com.slzhibo.library.http.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.slzhibo.library.http.bean.CustomGsonResultModel;
import com.slzhibo.library.http.bean.EncryptHttpResultModel;
import com.slzhibo.library.http.exception.ServerException;
import com.slzhibo.library.utils.AppUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/* loaded from: classes6.dex */
public class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private final Gson gson;
    private JsonParser jsonParser = new JsonParser();

    /* JADX INFO: Access modifiers changed from: package-private */
    public CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.adapter = typeAdapter;
    }

    /* JADX WARN: Finally extract failed */
    @Override
    public T convert(@NonNull ResponseBody responseBody) throws IOException {
        String string = responseBody.string();
//        Log.e("","CustomGsonResponseBodyConverter");
        if (isEncrypt(this.jsonParser, string)) {
//            JsonParser json = new JsonParser();
//            JsonObject jsonOb = json.parse(string ).getAsJsonObject();
//            Gson gsons = new GsonBuilder().setPrettyPrinting().create();
////            textView.setText(gsons.toJson(jsonOb));
//            Log.e("","CustomGsonResponseBodyConverter：未解333密数据："+gsons.toJson(jsonOb));
//            if(this.gson ==  null){
//                Log.e("","CustomGsonResponseBodyConverter：nullllllllll："+gsons.toJson(jsonOb));
//            }
//            EncryptHttpResultModel encryptHttpResultModels =  new Gson().fromJson(string,  EncryptHttpResultModel.class);
//            CustomGsonResultModel customGsonResultModels = new CustomGsonResultModel();
//            customGsonResultModels.code = encryptHttpResultModels.getCode();
//            customGsonResultModels.msg = encryptHttpResultModels.getMessage();
//            String jsonDatas = encryptHttpResultModels.getJsonData();
//
//
//            Log.e("","CustomGsonResponseBodyConverter：//////////："+jsonDatas);
//            if (this.jsonParser.parse(jsonDatas).isJsonArray()) {
//                customGsonResultModels.data = new JsonParser().parse(jsonDatas).getAsJsonArray();
//            } else {
//                customGsonResultModels.data = new JsonParser().parse(jsonDatas).getAsJsonObject();
//            }
//            string = this.gson.toJson(customGsonResultModels);
//            Log.e("","CustomGsonResponseBodyConverter：$$$$$$$$$$$$$$："+jsonDatas);
//            Log.e("","CustomGsonResponseBodyConverter：$$$$$$$$$$$$$$："+"111111111");
            EncryptHttpResultModel encryptHttpResultModel =  this.gson.fromJson(string,  EncryptHttpResultModel.class);
//            Log.e("","CustomGsonResponseBodyConverter：解密333后数据："+encryptHttpResultModel.getJsonData());
//            Log.e("","CustomGsonResponseBodyConverter：$$$$$$$$$$$$$$："+"22222222222");
            CustomGsonResultModel customGsonResultModel = new CustomGsonResultModel();
//            Log.e("","CustomGsonResponseBodyConverter：$$$$$$$$$$$$$$："+"333333333333");
            customGsonResultModel.code = encryptHttpResultModel.getCode();
//            Log.e("","CustomGsonResponseBodyConverter：$$$$$$$$$$$$$$："+"444444444444");
            customGsonResultModel.msg = encryptHttpResultModel.getMessage();
//            Log.e("","CustomGsonResponseBodyConverter：$$$$$$$$$$$$$$："+"555555555555");
            String jsonData = encryptHttpResultModel.getJsonData();
//            Log.e("","CustomGsonResponseBodyConverter：$$$$$$$$$$$$$$："+"6666666666666");

            if (this.jsonParser.parse(jsonData).isJsonArray()) {
                customGsonResultModel.data = new JsonParser().parse(jsonData).getAsJsonArray();
//                Log.e("","CustomGsonResponseBodyConverter：$$$$$$$$$$$$$$："+"777777777777");
            } else {
                customGsonResultModel.data = new JsonParser().parse(jsonData).getAsJsonObject();
//                Log.e("","CustomGsonResponseBodyConverter：$$$$$$$$$$$$$$："+"88888888888888");
            }
            string = this.gson.toJson(customGsonResultModel);
//            Log.e("","CustomGsonResponseBodyConverter：$$$$$$$$$$$$$$："+"99999999999");
        }
        try {
            CustomGsonResultModel customGsonResultModel2 = (CustomGsonResultModel) this.gson.fromJson(string,  CustomGsonResultModel.class);
//            Log.e("","CustomGsonResponseBodyConverter：$$$$$$$$$$$$$$："+"****************");
            if (customGsonResultModel2 != null && AppUtils.isTokenInvalidErrorCode(customGsonResultModel2.getCode())) {
//                Log.e("","CustomGsonResponseBodyConverter：$$$$$$$$$$$$$$："+"&&&&&&&&&&&&&&&");
                responseBody.close();
                throw new ServerException(customGsonResultModel2.getCode(), customGsonResultModel2.getMsg());
            }
//            Log.e("","CustomGsonResponseBodyConverter：$$$$$$$$$$$$$$："+"^^^^^^^^^^^^^^^");
            T fromJson = this.adapter.fromJson(string);
//            Log.e("","CustomGsonResponseBodyConverter：$$$$$$$$$$$$$$："+"%%%%%%%%%%%%%%%");
//            Log.e("","CustomGsonResponseBodyConverter：数据111："+string);
//            Log.e("","CustomGsonResponseBodyConverter：数据222："+fromJson);
            responseBody.close();
            return fromJson;
        } catch (Throwable th) {
            responseBody.close();
            throw th;
        }
    }

    private boolean isEncrypt(JsonParser jsonParser, String str) {
        try {
            String json = this.gson.toJson(((CustomGsonResultModel) this.gson.fromJson(str,  CustomGsonResultModel.class)).data);
//            Log.e("","CustomGsonResponseBodyConverter：未解密数据2："+str);
//            Log.e("","CustomGsonResponseBodyConverter：解密后数据2："+json);
            if (!jsonParser.parse(json).isJsonArray() && jsonParser.parse(json).getAsJsonObject().size() == 2 && jsonParser.parse(json).getAsJsonObject().has("key")) {
//                Log.e("","CustomGsonResponseBodyConverter：数据122："+jsonParser.parse(json).getAsJsonObject());
//                Log.e("","CustomGsonResponseBodyConverter：数据1223："+jsonParser.parse(json).getAsJsonObject().has("data"));

                return jsonParser.parse(json).getAsJsonObject().has("data");
            }

            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
