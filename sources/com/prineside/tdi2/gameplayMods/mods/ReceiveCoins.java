package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.math.MathUtils;
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
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/ReceiveCoins.class */
public final class ReceiveCoins extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private int f2167a = 100;

    /* renamed from: b, reason: collision with root package name */
    private float f2168b;

    public ReceiveCoins() {
        this.maxPower = 5;
        this.multipleInstances = false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeInt(this.f2167a);
        output.writeFloat(this.f2168b);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2167a = input.readInt();
        this.f2168b = input.readFloat();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isImmediateAndNotListed() {
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.ReceiveCoins");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_receive_coins", Integer.valueOf(this.f2167a));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ReceiveCoins cpy() {
        ReceiveCoins receiveCoins = new ReceiveCoins();
        a(receiveCoins);
        receiveCoins.f2167a = this.f2167a;
        receiveCoins.f2168b = this.f2168b;
        return receiveCoins;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final void configure(GameSystemProvider gameSystemProvider) {
        if (this.f2168b > 0.0f && gameSystemProvider.wave.wave != null) {
            this.f2167a = MathUtils.round(gameSystemProvider.wave.wave.enemiesSumBounty * this.f2168b);
        }
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        gameSystemProvider.gameState.addMoney(this.f2167a, true);
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(ReceiveCoins.class, str);
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
    public final ReceiveCoins applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2167a = jsonValue.getInt("amount", this.f2167a);
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/ReceiveCoins$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2169a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2169a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2169a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("ReceiveCoins");
            ReceiveCoins applyConfig = new ReceiveCoins().applyConfig(bonusConfig);
            applyConfig.f2168b = bonusConfig.getFloat("waveCoinValue", 3.0f);
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(applyConfig, i, array, new ProbableBonusesProvider.BonusProviderConfig(1.0f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
