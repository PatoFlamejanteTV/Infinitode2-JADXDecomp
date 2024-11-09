package com.prineside.tdi2.utils.mapeditor.tools;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.systems.MapEditorSystem;
import com.prineside.tdi2.ui.components.MapEditorUi;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/mapeditor/tools/Remove.class */
public class Remove implements MapEditorSystem.Tool {

    /* renamed from: a, reason: collision with root package name */
    private GameSystemProvider f3948a;

    /* renamed from: b, reason: collision with root package name */
    private MapEditorUi.ToolButton f3949b;
    private MapEditorSystem.HistoryImprint c;
    private static final Vector2 d = new Vector2();

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public void setup(GameSystemProvider gameSystemProvider) {
        this.f3948a = gameSystemProvider;
        this.f3949b = new MapEditorUi.ToolButton(Game.i.assetManager.getQuad("mapeditor.tools.remove"), () -> {
            gameSystemProvider._mapEditor.selectTool(this);
        }, null);
        gameSystemProvider._mapEditorUi.mainUi.addToolButton(this.f3949b);
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public MapEditorUi.PreparedTooltip getTooltip() {
        return new MapEditorUi.PreparedTooltip("map_editor_tool_move", this.f3949b, Game.i.localeManager.i18n.get("map_editor_tooltip_remove_tool"));
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public void enabled(MapEditorSystem.Tool tool) {
        this.f3949b.setActive(true);
        this.f3948a._input.getCameraController().dragButtonIndices.clear();
        this.f3948a._input.getCameraController().dragButtonIndices.add(1);
        this.f3948a._input.setupInputMultiplexer(true, true, true).addProcessor(new InputAdapter() { // from class: com.prineside.tdi2.utils.mapeditor.tools.Remove.1

            /* renamed from: a, reason: collision with root package name */
            private boolean f3950a;

            @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
            public boolean touchDown(int i, int i2, int i3, int i4) {
                if (i4 == 0) {
                    this.f3950a = true;
                    Remove.this.c = Remove.this.f3948a._mapEditor.startActionRecord();
                    a(i, i2);
                    return false;
                }
                return false;
            }

            @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
            public boolean touchUp(int i, int i2, int i3, int i4) {
                if (Remove.this.c != null) {
                    Remove.this.f3948a._mapEditor.finishActionRecord(Remove.this.c);
                }
                this.f3950a = false;
                return false;
            }

            private void a(float f, float f2) {
                Remove.d.set(f, f2);
                Remove.this.f3948a._input.getCameraController().screenToStage(Remove.d);
                if (!Remove.this.f3948a._mapEditorUi.inventoryMenu.isStagePointOnInventory(Remove.d.x, Remove.d.y)) {
                    Remove.this.f3948a._input.getCameraController().stageToMap(Remove.d);
                    Map map = Remove.this.f3948a.map.getMap();
                    boolean z = false;
                    Tile tileByMapPos = map.getTileByMapPos(Remove.d.x, Remove.d.y);
                    if (tileByMapPos != null) {
                        Remove.this.f3948a._inventory.addTile(tileByMapPos, 1);
                        if (Remove.this.f3948a._mapEditor.selection.removeTile(tileByMapPos)) {
                            z = true;
                        }
                        Remove.this.f3948a.map.setTile(tileByMapPos.getX(), tileByMapPos.getY(), null);
                    }
                    Gate gateByMapPos = map.getGateByMapPos(Remove.d.x, Remove.d.y);
                    if (gateByMapPos != null) {
                        Remove.this.f3948a._inventory.addGate(gateByMapPos, 1);
                        if (Remove.this.f3948a._mapEditor.selection.removeGate(gateByMapPos)) {
                            z = true;
                        }
                        Remove.this.f3948a.map.setGate(gateByMapPos.getX(), gateByMapPos.getY(), gateByMapPos.isLeftSide(), null);
                    }
                    if (z) {
                        Remove.this.f3948a._mapEditor.notifySelectionChanged();
                    }
                }
            }

            @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
            public boolean touchDragged(int i, int i2, int i3) {
                if (this.f3950a) {
                    a(i, i2);
                    return false;
                }
                return false;
            }
        });
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public void disabled() {
        this.f3949b.setActive(false);
    }
}
