package com.slzhibo.library.utils.live;

import android.animation.LayoutTransition;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.LinearLayout;

import com.slzhibo.library.model.LeftAnimEntity;
import com.slzhibo.library.ui.interfaces.OnAnimPlayListener;
import com.slzhibo.library.ui.view.custom.GuardOpenDanmuView;
import com.slzhibo.library.ui.view.custom.LeftAnimView;
import com.slzhibo.library.ui.view.custom.NobilityEnterDanmuView;
import com.slzhibo.library.ui.view.custom.NobilityOpenDanmuView;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.NumberUtils;

import java.util.concurrent.ConcurrentLinkedQueue;

public class LeftAnimManager implements OnAnimPlayListener {
    private ConcurrentLinkedQueue<LeftAnimEntity> leftAnimMsgQueue;
    private LinearLayout llAnimParent;
    private final int mAnimLayoutMaxNums = 2;
    private final Context mContext;
    private final Handler mainHandler;

    @Override // com.slzhibo.library.ui.interfaces.OnAnimPlayListener
    public void onStart() {
    }

    public void addLeftAnim(LeftAnimEntity leftAnimEntity) {
        synchronized (LeftAnimManager.class) {
            if (this.leftAnimMsgQueue == null) {
                this.leftAnimMsgQueue = new ConcurrentLinkedQueue<>();
            }
            if (this.leftAnimMsgQueue.size() == 9999) {
                this.leftAnimMsgQueue.poll();
            }
            this.leftAnimMsgQueue.offer(leftAnimEntity);
        }
        handlerMainPost(new Runnable() {
            /* class com.slzhibo.library.utils.live.$$Lambda$LeftAnimManager$BToXYWQL_z223rxAjXRNqRKoEOk */

            public final void run() {
                LeftAnimManager.this.lambda$addLeftAnim$0$LeftAnimManager();
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: getAnim */
    public void lambda$addLeftAnim$0$LeftAnimManager() {
        if (hasAnim()) {
            LeftAnimEntity poll = this.leftAnimMsgQueue.poll();
            int childCount = this.llAnimParent.getChildCount();
            LeftAnimView leftAnimView = null;
            if (childCount == 0) {
                switch (poll.leftAnimType) {
                    case ConstantUtils.LEFT_ANIM_OPEN_NOBILITY_TYPE:
                        leftAnimView = new NobilityOpenDanmuView(this.mContext);
                        break;
                    case ConstantUtils.LEFT_ANIM_OPEN_GUARD_TYPE:
                        leftAnimView = new GuardOpenDanmuView(this.mContext);
                        break;
                    case ConstantUtils.LEFT_ANIM_ENTER_NOBILITY_TYPE:
                        leftAnimView = new NobilityEnterDanmuView(this.mContext);
                        break;
                }
                leftAnimView.setOnAnimPlayListener(this);
                this.llAnimParent.addView(leftAnimView);
                leftAnimView.addItemInfo(poll);
                return;
            }
            int i = 0;
            if (childCount < this.mAnimLayoutMaxNums) {
                switch (poll.leftAnimType) {
                    case ConstantUtils.LEFT_ANIM_OPEN_NOBILITY_TYPE:
                        leftAnimView = new NobilityOpenDanmuView(this.mContext);
                        leftAnimView.setOnAnimPlayListener(this);
                        this.llAnimParent.addView(leftAnimView);
                        leftAnimView.addItemInfo(poll);
                        return;
                    case ConstantUtils.LEFT_ANIM_OPEN_GUARD_TYPE:
                        leftAnimView = new GuardOpenDanmuView(this.mContext);
                        leftAnimView.setOnAnimPlayListener(this);
                        this.llAnimParent.addView(leftAnimView);
                        leftAnimView.addItemInfo(poll);
                        return;
                    case ConstantUtils.LEFT_ANIM_ENTER_NOBILITY_TYPE:
                        while (i < childCount) {
                            LeftAnimView leftAnimView2 = (LeftAnimView) this.llAnimParent.getChildAt(i);
                            if (leftAnimView2.animType != 2310) {
                                i++;
                            } else if (poll.isLocalAnim()) {
                                this.llAnimParent.removeView(leftAnimView2);
                                NobilityEnterDanmuView nobilityEnterDanmuView = new NobilityEnterDanmuView(this.mContext);
                                nobilityEnterDanmuView.setOnAnimPlayListener(this);
                                this.llAnimParent.addView(nobilityEnterDanmuView);
                                nobilityEnterDanmuView.addItemInfo(poll);
                                return;
                            } else {
                                this.leftAnimMsgQueue.offer(poll);
                                return;
                            }
                        }
                        leftAnimView = new NobilityEnterDanmuView(this.mContext);
                        leftAnimView.setOnAnimPlayListener(this);
                        this.llAnimParent.addView(leftAnimView);
                        leftAnimView.addItemInfo(poll);
                        return;
                    default:
                        leftAnimView.setOnAnimPlayListener(this);
                        this.llAnimParent.addView(leftAnimView);
                        leftAnimView.addItemInfo(poll);
                        return;
                }
            } else if (poll.isLocalAnim()) {
                switch (poll.leftAnimType) {
                    case ConstantUtils.LEFT_ANIM_OPEN_NOBILITY_TYPE:
                        leftAnimView = new NobilityOpenDanmuView(this.mContext);
                        break;
                    case ConstantUtils.LEFT_ANIM_OPEN_GUARD_TYPE:
                        leftAnimView = new GuardOpenDanmuView(this.mContext);
                        break;
                    case ConstantUtils.LEFT_ANIM_ENTER_NOBILITY_TYPE:
                        for (int i2 = 0; i2 < childCount; i2++) {
                            LeftAnimView leftAnimView3 = (LeftAnimView) this.llAnimParent.getChildAt(i2);
                            if (leftAnimView3.animType == 2310) {
                                this.llAnimParent.removeView(leftAnimView3);
                                NobilityEnterDanmuView nobilityEnterDanmuView2 = new NobilityEnterDanmuView(this.mContext);
                                nobilityEnterDanmuView2.setOnAnimPlayListener(this);
                                this.llAnimParent.addView(nobilityEnterDanmuView2);
                                nobilityEnterDanmuView2.addItemInfo(poll);
                                return;
                            }
                        }
                        leftAnimView = new NobilityEnterDanmuView(this.mContext);
                        break;
                }
                this.llAnimParent.removeViewAt(0);
                leftAnimView.setOnAnimPlayListener(this);
                this.llAnimParent.addView(leftAnimView);
                leftAnimView.addItemInfo(poll);
            } else {
                switch (poll.leftAnimType) {
                    case ConstantUtils.LEFT_ANIM_OPEN_NOBILITY_TYPE:
                        while (i < childCount) {
                            LeftAnimView leftAnimView4 = (LeftAnimView) this.llAnimParent.getChildAt(i);
                            if (!leftAnimView4.leftAnimEntity.isLocalAnim()) {
                                if (leftAnimView4.animType != 2308) {
                                    NobilityOpenDanmuView nobilityOpenDanmuView = new NobilityOpenDanmuView(this.mContext);
                                    this.llAnimParent.removeView(leftAnimView4);
                                    nobilityOpenDanmuView.setOnAnimPlayListener(this);
                                    this.llAnimParent.addView(nobilityOpenDanmuView);
                                    nobilityOpenDanmuView.addItemInfo(poll);
                                    return;
                                } else if (poll.nobilityType > leftAnimView4.leftAnimEntity.nobilityType) {
                                    NobilityOpenDanmuView nobilityOpenDanmuView2 = new NobilityOpenDanmuView(this.mContext);
                                    this.llAnimParent.removeView(leftAnimView4);
                                    nobilityOpenDanmuView2.setOnAnimPlayListener(this);
                                    this.llAnimParent.addView(nobilityOpenDanmuView2);
                                    nobilityOpenDanmuView2.addItemInfo(poll);
                                    return;
                                }
                            }
                            i++;
                        }
                        return;
                    case ConstantUtils.LEFT_ANIM_OPEN_GUARD_TYPE:
                        while (i < childCount) {
                            LeftAnimView leftAnimView5 = (LeftAnimView) this.llAnimParent.getChildAt(i);
                            if (!leftAnimView5.leftAnimEntity.isLocalAnim()) {
                                int i3 = leftAnimView5.animType;
                                if (i3 == 2310) {
                                    GuardOpenDanmuView guardOpenDanmuView = new GuardOpenDanmuView(this.mContext);
                                    this.llAnimParent.removeView(leftAnimView5);
                                    guardOpenDanmuView.setOnAnimPlayListener(this);
                                    this.llAnimParent.addView(guardOpenDanmuView);
                                    guardOpenDanmuView.addItemInfo(poll);
                                    return;
                                } else if (i3 == 2309 && NumberUtils.string2int(poll.guardType) > NumberUtils.string2int(leftAnimView5.leftAnimEntity.guardType)) {
                                    GuardOpenDanmuView guardOpenDanmuView2 = new GuardOpenDanmuView(this.mContext);
                                    this.llAnimParent.removeView(leftAnimView5);
                                    guardOpenDanmuView2.setOnAnimPlayListener(this);
                                    this.llAnimParent.addView(guardOpenDanmuView2);
                                    guardOpenDanmuView2.addItemInfo(poll);
                                    return;
                                }
                            }
                            i++;
                        }
                        return;
                    case ConstantUtils.LEFT_ANIM_ENTER_NOBILITY_TYPE:
                        while (i < childCount) {
                            LeftAnimView leftAnimView6 = (LeftAnimView) this.llAnimParent.getChildAt(i);
                            if (leftAnimView6.animType != 2310 || leftAnimView6.leftAnimEntity.isLocalAnim() || leftAnimView6.leftAnimEntity.nobilityType >= poll.nobilityType) {
                                i++;
                            } else {
                                this.llAnimParent.removeView(leftAnimView6);
                                NobilityEnterDanmuView nobilityEnterDanmuView3 = new NobilityEnterDanmuView(this.mContext);
                                nobilityEnterDanmuView3.setOnAnimPlayListener(this);
                                this.llAnimParent.addView(nobilityEnterDanmuView3);
                                nobilityEnterDanmuView3.addItemInfo(poll);
                                return;
                            }
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private synchronized boolean hasAnim() {
        return this.leftAnimMsgQueue != null && !this.leftAnimMsgQueue.isEmpty();
    }

    public LeftAnimManager(Context context) {
        this.mContext = context;
        this.leftAnimMsgQueue = new ConcurrentLinkedQueue<>();
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    public LeftAnimManager setAnimLayout(LinearLayout linearLayout) {
        this.llAnimParent = linearLayout;
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setAnimator(0, layoutTransition.getAnimator(0));
        layoutTransition.setAnimator(2, layoutTransition.getAnimator(2));
        layoutTransition.setAnimator(3, layoutTransition.getAnimator(0));
        layoutTransition.setAnimator(1, layoutTransition.getAnimator(3));
        this.llAnimParent.setLayoutTransition(layoutTransition);
        return this;
    }

    @Override // com.slzhibo.library.ui.interfaces.OnAnimPlayListener
    public void onEnd(final LeftAnimView leftAnimView) {
        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.utils.live.-$$Lambda$LeftAnimManager$fZmTnDt2dVB0u9sUG3r8yb5gJjY
            @Override // java.lang.Runnable
            public final void run() {
                LeftAnimManager.this.lambda$onEnd$1$LeftAnimManager(leftAnimView);
            }
        });
    }

    public /* synthetic */ void lambda$onEnd$1$LeftAnimManager(LeftAnimView leftAnimView) {
        this.llAnimParent.removeView(leftAnimView);
        lambda$addLeftAnim$0$LeftAnimManager();
    }

    public synchronized void cleanAll() {
        if (this.llAnimParent != null) {
            if (this.leftAnimMsgQueue != null) {
                this.leftAnimMsgQueue.clear();
            }
            for (int i = 0; i < this.llAnimParent.getChildCount(); i++) {
                View childAt = this.llAnimParent.getChildAt(i);
                if (childAt instanceof LeftAnimView) {
                    ((LeftAnimView) childAt).onRelease();
                }
            }
            this.llAnimParent.removeAllViews();
            if (this.mainHandler != null) {
                this.mainHandler.removeCallbacksAndMessages(null);
            }
        }
    }

    private void handlerMainPost(Runnable runnable) {
        Handler handler = this.mainHandler;
        if (handler != null && runnable != null) {
            handler.post(runnable);
        }
    }
}
