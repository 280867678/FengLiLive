package com.slzhibo.library.utils.litepal;

import android.app.Application;
import android.content.Context;

import com.slzhibo.library.SLLiveSDK;
import com.slzhibo.library.utils.litepal.exceptions.GlobalException;

/* loaded from: classes12.dex */
public class LitePalApplication {
    public static Context getContext() {
        Application application = SLLiveSDK.getSingleton().getApplication();
        if (application != null) {
            return application;
        }
        throw new GlobalException(GlobalException.APPLICATION_CONTEXT_IS_NULL);
    }
}
