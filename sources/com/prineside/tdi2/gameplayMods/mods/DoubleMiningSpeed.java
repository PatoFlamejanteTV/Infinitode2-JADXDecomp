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
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/DoubleMiningSpeed.class */
public final class DoubleMiningSpeed extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private int f2115a = 300;

    /* renamed from: b, reason: collision with root package name */
    private float f2116b;

    public DoubleMiningSpeed() {
        this.maxPower = 3;
        this.multipleInstances = false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2116b);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2116b = input.readFloat();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isImmediateAndNotListed() {
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.LOOTING;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.DoubleMiningSpeed");
    }

    public final int getDuration() {
        return this.f2115a;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_double_mining_speed", StringFormatter.timePassed(getDuration(), false, false));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final DoubleMiningSpeed cpy() {
        DoubleMiningSpeed doubleMiningSpeed = new DoubleMiningSpeed();
        a(doubleMiningSpeed);
        doubleMiningSpeed.f2115a = this.f2115a;
        doubleMiningSpeed.f2116b = this.f2116b;
        return doubleMiningSpeed;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isAlwaysUseless(GameSystemProvider gameSystemProvider) {
        return gameSystemProvider.map.getMap().getTilesByType(SourceTile.class).size == 0;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        if (gameSystemProvider.miner.miners.size == 0) {
            return () -> {
                return Game.i.localeManager.i18n.get("gpmod_precondition_no_miners_on_map");
            };
        }
        if (gameSystemProvider.miner.bonusDoubleMiningSpeedTimeLeft >= this.f2116b) {
            String stringBuilder = StringFormatter.digestTime(MathUtils.round(this.f2116b)).toString();
            return () -> {
                return Game.i.localeManager.i18n.format("gpmod_precondition_double_mining_speed_still_active", stringBuilder);
            };
        }
        return null;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        gameSystemProvider.miner.bonusDoubleMiningSpeedTimeLeft += getDuration();
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(DoubleMiningSpeed.class, str);
        if (activeModFromSource != null) {
            activeModFromSource.getMod().setRegisteredPower(this.power);
            return false;
        }
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final DoubleMiningSpeed applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2115a = jsonValue.getInt("duration", this.f2115a);
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/DoubleMiningSpeed$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2117a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2117a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2117a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("DoubleMiningSpeed");
            DoubleMiningSpeed applyConfig = new DoubleMiningSpeed().applyConfig(bonusConfig);
            applyConfig.f2116b = bonusConfig.getFloat("zeroProbabilityOnDuration", 300.0f);
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(applyConfig, i, array, new ProbableBonusesProvider.BonusProviderConfig(0.5f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
