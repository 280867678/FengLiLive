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

import com.slzhibo.library.R;

public abstract class BaseBottomDialogFragment extends BaseRxDialogFragment {
    protected Dialog mDialog;
    protected Window mWindow;
    protected double maxHeightScale = 0.8d;
    public View rootView;

    /* access modifiers changed from: protected */
    public boolean getCancelOutside() {
        return true;
    }

    /* access modifiers changed from: protected */
    public double getHeightScale() {
        return 0.0d;
    }

    /* access modifiers changed from: protected */
    @LayoutRes
    public abstract int getLayoutRes();

    /* access modifiers changed from: protected */
    public void initListener(View view) {
    }

    /* access modifiers changed from: protected */
    public abstract void initView(View view);

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment, android.support.v4.app.Fragment, android.support.v4.app.DialogFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxDialogFragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.fq_BottomDialog);
    }

    @Override // android.support.v4.app.Fragment
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

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment, android.support.v4.app.Fragment, android.support.v4.app.DialogFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxDialogFragment
    public void onStart() {
        if (this.mDialog != null) {
            Window window = this.mWindow;
            if (window != null) {
                window.setLayout(-1, getDialogHeight());
                this.mWindow.setDimAmount(getDimAmount());
                this.mWindow.setGravity(80);
            }
            setDialogCancelable(getCancelOutside());
        }
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public int getDialogHeight() {
        int i;
        if (getHeightScale() <= 0.0d || (i = this.mHeightPixels) <= 0) {
            return -2;
        }
        return (int) (((double) i) * getHeightScale());
    }

    /* access modifiers changed from: protected */
    public void setDialogCancelable(boolean z) {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.setCancelable(z);
            this.mDialog.setCanceledOnTouchOutside(z);
        }
    }
}
