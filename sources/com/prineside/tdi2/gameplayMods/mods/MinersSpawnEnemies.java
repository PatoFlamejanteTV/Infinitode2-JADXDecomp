package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.EnemyGroup;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Wave;
import com.prineside.tdi2.WaveTemplates;
import com.prineside.tdi2.buffs.NoBonusSystemPointsBuff;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemySpawn;
import com.prineside.tdi2.events.game.MinerResourceChange;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import java.util.Arrays;
import java.util.Iterator;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/MinersSpawnEnemies.class */
public final class MinersSpawnEnemies extends GenericGameplayMod implements Listener<MinerResourceChange> {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2154a = TLog.forClass(MinersSpawnEnemies.class);
    public boolean sharedWaveNumber;
    public boolean onlyAllowedEnemies;
    public float enemyCountLimit;
    public float enemyCountLimitPerPower;
    public int actualWaveDiffMin;
    public int actualWaveDiffMax;
    public float lootMultiplier;
    public float coinsMultiplier;
    public float healthMultiplier;
    public float xpMultiplier;
    public float scoreMultiplier;
    public int startingWave;
    public float difficultyMultiplier;
    public float resourceInterval;
    public float resourceIntervalPerPower;
    public Array<Array<String>> waveTemplatesPerResource;

    /* renamed from: b, reason: collision with root package name */
    @Null
    private GameSystemProvider f2155b;
    private OnEnemySpawn c;
    public boolean enabled;
    public int intervalCounter;
    public int[] enemyQueueWaves;
    public Array<Array<Enemy>> enemyQueue;
    public int enemiesSpawned;
    public int realEnemiesCounter;

    public MinersSpawnEnemies() {
        this.maxPower = 5;
        this.multipleInstances = false;
        this.sharedWaveNumber = true;
        this.onlyAllowedEnemies = true;
        this.enemyCountLimit = 5.0f;
        this.enemyCountLimitPerPower = 1.0f;
        this.actualWaveDiffMin = -10;
        this.actualWaveDiffMax = 50;
        this.lootMultiplier = 0.5f;
        this.coinsMultiplier = 0.3f;
        this.healthMultiplier = 1.0f;
        this.xpMultiplier = 1.0f;
        this.scoreMultiplier = 0.5f;
        this.startingWave = 0;
        this.difficultyMultiplier = 0.75f;
        this.resourceInterval = 6.0f;
        this.resourceIntervalPerPower = -1.0f;
        this.waveTemplatesPerResource = new Array<>(true, 1, Array.class);
        for (int i = 0; i < ResourceType.values.length; i++) {
            this.waveTemplatesPerResource.add(new Array<>(true, 1, String.class));
        }
        this.waveTemplatesPerResource.get(ResourceType.SCALAR.ordinal()).addAll("REGULAR_MEDIUM", "REGULAR_LOW", "TOXIC_MEDIUM", "TOXIC_ARMORED");
        this.waveTemplatesPerResource.get(ResourceType.VECTOR.ordinal()).addAll("STRONG_MEDIUM", "STRONG_LOW", "FAST_MEDIUM", "FAST_LOW", "HEALER_STRONG");
        this.waveTemplatesPerResource.get(ResourceType.MATRIX.ordinal()).addAll("HELI_MEDIUM", "JET_MEDIUM", "HEALER_JET", "ICY_HIGH", "ICY_TOXIC", "HEALER_ICY");
        this.waveTemplatesPerResource.get(ResourceType.TENSOR.ordinal()).addAll("ARMORED_LOW", "ARMORED_REGULAR", "ARMORED_STRONG", "HEALER_REGULAR", "HEALER_SLOW", "FIGHTER_ARMORED");
        this.waveTemplatesPerResource.get(ResourceType.INFIAR.ordinal()).addAll("FIGHTER_LOW", "FIGHTER_MEDIUM", "LIGHT_MEDIUM", "LIGHT_HIGH", "LIGHT_FAST");
        this.c = new OnEnemySpawn(this);
        this.enabled = true;
        this.enemyQueueWaves = new int[ResourceType.values.length];
        this.enemyQueue = new Array<>(true, ResourceType.values.length, Array.class);
        int length = ResourceType.values.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.enemyQueue.add(new Array<>(true, 1, Enemy.class));
        }
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeBoolean(this.sharedWaveNumber);
        output.writeBoolean(this.onlyAllowedEnemies);
        output.writeFloat(this.enemyCountLimit);
        output.writeFloat(this.enemyCountLimitPerPower);
        output.writeInt(this.actualWaveDiffMin);
        output.writeInt(this.actualWaveDiffMax);
        output.writeFloat(this.lootMultiplier);
        output.writeFloat(this.coinsMultiplier);
        output.writeFloat(this.healthMultiplier);
        output.writeFloat(this.xpMultiplier);
        output.writeFloat(this.scoreMultiplier);
        output.writeInt(this.startingWave);
        output.writeFloat(this.difficultyMultiplier);
        output.writeFloat(this.resourceInterval);
        output.writeFloat(this.resourceIntervalPerPower);
        kryo.writeObject(output, this.waveTemplatesPerResource);
        kryo.writeObjectOrNull(output, this.f2155b, GameSystemProvider.class);
        output.writeBoolean(this.enabled);
        output.writeInt(this.intervalCounter);
        kryo.writeObject(output, this.enemyQueueWaves);
        kryo.writeObject(output, this.enemyQueue);
        output.writeInt(this.enemiesSpawned);
        output.writeInt(this.realEnemiesCounter);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.sharedWaveNumber = input.readBoolean();
        this.onlyAllowedEnemies = input.readBoolean();
        this.enemyCountLimit = input.readFloat();
        this.enemyCountLimitPerPower = input.readFloat();
        this.actualWaveDiffMin = input.readInt();
        this.actualWaveDiffMax = input.readInt();
        this.lootMultiplier = input.readFloat();
        this.coinsMultiplier = input.readFloat();
        this.healthMultiplier = input.readFloat();
        this.xpMultiplier = input.readFloat();
        this.scoreMultiplier = input.readFloat();
        this.startingWave = input.readInt();
        this.difficultyMultiplier = input.readFloat();
        this.resourceInterval = input.readFloat();
        this.resourceIntervalPerPower = input.readFloat();
        this.waveTemplatesPerResource = (Array) kryo.readObject(input, Array.class);
        this.f2155b = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        this.enabled = input.readBoolean();
        this.intervalCounter = input.readInt();
        this.enemyQueueWaves = (int[]) kryo.readObject(input, int[].class);
        this.enemyQueue = (Array) kryo.readObject(input, Array.class);
        this.enemiesSpawned = input.readInt();
        this.realEnemiesCounter = input.readInt();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.MinersSpawnEnemies");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_miners_spawn_enemies", Integer.valueOf(a()));
    }

    private int a() {
        return MathUtils.clamp(MathUtils.round(this.resourceInterval + (this.resourceIntervalPerPower * this.power)), 1, Integer.MAX_VALUE);
    }

    private int a(int i) {
        return MathUtils.round(this.f2155b.wave.getWaveDifficultyProvider().getDifficultWavesMultiplier(i) * this.difficultyMultiplier);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final MinersSpawnEnemies cpy() {
        MinersSpawnEnemies minersSpawnEnemies = new MinersSpawnEnemies();
        a(minersSpawnEnemies);
        minersSpawnEnemies.sharedWaveNumber = this.sharedWaveNumber;
        minersSpawnEnemies.onlyAllowedEnemies = this.onlyAllowedEnemies;
        minersSpawnEnemies.enemyCountLimit = this.enemyCountLimit;
        minersSpawnEnemies.enemyCountLimitPerPower = this.enemyCountLimitPerPower;
        minersSpawnEnemies.actualWaveDiffMin = this.actualWaveDiffMin;
        minersSpawnEnemies.actualWaveDiffMax = this.actualWaveDiffMax;
        minersSpawnEnemies.lootMultiplier = this.lootMultiplier;
        minersSpawnEnemies.coinsMultiplier = this.coinsMultiplier;
        minersSpawnEnemies.healthMultiplier = this.healthMultiplier;
        minersSpawnEnemies.xpMultiplier = this.xpMultiplier;
        minersSpawnEnemies.scoreMultiplier = this.scoreMultiplier;
        minersSpawnEnemies.startingWave = this.startingWave;
        minersSpawnEnemies.difficultyMultiplier = this.difficultyMultiplier;
        minersSpawnEnemies.resourceInterval = this.resourceInterval;
        minersSpawnEnemies.resourceIntervalPerPower = this.resourceIntervalPerPower;
        for (int i = 0; i < ResourceType.values.length; i++) {
            minersSpawnEnemies.waveTemplatesPerResource.get(i).clear();
            minersSpawnEnemies.waveTemplatesPerResource.get(i).addAll(this.waveTemplatesPerResource.get(i));
        }
        return minersSpawnEnemies;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isAlwaysUseless(GameSystemProvider gameSystemProvider) {
        return gameSystemProvider.map.getMap().getTilesByType(SourceTile.class).size == 0;
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
        int[] iArr;
        int i;
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(MinersSpawnEnemies.class, str);
        if (activeModFromSource == null) {
            this.f2155b = gameSystemProvider;
            if (this.startingWave > 0) {
                iArr = this.enemyQueueWaves;
                i = this.startingWave;
            } else {
                iArr = this.enemyQueueWaves;
                i = gameSystemProvider.wave.wave == null ? 1 : gameSystemProvider.wave.wave.waveNumber;
            }
            Arrays.fill(iArr, i);
            gameSystemProvider.events.getListeners(MinerResourceChange.class).addStateAffecting(this);
            gameSystemProvider.events.getListeners(EnemySpawn.class).addStateAffecting(this.c);
            return true;
        }
        activeModFromSource.getMod().setRegisteredPower(this.power);
        return false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OTHER;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getAdditionalCategory() {
        return GameplayModCategory.LOOTING;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final MinersSpawnEnemies applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.sharedWaveNumber = jsonValue.getBoolean("sharedWaveNumber", this.sharedWaveNumber);
        this.onlyAllowedEnemies = jsonValue.getBoolean("onlyAllowedEnemies", this.onlyAllowedEnemies);
        this.enemyCountLimit = jsonValue.getFloat("enemyCountLimit", this.enemyCountLimit);
        this.enemyCountLimitPerPower = jsonValue.getFloat("enemyCountLimitPerPower", this.enemyCountLimitPerPower);
        this.actualWaveDiffMax = jsonValue.getInt("actualWaveDiffMax", this.actualWaveDiffMax);
        this.actualWaveDiffMin = jsonValue.getInt("actualWaveDiffMin", this.actualWaveDiffMin);
        this.lootMultiplier = jsonValue.getFloat("lootMultiplier", this.lootMultiplier);
        this.coinsMultiplier = jsonValue.getFloat("coinsMultiplier", this.coinsMultiplier);
        this.healthMultiplier = jsonValue.getFloat("healthMultiplier", this.healthMultiplier);
        this.xpMultiplier = jsonValue.getFloat("xpMultiplier", this.xpMultiplier);
        this.scoreMultiplier = jsonValue.getFloat("scoreMultiplier", this.scoreMultiplier);
        this.startingWave = jsonValue.getInt("startingWave", this.startingWave);
        this.difficultyMultiplier = jsonValue.getFloat("difficultyMultiplier", this.difficultyMultiplier);
        this.resourceInterval = jsonValue.getFloat("resourceInterval", this.resourceInterval);
        this.resourceIntervalPerPower = jsonValue.getFloat("resourceIntervalPerPower", this.resourceIntervalPerPower);
        JsonValue child = jsonValue.getChild("waveTemplatesPerResource");
        if (child != null) {
            Iterator<JsonValue> iterator2 = child.iterator2();
            while (iterator2.hasNext()) {
                JsonValue next = iterator2.next();
                Array<String> array = this.waveTemplatesPerResource.get(ResourceType.valueOf(next.name).ordinal());
                array.clear();
                Iterator<JsonValue> iterator22 = next.iterator2();
                while (iterator22.hasNext()) {
                    array.add(iterator22.next().asString());
                }
            }
        }
        return this;
    }

    private Enemy a(ResourceType resourceType) {
        Array<Enemy> array = this.enemyQueue.get(resourceType.ordinal());
        if (array.size == 0) {
            Array<String> array2 = this.waveTemplatesPerResource.get(resourceType.ordinal());
            if (array2.size == 0) {
                return null;
            }
            String str = array2.get(this.f2155b.gameState.randomInt(array2.size));
            int i = this.sharedWaveNumber ? this.enemyQueueWaves[0] : this.enemyQueueWaves[resourceType.ordinal()];
            if (this.f2155b.wave.wave != null) {
                i = MathUtils.clamp(i, this.f2155b.wave.wave.waveNumber + this.actualWaveDiffMin, this.f2155b.wave.wave.waveNumber + this.actualWaveDiffMax);
            }
            if (i <= 0) {
                i = 1;
            }
            WaveTemplates.WaveTemplate byName = WaveTemplates.getByName(str);
            if (byName == null) {
                return null;
            }
            Wave generateWave = this.f2155b.wave.generateWave(byName, i, a(i));
            for (int i2 = 0; i2 < generateWave.enemyGroups.size; i2++) {
                EnemyGroup enemyGroup = generateWave.enemyGroups.get(i2);
                for (int i3 = 0; i3 < enemyGroup.count; i3++) {
                    Enemy obtain = Game.i.enemyManager.getFactory(enemyGroup.getEnemyType()).obtain();
                    obtain.setSpeed(enemyGroup.speed);
                    obtain.maxHealth = enemyGroup.health;
                    obtain.setHealth(enemyGroup.health);
                    obtain.killScore = enemyGroup.killScore;
                    obtain.bounty = enemyGroup.bounty;
                    obtain.setKillExp(enemyGroup.killExp);
                    array.add(obtain);
                }
            }
            f2154a.i("generate wave, number: " + i + ", difficulty: " + a(i), new Object[0]);
            int i4 = 37;
            for (int i5 = 0; i5 < array.size; i5++) {
                int randomIntIndependent = this.f2155b.gameState.randomIntIndependent(array.size, i4);
                i4 = ((i4 * 31) + randomIntIndependent) % 65536;
                array.swap(randomIntIndependent, i5);
            }
            if (this.sharedWaveNumber) {
                this.enemyQueueWaves[0] = i + 1;
            } else {
                this.enemyQueueWaves[resourceType.ordinal()] = i + 1;
            }
        }
        if (array.size == 0) {
            return null;
        }
        return array.pop();
    }

    @Override // com.prineside.tdi2.events.Listener
    public final void handleEvent(MinerResourceChange minerResourceChange) {
        if (minerResourceChange.isMined() && this.enabled) {
            this.intervalCounter++;
            if (this.intervalCounter >= a()) {
                this.intervalCounter = 0;
                int i = (int) ((this.enemyCountLimit + (this.enemyCountLimitPerPower * this.power)) * this.realEnemiesCounter);
                for (int i2 = 0; i2 < minerResourceChange.getDelta() && this.enemiesSpawned < i; i2++) {
                    Enemy a2 = a(minerResourceChange.getResourceType());
                    if (a2 != null) {
                        if (this.f2155b.pathfinding.findPathToTargetTile(minerResourceChange.getMiner().getTile(), a2.getTypeForPathfinding()) != null) {
                            a2.notAffectsWaveKillCounter.addReason("MinersSpawnEnemiesCustomSpawn");
                            a2.ignoredByAutoWaveCall = true;
                            this.f2155b.enemy.addEnemyWithFirstSpawn(a2, minerResourceChange.getMiner().getTile(), this.f2155b.gameState.randomInt(11));
                            NoBonusSystemPointsBuff noBonusSystemPointsBuff = new NoBonusSystemPointsBuff();
                            noBonusSystemPointsBuff.setup(9000.0f, 9000.0f);
                            this.f2155b.buff.P_NO_BONUS_SYSTEM_POINTS.addBuff(a2, noBonusSystemPointsBuff);
                            a2.bounty *= this.coinsMultiplier;
                            if (this.healthMultiplier != 1.0f) {
                                a2.maxHealth *= this.healthMultiplier;
                                a2.setHealth(a2.getHealth() * this.healthMultiplier);
                            }
                            a2.killScore = MathUtils.round(a2.killScore * this.scoreMultiplier);
                            a2.setKillExp(a2.getKillExp() * this.xpMultiplier);
                            if (this.lootMultiplier < 1.0f && a2.loot != null && a2.loot.size != 0 && this.f2155b.gameState.randomFloat() > this.lootMultiplier) {
                                a2.loot = null;
                            }
                            this.enemiesSpawned++;
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/MinersSpawnEnemies$OnEnemySpawn.class */
    public static final class OnEnemySpawn implements KryoSerializable, Listener<EnemySpawn> {

        /* renamed from: a, reason: collision with root package name */
        private MinersSpawnEnemies f2157a;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f2157a);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f2157a = (MinersSpawnEnemies) kryo.readObject(input, MinersSpawnEnemies.class);
        }

        private OnEnemySpawn() {
        }

        public OnEnemySpawn(MinersSpawnEnemies minersSpawnEnemies) {
            this.f2157a = minersSpawnEnemies;
        }

        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemySpawn enemySpawn) {
            if (!enemySpawn.getEnemy().notAffectsWaveKillCounter.isTrue()) {
                this.f2157a.realEnemiesCounter++;
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/MinersSpawnEnemies$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2156a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2156a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2156a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("MinersSpawnEnemies");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new MinersSpawnEnemies().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(1.0f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
