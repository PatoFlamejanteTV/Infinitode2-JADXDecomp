package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Tower;
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
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/AllAbilitiesForRandomTower.class */
public final class AllAbilitiesForRandomTower extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2097a = TLog.forClass(AllAbilitiesForRandomTower.class);

    /* renamed from: b, reason: collision with root package name */
    private int f2098b;

    public AllAbilitiesForRandomTower() {
        this.maxPower = 3;
        this.multipleInstances = false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.f2098b, true);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2098b = input.readVarInt(true);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OFFENSIVE;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.AllAbilitiesForRandomTower");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_all_abilities_for_random_tower", new Object[0]);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isImmediateAndNotListed() {
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final AllAbilitiesForRandomTower cpy() {
        AllAbilitiesForRandomTower allAbilitiesForRandomTower = new AllAbilitiesForRandomTower();
        a(allAbilitiesForRandomTower);
        allAbilitiesForRandomTower.f2098b = this.f2098b;
        return allAbilitiesForRandomTower;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        int i = getSuitableTowers(gameSystemProvider).size;
        if (i >= this.f2098b) {
            return null;
        }
        int i2 = this.f2098b;
        return () -> {
            return Game.i.localeManager.i18n.format("gpmod_precondition_all_abilities_for_random_tower", Integer.valueOf(i2), Integer.valueOf(i));
        };
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        Array<Tower> suitableTowers = getSuitableTowers(gameSystemProvider);
        if (suitableTowers.size == 0) {
            f2097a.e("no suitable towers found, can't register", new Object[0]);
            return false;
        }
        Tower tower = suitableTowers.get(gameSystemProvider.gameplayMod.getModRandom(3805).nextInt(suitableTowers.size));
        gameSystemProvider.tower.setAbilityInstalled(tower, 0, true);
        gameSystemProvider.tower.setAbilityInstalled(tower, 1, true);
        gameSystemProvider.tower.setAbilityInstalled(tower, 2, true);
        gameSystemProvider.tower.setAbilityInstalled(tower, 4, true);
        gameSystemProvider.tower.setAbilityInstalled(tower, 5, true);
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(AllAbilitiesForRandomTower.class, str);
        if (activeModFromSource != null) {
            activeModFromSource.getMod().setRegisteredPower(this.power);
            return false;
        }
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final AllAbilitiesForRandomTower applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        return this;
    }

    public static Array<Tower> getSuitableTowers(GameSystemProvider gameSystemProvider) {
        Array<Tower> array = new Array<>(true, 1, Tower.class);
        for (int i = 0; i < gameSystemProvider.tower.towers.size; i++) {
            Tower tower = gameSystemProvider.tower.towers.get(i);
            if (!tower.isAbilityInstalled(0) || !tower.isAbilityInstalled(1) || !tower.isAbilityInstalled(2) || !tower.isAbilityInstalled(4) || !tower.isAbilityInstalled(5)) {
                array.add(tower);
            }
        }
        return array;
    }

    public static Array<Tower> getAlreadyActiveTowers(GameSystemProvider gameSystemProvider) {
        Array<Tower> array = new Array<>(true, 1, Tower.class);
        for (int i = 0; i < gameSystemProvider.tower.towers.size; i++) {
            Tower tower = gameSystemProvider.tower.towers.get(i);
            if (tower.isAbilityInstalled(0) && tower.isAbilityInstalled(1) && tower.isAbilityInstalled(2) && tower.isAbilityInstalled(4) && tower.isAbilityInstalled(5)) {
                array.add(tower);
            }
        }
        return array;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/AllAbilitiesForRandomTower$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2099a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2099a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2099a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("AllAbilitiesForRandomTower");
            AllAbilitiesForRandomTower applyConfig = new AllAbilitiesForRandomTower().applyConfig(bonusConfig);
            applyConfig.f2098b = bonusConfig.getInt("minSuitableTowersOnMap", 2);
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(applyConfig, i, array, new ProbableBonusesProvider.BonusProviderConfig(0.7f).setPowerUpProbabilityMultiplier(0.9f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
