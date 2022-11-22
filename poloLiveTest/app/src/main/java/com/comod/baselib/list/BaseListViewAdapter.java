package com.comod.baselib.list;



import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.blmvl.blvl.VHDelegateImpl.VHDelegateImpl;
import com.comod.baselib.list.BaseListViewAdapter.C0890c;
import java.util.ArrayList;
import java.util.List;
//import p067e.p130f.p131a.p133b.VHDelegateImpl;

/* loaded from: classes.dex */
public abstract class BaseListViewAdapter<T extends C0890c> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public AbstractC0888a<T> mClickListener;
    public List<T> mList = new ArrayList();
    public AbstractC0889b<T> mLongClickListener;

    /* renamed from: com.comod.baselib.list.BaseListViewAdapter$a */
    /* loaded from: classes.dex */
    public interface AbstractC0888a<T> {
        /* renamed from: a */
        void mo9802a(View view, T t, int i);
    }

    /* renamed from: com.comod.baselib.list.BaseListViewAdapter$b */
    /* loaded from: classes.dex */
    public interface AbstractC0889b<T> {
        /* renamed from: a */
        boolean m20149a(View view, T t, int i);
    }

    /* renamed from: com.comod.baselib.list.BaseListViewAdapter$c */
    /* loaded from: classes.dex */
    public static class C0890c {
        public int startPosition;
        public int viewRenderType;

        public int getStartPosition() {
            return this.startPosition;
        }

        public int getViewRenderType() {
            return this.viewRenderType;
        }

        public void setStartPosition(int i) {
            this.startPosition = i;
        }

        public void setViewRenderType(int i) {
            this.viewRenderType = i;
        }
    }

    /* renamed from: a */
    public /* synthetic */ void m20151a(VHDelegateImpl dVar, int i, View view) {
        dVar.mo9235a(view, getItem(i), i);
        AbstractC0888a<T> aVar = this.mClickListener;
        if (aVar != null) {
            aVar.mo9802a(view, getItem(i), i);
        }
    }

    public void addItem(T t) {
        List<T> list = this.mList;
        if (list != null) {
            list.add(t);
            notifyDataSetChanged();
        }
    }

    public void addItems(List<T> list) {
        try {
            int i = 0;
            int size = (this.mList == null || this.mList.isEmpty()) ? 0 : this.mList.size() - 1;
            if (list != null && !list.isEmpty()) {
                i = list.size();
            }
            if (this.mList == null) {
                this.mList = new ArrayList();
            }
            if (i > 0) {
                this.mList.addAll(list);
                notifyItemRangeChanged(size, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0014 A[Catch: Exception -> 0x000d, TryCatch #0 {Exception -> 0x000d, blocks: (B:3:0x0002, B:5:0x0008, B:9:0x0010, B:11:0x0014, B:13:0x001d), top: B:16:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001d A[Catch: Exception -> 0x000d, TRY_LEAVE, TryCatch #0 {Exception -> 0x000d, blocks: (B:3:0x0002, B:5:0x0008, B:9:0x0010, B:11:0x0014, B:13:0x001d), top: B:16:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addItemsNotifyAll(List<T> list) {
        int size;
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    size = list.size();
                    if (this.mList == null) {
                        this.mList = new ArrayList();
                    }
                    if (size <= 0) {
                        this.mList.addAll(list);
                        notifyDataSetChanged();
                        return;
                    }
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        size = 0;
        if (this.mList == null) {
        }
        if (size <= 0) {
        }
    }

    /* renamed from: b */
    public /* synthetic */ boolean m20150b(VHDelegateImpl dVar, int i, View view) {
        boolean b = dVar.m9231b(view, getItem(i), i);
        AbstractC0889b<T> bVar = this.mLongClickListener;
        return bVar != null ? bVar.m20149a(view, getItem(i), i) : b;
    }

    public void clear() {
        List<T> list = this.mList;
        if (list != null && !list.isEmpty()) {
            this.mList.clear();
            notifyDataSetChanged();
        }
    }

    public void clearItems() {
        List<T> list = this.mList;
        if (list != null && !list.isEmpty()) {
            this.mList.clear();
        }
    }

    public abstract VHDelegateImpl<T> createVHDelegate(int i);

    public T getItem(int i) {
        try {
            if (i < this.mList.size()) {
                return this.mList.get(i);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        try {
            return this.mList.size();
        } catch (Exception unused) {
            return 0;
        }
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        try {
            return this.mList.get(i).getViewRenderType();
        } catch (Exception unused) {
            return -1;
        }
    }

    public List<T> getItems() {
        return this.mList;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        try {
            BaseListViewHolder baseListViewHolder = (BaseListViewHolder) viewHolder;
            final VHDelegateImpl<T> a = baseListViewHolder.m20148a();
            a.mo9233a( getItem(i), i);
            baseListViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: e.f.a.b.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseListViewAdapter.this.m20151a(a, i, view);
                }
            });
            baseListViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: e.f.a.b.b
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return BaseListViewAdapter.this.m20150b(a, i, view);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshAddItems(List<T> list) {
        try {
            if (this.mList != null && !this.mList.isEmpty()) {
                this.mList.clear();
            }
            int size = (list == null || list.isEmpty()) ? 0 : list.size();
            if (this.mList == null) {
                this.mList = new ArrayList();
            }
            if (size > 0) {
                this.mList.addAll(list);
                notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshClearItems() {
        List<T> list = this.mList;
        if (list != null && !list.isEmpty()) {
            this.mList.clear();
            notifyDataSetChanged();
        }
    }

    public void removeItem(T t) {
        if (!this.mList.isEmpty()) {
            this.mList.remove(t);
        }
        notifyDataSetChanged();
    }

    public void removeItems(List<T> list) {
        List<T> list2 = this.mList;
        if (list2 != null && !list2.isEmpty()) {
            this.mList.removeAll(list);
            notifyDataSetChanged();
        }
    }

    public void setItem(int i, T t) {
        try {
            if (this.mList != null) {
                this.mList.set(i, t);
                notifyItemChanged(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setItemWithoutNotify(int i, T t) {
        try {
            if (this.mList != null) {
                this.mList.set(i, t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnItemClickListener(AbstractC0888a<T> aVar) {
        this.mClickListener = aVar;
    }

    public void setOnItemLongClickListener(AbstractC0889b<T> bVar) {
        this.mLongClickListener = bVar;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        try {
            VHDelegateImpl<T> createVHDelegate = createVHDelegate(i);
            return new BaseListViewHolder(createVHDelegate.m9234a(viewGroup, this), createVHDelegate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addItem(int i, T t) {
        try {
            if (this.mList != null) {
                this.mList.add(i, t);
                notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeItem(int i) {
        if (!this.mList.isEmpty() && i < this.mList.size()) {
            this.mList.remove(i);
        }
        notifyDataSetChanged();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0014 A[Catch: Exception -> 0x000d, TryCatch #0 {Exception -> 0x000d, blocks: (B:3:0x0002, B:5:0x0008, B:9:0x0010, B:11:0x0014, B:13:0x001d), top: B:16:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001d A[Catch: Exception -> 0x000d, TRY_LEAVE, TryCatch #0 {Exception -> 0x000d, blocks: (B:3:0x0002, B:5:0x0008, B:9:0x0010, B:11:0x0014, B:13:0x001d), top: B:16:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addItems(int i, List<T> list) {
        int size;
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    size = list.size();
                    if (this.mList == null) {
                        this.mList = new ArrayList();
                    }
                    if (size <= 0) {
                        this.mList.addAll(i, list);
                        notifyDataSetChanged();
                        return;
                    }
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        size = 0;
        if (this.mList == null) {
        }
        if (size <= 0) {
        }
    }
}

