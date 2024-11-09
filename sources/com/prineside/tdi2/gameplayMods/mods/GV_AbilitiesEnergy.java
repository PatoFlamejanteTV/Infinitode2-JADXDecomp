package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayMod;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.JsonHandler;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import net.bytebuddy.utility.JavaConstant;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/GV_AbilitiesEnergy.class */
public final class GV_AbilitiesEnergy extends AbstractGameValueMod {

    /* renamed from: a, reason: collision with root package name */
    private int f2127a;

    /* renamed from: b, reason: collision with root package name */
    private AbilityType f2128b;

    @Override // com.prineside.tdi2.gameplayMods.mods.AbstractGameValueMod, com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f2128b);
        output.writeVarInt(this.f2127a, true);
    }

    @Override // com.prineside.tdi2.gameplayMods.mods.AbstractGameValueMod, com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2128b = (AbilityType) kryo.readObject(input, AbilityType.class);
        this.f2127a = input.readVarInt(true);
    }

    private GV_AbilitiesEnergy() {
    }

    public GV_AbilitiesEnergy(AbilityType abilityType, int i, GameValueType gameValueType, float f, float f2) {
        super(gameValueType, f, f2, true, GameplayModCategory.OFFENSIVE, null);
        this.f2128b = abilityType;
        this.f2127a = i;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.prineside.tdi2.gameplayMods.GameplayMod
    public final String getId() {
        return getClass().getSimpleName() + JavaConstant.Dynamic.DEFAULT_NAME + this.f2128b.name();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayMod cpy() {
        GV_AbilitiesEnergy gV_AbilitiesEnergy = new GV_AbilitiesEnergy();
        a(gV_AbilitiesEnergy);
        gV_AbilitiesEnergy.f2127a = this.f2127a;
        gV_AbilitiesEnergy.f2128b = this.f2128b;
        return gV_AbilitiesEnergy;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final void configure(GameSystemProvider gameSystemProvider) {
        this.maxPower = ((gameSystemProvider.gameValue.getIntValue(this.gvType) + this.power) - 1) - this.f2127a;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isAlwaysUseless(GameSystemProvider gameSystemProvider) {
        return gameSystemProvider.ability.abilitiesConfiguration.getSlot(this.f2128b) == -1;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        if (gameSystemProvider.gameValue.getIntValue(this.gvType) > this.f2127a && gameSystemProvider.ability.abilitiesConfiguration.getSlot(this.f2128b) != -1) {
            return null;
        }
        return () -> {
            return Game.i.localeManager.i18n.format("gpmod_precondition_gv_abilities_energy", Integer.valueOf(this.f2127a));
        };
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/GV_AbilitiesEnergy$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2129a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2129a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2129a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("GV_AbilitiesEnergy");
            ProbableBonusesProvider.BonusProviderConfig applyConfig = new ProbableBonusesProvider.BonusProviderConfig(0.2f).applyConfig(bonusConfig);
            int i2 = bonusConfig.getInt("minAbilityEnergy", 0);
            JsonValue orEmptyObject = JsonHandler.orEmptyObject(bonusConfig.get("abilities"));
            for (AbilityType abilityType : AbilityType.values) {
                JsonValue orEmptyObject2 = JsonHandler.orEmptyObject(orEmptyObject.get(abilityType.name()));
                ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new GV_AbilitiesEnergy(abilityType, orEmptyObject2.getInt("minAbilityEnergy", i2), Game.i.abilityManager.getEnergyCostGameValueType(abilityType), 0.0f, -1.0f).applyConfig(bonusConfig).applyConfig(orEmptyObject2), i, array, applyConfig.cpy().applyConfig(orEmptyObject2));
                if (addOrModify != null) {
                    array2.add(addOrModify);
                }
            }
        }
    }
}
