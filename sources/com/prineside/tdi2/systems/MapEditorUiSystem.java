package com.prineside.tdi2.systems;

import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.systems.MapEditorSystem;
import com.prineside.tdi2.ui.components.DraggingItemHelper;
import com.prineside.tdi2.ui.components.MapEditorInventoryMenu;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.ui.components.MapEditorUi;
import com.prineside.tdi2.ui.components.MapShiftButtons;
import com.prineside.tdi2.utils.NAGS;

@NAGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapEditorUiSystem.class */
public final class MapEditorUiSystem extends GameSystem {
    public MapEditorUi mainUi;
    public MapShiftButtons mapShiftButtons;
    public DraggingItemHelper draggingItemHelper;
    public MapEditorInventoryMenu inventoryMenu;
    public MapEditorItemInfoMenu itemInfoMenu;

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.mapShiftButtons = new MapShiftButtons(this.S);
        if (this.S._mapEditor.basicLevelEditor) {
            this.mapShiftButtons.setMapSizeChangesAllowed(true);
        }
        this.mapShiftButtons.addListener(new MapShiftButtons.MapShiftButtonsListener() { // from class: com.prineside.tdi2.systems.MapEditorUiSystem.1
            @Override // com.prineside.tdi2.ui.components.MapShiftButtons.MapShiftButtonsListener
            public void shifted(MapShiftButtons.Direction direction) {
                MapEditorSystem.HistoryImprint startActionRecord = MapEditorUiSystem.this.S._mapEditor.startActionRecord();
                MapEditorUiSystem.this.S._mapEditor.shiftMap(direction);
                MapEditorUiSystem.this.S._mapEditor.finishActionRecord(startActionRecord);
            }

            @Override // com.prineside.tdi2.ui.components.MapShiftButtons.MapShiftButtonsListener
            public void expanded(MapShiftButtons.Direction direction) {
                MapEditorSystem.HistoryImprint startActionRecord = MapEditorUiSystem.this.S._mapEditor.startActionRecord();
                MapEditorUiSystem.this.S._mapEditor.expandMap(direction);
                MapEditorUiSystem.this.S._mapEditor.finishActionRecord(startActionRecord);
            }

            @Override // com.prineside.tdi2.ui.components.MapShiftButtons.MapShiftButtonsListener
            public void reduced(MapShiftButtons.Direction direction) {
                MapEditorSystem.HistoryImprint startActionRecord = MapEditorUiSystem.this.S._mapEditor.startActionRecord();
                MapEditorUiSystem.this.S._mapEditor.reduceMap(direction);
                MapEditorUiSystem.this.S._mapEditor.finishActionRecord(startActionRecord);
            }
        });
        this.mainUi = new MapEditorUi(this.S);
        this.draggingItemHelper = new DraggingItemHelper(this.S);
        this.itemInfoMenu = new MapEditorItemInfoMenu(this.S);
        this.inventoryMenu = new MapEditorInventoryMenu(this.S);
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        this.draggingItemHelper.dispose();
        this.mainUi.dispose();
        this.mapShiftButtons.dispose();
        this.itemInfoMenu.dispose();
        this.inventoryMenu.dispose();
        this.mainUi = null;
        this.mapShiftButtons = null;
        this.draggingItemHelper = null;
        this.inventoryMenu = null;
        this.itemInfoMenu = null;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "MapEditorUi";
    }
}
