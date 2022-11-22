package com.slzhibo.library.http.bean;

import android.util.Log;

import com.slzhibo.library.http.utils.EncryptUtil;
import com.slzhibo.library.utils.CommonTransferUtils;

import java.io.Serializable;

/* loaded from: classes6.dex */
public class EncryptHttpResultModel implements Serializable {
    private int code = 0;
    private EncryptMode data=null;
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

    public String getMessage() {
        return this.msg;
    }

    public void setMessage(String str) {
        this.msg = str;
    }

    public int getCode() {
        return this.code;
    }

    public String getJsonData() {
        try {
            return this.data.getJsonData();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* loaded from: classes6.dex */
    public static class EncryptMode implements Serializable {
        public String data;
        public String key;

        public String getJsonData() throws Exception {
            Log.e("EncryptHttpResultModel：","解密密钥："+this.key);
            Log.e("EncryptHttpResultModel：","解密数据："+this.data);//+EncryptUtil.DESDecrypt(EncryptUtil.RSADecrypt(CommonTransferUtils.getSingleton().ENCRYPT_API_KEY, this.key), this.data));
            Log.e("EncryptHttpResultModel：","解密数据1："+EncryptUtil.DESDecrypt(EncryptUtil.RSADecrypt(CommonTransferUtils.getSingleton().ENCRYPT_API_KEY, this.key), this.data));

            return EncryptUtil.DESDecrypt(EncryptUtil.RSADecrypt(CommonTransferUtils.getSingleton().ENCRYPT_API_KEY, this.key), this.data);
        }
    }
}
