package com.slzhibo.library.analytics;

import android.annotation.TargetApi;
import android.view.View;

import java.util.List;

@TargetApi(16)
/* loaded from: classes6.dex */
public abstract class ViewVisitor implements Pathfinder.Accumulator {
    private static final String TAG = "SA.ViewVisitor";
    private final List<Pathfinder.PathElement> mPath;
    private final Pathfinder mPathfinder = new Pathfinder();

    public abstract void cleanup();

    protected abstract String name();

    protected ViewVisitor(List<Pathfinder.PathElement> list) {
        this.mPath = list;
    }

    public void visit(View view) {
        this.mPathfinder.findTargetsInRoot(view, this.mPath, this);
    }
}
