package com.blmvl.blvl.bean;

//package com.blmvl.blvl.bean;

import com.alibaba.fastjson.annotation.JSONField;

/* loaded from: classes.dex */
public class JsonBean {
    public boolean crypt;
    public String data;
    public boolean isLogin;
    public boolean isVV;
    public String msg;
    public boolean needLogin;
    public int status;

    public String getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getStatus() {
        return this.status;
    }

    public boolean isCrypt() {
        return this.crypt;
    }

    public boolean isLogin() {
        return this.isLogin;
    }

    public boolean isNeedLogin() {
        return this.needLogin;
    }

    @JSONField(name = "isVV")
    public boolean isVV() {
        return this.isVV;
    }

    public void setCrypt(boolean z) {
        this.crypt = z;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setLogin(boolean z) {
        this.isLogin = z;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setNeedLogin(boolean z) {
        this.needLogin = z;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    @JSONField(name = "isVV")
    public void setVV(boolean z) {
        this.isVV = z;
    }
}

