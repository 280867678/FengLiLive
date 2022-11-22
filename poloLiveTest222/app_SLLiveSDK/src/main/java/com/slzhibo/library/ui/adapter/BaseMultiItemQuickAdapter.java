package com.slzhibo.library.ui.adapter;


import android.util.SparseIntArray;
import android.view.ViewGroup;

import androidx.annotation.IntRange;
import androidx.annotation.LayoutRes;
//import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
import com.slzhibo.library.utils.adapter.entity.IExpandable;
import com.slzhibo.library.utils.adapter.entity.MultiItemEntity;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class BaseMultiItemQuickAdapter<T extends MultiItemEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    public static final int DEFAULT_VIEW_TYPE = -255;
    public static final int TYPE_NOT_FOUND = -404;
    public SparseIntArray layouts;

    public BaseMultiItemQuickAdapter(List<T> list) {
        super(list);
    }

    private int getLayoutId(int i) {
        return this.layouts.get(i, -404);
    }

    public void addItemType(int i, @LayoutRes int i2) {
        if (this.layouts == null) {
            this.layouts = new SparseIntArray();
        }
        this.layouts.put(i, i2);
    }

    @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter
    public int getDefItemViewType(int i) {
        MultiItemEntity multiItemEntity = (MultiItemEntity) this.mData.get(i);
        if (multiItemEntity != null) {
            return multiItemEntity.getItemType();
        }
        return -255;
    }

    public int getParentPositionInAll(int i) {
        List<T> data = getData();
        MultiItemEntity multiItemEntity = (MultiItemEntity) getItem(i);
        if (isExpandable(multiItemEntity)) {
            IExpandable iExpandable = (IExpandable) multiItemEntity;
            for (int i2 = i - 1; i2 >= 0; i2--) {
                MultiItemEntity multiItemEntity2 = (MultiItemEntity) data.get(i2);
                if (isExpandable(multiItemEntity2) && iExpandable.getLevel() > ((IExpandable) multiItemEntity2).getLevel()) {
                    return i2;
                }
            }
            return -1;
        }
        for (int i3 = i - 1; i3 >= 0; i3--) {
            if (isExpandable((MultiItemEntity) data.get(i3))) {
                return i3;
            }
        }
        return -1;
    }

    @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter
    public K onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        return createBaseViewHolder(viewGroup, getLayoutId(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.slzhibo.library.utils.adapter.BaseQuickAdapter
    public void remove(@IntRange(from = 0) int i) {
        List<T> list = this.mData;
        if (list.size() != 0 && i >= 0 && i < list.size()) {
            MultiItemEntity multiItemEntity = (MultiItemEntity) this.mData.get(i);
            if (multiItemEntity instanceof IExpandable) {
                removeAllChild((IExpandable) multiItemEntity, i);
            }
            removeDataFromParent((T) multiItemEntity);
            super.remove(i);
        }
    }

    public void removeAllChild(IExpandable iExpandable, int i) {
        List subItems;
        if (!(!iExpandable.isExpanded() || (subItems = iExpandable.getSubItems()) == null || subItems.size() == 0)) {
            int size = subItems.size();
            for (int i2 = 0; i2 < size; i2++) {
                remove(i + 1);
            }
        }
    }

    public void removeDataFromParent(T t) {
        IExpandable iExpandable;
        int parentPosition = getParentPosition(t);
        if (parentPosition >= 0 && (iExpandable = (IExpandable) this.mData.get(parentPosition)) != t) {
            iExpandable.getSubItems().remove(t);
        }
    }

    public void setDefaultViewTypeLayout(@LayoutRes int i) {
        addItemType(-255, i);
    }

    public boolean isExpandable(MultiItemEntity multiItemEntity) {
        return multiItemEntity != null && (multiItemEntity instanceof IExpandable);
    }
}

