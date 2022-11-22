package com.slzhibo.library.utils.permission.callback;

import com.slzhibo.library.utils.permission.request.ExplainScope;

import java.util.List;

public interface ExplainReasonCallback {
    void onExplainReason(ExplainScope explainScope, List<String> list);
}
