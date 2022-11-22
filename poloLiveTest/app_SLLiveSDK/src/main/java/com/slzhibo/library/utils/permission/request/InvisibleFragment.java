package com.slzhibo.library.utils.permission.request;

import android.app.Dialog;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.slzhibo.library.utils.permission.PermissionX;
import com.slzhibo.library.utils.permission.callback.ExplainReasonCallbackWithBeforeParam;

import java.util.ArrayList;
import java.util.Set;

public class InvisibleFragment extends Fragment {
    private PermissionBuilder pb;
    private ChainTask task;

    /* access modifiers changed from: package-private */
    public void requestNow(PermissionBuilder permissionBuilder, Set<String> set, ChainTask chainTask) {
        this.pb = permissionBuilder;
        this.task = chainTask;
        requestPermissions((String[]) set.toArray(new String[0]), 1);
    }

    /* access modifiers changed from: package-private */
    public void requestAccessBackgroundLocationNow(PermissionBuilder permissionBuilder, ChainTask chainTask) {
        this.pb = permissionBuilder;
        this.task = chainTask;
        requestPermissions(new String[]{"android.permission.ACCESS_BACKGROUND_LOCATION"}, 2);
    }

    @Override // android.support.v4.app.Fragment
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i == 1) {
            onRequestNormalPermissionsResult(strArr, iArr);
        } else if (i == 2) {
            onRequestBackgroundLocationPermissionResult();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 2 && checkForGC()) {
            this.task.requestAgain(new ArrayList(this.pb.forwardPermissions));
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        Dialog dialog;
        super.onDestroy();
        if (checkForGC() && (dialog = this.pb.currentDialog) != null && dialog.isShowing()) {
            this.pb.currentDialog.dismiss();
        }
    }

    private void onRequestNormalPermissionsResult(@NonNull String[] strArr, @NonNull int[] iArr) {
        if (checkForGC()) {
            this.pb.grantedPermissions.clear();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < strArr.length; i++) {
                String str = strArr[i];
                if (iArr[i] == 0) {
                    this.pb.grantedPermissions.add(str);
                    this.pb.deniedPermissions.remove(str);
                    this.pb.permanentDeniedPermissions.remove(str);
                } else if (shouldShowRequestPermissionRationale(str)) {
                    arrayList.add(strArr[i]);
                    this.pb.deniedPermissions.add(str);
                } else {
                    arrayList2.add(strArr[i]);
                    this.pb.permanentDeniedPermissions.add(str);
                    this.pb.deniedPermissions.remove(str);
                }
            }
            ArrayList<String> arrayList3 = new ArrayList();
            arrayList3.addAll(this.pb.deniedPermissions);
            arrayList3.addAll(this.pb.permanentDeniedPermissions);
            for (String str2 : arrayList3) {
                if (PermissionX.isGranted(getContext(), str2)) {
                    this.pb.deniedPermissions.remove(str2);
                    this.pb.grantedPermissions.add(str2);
                }
            }
            boolean z = true;
            if (this.pb.grantedPermissions.size() == this.pb.normalPermissions.size()) {
                this.task.finish();
                return;
            }
            PermissionBuilder permissionBuilder = this.pb;
            if (!(permissionBuilder.explainReasonCallback == null && permissionBuilder.explainReasonCallbackWithBeforeParam == null) && !arrayList.isEmpty()) {
                PermissionBuilder permissionBuilder2 = this.pb;
                ExplainReasonCallbackWithBeforeParam explainReasonCallbackWithBeforeParam = permissionBuilder2.explainReasonCallbackWithBeforeParam;
                if (explainReasonCallbackWithBeforeParam != null) {
                    explainReasonCallbackWithBeforeParam.onExplainReason(this.task.getExplainScope(), new ArrayList(this.pb.deniedPermissions), false);
                } else {
                    permissionBuilder2.explainReasonCallback.onExplainReason(this.task.getExplainScope(), new ArrayList(this.pb.deniedPermissions));
                }
                this.pb.tempPermanentDeniedPermissions.addAll(arrayList2);
            } else {
                if (this.pb.forwardToSettingsCallback != null && (!arrayList2.isEmpty() || !this.pb.tempPermanentDeniedPermissions.isEmpty())) {
                    this.pb.tempPermanentDeniedPermissions.clear();
                    this.pb.forwardToSettingsCallback.onForwardToSettings(this.task.getForwardScope(), new ArrayList(this.pb.permanentDeniedPermissions));
                }
                if (z || !this.pb.showDialogCalled) {
                    this.task.finish();
                }
                this.pb.showDialogCalled = false;
            }
            z = false;
            this.task.finish();
            this.pb.showDialogCalled = false;
        }
    }

    private void onRequestBackgroundLocationPermissionResult() {
        if (!checkForGC()) {
            return;
        }
        if (PermissionX.isGranted(getContext(), "android.permission.ACCESS_BACKGROUND_LOCATION")) {
            this.pb.grantedPermissions.add("android.permission.ACCESS_BACKGROUND_LOCATION");
            this.pb.deniedPermissions.remove("android.permission.ACCESS_BACKGROUND_LOCATION");
            this.pb.permanentDeniedPermissions.remove("android.permission.ACCESS_BACKGROUND_LOCATION");
            this.task.finish();
            return;
        }
        boolean shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale("android.permission.ACCESS_BACKGROUND_LOCATION");
        PermissionBuilder permissionBuilder = this.pb;
        boolean z = false;
        if (!(permissionBuilder.explainReasonCallback == null && permissionBuilder.explainReasonCallbackWithBeforeParam == null) && shouldShowRequestPermissionRationale) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("android.permission.ACCESS_BACKGROUND_LOCATION");
            PermissionBuilder permissionBuilder2 = this.pb;
            ExplainReasonCallbackWithBeforeParam explainReasonCallbackWithBeforeParam = permissionBuilder2.explainReasonCallbackWithBeforeParam;
            if (explainReasonCallbackWithBeforeParam != null) {
                explainReasonCallbackWithBeforeParam.onExplainReason(this.task.getExplainScope(), arrayList, false);
            } else {
                permissionBuilder2.explainReasonCallback.onExplainReason(this.task.getExplainScope(), arrayList);
            }
        } else if (this.pb.forwardToSettingsCallback == null || shouldShowRequestPermissionRationale) {
            z = true;
        } else {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add("android.permission.ACCESS_BACKGROUND_LOCATION");
            this.pb.forwardToSettingsCallback.onForwardToSettings(this.task.getForwardScope(), arrayList2);
        }
        if (z || !this.pb.showDialogCalled) {
            this.task.finish();
        }
    }

    private boolean checkForGC() {
        if (this.pb != null && this.task != null) {
            return true;
        }
        Log.w("PermissionX", "PermissionBuilder and ChainTask should not be null at this time, so we can do nothing in this case.");
        return false;
    }
}
