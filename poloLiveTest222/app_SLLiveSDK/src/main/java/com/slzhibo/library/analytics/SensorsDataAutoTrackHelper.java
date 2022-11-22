package com.slzhibo.library.analytics;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;


//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.analytics.util.AopUtil;
import com.slzhibo.library.analytics.util.SensorsDataUtils;
import com.slzhibo.library.analytics.util.WindowHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class SensorsDataAutoTrackHelper {
    private static HashMap<Integer, Long> eventTimestamp = new HashMap<>();

    private static boolean isDeBounceTrack(Object obj) {
        long currentTimeMillis = System.currentTimeMillis();
        Long l = eventTimestamp.get(Integer.valueOf(obj.hashCode()));
        boolean z = l != null && currentTimeMillis - l.longValue() < 500;
        eventTimestamp.put(Integer.valueOf(obj.hashCode()), Long.valueOf(currentTimeMillis));
        return z;
    }

    private static void traverseView(String str, ViewGroup viewGroup) {
        try {
            if (!TextUtils.isEmpty(str) && viewGroup != null) {
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    childAt.setTag(0, str);
                    if ((childAt instanceof ViewGroup) && !(childAt instanceof ListView) && !(childAt instanceof GridView) && !(childAt instanceof Spinner) && !(childAt instanceof RadioGroup)) {
                        traverseView(str, (ViewGroup) childAt);
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    private static boolean isFragment(Object obj) {
        Class<?> cls;
        Class<?> cls2;
        if (obj == null) {
            return false;
        }
        Class<?> cls3 = null;
        try {
            cls = Class.forName("android.app.Fragment");
        } catch (Exception unused) {
            cls = null;
        }
        try {
            cls2 = Class.forName("androidx.fragment.app.Fragment");
        } catch (Exception unused2) {
            cls2 = null;
        }
        try {
            cls3 = Class.forName("androidx.fragment.app.Fragment");
        } catch (Exception unused3) {
        }
        if (cls2 == null && cls3 == null && cls == null) {
            return false;
        }
        if (cls2 != null) {
            try {
                if (cls2.isInstance(obj)) {
                    return true;
                }
            } catch (Exception unused4) {
            }
        }
        if (cls3 != null && cls3.isInstance(obj)) {
            return true;
        }
        if (cls != null) {
            if (cls.isInstance(obj)) {
                return true;
            }
        }
        return false;
    }

    public static void onFragmentViewCreated(Object obj, View view, Bundle bundle) {
        try {
            if (isFragment(obj)) {
                String name = obj.getClass().getName();
                view.setTag(0, name);
                if (view instanceof ViewGroup) {
                    traverseView(name, (ViewGroup) view);
                }
            }
        } catch (Exception unused) {
        }
    }

    public static void trackRN(Object obj, int i, int i2, boolean z) {
        Method method;
        Object invoke;
        try {
            if (SensorsDataAPI.sharedInstance().isReactNativeAutoTrackEnabled() && SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(AopConstants.ELEMENT_TYPE, "RNView");
                if (!(obj == null || (method = Class.forName("com.facebook.react.uimanager.NativeViewHierarchyManager").getMethod("resolveView", Integer.TYPE)) == null || (invoke = method.invoke(obj, Integer.valueOf(i))) == null)) {
                    View view = (View) invoke;
                    Activity activityFromContext = AopUtil.getActivityFromContext(view.getContext(), view);
                    if (activityFromContext != null) {
                        SensorsDataUtils.mergeJSONObject(AopUtil.buildTitleAndScreenName(activityFromContext), jSONObject);
                        AopUtil.addViewPathProperties(activityFromContext, view, jSONObject);
                    }
                    if (!(view instanceof CompoundButton)) {
                        if (view instanceof TextView) {
                            TextView textView = (TextView) view;
                            if (!(view instanceof EditText) && !TextUtils.isEmpty(textView.getText())) {
                                jSONObject.put(AopConstants.ELEMENT_CONTENT, textView.getText().toString());
                            }
                        } else if (view instanceof ViewGroup) {
                            String traverseView = AopUtil.traverseView(new StringBuilder(), (ViewGroup) view);
                            if (!TextUtils.isEmpty(traverseView)) {
                                traverseView = traverseView.substring(0, traverseView.length() - 1);
                            }
                            jSONObject.put(AopConstants.ELEMENT_CONTENT, traverseView);
                        }
                    } else {
                        return;
                    }
                }
                SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
            }
        } catch (Exception unused) {
        }
    }

    private static void trackFragmentAppViewScreen(Object obj) {
        JSONObject trackProperties;
        try {
            if (!SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN) && SensorsDataAPI.sharedInstance().isTrackFragmentAppViewScreenEnabled() && !"com.bumptech.glide.manager.SupportRequestManagerFragment".equals(obj.getClass().getCanonicalName()) && SensorsDataAPI.sharedInstance().isFragmentAutoTrackAppViewScreen(obj.getClass())) {
                JSONObject jSONObject = new JSONObject();
                AopUtil.getScreenNameAndTitleFromFragment(jSONObject, obj, null);
                if ((obj instanceof ScreenAutoTracker) && (trackProperties = ((ScreenAutoTracker) obj).getTrackProperties()) != null) {
                    SensorsDataUtils.mergeJSONObject(trackProperties, jSONObject);
                }
                SensorsDataAPI.sharedInstance().trackViewScreen(SensorsDataUtils.getScreenUrl(obj), jSONObject);
            }
        } catch (Exception unused) {
        }
    }

    public static void trackFragmentResume(Object obj) {
        if (!SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN) && SensorsDataAPI.sharedInstance().isTrackFragmentAppViewScreenEnabled() && isFragment(obj)) {
            try {
                Method method = obj.getClass().getMethod("getParentFragment", new Class[0]);
                if (method != null) {
                    Object invoke = method.invoke(obj, new Object[0]);
                    if (invoke == null) {
                        if (!fragmentIsHidden(obj) && fragmentGetUserVisibleHint(obj)) {
                            trackFragmentAppViewScreen(obj);
                        }
                    } else if (!fragmentIsHidden(obj) && fragmentGetUserVisibleHint(obj) && !fragmentIsHidden(invoke) && fragmentGetUserVisibleHint(invoke)) {
                        trackFragmentAppViewScreen(obj);
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    private static boolean fragmentGetUserVisibleHint(Object obj) {
        try {
            Method method = obj.getClass().getMethod("getUserVisibleHint", new Class[0]);
            if (method != null) {
                return ((Boolean) method.invoke(obj, new Object[0])).booleanValue();
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private static boolean fragmentIsHidden(Object obj) {
        try {
            Method method = obj.getClass().getMethod("isHidden", new Class[0]);
            if (method != null) {
                return ((Boolean) method.invoke(obj, new Object[0])).booleanValue();
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static void trackFragmentSetUserVisibleHint(Object obj, boolean z) {
        if (!SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN) && SensorsDataAPI.sharedInstance().isTrackFragmentAppViewScreenEnabled() && isFragment(obj)) {
            Object obj2 = null;
            try {
                Method method = obj.getClass().getMethod("getParentFragment", new Class[0]);
                if (method != null) {
                    obj2 = method.invoke(obj, new Object[0]);
                }
            } catch (Exception unused) {
            }
            if (obj2 == null) {
                if (z && fragmentIsResumed(obj) && !fragmentIsHidden(obj)) {
                    trackFragmentAppViewScreen(obj);
                }
            } else if (z && fragmentGetUserVisibleHint(obj2) && fragmentIsResumed(obj) && fragmentIsResumed(obj2) && !fragmentIsHidden(obj) && !fragmentIsHidden(obj2)) {
                trackFragmentAppViewScreen(obj);
            }
        }
    }

    private static boolean fragmentIsResumed(Object obj) {
        try {
            Method method = obj.getClass().getMethod("isResumed", new Class[0]);
            if (method != null) {
                return ((Boolean) method.invoke(obj, new Object[0])).booleanValue();
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static void trackOnHiddenChanged(Object obj, boolean z) {
        if (!SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN) && SensorsDataAPI.sharedInstance().isTrackFragmentAppViewScreenEnabled() && isFragment(obj)) {
            Object obj2 = null;
            try {
                Method method = obj.getClass().getMethod("getParentFragment", new Class[0]);
                if (method != null) {
                    obj2 = method.invoke(obj, new Object[0]);
                }
            } catch (Exception unused) {
            }
            if (obj2 == null) {
                if (!z && fragmentIsResumed(obj) && fragmentGetUserVisibleHint(obj)) {
                    trackFragmentAppViewScreen(obj);
                }
            } else if (!z && !fragmentIsHidden(obj2) && fragmentIsResumed(obj) && fragmentIsResumed(obj2) && fragmentGetUserVisibleHint(obj) && fragmentGetUserVisibleHint(obj2)) {
                trackFragmentAppViewScreen(obj);
            }
        }
    }

    public static void trackExpandableListViewOnGroupClick(ExpandableListView expandableListView, View view, int i) {
        Context context;
        if (!(expandableListView == null || view == null)) {
            try {
                if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK) && (context = expandableListView.getContext()) != null) {
                    String str = null;
                    Activity activity = context instanceof Activity ? (Activity) context : null;
                    if (activity == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activity.getClass())) {
                        Object fragmentFromView = AopUtil.getFragmentFromView(expandableListView);
                        if ((fragmentFromView == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(fragmentFromView.getClass())) && !AopUtil.isViewIgnored(ExpandableListView.class) && !AopUtil.isViewIgnored(expandableListView)) {
                            JSONObject jSONObject = new JSONObject();
                            AopUtil.addViewPathProperties(activity, view, jSONObject);
                            if (activity != null) {
                                SensorsDataUtils.mergeJSONObject(AopUtil.buildTitleAndScreenName(activity), jSONObject);
                            }
                            String viewId = AopUtil.getViewId(expandableListView);
                            if (!TextUtils.isEmpty(viewId)) {
                                jSONObject.put(AopConstants.ELEMENT_ID, viewId);
                            }
                            jSONObject.put(AopConstants.ELEMENT_TYPE, "ExpandableListView");
                            if (view instanceof ViewGroup) {
                                try {
                                    str = AopUtil.traverseView(new StringBuilder(), (ViewGroup) view);
                                    if (!TextUtils.isEmpty(str)) {
                                        str = str.substring(0, str.length() - 1);
                                    }
                                } catch (Exception unused) {
                                }
                            } else {
                                str = AopUtil.getViewText(view);
                            }
                            if (!TextUtils.isEmpty(str)) {
                                jSONObject.put(AopConstants.ELEMENT_CONTENT, str);
                            }
                            if (fragmentFromView != null) {
                                AopUtil.getScreenNameAndTitleFromFragment(jSONObject, fragmentFromView, activity);
                            }
                            JSONObject jSONObject2 = (JSONObject) view.getTag(0);
                            if (jSONObject2 != null) {
                                AopUtil.mergeJSONObject(jSONObject2, jSONObject);
                            }
                            ExpandableListAdapter expandableListAdapter = expandableListView.getExpandableListAdapter();
                            if (expandableListAdapter != null && (expandableListAdapter instanceof SensorsExpandableListViewItemTrackProperties)) {
                                try {
                                    JSONObject sensorsGroupItemTrackProperties = ((SensorsExpandableListViewItemTrackProperties) expandableListAdapter).getSensorsGroupItemTrackProperties(i);
                                    if (sensorsGroupItemTrackProperties != null) {
                                        AopUtil.mergeJSONObject(sensorsGroupItemTrackProperties, jSONObject);
                                    }
                                } catch (JSONException unused2) {
                                }
                            }
                            SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
                        }
                    }
                }
            } catch (Exception unused3) {
            }
        }
    }

    public static void trackExpandableListViewOnChildClick(ExpandableListView expandableListView, View view, int i, int i2) {
        Context context;
        JSONObject sensorsChildItemTrackProperties;
        if (!(expandableListView == null || view == null)) {
            try {
                if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK) && (context = expandableListView.getContext()) != null) {
                    Activity activityFromContext = AopUtil.getActivityFromContext(context, expandableListView);
                    if (activityFromContext == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activityFromContext.getClass())) {
                        Object fragmentFromView = AopUtil.getFragmentFromView(expandableListView);
                        if ((fragmentFromView == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(fragmentFromView.getClass())) && !AopUtil.isViewIgnored(ExpandableListView.class) && !AopUtil.isViewIgnored(expandableListView) && !AopUtil.isViewIgnored(view)) {
                            JSONObject jSONObject = (JSONObject) view.getTag(0);
                            if (jSONObject == null) {
                                jSONObject = new JSONObject();
                            }
                            ExpandableListAdapter expandableListAdapter = expandableListView.getExpandableListAdapter();
                            if (!(expandableListAdapter == null || !(expandableListAdapter instanceof SensorsExpandableListViewItemTrackProperties) || (sensorsChildItemTrackProperties = ((SensorsExpandableListViewItemTrackProperties) expandableListAdapter).getSensorsChildItemTrackProperties(i, i2)) == null)) {
                                AopUtil.mergeJSONObject(sensorsChildItemTrackProperties, jSONObject);
                            }
                            AopUtil.addViewPathProperties(activityFromContext, view, jSONObject);
                            if (activityFromContext != null) {
                                SensorsDataUtils.mergeJSONObject(AopUtil.buildTitleAndScreenName(activityFromContext), jSONObject);
                            }
                            String viewId = AopUtil.getViewId(expandableListView);
                            if (!TextUtils.isEmpty(viewId)) {
                                jSONObject.put(AopConstants.ELEMENT_ID, viewId);
                            }
                            jSONObject.put(AopConstants.ELEMENT_TYPE, "ExpandableListView");
                            String str = null;
                            if (view instanceof ViewGroup) {
                                try {
                                    str = AopUtil.traverseView(new StringBuilder(), (ViewGroup) view);
                                    if (!TextUtils.isEmpty(str)) {
                                        str = str.substring(0, str.length() - 1);
                                    }
                                } catch (Exception unused) {
                                }
                            } else {
                                str = AopUtil.getViewText(view);
                            }
                            if (!TextUtils.isEmpty(str)) {
                                jSONObject.put(AopConstants.ELEMENT_CONTENT, str);
                            }
                            if (fragmentFromView != null) {
                                AopUtil.getScreenNameAndTitleFromFragment(jSONObject, fragmentFromView, activityFromContext);
                            }
                            JSONObject jSONObject2 = (JSONObject) view.getTag(0);
                            if (jSONObject2 != null) {
                                AopUtil.mergeJSONObject(jSONObject2, jSONObject);
                            }
                            SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
                        }
                    }
                }
            } catch (Exception unused2) {
            }
        }
    }

    public static void trackTabHost(String str) {
        try {
            if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK) && !AopUtil.isViewIgnored(TabHost.class)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(AopConstants.ELEMENT_CONTENT, str);
                jSONObject.put(AopConstants.ELEMENT_TYPE, "TabHost");
                SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
            }
        } catch (Exception unused) {
        }
    }

    public static void trackTabLayoutSelected(Object obj, Object obj2) {
        Class<?> cls;
        Class<?> cls2;
        Activity activity;
        Field[] declaredFields;
        boolean z;
        Class<?> cls3;
        Method method;
        Field field;
        View view;
        Field field2;
        String str;
        Object invoke;
        if (obj2 != null) {
            try {
                if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK)) {
                    View view2 = null;
                    try {
                        cls = Class.forName("com.google.android.material.tabs.TabLayout");
                    } catch (Exception unused) {
                        cls = null;
                    }
                    try {
                        cls2 = Class.forName("com.google.android.material.tabs.TabLayout");
                    } catch (Exception unused2) {
                        cls2 = null;
                    }
                    if (!(cls == null && cls2 == null)) {
                        if (cls != null && AopUtil.isViewIgnored(cls)) {
                            return;
                        }
                        if ((cls2 == null || !AopUtil.isViewIgnored(cls2)) && !isDeBounceTrack(obj2)) {
                            if (obj instanceof Context) {
                                activity = AopUtil.getActivityFromContext((Context) obj, null);
                            } else {
                                try {
                                    Activity activity2 = null;
                                    for (Field field3 : obj.getClass().getDeclaredFields()) {
                                        try {
                                            field3.setAccessible(true);
                                            Object obj3 = field3.get(obj);
                                            if (obj3 instanceof Activity) {
                                                activity = (Activity) obj3;
                                                break;
                                            } else if (isFragment(obj3)) {
                                                activity = activity2;
                                                obj = obj3;
                                                z = true;
                                                break;
                                            } else {
                                                if (obj3 instanceof View) {
                                                    activity2 = AopUtil.getActivityFromContext(((View) obj3).getContext(), null);
                                                }
                                            }
                                        } catch (Exception unused3) {
                                        }
                                    }
                                    activity = activity2;
                                } catch (Exception unused4) {
                                    activity = null;
                                }
                            }
                            z = false;
                            if (activity != null && SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activity.getClass())) {
                                return;
                            }
                            if (!z || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(obj.getClass())) {
                                JSONObject jSONObject = new JSONObject();
                                if (z) {
                                    activity = AopUtil.getActivityFromFragment(obj);
                                    AopUtil.getScreenNameAndTitleFromFragment(jSONObject, obj, activity);
                                } else if (activity != null) {
                                    SensorsDataUtils.mergeJSONObject(AopUtil.buildTitleAndScreenName(activity), jSONObject);
                                }
                                try {
                                    cls3 = Class.forName("com.google.android.material.tabs.TabLayout$Tab");
                                } catch (Exception unused5) {
                                    cls3 = null;
                                }
                                try {
                                    cls3 = Class.forName("com.google.android.material.tabs.TabLayout$Tab");
                                } catch (Exception unused6) {
                                    cls3 = null;
                                }
                                if (cls3 == null) {
                                }
                                if (cls3 != null) {
                                    try {
                                        method = cls3.getMethod("getText", new Class[0]);
                                    } catch (NoSuchMethodException unused7) {
                                        method = null;
                                    }
                                    if (!(method == null || (invoke = method.invoke(obj2, new Object[0])) == null)) {
                                        jSONObject.put(AopConstants.ELEMENT_CONTENT, invoke);
                                    }
                                    try {
                                        if (activity != null) {
                                            try {
                                                field = cls3.getDeclaredField("mCustomView");
                                            } catch (NoSuchFieldException unused8) {
                                                field = null;
                                            }
                                            if (field != null) {
                                                field.setAccessible(true);
                                                view = (View) field.get(obj2);
                                                if (view != null) {
                                                    try {
                                                        StringBuilder sb = new StringBuilder();
                                                        if (view instanceof ViewGroup) {
                                                            str = AopUtil.traverseView(sb, (ViewGroup) view);
                                                            if (!TextUtils.isEmpty(str)) {
                                                                str = str.substring(0, str.length() - 1);
                                                            }
                                                        } else {
                                                            str = AopUtil.getViewText(view);
                                                        }
                                                        if (!TextUtils.isEmpty(str)) {
                                                            jSONObject.put(AopConstants.ELEMENT_CONTENT, str);
                                                        }
                                                    } catch (Exception unused10) {
                                                    }
                                                }
                                            } else {
                                                view = null;
                                            }
                                            try {
                                                Field declaredField = cls3.getDeclaredField("view");
                                                declaredField.setAccessible(true);
                                                view2 = (View) declaredField.get(obj2);
                                            } catch (IllegalAccessException | NoSuchFieldException unused11) {
                                            }
                                            if (view2 == null) {
                                                try {
                                                    Field declaredField2 = cls3.getDeclaredField("mView");
                                                    declaredField2.setAccessible(true);
                                                    view2 = (View) declaredField2.get(obj2);
                                                } catch (IllegalAccessException | NoSuchFieldException unused12) {
                                                }
                                            }
                                            if (view2 != null) {
                                                AopUtil.addViewPathProperties(activity, view2, jSONObject);
                                            }
                                            if (view == null || view.getId() == -1) {
                                                try {
                                                    field2 = cls3.getDeclaredField("mParent");
                                                } catch (NoSuchFieldException unused13) {
                                                    field2 = cls3.getDeclaredField("parent");
                                                }
                                                field2.setAccessible(true);
                                                view = (View) field2.get(obj2);
                                            }
                                            if (!(view == null || view.getId() == -1)) {
                                                String resourceEntryName = activity.getResources().getResourceEntryName(view.getId());
                                                if (!TextUtils.isEmpty(resourceEntryName)) {
                                                    jSONObject.put(AopConstants.ELEMENT_ID, resourceEntryName);
                                                }
                                            }
                                        }
                                    } catch (Exception unused14) {
                                    }
                                }
                                jSONObject.put(AopConstants.ELEMENT_TYPE, "TabLayout");
                                SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
                            }
                        }
                    }
                }
            } catch (Exception unused15) {
            }
        }
    }

    public static void trackMenuItem(MenuItem menuItem) {
        trackMenuItem(null, menuItem);
    }

    public static void trackMenuItem(Object obj, MenuItem menuItem) {
        View clickView;
        if (menuItem != null) {
            try {
                if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK) && !AopUtil.isViewIgnored(MenuItem.class) && !isDeBounceTrack(menuItem)) {
                    String str = null;
                    Context context = (obj == null || !(obj instanceof Context)) ? null : (Context) obj;
                    Activity activityFromContext = context != null ? AopUtil.getActivityFromContext(context, null) : null;
                    if (activityFromContext == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activityFromContext.getClass())) {
                        if (context != null) {
                            try {
                                str = context.getResources().getResourceEntryName(menuItem.getItemId());
                            } catch (Exception unused) {
                            }
                        }
                        JSONObject jSONObject = new JSONObject();
                        if (activityFromContext != null) {
                            SensorsDataUtils.mergeJSONObject(AopUtil.buildTitleAndScreenName(activityFromContext), jSONObject);
                        }
                        if (!TextUtils.isEmpty(str)) {
                            jSONObject.put(AopConstants.ELEMENT_ID, str);
                        }
                        if (!TextUtils.isEmpty(menuItem.getTitle())) {
                            jSONObject.put(AopConstants.ELEMENT_CONTENT, menuItem.getTitle());
                        }
                        if (Build.VERSION.SDK_INT >= 14 && (clickView = WindowHelper.getClickView(menuItem)) != null) {
                            AopUtil.addViewPathProperties(activityFromContext, clickView, jSONObject);
                        }
                        jSONObject.put(AopConstants.ELEMENT_TYPE, "MenuItem");
                        SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
                    }
                }
            } catch (Exception unused2) {
            }
        }
    }

    public static void trackRadioGroup(RadioGroup radioGroup, int i) {
        Context context;
        if (radioGroup != null) {
            try {
                if (radioGroup.findViewById(i).isPressed() && SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK) && (context = radioGroup.getContext()) != null) {
                    Activity activityFromContext = AopUtil.getActivityFromContext(context, radioGroup);
                    if (activityFromContext == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activityFromContext.getClass())) {
                        Object fragmentFromView = AopUtil.getFragmentFromView(radioGroup);
                        if ((fragmentFromView == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(fragmentFromView.getClass())) && !AopUtil.isViewIgnored(radioGroup)) {
                            JSONObject jSONObject = new JSONObject();
                            String viewId = AopUtil.getViewId(radioGroup);
                            if (!TextUtils.isEmpty(viewId)) {
                                jSONObject.put(AopConstants.ELEMENT_ID, viewId);
                            }
                            if (activityFromContext != null) {
                                SensorsDataUtils.mergeJSONObject(AopUtil.buildTitleAndScreenName(activityFromContext), jSONObject);
                            }
                            View findViewById = radioGroup.findViewById(i);
                            String str = "RadioButton";
                            if (findViewById != null) {
                                str = AopUtil.getViewType(findViewById.getClass().getCanonicalName(), str);
                            }
                            jSONObject.put(AopConstants.ELEMENT_TYPE, str);
                            int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                            if (activityFromContext != null) {
                                try {
                                    RadioButton radioButton = (RadioButton) activityFromContext.findViewById(checkedRadioButtonId);
                                    if (radioButton != null) {
                                        if (!TextUtils.isEmpty(radioButton.getText())) {
                                            String charSequence = radioButton.getText().toString();
                                            if (!TextUtils.isEmpty(charSequence)) {
                                                jSONObject.put(AopConstants.ELEMENT_CONTENT, charSequence);
                                            }
                                        }
                                        AopUtil.addViewPathProperties(activityFromContext, radioButton, jSONObject);
                                    }
                                } catch (Exception unused) {
                                }
                            }
                            if (fragmentFromView != null) {
                                AopUtil.getScreenNameAndTitleFromFragment(jSONObject, fragmentFromView, activityFromContext);
                            }
                            JSONObject jSONObject2 = (JSONObject) radioGroup.getTag(0);
                            if (jSONObject2 != null) {
                                AopUtil.mergeJSONObject(jSONObject2, jSONObject);
                            }
                            SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
                        }
                    }
                }
            } catch (Exception unused2) {
            }
        }
    }

    public static void trackDialog(DialogInterface dialogInterface, int i) {
        Class<?> cls;
        Class<?> cls2;
        ListView listView;
        try {
            if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK)) {
                Button button = null;
                Dialog dialog = dialogInterface instanceof Dialog ? (Dialog) dialogInterface : null;
                if (dialog != null && !isDeBounceTrack(dialog)) {
                    Activity activityFromContext = AopUtil.getActivityFromContext(dialog.getContext(), null);
                    if (activityFromContext == null) {
                        activityFromContext = dialog.getOwnerActivity();
                    }
                    if ((activityFromContext == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activityFromContext.getClass())) && !AopUtil.isViewIgnored(Dialog.class)) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            if (dialog.getWindow() != null) {
                                String str = (String) dialog.getWindow().getDecorView().getTag(0);
                                if (!TextUtils.isEmpty(str)) {
                                    jSONObject.put(AopConstants.ELEMENT_ID, str);
                                }
                            }
                        } catch (Exception unused) {
                        }
                        if (activityFromContext != null) {
                            SensorsDataUtils.mergeJSONObject(AopUtil.buildTitleAndScreenName(activityFromContext), jSONObject);
                        }
                        jSONObject.put(AopConstants.ELEMENT_TYPE, "Dialog");
                        try {
                            cls = Class.forName("androidx.appcompat.app.AlertDialog");
                        } catch (Exception unused2) {
                            cls = null;
                        }
                        try {
                            cls2 = Class.forName("androidx.appcompat.app.AlertDialog");
                        } catch (Exception unused3) {
                            cls2 = null;
                        }
                        if (cls != null || cls2 != null) {
                            if (cls != null) {
                                cls2 = cls;
                            }
                            if (dialog instanceof AlertDialog) {
                                AlertDialog alertDialog = (AlertDialog) dialog;
                                Button button2 = alertDialog.getButton(i);
                                if (button2 != null) {
                                    if (!TextUtils.isEmpty(button2.getText())) {
                                        jSONObject.put(AopConstants.ELEMENT_CONTENT, button2.getText());
                                    }
                                    AopUtil.addViewPathProperties(activityFromContext, button2, jSONObject);
                                } else {
                                    ListView listView2 = alertDialog.getListView();
                                    if (listView2 != null) {
                                        Object item = listView2.getAdapter().getItem(i);
                                        if (item != null && (item instanceof String)) {
                                            jSONObject.put(AopConstants.ELEMENT_CONTENT, item);
                                        }
                                        View childAt = listView2.getChildAt(i);
                                        if (childAt != null) {
                                            AopUtil.addViewPathProperties(activityFromContext, childAt, jSONObject);
                                        }
                                    }
                                }
                            } else if (cls2.isInstance(dialog)) {
                                try {
                                    Method method = dialog.getClass().getMethod("getButton", Integer.TYPE);
                                    if (method != null) {
                                        button = (Button) method.invoke(dialog, Integer.valueOf(i));
                                    }
                                } catch (Exception unused4) {
                                }
                                if (button != null) {
                                    if (!TextUtils.isEmpty(button.getText())) {
                                        jSONObject.put(AopConstants.ELEMENT_CONTENT, button.getText());
                                    }
                                    AopUtil.addViewPathProperties(activityFromContext, button, jSONObject);
                                } else {
                                    try {
                                        Method method2 = dialog.getClass().getMethod("getListView", new Class[0]);
                                        if (!(method2 == null || (listView = (ListView) method2.invoke(dialog, new Object[0])) == null)) {
                                            Object item2 = listView.getAdapter().getItem(i);
                                            if (item2 != null && (item2 instanceof String)) {
                                                jSONObject.put(AopConstants.ELEMENT_CONTENT, item2);
                                            }
                                            View childAt2 = listView.getChildAt(i);
                                            if (childAt2 != null) {
                                                AopUtil.addViewPathProperties(activityFromContext, childAt2, jSONObject);
                                            }
                                        }
                                    } catch (Exception unused5) {
                                    }
                                }
                            }
                            SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
                        }
                    }
                }
            }
        } catch (Exception unused6) {
        }
    }

    public static void trackListView(AdapterView<?> adapterView, View view, int i) {
        Context context;
        if (view != null) {
            try {
                if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK) && (context = view.getContext()) != null) {
                    Activity activityFromContext = AopUtil.getActivityFromContext(context, view);
                    if (activityFromContext == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activityFromContext.getClass())) {
                        Object fragmentFromView = AopUtil.getFragmentFromView(adapterView);
                        if ((fragmentFromView == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(fragmentFromView.getClass())) && !AopUtil.isViewIgnored(adapterView)) {
                            JSONObject jSONObject = new JSONObject();
                            if (adapterView instanceof ListView) {
                                jSONObject.put(AopConstants.ELEMENT_TYPE, "ListView");
                                if (AopUtil.isViewIgnored(ListView.class)) {
                                    return;
                                }
                            } else if (adapterView instanceof GridView) {
                                jSONObject.put(AopConstants.ELEMENT_TYPE, "GridView");
                                if (AopUtil.isViewIgnored(GridView.class)) {
                                    return;
                                }
                            } else if (adapterView instanceof Spinner) {
                                jSONObject.put(AopConstants.ELEMENT_TYPE, "Spinner");
                                if (AopUtil.isViewIgnored(Spinner.class)) {
                                    return;
                                }
                            }
                            String viewId = AopUtil.getViewId(adapterView);
                            if (!TextUtils.isEmpty(viewId)) {
                                jSONObject.put(AopConstants.ELEMENT_ID, viewId);
                            }
                            Object adapter = adapterView.getAdapter();
                            if (adapter instanceof HeaderViewListAdapter) {
                                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
                            }
                            if (adapter instanceof SensorsAdapterViewItemTrackProperties) {
                                try {
                                    JSONObject sensorsItemTrackProperties = ((SensorsAdapterViewItemTrackProperties) adapter).getSensorsItemTrackProperties(i);
                                    if (sensorsItemTrackProperties != null) {
                                        AopUtil.mergeJSONObject(sensorsItemTrackProperties, jSONObject);
                                    }
                                } catch (JSONException unused) {
                                }
                            }
                            AopUtil.addViewPathProperties(activityFromContext, view, jSONObject);
                            if (activityFromContext != null) {
                                SensorsDataUtils.mergeJSONObject(AopUtil.buildTitleAndScreenName(activityFromContext), jSONObject);
                            }
                            String str = null;
                            if (view instanceof ViewGroup) {
                                try {
                                    str = AopUtil.traverseView(new StringBuilder(), (ViewGroup) view);
                                    if (!TextUtils.isEmpty(str)) {
                                        str = str.substring(0, str.length() - 1);
                                    }
                                } catch (Exception unused2) {
                                }
                            } else {
                                str = AopUtil.getViewText(view);
                            }
                            if (!TextUtils.isEmpty(str)) {
                                jSONObject.put(AopConstants.ELEMENT_CONTENT, str);
                            }
                            if (fragmentFromView != null) {
                                AopUtil.getScreenNameAndTitleFromFragment(jSONObject, fragmentFromView, activityFromContext);
                            }
                            JSONObject jSONObject2 = (JSONObject) view.getTag(0);
                            if (jSONObject2 != null) {
                                AopUtil.mergeJSONObject(jSONObject2, jSONObject);
                            }
                            SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
                        }
                    }
                }
            } catch (Exception unused3) {
            }
        }
    }

    public static void trackDrawerOpened(View view) {
        if (view != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(AopConstants.ELEMENT_CONTENT, "Open");
                SensorsDataAPI.sharedInstance().setViewProperties(view, jSONObject);
                trackViewOnClick(view);
            } catch (Exception unused) {
            }
        }
    }

    public static void trackDrawerClosed(View view) {
        if (view != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(AopConstants.ELEMENT_CONTENT, "Close");
                SensorsDataAPI.sharedInstance().setViewProperties(view, jSONObject);
                trackViewOnClick(view);
            } catch (Exception unused) {
            }
        }
    }

    public static void trackViewOnClick(View view) {
        if (view != null) {
            trackViewOnClick(view, view.isPressed());
        }
    }

    public static void trackViewOnClick(View view, boolean z) {
        if (view != null) {
            try {
                if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK)) {
                    Activity activityFromContext = AopUtil.getActivityFromContext(view.getContext(), view);
                    if (activityFromContext == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activityFromContext.getClass())) {
                        Object fragmentFromView = AopUtil.getFragmentFromView(view);
                        if ((fragmentFromView == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(fragmentFromView.getClass())) && !AopUtil.isViewIgnored(view) && !SensorsDataUtils.isDoubleClick(view)) {
                            JSONObject jSONObject = new JSONObject();
                            if (AopUtil.injectClickInfo(view, jSONObject, z)) {
                                SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
                            }
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void track(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = null;
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        jSONObject = new JSONObject(str2);
                    } catch (Exception unused) {
                    }
                }
                SensorsDataAPI.sharedInstance().track(str, jSONObject);
            }
        } catch (Exception unused2) {
        }
    }
}
