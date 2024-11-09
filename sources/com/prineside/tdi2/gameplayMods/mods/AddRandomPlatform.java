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
import com.prineside.tdi2.enums.SpaceTileBonusType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.utils.IntPair;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/AddRandomPlatform.class */
public final class AddRandomPlatform extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2094a = TLog.forClass(AddRandomPlatform.class);

    /* renamed from: b, reason: collision with root package name */
    private int f2095b = 2;
    private int c = 3;
    private int d = 5;

    public AddRandomPlatform() {
        this.maxPower = 4;
        this.multipleInstances = false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeInt(this.f2095b);
        output.writeInt(this.c);
        output.writeInt(this.d);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2095b = input.readInt();
        this.c = input.readInt();
        this.d = input.readInt();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isImmediateAndNotListed() {
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.AddRandomPlatform");
    }

    public final int getPlatformCount() {
        return this.f2095b;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_add_random_platform", Integer.valueOf(getPlatformCount()));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final AddRandomPlatform cpy() {
        AddRandomPlatform addRandomPlatform = new AddRandomPlatform();
        a((GenericGameplayMod) addRandomPlatform);
        addRandomPlatform.f2095b = this.f2095b;
        addRandomPlatform.c = this.c;
        addRandomPlatform.d = this.d;
        return addRandomPlatform;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isAlwaysUseless(GameSystemProvider gameSystemProvider) {
        return gameSystemProvider.map.getMap().getAllTiles().size >= gameSystemProvider.map.getMap().getWidth() * gameSystemProvider.map.getMap().getHeight();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        if (gameSystemProvider.map.getMap().getAllTiles().size < gameSystemProvider.map.getMap().getWidth() * gameSystemProvider.map.getMap().getHeight()) {
            return null;
        }
        return () -> {
            return Game.i.localeManager.i18n.get("gpmod_precondition_no_empty_space_on_map");
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        ObjectPair objectPair;
        RandomXS128 modRandom = gameSystemProvider.gameplayMod.getModRandom(1430);
        Map map = gameSystemProvider.map.getMap();
        Array array = new Array(true, 1, ObjectPair.class);
        DelayedRemovalArray<Tile> allTiles = map.getAllTiles();
        for (int i = 0; i < map.getHeight(); i++) {
            for (int i2 = 0; i2 < map.getWidth(); i2++) {
                if (map.getTile(i2, i) == null) {
                    float f = Float.MAX_VALUE;
                    for (int i3 = 0; i3 < allTiles.size; i3++) {
                        Tile tile = allTiles.items[i3];
                        if (tile.type == TileType.ROAD || tile.type == TileType.SPAWN || tile.type == TileType.TARGET) {
                            float squareDistanceBetweenPoints = PMath.getSquareDistanceBetweenPoints(i2, i, tile.getX(), tile.getY());
                            if (f > squareDistanceBetweenPoints) {
                                f = squareDistanceBetweenPoints;
                            }
                        }
                    }
                    array.add(new ObjectPair(new IntPair(i2, i), Float.valueOf(f)));
                }
            }
        }
        if (array.size == 0) {
            f2094a.e("registration skipped - no empty space on map", new Object[0]);
            return false;
        }
        gameSystemProvider.TSH.sort.sort(array, (objectPair2, objectPair3) -> {
            return Float.compare(((Float) objectPair2.second).floatValue(), ((Float) objectPair3.second).floatValue());
        });
        int i4 = 0;
        while (true) {
            if (i4 >= this.f2095b) {
                break;
            }
            if (array.size == 0) {
                f2094a.i("skipped platform idx " + i4 + " - no space left", new Object[0]);
                break;
            }
            ObjectPair objectPair4 = (ObjectPair) array.first();
            if (array.size > 1) {
                int i5 = array.size;
                int i6 = 1;
                while (true) {
                    if (i6 >= array.size) {
                        break;
                    }
                    if (((Float) ((ObjectPair[]) array.items)[i6].second).equals(objectPair4.second)) {
                        i6++;
                    } else {
                        i5 = i6;
                        break;
                    }
                }
                objectPair = (ObjectPair) array.removeIndex(modRandom.nextInt(i5));
            } else {
                objectPair = objectPair4;
                array.setSize(0);
            }
            PlatformTile create = Game.i.tileManager.F.PLATFORM.create();
            create.bonusType = SpaceTileBonusType.values[modRandom.nextInt(SpaceTileBonusType.values.length)];
            create.bonusLevel = modRandom.nextInt(this.d - this.c) + this.c;
            if (create.bonusLevel > 5) {
                create.bonusLevel = 5;
            }
            gameSystemProvider.map.getMap().setTile(((IntPair) objectPair.first).f3850a, ((IntPair) objectPair.first).f3851b, create);
            gameSystemProvider.map.registerTile(create);
            gameSystemProvider.map.showTileWarningParticle(((IntPair) objectPair.first).f3850a, ((IntPair) objectPair.first).f3851b);
            if (gameSystemProvider._mapRendering != null) {
                gameSystemProvider._mapRendering.forceTilesRedraw(true);
            }
            i4++;
        }
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(AddRandomPlatform.class, str);
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
    public final AddRandomPlatform applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2095b = jsonValue.getInt("platformCount", this.f2095b);
        this.c = jsonValue.getInt("minBonusLevel", this.c);
        this.d = jsonValue.getInt("maxBonusLevel", this.d);
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/AddRandomPlatform$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2096a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2096a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2096a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("AddRandomPlatform");
            float f = bonusConfig.getFloat("countPerStage", 0.2f);
            AddRandomPlatform applyConfig = new AddRandomPlatform().applyConfig(bonusConfig);
            applyConfig.f2095b = MathUtils.round(applyConfig.f2095b + (f * (i - 1)));
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(applyConfig, i, array, new ProbableBonusesProvider.BonusProviderConfig(1.0f).setPowerUpProbabilityMultiplier(0.5f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
