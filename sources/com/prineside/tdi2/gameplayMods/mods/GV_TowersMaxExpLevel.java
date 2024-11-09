package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayMod;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/GV_TowersMaxExpLevel.class */
public final class GV_TowersMaxExpLevel extends AbstractGameValueMod {
    public GV_TowersMaxExpLevel() {
        super(GameValueType.TOWERS_MAX_EXP_LEVEL, 0.0f, 10.0f, true, GameplayModCategory.OFFENSIVE, null);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayMod cpy() {
        GV_TowersMaxExpLevel gV_TowersMaxExpLevel = new GV_TowersMaxExpLevel();
        a(gV_TowersMaxExpLevel);
        return gV_TowersMaxExpLevel;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/GV_TowersMaxExpLevel$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2134a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2134a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2134a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("GV_TowersMaxExpLevel");
            GV_TowersMaxExpLevel gV_TowersMaxExpLevel = new GV_TowersMaxExpLevel();
            gV_TowersMaxExpLevel.maxPower = 3;
            gV_TowersMaxExpLevel.applyConfig(bonusConfig);
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(gV_TowersMaxExpLevel, i, array, new ProbableBonusesProvider.BonusProviderConfig(0.5f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
