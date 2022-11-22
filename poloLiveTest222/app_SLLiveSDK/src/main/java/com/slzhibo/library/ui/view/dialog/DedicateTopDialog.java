package com.slzhibo.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.slzhibo.library.R;
import com.slzhibo.library.http.ApiRetrofit;
import com.slzhibo.library.http.RequestParams;
import com.slzhibo.library.http.function.HttpResultFunction;
import com.slzhibo.library.http.function.ServerResultFunction;
import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.ui.adapter.DedicateTopAdapter;
import com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback;
import com.slzhibo.library.ui.view.dialog.base.BaseBottomDialogFragment;
import com.slzhibo.library.ui.view.emptyview.RecyclerIncomeEmptyView;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.SysConfigInfoManager;
import com.slzhibo.library.utils.UserInfoManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DedicateTopDialog extends BaseBottomDialogFragment {
    private String anchorId;
    private AnchorEntity anchorInfoItem;
    private int dayTagValue = 1;
    private SparseArray<List<AnchorEntity>> listMap = new SparseArray<>();
    private int liveType = 2;
    private LinearLayout llContentBg;
    private DedicateTopAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private OnLivePusherInfoCallback onLivePusherInfoCallback;
    private ProgressBar progressBar;
    private TextView tvAllTop;
    private TextView tvDayTop;
    private TextView tvMonthTop;
    private TextView tvWeekTop;

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment
    public float getDimAmount() {
        return 0.0f;
    }

    public static DedicateTopDialog newInstance(int i, AnchorEntity anchorEntity, OnLivePusherInfoCallback onLivePusherInfoCallback2) {
        Bundle bundle = new Bundle();
        DedicateTopDialog dedicateTopDialog = new DedicateTopDialog();
        bundle.putInt(ConstantUtils.BUNDLE_VALUE_LIVE_TYPE, i);
        bundle.putParcelable("anchorId_key", anchorEntity);
        dedicateTopDialog.setArguments(bundle);
        dedicateTopDialog.setOnLivePusherInfoCallback(onLivePusherInfoCallback2);
        return dedicateTopDialog;
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseBottomDialogFragment
    public int getLayoutRes() {
        return R.layout.fq_dialog_dedicate_top;
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseBottomDialogFragment
    public void initView(View view) {
        this.tvDayTop = (TextView) view.findViewById(R.id.tv_day_top);
        this.tvWeekTop = (TextView) view.findViewById(R.id.tv_week_top);
        this.tvMonthTop = (TextView) view.findViewById(R.id.tv_month_top);
        this.tvAllTop = (TextView) view.findViewById(R.id.tv_bottom_top);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progress_wheel);
        this.llContentBg = (LinearLayout) view.findViewById(R.id.ll_content_bg);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mAdapter = new DedicateTopAdapter(R.layout.fq_item_list_dedicate_top_live, true);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerIncomeEmptyView(this.mContext, 38));
        this.tvAllTop.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        this.tvAllTop.getPaint().setAntiAlias(true);
        initChangeView();
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment
    public void getBundle(Bundle bundle) {
        super.getBundle(bundle);
        this.liveType = getArgumentsInt(ConstantUtils.BUNDLE_VALUE_LIVE_TYPE, 2);
        this.anchorInfoItem = (AnchorEntity) bundle.getParcelable("anchorId_key");
        AnchorEntity anchorEntity = this.anchorInfoItem;
        this.anchorId = anchorEntity != null ? anchorEntity.userId : "";
    }

    /* access modifiers changed from: protected */
    @Override // com.slzhibo.library.ui.view.dialog.base.BaseBottomDialogFragment
    public void initListener(View view) {
        super.initListener(view);
        this.tvDayTop.setOnClickListener(new View.OnClickListener() {
            /* class com.slzhibo.library.ui.view.dialog.$$Lambda$DedicateTopDialog$GAMSi4bIp3jRvngtOuCMJwscdAs */

            public final void onClick(View view) {
                DedicateTopDialog.this.lambda$initListener$0$DedicateTopDialog(view);
            }
        });
        this.tvMonthTop.setOnClickListener(new View.OnClickListener() {
            /* class com.slzhibo.library.ui.view.dialog.$$Lambda$DedicateTopDialog$0qYjQimFmoliBoNvCapiHghzQU4 */

            public final void onClick(View view) {
                DedicateTopDialog.this.lambda$initListener$1$DedicateTopDialog(view);
            }
        });
        this.tvWeekTop.setOnClickListener(new View.OnClickListener() {
            /* class com.slzhibo.library.ui.view.dialog.$$Lambda$DedicateTopDialog$tb_mw5ddDF6EVU7xs5EqEWE_aY */

            public final void onClick(View view) {
                DedicateTopDialog.this.lambda$initListener$2$DedicateTopDialog(view);
            }
        });
        view.findViewById(R.id.tv_bottom_top).setOnClickListener(new View.OnClickListener() {
            /* class com.slzhibo.library.ui.view.dialog.$$Lambda$DedicateTopDialog$ELcrohx6qCS9jxVwO4c0K6XrJoQ */

            public final void onClick(View view) {
                DedicateTopDialog.this.lambda$initListener$3$DedicateTopDialog(view);
            }
        });
        this.mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            /* class com.slzhibo.library.ui.view.dialog.$$Lambda$DedicateTopDialog$w35D0tPDw3_N8PJ7wy0Z59WqGlA */

            @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DedicateTopDialog.this.lambda$initListener$5$DedicateTopDialog(baseQuickAdapter, view, i);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$DedicateTopDialog(View view) {
        hideTopTagView(1);
    }

    public /* synthetic */ void lambda$initListener$1$DedicateTopDialog(View view) {
        hideTopTagView(2);
    }

    public /* synthetic */ void lambda$initListener$2$DedicateTopDialog(View view) {
        hideTopTagView(3);
    }

    public /* synthetic */ void lambda$initListener$3$DedicateTopDialog(View view) {
        dismiss();
        DedicateTopAllDialog.newInstance(this.liveType, this.anchorInfoItem, this.onLivePusherInfoCallback).show(getFragmentManager());
    }

    public /* synthetic */ void lambda$initListener$5$DedicateTopDialog(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        AnchorEntity anchorEntity = (AnchorEntity) baseQuickAdapter.getItem(i);
        if (anchorEntity != null) {
            if (!anchorEntity.isRankHideBoolean() || TextUtils.equals(anchorEntity.userId, UserInfoManager.getInstance().getUserId())) {
                OnLivePusherInfoCallback onLivePusherInfoCallback2 = this.onLivePusherInfoCallback;
                if (onLivePusherInfoCallback2 != null) {
                    onLivePusherInfoCallback2.onClickUserAvatarListener(AppUtils.formatUserEntity(anchorEntity));
                    return;
                }
                return;
            }
            NobilityOpenTipsDialog.newInstance(13, new View.OnClickListener() {
                /* class com.slzhibo.library.ui.view.dialog.$$Lambda$DedicateTopDialog$xyJtKtWmylBhkG4AWgtEAUkRIUo */

                public final void onClick(View view) {
                    DedicateTopDialog.this.lambda$null$4$DedicateTopDialog(view);
                }
            }).show(getChildFragmentManager());
        }
    }

    public /* synthetic */ void lambda$null$4$DedicateTopDialog(View view) {
        OnLivePusherInfoCallback onLivePusherInfoCallback2;
        if (!AppUtils.isAnchorLiveType(this.liveType) && (onLivePusherInfoCallback2 = this.onLivePusherInfoCallback) != null) {
            onLivePusherInfoCallback2.onNobilityOpenListener();
        }
    }

    private void initChangeView() {
        if (AppUtils.isAnchorLiveType(getArgumentsInt(ConstantUtils.BUNDLE_VALUE_LIVE_TYPE, 2))) {
            this.tvDayTop.setVisibility(View.VISIBLE);
            this.tvWeekTop.setVisibility(View.VISIBLE);
            this.tvMonthTop.setVisibility(View.VISIBLE);
            this.tvAllTop.setVisibility(View.VISIBLE);
            hideTopTagView(1);
            return;
        }
        ArrayList<String> liveRankConfig = SysConfigInfoManager.getInstance().getLiveRankConfig();
        if (liveRankConfig == null || liveRankConfig.isEmpty()) {
            this.tvDayTop.setVisibility(View.VISIBLE);
            this.tvWeekTop.setVisibility(View.VISIBLE);
            this.tvMonthTop.setVisibility(View.VISIBLE);
            hideTopTagView(1);
            return;
        }
        Iterator<String> it2 = liveRankConfig.iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            char c = 65535;
            switch (next.hashCode()) {
                case 96673:
                    if (next.equals("all")) {
                        c = 3;
                        break;
                    }
                    break;
                case 99228:
                    if (next.equals(ConstantUtils.TOP_DAY)) {
                        c = 0;
                        break;
                    }
                    break;
                case 3645428:
                    if (next.equals(ConstantUtils.TOP_WEEK)) {
                        c = 1;
                        break;
                    }
                    break;
                case 104080000:
                    if (next.equals(ConstantUtils.TOP_MONTH)) {
                        c = 2;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                this.tvDayTop.setVisibility(View.VISIBLE);
            } else if (c == 1) {
                this.tvWeekTop.setVisibility(View.VISIBLE);
            } else if (c == 2) {
                this.tvMonthTop.setVisibility(View.VISIBLE);
            } else if (c == 3) {
                this.tvAllTop.setVisibility(View.VISIBLE);
            }
        }
        hideTopTagView(formatTopValue(liveRankConfig));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0044, code lost:
        if (r7.equals(com.slzhibo.library.utils.ConstantUtils.TOP_DAY) != false) goto L_0x0048;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004a  */
    private int formatTopValue(List<String> list) {
        if (list != null && !list.isEmpty()) {
            char c = 0;
            String str = list.get(0);
            int hashCode = str.hashCode();
            if (hashCode != 99228) {
                if (hashCode != 3645428) {
                    if (hashCode == 104080000 && str.equals(ConstantUtils.TOP_MONTH)) {
                        c = 2;
                        if (c != 0) {
                            if (c == 1) {
                                return 3;
                            }
                            if (c != 2) {
                                return 1;
                            }
                            return 2;
                        }
                    }
                } else if (str.equals(ConstantUtils.TOP_WEEK)) {
                    c = 1;
                    if (c != 0) {
                    }
                }
            }
            c = 65535;
            if (c != 0) {
            }
        }
        return 1;
    }

    @Override // com.slzhibo.library.ui.view.dialog.base.BaseBottomDialogFragment
    public double getHeightScale() {
        return this.maxHeightScale;
    }

    private void hideTopTagView(int i) {
        this.dayTagValue = i;
        boolean z = false;
        this.tvDayTop.setSelected(i == 1);
        this.tvMonthTop.setSelected(i == 2);
        TextView textView = this.tvWeekTop;
        if (i == 3) {
            z = true;
        }
        textView.setSelected(z);
        setTextViewDrawable(i);
        sendRequest(this.anchorId, i);
    }

    private void setTextViewDrawable(int i) {
        Drawable drawable = getResources().getDrawable(R.drawable.fq_shape_top_tag_red_divider);
        this.tvDayTop.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, i == 1 ? drawable : null);
        this.tvMonthTop.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, i == 2 ? drawable : null);
        TextView textView = this.tvWeekTop;
        if (i != 3) {
            drawable = null;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, drawable);
    }

    private String getDateType() {
        int i = this.dayTagValue;
        if (i == 1) {
            return ConstantUtils.TOP_DAY;
        }
        if (i != 2) {
            return i != 3 ? ConstantUtils.TOP_DAY : ConstantUtils.TOP_WEEK;
        }
        return ConstantUtils.TOP_MONTH;
    }

    private void sendRequest(String str, final int i) {
        List<AnchorEntity> charmDataList = getCharmDataList(i);
        if (charmDataList != null) {
            this.mAdapter.setNewData(charmDataList);
        } else {
            ApiRetrofit.getInstance().getApiService().getDedicateTopListService(new RequestParams().getContributionListParams(getDateType(), str)).map(new ServerResultFunction<List<AnchorEntity>>() {
                /* class com.slzhibo.library.ui.view.dialog.DedicateTopDialog.AnonymousClass2 */
            }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(bindToLifecycle()).subscribe(new Observer<List<AnchorEntity>>() {
                /* class com.slzhibo.library.ui.view.dialog.DedicateTopDialog.AnonymousClass1 */

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    DedicateTopDialog.this.showLoading(true);
                }

                public void onNext(List<AnchorEntity> list) {
                    if (list != null) {
                        DedicateTopDialog.this.putCharmDataList(list, i);
                        DedicateTopDialog.this.mAdapter.setNewData(list);
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    DedicateTopDialog.this.showLoading(false);
                }

                @Override // io.reactivex.Observer
                public void onComplete() {
                    DedicateTopDialog.this.showLoading(false);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @SuppressLint("WrongConstant")
    private void showLoading(boolean z) {
        int i = 0;
        this.progressBar.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
        LinearLayout linearLayout = this.llContentBg;
        if (z) {
            i = 4;
        }
        linearLayout.setVisibility(i);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void putCharmDataList(List<AnchorEntity> list, int i) {
        if (this.listMap == null) {
            this.listMap = new SparseArray<>();
        }
        if (i == 1) {
            this.listMap.put(11, formatList(list));
        } else if (i == 2) {
            this.listMap.put(13, formatList(list));
        } else if (i == 3) {
            this.listMap.put(12, formatList(list));
        }
    }

    private List<AnchorEntity> formatList(List<AnchorEntity> list) {
        if (list == null) {
            return new ArrayList();
        }
        return list.isEmpty() ? new ArrayList() : list;
    }

    private List<AnchorEntity> getCharmDataList(int i) {
        SparseArray<List<AnchorEntity>> sparseArray = this.listMap;
        if (sparseArray == null) {
            return null;
        }
        if (i == 1) {
            return sparseArray.get(11);
        }
        if (i == 2) {
            return sparseArray.get(13);
        }
        if (i != 3) {
            return new ArrayList();
        }
        return sparseArray.get(12);
    }

    public void setOnLivePusherInfoCallback(OnLivePusherInfoCallback onLivePusherInfoCallback2) {
        this.onLivePusherInfoCallback = onLivePusherInfoCallback2;
    }

    @Override // android.support.v4.app.Fragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxDialogFragment
    public void onDestroy() {
        super.onDestroy();
        SparseArray<List<AnchorEntity>> sparseArray = this.listMap;
        if (sparseArray != null) {
            sparseArray.clear();
            this.listMap = null;
        }
    }
}
