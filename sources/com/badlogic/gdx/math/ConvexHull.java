package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.ShortArray;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/ConvexHull.class */
public class ConvexHull {
    private float[] sortedPoints;
    private final IntArray quicksortStack = new IntArray();
    private final FloatArray hull = new FloatArray();
    private final IntArray indices = new IntArray();
    private final ShortArray originalIndices = new ShortArray(false, 0);

    public FloatArray computePolygon(FloatArray floatArray, boolean z) {
        return computePolygon(floatArray.items, 0, floatArray.size, z);
    }

    public FloatArray computePolygon(float[] fArr, boolean z) {
        return computePolygon(fArr, 0, fArr.length, z);
    }

    public FloatArray computePolygon(float[] fArr, int i, int i2, boolean z) {
        int i3 = i + i2;
        if (!z) {
            if (this.sortedPoints == null || this.sortedPoints.length < i2) {
                this.sortedPoints = new float[i2];
            }
            System.arraycopy(fArr, i, this.sortedPoints, 0, i2);
            fArr = this.sortedPoints;
            i = 0;
            i3 = i2;
            sort(fArr, i2);
        }
        FloatArray floatArray = this.hull;
        floatArray.clear();
        for (int i4 = i; i4 < i3; i4 += 2) {
            float f = fArr[i4];
            float f2 = fArr[i4 + 1];
            while (floatArray.size >= 4 && ccw(f, f2) <= 0.0f) {
                floatArray.size -= 2;
            }
            floatArray.add(f);
            floatArray.add(f2);
        }
        int i5 = floatArray.size + 2;
        for (int i6 = i3 - 4; i6 >= i; i6 -= 2) {
            float f3 = fArr[i6];
            float f4 = fArr[i6 + 1];
            while (floatArray.size >= i5 && ccw(f3, f4) <= 0.0f) {
                floatArray.size -= 2;
            }
            floatArray.add(f3);
            floatArray.add(f4);
        }
        return floatArray;
    }

    public IntArray computeIndices(FloatArray floatArray, boolean z, boolean z2) {
        return computeIndices(floatArray.items, 0, floatArray.size, z, z2);
    }

    public IntArray computeIndices(float[] fArr, boolean z, boolean z2) {
        return computeIndices(fArr, 0, fArr.length, z, z2);
    }

    public IntArray computeIndices(float[] fArr, int i, int i2, boolean z, boolean z2) {
        if (i2 > 32767) {
            throw new IllegalArgumentException("count must be <= 32767");
        }
        int i3 = i + i2;
        if (!z) {
            if (this.sortedPoints == null || this.sortedPoints.length < i2) {
                this.sortedPoints = new float[i2];
            }
            System.arraycopy(fArr, i, this.sortedPoints, 0, i2);
            fArr = this.sortedPoints;
            i = 0;
            i3 = i2;
            sortWithIndices(fArr, i2, z2);
        }
        IntArray intArray = this.indices;
        intArray.clear();
        FloatArray floatArray = this.hull;
        floatArray.clear();
        int i4 = i;
        int i5 = i4;
        int i6 = i4 / 2;
        while (i5 < i3) {
            float f = fArr[i5];
            float f2 = fArr[i5 + 1];
            while (floatArray.size >= 4 && ccw(f, f2) <= 0.0f) {
                floatArray.size -= 2;
                intArray.size--;
            }
            floatArray.add(f);
            floatArray.add(f2);
            intArray.add(i6);
            i5 += 2;
            i6++;
        }
        int i7 = i3 - 4;
        int i8 = i7;
        int i9 = i7 / 2;
        int i10 = floatArray.size + 2;
        while (i8 >= i) {
            float f3 = fArr[i8];
            float f4 = fArr[i8 + 1];
            while (floatArray.size >= i10 && ccw(f3, f4) <= 0.0f) {
                floatArray.size -= 2;
                intArray.size--;
            }
            floatArray.add(f3);
            floatArray.add(f4);
            intArray.add(i9);
            i8 -= 2;
            i9--;
        }
        if (!z) {
            short[] sArr = this.originalIndices.items;
            int[] iArr = intArray.items;
            int i11 = intArray.size;
            for (int i12 = 0; i12 < i11; i12++) {
                iArr[i12] = sArr[iArr[i12]];
            }
        }
        return intArray;
    }

    private float ccw(float f, float f2) {
        FloatArray floatArray = this.hull;
        int i = floatArray.size;
        float f3 = floatArray.get(i - 4);
        float f4 = floatArray.get(i - 3);
        return ((floatArray.get(i - 2) - f3) * (f2 - f4)) - ((floatArray.peek() - f4) * (f - f3));
    }

    private void sort(float[] fArr, int i) {
        IntArray intArray = this.quicksortStack;
        intArray.add(0);
        intArray.add((i - 1) - 1);
        while (intArray.size > 0) {
            int pop = intArray.pop();
            int pop2 = intArray.pop();
            if (pop > pop2) {
                int quicksortPartition = quicksortPartition(fArr, pop2, pop);
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

    private int quicksortPartition(float[] fArr, int i, int i2) {
        float f = fArr[i];
        float f2 = fArr[i + 1];
        int i3 = i2;
        int i4 = i;
        while (i4 < i3) {
            while (i4 < i3 && fArr[i4] <= f) {
                i4 += 2;
            }
            while (true) {
                if (fArr[i3] > f || (fArr[i3] == f && fArr[i3 + 1] < f2)) {
                    i3 -= 2;
                }
            }
            if (i4 < i3) {
                float f3 = fArr[i4];
                fArr[i4] = fArr[i3];
                fArr[i3] = f3;
                float f4 = fArr[i4 + 1];
                fArr[i4 + 1] = fArr[i3 + 1];
                fArr[i3 + 1] = f4;
            }
        }
        if (f > fArr[i3] || (f == fArr[i3] && f2 < fArr[i3 + 1])) {
            fArr[i] = fArr[i3];
            fArr[i3] = f;
            fArr[i + 1] = fArr[i3 + 1];
            fArr[i3 + 1] = f2;
        }
        return i3;
    }

    private void sortWithIndices(float[] fArr, int i, boolean z) {
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
                int quicksortPartitionWithIndices = quicksortPartitionWithIndices(fArr, pop2, pop, z, sArr);
                if (quicksortPartitionWithIndices - pop2 > pop - quicksortPartitionWithIndices) {
                    intArray.add(pop2);
                    intArray.add(quicksortPartitionWithIndices - 2);
                }
                intArray.add(quicksortPartitionWithIndices + 2);
                intArray.add(pop);
                if (pop - quicksortPartitionWithIndices >= quicksortPartitionWithIndices - pop2) {
                    intArray.add(pop2);
                    intArray.add(quicksortPartitionWithIndices - 2);
                }
            }
        }
    }

    private int quicksortPartitionWithIndices(float[] fArr, int i, int i2, boolean z, short[] sArr) {
        float f = fArr[i];
        float f2 = fArr[i + 1];
        int i3 = i2;
        int i4 = i;
        while (i4 < i3) {
            while (i4 < i3 && fArr[i4] <= f) {
                i4 += 2;
            }
            if (!z) {
                while (true) {
                    if (fArr[i3] <= f && (fArr[i3] != f || fArr[i3 + 1] <= f2)) {
                        break;
                    }
                    i3 -= 2;
                }
            } else {
                while (true) {
                    if (fArr[i3] <= f && (fArr[i3] != f || fArr[i3 + 1] >= f2)) {
                        break;
                    }
                    i3 -= 2;
                }
            }
            if (i4 < i3) {
                float f3 = fArr[i4];
                fArr[i4] = fArr[i3];
                fArr[i3] = f3;
                float f4 = fArr[i4 + 1];
                fArr[i4 + 1] = fArr[i3 + 1];
                fArr[i3 + 1] = f4;
                short s = sArr[i4 / 2];
                sArr[i4 / 2] = sArr[i3 / 2];
                sArr[i3 / 2] = s;
            }
        }
        if (f > fArr[i3] || (f == fArr[i3] && (!z ? f2 > fArr[i3 + 1] : f2 < fArr[i3 + 1]))) {
            fArr[i] = fArr[i3];
            fArr[i3] = f;
            fArr[i + 1] = fArr[i3 + 1];
            fArr[i3 + 1] = f2;
            short s2 = sArr[i / 2];
            sArr[i / 2] = sArr[i3 / 2];
            sArr[i3 / 2] = s2;
        }
        return i3;
    }
}
