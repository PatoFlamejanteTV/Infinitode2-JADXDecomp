package com.prineside.tdi2;

import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/GameSystem.class */
public abstract class GameSystem extends Registrable implements Disposable {
    public abstract boolean affectsGameState();

    public abstract String getSystemName();

    public int getFastStateHash() {
        return 0;
    }

    public void setup() {
    }

    public void postSetup() {
    }

    public void postStateRestore() {
    }

    public void update(float f) {
    }

    public boolean profileUpdate() {
        return true;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }
}
