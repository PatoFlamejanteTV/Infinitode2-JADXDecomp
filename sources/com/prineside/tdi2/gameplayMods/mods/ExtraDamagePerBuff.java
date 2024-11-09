package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.GiveDamageToEnemy;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayMod;
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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/ExtraDamagePerBuff.class */
public final class ExtraDamagePerBuff extends GenericGameplayMod implements Listener<GiveDamageToEnemy> {

    /* renamed from: a, reason: collision with root package name */
    private float f2122a = 5.0f;

    /* renamed from: b, reason: collision with root package name */
    private float f2123b = 0.0f;

    public ExtraDamagePerBuff() {
        this.maxPower = 5;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2122a);
        output.writeFloat(this.f2123b);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2122a = input.readFloat();
        this.f2123b = input.readFloat();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OFFENSIVE;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.ExtraDamagePerBuff");
    }

    public final float getDamage() {
        return this.f2123b + (this.f2122a * this.power);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_extra_damage_per_buff", StringFormatter.compactNumberWithPrecisionTrimZeros(getDamage(), 1, true));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayMod cpy() {
        ExtraDamagePerBuff extraDamagePerBuff = new ExtraDamagePerBuff();
        a(extraDamagePerBuff);
        extraDamagePerBuff.f2122a = this.f2122a;
        return extraDamagePerBuff;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(ExtraDamagePerBuff.class, str);
        if (activeModFromSource == null) {
            gameSystemProvider.events.getListeners(GiveDamageToEnemy.class).addWithFlags(this, 3);
            return true;
        }
        activeModFromSource.getMod().setRegisteredPower(this.power);
        return false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final ExtraDamagePerBuff applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2122a = jsonValue.getFloat("damagePerPwr", this.f2122a);
        this.f2123b = jsonValue.getFloat("baseDamage", this.f2123b);
        return this;
    }

    @Override // com.prineside.tdi2.events.Listener
    public final void handleEvent(GiveDamageToEnemy giveDamageToEnemy) {
        DamageRecord record = giveDamageToEnemy.getRecord();
        Enemy enemy = record.getEnemy();
        if (enemy.buffsByType != null) {
            int i = 0;
            for (BuffType buffType : BuffType.values) {
                if (enemy.buffsByType[buffType.ordinal()].size != 0) {
                    i++;
                }
            }
            if (i != 0) {
                record.setDamage(record.getDamage() + (i * getDamage() * 0.01f * record.getDamage()));
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/ExtraDamagePerBuff$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2124a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2124a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2124a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("ExtraDamagePerBuff");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new ExtraDamagePerBuff().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(1.0f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
