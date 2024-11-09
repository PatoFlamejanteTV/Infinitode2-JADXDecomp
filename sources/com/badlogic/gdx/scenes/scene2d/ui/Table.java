package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Table.class */
public class Table extends WidgetGroup {
    private static float[] columnWeightedWidth;
    private static float[] rowWeightedHeight;
    private int columns;
    private int rows;
    private boolean implicitEndRow;
    private final Array<Cell> cells;
    private final Cell cellDefaults;
    private final Array<Cell> columnDefaults;
    private Cell rowDefaults;
    private boolean sizeInvalid;
    private float[] columnMinWidth;
    private float[] rowMinHeight;
    private float[] columnPrefWidth;
    private float[] rowPrefHeight;
    private float tableMinWidth;
    private float tableMinHeight;
    private float tablePrefWidth;
    private float tablePrefHeight;
    private float[] columnWidth;
    private float[] rowHeight;
    private float[] expandWidth;
    private float[] expandHeight;
    Value padTop;
    Value padLeft;
    Value padBottom;
    Value padRight;
    int align;
    Debug debug;
    Array<DebugRect> debugRects;

    @Null
    Drawable background;
    private boolean clip;

    @Null
    private Skin skin;
    boolean round;
    public static Color debugTableColor = new Color(0.0f, 0.0f, 1.0f, 1.0f);
    public static Color debugCellColor = new Color(1.0f, 0.0f, 0.0f, 1.0f);
    public static Color debugActorColor = new Color(0.0f, 1.0f, 0.0f, 1.0f);
    static final Pool<Cell> cellPool = new Pool<Cell>() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Table.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.badlogic.gdx.utils.Pool
        public Cell newObject() {
            return new Cell();
        }
    };
    public static Value backgroundTop = new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Table.2
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(@Null Actor actor) {
            Drawable drawable = ((Table) actor).background;
            if (drawable == null) {
                return 0.0f;
            }
            return drawable.getTopHeight();
        }
    };
    public static Value backgroundLeft = new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Table.3
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(@Null Actor actor) {
            Drawable drawable = ((Table) actor).background;
            if (drawable == null) {
                return 0.0f;
            }
            return drawable.getLeftWidth();
        }
    };
    public static Value backgroundBottom = new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Table.4
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(@Null Actor actor) {
            Drawable drawable = ((Table) actor).background;
            if (drawable == null) {
                return 0.0f;
            }
            return drawable.getBottomHeight();
        }
    };
    public static Value backgroundRight = new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Table.5
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(@Null Actor actor) {
            Drawable drawable = ((Table) actor).background;
            if (drawable == null) {
                return 0.0f;
            }
            return drawable.getRightWidth();
        }
    };

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Table$Debug.class */
    public enum Debug {
        none,
        all,
        table,
        cell,
        actor
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Table$DebugRect.class */
    public static class DebugRect extends Rectangle {
        static Pool<DebugRect> pool = Pools.get(DebugRect.class);
        Color color;
    }

    public Table() {
        this(null);
    }

    public Table(@Null Skin skin) {
        this.cells = new Array<>(4);
        this.columnDefaults = new Array<>(2);
        this.sizeInvalid = true;
        this.padTop = backgroundTop;
        this.padLeft = backgroundLeft;
        this.padBottom = backgroundBottom;
        this.padRight = backgroundRight;
        this.align = 1;
        this.debug = Debug.none;
        this.round = true;
        this.skin = skin;
        this.cellDefaults = obtainCell();
        setTransform(false);
        setTouchable(Touchable.childrenOnly);
    }

    private Cell obtainCell() {
        Cell obtain = cellPool.obtain();
        obtain.setTable(this);
        return obtain;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        validate();
        if (isTransform()) {
            applyTransform(batch, computeTransform());
            drawBackground(batch, f, 0.0f, 0.0f);
            if (this.clip) {
                batch.flush();
                float f2 = this.padLeft.get(this);
                float f3 = this.padBottom.get(this);
                if (clipBegin(f2, f3, (getWidth() - f2) - this.padRight.get(this), (getHeight() - f3) - this.padTop.get(this))) {
                    drawChildren(batch, f);
                    batch.flush();
                    clipEnd();
                }
            } else {
                drawChildren(batch, f);
            }
            resetTransform(batch);
            return;
        }
        drawBackground(batch, f, getX(), getY());
        super.draw(batch, f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void drawBackground(Batch batch, float f, float f2, float f3) {
        if (this.background == null) {
            return;
        }
        Color color = getColor();
        batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
        this.background.draw(batch, f2, f3, getWidth(), getHeight());
    }

    public void setBackground(String str) {
        if (this.skin == null) {
            throw new IllegalStateException("Table must have a skin set to use this method.");
        }
        setBackground(this.skin.getDrawable(str));
    }

    public void setBackground(@Null Drawable drawable) {
        if (this.background == drawable) {
            return;
        }
        float padTop = getPadTop();
        float padLeft = getPadLeft();
        float padBottom = getPadBottom();
        float padRight = getPadRight();
        this.background = drawable;
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

    public Table background(String str) {
        setBackground(str);
        return this;
    }

    @Null
    public Drawable getBackground() {
        return this.background;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    @Null
    public Actor hit(float f, float f2, boolean z) {
        if (!this.clip || (!(z && getTouchable() == Touchable.disabled) && f >= 0.0f && f < getWidth() && f2 >= 0.0f && f2 < getHeight())) {
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
        this.clip = z;
        setTransform(z);
        invalidate();
    }

    public boolean getClip() {
        return this.clip;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void invalidate() {
        this.sizeInvalid = true;
        super.invalidate();
    }

    public <T extends Actor> Cell<T> add(@Null T t) {
        Cell<T> obtainCell = obtainCell();
        obtainCell.actor = t;
        if (this.implicitEndRow) {
            this.implicitEndRow = false;
            this.rows--;
            this.cells.peek().endRow = false;
        }
        int i = this.cells.size;
        if (i > 0) {
            Cell peek = this.cells.peek();
            if (!peek.endRow) {
                obtainCell.column = peek.column + peek.colspan.intValue();
                obtainCell.row = peek.row;
            } else {
                obtainCell.column = 0;
                obtainCell.row = peek.row + 1;
            }
            if (obtainCell.row > 0) {
                Cell[] cellArr = this.cells.items;
                int i2 = i - 1;
                loop0: while (true) {
                    if (i2 < 0) {
                        break;
                    }
                    Cell cell = cellArr[i2];
                    int i3 = cell.column;
                    int intValue = i3 + cell.colspan.intValue();
                    for (int i4 = i3; i4 < intValue; i4++) {
                        if (i4 == obtainCell.column) {
                            obtainCell.cellAboveIndex = i2;
                            break loop0;
                        }
                    }
                    i2--;
                }
            }
        } else {
            obtainCell.column = 0;
            obtainCell.row = 0;
        }
        this.cells.add(obtainCell);
        obtainCell.set(this.cellDefaults);
        if (obtainCell.column < this.columnDefaults.size) {
            obtainCell.merge(this.columnDefaults.get(obtainCell.column));
        }
        obtainCell.merge(this.rowDefaults);
        if (t != null) {
            addActor(t);
        }
        return obtainCell;
    }

    public Table add(Actor... actorArr) {
        for (Actor actor : actorArr) {
            add((Table) actor);
        }
        return this;
    }

    public Cell<Label> add(@Null CharSequence charSequence) {
        if (this.skin == null) {
            throw new IllegalStateException("Table must have a skin set to use this method.");
        }
        return add((Table) new Label(charSequence, this.skin));
    }

    public Cell<Label> add(@Null CharSequence charSequence, String str) {
        if (this.skin == null) {
            throw new IllegalStateException("Table must have a skin set to use this method.");
        }
        return add((Table) new Label(charSequence, (Label.LabelStyle) this.skin.get(str, Label.LabelStyle.class)));
    }

    public Cell<Label> add(@Null CharSequence charSequence, String str, @Null Color color) {
        if (this.skin == null) {
            throw new IllegalStateException("Table must have a skin set to use this method.");
        }
        return add((Table) new Label(charSequence, new Label.LabelStyle(this.skin.getFont(str), color)));
    }

    public Cell<Label> add(@Null CharSequence charSequence, String str, String str2) {
        if (this.skin == null) {
            throw new IllegalStateException("Table must have a skin set to use this method.");
        }
        return add((Table) new Label(charSequence, new Label.LabelStyle(this.skin.getFont(str), this.skin.getColor(str2))));
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

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor) {
        return removeActor(actor, true);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor, boolean z) {
        if (!super.removeActor(actor, z)) {
            return false;
        }
        Cell cell = getCell(actor);
        if (cell != null) {
            cell.actor = null;
            return true;
        }
        return true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public Actor removeActorAt(int i, boolean z) {
        Actor removeActorAt = super.removeActorAt(i, z);
        Cell cell = getCell(removeActorAt);
        if (cell != null) {
            cell.actor = null;
        }
        return removeActorAt;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public void clearChildren(boolean z) {
        Cell[] cellArr = this.cells.items;
        for (int i = this.cells.size - 1; i >= 0; i--) {
            Actor actor = cellArr[i].actor;
            if (actor != null) {
                actor.remove();
            }
        }
        cellPool.freeAll(this.cells);
        this.cells.clear();
        this.rows = 0;
        this.columns = 0;
        if (this.rowDefaults != null) {
            cellPool.free(this.rowDefaults);
        }
        this.rowDefaults = null;
        this.implicitEndRow = false;
        super.clearChildren(z);
    }

    public void reset() {
        clearChildren();
        this.padTop = backgroundTop;
        this.padLeft = backgroundLeft;
        this.padBottom = backgroundBottom;
        this.padRight = backgroundRight;
        this.align = 1;
        debug(Debug.none);
        this.cellDefaults.reset();
        int i = this.columnDefaults.size;
        for (int i2 = 0; i2 < i; i2++) {
            Cell cell = this.columnDefaults.get(i2);
            if (cell != null) {
                cellPool.free(cell);
            }
        }
        this.columnDefaults.clear();
    }

    public Cell row() {
        if (this.cells.size > 0) {
            if (!this.implicitEndRow) {
                if (this.cells.peek().endRow) {
                    return this.rowDefaults;
                }
                endRow();
            }
            invalidate();
        }
        this.implicitEndRow = false;
        if (this.rowDefaults != null) {
            cellPool.free(this.rowDefaults);
        }
        this.rowDefaults = obtainCell();
        this.rowDefaults.clear();
        return this.rowDefaults;
    }

    private void endRow() {
        Cell[] cellArr = this.cells.items;
        int i = 0;
        for (int i2 = this.cells.size - 1; i2 >= 0; i2--) {
            Cell cell = cellArr[i2];
            if (cell.endRow) {
                break;
            }
            i += cell.colspan.intValue();
        }
        this.columns = Math.max(this.columns, i);
        this.rows++;
        this.cells.peek().endRow = true;
    }

    public Cell columnDefaults(int i) {
        Cell cell = this.columnDefaults.size > i ? this.columnDefaults.get(i) : null;
        Cell cell2 = cell;
        if (cell == null) {
            Cell obtainCell = obtainCell();
            cell2 = obtainCell;
            obtainCell.clear();
            if (i >= this.columnDefaults.size) {
                for (int i2 = this.columnDefaults.size; i2 < i; i2++) {
                    this.columnDefaults.add(null);
                }
                this.columnDefaults.add(cell2);
            } else {
                this.columnDefaults.set(i, cell2);
            }
        }
        return cell2;
    }

    @Null
    public <T extends Actor> Cell<T> getCell(T t) {
        if (t == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        Cell<T>[] cellArr = this.cells.items;
        int i = this.cells.size;
        for (int i2 = 0; i2 < i; i2++) {
            Cell<T> cell = cellArr[i2];
            if (cell.actor == t) {
                return cell;
            }
        }
        return null;
    }

    public Array<Cell> getCells() {
        return this.cells;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.sizeInvalid) {
            computeSize();
        }
        float f = this.tablePrefWidth;
        return this.background != null ? Math.max(f, this.background.getMinWidth()) : f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.sizeInvalid) {
            computeSize();
        }
        float f = this.tablePrefHeight;
        return this.background != null ? Math.max(f, this.background.getMinHeight()) : f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinWidth() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.tableMinWidth;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinHeight() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.tableMinHeight;
    }

    public Cell defaults() {
        return this.cellDefaults;
    }

    public Table pad(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("pad cannot be null.");
        }
        this.padTop = value;
        this.padLeft = value;
        this.padBottom = value;
        this.padRight = value;
        this.sizeInvalid = true;
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
        this.padTop = value;
        this.padLeft = value2;
        this.padBottom = value3;
        this.padRight = value4;
        this.sizeInvalid = true;
        return this;
    }

    public Table padTop(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padTop cannot be null.");
        }
        this.padTop = value;
        this.sizeInvalid = true;
        return this;
    }

    public Table padLeft(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padLeft cannot be null.");
        }
        this.padLeft = value;
        this.sizeInvalid = true;
        return this;
    }

    public Table padBottom(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padBottom cannot be null.");
        }
        this.padBottom = value;
        this.sizeInvalid = true;
        return this;
    }

    public Table padRight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padRight cannot be null.");
        }
        this.padRight = value;
        this.sizeInvalid = true;
        return this;
    }

    public Table pad(float f) {
        pad(Value.Fixed.valueOf(f));
        return this;
    }

    public Table pad(float f, float f2, float f3, float f4) {
        this.padTop = Value.Fixed.valueOf(f);
        this.padLeft = Value.Fixed.valueOf(f2);
        this.padBottom = Value.Fixed.valueOf(f3);
        this.padRight = Value.Fixed.valueOf(f4);
        this.sizeInvalid = true;
        return this;
    }

    public Table padTop(float f) {
        this.padTop = Value.Fixed.valueOf(f);
        this.sizeInvalid = true;
        return this;
    }

    public Table padLeft(float f) {
        this.padLeft = Value.Fixed.valueOf(f);
        this.sizeInvalid = true;
        return this;
    }

    public Table padBottom(float f) {
        this.padBottom = Value.Fixed.valueOf(f);
        this.sizeInvalid = true;
        return this;
    }

    public Table padRight(float f) {
        this.padRight = Value.Fixed.valueOf(f);
        this.sizeInvalid = true;
        return this;
    }

    public Table align(int i) {
        this.align = i;
        return this;
    }

    public Table center() {
        this.align = 1;
        return this;
    }

    public Table top() {
        this.align |= 2;
        this.align &= -5;
        return this;
    }

    public Table left() {
        this.align |= 8;
        this.align &= -17;
        return this;
    }

    public Table bottom() {
        this.align |= 4;
        this.align &= -3;
        return this;
    }

    public Table right() {
        this.align |= 16;
        this.align &= -9;
        return this;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void setDebug(boolean z) {
        debug(z ? Debug.all : Debug.none);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public Table debug() {
        super.debug();
        return this;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public Table debugAll() {
        super.debugAll();
        return this;
    }

    public Table debugTable() {
        super.setDebug(true);
        if (this.debug != Debug.table) {
            this.debug = Debug.table;
            invalidate();
        }
        return this;
    }

    public Table debugCell() {
        super.setDebug(true);
        if (this.debug != Debug.cell) {
            this.debug = Debug.cell;
            invalidate();
        }
        return this;
    }

    public Table debugActor() {
        super.setDebug(true);
        if (this.debug != Debug.actor) {
            this.debug = Debug.actor;
            invalidate();
        }
        return this;
    }

    public Table debug(Debug debug) {
        super.setDebug(debug != Debug.none);
        if (this.debug != debug) {
            this.debug = debug;
            if (debug == Debug.none) {
                clearDebugRects();
            } else {
                invalidate();
            }
        }
        return this;
    }

    public Debug getTableDebug() {
        return this.debug;
    }

    public Value getPadTopValue() {
        return this.padTop;
    }

    public float getPadTop() {
        return this.padTop.get(this);
    }

    public Value getPadLeftValue() {
        return this.padLeft;
    }

    public float getPadLeft() {
        return this.padLeft.get(this);
    }

    public Value getPadBottomValue() {
        return this.padBottom;
    }

    public float getPadBottom() {
        return this.padBottom.get(this);
    }

    public Value getPadRightValue() {
        return this.padRight;
    }

    public float getPadRight() {
        return this.padRight.get(this);
    }

    public float getPadX() {
        return this.padLeft.get(this) + this.padRight.get(this);
    }

    public float getPadY() {
        return this.padTop.get(this) + this.padBottom.get(this);
    }

    public int getAlign() {
        return this.align;
    }

    public int getRow(float f) {
        int i = this.cells.size;
        if (i == 0) {
            return -1;
        }
        float padTop = f + getPadTop();
        Cell[] cellArr = this.cells.items;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = i2;
            i2++;
            Cell cell = cellArr[i4];
            if (cell.actorY + cell.computedPadTop < padTop) {
                return i3;
            }
            if (cell.endRow) {
                i3++;
            }
        }
        return -1;
    }

    public void setSkin(@Null Skin skin) {
        this.skin = skin;
    }

    public void setRound(boolean z) {
        this.round = z;
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public float getRowHeight(int i) {
        if (this.rowHeight == null) {
            return 0.0f;
        }
        return this.rowHeight[i];
    }

    public float getRowMinHeight(int i) {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.rowMinHeight[i];
    }

    public float getRowPrefHeight(int i) {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.rowPrefHeight[i];
    }

    public float getColumnWidth(int i) {
        if (this.columnWidth == null) {
            return 0.0f;
        }
        return this.columnWidth[i];
    }

    public float getColumnMinWidth(int i) {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.columnMinWidth[i];
    }

    public float getColumnPrefWidth(int i) {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.columnPrefWidth[i];
    }

    private float[] ensureSize(float[] fArr, int i) {
        if (fArr == null || fArr.length < i) {
            return new float[i];
        }
        Arrays.fill(fArr, 0, i, 0.0f);
        return fArr;
    }

    private void computeSize() {
        float f;
        float f2;
        this.sizeInvalid = false;
        Cell[] cellArr = this.cells.items;
        int i = this.cells.size;
        if (i > 0 && !cellArr[i - 1].endRow) {
            endRow();
            this.implicitEndRow = true;
        }
        int i2 = this.columns;
        int i3 = this.rows;
        float[] ensureSize = ensureSize(this.columnMinWidth, i2);
        this.columnMinWidth = ensureSize;
        float[] ensureSize2 = ensureSize(this.rowMinHeight, i3);
        this.rowMinHeight = ensureSize2;
        float[] ensureSize3 = ensureSize(this.columnPrefWidth, i2);
        this.columnPrefWidth = ensureSize3;
        float[] ensureSize4 = ensureSize(this.rowPrefHeight, i3);
        this.rowPrefHeight = ensureSize4;
        this.columnWidth = ensureSize(this.columnWidth, i2);
        this.rowHeight = ensureSize(this.rowHeight, i3);
        float[] ensureSize5 = ensureSize(this.expandWidth, i2);
        this.expandWidth = ensureSize5;
        float[] ensureSize6 = ensureSize(this.expandHeight, i3);
        this.expandHeight = ensureSize6;
        float f3 = 0.0f;
        for (int i4 = 0; i4 < i; i4++) {
            Cell cell = cellArr[i4];
            int i5 = cell.column;
            int i6 = cell.row;
            int intValue = cell.colspan.intValue();
            Actor actor = cell.actor;
            if (cell.expandY.intValue() != 0 && ensureSize6[i6] == 0.0f) {
                ensureSize6[i6] = cell.expandY.intValue();
            }
            if (intValue == 1 && cell.expandX.intValue() != 0 && ensureSize5[i5] == 0.0f) {
                ensureSize5[i5] = cell.expandX.intValue();
            }
            cell.computedPadLeft = cell.padLeft.get(actor) + (i5 == 0 ? 0.0f : Math.max(0.0f, cell.spaceLeft.get(actor) - f3));
            cell.computedPadTop = cell.padTop.get(actor);
            if (cell.cellAboveIndex != -1) {
                cell.computedPadTop += Math.max(0.0f, cell.spaceTop.get(actor) - cellArr[cell.cellAboveIndex].spaceBottom.get(actor));
            }
            float f4 = cell.spaceRight.get(actor);
            cell.computedPadRight = cell.padRight.get(actor) + (i5 + intValue == i2 ? 0.0f : f4);
            cell.computedPadBottom = cell.padBottom.get(actor) + (i6 == i3 - 1 ? 0.0f : cell.spaceBottom.get(actor));
            f3 = f4;
            float f5 = cell.prefWidth.get(actor);
            float f6 = cell.prefHeight.get(actor);
            float f7 = cell.minWidth.get(actor);
            float f8 = cell.minHeight.get(actor);
            float f9 = cell.maxWidth.get(actor);
            float f10 = cell.maxHeight.get(actor);
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
            if (this.round) {
                f7 = (float) Math.ceil(f7);
                f8 = (float) Math.ceil(f8);
                f5 = (float) Math.ceil(f5);
                f6 = (float) Math.ceil(f6);
            }
            if (intValue == 1) {
                float f11 = cell.computedPadLeft + cell.computedPadRight;
                ensureSize3[i5] = Math.max(ensureSize3[i5], f5 + f11);
                ensureSize[i5] = Math.max(ensureSize[i5], f7 + f11);
            }
            float f12 = cell.computedPadTop + cell.computedPadBottom;
            ensureSize4[i6] = Math.max(ensureSize4[i6], f6 + f12);
            ensureSize2[i6] = Math.max(ensureSize2[i6], f8 + f12);
        }
        float f13 = 0.0f;
        float f14 = 0.0f;
        float f15 = 0.0f;
        float f16 = 0.0f;
        for (int i7 = 0; i7 < i; i7++) {
            Cell cell2 = cellArr[i7];
            int i8 = cell2.column;
            int intValue2 = cell2.expandX.intValue();
            if (intValue2 != 0) {
                int intValue3 = i8 + cell2.colspan.intValue();
                int i9 = i8;
                while (true) {
                    if (i9 >= intValue3) {
                        for (int i10 = i8; i10 < intValue3; i10++) {
                            ensureSize5[i10] = intValue2;
                        }
                    } else if (ensureSize5[i9] != 0.0f) {
                        break;
                    } else {
                        i9++;
                    }
                }
            }
            if (cell2.uniformX == Boolean.TRUE && cell2.colspan.intValue() == 1) {
                float f17 = cell2.computedPadLeft + cell2.computedPadRight;
                f13 = Math.max(f13, ensureSize[i8] - f17);
                f15 = Math.max(f15, ensureSize3[i8] - f17);
            }
            if (cell2.uniformY == Boolean.TRUE) {
                float f18 = cell2.computedPadTop + cell2.computedPadBottom;
                f14 = Math.max(f14, ensureSize2[cell2.row] - f18);
                f16 = Math.max(f16, ensureSize4[cell2.row] - f18);
            }
        }
        if (f15 > 0.0f || f16 > 0.0f) {
            for (int i11 = 0; i11 < i; i11++) {
                Cell cell3 = cellArr[i11];
                if (f15 > 0.0f && cell3.uniformX == Boolean.TRUE && cell3.colspan.intValue() == 1) {
                    float f19 = cell3.computedPadLeft + cell3.computedPadRight;
                    ensureSize[cell3.column] = f13 + f19;
                    ensureSize3[cell3.column] = f15 + f19;
                }
                if (f16 > 0.0f && cell3.uniformY == Boolean.TRUE) {
                    float f20 = cell3.computedPadTop + cell3.computedPadBottom;
                    ensureSize2[cell3.row] = f14 + f20;
                    ensureSize4[cell3.row] = f16 + f20;
                }
            }
        }
        for (int i12 = 0; i12 < i; i12++) {
            Cell cell4 = cellArr[i12];
            int intValue4 = cell4.colspan.intValue();
            if (intValue4 != 1) {
                int i13 = cell4.column;
                Actor actor2 = cell4.actor;
                float f21 = cell4.minWidth.get(actor2);
                float f22 = cell4.prefWidth.get(actor2);
                float f23 = cell4.maxWidth.get(actor2);
                if (f22 < f21) {
                    f22 = f21;
                }
                if (f23 > 0.0f && f22 > f23) {
                    f22 = f23;
                }
                if (this.round) {
                    f21 = (float) Math.ceil(f21);
                    f22 = (float) Math.ceil(f22);
                }
                float f24 = -(cell4.computedPadLeft + cell4.computedPadRight);
                float f25 = f24;
                float f26 = f24;
                float f27 = 0.0f;
                int i14 = i13 + intValue4;
                for (int i15 = i13; i15 < i14; i15++) {
                    f25 += ensureSize[i15];
                    f26 += ensureSize3[i15];
                    f27 += ensureSize5[i15];
                }
                float max = Math.max(0.0f, f21 - f25);
                float max2 = Math.max(0.0f, f22 - f26);
                int i16 = i13 + intValue4;
                for (int i17 = i13; i17 < i16; i17++) {
                    if (f27 == 0.0f) {
                        f = 1.0f;
                        f2 = intValue4;
                    } else {
                        f = ensureSize5[i17];
                        f2 = f27;
                    }
                    float f28 = f / f2;
                    int i18 = i17;
                    ensureSize[i18] = ensureSize[i18] + (max * f28);
                    int i19 = i17;
                    ensureSize3[i19] = ensureSize3[i19] + (max2 * f28);
                }
            }
        }
        float f29 = this.padLeft.get(this) + this.padRight.get(this);
        float f30 = this.padTop.get(this) + this.padBottom.get(this);
        this.tableMinWidth = f29;
        this.tablePrefWidth = f29;
        for (int i20 = 0; i20 < i2; i20++) {
            this.tableMinWidth += ensureSize[i20];
            this.tablePrefWidth += ensureSize3[i20];
        }
        this.tableMinHeight = f30;
        this.tablePrefHeight = f30;
        for (int i21 = 0; i21 < i3; i21++) {
            this.tableMinHeight += ensureSize2[i21];
            this.tablePrefHeight += Math.max(ensureSize2[i21], ensureSize4[i21]);
        }
        this.tablePrefWidth = Math.max(this.tableMinWidth, this.tablePrefWidth);
        this.tablePrefHeight = Math.max(this.tableMinHeight, this.tablePrefHeight);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        float[] fArr;
        float[] fArr2;
        if (this.sizeInvalid) {
            computeSize();
        }
        float width = getWidth();
        float height = getHeight();
        int i = this.columns;
        int i2 = this.rows;
        float[] fArr3 = this.columnWidth;
        float[] fArr4 = this.rowHeight;
        float f = this.padLeft.get(this);
        float f2 = f + this.padRight.get(this);
        float f3 = this.padTop.get(this);
        float f4 = f3 + this.padBottom.get(this);
        float f5 = this.tablePrefWidth - this.tableMinWidth;
        if (f5 == 0.0f) {
            fArr = this.columnMinWidth;
        } else {
            float min = Math.min(f5, Math.max(0.0f, width - this.tableMinWidth));
            float[] ensureSize = ensureSize(columnWeightedWidth, i);
            columnWeightedWidth = ensureSize;
            fArr = ensureSize;
            float[] fArr5 = this.columnMinWidth;
            float[] fArr6 = this.columnPrefWidth;
            for (int i3 = 0; i3 < i; i3++) {
                fArr[i3] = fArr5[i3] + (min * ((fArr6[i3] - fArr5[i3]) / f5));
            }
        }
        float f6 = this.tablePrefHeight - this.tableMinHeight;
        if (f6 == 0.0f) {
            fArr2 = this.rowMinHeight;
        } else {
            float[] ensureSize2 = ensureSize(rowWeightedHeight, i2);
            rowWeightedHeight = ensureSize2;
            fArr2 = ensureSize2;
            float min2 = Math.min(f6, Math.max(0.0f, height - this.tableMinHeight));
            float[] fArr7 = this.rowMinHeight;
            float[] fArr8 = this.rowPrefHeight;
            for (int i4 = 0; i4 < i2; i4++) {
                fArr2[i4] = fArr7[i4] + (min2 * ((fArr8[i4] - fArr7[i4]) / f6));
            }
        }
        Cell[] cellArr = this.cells.items;
        int i5 = this.cells.size;
        for (int i6 = 0; i6 < i5; i6++) {
            Cell cell = cellArr[i6];
            int i7 = cell.column;
            int i8 = cell.row;
            Actor actor = cell.actor;
            float f7 = 0.0f;
            int intValue = cell.colspan.intValue();
            int i9 = i7 + intValue;
            for (int i10 = i7; i10 < i9; i10++) {
                f7 += fArr[i10];
            }
            float f8 = fArr2[i8];
            float f9 = cell.prefWidth.get(actor);
            float f10 = cell.prefHeight.get(actor);
            float f11 = cell.minWidth.get(actor);
            float f12 = cell.minHeight.get(actor);
            float f13 = cell.maxWidth.get(actor);
            float f14 = cell.maxHeight.get(actor);
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
            cell.actorWidth = Math.min((f7 - cell.computedPadLeft) - cell.computedPadRight, f9);
            cell.actorHeight = Math.min((f8 - cell.computedPadTop) - cell.computedPadBottom, f10);
            if (intValue == 1) {
                fArr3[i7] = Math.max(fArr3[i7], f7);
            }
            fArr4[i8] = Math.max(fArr4[i8], f8);
        }
        float[] fArr9 = this.expandWidth;
        float[] fArr10 = this.expandHeight;
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
            int intValue2 = cell2.colspan.intValue();
            if (intValue2 != 1) {
                float f23 = 0.0f;
                int i24 = cell2.column;
                int i25 = i24 + intValue2;
                for (int i26 = i24; i26 < i25; i26++) {
                    f23 += fArr[i26] - fArr3[i26];
                }
                float max = (f23 - Math.max(0.0f, cell2.computedPadLeft + cell2.computedPadRight)) / intValue2;
                if (max > 0.0f) {
                    int i27 = cell2.column;
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
        int i33 = this.align;
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
            int i35 = cell3.column;
            int intValue3 = i35 + cell3.colspan.intValue();
            for (int i36 = i35; i36 < intValue3; i36++) {
                f30 += fArr3[i36];
            }
            float f31 = f30 - (cell3.computedPadLeft + cell3.computedPadRight);
            float f32 = f28 + cell3.computedPadLeft;
            float floatValue = cell3.fillX.floatValue();
            float floatValue2 = cell3.fillY.floatValue();
            if (floatValue > 0.0f) {
                cell3.actorWidth = Math.max(f31 * floatValue, cell3.minWidth.get(cell3.actor));
                float f33 = cell3.maxWidth.get(cell3.actor);
                if (f33 > 0.0f) {
                    cell3.actorWidth = Math.min(cell3.actorWidth, f33);
                }
            }
            if (floatValue2 > 0.0f) {
                cell3.actorHeight = Math.max(((fArr4[cell3.row] * floatValue2) - cell3.computedPadTop) - cell3.computedPadBottom, cell3.minHeight.get(cell3.actor));
                float f34 = cell3.maxHeight.get(cell3.actor);
                if (f34 > 0.0f) {
                    cell3.actorHeight = Math.min(cell3.actorHeight, f34);
                }
            }
            int intValue4 = cell3.align.intValue();
            if ((intValue4 & 8) != 0) {
                cell3.actorX = f32;
            } else if ((intValue4 & 16) != 0) {
                cell3.actorX = (f32 + f31) - cell3.actorWidth;
            } else {
                cell3.actorX = f32 + ((f31 - cell3.actorWidth) / 2.0f);
            }
            if ((intValue4 & 2) != 0) {
                cell3.actorY = cell3.computedPadTop;
            } else if ((intValue4 & 4) != 0) {
                cell3.actorY = (fArr4[cell3.row] - cell3.actorHeight) - cell3.computedPadBottom;
            } else {
                cell3.actorY = (((fArr4[cell3.row] - cell3.actorHeight) + cell3.computedPadTop) - cell3.computedPadBottom) / 2.0f;
            }
            cell3.actorY = ((height - f29) - cell3.actorY) - cell3.actorHeight;
            if (this.round) {
                cell3.actorWidth = (float) Math.ceil(cell3.actorWidth);
                cell3.actorHeight = (float) Math.ceil(cell3.actorHeight);
                cell3.actorX = (float) Math.floor(cell3.actorX);
                cell3.actorY = (float) Math.floor(cell3.actorY);
            }
            if (cell3.actor != null) {
                cell3.actor.setBounds(cell3.actorX, cell3.actorY, cell3.actorWidth, cell3.actorHeight);
            }
            if (cell3.endRow) {
                f28 = f26;
                f29 += fArr4[cell3.row];
            } else {
                f28 = f32 + f31 + cell3.computedPadRight;
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
        if (this.debug != Debug.none) {
            addDebugRects(f26, f27, f24 - f2, f25 - f4);
        }
    }

    private void addDebugRects(float f, float f2, float f3, float f4) {
        clearDebugRects();
        if (this.debug == Debug.table || this.debug == Debug.all) {
            addDebugRect(0.0f, 0.0f, getWidth(), getHeight(), debugTableColor);
            addDebugRect(f, getHeight() - f2, f3, -f4, debugTableColor);
        }
        int i = this.cells.size;
        for (int i2 = 0; i2 < i; i2++) {
            Cell cell = this.cells.get(i2);
            if (this.debug == Debug.actor || this.debug == Debug.all) {
                addDebugRect(cell.actorX, cell.actorY, cell.actorWidth, cell.actorHeight, debugActorColor);
            }
            float f5 = 0.0f;
            int i3 = cell.column;
            int intValue = i3 + cell.colspan.intValue();
            for (int i4 = i3; i4 < intValue; i4++) {
                f5 += this.columnWidth[i4];
            }
            float f6 = f5 - (cell.computedPadLeft + cell.computedPadRight);
            float f7 = f + cell.computedPadLeft;
            if (this.debug == Debug.cell || this.debug == Debug.all) {
                addDebugRect(f7, getHeight() - (f2 + cell.computedPadTop), f6, -((this.rowHeight[cell.row] - cell.computedPadTop) - cell.computedPadBottom), debugCellColor);
            }
            if (cell.endRow) {
                f = f;
                f2 += this.rowHeight[cell.row];
            } else {
                f = f7 + f6 + cell.computedPadRight;
            }
        }
    }

    private void clearDebugRects() {
        if (this.debugRects == null) {
            this.debugRects = new Array<>();
        }
        DebugRect.pool.freeAll(this.debugRects);
        this.debugRects.clear();
    }

    private void addDebugRect(float f, float f2, float f3, float f4, Color color) {
        DebugRect obtain = DebugRect.pool.obtain();
        obtain.color = color;
        obtain.set(f, f2, f3, f4);
        this.debugRects.add(obtain);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void drawDebug(ShapeRenderer shapeRenderer) {
        if (isTransform()) {
            applyTransform(shapeRenderer, computeTransform());
            drawDebugRects(shapeRenderer);
            if (this.clip) {
                shapeRenderer.flush();
                float f = 0.0f;
                float f2 = 0.0f;
                float width = getWidth();
                float height = getHeight();
                if (this.background != null) {
                    f = this.padLeft.get(this);
                    f2 = this.padBottom.get(this);
                    width -= f + this.padRight.get(this);
                    height -= f2 + this.padTop.get(this);
                }
                if (clipBegin(f, f2, width, height)) {
                    drawDebugChildren(shapeRenderer);
                    clipEnd();
                }
            } else {
                drawDebugChildren(shapeRenderer);
            }
            resetTransform(shapeRenderer);
            return;
        }
        drawDebugRects(shapeRenderer);
        super.drawDebug(shapeRenderer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void drawDebugBounds(ShapeRenderer shapeRenderer) {
    }

    private void drawDebugRects(ShapeRenderer shapeRenderer) {
        if (this.debugRects == null || !getDebug()) {
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
        int i = this.debugRects.size;
        for (int i2 = 0; i2 < i; i2++) {
            DebugRect debugRect = this.debugRects.get(i2);
            shapeRenderer.setColor(debugRect.color);
            shapeRenderer.rect(f + debugRect.x, f2 + debugRect.y, debugRect.width, debugRect.height);
        }
    }

    @Null
    public Skin getSkin() {
        return this.skin;
    }
}
