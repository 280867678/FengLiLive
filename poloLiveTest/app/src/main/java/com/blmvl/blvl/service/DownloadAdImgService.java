package com.blmvl.blvl.service;

//package com.blmvl.blvl.service;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.blmvl.blvl.L;
import com.blmvl.blvl.bean.OpenScreenAdBean;
import com.blmvl.blvl.util.SpUtil;
import com.bumptech.glide.Glide;
import java.io.File;
//import p067e.p103c.p104a.p113k.SpUtil;
//import p067e.p130f.p131a.p135d.L;

/* loaded from: classes.dex */
public class DownloadAdImgService extends IntentService {
    public DownloadAdImgService() {
        super("DownloadAdImgService");
    }

    @Override // android.app.IntentService
    public void onHandleIntent(Intent intent) {
        try {
            OpenScreenAdBean openScreenAdBean = (OpenScreenAdBean) intent.getParcelableExtra("open_screen_ad");
            if (openScreenAdBean != null && !TextUtils.isEmpty(openScreenAdBean.getImg_url())) {
                String img_url = openScreenAdBean.getImg_url();
                String substring = img_url.substring(img_url.lastIndexOf("/") + 1);
                File file = Glide.with(this).asFile().load(img_url).submit().get();
                if (file != null) {
                    String path = file.getPath();
                    boolean z = false;
                    String str = path.substring(0, path.lastIndexOf("/")) + File.separator + "ad";
                    File file2 = new File(str);
                    String str2 = str + File.separator + substring;
                    if ((!file2.exists() ? file2.mkdirs() : false) || file2.exists()) {
                        z = file.renameTo(new File(str2));
                    }
                    if (z) {
                        openScreenAdBean.setLocal_path(str2);
                        SpUtil.m9573D().m9538g(JSON.toJSONString(openScreenAdBean));
                    }
                }
            }
        } catch (Exception e) {
            L.m9079a(e.toString());
        }
    }
}

