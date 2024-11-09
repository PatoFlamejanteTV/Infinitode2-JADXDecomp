package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/MultiplyMdps.class */
public final class MultiplyMdps extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private float f2162a = 2.0f;

    public MultiplyMdps() {
        this.maxPower = 3;
        this.multipleInstances = false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2162a);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2162a = input.readFloat();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isImmediateAndNotListed() {
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.MultiplyMdps");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_multiply_mdps", StringFormatter.compactNumberWithPrecisionTrimZeros(this.f2162a, 1, true).toString());
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final MultiplyMdps cpy() {
        MultiplyMdps multiplyMdps = new MultiplyMdps();
        a(multiplyMdps);
        multiplyMdps.f2162a = this.f2162a;
        return multiplyMdps;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isAlwaysUseless(GameSystemProvider gameSystemProvider) {
        for (AbilityType abilityType : gameSystemProvider.ability.abilitiesConfiguration.slots) {
            if (abilityType != null) {
                return false;
            }
        }
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        for (int i = 0; i < gameSystemProvider.ability.getAbilitySlotCount(); i++) {
            if (gameSystemProvider.ability.getAvailableAbilities(i) > 0) {
                return null;
            }
        }
        return () -> {
            return Game.i.localeManager.i18n.get("gpmod_precondition_multiply_mdps_no_abilities");
        };
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        gameSystemProvider.damage.setTowersMaxDps(gameSystemProvider.damage.getTowersMaxDps() * this.f2162a);
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(MultiplyMdps.class, str);
        if (activeModFromSource != null) {
            activeModFromSource.getMod().setRegisteredPower(this.power);
            return false;
        }
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OFFENSIVE;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final MultiplyMdps applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2162a = jsonValue.getFloat("mdpsMultiplier", this.f2162a);
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/MultiplyMdps$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2163a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2163a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2163a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("MultiplyMdps");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new MultiplyMdps().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(1.0f).setPowerUpProbabilityMultiplier(0.2f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
