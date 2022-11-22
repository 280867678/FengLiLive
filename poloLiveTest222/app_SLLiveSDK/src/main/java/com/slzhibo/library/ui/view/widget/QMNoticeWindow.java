package com.slzhibo.library.ui.view.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import com.slzhibo.library.R;
import com.slzhibo.library.model.QMInteractTaskEntity;
import com.slzhibo.library.ui.view.widget.badgeView.QBadgeView;
import com.slzhibo.library.ui.view.widget.marqueenview.MarqueeTextView;
import com.slzhibo.library.ui.view.widget.progress.QMTaskProgressView;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.NumberUtils;

public class QMNoticeWindow extends RelativeLayout {
    private boolean isShowRed;
    private ImageView ivGiftIcon;
    private final Context mContext;
    private MarqueeTextView mTaskName;
    private QBadgeView qBadgeView;
    private QMTaskProgressView qmTaskProgressView;

    public QMNoticeWindow(Context context) {
        this(context, null);
    }

    public QMNoticeWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public QMNoticeWindow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        initView();
    }

    private void initView() {
        RelativeLayout.inflate(this.mContext, R.layout.fq_qm_notice_window, this);
        setBackgroundResource(R.drawable.fq_hj_float_window_bg);
        this.mTaskName = (MarqueeTextView) findViewById(R.id.mv_task_name);
        this.ivGiftIcon = (ImageView) findViewById(R.id.iv_gift_icon);
        this.qmTaskProgressView = (QMTaskProgressView) findViewById(R.id.qm_task_progress);
    }

    public void showRedPoint() {
        if (this.qBadgeView == null) {
            this.qBadgeView = new QBadgeView(this.mContext);
            this.qBadgeView.bindTarget(this.ivGiftIcon).setBadgeTextColor(-1).setBadgePadding(1.0f, true).isNoNumber(true).setBadgeGravity(8388661).setBadgeBackgroundColor(ContextCompat.getColor(this.mContext, R.color.fq_qm_primary)).stroke(-1, 1.0f, true);
        }
        this.qBadgeView.showSinglePoint();
        this.isShowRed = true;
    }

    public void hideRedPoint() {
        if (this.isShowRed) {
            QBadgeView qBadgeView2 = this.qBadgeView;
            if (qBadgeView2 != null) {
                qBadgeView2.hideSinglePoint();
            }
            this.isShowRed = false;
        }
    }

    public void show(QMInteractTaskEntity qMInteractTaskEntity) {
        if (TextUtils.equals("1", qMInteractTaskEntity.taskType)) {
            this.qmTaskProgressView.setVisibility(View.VISIBLE);
            this.mTaskName.setVisibility(View.GONE);
            this.qmTaskProgressView.setMaxProgress(NumberUtils.string2int(qMInteractTaskEntity.giftNum));
            this.qmTaskProgressView.setProgressValue(NumberUtils.string2int(qMInteractTaskEntity.chargeGiftNum));
        } else {
            this.qmTaskProgressView.setVisibility(View.GONE);
            this.mTaskName.setVisibility(View.VISIBLE);
            this.mTaskName.startWithText(qMInteractTaskEntity.taskName);
        }
        GlideUtils.loadImage(this.mContext, this.ivGiftIcon, qMInteractTaskEntity.giftUrl);
    }

    public void onRelease() {
        MarqueeTextView marqueeTextView = this.mTaskName;
        if (marqueeTextView != null) {
            marqueeTextView.stopScroll();
        }
    }

    public /* synthetic */ void lambda$setProgressValue$0$QMNoticeWindow(int i) {
        this.qmTaskProgressView.setProgressValue(i);
    }

    public void setProgressValue(int i) {
        this.qmTaskProgressView.post(new Runnable() {
            /* class com.slzhibo.library.ui.view.widget.$$Lambda$QMNoticeWindow$qucapuc1a1EMkAiUJ0fNEEiEdnY */


            public final void run() {
                QMNoticeWindow.this.lambda$setProgressValue$0$QMNoticeWindow(i);
            }
        });
    }
}
