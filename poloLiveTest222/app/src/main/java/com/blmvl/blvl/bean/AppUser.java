package com.blmvl.blvl.bean;

//package com.blmvl.blvl.bean;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.blmvl.blvl.util.CommonCallback;
import com.blmvl.blvl.httpUtil.HttpUtil;
import com.blmvl.blvl.util.SpUtil;
//import p067e.p103c.p104a.p110h.CommonCallback;
//import p067e.p103c.p104a.p111i.HttpUtil;
//import p067e.p103c.p104a.p113k.SpUtil;

/* loaded from: classes.dex */
public class AppUser {
    public static AppUser mInstance;
    public UserBean mUserBean;

    public static AppUser getInstance() {
        if (mInstance == null) {
            synchronized (AppUser.class) {
                if (mInstance == null) {
                    mInstance = new AppUser();
                }
            }
        }
        return mInstance;
    }

    public UserBean getUser() {
        String r = SpUtil.m9573D().m9522r();
        if (!TextUtils.isEmpty(r)) {
            this.mUserBean = JSON.parseObject(r, UserBean.class);
        }
        return this.mUserBean;
    }

    public boolean hasMsg() {
        UserBean userBean = this.mUserBean;
        return userBean != null && userBean.getMessage_tip() > 0;
    }

    public boolean isRealVip() {
        UserBean userBean = this.mUserBean;
        return userBean != null && userBean.getIs_vip() == 1 && this.mUserBean.getVip_level() > 0;
    }

    public void setUser(UserBean userBean) {
        SpUtil.m9573D().m9532j(JSON.toJSONString(userBean));
    }

    public void getUser(CommonCallback<UserBean> aVar) {
        if (aVar != null) {
            try {
                UserBean user = getUser();
                if (user != null) {
                    aVar.mo9799a(user);
                } else {
                    HttpUtil.m9737b(aVar);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

