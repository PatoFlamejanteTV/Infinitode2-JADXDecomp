package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Bresenham2.class */
public class Bresenham2 {
    private final Array<GridPoint2> points = new Array<>();
    private final Pool<GridPoint2> pool = new Pool<GridPoint2>() { // from class: com.badlogic.gdx.math.Bresenham2.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.badlogic.gdx.utils.Pool
        public GridPoint2 newObject() {
            return new GridPoint2();
        }
    };

    public Array<GridPoint2> line(GridPoint2 gridPoint2, GridPoint2 gridPoint22) {
        return line(gridPoint2.x, gridPoint2.y, gridPoint22.x, gridPoint22.y);
    }

    public Array<GridPoint2> line(int i, int i2, int i3, int i4) {
        this.pool.freeAll(this.points);
        this.points.clear();
        return line(i, i2, i3, i4, this.pool, this.points);
    }

    public Array<GridPoint2> line(int i, int i2, int i3, int i4, Pool<GridPoint2> pool, Array<GridPoint2> array) {
        int i5;
        int i6;
        int i7 = i3 - i;
        int i8 = i4 - i2;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        if (i7 < 0) {
            i9 = -1;
            i11 = -1;
        } else if (i7 > 0) {
            i9 = 1;
            i11 = 1;
        }
        if (i8 < 0) {
            i10 = -1;
        } else if (i8 > 0) {
            i10 = 1;
        }
        int abs = Math.abs(i7);
        int abs2 = Math.abs(i8);
        if (abs < abs2) {
            abs = Math.abs(i8);
            abs2 = Math.abs(i7);
            if (i8 < 0) {
                i12 = -1;
            } else if (i8 > 0) {
                i12 = 1;
            }
            i11 = 0;
        }
        int i13 = abs2 << 1;
        int i14 = abs << 1;
        int i15 = 0;
        for (int i16 = 0; i16 <= abs; i16++) {
            GridPoint2 obtain = pool.obtain();
            obtain.set(i, i2);
            array.add(obtain);
            int i17 = i15 + i13;
            i15 = i17;
            if (i17 > abs) {
                i15 -= i14;
                i += i9;
                i5 = i2;
                i6 = i10;
            } else {
                i += i11;
                i5 = i2;
                i6 = i12;
            }
            i2 = i5 + i6;
        }
        return array;
    }
}
