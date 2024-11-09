package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameValueSystem;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/TowersAttackSpeed.class */
public final class TowersAttackSpeed extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private float f2182a = 0.0f;

    /* renamed from: b, reason: collision with root package name */
    private float f2183b = 15.0f;
    private GameSystemProvider c;
    private GameValueSystem.GlobalTowerStatMutator d;

    public TowersAttackSpeed() {
        this.maxPower = 3;
        this.multipleInstances = true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2182a);
        output.writeFloat(this.f2183b);
        kryo.writeObjectOrNull(output, this.c, GameSystemProvider.class);
        kryo.writeObjectOrNull(output, this.d, GameValueSystem.GlobalTowerStatMutator.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2182a = input.readFloat();
        this.f2183b = input.readFloat();
        this.c = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        this.d = (GameValueSystem.GlobalTowerStatMutator) kryo.readObjectOrNull(input, GameValueSystem.GlobalTowerStatMutator.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.TowersAttackSpeed");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_towers_attack_speed", StringFormatter.compactNumberWithPrecisionTrimZeros(this.f2182a + (this.f2183b * this.power), 1, true).toString());
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final TowersAttackSpeed cpy() {
        TowersAttackSpeed towersAttackSpeed = new TowersAttackSpeed();
        a(towersAttackSpeed);
        towersAttackSpeed.f2182a = this.f2182a;
        towersAttackSpeed.f2183b = this.f2183b;
        return towersAttackSpeed;
    }

    public final float getStatMultiplier() {
        return 1.0f + ((this.f2182a + (this.f2183b * this.power)) * 0.01f);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(TowersAttackSpeed.class, str);
        if (activeModFromSource == null) {
            this.c = gameSystemProvider;
            a();
            return true;
        }
        activeModFromSource.getMod().setRegisteredPower(this.power);
        return false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OFFENSIVE;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final TowersAttackSpeed applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2182a = jsonValue.getFloat("baseValue", this.f2182a);
        this.f2183b = jsonValue.getFloat("valuePerPower", this.f2183b);
        return this;
    }

    private void a() {
        float statMultiplier = getStatMultiplier();
        if (this.d == null) {
            this.d = new GameValueSystem.GlobalTowerStatMutator("TowersAttackSpeedGMOD", statMultiplier);
            this.c.gameValue.addGlobalTowerStatMutator(TowerStatType.ATTACK_SPEED, this.d);
        } else {
            this.d.setMultiplier(statMultiplier);
            this.c.gameValue.recalculateGlobalTowerStatMultipliers();
        }
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.prineside.tdi2.gameplayMods.GameplayMod
    public final void setRegisteredPower(int i) {
        super.setRegisteredPower(i);
        a();
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/TowersAttackSpeed$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2184a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2184a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2184a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("TowersAttackSpeed");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new TowersAttackSpeed().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(0.5f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
