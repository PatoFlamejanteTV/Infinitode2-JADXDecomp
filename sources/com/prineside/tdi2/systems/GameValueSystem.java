package com.prineside.tdi2.systems;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueConfig;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.GameValuesRecalculate;
import com.prineside.tdi2.events.game.TileChange;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.tiles.BossTile;
import com.prineside.tdi2.tiles.CoreTile;
import com.prineside.tdi2.tiles.GameValueTile;
import com.prineside.tdi2.tiles.TargetTile;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import java.util.Arrays;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameValueSystem.class */
public final class GameValueSystem extends GameSystem implements KryoSerializable, GameValueProvider {

    /* renamed from: a, reason: collision with root package name */
    @NAGS
    private GameValueManager.GameValuesSnapshot f2952a;

    /* renamed from: b, reason: collision with root package name */
    private GameValueManager.GameValuesSnapshot f2953b;
    private int c = Config.GAME_STATE_UPDATE_RATE;
    private float d = 1.0f / Config.GAME_STATE_UPDATE_RATE;
    private Array<GameValueConfig> e = new Array<>(true, 1, GameValueConfig.class);
    private float[] f = new float[TowerStatType.values.length];
    private Array<Array<GlobalTowerStatMutator>> g;

    @NAGS
    private final double[] h;

    public GameValueSystem() {
        Arrays.fill(this.f, 1.0f);
        this.g = new Array<>(true, TowerStatType.values.length, Array.class);
        for (int i = 0; i < TowerStatType.values.length; i++) {
            this.g.add(new Array<>(true, 1, GlobalTowerStatMutator.class));
        }
        this.h = new double[GameValueType.values.length];
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f2953b);
        output.writeVarInt(this.c, true);
        output.writeFloat(this.d);
        kryo.writeObject(output, this.e);
        kryo.writeObject(output, this.f);
        kryo.writeObject(output, this.g);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2953b = (GameValueManager.GameValuesSnapshot) kryo.readObject(input, GameValueManager.GameValuesSnapshot.class);
        this.c = input.readVarInt(true);
        this.d = input.readFloat();
        this.e = (Array) kryo.readObject(input, Array.class);
        this.f = (float[]) kryo.readObject(input, float[].class);
        this.g = (Array) kryo.readObject(input, Array.class);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    public final float getGlobalStatMultiplier(TowerStatType towerStatType) {
        return this.f[towerStatType.ordinal()];
    }

    public final boolean addGlobalTowerStatMutator(TowerStatType towerStatType, GlobalTowerStatMutator globalTowerStatMutator) {
        Array<GlobalTowerStatMutator> array = this.g.get(towerStatType.ordinal());
        if (!array.contains(globalTowerStatMutator, true)) {
            array.add(globalTowerStatMutator);
            recalculateGlobalTowerStatMultipliers();
            return true;
        }
        return false;
    }

    public final boolean removeGlobalTowerStatMutator(TowerStatType towerStatType, GlobalTowerStatMutator globalTowerStatMutator) {
        if (this.g.get(towerStatType.ordinal()).removeValue(globalTowerStatMutator, true)) {
            recalculateGlobalTowerStatMultipliers();
            return true;
        }
        return false;
    }

    public final Array<GlobalTowerStatMutator> getGlobalTowerStatMutators(TowerStatType towerStatType) {
        return this.g.get(towerStatType.ordinal());
    }

    public final void recalculateGlobalTowerStatMultipliers() {
        this.S.gameState.checkGameplayUpdateAllowed();
        boolean z = false;
        for (TowerStatType towerStatType : TowerStatType.values) {
            Array<GlobalTowerStatMutator> array = this.g.get(towerStatType.ordinal());
            float f = 1.0f;
            for (int i = 0; i < array.size; i++) {
                f *= array.items[i].f2954a;
            }
            if (this.f[towerStatType.ordinal()] != f) {
                this.f[towerStatType.ordinal()] = f;
                z = true;
            }
        }
        if (z) {
            System.arraycopy(this.f2953b.values, 0, this.h, 0, this.f2953b.values.length);
            this.S.events.trigger(new GameValuesRecalculate(this.h));
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.f2953b = new GameValueManager.GameValuesSnapshot();
        recalculate();
        this.S.events.getListeners(TileChange.class).addStateAffecting(new OnTileChange(this, (byte) 0)).setDescription("GameValueSystem - recalculates values");
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "GameValue";
    }

    public final GameValueManager.GameValuesSnapshot getGlobalSnapshot() {
        if (this.f2952a == null) {
            TargetTile targetTile = this.S.map.getMap().getTargetTile(false);
            boolean z = false;
            if (targetTile != null) {
                z = targetTile.isUseStockGameValues();
            }
            if (this.S.CFG.setup == GameSystemProvider.SystemsConfig.Setup.MAP_EDITOR) {
                this.f2952a = GameValueManager.createSnapshot(null, DifficultyMode.NORMAL, false, null, z, true, Game.i.progressManager.createProgressSnapshotForState());
            } else {
                this.f2952a = GameValueManager.createSnapshot(null, this.S.gameState.difficultyMode, false, this.S.gameState.basicLevelName == null ? null : Game.i.basicLevelManager.getLevel(this.S.gameState.basicLevelName), z, this.S.gameState.userMapId != null, this.S.gameState.gameStartProgressSnapshot);
            }
        }
        return this.f2952a;
    }

    public final void setGlobalSnapshot(@Null GameValueManager.GameValuesSnapshot gameValuesSnapshot) {
        this.f2952a = gameValuesSnapshot;
    }

    public final GameValueManager.GameValuesSnapshot getSnapshot() {
        return this.f2953b;
    }

    public final int getTickRate() {
        return this.c;
    }

    public final float getTickRateDeltaTime() {
        return this.d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void recalculate() {
        System.arraycopy(this.f2953b.values, 0, this.h, 0, this.f2953b.values.length);
        this.f2953b.effects.clear();
        GameValueManager.GameValuesSnapshot globalSnapshot = getGlobalSnapshot();
        System.arraycopy(globalSnapshot.values, 0, this.f2953b.values, 0, this.f2953b.values.length);
        for (int i = 0; i < globalSnapshot.effects.size; i++) {
            GameValueManager.GameValueEffect gameValueEffect = new GameValueManager.GameValueEffect();
            gameValueEffect.from(globalSnapshot.effects.items[i]);
            this.f2953b.effects.add(gameValueEffect);
        }
        TargetTile targetTile = this.S.map.getMap().getTargetTile(false);
        if (targetTile != null) {
            Array<GameValueConfig> gameValues = targetTile.getGameValues();
            for (int i2 = 0; i2 < gameValues.size; i2++) {
                GameValueConfig gameValueConfig = gameValues.items[i2];
                if (!gameValueConfig.isAllowBonuses()) {
                    this.f2953b.effects.begin();
                    for (int i3 = 0; i3 < this.f2953b.effects.size; i3++) {
                        GameValueManager.GameValueEffect gameValueEffect2 = this.f2953b.effects.get(i3);
                        if (gameValueEffect2.type == gameValueConfig.getType() && gameValueEffect2.source != GameValueManager.GameValueEffect.Source.STOCK) {
                            this.f2953b.effects.removeIndex(i3);
                        }
                    }
                    this.f2953b.effects.end();
                    this.f2953b.values[gameValueConfig.getType().ordinal()] = Game.i.gameValueManager.getStockValueConfig(gameValueConfig.getType()).stockValue;
                }
                if (!gameValueConfig.isOverwrite()) {
                    double[] dArr = this.f2953b.values;
                    int ordinal = gameValueConfig.getType().ordinal();
                    dArr[ordinal] = dArr[ordinal] + gameValueConfig.getValue();
                    GameValueManager.GameValueEffect gameValueEffect3 = new GameValueManager.GameValueEffect();
                    gameValueEffect3.delta = gameValueConfig.getValue();
                    gameValueEffect3.type = gameValueConfig.getType();
                    gameValueEffect3.source = GameValueManager.GameValueEffect.Source.BASE_TILE;
                    this.f2953b.effects.add(gameValueEffect3);
                } else {
                    this.f2953b.effects.begin();
                    for (int i4 = 0; i4 < this.f2953b.effects.size; i4++) {
                        if (this.f2953b.effects.get(i4).type == gameValueConfig.getType()) {
                            this.f2953b.effects.removeIndex(i4);
                        }
                    }
                    this.f2953b.effects.end();
                    GameValueManager.GameValueEffect gameValueEffect4 = new GameValueManager.GameValueEffect();
                    gameValueEffect4.delta = (-this.f2953b.values[gameValueConfig.getType().ordinal()]) + gameValueConfig.getValue();
                    gameValueEffect4.type = gameValueConfig.getType();
                    gameValueEffect4.source = GameValueManager.GameValueEffect.Source.BASE_TILE;
                    this.f2953b.effects.add(gameValueEffect4);
                    this.f2953b.values[gameValueConfig.getType().ordinal()] = gameValueConfig.getValue();
                }
            }
        }
        Array tilesByType = this.S.map.getMap().getTilesByType(BossTile.class);
        if (tilesByType.size != 0) {
            Array<GameValueConfig> gameValues2 = ((BossTile) tilesByType.first()).getGameValues();
            for (int i5 = 0; i5 < gameValues2.size; i5++) {
                GameValueConfig gameValueConfig2 = gameValues2.items[i5];
                double[] dArr2 = this.f2953b.values;
                int ordinal2 = gameValueConfig2.getType().ordinal();
                dArr2[ordinal2] = dArr2[ordinal2] + gameValueConfig2.getValue();
                GameValueManager.GameValueEffect gameValueEffect5 = new GameValueManager.GameValueEffect();
                gameValueEffect5.delta = gameValueConfig2.getValue();
                gameValueEffect5.type = gameValueConfig2.getType();
                gameValueEffect5.source = GameValueManager.GameValueEffect.Source.BOSS_TILE;
                this.f2953b.effects.add(gameValueEffect5);
            }
        }
        DelayedRemovalArray<Tile> allTiles = this.S.map.getMap().getAllTiles();
        for (int i6 = 0; i6 < allTiles.size; i6++) {
            Tile tile = allTiles.items[i6];
            if (tile instanceof GameValueTile) {
                GameValueTile gameValueTile = (GameValueTile) tile;
                if (!gameValueTile.isFinalMultiplier()) {
                    if (!gameValueTile.isOverwrite()) {
                        double[] dArr3 = this.f2953b.values;
                        int ordinal3 = gameValueTile.getGameValueType().ordinal();
                        dArr3[ordinal3] = dArr3[ordinal3] + gameValueTile.getDelta();
                        GameValueManager.GameValueEffect gameValueEffect6 = new GameValueManager.GameValueEffect();
                        gameValueEffect6.delta = gameValueTile.getDelta();
                        gameValueEffect6.type = gameValueTile.getGameValueType();
                        gameValueEffect6.source = GameValueManager.GameValueEffect.Source.GV_TILE;
                        this.f2953b.effects.add(gameValueEffect6);
                    } else {
                        this.f2953b.effects.begin();
                        for (int i7 = 0; i7 < this.f2953b.effects.size; i7++) {
                            if (this.f2953b.effects.get(i7).type == gameValueTile.getGameValueType()) {
                                this.f2953b.effects.removeIndex(i7);
                            }
                        }
                        this.f2953b.effects.end();
                        GameValueManager.GameValueEffect gameValueEffect7 = new GameValueManager.GameValueEffect();
                        gameValueEffect7.delta = (-this.f2953b.values[gameValueTile.getGameValueType().ordinal()]) + gameValueTile.getDelta();
                        gameValueEffect7.type = gameValueTile.getGameValueType();
                        gameValueEffect7.source = GameValueManager.GameValueEffect.Source.GV_TILE;
                        this.f2953b.effects.add(gameValueEffect7);
                        this.f2953b.values[gameValueTile.getGameValueType().ordinal()] = gameValueTile.getDelta();
                    }
                }
            }
        }
        Array tilesByType2 = this.S.map.getMap().getTilesByType(CoreTile.class);
        for (int i8 = 0; i8 < tilesByType2.size; i8++) {
            CoreTile coreTile = ((CoreTile[]) tilesByType2.items)[i8];
            Array<CoreTile.Upgrade> upgrades = coreTile.getUpgrades();
            for (int i9 = 0; i9 < upgrades.size; i9++) {
                CoreTile.Upgrade upgrade = upgrades.items[i9];
                int upgradeInstallLevelByIdx = coreTile.getUpgradeInstallLevelByIdx(i9);
                if (upgrade != null && !upgrade.isAction && upgradeInstallLevelByIdx != 0) {
                    CoreTile.Upgrade.UpgradeLevel upgradeLevel = upgrade.upgradeLevels.items[upgradeInstallLevelByIdx - 1];
                    double[] dArr4 = this.f2953b.values;
                    int ordinal4 = upgrade.getGameValueType().ordinal();
                    dArr4[ordinal4] = dArr4[ordinal4] + upgradeLevel.delta;
                    GameValueManager.GameValueEffect gameValueEffect8 = new GameValueManager.GameValueEffect();
                    gameValueEffect8.delta = upgradeLevel.delta;
                    gameValueEffect8.type = upgrade.getGameValueType();
                    gameValueEffect8.source = GameValueManager.GameValueEffect.Source.CORE_TILE;
                    this.f2953b.effects.add(gameValueEffect8);
                }
            }
        }
        for (int i10 = 0; i10 < allTiles.size; i10++) {
            Tile tile2 = allTiles.items[i10];
            if (tile2 instanceof GameValueTile) {
                GameValueTile gameValueTile2 = (GameValueTile) tile2;
                if (gameValueTile2.isFinalMultiplier()) {
                    double d = this.f2953b.values[gameValueTile2.getGameValueType().ordinal()];
                    double delta = d * gameValueTile2.getDelta();
                    GameValueManager.GameValueEffect gameValueEffect9 = new GameValueManager.GameValueEffect();
                    gameValueEffect9.delta = delta - d;
                    gameValueEffect9.type = gameValueTile2.getGameValueType();
                    gameValueEffect9.source = GameValueManager.GameValueEffect.Source.GV_TILE;
                    this.f2953b.effects.add(gameValueEffect9);
                    this.f2953b.values[gameValueTile2.getGameValueType().ordinal()] = delta;
                }
            }
        }
        for (int i11 = 0; i11 < this.e.size; i11++) {
            GameValueConfig gameValueConfig3 = this.e.items[i11];
            if (!gameValueConfig3.isFinalGlobalMultiplier()) {
                if (!gameValueConfig3.isOverwrite()) {
                    double[] dArr5 = this.f2953b.values;
                    int ordinal5 = gameValueConfig3.getType().ordinal();
                    dArr5[ordinal5] = dArr5[ordinal5] + gameValueConfig3.getValue();
                    GameValueManager.GameValueEffect gameValueEffect10 = new GameValueManager.GameValueEffect();
                    gameValueEffect10.delta = gameValueConfig3.getValue();
                    gameValueEffect10.type = gameValueConfig3.getType();
                    gameValueEffect10.source = GameValueManager.GameValueEffect.Source.CUSTOM;
                    this.f2953b.effects.add(gameValueEffect10);
                } else {
                    this.f2953b.effects.begin();
                    for (int i12 = 0; i12 < this.f2953b.effects.size; i12++) {
                        if (this.f2953b.effects.get(i12).type == gameValueConfig3.getType()) {
                            this.f2953b.effects.removeIndex(i12);
                        }
                    }
                    this.f2953b.effects.end();
                    GameValueManager.GameValueEffect gameValueEffect11 = new GameValueManager.GameValueEffect();
                    gameValueEffect11.delta = (-this.f2953b.values[gameValueConfig3.getType().ordinal()]) + gameValueConfig3.getValue();
                    gameValueEffect11.type = gameValueConfig3.getType();
                    gameValueEffect11.source = GameValueManager.GameValueEffect.Source.CUSTOM;
                    this.f2953b.effects.add(gameValueEffect11);
                    this.f2953b.values[gameValueConfig3.getType().ordinal()] = gameValueConfig3.getValue();
                }
            }
        }
        for (int i13 = 0; i13 < this.e.size; i13++) {
            GameValueConfig gameValueConfig4 = this.e.items[i13];
            if (gameValueConfig4.isFinalGlobalMultiplier()) {
                double d2 = this.f2953b.values[gameValueConfig4.getType().ordinal()];
                double value = d2 * gameValueConfig4.getValue();
                GameValueManager.GameValueEffect gameValueEffect12 = new GameValueManager.GameValueEffect();
                gameValueEffect12.delta = value - d2;
                gameValueEffect12.type = gameValueConfig4.getType();
                gameValueEffect12.source = GameValueManager.GameValueEffect.Source.CUSTOM;
                this.f2953b.effects.add(gameValueEffect12);
                this.f2953b.values[gameValueConfig4.getType().ordinal()] = value;
            }
        }
        int i14 = 1;
        for (double d3 : this.f2953b.values) {
            i14 = (i14 * 31) + ((int) (d3 * 10000.0d));
        }
        if (this.f2953b.hash != i14) {
            this.f2953b.hash = i14;
            this.c = MathUtils.round(getFloatValue(GameValueType.GAME_TICK_RATE));
            if (this.c < 10) {
                this.c = 10;
            }
            if (this.c > 300) {
                this.c = 300;
            }
            this.d = 1.0f / this.c;
            this.S.events.trigger(new GameValuesRecalculate(this.h));
        }
    }

    public final void addCustomGameValue(GameValueConfig gameValueConfig) {
        if (!this.e.contains(gameValueConfig, true)) {
            this.e.add(gameValueConfig);
        }
    }

    public final void removeCustomGameValue(GameValueConfig gameValueConfig) {
        this.e.removeValue(gameValueConfig, true);
    }

    @Override // com.prineside.tdi2.GameValueProvider
    public final double getValue(GameValueType gameValueType) {
        return this.f2953b.getValue(gameValueType);
    }

    @Override // com.prineside.tdi2.GameValueProvider
    public final float getFloatValue(GameValueType gameValueType) {
        return this.f2953b.getFloatValue(gameValueType);
    }

    @Override // com.prineside.tdi2.GameValueProvider
    public final boolean getBooleanValue(GameValueType gameValueType) {
        return this.f2953b.getBooleanValue(gameValueType);
    }

    @Override // com.prineside.tdi2.GameValueProvider
    public final int getIntValue(GameValueType gameValueType) {
        return this.f2953b.getIntValue(gameValueType);
    }

    @Override // com.prineside.tdi2.GameValueProvider
    public final int getIntValueSum(GameValueType gameValueType, GameValueType gameValueType2) {
        return this.f2953b.getIntValueSum(gameValueType, gameValueType2);
    }

    @Override // com.prineside.tdi2.GameValueProvider
    public final float getFloatValueSum(GameValueType gameValueType, GameValueType gameValueType2) {
        return this.f2953b.getFloatValueSum(gameValueType, gameValueType2);
    }

    @Override // com.prineside.tdi2.GameValueProvider
    public final double getPercentValueAsMultiplier(GameValueType gameValueType) {
        return this.f2953b.getPercentValueAsMultiplier(gameValueType);
    }

    @Override // com.prineside.tdi2.GameValueProvider
    public final double getPercentValueAsMultiplierSum(GameValueType gameValueType, GameValueType gameValueType2) {
        return this.f2953b.getPercentValueAsMultiplierSum(gameValueType, gameValueType2);
    }

    @Override // com.prineside.tdi2.GameValueProvider
    public final double getPercentValueAsMultiplierSumAll(GameValueType[] gameValueTypeArr) {
        return this.f2953b.getPercentValueAsMultiplierSumAll(gameValueTypeArr);
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameValueSystem$OnTileChange.class */
    public static class OnTileChange extends SerializableListener<GameValueSystem> implements Listener<TileChange> {
        /* synthetic */ OnTileChange(GameValueSystem gameValueSystem, byte b2) {
            this(gameValueSystem);
        }

        private OnTileChange() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnTileChange(GameValueSystem gameValueSystem) {
            this.f1759a = gameValueSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(TileChange tileChange) {
            Tile oldTile = tileChange.getOldTile();
            Tile newTile = tileChange.getNewTile();
            if ((oldTile instanceof CoreTile) || (oldTile instanceof TargetTile) || (oldTile instanceof GameValueTile) || (newTile instanceof CoreTile) || (newTile instanceof TargetTile) || (newTile instanceof GameValueTile)) {
                ((GameValueSystem) this.f1759a).recalculate();
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameValueSystem$GlobalTowerStatMutator.class */
    public static final class GlobalTowerStatMutator implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private float f2954a;

        /* renamed from: b, reason: collision with root package name */
        private String f2955b;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            output.writeFloat(this.f2954a);
            output.writeString(this.f2955b);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f2954a = input.readFloat();
            this.f2955b = input.readString();
        }

        private GlobalTowerStatMutator() {
            this.f2954a = 1.0f;
            this.f2955b = "";
        }

        public GlobalTowerStatMutator(String str, float f) {
            this.f2954a = 1.0f;
            this.f2955b = "";
            Preconditions.checkNotNull(str, "description can not be null");
            setMultiplier(f);
            this.f2955b = str;
            this.f2954a = f;
        }

        public final float getMultiplier() {
            return this.f2954a;
        }

        public final void setMultiplier(float f) {
            Preconditions.checkArgument(f >= 0.0f && PMath.isFinite(f), "invalid multiplier: %s", Float.valueOf(f));
            this.f2954a = f;
        }

        public final String getDescription() {
            return this.f2955b;
        }
    }
}
