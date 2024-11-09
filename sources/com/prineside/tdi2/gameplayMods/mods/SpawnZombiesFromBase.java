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
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemySpawn;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.units.DisorientedUnit;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/SpawnZombiesFromBase.class */
public final class SpawnZombiesFromBase extends GenericGameplayMod implements Listener<EnemySpawn> {
    public float baseCount;
    public float countPerPower;
    public float hp;
    public float hpPerPower;

    /* renamed from: a, reason: collision with root package name */
    @Null
    private GameSystemProvider f2176a;

    public SpawnZombiesFromBase() {
        this.maxPower = 3;
        this.multipleInstances = false;
        this.baseCount = 2.0f;
        this.countPerPower = 0.5f;
        this.hp = 30.0f;
        this.hpPerPower = 10.0f;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.baseCount);
        output.writeFloat(this.countPerPower);
        output.writeFloat(this.hp);
        output.writeFloat(this.hpPerPower);
        kryo.writeObjectOrNull(output, this.f2176a, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.baseCount = input.readFloat();
        this.countPerPower = input.readFloat();
        this.hp = input.readFloat();
        this.hpPerPower = input.readFloat();
        this.f2176a = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OFFENSIVE;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.SpawnZombiesFromBase");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_spawn_zombies_from_base", Integer.valueOf(getCountPerWave()), StringFormatter.compactNumberWithPrecisionTrimZeros(getHpMultiplier() * 100.0f, 1, true).toString());
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final SpawnZombiesFromBase cpy() {
        SpawnZombiesFromBase spawnZombiesFromBase = new SpawnZombiesFromBase();
        a(spawnZombiesFromBase);
        spawnZombiesFromBase.baseCount = this.baseCount;
        spawnZombiesFromBase.countPerPower = this.countPerPower;
        spawnZombiesFromBase.hp = this.hp;
        spawnZombiesFromBase.hpPerPower = this.hpPerPower;
        return spawnZombiesFromBase;
    }

    public final int getCountPerWave() {
        return MathUtils.round(this.baseCount + (this.countPerPower * this.power));
    }

    public final float getHpMultiplier() {
        return (this.hp + (this.hpPerPower * this.power)) * 0.01f;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(SpawnZombiesFromBase.class, str);
        if (activeModFromSource == null) {
            this.f2176a = gameSystemProvider;
            gameSystemProvider.events.getListeners(EnemySpawn.class).addStateAffecting(this).setDescription("Spawns the disoriented enemies");
            return true;
        }
        activeModFromSource.getMod().setRegisteredPower(this.power);
        return false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final SpawnZombiesFromBase applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.baseCount = jsonValue.getFloat("baseCount", this.baseCount);
        this.countPerPower = jsonValue.getFloat("countPerPower", this.countPerPower);
        this.hp = jsonValue.getFloat("hp", this.hp);
        this.hpPerPower = jsonValue.getFloat("hpPerPower", this.hpPerPower);
        return this;
    }

    @Override // com.prineside.tdi2.events.Listener
    public final void handleEvent(EnemySpawn enemySpawn) {
        Enemy enemy = enemySpawn.getEnemy();
        if (!enemy.notAffectsWaveKillCounter.isTrue() && enemy.wave != null && !enemy.isBossRelated() && enemy.wave.getSpawnedEnemyCount() <= getCountPerWave()) {
            DisorientedUnit create = Game.i.unitManager.F.DISORIENTED.create();
            create.setup(null, enemy.type, enemy.maxHealth * getHpMultiplier(), enemy.maxHealth);
            if (this.f2176a.unit.preparePathToRandomSpawn(create, this.f2176a.map.getMap().getTargetTileOrThrow())) {
                create.position.set(this.f2176a.map.getMap().getTargetTileOrThrow().center);
                this.f2176a.unit.register(create);
                this.f2176a.map.spawnUnit(create);
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/SpawnZombiesFromBase$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2177a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2177a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2177a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("SpawnZombiesFromBase");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new SpawnZombiesFromBase().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(0.5f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
