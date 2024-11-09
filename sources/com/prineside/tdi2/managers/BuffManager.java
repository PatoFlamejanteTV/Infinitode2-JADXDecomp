package com.prineside.tdi2.managers;

import com.prineside.tdi2.Buff;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.buffs.ArmorBuff;
import com.prineside.tdi2.buffs.BlizzardBuff;
import com.prineside.tdi2.buffs.BonusCoinsBuff;
import com.prineside.tdi2.buffs.BonusXpBuff;
import com.prineside.tdi2.buffs.BurnBuff;
import com.prineside.tdi2.buffs.ChainReactionBuff;
import com.prineside.tdi2.buffs.DeathExplosionBuff;
import com.prineside.tdi2.buffs.FreezingBuff;
import com.prineside.tdi2.buffs.InvulnerabilityBuff;
import com.prineside.tdi2.buffs.NoBonusSystemPointsBuff;
import com.prineside.tdi2.buffs.NoDamageBuff;
import com.prineside.tdi2.buffs.PoisonBuff;
import com.prineside.tdi2.buffs.RegenerationBuff;
import com.prineside.tdi2.buffs.SlippingBuff;
import com.prineside.tdi2.buffs.SnowballBuff;
import com.prineside.tdi2.buffs.StunBuff;
import com.prineside.tdi2.buffs.ThrowBackBuff;
import com.prineside.tdi2.buffs.VulnerabilityBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/BuffManager.class */
public class BuffManager extends Manager.ManagerAdapter {
    public final Factories F = new Factories();

    /* renamed from: a, reason: collision with root package name */
    private final Buff.Factory[] f2313a = new Buff.Factory[BuffType.values.length];

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/BuffManager$Factories.class */
    public static class Factories {
        public FreezingBuff.FreezingBuffFactory FREEZING;
        public PoisonBuff.PoisonBuffFactory POISON;
        public BlizzardBuff.BlizzardBuffFactory BLIZZARD;
        public SnowballBuff.SnowballBuffFactory SNOWBALL;
        public ThrowBackBuff.BlastThrowBackBuffFactory THROW_BACK;
        public StunBuff.StunBuffFactory STUN;
        public BurnBuff.BurnBuffFactory BURN;
        public RegenerationBuff.RegenerationBuffFactory REGENERATION;
        public ArmorBuff.ArmorBuffFactory ARMOR;
        public BonusCoinsBuff.BonusCoinsBuffFactory BONUS_COINS;
        public BonusXpBuff.BonusXpBuffFactory BONUS_XP;
        public DeathExplosionBuff.ExplosionChargeBuffFactory DEATH_EXPLOSION;
        public ChainReactionBuff.ChainReactionBuffFactory CHAIN_REACTION;
        public VulnerabilityBuff.VulnerabilityBuffFactory VULNERABILITY;
        public InvulnerabilityBuff.InvulnerabilityBuffFactory INVULNERABILITY;
        public SlippingBuff.SlippingBuffFactory SLIPPING;
        public NoDamageBuff.NoDamageBuffFactory NO_DAMAGE;
        public NoBonusSystemPointsBuff.NoBonusSystemPointsBuffFactory NO_BONUS_SYSTEM_POINTS;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/BuffManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<BuffManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public BuffManager read() {
            return Game.i.buffManager;
        }
    }

    public BuffManager() {
        Buff.Factory[] factoryArr = this.f2313a;
        int ordinal = BuffType.FREEZING.ordinal();
        Factories factories = this.F;
        FreezingBuff.FreezingBuffFactory freezingBuffFactory = new FreezingBuff.FreezingBuffFactory();
        factories.FREEZING = freezingBuffFactory;
        factoryArr[ordinal] = freezingBuffFactory;
        Buff.Factory[] factoryArr2 = this.f2313a;
        int ordinal2 = BuffType.POISON.ordinal();
        Factories factories2 = this.F;
        PoisonBuff.PoisonBuffFactory poisonBuffFactory = new PoisonBuff.PoisonBuffFactory();
        factories2.POISON = poisonBuffFactory;
        factoryArr2[ordinal2] = poisonBuffFactory;
        Buff.Factory[] factoryArr3 = this.f2313a;
        int ordinal3 = BuffType.BLIZZARD.ordinal();
        Factories factories3 = this.F;
        BlizzardBuff.BlizzardBuffFactory blizzardBuffFactory = new BlizzardBuff.BlizzardBuffFactory();
        factories3.BLIZZARD = blizzardBuffFactory;
        factoryArr3[ordinal3] = blizzardBuffFactory;
        Buff.Factory[] factoryArr4 = this.f2313a;
        int ordinal4 = BuffType.SNOWBALL.ordinal();
        Factories factories4 = this.F;
        SnowballBuff.SnowballBuffFactory snowballBuffFactory = new SnowballBuff.SnowballBuffFactory();
        factories4.SNOWBALL = snowballBuffFactory;
        factoryArr4[ordinal4] = snowballBuffFactory;
        Buff.Factory[] factoryArr5 = this.f2313a;
        int ordinal5 = BuffType.THROW_BACK.ordinal();
        Factories factories5 = this.F;
        ThrowBackBuff.BlastThrowBackBuffFactory blastThrowBackBuffFactory = new ThrowBackBuff.BlastThrowBackBuffFactory();
        factories5.THROW_BACK = blastThrowBackBuffFactory;
        factoryArr5[ordinal5] = blastThrowBackBuffFactory;
        Buff.Factory[] factoryArr6 = this.f2313a;
        int ordinal6 = BuffType.STUN.ordinal();
        Factories factories6 = this.F;
        StunBuff.StunBuffFactory stunBuffFactory = new StunBuff.StunBuffFactory();
        factories6.STUN = stunBuffFactory;
        factoryArr6[ordinal6] = stunBuffFactory;
        Buff.Factory[] factoryArr7 = this.f2313a;
        int ordinal7 = BuffType.BURN.ordinal();
        Factories factories7 = this.F;
        BurnBuff.BurnBuffFactory burnBuffFactory = new BurnBuff.BurnBuffFactory();
        factories7.BURN = burnBuffFactory;
        factoryArr7[ordinal7] = burnBuffFactory;
        Buff.Factory[] factoryArr8 = this.f2313a;
        int ordinal8 = BuffType.REGENERATION.ordinal();
        Factories factories8 = this.F;
        RegenerationBuff.RegenerationBuffFactory regenerationBuffFactory = new RegenerationBuff.RegenerationBuffFactory();
        factories8.REGENERATION = regenerationBuffFactory;
        factoryArr8[ordinal8] = regenerationBuffFactory;
        Buff.Factory[] factoryArr9 = this.f2313a;
        int ordinal9 = BuffType.ARMOR.ordinal();
        Factories factories9 = this.F;
        ArmorBuff.ArmorBuffFactory armorBuffFactory = new ArmorBuff.ArmorBuffFactory();
        factories9.ARMOR = armorBuffFactory;
        factoryArr9[ordinal9] = armorBuffFactory;
        Buff.Factory[] factoryArr10 = this.f2313a;
        int ordinal10 = BuffType.BONUS_COINS.ordinal();
        Factories factories10 = this.F;
        BonusCoinsBuff.BonusCoinsBuffFactory bonusCoinsBuffFactory = new BonusCoinsBuff.BonusCoinsBuffFactory();
        factories10.BONUS_COINS = bonusCoinsBuffFactory;
        factoryArr10[ordinal10] = bonusCoinsBuffFactory;
        Buff.Factory[] factoryArr11 = this.f2313a;
        int ordinal11 = BuffType.BONUS_XP.ordinal();
        Factories factories11 = this.F;
        BonusXpBuff.BonusXpBuffFactory bonusXpBuffFactory = new BonusXpBuff.BonusXpBuffFactory();
        factories11.BONUS_XP = bonusXpBuffFactory;
        factoryArr11[ordinal11] = bonusXpBuffFactory;
        Buff.Factory[] factoryArr12 = this.f2313a;
        int ordinal12 = BuffType.DEATH_EXPLOSION.ordinal();
        Factories factories12 = this.F;
        DeathExplosionBuff.ExplosionChargeBuffFactory explosionChargeBuffFactory = new DeathExplosionBuff.ExplosionChargeBuffFactory();
        factories12.DEATH_EXPLOSION = explosionChargeBuffFactory;
        factoryArr12[ordinal12] = explosionChargeBuffFactory;
        Buff.Factory[] factoryArr13 = this.f2313a;
        int ordinal13 = BuffType.CHAIN_REACTION.ordinal();
        Factories factories13 = this.F;
        ChainReactionBuff.ChainReactionBuffFactory chainReactionBuffFactory = new ChainReactionBuff.ChainReactionBuffFactory();
        factories13.CHAIN_REACTION = chainReactionBuffFactory;
        factoryArr13[ordinal13] = chainReactionBuffFactory;
        Buff.Factory[] factoryArr14 = this.f2313a;
        int ordinal14 = BuffType.VULNERABILITY.ordinal();
        Factories factories14 = this.F;
        VulnerabilityBuff.VulnerabilityBuffFactory vulnerabilityBuffFactory = new VulnerabilityBuff.VulnerabilityBuffFactory();
        factories14.VULNERABILITY = vulnerabilityBuffFactory;
        factoryArr14[ordinal14] = vulnerabilityBuffFactory;
        Buff.Factory[] factoryArr15 = this.f2313a;
        int ordinal15 = BuffType.INVULNERABILITY.ordinal();
        Factories factories15 = this.F;
        InvulnerabilityBuff.InvulnerabilityBuffFactory invulnerabilityBuffFactory = new InvulnerabilityBuff.InvulnerabilityBuffFactory();
        factories15.INVULNERABILITY = invulnerabilityBuffFactory;
        factoryArr15[ordinal15] = invulnerabilityBuffFactory;
        Buff.Factory[] factoryArr16 = this.f2313a;
        int ordinal16 = BuffType.SLIPPING.ordinal();
        Factories factories16 = this.F;
        SlippingBuff.SlippingBuffFactory slippingBuffFactory = new SlippingBuff.SlippingBuffFactory();
        factories16.SLIPPING = slippingBuffFactory;
        factoryArr16[ordinal16] = slippingBuffFactory;
        Buff.Factory[] factoryArr17 = this.f2313a;
        int ordinal17 = BuffType.NO_DAMAGE.ordinal();
        Factories factories17 = this.F;
        NoDamageBuff.NoDamageBuffFactory noDamageBuffFactory = new NoDamageBuff.NoDamageBuffFactory();
        factories17.NO_DAMAGE = noDamageBuffFactory;
        factoryArr17[ordinal17] = noDamageBuffFactory;
        Buff.Factory[] factoryArr18 = this.f2313a;
        int ordinal18 = BuffType.NO_BONUS_SYSTEM_POINTS.ordinal();
        Factories factories18 = this.F;
        NoBonusSystemPointsBuff.NoBonusSystemPointsBuffFactory noBonusSystemPointsBuffFactory = new NoBonusSystemPointsBuff.NoBonusSystemPointsBuffFactory();
        factories18.NO_BONUS_SYSTEM_POINTS = noBonusSystemPointsBuffFactory;
        factoryArr18[ordinal18] = noBonusSystemPointsBuffFactory;
        for (BuffType buffType : BuffType.values) {
            if (this.f2313a[buffType.ordinal()] == null) {
                throw new RuntimeException("Not all buff factories were created");
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        for (Buff.Factory factory : this.f2313a) {
            factory.setup();
        }
    }

    public Buff.Factory<? extends Buff> getFactory(BuffType buffType) {
        return this.f2313a[buffType.ordinal()];
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }
}
