package com.prineside.tdi2.managers;

import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.modifiers.AttackSpeedModifier;
import com.prineside.tdi2.modifiers.BalanceModifier;
import com.prineside.tdi2.modifiers.BountyModifier;
import com.prineside.tdi2.modifiers.DamageModifier;
import com.prineside.tdi2.modifiers.ExperienceModifier;
import com.prineside.tdi2.modifiers.MiningSpeedModifier;
import com.prineside.tdi2.modifiers.PowerModifier;
import com.prineside.tdi2.modifiers.SearchModifier;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ModifierManager.class */
public class ModifierManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private final Modifier.Factory[] f2383a = new Modifier.Factory[ModifierType.values.length];

    /* renamed from: b, reason: collision with root package name */
    private final GameValueType[] f2384b = new GameValueType[ModifierType.values.length];
    private final String[] c = new String[ModifierType.values.length];
    private final String[] d = new String[ModifierType.values.length];
    private final String[] e = new String[ModifierType.values.length];
    public final Factories F = new Factories();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ModifierManager$Factories.class */
    public static class Factories {
        public BalanceModifier.BalanceModifierFactory BALANCE;
        public PowerModifier.PowerModifierFactory POWER;
        public SearchModifier.SearchModifierFactory SEARCH;
        public DamageModifier.DamageModifierFactory DAMAGE;
        public AttackSpeedModifier.AttackSpeedModifierFactory ATTACK_SPEED;
        public MiningSpeedModifier.MiningSpeedModifierFactory MINING_SPEED;
        public ExperienceModifier.ExperienceModifierFactory EXPERIENCE;
        public BountyModifier.BountyModifierFactory BOUNTY;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ModifierManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<ModifierManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public ModifierManager read() {
            return Game.i.modifierManager;
        }
    }

    public ModifierManager() {
        Modifier.Factory[] factoryArr = this.f2383a;
        int ordinal = ModifierType.BALANCE.ordinal();
        Factories factories = this.F;
        BalanceModifier.BalanceModifierFactory balanceModifierFactory = new BalanceModifier.BalanceModifierFactory();
        factories.BALANCE = balanceModifierFactory;
        factoryArr[ordinal] = balanceModifierFactory;
        Modifier.Factory[] factoryArr2 = this.f2383a;
        int ordinal2 = ModifierType.POWER.ordinal();
        Factories factories2 = this.F;
        PowerModifier.PowerModifierFactory powerModifierFactory = new PowerModifier.PowerModifierFactory();
        factories2.POWER = powerModifierFactory;
        factoryArr2[ordinal2] = powerModifierFactory;
        Modifier.Factory[] factoryArr3 = this.f2383a;
        int ordinal3 = ModifierType.SEARCH.ordinal();
        Factories factories3 = this.F;
        SearchModifier.SearchModifierFactory searchModifierFactory = new SearchModifier.SearchModifierFactory();
        factories3.SEARCH = searchModifierFactory;
        factoryArr3[ordinal3] = searchModifierFactory;
        Modifier.Factory[] factoryArr4 = this.f2383a;
        int ordinal4 = ModifierType.DAMAGE.ordinal();
        Factories factories4 = this.F;
        DamageModifier.DamageModifierFactory damageModifierFactory = new DamageModifier.DamageModifierFactory();
        factories4.DAMAGE = damageModifierFactory;
        factoryArr4[ordinal4] = damageModifierFactory;
        Modifier.Factory[] factoryArr5 = this.f2383a;
        int ordinal5 = ModifierType.ATTACK_SPEED.ordinal();
        Factories factories5 = this.F;
        AttackSpeedModifier.AttackSpeedModifierFactory attackSpeedModifierFactory = new AttackSpeedModifier.AttackSpeedModifierFactory();
        factories5.ATTACK_SPEED = attackSpeedModifierFactory;
        factoryArr5[ordinal5] = attackSpeedModifierFactory;
        Modifier.Factory[] factoryArr6 = this.f2383a;
        int ordinal6 = ModifierType.MINING_SPEED.ordinal();
        Factories factories6 = this.F;
        MiningSpeedModifier.MiningSpeedModifierFactory miningSpeedModifierFactory = new MiningSpeedModifier.MiningSpeedModifierFactory();
        factories6.MINING_SPEED = miningSpeedModifierFactory;
        factoryArr6[ordinal6] = miningSpeedModifierFactory;
        Modifier.Factory[] factoryArr7 = this.f2383a;
        int ordinal7 = ModifierType.EXPERIENCE.ordinal();
        Factories factories7 = this.F;
        ExperienceModifier.ExperienceModifierFactory experienceModifierFactory = new ExperienceModifier.ExperienceModifierFactory();
        factories7.EXPERIENCE = experienceModifierFactory;
        factoryArr7[ordinal7] = experienceModifierFactory;
        Modifier.Factory[] factoryArr8 = this.f2383a;
        int ordinal8 = ModifierType.BOUNTY.ordinal();
        Factories factories8 = this.F;
        BountyModifier.BountyModifierFactory bountyModifierFactory = new BountyModifier.BountyModifierFactory();
        factories8.BOUNTY = bountyModifierFactory;
        factoryArr8[ordinal8] = bountyModifierFactory;
        for (ModifierType modifierType : ModifierType.values) {
            if (this.f2383a[modifierType.ordinal()] == null) {
                throw new RuntimeException("Not all modifier factories were created");
            }
        }
        for (ModifierType modifierType2 : ModifierType.values) {
            this.c[modifierType2.ordinal()] = "modifier_name_" + modifierType2.name();
            this.d[modifierType2.ordinal()] = "modifier_name_fancy_" + modifierType2.name();
            this.e[modifierType2.ordinal()] = "modifier_description_" + modifierType2.name();
            this.f2384b[modifierType2.ordinal()] = GameValueType.valueOf("MODIFIER_" + modifierType2.name() + "_COUNT");
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        for (ModifierType modifierType : ModifierType.values) {
            getFactory(modifierType).setup();
        }
    }

    public Modifier.Factory<? extends Modifier> getFactory(ModifierType modifierType) {
        return this.f2383a[modifierType.ordinal()];
    }

    public String getTitleAlias(ModifierType modifierType) {
        return this.c[modifierType.ordinal()];
    }

    public String getTitleFancyAlias(ModifierType modifierType) {
        return this.d[modifierType.ordinal()];
    }

    public String getDescriptionAlias(ModifierType modifierType) {
        return this.e[modifierType.ordinal()];
    }

    public GameValueType getCountGameValue(ModifierType modifierType) {
        return this.f2384b[modifierType.ordinal()];
    }

    public Modifier fromJson(JsonValue jsonValue) {
        Modifier create = getFactory(ModifierType.valueOf(jsonValue.getString("type"))).create();
        create.loadFromJson(jsonValue);
        return create;
    }
}
