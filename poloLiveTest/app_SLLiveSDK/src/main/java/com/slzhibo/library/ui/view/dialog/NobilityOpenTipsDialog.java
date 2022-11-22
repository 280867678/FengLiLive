package com.slzhibo.library.ui.view.dialog;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.slzhibo.library.R;
import com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.UserInfoManager;

public class NobilityOpenTipsDialog extends BaseDialogFragment {
    private View.OnClickListener openClickListener;

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment
    public double getWidthScale() {
        return 0.7d;
    }

    private void setOpenClickListener(View.OnClickListener onClickListener) {
        this.openClickListener = onClickListener;
    }

    public static NobilityOpenTipsDialog newInstance(int i, View.OnClickListener onClickListener) {
        NobilityOpenTipsDialog nobilityOpenTipsDialog = new NobilityOpenTipsDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("TIP_KEY", i);
        nobilityOpenTipsDialog.setArguments(bundle);
        nobilityOpenTipsDialog.setOpenClickListener(onClickListener);
        return nobilityOpenTipsDialog;
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment
    public int getLayoutRes() {
        return R.layout.fq_dialog_open_nobility_tips;
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment
    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_content);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_tips);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_open_room);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_bg);
        View findViewById = view.findViewById(R.id.ib_close);
        switch (getArgumentsInt("TIP_KEY")) {
            case 11:
                imageView.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_nobility_dialog_bg_ktgz));
                textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_colorPrimary));
                textView.setText(R.string.fq_nobility_danmu_tip);
                textView2.setText(Html.fromHtml(this.mContext.getString(R.string.fq_nobility_danmu_open_tip)));
                textView3.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.fq_red_shape_nobility_open));
                textView3.setText(getSMRTips(4));
                break;
            case 12:
                imageView.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_nobility_dialog_bg_ktgz_trumpet));
                textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_colorYellow));
                textView.setText(R.string.fq_nobility_trumpet_tip);
                textView2.setText(Html.fromHtml(this.mContext.getString(R.string.fq_nobility_trumpet_open_tip)));
                textView3.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.fq_yellow_shape_nobility_open));
                textView3.setText(getSMRTips(4));
                break;
            case 13:
                imageView.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_nobility_dialog_bg_ktgz_smr));
                textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_colorPurple));
                textView.setText(R.string.fq_nobility_smr_tip);
                textView2.setText(R.string.fq_nobility_hide_tip);
                textView3.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.fq_purple_shape_nobility_open));
                textView3.setText(getSMRTips(6));
                break;
        }
        findViewById.setOnClickListener(new View.OnClickListener() {
            /* class com.slzhibo.library.ui.view.dialog.$$Lambda$NobilityOpenTipsDialog$WnFIExp2vcmLUpb9BOIIKpOQ4zg */

            public final void onClick(View view) {
                NobilityOpenTipsDialog.this.lambda$initView$0$NobilityOpenTipsDialog(view);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            /* class com.slzhibo.library.ui.view.dialog.$$Lambda$NobilityOpenTipsDialog$Zk9jn6i9E_VHLszhoF1YaOB6riA */

            public final void onClick(View view) {
                NobilityOpenTipsDialog.this.lambda$initView$1$NobilityOpenTipsDialog(view);
            }
        });
    }

    public /* synthetic */ void lambda$initView$0$NobilityOpenTipsDialog(View view) {
        dismiss();
    }

    public /* synthetic */ void lambda$initView$1$NobilityOpenTipsDialog(View view) {
        dismiss();
        this.openClickListener.onClick(view);
    }

    private String getSMRTips(int i) {
        if (UserInfoManager.getInstance().getNobilityType() >= i) {
            return getString(R.string.fq_nobility_renewal_fee);
        }
        if (AppUtils.isNobilityUser()) {
            return getString(R.string.fq__nobility_upgrade);
        }
        return getString(R.string.fq_open_nobility);
    }
}
