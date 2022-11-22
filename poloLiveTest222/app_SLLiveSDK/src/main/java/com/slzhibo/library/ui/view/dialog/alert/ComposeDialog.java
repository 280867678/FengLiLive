package com.slzhibo.library.ui.view.dialog.alert;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slzhibo.library.R;
import com.slzhibo.library.model.PropConfigEntity;
import com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment;

/* loaded from: classes3.dex */
public class ComposeDialog extends BaseDialogFragment implements View.OnClickListener {
    private ComposeListener composeListener;
    private PropConfigEntity entity;

    /* loaded from: classes3.dex */
    public interface ComposeListener {
        void onClick(PropConfigEntity propConfigEntity);
    }

    public static ComposeDialog newInstance(PropConfigEntity propConfigEntity, boolean z, ComposeListener composeListener) {
        Bundle bundle = new Bundle();
        ComposeDialog composeDialog = new ComposeDialog();
        bundle.putBoolean("KEY_SUC", z);
        composeDialog.setArguments(bundle);
        composeDialog.setFragmentConfigEntity(propConfigEntity);
        composeDialog.setComposeListener(composeListener);
        return composeDialog;
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment
    public int getLayoutRes() {
        return R.layout.fq_dialog_compose_tips;
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment
    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_cancel);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_tips);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_content_tips);
        TextView textView4 = (TextView) view.findViewById(R.id.tv_start);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_root);
        TextView textView5 = (TextView) view.findViewById(R.id.tv_know);
        if (getArguments().getBoolean("KEY_SUC")) {
            linearLayout.setVisibility(View.VISIBLE);
            textView5.setVisibility(View.GONE);
            textView2.setText(getString(R.string.fq_sure_compose, this.entity.propName));
            textView3.setText(getString(R.string.fq_compose_sure_tip, "50", this.entity.propName));
        } else {
            linearLayout.setVisibility(View.GONE);
            textView5.setVisibility(View.VISIBLE);
            textView2.setText(R.string.fq_compose_fail_tips);
            textView3.setText(getString(R.string.fq_compose_tips, "50"));
        }
        textView5.setOnClickListener(this);
        textView.setOnClickListener(this);
        textView4.setOnClickListener(new View.OnClickListener() { // from class: com.slzhibo.library.ui.view.dialog.alert.-$$Lambda$ComposeDialog$GeW8cnkV9FIXVNZiRiVTzRpSiKg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ComposeDialog.this.lambda$initView$0$ComposeDialog(view2);
            }
        });
    }

    public /* synthetic */ void lambda$initView$0$ComposeDialog(View view) {
        if (this.composeListener != null) {
            dismiss();
            this.composeListener.onClick(this.entity);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        dismiss();
    }

    private void setComposeListener(ComposeListener composeListener) {
        this.composeListener = composeListener;
    }

    private void setFragmentConfigEntity(PropConfigEntity propConfigEntity) {
        this.entity = propConfigEntity;
    }
}
