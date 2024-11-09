package com.prineside.tdi2.utils.simulation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.StringBuilder;
import com.google.common.b.a.a;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.simulation.TowersBenchmarkScenario;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/TowerBenchmarkSim.class */
public final class TowerBenchmarkSim extends AbstractSimulation {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3967a = TLog.forClass(TowerBenchmarkSim.class);
    public static final long BENCHMARK_STATE_START_TIMESTAMP = 1692113508000L;
    public final String benchmarkName;
    public final int threadCount;
    public final int runsPerCombo;
    public final ResearchTreeMode researchTreeMode;
    public final SimConfig simConfig;
    public final TowerType[] towerTypes;
    public final int[] waveCounts;
    public final int[] upgradeLevels;
    public final TowersBenchmarkScenario.ExtraTowers[] extraTowerScenarios;
    public final TowerBenchmarkXpConfig[] towerXpConfigs;
    private Throwable d;
    private Runnable f;

    /* renamed from: b, reason: collision with root package name */
    private final Array<a> f3968b = new Array<>(true, 1, a.class);
    private final AtomicBoolean c = new AtomicBoolean(false);
    private final List<TowerBenchmarkResults> e = Collections.synchronizedList(new ArrayList());

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/TowerBenchmarkSim$ResearchTreeMode.class */
    public enum ResearchTreeMode {
        NO_RESEARCH,
        FULL_NORMAL,
        FULL_ENDLESS
    }

    public TowerBenchmarkSim(SimConfig simConfig, String str, int i, int i2, ResearchTreeMode researchTreeMode, Array<TowerBenchmarkXpConfig> array, IntArray intArray, IntArray intArray2, Array<TowersBenchmarkScenario.ExtraTowers> array2, Array<TowerType> array3) {
        this.benchmarkName = str;
        this.threadCount = i;
        this.runsPerCombo = i2;
        this.researchTreeMode = researchTreeMode;
        this.simConfig = simConfig.cpy();
        this.towerTypes = array3.toArray();
        this.waveCounts = intArray.toArray();
        this.upgradeLevels = intArray2.toArray();
        this.extraTowerScenarios = array2.toArray();
        this.towerXpConfigs = array.toArray();
    }

    private TowerBenchmarkResults a(String str, TowersBenchmarkScenario towersBenchmarkScenario, GameSystemProvider gameSystemProvider, ObjectConsumer<Float> objectConsumer) {
        TowersBenchmarkScenario cpy = towersBenchmarkScenario.cpy();
        cpy.start(gameSystemProvider);
        long realTickCount = Game.getRealTickCount();
        while (!cpy.isFinished() && isRunning()) {
            try {
                gameSystemProvider.updateSystems();
                cpy.onUpdate();
                objectConsumer.accept(Float.valueOf(cpy.getProgress()));
            } catch (Exception e) {
                logThrowable((byte) 3, "exception in " + str + ", frame " + gameSystemProvider.gameState.updateNumber, e);
                this.d = e;
                stop();
            }
        }
        TowerBenchmarkResults towerBenchmarkResults = new TowerBenchmarkResults(str);
        long j = 0;
        long j2 = 0;
        towerBenchmarkResults.ups = (int) ((gameSystemProvider.gameState.updateNumber * 1000) / (((float) (Game.getRealTickCount() - realTickCount)) * 0.001f));
        towerBenchmarkResults.minTowerDPS = Integer.MAX_VALUE;
        towerBenchmarkResults.maxTowerDPS = Integer.MIN_VALUE;
        towerBenchmarkResults.towerPrice = Integer.MIN_VALUE;
        towerBenchmarkResults.minTowerKills = Integer.MAX_VALUE;
        towerBenchmarkResults.maxTowerKills = Integer.MIN_VALUE;
        towerBenchmarkResults.sumTowersPrice = 0;
        for (int i = 0; i < gameSystemProvider.tower.towers.size; i++) {
            Tower tower = gameSystemProvider.tower.towers.get(i);
            if (tower.type == towersBenchmarkScenario.towerType && tower.moneySpentOn != 0) {
                towerBenchmarkResults.towerPrice = Math.max(towerBenchmarkResults.towerPrice, tower.moneySpentOn);
                int i2 = (int) tower.mdps;
                towerBenchmarkResults.minTowerDPS = Math.min(towerBenchmarkResults.minTowerDPS, i2);
                towerBenchmarkResults.maxTowerDPS = Math.max(towerBenchmarkResults.maxTowerDPS, i2);
                j += i2;
                towerBenchmarkResults.minTowerKills = Math.min(towerBenchmarkResults.minTowerKills, tower.enemiesKilled);
                towerBenchmarkResults.maxTowerKills = Math.max(towerBenchmarkResults.maxTowerKills, tower.enemiesKilled);
                j2 += tower.enemiesKilled;
                towerBenchmarkResults.sumTowersPrice += tower.moneySpentOn;
            }
        }
        towerBenchmarkResults.avgTowerDPS = (int) (j / gameSystemProvider.tower.towers.size);
        towerBenchmarkResults.avgTowerKills = (int) (j2 / gameSystemProvider.statistics.getStatistic(StatisticsType.EK));
        towerBenchmarkResults.totalKills = (int) gameSystemProvider.statistics.getStatistic(StatisticsType.EK);
        towerBenchmarkResults.mdps = (long) gameSystemProvider.damage.getTowersMaxDps();
        towerBenchmarkResults.totalDamage = gameSystemProvider.statistics.getStatistic(Game.i.towerManager.getDamageDealtStatisticType(towersBenchmarkScenario.towerType));
        return towerBenchmarkResults;
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final CharSequence getName() {
        return "Towers benchmark " + this.benchmarkName + " (" + this.researchTreeMode + ") on " + this.threadCount + " threads, " + Arrays.toString(this.waveCounts) + " waves, " + Arrays.toString(this.upgradeLevels) + " upgrade levels, " + Arrays.toString(this.extraTowerScenarios) + " extra towers, " + this.towerXpConfigs.length + " XP configs, " + this.simConfig.basicLevelName;
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final void setSimFinishListener(Runnable runnable) {
        this.f = runnable;
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final float getProgress() {
        synchronized (this.f3968b) {
            if (this.f3968b.size == 0) {
                return 0.0f;
            }
            double d = 0.0d;
            for (int i = 0; i < this.f3968b.size; i++) {
                d += this.f3968b.get(i).a();
            }
            return (float) (d / this.f3968b.size);
        }
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final void start() {
        if (isRunning()) {
            throw new IllegalStateException("Already running");
        }
        synchronized (this.f3968b) {
            this.f3968b.clear();
        }
        this.e.clear();
        this.d = null;
        this.c.set(true);
        SimConfig cpy = this.simConfig.cpy();
        cpy.startTimestamp = 1692113508000L;
        ProgressManager.ProgressSnapshotForState progressSnapshotForState = null;
        switch (this.researchTreeMode) {
            case NO_RESEARCH:
                cpy.difficultyMode = DifficultyMode.NORMAL;
                progressSnapshotForState = new ProgressManager.ProgressSnapshotForState();
                break;
            case FULL_NORMAL:
                cpy.difficultyMode = DifficultyMode.NORMAL;
                progressSnapshotForState = Game.i.progressManager.createProgressSnapshotForState();
                break;
            case FULL_ENDLESS:
                cpy.difficultyMode = DifficultyMode.ENDLESS_I;
                cpy.difficultyModeMultiplier = 900;
                progressSnapshotForState = Game.i.progressManager.createProgressSnapshotForState();
                break;
        }
        ProgressManager.ProgressSnapshotForState progressSnapshotForState2 = progressSnapshotForState;
        Thread thread = new Thread(() -> {
            ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(this.threadCount, new Threads.DaemonThreadFactory("Towers benchmark", true));
            ArrayList arrayList = new ArrayList();
            log((byte) 1, "starting tower benchmark in " + cpy.difficultyMode + " mode (" + this.researchTreeMode + " / " + this.benchmarkName + ")");
            AtomicInteger atomicInteger = new AtomicInteger(0);
            AtomicInteger atomicInteger2 = new AtomicInteger(0);
            RandomXS128 randomXS128 = new RandomXS128();
            for (int i : this.waveCounts) {
                for (TowerType towerType : this.towerTypes) {
                    for (TowersBenchmarkScenario.ExtraTowers extraTowers : this.extraTowerScenarios) {
                        for (int i2 : this.upgradeLevels) {
                            Tower.AimStrategy aimStrategy = Tower.AimStrategy.RANDOM;
                            for (TowerBenchmarkXpConfig towerBenchmarkXpConfig : this.towerXpConfigs) {
                                for (int i3 = 0; i3 < this.runsPerCombo; i3++) {
                                    atomicInteger2.addAndGet(1);
                                    a aVar = new a(0.0d);
                                    synchronized (this.f3968b) {
                                        this.f3968b.add(aVar);
                                    }
                                    int i4 = i3;
                                    arrayList.add(() -> {
                                        try {
                                            if (!this.c.get()) {
                                                return null;
                                            }
                                            SimConfig cpy2 = cpy.cpy();
                                            if (i4 != 0) {
                                                cpy2.startTimestamp += i4;
                                            }
                                            GameSystemProvider initGSP = SimConfig.initGSP(cpy2, progressSnapshotForState2);
                                            initGSP.gameState.setSeed(cpy2.startTimestamp);
                                            String str = towerType.name() + "|" + i2 + "|" + aimStrategy + "|" + towerBenchmarkXpConfig.name + "|" + i + "|" + extraTowers;
                                            TowersBenchmarkScenario towersBenchmarkScenario = new TowersBenchmarkScenario(i, towerType, towerBenchmarkXpConfig.abilities, aimStrategy, randomXS128.nextFloat(), towerBenchmarkXpConfig.xpLevel, i2, extraTowers);
                                            Objects.requireNonNull(aVar);
                                            TowerBenchmarkResults a2 = a(str, towersBenchmarkScenario, initGSP, (v1) -> {
                                                r4.a(v1);
                                            });
                                            int addAndGet = atomicInteger.addAndGet(1);
                                            this.e.add(a2);
                                            if (isRunning()) {
                                                log((byte) 1, "==== Done " + str + " (" + i4 + ") " + addAndGet + " / " + atomicInteger2.get());
                                            }
                                            return a2;
                                        } catch (Exception e) {
                                            logThrowable((byte) 3, "failed to start simulation", e);
                                            this.d = e;
                                            stop();
                                            return null;
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }
            this.c.set(true);
            try {
                newFixedThreadPool.invokeAll(arrayList);
                log((byte) 1, "All finished");
                if (this.d != null) {
                    this.d.printStackTrace();
                    log((byte) 3, "Finished with error, check console");
                } else {
                    synchronized (this.e) {
                        DelayedRemovalArray delayedRemovalArray = new DelayedRemovalArray(true, this.e.size(), TowerBenchmarkResults.class);
                        delayedRemovalArray.addAll((TowerBenchmarkResults[]) this.e.toArray(new TowerBenchmarkResults[0]));
                        f3967a.i(delayedRemovalArray.size + " (resultsCopy.size)", new Object[0]);
                        if (this.runsPerCombo > 1) {
                            for (int i5 = 0; i5 < delayedRemovalArray.size; i5++) {
                                TowerBenchmarkResults towerBenchmarkResults = (TowerBenchmarkResults) delayedRemovalArray.get(i5);
                                if (towerBenchmarkResults != null) {
                                    for (int i6 = i5 + 1; i6 < delayedRemovalArray.size; i6++) {
                                        TowerBenchmarkResults towerBenchmarkResults2 = (TowerBenchmarkResults) delayedRemovalArray.get(i6);
                                        if (towerBenchmarkResults2 != null && towerBenchmarkResults.name.equals(towerBenchmarkResults2.name)) {
                                            towerBenchmarkResults.add(towerBenchmarkResults2);
                                            delayedRemovalArray.set(i6, null);
                                        }
                                    }
                                    towerBenchmarkResults.divideValues(this.runsPerCombo);
                                }
                            }
                        }
                        delayedRemovalArray.begin();
                        for (int i7 = 0; i7 < delayedRemovalArray.size; i7++) {
                            if (delayedRemovalArray.get(i7) == 0) {
                                delayedRemovalArray.removeIndex(i7);
                            }
                        }
                        delayedRemovalArray.end();
                        f3967a.i(delayedRemovalArray.size + " (resultsCopy.size after)", new Object[0]);
                        TowerBenchmarkResults[] towerBenchmarkResultsArr = (TowerBenchmarkResults[]) delayedRemovalArray.toArray(TowerBenchmarkResults.class);
                        f3967a.i(towerBenchmarkResultsArr.length + " (resultsOrdered.length)", new Object[0]);
                        Threads.sort(towerBenchmarkResultsArr, (towerBenchmarkResults3, towerBenchmarkResults4) -> {
                            return towerBenchmarkResults3.name.compareTo(towerBenchmarkResults4.name);
                        });
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("INSERT INTO towers_benchmark (benchmarkName, waves, extraTowers, tower, aimStrategy, abilities, upgradeLevel, xpLevel, towerPrice, maxTowerDPS, avgTowerDPS, mdps, maxTowerKills, totalKills, ups, totalDamage, sumTowersPrice, dpsPerCoin, totalDamagePerCoin, killsPerCoin) VALUES \n");
                        for (TowerBenchmarkResults towerBenchmarkResults5 : towerBenchmarkResultsArr) {
                            stringBuilder.append("('").append(this.benchmarkName).append("', ").append(towerBenchmarkResults5.waveCount).append(", ").append("'").append(towerBenchmarkResults5.extraTowers).append("',").append("'").append(towerBenchmarkResults5.tower).append("',").append("'").append(towerBenchmarkResults5.aimStrategy).append("',").append("'").append(towerBenchmarkResults5.abilities).append("',").append("'").append(towerBenchmarkResults5.upgradeLevel).append("',").append("'").append(towerBenchmarkResults5.xpLevel).append("',").append("'").append(towerBenchmarkResults5.towerPrice).append("',").append("'").append(towerBenchmarkResults5.maxTowerDPS).append("',").append("'").append(towerBenchmarkResults5.avgTowerDPS).append("',").append("'").append(towerBenchmarkResults5.mdps).append("',").append("'").append(towerBenchmarkResults5.maxTowerKills).append("',").append("'").append(towerBenchmarkResults5.totalKills).append("',").append("'").append(towerBenchmarkResults5.ups).append("',").append("'").append(towerBenchmarkResults5.totalDamage).append("',").append("'").append(towerBenchmarkResults5.sumTowersPrice).append("',").append("'").append(towerBenchmarkResults5.maxTowerDPS / towerBenchmarkResults5.towerPrice).append("',").append("'").append(towerBenchmarkResults5.totalDamage / towerBenchmarkResults5.sumTowersPrice).append("',").append("'").append(towerBenchmarkResults5.maxTowerKills / towerBenchmarkResults5.towerPrice).append("'),\n");
                        }
                        stringBuilder.setLength(stringBuilder.length - 2);
                        stringBuilder.append(" \nON DUPLICATE KEY UPDATE towerPrice = VALUES(towerPrice), maxTowerDPS = VALUES(maxTowerDPS), avgTowerDPS = VALUES(avgTowerDPS), mdps = VALUES(mdps), maxTowerKills = VALUES(maxTowerKills), totalKills = VALUES(totalKills), ups = VALUES(ups), totalDamage = VALUES(totalDamage), sumTowersPrice = VALUES(sumTowersPrice), dpsPerCoin = VALUES(dpsPerCoin), killsPerCoin = VALUES(killsPerCoin), totalDamagePerCoin = VALUES(totalDamagePerCoin);");
                        Gdx.files.local(this.benchmarkName + ".sql").writeString(stringBuilder.toString(), false, "UTF-8");
                        log((byte) 1, "Saved results as " + this.benchmarkName + ".sql");
                    }
                }
                this.c.set(false);
                if (this.f != null) {
                    Threads.i().runOnMainThread(this.f);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "TowerBenchmarkSim main");
        thread.setDaemon(true);
        thread.start();
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final boolean isRunning() {
        return this.c.get();
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final boolean isReadyToStart() {
        return !isRunning();
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final boolean isSuccessful() {
        return this.d == null;
    }

    @Override // com.prineside.tdi2.utils.simulation.Simulation
    public final void stop() {
        this.c.set(false);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/TowerBenchmarkSim$TowerBenchmarkXpConfig.class */
    public static final class TowerBenchmarkXpConfig {
        public int xpLevel;
        public int[] abilities;
        public String name;

        public TowerBenchmarkXpConfig(String str, int i, int[] iArr) {
            this.name = str;
            this.xpLevel = i;
            this.abilities = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/TowerBenchmarkSim$TowerBenchmarkResults.class */
    public static final class TowerBenchmarkResults {
        public String name;
        public int waveCount;
        public String extraTowers;
        public String tower;
        public String aimStrategy;
        public String abilities;
        public int upgradeLevel;
        public int xpLevel;
        public int towerPrice;
        public int minTowerDPS;
        public int maxTowerDPS;
        public int avgTowerDPS;
        public long mdps;
        public int minTowerKills;
        public int maxTowerKills;
        public int avgTowerKills;
        public int totalKills;
        public int ups;
        public double totalDamage;
        public int sumTowersPrice;

        public TowerBenchmarkResults(String str) {
            this.name = str;
            String[] split = str.split("\\|");
            this.tower = split[0];
            this.upgradeLevel = Integer.parseInt(split[1]);
            this.aimStrategy = split[2];
            this.xpLevel = Integer.parseInt(split[3]);
            this.abilities = split[4];
            this.waveCount = Integer.parseInt(split[5]);
            this.extraTowers = split[6];
        }

        public final void add(TowerBenchmarkResults towerBenchmarkResults) {
            this.minTowerDPS += towerBenchmarkResults.minTowerDPS;
            this.maxTowerDPS += towerBenchmarkResults.maxTowerDPS;
            this.avgTowerDPS += towerBenchmarkResults.avgTowerDPS;
            this.mdps += towerBenchmarkResults.mdps;
            this.minTowerKills += towerBenchmarkResults.minTowerKills;
            this.maxTowerKills += towerBenchmarkResults.maxTowerKills;
            this.avgTowerKills += towerBenchmarkResults.avgTowerKills;
            this.totalKills += towerBenchmarkResults.totalKills;
            this.ups += towerBenchmarkResults.ups;
            this.totalDamage += towerBenchmarkResults.totalDamage;
            this.sumTowersPrice += towerBenchmarkResults.sumTowersPrice;
        }

        public final void divideValues(int i) {
            float f = 1.0f / i;
            this.minTowerDPS = (int) (this.minTowerDPS * f);
            this.maxTowerDPS = (int) (this.maxTowerDPS * f);
            this.avgTowerDPS = (int) (this.avgTowerDPS * f);
            this.mdps = ((float) this.mdps) * f;
            this.minTowerKills = (int) (this.minTowerKills * f);
            this.maxTowerKills = (int) (this.maxTowerKills * f);
            this.avgTowerKills = (int) (this.avgTowerKills * f);
            this.totalKills = (int) (this.totalKills * f);
            this.ups = (int) (this.ups * f);
            this.totalDamage *= f;
            this.sumTowersPrice = (int) (this.sumTowersPrice * f);
        }

        public final void toJson(Json json) {
            json.writeValue(this.name);
            json.writeValue(Integer.valueOf(this.waveCount));
            json.writeValue(this.extraTowers);
            json.writeValue(this.tower);
            json.writeValue(this.aimStrategy);
            json.writeValue(this.abilities);
            json.writeValue(Integer.valueOf(this.upgradeLevel));
            json.writeValue(Integer.valueOf(this.xpLevel));
            json.writeValue(Integer.valueOf(this.towerPrice));
            json.writeValue(Integer.valueOf(this.minTowerDPS));
            json.writeValue(Integer.valueOf(this.maxTowerDPS));
            json.writeValue(Integer.valueOf(this.avgTowerDPS));
            json.writeValue(Long.valueOf(this.mdps));
            json.writeValue(Integer.valueOf(this.minTowerKills));
            json.writeValue(Integer.valueOf(this.maxTowerKills));
            json.writeValue(Integer.valueOf(this.avgTowerKills));
            json.writeValue(Integer.valueOf(this.totalKills));
            json.writeValue(Integer.valueOf(this.ups));
            json.writeValue(Double.valueOf(this.totalDamage));
            json.writeValue(Integer.valueOf(this.sumTowersPrice));
        }

        public static void writeFieldsToJson(Json json) {
            json.writeValue(Attribute.NAME_ATTR);
            json.writeValue("waves");
            json.writeValue("extraTowers");
            json.writeValue("tower");
            json.writeValue("aimStrategy");
            json.writeValue("abilities");
            json.writeValue("upgradeLevel");
            json.writeValue("xpLevel");
            json.writeValue("towerPrice");
            json.writeValue("minTowerDPS");
            json.writeValue("maxTowerDPS");
            json.writeValue("avgTowerDPS");
            json.writeValue("mdps");
            json.writeValue("minTowerKills");
            json.writeValue("maxTowerKills");
            json.writeValue("avgTowerKills");
            json.writeValue("totalKills");
            json.writeValue("ups");
            json.writeValue("totalDamage");
            json.writeValue("sumTowersPrice");
        }

        public final String toString() {
            return "Tower: " + this.tower + ", aimStrategy: " + this.aimStrategy + ", abilities: " + this.abilities + ", upgradeLevel: " + this.upgradeLevel + ", xpLevel: " + this.xpLevel + ", tower price: " + this.towerPrice;
        }
    }
}
