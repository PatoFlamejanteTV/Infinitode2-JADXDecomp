package com.prineside.tdi2.buffs.processors;

import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.buffs.NoDamageBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyReachTarget;
import com.prineside.tdi2.tiles.TargetTile;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/NoDamageBuffProcessor.class */
public final class NoDamageBuffProcessor extends BuffProcessor<NoDamageBuff> implements Listener<EnemyReachTarget> {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1821a = TLog.forClass(NoDamageBuffProcessor.class);

    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean isDebuff() {
        return true;
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, NoDamageBuff noDamageBuff) {
        return a(enemy, noDamageBuff);
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setRegistered(GameSystemProvider gameSystemProvider) {
        super.setRegistered(gameSystemProvider);
        gameSystemProvider.events.getListeners(EnemyReachTarget.class).addStateAffecting(this).setDescription("NoDamageBuffProcessor - sets damage to zero if enemy has NO_DAMAGE buff");
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        super.setUnregistered();
        if (this.S != null && this.S.events != null) {
            this.S.events.getListeners(EnemyReachTarget.class).remove(this);
        }
    }

    @Override // com.prineside.tdi2.events.Listener
    public final void handleEvent(EnemyReachTarget enemyReachTarget) {
        if ((enemyReachTarget.getEnemy().getCurrentTile() instanceof TargetTile) && enemyReachTarget.getEnemy().hasBuffsByType(BuffType.NO_DAMAGE)) {
            f1821a.i("enemy has NO_DAMAGE buff, removing damage", new Object[0]);
            enemyReachTarget.setFactDamage(0);
        }
    }
}
