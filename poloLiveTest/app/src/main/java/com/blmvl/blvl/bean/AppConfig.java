package com.blmvl.blvl.bean;

//package com.blmvl.blvl.bean;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.blmvl.blvl.util.CommonCallback;
import com.blmvl.blvl.httpUtil.HttpUtil;
import com.blmvl.blvl.util.SpUtil;

import java.util.List;
//import p067e.p103c.p104a.p110h.CommonCallback;
//import p067e.p103c.p104a.p111i.HttpUtil;
//import p067e.p103c.p104a.p113k.SpUtil;

/* loaded from: classes.dex */
public class AppConfig {
    public static AppConfig mInstance;
    public ConfigBean mConfigBean;

    public static AppConfig getInstance() {
        if (mInstance == null) {
            synchronized (AppConfig.class) {
                if (mInstance == null) {
                    mInstance = new AppConfig();
                }
            }
        }
        return mInstance;
    }

    public ConfigBean getConfig() {
        String g = SpUtil.m9573D().m9539g();
        if (!TextUtils.isEmpty(g)) {
            this.mConfigBean = (ConfigBean) JSON.parseObject(g, ConfigBean.class);
        }
        return this.mConfigBean;
    }

    public List<PaySortBean> getPaySortList() {
        return getConfig().getPaySort();
    }

    public void getConfig(CommonCallback<ConfigBean> aVar) {
        if (aVar != null) {
            ConfigBean config = getConfig();
            if (config != null) {
                aVar.mo9799a(config);
            } else {
                HttpUtil.m9766a(aVar);
            }
        }
    }
}

