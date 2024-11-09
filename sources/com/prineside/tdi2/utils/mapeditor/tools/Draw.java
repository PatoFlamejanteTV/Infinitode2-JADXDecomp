package com.prineside.tdi2.utils.mapeditor.tools;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.systems.MapEditorSystem;
import com.prineside.tdi2.ui.components.MapEditorUi;
import com.prineside.tdi2.utils.IntRectangle;
import com.prineside.tdi2.utils.mapeditor.Selection;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/mapeditor/tools/Draw.class */
public class Draw implements MapEditorSystem.Tool {

    /* renamed from: a, reason: collision with root package name */
    private GameSystemProvider f3937a;

    /* renamed from: b, reason: collision with root package name */
    private MapEditorUi.ToolButton f3938b;
    private Selection c;
    private MapEditorSystem.HistoryImprint d;
    private final Gate e = Game.i.gateManager.F.BARRIER_TYPE.create();
    private static final Vector2 f = new Vector2();

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public void setup(GameSystemProvider gameSystemProvider) {
        this.f3937a = gameSystemProvider;
        this.f3938b = new MapEditorUi.ToolButton(Game.i.assetManager.getQuad("mapeditor.tools.draw"), () -> {
            gameSystemProvider._mapEditor.selectTool(this);
        }, null);
        gameSystemProvider._mapEditorUi.mainUi.addToolButton(this.f3938b);
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public MapEditorUi.PreparedTooltip getTooltip() {
        return new MapEditorUi.PreparedTooltip("map_editor_tool_draw", this.f3938b, Game.i.localeManager.i18n.get("map_editor_tooltip_draw_tool"));
    }

    public void paintGate(Gate gate, int i, int i2, boolean z) {
        if (this.f3937a._inventory.removeMany(Item.D.F_GATE.create(gate), 1)) {
            Gate gate2 = this.f3937a.map.getMap().getGate(i, i2, z);
            if (gate2 != null) {
                this.f3937a._inventory.addGate(gate2, 1);
            }
            this.f3937a.map.setGate(i, i2, z, gate.cloneGate());
        }
    }

    public void paintTile(Tile tile, int i, int i2) {
        if (this.f3937a._inventory.removeMany(Item.D.F_TILE.create(tile), 1)) {
            Tile tile2 = this.f3937a.map.getMap().getTile(i, i2);
            if (tile2 != null) {
                this.f3937a._inventory.addTile(tile2, 1);
            }
            this.f3937a.map.setTile(i, i2, tile.cloneTile());
        }
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public void enabled(MapEditorSystem.Tool tool) {
        this.f3938b.setActive(true);
        this.f3937a._input.getCameraController().dragButtonIndices.clear();
        this.f3937a._input.getCameraController().dragButtonIndices.add(1);
        this.f3937a._input.setupInputMultiplexer(true, true, true).addProcessor(new InputAdapter() { // from class: com.prineside.tdi2.utils.mapeditor.tools.Draw.1

            /* renamed from: a, reason: collision with root package name */
            private int f3939a = -1;

            @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
            public boolean touchDown(int i, int i2, int i3, int i4) {
                if (i4 == 0) {
                    this.f3939a = -1;
                    Draw.this.c = Draw.this.f3937a._mapEditor.selection.cpy();
                    Draw.this.d = Draw.this.f3937a._mapEditor.startActionRecord();
                    a(i, i2);
                    return false;
                }
                return false;
            }

            @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
            public boolean touchUp(int i, int i2, int i3, int i4) {
                if (Draw.this.d != null) {
                    Draw.this.f3937a._mapEditor.finishActionRecord(Draw.this.d);
                    Draw.this.d = null;
                }
                Draw.this.c = null;
                return false;
            }

            private void a(float f2, float f3) {
                int posToCell;
                Draw.f.set(f2, f3);
                Draw.this.f3937a._input.getCameraController().screenToStage(Draw.f);
                boolean isStagePointOnInventory = Draw.this.f3937a._mapEditorUi.inventoryMenu.isStagePointOnInventory(Draw.f.x, Draw.f.y);
                Draw.this.f3937a._input.getCameraController().stageToMap(Draw.f);
                if (!isStagePointOnInventory) {
                    posToCell = ((1627 + Map.posToCell(Draw.f.x)) * 1627) + Map.posToCell(Draw.f.y);
                    if (Draw.this.c.gates.size != 0 && Draw.this.f3937a.map.getMap().fitGateToMapPos(Draw.f.x, Draw.f.y, Draw.this.e)) {
                        posToCell = (((((posToCell * 1627) + Draw.this.e.getX()) * 1627) + Draw.this.e.getY()) * 1627) + (Draw.this.e.isLeftSide() ? 0 : 1);
                    }
                } else {
                    posToCell = 6961;
                }
                if (this.f3939a == posToCell) {
                    return;
                }
                this.f3939a = posToCell;
                if (!isStagePointOnInventory) {
                    Map map = Draw.this.f3937a.map.getMap();
                    if (Draw.this.c.count() != 1 || Draw.this.c.gates.size != 1) {
                        float f4 = Draw.f.x;
                        float f5 = Draw.f.y;
                        int floor = MathUtils.floor(f4 * 0.0078125f);
                        int floor2 = MathUtils.floor(f5 * 0.0078125f);
                        IntRectangle dimensions = Draw.this.c.dimensions();
                        Array.ArrayIterator<Tile> it = Draw.this.c.tiles.iterator();
                        while (it.hasNext()) {
                            Tile next = it.next();
                            int x = (next.getX() - dimensions.minX) + floor;
                            int y = (next.getY() - dimensions.minY) + floor2;
                            if (x >= 0 && x < map.getWidth() && y >= 0 && y < map.getHeight() && Draw.this.f3937a._inventory.contains(Item.D.F_TILE.create(next), 1) && !next.sameAsWithExtras(Draw.this.f3937a.map.getMap().getTile(x, y))) {
                                Draw.this.paintTile(next, x, y);
                            }
                        }
                        Array.ArrayIterator<Gate> it2 = Draw.this.c.gates.iterator();
                        while (it2.hasNext()) {
                            Gate next2 = it2.next();
                            int x2 = (next2.getX() - dimensions.minX) + floor;
                            int y2 = (next2.getY() - dimensions.minY) + floor2;
                            if (x2 >= 0 && x2 <= map.getWidth() && y2 >= 0 && y2 <= map.getHeight() && Draw.this.f3937a._inventory.contains(Item.D.F_GATE.create(next2), 1) && !next2.sameAs(Draw.this.f3937a.map.getMap().getGate(x2, y2, next2.isLeftSide()))) {
                                Draw.this.paintGate(next2, x2, y2, next2.isLeftSide());
                            }
                        }
                        return;
                    }
                    Gate first = Draw.this.c.gates.first();
                    if (Draw.this.f3937a.map.getMap().fitGateToMapPos(Draw.f.x, Draw.f.y, Draw.this.e) && Draw.this.f3937a._inventory.contains(Item.D.F_GATE.create(first), 1) && !first.sameAs(Draw.this.f3937a.map.getMap().getGate(Draw.this.e.getX(), Draw.this.e.getY(), Draw.this.e.isLeftSide()))) {
                        Draw.this.paintGate(first, Draw.this.e.getX(), Draw.this.e.getY(), Draw.this.e.isLeftSide());
                    }
                }
            }

            @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
            public boolean touchDragged(int i, int i2, int i3) {
                if (Draw.this.c != null) {
                    a(i, i2);
                    return false;
                }
                return false;
            }
        });
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public void disabled() {
        this.f3938b.setActive(false);
    }
}
