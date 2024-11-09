package com.prineside.tdi2.utils.mapeditor.tools;

import com.badlogic.gdx.Gdx;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.MouseClick;
import com.prineside.tdi2.systems.MapEditorSystem;
import com.prineside.tdi2.ui.components.MapEditorUi;
import com.prineside.tdi2.utils.mapeditor.Selection;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/mapeditor/tools/View.class */
public class View implements Listener<MouseClick>, MapEditorSystem.Tool {

    /* renamed from: a, reason: collision with root package name */
    private GameSystemProvider f3955a;

    /* renamed from: b, reason: collision with root package name */
    private MapEditorUi.ToolButton f3956b;

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public void setup(GameSystemProvider gameSystemProvider) {
        this.f3955a = gameSystemProvider;
        this.f3956b = new MapEditorUi.ToolButton(Game.i.assetManager.getQuad("mapeditor.tools.view"), () -> {
            gameSystemProvider._mapEditor.selectTool(this);
        }, null);
        gameSystemProvider._mapEditorUi.mainUi.addToolButton(this.f3956b);
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public MapEditorUi.PreparedTooltip getTooltip() {
        return new MapEditorUi.PreparedTooltip("map_editor_tool_view", this.f3956b, Game.i.localeManager.i18n.get("map_editor_tooltip_view_tool"));
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public void enabled(MapEditorSystem.Tool tool) {
        this.f3956b.setActive(true);
        this.f3955a.events.getListeners(MouseClick.class).add(this).setDescription("Tool.View");
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public void disabled() {
        this.f3956b.setActive(false);
        this.f3955a.events.getListeners(MouseClick.class).remove(this);
    }

    @Override // com.prineside.tdi2.events.Listener
    public void handleEvent(MouseClick mouseClick) {
        Selection selection = this.f3955a._mapEditor.selection;
        if (selection.isFromInventory()) {
            this.f3955a._mapEditor.deselectAll();
            selection.setFromInventory(false);
        }
        boolean isKeyPressed = Gdx.input.isKeyPressed(129);
        if (!isKeyPressed) {
            this.f3955a._mapEditor.deselectAll();
        }
        Gate gateByMapPos = this.f3955a.map.getMap().getGateByMapPos(mouseClick.getMapX(), mouseClick.getMapY());
        if (gateByMapPos != null) {
            if (!isKeyPressed || !selection.gates.contains(gateByMapPos, true)) {
                selection.gates.add(gateByMapPos);
            } else {
                selection.gates.removeValue(gateByMapPos, true);
            }
        } else {
            Tile tileByMapPos = this.f3955a.map.getMap().getTileByMapPos(mouseClick.getMapX(), mouseClick.getMapY());
            if (tileByMapPos != null) {
                if (isKeyPressed && selection.containsTile(tileByMapPos)) {
                    selection.removeTile(tileByMapPos);
                } else {
                    selection.addTile(tileByMapPos);
                }
            }
        }
        this.f3955a._mapEditor.notifySelectionChanged();
    }
}
