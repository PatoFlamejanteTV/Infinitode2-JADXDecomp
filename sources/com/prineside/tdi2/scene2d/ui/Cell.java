package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.ui.Value;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Cell.class */
public class Cell<T extends Actor> implements Pool.Poolable {
    private static final Float J = Float.valueOf(0.0f);
    private static final Float K = Float.valueOf(1.0f);
    private static final Integer L = 0;
    private static final Integer M = 1;
    private static final Integer N = 1;
    private static final Integer O = 2;
    private static final Integer P = 4;
    private static final Integer Q = 8;
    private static final Integer R = 16;
    private static Files S;
    private static Cell T;

    /* renamed from: a, reason: collision with root package name */
    Value f2656a;

    /* renamed from: b, reason: collision with root package name */
    Value f2657b;
    Value c;
    Value d;
    Value e;
    Value f;
    Value g;
    Value h;
    Value i;
    Value j;
    Value k;
    Value l;
    Value m;
    Value n;
    Float o;
    Float p;
    Integer q;
    Integer r;
    Integer s;
    Integer t;
    Boolean u;
    Boolean v;

    @Null
    Actor w;
    float x;
    float y;
    float z;
    float A;
    private Table U;
    boolean B;
    int C;
    int D;
    int E = -1;
    float F;
    float G;
    float H;
    float I;

    public Cell() {
        Cell defaults = defaults();
        if (defaults != null) {
            a(defaults);
        }
    }

    public void setTable(Table table) {
        this.U = table;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <A extends Actor> Cell<A> setActor(@Null A a2) {
        if (this.w != a2) {
            if (this.w != null && this.w.getParent() == this.U) {
                this.w.remove();
            }
            this.w = a2;
            if (a2 != null) {
                this.U.addActor(a2);
            }
        }
        return this;
    }

    public Cell<T> clearActor() {
        setActor(null);
        return this;
    }

    @Null
    public T getActor() {
        return (T) this.w;
    }

    public boolean hasActor() {
        return this.w != null;
    }

    public Cell<T> size(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.f2656a = value;
        this.f2657b = value;
        this.c = value;
        this.d = value;
        this.e = value;
        this.f = value;
        return this;
    }

    public Cell<T> size(Value value, Value value2) {
        if (value == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if (value2 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.f2656a = value;
        this.f2657b = value2;
        this.c = value;
        this.d = value2;
        this.e = value;
        this.f = value2;
        return this;
    }

    public Cell<T> size(float f) {
        size(Value.Fixed.valueOf(f));
        return this;
    }

    public Cell<T> size(float f, float f2) {
        size(Value.Fixed.valueOf(f), Value.Fixed.valueOf(f2));
        return this;
    }

    public Cell<T> width(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        this.f2656a = value;
        this.c = value;
        this.e = value;
        return this;
    }

    public Cell<T> width(float f) {
        width(Value.Fixed.valueOf(f));
        return this;
    }

    public Cell<T> height(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.f2657b = value;
        this.d = value;
        this.f = value;
        return this;
    }

    public Cell<T> height(float f) {
        height(Value.Fixed.valueOf(f));
        return this;
    }

    public Cell<T> minSize(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.f2656a = value;
        this.f2657b = value;
        return this;
    }

    public Cell<T> minSize(Value value, Value value2) {
        if (value == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if (value2 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.f2656a = value;
        this.f2657b = value2;
        return this;
    }

    public Cell<T> minWidth(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("minWidth cannot be null.");
        }
        this.f2656a = value;
        return this;
    }

    public Cell<T> minHeight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("minHeight cannot be null.");
        }
        this.f2657b = value;
        return this;
    }

    public Cell<T> minSize(float f) {
        minSize(Value.Fixed.valueOf(f));
        return this;
    }

    public Cell<T> minSize(float f, float f2) {
        minSize(Value.Fixed.valueOf(f), Value.Fixed.valueOf(f2));
        return this;
    }

    public Cell<T> minWidth(float f) {
        this.f2656a = Value.Fixed.valueOf(f);
        return this;
    }

    public Cell<T> minHeight(float f) {
        this.f2657b = Value.Fixed.valueOf(f);
        return this;
    }

    public Cell<T> prefSize(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.c = value;
        this.d = value;
        return this;
    }

    public Cell<T> prefSize(Value value, Value value2) {
        if (value == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if (value2 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.c = value;
        this.d = value2;
        return this;
    }

    public Cell<T> prefWidth(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("prefWidth cannot be null.");
        }
        this.c = value;
        return this;
    }

    public Cell<T> prefHeight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("prefHeight cannot be null.");
        }
        this.d = value;
        return this;
    }

    public Cell<T> prefSize(float f, float f2) {
        prefSize(Value.Fixed.valueOf(f), Value.Fixed.valueOf(f2));
        return this;
    }

    public Cell<T> prefSize(float f) {
        prefSize(Value.Fixed.valueOf(f));
        return this;
    }

    public Cell<T> prefWidth(float f) {
        this.c = Value.Fixed.valueOf(f);
        return this;
    }

    public Cell<T> prefHeight(float f) {
        this.d = Value.Fixed.valueOf(f);
        return this;
    }

    public Cell<T> maxSize(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.e = value;
        this.f = value;
        return this;
    }

    public Cell<T> maxSize(Value value, Value value2) {
        if (value == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if (value2 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.e = value;
        this.f = value2;
        return this;
    }

    public Cell<T> maxWidth(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("maxWidth cannot be null.");
        }
        this.e = value;
        return this;
    }

    public Cell<T> maxHeight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("maxHeight cannot be null.");
        }
        this.f = value;
        return this;
    }

    public Cell<T> maxSize(float f) {
        maxSize(Value.Fixed.valueOf(f));
        return this;
    }

    public Cell<T> maxSize(float f, float f2) {
        maxSize(Value.Fixed.valueOf(f), Value.Fixed.valueOf(f2));
        return this;
    }

    public Cell<T> maxWidth(float f) {
        this.e = Value.Fixed.valueOf(f);
        return this;
    }

    public Cell<T> maxHeight(float f) {
        this.f = Value.Fixed.valueOf(f);
        return this;
    }

    public Cell<T> space(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("space cannot be null.");
        }
        this.g = value;
        this.h = value;
        this.i = value;
        this.j = value;
        return this;
    }

    public Cell<T> space(Value value, Value value2, Value value3, Value value4) {
        if (value == null) {
            throw new IllegalArgumentException("top cannot be null.");
        }
        if (value2 == null) {
            throw new IllegalArgumentException("left cannot be null.");
        }
        if (value3 == null) {
            throw new IllegalArgumentException("bottom cannot be null.");
        }
        if (value4 == null) {
            throw new IllegalArgumentException("right cannot be null.");
        }
        this.g = value;
        this.h = value2;
        this.i = value3;
        this.j = value4;
        return this;
    }

    public Cell<T> spaceTop(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("spaceTop cannot be null.");
        }
        this.g = value;
        return this;
    }

    public Cell<T> spaceLeft(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("spaceLeft cannot be null.");
        }
        this.h = value;
        return this;
    }

    public Cell<T> spaceBottom(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("spaceBottom cannot be null.");
        }
        this.i = value;
        return this;
    }

    public Cell<T> spaceRight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("spaceRight cannot be null.");
        }
        this.j = value;
        return this;
    }

    public Cell<T> space(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("space cannot be < 0: " + f);
        }
        space(Value.Fixed.valueOf(f));
        return this;
    }

    public Cell<T> space(float f, float f2, float f3, float f4) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("top cannot be < 0: " + f);
        }
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("left cannot be < 0: " + f2);
        }
        if (f3 < 0.0f) {
            throw new IllegalArgumentException("bottom cannot be < 0: " + f3);
        }
        if (f4 < 0.0f) {
            throw new IllegalArgumentException("right cannot be < 0: " + f4);
        }
        space(Value.Fixed.valueOf(f), Value.Fixed.valueOf(f2), Value.Fixed.valueOf(f3), Value.Fixed.valueOf(f4));
        return this;
    }

    public Cell<T> spaceTop(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("spaceTop cannot be < 0: " + f);
        }
        this.g = Value.Fixed.valueOf(f);
        return this;
    }

    public Cell<T> spaceLeft(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("spaceLeft cannot be < 0: " + f);
        }
        this.h = Value.Fixed.valueOf(f);
        return this;
    }

    public Cell<T> spaceBottom(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("spaceBottom cannot be < 0: " + f);
        }
        this.i = Value.Fixed.valueOf(f);
        return this;
    }

    public Cell<T> spaceRight(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("spaceRight cannot be < 0: " + f);
        }
        this.j = Value.Fixed.valueOf(f);
        return this;
    }

    public Cell<T> pad(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("pad cannot be null.");
        }
        this.k = value;
        this.l = value;
        this.m = value;
        this.n = value;
        return this;
    }

    public Cell<T> pad(Value value, Value value2, Value value3, Value value4) {
        if (value == null) {
            throw new IllegalArgumentException("top cannot be null.");
        }
        if (value2 == null) {
            throw new IllegalArgumentException("left cannot be null.");
        }
        if (value3 == null) {
            throw new IllegalArgumentException("bottom cannot be null.");
        }
        if (value4 == null) {
            throw new IllegalArgumentException("right cannot be null.");
        }
        this.k = value;
        this.l = value2;
        this.m = value3;
        this.n = value4;
        return this;
    }

    public Cell<T> padTop(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padTop cannot be null.");
        }
        this.k = value;
        return this;
    }

    public Cell<T> padLeft(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padLeft cannot be null.");
        }
        this.l = value;
        return this;
    }

    public Cell<T> padBottom(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padBottom cannot be null.");
        }
        this.m = value;
        return this;
    }

    public Cell<T> padRight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padRight cannot be null.");
        }
        this.n = value;
        return this;
    }

    public Cell<T> pad(float f) {
        pad(Value.Fixed.valueOf(f));
        return this;
    }

    public Cell<T> pad(float f, float f2, float f3, float f4) {
        pad(Value.Fixed.valueOf(f), Value.Fixed.valueOf(f2), Value.Fixed.valueOf(f3), Value.Fixed.valueOf(f4));
        return this;
    }

    public Cell<T> padTop(float f) {
        this.k = Value.Fixed.valueOf(f);
        return this;
    }

    public Cell<T> padLeft(float f) {
        this.l = Value.Fixed.valueOf(f);
        return this;
    }

    public Cell<T> padBottom(float f) {
        this.m = Value.Fixed.valueOf(f);
        return this;
    }

    public Cell<T> padRight(float f) {
        this.n = Value.Fixed.valueOf(f);
        return this;
    }

    public Cell<T> fill() {
        this.o = K;
        this.p = K;
        return this;
    }

    public Cell<T> fillX() {
        this.o = K;
        return this;
    }

    public Cell<T> fillY() {
        this.p = K;
        return this;
    }

    public Cell<T> fill(float f, float f2) {
        this.o = Float.valueOf(f);
        this.p = Float.valueOf(f2);
        return this;
    }

    public Cell<T> fill(boolean z, boolean z2) {
        this.o = z ? K : J;
        this.p = z2 ? K : J;
        return this;
    }

    public Cell<T> fill(boolean z) {
        this.o = z ? K : J;
        this.p = z ? K : J;
        return this;
    }

    public Cell<T> align(int i) {
        this.q = Integer.valueOf(i);
        return this;
    }

    public Cell<T> center() {
        this.q = N;
        return this;
    }

    public Cell<T> top() {
        if (this.q == null) {
            this.q = O;
        } else {
            this.q = Integer.valueOf((this.q.intValue() | 2) & (-5));
        }
        return this;
    }

    public Cell<T> left() {
        if (this.q == null) {
            this.q = Q;
        } else {
            this.q = Integer.valueOf((this.q.intValue() | 8) & (-17));
        }
        return this;
    }

    public Cell<T> bottom() {
        if (this.q == null) {
            this.q = P;
        } else {
            this.q = Integer.valueOf((this.q.intValue() | 4) & (-3));
        }
        return this;
    }

    public Cell<T> right() {
        if (this.q == null) {
            this.q = R;
        } else {
            this.q = Integer.valueOf((this.q.intValue() | 16) & (-9));
        }
        return this;
    }

    public Cell<T> grow() {
        this.r = M;
        this.s = M;
        this.o = K;
        this.p = K;
        return this;
    }

    public Cell<T> growX() {
        this.r = M;
        this.o = K;
        return this;
    }

    public Cell<T> growY() {
        this.s = M;
        this.p = K;
        return this;
    }

    public Cell<T> expand() {
        this.r = M;
        this.s = M;
        return this;
    }

    public Cell<T> expandX() {
        this.r = M;
        return this;
    }

    public Cell<T> expandY() {
        this.s = M;
        return this;
    }

    public Cell<T> expand(int i, int i2) {
        this.r = Integer.valueOf(i);
        this.s = Integer.valueOf(i2);
        return this;
    }

    public Cell<T> expand(boolean z, boolean z2) {
        this.r = z ? M : L;
        this.s = z2 ? M : L;
        return this;
    }

    public Cell<T> colspan(int i) {
        this.t = Integer.valueOf(i);
        return this;
    }

    public Cell<T> uniform() {
        this.u = Boolean.TRUE;
        this.v = Boolean.TRUE;
        return this;
    }

    public Cell<T> uniformX() {
        this.u = Boolean.TRUE;
        return this;
    }

    public Cell<T> uniformY() {
        this.v = Boolean.TRUE;
        return this;
    }

    public Cell<T> uniform(boolean z) {
        this.u = Boolean.valueOf(z);
        this.v = Boolean.valueOf(z);
        return this;
    }

    public Cell<T> uniform(boolean z, boolean z2) {
        this.u = Boolean.valueOf(z);
        this.v = Boolean.valueOf(z2);
        return this;
    }

    public void setActorBounds(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.z = f3;
        this.A = f4;
    }

    public float getActorX() {
        return this.x;
    }

    public void setActorX(float f) {
        this.x = f;
    }

    public float getActorY() {
        return this.y;
    }

    public void setActorY(float f) {
        this.y = f;
    }

    public float getActorWidth() {
        return this.z;
    }

    public void setActorWidth(float f) {
        this.z = f;
    }

    public float getActorHeight() {
        return this.A;
    }

    public void setActorHeight(float f) {
        this.A = f;
    }

    public int getColumn() {
        return this.C;
    }

    public int getRow() {
        return this.D;
    }

    @Null
    public Value getMinWidthValue() {
        return this.f2656a;
    }

    public float getMinWidth() {
        return this.f2656a.get(this.w);
    }

    @Null
    public Value getMinHeightValue() {
        return this.f2657b;
    }

    public float getMinHeight() {
        return this.f2657b.get(this.w);
    }

    @Null
    public Value getPrefWidthValue() {
        return this.c;
    }

    public float getPrefWidth() {
        return this.c.get(this.w);
    }

    @Null
    public Value getPrefHeightValue() {
        return this.d;
    }

    public float getPrefHeight() {
        return this.d.get(this.w);
    }

    @Null
    public Value getMaxWidthValue() {
        return this.e;
    }

    public float getMaxWidth() {
        return this.e.get(this.w);
    }

    @Null
    public Value getMaxHeightValue() {
        return this.f;
    }

    public float getMaxHeight() {
        return this.f.get(this.w);
    }

    @Null
    public Value getSpaceTopValue() {
        return this.g;
    }

    public float getSpaceTop() {
        return this.g.get(this.w);
    }

    @Null
    public Value getSpaceLeftValue() {
        return this.h;
    }

    public float getSpaceLeft() {
        return this.h.get(this.w);
    }

    @Null
    public Value getSpaceBottomValue() {
        return this.i;
    }

    public float getSpaceBottom() {
        return this.i.get(this.w);
    }

    @Null
    public Value getSpaceRightValue() {
        return this.j;
    }

    public float getSpaceRight() {
        return this.j.get(this.w);
    }

    @Null
    public Value getPadTopValue() {
        return this.k;
    }

    public float getPadTop() {
        return this.k.get(this.w);
    }

    @Null
    public Value getPadLeftValue() {
        return this.l;
    }

    public float getPadLeft() {
        return this.l.get(this.w);
    }

    @Null
    public Value getPadBottomValue() {
        return this.m;
    }

    public float getPadBottom() {
        return this.m.get(this.w);
    }

    @Null
    public Value getPadRightValue() {
        return this.n;
    }

    public float getPadRight() {
        return this.n.get(this.w);
    }

    public float getPadX() {
        return this.l.get(this.w) + this.n.get(this.w);
    }

    public float getPadY() {
        return this.k.get(this.w) + this.m.get(this.w);
    }

    @Null
    public Float getFillX() {
        return this.o;
    }

    @Null
    public Float getFillY() {
        return this.p;
    }

    @Null
    public Integer getAlign() {
        return this.q;
    }

    @Null
    public Integer getExpandX() {
        return this.r;
    }

    @Null
    public Integer getExpandY() {
        return this.s;
    }

    @Null
    public Integer getColspan() {
        return this.t;
    }

    @Null
    public Boolean getUniformX() {
        return this.u;
    }

    @Null
    public Boolean getUniformY() {
        return this.v;
    }

    public boolean isEndRow() {
        return this.B;
    }

    public float getComputedPadTop() {
        return this.F;
    }

    public float getComputedPadLeft() {
        return this.G;
    }

    public float getComputedPadBottom() {
        return this.H;
    }

    public float getComputedPadRight() {
        return this.I;
    }

    public void row() {
        this.U.row();
    }

    public Table getTable() {
        return this.U;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        this.f2656a = null;
        this.f2657b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.w = null;
        this.U = null;
        this.B = false;
        this.E = -1;
        a(defaults());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(Cell cell) {
        this.f2656a = cell.f2656a;
        this.f2657b = cell.f2657b;
        this.c = cell.c;
        this.d = cell.d;
        this.e = cell.e;
        this.f = cell.f;
        this.g = cell.g;
        this.h = cell.h;
        this.i = cell.i;
        this.j = cell.j;
        this.k = cell.k;
        this.l = cell.l;
        this.m = cell.m;
        this.n = cell.n;
        this.o = cell.o;
        this.p = cell.p;
        this.q = cell.q;
        this.r = cell.r;
        this.s = cell.s;
        this.t = cell.t;
        this.u = cell.u;
        this.v = cell.v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(@Null Cell cell) {
        if (cell == null) {
            return;
        }
        if (cell.f2656a != null) {
            this.f2656a = cell.f2656a;
        }
        if (cell.f2657b != null) {
            this.f2657b = cell.f2657b;
        }
        if (cell.c != null) {
            this.c = cell.c;
        }
        if (cell.d != null) {
            this.d = cell.d;
        }
        if (cell.e != null) {
            this.e = cell.e;
        }
        if (cell.f != null) {
            this.f = cell.f;
        }
        if (cell.g != null) {
            this.g = cell.g;
        }
        if (cell.h != null) {
            this.h = cell.h;
        }
        if (cell.i != null) {
            this.i = cell.i;
        }
        if (cell.j != null) {
            this.j = cell.j;
        }
        if (cell.k != null) {
            this.k = cell.k;
        }
        if (cell.l != null) {
            this.l = cell.l;
        }
        if (cell.m != null) {
            this.m = cell.m;
        }
        if (cell.n != null) {
            this.n = cell.n;
        }
        if (cell.o != null) {
            this.o = cell.o;
        }
        if (cell.p != null) {
            this.p = cell.p;
        }
        if (cell.q != null) {
            this.q = cell.q;
        }
        if (cell.r != null) {
            this.r = cell.r;
        }
        if (cell.s != null) {
            this.s = cell.s;
        }
        if (cell.t != null) {
            this.t = cell.t;
        }
        if (cell.u != null) {
            this.u = cell.u;
        }
        if (cell.v != null) {
            this.v = cell.v;
        }
    }

    public String toString() {
        return this.w != null ? this.w.toString() : super.toString();
    }

    public static Cell defaults() {
        if (S == null || S != Gdx.files) {
            S = Gdx.files;
            Cell cell = new Cell();
            T = cell;
            cell.f2656a = Value.minWidth;
            T.f2657b = Value.minHeight;
            T.c = Value.prefWidth;
            T.d = Value.prefHeight;
            T.e = Value.maxWidth;
            T.f = Value.maxHeight;
            T.g = Value.zero;
            T.h = Value.zero;
            T.i = Value.zero;
            T.j = Value.zero;
            T.k = Value.zero;
            T.l = Value.zero;
            T.m = Value.zero;
            T.n = Value.zero;
            T.o = J;
            T.p = J;
            T.q = N;
            T.r = L;
            T.s = L;
            T.t = M;
            T.u = null;
            T.v = null;
        }
        return T;
    }
}
