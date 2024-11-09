package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/QuadTreeFloat.class */
public class QuadTreeFloat implements Pool.Poolable {
    public static final int VALUE = 0;
    public static final int X = 1;
    public static final int Y = 2;
    public static final int DISTSQR = 3;
    private static final Pool<QuadTreeFloat> pool = new Pool(128, 4096) { // from class: com.badlogic.gdx.utils.QuadTreeFloat.1
        @Override // com.badlogic.gdx.utils.Pool
        protected Object newObject() {
            return new QuadTreeFloat();
        }
    };
    public final int maxValues;
    public final int maxDepth;
    public float x;
    public float y;
    public float width;
    public float height;
    public int depth;

    @Null
    public QuadTreeFloat nw;

    @Null
    public QuadTreeFloat ne;

    @Null
    public QuadTreeFloat sw;

    @Null
    public QuadTreeFloat se;
    public float[] values;
    public int count;

    public QuadTreeFloat() {
        this(16, 8);
    }

    public QuadTreeFloat(int i, int i2) {
        this.maxValues = i * 3;
        this.maxDepth = i2;
        this.values = new float[this.maxValues];
    }

    public void setBounds(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.width = f3;
        this.height = f4;
    }

    public void add(float f, float f2, float f3) {
        int i = this.count;
        if (i == -1) {
            addToChild(f, f2, f3);
            return;
        }
        if (this.depth < this.maxDepth) {
            if (i == this.maxValues) {
                split(f, f2, f3);
                return;
            }
        } else if (i == this.values.length) {
            this.values = Arrays.copyOf(this.values, growValues());
        }
        this.values[i] = f;
        this.values[i + 1] = f2;
        this.values[i + 2] = f3;
        this.count += 3;
    }

    private void split(float f, float f2, float f3) {
        float[] fArr = this.values;
        for (int i = 0; i < this.maxValues; i += 3) {
            addToChild(fArr[i], fArr[i + 1], fArr[i + 2]);
        }
        this.count = -1;
        addToChild(f, f2, f3);
    }

    private void addToChild(float f, float f2, float f3) {
        QuadTreeFloat quadTreeFloat;
        QuadTreeFloat quadTreeFloat2;
        QuadTreeFloat quadTreeFloat3;
        QuadTreeFloat quadTreeFloat4;
        QuadTreeFloat quadTreeFloat5;
        float f4 = this.width / 2.0f;
        float f5 = this.height / 2.0f;
        if (f2 < this.x + f4) {
            if (f3 < this.y + f5) {
                if (this.sw != null) {
                    quadTreeFloat5 = this.sw;
                } else {
                    QuadTreeFloat obtainChild = obtainChild(this.x, this.y, f4, f5, this.depth + 1);
                    quadTreeFloat5 = obtainChild;
                    this.sw = obtainChild;
                }
                quadTreeFloat2 = quadTreeFloat5;
            } else {
                if (this.nw != null) {
                    quadTreeFloat4 = this.nw;
                } else {
                    QuadTreeFloat obtainChild2 = obtainChild(this.x, this.y + f5, f4, f5, this.depth + 1);
                    quadTreeFloat4 = obtainChild2;
                    this.nw = obtainChild2;
                }
                quadTreeFloat2 = quadTreeFloat4;
            }
        } else if (f3 < this.y + f5) {
            if (this.se != null) {
                quadTreeFloat3 = this.se;
            } else {
                QuadTreeFloat obtainChild3 = obtainChild(this.x + f4, this.y, f4, f5, this.depth + 1);
                quadTreeFloat3 = obtainChild3;
                this.se = obtainChild3;
            }
            quadTreeFloat2 = quadTreeFloat3;
        } else {
            if (this.ne != null) {
                quadTreeFloat = this.ne;
            } else {
                QuadTreeFloat obtainChild4 = obtainChild(this.x + f4, this.y + f5, f4, f5, this.depth + 1);
                quadTreeFloat = obtainChild4;
                this.ne = obtainChild4;
            }
            quadTreeFloat2 = quadTreeFloat;
        }
        quadTreeFloat2.add(f, f2, f3);
    }

    private QuadTreeFloat obtainChild(float f, float f2, float f3, float f4, int i) {
        QuadTreeFloat obtain = pool.obtain();
        obtain.x = f;
        obtain.y = f2;
        obtain.width = f3;
        obtain.height = f4;
        obtain.depth = i;
        return obtain;
    }

    protected int growValues() {
        return this.count + 30;
    }

    public void query(float f, float f2, float f3, FloatArray floatArray) {
        query(f, f2, f3 * f3, f - f3, f2 - f3, f3 * 2.0f, floatArray);
    }

    private void query(float f, float f2, float f3, float f4, float f5, float f6, FloatArray floatArray) {
        if (this.x >= f4 + f6 || this.x + this.width <= f4 || this.y >= f5 + f6 || this.y + this.height <= f5) {
            return;
        }
        int i = this.count;
        if (i != -1) {
            float[] fArr = this.values;
            for (int i2 = 1; i2 < i; i2 += 3) {
                float f7 = fArr[i2];
                float f8 = fArr[i2 + 1];
                float f9 = f7 - f;
                float f10 = f8 - f2;
                float f11 = (f9 * f9) + (f10 * f10);
                if (f11 <= f3) {
                    floatArray.add(fArr[i2 - 1]);
                    floatArray.add(f7);
                    floatArray.add(f8);
                    floatArray.add(f11);
                }
            }
            return;
        }
        if (this.nw != null) {
            this.nw.query(f, f2, f3, f4, f5, f6, floatArray);
        }
        if (this.sw != null) {
            this.sw.query(f, f2, f3, f4, f5, f6, floatArray);
        }
        if (this.ne != null) {
            this.ne.query(f, f2, f3, f4, f5, f6, floatArray);
        }
        if (this.se != null) {
            this.se.query(f, f2, f3, f4, f5, f6, floatArray);
        }
    }

    public void query(Rectangle rectangle, FloatArray floatArray) {
        if (this.x >= rectangle.x + rectangle.width || this.x + this.width <= rectangle.x || this.y >= rectangle.y + rectangle.height || this.y + this.height <= rectangle.y) {
            return;
        }
        int i = this.count;
        if (i != -1) {
            float[] fArr = this.values;
            for (int i2 = 1; i2 < i; i2 += 3) {
                float f = fArr[i2];
                float f2 = fArr[i2 + 1];
                if (rectangle.contains(f, f2)) {
                    floatArray.add(fArr[i2 - 1]);
                    floatArray.add(f);
                    floatArray.add(f2);
                }
            }
            return;
        }
        if (this.nw != null) {
            this.nw.query(rectangle, floatArray);
        }
        if (this.sw != null) {
            this.sw.query(rectangle, floatArray);
        }
        if (this.ne != null) {
            this.ne.query(rectangle, floatArray);
        }
        if (this.se != null) {
            this.se.query(rectangle, floatArray);
        }
    }

    public boolean nearest(float f, float f2, FloatArray floatArray) {
        floatArray.clear();
        floatArray.add(0.0f);
        floatArray.add(0.0f);
        floatArray.add(0.0f);
        floatArray.add(Float.POSITIVE_INFINITY);
        findNearestInternal(f, f2, floatArray);
        float first = floatArray.first();
        float f3 = floatArray.get(1);
        float f4 = floatArray.get(2);
        float f5 = floatArray.get(3);
        float f6 = f5;
        boolean z = f5 != Float.POSITIVE_INFINITY;
        boolean z2 = z;
        if (!z) {
            float max = Math.max(this.width, this.height);
            f6 = max * max;
        }
        floatArray.clear();
        query(f, f2, (float) Math.sqrt(f6), floatArray);
        int i = floatArray.size;
        for (int i2 = 3; i2 < i; i2 += 4) {
            float f7 = floatArray.get(i2);
            if (f7 < f6) {
                f6 = f7;
                first = floatArray.get(i2 - 3);
                f3 = floatArray.get(i2 - 2);
                f4 = floatArray.get(i2 - 1);
            }
        }
        if (!z2 && floatArray.isEmpty()) {
            return false;
        }
        floatArray.clear();
        floatArray.add(first);
        floatArray.add(f3);
        floatArray.add(f4);
        floatArray.add(f6);
        return true;
    }

    private void findNearestInternal(float f, float f2, FloatArray floatArray) {
        if (this.x >= f || this.x + this.width <= f || this.y >= f2 || this.y + this.height <= f2) {
            return;
        }
        int i = this.count;
        if (i != -1) {
            float first = floatArray.first();
            float f3 = floatArray.get(1);
            float f4 = floatArray.get(2);
            float f5 = floatArray.get(3);
            float[] fArr = this.values;
            for (int i2 = 1; i2 < i; i2 += 3) {
                float f6 = fArr[i2];
                float f7 = fArr[i2 + 1];
                float f8 = f6 - f;
                float f9 = f7 - f2;
                float f10 = (f8 * f8) + (f9 * f9);
                if (f10 < f5) {
                    f5 = f10;
                    first = fArr[i2 - 1];
                    f3 = f6;
                    f4 = f7;
                }
            }
            floatArray.set(0, first);
            floatArray.set(1, f3);
            floatArray.set(2, f4);
            floatArray.set(3, f5);
            return;
        }
        if (this.nw != null) {
            this.nw.findNearestInternal(f, f2, floatArray);
        }
        if (this.sw != null) {
            this.sw.findNearestInternal(f, f2, floatArray);
        }
        if (this.ne != null) {
            this.ne.findNearestInternal(f, f2, floatArray);
        }
        if (this.se != null) {
            this.se.findNearestInternal(f, f2, floatArray);
        }
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        if (this.count == -1) {
            if (this.nw != null) {
                pool.free(this.nw);
                this.nw = null;
            }
            if (this.sw != null) {
                pool.free(this.sw);
                this.sw = null;
            }
            if (this.ne != null) {
                pool.free(this.ne);
                this.ne = null;
            }
            if (this.se != null) {
                pool.free(this.se);
                this.se = null;
            }
        }
        this.count = 0;
        if (this.values.length > this.maxValues) {
            this.values = new float[this.maxValues];
        }
    }
}
