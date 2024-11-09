package com.prineside.tdi2.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ShortArray;
import com.badlogic.gdx.utils.reflect.ArrayReflection;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/AABB.class */
public final class AABB<T> {

    /* renamed from: a, reason: collision with root package name */
    private short f3806a;

    /* renamed from: b, reason: collision with root package name */
    private short f3807b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private T[] i;
    private BitVector j;
    private float[] k;
    private short[] l;
    private short[] m;
    public Batch debugBatch;

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/AABB$EntryFilter.class */
    public interface EntryFilter {
        boolean test(float f, float f2, float f3);
    }

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/AABB$EntryRetriever.class */
    public interface EntryRetriever<T> {
        boolean retrieve(T t, float f, float f2, float f3);
    }

    /* synthetic */ AABB(byte b2) {
        this();
    }

    static {
        TLog.forClass(AABB.class);
    }

    private AABB() {
        this.f3806a = (short) 0;
        this.f3807b = (short) 0;
        this.c = Float.MAX_VALUE;
        this.d = Float.MAX_VALUE;
        this.e = Float.MIN_VALUE;
        this.f = Float.MIN_VALUE;
        this.j = new BitVector();
        this.k = new float[0];
        this.l = new short[0];
        this.m = new short[0];
    }

    public final boolean isEmpty() {
        return this.f3806a == 0;
    }

    public final float getMinX() {
        return this.c;
    }

    public final float getMaxX() {
        return this.d;
    }

    public final float getMinY() {
        return this.e;
    }

    public final float getMaxY() {
        return this.f;
    }

    public final short getCols() {
        return this.f3806a;
    }

    public final short getRows() {
        return this.f3807b;
    }

    public final float getCellSizeXInv() {
        return this.g;
    }

    public final float getCellSizeYInv() {
        return this.h;
    }

    public final int getEntityCount(int i) {
        short s = this.m[i];
        if (s == -1) {
            return 0;
        }
        return this.l[s];
    }

    public final boolean entriesExistInRect(float f, float f2, float f3, float f4) {
        return f < this.d && f3 > this.c && f2 < this.f && f4 > this.e;
    }

    public final boolean lineCanHitEntry(float f, float f2, float f3, float f4) {
        float f5 = f;
        float f6 = f3;
        if (f > f3) {
            f5 = f3;
            f6 = f;
        }
        float f7 = f2;
        float f8 = f4;
        if (f2 > f4) {
            f7 = f4;
            f8 = f2;
        }
        return entriesExistInRect(f5, f7, f6, f8);
    }

    private void a(BitVector bitVector, EntryFilter entryFilter, EntryRetriever<T> entryRetriever) {
        int i = 0;
        while (true) {
            int nextSetBit = bitVector.nextSetBit(i + 1);
            i = nextSetBit;
            if (nextSetBit != -1) {
                int i2 = i - 1;
                float f = this.k[i2 * 3];
                float f2 = this.k[(i2 * 3) + 1];
                float f3 = this.k[(i2 * 3) + 2];
                if (entryFilter.test(f, f2, f3) && !entryRetriever.retrieve(this.i[i2], f, f2, f3)) {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private short a(float f) {
        return (short) MathUtils.clamp((int) ((f - this.c) * this.g), 0, this.f3806a - 1);
    }

    private short b(float f) {
        return (short) MathUtils.clamp((int) ((f - this.e) * this.h), 0, this.f3807b - 1);
    }

    public final void traverseEntriesInRect(float f, float f2, float f3, float f4, EntryFilter entryFilter, EntryRetriever<T> entryRetriever) {
        BitVector bitVector;
        boolean z;
        if (isEmpty()) {
            return;
        }
        int a2 = a(f);
        int a3 = a(f3);
        int b2 = b(f2);
        int b3 = b(f4);
        if (this.j == null) {
            bitVector = new BitVector(this.i.length + 1);
            z = false;
        } else {
            bitVector = this.j;
            this.j = null;
            bitVector.clear();
            bitVector.ensureCapacity(this.i.length + 1);
            z = true;
        }
        for (int i = b2; i <= b3; i++) {
            for (int i2 = a2; i2 <= a3; i2++) {
                short s = this.m[(i * this.f3806a) + i2];
                if (s != -1) {
                    short s2 = this.l[s];
                    for (int i3 = 0; i3 < s2; i3++) {
                        bitVector.unsafeSet(this.l[s + i3 + 1] + 1);
                    }
                }
            }
        }
        a(bitVector, entryFilter, entryRetriever);
        if (this.debugBatch != null) {
            float cellSizeXInv = 1.0f / getCellSizeXInv();
            float cellSizeYInv = 1.0f / getCellSizeYInv();
            for (int i4 = b2; i4 <= b3; i4++) {
                for (int i5 = a2; i5 <= a3; i5++) {
                    float f5 = (i5 * cellSizeXInv) + this.c;
                    float f6 = (i4 * cellSizeYInv) + this.e;
                    short s3 = this.m[(i4 * this.f3806a) + i5];
                    if (s3 != -1) {
                        short s4 = this.l[s3];
                        this.debugBatch.setColor(0.0f, 1.0f, 0.0f, 0.28f);
                        this.debugBatch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), f5, f6, cellSizeXInv, cellSizeYInv);
                        Game.i.assetManager.getSmallDebugFont().draw(this.debugBatch, new StringBuilder().append((int) s4).toString(), f5 + (cellSizeXInv * 0.5f), f6 + 10.0f, 1.0f, 1, false);
                    } else {
                        this.debugBatch.setColor(1.0f, 0.0f, 0.0f, 0.28f);
                        this.debugBatch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), f5, f6, cellSizeXInv, cellSizeYInv);
                    }
                }
            }
            this.debugBatch.setColor(1.0f, 0.7f, 0.0f, 0.28f);
            int i6 = 0;
            while (true) {
                int nextSetBit = bitVector.nextSetBit(i6 + 1);
                i6 = nextSetBit;
                if (nextSetBit == -1) {
                    break;
                }
                int i7 = i6 - 1;
                float f7 = this.k[i7 * 3];
                float f8 = this.k[(i7 * 3) + 1];
                float f9 = this.k[(i7 * 3) + 2];
                if (entryFilter.test(f7, f8, f9)) {
                    this.debugBatch.draw(Game.i.assetManager.getTextureRegion("circle"), f7 - f9, f8 - f9, f9 * 2.0f, f9 * 2.0f);
                    Game.i.assetManager.getSmallDebugFont().draw(this.debugBatch, new StringBuilder().append(i7).toString(), f7 + (f9 * 0.5f), f8 + (f9 * 0.5f), 1.0f, 1, false);
                }
            }
            this.debugBatch.setColor(Color.WHITE);
        }
        if (z) {
            this.j = bitVector;
        }
    }

    public final void traverseEntriesInLine(float f, float f2, float f3, float f4, EntryFilter entryFilter, EntryRetriever<T> entryRetriever) {
        BitVector bitVector;
        boolean z;
        if (isEmpty()) {
            return;
        }
        float f5 = 1.0f / this.g;
        float f6 = 1.0f / this.h;
        if (this.j == null) {
            bitVector = new BitVector(this.i.length + 1);
            z = false;
        } else {
            bitVector = this.j;
            this.j = null;
            bitVector.clear();
            bitVector.ensureCapacity(this.i.length + 1);
            z = true;
        }
        Vector2 nor = new Vector2(f3 - f, f4 - f2).nor();
        float distanceBetweenPoints = PMath.getDistanceBetweenPoints(f, f2, f3, f4);
        float min = Math.min(f5, f6) * 0.5f;
        Vector2 scl = new Vector2(nor).rotate90(0).scl(min * 0.5f);
        nor.scl(min);
        IntArray intArray = null;
        if (this.debugBatch != null) {
            intArray = new IntArray();
        }
        double d = 0.0d;
        float f7 = f;
        float f8 = f2;
        int i = -1;
        while (d < distanceBetweenPoints + min) {
            float f9 = f7 + scl.x;
            float f10 = f8 + scl.y;
            if (this.debugBatch != null) {
                this.debugBatch.setColor(Color.WHITE);
                this.debugBatch.draw(Game.i.assetManager.getTextureRegion("circle"), f9 - 2.0f, f10 - 2.0f, 4.0f, 4.0f);
            }
            if (f9 >= this.c && f9 <= this.d && f10 >= this.e && f10 <= this.f) {
                short a2 = a(f9);
                short b2 = b(f10);
                int i2 = (b2 * this.f3806a) + a2;
                if (i != i2) {
                    i = i2;
                    if (intArray != null) {
                        intArray.add(a2, b2);
                    }
                    short s = this.m[i2];
                    if (s != -1) {
                        short s2 = this.l[s];
                        for (int i3 = 0; i3 < s2; i3++) {
                            bitVector.unsafeSet(this.l[s + i3 + 1] + 1);
                        }
                    }
                }
            }
            float f11 = f7 - scl.x;
            float f12 = f8 - scl.y;
            if (this.debugBatch != null) {
                this.debugBatch.setColor(Color.WHITE);
                this.debugBatch.draw(Game.i.assetManager.getTextureRegion("circle"), f11 - 2.0f, f12 - 2.0f, 4.0f, 4.0f);
            }
            if (f11 >= this.c && f11 <= this.d && f12 >= this.e && f12 <= this.f) {
                short a3 = a(f11);
                short b3 = b(f12);
                int i4 = (b3 * this.f3806a) + a3;
                if (i != i4) {
                    i = i4;
                    if (intArray != null) {
                        intArray.add(a3, b3);
                    }
                    short s3 = this.m[i4];
                    if (s3 != -1) {
                        short s4 = this.l[s3];
                        for (int i5 = 0; i5 < s4; i5++) {
                            bitVector.unsafeSet(this.l[s3 + i5 + 1] + 1);
                        }
                    }
                }
            }
            d += min;
            f7 += nor.x;
            f8 += nor.y;
        }
        a(bitVector, entryFilter, entryRetriever);
        if (this.debugBatch != null) {
            this.debugBatch.setColor(0.0f, 1.0f, 0.0f, 0.28f);
            for (int i6 = 0; i6 < intArray.size; i6 += 2) {
                this.debugBatch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), (intArray.items[i6] * f5) + this.c, (intArray.items[i6 + 1] * f6) + this.e, f5, f6);
            }
            this.debugBatch.setColor(1.0f, 0.7f, 0.0f, 0.28f);
            int i7 = 0;
            while (true) {
                int nextSetBit = bitVector.nextSetBit(i7 + 1);
                i7 = nextSetBit;
                if (nextSetBit == -1) {
                    break;
                }
                int i8 = i7 - 1;
                float f13 = this.k[i8 * 3];
                float f14 = this.k[(i8 * 3) + 1];
                float f15 = this.k[(i8 * 3) + 2];
                if (entryFilter.test(f13, f14, f15)) {
                    this.debugBatch.draw(Game.i.assetManager.getTextureRegion("circle"), f13 - f15, f14 - f15, f15 * 2.0f, f15 * 2.0f);
                    Game.i.assetManager.getSmallDebugFont().draw(this.debugBatch, new StringBuilder().append(i8).toString(), f13 + (f15 * 0.5f), f14 + (f15 * 0.5f), 1.0f, 1, false);
                }
            }
            this.debugBatch.setColor(Color.WHITE);
        }
        if (z) {
            this.j = bitVector;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/AABB$Factory.class */
    public static final class Factory<T> {

        /* renamed from: b, reason: collision with root package name */
        private int f3809b;
        private final float c;
        private final Class<T> d;

        /* renamed from: a, reason: collision with root package name */
        private final Array<Entry<T>> f3808a = new Array<>(true, 1, Entry.class);
        private final Array<ShortArray> e = new Array<>(true, 1, ShortArray.class);
        private final Array<ShortArray> f = new Array<>(true, 1, ShortArray.class);

        public Factory(Class<T> cls, float f) {
            this.c = f;
            this.d = cls;
        }

        public final void add(T t, float f, float f2, float f3) {
            if (this.f3809b > this.f3808a.size) {
                Entry<T> entry = this.f3808a.items[this.f3808a.size];
                entry.object = t;
                entry.x = f;
                entry.y = f2;
                entry.size = f3;
                this.f3808a.size++;
                return;
            }
            this.f3808a.add(new Entry<>(t, f, f2, f3, (byte) 0));
            this.f3809b++;
        }

        public final void reset() {
            for (int i = 0; i < this.f3808a.size; i++) {
                this.f3808a.items[i].object = null;
            }
            this.f3808a.size = 0;
        }

        public final AABB<T> bake(@Null AABB<T> aabb) {
            AABB<T> aabb2;
            float f = Float.MAX_VALUE;
            float f2 = Float.MAX_VALUE;
            float f3 = -3.4028235E38f;
            float f4 = -3.4028235E38f;
            for (int i = 0; i < this.f3808a.size; i++) {
                Entry<T> entry = this.f3808a.items[i];
                f = Math.min(f, entry.x - entry.size);
                f2 = Math.min(f2, entry.y - entry.size);
                f3 = Math.max(f3, entry.x + entry.size);
                f4 = Math.max(f4, entry.y + entry.size);
            }
            float f5 = f3 - f;
            float f6 = f4 - f2;
            short ceil = (short) MathUtils.ceil(f5 / this.c);
            short ceil2 = (short) MathUtils.ceil(f6 / this.c);
            float f7 = f5 / ceil;
            float f8 = f6 / ceil2;
            float f9 = 1.0f / f7;
            float f10 = 1.0f / f8;
            int i2 = ceil * ceil2;
            int i3 = 0;
            short s = 0;
            while (true) {
                short s2 = s;
                if (s2 >= this.f3808a.size) {
                    break;
                }
                Entry<T> entry2 = this.f3808a.items[s2];
                float f11 = (entry2.x - entry2.size) - f;
                float f12 = (entry2.y - entry2.size) - f2;
                float f13 = (entry2.x + entry2.size) - f;
                float f14 = (entry2.y + entry2.size) - f2;
                int clamp = MathUtils.clamp((int) (f11 * f9), 0, ceil - 1);
                i3 += ((MathUtils.clamp((int) (f14 * f10), 0, ceil2 - 1) - MathUtils.clamp((int) (f12 * f10), 0, ceil2 - 1)) + 1) * ((MathUtils.clamp((int) (f13 * f9), 0, ceil - 1) - clamp) + 1);
                s = (short) (s2 + 1);
            }
            int i4 = i3 + i2;
            if (aabb == null) {
                AABB<T> aabb3 = new AABB<>((byte) 0);
                aabb2 = aabb3;
                ((AABB) aabb3).i = (Object[]) ArrayReflection.newInstance(this.d, this.f3808a.size);
            } else {
                aabb2 = aabb;
            }
            if (this.f3808a.size == 0) {
                Arrays.fill(((AABB) aabb2).i, (Object) null);
                AABB<T> aabb4 = aabb2;
                ((AABB) aabb4).c = ((AABB) aabb4).e = Float.MAX_VALUE;
                AABB<T> aabb5 = aabb2;
                ((AABB) aabb5).d = ((AABB) aabb5).f = -3.4028235E38f;
                AABB<T> aabb6 = aabb2;
                ((AABB) aabb6).f3806a = ((AABB) aabb6).f3807b = (short) 0;
            } else {
                if (((AABB) aabb2).i.length < this.f3808a.size) {
                    ((AABB) aabb2).i = (Object[]) ArrayReflection.newInstance(this.d, this.f3808a.size);
                } else {
                    Arrays.fill(((AABB) aabb2).i, (Object) null);
                }
                if (((AABB) aabb2).k.length < this.f3808a.size * 3) {
                    ((AABB) aabb2).k = new float[this.f3808a.size * 3];
                }
                if (((AABB) aabb2).l.length < i4) {
                    ((AABB) aabb2).l = new short[i4];
                }
                if (((AABB) aabb2).m.length < i2) {
                    ((AABB) aabb2).m = new short[i2];
                }
                ((AABB) aabb2).f3806a = ceil;
                ((AABB) aabb2).f3807b = ceil2;
                ((AABB) aabb2).g = 1.0f / f7;
                ((AABB) aabb2).h = 1.0f / f8;
                ((AABB) aabb2).c = f;
                ((AABB) aabb2).e = f2;
                ((AABB) aabb2).d = f3;
                ((AABB) aabb2).f = f4;
                this.e.clear();
                short s3 = 0;
                while (true) {
                    short s4 = s3;
                    if (s4 >= this.f3808a.size) {
                        break;
                    }
                    Entry<T> entry3 = this.f3808a.items[s4];
                    ((AABB) aabb2).i[s4] = entry3.object;
                    ((AABB) aabb2).k[s4 * 3] = entry3.x;
                    ((AABB) aabb2).k[(s4 * 3) + 1] = entry3.y;
                    ((AABB) aabb2).k[(s4 * 3) + 2] = entry3.size;
                    float f15 = (entry3.x - entry3.size) - f;
                    float f16 = (entry3.y - entry3.size) - f2;
                    float f17 = (entry3.x + entry3.size) - f;
                    float f18 = (entry3.y + entry3.size) - f2;
                    short clamp2 = (short) MathUtils.clamp((int) (f15 * f9), 0, ceil - 1);
                    short clamp3 = (short) MathUtils.clamp((int) (f17 * f9), 0, ceil - 1);
                    short clamp4 = (short) MathUtils.clamp((int) (f16 * f10), 0, ceil2 - 1);
                    short clamp5 = (short) MathUtils.clamp((int) (f18 * f10), 0, ceil2 - 1);
                    short s5 = clamp4;
                    while (true) {
                        short s6 = s5;
                        if (s6 <= clamp5) {
                            short s7 = clamp2;
                            while (true) {
                                short s8 = s7;
                                if (s8 <= clamp3) {
                                    int i5 = (s6 * ceil) + s8;
                                    ShortArray shortArray = this.e.size > i5 ? this.e.get(i5) : null;
                                    ShortArray shortArray2 = shortArray;
                                    if (shortArray == null) {
                                        shortArray2 = this.f.size == 0 ? new ShortArray() : this.f.pop();
                                        if (this.e.size < i5 + 1) {
                                            this.e.setSize(i5 + 1);
                                        }
                                        this.e.set(i5, shortArray2);
                                    }
                                    shortArray2.add(s4);
                                    s7 = (short) (s8 + 1);
                                }
                            }
                            s5 = (short) (s6 + 1);
                        }
                    }
                    s3 = (short) (s4 + 1);
                }
                for (int i6 = 0; i6 < i2; i6++) {
                    ((AABB) aabb2).m[i6] = -1;
                }
                short s9 = 0;
                int i7 = 0;
                while (i7 < i2) {
                    ShortArray shortArray3 = this.e.size > i7 ? this.e.items[i7] : null;
                    ShortArray shortArray4 = shortArray3;
                    if (shortArray3 != null) {
                        short s10 = s9;
                        ((AABB) aabb2).m[i7] = s9;
                        s9 = (short) (s9 + shortArray4.size + 1);
                        ((AABB) aabb2).l[s10] = (short) shortArray4.size;
                        System.arraycopy(shortArray4.items, 0, ((AABB) aabb2).l, s10 + 1, shortArray4.size);
                        shortArray4.clear();
                        this.f.add(shortArray4);
                    }
                    i7++;
                }
            }
            return aabb2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/AABB$Entry.class */
    public static final class Entry<T> {
        public T object;
        public float x;
        public float y;
        public float size;

        /* synthetic */ Entry(Object obj, float f, float f2, float f3, byte b2) {
            this(obj, f, f2, f3);
        }

        private Entry() {
        }

        private Entry(T t, float f, float f2, float f3) {
            this.object = t;
            this.x = f;
            this.y = f2;
            this.size = f3;
        }
    }
}
