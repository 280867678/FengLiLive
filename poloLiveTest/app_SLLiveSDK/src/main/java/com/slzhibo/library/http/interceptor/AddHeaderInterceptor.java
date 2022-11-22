package com.slzhibo.library.http.interceptor;

import android.util.Log;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.DeviceUtils;
//import com.eclipsesource.v8.Platform;
import com.slzhibo.library.SLLiveSDK;
//import com.slzhibo.library.utils.LogConstants;
import com.slzhibo.library.utils.StringUtils;
import com.slzhibo.library.utils.UserInfoManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes6.dex */
public class AddHeaderInterceptor implements Interceptor {
    public static final String DEVICE_ID = "deviceId";
    public static final String RANDOM_STR = "randomStr";
    public static final String TIME_STAMP_STR = "timeStampStr";

    @Override // okhttp3.Interceptor
    public Response intercept(@NonNull Chain chain) throws IOException {
//        Log.e("AddHeaderInterceptor::","getAppId:"+UserInfoManager.getInstance().getAppId());
//        Log.e("AddHeaderInterceptor::","getSDKVersionName:"+DeviceUtils.getSDKVersionName());
//        Log.e("AddHeaderInterceptor::","getManufacturer:"+DeviceUtils.getManufacturer());
//        Log.e("AddHeaderInterceptor::","getModel:"+DeviceUtils.getModel());
//        Log.e("AddHeaderInterceptor::","getAndroidID:"+DeviceUtils.getAndroidID());
//        Log.e("AddHeaderInterceptor::","getAppOpenId:"+UserInfoManager.getInstance().getAppOpenId());
//        Log.e("AddHeaderInterceptor::","getToken:"+UserInfoManager.getInstance().getToken());
//        Log.e("AddHeaderInterceptor::","getUserId::"+UserInfoManager.getInstance().getUserId());
//        Log.e("AddHeaderInterceptor::","");
        Request.Builder header = chain.request().newBuilder().header("deviceType", "android").header("appId", UserInfoManager.getInstance().getAppId()).header("appKey", SLLiveSDK.getSingleton().APP_KEY).header("sdkVersion", "3.6.0").header("osVersion", DeviceUtils.getSDKVersionName());
        return chain.proceed(header.header("deviceName", DeviceUtils.getManufacturer() + "_" + DeviceUtils.getModel()).header(TIME_STAMP_STR, String.valueOf(System.currentTimeMillis() / 1000)).header(RANDOM_STR, StringUtils.getRandomString(16)).header("deviceId", DeviceUtils.getAndroidID()).header("openId", UserInfoManager.getInstance().getAppOpenId()).header("token", UserInfoManager.getInstance().getToken()).header("userId", UserInfoManager.getInstance().getUserId()).build());
//        Request.Builder header = chain.request().newBuilder().header("deviceType", "android").header("appId", UserInfoManager.getInstance().getAppId()).header("appKey", SLLiveSDK.getSingleton().APP_KEY).header("sdkVersion", "3.6.0").header("osVersion", DeviceUtils.getSDKVersionName());
//        return chain.proceed(header.header("deviceName", DeviceUtils.getManufacturer() + "_" + DeviceUtils.getModel()).header(TIME_STAMP_STR, String.valueOf(System.currentTimeMillis() / 1000)).header(RANDOM_STR, StringUtils.getRandomString(16)).header("deviceId", DeviceUtils.getAndroidID()).header("openId", UserInfoManager.getInstance().getAppOpenId()).header("token", UserInfoManager.getInstance().getChannelToken()).header("userId", UserInfoManager.getInstance().getAppOpenId()).build());

    }
}
