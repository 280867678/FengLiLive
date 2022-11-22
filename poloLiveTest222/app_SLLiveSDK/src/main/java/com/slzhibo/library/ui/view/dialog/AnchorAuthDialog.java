package com.slzhibo.library.ui.view.dialog;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.slzhibo.library.R;
//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.ui.activity.AnchorAuthActivity;
import com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment;


/* renamed from: com.slzhibo.library.ui.view.dialog.confirm.AnchorAuthDialog */
/* loaded from: classes2.dex */
public class AnchorAuthDialog extends BaseDialogFragment {
    public static AnchorAuthDialog newInstance() {
        Bundle bundle = new Bundle();
        AnchorAuthDialog anchorAuthDialog = new AnchorAuthDialog();
        anchorAuthDialog.setArguments(bundle);
        return anchorAuthDialog;
    }

    private void setAuthListener(View.OnClickListener onClickListener) {
    }

    /* renamed from: a */
    public /* synthetic */ void m14669a(View view) {
        dismiss();
    }

    /* renamed from: b */
    public /* synthetic */ void m14668b(View view) {
        dismiss();
        Context context = this.mContext;
        context.startActivity(new Intent(context, AnchorAuthActivity.class));
    }

    @Override // com.slzhibo.library.p018ui.view.dialog.base.BaseDialogFragment
    public int getLayoutRes() {
        return R.layout.fq_dialog_auth_tips;
    }

    @Override // com.slzhibo.library.p018ui.view.dialog.base.BaseDialogFragment
    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_sure);
        textView.setText(R.string.fq_btn_go_auth);
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() { // from class: e.t.a.h.f.b.d4.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AnchorAuthDialog.this.m14669a(view2);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: e.t.a.h.f.b.d4.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AnchorAuthDialog.this.m14668b(view2);
            }
        });
    }
}

