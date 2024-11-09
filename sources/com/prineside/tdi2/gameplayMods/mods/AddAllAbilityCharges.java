package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
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
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/AddAllAbilityCharges.class */
public final class AddAllAbilityCharges extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private int f2087a = 5;

    /* renamed from: b, reason: collision with root package name */
    private int f2088b = 1;

    public AddAllAbilityCharges() {
        this.maxPower = 3;
        this.multipleInstances = false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeInt(this.f2087a);
        output.writeInt(this.f2088b);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2087a = input.readInt();
        this.f2088b = input.readInt();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isImmediateAndNotListed() {
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.AddAllAbilityCharges");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_add_ability_charges", Integer.valueOf(this.f2088b), Integer.valueOf(this.f2087a));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final AddAllAbilityCharges cpy() {
        AddAllAbilityCharges addAllAbilityCharges = new AddAllAbilityCharges();
        a(addAllAbilityCharges);
        addAllAbilityCharges.f2087a = this.f2087a;
        addAllAbilityCharges.f2088b = this.f2088b;
        return addAllAbilityCharges;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isAlwaysUseless(GameSystemProvider gameSystemProvider) {
        for (int i = 0; i < gameSystemProvider.ability.getAbilitySlotCount(); i++) {
            if (gameSystemProvider.ability.abilitiesConfiguration.slots[i] != null) {
                return false;
            }
        }
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        for (int i = 0; i < gameSystemProvider.ability.getAbilitySlotCount(); i++) {
            if (gameSystemProvider.ability.abilitiesConfiguration.slots[i] != null && gameSystemProvider.ability.getAvailableAbilities(i) < this.f2087a) {
                return null;
            }
        }
        int i2 = this.f2087a;
        return () -> {
            return Game.i.localeManager.i18n.format("gpmod_precondition_add_all_ability_charges", Integer.valueOf(i2));
        };
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        for (int i = 0; i < gameSystemProvider.ability.getAbilitySlotCount(); i++) {
            if (gameSystemProvider.ability.abilitiesConfiguration.slots[i] != null && gameSystemProvider.ability.getAvailableAbilities(i) < this.f2087a) {
                gameSystemProvider.ability.addAbilityCharges(i, this.f2088b);
            }
        }
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(AddAllAbilityCharges.class, str);
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
    public final AddAllAbilityCharges applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2087a = jsonValue.getInt("maxCurrentCharges", this.f2087a);
        if (this.f2087a <= 0) {
            this.f2087a = 1;
        }
        this.f2088b = jsonValue.getInt("chargeCount", this.f2088b);
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/AddAllAbilityCharges$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2089a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2089a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2089a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("AddAllAbilityCharges");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new AddAllAbilityCharges().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(1.0f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
