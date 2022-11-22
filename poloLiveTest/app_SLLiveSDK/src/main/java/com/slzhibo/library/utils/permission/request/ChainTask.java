package com.slzhibo.library.utils.permission.request;

import java.util.List;

public interface ChainTask {
    void finish();

    ExplainScope getExplainScope();

    ForwardScope getForwardScope();

    void request();

    void requestAgain(List<String> list);
}
