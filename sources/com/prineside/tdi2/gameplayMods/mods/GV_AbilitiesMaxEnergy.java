package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayMod;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/GV_AbilitiesMaxEnergy.class */
public final class GV_AbilitiesMaxEnergy extends AbstractGameValueMod {
    public GV_AbilitiesMaxEnergy() {
        super(GameValueType.ABILITIES_MAX_ENERGY, 1.0f, 2.0f, true, GameplayModCategory.OFFENSIVE, null);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayMod cpy() {
        GV_AbilitiesMaxEnergy gV_AbilitiesMaxEnergy = new GV_AbilitiesMaxEnergy();
        a(gV_AbilitiesMaxEnergy);
        return gV_AbilitiesMaxEnergy;
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
        for (int i = 0; i < gameSystemProvider.ability.abilitiesConfiguration.slots.length; i++) {
            if (gameSystemProvider.ability.abilitiesConfiguration.slots[i] != null) {
                return null;
            }
        }
        return () -> {
            return Game.i.localeManager.i18n.get("gpmod_precondition_no_abilities_selected");
        };
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/GV_AbilitiesMaxEnergy$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2130a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2130a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2130a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("GV_AbilitiesMaxEnergy");
            GV_AbilitiesMaxEnergy gV_AbilitiesMaxEnergy = new GV_AbilitiesMaxEnergy();
            gV_AbilitiesMaxEnergy.maxPower = 3;
            gV_AbilitiesMaxEnergy.applyConfig(bonusConfig);
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(gV_AbilitiesMaxEnergy, i, array, new ProbableBonusesProvider.BonusProviderConfig(0.5f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
