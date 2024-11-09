package com.prineside.tdi2.systems;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.ModifierProcessor;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.actions.BuildModifierAction;
import com.prineside.tdi2.actions.CustomModifierButtonAction;
import com.prineside.tdi2.actions.SellModifierAction;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.BuildingRemove;
import com.prineside.tdi2.events.game.ModifierBuild;
import com.prineside.tdi2.events.game.ModifierCustomButtonPress;
import com.prineside.tdi2.events.game.ModifierPlace;
import com.prineside.tdi2.events.game.ModifierSell;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.systems.StateSystem;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ModifierSystem.class */
public final class ModifierSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3005a = TLog.forClass(ModifierSystem.class);
    public DelayedRemovalArray<Modifier> modifiers = new DelayedRemovalArray<>(false, 8, Modifier.class);
    public int[] modifiersBuiltByType = new int[ModifierType.values.length];
    public int[] modifiersBuiltByTypeAllTime = new int[ModifierType.values.length];
    public int[] modifiersSoldByTypeAllTime = new int[ModifierType.values.length];

    /* renamed from: b, reason: collision with root package name */
    private int f3006b = 1;
    private int[] c = new int[ModifierType.values.length];
    private ModifierProcessor[] d = new ModifierProcessor[ModifierType.values.length];

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.modifiers);
        kryo.writeObject(output, this.modifiersBuiltByType);
        kryo.writeObject(output, this.modifiersBuiltByTypeAllTime);
        kryo.writeObject(output, this.modifiersSoldByTypeAllTime);
        output.writeVarInt(this.f3006b, true);
        kryo.writeObject(output, this.c);
        kryo.writeObject(output, this.d);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.modifiers = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.modifiersBuiltByType = (int[]) kryo.readObject(input, int[].class);
        this.modifiersBuiltByTypeAllTime = (int[]) kryo.readObject(input, int[].class);
        this.modifiersSoldByTypeAllTime = (int[]) kryo.readObject(input, int[].class);
        this.f3006b = input.readVarInt(true);
        this.c = (int[]) kryo.readObject(input, int[].class);
        this.d = (ModifierProcessor[]) kryo.readObject(input, ModifierProcessor[].class);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        for (ModifierType modifierType : ModifierType.values) {
            this.d[modifierType.ordinal()] = Game.i.modifierManager.getFactory(modifierType).createProcessor();
        }
        for (ModifierProcessor modifierProcessor : this.d) {
            if (modifierProcessor != null) {
                modifierProcessor.setRegistered(this.S);
            }
        }
        this.S.events.getListeners(ModifierPlace.class).addStateAffecting(new OnModifierPlace(this));
        this.S.events.getListeners(BuildingRemove.class).addStateAffecting(new OnBuildingRemove(this));
        if (!this.S.CFG.headless) {
            a();
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postStateRestore() {
        a();
    }

    private void a() {
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MODIFIER_DRAW_BATCH, false, (batch, f, f2, f3) -> {
            drawBatch(batch, this.S.map.getMap(), f2, this.S._mapRendering.getDrawMode());
        }).setName("Modifier-drawBatch"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MODIFIER_DRAW_BATCH_ADDITIVE, true, (batch2, f4, f5, f6) -> {
            drawBatchAdditive(batch2, this.S.map.getMap(), f5, this.S._mapRendering.getDrawMode());
        }).setName("Modifier-drawBatchAdditive"));
    }

    public final ModifierProcessor getProcessor(ModifierType modifierType) {
        return this.d[modifierType.ordinal()];
    }

    public final boolean isRegistered(Modifier modifier) {
        return modifier.isRegistered();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Modifier modifier) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (modifier.isRegistered()) {
            throw new IllegalArgumentException("Modifier is already registered");
        }
        int[] iArr = this.c;
        int ordinal = modifier.type.ordinal();
        iArr[ordinal] = iArr[ordinal] + 1;
        int i = this.f3006b;
        this.f3006b = i + 1;
        modifier.id = i;
        modifier.setRegistered(this.S);
        this.modifiers.add(modifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Modifier modifier) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (!modifier.isRegistered()) {
            throw new IllegalArgumentException("Modifier is not registered");
        }
        int[] iArr = this.c;
        int ordinal = modifier.type.ordinal();
        iArr[ordinal] = iArr[ordinal] - 1;
        modifier.setUnregistered();
        this.modifiers.removeValue(modifier, true);
    }

    public final int getBuildPrice(ModifierType modifierType) {
        return Game.i.modifierManager.getFactory(modifierType).getBuildPrice(this.S, a(modifierType));
    }

    public final int getMaxModifiersCount(ModifierType modifierType) {
        return this.S.gameValue.getIntValue(Game.i.modifierManager.getCountGameValue(modifierType));
    }

    private int a(ModifierType modifierType) {
        return this.c[modifierType.ordinal()];
    }

    public final int getBuildableModifiersCount(ModifierType modifierType) {
        return Math.max(0, getMaxModifiersCount(modifierType) - this.c[modifierType.ordinal()]);
    }

    public final void buildModifierActionAtSelectedTile(ModifierType modifierType) {
        Tile selectedTile = this.S._gameMapSelection.getSelectedTile();
        if (selectedTile != null && selectedTile.type == TileType.PLATFORM && ((PlatformTile) selectedTile).building == null) {
            buildModifierActionAt(modifierType, selectedTile.getX(), selectedTile.getY());
        }
    }

    public final void buildModifierActionAt(ModifierType modifierType, int i, int i2) {
        boolean[] zArr = {true};
        this.S.map.traverseNeighborTilesAroundPos(i, i2, tile -> {
            if (tile.type == TileType.PLATFORM && ((PlatformTile) tile).building != null && ((PlatformTile) tile).building.buildingType == BuildingType.MODIFIER && !Game.i.modifierManager.getFactory(modifierType).canBePlacedNear(((Modifier) ((PlatformTile) tile).building).type, this.S.gameValue)) {
                zArr[0] = false;
                return false;
            }
            return true;
        });
        if (zArr[0]) {
            if (this.S.gameState.getMoney() >= getBuildPrice(modifierType)) {
                this.S.gameState.pushActionNextUpdate(new BuildModifierAction(modifierType, i, i2));
                return;
            }
            return;
        }
        Notifications.i().add(Game.i.localeManager.i18n.get("modifier_cant_be_placed_near_other"), null, null, null);
    }

    public final void customModifierButtonAction(Modifier modifier, int i, int i2) {
        this.S.gameState.pushActionNextUpdate(new CustomModifierButtonAction(modifier.getTile().getX(), modifier.getTile().getY(), i, i2));
    }

    public final void customModifierButtonActionAt(int i, int i2, int i3, int i4) {
        this.S.gameState.pushActionNextUpdate(new CustomModifierButtonAction(i, i2, i3, i4));
    }

    @Null
    public final Modifier buildModifier(ModifierType modifierType, int i, int i2) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (this.c[modifierType.ordinal()] + 1 > getMaxModifiersCount(modifierType)) {
            f3005a.e("No more modifiers of type " + modifierType.name() + " can be built", new Object[0]);
            return null;
        }
        Tile tile = this.S.map.getMap().getTile(i, i2);
        if (tile != null) {
            if (tile.type != TileType.PLATFORM) {
                f3005a.e("buildModifier - tile type is " + tile.type.name(), new Object[0]);
                return null;
            }
            PlatformTile platformTile = (PlatformTile) tile;
            if (platformTile.building == null) {
                boolean[] zArr = {true};
                this.S.map.traverseNeighborTilesAroundTile(platformTile, tile2 -> {
                    if (tile2.type == TileType.PLATFORM && ((PlatformTile) tile2).building != null && ((PlatformTile) tile2).building.buildingType == BuildingType.MODIFIER && !Game.i.modifierManager.getFactory(modifierType).canBePlacedNear(((Modifier) ((PlatformTile) tile2).building).type, this.S.gameValue)) {
                        zArr[0] = false;
                        return false;
                    }
                    return true;
                });
                if (zArr[0]) {
                    Modifier create = Game.i.modifierManager.getFactory(modifierType).create();
                    int buildPrice = getBuildPrice(modifierType);
                    if (this.S.gameState.removeMoney(buildPrice)) {
                        create.moneySpentOn = buildPrice;
                        this.S.map.setModifier(tile.getX(), tile.getY(), create);
                        int[] iArr = this.modifiersBuiltByType;
                        int ordinal = modifierType.ordinal();
                        iArr[ordinal] = iArr[ordinal] + 1;
                        int[] iArr2 = this.modifiersBuiltByTypeAllTime;
                        int ordinal2 = modifierType.ordinal();
                        iArr2[ordinal2] = iArr2[ordinal2] + 1;
                        this.S.map.updateDirtyTiles();
                        this.S.events.trigger(new ModifierBuild(create, buildPrice));
                        return create;
                    }
                    f3005a.w("not enough money to build a modifier", new Object[0]);
                    return null;
                }
                f3005a.w("can't place near other modifier", new Object[0]);
                return null;
            }
            f3005a.e("trying to build modifier on tile which already has a building", new Object[0]);
            return null;
        }
        f3005a.e("buildModifier - tile is null", new Object[0]);
        return null;
    }

    public final void sellModifierAction(Modifier modifier) {
        this.S.gameState.pushActionNextUpdate(new SellModifierAction(modifier.getTile().getX(), modifier.getTile().getY()));
    }

    public final void sellModifier(Modifier modifier) {
        this.S.gameState.checkGameplayUpdateAllowed();
        int sellPrice = modifier.getSellPrice();
        if (!modifier.isSellAvailable()) {
            sellPrice = (int) (sellPrice * 0.75f);
        }
        if (sellPrice > 0) {
            this.S.gameState.addMoney(sellPrice, false);
        }
        this.S.map.removeBuilding(modifier);
        int[] iArr = this.modifiersSoldByTypeAllTime;
        int ordinal = modifier.type.ordinal();
        iArr[ordinal] = iArr[ordinal] + 1;
        int[] iArr2 = this.modifiersBuiltByType;
        int ordinal2 = modifier.type.ordinal();
        iArr2[ordinal2] = iArr2[ordinal2] - 1;
        this.S.events.trigger(new ModifierSell(modifier, sellPrice));
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        for (int i = 0; i < this.d.length; i++) {
            if (this.d[i] != null) {
                this.d[i].setUnregistered();
                this.d[i] = null;
            }
        }
        this.modifiers.clear();
        super.dispose();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        StateSystem.ActionsArray currentUpdateActions = this.S.gameState.getCurrentUpdateActions();
        if (currentUpdateActions != null) {
            for (int i = 0; i < currentUpdateActions.size; i++) {
                Action action = currentUpdateActions.actions[i];
                if (action.getType() != ActionType.BMO) {
                    if (action.getType() != ActionType.SMO) {
                        if (action.getType() == ActionType.CMB) {
                            CustomModifierButtonAction customModifierButtonAction = (CustomModifierButtonAction) action;
                            Tile tile = this.S.map.getMap().getTile(customModifierButtonAction.x, customModifierButtonAction.y);
                            if (tile != null && tile.type == TileType.PLATFORM) {
                                PlatformTile platformTile = (PlatformTile) tile;
                                if (platformTile.building != null && platformTile.building.buildingType == BuildingType.MODIFIER) {
                                    Modifier modifier = (Modifier) platformTile.building;
                                    if (modifier.hasCustomButton()) {
                                        modifier.customButtonAction(customModifierButtonAction.mapX, customModifierButtonAction.mapY);
                                        this.S.events.trigger(new ModifierCustomButtonPress(modifier));
                                    }
                                }
                            }
                        }
                    } else {
                        SellModifierAction sellModifierAction = (SellModifierAction) action;
                        Tile tile2 = this.S.map.getMap().getTile(sellModifierAction.x, sellModifierAction.y);
                        if (tile2 != null && tile2.type == TileType.PLATFORM) {
                            PlatformTile platformTile2 = (PlatformTile) tile2;
                            if (platformTile2.building != null && platformTile2.building.buildingType == BuildingType.MODIFIER) {
                                sellModifier((Modifier) platformTile2.building);
                                this.S.gameState.registerPlayerActivity();
                            }
                        }
                    }
                } else {
                    BuildModifierAction buildModifierAction = (BuildModifierAction) action;
                    if (buildModifier(buildModifierAction.modifierType, buildModifierAction.x, buildModifierAction.y) != null) {
                        this.S.gameState.registerPlayerActivity();
                    }
                }
            }
        }
        this.modifiers.begin();
        int i2 = this.modifiers.size;
        for (int i3 = 0; i3 < i2; i3++) {
            this.modifiers.items[i3].update(f);
        }
        this.modifiers.end();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Modifier";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void drawBatch(Batch batch, Map map, float f, MapRenderingSystem.DrawMode drawMode) {
        Array.ArrayIterator it = map.getTilesByType(PlatformTile.class).iterator();
        while (it.hasNext()) {
            PlatformTile platformTile = (PlatformTile) it.next();
            if (platformTile.visibleOnScreen && (platformTile.building instanceof Modifier)) {
                ((Modifier) platformTile.building).drawBatch(batch, f, drawMode);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void drawBatchAdditive(Batch batch, Map map, float f, MapRenderingSystem.DrawMode drawMode) {
        Array.ArrayIterator it = map.getTilesByType(PlatformTile.class).iterator();
        while (it.hasNext()) {
            PlatformTile platformTile = (PlatformTile) it.next();
            if (platformTile.visibleOnScreen && (platformTile.building instanceof Modifier)) {
                ((Modifier) platformTile.building).drawBatchAdditive(batch, f, drawMode);
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ModifierSystem$OnModifierPlace.class */
    public static final class OnModifierPlace extends SerializableListener<ModifierSystem> implements Listener<ModifierPlace> {
        private OnModifierPlace() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnModifierPlace(ModifierSystem modifierSystem) {
            this.f1759a = modifierSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(ModifierPlace modifierPlace) {
            ((ModifierSystem) this.f1759a).a(modifierPlace.getModifier());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ModifierSystem$OnBuildingRemove.class */
    public static final class OnBuildingRemove extends SerializableListener<ModifierSystem> implements Listener<BuildingRemove> {
        private OnBuildingRemove() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnBuildingRemove(ModifierSystem modifierSystem) {
            this.f1759a = modifierSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(BuildingRemove buildingRemove) {
            if (buildingRemove.getBuilding().buildingType == BuildingType.MODIFIER) {
                ((ModifierSystem) this.f1759a).b((Modifier) buildingRemove.getBuilding());
            }
        }
    }
}
