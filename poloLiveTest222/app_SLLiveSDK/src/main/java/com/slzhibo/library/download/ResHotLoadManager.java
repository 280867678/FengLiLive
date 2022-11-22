package com.slzhibo.library.download;

import android.text.TextUtils;

import com.blankj.utilcode.util.FileUtils;
import com.slzhibo.library.model.SysParamsInfoEntity;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.SysConfigInfoManager;
import com.slzhibo.library.utils.live.SimpleRxObserver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ResHotLoadManager {
    public final String destDir;

    /* access modifiers changed from: private */
    public static class SingletonHolder {
        private static final ResHotLoadManager INSTANCE = new ResHotLoadManager();
    }

    private ResHotLoadManager() {
        this.destDir = GiftConfig.INSTANCE.resHotLoadRootPath;
    }

    public static ResHotLoadManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void dealResLoad(SysParamsInfoEntity sysParamsInfoEntity) {
        if (!TextUtils.equals(SysConfigInfoManager.getInstance().getLocalResourceVersion(), sysParamsInfoEntity.resourceVersion)) {
            SysConfigInfoManager.getInstance().setLocalResourceVersion(sysParamsInfoEntity.resourceVersion);
            SysConfigInfoManager.getInstance().setLocalResUrl(sysParamsInfoEntity.resourceDownloadUrl);
            startDownloadRes(sysParamsInfoEntity.resourceDownloadUrl);
        }
    }

    public void reLoad() {
        startDownloadRes(SysConfigInfoManager.getInstance().getLocalResUrl());
    }

    private void startDownloadRes(String str) {
        if (AppUtils.isDownloadService() && !TextUtils.isEmpty(str)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(FileUtils.getFileNameNoExtension(str));
            stringBuffer.append(".zip");
            final String stringBuffer2 = stringBuffer.toString();
            DownLoadRetrofit.getInstance().getApiService().downLoadFile(GlideUtils.formatDownUrl(str)).map(new Function() { // from class: com.slzhibo.library.download.-$$Lambda$ResHotLoadManager$wNgI5LjB69n8bOJRxn5uiMHHRvA
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) throws Exception {
                    return ResHotLoadManager.this.lambda$startDownloadRes$0$ResHotLoadManager(stringBuffer2, (ResponseBody) obj);
                }
            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SimpleRxObserver<File>() { // from class: com.slzhibo.library.download.ResHotLoadManager.1
                public void accept(File file) {
                    ResHotLoadManager.this.dealFile(file);
                }
            });
        }
    }

    public /* synthetic */ File lambda$startDownloadRes$0$ResHotLoadManager(String str, ResponseBody responseBody) throws Exception {
        return com.slzhibo.library.utils.FileUtils.saveFile(responseBody, this.destDir, str);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void dealFile(File file) {
        if (file != null) {
            unZipRes(file.getAbsolutePath());
        }
    }

//    public void unZipRes(final String str) {
//        try {
//            if (com.slzhibo.library.utils.FileUtils.isFileExists(str) && com.slzhibo.library.utils.FileUtils.unZip(str, this.destDir)) {
//                Observable.timer(5, TimeUnit.SECONDS).subscribe(new SimpleRxObserver<Long>(this) {
//                    /* class com.slzhibo.library.download.ResHotLoadManager.AnonymousClass2 */
//
//                    public void accept(Long l) {
//                        com.slzhibo.library.utils.FileUtils.deleteFile(str);
//                    }
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void unZipRes(final String str) {
        try {
            if (com.slzhibo.library.utils.FileUtils.isFileExists(str) && com.slzhibo.library.utils.FileUtils.unZip(str, this.destDir)) {
                Observable.timer(5L, TimeUnit.SECONDS).subscribe(new SimpleRxObserver<Long>() { // from class: com.slzhibo.library.download.ResHotLoadManager.2
                    public void accept(Long l) {
                        com.slzhibo.library.utils.FileUtils.deleteFile(str);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getResHotLoadPath(String str) {
        return this.destDir + str;
    }
}
