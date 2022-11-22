package com.slzhibo.library.ui.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ScreenUtils;
import com.slzhibo.library.R;
import com.slzhibo.library.http.ApiRetrofit;
import com.slzhibo.library.http.RequestParams;
import com.slzhibo.library.http.function.HttpResultFunction;
import com.slzhibo.library.http.function.ServerResultFunction;
import com.slzhibo.library.model.LYModelDataEntity;
import com.slzhibo.library.ui.interfaces.OnLYDeviceCallback;
import com.slzhibo.library.utils.StringUtils;
import com.slzhibo.library.utils.live.SimpleRxObserver;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LYControlWindowView extends RelativeLayout {
    private float borderXLeft;
    private float borderXRight;
    private float borderYBottom;
    private float borderYTop;
    private OnLYDeviceCallback callback;
    private CompositeDisposable compositeDisposable;
    private final long countdownSecond;
    private int lastX;
    private int lastY;
    private String liveId;
    private Context mContext;
    private Disposable mCountdownDisposable;
    private List<LYModelDataEntity.Data> modelDataList;
    private TextView tvFreeModelSend;
    private TextView tvMode_1;
    private TextView tvMode_2;
    private TextView tvMode_3;
    private TextView tvModelCustom;

    public LYControlWindowView(Context context) {
        this(context, null);
    }

    public LYControlWindowView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LYControlWindowView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.countdownSecond = 300;
        initView(context);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.borderYBottom = (float) (ScreenUtils.getScreenHeight() - i2);
        this.borderYTop = 0.0f;
        this.borderXLeft = 0.0f;
        this.borderXRight = (float) (ScreenUtils.getAppScreenWidth() - i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0017, code lost:
        if (r5 != 3) goto L_0x0087;
     */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    int i = x - this.lastX;
                    int i2 = y - this.lastY;
                    float y2 = getY();
                    float f = this.borderYBottom;
                    if (y2 < f || i2 <= 0) {
                        float y3 = getY();
                        float f2 = this.borderYTop;
                        if (y3 > f2 || i2 >= 0) {
                            setY(getY() + ((float) i2));
                        } else {
                            setY(f2);
                        }
                    } else {
                        setY(f);
                    }
                    float x2 = getX();
                    float f3 = this.borderXRight;
                    if (x2 < f3 || i <= 0) {
                        float x3 = getX();
                        float f4 = this.borderXLeft;
                        if (x3 > f4 || i >= 0) {
                            setX(getX() + ((float) i));
                        } else {
                            setX(f4);
                        }
                    } else {
                        setX(f3);
                    }
                }
            }
            getParent().requestDisallowInterceptTouchEvent(false);
        } else {
            getParent().requestDisallowInterceptTouchEvent(true);
            this.lastX = x;
            this.lastY = y;
        }
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        boolean z = false;
        if (!(action == 0 || action == 1 || action != 2)) {
            int i = y - this.lastY;
            if (Math.abs(x - this.lastX) > 5 || Math.abs(i) > 5) {
                z = true;
            }
        }
        this.lastX = x;
        this.lastY = y;
        return z;
    }

    private void initView(Context context) {
        RelativeLayout.inflate(context, R.layout.fq_layout_ly_telecontrol_window_view, this);
        this.mContext = context;
        this.tvMode_1 = (TextView) findViewById(R.id.tv_window_model_1);
        this.tvMode_2 = (TextView) findViewById(R.id.tv_window_model_2);
        this.tvMode_3 = (TextView) findViewById(R.id.tv_window_model_3);
        this.tvModelCustom = (TextView) findViewById(R.id.tv_window_model_custom);
        this.tvFreeModelSend = (TextView) findViewById(R.id.tv_free_model_send);
        this.compositeDisposable = new CompositeDisposable();
        this.tvMode_1.setOnClickListener(new OnClickListener() {
            /* class com.slzhibo.library.ui.view.custom.LYControlWindowView.AnonymousClass1 */

            public void onClick(View view) {
                if (LYControlWindowView.this.callback != null) {
                    LYControlWindowView.this.callback.onModelSelectedCallback(0, LYControlWindowView.this.getModelDataItem(0));
                }
            }
        });
        this.tvMode_2.setOnClickListener(new OnClickListener() {
            /* class com.slzhibo.library.ui.view.custom.LYControlWindowView.AnonymousClass2 */

            public void onClick(View view) {
                if (LYControlWindowView.this.callback != null) {
                    LYControlWindowView.this.callback.onModelSelectedCallback(1, LYControlWindowView.this.getModelDataItem(1));
                }
            }
        });
        this.tvMode_3.setOnClickListener(new OnClickListener() {
            /* class com.slzhibo.library.ui.view.custom.LYControlWindowView.AnonymousClass3 */

            public void onClick(View view) {
                if (LYControlWindowView.this.callback != null) {
                    LYControlWindowView.this.callback.onModelSelectedCallback(2, LYControlWindowView.this.getModelDataItem(2));
                }
            }
        });
        this.tvFreeModelSend.setOnClickListener(new OnClickListener() {
            /* class com.slzhibo.library.ui.view.custom.LYControlWindowView.AnonymousClass4 */

            public void onClick(View view) {
                if (LYControlWindowView.this.callback != null) {
                    LYControlWindowView.this.callback.onModelSelectedCallback(5, null);
                }
            }
        });
        this.tvModelCustom.setOnClickListener(new OnClickListener() {
            /* class com.slzhibo.library.ui.view.custom.LYControlWindowView.AnonymousClass5 */

            public void onClick(View view) {
                if (LYControlWindowView.this.callback != null) {
                    LYControlWindowView.this.setVisibility(View.INVISIBLE);
                    LYControlWindowView.this.callback.onModelSelectedCallback(-2, null);
                }
            }
        });
        findViewById(R.id.iv_show_model_dialog).setOnClickListener(new OnClickListener() {
            /* class com.slzhibo.library.ui.view.custom.LYControlWindowView.AnonymousClass6 */

            public void onClick(View view) {
                if (LYControlWindowView.this.callback != null) {
                    LYControlWindowView.this.setVisibility(View.INVISIBLE);
                    LYControlWindowView.this.callback.onShowModelDataDialog();
                }
            }
        });
        findViewById(R.id.iv_close).setOnClickListener(new OnClickListener() {
            /* class com.slzhibo.library.ui.view.custom.LYControlWindowView.AnonymousClass7 */

            public void onClick(View view) {
                LYControlWindowView.this.setVisibility(View.INVISIBLE);
                if (LYControlWindowView.this.callback != null) {
                    LYControlWindowView.this.callback.onClose();
                }
            }
        });
    }

    public void setCallback(OnLYDeviceCallback onLYDeviceCallback) {
        this.callback = onLYDeviceCallback;
    }

    public void onReleaseData() {
        setVisibility(View.INVISIBLE);
        clearCompositeDisposable();
        List<LYModelDataEntity.Data> list = this.modelDataList;
        if (list != null) {
            list.clear();
        }
    }

    public void updateModelData(String str) {
        this.liveId = str;
        sendModelDataRequest();
    }

    private void sendModelDataRequest() {
        ApiRetrofit.getInstance().getApiService().modelDataListService(new RequestParams().getLiveId(this.liveId)).map(new ServerResultFunction<LYModelDataEntity>() {
            /* class com.slzhibo.library.ui.view.custom.LYControlWindowView.AnonymousClass9 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<LYModelDataEntity>(this.mContext) {
            /* class com.slzhibo.library.ui.view.custom.LYControlWindowView.AnonymousClass8 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                LYControlWindowView.this.compositeDisposableAdd(disposable);
            }

            public void accept(LYModelDataEntity lYModelDataEntity) {
                LYControlWindowView.this.modelDataList = lYModelDataEntity.modelData;
                LYControlWindowView.this.tvMode_1.setText(LYControlWindowView.this.getModelTypeTitle(0));
                LYControlWindowView.this.tvMode_2.setText(LYControlWindowView.this.getModelTypeTitle(1));
                LYControlWindowView.this.tvMode_3.setText(LYControlWindowView.this.getModelTypeTitle(2));
                if (LYControlWindowView.this.modelDataList == null) {
                    LYControlWindowView.this.tvMode_1.setVisibility(View.GONE);
                    LYControlWindowView.this.tvMode_2.setVisibility(View.GONE);
                    LYControlWindowView.this.tvMode_3.setVisibility(View.GONE);
                } else if (LYControlWindowView.this.modelDataList.size() >= 3) {
                    LYControlWindowView.this.tvMode_1.setVisibility(View.VISIBLE);
                    LYControlWindowView.this.tvMode_2.setVisibility(View.VISIBLE);
                    LYControlWindowView.this.tvMode_3.setVisibility(View.VISIBLE);
                } else if (LYControlWindowView.this.modelDataList.size() >= 2) {
                    LYControlWindowView.this.tvMode_1.setVisibility(View.VISIBLE);
                    LYControlWindowView.this.tvMode_2.setVisibility(View.VISIBLE);
                    LYControlWindowView.this.tvMode_3.setVisibility(View.GONE);
                } else if (LYControlWindowView.this.modelDataList.size() >= 1) {
                    LYControlWindowView.this.tvMode_1.setVisibility(View.VISIBLE);
                    LYControlWindowView.this.tvMode_2.setVisibility(View.GONE);
                    LYControlWindowView.this.tvMode_3.setVisibility(View.GONE);
                } else {
                    LYControlWindowView.this.tvMode_1.setVisibility(View.GONE);
                    LYControlWindowView.this.tvMode_2.setVisibility(View.GONE);
                    LYControlWindowView.this.tvMode_3.setVisibility(View.GONE);
                }
                LYControlWindowView.this.tvModelCustom.setVisibility(View.VISIBLE);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private LYModelDataEntity.Data getModelDataItem(int i) {
        List<LYModelDataEntity.Data> list = this.modelDataList;
        if (list != null && !list.isEmpty()) {
            try {
                return this.modelDataList.get(i);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String getModelTypeTitle(int i) {
        LYModelDataEntity.Data modelDataItem = getModelDataItem(i);
        if (modelDataItem != null) {
            return StringUtils.formatStrLen(modelDataItem.name, 4);
        }
        if (i == 0) {
            return this.mContext.getString(R.string.fq_ly_mode_1);
        }
        if (i == 1) {
            return this.mContext.getString(R.string.fq_ly_mode_2);
        }
        if (i != 2) {
            return this.mContext.getString(R.string.fq_ly_mode_1);
        }
        return this.mContext.getString(R.string.fq_ly_mode_3);
    }

    public void startCountdown() {
        if (this.countdownSecond > 0) {
            countdownDispose();
            this.mCountdownDisposable = Flowable.intervalRange(0, this.countdownSecond + 1, 0, 1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Subscription>() {
                /* class com.slzhibo.library.ui.view.custom.LYControlWindowView.AnonymousClass12 */

                public void accept(Subscription subscription) throws Exception {
                    LYControlWindowView.this.tvFreeModelSend.setEnabled(false);
                    LYControlWindowView.this.tvFreeModelSend.setText(LYControlWindowView.this.mContext.getString(R.string.fq_ly_free_mode_send, String.valueOf(LYControlWindowView.this.countdownSecond)));
                }
            }).doOnNext(new Consumer<Long>() {
                /* class com.slzhibo.library.ui.view.custom.LYControlWindowView.AnonymousClass11 */

                public void accept(Long l) throws Exception {
                    long longValue = LYControlWindowView.this.countdownSecond - l.longValue();
                    LYControlWindowView.this.tvFreeModelSend.setText(LYControlWindowView.this.mContext.getString(R.string.fq_ly_free_mode_send, String.valueOf(longValue)));
                }
            }).doOnComplete(new Action() {
                /* class com.slzhibo.library.ui.view.custom.LYControlWindowView.AnonymousClass10 */

                @Override // io.reactivex.functions.Action
                public void run() throws Exception {
                    LYControlWindowView.this.tvFreeModelSend.setEnabled(true);
                    LYControlWindowView.this.tvFreeModelSend.setText(R.string.fq_ly_free_mode_send_start);
                    if (LYControlWindowView.this.callback != null) {
                        LYControlWindowView.this.callback.onFreeCountdownCompleteCallback();
                    }
                }
            }).subscribe();
            compositeDisposableAdd(this.mCountdownDisposable);
        }
    }

    private void countdownDispose() {
        Disposable disposable = this.mCountdownDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.mCountdownDisposable.dispose();
        }
    }

    private void clearCompositeDisposable() {
        CompositeDisposable compositeDisposable2 = this.compositeDisposable;
        if (compositeDisposable2 != null) {
            compositeDisposable2.clear();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void compositeDisposableAdd(Disposable disposable) {
        CompositeDisposable compositeDisposable2 = this.compositeDisposable;
        if (compositeDisposable2 != null) {
            compositeDisposable2.add(disposable);
        }
    }
}
