package com.slzhibo.library.download;



import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.slzhibo.library.model.GiftDownloadItemEntity;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.FileUtils;
import com.slzhibo.library.utils.GlideUtils;
//import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.List;
import okhttp3.ResponseBody;

/* loaded from: classes6.dex */
public class GiftDownLoaderImpl implements IGiftDownLoader {
    private GiftDownLoaderImpl() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class SingletonHolder {
        private static final GiftDownLoaderImpl INSTANCE = new GiftDownLoaderImpl();

        private SingletonHolder() {
        }
    }

    public static GiftDownLoaderImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override // com.slzhibo.library.download.IGiftDownLoader
    public void startDownLoad(List<GiftDownloadItemEntity> list) {
        if (!(list == null || list.isEmpty())) {
            for (GiftDownloadItemEntity giftDownloadItemEntity : list) {
                if (giftDownloadItemEntity != null && !TextUtils.isEmpty(giftDownloadItemEntity.animalUrl)) {
                    downloadFile(giftDownloadItemEntity.animalUrl, GiftConfig.INSTANCE.animResRootPath, FileUtils.formatSVGAFileName(giftDownloadItemEntity.getLocalDirName()), giftDownloadItemEntity);
                }
            }
        }
    }

    public synchronized void downloadFile(String str, final String str2, final String str3, final GiftDownloadItemEntity giftDownloadItemEntity) {
        if (AppUtils.isDownloadService()) {
            GiftDownLoadManager.getInstance().updateDownloadItem(giftDownloadItemEntity.markId, giftDownloadItemEntity.giftDirPath, 1);
            DownLoadRetrofit.getInstance().getApiService().downLoadFile(GlideUtils.formatDownUrl(str)).map(new Function<ResponseBody, File>() { // from class: com.slzhibo.library.download.GiftDownLoaderImpl.2
                public File apply(@NonNull ResponseBody responseBody) throws Exception {
                    return FileUtils.saveFile(responseBody, str2, str3);
                }
            }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation()).subscribe(new FileDownLoadObserver<File>() { // from class: com.slzhibo.library.download.GiftDownLoaderImpl.1
                @Override // com.slzhibo.library.download.FileDownLoadObserver
                public void onProgress(int i, long j) {
                }

                public void onDownLoadSuccess(File file) {
                    GiftDownLoaderImpl.this.dealDownLoadItem(giftDownloadItemEntity);
                }

                @Override // com.slzhibo.library.download.FileDownLoadObserver
                public void onDownLoadFail(Throwable th) {
                    GiftDownLoadManager instance = GiftDownLoadManager.getInstance();
                    GiftDownloadItemEntity giftDownloadItemEntity2 = giftDownloadItemEntity;
                    instance.updateDownloadItem(giftDownloadItemEntity2.markId, giftDownloadItemEntity2.giftDirPath, 0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealDownLoadItem(GiftDownloadItemEntity giftDownloadItemEntity) {
        if (giftDownloadItemEntity != null) {
            giftDownloadItemEntity.giftDirPath = FileUtils.formatSVGAFileName(getGiftDirPath(giftDownloadItemEntity.getLocalDirName()));
            GiftDownLoadManager.getInstance().updateDownloadItem(giftDownloadItemEntity.markId, giftDownloadItemEntity.giftDirPath, 2);
        }
    }

    private String getGiftDirPath(String str) {
        return GiftConfig.INSTANCE.animResRootPath + str;
    }
}
