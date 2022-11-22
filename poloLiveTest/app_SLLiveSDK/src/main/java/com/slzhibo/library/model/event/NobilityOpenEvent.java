package com.slzhibo.library.model.event;

public class NobilityOpenEvent extends BaseEvent {
    public String accountBalance;
    public boolean isOpenSuccess = false;
    public int nobilityType;
    public String toastTips;

    public NobilityOpenEvent() {
    }

    public NobilityOpenEvent(int i) {
        this.nobilityType = i;
    }

    public NobilityOpenEvent(boolean z, String str, String str2) {
        this.isOpenSuccess = z;
        this.toastTips = str;
        this.accountBalance = str2;
    }
}
