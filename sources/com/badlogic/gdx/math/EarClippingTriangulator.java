package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.ShortArray;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/EarClippingTriangulator.class */
public class EarClippingTriangulator {
    private static final int CONCAVE = -1;
    private static final int CONVEX = 1;
    private short[] indices;
    private float[] vertices;
    private int vertexCount;
    private final ShortArray indicesArray = new ShortArray();
    private final IntArray vertexTypes = new IntArray();
    private final ShortArray triangles = new ShortArray();

    public ShortArray computeTriangles(FloatArray floatArray) {
        return computeTriangles(floatArray.items, 0, floatArray.size);
    }

    public ShortArray computeTriangles(float[] fArr) {
        return computeTriangles(fArr, 0, fArr.length);
    }

    public ShortArray computeTriangles(float[] fArr, int i, int i2) {
        this.vertices = fArr;
        int i3 = i2 / 2;
        this.vertexCount = i3;
        int i4 = i / 2;
        ShortArray shortArray = this.indicesArray;
        shortArray.clear();
        shortArray.ensureCapacity(i3);
        shortArray.size = i3;
        short[] sArr = shortArray.items;
        this.indices = sArr;
        if (GeometryUtils.isClockwise(fArr, i, i2)) {
            short s = 0;
            while (true) {
                short s2 = s;
                if (s2 >= i3) {
                    break;
                }
                sArr[s2] = (short) (i4 + s2);
                s = (short) (s2 + 1);
            }
        } else {
            int i5 = i3 - 1;
            for (int i6 = 0; i6 < i3; i6++) {
                sArr[i6] = (short) ((i4 + i5) - i6);
            }
        }
        IntArray intArray = this.vertexTypes;
        intArray.clear();
        intArray.ensureCapacity(i3);
        for (int i7 = 0; i7 < i3; i7++) {
            intArray.add(classifyVertex(i7));
        }
        ShortArray shortArray2 = this.triangles;
        shortArray2.clear();
        shortArray2.ensureCapacity(Math.max(0, i3 - 2) * 3);
        triangulate();
        return shortArray2;
    }

    private void triangulate() {
        int[] iArr = this.vertexTypes.items;
        while (this.vertexCount > 3) {
            int findEarTip = findEarTip();
            cutEarTip(findEarTip);
            int previousIndex = previousIndex(findEarTip);
            int i = findEarTip == this.vertexCount ? 0 : findEarTip;
            iArr[previousIndex] = classifyVertex(previousIndex);
            iArr[i] = classifyVertex(i);
        }
        if (this.vertexCount == 3) {
            ShortArray shortArray = this.triangles;
            short[] sArr = this.indices;
            shortArray.add(sArr[0]);
            shortArray.add(sArr[1]);
            shortArray.add(sArr[2]);
        }
    }

    private int classifyVertex(int i) {
        short[] sArr = this.indices;
        int i2 = sArr[previousIndex(i)] << 1;
        int i3 = sArr[i] << 1;
        int i4 = sArr[nextIndex(i)] << 1;
        float[] fArr = this.vertices;
        return computeSpannedAreaSign(fArr[i2], fArr[i2 + 1], fArr[i3], fArr[i3 + 1], fArr[i4], fArr[i4 + 1]);
    }

    private int findEarTip() {
        int i = this.vertexCount;
        for (int i2 = 0; i2 < i; i2++) {
            if (isEarTip(i2)) {
                return i2;
            }
        }
        int[] iArr = this.vertexTypes.items;
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] != -1) {
                return i3;
            }
        }
        return 0;
    }

    private boolean isEarTip(int i) {
        int[] iArr = this.vertexTypes.items;
        if (iArr[i] == -1) {
            return false;
        }
        int previousIndex = previousIndex(i);
        int nextIndex = nextIndex(i);
        short[] sArr = this.indices;
        int i2 = sArr[previousIndex] << 1;
        int i3 = sArr[i] << 1;
        int i4 = sArr[nextIndex] << 1;
        float[] fArr = this.vertices;
        float f = fArr[i2];
        float f2 = fArr[i2 + 1];
        float f3 = fArr[i3];
        float f4 = fArr[i3 + 1];
        float f5 = fArr[i4];
        float f6 = fArr[i4 + 1];
        int nextIndex2 = nextIndex(nextIndex);
        while (true) {
            int i5 = nextIndex2;
            if (i5 != previousIndex) {
                if (iArr[i5] != 1) {
                    int i6 = sArr[i5] << 1;
                    float f7 = fArr[i6];
                    float f8 = fArr[i6 + 1];
                    if (computeSpannedAreaSign(f5, f6, f, f2, f7, f8) >= 0 && computeSpannedAreaSign(f, f2, f3, f4, f7, f8) >= 0 && computeSpannedAreaSign(f3, f4, f5, f6, f7, f8) >= 0) {
                        return false;
                    }
                }
                nextIndex2 = nextIndex(i5);
            } else {
                return true;
            }
        }
    }

    private void cutEarTip(int i) {
        short[] sArr = this.indices;
        ShortArray shortArray = this.triangles;
        shortArray.add(sArr[previousIndex(i)]);
        shortArray.add(sArr[i]);
        shortArray.add(sArr[nextIndex(i)]);
        this.indicesArray.removeIndex(i);
        this.vertexTypes.removeIndex(i);
        this.vertexCount--;
    }

    private int previousIndex(int i) {
        return (i == 0 ? this.vertexCount : i) - 1;
    }

    private int nextIndex(int i) {
        return (i + 1) % this.vertexCount;
    }

    private static int computeSpannedAreaSign(float f, float f2, float f3, float f4, float f5, float f6) {
        return (int) Math.signum((f * (f6 - f4)) + (f3 * (f2 - f6)) + (f5 * (f4 - f2)));
    }
}
