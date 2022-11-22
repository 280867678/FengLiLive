package com.slzhibo.library.download;



import android.app.Dialog;
import android.text.TextUtils;
import android.util.Log;

import com.slzhibo.library.http.ApiRetrofit;
import com.slzhibo.library.http.RequestParams;
import com.slzhibo.library.http.ResultCallBack;
import com.slzhibo.library.http.function.HttpResultFunction;
import com.slzhibo.library.http.function.ServerResultFunction;
import com.slzhibo.library.http.utils.RetryWithDelayUtils;
import com.slzhibo.library.model.NobilityDownLoadEntity;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.FileUtils;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.MD5Utils;
import com.slzhibo.library.utils.live.SimpleRxObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.util.List;
import okhttp3.ResponseBody;

/* loaded from: classes6.dex */
public class NobilityDownLoadManager {
    private final String FILE_FORMAT;

    private NobilityDownLoadManager() {
        this.FILE_FORMAT = ".svga";
    }

    /* loaded from: classes6.dex */
    private static class SingletonHolder {
        private static final NobilityDownLoadManager INSTANCE = new NobilityDownLoadManager();

        private SingletonHolder() {
        }
    }

    public static NobilityDownLoadManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void updateAnimOnlineAllRes() {
        if (AppUtils.isApiService()) {
            ApiRetrofit.getInstance().getApiService().getNobilitySourceListService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<List<NobilityDownLoadEntity>>() { // from class: com.slzhibo.library.download.NobilityDownLoadManager.3
            }).onErrorResumeNext(new HttpResultFunction<List<NobilityDownLoadEntity>>() { // from class: com.slzhibo.library.download.NobilityDownLoadManager.2
            }).retryWhen(new RetryWithDelayUtils(3, 5)).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation()).subscribe(new SimpleRxObserver<List<NobilityDownLoadEntity>>() { // from class: com.slzhibo.library.download.NobilityDownLoadManager.1

                @Override
                public void accept(List<NobilityDownLoadEntity> list) {
                    Log.e("","tl/nobility/source/list");
                    if (!(list == null || list.isEmpty())) {
                        for (NobilityDownLoadEntity nobilityDownLoadEntity : list) {
                            if (NobilityDownLoadManager.this.isDownloadFile(nobilityDownLoadEntity.type) && !TextUtils.isEmpty(nobilityDownLoadEntity.animalUrl)) {
                                NobilityDownLoadManager nobilityDownLoadManager = NobilityDownLoadManager.this;
                                nobilityDownLoadManager.downloadFile(nobilityDownLoadEntity.animalUrl, GiftConfig.INSTANCE.nobilityAnimResRootPath, nobilityDownLoadManager.getDESEncryptFileName(nobilityDownLoadEntity.type));
                            }
                        }
                    }
                }
            });
        }
    }

    public void updateAnimOnlineSingleRes(final int i) {
        if (AppUtils.isApiService()) {
            ApiRetrofit.getInstance().getApiService().getNobilitySourceListService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<List<NobilityDownLoadEntity>>() { // from class: com.slzhibo.library.download.NobilityDownLoadManager.5
            }).flatMap(new Function() { // from class: com.slzhibo.library.download.-$$Lambda$NobilityDownLoadManager$0GaCd05hPkymccpqvahYdvnjPyI
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) throws Exception {
                    return NobilityDownLoadManager.this.lambda$updateAnimOnlineSingleRes$0$NobilityDownLoadManager(i, (List) obj);
                }
            }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation()).subscribe(new SimpleRxObserver<NobilityDownLoadEntity>() { // from class: com.slzhibo.library.download.NobilityDownLoadManager.4
                public void accept(NobilityDownLoadEntity nobilityDownLoadEntity) {
                    if (nobilityDownLoadEntity != null && NobilityDownLoadManager.this.isDownloadFile(nobilityDownLoadEntity.type) && nobilityDownLoadEntity.type == i && !TextUtils.isEmpty(nobilityDownLoadEntity.animalUrl)) {
                        NobilityDownLoadManager nobilityDownLoadManager = NobilityDownLoadManager.this;
                        nobilityDownLoadManager.downloadFile(nobilityDownLoadEntity.animalUrl, GiftConfig.INSTANCE.nobilityAnimResRootPath, nobilityDownLoadManager.getDESEncryptFileName(nobilityDownLoadEntity.type));
                    }
                }
            });
        }
    }

    public /* synthetic */ ObservableSource lambda$updateAnimOnlineSingleRes$0$NobilityDownLoadManager(int i, List list) throws Exception {
        return Observable.just(getNobilityItemByType(list, i));
    }

    public void updateAnimOnlineSingleRes(final int i, final Dialog dialog, final ResultCallBack<String> resultCallBack) {
        if (AppUtils.isApiService()) {
            ApiRetrofit.getInstance().getApiService().getNobilitySourceListService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<List<NobilityDownLoadEntity>>() { // from class: com.slzhibo.library.download.NobilityDownLoadManager.7
            }).flatMap(new Function() { // from class: com.slzhibo.library.download.-$$Lambda$NobilityDownLoadManager$EN_EEC6XyH0RETr86S8XPrW9FAU
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) throws Exception {
                    return NobilityDownLoadManager.this.lambda$updateAnimOnlineSingleRes$1$NobilityDownLoadManager(i, (List) obj);
                }
            }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<NobilityDownLoadEntity>() { // from class: com.slzhibo.library.download.NobilityDownLoadManager.6
                @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    super.onSubscribe(disposable);
                    Dialog dialog2 = dialog;
                    if (dialog2 != null) {
                        dialog2.show();
                    }
                }

                public void accept(NobilityDownLoadEntity nobilityDownLoadEntity) {
                    if (nobilityDownLoadEntity != null) {
                        if (!NobilityDownLoadManager.this.isDownloadFile(nobilityDownLoadEntity.type)) {
                            ResultCallBack resultCallBack2 = resultCallBack;
                            if (resultCallBack2 != null) {
                                resultCallBack2.onError(0, "");
                            }
                        } else if (nobilityDownLoadEntity.type != i || TextUtils.isEmpty(nobilityDownLoadEntity.animalUrl)) {
                            ResultCallBack resultCallBack3 = resultCallBack;
                            if (resultCallBack3 != null) {
                                resultCallBack3.onError(0, "");
                            }
                        } else {
                            NobilityDownLoadManager nobilityDownLoadManager = NobilityDownLoadManager.this;
                            nobilityDownLoadManager.downloadFile(nobilityDownLoadEntity.animalUrl, GiftConfig.INSTANCE.nobilityAnimResRootPath, nobilityDownLoadManager.getDESEncryptFileName(nobilityDownLoadEntity.type), resultCallBack);
                        }
                    }
                }

                @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
                public void onComplete() {
                    super.onComplete();
                }

                @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    super.onError(th);
                    ResultCallBack resultCallBack2 = resultCallBack;
                    if (resultCallBack2 != null) {
                        resultCallBack2.onError(0, th.getMessage());
                    }
                }
            });
        }
    }

    public /* synthetic */ ObservableSource lambda$updateAnimOnlineSingleRes$1$NobilityDownLoadManager(int i, List list) throws Exception {
        return Observable.just(getNobilityItemByType(list, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadFile(String str, final String str2, final String str3) {
        if (AppUtils.isDownloadService()) {
            DownLoadRetrofit.getInstance().getApiService().downLoadFile(GlideUtils.formatDownUrl(str)).map(new Function() { // from class: com.slzhibo.library.download.-$$Lambda$NobilityDownLoadManager$_EBnxfccucr_2klokLh1mTLdyJg
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) throws IOException {
                    File saveFile;
                    saveFile = FileUtils.saveFile((ResponseBody) obj, str2, str3);
                    return saveFile;
                }
            }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation()).subscribe(new SimpleRxObserver<File>() { // from class: com.slzhibo.library.download.NobilityDownLoadManager.8
                public void accept(File file) {
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadFile(String str, final String str2, final String str3, final ResultCallBack<String> resultCallBack) {
        if (AppUtils.isDownloadService()) {
            DownLoadRetrofit.getInstance().getApiService().downLoadFile(GlideUtils.formatDownUrl(str)).map(new Function() { // from class: com.slzhibo.library.download.-$$Lambda$NobilityDownLoadManager$WjbB-iQ61jfEJHXhXrToll85TH4
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) throws IOException {
                    File saveFile;
                    saveFile = FileUtils.saveFile((ResponseBody) obj, str2, str3);
                    return saveFile;
                }
            }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<File>() { // from class: com.slzhibo.library.download.NobilityDownLoadManager.9
                public void accept(File file) {
                }

                @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
                public void onComplete() {
                    super.onComplete();
                    ResultCallBack resultCallBack2 = resultCallBack;
                    if (resultCallBack2 != null) {
                        resultCallBack2.onSuccess("");
                    }
                }

                @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    super.onError(th);
                    ResultCallBack resultCallBack2 = resultCallBack;
                    if (resultCallBack2 != null) {
                        resultCallBack2.onError(0, th.getMessage());
                    }
                }
            });
        }
    }

    public String getDESEncryptFileName(int i) {
        return MD5Utils.getMd5(getNobilityName(i)) + ".svga";
    }

    public String getNobilityFilePath(int i) {
        return GiftConfig.INSTANCE.nobilityAnimResRootPath + getDESEncryptFileName(i);
    }

    private String getNobilityName(int i) {
        return "nobilityName_" + i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDownloadFile(int i) {
        if (i == 1 || i == 2) {
            return false;
        }
        return !FileUtils.isFileExists(getNobilityFilePath(i));
    }

    private NobilityDownLoadEntity getNobilityItemByType(List<NobilityDownLoadEntity> list, int i) {
        NobilityDownLoadEntity nobilityDownLoadEntity = new NobilityDownLoadEntity();
        for (NobilityDownLoadEntity nobilityDownLoadEntity2 : list) {
            if (nobilityDownLoadEntity2.type == i) {
                return nobilityDownLoadEntity2;
            }
        }
        return nobilityDownLoadEntity;
    }
}
