package com.blmvl.blvl.util;

//package p067e.p103c.p104a.p113k;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.blmvl.blvl.AppContext;
import com.blmvl.blvl.R;
import com.blmvl.blvl.activity.BuyMemberActivity;
import com.blmvl.blvl.activity.VideoDetailInfoActivity;
import com.blmvl.blvl.event.CurSelectVideoIdEvent;

import org.greenrobot.eventbus.EventBus;
//import com.blmvl.blvl.activity.CoinRechargeActivity;
//import com.blmvl.blvl.activity.PostVideoActivity;
//import com.blmvl.blvl.activity.UploadRuleAnswerActivity;
//import com.blmvl.blvl.activity.VideoDetailInfoActivity;
//import com.blmvl.blvl.activity.WebViewActivity;
//import com.blmvl.blvl.event.CurSelectVideoIdEvent;
//import p067e.p130f.p131a.p135d.IntentUtil;
//import p067e.p130f.p131a.p135d.ToastUtil;
//import p067e.p130f.p131a.p135d.WebViewUtil;
//import org1.greenrobot.p524a.EventBus;
//import us.ceubf.qeqeff.R;

/* renamed from: e.c.a.k.u */
/* loaded from: classes.dex */
public class JumpUtil {

    /* renamed from: a */
    public static JumpUtil f13057a;

    /* renamed from: a */
    public static JumpUtil m9417a() {
        if (f13057a == null) {
            synchronized (JumpUtil.class) {
                if (f13057a == null) {
                    f13057a = new JumpUtil();
                }
            }
        }
        return f13057a;
    }

    /* renamed from: a */
    public void m9413a(Context context, int i, String str) {
        try {
            if (i == 1 || i == 5) {
                if (!TextUtils.isEmpty(str)) {
//                    WebViewUtil.m9087a(context, str);
                    ToastUtil.m9102c(AppContext.m21299a(),"JumpUtil:WebViewUtil");
                }
            } else if (i == 3 || i == 0) {
                if (!TextUtils.isEmpty(str)) {
//                    WebViewActivity.m20642a(context, str);
                    ToastUtil.m9102c(AppContext.m21299a(),"JumpUtil:WebViewActivity");
                }
            } else if (i == 4) {
                m9412a(context, str);
            } else if (i == 6) {
                BuyMemberActivity.m21257a(context);
            } else if (i == 7) {
//                CoinRechargeActivity.m21240a(context);
                ToastUtil.m9102c(AppContext.m21299a(),"JumpUtil:CoinRechargeActivity");
            } else if (i == 8) {
//                GameJumpUtil.m9432a(context, Integer.parseInt(str));
                ToastUtil.m9102c(AppContext.m21299a(),"JumpUtil:GameJumpUtil");
            } else {
                ToastUtil.m9102c(context, context.getString(R.string.lower_version_tips));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m9412a(Context context, String str) {
        m9417a().m9414a(context, Integer.parseInt(str));
    }

//    /* renamed from: a */
//    public void m9415a(Context context) {
//        if (SpUtil.m9573D().m9575B()) {
//            PostVideoActivity.m20990a(context);
//        } else {
//            IntentUtil.m9081a(context, UploadRuleAnswerActivity.class);
//        }
//    }

    /* renamed from: a */
    public void m9414a(Context context, final int i) {
        try {
            VideoDetailInfoActivity.m20690a(context);
            new Handler().postDelayed(new Runnable() { // from class: e.c.a.k.e
                @Override // java.lang.Runnable
                public final void run() {
//                    EventBus.m309d().m320a(new CurSelectVideoIdEvent(i));
                    EventBus.getDefault().post(new CurSelectVideoIdEvent(i));
                }
            }, 400L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

