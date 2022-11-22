package com.slzhibo.library.utils;



/* loaded from: classes12.dex */
public class CommonTransferUtils {
    public String SIGN_API_KEY = "8zy8nbs9lyddx02slcz8ypmwcr2tlu72";
    public String SIGN_SOCKET_KEY = "8zy8nbs9lyddx02slcz8ypmwcr2tlu72";
    public String ENCRYPT_API_KEY = ConstantUtils.ENCRYPT_FILE_KEY;
    public String ENCRYPT_SOCKET_KEY = "246887c3-ee20-4fe8-a320-1fde4a8d10b6";
    public String ML_ENCRYPT_SOCKET_KEY = "c21d31be-4300-48";
    public String CALL_SIGN_API_KEY = "hhxxttxs20210601";

    /* loaded from: classes12.dex */
    private static class SingletonHolder {
        private static final CommonTransferUtils INSTANCE = new CommonTransferUtils();

        private SingletonHolder() {
        }
    }

    public static CommonTransferUtils getSingleton() {
        return SingletonHolder.INSTANCE;
    }

    public void formatSignEncryptKey(boolean z) {
        if (z) {
            this.SIGN_API_KEY = "8zy8nbs9lyddx02slcz8ypmwcr2tlu72";
            this.SIGN_SOCKET_KEY = "8zy8nbs9lyddx02slcz8ypmwcr2tlu72";
            this.ENCRYPT_API_KEY = ConstantUtils.ENCRYPT_FILE_KEY;
            this.ENCRYPT_SOCKET_KEY = "246887c3-ee20-4fe8-a320-1fde4a8d10b6";
            return;
        }
        this.SIGN_API_KEY = "789";
        this.SIGN_SOCKET_KEY = "456";
        this.ENCRYPT_API_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIQVlM6t9IBT6OxfQPhAhTAm/lCnzjy5K9mtrlYmlEXc/uNlhIqs0bCUvBPCWZ6UqWGK23LjPxwiC2jy9+i9SdkopsenLKhZV+yf+y4m02gV/wROQ2QsLng1/0IePiSyl0iGgmXsjR+LYcdAGSBBweZ/iNyiyEsnV5FYHS0cLb0XAgMBAAECgYADpmEFURNHlIoENiGieo3zpbAzZF+zl95ZVo5RvSEtyQyWFhESj/H/ciy8UwuM8Ui49FBaHWN6EIrGLKijGs/2kRcmx4mbnK9eQmkQBuRaTfgqc03XTK7LNU+pz3PVTRlfn7GkDfsSWaeDWNtbR1zK1mgoR+JnMfqbM8C0FqOaEQJBAMEzfzqEgkpmEtx9cUfyLPIw0pGviepZtp+lFO6PHQlPszwM/Xof9ZVhIR8oIR+mCJfqqCGoeoWQbAnoiQizoD8CQQCvBHnxnsxBITaq2Wrjod/rDeM3YHRDg6HET9cVKKIIvhSlLFx8KYw+ZbhPxdz219hdFmdjM3PYy1xibucsQi0pAkBDgKypU3b6a6OXajTUQGc3z5siz8ROHz5RlSo1F8e7Yx9qkddWfigeIyuhaTH5jtddzN0ltWnplMZKx/ZpFemdAkEAot86kHWkRZQgKLyucWpKVJeW9QjpCY9tMqDOWx12NUaXNeNjqhSMM+E7tdk/uePCsVZRHotaas1NizkEHzbyiQJAfC0aRuF5AdJ81o8GJ4j0FwnRUiqWS2DPT9n2x16cmhP2v2ik14nQzp2ihML2kE1I7WUtHzFkZv6NnxBthM4Xwg==";
        this.ENCRYPT_SOCKET_KEY = "c21d31be-4300-4881-a553-156ebb5df087";
    }

    public void setApiKey(String str, String str2, String str3, String str4) {
        this.SIGN_API_KEY = str;
        this.ENCRYPT_API_KEY = str2;
        this.SIGN_SOCKET_KEY = str3;
        this.ENCRYPT_SOCKET_KEY = str4;
    }


//    /* renamed from: a */
//    public static CommonTransferUtils m1583a() {
//        return C5986a.f20074a;
//    }

}

