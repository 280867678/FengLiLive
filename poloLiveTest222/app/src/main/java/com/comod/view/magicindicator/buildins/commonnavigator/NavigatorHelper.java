package com.comod.view.magicindicator.buildins.commonnavigator;

//package p067e.p130f.p148b.p149a;

import android.util.SparseArray;
import android.util.SparseBooleanArray;

/* renamed from: e.f.b.a.b */
/* loaded from: classes.dex */
public class NavigatorHelper {

    /* renamed from: a */
    public SparseBooleanArray f13245a = new SparseBooleanArray();

    /* renamed from: b */
    public SparseArray<Float> f13246b = new SparseArray<>();

    /* renamed from: c */
    public int f13247c;

    /* renamed from: d */
    public int f13248d;

    /* renamed from: e */
    public int f13249e;

    /* renamed from: f */
    public float f13250f;

    /* renamed from: g */
    public int f13251g;

    /* renamed from: h */
    public boolean f13252h;

    /* renamed from: i */
    public AbstractC4333a f13253i;

    /* compiled from: NavigatorHelper.java */
    /* renamed from: e.f.b.a.b$a */
    /* loaded from: classes.dex */
    public interface AbstractC4333a {
        /* renamed from: a */
        void mo8996a(int i, int i2);

        /* renamed from: a */
        void mo8995a(int i, int i2, float f, boolean z);

        /* renamed from: b */
        void mo8994b(int i, int i2);

        /* renamed from: b */
        void mo8993b(int i, int i2, float f, boolean z);
    }

    /* renamed from: a */
    public void m9006a(int i, float f, int i2) {
        boolean z;
        float f2 = i + f;
        boolean z2 = this.f13250f <= f2;
        if (this.f13251g == 0) {
            for (int i3 = 0; i3 < this.f13247c; i3++) {
                if (i3 != this.f13248d) {
                    if (!this.f13245a.get(i3)) {
                        m9007a(i3);
                    }
                    if (this.f13246b.get(i3, Float.valueOf(0.0f)).floatValue() != 1.0f) {
                        m9001b(i3, 1.0f, false, true);
                    }
                }
            }
            m9005a(this.f13248d, 1.0f, false, true);
            m9002b(this.f13248d);
        } else if (f2 != this.f13250f) {
            int i4 = i + 1;
            if (f != 0.0f || !z2) {
                z = true;
            } else {
                i4 = i - 1;
                z = false;
            }
            for (int i5 = 0; i5 < this.f13247c; i5++) {
                if (!(i5 == i || i5 == i4 || this.f13246b.get(i5, Float.valueOf(0.0f)).floatValue() == 1.0f)) {
                    m9001b(i5, 1.0f, z2, true);
                }
            }
            if (!z) {
                float f3 = 1.0f - f;
                m9001b(i4, f3, true, false);
                m9005a(i, f3, true, false);
            } else if (z2) {
                m9001b(i, f, true, false);
                m9005a(i4, f, true, false);
            } else {
                float f4 = 1.0f - f;
                m9001b(i4, f4, false, false);
                m9005a(i, f4, false, false);
            }
        } else {
            return;
        }
        this.f13250f = f2;
    }

    /* renamed from: b */
    public final void m9001b(int i, float f, boolean z, boolean z2) {
        if (!(this.f13252h || i == this.f13249e || this.f13251g == 1)) {
            int i2 = this.f13248d;
            if ((!(i == i2 - 1 || i == i2 + 1) || this.f13246b.get(i, Float.valueOf(0.0f)).floatValue() == 1.0f) && !z2) {
                return;
            }
        }
        AbstractC4333a aVar = this.f13253i;
        if (aVar != null) {
            aVar.mo8993b(i, this.f13247c, f, z);
        }
        this.f13246b.put(i, Float.valueOf(f));
    }

    /* renamed from: c */
    public void m8999c(int i) {
        this.f13251g = i;
    }

    /* renamed from: d */
    public void m8998d(int i) {
        this.f13249e = this.f13248d;
        this.f13248d = i;
        m9002b(this.f13248d);
        for (int i2 = 0; i2 < this.f13247c; i2++) {
            if (i2 != this.f13248d && !this.f13245a.get(i2)) {
                m9007a(i2);
            }
        }
    }

    /* renamed from: e */
    public void m8997e(int i) {
        this.f13247c = i;
        this.f13245a.clear();
        this.f13246b.clear();
    }

    public void setNavigatorScrollListener(AbstractC4333a aVar) {
        this.f13253i = aVar;
    }

    /* renamed from: c */
    public int m9000c() {
        return this.f13247c;
    }

    /* renamed from: b */
    public final void m9002b(int i) {
        AbstractC4333a aVar = this.f13253i;
        if (aVar != null) {
            aVar.mo8994b(i, this.f13247c);
        }
        this.f13245a.put(i, false);
    }

    /* renamed from: b */
    public int m9003b() {
        return this.f13251g;
    }

    /* renamed from: a */
    public final void m9005a(int i, float f, boolean z, boolean z2) {
        if (this.f13252h || i == this.f13248d || this.f13251g == 1 || z2) {
            AbstractC4333a aVar = this.f13253i;
            if (aVar != null) {
                aVar.mo8995a(i, this.f13247c, f, z);
            }
            this.f13246b.put(i, Float.valueOf(1.0f - f));
        }
    }

    /* renamed from: a */
    public final void m9007a(int i) {
        AbstractC4333a aVar = this.f13253i;
        if (aVar != null) {
            aVar.mo8996a(i, this.f13247c);
        }
        this.f13245a.put(i, true);
    }

    /* renamed from: a */
    public void m9004a(boolean z) {
        this.f13252h = z;
    }

    /* renamed from: a */
    public int m9008a() {
        return this.f13248d;
    }
}

