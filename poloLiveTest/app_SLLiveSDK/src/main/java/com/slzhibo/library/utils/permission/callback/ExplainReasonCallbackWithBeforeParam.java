package com.slzhibo.library.utils.permission.callback;

import com.slzhibo.library.utils.permission.request.ExplainScope;

import java.util.List;

public interface ExplainReasonCallbackWithBeforeParam {
    void onExplainReason(ExplainScope explainScope, List<String> list, boolean z);
}
