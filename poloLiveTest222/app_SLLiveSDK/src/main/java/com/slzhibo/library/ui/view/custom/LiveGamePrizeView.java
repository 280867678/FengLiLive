package com.slzhibo.library.ui.view.custom;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.slzhibo.library.R;
import com.slzhibo.library.model.ComponentsEntity;
import com.slzhibo.library.model.SocketMessageEvent;
import com.slzhibo.library.utils.CacheUtils;

public class LiveGamePrizeView extends RelativeLayout {
    private TextView tvContent;
    private TextView tvTitle;

    public LiveGamePrizeView(Context context) {
        this(context, null);
    }

    public LiveGamePrizeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        RelativeLayout.inflate(context, R.layout.fq_layout_live_game_open_prize_view, this);
        this.tvTitle = (TextView) findViewById(R.id.tv_game_title);
        this.tvContent = (TextView) findViewById(R.id.tv_game_content);
    }

    public void initData(SocketMessageEvent.ResultData resultData) {
        String string = getContext().getString(R.string.fq_game_open_prize_result);
        ComponentsEntity localCacheComponentsByGameId = CacheUtils.getLocalCacheComponentsByGameId(resultData.gameId);
        if (localCacheComponentsByGameId != null) {
            string = localCacheComponentsByGameId.name;
        }
        TextView textView = this.tvTitle;
        if (TextUtils.isEmpty(string)) {
            string = getContext().getString(R.string.fq_game_open_prize_result);
        }
        textView.setText(string);
        this.tvContent.setText(resultData.content);
    }
}
