package com.slzhibo.library.ui.interfaces;

import com.slzhibo.library.model.MyAccountEntity;

public interface OnHJProductCallback {
    void onBuyProductClickListener();

    void onBuyProductSuccessCallback(MyAccountEntity myAccountEntity);

    void onProductOperationListener(int i);
}
