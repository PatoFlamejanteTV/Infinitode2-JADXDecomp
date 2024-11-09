package com.prineside.tdi2.systems;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.buffs.processors.ArmorBuffProcessor;
import com.prineside.tdi2.buffs.processors.BlizzardBuffProcessor;
import com.prineside.tdi2.buffs.processors.BonusCoinsBuffProcessor;
import com.prineside.tdi2.buffs.processors.BonusXpBuffProcessor;
import com.prineside.tdi2.buffs.processors.BurnBuffProcessor;
import com.prineside.tdi2.buffs.processors.ChainReactionBuffProcessor;
import com.prineside.tdi2.buffs.processors.DeathExplosionBuffProcessor;
import com.prineside.tdi2.buffs.processors.FreezingBuffProcessor;
import com.prineside.tdi2.buffs.processors.InvulnerabilityBuffProcessor;
import com.prineside.tdi2.buffs.processors.NoBonusSystemPointsBuffProcessor;
import com.prineside.tdi2.buffs.processors.NoDamageBuffProcessor;
import com.prineside.tdi2.buffs.processors.PoisonBuffProcessor;
import com.prineside.tdi2.buffs.processors.RegenerationBuffProcessor;
import com.prineside.tdi2.buffs.processors.SlippingBuffProcessor;
import com.prineside.tdi2.buffs.processors.SnowballBuffProcessor;
import com.prineside.tdi2.buffs.processors.StunBuffProcessor;
import com.prineside.tdi2.buffs.processors.ThrowBackBuffProcessor;
import com.prineside.tdi2.buffs.processors.VulnerabilityBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/BuffSystem.class */
public final class BuffSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private BuffProcessor[] f2932a = new BuffProcessor[BuffType.values.length];
    public FreezingBuffProcessor P_FREEZING;
    public PoisonBuffProcessor P_POISON;
    public BurnBuffProcessor P_BURN;
    public BlizzardBuffProcessor P_BLIZZARD;
    public ArmorBuffProcessor P_ARMOR;
    public SnowballBuffProcessor P_SNOWBALL;
    public RegenerationBuffProcessor P_REGENERATION;
    public ThrowBackBuffProcessor P_THROW_BACK;
    public StunBuffProcessor P_STUN;
    public BonusCoinsBuffProcessor P_BONUS_COINS;
    public BonusXpBuffProcessor P_BONUS_XP;
    public DeathExplosionBuffProcessor P_DEATH_EXPLOSION;
    public ChainReactionBuffProcessor P_CHAIN_REACTION;
    public VulnerabilityBuffProcessor P_VULNERABILITY;
    public InvulnerabilityBuffProcessor P_INVULNERABILITY;
    public SlippingBuffProcessor P_SLIPPING;
    public NoDamageBuffProcessor P_NO_DAMAGE;
    public NoBonusSystemPointsBuffProcessor P_NO_BONUS_SYSTEM_POINTS;

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f2932a);
        kryo.writeObject(output, this.P_FREEZING);
        kryo.writeObject(output, this.P_POISON);
        kryo.writeObject(output, this.P_BURN);
        kryo.writeObject(output, this.P_BLIZZARD);
        kryo.writeObject(output, this.P_ARMOR);
        kryo.writeObject(output, this.P_SNOWBALL);
        kryo.writeObject(output, this.P_REGENERATION);
        kryo.writeObject(output, this.P_THROW_BACK);
        kryo.writeObject(output, this.P_STUN);
        kryo.writeObject(output, this.P_BONUS_COINS);
        kryo.writeObject(output, this.P_BONUS_XP);
        kryo.writeObject(output, this.P_DEATH_EXPLOSION);
        kryo.writeObject(output, this.P_CHAIN_REACTION);
        kryo.writeObject(output, this.P_VULNERABILITY);
        kryo.writeObject(output, this.P_INVULNERABILITY);
        kryo.writeObject(output, this.P_SLIPPING);
        kryo.writeObject(output, this.P_NO_DAMAGE);
        kryo.writeObject(output, this.P_NO_BONUS_SYSTEM_POINTS);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2932a = (BuffProcessor[]) kryo.readObject(input, BuffProcessor[].class);
        this.P_FREEZING = (FreezingBuffProcessor) kryo.readObject(input, FreezingBuffProcessor.class);
        this.P_POISON = (PoisonBuffProcessor) kryo.readObject(input, PoisonBuffProcessor.class);
        this.P_BURN = (BurnBuffProcessor) kryo.readObject(input, BurnBuffProcessor.class);
        this.P_BLIZZARD = (BlizzardBuffProcessor) kryo.readObject(input, BlizzardBuffProcessor.class);
        this.P_ARMOR = (ArmorBuffProcessor) kryo.readObject(input, ArmorBuffProcessor.class);
        this.P_SNOWBALL = (SnowballBuffProcessor) kryo.readObject(input, SnowballBuffProcessor.class);
        this.P_REGENERATION = (RegenerationBuffProcessor) kryo.readObject(input, RegenerationBuffProcessor.class);
        this.P_THROW_BACK = (ThrowBackBuffProcessor) kryo.readObject(input, ThrowBackBuffProcessor.class);
        this.P_STUN = (StunBuffProcessor) kryo.readObject(input, StunBuffProcessor.class);
        this.P_BONUS_COINS = (BonusCoinsBuffProcessor) kryo.readObject(input, BonusCoinsBuffProcessor.class);
        this.P_BONUS_XP = (BonusXpBuffProcessor) kryo.readObject(input, BonusXpBuffProcessor.class);
        this.P_DEATH_EXPLOSION = (DeathExplosionBuffProcessor) kryo.readObject(input, DeathExplosionBuffProcessor.class);
        this.P_CHAIN_REACTION = (ChainReactionBuffProcessor) kryo.readObject(input, ChainReactionBuffProcessor.class);
        this.P_VULNERABILITY = (VulnerabilityBuffProcessor) kryo.readObject(input, VulnerabilityBuffProcessor.class);
        this.P_INVULNERABILITY = (InvulnerabilityBuffProcessor) kryo.readObject(input, InvulnerabilityBuffProcessor.class);
        this.P_SLIPPING = (SlippingBuffProcessor) kryo.readObject(input, SlippingBuffProcessor.class);
        this.P_NO_DAMAGE = (NoDamageBuffProcessor) kryo.readObject(input, NoDamageBuffProcessor.class);
        this.P_NO_BONUS_SYSTEM_POINTS = (NoBonusSystemPointsBuffProcessor) kryo.readObject(input, NoBonusSystemPointsBuffProcessor.class);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        for (BuffType buffType : BuffType.values) {
            this.f2932a[buffType.ordinal()] = Game.i.buffManager.getFactory(buffType).createProcessor();
            this.f2932a[buffType.ordinal()].setRegistered(this.S);
        }
        this.P_FREEZING = (FreezingBuffProcessor) getProcessor(BuffType.FREEZING);
        this.P_POISON = (PoisonBuffProcessor) getProcessor(BuffType.POISON);
        this.P_BURN = (BurnBuffProcessor) getProcessor(BuffType.BURN);
        this.P_BLIZZARD = (BlizzardBuffProcessor) getProcessor(BuffType.BLIZZARD);
        this.P_ARMOR = (ArmorBuffProcessor) getProcessor(BuffType.ARMOR);
        this.P_SNOWBALL = (SnowballBuffProcessor) getProcessor(BuffType.SNOWBALL);
        this.P_REGENERATION = (RegenerationBuffProcessor) getProcessor(BuffType.REGENERATION);
        this.P_THROW_BACK = (ThrowBackBuffProcessor) getProcessor(BuffType.THROW_BACK);
        this.P_STUN = (StunBuffProcessor) getProcessor(BuffType.STUN);
        this.P_BONUS_COINS = (BonusCoinsBuffProcessor) getProcessor(BuffType.BONUS_COINS);
        this.P_BONUS_XP = (BonusXpBuffProcessor) getProcessor(BuffType.BONUS_XP);
        this.P_DEATH_EXPLOSION = (DeathExplosionBuffProcessor) getProcessor(BuffType.DEATH_EXPLOSION);
        this.P_CHAIN_REACTION = (ChainReactionBuffProcessor) getProcessor(BuffType.CHAIN_REACTION);
        this.P_VULNERABILITY = (VulnerabilityBuffProcessor) getProcessor(BuffType.VULNERABILITY);
        this.P_INVULNERABILITY = (InvulnerabilityBuffProcessor) getProcessor(BuffType.INVULNERABILITY);
        this.P_SLIPPING = (SlippingBuffProcessor) getProcessor(BuffType.SLIPPING);
        this.P_NO_DAMAGE = (NoDamageBuffProcessor) getProcessor(BuffType.NO_DAMAGE);
        this.P_NO_BONUS_SYSTEM_POINTS = (NoBonusSystemPointsBuffProcessor) getProcessor(BuffType.NO_BONUS_SYSTEM_POINTS);
    }

    public final BuffProcessor getProcessor(BuffType buffType) {
        return this.f2932a[buffType.ordinal()];
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        int i = this.S.map.spawnedEnemies.size;
        for (int i2 = 0; i2 < i; i2++) {
            Enemy enemy = this.S.map.spawnedEnemies.items[i2].enemy;
            if (enemy != null && enemy.buffsByType != null) {
                for (BuffType buffType : BuffType.values) {
                    DelayedRemovalArray delayedRemovalArray = enemy.buffsByType[buffType.ordinal()];
                    delayedRemovalArray.begin();
                    int i3 = delayedRemovalArray.size;
                    for (int i4 = 0; i4 < i3; i4++) {
                        Buff buff = ((Buff[]) delayedRemovalArray.items)[i4];
                        buff.duration -= f;
                        if (buff.duration <= 0.0f) {
                            this.f2932a[buffType.ordinal()].removeBuffAtIndex(enemy, buffType, i4);
                        }
                    }
                    delayedRemovalArray.end();
                }
            }
        }
        for (BuffProcessor buffProcessor : this.f2932a) {
            buffProcessor.update(f);
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Buff";
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        for (int i = 0; i < this.f2932a.length; i++) {
            this.f2932a[i].setUnregistered();
            this.f2932a[i] = null;
        }
        super.dispose();
    }
}
