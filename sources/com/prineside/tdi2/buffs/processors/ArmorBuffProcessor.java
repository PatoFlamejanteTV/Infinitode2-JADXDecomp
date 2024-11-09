package com.prineside.tdi2.buffs.processors;

import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.buffs.ArmorBuff;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/ArmorBuffProcessor.class */
public final class ArmorBuffProcessor extends BuffProcessor<ArmorBuff> {
    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean isDebuff() {
        return false;
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, ArmorBuff armorBuff) {
        return a(enemy, armorBuff);
    }
}
