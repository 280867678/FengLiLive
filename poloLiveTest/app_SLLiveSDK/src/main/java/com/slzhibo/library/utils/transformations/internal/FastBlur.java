package com.slzhibo.library.utils.transformations.internal;

import android.graphics.Bitmap;

import androidx.core.view.MotionEventCompat;

import java.lang.reflect.Array;

public class FastBlur {
    public static Bitmap blur(Bitmap bitmap, int i, boolean z) {
        int[] iArr;
        int i2 = i;
        Bitmap copy = z ? bitmap : bitmap.copy(bitmap.getConfig(), true);
        if (i2 < 1) {
            return null;
        }
        int width = copy.getWidth();
        int height = copy.getHeight();
        int i3 = width * height;
        int[] iArr2 = new int[i3];
        copy.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i4 = width - 1;
        int i5 = height - 1;
        int i6 = i2 + i2 + 1;
        int[] iArr3 = new int[i3];
        int[] iArr4 = new int[i3];
        int[] iArr5 = new int[i3];
        int[] iArr6 = new int[Math.max(width, height)];
        int i7 = (i6 + 1) >> 1;
        int i8 = i7 * i7;
        int i9 = i8 * 256;
        int[] iArr7 = new int[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            iArr7[i10] = i10 / i8;
        }
        int[][] iArr8 = (int[][]) Array.newInstance(int.class, i6, 3);
        int i11 = i2 + 1;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (i12 < height) {
            int i15 = -i2;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            while (i15 <= i2) {
                int i25 = iArr2[i13 + Math.min(i4, Math.max(i15, 0))];
                int[] iArr9 = iArr8[i15 + i2];
                iArr9[0] = (i25 & 16711680) >> 16;
                iArr9[1] = (i25 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr9[2] = i25 & 255;
                int abs = i11 - Math.abs(i15);
                i16 += iArr9[0] * abs;
                i17 += iArr9[1] * abs;
                i18 += iArr9[2] * abs;
                if (i15 > 0) {
                    i22 += iArr9[0];
                    i23 += iArr9[1];
                    i24 += iArr9[2];
                } else {
                    i19 += iArr9[0];
                    i20 += iArr9[1];
                    i21 += iArr9[2];
                }
                i15++;
                height = height;
                i5 = i5;
            }
            int i26 = i2;
            int i27 = 0;
            while (i27 < width) {
                iArr3[i13] = iArr7[i16];
                iArr4[i13] = iArr7[i17];
                iArr5[i13] = iArr7[i18];
                int i28 = i16 - i19;
                int i29 = i17 - i20;
                int i30 = i18 - i21;
                int[] iArr10 = iArr8[((i26 - i2) + i6) % i6];
                int i31 = i19 - iArr10[0];
                int i32 = i20 - iArr10[1];
                int i33 = i21 - iArr10[2];
                if (i12 == 0) {
                    iArr = iArr7;
                    iArr6[i27] = Math.min(i27 + i2 + 1, i4);
                } else {
                    iArr = iArr7;
                }
                int i34 = iArr2[i14 + iArr6[i27]];
                iArr10[0] = (i34 & 16711680) >> 16;
                iArr10[1] = (i34 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr10[2] = i34 & 255;
                int i35 = i22 + iArr10[0];
                int i36 = i23 + iArr10[1];
                int i37 = i24 + iArr10[2];
                i16 = i28 + i35;
                i17 = i29 + i36;
                i18 = i30 + i37;
                i26 = (i26 + 1) % i6;
                int[] iArr11 = iArr8[i26 % i6];
                i19 = i31 + iArr11[0];
                i20 = i32 + iArr11[1];
                i21 = i33 + iArr11[2];
                i22 = i35 - iArr11[0];
                i23 = i36 - iArr11[1];
                i24 = i37 - iArr11[2];
                i13++;
                i27++;
                iArr7 = iArr;
            }
            i14 += width;
            i12++;
            copy = copy;
            height = height;
            i5 = i5;
        }
        int i38 = i5;
        int i39 = height;
        int i40 = 0;
        while (i40 < width) {
            int i41 = -i2;
            int i42 = i41 * width;
            int i43 = 0;
            int i44 = 0;
            int i45 = 0;
            int i46 = 0;
            int i47 = 0;
            int i48 = 0;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            while (i41 <= i2) {
                int max = Math.max(0, i42) + i40;
                int[] iArr12 = iArr8[i41 + i2];
                iArr12[0] = iArr3[max];
                iArr12[1] = iArr4[max];
                iArr12[2] = iArr5[max];
                int abs2 = i11 - Math.abs(i41);
                i43 += iArr3[max] * abs2;
                i44 += iArr4[max] * abs2;
                i45 += iArr5[max] * abs2;
                if (i41 > 0) {
                    i49 += iArr12[0];
                    i50 += iArr12[1];
                    i51 += iArr12[2];
                } else {
                    i46 += iArr12[0];
                    i47 += iArr12[1];
                    i48 += iArr12[2];
                }
                if (i41 < i38) {
                    i42 += width;
                }
                i41++;
                i38 = i38;
                iArr6 = iArr6;
            }
            int i52 = i40;
            int i53 = i50;
            int i54 = i51;
            int i55 = 0;
            int i56 = i2;
            int i57 = i49;
            int i58 = i48;
            int i59 = i47;
            int i60 = i46;
            int i61 = i45;
            int i62 = i44;
            int i63 = i43;
            while (i55 < i39) {
                iArr2[i52] = (iArr2[i52] & -16777216) | (iArr7[i63] << 16) | (iArr7[i62] << 8) | iArr7[i61];
                int i64 = i63 - i60;
                int i65 = i62 - i59;
                int i66 = i61 - i58;
                int[] iArr13 = iArr8[((i56 - i2) + i6) % i6];
                int i67 = i60 - iArr13[0];
                int i68 = i59 - iArr13[1];
                int i69 = i58 - iArr13[2];
                if (i40 == 0) {
                    iArr6[i55] = Math.min(i55 + i11, i38) * width;
                }
                int i70 = iArr6[i55] + i40;
                iArr13[0] = iArr3[i70];
                iArr13[1] = iArr4[i70];
                iArr13[2] = iArr5[i70];
                int i71 = i57 + iArr13[0];
                int i72 = i53 + iArr13[1];
                int i73 = i54 + iArr13[2];
                i63 = i64 + i71;
                i62 = i65 + i72;
                i61 = i66 + i73;
                i56 = (i56 + 1) % i6;
                int[] iArr14 = iArr8[i56];
                i60 = i67 + iArr14[0];
                i59 = i68 + iArr14[1];
                i58 = i69 + iArr14[2];
                i57 = i71 - iArr14[0];
                i53 = i72 - iArr14[1];
                i54 = i73 - iArr14[2];
                i52 += width;
                i55++;
                i2 = i;
            }
            i40++;
            i2 = i;
            i38 = i38;
            i39 = i39;
            iArr6 = iArr6;
        }
        copy.setPixels(iArr2, 0, width, 0, 0, width, i39);
        return copy;
    }
}
