package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
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
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/SellAllTowers.class */
public final class SellAllTowers extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2173a = TLog.forClass(SellAllTowers.class);

    /* renamed from: b, reason: collision with root package name */
    private float f2174b = 1.15f;
    private int c;

    public SellAllTowers() {
        this.maxPower = 2;
        this.multipleInstances = false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2174b);
        output.writeInt(this.c);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2174b = input.readFloat();
        this.c = input.readInt();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isImmediateAndNotListed() {
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.SellAllTowers");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_sell_all_towers", StringFormatter.compactNumberWithPrecisionTrimZeros(this.f2174b * 100.0f, 1, true).toString(), StringFormatter.commaSeparatedNumber(this.c).toString());
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final SellAllTowers cpy() {
        SellAllTowers sellAllTowers = new SellAllTowers();
        a(sellAllTowers);
        sellAllTowers.f2174b = this.f2174b;
        sellAllTowers.c = this.c;
        return sellAllTowers;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    @Null
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        int i = 0;
        for (int i2 = 0; i2 < gameSystemProvider.tower.towers.size; i2++) {
            i += gameSystemProvider.tower.towers.get(i2).moneySpentOn;
        }
        if (i == 0) {
            return () -> {
                return Game.i.localeManager.i18n.get("gpmod_precondition_sell_all_towers");
            };
        }
        return null;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final void configure(GameSystemProvider gameSystemProvider) {
        int i = 0;
        for (int i2 = 0; i2 < gameSystemProvider.tower.towers.size; i2++) {
            i += gameSystemProvider.tower.towers.get(i2).moneySpentOn;
        }
        this.c = MathUtils.round(i * this.f2174b);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        int i = 0;
        for (int i2 = 0; i2 < gameSystemProvider.tower.towers.size; i2++) {
            i += gameSystemProvider.tower.towers.get(i2).getSellPrice();
        }
        if (i == 0) {
            f2173a.i("skipped - sumPrice is zero", new Object[0]);
            return false;
        }
        Array array = new Array(true, gameSystemProvider.tower.towers.size, Tower.class);
        array.addAll(gameSystemProvider.tower.towers);
        float f = 0.0f;
        for (int i3 = 0; i3 < array.size; i3++) {
            Tower tower = (Tower) array.get(i3);
            if (tower.moneySpentOn > 0) {
                f += tower.moneySpentOn;
                tower.moneySpentOn = 0;
                gameSystemProvider.tower.sellTower(tower);
            }
        }
        f2173a.i("add money " + f + " -> " + (f * this.f2174b) + " (+" + ((f * this.f2174b) - f) + " coins)", new Object[0]);
        gameSystemProvider.gameState.addMoney(f * this.f2174b, false);
        for (int i4 = 0; i4 < gameSystemProvider.gameplayMod.getActiveMods().size; i4++) {
            GameplayModSystem.ActiveMod activeMod = gameSystemProvider.gameplayMod.getActiveMods().get(i4);
            if (activeMod.getMod() instanceof LightningStrikeOnTowerLevelUp) {
                ((LightningStrikeOnTowerLevelUp) activeMod.getMod()).resetStrikeLevelLimits();
            }
        }
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(SellAllTowers.class, str);
        if (activeModFromSource != null) {
            activeModFromSource.getMod().setRegisteredPower(this.power);
            return false;
        }
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.ECONOMICS;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final SellAllTowers applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2174b = jsonValue.getFloat("coinMultiplier", this.f2174b);
        if (this.f2174b < 1.0f) {
            this.f2174b = 1.0f;
        }
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/SellAllTowers$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2175a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2175a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2175a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("SellAllTowers");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new SellAllTowers().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(0.5f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
