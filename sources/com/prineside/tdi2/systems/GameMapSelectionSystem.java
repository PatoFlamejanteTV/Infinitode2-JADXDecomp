package com.prineside.tdi2.systems;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.MapElementPos;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.events.game.MapElementHover;
import com.prineside.tdi2.events.game.MapElementSelect;
import com.prineside.tdi2.events.game.MouseClick;
import com.prineside.tdi2.events.game.MouseMove;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.utils.NAGS;
import java.util.Objects;

@NAGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameMapSelectionSystem.class */
public final class GameMapSelectionSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    @Null
    private MapElementPos f2942a;

    /* renamed from: b, reason: collision with root package name */
    @Null
    private MapElementPos f2943b;

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            this.S.events.getListeners(MouseMove.class).add(mouseMove -> {
                a(mouseMove.getMapX(), mouseMove.getMapY(), false);
            });
        }
        this.S.events.getListeners(MouseClick.class).add(mouseClick -> {
            a(mouseClick.getMapX(), mouseClick.getMapY(), true);
        });
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MAP_RENDERING_GAME_SELECTION, false, (batch, f, f2, f3) -> {
            drawMapHoverSelect(batch);
        }).setName("MapRendering-postDrawBatch"));
    }

    public final void drawMapHoverSelect(Batch batch) {
        Tile selectedTile = getSelectedTile();
        Tile hoveredTile = getHoveredTile();
        Gate selectedGate = getSelectedGate();
        Gate hoveredGate = getHoveredGate();
        if (selectedTile != null) {
            drawTileSelection(batch, selectedTile.getX(), selectedTile.getY());
        } else if (selectedGate != null) {
            drawGateSelection(batch, selectedGate.getX(), selectedGate.getY(), selectedGate.isLeftSide());
        }
        if (hoveredGate != null && hoveredGate != selectedGate) {
            drawGateHover(batch, hoveredGate.getX(), hoveredGate.getY(), hoveredGate.isLeftSide());
        } else if (hoveredTile != null && hoveredTile != selectedTile) {
            drawTileHover(batch, hoveredTile.getX(), hoveredTile.getY());
        }
    }

    public static void drawTileHover(Batch batch, int i, int i2) {
        batch.draw(AssetManager.TextureRegions.i().tileOutlineHover, ((i << 7) + 64) - 76.0f, ((i2 << 7) + 64) - 76.0f, 152.0f, 152.0f);
    }

    public static void drawTileSelection(Batch batch, int i, int i2) {
        batch.draw(AssetManager.TextureRegions.i().tileOutlineActive, ((i << 7) + 64) - 76.0f, ((i2 << 7) + 64) - 76.0f, 152.0f, 152.0f);
    }

    public static void drawGateHover(Batch batch, int i, int i2, boolean z) {
        if (z) {
            batch.draw(AssetManager.TextureRegions.i().gateOutlineVerticalHover, (i << 7) - 25.0f, ((i2 << 7) + 64) - 81.0f, 50.0f, 162.0f);
        } else {
            batch.draw(AssetManager.TextureRegions.i().gateOutlineHorizontalHover, ((i << 7) + 64) - 81.0f, (i2 << 7) - 25.0f, 162.0f, 50.0f);
        }
    }

    public static void drawGateSelection(Batch batch, int i, int i2, boolean z) {
        if (z) {
            batch.draw(AssetManager.TextureRegions.i().gateOutlineVerticalActive, (i << 7) - 25.0f, ((i2 << 7) + 64) - 81.0f, 50.0f, 162.0f);
        } else {
            batch.draw(AssetManager.TextureRegions.i().gateOutlineHorizontalActive, ((i << 7) + 64) - 81.0f, (i2 << 7) - 25.0f, 162.0f, 50.0f);
        }
    }

    private void a(float f, float f2, boolean z) {
        Map map = this.S.map.getMap();
        Gate gateByMapPos = map.getGateByMapPos(f, f2);
        if (gateByMapPos != null) {
            if (z) {
                setSelectedGateAtPos(gateByMapPos.getX(), gateByMapPos.getY(), gateByMapPos.isLeftSide());
                return;
            } else {
                setHoveredGateAtPos(gateByMapPos.getX(), gateByMapPos.getY(), gateByMapPos.isLeftSide());
                return;
            }
        }
        Tile tileByMapPos = map.getTileByMapPos(f, f2);
        if (tileByMapPos == null) {
            if (z) {
                disableSelection();
                return;
            } else {
                disableHover();
                return;
            }
        }
        if (z) {
            setSelectedTileAtPos(tileByMapPos.getX(), tileByMapPos.getY());
        } else {
            setHoveredTileAtPos(tileByMapPos.getX(), tileByMapPos.getY());
        }
    }

    private void a(@Null MapElementPos mapElementPos) {
        if (!Objects.equals(mapElementPos, this.f2942a)) {
            MapElementPos mapElementPos2 = this.f2942a;
            this.f2942a = mapElementPos;
            if (((MapElementSelect) this.S.events.trigger(new MapElementSelect(mapElementPos2, mapElementPos))).isCancelled()) {
                this.f2942a = mapElementPos2;
                this.S.events.trigger(new MapElementSelect(mapElementPos, mapElementPos2));
            }
        }
    }

    private void b(@Null MapElementPos mapElementPos) {
        if (!Objects.equals(mapElementPos, this.f2943b)) {
            MapElementPos mapElementPos2 = this.f2943b;
            this.f2943b = mapElementPos;
            if (((MapElementHover) this.S.events.trigger(new MapElementHover(mapElementPos2, mapElementPos))).isCancelled()) {
                this.f2943b = mapElementPos2;
                this.S.events.trigger(new MapElementHover(mapElementPos, mapElementPos2));
            }
        }
    }

    public final void disableSelection() {
        a((MapElementPos) null);
    }

    public final void disableHover() {
        b(null);
    }

    public final void setSelectedTile(@Null Tile tile) {
        if (tile == null) {
            disableSelection();
        } else {
            setSelectedTileAtPos(tile.getX(), tile.getY());
        }
    }

    public final void setSelectedTileAtPos(int i, int i2) {
        Tile tile = this.S.map.getMap().getTile(i, i2);
        Tile tile2 = tile;
        if (tile != null && !tile2.canBeSelected()) {
            tile2 = null;
        }
        if (tile2 == null) {
            disableSelection();
        } else {
            a(new Tile.Pos(tile2.getX(), tile2.getY()));
        }
    }

    public final void setHoveredTile(@Null Tile tile) {
        if (tile == null) {
            disableHover();
        } else {
            setHoveredTileAtPos(tile.getX(), tile.getY());
        }
    }

    public final void setHoveredTileAtPos(int i, int i2) {
        Tile tile = this.S.map.getMap().getTile(i, i2);
        Tile tile2 = tile;
        if (tile != null && !tile2.canBeSelected()) {
            tile2 = null;
        }
        if (tile2 == null) {
            disableHover();
        } else {
            b(new Tile.Pos(tile2.getX(), tile2.getY()));
        }
    }

    @Null
    public final Tile getSelectedTile() {
        if (this.f2942a instanceof Tile.Pos) {
            return this.S.map.getMap().getTileAtPos((Tile.Pos) this.f2942a);
        }
        return null;
    }

    @Null
    public final Tile getHoveredTile() {
        if (this.f2943b instanceof Tile.Pos) {
            return this.S.map.getMap().getTileAtPos((Tile.Pos) this.f2943b);
        }
        return null;
    }

    public final void setSelectedGate(@Null Gate gate) {
        if (gate == null) {
            disableSelection();
        } else {
            setSelectedGateAtPos(gate.getX(), gate.getY(), gate.isLeftSide());
        }
    }

    public final void setSelectedGateAtPos(int i, int i2, boolean z) {
        Gate gate = this.S.map.getMap().getGate(i, i2, z);
        if (gate == null) {
            disableSelection();
        } else {
            a(new Gate.Pos(gate.getX(), gate.getY(), gate.isLeftSide()));
        }
    }

    public final void setHoveredGate(@Null Gate gate) {
        if (gate == null) {
            disableSelection();
        } else {
            setHoveredGateAtPos(gate.getX(), gate.getY(), gate.isLeftSide());
        }
    }

    public final void setHoveredGateAtPos(int i, int i2, boolean z) {
        Gate gate = this.S.map.getMap().getGate(i, i2, z);
        if (gate == null) {
            disableHover();
        } else {
            b(new Gate.Pos(gate.getX(), gate.getY(), gate.isLeftSide()));
        }
    }

    @Null
    public final Gate getSelectedGate() {
        if (this.f2942a instanceof Gate.Pos) {
            return this.S.map.getMap().getGateAtPos((Gate.Pos) this.f2942a);
        }
        return null;
    }

    @Null
    public final Gate getHoveredGate() {
        if (this.f2943b instanceof Gate.Pos) {
            return this.S.map.getMap().getGateAtPos((Gate.Pos) this.f2943b);
        }
        return null;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "GameMapSelection";
    }
}
