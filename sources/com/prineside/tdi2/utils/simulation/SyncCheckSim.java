package com.prineside.tdi2.utils.simulation;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.StringBuilder;
import com.google.common.b.a.a;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.utils.ObjectPair;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/SyncCheckSim.class */
public class SyncCheckSim extends AbstractSimulation {
    public final SimConfig simConfig;
    public final int syncCheckFrameInterval;
    public final int parallelThreads;
    public final int extraLoadThreads;
    public final Array<ObjectPair<String, Scenario>> scenarios;
    public final Scenario extraLoadScenario;
    private final CyclicBarrier e;
    private Runnable h;

    /* renamed from: a, reason: collision with root package name */
    private final AtomicBoolean f3965a = new AtomicBoolean(false);

    /* renamed from: b, reason: collision with root package name */
    private final AtomicBoolean f3966b = new AtomicBoolean(true);
    private final AtomicBoolean c = new AtomicBoolean(true);
    private final Array<GameSystemProvider> d = new Array<>(true, 1, GameSystemProvider.class);
    private final AtomicInteger f = new AtomicInteger(0);
    private final a g = new a(0.0d);

    public SyncCheckSim(SimConfig simConfig, Array<ObjectPair<String, Scenario>> array, Scenario scenario, int i, int i2, int i3) {
        Preconditions.checkNotNull(simConfig, "simConfig can not be null");
        Preconditions.checkNotNull(array, "scenarios can not be null");
        Preconditions.checkArgument(i >= 2, "parallelThreads must be 2+, %s given", i);
        Preconditions.checkArgument(i2 >= 0, "extraLoadThreads must be 0+, %s given", i2);
        Preconditions.checkArgument(i3 > 0, "syncCheckFrameInterval must be 1+, %s given", i3);
        this.simConfig = simConfig.cpy();
        this.extraLoadScenario = scenario;
        this.syncCheckFrameInterval = i3;
        this.parallelThreads = i;
        this.extraLoadThreads = i2;
        this.scenarios = array;
        this.e = new CyclicBarrier(i, () -> {
            GameSystemProvider first = this.d.first();
            if (first.gameState.updateNumber % i3 == 0) {
                if (first.gameState.updateNumber % (i3 * 100) == 0) {
                    log((byte) 1, "comparing GSPs " + first.gameState.updateNumber + " on " + Thread.currentThread().getName());
                }
                for (int i4 = 1; i4 < i; i4++) {
                    try {
                        GameSystemProvider gameSystemProvider = this.d.items[i4];
                        StringBuilder stringBuilder = new StringBuilder();
                        first.compareSync(gameSystemProvider, stringBuilder, false);
                        if (stringBuilder.length != 0) {
                            log((byte) 3, "Desync on frame " + first.gameState.updateNumber);
                            log((byte) 3, stringBuilder.toString());
                            log((byte) 3, "- S1 log: \n" + first.syncCheckLog.toString(SequenceUtils.EOL));
                            log((byte) 3, "- S2 log: \n" + gameSystemProvider.syncCheckLog.toString(SequenceUtils.EOL));
                            this.f3966b.set(false);
                            stop();
                        }
                        gameSystemProvider.syncCheckLog.clear();
                    } catch (Exception e) {
                        logThrowable((byte) 3, "exception in sim-sync, frame " + first.gameState.updateNumber, e);
                    }
                }
                for (int i5 = 0; i5 < i; i5++) {
                    this.d.items[i5].syncCheckLog.clear();
                }
                if ((first.gameState.updateNumber / i3) % 10 == 0) {
                    GameSystemProvider deepCopy = first.deepCopy();
                    StringBuilder stringBuilder2 = new StringBuilder();
                    first.compareSync(deepCopy, stringBuilder2, false);
                    if (stringBuilder2.length != 0) {
                        log((byte) 3, "Desync on frame " + first.gameState.updateNumber + " from deep copy");
                        log((byte) 3, stringBuilder2.toString());
                        log((byte) 3, "- S1 log: \n" + first.syncCheckLog.toString(SequenceUtils.EOL));
                        log((byte) 3, "- S2 log: \n" + deepCopy.syncCheckLog.toString(SequenceUtils.EOL));
                        this.f3966b.set(false);
                        stop();
                    }
                    deepCopy.syncCheckLog.clear();
                }
            }
        });
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public CharSequence getName() {
        return "Sync check on " + this.parallelThreads + " threads, " + this.extraLoadThreads + " extra threads, " + this.simConfig.getShortDescription();
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public void setSimFinishListener(Runnable runnable) {
        this.h = runnable;
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public float getProgress() {
        return (this.g.floatValue() / this.scenarios.size) + (this.f.get() / this.scenarios.size);
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public void start() {
        if (isRunning()) {
            throw new IllegalStateException("Already running");
        }
        this.f.set(0);
        this.g.a(0.0d);
        this.c.set(false);
        this.e.reset();
        this.f3965a.set(true);
        this.f3966b.set(true);
        Thread thread = new Thread(() -> {
            Array.ArrayIterator<ObjectPair<String, Scenario>> it = this.scenarios.iterator();
            while (it.hasNext()) {
                ObjectPair<String, Scenario> next = it.next();
                a(next.second);
                this.g.a(0.0d);
                if (!isSuccessful() || !isRunning()) {
                    break;
                }
                this.f.addAndGet(1);
                log((byte) 1, "==== SIM END " + next.first + " : " + this.f3966b.get());
            }
            log((byte) 1, "==== ALL DONE");
            stop();
            this.c.set(true);
            if (this.h != null) {
                Threads.i().runOnMainThread(this.h);
            }
        }, "sync-check-main");
        thread.setDaemon(true);
        thread.start();
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public boolean isRunning() {
        return this.f3965a.get();
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public boolean isReadyToStart() {
        return this.c.get();
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public boolean isSuccessful() {
        return this.f3966b.get();
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public void stop() {
        this.f3965a.set(false);
        this.e.reset();
    }

    private void a(Scenario scenario) {
        log((byte) 1, "Running test scenario: " + scenario);
        this.d.clear();
        Array array = new Array(true, 1, Thread.class);
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        for (int i = 0; i < this.parallelThreads; i++) {
            GameSystemProvider createProgressSnapshotAndInitGSP = SimConfig.createProgressSnapshotAndInitGSP(this.simConfig);
            Scenario cpy = scenario.cpy();
            this.d.add(createProgressSnapshotAndInitGSP);
            int i2 = this.d.size - 1;
            if (this.syncCheckFrameInterval == 1) {
                createProgressSnapshotAndInitGSP.syncChecking = true;
            }
            int i3 = i + 1;
            Thread thread = new Thread(() -> {
                cpy.start(this.d.items[i2]);
                long realTickCount = Game.getRealTickCount();
                while (!cpy.isFinished() && isRunning()) {
                    GameSystemProvider gameSystemProvider = this.d.items[i2];
                    try {
                        gameSystemProvider.updateSystems();
                        cpy.onUpdate();
                        if (i3 == 1) {
                            this.g.a(cpy.getProgress());
                        }
                        if (!isRunning()) {
                            break;
                        } else {
                            this.e.await();
                        }
                    } catch (BrokenBarrierException unused) {
                        log((byte) 2, "broken barrier, stopping sim on frame " + gameSystemProvider.gameState.updateNumber);
                    } catch (Exception e) {
                        this.f3966b.set(false);
                        stop();
                        logThrowable((byte) 3, "exception in sim, frame " + gameSystemProvider.gameState.updateNumber, e);
                    }
                }
                log((byte) 1, "sim-" + i3 + " finished in " + ((Game.getRealTickCount() - realTickCount) * 0.001d) + "ms, " + this.d.items[i2].gameState.updateNumber + " frames, random seed: " + this.d.items[i2].gameState.getRandomState(0));
                atomicBoolean.set(false);
            }, "sim-" + i3);
            thread.setDaemon(true);
            array.add(thread);
        }
        for (int i4 = 0; i4 < this.extraLoadThreads; i4++) {
            int i5 = i4 + 1 + this.parallelThreads;
            Scenario cpy2 = this.extraLoadScenario.cpy();
            GameSystemProvider createProgressSnapshotAndInitGSP2 = SimConfig.createProgressSnapshotAndInitGSP(this.simConfig);
            String str = "sim-mass-" + i5;
            Thread thread2 = new Thread(() -> {
                cpy2.start(createProgressSnapshotAndInitGSP2);
                long realTickCount = Game.getRealTickCount();
                while (!cpy2.isFinished() && isRunning() && atomicBoolean.get()) {
                    try {
                        createProgressSnapshotAndInitGSP2.updateSystems();
                        cpy2.onUpdate();
                    } catch (Exception e) {
                        this.f3966b.set(false);
                        stop();
                        logThrowable((byte) 3, "exception in " + str + ", frame " + createProgressSnapshotAndInitGSP2.gameState.updateNumber, e);
                    }
                }
                log((byte) 1, str + " finished in " + ((Game.getRealTickCount() - realTickCount) * 0.001d) + "ms, " + createProgressSnapshotAndInitGSP2.gameState.updateNumber + " frames, massThreadsAlive: " + atomicBoolean.get());
            }, str);
            thread2.setDaemon(true);
            array.add(thread2);
        }
        for (int i6 = 0; i6 < array.size; i6++) {
            try {
                ((Thread) array.get(i6)).start();
            } catch (Exception unused) {
                return;
            }
        }
        for (int i7 = 0; i7 < array.size; i7++) {
            ((Thread) array.get(i7)).join();
        }
    }
}
