package com.prineside.tdi2.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.MapElementPos;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.UserMap;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.events.EventListeners;
import com.prineside.tdi2.events.game.MouseMove;
import com.prineside.tdi2.events.game.Render;
import com.prineside.tdi2.events.mapEditor.HistoryUpdate;
import com.prineside.tdi2.events.mapEditor.MapEditorSelectionChange;
import com.prineside.tdi2.events.mapEditor.MapValidationFail;
import com.prineside.tdi2.items.GateItem;
import com.prineside.tdi2.items.TileItem;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.screens.MapEditorScreen;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.ui.components.MapEditorInventoryMenu;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.ui.components.MapEditorUi;
import com.prineside.tdi2.ui.components.MapShiftButtons;
import com.prineside.tdi2.ui.shared.AbilitySelectionOverlay;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.utils.IntRectangle;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.mapeditor.Selection;
import com.prineside.tdi2.utils.mapeditor.tools.Draw;
import com.prineside.tdi2.utils.mapeditor.tools.Move;
import com.prineside.tdi2.utils.mapeditor.tools.Remove;
import com.prineside.tdi2.utils.mapeditor.tools.SelectRectangle;
import com.prineside.tdi2.utils.mapeditor.tools.View;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapEditorSystem.class */
public final class MapEditorSystem extends GameSystem {
    public static final int HISTORY_SIZE = 30;

    /* renamed from: b, reason: collision with root package name */
    @Null
    private MapElementPos f2977b;
    private Tool d;
    private int f;
    public boolean mapChanged;
    public UserMap userMap;
    public BasicLevel basicLevel;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2976a = TLog.forClass(MapEditorSystem.class);
    public static final Color SELECTION_OUTLINE_COLOR = MaterialColor.LIGHT_BLUE.P500;
    public static final Color SELECTION_FILL_COLOR = MaterialColor.LIGHT_BLUE.P500.cpy().mul(1.0f, 1.0f, 1.0f, 0.07f);
    public final Selection selection = new Selection();
    private final Array<Tool> c = new Array<>();
    private final Array<HistoryImprint> e = new Array<>(true, 1, HistoryImprint.class);
    public InventoryDefaultListener inventoryDefaultListener = new InventoryDefaultListener();
    public boolean basicLevelEditor = false;
    private final AtomicBoolean g = new AtomicBoolean(false);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapEditorSystem$Tool.class */
    public interface Tool {
        void setup(GameSystemProvider gameSystemProvider);

        @Null
        MapEditorUi.PreparedTooltip getTooltip();

        void enabled(Tool tool);

        void disabled();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MAP_RENDERING_MAP_EDITOR_SELECTION, false, (batch, f, f2, f3) -> {
            if (!this.selection.isFromInventory()) {
                IntRectangle dimensions = this.selection.dimensions();
                this.selection.drawOutline(batch, dimensions.minX << 7, dimensions.minY << 7, 1.0f, 1.0f, SELECTION_OUTLINE_COLOR, SELECTION_FILL_COLOR);
            }
            if (this.f2977b != null) {
                Tile hoveredTile = getHoveredTile();
                Gate hoveredGate = getHoveredGate();
                if (hoveredGate != null) {
                    GameMapSelectionSystem.drawGateHover(batch, hoveredGate.getX(), hoveredGate.getY(), hoveredGate.isLeftSide());
                } else if (hoveredTile != null) {
                    GameMapSelectionSystem.drawTileHover(batch, hoveredTile.getX(), hoveredTile.getY());
                }
            }
        }));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MODIFIER_DRAW_BATCH, false, (batch2, f4, f5, f6) -> {
            ModifierSystem.drawBatch(batch2, this.S.map.getMap(), f5, MapRenderingSystem.DrawMode.MAP_EDITOR);
        }).setName("MapEditorSystem-drawModifiersBatch"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MODIFIER_DRAW_BATCH_ADDITIVE, true, (batch3, f7, f8, f9) -> {
            ModifierSystem.drawBatchAdditive(batch3, this.S.map.getMap(), f8, MapRenderingSystem.DrawMode.MAP_EDITOR);
        }).setName("MapEditorSystem-drawModifiersBatchAdditive"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MINER_DRAW_BATCH, false, (batch4, f10, f11, f12) -> {
            MinerSystem.drawBatch(batch4, this.S.map.getMap(), f11, MapRenderingSystem.DrawMode.MAP_EDITOR);
        }).setName("MapEditorSystem-drawMinersBatch"));
        this.S.events.getListeners(MouseMove.class).add(mouseMove -> {
            a(mouseMove.getMapX(), mouseMove.getMapY());
        }).setDescription("MapEditorSystem - handles hover");
        this.S.events.getListeners(Render.class).addWithPriority(render -> {
            c();
        }, EventListeners.PRIORITY_HIGHEST).setDescription("MapEditorSystem - handles input");
    }

    private void b() {
        Threads.i().runAsync(() -> {
            try {
                Map cpy = this.S.map.getMap().cpy();
                Json json = new Json(JsonWriter.OutputType.minimal);
                StringWriter stringWriter = new StringWriter();
                json.setWriter(stringWriter);
                json.writeObjectStart();
                json.writeValue("isBasicLevel", Boolean.valueOf(this.basicLevelEditor));
                if (this.basicLevelEditor) {
                    json.writeValue("mapId", this.basicLevel.name);
                    json.writeValue("mapName", this.basicLevel.name);
                } else {
                    json.writeValue("mapId", this.userMap.id);
                    json.writeValue("mapName", this.userMap.name);
                }
                json.writeValue("timestamp", Long.valueOf(Game.getTimestampMillis()));
                json.writeObjectStart("map");
                cpy.toJson(json);
                json.writeObjectEnd();
                json.writeObjectEnd();
                if (!this.g.get()) {
                    Gdx.files.local("cache/map-editor-backup.json").writeString(stringWriter.toString(), false, "UTF-8");
                }
            } catch (Exception e) {
                if (Game.i.settingsManager.isInDebugDetailedMode()) {
                    f2976a.e("failed to auto-save the map", e);
                }
            }
        });
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postSetup() {
        this.S._mapEditorUi.itemInfoMenu.addListener(new MapEditorItemInfoMenu.MapEditorTileInfoMenuListener() { // from class: com.prineside.tdi2.systems.MapEditorSystem.1
            @Override // com.prineside.tdi2.ui.components.MapEditorItemInfoMenu.MapEditorTileInfoMenuListener
            public void selectedElementModified() {
                if (!MapEditorSystem.this.selection.isFromInventory() && MapEditorSystem.this.selection.count() == 1) {
                    HistoryImprint startActionRecord = MapEditorSystem.this.startActionRecord();
                    if (MapEditorSystem.this.selection.tiles.size != 0) {
                        Tile first = MapEditorSystem.this.selection.tiles.first();
                        MapEditorSystem.this.S.map.setTile(first.getX(), first.getY(), first.cloneTile());
                    } else {
                        Gate first2 = MapEditorSystem.this.selection.gates.first();
                        MapEditorSystem.this.S.map.setGate(first2.getX(), first2.getY(), first2.isLeftSide(), first2.cloneGate());
                    }
                    MapEditorSystem.this.finishActionRecord(startActionRecord);
                }
            }

            @Override // com.prineside.tdi2.ui.components.MapEditorItemInfoMenu.MapEditorTileInfoMenuListener
            public void menuUpdated() {
            }
        });
        View view = new View();
        registerTool(new Remove());
        registerTool(new Draw());
        registerTool(new Move());
        registerTool(new SelectRectangle());
        registerTool(view);
        selectTool(view);
    }

    private void c() {
        if (Gdx.input.isKeyJustPressed(61)) {
            this.S._mapEditorUi.inventoryMenu.getSideMenu().setOffscreen(!this.S._mapEditorUi.inventoryMenu.getSideMenu().isOffscreen());
        }
        if ((Gdx.input.isKeyPressed(129) && Gdx.input.isKeyJustPressed(54)) || (Gdx.input.isKeyJustPressed(129) && Gdx.input.isKeyPressed(54))) {
            if (Gdx.input.isKeyPressed(59)) {
                historyForward();
            } else {
                historyBack();
            }
        }
    }

    public final void registerTool(Tool tool) {
        this.c.add(tool);
        tool.setup(this.S);
    }

    @Null
    public final Tile getHoveredTile() {
        if (this.f2977b instanceof Tile.Pos) {
            return this.S.map.getMap().getTileAtPos((Tile.Pos) this.f2977b);
        }
        return null;
    }

    @Null
    public final Gate getHoveredGate() {
        if (this.f2977b instanceof Gate.Pos) {
            return this.S.map.getMap().getGateAtPos((Gate.Pos) this.f2977b);
        }
        return null;
    }

    private void a(float f, float f2) {
        Map map = this.S.map.getMap();
        Gate gateByMapPos = map.getGateByMapPos(f, f2);
        if (gateByMapPos != null) {
            this.f2977b = new Gate.Pos(gateByMapPos.getX(), gateByMapPos.getY(), gateByMapPos.isLeftSide());
            return;
        }
        Tile tileByMapPos = map.getTileByMapPos(f, f2);
        if (tileByMapPos == null) {
            this.f2977b = null;
        } else {
            this.f2977b = new Tile.Pos(tileByMapPos.getX(), tileByMapPos.getY());
        }
    }

    public final HistoryImprint startActionRecord() {
        return new HistoryImprint(this, (byte) 0);
    }

    public final void finishActionRecord(HistoryImprint historyImprint) {
        Preconditions.checkNotNull(historyImprint, "imprint can not be null");
        historyImprint.a();
        a(historyImprint);
    }

    private void a(HistoryImprint historyImprint) {
        if (!historyImprint.f2980a) {
            f2976a.e("imprint is not sealed", new Object[0]);
            return;
        }
        if (historyImprint.getChangesCount() == 0) {
            f2976a.i("no changes in history imprint, skipping", new Object[0]);
            return;
        }
        if (this.f == 30) {
            this.e.removeIndex(0);
            this.f--;
        }
        this.e.setSize(this.f);
        this.e.add(historyImprint);
        this.f++;
        this.S.events.trigger(new HistoryUpdate());
        this.mapChanged = true;
        b();
    }

    public final void historyBack() {
        if (this.f > 0) {
            HistoryImprint historyImprint = this.e.get(this.f - 1);
            if (!historyImprint.f2980a) {
                f2976a.e("last history imprint is not sealed yet", new Object[0]);
                return;
            }
            historyImprint.revert();
            this.f--;
            this.S.events.trigger(new HistoryUpdate());
            this.mapChanged = true;
        }
    }

    public final boolean hasHistoryBack() {
        return this.f > 0;
    }

    public final boolean hasHistoryForward() {
        return this.f < this.e.size;
    }

    public final Array<HistoryImprint> getHistory() {
        return this.e;
    }

    public final int getHistoryPointer() {
        return this.f;
    }

    public final void historyForward() {
        if (this.f < this.e.size) {
            HistoryImprint historyImprint = this.e.get(this.f);
            if (!historyImprint.f2980a) {
                f2976a.e("last history imprint is not sealed yet", new Object[0]);
                return;
            }
            historyImprint.repeat();
            this.f++;
            this.S.events.trigger(new HistoryUpdate());
            this.mapChanged = true;
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "MapEditor";
    }

    public final boolean selectTool(Tool tool) {
        if (this.d != tool) {
            if (this.d != null) {
                this.d.disabled();
            }
            Tool tool2 = this.d;
            d();
            this.d = tool;
            tool.enabled(tool2);
            return true;
        }
        return false;
    }

    private void d() {
        this.S._mapEditorUi.inventoryMenu.setItemDraggingMode(false);
        this.S._mapEditorUi.inventoryMenu.clearListeners();
        this.S._mapEditorUi.inventoryMenu.addListener(this.inventoryDefaultListener);
        this.S._input.enableAllInput();
        this.S._input.getCameraController().dragButtonIndices.clear();
        this.S._input.getCameraController().dragButtonIndices.add(0, 1);
    }

    public final Tool getTool() {
        return this.d;
    }

    public final Array<Tool> getTools() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MapEditorInventoryMenu.ItemSlot itemSlot) {
        this.selection.clear();
        this.selection.setFromInventory(true);
        Item item = itemSlot.getItemStack().getItem();
        if (item instanceof TileItem) {
            this.selection.addTile(((TileItem) item).tile);
        } else if (item instanceof GateItem) {
            this.selection.addGate(((GateItem) item).gate);
        } else {
            f2976a.e("can't selectItemFromInventory - " + item + " is not a tile/gate", new Object[0]);
        }
        notifySelectionChanged();
    }

    public final void notifySelectionChanged() {
        this.S.events.trigger(new MapEditorSelectionChange());
    }

    public final void deselectAll() {
        this.selection.clear();
        notifySelectionChanged();
    }

    public final boolean startMap() {
        f2976a.i("starting the map...", new Object[0]);
        try {
            this.S.map.getMap().validate();
            saveMap();
            Runnable runnable = () -> {
                if ((this.basicLevel == null ? this.userMap.map : this.basicLevel.getMap()).getTargetTileOrThrow().isDisableAbilities() || !Game.i.abilityManager.isAnyAbilityOpened()) {
                    if (this.basicLevel != null) {
                        Game.i.screenManager.startNewBasicLevel(this.basicLevel, null);
                        return;
                    } else {
                        Game.i.screenManager.startNewUserLevel(this.userMap, null);
                        return;
                    }
                }
                AbilitySelectionOverlay.i().show(() -> {
                }, selectedAbilitiesConfiguration -> {
                    if (this.basicLevel != null) {
                        Game.i.screenManager.startNewBasicLevel(this.basicLevel, selectedAbilitiesConfiguration);
                    } else {
                        Game.i.screenManager.startNewUserLevel(this.userMap, selectedAbilitiesConfiguration);
                    }
                });
            };
            Runnable runnable2 = () -> {
                if (GameStateSystem.savedGameExists()) {
                    Dialog.i().showConfirm(Game.i.localeManager.i18n.get("saved_game_will_be_lost_confirm"), () -> {
                        GameStateSystem.deleteSavedGame();
                        runnable.run();
                    });
                } else {
                    runnable.run();
                }
            };
            runnable2.run();
            return true;
        } catch (Map.InvalidMapException e) {
            this.S.events.trigger(new MapValidationFail(e));
            return false;
        }
    }

    public final void saveMap() {
        if (this.basicLevelEditor) {
            Game.i.basicLevelManager.setMap(this.basicLevel, this.S.map.getMap().cpy());
            return;
        }
        this.userMap.map = this.S.map.getMap().cpy();
        ProgressPrefs.i().requireSave();
    }

    public final void goToPreviousScreen() {
        if (this.mapChanged) {
            Dialog.i().showConfirmWithCallbacks(Game.i.localeManager.i18n.get("edited_map_save_confirm"), () -> {
                saveMap();
                if (this.basicLevelEditor) {
                    Game.i.screenManager.goToLevelSelectScreen();
                } else {
                    Game.i.screenManager.goToCustomMapSelectScreen();
                }
            }, () -> {
                if (this.basicLevelEditor) {
                    Game.i.screenManager.goToLevelSelectScreen();
                } else {
                    Game.i.screenManager.goToCustomMapSelectScreen();
                }
            });
        } else if (this.basicLevelEditor) {
            Game.i.screenManager.goToLevelSelectScreen();
        } else {
            Game.i.screenManager.goToCustomMapSelectScreen();
        }
    }

    public final void expandMap(MapShiftButtons.Direction direction) {
        deselectAll();
        Map map = this.S.map.getMap();
        f2976a.i("expand " + direction.name(), new Object[0]);
        if ((direction == MapShiftButtons.Direction.LEFT || direction == MapShiftButtons.Direction.RIGHT) && map.getWidth() >= 48) {
            return;
        }
        if ((direction == MapShiftButtons.Direction.DOWN || direction == MapShiftButtons.Direction.UP) && map.getHeight() >= 48) {
            return;
        }
        if (direction == MapShiftButtons.Direction.RIGHT) {
            this.S.map.setMapSize(map.getWidth() + 1, map.getHeight());
        } else if (direction == MapShiftButtons.Direction.UP) {
            this.S.map.setMapSize(map.getWidth(), map.getHeight() + 1);
        } else if (direction == MapShiftButtons.Direction.LEFT) {
            this.S.map.setMapSize(map.getWidth() + 1, map.getHeight());
            shiftMap(MapShiftButtons.Direction.RIGHT);
        } else if (direction == MapShiftButtons.Direction.DOWN) {
            this.S.map.setMapSize(map.getWidth(), map.getHeight() + 1);
            shiftMap(MapShiftButtons.Direction.UP);
        }
        this.S._mapRendering.forceTilesRedraw(true);
        this.S.pathfinding.forcePathfindingRebuild();
    }

    public final void reduceMap(MapShiftButtons.Direction direction) {
        Map map = this.S.map.getMap();
        if ((direction == MapShiftButtons.Direction.LEFT || direction == MapShiftButtons.Direction.RIGHT) && map.getWidth() == 1) {
            return;
        }
        if ((direction == MapShiftButtons.Direction.DOWN || direction == MapShiftButtons.Direction.UP) && map.getHeight() == 1) {
            return;
        }
        if (direction == MapShiftButtons.Direction.RIGHT) {
            shiftMap(MapShiftButtons.Direction.RIGHT);
            shiftMap(MapShiftButtons.Direction.LEFT);
            this.S.map.setMapSize(map.getWidth() - 1, map.getHeight());
        } else if (direction == MapShiftButtons.Direction.UP) {
            shiftMap(MapShiftButtons.Direction.UP);
            shiftMap(MapShiftButtons.Direction.DOWN);
            this.S.map.setMapSize(map.getWidth(), map.getHeight() - 1);
        } else if (direction == MapShiftButtons.Direction.LEFT) {
            shiftMap(MapShiftButtons.Direction.LEFT);
            this.S.map.setMapSize(map.getWidth() - 1, map.getHeight());
        } else if (direction == MapShiftButtons.Direction.DOWN) {
            shiftMap(MapShiftButtons.Direction.DOWN);
            this.S.map.setMapSize(map.getWidth(), map.getHeight() - 1);
        }
        this.S._mapRendering.forceTilesRedraw(true);
        this.S.pathfinding.forcePathfindingRebuild();
    }

    public final void shiftMap(MapShiftButtons.Direction direction) {
        deselectAll();
        Map map = this.S.map.getMap();
        switch (direction) {
            case LEFT:
                for (int i = 0; i < map.getHeight(); i++) {
                    Tile tile = map.getTile(0, i);
                    if (tile != null) {
                        this.S._inventory.addTile(tile, 1);
                    }
                    for (int i2 = 1; i2 < map.getWidth(); i2++) {
                        Tile tile2 = map.getTile(i2, i);
                        this.S.map.setTile(i2 - 1, i, tile2 == null ? null : tile2.cloneTile());
                    }
                    this.S.map.setTile(map.getWidth() - 1, i, null);
                }
                for (int i3 = 0; i3 <= map.getHeight(); i3++) {
                    boolean[] zArr = {false, true};
                    for (int i4 = 0; i4 < 2; i4++) {
                        Gate gate = map.getGate(0, i3, zArr[i4]);
                        if (gate != null) {
                            this.S._inventory.addGate(gate, 1);
                        }
                    }
                    for (int i5 = 1; i5 <= map.getWidth(); i5++) {
                        boolean[] zArr2 = {false, true};
                        for (int i6 = 0; i6 < 2; i6++) {
                            boolean z = zArr2[i6];
                            Gate gate2 = map.getGate(i5, i3, z);
                            this.S.map.setGate(i5 - 1, i3, z, gate2 == null ? null : gate2.cloneGate());
                        }
                    }
                    boolean[] zArr3 = {false, true};
                    for (int i7 = 0; i7 < 2; i7++) {
                        this.S.map.setGate(map.getWidth(), i3, zArr3[i7], null);
                    }
                }
                break;
            case RIGHT:
                for (int i8 = 0; i8 < map.getHeight(); i8++) {
                    Tile tile3 = map.getTile(map.getWidth() - 1, i8);
                    if (tile3 != null) {
                        this.S._inventory.addTile(tile3, 1);
                    }
                    for (int width = map.getWidth() - 2; width >= 0; width--) {
                        Tile tile4 = map.getTile(width, i8);
                        this.S.map.setTile(width + 1, i8, tile4 == null ? null : tile4.cloneTile());
                    }
                    this.S.map.setTile(0, i8, null);
                }
                for (int i9 = 0; i9 <= map.getHeight(); i9++) {
                    boolean[] zArr4 = {false, true};
                    for (int i10 = 0; i10 < 2; i10++) {
                        Gate gate3 = map.getGate(map.getWidth(), i9, zArr4[i10]);
                        if (gate3 != null) {
                            this.S._inventory.addGate(gate3, 1);
                        }
                    }
                    Gate gate4 = map.getGate(map.getWidth() - 1, i9, false);
                    if (gate4 != null) {
                        this.S._inventory.addGate(gate4, 1);
                        this.S.map.setGate(map.getWidth() - 1, i9, false, null);
                    }
                    for (int width2 = map.getWidth() - 1; width2 >= 0; width2--) {
                        boolean[] zArr5 = {false, true};
                        for (int i11 = 0; i11 < 2; i11++) {
                            boolean z2 = zArr5[i11];
                            Gate gate5 = map.getGate(width2, i9, z2);
                            this.S.map.setGate(width2 + 1, i9, z2, gate5 == null ? null : gate5.cloneGate());
                        }
                    }
                    boolean[] zArr6 = {false, true};
                    for (int i12 = 0; i12 < 2; i12++) {
                        this.S.map.setGate(0, i9, zArr6[i12], null);
                    }
                }
                break;
            case DOWN:
                for (int i13 = 0; i13 < map.getWidth(); i13++) {
                    Tile tile5 = map.getTile(i13, 0);
                    if (tile5 != null) {
                        this.S._inventory.addTile(tile5, 1);
                    }
                    for (int i14 = 1; i14 < map.getHeight(); i14++) {
                        Tile tile6 = map.getTile(i13, i14);
                        this.S.map.setTile(i13, i14 - 1, tile6 == null ? null : tile6.cloneTile());
                    }
                    this.S.map.setTile(i13, map.getHeight() - 1, null);
                }
                for (int i15 = 0; i15 <= map.getWidth(); i15++) {
                    boolean[] zArr7 = {false, true};
                    for (int i16 = 0; i16 < 2; i16++) {
                        Gate gate6 = map.getGate(i15, 0, zArr7[i16]);
                        if (gate6 != null) {
                            this.S._inventory.addGate(gate6, 1);
                        }
                    }
                    for (int i17 = 1; i17 <= map.getHeight(); i17++) {
                        boolean[] zArr8 = {false, true};
                        for (int i18 = 0; i18 < 2; i18++) {
                            boolean z3 = zArr8[i18];
                            Gate gate7 = map.getGate(i15, i17, z3);
                            this.S.map.setGate(i15, i17 - 1, z3, gate7 == null ? null : gate7.cloneGate());
                        }
                    }
                    boolean[] zArr9 = {false, true};
                    for (int i19 = 0; i19 < 2; i19++) {
                        this.S.map.setGate(i15, map.getHeight(), zArr9[i19], null);
                    }
                }
                break;
            case UP:
                for (int i20 = 0; i20 < map.getWidth(); i20++) {
                    Tile tile7 = map.getTile(i20, map.getHeight() - 1);
                    if (tile7 != null) {
                        this.S._inventory.addTile(tile7, 1);
                    }
                    for (int height = map.getHeight() - 2; height >= 0; height--) {
                        Tile tile8 = map.getTile(i20, height);
                        this.S.map.setTile(i20, height + 1, tile8 == null ? null : tile8.cloneTile());
                    }
                    this.S.map.setTile(i20, 0, null);
                }
                for (int i21 = 0; i21 <= map.getWidth(); i21++) {
                    boolean[] zArr10 = {false, true};
                    for (int i22 = 0; i22 < 2; i22++) {
                        Gate gate8 = map.getGate(i21, map.getHeight(), zArr10[i22]);
                        if (gate8 != null) {
                            this.S._inventory.addGate(gate8, 1);
                        }
                    }
                    Gate gate9 = map.getGate(i21, map.getHeight() - 1, true);
                    if (gate9 != null) {
                        this.S._inventory.addGate(gate9, 1);
                        this.S.map.setGate(i21, map.getHeight() - 1, true, null);
                    }
                    for (int height2 = map.getHeight() - 1; height2 >= 0; height2--) {
                        boolean[] zArr11 = {false, true};
                        for (int i23 = 0; i23 < 2; i23++) {
                            boolean z4 = zArr11[i23];
                            Gate gate10 = map.getGate(i21, height2, z4);
                            this.S.map.setGate(i21, height2 + 1, z4, gate10 == null ? null : gate10.cloneGate());
                        }
                    }
                    boolean[] zArr12 = {false, true};
                    for (int i24 = 0; i24 < 2; i24++) {
                        this.S.map.setGate(i21, 0, zArr12[i24], null);
                    }
                }
                break;
        }
        this.S._mapRendering.forceTilesRedraw(true);
        this.S.pathfinding.forcePathfindingRebuild();
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        this.userMap = null;
        this.basicLevel = null;
        this.g.set(true);
        deleteBackupFile();
        super.dispose();
    }

    public static void deleteBackupFile() {
        try {
            Gdx.files.local("cache/map-editor-backup.json").delete();
        } catch (Exception unused) {
        }
    }

    @Null
    public static BackedUpMapInfo getBackUpInfo() {
        try {
            JsonValue parse = new JsonReader().parse(Gdx.files.local("cache/map-editor-backup.json").readString("UTF-8"));
            BackedUpMapInfo backedUpMapInfo = new BackedUpMapInfo();
            backedUpMapInfo.backupTimestamp = parse.getLong("timestamp");
            backedUpMapInfo.isBasicLevel = parse.getBoolean("isBasicLevel");
            backedUpMapInfo.mapId = parse.getString("mapId");
            backedUpMapInfo.mapName = parse.getString("mapName");
            backedUpMapInfo.mapJson = parse.get("map");
            return backedUpMapInfo;
        } catch (Exception unused) {
            return null;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapEditorSystem$InventoryDefaultListener.class */
    public class InventoryDefaultListener extends MapEditorInventoryMenu.MapEditorInventoryMenuListener.Adapter {
        public InventoryDefaultListener() {
        }

        @Override // com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener.Adapter, com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener
        public void itemSlotDragStart(MapEditorInventoryMenu.ItemSlot itemSlot, Vector2 vector2) {
            MapEditorSystem.this.a(itemSlot);
        }

        @Override // com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener.Adapter, com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener
        public void itemSlotClicked(MapEditorInventoryMenu.ItemSlot itemSlot) {
            MapEditorSystem.this.a(itemSlot);
        }

        @Override // com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener.Adapter, com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener
        public void categoryTabChanged() {
            if (MapEditorSystem.this.selection.isFromInventory()) {
                MapEditorSystem.this.deselectAll();
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapEditorSystem$HistoryImprint.class */
    public final class HistoryImprint {

        /* renamed from: a, reason: collision with root package name */
        private boolean f2980a;
        public Array<DelayedRemovalArray<ItemStack>> startingInventory;
        public Map startingMap;
        public Array<ItemStack> inventoryAddedItems;
        public Array<ItemStack> inventoryRemovedItems;
        public int mapWidthDelta;
        public int mapHeightDelta;
        public Array<Tile> mapRemovedTiles;
        public Array<Tile> mapAddedTiles;
        public Array<ObjectPair<Tile, Tile>> mapReplacedTiles;
        public Array<Gate> mapRemovedGates;
        public Array<Gate> mapAddedGates;
        public Array<ObjectPair<Gate, Gate>> mapReplacedGates;

        /* synthetic */ HistoryImprint(MapEditorSystem mapEditorSystem, byte b2) {
            this();
        }

        private HistoryImprint() {
            this.inventoryAddedItems = new Array<>(true, 1, ItemStack.class);
            this.inventoryRemovedItems = new Array<>(true, 1, ItemStack.class);
            this.mapWidthDelta = 0;
            this.mapHeightDelta = 0;
            this.mapRemovedTiles = new Array<>(true, 1, Tile.class);
            this.mapAddedTiles = new Array<>(true, 1, Tile.class);
            this.mapReplacedTiles = new Array<>(true, 1, ObjectPair.class);
            this.mapRemovedGates = new Array<>(true, 1, Gate.class);
            this.mapAddedGates = new Array<>(true, 1, Gate.class);
            this.mapReplacedGates = new Array<>(true, 1, ObjectPair.class);
            Array<DelayedRemovalArray<ItemStack>> allItems = MapEditorSystem.this.S._inventory.getAllItems();
            this.startingInventory = new Array<>(true, allItems.size, DelayedRemovalArray.class);
            this.startingInventory.setSize(allItems.size);
            for (int i = 0; i < allItems.size; i++) {
                DelayedRemovalArray<ItemStack> delayedRemovalArray = allItems.get(i);
                DelayedRemovalArray<ItemStack> delayedRemovalArray2 = new DelayedRemovalArray<>(true, delayedRemovalArray.size, ItemStack.class);
                for (int i2 = 0; i2 < delayedRemovalArray.size; i2++) {
                    delayedRemovalArray2.add(delayedRemovalArray.get(i2).cpy());
                }
                this.startingInventory.set(i, delayedRemovalArray2);
            }
            this.startingMap = MapEditorSystem.this.S.map.getMap().cpy();
        }

        public final boolean isSealed() {
            return this.f2980a;
        }

        public final int getChangesCount() {
            return this.inventoryAddedItems.size + this.inventoryRemovedItems.size + Math.abs(this.mapWidthDelta) + Math.abs(this.mapHeightDelta) + this.mapRemovedTiles.size + this.mapAddedTiles.size + this.mapReplacedTiles.size + this.mapAddedGates.size + this.mapRemovedGates.size + this.mapReplacedGates.size;
        }

        public final boolean isEmpty() {
            return getChangesCount() == 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            if (this.f2980a) {
                return;
            }
            Array<DelayedRemovalArray<ItemStack>> allItems = MapEditorSystem.this.S._inventory.getAllItems();
            for (int i = 0; i < allItems.size; i++) {
                DelayedRemovalArray<ItemStack> delayedRemovalArray = this.startingInventory.items[i];
                DelayedRemovalArray<ItemStack> delayedRemovalArray2 = allItems.items[i];
                for (int i2 = 0; i2 < delayedRemovalArray2.size; i2++) {
                    ItemStack itemStack = delayedRemovalArray2.items[i2];
                    ItemStack itemStackFromArray = ProgressManager.getItemStackFromArray(delayedRemovalArray, itemStack.getItem());
                    if (itemStackFromArray == null) {
                        this.inventoryAddedItems.add(new ItemStack(itemStack.getItem(), itemStack.getCount()));
                    } else if (itemStackFromArray.getCount() < itemStack.getCount()) {
                        this.inventoryAddedItems.add(new ItemStack(itemStack.getItem(), itemStack.getCount() - itemStackFromArray.getCount()));
                    } else if (itemStackFromArray.getCount() > itemStack.getCount()) {
                        this.inventoryRemovedItems.add(new ItemStack(itemStack.getItem(), itemStackFromArray.getCount() - itemStack.getCount()));
                    }
                }
                for (int i3 = 0; i3 < delayedRemovalArray.size; i3++) {
                    ItemStack itemStack2 = delayedRemovalArray.items[i3];
                    if (ProgressManager.getItemStackFromArray(delayedRemovalArray2, itemStack2.getItem()) == null) {
                        this.inventoryRemovedItems.add(new ItemStack(itemStack2.getItem(), itemStack2.getCount()));
                    }
                }
            }
            this.startingInventory = null;
            Map map = MapEditorSystem.this.S.map.getMap();
            this.mapWidthDelta = map.getWidth() - this.startingMap.getWidth();
            this.mapHeightDelta = map.getHeight() - this.startingMap.getHeight();
            int max = Math.max(map.getWidth(), this.startingMap.getWidth());
            int max2 = Math.max(map.getHeight(), this.startingMap.getHeight());
            for (int i4 = 0; i4 < max2; i4++) {
                for (int i5 = 0; i5 < max; i5++) {
                    Tile tile = map.getTile(i5, i4);
                    Tile tile2 = this.startingMap.getTile(i5, i4);
                    if (tile == null) {
                        if (tile2 != null) {
                            this.mapRemovedTiles.add(tile2.cloneTile());
                        }
                    } else if (tile2 == null) {
                        this.mapAddedTiles.add(tile.cloneTile());
                    } else if (!tile.sameAsWithExtras(tile2)) {
                        this.mapReplacedTiles.add(new ObjectPair<>(tile2.cloneTile(), tile.cloneTile()));
                    }
                }
            }
            for (int i6 = 0; i6 < max2 + 1; i6++) {
                for (int i7 = 0; i7 < max + 1; i7++) {
                    boolean[] zArr = {false, true};
                    for (int i8 = 0; i8 < 2; i8++) {
                        boolean z = zArr[i8];
                        Gate gate = map.getGate(i7, i6, z);
                        Gate gate2 = this.startingMap.getGate(i7, i6, z);
                        if (gate == null) {
                            if (gate2 != null) {
                                this.mapRemovedGates.add(gate2.cloneGate());
                            }
                        } else if (gate2 == null) {
                            this.mapAddedGates.add(gate.cloneGate());
                        } else if (!gate.sameAs(gate2)) {
                            this.mapReplacedGates.add(new ObjectPair<>(gate2.cloneGate(), gate.cloneGate()));
                        }
                    }
                }
            }
            this.startingMap = null;
            this.f2980a = true;
        }

        public final void repeat() {
            if (!this.f2980a) {
                MapEditorSystem.f2976a.e("can't revert unsealed history imprint", new Object[0]);
                return;
            }
            if (this.mapWidthDelta > 0) {
                MapEditorSystem.this.S.map.setMapSize(MapEditorSystem.this.S.map.getMap().getWidth() + this.mapWidthDelta, MapEditorSystem.this.S.map.getMap().getHeight());
            } else if (this.mapHeightDelta > 0) {
                MapEditorSystem.this.S.map.setMapSize(MapEditorSystem.this.S.map.getMap().getWidth(), MapEditorSystem.this.S.map.getMap().getHeight() + this.mapHeightDelta);
            }
            for (int i = 0; i < this.inventoryRemovedItems.size; i++) {
                ItemStack itemStack = this.inventoryRemovedItems.get(i);
                MapEditorSystem.this.S._inventory.removeMany(itemStack.getItem(), itemStack.getCount());
            }
            for (int i2 = 0; i2 < this.inventoryAddedItems.size; i2++) {
                ItemStack itemStack2 = this.inventoryAddedItems.get(i2);
                MapEditorSystem.this.S._inventory.add(itemStack2.getItem(), itemStack2.getCount());
            }
            for (int i3 = 0; i3 < this.mapAddedTiles.size; i3++) {
                Tile tile = this.mapAddedTiles.get(i3);
                MapEditorSystem.this.S.map.setTile(tile.getX(), tile.getY(), tile.cloneTile());
            }
            for (int i4 = 0; i4 < this.mapAddedGates.size; i4++) {
                Gate gate = this.mapAddedGates.get(i4);
                MapEditorSystem.this.S.map.setGate(gate.getX(), gate.getY(), gate.isLeftSide(), gate.cloneGate());
            }
            for (int i5 = 0; i5 < this.mapRemovedTiles.size; i5++) {
                Tile tile2 = this.mapRemovedTiles.get(i5);
                MapEditorSystem.this.S.map.setTile(tile2.getX(), tile2.getY(), null);
            }
            for (int i6 = 0; i6 < this.mapRemovedGates.size; i6++) {
                Gate gate2 = this.mapRemovedGates.get(i6);
                MapEditorSystem.this.S.map.setGate(gate2.getX(), gate2.getY(), gate2.isLeftSide(), null);
            }
            for (int i7 = 0; i7 < this.mapReplacedTiles.size; i7++) {
                Tile tile3 = this.mapReplacedTiles.get(i7).second;
                MapEditorSystem.this.S.map.setTile(tile3.getX(), tile3.getY(), tile3.cloneTile());
            }
            for (int i8 = 0; i8 < this.mapReplacedGates.size; i8++) {
                Gate gate3 = this.mapReplacedGates.get(i8).second;
                MapEditorSystem.this.S.map.setGate(gate3.getX(), gate3.getY(), gate3.isLeftSide(), gate3.cloneGate());
            }
            if (this.mapWidthDelta < 0) {
                MapEditorSystem.this.S.map.setMapSize(MapEditorSystem.this.S.map.getMap().getWidth() - this.mapWidthDelta, MapEditorSystem.this.S.map.getMap().getHeight());
            } else if (this.mapHeightDelta < 0) {
                MapEditorSystem.this.S.map.setMapSize(MapEditorSystem.this.S.map.getMap().getWidth(), MapEditorSystem.this.S.map.getMap().getHeight() - this.mapHeightDelta);
            }
        }

        public final void revert() {
            if (!this.f2980a) {
                MapEditorSystem.f2976a.e("can't revert unsealed history imprint", new Object[0]);
                return;
            }
            if (this.mapWidthDelta < 0) {
                MapEditorSystem.this.S.map.setMapSize(MapEditorSystem.this.S.map.getMap().getWidth() - this.mapWidthDelta, MapEditorSystem.this.S.map.getMap().getHeight());
            } else if (this.mapHeightDelta < 0) {
                MapEditorSystem.this.S.map.setMapSize(MapEditorSystem.this.S.map.getMap().getWidth(), MapEditorSystem.this.S.map.getMap().getHeight() - this.mapHeightDelta);
            }
            for (int i = 0; i < this.inventoryRemovedItems.size; i++) {
                ItemStack itemStack = this.inventoryRemovedItems.get(i);
                MapEditorSystem.this.S._inventory.add(itemStack.getItem(), itemStack.getCount());
            }
            for (int i2 = 0; i2 < this.inventoryAddedItems.size; i2++) {
                ItemStack itemStack2 = this.inventoryAddedItems.get(i2);
                MapEditorSystem.this.S._inventory.removeMany(itemStack2.getItem(), itemStack2.getCount());
            }
            for (int i3 = 0; i3 < this.mapAddedTiles.size; i3++) {
                Tile tile = this.mapAddedTiles.get(i3);
                MapEditorSystem.this.S.map.setTile(tile.getX(), tile.getY(), null);
            }
            for (int i4 = 0; i4 < this.mapAddedGates.size; i4++) {
                Gate gate = this.mapAddedGates.get(i4);
                MapEditorSystem.this.S.map.setGate(gate.getX(), gate.getY(), gate.isLeftSide(), null);
            }
            for (int i5 = 0; i5 < this.mapRemovedTiles.size; i5++) {
                Tile tile2 = this.mapRemovedTiles.get(i5);
                MapEditorSystem.this.S.map.setTile(tile2.getX(), tile2.getY(), tile2.cloneTile());
            }
            for (int i6 = 0; i6 < this.mapRemovedGates.size; i6++) {
                Gate gate2 = this.mapRemovedGates.get(i6);
                MapEditorSystem.this.S.map.setGate(gate2.getX(), gate2.getY(), gate2.isLeftSide(), gate2.cloneGate());
            }
            for (int i7 = 0; i7 < this.mapReplacedTiles.size; i7++) {
                Tile tile3 = this.mapReplacedTiles.get(i7).first;
                MapEditorSystem.this.S.map.setTile(tile3.getX(), tile3.getY(), tile3.cloneTile());
            }
            for (int i8 = 0; i8 < this.mapReplacedGates.size; i8++) {
                Gate gate3 = this.mapReplacedGates.get(i8).first;
                MapEditorSystem.this.S.map.setGate(gate3.getX(), gate3.getY(), gate3.isLeftSide(), gate3.cloneGate());
            }
            if (this.mapWidthDelta > 0) {
                MapEditorSystem.this.S.map.setMapSize(MapEditorSystem.this.S.map.getMap().getWidth() - this.mapWidthDelta, MapEditorSystem.this.S.map.getMap().getHeight());
            } else if (this.mapHeightDelta > 0) {
                MapEditorSystem.this.S.map.setMapSize(MapEditorSystem.this.S.map.getMap().getWidth(), MapEditorSystem.this.S.map.getMap().getHeight() - this.mapHeightDelta);
            }
        }

        public final String toString() {
            return "HistoryImprint (\n  sealed: " + this.f2980a + "\n  inventoryAddedItems: " + this.inventoryAddedItems.toString(", ") + "\n  inventoryRemovedItems: " + this.inventoryRemovedItems.toString(", ") + "\n  mapWidthDelta: " + this.mapWidthDelta + "\n  mapHeightDelta: " + this.mapHeightDelta + "\n  mapRemovedTiles: " + this.mapRemovedTiles.toString(", ") + "\n  mapAddedTiles: " + this.mapAddedTiles.toString(", ") + "\n  mapReplacedTiles: " + this.mapReplacedTiles.toString(", ") + "\n  mapRemovedGates: " + this.mapRemovedGates.toString(", ") + "\n  mapAddedGates: " + this.mapAddedGates.toString(", ") + "\n  mapReplacedGates: " + this.mapAddedGates.toString(", ") + "\n)";
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapEditorSystem$BackedUpMapInfo.class */
    public static final class BackedUpMapInfo {
        public boolean isBasicLevel;
        public String mapId;
        public String mapName;
        public long backupTimestamp;
        public JsonValue mapJson;

        public final void startEditor() {
            MapEditorScreen mapEditorScreen;
            Map fromJson = Map.fromJson(this.mapJson);
            if (this.isBasicLevel) {
                BasicLevel level = Game.i.basicLevelManager.getLevel(this.mapId);
                if (level == null) {
                    throw new IllegalArgumentException("Basic level '" + this.mapId + "' not exists");
                }
                Map map = level.getMap();
                level.setMap(fromJson);
                mapEditorScreen = new MapEditorScreen(level);
                level.setMap(map);
            } else {
                UserMap userMap = Game.i.userMapManager.getUserMap(this.mapId);
                if (userMap == null) {
                    throw new IllegalArgumentException("User map '" + this.mapId + "' not exists");
                }
                Map map2 = userMap.map;
                userMap.map = fromJson;
                mapEditorScreen = new MapEditorScreen(userMap);
                userMap.map = map2;
            }
            Game.i.screenManager.setScreen(mapEditorScreen);
            mapEditorScreen.S._mapEditor.mapChanged = true;
        }
    }
}
