package com.slzhibo.library.utils.permission.request;

import com.slzhibo.library.utils.permission.PermissionX;
import com.slzhibo.library.utils.permission.callback.ExplainReasonCallbackWithBeforeParam;

import java.util.ArrayList;
import java.util.List;

public class RequestBackgroundLocationPermission extends BaseTask {
    RequestBackgroundLocationPermission(PermissionBuilder permissionBuilder) {
        super(permissionBuilder);
    }

    @Override // com.slzhibo.library.utils.permission.request.ChainTask
    public void request() {
        PermissionBuilder permissionBuilder = this.pb;
        if (permissionBuilder.requireBackgroundLocationPermission) {
            boolean isGranted = PermissionX.isGranted(permissionBuilder.activity, "android.permission.ACCESS_FINE_LOCATION");
            boolean isGranted2 = PermissionX.isGranted(this.pb.activity, "android.permission.ACCESS_COARSE_LOCATION");
            if (isGranted || isGranted2) {
                PermissionBuilder permissionBuilder2 = this.pb;
                if (permissionBuilder2.explainReasonCallback == null && permissionBuilder2.explainReasonCallbackWithBeforeParam == null) {
                    requestAgain(null);
                    return;
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add("android.permission.ACCESS_BACKGROUND_LOCATION");
                PermissionBuilder permissionBuilder3 = this.pb;
                ExplainReasonCallbackWithBeforeParam explainReasonCallbackWithBeforeParam = permissionBuilder3.explainReasonCallbackWithBeforeParam;
                if (explainReasonCallbackWithBeforeParam != null) {
                    explainReasonCallbackWithBeforeParam.onExplainReason(this.explainReasonScope, arrayList, true);
                    return;
                } else {
                    permissionBuilder3.explainReasonCallback.onExplainReason(this.explainReasonScope, arrayList);
                    return;
                }
            }
        }
        finish();
    }

    @Override // com.slzhibo.library.utils.permission.request.ChainTask
    public void requestAgain(List<String> list) {
        this.pb.requestAccessBackgroundLocationNow(this);
    }
}
