package com.prineside.tdi2.gameplayMods;

import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/GameplayMod.class */
public interface GameplayMod {
    String getId();

    Drawable getIcon();

    CharSequence getDescription();

    GameplayMod cpy();

    void markPowerLevelUpgradedByOtherMod(int i);

    boolean isPowerLevelUpgradedByOtherMod(int i);

    void setReplacesUnsatisfiedMod(GameplayMod gameplayMod);

    @Null
    GameplayMod getReplacesUnsatisfiedMod();

    boolean register(GameSystemProvider gameSystemProvider, String str);

    GameplayModCategory getCategory();

    @Null
    default ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        return null;
    }

    default boolean isImmediateAndNotListed() {
        return false;
    }

    default boolean isAlwaysUseless(GameSystemProvider gameSystemProvider) {
        return false;
    }

    default boolean allowsMultipleInstancesFromDifferentSources() {
        return true;
    }

    default int getPower() {
        return 1;
    }

    default int getMaxPower() {
        return 1;
    }

    default void setRegisteredPower(int i) {
    }

    default void configure(GameSystemProvider gameSystemProvider) {
    }

    @Null
    default GameplayModCategory getAdditionalCategory() {
        return null;
    }
}
