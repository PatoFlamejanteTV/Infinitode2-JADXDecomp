package com.prineside.tdi2.enums;

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
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enums/BuffType.class */
public enum BuffType {
    FREEZING,
    POISON,
    BURN,
    BLIZZARD,
    ARMOR,
    SNOWBALL,
    REGENERATION,
    THROW_BACK,
    STUN,
    BONUS_COINS,
    BONUS_XP,
    DEATH_EXPLOSION,
    CHAIN_REACTION,
    VULNERABILITY,
    INVULNERABILITY,
    SLIPPING,
    NO_DAMAGE,
    NO_BONUS_SYSTEM_POINTS;

    public static final Class[] relevantClasses = {FreezingBuff.class, PoisonBuff.class, BurnBuff.class, BlizzardBuff.class, ArmorBuff.class, SnowballBuff.class, RegenerationBuff.class, ThrowBackBuff.class, StunBuff.class, BonusCoinsBuff.class, BonusXpBuff.class, DeathExplosionBuff.class, ChainReactionBuff.class, VulnerabilityBuff.class, InvulnerabilityBuff.class, SlippingBuff.class, NoDamageBuff.class, NoBonusSystemPointsBuff.class};
    public static final BuffType[] values = values();
}
