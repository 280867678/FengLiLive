package com.slzhibo.library.download;

import android.text.TextUtils;

import com.slzhibo.library.model.CarDownloadEntity;
import com.slzhibo.library.model.CarDownloadListEntity;
import com.slzhibo.library.utils.FileUtils;
import com.slzhibo.library.utils.GsonUtils;

import java.io.File;
import java.util.Iterator;

public class CarDownLoadManager {
    private volatile CarDownloadListEntity mDownloadListConfig;

    /* access modifiers changed from: private */
    public static class SingletonHolder {
        private static final CarDownLoadManager INSTANCE = new CarDownLoadManager();
    }

    public void updateAnimOnlineAllRes() {
    }

    public void updateAnimOnlineSingleRes(CarDownloadEntity carDownloadEntity) {
    }

    private CarDownLoadManager() {
    }

    public static CarDownLoadManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private synchronized void setLocalDownloadList(CarDownloadListEntity carDownloadListEntity) {
        if (carDownloadListEntity != null) {
            try {
                this.mDownloadListConfig = carDownloadListEntity;
                FileUtils.writByEncrypt(GsonUtils.toJson(carDownloadListEntity), getDownConfigPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        return;
    }

    public CarDownloadEntity getCarItemEntity(String str) {
        CarDownloadEntity carDownloadEntity = null;
        if (!(this.mDownloadListConfig == null || this.mDownloadListConfig.carList == null || this.mDownloadListConfig.carList.size() <= 0)) {
            for (CarDownloadEntity carDownloadEntity2 : this.mDownloadListConfig.carList) {
                if (TextUtils.equals(carDownloadEntity2.id, str) && !TextUtils.isEmpty(carDownloadEntity2.animLocalPath)) {
                    if (FileUtils.isExist(carDownloadEntity2.animLocalPath)) {
                        carDownloadEntity = carDownloadEntity2;
                    } else {
                        updateLocalCarItem(str);
                    }
                }
            }
        }
        return carDownloadEntity;
    }

    public File getDownloadFile(String str) {
        return FileUtils.getFileByPath(GiftConfig.INSTANCE.carAnimResRootPath + str);
    }

    private void updateLocalCarItem(String str) {
        if (this.mDownloadListConfig != null && this.mDownloadListConfig.carList != null && this.mDownloadListConfig.carList.size() > 0) {
            CarDownloadListEntity carDownloadListEntity = this.mDownloadListConfig;
            Iterator<CarDownloadEntity> it2 = carDownloadListEntity.carList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                CarDownloadEntity next = it2.next();
                if (next.id.equals(str)) {
                    next.animLocalPath = "";
                    break;
                }
            }
            setLocalDownloadList(carDownloadListEntity);
        }
    }

    private String getDownConfigPath() {
        return GiftConfig.INSTANCE.carAnimResRootPath + GiftConfig.INSTANCE.CAR_CONFIG_NAME;
    }
}
