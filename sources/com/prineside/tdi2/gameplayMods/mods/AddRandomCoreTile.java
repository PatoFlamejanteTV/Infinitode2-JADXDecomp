package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.PredefinedCoreTileType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.tiles.CoreTile;
import com.prineside.tdi2.utils.IntPair;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/AddRandomCoreTile.class */
public final class AddRandomCoreTile extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2090a = TLog.forClass(AddRandomCoreTile.class);

    /* renamed from: b, reason: collision with root package name */
    private float f2091b = 0.0f;
    private int c;

    public AddRandomCoreTile() {
        this.maxPower = 2;
        this.multipleInstances = false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2091b);
        output.writeVarInt(this.c, true);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2091b = input.readFloat();
        this.c = input.readVarInt(true);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isImmediateAndNotListed() {
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.AddRandomCoreTile");
    }

    public final RarityType getRarityType() {
        if (this.f2091b < 1.0f) {
            return RarityType.VERY_RARE;
        }
        if (this.f2091b < 2.0f) {
            return RarityType.EPIC;
        }
        return RarityType.LEGENDARY;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        RarityType rarityType = getRarityType();
        return Game.i.localeManager.i18n.format("gmod_descr_add_random_core_tile", "[#" + Game.i.progressManager.getRarityColor(rarityType).toString() + "]" + Game.i.progressManager.getRarityName(rarityType) + "[]");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final AddRandomCoreTile cpy() {
        AddRandomCoreTile addRandomCoreTile = new AddRandomCoreTile();
        a((GenericGameplayMod) addRandomCoreTile);
        addRandomCoreTile.f2091b = this.f2091b;
        addRandomCoreTile.c = this.c;
        return addRandomCoreTile;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isAlwaysUseless(GameSystemProvider gameSystemProvider) {
        if (gameSystemProvider.map.getMap().getAllTiles().size < gameSystemProvider.map.getMap().getWidth() * gameSystemProvider.map.getMap().getHeight() && gameSystemProvider.map.getMap().getTilesByType(CoreTile.class).size + 1 <= this.c) {
            return false;
        }
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        if (gameSystemProvider.map.getMap().getAllTiles().size >= gameSystemProvider.map.getMap().getWidth() * gameSystemProvider.map.getMap().getHeight()) {
            return () -> {
                return Game.i.localeManager.i18n.get("gpmod_precondition_no_empty_space_on_map");
            };
        }
        if (gameSystemProvider.map.getMap().getTilesByType(CoreTile.class).size + 1 <= this.c) {
            return null;
        }
        int i = this.c;
        return () -> {
            return Game.i.localeManager.i18n.format("gpmod_precondition_add_random_core_tile", Integer.valueOf(i));
        };
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        PredefinedCoreTileType predefinedCoreTileType;
        ObjectPair objectPair;
        f2090a.i("register rarity " + this.f2091b, new Object[0]);
        RandomXS128 modRandom = gameSystemProvider.gameplayMod.getModRandom(9615);
        switch (getRarityType()) {
            case VERY_RARE:
                if (modRandom.nextFloat() >= 0.5f) {
                    predefinedCoreTileType = PredefinedCoreTileType.BETA;
                    break;
                }
                predefinedCoreTileType = PredefinedCoreTileType.ALPHA;
                break;
            case EPIC:
                if (modRandom.nextFloat() < 0.5f) {
                    predefinedCoreTileType = PredefinedCoreTileType.DELTA;
                    break;
                } else {
                    predefinedCoreTileType = PredefinedCoreTileType.THETA;
                    break;
                }
            case LEGENDARY:
                if (modRandom.nextFloat() < 0.5f) {
                    predefinedCoreTileType = PredefinedCoreTileType.ZETA;
                    break;
                } else {
                    predefinedCoreTileType = PredefinedCoreTileType.XI;
                    break;
                }
            default:
                predefinedCoreTileType = PredefinedCoreTileType.ALPHA;
                break;
        }
        Map map = gameSystemProvider.map.getMap();
        Array array = new Array(true, 1, ObjectPair.class);
        DelayedRemovalArray<Tile> allTiles = map.getAllTiles();
        for (int i = 0; i < map.getHeight(); i++) {
            for (int i2 = 0; i2 < map.getWidth(); i2++) {
                if (map.getTile(i2, i) == null) {
                    float f = Float.MAX_VALUE;
                    float f2 = Float.MAX_VALUE;
                    for (int i3 = 0; i3 < allTiles.size; i3++) {
                        Tile tile = allTiles.items[i3];
                        if (tile.type == TileType.PLATFORM) {
                            float squareDistanceBetweenPoints = PMath.getSquareDistanceBetweenPoints(i2, i, tile.getX(), tile.getY());
                            if (f > squareDistanceBetweenPoints) {
                                f = squareDistanceBetweenPoints;
                            }
                        } else if (tile.type == TileType.CORE) {
                            float squareDistanceBetweenPoints2 = PMath.getSquareDistanceBetweenPoints(i2, i, tile.getX(), tile.getY());
                            if (f2 > squareDistanceBetweenPoints2) {
                                f2 = squareDistanceBetweenPoints2;
                            }
                        }
                    }
                    if (f > 9.61f) {
                        if (f > 26.009998f) {
                            f = 0.0f;
                        } else {
                            f = 9.61f;
                        }
                    }
                    if (f2 != Float.MAX_VALUE) {
                        f += 1.0f / f2;
                    }
                    array.add(new ObjectPair(new IntPair(i2, i), Float.valueOf(f)));
                }
            }
        }
        if (array.size == 0) {
            f2090a.e("registration skipped - no empty space on map", new Object[0]);
            return false;
        }
        gameSystemProvider.TSH.sort.sort(array, (objectPair2, objectPair3) -> {
            return Float.compare(((Float) objectPair3.second).floatValue(), ((Float) objectPair2.second).floatValue());
        });
        ObjectPair objectPair4 = (ObjectPair) array.first();
        if (array.size > 1) {
            int i4 = 1;
            while (true) {
                if (i4 < array.size) {
                    if (((Float) ((ObjectPair[]) array.items)[i4].second).equals(objectPair4.second)) {
                        i4++;
                    } else {
                        array.setSize(i4);
                    }
                }
            }
            objectPair = (ObjectPair) array.get(modRandom.nextInt(array.size));
        } else {
            objectPair = objectPair4;
        }
        CoreTile create = Game.i.tileManager.F.CORE.create();
        create.predefinedType = predefinedCoreTileType;
        gameSystemProvider.map.getMap().setTile(((IntPair) objectPair.first).f3850a, ((IntPair) objectPair.first).f3851b, create);
        gameSystemProvider.map.registerTile(create);
        if (gameSystemProvider._mapRendering != null) {
            gameSystemProvider._mapRendering.forceTilesRedraw(true);
        }
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(AddRandomCoreTile.class, str);
        if (activeModFromSource != null) {
            activeModFromSource.getMod().setRegisteredPower(this.power);
            return false;
        }
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OTHER;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final AddRandomCoreTile applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        f2090a.i("applyConfig " + this.f2091b + " => " + jsonValue.getFloat("coreRarity", this.f2091b), new Object[0]);
        this.f2091b = jsonValue.getFloat("coreRarity", this.f2091b);
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/AddRandomCoreTile$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2093a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2093a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2093a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("AddRandomCoreTile");
            float f = bonusConfig.getFloat("coreRarityPerStage", 0.2f);
            AddRandomCoreTile addRandomCoreTile = new AddRandomCoreTile();
            addRandomCoreTile.c = MathUtils.floor(bonusConfig.getFloat("maxCoresOnMap", 2.0f) + (bonusConfig.getFloat("maxCoresOnMapPerStage", 0.0f) * (i - 1)) + 0.001f);
            addRandomCoreTile.f2091b = bonusConfig.getFloat("coreRarity", addRandomCoreTile.f2091b);
            addRandomCoreTile.applyConfig(bonusConfig);
            addRandomCoreTile.f2091b += f * (i - 1);
            logger.i("must be rarity " + addRandomCoreTile.f2091b, new Object[0]);
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(addRandomCoreTile, i, array, new ProbableBonusesProvider.BonusProviderConfig(0.4f).setPowerUpProbabilityMultiplier(0.1f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
