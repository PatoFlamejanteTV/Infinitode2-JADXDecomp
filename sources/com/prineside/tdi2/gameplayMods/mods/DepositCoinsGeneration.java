package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueConfig;
import com.prineside.tdi2.enums.GameValueType;
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
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/DepositCoinsGeneration.class */
public final class DepositCoinsGeneration extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2112a = TLog.forClass(DepositCoinsGeneration.class);

    /* renamed from: b, reason: collision with root package name */
    private float f2113b = 1.0f;

    @Null
    private GameSystemProvider c;
    private GameValueConfig d;
    private float e;
    private float f;
    private boolean g;

    public DepositCoinsGeneration() {
        this.maxPower = 5;
    }

    static /* synthetic */ float c(DepositCoinsGeneration depositCoinsGeneration, float f) {
        float f2 = depositCoinsGeneration.e + f;
        depositCoinsGeneration.e = f2;
        return f2;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2113b);
        kryo.writeObjectOrNull(output, this.d, GameValueConfig.class);
        kryo.writeObjectOrNull(output, this.c, GameSystemProvider.class);
        output.writeFloat(this.e);
        output.writeFloat(this.f);
        output.writeBoolean(this.g);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2113b = input.readFloat();
        this.d = (GameValueConfig) kryo.readObjectOrNull(input, GameValueConfig.class);
        this.c = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        this.e = input.readFloat();
        this.f = input.readFloat();
        this.g = input.readBoolean();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.ECONOMICS;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.DepositCoinsGeneration");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_deposit_coins_generation", StringFormatter.compactNumberWithPrecisionTrimZeros(this.f2113b * this.power, 1, true));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayMod cpy() {
        DepositCoinsGeneration depositCoinsGeneration = new DepositCoinsGeneration();
        a(depositCoinsGeneration);
        depositCoinsGeneration.f2113b = this.f2113b;
        depositCoinsGeneration.e = this.e;
        depositCoinsGeneration.f = this.f;
        depositCoinsGeneration.g = this.g;
        return depositCoinsGeneration;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final void configure(GameSystemProvider gameSystemProvider) {
        float f;
        if (this.g) {
            f2112a.i("config on " + gameSystemProvider.statistics.getAverageCoinsPerMinute() + " x " + this.e, new Object[0]);
            f = gameSystemProvider.statistics.getAverageCoinsPerMinute() * this.e;
        } else {
            f = this.f;
        }
        this.f2113b = f / this.power;
        gameSystemProvider.syncLog("configure", "DepositCoinsGeneration", Float.valueOf(this.f2113b));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        this.c = gameSystemProvider;
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(DepositCoinsGeneration.class, str);
        if (activeModFromSource == null) {
            this.d = new GameValueConfig(GameValueType.COINS_GENERATION, this.f2113b * this.power, false, true);
            gameSystemProvider.gameValue.addCustomGameValue(this.d);
            return true;
        }
        DepositCoinsGeneration depositCoinsGeneration = (DepositCoinsGeneration) activeModFromSource.getMod();
        depositCoinsGeneration.f2113b = this.f2113b;
        depositCoinsGeneration.power = this.power;
        depositCoinsGeneration.a();
        return false;
    }

    private void a() {
        this.d.setValue(this.f2113b * this.power);
        this.c.gameValue.recalculate();
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final DepositCoinsGeneration applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2113b = jsonValue.getFloat("coinsPerMinute", this.f2113b);
        return this;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.prineside.tdi2.gameplayMods.GameplayMod
    public final void setRegisteredPower(int i) {
        super.setRegisteredPower(i);
        a();
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/DepositCoinsGeneration$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2114a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2114a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2114a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("DepositCoinsGeneration");
            ProbableBonusesProvider.BonusProviderConfig applyConfig = new ProbableBonusesProvider.BonusProviderConfig(1.0f).setPowerUpProbabilityMultiplier(0.9f).applyConfig(bonusConfig);
            boolean z = bonusConfig.getBoolean("dependsOnPlayerCpm", true);
            float f = 0.0f;
            if (!z) {
                f = bonusConfig.getFloat("baseBonusCpm", 10.0f) + (bonusConfig.getFloat("bonusCpmPerStage", 2.0f) * (i - 1));
            }
            DepositCoinsGeneration depositCoinsGeneration = new DepositCoinsGeneration();
            DepositCoinsGeneration depositCoinsGeneration2 = null;
            int i2 = 0;
            while (true) {
                if (i2 >= array.size) {
                    break;
                }
                GameplayModSystem.ActiveMod activeMod = array.items[i2];
                if (!activeMod.getMod().getId().equals(depositCoinsGeneration.getId()) || !activeMod.getSource().equals(BonusSystem.GAMEPLAY_MOD_SOURCE_NAME)) {
                    i2++;
                } else {
                    depositCoinsGeneration2 = (DepositCoinsGeneration) activeMod.getMod();
                    break;
                }
            }
            depositCoinsGeneration.e = bonusConfig.getFloat("playerCpmMultiplier", 0.03f) + (bonusConfig.getFloat("playerCpmMultiplierPerStage", 0.001f) * (i - 1));
            depositCoinsGeneration.f = f;
            depositCoinsGeneration.g = z;
            if (depositCoinsGeneration2 != null) {
                DepositCoinsGeneration.c(depositCoinsGeneration, bonusConfig.getFloat("playerCpmMultiplierPerLevel", 0.003f) * depositCoinsGeneration2.power);
            }
            depositCoinsGeneration.applyConfig(bonusConfig);
            depositCoinsGeneration.applyConfig(bonusConfig);
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(depositCoinsGeneration, i, array, applyConfig);
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
