package com.slzhibo.library.utils.permission.callback;

import com.slzhibo.library.utils.permission.request.ForwardScope;

import java.util.List;

public interface ForwardToSettingsCallback {
    void onForwardToSettings(ForwardScope forwardScope, List<String> list);
}
