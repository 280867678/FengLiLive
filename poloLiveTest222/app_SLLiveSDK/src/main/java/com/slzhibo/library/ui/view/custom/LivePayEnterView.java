package com.slzhibo.library.ui.view.custom;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.slzhibo.library.R;
import com.slzhibo.library.ui.activity.home.WebViewActivity;
import com.slzhibo.library.ui.interfaces.OnPayLiveCallback;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.DateUtils;
import com.slzhibo.library.utils.NumberUtils;
import com.slzhibo.library.utils.RxViewUtils;

import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class LivePayEnterView extends RelativeLayout {
    private final AtomicBoolean isDownTimeEnable = new AtomicBoolean(false);
    private Disposable mDisposable;
    private OnPayLiveCallback onPayLiveEnterCallback;
    private long times = 10;
    private TextView tvEndLiveTime;
    private TextView tvEnter;
    private TextView tvExit;
    private TextView tvHistoryScore;
    private TextView tvLiveTime;
    private TextView tvRuleDesc;
    private TextView tvTicketsPrice;
    private TextView tvTotalCount;

    public LivePayEnterView(Context context) {
        super(context);
        initView(context);
    }

    public LivePayEnterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        setBackground(new ColorDrawable(Color.parseColor("#B8000000")));
        RelativeLayout.inflate(context, R.layout.fq_layout_pay_tickets_enter_view, this);
        this.tvTicketsPrice = (TextView) findViewById(R.id.tv_tickets_price);
        this.tvExit = (TextView) findViewById(R.id.tv_exit);
        this.tvEnter = (TextView) findViewById(R.id.tv_enter);
        this.tvHistoryScore = (TextView) findViewById(R.id.tv_history_score);
        this.tvTotalCount = (TextView) findViewById(R.id.tv_total_count);
        this.tvLiveTime = (TextView) findViewById(R.id.tv_live_time);
        this.tvRuleDesc = (TextView) findViewById(R.id.tv_rule_desc);
        this.tvEndLiveTime = (TextView) findViewById(R.id.tv_pay_end_live_time);
        initListener();
    }

    public void initData(String str, String str2, String str3, String str4, String str5, String str6) {
        String str7 = AppUtils.getLiveMoneyUnitStr(getContext()) + AppUtils.formatDisplayPrice(str2, false);
        this.tvTicketsPrice.setText(Html.fromHtml(getContext().getString(R.string.fq_current_live_tickets_room_price_tips, str7, str7)));
        TextView textView = this.tvHistoryScore;
        Context context = getContext();
        int i = R.string.fq_pay_history_score;
        Object[] objArr = new Object[1];
        if (TextUtils.isEmpty(str4)) {
            str4 = getContext().getString(R.string.fq_pay_history_score_empty);
        }
        objArr[0] = str4;
        textView.setText(context.getString(i, objArr));
        this.tvTotalCount.setText(getContext().getString(R.string.fq_pay_live_total_count, str5));
        this.tvLiveTime.setText(getContext().getString(R.string.fq_pay_live_time, DateUtils.secondToMinutesString(NumberUtils.string2long(str3))));
        this.tvEndLiveTime.setText(R.string.fq_yh_end_live_time_tips_2);
        if (!TextUtils.isEmpty(str6) && str6.contains(",")) {
            String[] split = str6.split(",");
            if (split.length > 1) {
                this.tvEndLiveTime.setVisibility(View.VISIBLE);
                this.tvEndLiveTime.setText(getContext().getString(R.string.fq_yh_end_live_time_tips, split[0], split[1]));
            }
        }
        this.times = 10;
        this.isDownTimeEnable.set(true);
        showCountDown(this.times);
    }

    public void setOnPayLiveEnterCallback(OnPayLiveCallback onPayLiveCallback) {
        this.onPayLiveEnterCallback = onPayLiveCallback;
    }

    private void initListener() {
        RxViewUtils.getInstance().throttleFirst(this.tvEnter, 1000, new RxViewUtils.RxViewAction() {
            /* class com.slzhibo.library.ui.view.custom.LivePayEnterView.AnonymousClass1 */

            @Override // com.slzhibo.library.utils.RxViewUtils.RxViewAction
            public void action(Object obj) {
                if (LivePayEnterView.this.onPayLiveEnterCallback != null) {
                    LivePayEnterView.this.onPayLiveEnterCallback.onPayEnterClickListener(LivePayEnterView.this.tvEnter);
                }
            }
        });
        this.tvExit.setOnClickListener(new OnClickListener() {
            /* class com.slzhibo.library.ui.view.custom.$$Lambda$LivePayEnterView$PYWIXxvR2xhie09IvHMHnGjjIQ */

            public final void onClick(View view) {
                LivePayEnterView.this.lambda$initListener$0$LivePayEnterView(view);
            }
        });
        RxViewUtils.getInstance().throttleFirst(this.tvRuleDesc, 500, new RxViewUtils.RxViewAction() {
            /* class com.slzhibo.library.ui.view.custom.$$Lambda$LivePayEnterView$vCGro9dWBpfBST36yKOkNojS42c */

            @Override // com.slzhibo.library.utils.RxViewUtils.RxViewAction
            public final void action(Object obj) {
                LivePayEnterView.this.lambda$initListener$1$LivePayEnterView(obj);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$LivePayEnterView(View view) {
        if (this.onPayLiveEnterCallback != null) {
            onRelease();
            this.onPayLiveEnterCallback.onPayExitClickListener();
        }
    }

    public /* synthetic */ void lambda$initListener$1$LivePayEnterView(Object obj) {
        Intent intent = new Intent(getContext(), WebViewActivity.class);
        intent.putExtra(ConstantUtils.WEB_VIEW_FROM_SERVICE, true);
        intent.putExtra("url", ConstantUtils.APP_PARAM_PAY_LIVE);
        intent.putExtra(ConstantUtils.WEB_VIEW_TITLE, getContext().getString(R.string.fq_week_star_rule_desc));
        getContext().startActivity(intent);
    }

    public void onResume() {
        if (this.isDownTimeEnable.get()) {
            showCountDown(this.times);
        }
    }

    public void onPause() {
        if (this.isDownTimeEnable.get()) {
            downCountDispose();
        }
    }

    public void onRelease() {
        downCountDispose();
        this.isDownTimeEnable.set(false);
    }

    public void onReset() {
        this.isDownTimeEnable.set(true);
        onResume();
    }

    private void showCountDown(final long j) {
        if (j <= 0) {
            onPayExitClickListener(true);
            return;
        }
        downCountDispose();
        this.tvExit.setText(getContext().getString(R.string.fq_pay_exit_room_tips, String.valueOf(j)));
        this.mDisposable = Flowable.intervalRange(1, j, 1, 1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Subscription>() {
            /* class com.slzhibo.library.ui.view.custom.LivePayEnterView.AnonymousClass4 */

            public void accept(Subscription subscription) throws Exception {
            }
        }).doOnNext(new Consumer<Long>() {
            /* class com.slzhibo.library.ui.view.custom.LivePayEnterView.AnonymousClass3 */

            public void accept(Long l) throws Exception {
                long longValue = j - l.longValue();
                LivePayEnterView.this.times = longValue;
                LivePayEnterView.this.tvExit.setText(LivePayEnterView.this.getContext().getString(R.string.fq_pay_exit_room_tips, String.valueOf(longValue)));
            }
        }).doOnComplete(new Action() {
            /* class com.slzhibo.library.ui.view.custom.LivePayEnterView.AnonymousClass2 */

            @Override // io.reactivex.functions.Action
            public void run() throws Exception {
                LivePayEnterView.this.onPayExitClickListener(true);
            }
        }).subscribe();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onPayExitClickListener(boolean z) {
        if (this.onPayLiveEnterCallback != null) {
            if (z) {
                ToastUtils.showShort(getContext().getString(R.string.fq_pay_live_timeout_toast));
            }
            this.onPayLiveEnterCallback.onPayExitClickListener();
        }
    }

    private void downCountDispose() {
        Disposable disposable = this.mDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.mDisposable.dispose();
        }
    }
}
