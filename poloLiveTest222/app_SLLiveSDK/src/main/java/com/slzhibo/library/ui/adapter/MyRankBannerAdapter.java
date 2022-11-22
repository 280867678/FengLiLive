package com.slzhibo.library.ui.adapter;



import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.R;
import com.slzhibo.library.model.IndexRankEntity;
//import com.slzhibo.library.ui.activity.home.RankingNewActivity;
import com.slzhibo.library.ui.view.widget.bgabanner.BGABanner;
import com.slzhibo.library.ui.view.widget.bgabanner.BGAOnNoDoubleClickListener;
import com.slzhibo.library.utils.ConstantUtils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.slzhibo.library.ui.adapter.MyRankBannerAdapter */
/* loaded from: classes6.dex */
public class MyRankBannerAdapter implements BGABanner.Adapter<View, String> {
    private List<IndexRankEntity> indexRanks;
    private Context mContext;

    public MyRankBannerAdapter(Context context) {
        this.mContext = context;
    }

    public MyRankBannerAdapter(List<IndexRankEntity> list, Context context) {
        this.mContext = context;
        this.indexRanks = list;
    }

    public void fillBannerItem(BGABanner bGABanner, View view, @Nullable String str, int i) {
        initView(view, i);
    }

    private void initView(View view, int i) {
        final View findViewById = view.findViewById(R.id.rl_left);
        int i2 = i * 2;
        IndexRankEntity indexRankEntity = this.indexRanks.get(i2);
        final String type = indexRankEntity.getType();
        ((TextView) view.findViewById(R.id.tv_left_title)).setText(getTitle(type));
        findViewById.setOnClickListener(new BGAOnNoDoubleClickListener() { // from class: com.slzhibo.library.ui.adapter.MyRankBannerAdapter.1
            @Override // com.slzhibo.library.p115ui.view.widget.bgabanner.BGAOnNoDoubleClickListener
            public void onNoDoubleClick(View view2) {
                Toast.makeText(mContext, "MyRankBannerAdapter.this.mContext,::55:: RankingNewActivity.class", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MyRankBannerAdapter.this.mContext, RankingNewActivity.class);
//                intent.putExtra(ConstantUtils.RESULT_FLAG, MyRankBannerAdapter.this.getPosition(type));
//                intent.putStringArrayListExtra(ConstantUtils.RESULT_ITEM, MyRankBannerAdapter.this.getRankTypeList());
//                MyRankBannerAdapter.this.mContext.startActivity(intent);
            }
        });
        List<String> avatars = indexRankEntity.getAvatars();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_left_avatars);
        recyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.slzhibo.library.ui.adapter.-$$Lambda$MyRankBannerAdapter$7g01UTAmeLPeXQ8Z2anRAdVJc3A
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return MyRankBannerAdapter.lambda$initView$0(findViewById, view2, motionEvent);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext, RecyclerView.HORIZONTAL, true);
        linearLayoutManager.setStackFromEnd(true);
        RankEnterAvatarsAdapter rankEnterAvatarsAdapter = new RankEnterAvatarsAdapter(R.layout.fq_item_layout_head_view_rank_enter);
        recyclerView.setAdapter(rankEnterAvatarsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        rankEnterAvatarsAdapter.bindToRecyclerView(recyclerView);
        rankEnterAvatarsAdapter.setNewData(avatars);
        View findViewById2 = view.findViewById(R.id.line);
        TextView textView = (TextView) view.findViewById(R.id.tv_right_title);
        final View findViewById3 = view.findViewById(R.id.rl_right);
        findViewById2.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.fq_D9D9D9));
        int i3 = i2 + 1;
        if (i3 > this.indexRanks.size() - 1) {
            findViewById2.setVisibility(View.GONE);
            findViewById3.setVisibility(View.GONE);
            return;
        }
        IndexRankEntity indexRankEntity2 = this.indexRanks.get(i3);
        final String type2 = indexRankEntity2.getType();
        textView.setText(getTitle(type2));
        findViewById2.setVisibility(View.VISIBLE);
        findViewById3.setVisibility(View.VISIBLE);
        findViewById3.setOnClickListener(new BGAOnNoDoubleClickListener() { // from class: com.slzhibo.library.ui.adapter.MyRankBannerAdapter.2
            @Override // com.slzhibo.library.p115ui.view.widget.bgabanner.BGAOnNoDoubleClickListener
            public void onNoDoubleClick(View view2) {
                Toast.makeText(mContext, "MyRankBannerAdapter.this.mContext,::94:: RankingNewActivity.class", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MyRankBannerAdapter.this.mContext, RankingNewActivity.class);
//                intent.putExtra(ConstantUtils.RESULT_FLAG, MyRankBannerAdapter.this.getPosition(type2));
//                intent.putStringArrayListExtra(ConstantUtils.RESULT_ITEM, MyRankBannerAdapter.this.getRankTypeList());
//                MyRankBannerAdapter.this.mContext.startActivity(intent);
            }
        });
        List<String> avatars2 = indexRankEntity2.getAvatars();
        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.rv_right_avatars);
        recyclerView2.setOnTouchListener(new View.OnTouchListener() { // from class: com.slzhibo.library.ui.adapter.-$$Lambda$MyRankBannerAdapter$WEyPs7glzdayrTyu9easiVMMYSc
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return MyRankBannerAdapter.lambda$initView$1(findViewById3, view2, motionEvent);
            }
        });
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this.mContext, RecyclerView.HORIZONTAL, true);
        linearLayoutManager2.setStackFromEnd(true);
        RankEnterAvatarsAdapter rankEnterAvatarsAdapter2 = new RankEnterAvatarsAdapter(R.layout.fq_item_layout_head_view_rank_enter);
        recyclerView2.setAdapter(rankEnterAvatarsAdapter2);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        rankEnterAvatarsAdapter2.bindToRecyclerView(recyclerView2);
        rankEnterAvatarsAdapter2.setNewData(avatars2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$initView$0(View view, View view2, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) {
            return false;
        }
        view.performClick();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$initView$1(View view, View view2, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) {
            return false;
        }
        view.performClick();
        return false;
    }

    private String getTitle(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -1309357992) {
            if (str.equals(ConstantUtils.RANK_TYPE_EXPENSE)) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != -1184259671) {
            if (hashCode == -622460826 && str.equals(ConstantUtils.RANK_TYPE_WEEKSTAR)) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals(ConstantUtils.RANK_TYPE_INCOME)) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0) {
            return this.mContext.getString(R.string.fq_idol);
        }
        if (c != 1) {
            return c != 2 ? "" : this.mContext.getString(R.string.fq_week_star_gift);
        }
        return this.mContext.getString(R.string.fq_rich_man);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPosition(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -1309357992) {
            if (str.equals(ConstantUtils.RANK_TYPE_EXPENSE)) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != -1184259671) {
            if (hashCode == -622460826 && str.equals(ConstantUtils.RANK_TYPE_WEEKSTAR)) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals(ConstantUtils.RANK_TYPE_INCOME)) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0) {
            return 0;
        }
        if (c != 1) {
            return c != 2 ? 0 : 2;
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<String> getRankTypeList() {
        List<IndexRankEntity> list = this.indexRanks;
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (IndexRankEntity indexRankEntity : this.indexRanks) {
            arrayList.add(indexRankEntity.getType());
        }
        return arrayList;
    }
}

