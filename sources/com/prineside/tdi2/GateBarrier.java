package com.prineside.tdi2;

import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GateType;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/GateBarrier.class */
public abstract class GateBarrier extends Gate {
    public abstract boolean canEnemyPass(EnemyType enemyType);

    /* JADX INFO: Access modifiers changed from: protected */
    public GateBarrier(GateType gateType) {
        super(gateType);
    }
}
