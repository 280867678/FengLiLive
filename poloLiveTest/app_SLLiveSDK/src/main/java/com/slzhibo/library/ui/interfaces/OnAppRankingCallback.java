package com.slzhibo.library.ui.interfaces;

import java.util.List;

public interface OnAppRankingCallback {
    void onAppRankingFail();

    void onAppRankingSuccess(List<String> list, List<String> list2);
}
