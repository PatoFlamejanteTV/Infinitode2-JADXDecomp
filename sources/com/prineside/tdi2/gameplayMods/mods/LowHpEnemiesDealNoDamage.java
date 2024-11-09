package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.buffs.NoDamageBuff;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyReachTarget;
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
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/LowHpEnemiesDealNoDamage.class */
public final class LowHpEnemiesDealNoDamage extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2146a = TLog.forClass(LowHpEnemiesDealNoDamage.class);
    public float baseHp;
    public float hpPerPower;

    /* renamed from: b, reason: collision with root package name */
    private OnEnemyReachTarget f2147b;

    @Null
    private GameSystemProvider c;

    public LowHpEnemiesDealNoDamage() {
        this.maxPower = 5;
        this.multipleInstances = false;
        this.baseHp = 0.0f;
        this.hpPerPower = 5.0f;
        this.f2147b = new OnEnemyReachTarget(this, (byte) 0);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.baseHp);
        output.writeFloat(this.hpPerPower);
        kryo.writeObjectOrNull(output, this.f2147b, OnEnemyReachTarget.class);
        kryo.writeObjectOrNull(output, this.c, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.baseHp = input.readFloat();
        this.hpPerPower = input.readFloat();
        this.f2147b = (OnEnemyReachTarget) kryo.readObjectOrNull(input, OnEnemyReachTarget.class);
        this.c = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
    }

    public final float getHpThreshold() {
        return this.baseHp + (this.hpPerPower * this.power);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.LowHpEnemiesDealNoDamage");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_low_hp_enemies_deal_no_damage", StringFormatter.compactNumberWithPrecisionTrimZeros(getHpThreshold(), 1, true));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final LowHpEnemiesDealNoDamage cpy() {
        LowHpEnemiesDealNoDamage lowHpEnemiesDealNoDamage = new LowHpEnemiesDealNoDamage();
        a((GenericGameplayMod) lowHpEnemiesDealNoDamage);
        lowHpEnemiesDealNoDamage.hpPerPower = this.hpPerPower;
        lowHpEnemiesDealNoDamage.baseHp = this.baseHp;
        return lowHpEnemiesDealNoDamage;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(LowHpEnemiesDealNoDamage.class, str);
        if (activeModFromSource == null) {
            this.c = gameSystemProvider;
            gameSystemProvider.events.getListeners(EnemyReachTarget.class).addStateAffectingWithPriority(this.f2147b, 2000);
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
    public final LowHpEnemiesDealNoDamage applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.hpPerPower = jsonValue.getFloat("hpPerPower", this.hpPerPower);
        this.baseHp = jsonValue.getFloat("baseHp", this.baseHp);
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/LowHpEnemiesDealNoDamage$OnEnemyReachTarget.class */
    public static final class OnEnemyReachTarget implements KryoSerializable, Listener<EnemyReachTarget> {

        /* renamed from: a, reason: collision with root package name */
        private LowHpEnemiesDealNoDamage f2149a;

        /* synthetic */ OnEnemyReachTarget(LowHpEnemiesDealNoDamage lowHpEnemiesDealNoDamage, byte b2) {
            this(lowHpEnemiesDealNoDamage);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f2149a);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f2149a = (LowHpEnemiesDealNoDamage) kryo.readObject(input, LowHpEnemiesDealNoDamage.class);
        }

        private OnEnemyReachTarget() {
        }

        private OnEnemyReachTarget(LowHpEnemiesDealNoDamage lowHpEnemiesDealNoDamage) {
            this.f2149a = lowHpEnemiesDealNoDamage;
        }

        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyReachTarget enemyReachTarget) {
            LowHpEnemiesDealNoDamage.f2146a.i("EnemyReachTarget event", new Object[0]);
            if ((enemyReachTarget.getEnemy().getHealth() / enemyReachTarget.getEnemy().maxHealth) * 100.0f <= this.f2149a.getHpThreshold()) {
                LowHpEnemiesDealNoDamage.f2146a.i("enemy is low on hp, no damage to the base", new Object[0]);
                NoDamageBuff noDamageBuff = new NoDamageBuff();
                noDamageBuff.setup(3600.0f, 3600.0f);
                this.f2149a.c.buff.P_NO_DAMAGE.addBuff(enemyReachTarget.getEnemy(), noDamageBuff);
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/LowHpEnemiesDealNoDamage$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2148a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2148a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2148a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("LowHpEnemiesDealNoDamage");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new LowHpEnemiesDealNoDamage().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(1.0f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
