package com.slzhibo.library.utils.permission.request;

import android.app.Dialog;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.slzhibo.library.utils.permission.callback.ExplainReasonCallback;
import com.slzhibo.library.utils.permission.callback.ExplainReasonCallbackWithBeforeParam;
import com.slzhibo.library.utils.permission.callback.ForwardToSettingsCallback;
import com.slzhibo.library.utils.permission.callback.RequestCallback;

import java.util.HashSet;
import java.util.Set;

public class PermissionBuilder {
    FragmentActivity activity;
    Dialog currentDialog;
    Set<String> deniedPermissions = new HashSet();
    boolean explainReasonBeforeRequest = false;
    ExplainReasonCallback explainReasonCallback;
    ExplainReasonCallbackWithBeforeParam explainReasonCallbackWithBeforeParam;
    Set<String> forwardPermissions = new HashSet();
    ForwardToSettingsCallback forwardToSettingsCallback;
    Fragment fragment;
    Set<String> grantedPermissions = new HashSet();
    Set<String> normalPermissions;
    Set<String> permanentDeniedPermissions = new HashSet();
    Set<String> permissionsWontRequest;
    RequestCallback requestCallback;
    boolean requireBackgroundLocationPermission;
    boolean showDialogCalled = false;
    Set<String> tempPermanentDeniedPermissions = new HashSet();

    public PermissionBuilder(FragmentActivity fragmentActivity, Fragment fragment2, Set<String> set, boolean z, Set<String> set2) {
        this.activity = fragmentActivity;
        this.fragment = fragment2;
        if (fragmentActivity == null && fragment2 != null) {
            this.activity = fragment2.getActivity();
        }
        this.normalPermissions = set;
        this.requireBackgroundLocationPermission = z;
        this.permissionsWontRequest = set2;
    }

    public void request(RequestCallback requestCallback2) {
        this.requestCallback = requestCallback2;
        RequestChain requestChain = new RequestChain();
        requestChain.addTaskToChain(new RequestNormalPermissions(this));
        requestChain.addTaskToChain(new RequestBackgroundLocationPermission(this));
        requestChain.runTask();
    }

    /* access modifiers changed from: package-private */
    public void requestNow(Set<String> set, ChainTask chainTask) {
        getInvisibleFragment().requestNow(this, set, chainTask);
    }

    /* access modifiers changed from: package-private */
    public void requestAccessBackgroundLocationNow(ChainTask chainTask) {
        getInvisibleFragment().requestAccessBackgroundLocationNow(this, chainTask);
    }

    private InvisibleFragment getInvisibleFragment() {
        FragmentManager fragmentManager;
        Fragment fragment2 = this.fragment;
        if (fragment2 != null) {
            fragmentManager = fragment2.getChildFragmentManager();
        } else {
            fragmentManager = this.activity.getSupportFragmentManager();
        }
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag("InvisibleFragment");
        if (findFragmentByTag != null) {
            return (InvisibleFragment) findFragmentByTag;
        }
        InvisibleFragment invisibleFragment = new InvisibleFragment();
        fragmentManager.beginTransaction().add(invisibleFragment, "InvisibleFragment").commitNowAllowingStateLoss();
        return invisibleFragment;
    }
}
