package com.slzhibo.library.ui.view.dialog;



import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.slzhibo.library.R;
//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.ui.view.dialog.LiveKickOutDialog;
import com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment;
import com.slzhibo.library.utils.ConstantUtils;

/* renamed from: com.slzhibo.library.ui.view.dialog.alert.LiveKickOutDialog */
/* loaded from: classes2.dex */
public class LiveKickOutDialog extends BaseDialogFragment {
    /* renamed from: a */
    public static LiveKickOutDialog m14688a(String str) {
        Bundle bundle = new Bundle();
        LiveKickOutDialog liveKickOutDialog = new LiveKickOutDialog();
        bundle.putString(ConstantUtils.RESULT_ITEM, str);
        liveKickOutDialog.setArguments(bundle);
        return liveKickOutDialog;
    }

    @Override // com.slzhibo.library.p018ui.view.dialog.base.BaseDialogFragment
    public boolean getCancelOutside() {
        return false;
    }

    @Override // com.slzhibo.library.p018ui.view.dialog.base.BaseDialogFragment
    public int getLayoutRes() {
        return R.layout.fq_chat_tip_dialog;
    }

    @Override // com.slzhibo.library.p018ui.view.dialog.base.BaseDialogFragment
    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_tips);
        String argumentsString = getArgumentsString(ConstantUtils.RESULT_ITEM);
        if (!TextUtils.isEmpty(argumentsString)) {
            textView.setText(argumentsString);
        } else {
            textView.setText(R.string.fq_text_kick_out_live);
        }
        view.findViewById(R.id.ll_button).setOnClickListener(new View.OnClickListener() { // from class: e.t.a.h.f.b.b4.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LiveKickOutDialog.this.m14689a(view2);
            }
        });
    }

    /* renamed from: a */
    public /* synthetic */ void m14689a(View view) {
        dismiss();
    }




    public static LiveKickOutDialog newInstance(String str) {
        Bundle bundle = new Bundle();
        LiveKickOutDialog liveKickOutDialog = new LiveKickOutDialog();
        bundle.putString(ConstantUtils.RESULT_ITEM, str);
        liveKickOutDialog.setArguments(bundle);
        return liveKickOutDialog;
    }

}

