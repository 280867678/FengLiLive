package com.slzhibo.library.analytics.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.LruCache;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.slzhibo.library.analytics.AppSateManager;
import com.slzhibo.library.analytics.visual.ViewNode;



import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/* loaded from: classes6.dex */
public class ViewUtil {
    private static LruCache<Class, String> sClassNameCache = null;
    private static boolean sHaveCustomRecyclerView = false;
    private static boolean sHaveRecyclerView = haveRecyclerView();
    private static Class sRecyclerViewClass;
    private static Method sRecyclerViewGetChildAdapterPositionMethod;
    private static SparseArray sViewCache;

    private static String getCanonicalName(Class cls) {
        String str;
        if (Build.VERSION.SDK_INT >= 12) {
            if (sClassNameCache == null) {
                sClassNameCache = new LruCache<>(100);
            }
            str = sClassNameCache.get(cls);
        } else {
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            str = cls.getCanonicalName();
            if (TextUtils.isEmpty(str)) {
                str = "Anonymous";
            }
            if (Build.VERSION.SDK_INT >= 12) {
                synchronized (ViewUtil.class) {
                    sClassNameCache.put(cls, str);
                }
            }
            checkCustomRecyclerView(cls, str);
        }
        return str;
    }

    private static boolean instanceOfSupportViewPager(Object obj) {
        try {
            return Class.forName("androidx.viewpager.widget.ViewPager").isInstance(obj);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private static boolean instanceOfAndroidXViewPager(Object obj) {
        try {
            return Class.forName("androidx.viewpager.widget.ViewPager").isInstance(obj);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean instanceOfRecyclerView(Object obj) {
        Class cls;
        Class<?> cls2;
        try {
            try {
                cls2 = Class.forName("androidx.recyclerview.widget.RecyclerView");
            } catch (ClassNotFoundException unused) {
                cls2 = Class.forName("androidx.recyclerview.widget.RecyclerView");
            }
            return cls2.isInstance(obj);
        } catch (ClassNotFoundException unused2) {
            return sHaveCustomRecyclerView && obj != null && (cls = sRecyclerViewClass) != null && cls.isAssignableFrom(obj.getClass());
        }
    }

    private static boolean instanceOfSupportSwipeRefreshLayout(Object obj) {
        Class<?> cls;
        try {
            try {
                cls = Class.forName("androidx.swiperefreshlayout.widget.SwipeRefreshLayout");
            } catch (ClassNotFoundException unused) {
                cls = Class.forName("androidx.swiperefreshlayout.widget.SwipeRefreshLayout");
            }
            return cls.isInstance(obj);
        } catch (ClassNotFoundException unused2) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean instanceOfSupportListMenuItemView(Object obj) {
        try {
            return Class.forName("androidx.appcompat.view.menu.ListMenuItemView").isInstance(obj);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean instanceOfAndroidXListMenuItemView(Object obj) {
        try {
            return Class.forName("androidx.appcompat.view.menu.ListMenuItemView").isInstance(obj);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean instanceOfBottomNavigationItemView(Object obj) {
        try {
            return Class.forName("com.google.android.material.bottomnavigation.BottomNavigationItemView").isInstance(obj);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private static Object instanceOfFragmentRootView(View view, View view2) {
        Object fragmentFromView = AopUtil.getFragmentFromView(view);
        Object fragmentFromView2 = AopUtil.getFragmentFromView(view2);
        if (fragmentFromView != null || fragmentFromView2 == null) {
            return null;
        }
        return fragmentFromView2;
    }

    public static boolean instanceOfX5WebView(Object obj) {
        try {
            return Class.forName("com.tencent.smtt.sdk.WebView").isInstance(obj);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private static int getChildAdapterPositionInRecyclerView(View view, ViewGroup viewGroup) {
        Object invoke;
        if (instanceOfRecyclerView(viewGroup)) {
            try {
                sRecyclerViewGetChildAdapterPositionMethod = viewGroup.getClass().getDeclaredMethod("getChildAdapterPosition", View.class);
            } catch (NoSuchMethodException unused) {
            }
            if (sRecyclerViewGetChildAdapterPositionMethod == null) {
                try {
                    sRecyclerViewGetChildAdapterPositionMethod = viewGroup.getClass().getDeclaredMethod("getChildPosition", View.class);
                } catch (NoSuchMethodException unused2) {
                }
            }
            try {
                if (sRecyclerViewGetChildAdapterPositionMethod == null || (invoke = sRecyclerViewGetChildAdapterPositionMethod.invoke(viewGroup, view)) == null) {
                    return -1;
                }
                return ((Integer) invoke).intValue();
            } catch (IllegalAccessException | InvocationTargetException unused3) {
                return -1;
            }
        } else if (sHaveCustomRecyclerView) {
            return invokeCRVGetChildAdapterPositionMethod(viewGroup, view);
        } else {
            return -1;
        }
    }

    private static int getCurrentItem(View view) {
        try {
            Object invoke = view.getClass().getMethod("getCurrentItem", new Class[0]).invoke(view, new Object[0]);
            if (invoke != null) {
                return ((Integer) invoke).intValue();
            }
            return -1;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object getItemData(View view) {
        try {
            return view.getClass().getMethod("getItemData", new Class[0]).invoke(view, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    private static boolean haveRecyclerView() {
        try {
            try {
                Class.forName("androidx.recyclerview.widget.RecyclerView");
                return true;
            } catch (ClassNotFoundException unused) {
                Class.forName("androidx.recyclerview.widget.RecyclerView");
                return true;
            }
        } catch (ClassNotFoundException unused2) {
            return false;
        }
    }

    @TargetApi(12)
    private static void checkCustomRecyclerView(Class<?> cls, String str) {
        if (!sHaveRecyclerView && !sHaveCustomRecyclerView && str != null && str.contains("RecyclerView")) {
            try {
                if (findRecyclerInSuper(cls) != null && sRecyclerViewGetChildAdapterPositionMethod != null) {
                    sRecyclerViewClass = cls;
                    sHaveCustomRecyclerView = true;
                }
            } catch (Exception unused) {
            }
        }
    }

    private static Class<?> findRecyclerInSuper(Class<?> cls) {
        while (cls != null && !cls.equals(ViewGroup.class)) {
            try {
                sRecyclerViewGetChildAdapterPositionMethod = cls.getDeclaredMethod("getChildAdapterPosition", View.class);
            } catch (NoSuchMethodException unused) {
            }
            if (sRecyclerViewGetChildAdapterPositionMethod == null) {
                try {
                    sRecyclerViewGetChildAdapterPositionMethod = cls.getDeclaredMethod("getChildPosition", View.class);
                } catch (NoSuchMethodException unused2) {
                }
            }
            if (sRecyclerViewGetChildAdapterPositionMethod != null) {
                return cls;
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    private static int invokeCRVGetChildAdapterPositionMethod(View view, View view2) {
        try {
            if (view.getClass() == sRecyclerViewClass) {
                return ((Integer) sRecyclerViewGetChildAdapterPositionMethod.invoke(view, view2)).intValue();
            }
            return -1;
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return -1;
        }
    }

    private static boolean isListView(View view) {
        return (view instanceof AdapterView) || instanceOfRecyclerView(view) || instanceOfAndroidXViewPager(view) || instanceOfSupportViewPager(view);
    }

    @SuppressLint({"NewApi"})
    private static boolean isViewSelfVisible(View view) {
        if (view == null || view.getWindowVisibility() == View.GONE) {
            return false;
        }
        if (WindowHelper.isDecorView(view.getClass())) {
            return true;
        }
        if (view.getWidth() <= 0 || view.getHeight() <= 0 || view.getAlpha() <= 0.0f || !view.getLocalVisibleRect(new Rect())) {
            return false;
        }
        return !(view.getVisibility() == View.VISIBLE || view.getAnimation() == null || !view.getAnimation().getFillAfter()) || view.getVisibility() == View.VISIBLE;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean viewVisibilityInParents(View view) {
        if (view == null || !isViewSelfVisible(view)) {
            return false;
        }
        ViewParent parent = view.getParent();
        while (parent instanceof View) {
            if (!isViewSelfVisible((View) parent) || (parent = parent.getParent()) == null) {
                return false;
            }
            while (parent instanceof View) {
            }
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    public static void invalidateLayerTypeView(View[] viewArr) {
        for (View view : viewArr) {
            if (viewVisibilityInParents(view) && view.isHardwareAccelerated()) {
                checkAndInvalidate(view);
                if (view instanceof ViewGroup) {
                    invalidateViewGroup((ViewGroup) view);
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    private static void checkAndInvalidate(View view) {
        if (view.getLayerType() != View.LAYER_TYPE_NONE) {
            view.invalidate();
        }
    }

    private static void invalidateViewGroup(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (isViewSelfVisible(childAt)) {
                checkAndInvalidate(childAt);
                if (childAt instanceof ViewGroup) {
                    invalidateViewGroup((ViewGroup) childAt);
                }
            }
        }
    }

    public static int getMainWindowCount(View[] viewArr) {
        WindowHelper.init();
        int i = 0;
        for (View view : viewArr) {
            if (view != null) {
                i += WindowHelper.getWindowPrefix(view).equals(WindowHelper.getMainWindowPrefix()) ? 1 : 0;
            }
        }
        return i;
    }

    public static boolean isWindowNeedTraverse(View view, String str, boolean z) {
        if (view.hashCode() == AppSateManager.getInstance().getCurrentRootWindowsHashCode()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        if (!z) {
            return true;
        }
        return (view.getWindowVisibility() == View.GONE || view.getVisibility() != View.VISIBLE || TextUtils.equals(str, WindowHelper.getMainWindowPrefix()) || view.getWidth() == 0 || view.getHeight() == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ViewNode getViewPathAndPosition(View view) {
        int indexOf;
        ArrayList arrayList = new ArrayList(8);
        arrayList.add(view);
        for (ViewParent parent = view.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            arrayList.add((ViewGroup) parent);
        }
        int size = arrayList.size() - 1;
        View view2 = (View) arrayList.get(size);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        String str = null;
        if (!(view2 instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view2;
        for (int i = size - 1; i >= 0; i--) {
            View view3 = (View) arrayList.get(i);
            ViewNode viewNode = getViewNode(view3, viewGroup.indexOfChild(view3));
            if (viewNode != null) {
                if (!TextUtils.isEmpty(viewNode.getViewPath()) && viewNode.getViewPath().contains("-") && !TextUtils.isEmpty(str) && (indexOf = sb2.indexOf("-")) != -1) {
                    sb2.replace(indexOf, indexOf + 1, String.valueOf(str));
                }
                sb.append(viewNode.getViewOriginalPath());
                sb2.append(viewNode.getViewPath());
                str = viewNode.getViewPosition();
            }
            if (!(view3 instanceof ViewGroup)) {
                break;
            }
            viewGroup = (ViewGroup) view3;
        }
        return new ViewNode(str, sb.toString(), sb2.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getElementSelector(View view) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        do {
            ViewParent parent = view.getParent();
            int childIndex = AopUtil.getChildIndex(parent, view);
            arrayList.add(view.getClass().getCanonicalName() + "[" + childIndex + "]");
            z = parent instanceof ViewGroup;
            if (z) {
                view = (ViewGroup) parent;
                continue;
            }
        } while (z);
        Collections.reverse(arrayList);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < arrayList.size(); i++) {
            sb.append((String) arrayList.get(i));
            if (i != arrayList.size() - 1) {
                sb.append("/");
            }
        }
        return sb.toString();
    }

    private static int getViewPosition(View view, int i) {
        int childAdapterPositionInRecyclerView;
        if (view.getParent() == null || !(view.getParent() instanceof ViewGroup)) {
            return i;
        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (instanceOfAndroidXViewPager(viewGroup) || instanceOfSupportViewPager(viewGroup)) {
            return getCurrentItem(viewGroup);
        }
        if (viewGroup instanceof AdapterView) {
            return i + ((AdapterView) viewGroup).getFirstVisiblePosition();
        }
        return (!instanceOfRecyclerView(viewGroup) || (childAdapterPositionInRecyclerView = getChildAdapterPositionInRecyclerView(view, viewGroup)) < 0) ? i : childAdapterPositionInRecyclerView;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0203  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ViewNode getViewNode(View view, int i) {
        String str;
        int viewPosition = getViewPosition(view, i);
        ViewParent parent = view.getParent();
        if (parent == null) {
            return null;
        }
        if ((WindowHelper.isDecorView(view.getClass()) && !(parent instanceof View)) || !(parent instanceof View)) {
            return null;
        }
        View view2 = (View) parent;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        String canonicalName = getCanonicalName(view.getClass());
        if (view2 instanceof ExpandableListView) {
            ExpandableListView expandableListView = (ExpandableListView) view2;
            long expandableListPosition = expandableListView.getExpandableListPosition(viewPosition);
            if (ExpandableListView.getPackedPositionType(expandableListPosition) != 2) {
                int packedPositionGroup = ExpandableListView.getPackedPositionGroup(expandableListPosition);
                int packedPositionChild = ExpandableListView.getPackedPositionChild(expandableListPosition);
                if (packedPositionChild != -1) {
                    str = String.format(Locale.CHINA, "%d:%d", Integer.valueOf(packedPositionGroup), Integer.valueOf(packedPositionChild));
                    sb2.append((CharSequence) sb);
                    sb2.append("/ELVG[");
                    sb2.append(packedPositionGroup);
                    sb2.append("]/ELVC[-]/");
                    sb2.append(canonicalName);
                    sb2.append("[0]");
                    sb.append("/ELVG[");
                    sb.append(packedPositionGroup);
                    sb.append("]/ELVC[");
                    sb.append(packedPositionChild);
                    sb.append("]/");
                    sb.append(canonicalName);
                    sb.append("[0]");
                } else {
                    str = String.format(Locale.CHINA, "%d", Integer.valueOf(packedPositionGroup));
                    sb2.append((CharSequence) sb);
                    sb2.append("/ELVG[-]/");
                    sb2.append(canonicalName);
                    sb2.append("[0]");
                    sb.append("/ELVG[");
                    sb.append(packedPositionGroup);
                    sb.append("]/");
                    sb.append(canonicalName);
                    sb.append("[0]");
                }
                if (WindowHelper.isDecorView(view2.getClass())) {
                    if (sb.length() > 0) {
                        sb.deleteCharAt(0);
                    }
                    if (sb2.length() > 0) {
                        sb2.deleteCharAt(0);
                    }
                }
                if (!TextUtils.isEmpty(str)) {
                    if (sViewCache == null) {
                        sViewCache = new SparseArray();
                    }
                    sViewCache.put(view2.hashCode(), str);
                }
                ViewNode viewContentAndType = getViewContentAndType(view);
                return new ViewNode(str, sb.toString(), sb2.toString(), viewContentAndType.getViewContent(), viewContentAndType.getViewType());
            }
            if (viewPosition < expandableListView.getHeaderViewsCount()) {
                sb.append("/ELH[");
                sb.append(viewPosition);
                sb.append("]/");
                sb.append(canonicalName);
                sb.append("[0]");
                sb2.append("/ELH[");
                sb2.append(viewPosition);
                sb2.append("]/");
                sb2.append(canonicalName);
                sb2.append("[0]");
            } else {
                int count = viewPosition - (expandableListView.getCount() - expandableListView.getFooterViewsCount());
                sb.append("/ELF[");
                sb.append(count);
                sb.append("]/");
                sb.append(canonicalName);
                sb.append("[0]");
                sb2.append("/ELF[");
                sb2.append(count);
                sb2.append("]/");
                sb2.append(canonicalName);
                sb2.append("[0]");
            }
            str = null;
            if (WindowHelper.isDecorView(view2.getClass())) {
            }
            if (!TextUtils.isEmpty(str)) {
            }
            ViewNode viewContentAndType2 = getViewContentAndType(view);
            return new ViewNode(str, sb.toString(), sb2.toString(), viewContentAndType2.getViewContent(), viewContentAndType2.getViewType());
        }
        if (isListView(view2)) {
            str = String.format(Locale.CHINA, "%d", Integer.valueOf(viewPosition));
            sb2.append((CharSequence) sb);
            sb2.append("/");
            sb2.append(canonicalName);
            sb2.append("[-]");
            sb.append("/");
            sb.append(canonicalName);
            sb.append("[");
            sb.append(str);
            sb.append("]");
        } else {
            if (instanceOfSupportSwipeRefreshLayout(view2)) {
                sb.append("/");
                sb.append(canonicalName);
                sb.append("[0]");
                sb2.append("/");
                sb2.append(canonicalName);
                sb2.append("[0]");
            } else {
                Object instanceOfFragmentRootView = instanceOfFragmentRootView(view2, view);
                if (instanceOfFragmentRootView != null) {
                    String canonicalName2 = getCanonicalName(instanceOfFragmentRootView.getClass());
                    sb.append("/");
                    sb.append(canonicalName2);
                    sb.append("[0]");
                    sb2.append("/");
                    sb2.append(canonicalName2);
                    sb2.append("[0]");
                } else {
                    ViewParent parent2 = view2.getParent();
                    if (parent2 instanceof View) {
                        View view3 = (View) parent2;
                        if (sViewCache == null) {
                            sViewCache = new SparseArray();
                        }
                        String str2 = (String) sViewCache.get(view3.hashCode());
                        if (!TextUtils.isEmpty(str2)) {
                            str = str2;
                            int childIndex = AopUtil.getChildIndex(parent, view);
                            sb.append("/");
                            sb.append(canonicalName);
                            sb.append("[");
                            sb.append(childIndex);
                            sb.append("]");
                            sb2.append("/");
                            sb2.append(canonicalName);
                            sb2.append("[");
                            sb2.append(childIndex);
                            sb2.append("]");
                        }
                    }
                    str = null;
                    int childIndex2 = AopUtil.getChildIndex(parent, view);
                    sb.append("/");
                    sb.append(canonicalName);
                    sb.append("[");
                    sb.append(childIndex2);
                    sb.append("]");
                    sb2.append("/");
                    sb2.append(canonicalName);
                    sb2.append("[");
                    sb2.append(childIndex2);
                    sb2.append("]");
                }
            }
            str = null;
        }
        if (WindowHelper.isDecorView(view2.getClass())) {
        }
        if (!TextUtils.isEmpty(str)) {
        }
        ViewNode viewContentAndType22 = getViewContentAndType(view);
        return new ViewNode(str, sb.toString(), sb2.toString(), viewContentAndType22.getViewContent(), viewContentAndType22.getViewType());
    }

    public static void clear() {
        SparseArray sparseArray = sViewCache;
        if (sparseArray != null) {
            sparseArray.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isTrackEvent(View view, boolean z) {
        if (view instanceof CheckBox) {
            if (!z) {
                return false;
            }
        } else if (view instanceof RadioButton) {
            if (!z) {
                return false;
            }
        } else if (view instanceof ToggleButton) {
            if (!z) {
                return false;
            }
        } else if ((view instanceof CompoundButton) && !z) {
            return false;
        }
        if (view instanceof RatingBar) {
            return z;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0140, code lost:
        if (android.text.TextUtils.isEmpty(r1) != false) goto L_0x0175;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v19, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v31, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARN: Type inference failed for: r1v67 */
    /* JADX WARN: Type inference failed for: r1v68 */
    /* JADX WARN: Type inference failed for: r1v69 */
    /* JADX WARN: Type inference failed for: r1v70 */
    /* JADX WARN: Type inference failed for: r1v71 */
    /* JADX WARN: Type inference failed for: r2v12, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v15, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Unknown variable types count: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ViewNode getViewContentAndType(View view) {
        Class<?> cls;
        Field field;
        String canonicalName = view.getClass().getCanonicalName();
        CharSequence charSequence = null;
        if (view instanceof CheckBox) {
            canonicalName = AopUtil.getViewType(canonicalName, "CheckBox");
            charSequence = ((CheckBox) view).getText();
        } else if (view instanceof RadioButton) {
            canonicalName = AopUtil.getViewType(canonicalName, "RadioButton");
            charSequence = ((RadioButton) view).getText();
        } else if (view instanceof ToggleButton) {
            canonicalName = AopUtil.getViewType(canonicalName, "ToggleButton");
            charSequence = AopUtil.getCompoundButtonText(view);
        } else if (view instanceof CompoundButton) {
            canonicalName = AopUtil.getViewTypeByReflect(view);
            charSequence = AopUtil.getCompoundButtonText(view);
        } else if (view instanceof Button) {
            canonicalName = AopUtil.getViewType(canonicalName, "Button");
            charSequence = ((Button) view).getText();
        } else if (view instanceof CheckedTextView) {
            canonicalName = AopUtil.getViewType(canonicalName, "CheckedTextView");
            charSequence = ((CheckedTextView) view).getText();
        } else if (view instanceof TextView) {
            canonicalName = AopUtil.getViewType(canonicalName, "TextView");
            charSequence = ((TextView) view).getText();
        } else if (view instanceof ImageView) {
            canonicalName = AopUtil.getViewType(canonicalName, "ImageView");
            ImageView imageView = (ImageView) view;
            if (!TextUtils.isEmpty(imageView.getContentDescription())) {
                charSequence = imageView.getContentDescription().toString();
            }
        } else if (view instanceof RatingBar) {
            canonicalName = AopUtil.getViewType(canonicalName, "RatingBar");
            charSequence = String.valueOf(((RatingBar) view).getRating());
        } else if (view instanceof SeekBar) {
            canonicalName = AopUtil.getViewType(canonicalName, "SeekBar");
            charSequence = String.valueOf(((SeekBar) view).getProgress());
        } else {
//            charSequence = view instanceof Spinner;
            try {
                try {
                    if (charSequence != null) {
                        canonicalName = AopUtil.getViewType(canonicalName, "Spinner");
                        String traverseView = AopUtil.traverseView(new StringBuilder(), (ViewGroup) view);
//                        ?? isEmpty = TextUtils.isEmpty(traverseView);
                        charSequence = traverseView;
//                        charSequence = isEmpty;
//                        if (isEmpty == 0) {
//                            String charSequence2 = traverseView.toString();
//                            charSequence = charSequence2.substring(0, traverseView.length() - 1);
//                            charSequence = charSequence2;
//                        }
                    } else {
                        Object instanceOfTabView = instanceOfTabView(view);
                        if (instanceOfTabView != null) {
                            charSequence = getTabLayoutContent(instanceOfTabView);
                        } else if (instanceOfBottomNavigationItemView(view)) {
                            Object itemData = getItemData(view);
                            if (itemData != null) {
                                try {
                                    cls = Class.forName("androidx.appcompat.view.menu.MenuItemImpl");
                                } catch (ClassNotFoundException unused) {
                                    cls = null;
                                }
                                if (cls != null) {
                                    try {
                                        field = cls.getDeclaredField("mTitle");
                                    } catch (NoSuchFieldException unused2) {
                                        field = null;
                                    }
                                    if (field != null) {
                                        field.setAccessible(true);
                                        try {
                                            charSequence = (String) field.get(itemData);
                                        } catch (IllegalAccessException unused3) {
                                        }
                                    }
//                                    charSequence = 0;
                                }
                            }
                        } else if (view instanceof ViewGroup) {
                            canonicalName = AopUtil.getViewGroupTypeByReflect(view);
                            charSequence = view.getContentDescription();
                            if (TextUtils.isEmpty(charSequence)) {
                                String traverseView2 = AopUtil.traverseView(new StringBuilder(), (ViewGroup) view);
//                                ?? isEmpty2 = TextUtils.isEmpty(traverseView2);
                                charSequence = traverseView2;
//                                charSequence = isEmpty2;
//                                if (isEmpty2 == 0) {
//                                    String charSequence3 = traverseView2.toString();
//                                    charSequence = charSequence3.substring(0, traverseView2.length() - 1);
//                                    charSequence = charSequence3;
//                                }
                            }
                        }
                    }
                } catch (Exception unused4) {
                }
            } catch (Exception unused5) {
            }
        }
        if (TextUtils.isEmpty(charSequence) && (view instanceof TextView)) {
            charSequence = ((TextView) view).getHint();
        }
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = view.getContentDescription();
        }
        CharSequence charSequence4 = "";
        if (view instanceof EditText) {
            charSequence = charSequence4;
        }
        if (!TextUtils.isEmpty(charSequence)) {
            charSequence4 = charSequence;
        }
        return new ViewNode(charSequence4.toString(), canonicalName);
    }

    private static Object instanceOfTabView(View view) {
        Class<?> cls;
        Class<?> cls2;
        Field field = null;
        try {
            cls = Class.forName("com.google.android.material.tabs.TabLayout$TabView");
        } catch (Exception unused) {
            cls = null;
        }
        try {
            cls2 = Class.forName("com.google.android.material.tabs.TabLayout$TabView");
        } catch (Exception unused2) {
            cls2 = null;
        }
        if (cls != null) {
            cls2 = cls;
        }
        if (cls2 != null && cls2.isAssignableFrom(view.getClass())) {
            try {
                field = cls2.getDeclaredField("mTab");
            } catch (Exception unused3) {
            }
            if (field == null) {
                try {
                    field = cls2.getDeclaredField("tab");
                } catch (NoSuchFieldException unused5) {
                }
            }
            if (field != null) {
                field.setAccessible(true);
                try {
                    return field.get(view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:10|41|11|(3:48|14|(6:17|37|19|(5:23|24|45|25|(2:28|(2:30|(1:32))(1:33)))|34|35))|18|37|19|(0)|34|35) */
    /* JADX WARN: Can't wrap try/catch for region: R(9:10|(2:41|11)|(3:48|14|(6:17|37|19|(5:23|24|45|25|(2:28|(2:30|(1:32))(1:33)))|34|35))|18|37|19|(0)|34|35) */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0038, code lost:
        r0 = r0.getDeclaredField("customView");
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003f, code lost:
        r0 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String getTabLayoutContent(Object obj) throws NoSuchFieldException {
        Class<?> cls;
        Class<?> cls2;
        Method method;
        Object obj2;
        String obj3;
        Field field = null;
        View view;
        try {
            cls = Class.forName("com.google.android.material.tabs.TabLayout$Tab");
        } catch (Exception unused) {
            cls = null;
        }
        try {
            cls2 = Class.forName("com.google.android.material.tabs.TabLayout$Tab");
        } catch (Exception unused2) {
            cls2 = null;
        }
        if (cls != null) {
            cls2 = cls;
        }
        if (cls2 == null) {
            return null;
        }
        try {
            method = cls2.getMethod("getText", new Class[0]);
        } catch (NoSuchMethodException unused3) {
            method = null;
        }
        if (method != null) {
            try {
                obj2 = method.invoke(obj, new Object[0]);
            } catch (IllegalAccessException | InvocationTargetException unused4) {
                obj2 = null;
            }
            if (obj2 != null) {
                obj3 = obj2.toString();
                field = cls2.getDeclaredField("mCustomView");
                if (field != null) {
                    field.setAccessible(true);
                    try {
                        view = (View) field.get(obj);
                    } catch (IllegalAccessException unused5) {
                        view = null;
                    }
                    if (view != null) {
                        StringBuilder sb = new StringBuilder();
                        if (view instanceof ViewGroup) {
                            obj3 = AopUtil.traverseView(sb, (ViewGroup) view);
                            if (!TextUtils.isEmpty(obj3)) {
                                obj3 = obj3.substring(0, obj3.length() - 1);
                            }
                        } else {
                            obj3 = AopUtil.getViewText(view);
                        }
                    }
                }
                return obj3;
            }
        }
        obj3 = null;
        try {
            field = cls2.getDeclaredField("mCustomView");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if (field != null) {
        }
        return obj3;
    }
}
