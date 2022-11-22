package com.slzhibo.library.ui.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.slzhibo.library.R;
import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.ui.interfaces.OnLiveFloatingWindowCallback;
import com.slzhibo.library.ui.view.widget.BannerWebView;
import com.slzhibo.library.ui.view.widget.bgabanner.BGABanner;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.RxViewUtils;

import java.util.ArrayList;
import java.util.List;

public class LiveAdBannerView extends LinearLayout {
    private BGABanner bannerView;
    private ImageView ivAdClose;
    private ImageView ivAdImg;
    private ImageView ivBannerClose;
    private final Context mContext;
    private OnLiveFloatingWindowCallback onAdBannerClickListener;
    private RelativeLayout rlAdBg;
    private RelativeLayout rlBannerBg;
    private BannerWebView wvVerticalAd;

    public LiveAdBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initView();
    }

    private void initView() {
        LinearLayout.inflate(this.mContext, R.layout.fq_layout_live_banner_view, this);
        this.bannerView = (BGABanner) findViewById(R.id.banner_top);
        this.ivAdImg = (ImageView) findViewById(R.id.iv_ad);
        this.ivBannerClose = (ImageView) findViewById(R.id.iv_banner_close);
        this.ivAdClose = (ImageView) findViewById(R.id.iv_ad_close);
        this.rlAdBg = (RelativeLayout) findViewById(R.id.rl_ad_bg);
        this.rlBannerBg = (RelativeLayout) findViewById(R.id.rl_banner_bg);
        this.wvVerticalAd = (BannerWebView) findViewById(R.id.wv_vertical_ad);
        this.ivAdClose.setOnClickListener(new OnClickListener() {
            /* class com.slzhibo.library.ui.view.custom.$$Lambda$LiveAdBannerView$vBHUaTvZ309oQDiH8ggUpKWO44 */

            public final void onClick(View view) {
                LiveAdBannerView.this.lambda$initView$0$LiveAdBannerView(view);
            }
        });
        this.ivBannerClose.setOnClickListener(new OnClickListener() {
            /* class com.slzhibo.library.ui.view.custom.$$Lambda$LiveAdBannerView$IQRijXIS4dEDaIyXJOJYYGcYxp0 */

            public final void onClick(View view) {
                LiveAdBannerView.this.lambda$initView$1$LiveAdBannerView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initView$0$LiveAdBannerView(View view) {
        this.rlAdBg.setVisibility(View.GONE);
    }

    public /* synthetic */ void lambda$initView$1$LiveAdBannerView(View view) {
        this.rlBannerBg.setVisibility(View.GONE);
    }

    public void initAdBannerImages(final String str, final String str2, List<BannerEntity> list) {
        if (this.bannerView != null) {
            List<BannerEntity> imgBannerItem = AppUtils.getImgBannerItem(list);
            if (imgBannerItem == null || imgBannerItem.isEmpty()) {
                this.rlBannerBg.setVisibility(View.GONE);
                return;
            }
            this.rlBannerBg.setVisibility(View.VISIBLE);
            this.ivBannerClose.setVisibility(isAllowClose(list) ? View.VISIBLE : View.INVISIBLE);
            this.bannerView.setAdapter(new BGABanner.Adapter() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$LiveAdBannerView$u8VZOeslVsdoMsvoLcKU51j4WKM
                @Override // com.slzhibo.library.ui.view.widget.bgabanner.BGABanner.Adapter
                public final void fillBannerItem(BGABanner bGABanner, View view, Object obj, int i) {
                    LiveAdBannerView.this.lambda$initAdBannerImages$2$LiveAdBannerView(str, str2, bGABanner, view, (BannerEntity) obj, i);
                }
            });
            BGABanner bGABanner = this.bannerView;
            boolean z = imgBannerItem.size() > 1;
            bGABanner.setAutoPlayAble(z);
            this.bannerView.setDataWithWebView(getBannerView(imgBannerItem), imgBannerItem);
            this.bannerView.setDelegate(new BGABanner.Delegate() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$LiveAdBannerView$fJOlqI2Fg158Q5-ekg3yTC3NprY
                @Override // com.slzhibo.library.ui.view.widget.bgabanner.BGABanner.Delegate
                public final void onBannerItemClick(BGABanner bGABanner2, View view, Object obj, int i) {
                    LiveAdBannerView.this.lambda$initAdBannerImages$3$LiveAdBannerView(bGABanner2, view, (BannerEntity) obj, i);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initAdBannerImages$2$LiveAdBannerView(String str, String str2, BGABanner bGABanner, View view, BannerEntity bannerEntity, int i) {
        if (view instanceof ImageView) {
            loadImg(bannerEntity.img, (ImageView) view);
        }
        if (view instanceof BannerWebView) {
            BannerWebView bannerWebView = (BannerWebView) view;
            if (!bannerWebView.isLoadBoolean()) {
                bannerWebView.loadUrl(getWebH5Url(bannerEntity.img, str, str2));
            }
        }
    }

    public /* synthetic */ void lambda$initAdBannerImages$3$LiveAdBannerView(BGABanner bGABanner, View view, BannerEntity bannerEntity, int i) {
        onAdBannerClick(bannerEntity);
    }

    public void initVerticalAdImage(String str, String str2, List<BannerEntity> list) {
        if (list == null || list.isEmpty()) {
            this.rlAdBg.setVisibility(View.INVISIBLE);
            return;
        }
        List<BannerEntity> imgBannerItem = AppUtils.getImgBannerItem(list);
        if (imgBannerItem.size() > 0) {
            final BannerEntity bannerEntity = imgBannerItem.get(0);
            this.rlAdBg.setVisibility(View.VISIBLE);
            this.ivAdClose.setVisibility(bannerEntity.isAllowClose() ? View.INVISIBLE : View.VISIBLE);
            if (bannerEntity.isWebH5View()) {
                this.ivAdImg.setVisibility(View.INVISIBLE);
                this.wvVerticalAd.setVisibility(View.VISIBLE);
                this.wvVerticalAd.loadUrl(getWebH5Url(bannerEntity.img, str, str2));
            } else {
                this.wvVerticalAd.setVisibility(View.INVISIBLE);
                this.ivAdImg.setVisibility(View.VISIBLE);
                loadImg(bannerEntity.img, this.ivAdImg);
            }
            RxViewUtils.getInstance().throttleFirst(this.rlAdBg, 500, new RxViewUtils.RxViewAction() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$LiveAdBannerView$djHf8yvmbFfWuA4OowH08wc8Elw
                @Override // com.slzhibo.library.utils.RxViewUtils.RxViewAction
                public final void action(Object obj) {
                    LiveAdBannerView.this.lambda$initVerticalAdImage$4$LiveAdBannerView(bannerEntity, obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initVerticalAdImage$4$LiveAdBannerView(BannerEntity bannerEntity, Object obj) {
        onAdBannerClick(bannerEntity);
    }

    private List<View> getBannerView(List<BannerEntity> list) {
        ArrayList arrayList = new ArrayList();
        for (BannerEntity bannerEntity : list) {
            if (bannerEntity.isWebH5View()) {
                arrayList.add(new BannerWebView(getContext()));
            } else {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                arrayList.add(imageView);
            }
        }
        return arrayList;
    }

    private void onAdBannerClick(BannerEntity bannerEntity) {
        AppUtils.onAdvChannelHitsListener(this.mContext, bannerEntity.id, "2");
        OnLiveFloatingWindowCallback onLiveFloatingWindowCallback = this.onAdBannerClickListener;
        if (onLiveFloatingWindowCallback != null) {
            onLiveFloatingWindowCallback.onAdBannerClickListener(bannerEntity);
        }
    }

    private void loadImg(String str, ImageView imageView) {
        GlideUtils.loadAdBannerImageForRoundView(this.mContext, imageView, str, R.drawable.fq_shape_default_banner_cover_bg);
    }

    public void setOnAdBannerClickListener(OnLiveFloatingWindowCallback onLiveFloatingWindowCallback) {
        this.onAdBannerClickListener = onLiveFloatingWindowCallback;
    }

    private boolean isAllowClose(List<BannerEntity> list) {
        boolean z = true;
        for (BannerEntity bannerEntity : list) {
            if (bannerEntity.isAllowClose()) {
                z = false;
            }
        }
        return z;
    }

    private String getWebH5Url(String str, String str2, String str3) {
        return str + "?" + "appId" + "=" + str2 + "&" + "openId" + "=" + str3;
    }
}
