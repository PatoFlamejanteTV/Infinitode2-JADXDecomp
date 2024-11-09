package com.prineside.tdi2.systems;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.BestReplayLoadFromServer;
import com.prineside.tdi2.events.game.CoinsChange;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.events.game.EnemyReachTarget;
import com.prineside.tdi2.events.game.EnemyTakeDamage;
import com.prineside.tdi2.events.game.GameOver;
import com.prineside.tdi2.events.game.MinedResourcesChange;
import com.prineside.tdi2.events.game.MinerBuild;
import com.prineside.tdi2.events.game.MinerUpgrade;
import com.prineside.tdi2.events.game.NextWaveForce;
import com.prineside.tdi2.events.game.ScoreChange;
import com.prineside.tdi2.events.game.TowerBuild;
import com.prineside.tdi2.events.game.TowerSell;
import com.prineside.tdi2.events.game.TowerUpgrade;
import com.prineside.tdi2.events.game.WaveComplete;
import com.prineside.tdi2.managers.ReplayManager;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.tiles.TargetTile;
import com.prineside.tdi2.utils.EnumKeyArray;
import com.prineside.tdi2.utils.MovingAverageFloat;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StatisticsSystem.class */
public final class StatisticsSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3063a = TLog.forClass(StatisticsSystem.class);
    public static final float AVERAGES_COUNT_INTERVAL = 3.0f;
    public static final int AVERAGES_COUNT_BUFFER_SIZE = 20;
    private float c;
    private float d;
    private float e;
    private float f;
    public int replayChartFrameCounter;

    @NAGS
    private ReplayManager.ReplayRecord k;

    @NAGS
    private boolean l;
    private boolean m;
    private float n;

    @NAGS
    private boolean o;

    /* renamed from: b, reason: collision with root package name */
    @EnumKeyArray(enumerator = StatisticsType.class)
    private double[] f3064b = new double[StatisticsType.values.length];
    private MovingAverageFloat g = new MovingAverageFloat(20);
    private MovingAverageFloat h = new MovingAverageFloat(20);
    private MovingAverageFloat i = new MovingAverageFloat(20);
    private ReplayManager.ReplayRecord.ChartFrames j = new ReplayManager.ReplayRecord.ChartFrames();

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f3064b);
        output.writeFloat(this.c);
        output.writeFloat(this.d);
        output.writeFloat(this.e);
        output.writeFloat(this.f);
        kryo.writeObject(output, this.g);
        kryo.writeObject(output, this.h);
        kryo.writeObject(output, this.i);
        kryo.writeObject(output, this.j);
        output.writeVarInt(this.replayChartFrameCounter, true);
        output.writeBoolean(this.m);
        output.writeFloat(this.n);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f3064b = (double[]) kryo.readObject(input, double[].class);
        this.c = input.readFloat();
        this.d = input.readFloat();
        this.e = input.readFloat();
        this.f = input.readFloat();
        this.g = (MovingAverageFloat) kryo.readObject(input, MovingAverageFloat.class);
        this.h = (MovingAverageFloat) kryo.readObject(input, MovingAverageFloat.class);
        this.i = (MovingAverageFloat) kryo.readObject(input, MovingAverageFloat.class);
        this.j = (ReplayManager.ReplayRecord.ChartFrames) kryo.readObject(input, ReplayManager.ReplayRecord.ChartFrames.class);
        this.replayChartFrameCounter = input.readVarInt(true);
        this.m = input.readBoolean();
        this.n = input.readFloat();
    }

    public final float getAverageCoinsPerMinute() {
        return this.g.getAverage() * 20.0f;
    }

    public final float getAverageScorePerMinute() {
        return this.h.getAverage() * 20.0f;
    }

    public final float getAverageKillsPerMinute() {
        return this.i.getAverage() * 20.0f;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.S.events.getListeners(WaveComplete.class).addStateAffecting(new OnWaveComplete(this, (byte) 0));
        this.S.events.getListeners(NextWaveForce.class).addStateAffecting(new OnNextWaveForce(this, (byte) 0));
        this.S.events.getListeners(GameOver.class).addStateAffecting(new OnGameOver(this, (byte) 0));
        this.S.events.getListeners(MinerBuild.class).addStateAffecting(new OnMinerBuild(this, (byte) 0));
        this.S.events.getListeners(MinerUpgrade.class).addStateAffecting(new OnMinerUpgrade(this, (byte) 0));
        this.S.events.getListeners(CoinsChange.class).addStateAffecting(new OnCoinsChange(this, (byte) 0));
        this.S.events.getListeners(ScoreChange.class).addStateAffecting(new OnScoreChange(this, (byte) 0));
        this.S.events.getListeners(TowerBuild.class).addStateAffecting(new OnTowerBuild(this, (byte) 0));
        this.S.events.getListeners(TowerSell.class).addStateAffecting(new OnTowerSell(this, (byte) 0));
        this.S.events.getListeners(TowerUpgrade.class).addStateAffecting(new OnTowerUpgrade(this, (byte) 0));
        this.S.events.getListeners(MinedResourcesChange.class).addStateAffecting(new OnMinedResourcesChange(this, (byte) 0));
        this.S.events.getListeners(EnemyTakeDamage.class).addStateAffecting(new OnEnemyTakeDamage(this));
        this.S.events.getListeners(EnemyDie.class).addStateAffecting(new OnEnemyDie(this));
        this.S.events.getListeners(EnemyReachTarget.class).addStateAffecting(new OnEnemyReachTarget(this.S));
    }

    public final ReplayManager.ReplayRecord.ChartFrames getCurrentReplayChart() {
        return this.j;
    }

    public final ReplayManager.ReplayRecord.ChartFrames.FrameValues getCurrentReplayChartValues() {
        return ReplayManager.ReplayRecord.ChartFrames.generateFrameValues(this.S);
    }

    @Null
    public final ReplayManager.ReplayRecord getBestReplay() {
        if (!this.o) {
            this.o = true;
            if (this.S.gameState.gameMode == GameStateSystem.GameMode.BASIC_LEVELS && this.S.gameState.difficultyMode == DifficultyMode.NORMAL) {
                Game.i.replayManager.loadAndStoreBestReplayFromServer(this.S.gameState.basicLevelName, replayRecord -> {
                    if (replayRecord != null) {
                        if (this.k != null && replayRecord.id.equals(this.k.id)) {
                            return;
                        }
                        Threads.i().runOnMainThread(() -> {
                            f3063a.i("loaded best replay from server: " + replayRecord.id, new Object[0]);
                            this.k = replayRecord;
                            this.S.events.trigger(new BestReplayLoadFromServer());
                        });
                    }
                });
            }
        }
        if (this.k != null) {
            return this.k;
        }
        if (this.l) {
            return null;
        }
        String str = "";
        if (GameStateSystem.GameMode.isBasicLevel(this.S.gameState.gameMode)) {
            str = this.S.gameState.basicLevelName;
        } else if (this.S.gameState.gameMode == GameStateSystem.GameMode.USER_MAPS) {
            str = this.S.gameState.userMapId;
        }
        Array<String> allRecordIds = Game.i.replayManager.getAllRecordIds();
        ReplayManager.ReplayRecord replayRecord2 = null;
        for (int i = 0; i < allRecordIds.size; i++) {
            try {
                ReplayManager.ReplayRecord record = Game.i.replayManager.getRecord(allRecordIds.items[i]);
                if (record != null && record.gameMode == this.S.gameState.gameMode && record.difficultyMode == this.S.gameState.difficultyMode && record.chartFrames.version == 2 && str.equals(record.levelName) && (replayRecord2 == null || replayRecord2.score < record.score)) {
                    replayRecord2 = record;
                }
            } catch (Exception e) {
                f3063a.e("failed to get best replay", e);
            }
        }
        if (replayRecord2 == null) {
            this.l = true;
            return null;
        }
        this.k = replayRecord2;
        return replayRecord2;
    }

    @Deprecated
    public final void setStatistic(StatisticsType statisticsType, double d) {
        if (this.m) {
            throw new IllegalStateException("Statistics are already flushed");
        }
        this.f3064b[statisticsType.ordinal()] = d;
    }

    public final double getStatistic(StatisticsType statisticsType) {
        return this.f3064b[statisticsType.ordinal()];
    }

    public final void addStatistic(StatisticsType statisticsType, double d) {
        if (this.m) {
            throw new IllegalStateException("Statistics are already flushed");
        }
        double[] dArr = this.f3064b;
        int ordinal = statisticsType.ordinal();
        dArr[ordinal] = dArr[ordinal] + d;
        switch (statisticsType) {
            case CG:
                this.d = (float) (this.d + d);
                return;
            case SG:
                this.e = (float) (this.e + d);
                return;
            case EK:
                this.f = (float) (this.f + d);
                return;
            default:
                return;
        }
    }

    public final double getCurrentGameStatistic(StatisticsType statisticsType) {
        return this.f3064b[statisticsType.ordinal()];
    }

    public final double[] getCurrentGameStatistics() {
        return this.f3064b;
    }

    public final void flushStatistics() {
        BasicLevel level;
        if (this.m) {
            throw new IllegalStateException("Statistics are already flushed");
        }
        if (this.S.gameState.basicLevelName != null && (level = Game.i.basicLevelManager.getLevel(this.S.gameState.basicLevelName)) != null && level.notAffectsStatistics) {
            f3063a.i("statistics flush skipped - disabled by level configuration", new Object[0]);
            return;
        }
        for (StatisticsType statisticsType : StatisticsType.values) {
            Game.i.statisticsManager.registerDelta(statisticsType, this.f3064b[statisticsType.ordinal()]);
            Game.i.statisticsManager.registerMaxOneGame(statisticsType, this.f3064b[statisticsType.ordinal()]);
        }
        this.m = true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        float f2;
        if (this.S.gameState.isGameRealTimePasses()) {
            this.n += f;
            if (this.n > 1.0f) {
                addStatistic(StatisticsType.PT, 1.0d);
                if (this.S.loot.canGiveChests()) {
                    addStatistic(StatisticsType.PTCL, 1.0d);
                }
                if (DifficultyMode.isEndless(this.S.gameState.difficultyMode)) {
                    if (this.S.gameState.averageDifficulty <= 100) {
                        f2 = (float) StrictMath.pow(this.S.gameState.averageDifficulty / 100.0f, 1.75d);
                    } else {
                        f2 = 1.0f + (((this.S.gameState.averageDifficulty - 100.0f) / 400.0f) * 0.75f);
                    }
                    addStatistic(StatisticsType.PTEMWD, f2);
                }
                this.n -= 1.0f;
            }
            this.c += f;
            if (this.c > 3.0f) {
                this.g.push(this.d);
                this.h.push(this.e);
                this.i.push(this.f);
                this.d = 0.0f;
                this.e = 0.0f;
                this.f = 0.0f;
                this.c -= 3.0f;
            }
            this.replayChartFrameCounter++;
            if (this.replayChartFrameCounter % Config.REPLAY_CHARTS_INTERVAL == 0) {
                this.j.addFrame(this.S);
            }
            if (Game.i.debugManager != null && Game.i.debugManager.isEnabled()) {
                Game.i.debugManager.registerValue("Stats coin/score/kill PM").append(MathUtils.round(getAverageCoinsPerMinute())).append(" / ").append(MathUtils.round(getAverageScorePerMinute())).append(" / ").append(MathUtils.round(getAverageKillsPerMinute()));
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Statistics";
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        super.dispose();
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StatisticsSystem$OnMinerBuild.class */
    public static class OnMinerBuild extends SerializableListener<StatisticsSystem> implements Listener<MinerBuild> {
        /* synthetic */ OnMinerBuild(StatisticsSystem statisticsSystem, byte b2) {
            this(statisticsSystem);
        }

        private OnMinerBuild() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnMinerBuild(StatisticsSystem statisticsSystem) {
            this.f1759a = statisticsSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(MinerBuild minerBuild) {
            Miner miner = minerBuild.getMiner();
            int buildPrice = minerBuild.getBuildPrice();
            ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.MB, 1.0d);
            ((StatisticsSystem) this.f1759a).addStatistic(Game.i.minerManager.getBuiltStatisticType(miner.type), 1.0d);
            ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.MMS, buildPrice);
            ((StatisticsSystem) this.f1759a).addStatistic(Game.i.minerManager.getMoneySpentStatisticType(miner.type), buildPrice);
            if (((StatisticsSystem) this.f1759a).getStatistic(StatisticsType.MBS) < ((StatisticsSystem) this.f1759a).S.miner.miners.size) {
                ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.MBS, ((StatisticsSystem) this.f1759a).S.miner.miners.size - ((StatisticsSystem) this.f1759a).getStatistic(StatisticsType.MBS));
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StatisticsSystem$OnMinerUpgrade.class */
    public static class OnMinerUpgrade extends SerializableListener<StatisticsSystem> implements Listener<MinerUpgrade> {
        /* synthetic */ OnMinerUpgrade(StatisticsSystem statisticsSystem, byte b2) {
            this(statisticsSystem);
        }

        private OnMinerUpgrade() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnMinerUpgrade(StatisticsSystem statisticsSystem) {
            this.f1759a = statisticsSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(MinerUpgrade minerUpgrade) {
            Miner miner = minerUpgrade.getMiner();
            int upgradePrice = minerUpgrade.getUpgradePrice();
            ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.MU, 1.0d);
            ((StatisticsSystem) this.f1759a).addStatistic(Game.i.minerManager.getUpgradedStatisticType(miner.type), 1.0d);
            ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.MMS, upgradePrice);
            ((StatisticsSystem) this.f1759a).addStatistic(Game.i.minerManager.getMoneySpentStatisticType(miner.type), upgradePrice);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StatisticsSystem$OnTowerBuild.class */
    public static final class OnTowerBuild extends SerializableListener<StatisticsSystem> implements Listener<TowerBuild> {
        /* synthetic */ OnTowerBuild(StatisticsSystem statisticsSystem, byte b2) {
            this(statisticsSystem);
        }

        private OnTowerBuild() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnTowerBuild(StatisticsSystem statisticsSystem) {
            this.f1759a = statisticsSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(TowerBuild towerBuild) {
            Tower tower = towerBuild.getTower();
            int price = towerBuild.getPrice();
            ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.TB, 1.0d);
            ((StatisticsSystem) this.f1759a).addStatistic(Game.i.towerManager.getBuiltStatisticType(tower.type), 1.0d);
            ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.TMS, price);
            ((StatisticsSystem) this.f1759a).addStatistic(Game.i.towerManager.getMoneySpentStatisticType(tower.type), price);
            if (((StatisticsSystem) this.f1759a).getStatistic(StatisticsType.TBS) < ((StatisticsSystem) this.f1759a).S.tower.towers.size) {
                ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.TBS, ((StatisticsSystem) this.f1759a).S.tower.towers.size - ((StatisticsSystem) this.f1759a).S.statistics.getStatistic(StatisticsType.TBS));
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StatisticsSystem$OnTowerSell.class */
    public static final class OnTowerSell extends SerializableListener<StatisticsSystem> implements Listener<TowerSell> {
        /* synthetic */ OnTowerSell(StatisticsSystem statisticsSystem, byte b2) {
            this(statisticsSystem);
        }

        private OnTowerSell() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnTowerSell(StatisticsSystem statisticsSystem) {
            this.f1759a = statisticsSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(TowerSell towerSell) {
            Tower tower = towerSell.getTower();
            int returnedCoins = towerSell.getReturnedCoins();
            if (!tower.isSellFullRefundStillActive()) {
                ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.TS, 1.0d);
                ((StatisticsSystem) this.f1759a).addStatistic(Game.i.towerManager.getSoldStatisticType(tower.type), 1.0d);
                ((StatisticsSystem) this.f1759a).addStatistic(Game.i.towerManager.getMoneySpentStatisticType(tower.type), -returnedCoins);
            } else {
                ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.TB, -1.0d);
                ((StatisticsSystem) this.f1759a).addStatistic(Game.i.towerManager.getBuiltStatisticType(tower.type), -1.0d);
                ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.TMS, -returnedCoins);
                ((StatisticsSystem) this.f1759a).addStatistic(Game.i.towerManager.getMoneySpentStatisticType(tower.type), -returnedCoins);
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StatisticsSystem$OnTowerUpgrade.class */
    public static final class OnTowerUpgrade extends SerializableListener<StatisticsSystem> implements Listener<TowerUpgrade> {
        /* synthetic */ OnTowerUpgrade(StatisticsSystem statisticsSystem, byte b2) {
            this(statisticsSystem);
        }

        private OnTowerUpgrade() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnTowerUpgrade(StatisticsSystem statisticsSystem) {
            this.f1759a = statisticsSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(TowerUpgrade towerUpgrade) {
            Tower tower = towerUpgrade.getTower();
            int price = towerUpgrade.getPrice();
            ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.TMS, price);
            ((StatisticsSystem) this.f1759a).addStatistic(Game.i.towerManager.getMoneySpentStatisticType(tower.type), price);
            ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.TU, 1.0d);
            ((StatisticsSystem) this.f1759a).addStatistic(Game.i.towerManager.getUpgradedStatisticType(tower.type), 1.0d);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StatisticsSystem$OnGameOver.class */
    public static class OnGameOver extends SerializableListener<StatisticsSystem> implements Listener<GameOver> {
        /* synthetic */ OnGameOver(StatisticsSystem statisticsSystem, byte b2) {
            this(statisticsSystem);
        }

        private OnGameOver() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnGameOver(StatisticsSystem statisticsSystem) {
            this.f1759a = statisticsSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(GameOver gameOver) {
            ((StatisticsSystem) this.f1759a).j.addFrame(((StatisticsSystem) this.f1759a).S);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StatisticsSystem$OnCoinsChange.class */
    public static class OnCoinsChange extends SerializableListener<StatisticsSystem> implements Listener<CoinsChange> {
        /* synthetic */ OnCoinsChange(StatisticsSystem statisticsSystem, byte b2) {
            this(statisticsSystem);
        }

        private OnCoinsChange() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnCoinsChange(StatisticsSystem statisticsSystem) {
            this.f1759a = statisticsSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(CoinsChange coinsChange) {
            if (coinsChange.isGained() && ((StatisticsSystem) this.f1759a).S.gameState.getMoney() > coinsChange.getOldValue()) {
                ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.CG, ((StatisticsSystem) this.f1759a).S.gameState.getMoney() - coinsChange.getOldValue());
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StatisticsSystem$OnScoreChange.class */
    public static class OnScoreChange extends SerializableListener<StatisticsSystem> implements Listener<ScoreChange> {
        /* synthetic */ OnScoreChange(StatisticsSystem statisticsSystem, byte b2) {
            this(statisticsSystem);
        }

        private OnScoreChange() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnScoreChange(StatisticsSystem statisticsSystem) {
            this.f1759a = statisticsSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(ScoreChange scoreChange) {
            if (scoreChange.isGained() && ((StatisticsSystem) this.f1759a).S.gameState.getScore() > scoreChange.getOldValue()) {
                long score = ((StatisticsSystem) this.f1759a).S.gameState.getScore() - scoreChange.getOldValue();
                ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.SG, score);
                if (scoreChange.getReason() != null) {
                    ((StatisticsSystem) this.f1759a).addStatistic(scoreChange.getReason(), score);
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StatisticsSystem$OnMinedResourcesChange.class */
    public static class OnMinedResourcesChange extends SerializableListener<StatisticsSystem> implements Listener<MinedResourcesChange> {
        /* synthetic */ OnMinedResourcesChange(StatisticsSystem statisticsSystem, byte b2) {
            this(statisticsSystem);
        }

        private OnMinedResourcesChange() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnMinedResourcesChange(StatisticsSystem statisticsSystem) {
            this.f1759a = statisticsSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(MinedResourcesChange minedResourcesChange) {
            if (minedResourcesChange.isGained() && ((StatisticsSystem) this.f1759a).S.gameState.getResources(minedResourcesChange.getType()) > minedResourcesChange.getOldValue()) {
                int resources = ((StatisticsSystem) this.f1759a).S.gameState.getResources(minedResourcesChange.getType()) - minedResourcesChange.getOldValue();
                ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.RG, resources);
                ((StatisticsSystem) this.f1759a).addStatistic(Game.i.resourceManager.getGainedCountStatistic(minedResourcesChange.getType()), resources);
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StatisticsSystem$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<StatisticsSystem> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(StatisticsSystem statisticsSystem) {
            this.f1759a = statisticsSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            DamageRecord lastDamage = enemyDie.getLastDamage();
            Tower tower = lastDamage.getTower();
            Ability ability = lastDamage.getAbility();
            DamageType damageType = lastDamage.getDamageType();
            ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.EK, 1.0d);
            if (tower != null) {
                ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.TEK, 1.0d);
                ((StatisticsSystem) this.f1759a).addStatistic(Game.i.towerManager.getEnemiesKilledStatisticsType(tower.type), 1.0d);
            }
            if (ability != null) {
                ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.KEW_A, 1.0d);
            }
            switch (damageType) {
                case BULLET:
                    ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.KEW_B, 1.0d);
                    return;
                case FIRE:
                    ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.KEW_F, 1.0d);
                    return;
                case POISON:
                    ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.KEW_P, 1.0d);
                    return;
                case EXPLOSION:
                    ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.KEW_E, 1.0d);
                    return;
                case ELECTRICITY:
                    ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.KEW_EL, 1.0d);
                    return;
                case LASER:
                    ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.KEW_L, 1.0d);
                    return;
                default:
                    return;
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StatisticsSystem$OnEnemyTakeDamage.class */
    public static final class OnEnemyTakeDamage extends SerializableListener<StatisticsSystem> implements Listener<EnemyTakeDamage> {
        private OnEnemyTakeDamage() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyTakeDamage(StatisticsSystem statisticsSystem) {
            this.f1759a = statisticsSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyTakeDamage enemyTakeDamage) {
            DamageRecord record = enemyTakeDamage.getRecord();
            Tower tower = record.getTower();
            float factDamage = record.getFactDamage();
            if (tower != null) {
                if (record.isCleanForDps()) {
                    ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.TDD, factDamage);
                    ((StatisticsSystem) this.f1759a).addStatistic(Game.i.towerManager.getDamageDealtStatisticType(tower.type), factDamage);
                } else {
                    ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.TDDNC, factDamage);
                }
            }
            ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.SDD, factDamage);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StatisticsSystem$OnEnemyReachTarget.class */
    public static final class OnEnemyReachTarget implements KryoSerializable, Listener<EnemyReachTarget> {

        /* renamed from: a, reason: collision with root package name */
        private GameSystemProvider f3067a;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f3067a);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f3067a = (GameSystemProvider) kryo.readObject(input, GameSystemProvider.class);
        }

        private OnEnemyReachTarget() {
        }

        public OnEnemyReachTarget(GameSystemProvider gameSystemProvider) {
            this.f3067a = gameSystemProvider;
        }

        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyReachTarget enemyReachTarget) {
            if (enemyReachTarget.getEnemy().getCurrentTile() instanceof TargetTile) {
                this.f3067a.statistics.addStatistic(StatisticsType.EP, 1.0d);
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StatisticsSystem$OnWaveComplete.class */
    public static final class OnWaveComplete extends SerializableListener<StatisticsSystem> implements Listener<WaveComplete> {
        /* synthetic */ OnWaveComplete(StatisticsSystem statisticsSystem, byte b2) {
            this(statisticsSystem);
        }

        private OnWaveComplete() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnWaveComplete(StatisticsSystem statisticsSystem) {
            this.f1759a = statisticsSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(WaveComplete waveComplete) {
            ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.WD, 1.0d);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StatisticsSystem$OnNextWaveForce.class */
    public static final class OnNextWaveForce extends SerializableListener<StatisticsSystem> implements Listener<NextWaveForce> {
        /* synthetic */ OnNextWaveForce(StatisticsSystem statisticsSystem, byte b2) {
            this(statisticsSystem);
        }

        private OnNextWaveForce() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnNextWaveForce(StatisticsSystem statisticsSystem) {
            this.f1759a = statisticsSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(NextWaveForce nextWaveForce) {
            ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.WC, 1.0d);
            if (nextWaveForce.getTime() > 0.0f) {
                ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.WCST, nextWaveForce.getTime());
            }
            ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.WCGC, nextWaveForce.getBonusMoney());
            ((StatisticsSystem) this.f1759a).addStatistic(StatisticsType.WCGS, nextWaveForce.getBonusScore());
        }
    }
}
