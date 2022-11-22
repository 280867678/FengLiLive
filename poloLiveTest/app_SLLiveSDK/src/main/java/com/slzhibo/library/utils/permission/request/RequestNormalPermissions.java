package com.slzhibo.library.utils.permission.request;

import com.slzhibo.library.utils.permission.PermissionX;
import com.slzhibo.library.utils.permission.callback.ExplainReasonCallbackWithBeforeParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RequestNormalPermissions extends BaseTask {
    RequestNormalPermissions(PermissionBuilder permissionBuilder) {
        super(permissionBuilder);
    }

    @Override // com.slzhibo.library.utils.permission.request.ChainTask
    public void request() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.pb.normalPermissions) {
            if (PermissionX.isGranted(this.pb.activity, str)) {
                this.pb.grantedPermissions.add(str);
            } else {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            finish();
            return;
        }
        PermissionBuilder permissionBuilder = this.pb;
        if (!permissionBuilder.explainReasonBeforeRequest || (permissionBuilder.explainReasonCallback == null && permissionBuilder.explainReasonCallbackWithBeforeParam == null)) {
            PermissionBuilder permissionBuilder2 = this.pb;
            permissionBuilder2.requestNow(permissionBuilder2.normalPermissions, this);
            return;
        }
        PermissionBuilder permissionBuilder3 = this.pb;
        permissionBuilder3.explainReasonBeforeRequest = false;
        permissionBuilder3.deniedPermissions.addAll(arrayList);
        PermissionBuilder permissionBuilder4 = this.pb;
        ExplainReasonCallbackWithBeforeParam explainReasonCallbackWithBeforeParam = permissionBuilder4.explainReasonCallbackWithBeforeParam;
        if (explainReasonCallbackWithBeforeParam != null) {
            explainReasonCallbackWithBeforeParam.onExplainReason(this.explainReasonScope, arrayList, true);
        } else {
            permissionBuilder4.explainReasonCallback.onExplainReason(this.explainReasonScope, arrayList);
        }
    }

    @Override // com.slzhibo.library.utils.permission.request.ChainTask
    public void requestAgain(List<String> list) {
        HashSet hashSet = new HashSet(this.pb.grantedPermissions);
        hashSet.addAll(list);
        this.pb.requestNow(hashSet, this);
    }
}
