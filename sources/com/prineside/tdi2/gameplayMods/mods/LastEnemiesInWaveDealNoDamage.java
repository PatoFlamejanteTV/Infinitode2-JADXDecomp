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
import com.prineside.tdi2.buffs.NoDamageBuff;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemySpawn;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/LastEnemiesInWaveDealNoDamage.class */
public final class LastEnemiesInWaveDealNoDamage extends GenericGameplayMod implements Listener<EnemySpawn> {
    public int baseEnemyCount;
    public int enemyCountPerPower;

    /* renamed from: a, reason: collision with root package name */
    @Null
    private GameSystemProvider f2141a;

    public LastEnemiesInWaveDealNoDamage() {
        this.maxPower = 3;
        this.multipleInstances = false;
        this.baseEnemyCount = 0;
        this.enemyCountPerPower = 1;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeInt(this.baseEnemyCount);
        output.writeInt(this.enemyCountPerPower);
        kryo.writeObjectOrNull(output, this.f2141a, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.baseEnemyCount = input.readInt();
        this.enemyCountPerPower = input.readInt();
        this.f2141a = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
    }

    public final int getEnemyCount() {
        return MathUtils.round(this.baseEnemyCount + (this.enemyCountPerPower * this.power));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.LastEnemiesInWaveDealNoDamage");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_last_enemies_in_wave_deal_no_damage", Integer.valueOf(getEnemyCount()));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final LastEnemiesInWaveDealNoDamage cpy() {
        LastEnemiesInWaveDealNoDamage lastEnemiesInWaveDealNoDamage = new LastEnemiesInWaveDealNoDamage();
        a(lastEnemiesInWaveDealNoDamage);
        lastEnemiesInWaveDealNoDamage.baseEnemyCount = this.baseEnemyCount;
        lastEnemiesInWaveDealNoDamage.enemyCountPerPower = this.enemyCountPerPower;
        return lastEnemiesInWaveDealNoDamage;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(LastEnemiesInWaveDealNoDamage.class, str);
        if (activeModFromSource == null) {
            this.f2141a = gameSystemProvider;
            gameSystemProvider.events.getListeners(EnemySpawn.class).addStateAffecting(this).setDescription("Applies the mod");
            return true;
        }
        activeModFromSource.getMod().setRegisteredPower(this.power);
        return false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.DEFENSIVE;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final LastEnemiesInWaveDealNoDamage applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.baseEnemyCount = jsonValue.getInt("baseEnemyCount", this.baseEnemyCount);
        this.enemyCountPerPower = jsonValue.getInt("enemyCountPerPower", this.enemyCountPerPower);
        return this;
    }

    @Override // com.prineside.tdi2.events.Listener
    public final void handleEvent(EnemySpawn enemySpawn) {
        Enemy enemy = enemySpawn.getEnemy();
        if (!enemy.notAffectsWaveKillCounter.isTrue() && enemy.wave != null && enemy.wave.getSpawnedEnemyCount() > 1 && enemy.wave.totalEnemiesCount - enemy.wave.getSpawnedEnemyCount() < getEnemyCount()) {
            NoDamageBuff noDamageBuff = new NoDamageBuff();
            noDamageBuff.setup(3600.0f, 3600.0f);
            this.f2141a.buff.P_NO_DAMAGE.addBuff(enemy, noDamageBuff);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/LastEnemiesInWaveDealNoDamage$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2142a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2142a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2142a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("LastEnemiesInWaveDealNoDamage");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new LastEnemiesInWaveDealNoDamage().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(1.0f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
