package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.math.RandomXS128;
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
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Wave;
import com.prineside.tdi2.buffs.NoDamageBuff;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemySpawn;
import com.prineside.tdi2.events.game.WaveComplete;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/SummonLootBoss.class */
public final class SummonLootBoss extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2178a = TLog.forClass(SummonLootBoss.class);
    public int itemCount;
    public float bossHp;
    public boolean damageToBase;
    public float coinMultiplier;

    /* renamed from: b, reason: collision with root package name */
    @Null
    private GameSystemProvider f2179b;

    @Null
    private Wave c;
    private OnEnemySpawn d;
    private OnWaveComplete e;
    private boolean f;

    public SummonLootBoss() {
        this.maxPower = 3;
        this.multipleInstances = false;
        this.itemCount = 20;
        this.bossHp = 0.8f;
        this.damageToBase = false;
        this.coinMultiplier = 1.0f;
        this.d = new OnEnemySpawn(this);
        this.e = new OnWaveComplete(this, (byte) 0);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeInt(this.itemCount);
        output.writeFloat(this.bossHp);
        output.writeBoolean(this.damageToBase);
        output.writeFloat(this.coinMultiplier);
        kryo.writeObjectOrNull(output, this.f2179b, GameSystemProvider.class);
        kryo.writeObjectOrNull(output, this.c, Wave.class);
        kryo.writeObject(output, this.d);
        kryo.writeObject(output, this.e);
        output.writeBoolean(this.f);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.itemCount = input.readInt();
        this.bossHp = input.readFloat();
        this.damageToBase = input.readBoolean();
        this.coinMultiplier = input.readFloat();
        this.f2179b = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        this.c = (Wave) kryo.readObjectOrNull(input, Wave.class);
        this.d = (OnEnemySpawn) kryo.readObject(input, OnEnemySpawn.class);
        this.e = (OnWaveComplete) kryo.readObject(input, OnWaveComplete.class);
        this.f = input.readBoolean();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isImmediateAndNotListed() {
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.LOOTING;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.SummonLootBoss");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_summon_loot_boss", new Object[0]);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final SummonLootBoss cpy() {
        SummonLootBoss summonLootBoss = new SummonLootBoss();
        a(summonLootBoss);
        summonLootBoss.itemCount = this.itemCount;
        summonLootBoss.bossHp = this.bossHp;
        summonLootBoss.damageToBase = this.damageToBase;
        summonLootBoss.coinMultiplier = this.coinMultiplier;
        return summonLootBoss;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isAlwaysUseless(GameSystemProvider gameSystemProvider) {
        Array tilesByType = gameSystemProvider.map.getMap().getTilesByType(SpawnTile.class);
        for (int i = 0; i < tilesByType.size; i++) {
            if (((SpawnTile) tilesByType.get(i)).getAllowedEnemiesSet().contains(EnemyType.BOSS)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        Array tilesByType = gameSystemProvider.map.getMap().getTilesByType(SpawnTile.class);
        for (int i = 0; i < tilesByType.size; i++) {
            if (((SpawnTile) tilesByType.get(i)).getAllowedEnemiesSet().contains(EnemyType.BOSS)) {
                return null;
            }
        }
        return () -> {
            return Game.i.localeManager.i18n.get("gpmod_precondition_nowhere_to_spawn_boss");
        };
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        this.f2179b = gameSystemProvider;
        RandomXS128 modRandom = gameSystemProvider.gameplayMod.getModRandom(7784);
        int nextOverridableWaveNumber = gameSystemProvider.wave.getNextOverridableWaveNumber();
        if (nextOverridableWaveNumber != -1) {
            Wave generateBossWaveWithProcessor = gameSystemProvider.wave.generateBossWaveWithProcessor(BossType.values[modRandom.nextInt(BossType.values.length)], nextOverridableWaveNumber, gameSystemProvider.wave.getWaveDifficultyProvider().getDifficultWavesMultiplier(nextOverridableWaveNumber));
            this.c = generateBossWaveWithProcessor;
            for (int i = 0; i < generateBossWaveWithProcessor.enemyGroups.size; i++) {
                EnemyGroup enemyGroup = generateBossWaveWithProcessor.enemyGroups.get(i);
                enemyGroup.health *= this.bossHp;
                enemyGroup.bounty *= this.coinMultiplier;
            }
            gameSystemProvider.wave.overrideWave(nextOverridableWaveNumber, generateBossWaveWithProcessor);
            gameSystemProvider.events.getListeners(WaveComplete.class).addStateAffecting(this.e);
            gameSystemProvider.events.getListeners(EnemySpawn.class).addStateAffecting(this.d).setDescription("Fills enemies with loot and configures them");
        } else {
            f2178a.e("skipped - no wave to override", new Object[0]);
        }
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(SummonLootBoss.class, str);
        if (activeModFromSource == null) {
            return true;
        }
        activeModFromSource.getMod().setRegisteredPower(this.power);
        return false;
    }

    public final void onWaveCompleted(Wave wave) {
        if (wave == this.c) {
            this.f2179b.events.getListeners(EnemySpawn.class).remove(this.d);
            this.f2179b.events.getListeners(WaveComplete.class).remove(this.e);
        }
    }

    public final void onEnemySpawn(EnemySpawn enemySpawn) {
        Enemy enemy = enemySpawn.getEnemy();
        if (enemy.wave == this.c) {
            if (!this.damageToBase) {
                NoDamageBuff noDamageBuff = new NoDamageBuff();
                noDamageBuff.setup(3600.0f, 3600.0f);
                this.f2179b.buff.P_NO_DAMAGE.addBuff(enemy, noDamageBuff);
            }
            if (!this.f && enemy.isBossMainBodyPart()) {
                f2178a.i("filling with loot " + enemy, new Object[0]);
                for (int i = 0; i < this.itemCount; i++) {
                    this.f2179b.loot.forceFillWithLoot(enemy);
                }
            }
        }
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final SummonLootBoss applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.itemCount = jsonValue.getInt("itemCount", this.itemCount);
        this.bossHp = jsonValue.getFloat("bossHp", this.bossHp);
        this.damageToBase = jsonValue.getBoolean("damageToBase", this.damageToBase);
        this.coinMultiplier = jsonValue.getFloat("coinMultiplier", this.coinMultiplier);
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/SummonLootBoss$OnWaveComplete.class */
    public static final class OnWaveComplete extends SerializableListener<SummonLootBoss> implements Listener<WaveComplete> {
        /* synthetic */ OnWaveComplete(SummonLootBoss summonLootBoss, byte b2) {
            this(summonLootBoss);
        }

        private OnWaveComplete() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnWaveComplete(SummonLootBoss summonLootBoss) {
            this.f1759a = summonLootBoss;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(WaveComplete waveComplete) {
            ((SummonLootBoss) this.f1759a).onWaveCompleted(waveComplete.getWave());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/SummonLootBoss$OnEnemySpawn.class */
    public static final class OnEnemySpawn implements KryoSerializable, Listener<EnemySpawn> {

        /* renamed from: a, reason: collision with root package name */
        private SummonLootBoss f2181a;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f2181a);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f2181a = (SummonLootBoss) kryo.readObject(input, SummonLootBoss.class);
        }

        @Deprecated
        private OnEnemySpawn() {
        }

        public OnEnemySpawn(SummonLootBoss summonLootBoss) {
            this.f2181a = summonLootBoss;
        }

        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemySpawn enemySpawn) {
            this.f2181a.onEnemySpawn(enemySpawn);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/SummonLootBoss$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2180a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2180a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2180a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("SummonLootBoss");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new SummonLootBoss().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(0.7f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
