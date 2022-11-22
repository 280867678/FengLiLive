package com.slzhibo.library.download;



import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* compiled from: lambda */
/* renamed from: com.slzhibo.library.download.-$$Lambda$DownLoadRetrofit$Yyr5h0fyoSQrAwNbvL9pkscK0GE  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$DownLoadRetrofit$Yyr5h0fyoSQrAwNbvL9pkscK0GE implements HostnameVerifier {
    public static final /* synthetic */ $$Lambda$DownLoadRetrofit$Yyr5h0fyoSQrAwNbvL9pkscK0GE INSTANCE = new $$Lambda$DownLoadRetrofit$Yyr5h0fyoSQrAwNbvL9pkscK0GE();

    private /* synthetic */ $$Lambda$DownLoadRetrofit$Yyr5h0fyoSQrAwNbvL9pkscK0GE() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        return DownLoadRetrofit.lambda$new$0(str, sSLSession);
    }
}
