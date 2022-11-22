package com.slzhibo.library.ui.view.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.slzhibo.library.R;
import com.slzhibo.library.model.YYLinkApplyEntity;
import com.slzhibo.library.ui.adapter.YYLinkSeatAdapter;
import com.slzhibo.library.ui.interfaces.YYLinkCallback;
import com.slzhibo.library.ui.view.widget.MicVoiceView;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.NumberUtils;
import com.slzhibo.library.utils.StringUtils;
import com.slzhibo.library.utils.live.SimpleRxObserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class YYLinkSeatListView extends RelativeLayout {
    private MicVoiceView anchorMicView;
    private int currentLinkMode;
    private boolean isAnchorMode;
    private ImageView ivAnchorAvatar;
    private ImageView ivAnchorAvatar_2;
    private ImageView ivAnchorMute;
    private ImageView ivAnchorMuteBg;
    private ImageView ivLock;
    private ImageView ivMute;
    private ImageView ivMuteBg;
    private ImageView ivSingleMute;
    private ImageView ivSingleMuteBg;
    private ImageView ivUserAvatar_2;
    private YYLinkCallback linkCallback;
    private LinearLayout llLinkSeatBg_1;
    private LinearLayout llLinkSeatBg_2;
    private Context mContext;
    private RecyclerView recyclerView;
    private YYLinkSeatAdapter seatAdapter;
    private MicVoiceView singleMicView;
    private TextView tvAnchorLike;
    private TextView tvAnchorLike_2;
    private TextView tvAnchorName;
    private TextView tvAnchorName_2;
    private TextView tvUserLike_2;
    private TextView tvUserName_2;
    private MicVoiceView userMicView;

    public YYLinkSeatListView(Context context) {
        this(context, null);
    }

    public YYLinkSeatListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isAnchorMode = false;
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        RelativeLayout.inflate(this.mContext, R.layout.fq_layout_yy_link_seat_view, this);
        this.llLinkSeatBg_1 = (LinearLayout) findViewById(R.id.ll_link_seat_bg_1);
        this.ivAnchorAvatar = (ImageView) findViewById(R.id.iv_anchor_avatar);
        this.tvAnchorName = (TextView) findViewById(R.id.tv_anchor_name);
        this.llLinkSeatBg_2 = (LinearLayout) findViewById(R.id.ll_link_seat_bg_2);
        this.ivAnchorAvatar_2 = (ImageView) findViewById(R.id.iv_anchor_avatar_2);
        this.tvAnchorName_2 = (TextView) findViewById(R.id.tv_anchor_name_2);
        this.tvAnchorLike_2 = (TextView) findViewById(R.id.tv_anchor_like_2);
        this.ivUserAvatar_2 = (ImageView) findViewById(R.id.iv_user_avatar);
        this.tvUserName_2 = (TextView) findViewById(R.id.tv_user_name);
        this.tvUserLike_2 = (TextView) findViewById(R.id.tv_user_like);
        this.recyclerView = (RecyclerView) findViewById(R.id.rv_seat);
        this.ivMute = (ImageView) findViewById(R.id.iv_mute);
        this.ivLock = (ImageView) findViewById(R.id.iv_lock);
        this.anchorMicView = (MicVoiceView) findViewById(R.id.anchor_mic_view);
        this.userMicView = (MicVoiceView) findViewById(R.id.user_mic_view);
        this.ivAnchorMute = (ImageView) findViewById(R.id.iv_anchor_mute);
        this.ivAnchorMuteBg = (ImageView) findViewById(R.id.iv_anchor_mute_bg);
        this.ivMuteBg = (ImageView) findViewById(R.id.iv_mute_bg);
        this.singleMicView = (MicVoiceView) findViewById(R.id.single_mic_view);
        this.ivSingleMuteBg = (ImageView) findViewById(R.id.iv_single_mute_bg);
        this.ivSingleMute = (ImageView) findViewById(R.id.iv_single_mute);
        this.tvAnchorLike = (TextView) findViewById(R.id.tv_anchor_like);
        initSeatAdapter();
        this.ivAnchorAvatar.setOnClickListener(new OnClickListener() {
            /* class com.slzhibo.library.ui.view.custom.YYLinkSeatListView.AnonymousClass1 */

            public void onClick(View view) {
                if (YYLinkSeatListView.this.linkCallback != null && YYLinkSeatListView.this.getSeatAdapterData().size() >= 1) {
                    YYLinkApplyEntity anchorEntityByDoubleLinkMode = YYLinkSeatListView.this.getAnchorEntityByDoubleLinkMode();
                    if (!TextUtils.isEmpty(anchorEntityByDoubleLinkMode.userId) || YYLinkSeatListView.this.isAnchorMode) {
                        YYLinkSeatListView.this.linkCallback.onClickLinkRTCUserListener(YYLinkSeatListView.this.isAnchorMode ? 2 : 3, anchorEntityByDoubleLinkMode);
                    }
                }
            }
        });
        this.ivAnchorAvatar_2.setOnClickListener(new OnClickListener() {
            /* class com.slzhibo.library.ui.view.custom.YYLinkSeatListView.AnonymousClass2 */

            public void onClick(View view) {
                if (YYLinkSeatListView.this.linkCallback != null) {
                    int i = 2;
                    if (YYLinkSeatListView.this.getSeatAdapterData().size() >= 2) {
                        YYLinkApplyEntity anchorEntityByDoubleLinkMode = YYLinkSeatListView.this.getAnchorEntityByDoubleLinkMode();
                        if (!TextUtils.isEmpty(anchorEntityByDoubleLinkMode.userId) || YYLinkSeatListView.this.isAnchorMode) {
                            YYLinkCallback yYLinkCallback = YYLinkSeatListView.this.linkCallback;
                            if (!YYLinkSeatListView.this.isAnchorMode) {
                                i = 3;
                            }
                            yYLinkCallback.onClickLinkRTCUserListener(i, anchorEntityByDoubleLinkMode);
                        }
                    }
                }
            }
        });
        this.ivUserAvatar_2.setOnClickListener(new OnClickListener() {
            /* class com.slzhibo.library.ui.view.custom.YYLinkSeatListView.AnonymousClass3 */

            public void onClick(View view) {
                if (YYLinkSeatListView.this.getSeatAdapterData().size() >= 2) {
                    YYLinkApplyEntity userEntityByDoubleLinkMode = YYLinkSeatListView.this.getUserEntityByDoubleLinkMode();
                    if (TextUtils.isEmpty(userEntityByDoubleLinkMode.userId) && !YYLinkSeatListView.this.isAnchorMode) {
                        return;
                    }
                    if (TextUtils.isEmpty(userEntityByDoubleLinkMode.userId)) {
                        if (YYLinkSeatListView.this.linkCallback != null) {
                            YYLinkSeatListView.this.linkCallback.onClickLinkRTCUserListener(4, userEntityByDoubleLinkMode);
                        }
                    } else if (YYLinkSeatListView.this.linkCallback != null) {
                        YYLinkSeatListView.this.linkCallback.onClickLinkRTCUserListener(YYLinkSeatListView.this.isAnchorMode ? 1 : 3, userEntityByDoubleLinkMode);
                    }
                }
            }
        });
    }

    public void initAudienceSeatData(final List<YYLinkApplyEntity> list) {
        int i = 0;
        this.isAnchorMode = false;
        if (list == null || list.isEmpty()) {
            while (i < 8) {
                YYLinkApplyEntity yYLinkApplyEntity = new YYLinkApplyEntity();
                yYLinkApplyEntity.userId = null;
                i++;
                yYLinkApplyEntity.seat = String.valueOf(i);
                yYLinkApplyEntity.userName = this.mContext.getString(R.string.fq_text_list_empty_waiting);
                yYLinkApplyEntity.isQuiet = "0";
                yYLinkApplyEntity.likeCount = "0";
                yYLinkApplyEntity.seatStatus = "1";
                list.add(yYLinkApplyEntity);
            }
        }
        initDefaultSeatDataList(list.size(), list);
        Observable.just(getSeatAdapterData()).map(new Function() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$YYLinkSeatListView$ddKJEzmvSSUmMjYM0Bq1-KotDYA
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) throws Exception {
                return YYLinkSeatListView.lambda$initAudienceSeatData$0(list, (List) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Boolean>() { // from class: com.slzhibo.library.ui.view.custom.YYLinkSeatListView.4
            public void accept(Boolean bool) {
                YYLinkSeatListView.this.seatAdapter.notifyDataSetChanged();
            }
        });
    }

    static /* synthetic */ Boolean lambda$initAudienceSeatData$0(List list, List list2) throws Exception {
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            YYLinkApplyEntity yYLinkApplyEntity = (YYLinkApplyEntity) it2.next();
            Iterator it3 = list.iterator();
            while (it3.hasNext()) {
                YYLinkApplyEntity yYLinkApplyEntity2 = (YYLinkApplyEntity) it3.next();
                if (TextUtils.equals(yYLinkApplyEntity2.seat, yYLinkApplyEntity.seat)) {
                    yYLinkApplyEntity.rtcUid = yYLinkApplyEntity2.rtcUid;
                    yYLinkApplyEntity.userId = yYLinkApplyEntity2.userId;
                    yYLinkApplyEntity.userName = yYLinkApplyEntity2.userName;
                    yYLinkApplyEntity.sex = yYLinkApplyEntity2.sex;
                    yYLinkApplyEntity.userAvatar = yYLinkApplyEntity2.userAvatar;
                    yYLinkApplyEntity.seat = yYLinkApplyEntity2.seat;
                    yYLinkApplyEntity.likeCount = yYLinkApplyEntity2.likeCount;
                    yYLinkApplyEntity.isQuiet = yYLinkApplyEntity2.isQuiet;
                    yYLinkApplyEntity.seatStatus = yYLinkApplyEntity2.seatStatus;
                    yYLinkApplyEntity.expGrade = yYLinkApplyEntity2.expGrade;
                    yYLinkApplyEntity.guardType = yYLinkApplyEntity2.guardType;
                    yYLinkApplyEntity.userOpenId = yYLinkApplyEntity2.userOpenId;
                    yYLinkApplyEntity.userAppId = yYLinkApplyEntity2.userAppId;
                    yYLinkApplyEntity.role = yYLinkApplyEntity2.role;
                }
            }
        }
        return true;
    }

    public void changeSeatNumMode(int i) {
        List<YYLinkApplyEntity> seatAdapterData = getSeatAdapterData();
        if (i == 1) {
            this.llLinkSeatBg_1.setVisibility(View.VISIBLE);
            this.llLinkSeatBg_2.setVisibility(View.INVISIBLE);
            this.recyclerView.setVisibility(View.INVISIBLE);
            updateAnchorInfoBySingleMode(getAnchorEntityByDoubleLinkMode());
            if (seatAdapterData == null || seatAdapterData.size() < 1) {
                setSeatAdapterData(formatSeatDataList(1));
            } else {
                setSeatAdapterData(seatAdapterData.subList(0, 1));
            }
            changeAnimStatus(this.userMicView, false);
            changeAnimStatus(this.anchorMicView, false);
        } else if (i == 2) {
            this.llLinkSeatBg_1.setVisibility(View.INVISIBLE);
            this.llLinkSeatBg_2.setVisibility(View.VISIBLE);
            this.recyclerView.setVisibility(View.INVISIBLE);
            updateAnchorInfoByDoubleMode(getAnchorEntityByDoubleLinkMode());
            updateUserInfoByDoubleMode(getUserEntityByDoubleLinkMode());
            if (seatAdapterData == null || seatAdapterData.size() < 2) {
                setSeatAdapterData(formatSeatDataList(2));
            } else {
                setSeatAdapterData(seatAdapterData.subList(0, 2));
            }
            changeAnimStatus(this.singleMicView, false);
        } else if (i == 4 || i == 8) {
            this.llLinkSeatBg_1.setVisibility(View.INVISIBLE);
            this.llLinkSeatBg_2.setVisibility(View.INVISIBLE);
            this.recyclerView.setVisibility(View.VISIBLE);
            if (seatAdapterData == null || seatAdapterData.size() <= i) {
                setSeatAdapterData(formatSeatDataList(i));
            } else {
                setSeatAdapterData(seatAdapterData.subList(0, i));
            }
            changeAnimStatus(this.userMicView, false);
            changeAnimStatus(this.anchorMicView, false);
            changeAnimStatus(this.singleMicView, false);
        }
        this.currentLinkMode = i;
    }

    public void switchUserMuteMode(final String str, final boolean z) {
        Observable.just(getSeatAdapterData()).map(new Function() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$YYLinkSeatListView$B_cgRXG4GqrtupVuDHLsASe6VF0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) throws Exception {
                return YYLinkSeatListView.lambda$switchUserMuteMode$1(str, z, (List) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Boolean>() { // from class: com.slzhibo.library.ui.view.custom.YYLinkSeatListView.5
            @SuppressLint("WrongConstant")
            public void accept(Boolean bool) {
                YYLinkSeatListView.this.seatAdapter.notifyDataSetChanged();
                int i = 4;
                if (YYLinkSeatListView.this.isUserByDoubleLinkMode(str)) {
                    YYLinkSeatListView.this.ivMute.setVisibility(z ? View.INVISIBLE : View.VISIBLE);
                    YYLinkSeatListView.this.ivMuteBg.setVisibility(z ? View.INVISIBLE : View.VISIBLE);
                    YYLinkSeatListView yYLinkSeatListView = YYLinkSeatListView.this;
                    yYLinkSeatListView.startPlaySpeakAnim(yYLinkSeatListView.userMicView, YYLinkSeatListView.this.getUserEntityByDoubleLinkMode());
                }
                if (YYLinkSeatListView.this.isAnchorByDoubleLinkMode(str)) {
                    YYLinkSeatListView.this.ivAnchorMute.setVisibility(z ? View.INVISIBLE : View.VISIBLE);
                    YYLinkSeatListView.this.ivAnchorMuteBg.setVisibility(z ? View.INVISIBLE : View.VISIBLE);
                    YYLinkSeatListView yYLinkSeatListView2 = YYLinkSeatListView.this;
                    yYLinkSeatListView2.startPlaySpeakAnim(yYLinkSeatListView2.anchorMicView, YYLinkSeatListView.this.getAnchorEntityByDoubleLinkMode());
                }
                if (YYLinkSeatListView.this.isSingleLinkModeAndUpdate()) {
                    YYLinkSeatListView.this.ivSingleMute.setVisibility(z ? View.INVISIBLE : View.VISIBLE);
                    ImageView imageView = YYLinkSeatListView.this.ivSingleMuteBg;
                    if (!z) {
                        i = 0;
                    }
                    imageView.setVisibility(i);
                    YYLinkSeatListView yYLinkSeatListView3 = YYLinkSeatListView.this;
                    yYLinkSeatListView3.startPlaySpeakAnim(yYLinkSeatListView3.singleMicView, YYLinkSeatListView.this.getAnchorEntityByDoubleLinkMode());
                }
            }
        });
    }

    static /* synthetic */ Boolean lambda$switchUserMuteMode$1(String str, boolean z, List list) throws Exception {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            YYLinkApplyEntity yYLinkApplyEntity = (YYLinkApplyEntity) it2.next();
            if (TextUtils.equals(str, yYLinkApplyEntity.userId)) {
                yYLinkApplyEntity.setMuteStatus(true ^ z);
            }
        }
        return true;
    }

    public void switchUserLockMode(final int i, final String str) {
        Observable.just(getSeatAdapterData()).map(new Function() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$YYLinkSeatListView$Rlg2GdS316Bqs2OAVbFSYA61ihk
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) throws Exception {
                return YYLinkSeatListView.lambda$switchUserLockMode$2(i, str, (List) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Boolean>() { // from class: com.slzhibo.library.ui.view.custom.YYLinkSeatListView.6
            public void accept(Boolean bool) {
                YYLinkSeatListView.this.seatAdapter.notifyDataSetChanged();
                if (YYLinkSeatListView.this.isUserByDoubleLinkMode(i)) {
                    YYLinkSeatListView.this.ivLock.setVisibility(YYLinkSeatListView.this.getUserEntityByDoubleLinkMode().isLockSeatStatus() ? View.VISIBLE : View.INVISIBLE);
                }
            }
        });
    }

    static /* synthetic */ Boolean lambda$switchUserLockMode$2(int i, String str, List list) throws Exception {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            YYLinkApplyEntity yYLinkApplyEntity = (YYLinkApplyEntity) it2.next();
            if (TextUtils.equals(String.valueOf(i), yYLinkApplyEntity.seat)) {
                yYLinkApplyEntity.seatStatus = str;
            }
        }
        return true;
    }

    public void removeUserLink(final String str) {
        if (this.currentLinkMode != 1) {
            if (isUserByDoubleLinkMode(str)) {
                updateUserInfoByDoubleMode(null);
            }
            Observable.just(getSeatAdapterData()).map(new Function() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$YYLinkSeatListView$7oMK9jR5LMAjMVcFZR75cS4ZQ_c
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) throws Exception {
                    return YYLinkSeatListView.this.lambda$removeUserLink$3$YYLinkSeatListView(str, (List) obj);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Boolean>() { // from class: com.slzhibo.library.ui.view.custom.YYLinkSeatListView.7
                public void accept(Boolean bool) {
                    YYLinkSeatListView.this.seatAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    public /* synthetic */ Boolean lambda$removeUserLink$3$YYLinkSeatListView(String str, List list) throws Exception {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            YYLinkApplyEntity yYLinkApplyEntity = (YYLinkApplyEntity) it2.next();
            if (TextUtils.equals(str, yYLinkApplyEntity.userId)) {
                yYLinkApplyEntity.userId = null;
                yYLinkApplyEntity.userName = this.mContext.getString(R.string.fq_text_list_empty_waiting);
                yYLinkApplyEntity.isQuiet = "0";
                yYLinkApplyEntity.likeCount = "0";
                yYLinkApplyEntity.seatStatus = "1";
            }
        }
        return true;
    }

    public void updateLikeCount(final String str, final String str2) {
        Observable.just(getSeatAdapterData()).map(new Function() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$YYLinkSeatListView$rU0Ne7R1gdy6jUjsS6DQSKVMc0w
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) throws Exception {
                return YYLinkSeatListView.lambda$updateLikeCount$4(str, str2, (List) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Boolean>() { // from class: com.slzhibo.library.ui.view.custom.YYLinkSeatListView.8
            public void accept(Boolean bool) {
                YYLinkSeatListView.this.seatAdapter.notifyDataSetChanged();
                if (YYLinkSeatListView.this.isUserByDoubleLinkMode(str)) {
                    YYLinkSeatListView.this.tvUserLike_2.setVisibility(View.VISIBLE);
                    YYLinkSeatListView.this.tvUserLike_2.setText(YYLinkSeatListView.this.getUserEntityByDoubleLinkMode().likeCount);
                }
                if (YYLinkSeatListView.this.isAnchorByDoubleLinkMode(str)) {
                    YYLinkSeatListView.this.tvAnchorLike_2.setVisibility(View.VISIBLE);
                    YYLinkSeatListView.this.tvAnchorLike_2.setText(YYLinkSeatListView.this.getAnchorEntityByDoubleLinkMode().likeCount);
                }
                if (YYLinkSeatListView.this.isSingleLinkModeAndUpdate()) {
                    YYLinkSeatListView.this.tvAnchorLike.setVisibility(View.VISIBLE);
                    YYLinkSeatListView.this.tvAnchorLike.setText(YYLinkSeatListView.this.getAnchorEntityByDoubleLinkMode().likeCount);
                }
            }
        });
    }

    static /* synthetic */ Boolean lambda$updateLikeCount$4(String str, String str2, List list) throws Exception {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            YYLinkApplyEntity yYLinkApplyEntity = (YYLinkApplyEntity) it2.next();
            if (TextUtils.equals(str, yYLinkApplyEntity.userId)) {
                yYLinkApplyEntity.likeCount = String.valueOf(NumberUtils.string2int(yYLinkApplyEntity.likeCount) + NumberUtils.string2int(str2));
            }
        }
        return true;
    }

    public void userJoinLinkSeat(final YYLinkApplyEntity yYLinkApplyEntity) {
        Observable.just(getSeatAdapterData()).map(new Function() {
            /* class com.slzhibo.library.ui.view.custom.$$Lambda$YYLinkSeatListView$T9yi71gYUDoIZUCvP6sGoKLq7_I */

            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) throws Exception {
                return YYLinkSeatListView.lambda$userJoinLinkSeat$5(yYLinkApplyEntity, (List) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Boolean>() {
            /* class com.slzhibo.library.ui.view.custom.YYLinkSeatListView.AnonymousClass9 */

            public void accept(Boolean bool) {
                YYLinkSeatListView.this.seatAdapter.notifyDataSetChanged();
                if (YYLinkSeatListView.this.currentLinkMode == 2) {
                    YYLinkSeatListView.this.updateUserInfoByDoubleMode(yYLinkApplyEntity);
                }
            }
        });
    }

    static /* synthetic */ Boolean lambda$userJoinLinkSeat$5(YYLinkApplyEntity yYLinkApplyEntity, List list) throws Exception {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            YYLinkApplyEntity yYLinkApplyEntity2 = (YYLinkApplyEntity) it2.next();
            if (TextUtils.equals(yYLinkApplyEntity.seat, yYLinkApplyEntity2.seat)) {
                yYLinkApplyEntity2.rtcUid = yYLinkApplyEntity.rtcUid;
                yYLinkApplyEntity2.userId = yYLinkApplyEntity.userId;
                yYLinkApplyEntity2.userName = yYLinkApplyEntity.userName;
                yYLinkApplyEntity2.sex = yYLinkApplyEntity.sex;
                yYLinkApplyEntity2.userAvatar = yYLinkApplyEntity.userAvatar;
                yYLinkApplyEntity2.likeCount = yYLinkApplyEntity.likeCount;
                yYLinkApplyEntity2.isQuiet = yYLinkApplyEntity.isQuiet;
                yYLinkApplyEntity2.seatStatus = yYLinkApplyEntity.seatStatus;
                yYLinkApplyEntity2.expGrade = yYLinkApplyEntity.expGrade;
                yYLinkApplyEntity2.guardType = yYLinkApplyEntity.guardType;
                yYLinkApplyEntity2.nobilityType = yYLinkApplyEntity.nobilityType;
                yYLinkApplyEntity2.userOpenId = yYLinkApplyEntity.userOpenId;
                yYLinkApplyEntity2.userAppId = yYLinkApplyEntity.userAppId;
                yYLinkApplyEntity2.role = yYLinkApplyEntity.role;
            }
        }
        return true;
    }

    public void setUserSpeakBySocket(final List<String> list) {
        Observable.just(getSeatAdapterData()).map(new Function() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$YYLinkSeatListView$YWO2fAjmWLrWh51vPo8orRW1g-M
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) throws Exception {
                return YYLinkSeatListView.lambda$setUserSpeakBySocket$6(list, (List) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Boolean>() { // from class: com.slzhibo.library.ui.view.custom.YYLinkSeatListView.10
            public void accept(Boolean bool) {
                if (bool.booleanValue()) {
                    YYLinkSeatListView.this.seatAdapter.notifyDataSetChanged();
                    YYLinkApplyEntity userEntityByDoubleLinkMode = YYLinkSeatListView.this.getUserEntityByDoubleLinkMode();
                    YYLinkApplyEntity anchorEntityByDoubleLinkMode = YYLinkSeatListView.this.getAnchorEntityByDoubleLinkMode();
                    if (YYLinkSeatListView.this.isSingleLinkModeAndUpdate()) {
                        anchorEntityByDoubleLinkMode.isSpeak = list.contains(String.valueOf(anchorEntityByDoubleLinkMode.rtcUid));
                        YYLinkSeatListView yYLinkSeatListView = YYLinkSeatListView.this;
                        yYLinkSeatListView.startPlaySpeakAnim(yYLinkSeatListView.singleMicView, anchorEntityByDoubleLinkMode);
                    }
                    if (YYLinkSeatListView.this.isDoubleLinkModeAndUpdate()) {
                        userEntityByDoubleLinkMode.isSpeak = list.contains(String.valueOf(userEntityByDoubleLinkMode.rtcUid));
                        YYLinkSeatListView yYLinkSeatListView2 = YYLinkSeatListView.this;
                        yYLinkSeatListView2.startPlaySpeakAnim(yYLinkSeatListView2.userMicView, userEntityByDoubleLinkMode);
                        anchorEntityByDoubleLinkMode.isSpeak = list.contains(String.valueOf(anchorEntityByDoubleLinkMode.rtcUid));
                        YYLinkSeatListView yYLinkSeatListView3 = YYLinkSeatListView.this;
                        yYLinkSeatListView3.startPlaySpeakAnim(yYLinkSeatListView3.anchorMicView, anchorEntityByDoubleLinkMode);
                    }
                }
            }
        });
    }

    static /* synthetic */ Boolean lambda$setUserSpeakBySocket$6(List list, List list2) throws Exception {
        if (list == null || list.isEmpty() || list2 == null || list2.isEmpty()) {
            return false;
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            ((YYLinkApplyEntity) it2.next()).isSpeak = false;
        }
        if (list != null) {
            Iterator it3 = list.iterator();
            while (it3.hasNext()) {
                String str = (String) it3.next();
                Iterator it4 = list2.iterator();
                while (it4.hasNext()) {
                    YYLinkApplyEntity yYLinkApplyEntity = (YYLinkApplyEntity) it4.next();
                    if (yYLinkApplyEntity.rtcUid == NumberUtils.string2long(str) && !yYLinkApplyEntity.isMuteStatus() && !TextUtils.isEmpty(yYLinkApplyEntity.userId)) {
                        yYLinkApplyEntity.isSpeak = true;
                    }
                }
            }
        }
        return true;
    }

    public void clearSeatInfo() {
        int i = this.currentLinkMode;
        if (i == 1) {
            updateAnchorInfoBySingleMode(null);
        } else if (i == 2) {
            updateUserInfoByDoubleMode(null);
            updateAnchorInfoByDoubleMode(null);
        } else {
            Observable.just(getSeatAdapterData()).map(new Function() {
                /* class com.slzhibo.library.ui.view.custom.$$Lambda$YYLinkSeatListView$yQKmuDyrG6wdRe8wk9Ly0NDKygw */

                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) throws Exception {
                    return YYLinkSeatListView.this.lambda$clearSeatInfo$7$YYLinkSeatListView((List) obj);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Boolean>() {
                /* class com.slzhibo.library.ui.view.custom.YYLinkSeatListView.AnonymousClass11 */

                public void accept(Boolean bool) {
                    YYLinkSeatListView.this.seatAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    public /* synthetic */ Boolean lambda$clearSeatInfo$7$YYLinkSeatListView(List list) throws Exception {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            YYLinkApplyEntity yYLinkApplyEntity = (YYLinkApplyEntity) it2.next();
            yYLinkApplyEntity.userId = null;
            yYLinkApplyEntity.userName = this.mContext.getString(R.string.fq_text_list_empty_waiting);
            yYLinkApplyEntity.isQuiet = "0";
            yYLinkApplyEntity.likeCount = "0";
            yYLinkApplyEntity.seatStatus = "1";
        }
        return true;
    }

    public int getCurrentLinkMode() {
        return this.currentLinkMode;
    }

    public void setLinkCallback(YYLinkCallback yYLinkCallback) {
        this.linkCallback = yYLinkCallback;
    }

    private void initSeatAdapter() {
        ((DefaultItemAnimator) this.recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.seatAdapter = new YYLinkSeatAdapter(R.layout.fq_item_list_yy_link_seat);
        this.seatAdapter.setHasStableIds(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.mContext, 4);
        this.recyclerView.setAdapter(this.seatAdapter);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.seatAdapter.bindToRecyclerView(this.recyclerView);
        this.seatAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            /* class com.slzhibo.library.ui.view.custom.YYLinkSeatListView.AnonymousClass12 */

            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemChildClickListener
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                YYLinkApplyEntity yYLinkApplyEntity;
                if (view.getId() == R.id.rl_item_view && (yYLinkApplyEntity = (YYLinkApplyEntity) baseQuickAdapter.getItem(i)) != null) {
                    if (TextUtils.isEmpty(yYLinkApplyEntity.userId) && !YYLinkSeatListView.this.isAnchorMode) {
                        return;
                    }
                    if (!TextUtils.isEmpty(yYLinkApplyEntity.userId)) {
                        int i2 = 3;
                        if (i == 0) {
                            if (YYLinkSeatListView.this.linkCallback != null) {
                                YYLinkCallback yYLinkCallback = YYLinkSeatListView.this.linkCallback;
                                if (YYLinkSeatListView.this.isAnchorMode) {
                                    i2 = 2;
                                }
                                yYLinkCallback.onClickLinkRTCUserListener(i2, yYLinkApplyEntity);
                            }
                        } else if (YYLinkSeatListView.this.linkCallback != null) {
                            YYLinkCallback yYLinkCallback2 = YYLinkSeatListView.this.linkCallback;
                            if (YYLinkSeatListView.this.isAnchorMode) {
                                i2 = 1;
                            }
                            yYLinkCallback2.onClickLinkRTCUserListener(i2, yYLinkApplyEntity);
                        }
                    } else if (YYLinkSeatListView.this.linkCallback != null) {
                        YYLinkSeatListView.this.linkCallback.onClickLinkRTCUserListener(4, yYLinkApplyEntity);
                    }
                }
            }
        });
    }

    private void initDefaultSeatDataList(int i, List<YYLinkApplyEntity> list) {
        this.currentLinkMode = i;
        setSeatAdapterData(list);
        if (i == 1) {
            this.llLinkSeatBg_1.setVisibility(View.VISIBLE);
            this.llLinkSeatBg_2.setVisibility(View.INVISIBLE);
            this.recyclerView.setVisibility(View.INVISIBLE);
            updateAnchorInfoBySingleMode(getAnchorEntityByDoubleLinkMode());
        } else if (i == 2) {
            this.llLinkSeatBg_1.setVisibility(View.INVISIBLE);
            this.llLinkSeatBg_2.setVisibility(View.VISIBLE);
            this.recyclerView.setVisibility(View.INVISIBLE);
            updateAnchorInfoByDoubleMode(getAnchorEntityByDoubleLinkMode());
            updateUserInfoByDoubleMode(getUserEntityByDoubleLinkMode());
        } else if (i == 4 || i == 8) {
            this.llLinkSeatBg_1.setVisibility(View.INVISIBLE);
            this.llLinkSeatBg_2.setVisibility(View.INVISIBLE);
            this.recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private List<YYLinkApplyEntity> formatSeatDataList(int i) {
        ArrayList arrayList = new ArrayList(getSeatAdapterData());
        int size = i - arrayList.size();
        int size2 = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            YYLinkApplyEntity yYLinkApplyEntity = new YYLinkApplyEntity();
            size2++;
            yYLinkApplyEntity.userId = null;
            yYLinkApplyEntity.userName = this.mContext.getString(R.string.fq_text_list_empty_waiting);
            yYLinkApplyEntity.seat = String.valueOf(size2);
            yYLinkApplyEntity.likeCount = String.valueOf(0);
            yYLinkApplyEntity.setLockSeatStatus(false);
            yYLinkApplyEntity.isSpeak = false;
            yYLinkApplyEntity.setMuteStatus(false);
            arrayList.add(yYLinkApplyEntity);
        }
        return arrayList;
    }

    private YYLinkApplyEntity getAdapterEntityItem(int i) {
        return getSeatAdapterData().get(i);
    }

    private void setSeatAdapterData(List<YYLinkApplyEntity> list) {
        this.seatAdapter.setNewData(list);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private List<YYLinkApplyEntity> getSeatAdapterData() {
        return this.seatAdapter.getData();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @SuppressLint("WrongConstant")
    private void updateUserInfoByDoubleMode(YYLinkApplyEntity yYLinkApplyEntity) {
        int i = 4;
        this.ivLock.setVisibility(View.INVISIBLE);
        this.ivMute.setVisibility(View.INVISIBLE);
        this.ivMuteBg.setVisibility(View.INVISIBLE);
        if (yYLinkApplyEntity == null) {
            this.tvUserName_2.setText(R.string.fq_text_list_empty_waiting);
            this.tvUserLike_2.setVisibility(View.INVISIBLE);
            GlideUtils.loadTargetToImage(this.mContext, this.ivUserAvatar_2, R.drawable.fq_ic_yy_link_sofa);
            startPlaySpeakAnim(this.userMicView, null);
            return;
        }
        this.tvUserName_2.setText(StringUtils.formatStrLen(yYLinkApplyEntity.userName, 5));
        this.ivLock.setVisibility(yYLinkApplyEntity.isLockSeatStatus() ? View.VISIBLE : View.INVISIBLE);
        if (TextUtils.isEmpty(yYLinkApplyEntity.userId)) {
            this.tvUserLike_2.setVisibility(4);
            GlideUtils.loadTargetToImage(this.mContext, this.ivUserAvatar_2, R.drawable.fq_ic_yy_link_sofa);
            return;
        }
        Context context = this.mContext;
        GlideUtils.loadAvatar(context, this.ivUserAvatar_2, yYLinkApplyEntity.userAvatar, 2, ContextCompat.getColor(context, R.color.fq_colorWhite));
        this.tvUserLike_2.setVisibility(View.VISIBLE);
        this.tvUserLike_2.setText(yYLinkApplyEntity.likeCount);
        this.ivMute.setVisibility(yYLinkApplyEntity.isMuteStatus() ? View.VISIBLE : View.INVISIBLE);
        ImageView imageView = this.ivMuteBg;
        if (yYLinkApplyEntity.isMuteStatus()) {
            i = 0;
        }
        imageView.setVisibility(i);
    }

    @SuppressLint("WrongConstant")
    private void updateAnchorInfoBySingleMode(YYLinkApplyEntity yYLinkApplyEntity) {
        int i = 4;
        if (yYLinkApplyEntity == null) {
            GlideUtils.loadTargetToImage(this.mContext, this.ivAnchorAvatar, R.drawable.fq_ic_yy_link_sofa);
            this.tvAnchorName.setText(R.string.fq_text_list_empty_waiting);
            this.tvAnchorLike.setVisibility(View.INVISIBLE);
            this.ivSingleMuteBg.setVisibility(View.INVISIBLE);
            this.ivSingleMute.setVisibility(View.INVISIBLE);
            startPlaySpeakAnim(this.singleMicView, null);
            return;
        }
        this.tvAnchorName.setText(StringUtils.formatStrLen(yYLinkApplyEntity.userName, 5));
        Context context = this.mContext;
        GlideUtils.loadAvatar(context, this.ivAnchorAvatar, yYLinkApplyEntity.userAvatar, 2, ContextCompat.getColor(context, R.color.fq_colorWhite));
        this.tvAnchorLike.setText(yYLinkApplyEntity.likeCount);
        this.tvAnchorLike.setVisibility(View.VISIBLE);
        this.ivSingleMuteBg.setVisibility(yYLinkApplyEntity.isMuteStatus() ? View.VISIBLE : View.INVISIBLE);
        ImageView imageView = this.ivSingleMute;
        if (yYLinkApplyEntity.isMuteStatus()) {
            i = 0;
        }
        imageView.setVisibility(i);
    }

    @SuppressLint("WrongConstant")
    private void updateAnchorInfoByDoubleMode(YYLinkApplyEntity yYLinkApplyEntity) {
        int i = 4;
        if (yYLinkApplyEntity == null) {
            GlideUtils.loadTargetToImage(this.mContext, this.ivAnchorAvatar_2, R.drawable.fq_ic_yy_link_sofa);
            this.tvAnchorName_2.setText(R.string.fq_text_list_empty_waiting);
            this.tvAnchorLike_2.setVisibility(View.INVISIBLE);
            this.ivAnchorMute.setVisibility(View.INVISIBLE);
            this.ivAnchorMuteBg.setVisibility(View.INVISIBLE);
            startPlaySpeakAnim(this.anchorMicView, null);
            return;
        }
        this.tvAnchorName_2.setText(StringUtils.formatStrLen(yYLinkApplyEntity.userName, 5));
        Context context = this.mContext;
        GlideUtils.loadAvatar(context, this.ivAnchorAvatar_2, yYLinkApplyEntity.userAvatar, 2, ContextCompat.getColor(context, R.color.fq_colorWhite));
        this.tvAnchorLike_2.setText(yYLinkApplyEntity.likeCount);
        this.ivAnchorMute.setVisibility(yYLinkApplyEntity.isMuteStatus() ? View.VISIBLE : View.INVISIBLE);
        ImageView imageView = this.ivAnchorMuteBg;
        if (yYLinkApplyEntity.isMuteStatus()) {
            i = 0;
        }
        imageView.setVisibility(i);
        this.tvAnchorLike_2.setVisibility(View.VISIBLE);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void startPlaySpeakAnim(MicVoiceView micVoiceView, YYLinkApplyEntity yYLinkApplyEntity) {
        if (yYLinkApplyEntity == null) {
            micVoiceView.setVisibility(View.INVISIBLE);
            micVoiceView.stop();
        } else if (!yYLinkApplyEntity.isSpeak || yYLinkApplyEntity.isMuteStatus() || TextUtils.isEmpty(yYLinkApplyEntity.userId)) {
            micVoiceView.setVisibility(View.INVISIBLE);
            micVoiceView.stop();
        } else {
            micVoiceView.setVisibility(View.VISIBLE);
            micVoiceView.start();
        }
    }

    private void changeAnimStatus(MicVoiceView micVoiceView, boolean z) {
        if (z) {
            micVoiceView.setVisibility(View.VISIBLE);
            micVoiceView.start();
            return;
        }
        micVoiceView.setVisibility(View.INVISIBLE);
        micVoiceView.stop();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isSingleLinkModeAndUpdate() {
        return this.currentLinkMode == 1 && getSeatAdapterData().size() >= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isDoubleLinkModeAndUpdate() {
        return this.currentLinkMode == 2 && getSeatAdapterData().size() >= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isUserByDoubleLinkMode(String str) {
        return isDoubleLinkModeAndUpdate() && TextUtils.equals(getUserEntityByDoubleLinkMode().userId, str);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isUserByDoubleLinkMode(int i) {
        return isDoubleLinkModeAndUpdate() && TextUtils.equals(getUserEntityByDoubleLinkMode().seat, String.valueOf(i));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isAnchorByDoubleLinkMode(String str) {
        return isDoubleLinkModeAndUpdate() && TextUtils.equals(getAnchorEntityByDoubleLinkMode().userId, str);
    }

    public YYLinkApplyEntity getUserEntityByDoubleLinkMode() {
        if (getSeatAdapterData().size() > 1) {
            return getAdapterEntityItem(1);
        }
        return null;
    }

    public YYLinkApplyEntity getAnchorEntityByDoubleLinkMode() {
        return getAdapterEntityItem(0);
    }

    public void updateUserRole(final String str, final String str2) {
        Observable.just(getSeatAdapterData()).map(new Function() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$YYLinkSeatListView$716-Co4FN2CPMQ1DbH4o6QNf1zU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) throws Exception {
                return YYLinkSeatListView.lambda$updateUserRole$8(str, str2, (List) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Boolean>() { // from class: com.slzhibo.library.ui.view.custom.YYLinkSeatListView.13
            public void accept(Boolean bool) {
                YYLinkSeatListView.this.seatAdapter.notifyDataSetChanged();
                if (YYLinkSeatListView.this.isUserByDoubleLinkMode(str)) {
                    YYLinkSeatListView.this.getUserEntityByDoubleLinkMode().role = str2;
                }
            }
        });
    }

    static /* synthetic */ Boolean lambda$updateUserRole$8(String str, String str2, List list) throws Exception {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            YYLinkApplyEntity yYLinkApplyEntity = (YYLinkApplyEntity) it2.next();
            if (TextUtils.equals(str, yYLinkApplyEntity.userId)) {
                yYLinkApplyEntity.role = str2;
            }
        }
        return true;
    }

    public void updateUserGuardType(final String str, final String str2) {
        Observable.just(getSeatAdapterData()).map(new Function() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$YYLinkSeatListView$ze7YMF2x58_EqJBltXnImkdI93I
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) throws Exception {
                return YYLinkSeatListView.lambda$updateUserGuardType$9(str, str2, (List) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Boolean>() { // from class: com.slzhibo.library.ui.view.custom.YYLinkSeatListView.14
            public void accept(Boolean bool) {
                YYLinkSeatListView.this.seatAdapter.notifyDataSetChanged();
                if (YYLinkSeatListView.this.isUserByDoubleLinkMode(str)) {
                    YYLinkSeatListView.this.getUserEntityByDoubleLinkMode().guardType = str2;
                }
            }
        });
    }

    static /* synthetic */ Boolean lambda$updateUserGuardType$9(String str, String str2, List list) throws Exception {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            YYLinkApplyEntity yYLinkApplyEntity = (YYLinkApplyEntity) it2.next();
            if (TextUtils.equals(str, yYLinkApplyEntity.userId)) {
                yYLinkApplyEntity.guardType = str2;
            }
        }
        return true;
    }

    public void updateUserNobility(final String str, final int i) {
        Observable.just(getSeatAdapterData()).map(new Function() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$YYLinkSeatListView$1eMJ9ZjbZHjsV3OZGyEEA1nmYLA
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) throws Exception {
                return YYLinkSeatListView.lambda$updateUserNobility$10(str, i, (List) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Boolean>() { // from class: com.slzhibo.library.ui.view.custom.YYLinkSeatListView.15
            public void accept(Boolean bool) {
                YYLinkSeatListView.this.seatAdapter.notifyDataSetChanged();
                if (YYLinkSeatListView.this.isUserByDoubleLinkMode(str)) {
                    YYLinkSeatListView.this.getUserEntityByDoubleLinkMode().nobilityType = i;
                }
            }
        });
    }

    static /* synthetic */ Boolean lambda$updateUserNobility$10(String str, int i, List list) throws Exception {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            YYLinkApplyEntity yYLinkApplyEntity = (YYLinkApplyEntity) it2.next();
            if (TextUtils.equals(str, yYLinkApplyEntity.userId)) {
                yYLinkApplyEntity.nobilityType = i;
            }
        }
        return true;
    }
}
