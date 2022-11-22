package com.slzhibo.library.ui.view.dialog.base;



import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.R;

/* loaded from: classes11.dex */
public abstract class BaseDialogFragment extends BaseRxDialogFragment {
    protected Dialog mDialog;
    protected Window mWindow;
    public View rootView;

    public boolean getCancelOutside() {
        return true;
    }

    public double getHeightScale() {
        return 0.0d;
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    public double getWidthScale() {
        return 0.7d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initListener(View view) {
    }

    protected abstract void initView(View view);

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.fq_CenterDialog);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mDialog = getDialog();
        this.mWindow = this.mDialog.getWindow();
        Window window = this.mWindow;
        if (window != null) {
            window.requestFeature(1);
            this.mWindow.setBackgroundDrawable(new ColorDrawable(0));
        }
        View view = this.rootView;
        if (view != null) {
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.rootView);
            }
        } else {
            this.rootView = layoutInflater.inflate(getLayoutRes(), viewGroup, false);
            initView(this.rootView);
            initListener(this.rootView);
        }
        return this.rootView;
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        int i;
        int i2;
        int i3;
        if (this.mDialog != null) {
            if (this.mWindow != null) {
                int i4 = -2;
                if (getWidthScale() <= 0.0d || (i3 = this.mWidthPixels) <= 0) {
                    i = -2;
                } else {
                    double d = i3;
                    double widthScale = getWidthScale();
                    Double.isNaN(d);
                    i = (int) (d * widthScale);
                }
                if (getHeightScale() > 0.0d && (i2 = this.mHeightPixels) > 0) {
                    double d2 = i2;
                    double heightScale = getHeightScale();
                    Double.isNaN(d2);
                    i4 = (int) (d2 * heightScale);
                }
                this.mWindow.setLayout(i, i4);
                this.mWindow.setDimAmount(getDimAmount());
            }
            this.mDialog.setCancelable(getCancelOutside());
            this.mDialog.setCanceledOnTouchOutside(getCancelOutside());
        }
        super.onStart();
    }
}

