package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayMod;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.BonusSystem;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/IncreaseSelectedBonusesPower.class */
public final class IncreaseSelectedBonusesPower extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private int f2135a = 1;

    /* renamed from: b, reason: collision with root package name */
    private int f2136b = 2;
    private boolean c = false;

    public IncreaseSelectedBonusesPower() {
        this.maxPower = 3;
        this.multipleInstances = false;
    }

    static /* synthetic */ boolean a(IncreaseSelectedBonusesPower increaseSelectedBonusesPower, boolean z) {
        increaseSelectedBonusesPower.c = true;
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.f2135a, true);
        output.writeVarInt(this.f2136b, true);
        output.writeBoolean(this.c);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2135a = input.readVarInt(true);
        this.f2136b = input.readVarInt(true);
        this.c = input.readBoolean();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isImmediateAndNotListed() {
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.IncreaseSelectedBonusesPower");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return this.c ? Game.i.localeManager.i18n.format("gmod_descr_increase_selected_bonuses_power", Integer.valueOf(this.f2135a)) : Game.i.localeManager.i18n.format("gmod_descr_increase_selected_bonuses_power_random", Integer.valueOf(this.f2136b), Integer.valueOf(this.f2135a));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayMod cpy() {
        IncreaseSelectedBonusesPower increaseSelectedBonusesPower = new IncreaseSelectedBonusesPower();
        a((GenericGameplayMod) increaseSelectedBonusesPower);
        increaseSelectedBonusesPower.f2135a = this.f2135a;
        increaseSelectedBonusesPower.f2136b = this.f2136b;
        increaseSelectedBonusesPower.c = this.c;
        return increaseSelectedBonusesPower;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final IncreaseSelectedBonusesPower applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2135a = jsonValue.getInt("bonusPower", this.f2135a);
        this.f2136b = jsonValue.getInt("maxBonusCount", this.f2136b);
        this.c = jsonValue.getBoolean("allActiveBonuses", this.c);
        return this;
    }

    public static Array<GameplayModSystem.ActiveMod> getSuitableMods(Array<GameplayModSystem.ActiveMod> array) {
        Array<GameplayModSystem.ActiveMod> array2 = new Array<>(true, 1, GameplayModSystem.ActiveMod.class);
        for (int i = 0; i < array.size; i++) {
            GameplayModSystem.ActiveMod activeMod = array.get(i);
            GameplayMod mod = activeMod.getMod();
            if (!(mod instanceof IncreaseSelectedBonusesPower) && activeMod.getSource().equals(BonusSystem.GAMEPLAY_MOD_SOURCE_NAME) && !activeMod.getMod().isImmediateAndNotListed() && mod.getPower() < mod.getMaxPower()) {
                array2.add(activeMod);
            }
        }
        return array2;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        RandomXS128 modRandom = gameSystemProvider.gameplayMod.getModRandom(6523);
        Array<GameplayModSystem.ActiveMod> suitableMods = getSuitableMods(gameSystemProvider.gameplayMod.getActiveMods());
        if (this.c) {
            int i = suitableMods.size;
            for (int i2 = 0; i2 < i; i2++) {
                GameplayMod mod = suitableMods.items[i2].getMod();
                int min = Math.min(mod.getPower() + this.f2135a, mod.getMaxPower());
                if (mod.getPower() < min) {
                    mod.setRegisteredPower(min);
                    mod.markPowerLevelUpgradedByOtherMod(min);
                }
            }
        } else {
            for (int i3 = 0; i3 < suitableMods.size; i3++) {
                suitableMods.swap(i3, modRandom.nextInt(suitableMods.size));
            }
            int i4 = this.f2136b;
            int i5 = suitableMods.size;
            for (int i6 = 0; i6 < i5 && i4 != 0; i6++) {
                GameplayMod mod2 = suitableMods.items[i6].getMod();
                int min2 = Math.min(mod2.getPower() + this.f2135a, mod2.getMaxPower());
                if (mod2.getPower() < min2) {
                    mod2.setRegisteredPower(min2);
                    mod2.markPowerLevelUpgradedByOtherMod(min2);
                    i4--;
                }
            }
        }
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(IncreaseSelectedBonusesPower.class, str);
        if (activeModFromSource == null) {
            return true;
        }
        activeModFromSource.getMod().setRegisteredPower(this.power);
        return false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OTHER;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/IncreaseSelectedBonusesPower$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2137a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2137a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2137a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("IncreaseSelectedBonusesPower");
            ProbableBonusesProvider.BonusProviderConfig applyConfig = new ProbableBonusesProvider.BonusProviderConfig(1.0f).setMinStage(3).setProbability(0.5f).setProbabilityMultiplierPerStage(0.9f).applyConfig(bonusConfig);
            applyConfig.setMinStage(Math.min(2, applyConfig.minStage));
            IncreaseSelectedBonusesPower increaseSelectedBonusesPower = new IncreaseSelectedBonusesPower();
            increaseSelectedBonusesPower.applyConfig(bonusConfig);
            int i2 = IncreaseSelectedBonusesPower.getSuitableMods(array).size;
            increaseSelectedBonusesPower.f2136b += MathUtils.floor(((i - applyConfig.getMinStage()) * bonusConfig.getFloat("maxBonusCountPerStage", 0.2f)) + 0.01f);
            increaseSelectedBonusesPower.f2136b = Math.min(increaseSelectedBonusesPower.f2136b, i2);
            if (increaseSelectedBonusesPower.f2136b != 0) {
                if (increaseSelectedBonusesPower.f2136b == i2) {
                    IncreaseSelectedBonusesPower.a(increaseSelectedBonusesPower, true);
                }
                increaseSelectedBonusesPower.f2135a += MathUtils.floor(((i - applyConfig.getMinStage()) * bonusConfig.getFloat("bonusPowerPerStage", 0.0f)) + 0.01f);
                ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(increaseSelectedBonusesPower, i, array, applyConfig);
                if (addOrModify != null) {
                    array2.add(addOrModify);
                }
            }
        }
    }
}
