package com.slzhibo.library.ui.view.gift;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.content.Context;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.slzhibo.library.utils.UserInfoManager;

import java.util.ArrayList;
import java.util.Iterator;

public class GiftAnimManage implements GiftFrameLayout.LeftGiftAnimDismissListener {
    private final int curDisplayMode = 1;
    private ICustomAnim custormAnim;
    private boolean isHideMode;
    private final Context mContext;
    private int mGiftLayoutMaxNums;
    private LinearLayout mGiftLayoutParent;
    private final ArrayList<GiftAnimModel> mGiftQueue;
    private OnLeftGiftAnimListener onDeleteGiftAnimListener;

    public interface OnLeftGiftAnimListener {
        void onClick(GiftAnimModel giftAnimModel);

        void onDelete(GiftAnimModel giftAnimModel);
    }

    public GiftAnimManage(Context context) {
        this.mContext = context;
        this.mGiftQueue = new ArrayList<>();
    }

    public GiftAnimManage setCustomAnim(ICustomAnim iCustomAnim) {
        this.custormAnim = iCustomAnim;
        return this;
    }

    public GiftAnimManage setHideMode(boolean z) {
        this.isHideMode = z;
        return this;
    }

    public void loadGift(GiftAnimModel giftAnimModel) {
        loadGift(giftAnimModel, true);
    }

    public void loadReceiveGift(GiftAnimModel giftAnimModel) {
        loadReceiveGift(giftAnimModel, true);
    }

    private void loadGift(GiftAnimModel giftAnimModel, boolean z) {
        if (this.mGiftQueue != null) {
            if (z) {
                if (this.mGiftLayoutParent != null) {
                    for (int i = 0; i < this.mGiftLayoutParent.getChildCount(); i++) {
                        GiftFrameLayout giftFrameLayout = (GiftFrameLayout) this.mGiftLayoutParent.getChildAt(i);
                        if (giftFrameLayout.isShowing() && TextUtils.equals(giftFrameLayout.getCurrentGiftId(), giftAnimModel.getGiftId()) && TextUtils.equals(giftFrameLayout.getCurrentSendUserId(), giftAnimModel.getSendUserId())) {
                            if (giftAnimModel.getJumpCombo() > 0) {
                                giftFrameLayout.setGiftAddCount(giftAnimModel.getJumpCombo());
                            } else {
                                giftFrameLayout.setGiftCount(giftAnimModel.getGiftCount());
                                giftFrameLayout.setJumpCombo(0);
                            }
                            giftFrameLayout.setSendGiftTime(giftAnimModel.getSendGiftTime().longValue());
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
            addGiftQueue(giftAnimModel, z);
        }
    }

    private void loadReceiveGift(GiftAnimModel giftAnimModel, boolean z) {
        if (this.mGiftQueue != null) {
            if (z) {
                if (this.mGiftLayoutParent != null) {
                    for (int i = 0; i < this.mGiftLayoutParent.getChildCount(); i++) {
                        GiftFrameLayout giftFrameLayout = (GiftFrameLayout) this.mGiftLayoutParent.getChildAt(i);
                        if (giftFrameLayout.isShowing() && TextUtils.equals(giftFrameLayout.getCurrentGiftId(), giftAnimModel.getGiftId()) && TextUtils.equals(giftFrameLayout.getCurrentSendUserId(), giftAnimModel.getSendUserId())) {
                            giftFrameLayout.setGiftCount(giftAnimModel.getGiftCount());
                            giftFrameLayout.setJumpCombo(0);
                            giftFrameLayout.setSendGiftTime(giftAnimModel.getSendGiftTime().longValue());
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
            addGiftQueue(giftAnimModel, z);
        }
    }

    private void addGiftQueue(GiftAnimModel giftAnimModel, boolean z) {
        ArrayList<GiftAnimModel> arrayList;
        ArrayList<GiftAnimModel> arrayList2 = this.mGiftQueue;
        if (arrayList2 != null && arrayList2.size() == 0) {
            this.mGiftQueue.add(giftAnimModel);
            showGift(null);
        } else if (z) {
            boolean z2 = false;
            Iterator<GiftAnimModel> it2 = this.mGiftQueue.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                GiftAnimModel next = it2.next();
                if (TextUtils.equals(next.getGiftId(), giftAnimModel.getGiftId()) && TextUtils.equals(next.getSendUserId(), giftAnimModel.getSendUserId())) {
                    next.setGiftCount(next.getGiftCount() + giftAnimModel.getGiftCount());
                    z2 = true;
                    break;
                }
            }
            if (!z2 && (arrayList = this.mGiftQueue) != null) {
                arrayList.add(giftAnimModel);
                showGift(null);
            }
        } else {
            ArrayList<GiftAnimModel> arrayList3 = this.mGiftQueue;
            if (arrayList3 != null) {
                arrayList3.add(giftAnimModel);
                showGift(null);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void showGift(GiftAnimModel giftAnimModel) {
        boolean z;
        if (this.mGiftLayoutParent != null) {
            int i = 0;
            if (this.mGiftLayoutParent.getChildCount() < this.mGiftLayoutMaxNums) {
                GiftFrameLayout giftFrameLayout = new GiftFrameLayout(this.mContext);
                giftFrameLayout.setIndex(0);
                giftFrameLayout.setGiftAnimationListener(this);
                giftFrameLayout.setHideMode(this.isHideMode);
                if (this.curDisplayMode == 0) {
                    ((RelativeLayout.LayoutParams) this.mGiftLayoutParent.getLayoutParams()).addRule(12);
                    this.mGiftLayoutParent.addView(giftFrameLayout);
                } else if (this.curDisplayMode == 1) {
                    ((RelativeLayout.LayoutParams) this.mGiftLayoutParent.getLayoutParams()).addRule(12, 0);
                    this.mGiftLayoutParent.addView(giftFrameLayout, 0);
                } else {
                    ((RelativeLayout.LayoutParams) this.mGiftLayoutParent.getLayoutParams()).addRule(12);
                    this.mGiftLayoutParent.addView(giftFrameLayout);
                }
                if (giftAnimModel != null) {
                    z = giftFrameLayout.setGift(giftAnimModel);
                } else {
                    z = giftFrameLayout.setGift(getGift());
                }
                if (z) {
                    giftFrameLayout.startAnimation(this.custormAnim);
                }
            } else {
                GiftAnimModel gift = getGift();
                if (gift != null) {
                    if (TextUtils.equals(gift.getSendUserId(), UserInfoManager.getInstance().getUserId())) {
                        GiftFrameLayout giftFrameLayout2 = (GiftFrameLayout) this.mGiftLayoutParent.getChildAt(0);
                        giftFrameLayout2.setDelete(true);
                        GiftAnimModel gift2 = giftFrameLayout2.getGift();
                        if (this.onDeleteGiftAnimListener instanceof OnLeftGiftAnimListener) {
                            this.onDeleteGiftAnimListener.onDelete(gift2);
                        }
                        giftFrameLayout2.onRelease();
                        this.mGiftLayoutParent.removeViewAt(0);
                        showGift(gift);
                    } else if (TextUtils.equals(gift.getEffectType(), "2")) {
                        while (true) {
                            if (i >= this.mGiftLayoutParent.getChildCount()) {
                                break;
                            }
                            GiftAnimModel gift3 = ((GiftFrameLayout) this.mGiftLayoutParent.getChildAt(i)).getGift();
                            if (TextUtils.equals(gift3.getSendUserId(), UserInfoManager.getInstance().getUserId()) || !TextUtils.equals(gift3.getEffectType(), "1")) {
                                i++;
                            } else {
                                ((GiftFrameLayout) this.mGiftLayoutParent.getChildAt(i)).setDelete(true);
                                ((GiftFrameLayout) this.mGiftLayoutParent.getChildAt(i)).onRelease();
                                if (this.onDeleteGiftAnimListener instanceof OnLeftGiftAnimListener) {
                                    this.onDeleteGiftAnimListener.onDelete(gift3);
                                }
                                this.mGiftLayoutParent.removeViewAt(i);
                                showGift(gift);
                            }
                        }
                    }
                }
            }
        }
    }

    public GiftAnimManage setOnLeftGiftAnimListener(OnLeftGiftAnimListener onLeftGiftAnimListener) {
        this.onDeleteGiftAnimListener = onLeftGiftAnimListener;
        return this;
    }

    private synchronized GiftAnimModel getGift() {
        GiftAnimModel giftAnimModel;
        giftAnimModel = null;
        if (this.mGiftQueue.size() != 0) {
            giftAnimModel = this.mGiftQueue.get(0);
            this.mGiftQueue.remove(0);
        }
        return giftAnimModel;
    }

    @Override // com.slzhibo.library.ui.view.gift.GiftFrameLayout.LeftGiftAnimDismissListener
    public void dismiss(GiftFrameLayout giftFrameLayout) {
        reStartAnimation(giftFrameLayout, giftFrameLayout.getIndex());
    }

    @Override // com.slzhibo.library.ui.view.gift.GiftFrameLayout.LeftGiftAnimDismissListener
    public void onClick(GiftAnimModel giftAnimModel) {
        OnLeftGiftAnimListener onLeftGiftAnimListener = this.onDeleteGiftAnimListener;
        if (onLeftGiftAnimListener != null) {
            onLeftGiftAnimListener.onClick(giftAnimModel);
        }
    }

    private void reStartAnimation(final GiftFrameLayout giftFrameLayout, int i) {
        giftFrameLayout.setCurrentShowStatus(false);
        AnimatorSet endAnimation = giftFrameLayout.endAnimation(this.custormAnim);
        if (endAnimation != null) {
            endAnimation.addListener(new AnimatorListenerAdapter() {
                /* class com.slzhibo.library.ui.view.gift.GiftAnimManage.AnonymousClass1 */

                public void onAnimationEnd(Animator animator) {
                    giftFrameLayout.CurrentEndStatus(true);
                    giftFrameLayout.setGiftViewEndVisibility(GiftAnimManage.this.isEmpty());
                    GiftAnimManage.this.mGiftLayoutParent.removeView(giftFrameLayout);
                    if (!GiftAnimManage.this.isEmpty()) {
                        GiftAnimManage.this.showGift(null);
                    }
                }
            });
        }
    }

    public GiftAnimManage setGiftLayout(LinearLayout linearLayout, @NonNull int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("GiftFrameLayout数量必须大于0");
        } else if (linearLayout.getChildCount() > 0) {
            return this;
        } else {
            this.mGiftLayoutParent = linearLayout;
            this.mGiftLayoutMaxNums = i;
            LayoutTransition layoutTransition = new LayoutTransition();
            layoutTransition.setAnimator(0, layoutTransition.getAnimator(0));
            layoutTransition.setAnimator(2, layoutTransition.getAnimator(2));
            layoutTransition.setAnimator(3, layoutTransition.getAnimator(0));
            layoutTransition.setAnimator(1, layoutTransition.getAnimator(3));
            this.mGiftLayoutParent.setLayoutTransition(layoutTransition);
            return this;
        }
    }

    public synchronized void cleanAll() {
        if (this.mGiftLayoutParent != null) {
            if (this.mGiftQueue != null) {
                this.mGiftQueue.clear();
            }
            for (int i = 0; i < this.mGiftLayoutParent.getChildCount(); i++) {
                GiftFrameLayout giftFrameLayout = (GiftFrameLayout) this.mGiftLayoutParent.getChildAt(i);
                if (giftFrameLayout != null) {
                    if (giftFrameLayout.getGift() != null) {
                        giftFrameLayout.getGift().setAnimationListener(null);
                    }
                    giftFrameLayout.clearHandler();
                    giftFrameLayout.firstHideLayout();
                }
            }
            this.mGiftLayoutParent.removeAllViews();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized boolean isEmpty() {
        return this.mGiftQueue == null || this.mGiftQueue.isEmpty();
    }
}
