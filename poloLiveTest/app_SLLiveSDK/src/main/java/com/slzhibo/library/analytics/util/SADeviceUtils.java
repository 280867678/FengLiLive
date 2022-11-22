package com.slzhibo.library.analytics.util;

import android.content.Context;
import android.text.TextUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class SADeviceUtils {
    private static final String TAG = "SA.DeviceUtils";
    private static CountDownLatch countDownLatch = null;
    private static String oaid = "";

    public static String getOAID(Context context) {
        try {
            countDownLatch = new CountDownLatch(1);
            if (!TextUtils.isEmpty(oaid)) {
                return oaid;
            }
            getOAIDReflect(context, 2);
            try {
                countDownLatch.await();
            } catch (InterruptedException unused) {
            }
            return oaid;
        } catch (Exception unused2) {
            return "";
        }
    }

    private static void getOAIDReflect(Context context, int i) {
        Class<?> cls;
        if (i != 0) {
            try {
                Class.forName("com.bun.miitmdid.core.JLibrary").getDeclaredMethod("InitEntry", Context.class).invoke(null, context);
                try {
                    cls = Class.forName("com.bun.miitmdid.core.IIdentifierListener");
                } catch (ClassNotFoundException unused) {
                    cls = Class.forName("com.bun.supplier.IIdentifierListener");
                }
                if (((Integer) Class.forName("com.bun.miitmdid.core.MdidSdkHelper").getDeclaredMethod("InitSdk", Context.class, Boolean.TYPE, cls).invoke(null, context, true, Proxy.newProxyInstance(context.getClassLoader(), new Class[]{cls}, new IdentifyListenerHandler()))).intValue() != 1008614) {
                    i--;
                    getOAIDReflect(context, i);
                    if (i == 0) {
                        countDownLatch.countDown();
                    }
                }
                new Thread(new Runnable() { // from class: com.slzhibo.library.analytics.util.SADeviceUtils.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            Thread.sleep(2000L);
                        } catch (InterruptedException unused2) {
                        }
                        SADeviceUtils.countDownLatch.countDown();
                    }
                }).start();
            } catch (Exception unused2) {
                int i2 = i - 1;
                getOAIDReflect(context, i2);
                if (i2 == 0) {
                    countDownLatch.countDown();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class IdentifyListenerHandler implements InvocationHandler {
        IdentifyListenerHandler() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            Class<?> cls;
            try {
                if (!"OnSupport".equals(method.getName())) {
                    return null;
                }
                if (((Boolean) objArr[0]).booleanValue()) {
                    try {
                        cls = Class.forName("com.bun.miitmdid.supplier.IdSupplier");
                    } catch (ClassNotFoundException unused) {
                        cls = Class.forName("com.bun.supplier.IdSupplier");
                    }
                    String unused2 = SADeviceUtils.oaid = (String) cls.getDeclaredMethod("getOAID", new Class[0]).invoke(objArr[1], new Object[0]);
                }
                SADeviceUtils.countDownLatch.countDown();
                return null;
            } catch (Exception unused3) {
                SADeviceUtils.countDownLatch.countDown();
                return null;
            }
        }
    }
}
