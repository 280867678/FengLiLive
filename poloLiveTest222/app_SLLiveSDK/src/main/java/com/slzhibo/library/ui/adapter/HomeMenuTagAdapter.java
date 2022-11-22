package com.slzhibo.library.ui.adapter;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.slzhibo.library.base.BaseFragment;
import com.slzhibo.library.model.LabelEntity;

import java.util.List;

/* loaded from: classes4.dex */
public class HomeMenuTagAdapter extends FragmentStatePagerAdapter {
    private final List<LabelEntity> entityList;
    private final List<BaseFragment> mFragments;

    public HomeMenuTagAdapter(List<BaseFragment> list, List<LabelEntity> list2, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mFragments = list;
        this.entityList = list2;
    }

    @Override // android.support.v4.app.FragmentStatePagerAdapter
    public BaseFragment getItem(int i) {
        return this.mFragments.get(i);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.mFragments.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return this.entityList.get(i).name;
    }
}
