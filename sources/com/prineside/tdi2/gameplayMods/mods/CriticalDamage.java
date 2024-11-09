package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.GiveDamageToEnemy;
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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/CriticalDamage.class */
public final class CriticalDamage extends GenericGameplayMod implements Listener<GiveDamageToEnemy> {
    public float baseChance;
    public float chancePerPower;
    public float baseDamage;
    public float damagePerPower;

    /* renamed from: a, reason: collision with root package name */
    @Null
    private GameSystemProvider f2108a;

    public CriticalDamage() {
        this.maxPower = 3;
        this.multipleInstances = false;
        this.baseChance = 5.0f;
        this.chancePerPower = 5.0f;
        this.baseDamage = 200.0f;
        this.damagePerPower = 0.0f;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.baseChance);
        output.writeFloat(this.chancePerPower);
        output.writeFloat(this.baseDamage);
        output.writeFloat(this.damagePerPower);
        kryo.writeObjectOrNull(output, this.f2108a, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.baseChance = input.readFloat();
        this.chancePerPower = input.readFloat();
        this.baseDamage = input.readFloat();
        this.damagePerPower = input.readFloat();
        this.f2108a = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OFFENSIVE;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.CriticalDamage");
    }

    public final float getChanceMultiplier() {
        return (this.baseChance + (this.chancePerPower * this.power)) * 0.01f;
    }

    public final float getDamageMultiplier() {
        return (this.baseDamage + (this.damagePerPower * this.power)) * 0.01f;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_critical_damage", StringFormatter.compactNumberWithPrecisionTrimZeros(getChanceMultiplier() * 100.0f, 1, true).toString(), StringFormatter.compactNumberWithPrecisionTrimZeros(getDamageMultiplier() * 100.0f, 1, true).toString());
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CriticalDamage cpy() {
        CriticalDamage criticalDamage = new CriticalDamage();
        a(criticalDamage);
        criticalDamage.baseChance = this.baseChance;
        criticalDamage.chancePerPower = this.chancePerPower;
        criticalDamage.baseDamage = this.baseDamage;
        criticalDamage.damagePerPower = this.damagePerPower;
        return criticalDamage;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(CriticalDamage.class, str);
        if (activeModFromSource == null) {
            this.f2108a = gameSystemProvider;
            gameSystemProvider.events.getListeners(GiveDamageToEnemy.class).addStateAffecting(this);
            return true;
        }
        activeModFromSource.getMod().setRegisteredPower(this.power);
        return false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final CriticalDamage applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.baseChance = jsonValue.getFloat("baseChance", this.baseChance);
        this.chancePerPower = jsonValue.getFloat("chancePerPower", this.chancePerPower);
        this.baseDamage = jsonValue.getFloat("baseDamage", this.baseDamage);
        this.damagePerPower = jsonValue.getFloat("damagePerPower", this.damagePerPower);
        return this;
    }

    @Override // com.prineside.tdi2.events.Listener
    public final void handleEvent(GiveDamageToEnemy giveDamageToEnemy) {
        DamageRecord record = giveDamageToEnemy.getRecord();
        if (!DamageType.Efficiency.isCritical(record.getEfficiency()) && this.f2108a.gameState.randomFloat() < getChanceMultiplier()) {
            record.setDamage(record.getDamage() * getDamageMultiplier());
            record.setEfficiency(record.getEfficiency() | 1);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/CriticalDamage$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2109a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2109a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2109a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("CriticalDamage");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new CriticalDamage().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(0.5f).setPowerUpProbabilityMultiplier(0.9f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
