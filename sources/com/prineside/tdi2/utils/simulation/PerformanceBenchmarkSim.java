package com.prineside.tdi2.utils.simulation;

import com.google.common.b.a.a;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/PerformanceBenchmarkSim.class */
public final class PerformanceBenchmarkSim extends AbstractSimulation {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3963a = TLog.forClass(PerformanceBenchmarkSim.class);
    public static final long BENCHMARK_STATE_START_TIMESTAMP = 1692113508000L;
    public final SimConfig simConfig;
    public final int threadCount;
    public final int repeatCount;
    public final int frameCount;
    public final int jobCount;
    private final a[] c;
    private Runnable e;

    /* renamed from: b, reason: collision with root package name */
    private final AtomicBoolean f3964b = new AtomicBoolean(true);
    private final AtomicBoolean d = new AtomicBoolean();

    public PerformanceBenchmarkSim(SimConfig simConfig, int i, int i2, int i3) {
        this.simConfig = simConfig.cpy();
        this.simConfig.startTimestamp = 1692113508000L;
        this.threadCount = i;
        this.repeatCount = i2;
        this.frameCount = i3;
        this.jobCount = i2 * i;
        this.c = new a[this.jobCount];
        for (int i4 = 0; i4 < this.jobCount; i4++) {
            this.c[i4] = new a(0.0d);
        }
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final CharSequence getName() {
        return "Performance benchmark on " + this.threadCount + " threads, " + this.repeatCount + " repeats, " + this.frameCount + " frames, " + this.simConfig;
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final void setSimFinishListener(Runnable runnable) {
        this.e = runnable;
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final float getProgress() {
        double d = 0.0d;
        for (a aVar : this.c) {
            d += aVar.a();
        }
        return (float) (d / this.jobCount);
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final void start() {
        if (isRunning()) {
            throw new IllegalStateException("Already running");
        }
        for (int i = 0; i < this.jobCount; i++) {
            this.c[i].a(0.0d);
        }
        this.f3964b.set(true);
        Thread thread = new Thread(() -> {
            this.d.set(true);
            ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(this.threadCount, new Threads.DaemonThreadFactory("Performance benchmark", true));
            ArrayList arrayList = new ArrayList();
            JustUpdateScenario justUpdateScenario = new JustUpdateScenario(this.frameCount);
            for (int i2 = 0; i2 < this.jobCount; i2++) {
                int i3 = i2;
                f3963a.i("add callable " + i3, new Object[0]);
                arrayList.add(() -> {
                    try {
                        a(i3, this.simConfig, justUpdateScenario, this.c[i3]);
                        return null;
                    } catch (Exception e) {
                        logThrowable((byte) 3, "failed to start benchmark", e);
                        stop();
                        return null;
                    }
                });
            }
            this.d.set(true);
            long realTickCount = Game.getRealTickCount();
            try {
                log((byte) 1, "invoking " + arrayList.size() + " callables");
                newFixedThreadPool.invokeAll(arrayList);
                long j = this.frameCount * this.repeatCount * this.threadCount;
                double realTickCount2 = (Game.getRealTickCount() - realTickCount) * 0.001d;
                log((byte) 1, "all sim threads finished in " + realTickCount2 + "ms, " + ((Object) StringFormatter.commaSeparatedNumber(j)) + " frames / " + ((Object) StringFormatter.commaSeparatedNumber((long) ((j / realTickCount2) * 1000.0d))) + " UPS");
                stop();
                if (this.e != null) {
                    Threads.i().runOnMainThread(this.e);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "PerformanceBenchmarkSim main");
        thread.setDaemon(true);
        thread.start();
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final boolean isRunning() {
        return this.d.get();
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final boolean isReadyToStart() {
        return !isRunning();
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final boolean isSuccessful() {
        return this.f3964b.get();
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final void stop() {
        this.d.set(false);
    }

    private void a(int i, SimConfig simConfig, Scenario scenario, a aVar) {
        log((byte) 1, "starting sim #" + i);
        GameSystemProvider createProgressSnapshotAndInitGSP = SimConfig.createProgressSnapshotAndInitGSP(simConfig);
        Scenario cpy = scenario.cpy();
        cpy.start(createProgressSnapshotAndInitGSP);
        long realTickCount = Game.getRealTickCount();
        while (!cpy.isFinished() && isRunning()) {
            try {
                createProgressSnapshotAndInitGSP.updateSystems();
                cpy.onUpdate();
                aVar.a(cpy.getProgress());
            } catch (Exception e) {
                stop();
                this.f3964b.set(false);
                logThrowable((byte) 3, "exception in sim, frame " + createProgressSnapshotAndInitGSP.gameState.updateNumber, e);
            }
        }
        log((byte) 1, "sim #" + i + " finished in " + ((Game.getRealTickCount() - realTickCount) * 0.001d) + "ms, " + createProgressSnapshotAndInitGSP.gameState.updateNumber + " frames, completed waves: " + createProgressSnapshotAndInitGSP.wave.getCompletedWavesCount());
    }
}
