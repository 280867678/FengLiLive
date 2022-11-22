package com.blmvl.blvl.util;

//package p067e.p103c.p104a.p113k;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.blmvl.blvl.AppContext;
import com.blmvl.blvl.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import p067e.p103c.p104a.Config;

/* renamed from: e.c.a.k.b0 */
/* loaded from: classes.dex */
public class SpUtil {

    /* renamed from: b */
    public static SpUtil f13005b;

    /* renamed from: a */
    public SharedPreferences f13006a = AppContext.m21299a().getSharedPreferences("sp_boluo", 0);

    /* renamed from: D */
    public static SpUtil m9573D() {
        if (f13005b == null) {
            synchronized (SpUtil.class) {
                if (f13005b == null) {
                    f13005b = new SpUtil();
                }
            }
        }
        return f13005b;
    }

    /* renamed from: A */
    public boolean m9576A() {
        return m9565a("key_need_login", false);
    }

    /* renamed from: B */
    public boolean m9575B() {
        return m9565a("key_is_rule_answered", false);
    }

    /* renamed from: C */
    public int m9574C() {
        return m9568a("key_is_show_game", 0);
    }

    /* renamed from: a */
    public String m9569a(String str) {
        return this.f13006a.getString(str, "");
    }

    /* renamed from: b */
    public void m9557b(String str, String str2) {
        SharedPreferences sharedPreferences = this.f13006a;
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(str, str2);
            edit.apply();
        }
    }

    /* renamed from: c */
    public void m9554c() {
        m9557b("key_submitted_watch_history", "");
    }

    /* renamed from: d */
    public void m9548d(int i) {
        m9559b("key_watch_count", i);
    }

    /* renamed from: e */
    public void m9544e(String str) {
        m9557b("device_id", str);
    }

    /* renamed from: f */
    public String m9542f() {
        return m9566a("key_base_url", "");
    }

    /* renamed from: g */
    public void m9538g(String str) {
        m9557b("key_open_screen_ad", str);
    }

    /* renamed from: h */
    public void m9536h(String str) {
        try {
            String o = m9525o();
            if (!TextUtils.isEmpty(o)) {
                ArrayList arrayList = new ArrayList(Arrays.asList(o.split(",")));
                arrayList.remove(str);
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(",");
                for (int i = 0; i < arrayList.size(); i++) {
                    String str2 = (String) arrayList.get(i);
                    if (i == arrayList.size() - 1) {
                        sb.append(str2);
                    } else {
                        sb.append(str2);
                        sb.append(",");
                    }
                }
                m9557b("search_history", sb.toString());
                return;
            }
            m9557b("search_history", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: i */
    public void m9534i(String str) {
        m9557b("key_spare_url", str);
    }

    /* renamed from: j */
    public long m9533j() {
        return m9567a("key_free_look_time", 0L);
    }

    /* renamed from: k */
    public boolean m9531k() {
        return m9565a("key_is_showed_like_prompt", false);
    }

    /* renamed from: l */
    public int m9529l() {
        return m9568a("key_left_watch_count", 0);
    }

    /* renamed from: m */
    public String m9527m() {
        return m9569a("key_local_play_history");
    }

    /* renamed from: n */
    public String m9526n() {
        return m9566a("key_open_screen_ad", "");
    }

    /* renamed from: o */
    public String m9525o() {
        return m9566a("search_history", "");
    }

    /* renamed from: p */
    public List<String> m9524p() {
        try {
            String o = m9525o();
            if (!TextUtils.isEmpty(o)) {
                return Arrays.asList(o.split(","));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: q */
    public String m9523q() {
        return m9566a("key_spare_url", Config.f12180a + "," + Config.f12181b + "," + Config.f12182c);
    }

    /* renamed from: r */
    public String m9522r() {
        return m9566a("key_user_info", "");
    }

    /* renamed from: s */
    public String m9521s() {
        return m9566a("key_video_list", "");
    }

    /* renamed from: t */
    public int m9520t() {
        return m9568a("key_watch_count", 0);
    }

    /* renamed from: u */
    public String m9519u() {
        return m9569a("key_watch_history");
    }

    /* renamed from: v */
    public List<String> m9518v() {
        ArrayList arrayList = new ArrayList();
        String u = m9519u();
        return !TextUtils.isEmpty(u) ? new ArrayList(Arrays.asList(u.split(","))) : arrayList;
    }

    /* renamed from: w */
    public long m9517w() {
        return m9567a("key_watch_time", 0L);
    }

    /* renamed from: x */
    public String m9516x() {
        return m9569a("key_watch_time_history");
    }

    /* renamed from: y */
    public boolean m9515y() {
        return m9565a("key_is_can_watch_live", false);
    }

    /* renamed from: z */
    public boolean m9514z() {
        return m9565a("key_is_login", false);
    }

    /* renamed from: a */
    public String m9566a(String str, String str2) {
        return this.f13006a.getString(str, str2);
    }

    /* renamed from: c */
    public void m9553c(int i) {
        m9559b("key_left_watch_count", i);
    }

    /* renamed from: d */
    public void m9549d() {
        m9557b("key_watch_history", "");
    }

    /* renamed from: e */
    public void m9545e() {
        m9557b("key_watch_time_history", "");
    }

    /* renamed from: f */
    public void m9540f(boolean z) {
        m9556b("key_need_login", z);
    }

    /* renamed from: g */
    public String m9539g() {
        return m9566a("key_config", "");
    }

    /* renamed from: i */
    public String m9535i() {
        return m9566a("device_id", "");
    }

    /* renamed from: j */
    public void m9532j(String str) {
        m9557b("key_user_info", str);
    }

    /* renamed from: k */
    public void m9530k(String str) {
        m9557b("key_video_collect_show_tag", str);
    }

    /* renamed from: l */
    public void m9528l(String str) {
        m9557b("key_watch_time_history", str);
    }

    /* renamed from: a */
    public boolean m9565a(String str, boolean z) {
        return this.f13006a.getBoolean(str, z);
    }

    /* renamed from: c */
    public void m9552c(String str) {
        m9557b("key_config", str);
    }

    /* renamed from: d */
    public void m9546d(boolean z) {
        m9556b("key_is_rule_answered", z);
    }

    /* renamed from: e */
    public void m9543e(boolean z) {
        m9556b("key_is_showed_like_prompt", z);
    }

    /* renamed from: f */
    public void m9541f(String str) {
        m9557b("key_local_play_history", str);
    }

    /* renamed from: a */
    public int m9568a(String str, int i) {
        return this.f13006a.getInt(str, i);
    }

    /* renamed from: c */
    public boolean m9551c(String str, boolean z) {
        int l;
        if (!z && m9529l() < 1) {
            return false;
        }
        List<String> v = m9518v();
        StringBuilder sb = new StringBuilder();
        if (v != null && !v.isEmpty()) {
            if (v.contains(str)) {
                return false;
            }
            int size = v.size();
            for (int i = 0; i < size; i++) {
                sb.append(v.get(i));
                sb.append(",");
            }
        }
        sb.append(str);
        m9557b("key_watch_history", sb.toString());
        if (!z && (l = m9529l() - 1) >= 0) {
            m9553c(l);
        }
        return true;
    }

    /* renamed from: d */
    public void m9547d(String str) {
        m9557b("key_cur_add_video_id", str);
    }

    /* renamed from: a */
    public long m9567a(String str, long j) {
        return this.f13006a.getLong(str, j);
    }

    /* renamed from: b */
    public void m9556b(String str, boolean z) {
        SharedPreferences sharedPreferences = this.f13006a;
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(str, z);
            edit.apply();
        }
    }

    /* renamed from: a */
    public void m9571a(int i) {
        m9559b("key_fee_count_status", i);
    }

    /* renamed from: a */
    public void m9570a(long j) {
        m9558b("key_free_look_time", j);
    }

    /* renamed from: a */
    public void m9572a() {
        m9573D().m9549d();
        m9573D().m9545e();
        m9573D().m9554c();
        m9573D().m9553c(0);
        m9573D().m9570a(0L);
        m9573D().m9532j("");
    }

    /* renamed from: b */
    public void m9559b(String str, int i) {
        SharedPreferences sharedPreferences = this.f13006a;
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(str, i);
            edit.apply();
        }
    }

    /* renamed from: b */
    public void m9558b(String str, long j) {
        SharedPreferences sharedPreferences = this.f13006a;
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putLong(str, j);
            edit.apply();
        }
    }

    /* renamed from: a */
    public void m9564a(boolean z) {
        m9556b("key_is_can_watch_live", z);
    }

    /* renamed from: c */
    public void m9550c(boolean z) {
        m9556b("key_is_real_user", z);
    }

    /* renamed from: b */
    public void m9560b(String str) {
        m9557b("key_base_url", str);
    }

    /* renamed from: h */
    public String m9537h() {
        return m9566a("key_cur_add_video_id", "");
    }

    /* renamed from: b */
    public void m9555b(boolean z) {
        m9556b("key_is_login", z);
    }

    /* renamed from: b */
    public void m9561b(long j) {
        m9558b("key_watch_time", j);
    }

    /* renamed from: b */
    public void m9563b() {
        m9557b("search_history", "");
    }

    /* renamed from: b */
    public void m9562b(int i) {
        m9559b("key_is_show_game", i);
    }
}

