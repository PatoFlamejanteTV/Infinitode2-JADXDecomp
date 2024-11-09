package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemySpawn;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayMod;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.items.ResourceItem;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import java.util.Arrays;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/EnemiesDropResources.class */
public final class EnemiesDropResources extends GenericGameplayMod implements Listener<EnemySpawn> {
    public float resourceCountMultiplier = 0.025f;
    public float resourceCountDispersion = 0.25f;
    public float maxResourcesPerPower = 50.0f;
    public int baseEnemyInterval = 5;
    public float enemyIntervalDeltaPerPower = -1.0f;
    public int minEnemyInterval = 3;

    /* renamed from: a, reason: collision with root package name */
    @Null
    private GameSystemProvider f2118a;

    /* renamed from: b, reason: collision with root package name */
    private int[] f2119b;
    private int c;
    private int d;

    public EnemiesDropResources() {
        this.maxPower = 3;
        this.multipleInstances = false;
        this.f2119b = new int[ResourceType.values.length];
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.resourceCountMultiplier);
        output.writeFloat(this.resourceCountDispersion);
        output.writeFloat(this.maxResourcesPerPower);
        output.writeVarInt(this.baseEnemyInterval, true);
        output.writeFloat(this.enemyIntervalDeltaPerPower);
        output.writeVarInt(this.minEnemyInterval, true);
        kryo.writeObjectOrNull(output, this.f2118a, GameSystemProvider.class);
        kryo.writeObject(output, this.f2119b);
        output.writeVarInt(this.c, true);
        output.writeVarInt(this.d, true);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.resourceCountMultiplier = input.readFloat();
        this.resourceCountDispersion = input.readFloat();
        this.maxResourcesPerPower = input.readFloat();
        this.baseEnemyInterval = input.readVarInt(true);
        this.enemyIntervalDeltaPerPower = input.readFloat();
        this.minEnemyInterval = input.readVarInt(true);
        this.f2118a = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        this.f2119b = (int[]) kryo.readObject(input, int[].class);
        this.c = input.readVarInt(true);
        this.d = input.readVarInt(true);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.LOOTING;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.EnemiesDropResources");
    }

    public final int getMaxTotalResourcesPercentage() {
        return (int) (this.power * this.maxResourcesPerPower);
    }

    private void a(Enemy enemy) {
        ResourceItem resourceItem;
        this.c++;
        if (this.c >= this.baseEnemyInterval) {
            this.c -= this.baseEnemyInterval;
            int i = 0;
            for (int i2 : this.f2119b) {
                i += i2;
            }
            if (i == 0) {
                return;
            }
            int randomInt = this.f2118a.gameState.randomInt(i);
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (true) {
                if (i5 >= this.f2119b.length) {
                    break;
                }
                int i6 = this.f2119b[i5];
                if (i3 + i6 > randomInt) {
                    i4 = i5;
                    break;
                } else {
                    i3 += i6;
                    i5++;
                }
            }
            int max = Math.max(1, MathUtils.round(this.f2119b[i4] * (1.0f - this.resourceCountDispersion) * this.resourceCountMultiplier));
            int max2 = Math.max(1, MathUtils.round(this.f2119b[i4] * (1.0f + this.resourceCountDispersion) * this.resourceCountMultiplier));
            int randomInt2 = max2 == max ? max : max + this.f2118a.gameState.randomInt((max2 - max) + 1);
            int i7 = randomInt2;
            if (randomInt2 > 0) {
                int i8 = 0;
                for (ResourceType resourceType : ResourceType.values) {
                    i8 += this.f2118a.gameState.getResources(resourceType);
                }
                if (this.d + i7 > i8 * getMaxTotalResourcesPercentage() * 0.01f) {
                    return;
                }
                switch (ResourceType.values[i4]) {
                    case VECTOR:
                        resourceItem = Item.D.RESOURCE_VECTOR;
                        break;
                    case MATRIX:
                        resourceItem = Item.D.RESOURCE_MATRIX;
                        break;
                    case TENSOR:
                        resourceItem = Item.D.RESOURCE_TENSOR;
                        break;
                    case INFIAR:
                        resourceItem = Item.D.RESOURCE_INFIAR;
                        break;
                    default:
                        resourceItem = Item.D.RESOURCE_SCALAR;
                        break;
                }
                ItemStack addLoot = this.f2118a.loot.addLoot(enemy, resourceItem, i7);
                if (addLoot != null) {
                    addLoot.setCovered(false);
                }
                this.d += i7;
            }
        }
    }

    public final int getEnemyInterval() {
        int i = this.baseEnemyInterval + ((int) (this.enemyIntervalDeltaPerPower * (this.power - 1)));
        int i2 = i;
        if (i < this.minEnemyInterval) {
            i2 = this.minEnemyInterval;
        }
        return i2;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_enemies_drop_resources", Game.i.localeManager.formatNthEnemy(getEnemyInterval()), Integer.valueOf(getMaxTotalResourcesPercentage()));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayMod cpy() {
        EnemiesDropResources enemiesDropResources = new EnemiesDropResources();
        a(enemiesDropResources);
        enemiesDropResources.resourceCountMultiplier = this.resourceCountMultiplier;
        enemiesDropResources.resourceCountDispersion = this.resourceCountDispersion;
        enemiesDropResources.maxResourcesPerPower = this.maxResourcesPerPower;
        enemiesDropResources.baseEnemyInterval = this.baseEnemyInterval;
        enemiesDropResources.enemyIntervalDeltaPerPower = this.enemyIntervalDeltaPerPower;
        enemiesDropResources.minEnemyInterval = this.minEnemyInterval;
        return enemiesDropResources;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        if (gameSystemProvider.map.getMap().getTilesByType(SourceTile.class).size == 0) {
            return () -> {
                return Game.i.localeManager.i18n.get("gpmod_precondition_no_source_tiles_on_map");
            };
        }
        return null;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(EnemiesDropResources.class, str);
        if (activeModFromSource == null) {
            this.f2118a = gameSystemProvider;
            Arrays.fill(this.f2119b, 0);
            Map map = gameSystemProvider.map.getMap();
            float[] fArr = new float[ResourceType.values.length];
            int i = map.getAllTiles().size;
            for (int i2 = 0; i2 < i; i2++) {
                Tile tile = map.getAllTiles().items[i2];
                if (tile instanceof SourceTile) {
                    SourceTile sourceTile = (SourceTile) tile;
                    float resourceDensity = sourceTile.getResourceDensity();
                    int i3 = 0;
                    for (ResourceType resourceType : ResourceType.values) {
                        i3 += sourceTile.getResourcesCount(resourceType);
                    }
                    for (ResourceType resourceType2 : ResourceType.values) {
                        int ordinal = resourceType2.ordinal();
                        fArr[ordinal] = fArr[ordinal] + ((sourceTile.getResourcesCount(r0) / i3) * resourceDensity);
                    }
                }
            }
            for (int i4 = 0; i4 < fArr.length; i4++) {
                this.f2119b[i4] = (int) (fArr[i4] * 100.0f);
            }
            gameSystemProvider.events.getListeners(EnemySpawn.class).addStateAffecting(this).setDescription("Fills enemies with resources");
            return true;
        }
        ((EnemiesDropResources) activeModFromSource.getMod()).power = this.power;
        return false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final EnemiesDropResources applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.resourceCountMultiplier = jsonValue.getFloat("resourceCountMultiplier", this.resourceCountMultiplier);
        this.resourceCountDispersion = jsonValue.getFloat("resourceCountDispersion", this.resourceCountDispersion);
        this.maxResourcesPerPower = jsonValue.getFloat("maxResourcesPerPower", this.maxResourcesPerPower);
        this.baseEnemyInterval = jsonValue.getInt("baseEnemyInterval", this.baseEnemyInterval);
        this.enemyIntervalDeltaPerPower = jsonValue.getFloat("enemyIntervalDeltaPerPower", this.enemyIntervalDeltaPerPower);
        this.minEnemyInterval = jsonValue.getInt("minEnemyInterval", this.minEnemyInterval);
        return this;
    }

    @Override // com.prineside.tdi2.events.Listener
    public final void handleEvent(EnemySpawn enemySpawn) {
        a(enemySpawn.getEnemy());
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/EnemiesDropResources$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2121a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2121a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2121a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("EnemiesDropResources");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new EnemiesDropResources().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(1.0f).setPowerUpProbabilityMultiplier(0.9f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
