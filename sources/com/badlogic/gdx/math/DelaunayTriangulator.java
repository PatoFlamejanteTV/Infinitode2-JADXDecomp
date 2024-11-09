package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.BooleanArray;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.ShortArray;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/DelaunayTriangulator.class */
public class DelaunayTriangulator {
    private static final float EPSILON = 1.0E-6f;
    private static final int INSIDE = 0;
    private static final int COMPLETE = 1;
    private static final int INCOMPLETE = 2;
    private float[] sortedPoints;
    private final IntArray quicksortStack = new IntArray();
    private final ShortArray triangles = new ShortArray(false, 16);
    private final ShortArray originalIndices = new ShortArray(false, 0);
    private final IntArray edges = new IntArray();
    private final BooleanArray complete = new BooleanArray(false, 16);
    private final float[] superTriangle = new float[6];
    private final Vector2 centroid = new Vector2();

    public ShortArray computeTriangles(FloatArray floatArray, boolean z) {
        return computeTriangles(floatArray.items, 0, floatArray.size, z);
    }

    public ShortArray computeTriangles(float[] fArr, boolean z) {
        return computeTriangles(fArr, 0, fArr.length, z);
    }

    public ShortArray computeTriangles(float[] fArr, int i, int i2, boolean z) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        if (i2 > 32767) {
            throw new IllegalArgumentException("count must be <= 32767");
        }
        ShortArray shortArray = this.triangles;
        shortArray.clear();
        if (i2 < 6) {
            return shortArray;
        }
        shortArray.ensureCapacity(i2);
        if (!z) {
            if (this.sortedPoints == null || this.sortedPoints.length < i2) {
                this.sortedPoints = new float[i2];
            }
            System.arraycopy(fArr, i, this.sortedPoints, 0, i2);
            fArr = this.sortedPoints;
            i = 0;
            sort(fArr, i2);
        }
        int i3 = i + i2;
        float f7 = fArr[0];
        float f8 = fArr[1];
        float f9 = f7;
        float f10 = f8;
        int i4 = i + 2;
        while (i4 < i3) {
            float f11 = fArr[i4];
            if (f11 < f7) {
                f7 = f11;
            }
            if (f11 > f9) {
                f9 = f11;
            }
            int i5 = i4 + 1;
            float f12 = fArr[i5];
            if (f12 < f8) {
                f8 = f12;
            }
            if (f12 > f10) {
                f10 = f12;
            }
            i4 = i5 + 1;
        }
        float f13 = f9 - f7;
        float f14 = f10 - f8;
        float f15 = (f13 > f14 ? f13 : f14) * 20.0f;
        float f16 = (f9 + f7) / 2.0f;
        float f17 = (f10 + f8) / 2.0f;
        float[] fArr2 = this.superTriangle;
        fArr2[0] = f16 - f15;
        fArr2[1] = f17 - f15;
        fArr2[2] = f16;
        fArr2[3] = f17 + f15;
        fArr2[4] = f16 + f15;
        fArr2[5] = f17 - f15;
        IntArray intArray = this.edges;
        intArray.ensureCapacity(i2 / 2);
        BooleanArray booleanArray = this.complete;
        booleanArray.clear();
        booleanArray.ensureCapacity(i2);
        shortArray.add(i3);
        shortArray.add(i3 + 2);
        shortArray.add(i3 + 4);
        booleanArray.add(false);
        for (int i6 = i; i6 < i3; i6 += 2) {
            float f18 = fArr[i6];
            float f19 = fArr[i6 + 1];
            short[] sArr = shortArray.items;
            boolean[] zArr = booleanArray.items;
            for (int i7 = shortArray.size - 1; i7 >= 0; i7 -= 3) {
                int i8 = i7 / 3;
                if (!zArr[i8]) {
                    short s = sArr[i7 - 2];
                    short s2 = sArr[i7 - 1];
                    short s3 = sArr[i7];
                    if (s >= i3) {
                        int i9 = s - i3;
                        f = fArr2[i9];
                        f2 = fArr2[i9 + 1];
                    } else {
                        f = fArr[s];
                        f2 = fArr[s + 1];
                    }
                    if (s2 >= i3) {
                        int i10 = s2 - i3;
                        f3 = fArr2[i10];
                        f4 = fArr2[i10 + 1];
                    } else {
                        f3 = fArr[s2];
                        f4 = fArr[s2 + 1];
                    }
                    if (s3 >= i3) {
                        int i11 = s3 - i3;
                        f5 = fArr2[i11];
                        f6 = fArr2[i11 + 1];
                    } else {
                        f5 = fArr[s3];
                        f6 = fArr[s3 + 1];
                    }
                    switch (circumCircle(f18, f19, f, f2, f3, f4, f5, f6)) {
                        case 0:
                            intArray.add(s, s2, s2, s3);
                            intArray.add(s3, s);
                            shortArray.removeRange(i7 - 2, i7);
                            booleanArray.removeIndex(i8);
                            break;
                        case 1:
                            zArr[i8] = true;
                            break;
                    }
                }
            }
            int[] iArr = intArray.items;
            int i12 = intArray.size;
            for (int i13 = 0; i13 < i12; i13 += 2) {
                int i14 = iArr[i13];
                if (i14 != -1) {
                    int i15 = iArr[i13 + 1];
                    boolean z2 = false;
                    for (int i16 = i13 + 2; i16 < i12; i16 += 2) {
                        if (i14 == iArr[i16 + 1] && i15 == iArr[i16]) {
                            z2 = true;
                            iArr[i16] = -1;
                        }
                    }
                    if (!z2) {
                        shortArray.add(i14);
                        shortArray.add(iArr[i13 + 1]);
                        shortArray.add(i6);
                        booleanArray.add(false);
                    }
                }
            }
            intArray.clear();
        }
        short[] sArr2 = shortArray.items;
        for (int i17 = shortArray.size - 1; i17 >= 0; i17 -= 3) {
            if (sArr2[i17] >= i3 || sArr2[i17 - 1] >= i3 || sArr2[i17 - 2] >= i3) {
                shortArray.removeIndex(i17);
                shortArray.removeIndex(i17 - 1);
                shortArray.removeIndex(i17 - 2);
            }
        }
        if (!z) {
            short[] sArr3 = this.originalIndices.items;
            int i18 = shortArray.size;
            for (int i19 = 0; i19 < i18; i19++) {
                sArr2[i19] = (short) (sArr3[sArr2[i19] / 2] << 1);
            }
        }
        if (i == 0) {
            int i20 = shortArray.size;
            for (int i21 = 0; i21 < i20; i21++) {
                sArr2[i21] = (short) (sArr2[i21] / 2);
            }
        } else {
            int i22 = shortArray.size;
            for (int i23 = 0; i23 < i22; i23++) {
                sArr2[i23] = (short) ((sArr2[i23] - i) / 2);
            }
        }
        return shortArray;
    }

    private int circumCircle(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f9;
        float f10;
        float abs = Math.abs(f4 - f6);
        float abs2 = Math.abs(f6 - f8);
        if (abs >= 1.0E-6f) {
            float f11 = (-(f5 - f3)) / (f6 - f4);
            float f12 = (f3 + f5) / 2.0f;
            float f13 = (f4 + f6) / 2.0f;
            if (abs2 < 1.0E-6f) {
                f9 = (f7 + f5) / 2.0f;
                f10 = (f11 * (f9 - f12)) + f13;
            } else {
                float f14 = (-(f7 - f5)) / (f8 - f6);
                f9 = ((((f11 * f12) - (f14 * ((f5 + f7) / 2.0f))) + ((f6 + f8) / 2.0f)) - f13) / (f11 - f14);
                f10 = (f11 * (f9 - f12)) + f13;
            }
        } else {
            if (abs2 < 1.0E-6f) {
                return 2;
            }
            f9 = (f5 + f3) / 2.0f;
            f10 = (((-(f7 - f5)) / (f8 - f6)) * (f9 - ((f5 + f7) / 2.0f))) + ((f6 + f8) / 2.0f);
        }
        float f15 = f5 - f9;
        float f16 = f6 - f10;
        float f17 = (f15 * f15) + (f16 * f16);
        float f18 = f - f9;
        float f19 = f18 * f18;
        float f20 = f2 - f10;
        if ((f19 + (f20 * f20)) - f17 <= 1.0E-6f) {
            return 0;
        }
        return (f <= f9 || f19 <= f17) ? 2 : 1;
    }

    private void sort(float[] fArr, int i) {
        int i2 = i / 2;
        this.originalIndices.clear();
        this.originalIndices.ensureCapacity(i2);
        short[] sArr = this.originalIndices.items;
        short s = 0;
        while (true) {
            short s2 = s;
            if (s2 >= i2) {
                break;
            }
            sArr[s2] = s2;
            s = (short) (s2 + 1);
        }
        IntArray intArray = this.quicksortStack;
        intArray.add(0);
        intArray.add((i - 1) - 1);
        while (intArray.size > 0) {
            int pop = intArray.pop();
            int pop2 = intArray.pop();
            if (pop > pop2) {
                int quicksortPartition = quicksortPartition(fArr, pop2, pop, sArr);
                if (quicksortPartition - pop2 > pop - quicksortPartition) {
                    intArray.add(pop2);
                    intArray.add(quicksortPartition - 2);
                }
                intArray.add(quicksortPartition + 2);
                intArray.add(pop);
                if (pop - quicksortPartition >= quicksortPartition - pop2) {
                    intArray.add(pop2);
                    intArray.add(quicksortPartition - 2);
                }
            }
        }
    }

    private int quicksortPartition(float[] fArr, int i, int i2, short[] sArr) {
        float f = fArr[i];
        int i3 = i2;
        int i4 = i + 2;
        while (i4 < i3) {
            while (i4 < i3 && fArr[i4] <= f) {
                i4 += 2;
            }
            while (fArr[i3] > f) {
                i3 -= 2;
            }
            if (i4 < i3) {
                float f2 = fArr[i4];
                fArr[i4] = fArr[i3];
                fArr[i3] = f2;
                float f3 = fArr[i4 + 1];
                fArr[i4 + 1] = fArr[i3 + 1];
                fArr[i3 + 1] = f3;
                short s = sArr[i4 / 2];
                sArr[i4 / 2] = sArr[i3 / 2];
                sArr[i3 / 2] = s;
            }
        }
        if (f > fArr[i3]) {
            fArr[i] = fArr[i3];
            fArr[i3] = f;
            float f4 = fArr[i + 1];
            fArr[i + 1] = fArr[i3 + 1];
            fArr[i3 + 1] = f4;
            short s2 = sArr[i / 2];
            sArr[i / 2] = sArr[i3 / 2];
            sArr[i3 / 2] = s2;
        }
        return i3;
    }

    public void trim(ShortArray shortArray, float[] fArr, float[] fArr2, int i, int i2) {
        short[] sArr = shortArray.items;
        for (int i3 = shortArray.size - 1; i3 >= 0; i3 -= 3) {
            int i4 = sArr[i3 - 2] << 1;
            int i5 = sArr[i3 - 1] << 1;
            int i6 = sArr[i3] << 1;
            GeometryUtils.triangleCentroid(fArr[i4], fArr[i4 + 1], fArr[i5], fArr[i5 + 1], fArr[i6], fArr[i6 + 1], this.centroid);
            if (!Intersector.isPointInPolygon(fArr2, i, i2, this.centroid.x, this.centroid.y)) {
                shortArray.removeIndex(i3);
                shortArray.removeIndex(i3 - 1);
                shortArray.removeIndex(i3 - 2);
            }
        }
    }
}
