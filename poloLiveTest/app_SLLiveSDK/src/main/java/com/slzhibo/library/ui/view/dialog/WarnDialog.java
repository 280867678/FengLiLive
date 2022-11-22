package com.slzhibo.library.ui.view.dialog;



import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.R;
import com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment;


/* renamed from: com.slzhibo.library.ui.view.dialog.alert.WarnDialog */
/* loaded from: classes2.dex */
public class WarnDialog extends BaseDialogFragment {
    private static final String TIP_KEY = "TIP_KEY";
    /* renamed from: b */
    public View.OnClickListener f7666b;

    /* renamed from: a */
    public static WarnDialog m14685a(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("TIP_KEY", str);
        WarnDialog warnDialog = new WarnDialog();
        warnDialog.setArguments(bundle);
        return warnDialog;
    }

    @Override // com.slzhibo.library.p018ui.view.dialog.base.BaseDialogFragment
    public boolean getCancelOutside() {
        return !TextUtils.equals(getArgumentsString("TIP_KEY"), "REPORT_TIP");
    }

    @Override // com.slzhibo.library.p018ui.view.dialog.base.BaseDialogFragment
    public int getLayoutRes() {
        return R.layout.fq_chat_tip_dialog;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.slzhibo.library.p018ui.view.dialog.base.BaseDialogFragment
    public void initView(View view) {
        char c;
        String str;
        String str2;
        String str3;
        TextView textView = (TextView) view.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_tips);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_sure);
        String argumentsString = getArgumentsString("TIP_KEY");
        textView2.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_text_gray));
        switch (argumentsString.hashCode()) {
            case -1877212432:
                if (argumentsString.equals("REPORT_TIP")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -460174899:
                if (argumentsString.equals("TRANSLATION_TIP")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 381543467:
                if (argumentsString.equals("BAN_TIP")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 928205355:
                if (argumentsString.equals("OPERATION_AUTHORITY")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1281379138:
                if (argumentsString.equals("WARN_TIP")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1805920860:
                if (argumentsString.equals("FROZEN_TIP")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1919174984:
                if (argumentsString.equals("YY_LINK_APPLY")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1980849887:
                if (argumentsString.equals("STOP_WARN_TIP")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                str3 = getString(R.string.fq_tip);
                str2 = getString(R.string.fq_anchor_ban_content);
                str = getString(R.string.fq_know);
                break;
            case 1:
                str3 = getString(R.string.fq_warn_tip);
                str2 = getString(R.string.fq_warn_content);
                str = getString(R.string.fq_know);
                break;
            case 2:
                str3 = getString(R.string.fq_back_out_warn);
                str2 = getString(R.string.fq_back_out_warn_content);
                str = getString(R.string.fq_know);
                break;
            case 3:
                str3 = getString(R.string.fq_frozen_tip);
                str2 = getString(R.string.fq_frozen_content);
                str = getString(R.string.fq_btn_sure);
                break;
            case 4:
                str3 = getString(R.string.fq_tip);
                str2 = getArgumentsString("CONTENT_KEY");
                str = getString(R.string.fq_know);
                break;
            case 5:
                str3 = getString(R.string.fq_report_live_success_title);
                str2 = getString(R.string.fq_report_live_success_tips);
                str = getString(R.string.fq_btn_sure);
                break;
            case 6:
                str3 = getString(R.string.fq_no_operation_authority);
                str2 = getString(R.string.fq_no_operation_authority_tips);
                str = getString(R.string.fq_know);
                break;
            case 7:
                textView2.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_text_black));
                textView2.setGravity(3);
                str3 = getString(R.string.fq_yy_call_link_condition_tips);
                str2 = getArgumentsString("CONTENT_KEY");
                str = getString(R.string.fq_know);
                break;
            default:
                return;
        }
        textView.setText(str3);
        textView2.setText(str2);
        textView3.setText(str);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: e.t.a.h.f.b.b4.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                WarnDialog.this.m14686a(view2);
            }
        });
    }

    public void setListener(View.OnClickListener onClickListener) {
        this.f7666b = onClickListener;
    }

    @Override // com.slzhibo.library.p018ui.view.dialog.base.BaseRxDialogFragment
    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, getArgumentsString("TIP_KEY"));
    }

    /* renamed from: a */
    public static WarnDialog m14683a(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("TIP_KEY", str);
        bundle.putString("CONTENT_KEY", str2);
        WarnDialog warnDialog = new WarnDialog();
        warnDialog.setArguments(bundle);
        return warnDialog;
    }

    /* renamed from: a */
    public static WarnDialog m14684a(String str, View.OnClickListener onClickListener) {
        Bundle bundle = new Bundle();
        bundle.putString("TIP_KEY", str);
        WarnDialog warnDialog = new WarnDialog();
        warnDialog.setListener(onClickListener);
        warnDialog.setArguments(bundle);
        return warnDialog;
    }

    /* renamed from: a */
    public /* synthetic */ void m14686a(View view) {
        dismiss();
        View.OnClickListener onClickListener = this.f7666b;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }






    public static WarnDialog newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(TIP_KEY, str);
        WarnDialog warnDialog = new WarnDialog();
        warnDialog.setArguments(bundle);
        return warnDialog;
    }



}

