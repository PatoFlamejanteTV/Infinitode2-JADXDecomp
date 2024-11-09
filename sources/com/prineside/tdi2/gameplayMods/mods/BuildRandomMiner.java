package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.MinerType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/BuildRandomMiner.class */
public final class BuildRandomMiner extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2106a = TLog.forClass(BuildRandomMiner.class);
    public int minerCount;
    public int upgradeLevel;
    public float doubleSpeedDuration;

    public BuildRandomMiner() {
        this.maxPower = 3;
        this.multipleInstances = false;
        this.minerCount = 1;
        this.upgradeLevel = 1;
        this.doubleSpeedDuration = 600.0f;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeInt(this.minerCount);
        output.writeInt(this.upgradeLevel);
        output.writeFloat(this.doubleSpeedDuration);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.minerCount = input.readInt();
        this.upgradeLevel = input.readInt();
        this.doubleSpeedDuration = input.readFloat();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isImmediateAndNotListed() {
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.BuildRandomMiner");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_build_random_miner", Integer.valueOf(this.minerCount), Integer.valueOf(this.upgradeLevel), StringFormatter.timePassed(MathUtils.round(this.doubleSpeedDuration), false, false));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final BuildRandomMiner cpy() {
        BuildRandomMiner buildRandomMiner = new BuildRandomMiner();
        a(buildRandomMiner);
        buildRandomMiner.minerCount = this.minerCount;
        buildRandomMiner.upgradeLevel = this.upgradeLevel;
        buildRandomMiner.doubleSpeedDuration = this.doubleSpeedDuration;
        return buildRandomMiner;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isAlwaysUseless(GameSystemProvider gameSystemProvider) {
        return gameSystemProvider.map.getMap().getTilesByType(SourceTile.class).size == 0;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        if (getSuitablePlaces(gameSystemProvider).size == 0) {
            return () -> {
                return Game.i.localeManager.i18n.get("gpmod_precondition_build_random_miner");
            };
        }
        return null;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        RandomXS128 modRandom = gameSystemProvider.gameplayMod.getModRandom(9427);
        Array<ObjectPair<SourceTile, MinerType>> suitablePlaces = getSuitablePlaces(gameSystemProvider);
        for (int i = 0; i < this.minerCount; i++) {
            if (suitablePlaces.size > 0) {
                ObjectPair<SourceTile, MinerType> removeIndex = suitablePlaces.removeIndex(modRandom.nextInt(suitablePlaces.size));
                Miner buildMiner = gameSystemProvider.miner.buildMiner(removeIndex.second, removeIndex.first.getX(), removeIndex.first.getY(), false, false);
                if (buildMiner != null) {
                    if (this.upgradeLevel > 0) {
                        buildMiner.setUpgradeLevel(this.upgradeLevel);
                    }
                    if (this.doubleSpeedDuration > 0.0f) {
                        buildMiner.doubleSpeedTimeLeft = this.doubleSpeedDuration;
                    }
                }
            } else {
                f2106a.e("bonus ignored - no suitable place for miner " + (i + 1) + " / " + this.minerCount, new Object[0]);
            }
        }
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(BuildRandomMiner.class, str);
        if (activeModFromSource != null) {
            activeModFromSource.getMod().setRegisteredPower(this.power);
            return false;
        }
        return true;
    }

    public static Array<ObjectPair<SourceTile, MinerType>> getSuitablePlaces(GameSystemProvider gameSystemProvider) {
        boolean[][] zArr = new boolean[MinerType.values.length][ResourceType.values.length];
        for (MinerType minerType : MinerType.values) {
            if (gameSystemProvider.miner.getMaxMinersCount(minerType) > 0) {
                Miner.Factory<? extends Miner> factory = Game.i.minerManager.getFactory(minerType);
                for (ResourceType resourceType : ResourceType.values) {
                    if (factory.canMineResource(resourceType)) {
                        zArr[minerType.ordinal()][resourceType.ordinal()] = true;
                    }
                }
            }
        }
        Array<ObjectPair<SourceTile, MinerType>> array = new Array<>(true, 1, ObjectPair.class);
        for (int i = 0; i < gameSystemProvider.map.getMap().getAllTiles().size; i++) {
            Tile tile = gameSystemProvider.map.getMap().getAllTiles().get(i);
            if (tile.type == TileType.SOURCE) {
                SourceTile sourceTile = (SourceTile) tile;
                if (sourceTile.miner == null) {
                    for (ResourceType resourceType2 : ResourceType.values) {
                        if (sourceTile.getResourcesCount(resourceType2) > 0) {
                            for (MinerType minerType2 : MinerType.values) {
                                if (zArr[minerType2.ordinal()][resourceType2.ordinal()]) {
                                    array.add(new ObjectPair<>(sourceTile, minerType2));
                                }
                            }
                        }
                    }
                }
            }
        }
        return array;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.LOOTING;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final BuildRandomMiner applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.minerCount = jsonValue.getInt("minerCount", this.minerCount);
        this.upgradeLevel = jsonValue.getInt("upgradeLevel", this.upgradeLevel);
        this.doubleSpeedDuration = jsonValue.getFloat("doubleSpeedDuration", this.doubleSpeedDuration);
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/BuildRandomMiner$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2107a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2107a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2107a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("BuildRandomMiner");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new BuildRandomMiner().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(1.0f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
