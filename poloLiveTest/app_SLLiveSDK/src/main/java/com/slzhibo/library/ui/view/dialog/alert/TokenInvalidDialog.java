package com.slzhibo.library.ui.view.dialog.alert;



import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.slzhibo.library.R;
//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.SLLiveSDK;
import com.slzhibo.library.model.event.LogoutEvent;
import com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment;
import com.slzhibo.library.utils.UserInfoManager;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes11.dex */
public class TokenInvalidDialog extends BaseDialogFragment {
    @Override // com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment
    public boolean getCancelOutside() {
        return false;
    }

    public static TokenInvalidDialog newInstance() {
        Bundle bundle = new Bundle();
        TokenInvalidDialog tokenInvalidDialog = new TokenInvalidDialog();
        UserInfoManager.getInstance().clearTokenInfo();
        EventBus.getDefault().post(new LogoutEvent());
        tokenInvalidDialog.setArguments(bundle);
        return tokenInvalidDialog;
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment
    public int getLayoutRes() {
        return R.layout.fq_chat_tip_dialog;
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseDialogFragment
    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_tips);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_sure);
        ((TextView) view.findViewById(R.id.tv_title)).setText(R.string.fq_tip);
        textView.setText(R.string.fq_token_invalid_tip);
        textView2.setText(R.string.fq_btn_sure);
        textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_colorPrimary));
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.slzhibo.library.ui.view.dialog.alert.-$$Lambda$TokenInvalidDialog$tsDRPCG8SyMprKx8O350PboWa6A
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TokenInvalidDialog.this.lambda$initView$0$TokenInvalidDialog(view2);
            }
        });
    }

    public /* synthetic */ void lambda$initView$0$TokenInvalidDialog(View view) {
        if (SLLiveSDK.getSingleton().sdkCallbackListener != null) {
            SLLiveSDK.getSingleton().sdkCallbackListener.onUserOfflineListener(this.mContext);
        }
        dismiss();
    }
}

