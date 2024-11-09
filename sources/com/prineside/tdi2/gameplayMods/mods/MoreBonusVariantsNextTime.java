package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
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
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/MoreBonusVariantsNextTime.class */
public final class MoreBonusVariantsNextTime extends GenericGameplayMod {
    public MoreBonusVariantsNextTime() {
        this.maxPower = 1;
        this.multipleInstances = false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.MoreBonusVariantsNextTime");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_more_bonus_variants_next_time", new Object[0]);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final MoreBonusVariantsNextTime cpy() {
        MoreBonusVariantsNextTime moreBonusVariantsNextTime = new MoreBonusVariantsNextTime();
        a(moreBonusVariantsNextTime);
        return moreBonusVariantsNextTime;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        gameSystemProvider.bonus.additionalBonusToSelectFrom = true;
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OTHER;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final MoreBonusVariantsNextTime applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/MoreBonusVariantsNextTime$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2158a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2158a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2158a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            for (int i2 = 0; i2 < array.size; i2++) {
                if (array.get(i2).getMod() instanceof MoreBonusVariantsNextTime) {
                    return;
                }
            }
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("MoreBonusVariantsNextTime");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new MoreBonusVariantsNextTime().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(0.2f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
