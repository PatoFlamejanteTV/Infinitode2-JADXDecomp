package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Value;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.Layout;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Table.class */
public class Table extends WidgetGroup {
    private static float[] l;
    private static float[] n;
    private int o;
    private int p;
    private boolean q;
    private Cell u;
    private float[] w;
    private float[] x;
    private float[] y;
    private float[] z;
    private float A;
    private float B;
    private float C;
    private float D;
    private float[] E;
    private float[] F;
    private float[] G;
    private float[] H;
    private Array<DebugRect> O;

    @Null
    Drawable m;
    private boolean P;
    public static Color debugTableColor = new Color(0.0f, 0.0f, 1.0f, 1.0f);
    public static Color debugCellColor = new Color(1.0f, 0.0f, 0.0f, 1.0f);
    public static Color debugActorColor = new Color(0.0f, 1.0f, 0.0f, 1.0f);
    private static Pool<Cell> k = new Pool<Cell>() { // from class: com.prineside.tdi2.scene2d.ui.Table.1
        @Override // com.badlogic.gdx.utils.Pool
        protected /* synthetic */ Cell newObject() {
            return a();
        }

        private static Cell a() {
            return new Cell();
        }
    };
    public static Value backgroundTop = new Value() { // from class: com.prineside.tdi2.scene2d.ui.Table.2
        @Override // com.prineside.tdi2.scene2d.ui.Value
        public float get(@Null Actor actor) {
            Drawable drawable = ((Table) actor).m;
            if (drawable == null) {
                return 0.0f;
            }
            return drawable.getTopHeight();
        }
    };
    public static Value backgroundLeft = new Value() { // from class: com.prineside.tdi2.scene2d.ui.Table.3
        @Override // com.prineside.tdi2.scene2d.ui.Value
        public float get(@Null Actor actor) {
            Drawable drawable = ((Table) actor).m;
            if (drawable == null) {
                return 0.0f;
            }
            return drawable.getLeftWidth();
        }
    };
    public static Value backgroundBottom = new Value() { // from class: com.prineside.tdi2.scene2d.ui.Table.4
        @Override // com.prineside.tdi2.scene2d.ui.Value
        public float get(@Null Actor actor) {
            Drawable drawable = ((Table) actor).m;
            if (drawable == null) {
                return 0.0f;
            }
            return drawable.getBottomHeight();
        }
    };
    public static Value backgroundRight = new Value() { // from class: com.prineside.tdi2.scene2d.ui.Table.5
        @Override // com.prineside.tdi2.scene2d.ui.Value
        public float get(@Null Actor actor) {
            Drawable drawable = ((Table) actor).m;
            if (drawable == null) {
                return 0.0f;
            }
            return drawable.getRightWidth();
        }
    };
    private final Array<Cell> r = new Array<>(4);
    private final Array<Cell> t = new Array<>(2);
    private boolean v = true;
    private Value I = backgroundTop;
    private Value J = backgroundLeft;
    private Value K = backgroundBottom;
    private Value L = backgroundRight;
    private int M = 1;
    private Debug N = Debug.none;
    private boolean Q = true;
    private final Cell s = d();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Table$Debug.class */
    public enum Debug {
        none,
        all,
        table,
        cell,
        actor
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Table$DebugRect.class */
    public static class DebugRect extends Rectangle {

        /* renamed from: a, reason: collision with root package name */
        static Pool<DebugRect> f2675a = Pools.get(DebugRect.class);

        /* renamed from: b, reason: collision with root package name */
        Color f2676b;
    }

    public Table() {
        setTransform(false);
        setTouchable(Touchable.childrenOnly);
    }

    private Cell d() {
        Cell obtain = k.obtain();
        obtain.setTable(this);
        return obtain;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        validate();
        if (isTransform()) {
            a(batch, b());
            a(batch, f, 0.0f, 0.0f);
            if (this.P) {
                batch.flush();
                float f2 = this.J.get(this);
                float f3 = this.K.get(this);
                if (clipBegin(f2, f3, (getWidth() - f2) - this.L.get(this), (getHeight() - f3) - this.I.get(this))) {
                    a(batch, f);
                    batch.flush();
                    clipEnd();
                }
            } else {
                a(batch, f);
            }
            a(batch);
            return;
        }
        a(batch, f, getX(), getY());
        super.draw(batch, f);
    }

    private void a(Batch batch, float f, float f2, float f3) {
        if (this.m == null) {
            return;
        }
        Color color = getColor();
        batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
        this.m.draw(batch, f2, f3, getWidth(), getHeight());
    }

    public void setBackground(@Null Drawable drawable) {
        if (this.m == drawable) {
            return;
        }
        float padTop = getPadTop();
        float padLeft = getPadLeft();
        float padBottom = getPadBottom();
        float padRight = getPadRight();
        this.m = drawable;
        float padTop2 = getPadTop();
        float padLeft2 = getPadLeft();
        float padBottom2 = getPadBottom();
        float padRight2 = getPadRight();
        if (padTop + padBottom != padTop2 + padBottom2 || padLeft + padRight != padLeft2 + padRight2) {
            invalidateHierarchy();
        } else if (padTop != padTop2 || padLeft != padLeft2 || padBottom != padBottom2 || padRight != padRight2) {
            invalidate();
        }
    }

    public Table background(@Null Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    @Null
    public Drawable getBackground() {
        return this.m;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    @Null
    public Actor hit(float f, float f2, boolean z) {
        if (!this.P || (!(z && getTouchable() == Touchable.disabled) && f >= 0.0f && f < getWidth() && f2 >= 0.0f && f2 < getHeight())) {
            return super.hit(f, f2, z);
        }
        return null;
    }

    public Table clip() {
        setClip(true);
        return this;
    }

    public Table clip(boolean z) {
        setClip(z);
        return this;
    }

    public void setClip(boolean z) {
        this.P = z;
        setTransform(z);
        invalidate();
    }

    public boolean getClip() {
        return this.P;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public void invalidate() {
        this.v = true;
        super.invalidate();
    }

    public <T extends Actor> Cell<T> add(@Null T t) {
        Cell<T> d = d();
        d.w = t;
        if (this.q) {
            this.q = false;
            this.p--;
            this.r.peek().B = false;
        }
        int i = this.r.size;
        if (i > 0) {
            Cell peek = this.r.peek();
            if (!peek.B) {
                d.C = peek.C + peek.t.intValue();
                d.D = peek.D;
            } else {
                d.C = 0;
                d.D = peek.D + 1;
            }
            if (d.D > 0) {
                Cell[] cellArr = this.r.items;
                int i2 = i - 1;
                loop0: while (true) {
                    if (i2 < 0) {
                        break;
                    }
                    Cell cell = cellArr[i2];
                    int i3 = cell.C;
                    int intValue = i3 + cell.t.intValue();
                    for (int i4 = i3; i4 < intValue; i4++) {
                        if (i4 == d.C) {
                            d.E = i2;
                            break loop0;
                        }
                    }
                    i2--;
                }
            }
        } else {
            d.C = 0;
            d.D = 0;
        }
        this.r.add(d);
        d.a(this.s);
        if (d.C < this.t.size) {
            d.b(this.t.get(d.C));
        }
        d.b(this.u);
        if (t != null) {
            addActor(t);
        }
        return d;
    }

    public Table add(Actor... actorArr) {
        for (Actor actor : actorArr) {
            add((Table) actor);
        }
        return this;
    }

    public Cell add() {
        return add((Table) null);
    }

    public Cell<Stack> stack(@Null Actor... actorArr) {
        Stack stack = new Stack();
        if (actorArr != null) {
            for (Actor actor : actorArr) {
                stack.addActor(actor);
            }
        }
        return add((Table) stack);
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public boolean removeActor(Actor actor) {
        return removeActor(actor, true);
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public boolean removeActor(Actor actor, boolean z) {
        if (!super.removeActor(actor, z)) {
            return false;
        }
        Cell cell = getCell(actor);
        if (cell != null) {
            cell.w = null;
            return true;
        }
        return true;
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public Actor removeActorAt(int i, boolean z) {
        Actor removeActorAt = super.removeActorAt(i, z);
        Cell cell = getCell(removeActorAt);
        if (cell != null) {
            cell.w = null;
        }
        return removeActorAt;
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public void clearChildren(boolean z) {
        Cell[] cellArr = this.r.items;
        for (int i = this.r.size - 1; i >= 0; i--) {
            Actor actor = cellArr[i].w;
            if (actor != null) {
                actor.remove();
            }
        }
        k.freeAll(this.r);
        this.r.clear();
        this.p = 0;
        this.o = 0;
        if (this.u != null) {
            k.free(this.u);
        }
        this.u = null;
        this.q = false;
        super.clearChildren(z);
    }

    public void reset() {
        clearChildren();
        this.I = backgroundTop;
        this.J = backgroundLeft;
        this.K = backgroundBottom;
        this.L = backgroundRight;
        this.M = 1;
        debug(Debug.none);
        this.s.reset();
        int i = this.t.size;
        for (int i2 = 0; i2 < i; i2++) {
            Cell cell = this.t.get(i2);
            if (cell != null) {
                k.free(cell);
            }
        }
        this.t.clear();
    }

    public Cell row() {
        if (this.r.size > 0) {
            if (!this.q) {
                if (this.r.peek().B) {
                    return this.u;
                }
                e();
            }
            invalidate();
        }
        this.q = false;
        if (this.u != null) {
            k.free(this.u);
        }
        this.u = d();
        this.u.a();
        return this.u;
    }

    private void e() {
        Cell[] cellArr = this.r.items;
        int i = 0;
        for (int i2 = this.r.size - 1; i2 >= 0; i2--) {
            Cell cell = cellArr[i2];
            if (cell.B) {
                break;
            }
            i += cell.t.intValue();
        }
        this.o = Math.max(this.o, i);
        this.p++;
        this.r.peek().B = true;
    }

    public Cell columnDefaults(int i) {
        Cell cell = this.t.size > i ? this.t.get(i) : null;
        Cell cell2 = cell;
        if (cell == null) {
            Cell d = d();
            cell2 = d;
            d.a();
            if (i >= this.t.size) {
                for (int i2 = this.t.size; i2 < i; i2++) {
                    this.t.add(null);
                }
                this.t.add(cell2);
            } else {
                this.t.set(i, cell2);
            }
        }
        return cell2;
    }

    @Null
    public <T extends Actor> Cell<T> getCell(T t) {
        if (t == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        Cell<T>[] cellArr = this.r.items;
        int i = this.r.size;
        for (int i2 = 0; i2 < i; i2++) {
            Cell<T> cell = cellArr[i2];
            if (cell.w == t) {
                return cell;
            }
        }
        return null;
    }

    public Array<Cell> getCells() {
        return this.r;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.v) {
            f();
        }
        float f = this.C;
        return this.m != null ? Math.max(f, this.m.getMinWidth()) : f;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.v) {
            f();
        }
        float f = this.D;
        return this.m != null ? Math.max(f, this.m.getMinHeight()) : f;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinWidth() {
        if (this.v) {
            f();
        }
        return this.A;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinHeight() {
        if (this.v) {
            f();
        }
        return this.B;
    }

    public Cell defaults() {
        return this.s;
    }

    public Table pad(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("pad cannot be null.");
        }
        this.I = value;
        this.J = value;
        this.K = value;
        this.L = value;
        this.v = true;
        return this;
    }

    public Table pad(Value value, Value value2, Value value3, Value value4) {
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
        this.I = value;
        this.J = value2;
        this.K = value3;
        this.L = value4;
        this.v = true;
        return this;
    }

    public Table padTop(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padTop cannot be null.");
        }
        this.I = value;
        this.v = true;
        return this;
    }

    public Table padLeft(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padLeft cannot be null.");
        }
        this.J = value;
        this.v = true;
        return this;
    }

    public Table padBottom(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padBottom cannot be null.");
        }
        this.K = value;
        this.v = true;
        return this;
    }

    public Table padRight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padRight cannot be null.");
        }
        this.L = value;
        this.v = true;
        return this;
    }

    public Table pad(float f) {
        pad(Value.Fixed.valueOf(f));
        return this;
    }

    public Table pad(float f, float f2, float f3, float f4) {
        this.I = Value.Fixed.valueOf(f);
        this.J = Value.Fixed.valueOf(f2);
        this.K = Value.Fixed.valueOf(f3);
        this.L = Value.Fixed.valueOf(f4);
        this.v = true;
        return this;
    }

    public Table padTop(float f) {
        this.I = Value.Fixed.valueOf(f);
        this.v = true;
        return this;
    }

    public Table padLeft(float f) {
        this.J = Value.Fixed.valueOf(f);
        this.v = true;
        return this;
    }

    public Table padBottom(float f) {
        this.K = Value.Fixed.valueOf(f);
        this.v = true;
        return this;
    }

    public Table padRight(float f) {
        this.L = Value.Fixed.valueOf(f);
        this.v = true;
        return this;
    }

    public Table align(int i) {
        this.M = i;
        return this;
    }

    public Table center() {
        this.M = 1;
        return this;
    }

    public Table top() {
        this.M |= 2;
        this.M &= -5;
        return this;
    }

    public Table left() {
        this.M |= 8;
        this.M &= -17;
        return this;
    }

    public Table bottom() {
        this.M |= 4;
        this.M &= -3;
        return this;
    }

    public Table right() {
        this.M |= 16;
        this.M &= -9;
        return this;
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void setDebug(boolean z) {
        debug(z ? Debug.all : Debug.none);
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public Table debug() {
        super.debug();
        return this;
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public Table debugAll() {
        super.debugAll();
        return this;
    }

    public Table debugTable() {
        super.setDebug(true);
        if (this.N != Debug.table) {
            this.N = Debug.table;
            invalidate();
        }
        return this;
    }

    public Table debugCell() {
        super.setDebug(true);
        if (this.N != Debug.cell) {
            this.N = Debug.cell;
            invalidate();
        }
        return this;
    }

    public Table debugActor() {
        super.setDebug(true);
        if (this.N != Debug.actor) {
            this.N = Debug.actor;
            invalidate();
        }
        return this;
    }

    public Table debug(Debug debug) {
        super.setDebug(debug != Debug.none);
        if (this.N != debug) {
            this.N = debug;
            if (debug == Debug.none) {
                g();
            } else {
                invalidate();
            }
        }
        return this;
    }

    public Debug getTableDebug() {
        return this.N;
    }

    public Value getPadTopValue() {
        return this.I;
    }

    public float getPadTop() {
        return this.I.get(this);
    }

    public Value getPadLeftValue() {
        return this.J;
    }

    public float getPadLeft() {
        return this.J.get(this);
    }

    public Value getPadBottomValue() {
        return this.K;
    }

    public float getPadBottom() {
        return this.K.get(this);
    }

    public Value getPadRightValue() {
        return this.L;
    }

    public float getPadRight() {
        return this.L.get(this);
    }

    public float getPadX() {
        return this.J.get(this) + this.L.get(this);
    }

    public float getPadY() {
        return this.I.get(this) + this.K.get(this);
    }

    public int getAlign() {
        return this.M;
    }

    public int getRow(float f) {
        int i = this.r.size;
        if (i == 0) {
            return -1;
        }
        float padTop = f + getPadTop();
        Cell[] cellArr = this.r.items;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = i2;
            i2++;
            Cell cell = cellArr[i4];
            if (cell.y + cell.F < padTop) {
                return i3;
            }
            if (cell.B) {
                i3++;
            }
        }
        return -1;
    }

    public void setRound(boolean z) {
        this.Q = z;
    }

    public int getRows() {
        return this.p;
    }

    public int getColumns() {
        return this.o;
    }

    public float getRowHeight(int i) {
        if (this.F == null) {
            return 0.0f;
        }
        return this.F[i];
    }

    public float getRowMinHeight(int i) {
        if (this.v) {
            f();
        }
        return this.x[i];
    }

    public float getRowPrefHeight(int i) {
        if (this.v) {
            f();
        }
        return this.z[i];
    }

    public float getColumnWidth(int i) {
        if (this.E == null) {
            return 0.0f;
        }
        return this.E[i];
    }

    public float getColumnMinWidth(int i) {
        if (this.v) {
            f();
        }
        return this.w[i];
    }

    public float getColumnPrefWidth(int i) {
        if (this.v) {
            f();
        }
        return this.y[i];
    }

    private static float[] a(float[] fArr, int i) {
        if (fArr == null || fArr.length < i) {
            return new float[i];
        }
        Arrays.fill(fArr, 0, i, 0.0f);
        return fArr;
    }

    private void f() {
        float f;
        float f2;
        this.v = false;
        Cell[] cellArr = this.r.items;
        int i = this.r.size;
        if (i > 0 && !cellArr[i - 1].B) {
            e();
            this.q = true;
        }
        int i2 = this.o;
        int i3 = this.p;
        float[] a2 = a(this.w, i2);
        this.w = a2;
        float[] a3 = a(this.x, i3);
        this.x = a3;
        float[] a4 = a(this.y, i2);
        this.y = a4;
        float[] a5 = a(this.z, i3);
        this.z = a5;
        this.E = a(this.E, i2);
        this.F = a(this.F, i3);
        float[] a6 = a(this.G, i2);
        this.G = a6;
        float[] a7 = a(this.H, i3);
        this.H = a7;
        float f3 = 0.0f;
        for (int i4 = 0; i4 < i; i4++) {
            Cell cell = cellArr[i4];
            int i5 = cell.C;
            int i6 = cell.D;
            int intValue = cell.t.intValue();
            Actor actor = cell.w;
            if (cell.s.intValue() != 0 && a7[i6] == 0.0f) {
                a7[i6] = cell.s.intValue();
            }
            if (intValue == 1 && cell.r.intValue() != 0 && a6[i5] == 0.0f) {
                a6[i5] = cell.r.intValue();
            }
            cell.G = cell.l.get(actor) + (i5 == 0 ? 0.0f : Math.max(0.0f, cell.h.get(actor) - f3));
            cell.F = cell.k.get(actor);
            if (cell.E != -1) {
                cell.F += Math.max(0.0f, cell.g.get(actor) - cellArr[cell.E].i.get(actor));
            }
            float f4 = cell.j.get(actor);
            cell.I = cell.n.get(actor) + (i5 + intValue == i2 ? 0.0f : f4);
            cell.H = cell.m.get(actor) + (i6 == i3 - 1 ? 0.0f : cell.i.get(actor));
            f3 = f4;
            float f5 = cell.c.get(actor);
            float f6 = cell.d.get(actor);
            float f7 = cell.f2656a.get(actor);
            float f8 = cell.f2657b.get(actor);
            float f9 = cell.e.get(actor);
            float f10 = cell.f.get(actor);
            if (f5 < f7) {
                f5 = f7;
            }
            if (f6 < f8) {
                f6 = f8;
            }
            if (f9 > 0.0f && f5 > f9) {
                f5 = f9;
            }
            if (f10 > 0.0f && f6 > f10) {
                f6 = f10;
            }
            if (this.Q) {
                f7 = (float) Math.ceil(f7);
                f8 = (float) Math.ceil(f8);
                f5 = (float) Math.ceil(f5);
                f6 = (float) Math.ceil(f6);
            }
            if (intValue == 1) {
                float f11 = cell.G + cell.I;
                a4[i5] = Math.max(a4[i5], f5 + f11);
                a2[i5] = Math.max(a2[i5], f7 + f11);
            }
            float f12 = cell.F + cell.H;
            a5[i6] = Math.max(a5[i6], f6 + f12);
            a3[i6] = Math.max(a3[i6], f8 + f12);
        }
        float f13 = 0.0f;
        float f14 = 0.0f;
        float f15 = 0.0f;
        float f16 = 0.0f;
        for (int i7 = 0; i7 < i; i7++) {
            Cell cell2 = cellArr[i7];
            int i8 = cell2.C;
            int intValue2 = cell2.r.intValue();
            if (intValue2 != 0) {
                int intValue3 = i8 + cell2.t.intValue();
                int i9 = i8;
                while (true) {
                    if (i9 >= intValue3) {
                        for (int i10 = i8; i10 < intValue3; i10++) {
                            a6[i10] = intValue2;
                        }
                    } else if (a6[i9] != 0.0f) {
                        break;
                    } else {
                        i9++;
                    }
                }
            }
            if (cell2.u == Boolean.TRUE && cell2.t.intValue() == 1) {
                float f17 = cell2.G + cell2.I;
                f13 = Math.max(f13, a2[i8] - f17);
                f15 = Math.max(f15, a4[i8] - f17);
            }
            if (cell2.v == Boolean.TRUE) {
                float f18 = cell2.F + cell2.H;
                f14 = Math.max(f14, a3[cell2.D] - f18);
                f16 = Math.max(f16, a5[cell2.D] - f18);
            }
        }
        if (f15 > 0.0f || f16 > 0.0f) {
            for (int i11 = 0; i11 < i; i11++) {
                Cell cell3 = cellArr[i11];
                if (f15 > 0.0f && cell3.u == Boolean.TRUE && cell3.t.intValue() == 1) {
                    float f19 = cell3.G + cell3.I;
                    a2[cell3.C] = f13 + f19;
                    a4[cell3.C] = f15 + f19;
                }
                if (f16 > 0.0f && cell3.v == Boolean.TRUE) {
                    float f20 = cell3.F + cell3.H;
                    a3[cell3.D] = f14 + f20;
                    a5[cell3.D] = f16 + f20;
                }
            }
        }
        for (int i12 = 0; i12 < i; i12++) {
            Cell cell4 = cellArr[i12];
            int intValue4 = cell4.t.intValue();
            if (intValue4 != 1) {
                int i13 = cell4.C;
                Actor actor2 = cell4.w;
                float f21 = cell4.f2656a.get(actor2);
                float f22 = cell4.c.get(actor2);
                float f23 = cell4.e.get(actor2);
                if (f22 < f21) {
                    f22 = f21;
                }
                if (f23 > 0.0f && f22 > f23) {
                    f22 = f23;
                }
                if (this.Q) {
                    f21 = (float) Math.ceil(f21);
                    f22 = (float) Math.ceil(f22);
                }
                float f24 = -(cell4.G + cell4.I);
                float f25 = f24;
                float f26 = f24;
                float f27 = 0.0f;
                int i14 = i13 + intValue4;
                for (int i15 = i13; i15 < i14; i15++) {
                    f25 += a2[i15];
                    f26 += a4[i15];
                    f27 += a6[i15];
                }
                float max = Math.max(0.0f, f21 - f25);
                float max2 = Math.max(0.0f, f22 - f26);
                int i16 = i13 + intValue4;
                for (int i17 = i13; i17 < i16; i17++) {
                    if (f27 == 0.0f) {
                        f = 1.0f;
                        f2 = intValue4;
                    } else {
                        f = a6[i17];
                        f2 = f27;
                    }
                    float f28 = f / f2;
                    int i18 = i17;
                    a2[i18] = a2[i18] + (max * f28);
                    int i19 = i17;
                    a4[i19] = a4[i19] + (max2 * f28);
                }
            }
        }
        float f29 = this.J.get(this) + this.L.get(this);
        float f30 = this.I.get(this) + this.K.get(this);
        this.A = f29;
        this.C = f29;
        for (int i20 = 0; i20 < i2; i20++) {
            this.A += a2[i20];
            this.C += a4[i20];
        }
        this.B = f30;
        this.D = f30;
        for (int i21 = 0; i21 < i3; i21++) {
            this.B += a3[i21];
            this.D += Math.max(a3[i21], a5[i21]);
        }
        this.C = Math.max(this.A, this.C);
        this.D = Math.max(this.B, this.D);
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public void layout() {
        float[] fArr;
        float[] fArr2;
        if (this.v) {
            f();
        }
        float width = getWidth();
        float height = getHeight();
        int i = this.o;
        int i2 = this.p;
        float[] fArr3 = this.E;
        float[] fArr4 = this.F;
        float f = this.J.get(this);
        float f2 = f + this.L.get(this);
        float f3 = this.I.get(this);
        float f4 = f3 + this.K.get(this);
        float f5 = this.C - this.A;
        if (f5 == 0.0f) {
            fArr = this.w;
        } else {
            float min = Math.min(f5, Math.max(0.0f, width - this.A));
            float[] a2 = a(l, i);
            l = a2;
            fArr = a2;
            float[] fArr5 = this.w;
            float[] fArr6 = this.y;
            for (int i3 = 0; i3 < i; i3++) {
                fArr[i3] = fArr5[i3] + (min * ((fArr6[i3] - fArr5[i3]) / f5));
            }
        }
        float f6 = this.D - this.B;
        if (f6 == 0.0f) {
            fArr2 = this.x;
        } else {
            float[] a3 = a(n, i2);
            n = a3;
            fArr2 = a3;
            float min2 = Math.min(f6, Math.max(0.0f, height - this.B));
            float[] fArr7 = this.x;
            float[] fArr8 = this.z;
            for (int i4 = 0; i4 < i2; i4++) {
                fArr2[i4] = fArr7[i4] + (min2 * ((fArr8[i4] - fArr7[i4]) / f6));
            }
        }
        Cell[] cellArr = this.r.items;
        int i5 = this.r.size;
        for (int i6 = 0; i6 < i5; i6++) {
            Cell cell = cellArr[i6];
            int i7 = cell.C;
            int i8 = cell.D;
            Actor actor = cell.w;
            float f7 = 0.0f;
            int intValue = cell.t.intValue();
            int i9 = i7 + intValue;
            for (int i10 = i7; i10 < i9; i10++) {
                f7 += fArr[i10];
            }
            float f8 = fArr2[i8];
            float f9 = cell.c.get(actor);
            float f10 = cell.d.get(actor);
            float f11 = cell.f2656a.get(actor);
            float f12 = cell.f2657b.get(actor);
            float f13 = cell.e.get(actor);
            float f14 = cell.f.get(actor);
            if (f9 < f11) {
                f9 = f11;
            }
            if (f10 < f12) {
                f10 = f12;
            }
            if (f13 > 0.0f && f9 > f13) {
                f9 = f13;
            }
            if (f14 > 0.0f && f10 > f14) {
                f10 = f14;
            }
            cell.z = Math.min((f7 - cell.G) - cell.I, f9);
            cell.A = Math.min((f8 - cell.F) - cell.H, f10);
            if (intValue == 1) {
                fArr3[i7] = Math.max(fArr3[i7], f7);
            }
            fArr4[i8] = Math.max(fArr4[i8], f8);
        }
        float[] fArr9 = this.G;
        float[] fArr10 = this.H;
        float f15 = 0.0f;
        for (int i11 = 0; i11 < i; i11++) {
            f15 += fArr9[i11];
        }
        if (f15 > 0.0f) {
            float f16 = width - f2;
            for (int i12 = 0; i12 < i; i12++) {
                f16 -= fArr3[i12];
            }
            if (f16 > 0.0f) {
                float f17 = 0.0f;
                int i13 = 0;
                for (int i14 = 0; i14 < i; i14++) {
                    if (fArr9[i14] != 0.0f) {
                        float f18 = (f16 * fArr9[i14]) / f15;
                        int i15 = i14;
                        fArr3[i15] = fArr3[i15] + f18;
                        f17 += f18;
                        i13 = i14;
                    }
                }
                int i16 = i13;
                fArr3[i16] = fArr3[i16] + (f16 - f17);
            }
        }
        float f19 = 0.0f;
        for (int i17 = 0; i17 < i2; i17++) {
            f19 += fArr10[i17];
        }
        if (f19 > 0.0f) {
            float f20 = height - f4;
            for (int i18 = 0; i18 < i2; i18++) {
                f20 -= fArr4[i18];
            }
            if (f20 > 0.0f) {
                float f21 = 0.0f;
                int i19 = 0;
                for (int i20 = 0; i20 < i2; i20++) {
                    if (fArr10[i20] != 0.0f) {
                        float f22 = (f20 * fArr10[i20]) / f19;
                        int i21 = i20;
                        fArr4[i21] = fArr4[i21] + f22;
                        f21 += f22;
                        i19 = i20;
                    }
                }
                int i22 = i19;
                fArr4[i22] = fArr4[i22] + (f20 - f21);
            }
        }
        for (int i23 = 0; i23 < i5; i23++) {
            Cell cell2 = cellArr[i23];
            int intValue2 = cell2.t.intValue();
            if (intValue2 != 1) {
                float f23 = 0.0f;
                int i24 = cell2.C;
                int i25 = i24 + intValue2;
                for (int i26 = i24; i26 < i25; i26++) {
                    f23 += fArr[i26] - fArr3[i26];
                }
                float max = (f23 - Math.max(0.0f, cell2.G + cell2.I)) / intValue2;
                if (max > 0.0f) {
                    int i27 = cell2.C;
                    int i28 = i27 + intValue2;
                    for (int i29 = i27; i29 < i28; i29++) {
                        int i30 = i29;
                        fArr3[i30] = fArr3[i30] + max;
                    }
                }
            }
        }
        float f24 = f2;
        float f25 = f4;
        for (int i31 = 0; i31 < i; i31++) {
            f24 += fArr3[i31];
        }
        for (int i32 = 0; i32 < i2; i32++) {
            f25 += fArr4[i32];
        }
        int i33 = this.M;
        float f26 = f;
        if ((i33 & 16) != 0) {
            f26 = f + (width - f24);
        } else if ((i33 & 8) == 0) {
            f26 = f + ((width - f24) / 2.0f);
        }
        float f27 = f3;
        if ((i33 & 4) != 0) {
            f27 = f3 + (height - f25);
        } else if ((i33 & 2) == 0) {
            f27 = f3 + ((height - f25) / 2.0f);
        }
        float f28 = f26;
        float f29 = f27;
        for (int i34 = 0; i34 < i5; i34++) {
            Cell cell3 = cellArr[i34];
            float f30 = 0.0f;
            int i35 = cell3.C;
            int intValue3 = i35 + cell3.t.intValue();
            for (int i36 = i35; i36 < intValue3; i36++) {
                f30 += fArr3[i36];
            }
            float f31 = f30 - (cell3.G + cell3.I);
            float f32 = f28 + cell3.G;
            float floatValue = cell3.o.floatValue();
            float floatValue2 = cell3.p.floatValue();
            if (floatValue > 0.0f) {
                cell3.z = Math.max(f31 * floatValue, cell3.f2656a.get(cell3.w));
                float f33 = cell3.e.get(cell3.w);
                if (f33 > 0.0f) {
                    cell3.z = Math.min(cell3.z, f33);
                }
            }
            if (floatValue2 > 0.0f) {
                cell3.A = Math.max(((fArr4[cell3.D] * floatValue2) - cell3.F) - cell3.H, cell3.f2657b.get(cell3.w));
                float f34 = cell3.f.get(cell3.w);
                if (f34 > 0.0f) {
                    cell3.A = Math.min(cell3.A, f34);
                }
            }
            int intValue4 = cell3.q.intValue();
            if ((intValue4 & 8) != 0) {
                cell3.x = f32;
            } else if ((intValue4 & 16) != 0) {
                cell3.x = (f32 + f31) - cell3.z;
            } else {
                cell3.x = f32 + ((f31 - cell3.z) / 2.0f);
            }
            if ((intValue4 & 2) != 0) {
                cell3.y = cell3.F;
            } else if ((intValue4 & 4) != 0) {
                cell3.y = (fArr4[cell3.D] - cell3.A) - cell3.H;
            } else {
                cell3.y = (((fArr4[cell3.D] - cell3.A) + cell3.F) - cell3.H) / 2.0f;
            }
            cell3.y = ((height - f29) - cell3.y) - cell3.A;
            if (this.Q) {
                cell3.z = (float) Math.ceil(cell3.z);
                cell3.A = (float) Math.ceil(cell3.A);
                cell3.x = (float) Math.floor(cell3.x);
                cell3.y = (float) Math.floor(cell3.y);
            }
            if (cell3.w != null) {
                cell3.w.setBounds(cell3.x, cell3.y, cell3.z, cell3.A);
            }
            if (cell3.B) {
                f28 = f26;
                f29 += fArr4[cell3.D];
            } else {
                f28 = f32 + f31 + cell3.I;
            }
        }
        SnapshotArray<Actor> children = getChildren();
        Actor[] actorArr = children.items;
        int i37 = children.size;
        for (int i38 = 0; i38 < i37; i38++) {
            Object obj = actorArr[i38];
            if (obj instanceof Layout) {
                ((Layout) obj).validate();
            }
        }
        if (this.N != Debug.none) {
            a(f26, f27, f24 - f2, f25 - f4);
        }
    }

    private void a(float f, float f2, float f3, float f4) {
        g();
        if (this.N == Debug.table || this.N == Debug.all) {
            a(0.0f, 0.0f, getWidth(), getHeight(), debugTableColor);
            a(f, getHeight() - f2, f3, -f4, debugTableColor);
        }
        int i = this.r.size;
        for (int i2 = 0; i2 < i; i2++) {
            Cell cell = this.r.get(i2);
            if (this.N == Debug.actor || this.N == Debug.all) {
                a(cell.x, cell.y, cell.z, cell.A, debugActorColor);
            }
            float f5 = 0.0f;
            int i3 = cell.C;
            int intValue = i3 + cell.t.intValue();
            for (int i4 = i3; i4 < intValue; i4++) {
                f5 += this.E[i4];
            }
            float f6 = f5 - (cell.G + cell.I);
            float f7 = f + cell.G;
            if (this.N == Debug.cell || this.N == Debug.all) {
                a(f7, getHeight() - (f2 + cell.F), f6, -((this.F[cell.D] - cell.F) - cell.H), debugCellColor);
            }
            if (cell.B) {
                f = f;
                f2 += this.F[cell.D];
            } else {
                f = f7 + f6 + cell.I;
            }
        }
    }

    private void g() {
        if (this.O == null) {
            this.O = new Array<>();
        }
        DebugRect.f2675a.freeAll(this.O);
        this.O.clear();
    }

    private void a(float f, float f2, float f3, float f4, Color color) {
        DebugRect obtain = DebugRect.f2675a.obtain();
        obtain.f2676b = color;
        obtain.set(f, f2, f3, f4);
        this.O.add(obtain);
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void drawDebug(ShapeRenderer shapeRenderer) {
        if (isTransform()) {
            a(shapeRenderer, b());
            d(shapeRenderer);
            if (this.P) {
                shapeRenderer.flush();
                float f = 0.0f;
                float f2 = 0.0f;
                float width = getWidth();
                float height = getHeight();
                if (this.m != null) {
                    f = this.J.get(this);
                    f2 = this.K.get(this);
                    width -= f + this.L.get(this);
                    height -= f2 + this.I.get(this);
                }
                if (clipBegin(f, f2, width, height)) {
                    b(shapeRenderer);
                    clipEnd();
                }
            } else {
                b(shapeRenderer);
            }
            c(shapeRenderer);
            return;
        }
        d(shapeRenderer);
        super.drawDebug(shapeRenderer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.tdi2.scene2d.Actor
    public final void a(ShapeRenderer shapeRenderer) {
    }

    private void d(ShapeRenderer shapeRenderer) {
        if (this.O == null || !getDebug()) {
            return;
        }
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        if (getStage() != null) {
            shapeRenderer.setColor(getStage().getDebugColor());
        }
        float f = 0.0f;
        float f2 = 0.0f;
        if (!isTransform()) {
            f = getX();
            f2 = getY();
        }
        int i = this.O.size;
        for (int i2 = 0; i2 < i; i2++) {
            DebugRect debugRect = this.O.get(i2);
            shapeRenderer.setColor(debugRect.f2676b);
            shapeRenderer.rect(f + debugRect.x, f2 + debugRect.y, debugRect.width, debugRect.height);
        }
    }
}
