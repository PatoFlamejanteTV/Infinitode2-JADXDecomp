package com.prineside.tdi2.utils.simulation;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.GameSystemProvider;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/JustUpdateScenario.class */
public final class JustUpdateScenario implements Scenario {

    /* renamed from: a, reason: collision with root package name */
    private GameSystemProvider f3961a;

    /* renamed from: b, reason: collision with root package name */
    private final int f3962b;

    public JustUpdateScenario(int i) {
        Preconditions.checkArgument(i > 0, "frames must be > 0, %s given", i);
        this.f3962b = i;
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public final void setGSP(GameSystemProvider gameSystemProvider) {
        this.f3961a = gameSystemProvider;
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public final void start(GameSystemProvider gameSystemProvider) {
        this.f3961a = gameSystemProvider;
        gameSystemProvider.wave.startNextWave();
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public final boolean isFinished() {
        return this.f3961a.gameState.updateNumber >= this.f3962b;
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public final float getProgress() {
        return this.f3961a.gameState.updateNumber / this.f3962b;
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public final void onUpdate() {
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public final Scenario cpy() {
        return new JustUpdateScenario(this.f3962b);
    }
}
