package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ModifierType;
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
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/GV_BountiesNearby.class */
public final class GV_BountiesNearby extends AbstractGameValueMod {
    public GV_BountiesNearby() {
        super(GameValueType.MODIFIER_BOUNTY_NEIGHBORING, 0.0f, 1.0f, true, GameplayModCategory.ECONOMICS, null);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayMod cpy() {
        GV_BountiesNearby gV_BountiesNearby = new GV_BountiesNearby();
        a(gV_BountiesNearby);
        return gV_BountiesNearby;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isAlwaysUseless(GameSystemProvider gameSystemProvider) {
        return gameSystemProvider.modifier.getMaxModifiersCount(ModifierType.BOUNTY) == 0;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        if (gameSystemProvider.modifier.getMaxModifiersCount(ModifierType.BOUNTY) == 0 || gameSystemProvider.gameValue.getBooleanValue(GameValueType.MODIFIER_BOUNTY_NEIGHBORING)) {
            return () -> {
                String str = null;
                if (gameSystemProvider.modifier.getMaxModifiersCount(ModifierType.BOUNTY) == 0) {
                    str = Game.i.localeManager.i18n.get("gpmod_precondition_no_modifier");
                }
                if (gameSystemProvider.gameValue.getBooleanValue(GameValueType.MODIFIER_BOUNTY_NEIGHBORING)) {
                    if (str == null) {
                        str = "";
                    }
                    str = ((Object) str) + SequenceUtils.EOL + Game.i.localeManager.i18n.get("gpmod_precondition_effect_already_enabled");
                }
                return str;
            };
        }
        return null;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/GV_BountiesNearby$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2131a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2131a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2131a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("GV_BountiesNearby");
            GV_BountiesNearby gV_BountiesNearby = new GV_BountiesNearby();
            gV_BountiesNearby.multipleInstances = false;
            gV_BountiesNearby.maxPower = 1;
            gV_BountiesNearby.applyConfig(bonusConfig);
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(gV_BountiesNearby, i, array, new ProbableBonusesProvider.BonusProviderConfig(0.6f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
