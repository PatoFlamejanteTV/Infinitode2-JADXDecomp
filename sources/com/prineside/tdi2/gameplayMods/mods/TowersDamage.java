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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/TowersDamage.class */
public final class TowersDamage extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private float f2185a = 0.0f;

    /* renamed from: b, reason: collision with root package name */
    private float f2186b = 15.0f;
    private GameSystemProvider c;
    private GameValueSystem.GlobalTowerStatMutator d;

    public TowersDamage() {
        this.maxPower = 3;
        this.multipleInstances = true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2185a);
        output.writeFloat(this.f2186b);
        kryo.writeObjectOrNull(output, this.c, GameSystemProvider.class);
        kryo.writeObjectOrNull(output, this.d, GameValueSystem.GlobalTowerStatMutator.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2185a = input.readFloat();
        this.f2186b = input.readFloat();
        this.c = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        this.d = (GameValueSystem.GlobalTowerStatMutator) kryo.readObjectOrNull(input, GameValueSystem.GlobalTowerStatMutator.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.TowersDamage");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_towers_damage", StringFormatter.compactNumberWithPrecisionTrimZeros(this.f2185a + (this.f2186b * this.power), 1, true).toString());
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final TowersDamage cpy() {
        TowersDamage towersDamage = new TowersDamage();
        a(towersDamage);
        towersDamage.f2185a = this.f2185a;
        towersDamage.f2186b = this.f2186b;
        return towersDamage;
    }

    public final float getStatMultiplier() {
        return 1.0f + ((this.f2185a + (this.f2186b * this.power)) * 0.01f);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(TowersDamage.class, str);
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
    public final TowersDamage applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2185a = jsonValue.getFloat("baseValue", this.f2185a);
        this.f2186b = jsonValue.getFloat("valuePerPower", this.f2186b);
        return this;
    }

    private void a() {
        float statMultiplier = getStatMultiplier();
        if (this.d == null) {
            this.d = new GameValueSystem.GlobalTowerStatMutator("TowersDamageGMOD", statMultiplier);
            this.c.gameValue.addGlobalTowerStatMutator(TowerStatType.DAMAGE, this.d);
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
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/TowersDamage$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2187a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2187a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2187a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("TowersDamage");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new TowersDamage().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(0.5f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
