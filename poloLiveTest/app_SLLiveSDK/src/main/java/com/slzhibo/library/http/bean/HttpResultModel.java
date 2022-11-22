package com.slzhibo.library.http.bean;

/* loaded from: classes6.dex */
public class HttpResultModel<T> {
    private int code = 0;
    private T data = null;
    private String msg;
    private int status;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public boolean isSuccess() {
        return this.code == 100001 || this.status == 1;
    }

    public boolean isCallFail() {
        return this.code == 200009;
    }

    public String getMessage() {
        return this.msg;
    }

    public void setMessage(String str) {
        this.msg = str;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public int getCode() {
        return this.code;
    }
}
