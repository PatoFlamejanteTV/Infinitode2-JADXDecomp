package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.MinerType;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayMod;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/GV_MinersMaxUpgradeLevel.class */
public final class GV_MinersMaxUpgradeLevel extends AbstractGameValueMod {
    public GV_MinersMaxUpgradeLevel() {
        super(GameValueType.MINERS_MAX_UPGRADE_LEVEL, 0.0f, 1.0f, true, GameplayModCategory.LOOTING, null);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayMod cpy() {
        GV_MinersMaxUpgradeLevel gV_MinersMaxUpgradeLevel = new GV_MinersMaxUpgradeLevel();
        a(gV_MinersMaxUpgradeLevel);
        return gV_MinersMaxUpgradeLevel;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final void configure(GameSystemProvider gameSystemProvider) {
        int i = 0;
        for (MinerType minerType : MinerType.values) {
            i = Math.max(i, gameSystemProvider.miner.getMaxUpgradeLevel(minerType));
        }
        this.maxPower = Math.min(this.maxPower, 10 - i);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isAlwaysUseless(GameSystemProvider gameSystemProvider) {
        return gameSystemProvider.map.getMap().getTilesByType(SourceTile.class).size == 0;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        int i = 0;
        boolean z = false;
        for (MinerType minerType : MinerType.values) {
            i = Math.max(i, gameSystemProvider.miner.getMaxUpgradeLevel(minerType));
            if (gameSystemProvider.miner.getMaxMinersCount(minerType) != 0) {
                z = true;
            }
        }
        if (i >= 10 || !z) {
            int i2 = i;
            boolean z2 = z;
            return () -> {
                String str = null;
                if (i2 >= 10) {
                    str = Game.i.localeManager.i18n.format("gpmod_precondition_miners_already_have_max_level", new Object[0]);
                }
                if (!z2) {
                    if (str == null) {
                        str = "";
                    }
                    str = str + Game.i.localeManager.i18n.format("gpmod_precondition_miners_not_available", new Object[0]);
                }
                return str;
            };
        }
        return null;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/GV_MinersMaxUpgradeLevel$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2133a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2133a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2133a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("GV_MinersMaxUpgradeLevel");
            GV_MinersMaxUpgradeLevel gV_MinersMaxUpgradeLevel = new GV_MinersMaxUpgradeLevel();
            gV_MinersMaxUpgradeLevel.multipleInstances = true;
            gV_MinersMaxUpgradeLevel.maxPower = 3;
            gV_MinersMaxUpgradeLevel.applyConfig(bonusConfig);
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(gV_MinersMaxUpgradeLevel, i, array, new ProbableBonusesProvider.BonusProviderConfig(0.5f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
