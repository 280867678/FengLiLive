package com.slzhibo.library.utils.permission.request;

import com.slzhibo.library.utils.permission.PermissionX;
import com.slzhibo.library.utils.permission.callback.RequestCallback;

import java.util.ArrayList;

/* access modifiers changed from: package-private */
public abstract class BaseTask implements ChainTask {
    ExplainScope explainReasonScope = new ExplainScope(this.pb, this);
    ForwardScope forwardToSettingsScope = new ForwardScope(this.pb, this);
    protected ChainTask next;
    protected PermissionBuilder pb;

    BaseTask(PermissionBuilder permissionBuilder) {
        this.pb = permissionBuilder;
    }

    @Override // com.slzhibo.library.utils.permission.request.ChainTask
    public ExplainScope getExplainScope() {
        return this.explainReasonScope;
    }

    @Override // com.slzhibo.library.utils.permission.request.ChainTask
    public ForwardScope getForwardScope() {
        return this.forwardToSettingsScope;
    }

    @Override // com.slzhibo.library.utils.permission.request.ChainTask
    public void finish() {
        ChainTask chainTask = this.next;
        if (chainTask != null) {
            chainTask.request();
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.pb.deniedPermissions);
        arrayList.addAll(this.pb.permanentDeniedPermissions);
        arrayList.addAll(this.pb.permissionsWontRequest);
        PermissionBuilder permissionBuilder = this.pb;
        if (permissionBuilder.requireBackgroundLocationPermission) {
            if (PermissionX.isGranted(permissionBuilder.activity, "android.permission.ACCESS_BACKGROUND_LOCATION")) {
                this.pb.grantedPermissions.add("android.permission.ACCESS_BACKGROUND_LOCATION");
            } else {
                arrayList.add("android.permission.ACCESS_BACKGROUND_LOCATION");
            }
        }
        RequestCallback requestCallback = this.pb.requestCallback;
        if (requestCallback != null) {
            requestCallback.onResult(arrayList.isEmpty(), new ArrayList(this.pb.grantedPermissions), arrayList);
        }
    }
}
