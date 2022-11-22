package com.slzhibo.library.service;



import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;
import com.slzhibo.library.utils.ConstantUtils;

/* loaded from: classes8.dex */
public class TokenDialogService extends IntentService {
    public TokenDialogService() {
        super("TokenDialogService");
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(500L);
            sendBroadcast(new Intent(ConstantUtils.LIVE_TOKEN_INVALID_ACTION));
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}

