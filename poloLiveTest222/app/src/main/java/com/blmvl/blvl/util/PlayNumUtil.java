package com.blmvl.blvl.util;

//package p067e.p103c.p104a.p113k;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.blmvl.blvl.bean.AppUser;
import com.blmvl.blvl.bean.SubmitPlayDataResultBean;
import com.blmvl.blvl.bean.UserBean;
import com.blmvl.blvl.bean.VideoBean;
import com.blmvl.blvl.event.PlayNumChangeEvent;
import com.blmvl.blvl.httpUtil.HttpUtil;

import org.greenrobot.eventbus.EventBus;

//import p067e.p103c.p104a.p111i.HttpCallback;
//import p067e.p103c.p104a.p111i.HttpUtil;
//import org1.greenrobot.p524a.EventBus;

/* renamed from: e.c.a.k.x */
/* loaded from: classes.dex */
public class PlayNumUtil {

    /* renamed from: a */
    public static volatile PlayNumUtil f13058a;

    /* compiled from: PlayNumUtil.java */
    /* renamed from: e.c.a.k.x$a */
    /* loaded from: classes.dex */
    public class C4248a extends HttpCallback {

        /* renamed from: a */
        public final /* synthetic */ AbstractC4249b f13059a;

        public C4248a(PlayNumUtil xVar, AbstractC4249b bVar) {
            this.f13059a = bVar;
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onError() {
            super.onError();
            AbstractC4249b bVar = this.f13059a;
            if (bVar != null) {
                bVar.m9396a();
            }
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onException(int i, String str) {
            super.onException(i, str);
            AbstractC4249b bVar = this.f13059a;
            if (bVar != null) {
                bVar.m9396a();
            }
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onNetworkError() {
            super.onNetworkError();
            AbstractC4249b bVar = this.f13059a;
            if (bVar != null) {
                bVar.m9396a();
            }
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onSuccess(String str, String str2, boolean z, boolean z2) {
            super.onSuccess(str, str2, z, z2);
            try {
                SubmitPlayDataResultBean submitPlayDataResultBean = JSON.parseObject(str, SubmitPlayDataResultBean.class);
                int left = submitPlayDataResultBean.getLeft();
                int canWatchCount = submitPlayDataResultBean.getCanWatchCount();
                if (left >= 0) {
                    SpUtil.m9573D().m9553c(left);
                }
                if (canWatchCount > 0) {
                    SpUtil.m9573D().m9548d(canWatchCount);
                }
                long timestamp = submitPlayDataResultBean.getTimestamp();
                if (SpUtil.m9573D().m9517w() != timestamp) {
                    SpUtil.m9573D().m9561b(timestamp);
                    SpUtil.m9573D().m9549d();
                    SpUtil.m9573D().m9545e();
                    SpUtil.m9573D().m9554c();
                }
                if (this.f13059a != null) {
                    this.f13059a.m9396a();
                }
            } catch (Exception e) {
                e.printStackTrace();
                AbstractC4249b bVar = this.f13059a;
                if (bVar != null) {
                    bVar.m9396a();
                }
            }
        }
    }

    /* compiled from: PlayNumUtil.java */
    /* renamed from: e.c.a.k.x$b */
    /* loaded from: classes.dex */
    public interface AbstractC4249b {
        /* renamed from: a */
        void m9396a();
    }

    public PlayNumUtil() {
        m9401a();
    }

    /* renamed from: b */
    public static PlayNumUtil m9398b() {
        if (f13058a == null) {
            synchronized (PlayNumUtil.class) {
                if (f13058a == null) {
                    f13058a = new PlayNumUtil();
                }
            }
        }
        return f13058a;
    }

    /* renamed from: a */
    public final void m9401a() {
    }

    /* renamed from: a */
    public void m9400a(VideoBean videoBean) {
        try {
            UserBean user = AppUser.getInstance().getUser();
            if (user.getUid() != videoBean.getUser().getUid()) {
                String valueOf = String.valueOf(videoBean.getId());
                SpUtil.m9573D().m9547d(valueOf);
                if (SpUtil.m9573D().m9551c(valueOf, user.getIs_vip() == 1) && user.getIs_vip() != 1) {
//                    EventBus.m309d().m320a(new PlayNumChangeEvent());
                    EventBus.getDefault().post(new PlayNumChangeEvent());
                }
                m9399a(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public boolean m9397b(VideoBean videoBean) {
        if (videoBean == null) {
            return false;
        }
        try {
            UserBean user = AppUser.getInstance().getUser();
            long w = SpUtil.m9573D().m9517w();
            long j = SpUtil.m9573D().m9533j();
            int uid = videoBean.getUser().getUid();
            int id = videoBean.getId();
            if (uid == user.getUid()) {
                return true;
            }
            return videoBean.getCoins() > 0 ? videoBean.getIs_pay() == 1 : user.getIs_vip() == 1 || j > w || SpUtil.m9573D().m9518v().contains(String.valueOf(id)) || SpUtil.m9573D().m9529l() > 0;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    public void m9399a(AbstractC4249b bVar) {
        try {
            String h = SpUtil.m9573D().m9537h();
            if (!TextUtils.isEmpty(h)) {
                HttpUtil.m9694e(SpUtil.m9573D().m9516x(), h, new C4248a(this, bVar));
            } else if (bVar != null) {
                bVar.m9396a();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (bVar != null) {
                bVar.m9396a();
            }
        }
    }
}

