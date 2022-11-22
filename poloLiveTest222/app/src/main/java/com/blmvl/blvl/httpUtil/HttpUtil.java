package com.blmvl.blvl.httpUtil;

//package p067e.p103c.p104a.p111i;

import android.content.Context;
import androidx.core.app.NotificationCompat;
//import android.support.transition.Transition;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blmvl.blvl.AppContext;
import com.blmvl.blvl.L;
import com.blmvl.blvl.R;
import com.blmvl.blvl.bean.AppUser;
import com.blmvl.blvl.bean.ConfigBean;
import com.blmvl.blvl.bean.ResponseJsonBean;
import com.blmvl.blvl.bean.UploadVideoBean;
import com.blmvl.blvl.bean.UserBean;
//import com.blmvl.blvl.event.FollowEvent;
//import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;
//import com.luck.picture.lib.config.PictureConfig;
import com.blmvl.blvl.event.FollowEvent;
import com.blmvl.blvl.util.CommonCallback;
import com.blmvl.blvl.util.CustomWordUtil;
import com.blmvl.blvl.util.HttpCallback;
import com.blmvl.blvl.util.SpUtil;
import com.blmvl.blvl.util.ToastUtil;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.PostRequest;
import com.slzhibo.library.SLLiveSDK;
import com.slzhibo.library.model.UserEntity;
import com.slzhibo.library.utils.UserInfoManager;
import com.slzhibo.library.SLLiveSDK;
import com.slzhibo.library.model.UserEntity;
import com.slzhibo.library.utils.UserInfoManager;
//import com.slzhibo.library.utils.litepal.parser.LitePalParser;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;
//import p067e.p103c.p104a.p108f.LikedPromptDialog;
//import p067e.p103c.p104a.p110h.CommonCallback;
//import p067e.p103c.p104a.p113k.CustomWordUtil;
//import p067e.p103c.p104a.p113k.DialogUtil;
//import p067e.p103c.p104a.p113k.SpUtil;
//import p067e.p130f.p131a.p135d.L;
//import p067e.p130f.p131a.p135d.ToastUtil;
//import p067e.p130f.p131a.p135d.VersionUtil;
//import org1.greenrobot.p524a.EventBus;
//import us.ceubf.qeqeff.R;

/* renamed from: e.c.a.i.d */
/* loaded from: classes.dex */
public class HttpUtil {

    /* compiled from: HttpUtil.java */
    /* renamed from: e.c.a.i.d$a */
    /* loaded from: classes.dex */
    public static class C4221a extends HttpCallback {

        /* renamed from: a */
        public final /* synthetic */ CommonCallback f12975a;

        public C4221a(CommonCallback aVar) {
            this.f12975a = aVar;
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onError() {
            CommonCallback aVar = this.f12975a;
            if (aVar != null) {
                aVar.mo9799a(null);
            }
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onException(int i, String str) {
            super.onException(i, str);
            CommonCallback aVar = this.f12975a;
            if (aVar != null) {
                aVar.mo9799a(null);
            }
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onNetworkError() {
            super.onNetworkError();
            CommonCallback aVar = this.f12975a;
            if (aVar != null) {
                aVar.mo9799a(null);
            }
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onSuccess(String str, String str2, boolean z, boolean z2) {
            try {
                SpUtil.m9573D().m9552c(str);
                JSONObject parseObject = JSON.parseObject(str);
                if (parseObject != null) {
                    SpUtil.m9573D().m9571a(parseObject.getIntValue("watch_is_fee_count"));
                    int intValue = parseObject.getIntValue("watch_count");
                    if (intValue > 0) {
                        SpUtil.m9573D().m9548d(intValue);
                    }
                    long longValue = parseObject.getLongValue("timestamp");
                    long w = SpUtil.m9573D().m9517w();
                    SpUtil.m9573D().m9530k(parseObject.getString("feature_page_ads"));
                    if (longValue != w) {
                        SpUtil.m9573D().m9561b(longValue);
                        SpUtil.m9573D().m9549d();
                        SpUtil.m9573D().m9554c();
                    }
                    SpUtil.m9573D().m9562b(parseObject.getIntValue("game_bottom_nav_show"));
                }
                ConfigBean configBean = JSON.parseObject(str, ConfigBean.class);
                if (configBean != null && !TextUtils.isEmpty(configBean.getSpareUrls())) {
                    SpUtil.m9573D().m9534i(configBean.getSpareUrls());
                }
                if (this.f12975a != null) {
                    this.f12975a.mo9799a(configBean);
                }
            } catch (Exception e) {
                e.printStackTrace();
                CommonCallback aVar = this.f12975a;
                if (aVar != null) {
                    aVar.mo9799a(null);
                }
            }
        }
    }

    /* compiled from: HttpUtil.java */
    /* renamed from: e.c.a.i.d$b */
    /* loaded from: classes.dex */
    public static class C4222b extends HttpCallback {

        /* renamed from: a */
        public final /* synthetic */ CommonCallback f12976a;

        public C4222b(CommonCallback aVar) {
            this.f12976a = aVar;
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onError() {
            super.onError();
            CommonCallback aVar = this.f12976a;
            if (aVar != null) {
                aVar.mo9799a(null);
            }
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onException(int i, String str) {
            super.onException(i, str);
            CommonCallback aVar = this.f12976a;
            if (aVar != null) {
                aVar.mo9799a(null);
            }
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onNetworkError() {
            super.onNetworkError();
            CommonCallback aVar = this.f12976a;
            if (aVar != null) {
                aVar.mo9799a(null);
            }
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onSuccess(String str, String str2, boolean z, boolean z2) {
            super.onSuccess(str, str2, z, z2);
            try {
                SpUtil.m9573D().m9532j(str);
                JSONObject parseObject = JSON.parseObject(str);
                if (parseObject != null) {
                    int intValue = parseObject.getIntValue("can_watch");
                    if (intValue >= 0) {
                        SpUtil.m9573D().m9553c(intValue);
                    }
                    long longValue = parseObject.getLongValue("expired_at");
                    if (longValue > 0) {
                        SpUtil.m9573D().m9570a(longValue);
                    }
                    SpUtil.m9573D().m9564a(parseObject.getBooleanValue("isCanWatchLive"));
                    SpUtil.m9573D().m9550c(parseObject.getBooleanValue("isRealUser"));
                }
                UserBean userBean = JSON.parseObject(str, UserBean.class);
                if (userBean != null) {
                    if (this.f12976a != null) {
                        this.f12976a.mo9799a(userBean);
                        HttpUtil.m9706d(str);
                    }
                } else if (this.f12976a != null) {
                    this.f12976a.mo9799a(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* compiled from: HttpUtil.java */
    /* renamed from: e.c.a.i.d$c */
    /* loaded from: classes.dex */
    public static class C4223c implements SLLiveSDK.LiveSDKLoginCallbackListener {
        @Override // com.slzhibo.library.SLLiveSDK.LiveSDKLoginCallbackListener
        public void onLoginFailListener(Context context) {
            L.m9079a("---------onLoginFail---------");
        }

        @Override // com.slzhibo.library.SLLiveSDK.LiveSDKLoginCallbackListener
        public void onLoginSuccessListener(Context context) {
            L.m9079a("---------onLoginSuccess---------");
        }
    }

    /* compiled from: HttpUtil.java */
    /* renamed from: e.c.a.i.d$d */
    /* loaded from: classes.dex */
    public static class C4224d extends HttpCallback {

        /* renamed from: a */
        public final /* synthetic */ int f12977a;

        /* renamed from: b */
        public final /* synthetic */ CommonCallback f12978b;

        public C4224d(int i, CommonCallback aVar) {
            this.f12977a = i;
            this.f12978b = aVar;
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onException(int i, String str) {
            super.onException(i, str);
            if (!TextUtils.isEmpty(str)) {
                ToastUtil.m9102c(AppContext.m21299a(), str);
            }
        }

        @Override // p067e.p103c.p104a.p111i.HttpCallback
        public void onSuccess(String str, String str2, boolean z, boolean z2) {
            try {
                JSONObject parseObject = JSON.parseObject(str);
                int intValue = parseObject.getIntValue("is_attention");
                String string = parseObject.getString(NotificationCompat.CATEGORY_MESSAGE);
                if (!TextUtils.isEmpty(string)) {
                    ToastUtil.m9102c(AppContext.m21299a(), string);
                }
//                EventBus.m309d().m320a(new FollowEvent(this.f12977a, intValue));
                EventBus.getDefault().post(new FollowEvent(this.f12977a, intValue));
                if (this.f12978b != null) {
                    this.f12978b.mo9799a(Integer.valueOf(intValue));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    public static void m9735b(String str) {
        C4220b.m9794b().m9797a(str);
    }

    /* renamed from: c */
    public static HttpParams m9719c(String str) {
        HttpParams a = m9789a();
        a.put("type", str);
        m9767a(a);
        return a;
    }

    /* renamed from: d */
    public static void m9706d(String str) {
        try {
            JSONObject parseObject = JSON.parseObject(str);
            String string = parseObject.getString("zbToken");
            String string2 = parseObject.getString("zbUid");
            UserBean userBean = JSON.parseObject(str, UserBean.class);
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(string2);
            userEntity.setName(userBean.getNickname());
            userEntity.setAvatar(userBean.getAvatar_url());
            userEntity.setToken(string);
            userEntity.setIsRisk("0");
            userEntity.setSex(String.valueOf(userBean.getSexType()));
            UserInfoManager.getInstance().loadUserInfo(userEntity);
            SLLiveSDK.getSingleton().loginSDK(AppContext.m21299a(), new C4223c());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: e */
    public static HttpParams m9700e() {
        HttpParams a = m9789a();
        m9767a(a);
        return a;
    }

    /* renamed from: f */
    public static void m9689f(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/videoincome/hottest", "getHotWorkIncomeList").params(m9679h(i)).execute(aVar);
    }

    /* renamed from: g */
    public static void m9683g(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/videoincome/newest", "getLatestWorkIncomeList").params(m9675i(i)).execute(aVar);
    }

    /* renamed from: h */
    public static void m9677h(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/users/creatorStudy", "getCreateCourse");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: i */
    public static HttpParams m9675i(int i) {
        HttpParams a = m9789a();
        a.put("page", i);
        m9767a(a);
        return a;
    }

    /* renamed from: j */
    public static HttpParams m9672j(int i) {
        HttpParams a = m9789a();
        a.put("page", i);
        m9767a(a);
        return a;
    }

    /* renamed from: k */
    public static void m9668k(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/usertopic/toggle_like", "likeVideoCollectAction").params(m9660n(i)).execute(aVar);
    }

    /* renamed from: l */
    public static HttpParams m9666l(int i) {
        HttpParams a = m9789a();
        a.put("to_uid", i);
        m9767a(a);
        return a;
    }

    /* renamed from: m */
    public static void m9661m(HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/product/list", "getSystemNoticeList").params(m9691f()).execute(aVar);
    }

    /* renamed from: n */
    public static void m9658n(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/mv/preUpload", "prePostVideo");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: o */
    public static void m9655o(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/users/yqzq", "getPromoteEarnData");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: p */
    public static HttpParams m9654p(int i) {
        HttpParams a = m9789a();
        a.put("to_uid", i);
        m9767a(a);
        return a;
    }

    /* renamed from: q */
    public static void m9649q(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/search/conf", "getSearchNormalInfo");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: r */
    public static HttpParams m9648r(int i) {
        HttpParams a = m9789a();
        a.put("id", i);
        m9767a(a);
        return a;
    }

    /* renamed from: s */
    public static HttpParams m9646s(int i) {
        HttpParams a = m9789a();
        a.put("id", i);
        m9767a(a);
        return a;
    }

    /* renamed from: t */
    public static void m9643t(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/Topcreator/recommend", "get_video_creator_recommend");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: u */
    public static void m9642u(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/topvideo/commentTop1", "getVideoHotTalk");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: v */
    public static void m9641v(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/videoIncome/videoProfit", "getFollowList");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: w */
    public static void m9640w(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/creator/preCheck", "getVideoMakerConditions");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: x */
    public static void m9639x(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/creator/info", "getVideoMakerHomePageInfo");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: y */
    public static void m9638y(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/p/getAccount", "getWithdrawAccount");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9789a() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("oauth_type", "android");
        httpParams.put("oauth_id", SpUtil.m9573D().m9535i());
        httpParams.put("version", VersionUtil.m9100a(AppContext.m21299a()));
        if (AppUser.getInstance().getUser() == null || TextUtils.isEmpty(AppUser.getInstance().getUser().getToken())) {
            httpParams.put("token", "");
        } else {
            httpParams.put("token", AppUser.getInstance().getUser().getToken());
        }
        httpParams.put("new_player", "fx");
        httpParams.put("bundle_id", AppContext.m21299a().getPackageName());
        return httpParams;
    }

    /* renamed from: b */
    public static HttpParams m9745b() {
        HttpParams a = m9789a();
        m9767a(a);
        return a;
    }

    /* renamed from: e */
    public static void m9697e(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/ChargeVideo/index", "getCoinVideoBaseData");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: b */
    public static void m9737b(CommonCallback<UserBean> aVar) {
        C4220b.m9794b().m9793b("/api/users/getBaseInfo", "getBaseInfo").params(m9745b()).execute(new C4222b(aVar));
    }

    /* renamed from: c */
    public static HttpParams m9725c() {
        HttpParams a = m9789a();
        a.put("type", 2);
        m9767a(a);
        return a;
    }

    /* renamed from: f */
    public static HttpParams m9691f() {
        HttpParams a = m9789a();
        a.put("type", 1);
        m9767a(a);
        return a;
    }

    /* renamed from: g */
    public static HttpParams m9681g(String str) {
        HttpParams a = m9789a();
        a.put("aff", str);
        m9767a(a);
        return a;
    }

    /* renamed from: h */
    public static HttpParams m9679h(int i) {
        HttpParams a = m9789a();
        a.put("page", i);
        m9767a(a);
        return a;
    }

    /* renamed from: i */
    public static void m9674i(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/users/getUserHome", "get_user_info").params(m9666l(i)).execute(aVar);
    }

    /* renamed from: j */
    public static void m9671j(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/usertopic/listmvOfTopic", "getVideoCollectDetail").params(m9663m(i)).execute(aVar);
    }

    /* renamed from: k */
    public static HttpParams m9669k(int i) {
        HttpParams a = m9789a();
        a.put("mv_id", i);
        m9767a(a);
        return a;
    }

    /* renamed from: l */
    public static void m9665l(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/works/upShelves", "onShelf").params(m9657o(i)).execute(aVar);
    }

    /* renamed from: m */
    public static void m9662m(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/works/downShelves", "removeShelf").params(m9651q(i)).execute(aVar);
    }

    /* renamed from: n */
    public static void m9659n(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/system/appclick", "appDownloadClick").params(m9648r(i)).execute(aVar);
    }

    /* renamed from: o */
    public static HttpParams m9657o(int i) {
        HttpParams a = m9789a();
        a.put("mv_id", i);
        m9767a(a);
        return a;
    }

    /* renamed from: p */
    public static void m9652p(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/comments/saoTalk", "getSaoTalkList");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: q */
    public static HttpParams m9651q(int i) {
        HttpParams a = m9789a();
        a.put("mv_id", i);
        m9767a(a);
        return a;
    }

    /* renamed from: r */
    public static void m9647r(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/tab/category", "getSortTag");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: s */
    public static void m9645s(HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/mv/uploadAnswer", "getUploadRuleAnswer").params(m9685g()).execute(aVar);
    }

    /* renamed from: t */
    public static HttpParams m9644t(int i) {
        HttpParams a = m9789a();
        a.put("topic_id", i);
        m9767a(a);
        return a;
    }

    /* renamed from: e */
    public static HttpParams m9695e(String str, String str2) {
        HttpParams a = m9789a();
        a.put("nickname", str);
        a.put("person_signnatrue", str2);
        m9767a(a);
        return a;
    }

    /* renamed from: b */
    public static void m9727b(String str, String str2, String str3, int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b(str, str).params(m9775a(i, str2, str3)).execute(aVar);
    }

    /* renamed from: c */
    public static void m9717c(String str, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/system/exchange", "writeExchangeCode").params(m9687f(str)).execute(aVar);
    }

    /* renamed from: f */
    public static HttpParams m9687f(String str) {
        HttpParams a = m9789a();
        a.put("code", str);
        m9767a(a);
        return a;
    }

    /* renamed from: g */
    public static void m9682g(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/users/getSMSCountry", "get_country_code");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: h */
    public static void m9678h(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/proxy/userMoney", "getPromoteIncomeInfo").params(m9672j(i)).execute(aVar);
    }

    /* renamed from: i */
    public static void m9673i(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/game/productList", "getGameProduct");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: j */
    public static void m9670j(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/game/drawConf", "getGameWithdrawInfo");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: k */
    public static void m9667k(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/home/verifyUrl", "getGraphVerifyCode");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: l */
    public static void m9664l(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/tab/index", "getHomePageSortInfo");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: m */
    public static HttpParams m9663m(int i) {
        HttpParams a = m9789a();
        a.put("topic_id", i);
        m9767a(a);
        return a;
    }

    /* renamed from: n */
    public static HttpParams m9660n(int i) {
        HttpParams a = m9789a();
        a.put("topic_id", i);
        m9767a(a);
        return a;
    }

    /* renamed from: o */
    public static void m9656o(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/mv/toggleTop", "toggleTop").params(m9646s(i)).execute(aVar);
    }

    /* renamed from: p */
    public static void m9653p(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/usertopic/toggle_top", "topVideoCollectAction").params(m9644t(i)).execute(aVar);
    }

    /* renamed from: q */
    public static void m9650q(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/userbuy/checkByTicket", "ticketBuyCoinVideo").params(m9669k(i)).execute(aVar);
    }

    /* renamed from: b */
    public static HttpParams m9728b(String str, String str2, String str3) {
        HttpParams a = m9789a();
        a.put("mobile_prefix", str);
        a.put("phone", str2);
        a.put("verify_code", str3);
        m9767a(a);
        return a;
    }

    /* renamed from: c */
    public static void m9713c(String str, String str2, String str3, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/users/sms", "getFindPwdCode").params(m9728b(str, str2, str3)).execute(aVar);
    }

    /* renamed from: e */
    public static HttpParams m9696e(String str) {
        HttpParams a = m9789a();
        a.put("thumb", str);
        m9767a(a);
        return a;
    }

    /* renamed from: f */
    public static void m9686f(String str, String str2, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/users/login", "switch_account").params(m9716c(str, str2)).execute(aVar);
    }

    /* renamed from: g */
    public static void m9680g(String str, String str2, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/users/personal", "updateUserInfo").params(m9695e(str, str2)).execute(aVar);
    }

    /* renamed from: h */
    public static void m9676h(String str, String str2, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/users/hasPhone", "verify_phone_num").params(m9704d(str, str2)).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9767a(HttpParams httpParams) {
        try {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry<String, List<String>> entry : httpParams.urlParamsMap.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue().get(0));
            }
            httpParams.urlParamsMap.clear();
            JSONObject parseObject = JSON.parseObject(HttpParamUtil.m9791a(jSONObject.toJSONString()));
            httpParams.put("timestamp", parseObject.getString("timestamp"));
            httpParams.put(CacheEntity.DATA, parseObject.getString(CacheEntity.DATA));
            httpParams.put("sign", parseObject.getString("sign"));
            L.m9079a("請求參數加密後=====" + httpParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpParams;
    }

    /* renamed from: c */
    public static HttpParams m9716c(String str, String str2) {
        HttpParams a = m9789a();
        a.put("phone", str);
        a.put("password", str2);
        m9767a(a);
        return a;
    }

    /* renamed from: e */
    public static void m9694e(String str, String str2, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/mv/watching", "submit_play_data").params(m9761a(str, SpUtil.m9573D().m9517w(), str2)).execute(aVar);
    }

    /* renamed from: f */
    public static void m9688f(HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/mv/report_type", "complaintOptionsList").params(m9712d()).execute(aVar);
    }

    /* renamed from: g */
    public static HttpParams m9685g() {
        HttpParams a = m9789a();
        m9767a(a);
        return a;
    }

    /* renamed from: b */
    public static void m9729b(String str, String str2, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/users/login_account", "loginAction").params(m9759a(str, str2)).execute(aVar);
    }

    /* renamed from: d */
    public static void m9707d(HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/product/list", "getCoinRechargeList").params(m9725c()).execute(aVar);
    }

    /* renamed from: g */
    public static HttpParams m9684g(int i) {
        HttpParams a = m9789a();
        a.put("id", i);
        m9767a(a);
        return a;
    }

    /* renamed from: f */
    public static HttpParams m9690f(int i) {
        HttpParams a = m9789a();
        a.put("page", i);
        a.put("limit", 24);
        m9767a(a);
        return a;
    }

    /* renamed from: c */
    public static HttpParams m9714c(String str, String str2, String str3) {
        HttpParams a = m9789a();
        a.put("phone", str);
        a.put("password", str2);
        a.put("identify", str3);
        m9767a(a);
        return a;
    }

    /* renamed from: e */
    public static HttpParams m9699e(int i) {
        HttpParams a = m9789a();
        a.put("id", i);
        m9767a(a);
        return a;
    }

    /* renamed from: b */
    public static void m9732b(String str, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/users/thumb", "updateAvatar").params(m9696e(str)).execute(aVar);
    }

    /* renamed from: d */
    public static void m9705d(String str, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/users/invitation", "writeInviteCode").params(m9681g(str)).execute(aVar);
    }

    /* renamed from: e */
    public static void m9698e(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/game/enter", "getGameInfo").params(m9684g(i)).execute(aVar);
    }

    /* renamed from: b */
    public static void m9730b(String str, String str2, int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/helper/feedSave", "onlineFeedback").params(m9758a(str, str2, i)).execute(aVar);
    }

    /* renamed from: d */
    public static void m9709d(int i, int i2, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/videoIncome/videoIncomeList", "getVideoIncomeDetail").params(m9710d(i, i2)).execute(aVar);
    }

    /* renamed from: c */
    public static void m9722c(int i, int i2, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/comments/list", "getVideoCommentList").params(m9723c(i, i2)).execute(aVar);
    }

    /* renamed from: a */
    public static void m9766a(CommonCallback<ConfigBean> aVar) {
        C4220b.m9794b().m9793b("/api/home/getConfig", "getConfigInfo").params(m9700e()).execute(new C4221a(aVar));
    }

    /* renamed from: e */
    public static void m9692e(String str, String str2, String str3, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/game/transAccount", "transfer_balance").params(m9693e(str, str2, str3)).execute(aVar);
    }

    /* renamed from: b */
    public static void m9741b(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/Chargevideo/buy", "get_coin_video_info").params(m9699e(i)).execute(aVar);
    }

    /* renamed from: d */
    public static HttpParams m9710d(int i, int i2) {
        HttpParams a = m9789a();
        a.put("page", i2);
        a.put("vid", i);
        m9767a(a);
        return a;
    }

    /* renamed from: c */
    public static HttpParams m9723c(int i, int i2) {
        HttpParams a = m9789a();
        a.put("id", i);
        a.put("page", i2);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static void m9783a(int i, CommonCallback<Integer> aVar) {
        if (i == AppUser.getInstance().getUser().getUid()) {
            ToastUtil.m9102c(AppContext.m21299a(), CustomWordUtil.m9463a(R.string.cannot_follow_self));
        } else {
            C4220b.m9794b().m9793b("/api/users/following", "addAttention").params(m9654p(i)).execute(new C4224d(i, aVar));
        }
    }

    /* renamed from: e */
    public static HttpParams m9693e(String str, String str2, String str3) {
        HttpParams a = m9789a();
        a.put("from_account", str);
        a.put("to_account", str2);
        a.put("account_value", str3);
        m9767a(a);
        return a;
    }

    /* renamed from: b */
    public static HttpParams m9744b(int i) {
        HttpParams a = m9789a();
        a.put("id", i);
        m9767a(a);
        return a;
    }

    /* renamed from: d */
    public static void m9701d(String str, String str2, String str3, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/users/sms", "getRegisterCode").params(m9702d(str, str2, str3)).execute(aVar);
    }

    /* renamed from: c */
    public static HttpParams m9724c(int i) {
        HttpParams a = m9789a();
        a.put("id", i);
        m9767a(a);
        return a;
    }

    /* renamed from: b */
    public static void m9736b(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/system/appcenter", "getAppCenter");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: a */
    public static void m9756a(String str, String str2, HttpCallback aVar) {
        C4220b.m9794b().m9793b(str, str).params(m9719c(str2)).execute(aVar);
    }

    /* renamed from: d */
    public static HttpParams m9702d(String str, String str2, String str3) {
        HttpParams a = m9789a();
        a.put("mobile_prefix", str);
        a.put("phone", str2);
        a.put("verify_code", str3);
        m9767a(a);
        return a;
    }

    /* renamed from: c */
    public static void m9715c(String str, String str2, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/users/changePassword", "modPwd").params(m9731b(str, str2)).execute(aVar);
    }

    /* renamed from: b */
    public static void m9738b(long j, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/mvnew/detail", "getVideoDetail").params(m9739b(j)).execute(aVar);
    }

    /* renamed from: a */
    public static void m9760a(String str, HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b(str, str);
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: c */
    public static void m9718c(String str, int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/mv/report_push", "submitComplaintOptions").params(m9734b(str, i)).execute(aVar);
    }

    /* renamed from: b */
    public static HttpParams m9739b(long j) {
        HttpParams a = m9789a();
        a.put("id", j);
        m9767a(a);
        return a;
    }

    /* renamed from: d */
    public static HttpParams m9704d(String str, String str2) {
        HttpParams a = m9789a();
        a.put("mobile_prefix", str);
        a.put("phone", str2);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static void m9773a(int i, String str, String str2, String str3, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/Api/p/createP", "getPayLink").params(m9774a(i, str, str2, str3)).execute(aVar);
    }

    /* renamed from: c */
    public static void m9721c(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/usertopic/delete", "delVideoCollectAction").params(m9788a(i)).execute(aVar);
    }

    /* renamed from: b */
    public static HttpParams m9731b(String str, String str2) {
        HttpParams a = m9789a();
        a.put("oldPassword", str);
        a.put("password", str2);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static HttpParams m9774a(int i, String str, String str2, String str3) {
        HttpParams a = m9789a();
        a.put("product_id", i);
        a.put("pt", str);
        a.put("pw", str2);
        a.put("is_sdk", "0");
        a.put("verify_code", str3);
        m9767a(a);
        return a;
    }

    /* renamed from: d */
    public static void m9703d(String str, String str2, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/users/register_account", "registerAction").params(m9759a(str, str2)).execute(aVar);
    }

    /* renamed from: c */
    public static void m9720c(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/game/transInfo", "getBalanceInfo");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: b */
    public static HttpParams m9734b(String str, int i) {
        HttpParams a = m9789a();
        a.put("content", str);
        a.put("mv_id", i);
        m9767a(a);
        return a;
    }

    /* renamed from: d */
    public static HttpParams m9711d(int i) {
        HttpParams a = m9789a();
        a.put("id", i);
        m9767a(a);
        return a;
    }

    /* renamed from: d */
    public static void m9708d(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/p/delAccount", "delWithdrawAccount").params(m9744b(i)).execute(aVar);
    }

    /* renamed from: a */
    public static void m9757a(String str, String str2, int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b(str, str).params(m9781a(i, str2)).execute(aVar);
    }

    /* renamed from: b */
    public static void m9742b(int i, int i2, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/topic/mv", "getVideoCollect").params(m9743b(i, i2)).execute(aVar);
    }

    /* renamed from: d */
    public static HttpParams m9712d() {
        HttpParams a = m9789a();
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static HttpParams m9781a(int i, String str) {
        HttpParams a = m9789a();
        a.put("page", i);
        a.put("kwy", str);
        m9767a(a);
        return a;
    }

    /* renamed from: b */
    public static HttpParams m9743b(int i, int i2) {
        HttpParams a = m9789a();
        a.put("topic_id", i);
        a.put("page", i2);
        a.put("limit", 24);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static HttpParams m9775a(int i, String str, String str2) {
        HttpParams a = m9789a();
        a.put("page", i);
        a.put("uid", str);
        a.put("kwy", str2);
        m9767a(a);
        return a;
    }

    /* renamed from: b */
    public static void m9733b(String str, int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/usertopic/listmv", "getForSelectVideoList").params(m9763a(str, i)).execute(aVar);
    }

    /* renamed from: b */
    public static HttpParams m9740b(int i, String str) {
        HttpParams a = m9789a();
        a.put("topic_id", i);
        a.put("mv_id", str);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static void m9746a(String str, String str2, String str3, String str4, String str5, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/users/register", "register").params(m9747a(str, str2, str3, str4, str5)).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9747a(String str, String str2, String str3, String str4, String str5) {
        HttpParams a = m9789a();
        a.put("mobile_prefix", str);
        a.put("phone", str2);
        a.put("identify", str3);
        a.put("password", str4);
        a.put("aff", str5);
        m9767a(a);
        return a;
    }

    /* renamed from: b */
    public static void m9726b(String str, String str2, String str3, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/game/draw", "gameWithdraw").params(m9755a(str, str2, str3)).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9759a(String str, String str2) {
        HttpParams a = m9789a();
        a.put("username", str);
        a.put("password", str2);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static void m9752a(String str, String str2, String str3, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/users/findPassword", "findPwd").params(m9714c(str, str2, str3)).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9758a(String str, String str2, int i) {
        HttpParams a = m9789a();
        a.put("thumb", str);
        a.put("content", str2);
        a.put("platform", i);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static void m9786a(int i, int i2, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/helper/feedList", "onlineFeedbackList").params(m9787a(i, i2)).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9787a(int i, int i2) {
        HttpParams a = m9789a();
        a.put("page", i);
        a.put("platform", i2);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
//    public static void m9770a(Context context, int i, int i2, HttpCallback aVar) {
//        if (i == 1) {
//            try {
//                if (!SpUtil.m9573D().m9531k()) {
//                    DialogUtil.m9457a(context, new LikedPromptDialog(context));
//                    SpUtil.m9573D().m9543e(true);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                return;
//            }
//        }
//        ((PostRequest) C4220b.m9794b().m9793b("/api/mv/liking", "addLike").params(m9711d(i2))).execute(aVar);
//    }

    /* renamed from: a */
    public static void m9768a(UploadVideoBean uploadVideoBean, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/mv/upload", "postVideo").params(m9769a(uploadVideoBean)).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9769a(UploadVideoBean uploadVideoBean) {
        HttpParams a = m9789a();
        a.put("title", uploadVideoBean.title);
        a.put("img_url", uploadVideoBean.thumbUrl);
        a.put("url", uploadVideoBean.videoUrl);
        a.put("tags", uploadVideoBean.tags);
        a.put("thumb_height", uploadVideoBean.thumbHeight);
        a.put("thumb_width", uploadVideoBean.thumbWidth);
        a.put("coins", uploadVideoBean.coins);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static void m9782a(int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/comments/liking", "addCommentLike").params(m9724c(i)).execute(aVar);
    }

    /* renamed from: a */
    public static void m9777a(int i, String str, int i2, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/comments/create", "addComment").params(m9780a(i, str, i2)).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9780a(int i, String str, int i2) {
        HttpParams a = m9789a();
        a.put("mv_id", i);
        a.put("c_id", 0);
        if (i2 > 0) {
            a.put("s_id", i2);
        }
        a.put("comment", str);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static void m9778a(int i, String str, int i2, int i3, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/comments/create", "setComment").params(m9779a(i, str, i2, i3)).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9779a(int i, String str, int i2, int i3) {
        HttpParams a = m9789a();
        a.put("mv_id", i);
        a.put("c_id", i2);
        if (i3 > 0) {
            a.put("s_id", i3);
        }
        a.put("comment", str);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static HttpParams m9761a(String str, long j, String str2) {
        HttpParams a = m9789a();
        a.put("timestamp", j);
        a.put("id_log", str2);
        a.put("log", str);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static void m9753a(String str, String str2, String str3, int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/p/addAccount", "addWithdrawAccount").params(m9754a(str, str2, str3, i)).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9754a(String str, String str2, String str3, int i) {
        HttpParams a = m9789a();
        a.put("account", str);
        a.put("name", str2);
        a.put("account_bank", str3);
        a.put("type", i);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static void m9784a(int i, int i2, String str, String str2, String str3, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/proxy/withdraw", "withdrawAction").params(m9785a(i, i2, str, str2, str3)).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9785a(int i, int i2, String str, String str2, String str3) {
        HttpParams a = m9789a();
        a.put("withdraw_from", i);
        a.put("withdraw_type", i2);
        a.put("withdraw_account", str);
        a.put("withdraw_name", str2);
        a.put("withdraw_amount", str3);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static void m9771a(long j, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/mv/long", "getLongDetail").params(m9772a(j)).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9772a(long j) {
        HttpParams a = m9789a();
        a.put("id", j);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static void m9765a(HttpCallback aVar) {
        PostRequest<ResponseJsonBean> b = C4220b.m9794b().m9793b("/api/creator/apply", "applyVideoMaker");
        HttpParams a = m9789a();
        m9767a(a);
        b.params(a).execute(aVar);
    }

    /* renamed from: a */
    public static void m9762a(String str, int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b(str, str).params(m9690f(i)).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9763a(String str, int i) {
        HttpParams a = m9789a();
        a.put("kwy", str);
        a.put("page", i);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static void m9749a(String str, String str2, String str3, String str4, int i, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/usertopic/create", "createVideoCollectAction").params(m9750a(str, str2, str3, str4, i)).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9750a(String str, String str2, String str3, String str4, int i) {
        HttpParams a = m9789a();
        a.put("title", str);
        a.put("desc", str2);
        a.put("image", str3);
        a.put("mv_id", str4);
        a.put("determine", i);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static void m9776a(int i, String str, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/usertopic/update", "updateVideoCollectVideoList").params(m9740b(i, str)).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9788a(int i) {
        HttpParams a = m9789a();
        a.put("topic_id", i);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static HttpParams m9755a(String str, String str2, String str3) {
        HttpParams a = m9789a();
        a.put("bankcard", str);
        a.put("name", str2);
        a.put("amount", str3);
        m9767a(a);
        return a;
    }

    /* renamed from: a */
    public static void m9748a(String str, String str2, String str3, String str4, HttpCallback aVar) {
        C4220b.m9794b().m9793b("/api/game/pay", "gameRecharge").params(m9751a(str, str2, str3, str4)).execute(aVar);
    }

    /* renamed from: a */
    public static HttpParams m9751a(String str, String str2, String str3, String str4) {
        HttpParams a = m9789a();
        a.put("amount", str);
        a.put("type", str2);
        a.put("way", str3);
        a.put("verify_code", str4);
        m9767a(a);
        return a;
    }
}

