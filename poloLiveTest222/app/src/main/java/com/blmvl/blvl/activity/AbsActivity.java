package com.blmvl.blvl.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import com.blmvl.blvl.AppContext;


import com.blmvl.blvl.R;
import com.gyf.immersionbar.ImmersionBar;

public abstract class AbsActivity extends AppCompatActivity {
    /* renamed from: B */
    public void mo20620B() {
//        ImmersionBar b = ImmersionBar.m5078b(this);
//        b.m5105D();
//        b.m5082a(true, R.color.white);
//        b.m5075b(true, 0.2f);
//        b.m5072c(R.color.white);
//        b.m5045w();

        ImmersionBar mBarParams = ImmersionBar.with(this);
        mBarParams.reset();
        mBarParams.fitsSystemWindows(true, R.color.white);
        mBarParams.navigationBarDarkIcon(true, 0.2f);      ////////
        mBarParams.navigationBarColor(R.color.white);      ///////
        mBarParams.init();
    }

    /* renamed from: C */
    public void m21294C() {
//        try {
//            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_title);
//            if (linearLayout != null) {
//                linearLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
//                linearLayout.setFitsSystemWindows(true);
//                View findViewById = findViewById(R.id.line);
//                if (findViewById != null) {
//                    findViewById.setVisibility(View.GONE);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /* renamed from: a */
    public void m21293a(int i, boolean z, boolean z2) {
//        try {
//            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_title);
//            if (linearLayout != null) {
//                linearLayout.setBackgroundColor(getResources().getColor(i));
//                linearLayout.setFitsSystemWindows(z);
//                View findViewById = findViewById(R.id.line);
//                if (findViewById != null) {
//                    if (z2) {
//                        findViewById.setVisibility(View.VISIBLE);
//                    } else {
//                        findViewById.setVisibility(View.GONE);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /* renamed from: a */
    public abstract void mo20592a(Bundle bundle);

    public void backClick(View view) {
//        try {
//            if (view.getId() == R.id.img_back) {
//                onBackPressed();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /* renamed from: c */
    public void m21292c(String str) {
//        try {
//            TextView textView = (TextView) findViewById(R.id.tv_sub_title);
//            if (textView != null) {
//                if (!TextUtils.isEmpty(str)) {
//                    textView.setVisibility(View.VISIBLE);
//                    textView.setText(str);
//                } else {
//                    textView.setVisibility(View.GONE);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /* renamed from: d */
    public void m21291d(String str) {
        try {
            TextView textView = findViewById(R.id.tv_title);
            if (textView != null) {
                textView.setText(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: i */
    public void m21290i(int i) {
//        try {
//            ImageView imageView = (ImageView) findViewById(R.id.img_back);
//            if (imageView != null) {
//                imageView.setImageResource(i);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /* renamed from: j */
    public void m21289j(int i) {
//        try {
//            TextView textView = (TextView) findViewById(R.id.tv_sub_title);
//            if (textView != null) {
//                textView.setTextColor(getResources().getColor(i));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /* renamed from: k */
    public void m21288k(int i) {
        try {
            TextView textView = findViewById(R.id.tv_title);
            if (textView != null) {
                textView.setTextColor(getResources().getColor(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        try {
            mo20620B();
            AppContext.m21299a().mo20166a(this);
            setContentView(mo20588z());
            mo20592a(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            AppContext.m21299a().mo20165b(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void subTitleClick(View view) {
    }

    /* renamed from: z */
    public abstract int mo20588z();
}
