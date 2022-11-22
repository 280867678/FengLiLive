package com.slzhibo.library.ui.view.widget.guideview;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class Configuration implements Parcelable {
    public static final Creator<Configuration> CREATOR = new Creator<Configuration>() {
        /* class com.slzhibo.library.ui.view.widget.guideview.Configuration.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Configuration createFromParcel(Parcel parcel) {
            Configuration configuration = new Configuration();
            configuration.mAlpha = parcel.readInt();
            configuration.mFullingViewId = parcel.readInt();
            configuration.mTargetViewId = parcel.readInt();
            configuration.mFullingColorId = parcel.readInt();
            configuration.mCorner = parcel.readInt();
            configuration.mPadding = parcel.readInt();
            configuration.mPaddingLeft = parcel.readInt();
            configuration.mPaddingTop = parcel.readInt();
            configuration.mPaddingRight = parcel.readInt();
            configuration.mPaddingBottom = parcel.readInt();
            configuration.mGraphStyle = parcel.readInt();
            boolean z = false;
            configuration.mAutoDismiss = parcel.readByte() == 1;
            if (parcel.readByte() == 1) {
                z = true;
            }
            configuration.mOverlayTarget = z;
            return configuration;
        }

        @Override // android.os.Parcelable.Creator
        public Configuration[] newArray(int i) {
            return new Configuration[i];
        }
    };
    int mAlpha = 255;
    boolean mAutoDismiss = true;
    Bitmap mBitmap;
    int mCorner = 0;
    int mEnterAnimationId = -1;
    int mExitAnimationId = -1;
    int mFullingColorId = 17170444;
    int mFullingViewId = -1;
    int mGraphStyle = 0;
    boolean mOutsideTouchable = false;
    boolean mOverlayTarget = false;
    int mPadding = 0;
    int mPaddingBottom = 0;
    int mPaddingLeft = 0;
    int mPaddingRight = 0;
    int mPaddingTop = 0;
    View mTargetView = null;
    int mTargetViewId = -1;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mAlpha);
        parcel.writeInt(this.mFullingViewId);
        parcel.writeInt(this.mTargetViewId);
        parcel.writeInt(this.mFullingColorId);
        parcel.writeInt(this.mCorner);
        parcel.writeInt(this.mPadding);
        parcel.writeInt(this.mPaddingLeft);
        parcel.writeInt(this.mPaddingTop);
        parcel.writeInt(this.mPaddingRight);
        parcel.writeInt(this.mPaddingBottom);
        parcel.writeInt(this.mGraphStyle);
        parcel.writeByte(this.mAutoDismiss ? (byte) 1 : 0);
        parcel.writeByte(this.mOverlayTarget ? (byte) 1 : 0);
    }
}
