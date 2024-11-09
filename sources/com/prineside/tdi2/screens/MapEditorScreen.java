package com.prineside.tdi2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.lib.jse.CoerceJavaToLua;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.UserMap;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.events.game.MapSizeChange;
import com.prineside.tdi2.events.game.Render;
import com.prineside.tdi2.events.mapEditor.MapValidationFail;
import com.prineside.tdi2.systems.MapEditorSystem;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/MapEditorScreen.class */
public class MapEditorScreen extends Screen {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2790a = TLog.forClass(MapEditorScreen.class);
    public GameSystemProvider S;

    /* renamed from: b, reason: collision with root package name */
    private UserMap f2791b;
    private BasicLevel c;

    public MapEditorScreen(UserMap userMap) {
        this.f2791b = userMap;
        a();
    }

    public MapEditorScreen(BasicLevel basicLevel) {
        this.c = basicLevel;
        a();
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void show() {
        MapEditorSystem.BackedUpMapInfo backUpInfo = MapEditorSystem.getBackUpInfo();
        if (backUpInfo != null) {
            Threads.i().postRunnable(() -> {
                Dialog.i().showConfirmWithCallbacks("Unsaved backup for map \"" + backUpInfo.mapName + "\" found, would you like to restore and edit that map? Saying \"No\" will delete the backup.", () -> {
                    try {
                        backUpInfo.startEditor();
                    } catch (Exception e) {
                        f2790a.e("failed to start map editor for backup", e);
                    }
                }, MapEditorSystem::deleteBackupFile);
            });
        }
    }

    private void a() {
        Map cpy;
        Game.i.uiManager.hideAllComponents();
        Game.i.musicManager.continuePlayingMenuMusicTrack();
        this.S = new GameSystemProvider(new GameSystemProvider.SystemsConfig(GameSystemProvider.SystemsConfig.Setup.MAP_EDITOR, false));
        this.S.createSystems();
        Game.i.scriptManager.global.getGlobals().set("S", CoerceJavaToLua.coerce(this.S));
        if (this.f2791b != null) {
            this.S._mapEditor.basicLevelEditor = false;
            this.S._mapEditor.userMap = this.f2791b;
            this.S._inventory.initAddItems(Game.i.progressManager.getItemsByCategory(ItemCategoryType.MAP_EDITOR));
            cpy = this.f2791b.map.cpy();
            for (int i = 0; i < cpy.getHeight(); i++) {
                for (int i2 = 0; i2 < cpy.getWidth(); i2++) {
                    Tile tile = cpy.getTile(i2, i);
                    if (tile != null && !this.S._inventory.remove(Item.D.F_TILE.create(tile))) {
                        cpy.setTile(i2, i, null);
                    }
                }
            }
            for (int i3 = 0; i3 <= cpy.getHeight(); i3++) {
                for (int i4 = 0; i4 <= cpy.getWidth(); i4++) {
                    boolean[] zArr = {false, true};
                    for (int i5 = 0; i5 < 2; i5++) {
                        boolean z = zArr[i5];
                        Gate gate = cpy.getGate(i4, i3, z);
                        if (gate != null && !this.S._inventory.remove(Item.D.F_GATE.create(gate))) {
                            cpy.setGate(i4, i3, z, null);
                        }
                    }
                }
            }
            int intValue = Game.i.gameValueManager.getSnapshot().getIntValue(GameValueType.USER_MAP_MAX_SIZE);
            if (cpy.getWidth() < intValue || cpy.getHeight() < intValue) {
                Map cpy2 = cpy.cpy();
                cpy = cpy2;
                cpy2.setSize(intValue, intValue);
            }
        } else {
            this.S._mapEditor.basicLevelEditor = true;
            this.S._mapEditor.basicLevel = this.c;
            this.S._inventory.initAddItems(Game.i.progressManager.getItemsByCategory(ItemCategoryType.MAP_EDITOR));
            this.S._inventory.setStaticMode(true);
            cpy = this.c.getMap().cpy();
        }
        this.S.pathfinding.throwExceptionOnMissingPath = false;
        this.S.map.setMap(cpy);
        this.S.map.drawPathTraces = true;
        this.S._mapRendering.drawMapGrid = true;
        this.S.setupSystems();
        this.S.postSetupSystems();
        this.S.events.getListeners(MapSizeChange.class).add(mapSizeChange -> {
            this.S._input.getCameraController().setMapSize(this.S.map.getMap().getWidth() << 7, this.S.map.getMap().getHeight() << 7);
        }).setDescription("MapEditorScreen - updates camera controller map size");
        this.S.events.getListeners(MapValidationFail.class).add(mapValidationFail -> {
            Map.InvalidMapException exception = mapValidationFail.getException();
            f2790a.e(exception.getFixHintMessage(), exception);
            Dialog.i().showAlert(exception.getFixHintMessage());
        }).setDescription("MapEditorScreen - shows validation error in the ui");
        this.S._mapRendering.setDrawMode(MapRenderingSystem.DrawMode.MAP_EDITOR);
        this.S._pathRendering.showAllPathTraces(true);
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f) {
        Color color = Game.i.assetManager.getColor("game_background");
        Gdx.gl.glClearColor(color.r, color.g, color.f888b, color.f889a);
        Gdx.gl.glClear(16640);
        if (Game.i.settingsManager.isEscButtonJustPressed() && !this.S._mapEditor.mapChanged) {
            this.S._mapEditor.goToPreviousScreen();
            return;
        }
        this.S.updateSystems();
        this.S.events.trigger(new Render(f, f));
        Game.i.renderingManager.stopAnyBatchDrawing();
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.scriptManager.global.getGlobals().set("S", LuaValue.NIL);
        this.S.dispose();
        this.S = null;
        this.c = null;
    }
}
