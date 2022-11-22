package com.slzhibo.library.http.bean;

/* loaded from: classes6.dex */
public class CustomGsonResultModel {
    public int code = 0;
    public Object data;
    public String msg;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object obj) {
        this.data = obj;
    }
}
