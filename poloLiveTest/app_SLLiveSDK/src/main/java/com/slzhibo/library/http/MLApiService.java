package com.slzhibo.library.http;



import com.slzhibo.library.http.bean.HttpResultModel;
import com.slzhibo.library.model.MLIMEntity;
import com.slzhibo.library.model.MLRTCEntity;
import io.reactivex.Observable;
import java.util.Map;
import retrofit2.http.Body;
import retrofit2.http.POST;

/* loaded from: classes6.dex */
public interface MLApiService {
    public static final String BASE_CALL_URL = "call/";

    @POST("call/kick")
    Observable<HttpResultModel<Object>> callKick(@Body Map<String, Object> map);

    @POST("call/deal")
    Observable<HttpResultModel<MLRTCEntity>> dealCallService(@Body Map<String, Object> map);

    @POST("call/enter/success")
    Observable<HttpResultModel<Object>> enterMLSuccessService(@Body Map<String, Object> map);

    @POST("call/beInvited")
    Observable<HttpResultModel<MLIMEntity>> getMLBeInviterAddress(@Body Map<String, Object> map);

    @POST("call/invite")
    Observable<HttpResultModel<MLIMEntity>> getMLInviterAddress(@Body Map<String, Object> map);
}
