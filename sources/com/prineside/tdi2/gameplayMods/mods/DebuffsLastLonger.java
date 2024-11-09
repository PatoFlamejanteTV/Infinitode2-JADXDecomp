package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.AddBuffToEnemy;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/DebuffsLastLonger.class */
public final class DebuffsLastLonger extends GenericGameplayMod implements Listener<AddBuffToEnemy> {
    public float baseDuration;
    public float durationPerPower;

    /* renamed from: a, reason: collision with root package name */
    @Null
    private GameSystemProvider f2110a;

    public DebuffsLastLonger() {
        this.maxPower = 3;
        this.multipleInstances = false;
        this.baseDuration = 0.0f;
        this.durationPerPower = 0.2f;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.baseDuration);
        output.writeFloat(this.durationPerPower);
        kryo.writeObjectOrNull(output, this.f2110a, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.baseDuration = input.readFloat();
        this.durationPerPower = input.readFloat();
        this.f2110a = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OFFENSIVE;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.DebuffsLastLonger");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_debuffs_last_longer", StringFormatter.compactNumberWithPrecisionTrimZeros(this.durationPerPower * this.power * 100.0f, 1, true));
    }

    public final float getDurationMultiplier() {
        return 1.0f + this.baseDuration + (this.durationPerPower * this.power);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final DebuffsLastLonger cpy() {
        DebuffsLastLonger debuffsLastLonger = new DebuffsLastLonger();
        a(debuffsLastLonger);
        debuffsLastLonger.baseDuration = this.baseDuration;
        debuffsLastLonger.durationPerPower = this.durationPerPower;
        return debuffsLastLonger;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(DebuffsLastLonger.class, str);
        if (activeModFromSource == null) {
            this.f2110a = gameSystemProvider;
            gameSystemProvider.events.getListeners(AddBuffToEnemy.class).addStateAffectingWithPriority(this, 1000);
            return true;
        }
        activeModFromSource.getMod().setRegisteredPower(this.power);
        return false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final DebuffsLastLonger applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.durationPerPower = jsonValue.getFloat("durationPerPower", this.durationPerPower);
        this.baseDuration = jsonValue.getFloat("baseDuration", this.baseDuration);
        return this;
    }

    @Override // com.prineside.tdi2.events.Listener
    public final void handleEvent(AddBuffToEnemy addBuffToEnemy) {
        Buff buff = addBuffToEnemy.getBuff();
        if (this.f2110a.buff.getProcessor(buff.getType()).isDebuff()) {
            buff.duration = Math.min(buff.maxDuration, buff.duration * getDurationMultiplier());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/DebuffsLastLonger$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2111a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2111a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2111a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("DebuffsLastLonger");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new DebuffsLastLonger().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(1.0f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
