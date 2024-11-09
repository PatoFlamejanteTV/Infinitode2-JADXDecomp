package com.prineside.tdi2.utils.mapeditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.utils.IntRectangle;
import com.prineside.tdi2.utils.mapeditor.SelectionOutline;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/mapeditor/Selection.class */
public final class Selection {

    /* renamed from: a, reason: collision with root package name */
    private boolean f3933a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f3934b;
    private int e;
    public Array<Tile> tiles = new Array<>(true, 1, Tile.class);
    public Array<Gate> gates = new Array<>(true, 1, Gate.class);
    private final IntRectangle c = new IntRectangle();
    private SelectionOutline d = new SelectionOutline();

    public final int count() {
        return this.tiles.size + this.gates.size;
    }

    public final Selection cpy() {
        Selection selection = new Selection();
        Array.ArrayIterator<Tile> it = this.tiles.iterator();
        while (it.hasNext()) {
            selection.addTile(it.next());
        }
        Array.ArrayIterator<Gate> it2 = this.gates.iterator();
        while (it2.hasNext()) {
            selection.addGate(it2.next());
        }
        selection.f3933a = this.f3933a;
        return selection;
    }

    public final int hash() {
        a();
        return this.e;
    }

    public final boolean hasTileOn(int i, int i2) {
        Array.ArrayIterator<Tile> it = this.tiles.iterator();
        while (it.hasNext()) {
            Tile next = it.next();
            if (next.getX() == i && next.getY() == i2) {
                return true;
            }
        }
        return false;
    }

    public final void setFromInventory(boolean z) {
        if (this.f3933a != z) {
            clear();
            this.f3933a = z;
        }
    }

    public final boolean isFromInventory() {
        return this.f3933a;
    }

    public final IntRectangle dimensions() {
        a();
        return this.c;
    }

    private void a() {
        if (this.f3934b) {
            b();
        }
    }

    private void b() {
        this.f3934b = false;
        this.c.set(0, 0, 0, 0);
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MIN_VALUE;
        this.e = this.f3933a ? 1 : 2;
        this.tiles.sort((tile, tile2) -> {
            int compare = Integer.compare(tile.getX(), tile2.getX());
            if (compare != 0) {
                return compare;
            }
            return Integer.compare(tile.getY(), tile2.getY());
        });
        this.gates.sort((gate, gate2) -> {
            int compare = Integer.compare(gate.getX(), gate2.getX());
            if (compare != 0) {
                return compare;
            }
            int compare2 = Integer.compare(gate.getY(), gate2.getY());
            if (compare2 != 0) {
                return compare2;
            }
            return Boolean.compare(gate.isLeftSide(), gate2.isLeftSide());
        });
        Array.ArrayIterator<Tile> it = this.tiles.iterator();
        while (it.hasNext()) {
            Tile next = it.next();
            i = Math.min(i, next.getX());
            i2 = Math.min(i2, next.getY());
            i3 = Math.max(i3, next.getX());
            i4 = Math.max(i4, next.getY());
            this.e = (this.e * 31) + next.getX();
            this.e = (this.e * 31) + next.getY();
            this.e = (this.e * 31) + next.generateSeedSalt();
        }
        Array.ArrayIterator<Gate> it2 = this.gates.iterator();
        while (it2.hasNext()) {
            Gate next2 = it2.next();
            this.e = (this.e * 31) + next2.getX();
            this.e = (this.e * 31) + next2.getY();
            this.e = (this.e * 31) + (next2.isLeftSide() ? 0 : 1);
            i = Math.min(i, next2.getX());
            i2 = Math.min(i2, next2.getY());
            i3 = Math.max(i3, next2.getX());
            i4 = Math.max(i4, next2.getY());
        }
        if (i != Integer.MAX_VALUE) {
            this.c.set(i, i3, i2, i4);
        }
        this.d = new SelectionOutline();
        if (this.tiles.size > 1) {
            Array.ArrayIterator<Tile> it3 = this.tiles.iterator();
            while (it3.hasNext()) {
                Tile next3 = it3.next();
                boolean z = false;
                boolean z2 = false;
                boolean z3 = false;
                boolean z4 = false;
                for (int i5 = 0; i5 < this.tiles.size; i5++) {
                    Tile tile3 = this.tiles.get(i5);
                    z2 = z2 || (next3.getY() == tile3.getY() && next3.getX() + 1 == tile3.getX());
                    z = z || (next3.getY() == tile3.getY() && next3.getX() - 1 == tile3.getX());
                    z3 = z3 || (next3.getX() == tile3.getX() && next3.getY() + 1 == tile3.getY());
                    z4 = z4 || (next3.getX() == tile3.getX() && next3.getY() - 1 == tile3.getY());
                }
                if (!z) {
                    this.d.add(next3.getX(), next3.getY(), SelectionOutline.Edge.Side.LEFT);
                }
                if (!z3) {
                    this.d.add(next3.getX(), next3.getY(), SelectionOutline.Edge.Side.TOP);
                }
                if (!z2) {
                    this.d.add(next3.getX(), next3.getY(), SelectionOutline.Edge.Side.RIGHT);
                }
                if (!z4) {
                    this.d.add(next3.getX(), next3.getY(), SelectionOutline.Edge.Side.BOTTOM);
                }
            }
            Array.ArrayIterator<Gate> it4 = this.gates.iterator();
            while (it4.hasNext()) {
                this.d.removeOverGate(it4.next());
            }
        }
    }

    public final void clear() {
        this.tiles.clear();
        this.gates.clear();
        this.f3934b = true;
    }

    public final void drawOutline(Batch batch, float f, float f2, float f3, float f4, Color color, @Null Color color2) {
        a();
        float f5 = 128.0f * f3;
        float f6 = 128.0f * f4;
        ResourcePack.AtlasTextureRegion blankWhiteTextureRegion = Game.i.assetManager.getBlankWhiteTextureRegion();
        if (color2 != null) {
            batch.setColor(color2);
            for (int i = 0; i < this.tiles.size; i++) {
                Tile tile = this.tiles.get(i);
                batch.draw(blankWhiteTextureRegion, tile.getX() << 7, tile.getY() << 7, 128.0f, 128.0f);
            }
        }
        if (this.tiles.size > 1) {
            batch.setColor(color);
            Array.ArrayIterator<SelectionOutline.Edge> it = this.d.edges.iterator();
            while (it.hasNext()) {
                SelectionOutline.Edge next = it.next();
                float f7 = f + ((next.x - this.c.minX) * f5);
                float f8 = f2 + ((next.y - this.c.minY) * f6);
                switch (next.side) {
                    case LEFT:
                        boolean z = hasTileOn(next.x, next.y + 1) || hasTileOn(next.x - 1, next.y + 1);
                        boolean z2 = hasTileOn(next.x, next.y - 1) || hasTileOn(next.x - 1, next.y - 1);
                        batch.draw(blankWhiteTextureRegion, f7 - 4.0f, f8 - (z2 ? 0.0f : 4.0f), 4.0f, f6 + (z2 ? 0.0f : 4.0f) + (z ? 0.0f : 4.0f));
                        break;
                    case TOP:
                        batch.draw(blankWhiteTextureRegion, f7, f8 + f6, f5, 4.0f);
                        break;
                    case RIGHT:
                        boolean z3 = hasTileOn(next.x, next.y + 1) || hasTileOn(next.x + 1, next.y + 1);
                        boolean z4 = hasTileOn(next.x, next.y - 1) || hasTileOn(next.x + 1, next.y - 1);
                        batch.draw(blankWhiteTextureRegion, f7 + f5, f8 - (z4 ? 0.0f : 4.0f), 4.0f, f6 + (z4 ? 0.0f : 4.0f) + (z3 ? 0.0f : 4.0f));
                        break;
                    case BOTTOM:
                        batch.draw(blankWhiteTextureRegion, f7, f8 - 4.0f, f5, 4.0f);
                        break;
                }
            }
        } else if (this.tiles.size == 1) {
            batch.setColor(color);
            Game.i.assetManager.getQuad("tile.outlineSelected").draw(batch, f + ((this.tiles.first().getX() - this.c.minX) * f5), f2 + ((this.tiles.first().getY() - this.c.minY) * f6), 128.0f, 128.0f);
        }
        batch.setColor(color);
        Array.ArrayIterator<Gate> it2 = this.gates.iterator();
        while (it2.hasNext()) {
            Gate next2 = it2.next();
            float x = f + ((next2.getX() - this.c.minX) * f5);
            float y = f2 + ((next2.getY() - this.c.minY) * f6);
            if (next2.isLeftSide()) {
                Game.i.assetManager.getQuad("gate.outlineLeftSelected").draw(batch, x, y, 128.0f, 128.0f);
            } else {
                Game.i.assetManager.getQuad("gate.outlineBottomSelected").draw(batch, x, y, 128.0f, 128.0f);
            }
        }
        batch.setColor(Color.WHITE);
    }

    public final void draw(Batch batch, float f, float f2, float f3, float f4, Map map) {
        a();
        float f5 = 128.0f * f3;
        float f6 = 128.0f * f4;
        float deltaTime = Gdx.graphics.getDeltaTime();
        Array.ArrayIterator<Tile> it = this.tiles.iterator();
        while (it.hasNext()) {
            Tile next = it.next();
            float x = f + ((next.getX() - this.c.minX) * f5);
            float y = f2 + ((next.getY() - this.c.minY) * f6);
            next.drawStatic(batch, x, y, f5, f6, map, MapRenderingSystem.DrawMode.FULL);
            next.drawExtras(batch, x, y, f5, f6, MapRenderingSystem.DrawMode.FULL);
            next.drawBatch(batch, deltaTime, x, y, f5, f6, MapRenderingSystem.DrawMode.FULL);
            next.postDrawBatch(batch, deltaTime, x, y, f5, f6, MapRenderingSystem.DrawMode.FULL);
        }
        Array.ArrayIterator<Gate> it2 = this.gates.iterator();
        while (it2.hasNext()) {
            Gate next2 = it2.next();
            float x2 = f + ((next2.getX() - this.c.minX) * f5);
            float y2 = f2 + ((next2.getY() - this.c.minY) * f6);
            next2.drawStatic(batch, x2, y2, f5, f6);
            next2.drawBatch(batch, deltaTime, x2, y2, f5, f6);
        }
    }

    public final void addAll(Selection selection) {
        Array.ArrayIterator<Tile> it = selection.tiles.iterator();
        while (it.hasNext()) {
            addTile(it.next());
        }
        Array.ArrayIterator<Gate> it2 = selection.gates.iterator();
        while (it2.hasNext()) {
            addGate(it2.next());
        }
    }

    public final boolean addTile(Tile tile) {
        Array.ArrayIterator<Tile> it = this.tiles.iterator();
        while (it.hasNext()) {
            Tile next = it.next();
            if (next.getX() == tile.getX() && next.getY() == tile.getY()) {
                return false;
            }
        }
        this.tiles.add(tile.cloneTile());
        this.f3934b = true;
        return true;
    }

    public final boolean addGate(Gate gate) {
        Array.ArrayIterator<Gate> it = this.gates.iterator();
        while (it.hasNext()) {
            Gate next = it.next();
            if (next.getX() == gate.getX() && next.getY() == gate.getY() && next.isLeftSide() == gate.isLeftSide()) {
                return false;
            }
        }
        this.gates.add(gate.cloneGate());
        this.f3934b = true;
        return true;
    }

    public final boolean removeTile(Tile tile) {
        for (int i = 0; i < this.tiles.size; i++) {
            Tile tile2 = this.tiles.get(i);
            if (tile2.getX() == tile.getX() && tile2.getY() == tile.getY()) {
                this.tiles.removeIndex(i);
                this.f3934b = true;
                return true;
            }
        }
        return false;
    }

    public final boolean containsTile(Tile tile) {
        for (int i = 0; i < this.tiles.size; i++) {
            Tile tile2 = this.tiles.get(i);
            if (tile2.getX() == tile.getX() && tile2.getY() == tile.getY() && tile.sameAs(tile2)) {
                return true;
            }
        }
        return false;
    }

    public final boolean containsGate(Gate gate) {
        for (int i = 0; i < this.gates.size; i++) {
            Gate gate2 = this.gates.get(i);
            if (gate2.getX() == gate.getX() && gate2.getY() == gate.getY() && gate2.isLeftSide() == gate.isLeftSide() && gate.sameAs(gate2)) {
                return true;
            }
        }
        return false;
    }

    public final boolean removeGate(Gate gate) {
        for (int i = 0; i < this.gates.size; i++) {
            Gate gate2 = this.gates.get(i);
            if (gate2.getX() == gate.getX() && gate2.getY() == gate.getY() && gate2.isLeftSide() == gate.isLeftSide()) {
                this.gates.removeIndex(i);
                this.f3934b = true;
                return true;
            }
        }
        return false;
    }

    public final Item getCurrentItem() {
        if (this.gates.size != 0) {
            return Item.D.F_GATE.create(this.gates.first());
        }
        return Item.D.F_TILE.create(this.tiles.first());
    }

    public final String toString() {
        return "Selection (count: " + count() + ", tiles: " + this.tiles.toString(",") + ", gates: " + this.gates.toString(",") + ")";
    }
}
