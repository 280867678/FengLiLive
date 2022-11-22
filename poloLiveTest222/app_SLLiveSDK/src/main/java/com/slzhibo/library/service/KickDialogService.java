package com.slzhibo.library.service;


import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.slzhibo.library.utils.ConstantUtils;

/* loaded from: classes2.dex */
public class KickDialogService extends IntentService {
    public KickDialogService() {
        super("KickDialogService");
    }

    @Override // android.app.IntentService
    public void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(500L);
            Intent intent2 = new Intent(ConstantUtils.LIVE_KICK_OUT_ACTION);
            intent2.putExtra(ConstantUtils.RESULT_ITEM, intent.getStringExtra(ConstantUtils.RESULT_ITEM));
            sendBroadcast(intent2);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
