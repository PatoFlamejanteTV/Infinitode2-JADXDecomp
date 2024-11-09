package com.prineside.tdi2.events.game;

import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/TileChange.class */
public final class TileChange extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final int f2042a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2043b;

    @Null
    private final Tile c;

    @Null
    private final Tile d;

    public TileChange(int i, int i2, @Null Tile tile, @Null Tile tile2) {
        this.f2042a = i;
        this.f2043b = i2;
        this.c = tile;
        this.d = tile2;
    }

    public final int getX() {
        return this.f2042a;
    }

    public final int getY() {
        return this.f2043b;
    }

    public final Tile getOldTile() {
        return this.c;
    }

    public final Tile getNewTile() {
        return this.d;
    }
}
