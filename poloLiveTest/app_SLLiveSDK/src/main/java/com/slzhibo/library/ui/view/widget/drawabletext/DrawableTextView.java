package com.slzhibo.library.ui.view.widget.drawabletext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.LinearGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;

import com.slzhibo.library.R;

import java.util.HashMap;

public class DrawableTextView extends AppCompatTextView {
    boolean inflated;
    private final HashMap<String, ViewState> mShaderHashMap = new HashMap<>();
    private final Runnable refreshRunnable = new Runnable() {
        /* class com.slzhibo.library.ui.view.widget.drawabletext.DrawableTextView.AnonymousClass1 */

        public void run() {
            if (DrawableTextView.this.textColorDrawable == null) {
                return;
            }
            if ((DrawableTextView.this.textColorDrawable instanceof GradientDrawable) || (DrawableTextView.this.textColorDrawable instanceof StateListDrawable)) {
                DrawableTextView.this.resetColorState();
            }
        }
    };
    private Drawable textColorDrawable;

    public DrawableTextView(Context context) {
        super(context);
        new RectF();
        setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    }

    public DrawableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        new RectF();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DrawableTextView);
            this.textColorDrawable = obtainStyledAttributes.getDrawable(R.styleable.DrawableTextView_textDrawable);
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, BufferType bufferType) {
        super.setText(charSequence, bufferType);
        post(this.refreshRunnable);
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v7.widget.AppCompatTextView
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getMeasuredWidth() != 0 && !this.inflated && this.textColorDrawable != null) {
            resetColorState();
            this.inflated = true;
        }
    }

    public void setTextDrawable(Drawable drawable) {
        this.textColorDrawable = drawable;
        this.mShaderHashMap.clear();
        setTextDrawableInner(drawable);
    }

    private void setTextDrawableInner(Drawable drawable) {
        this.textColorDrawable = drawable;
        Drawable drawable2 = this.textColorDrawable;
        if (drawable2 instanceof StateListDrawable) {
            drawable2.setState(getDrawableState());
            Drawable current = this.textColorDrawable.getCurrent();
            Shader cachedShader = getCachedShader();
            if (cachedShader != null) {
                setTextColor(-16777216);
                getPaint().setShader(cachedShader);
            } else if (current instanceof ColorDrawable) {
                getPaint().setShader(null);
                setTextColor(((ColorDrawable) current).getColor());
            } else if (current instanceof GradientDrawable) {
                setTextColor(-16777216);
                setGradientColor((GradientDrawable) current);
            } else if (current instanceof BitmapDrawable) {
                setTextColor(-16777216);
                TextPaint paint = getPaint();
                Shader bitmapDrawable = setBitmapDrawable((BitmapDrawable) current);
                paint.setShader(bitmapDrawable);
                this.mShaderHashMap.put(current.getClass().getName(), new ViewState(this, bitmapDrawable, getMeasuredWidth()));
            }
        } else if (drawable2 instanceof GradientDrawable) {
            setTextColor(-16777216);
            Shader cachedShader2 = getCachedShader();
            if (cachedShader2 != null) {
                getPaint().setShader(cachedShader2);
            } else {
                setGradientColor((GradientDrawable) this.textColorDrawable);
            }
        } else if (drawable2 instanceof BitmapDrawable) {
            this.mShaderHashMap.put(this.textColorDrawable.getClass().getName(), new ViewState(this, setBitmapDrawable((BitmapDrawable) drawable2), getMeasuredWidth()));
        }
    }

    private Shader getCachedShader() {
        ViewState viewState = this.mShaderHashMap.get(this.textColorDrawable.getClass().getName());
        if (this.textColorDrawable == null || viewState == null) {
            return null;
        }
        return viewState.getShader(getMeasuredWidth());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void resetColorState() {
        setTextDrawableInner(this.textColorDrawable);
    }

    private Shader setBitmapDrawable(BitmapDrawable bitmapDrawable) {
        setTextColor(-16777216);
        TextPaint paint = getPaint();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        paint.setShader(bitmapShader);
        return bitmapShader;
    }

    private void setGradientColor(GradientDrawable gradientDrawable) {
        int[] iArr;
        int[] colors = DrawableCompatHelper.getColors(gradientDrawable);
        boolean z = false;
        if (getMeasuredWidth() == 0) {
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            measure(makeMeasureSpec, makeMeasureSpec);
        }
        if (getMeasuredWidth() != 0 && colors != null && colors.length > 0) {
            if (colors.length == 1) {
                iArr = new int[]{colors[0], colors[0]};
            } else {
                iArr = colors;
            }
            TextPaint paint = getPaint();
            float measuredWidth = (float) getMeasuredWidth();
            if (iArr.length == 3) {
                z = true;
            }
            LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, measuredWidth, 0.0f, iArr, DrawableCompatHelper.getPositions(gradientDrawable, z), Shader.TileMode.CLAMP);
            paint.setShader(linearGradient);
            this.mShaderHashMap.put(gradientDrawable.getClass().getName(), new ViewState(this, linearGradient, getMeasuredWidth()));
        }
    }

    public void setPressed(boolean z) {
        boolean isPressed = isPressed();
        super.setPressed(z);
        if (isPressed != z) {
            resetColorState();
        }
    }

    public void setSelected(boolean z) {
        boolean isSelected = isSelected();
        super.setSelected(z);
        if (isSelected != z) {
            resetColorState();
        }
    }

    /* access modifiers changed from: private */
    public final class ViewState {
        int initWidth;
        Shader shader;

        public Shader getShader(int i) {
            if (i != this.initWidth) {
                return null;
            }
            return this.shader;
        }

        public ViewState(DrawableTextView drawableTextView, Shader shader2, int i) {
            this.shader = shader2;
            this.initWidth = i;
        }
    }
}
