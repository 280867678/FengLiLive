package com.slzhibo.library.ui.view.dialog;



import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import com.blankj.utilcode.util.LogUtils;
import com.slzhibo.library.R;
//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.SLLiveSDK;
//import com.slzhibo.library.analytics.AopConstants;
import com.slzhibo.library.http.ApiRetrofit;
import com.slzhibo.library.http.RequestParams;
import com.slzhibo.library.http.function.HttpResultFunction;
import com.slzhibo.library.http.function.ServerResultFunction;
import com.slzhibo.library.model.ActivityListEntity;
import com.slzhibo.library.model.db.ActivityBagDBEntity;
import com.slzhibo.library.model.db.ActivityBagDBEntity;
import com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment;
import com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment;
import com.slzhibo.library.ui.view.widget.C5589c0;
import com.slzhibo.library.ui.view.widget.Html5WebView;
import com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment;
import com.slzhibo.library.ui.view.widget.Html5WebView;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.DBUtils;
import com.slzhibo.library.utils.GsonUtils;
import com.slzhibo.library.utils.NumberUtils;
import com.slzhibo.library.utils.live.SimpleRxObserver;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.slzhibo.library.ui.view.dialog.GiftBagWebViewDialog */
/* loaded from: classes11.dex */
public class GiftBagWebViewDialog extends BaseDialogFragment {
    private ActivityListEntity activityEntity;
    private View llFailContentView;
    private View llFailLoadingBg;
    private View loadingView;
    private Html5WebView mWebView;
    private final String JsMsgHandlerName = "messageHandlers";
    private final String JsMsgAc = "action";
    private final String JsMsgParams = "params";
    private final String JsMsgReady = "ready";
    private final String JsMsgCommon = "common";
    private final String JsMsgOpenPage = "openPage";
    private final String JsMsgHide = "hide";
    private final String JsMsgClose = "close";
    private final String JsMsgActivityId = "bagId";
    private final String JsMsgUrl = "url";
    private final String JsMsgClick = "click";
    private final String JsMsgType = "type";
    private final int CONTENT_TYPE_LOADING = 1;
    private final int CONTENT_TYPE_CONTENT = 2;
    private final int CONTENT_TYPE_FAIL = 3;
    private volatile boolean isLoadError = false;
    private String mUrl = "";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(String str) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$2(String str) {
    }

    @Override // com.slzhibo.library.p115ui.view.dialog.base.BaseRxDialogFragment
    public float getDimAmount() {
        return 0.0f;
    }

    @Override // com.slzhibo.library.p115ui.view.dialog.base.BaseDialogFragment
    public double getHeightScale() {
        return 1.0d;
    }



    @Override // com.slzhibo.library.p115ui.view.dialog.base.BaseDialogFragment
    public double getWidthScale() {
        return 1.0d;
    }



    public static GiftBagWebViewDialog newInstance(ActivityListEntity activityListEntity) {
        Bundle bundle = new Bundle();
        GiftBagWebViewDialog giftBagWebViewDialog = new GiftBagWebViewDialog();
        bundle.putSerializable(ConstantUtils.RESULT_ITEM, activityListEntity);
        giftBagWebViewDialog.setArguments(bundle);
        return giftBagWebViewDialog;
    }

    @Override // com.slzhibo.library.p115ui.view.dialog.base.BaseRxDialogFragment
    public void getBundle(Bundle bundle) {
        super.getBundle(bundle);
        this.activityEntity = (ActivityListEntity) bundle.getSerializable(ConstantUtils.RESULT_ITEM);
        ActivityListEntity activityListEntity = this.activityEntity;
        this.mUrl = activityListEntity != null ? activityListEntity.urlLink : "";
    }

    @Override // com.slzhibo.library.p115ui.view.dialog.base.BaseDialogFragment
    protected int getLayoutRes() {
        return R.layout.fq_dialog_bottom_webview_gift_bag;
    }

    @Override // com.slzhibo.library.p115ui.view.dialog.base.BaseDialogFragment
    protected void initView(View view) {
        this.mWebView = (Html5WebView) view.findViewById(R.id.web_view);
        this.llFailContentView = view.findViewById(R.id.ll_fail_content_view);
        this.loadingView = view.findViewById(R.id.tv_loading_view);
        this.llFailLoadingBg = view.findViewById(R.id.ll_fail_loading_bg);
        initContentView(1);
        initWebView();
    }

    @Override // com.slzhibo.library.p115ui.view.dialog.base.BaseDialogFragment
    public void initListener(View view) {
        this.mWebView.setOnTouchListener(new View.OnTouchListener() { // from class: com.slzhibo.library.ui.view.dialog.GiftBagWebViewDialog.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if ((action != 0 && action != 1) || view2.hasFocus()) {
                    return false;
                }
                view2.requestFocus();
                return false;
            }
        });
        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() { // from class: com.slzhibo.library.ui.view.dialog.GiftBagWebViewDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                GiftBagWebViewDialog.this.dismiss();
            }
        });
    }

    private void initWebView() {
        this.mWebView.setBackgroundColor(0);
        this.mWebView.getSettings().setSupportZoom(false);
        this.mWebView.getSettings().setBuiltInZoomControls(false);
        this.mWebView.setWebViewClient(new Html5WebViewClient());
        this.mWebView.addJavascriptInterface(this, "messageHandlers");
        if (!TextUtils.isEmpty(this.mUrl)) {
            this.mUrl += "?" + "time" + "=" + System.currentTimeMillis();
            this.mWebView.loadUrl(this.mUrl);
        }
    }

    @Override // com.slzhibo.library.utils.rxlifecycle2.components.support.RxDialogFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        Html5WebView html5WebView = this.mWebView;
        if (html5WebView != null) {
            html5WebView.clearHistory();
            this.mWebView.destroy();
            this.mWebView = null;
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint("WrongConstant")
    public void initContentView(int i) {
        int i2 = 0;
        this.loadingView.setVisibility(i == 1 ? 0 : 4);
        this.llFailContentView.setVisibility(i == 3 ? 0 : 4);
        this.llFailLoadingBg.setVisibility(i == 2 ? 4 : 0);
        Html5WebView html5WebView = this.mWebView;
        if (html5WebView != null) {
            if (i != 2) {
                i2 = 4;
            }
            html5WebView.setVisibility(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.slzhibo.library.ui.view.dialog.GiftBagWebViewDialog$Html5WebViewClient */
    /* loaded from: classes11.dex */
    public class Html5WebViewClient extends Html5WebView.BaseWebViewClient {
        private Html5WebViewClient() {
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // com.slzhibo.library.p115ui.view.widget.Html5WebView.BaseWebViewClient, com.tencent.smtt.sdk.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!str.startsWith("http") && !str.startsWith("https")) {
                return true;
            }
            if (Build.VERSION.SDK_INT >= 26) {
                return false;
            }
            webView.loadUrl(str);
            return true;
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            if (Build.VERSION.SDK_INT < 23) {
                GiftBagWebViewDialog.this.loadErrorView();
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        @TargetApi(23)
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            if (webResourceRequest.isForMainFrame()) {
                GiftBagWebViewDialog.this.loadErrorView();
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            if (webResourceRequest.isForMainFrame()) {
                GiftBagWebViewDialog.this.loadErrorView();
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (GiftBagWebViewDialog.this.isLoadError) {
                GiftBagWebViewDialog.this.loadErrorView();
                return;
            }
            GiftBagWebViewDialog.this.isLoadError = false;
            GiftBagWebViewDialog.this.initContentView(2);
        }
    }

    @JavascriptInterface
    @RequiresApi(api = 19)
    public void receiveMessageFromJS(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("action");
            JSONObject jSONObject2 = jSONObject.getJSONObject("params");
            char c = 65535;
            switch (string.hashCode()) {
                case -1354814997:
                    if (string.equals("common")) {
                        c = 1;
                        break;
                    }
                    break;
                case -504772615:
                    if (string.equals("openPage")) {
                        c = 4;
                        break;
                    }
                    break;
                case 3202370:
                    if (string.equals("hide")) {
                        c = 3;
                        break;
                    }
                    break;
                case 94750088:
                    if (string.equals("click")) {
                        c = 5;
                        break;
                    }
                    break;
                case 94756344:
                    if (string.equals("close")) {
                        c = 2;
                        break;
                    }
                    break;
                case 108386723:
                    if (string.equals("ready")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            if (c != 0) {
                if (c != 1) {
                    if (c == 2) {
                        dismiss();
                    } else if (c == 3) {
                        DBUtils.updateActivityOfTodayRemindStatus(this.activityEntity, "1");
                    } else if (c == 4) {
                        AppUtils.onSysWebView(this.mContext, jSONObject2.getString("url"));
                    } else if (c == 5) {
                        SLLiveSDK.getSingleton().clickBannerReport(this.mContext, jSONObject2.getString("bagId"));
                    }
                } else if (AppUtils.isConsumptionPermissionUser(this.mContext)) {
                    sendBuyRequest(jSONObject2.getString("bagId"));
                }
            } else if (TextUtils.isEmpty(jSONObject2.getString("type"))) {
                sendReadyJS(getReadJson());
            } else {
                sendReadyJS(AppUtils.getH5ReadConfigInfoJson());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = 19)
    public void sendReadyJS(final String str) {
        this.mWebView.post(new Runnable() { // from class: com.slzhibo.library.ui.view.dialog.-$$Lambda$GiftBagWebViewDialog$D9etf1iSpzEXHLpcGXpEz0a4R4s
            @Override // java.lang.Runnable
            public final void run() {
                GiftBagWebViewDialog.this.lambda$sendReadyJS$1$GiftBagWebViewDialog(str);
            }
        });
    }

    public /* synthetic */ void lambda$sendReadyJS$1$GiftBagWebViewDialog(String str) {
        if (!TextUtils.isEmpty(str)) {
            String str2 = "javascript:postData(" + str + ")";
//            LogUtils.m1878i(str2);
            Html5WebView html5WebView = this.mWebView;
            if (html5WebView != null) {
                html5WebView.evaluateJavascript(str2, C5589c0.f18638a);
            }
        }
    }

    @RequiresApi(api = 19)
    public void sendBuyJS(final String str, final String str2) {
        this.mWebView.post(new Runnable() { // from class: com.slzhibo.library.ui.view.dialog.-$$Lambda$GiftBagWebViewDialog$ZPXXhKYT_7KiX0fgHjlvg2T0Vig
            @Override // java.lang.Runnable
            public final void run() {
                GiftBagWebViewDialog.this.lambda$sendBuyJS$3$GiftBagWebViewDialog(str, str2);
            }
        });
    }
    /* renamed from: g */
    public static /* synthetic */ void m15734g(String str) {
    }

    public /* synthetic */ void lambda$sendBuyJS$3$GiftBagWebViewDialog(String str, String str2) {
        String str3 = "javascript:postData(" + getBuyJson(str, str2) + ")";
//        LogUtils.m1878i(str3);
        Html5WebView html5WebView = this.mWebView;
        if (html5WebView != null) {
            html5WebView.evaluateJavascript(str3, C5589c0.f18638a);
        }
    }

    private void sendBuyRequest(final String str) {
        ApiRetrofit.getInstance().getApiService().getGiftBagService(new RequestParams().getBagIdParams(str)).map(new ServerResultFunction<ActivityListEntity>() { // from class: com.slzhibo.library.ui.view.dialog.GiftBagWebViewDialog.4
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<ActivityListEntity>(this.mContext) { // from class: com.slzhibo.library.ui.view.dialog.GiftBagWebViewDialog.3
            @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                GiftBagWebViewDialog.this.showLoadingDialog(true, false);
            }

            @RequiresApi(api = 19)
            public void accept(ActivityListEntity activityListEntity) {
                GiftBagWebViewDialog.this.dismissLoadingDialog();
                if (activityListEntity != null) {
                    GiftBagWebViewDialog.this.sendBuyJS(str, activityListEntity.repetition);
                    DBUtils.saveActivityBagItemInfo(GiftBagWebViewDialog.this.activityEntity, str, activityListEntity.repetition);
                    GiftBagWebViewDialog.this.showToast(R.string.fq_buy_success_tips);
                    GiftBagWebViewDialog.this.dismiss();
                }
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
            public void onError(Throwable th) {
                super.onError(th);
                GiftBagWebViewDialog.this.dismissLoadingDialog();
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(int i, String str2) {
                super.onError(i, str2);
                GiftBagWebViewDialog.this.dismissLoadingDialog();
                if (i == 102001) {
                    AppUtils.onRechargeListener(( GiftBagWebViewDialog.this).mContext);
                    Toast.makeText(mContext, "GiftBagWebViewDialog::onError400", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getBuyJson(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", "common");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("bagId", Integer.valueOf(NumberUtils.string2int(str)));
        hashMap2.put("repetition", Integer.valueOf(NumberUtils.string2int(str2)));
        hashMap.put("data", hashMap2);
        return GsonUtils.toJson(hashMap);
    }

    private String getReadJson() {
        if (this.activityEntity == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("type", "ready");
        ArrayList arrayList = new ArrayList();
        List<ActivityBagDBEntity> activityBagList = DBUtils.getActivityBagList(this.activityEntity.id);
        if (activityBagList != null && !activityBagList.isEmpty()) {
            for (ActivityBagDBEntity activityBagDBEntity : activityBagList) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("bagId", Integer.valueOf(NumberUtils.string2int(activityBagDBEntity.bagId)));
                hashMap2.put("repetition", Integer.valueOf(NumberUtils.string2int(activityBagDBEntity.buyStatus)));
                arrayList.add(hashMap2);
            }
        }
        hashMap.put("data", arrayList);
        return GsonUtils.toJson(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadErrorView() {
        this.isLoadError = true;
        initContentView(3);
    }
}
