package com.slzhibo.library.ui.view.dialog.alert;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

//import com.slzhibo.library.R$id;
//import com.slzhibo.library.R$layout;
//import com.slzhibo.library.R$string;
import com.slzhibo.library.R;
import com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment;
import com.slzhibo.library.utils.ConstantUtils;

/* loaded from: classes3.dex */
public class LiveKickOutDialog extends BaseDialogFragment {
    @Override // com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment
    public boolean getCancelOutside() {
        return false;
    }

    public static LiveKickOutDialog newInstance(String str) {
        Bundle bundle = new Bundle();
        LiveKickOutDialog liveKickOutDialog = new LiveKickOutDialog();
        bundle.putString(ConstantUtils.RESULT_ITEM, str);
        liveKickOutDialog.setArguments(bundle);
        return liveKickOutDialog;
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment
    public int getLayoutRes() {
        return R.layout.fq_chat_tip_dialog;
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment
    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_tips);
        String argumentsString = getArgumentsString(ConstantUtils.RESULT_ITEM);
        if (!TextUtils.isEmpty(argumentsString)) {
            textView.setText(argumentsString);
        } else {
            textView.setText(R.string.fq_text_kick_out_live);
        }
        view.findViewById(R.id.ll_button).setOnClickListener(new View.OnClickListener() { // from class: com.slzhibo.library.ui.view.dialog.alert.-$$Lambda$LiveKickOutDialog$k7NmhMomNgPXU4nW8A50W5FHBjc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LiveKickOutDialog.this.lambda$initView$0$LiveKickOutDialog(view2);
            }
        });
    }

    public /* synthetic */ void lambda$initView$0$LiveKickOutDialog(View view) {
        dismiss();
    }
}
