package com.prineside.tdi2.utils.simulation;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/AbstractSimulation.class */
public abstract class AbstractSimulation implements Simulation {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3957a = TLog.forClass(AbstractSimulation.class);

    /* renamed from: b, reason: collision with root package name */
    private SimLogListener f3958b = (b2, str) -> {
    };

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public void setSimLogListener(SimLogListener simLogListener) {
        Preconditions.checkNotNull(simLogListener);
        this.f3958b = simLogListener;
    }

    public void logThrowable(byte b2, String str, Throwable th) {
        switch (b2) {
            case 1:
            case 2:
                f3957a.i(getClass().getSimpleName(), str, th);
                break;
            case 3:
                f3957a.e(getClass().getSimpleName(), str, th);
                break;
        }
        this.f3958b.onLog(b2, str);
    }

    public void log(byte b2, String str) {
        switch (b2) {
            case 1:
            case 2:
                f3957a.i(getClass().getSimpleName(), str);
                break;
            case 3:
                f3957a.e(getClass().getSimpleName(), str);
                break;
        }
        this.f3958b.onLog(b2, str);
    }
}
