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
import com.prineside.tdi2.buffs.DeathExplosionBuff;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemySpawn;
import com.prineside.tdi2.explosions.GenericExplosion;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/FirstEnemiesInWaveExplode.class */
public final class FirstEnemiesInWaveExplode extends GenericGameplayMod implements Listener<EnemySpawn> {
    public int baseEnemyCount;
    public int enemyCountPerPower;
    public float baseDamage;
    public float damagePerPower;
    public float explosionRange;

    /* renamed from: a, reason: collision with root package name */
    @Null
    private GameSystemProvider f2125a;

    public FirstEnemiesInWaveExplode() {
        this.maxPower = 3;
        this.multipleInstances = false;
        this.baseEnemyCount = 0;
        this.enemyCountPerPower = 1;
        this.baseDamage = 20.0f;
        this.damagePerPower = 10.0f;
        this.explosionRange = 4.0f;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeInt(this.baseEnemyCount);
        output.writeInt(this.enemyCountPerPower);
        output.writeFloat(this.baseDamage);
        output.writeFloat(this.damagePerPower);
        output.writeFloat(this.explosionRange);
        kryo.writeObjectOrNull(output, this.f2125a, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.baseEnemyCount = input.readInt();
        this.enemyCountPerPower = input.readInt();
        this.baseDamage = input.readFloat();
        this.damagePerPower = input.readFloat();
        this.explosionRange = input.readFloat();
        this.f2125a = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OFFENSIVE;
    }

    public final int getEnemyCount() {
        return MathUtils.round(this.baseEnemyCount + (this.enemyCountPerPower * this.power));
    }

    public final float getDamage() {
        return this.baseDamage + (this.damagePerPower * this.power);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.FirstEnemiesInWaveExplode");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_first_enemies_in_wave_explode", Integer.valueOf(getEnemyCount()), StringFormatter.compactNumberWithPrecisionTrimZeros(getDamage(), 1, true).toString());
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final FirstEnemiesInWaveExplode cpy() {
        FirstEnemiesInWaveExplode firstEnemiesInWaveExplode = new FirstEnemiesInWaveExplode();
        a(firstEnemiesInWaveExplode);
        firstEnemiesInWaveExplode.baseEnemyCount = this.baseEnemyCount;
        firstEnemiesInWaveExplode.enemyCountPerPower = this.enemyCountPerPower;
        firstEnemiesInWaveExplode.baseDamage = this.baseDamage;
        firstEnemiesInWaveExplode.damagePerPower = this.damagePerPower;
        firstEnemiesInWaveExplode.explosionRange = this.explosionRange;
        return firstEnemiesInWaveExplode;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(FirstEnemiesInWaveExplode.class, str);
        if (activeModFromSource == null) {
            this.f2125a = gameSystemProvider;
            gameSystemProvider.events.getListeners(EnemySpawn.class).addStateAffecting(this).setDescription("Applies the mod");
            return true;
        }
        activeModFromSource.getMod().setRegisteredPower(this.power);
        return false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final FirstEnemiesInWaveExplode applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.baseEnemyCount = jsonValue.getInt("baseEnemyCount", this.baseEnemyCount);
        this.enemyCountPerPower = jsonValue.getInt("enemyCountPerPower", this.enemyCountPerPower);
        this.baseDamage = jsonValue.getFloat("baseDamage", this.baseDamage);
        this.damagePerPower = jsonValue.getFloat("damagePerPower", this.damagePerPower);
        this.explosionRange = jsonValue.getFloat("explosionRange", this.explosionRange);
        return this;
    }

    @Override // com.prineside.tdi2.events.Listener
    public final void handleEvent(EnemySpawn enemySpawn) {
        Enemy enemy = enemySpawn.getEnemy();
        if (!enemy.notAffectsWaveKillCounter.isTrue() && enemy.wave != null && enemy.wave.getSpawnedEnemyCount() <= getEnemyCount()) {
            GenericExplosion obtain = this.f2125a.explosion.F.GENERIC.obtain();
            obtain.setup(null, 0.0f, 0.0f, enemy.maxHealth * getDamage() * 0.01f, enemy.getSize() * 0.0078125f * this.explosionRange, 0, 0.0f, 0.0f, Game.i.enemyManager.getFactory(enemy.type).getColor(), null);
            this.f2125a.buff.P_DEATH_EXPLOSION.addBuff(enemy, new DeathExplosionBuff().setup(60.0f, 180.0f, obtain));
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/FirstEnemiesInWaveExplode$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2126a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2126a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2126a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("FirstEnemiesInWaveExplode");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new FirstEnemiesInWaveExplode().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(1.0f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
