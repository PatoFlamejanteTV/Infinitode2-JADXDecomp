package com.prineside.tdi2.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.Timer;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.SpaceTileBonus;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.UserMap;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.SpaceTileBonusType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.EventListeners;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.BaseHealthChange;
import com.prineside.tdi2.events.game.CoinsChange;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.events.game.EnemyReachTarget;
import com.prineside.tdi2.events.game.GameOver;
import com.prineside.tdi2.events.game.GamePaused;
import com.prineside.tdi2.events.game.GameResumed;
import com.prineside.tdi2.events.game.GameSpeedChange;
import com.prineside.tdi2.events.game.GameStateTick;
import com.prineside.tdi2.events.game.IssuedItemsAdd;
import com.prineside.tdi2.events.game.MinedResourcesChange;
import com.prineside.tdi2.events.game.MinerResourceChange;
import com.prineside.tdi2.events.game.NextWaveForce;
import com.prineside.tdi2.events.game.ScoreChange;
import com.prineside.tdi2.items.StarItem;
import com.prineside.tdi2.managers.DailyQuestManager;
import com.prineside.tdi2.managers.NetworkManager;
import com.prineside.tdi2.managers.PreferencesManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.ReplayManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.screens.GameScreen;
import com.prineside.tdi2.systems.StateSystem;
import com.prineside.tdi2.systems.WaveSystem;
import com.prineside.tdi2.tiles.TargetTile;
import com.prineside.tdi2.ui.shared.AbilitySelectionOverlay;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.errorhandling.CrashHandler;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameStateSystem.class */
public final class GameStateSystem extends StateSystem {
    public static final float NOT_COMPLETED_DAILY_QUEST_PRIZE_MULTIPLIER = 0.1f;
    public static final float SLOW_MOTION_GAME_SPEED = 0.0667f;
    public DifficultyMode difficultyMode;
    public GameMode gameMode;
    public int modeDifficultyMultiplier;

    @NAGS
    public String replayId;

    @Null
    public String basicLevelName;

    @Null
    public String userMapId;
    public int userMapOriginalSeed;

    @NAGS
    public DailyQuestManager.DailyQuestLevel dailyQuestLevel;

    @NAGS
    public AbilitySelectionOverlay.SelectedAbilitiesConfiguration startingAbilitiesConfiguration;

    @NAGS
    public int continuedGameApproxStateHash;

    @NAGS
    public boolean gameIsContinued;
    public boolean canLootCases;
    public boolean lootBoostEnabled;
    public boolean rarityBoostEnabled;

    @NAGS
    public boolean gameSavesDisabled;
    public long scoreWithEndlessTimeLimit;
    private IssuedItems i;
    private RandomXS128 l;
    public int averageDifficulty;

    @NAGS
    public float playRealTime;

    @NAGS
    private long p;
    public EnemyType lastEnemyReachedTarget;

    @NAGS
    public GameOverReason gameOverReason;

    @NAGS
    public ProgressManager.ProgressSnapshotForState gameStartProgressSnapshot;
    private float u;
    private float v;
    private float w;

    @NAGS
    private byte[] z;

    @NAGS
    private int A;

    @NAGS
    private byte[] C;

    @NAGS
    private int D;

    @NAGS
    public ReplayManager.ReplayRecord headlessValidatedReplayRecord;

    @NAGS
    public int validationLastUpdateNumber;

    @NAGS
    public boolean validationFingerprintMismatchPrinted;

    @NAGS
    private float H;

    @NAGS
    private float I;

    @NAGS
    private float J;

    @NAGS
    private _LifecycleListener L;
    private static final TLog c = TLog.forClass(GameStateSystem.class);
    private static final Input F = new Input();
    public BossType[] allowedBossesForCustomMaps = null;
    private long d = -1;
    public long gameStartTimestamp = -1;

    @NAGS
    public boolean snapshotSavesEnabled = true;
    private long e = 0;
    private int f = 0;
    private int g = 0;
    private int[] h = new int[ResourceType.values.length];

    @NAGS
    private Array<String> j = new Array<>(String.class);

    @NAGS
    private Array<IssuedItems> k = new Array<>(IssuedItems.class);

    @NAGS
    private boolean m = false;

    @NAGS
    private float n = 1.0f;

    @NAGS
    private float o = 1.0f;
    private boolean q = false;
    private int r = 0;
    public int pxpExperience = 0;
    private int s = -13501;
    private int t = 0;

    @NAGS
    private final Output x = new Output(1024, -1);

    @NAGS
    private final Output y = new Output(1024, -1);

    @NAGS
    private Output B = new Output(1024, -1);

    @NAGS
    private final Object E = new Object();

    @NAGS
    public long validationStartTimestamp = -1;

    @NAGS
    private boolean G = false;

    @NAGS
    private final RandomXS128 K = new RandomXS128();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameStateSystem$ContinueGameStatus.class */
    public enum ContinueGameStatus {
        MAP_NOT_FOUND,
        MAP_CHANGED,
        GAME_VALUES_CHANGED,
        GAME_FROM_PREVIOUS_BUILD,
        OTHER_ERROR,
        SUCCESS;

        public static final ContinueGameStatus[] values = values();
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameStateSystem$GameOverReason.class */
    public enum GameOverReason {
        MANUAL,
        ZERO_BASE_HEALTH,
        NO_ENEMIES_LEFT,
        QUEST_FAILED
    }

    static /* synthetic */ float a(GameStateSystem gameStateSystem, float f) {
        float f2 = gameStateSystem.w + f;
        gameStateSystem.w = f2;
        return f2;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameStateSystem$GameMode.class */
    public enum GameMode {
        BASIC_LEVELS,
        USER_MAPS;

        public static GameMode[] values = values();

        public static boolean isBasicLevel(GameMode gameMode) {
            return gameMode == BASIC_LEVELS;
        }
    }

    @Override // com.prineside.tdi2.systems.StateSystem, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.difficultyMode);
        kryo.writeObject(output, this.gameMode);
        output.writeVarInt(this.modeDifficultyMultiplier, true);
        output.writeString(this.replayId);
        kryo.writeObjectOrNull(output, this.basicLevelName, String.class);
        kryo.writeObjectOrNull(output, this.userMapId, String.class);
        output.writeInt(this.userMapOriginalSeed);
        kryo.writeObjectOrNull(output, this.allowedBossesForCustomMaps, BossType[].class);
        kryo.writeObjectOrNull(output, this.dailyQuestLevel, DailyQuestManager.DailyQuestLevel.class);
        kryo.writeObjectOrNull(output, this.startingAbilitiesConfiguration, AbilitySelectionOverlay.SelectedAbilitiesConfiguration.class);
        output.writeLong(this.d);
        output.writeBoolean(this.canLootCases);
        output.writeBoolean(this.lootBoostEnabled);
        output.writeBoolean(this.rarityBoostEnabled);
        output.writeVarLong(this.e, true);
        output.writeVarInt(this.f, true);
        output.writeVarInt(this.g, true);
        output.writeVarLong(this.scoreWithEndlessTimeLimit, true);
        kryo.writeObject(output, this.h);
        kryo.writeObject(output, this.j);
        kryo.writeObjectOrNull(output, this.i, IssuedItems.class);
        kryo.writeObjectOrNull(output, this.l, RandomXS128.class);
        output.writeVarInt(this.averageDifficulty, true);
        output.writeFloat(this.playRealTime);
        kryo.writeObjectOrNull(output, this.lastEnemyReachedTarget, EnemyType.class);
        output.writeBoolean(this.q);
        kryo.writeObjectOrNull(output, this.gameOverReason, GameOverReason.class);
        kryo.writeObjectOrNull(output, this.gameStartProgressSnapshot, ProgressManager.ProgressSnapshotForState.class);
        output.writeVarInt(this.r, true);
        output.writeVarInt(this.pxpExperience, true);
        output.writeVarInt(this.s, true);
        output.writeVarInt(this.t, true);
        output.writeFloat(this.u);
        output.writeFloat(this.v);
        output.writeFloat(this.w);
    }

    @Override // com.prineside.tdi2.systems.StateSystem, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.difficultyMode = (DifficultyMode) kryo.readObject(input, DifficultyMode.class);
        this.gameMode = (GameMode) kryo.readObject(input, GameMode.class);
        this.modeDifficultyMultiplier = input.readVarInt(true);
        this.replayId = input.readString();
        this.basicLevelName = (String) kryo.readObjectOrNull(input, String.class);
        this.userMapId = (String) kryo.readObjectOrNull(input, String.class);
        this.userMapOriginalSeed = input.readInt();
        this.allowedBossesForCustomMaps = (BossType[]) kryo.readObjectOrNull(input, BossType[].class);
        this.dailyQuestLevel = (DailyQuestManager.DailyQuestLevel) kryo.readObjectOrNull(input, DailyQuestManager.DailyQuestLevel.class);
        this.startingAbilitiesConfiguration = (AbilitySelectionOverlay.SelectedAbilitiesConfiguration) kryo.readObjectOrNull(input, AbilitySelectionOverlay.SelectedAbilitiesConfiguration.class);
        this.d = input.readLong();
        this.canLootCases = input.readBoolean();
        this.lootBoostEnabled = input.readBoolean();
        this.rarityBoostEnabled = input.readBoolean();
        this.e = input.readVarLong(true);
        this.f = input.readVarInt(true);
        this.g = input.readVarInt(true);
        this.scoreWithEndlessTimeLimit = input.readVarLong(true);
        this.h = (int[]) kryo.readObject(input, int[].class);
        this.j = (Array) kryo.readObject(input, Array.class);
        this.i = (IssuedItems) kryo.readObjectOrNull(input, IssuedItems.class);
        this.l = (RandomXS128) kryo.readObjectOrNull(input, RandomXS128.class);
        this.averageDifficulty = input.readVarInt(true);
        this.playRealTime = input.readFloat();
        this.lastEnemyReachedTarget = (EnemyType) kryo.readObjectOrNull(input, EnemyType.class);
        this.q = input.readBoolean();
        this.gameOverReason = (GameOverReason) kryo.readObjectOrNull(input, GameOverReason.class);
        this.gameStartProgressSnapshot = (ProgressManager.ProgressSnapshotForState) kryo.readObjectOrNull(input, ProgressManager.ProgressSnapshotForState.class);
        this.r = input.readVarInt(true);
        this.pxpExperience = input.readVarInt(true);
        this.s = input.readVarInt(true);
        this.t = input.readVarInt(true);
        this.u = input.readFloat();
        this.v = input.readFloat();
        this.w = input.readFloat();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.replayId = "R-" + FastRandom.generateUniqueDistinguishableId();
        if (this.gameStartTimestamp == -1) {
            throw new IllegalStateException("Game start timestamp not set");
        }
        for (int i = 0; i < ResourceType.values.length; i++) {
            this.h[i] = 0;
        }
        if (this.gameStartProgressSnapshot == null) {
            this.gameStartProgressSnapshot = Game.i.progressManager.createProgressSnapshotForState();
        }
        this.S.events.getListeners(NextWaveForce.class).addStateAffecting(new OnNextWaveForce(this, (byte) 0)).setDescription("GameStateSystem - increases double speed bonus");
        this.S.events.getListeners(EnemyDie.class).addStateAffecting(new OnEnemyDie(this)).setDescription("GameStateSystem - removes base HP, gives kill bounty and triggers game over");
        this.S.events.getListeners(EnemyReachTarget.class).addStateAffectingWithPriority(new OnEnemyReachTarget(this.S), EventListeners.PRIORITY_VERY_LOW).setDescription("GameStateSystem - removes base HP and triggers game over");
        this.S.events.getListeners(MinerResourceChange.class).addStateAffecting(new OnMinerResourceChange(this.S, (byte) 0)).setDescription("GameStateSystem - adds a score for a mined resource");
        if (GameMode.isBasicLevel(this.gameMode)) {
            this.i = new IssuedItems(IssuedItems.IssueReason.GAME_OVER_BASIC_LEVEL, Game.getTimestampSeconds());
            this.i.gameOverBasicLevel = this.basicLevelName;
            this.i.basicLevelGameMode = this.gameMode;
            return;
        }
        if (this.gameMode == GameMode.USER_MAPS) {
            this.i = new IssuedItems(IssuedItems.IssueReason.GAME_OVER_USER_MAP, Game.getTimestampSeconds());
            this.i.userMapId = this.userMapId;
            this.i.userMapHash = Game.i.userMapManager.getUserMap(this.userMapId).map.generateSeed();
            return;
        }
        throw new IllegalStateException("Game mode " + this.gameMode.name() + " not implemented");
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postSetup() {
        this.n = 1.0f;
        addMoney(this.S.gameValue.getIntValue(GameValueType.STARTING_MONEY), false);
        addHealth(this.S.gameValue.getIntValue(GameValueType.STARTING_HEALTH));
    }

    public final float getDoubleSpeedTimeLeft() {
        return this.w;
    }

    public final boolean isDoubleSpeedActive() {
        return getDoubleSpeedTimeLeft() > 0.0f;
    }

    public final void setSeed(long j) {
        this.d = j;
        this.l = new RandomXS128(j);
    }

    public final boolean isDailyQuestAndNotCompleted() {
        return this.dailyQuestLevel != null && this.j.size == 0;
    }

    public final IssuedItems getGameLootIssuedItems() {
        return this.i;
    }

    public final long getSeed() {
        return this.d;
    }

    public final int randomInt(int i) {
        checkGameplayUpdateAllowed();
        return this.l.nextInt(i);
    }

    public final int randomIntIndependent(int i, long j) {
        this.K.setSeed(j);
        return this.K.nextInt(i);
    }

    public final float randomFloat() {
        checkGameplayUpdateAllowed();
        return this.l.nextFloat();
    }

    public final RandomXS128 getRandom() {
        checkGameplayUpdateAllowed();
        return this.l;
    }

    public final float randomTriangular() {
        checkGameplayUpdateAllowed();
        return this.l.nextFloat() - this.l.nextFloat();
    }

    public final long getRandomState(int i) {
        return this.l.getState(i);
    }

    public final int calculatePrizeGreenPapers() {
        long j;
        if (this.S.wave.wave == null) {
            return 0;
        }
        long score = getScore();
        int i = 1;
        while (true) {
            long j2 = 50000 * i;
            if (score <= j2) {
                break;
            }
            score = j2 + ((long) ((score - j2) * (1.0d - (0.025d * i))));
            i++;
        }
        float activeSecondsPlayed = this.S.loot.getActiveSecondsPlayed() + ((float) ((this.S.statistics.getCurrentGameStatistic(StatisticsType.WCST) * this.S.wave.getCompletedWavesCount()) / this.S.wave.wave.waveNumber));
        float f = 1.0f + ((activeSecondsPlayed / 60.0f) * 0.03f);
        long j3 = ((float) score) * 0.02f * this.averageDifficulty * 0.01f * 0.5f;
        int i2 = (int) (activeSecondsPlayed * f * 0.15f * 3.0f);
        if (this.gameMode == GameMode.USER_MAPS) {
            j = ((float) j3) * 0.6f;
            i2 = (int) (i2 * 1.2f);
        } else {
            j = ((float) j3) * 1.4f;
        }
        if (j > 2147483647L) {
            j = 2147483647L;
        }
        long difficultyModePrizeMultiplier = (long) (ProgressManager.getDifficultyModePrizeMultiplier(this.difficultyMode) * ((float) (j + i2)) * 0.85f * (this.S.gameValue.getPercentValueAsMultiplier(GameValueType.GREEN_PAPERS_BONUS) + 1.0d));
        if (difficultyModePrizeMultiplier > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) difficultyModePrizeMultiplier;
    }

    public final void restartGame(boolean z) {
        if (this.dailyQuestLevel != null) {
            Game.i.dailyQuestManager.startDailyLevel();
            return;
        }
        if (z) {
            GameScreen gameScreen = null;
            if (this.gameMode == GameMode.BASIC_LEVELS) {
                gameScreen = new GameScreen(Game.i.basicLevelManager.getLevel(this.basicLevelName), this.S.gameState.difficultyMode, this.S.gameState.modeDifficultyMultiplier, this.startingAbilitiesConfiguration, this.S.gameState.canLootCases, this.S.gameState.lootBoostEnabled, this.S.gameState.rarityBoostEnabled, this.gameStartTimestamp, null, this.S.loot.inventoryStatistics, this.dailyQuestLevel);
            } else if (this.gameMode == GameMode.USER_MAPS) {
                gameScreen = new GameScreen(Game.i.userMapManager.getUserMap(this.userMapId), this.S.gameState.difficultyMode, this.S.gameState.modeDifficultyMultiplier, this.startingAbilitiesConfiguration, this.gameStartTimestamp, this.allowedBossesForCustomMaps, null, this.S.loot.inventoryStatistics);
            }
            if (gameScreen != null) {
                deleteSavedGame();
                Game.i.screenManager.setScreen(gameScreen);
                return;
            }
            throw new RuntimeException("Not implemented for " + this.gameMode.name());
        }
        if (this.gameMode == GameMode.BASIC_LEVELS) {
            if (Game.i.basicLevelManager.getLevel(this.basicLevelName).getMap().getTargetTileOrThrow().isDisableAbilities() || !Game.i.abilityManager.isAnyAbilityOpened()) {
                Game.i.screenManager.startNewBasicLevel(Game.i.basicLevelManager.getLevel(this.basicLevelName), null);
                return;
            } else {
                AbilitySelectionOverlay.i().show(() -> {
                }, selectedAbilitiesConfiguration -> {
                    Game.i.screenManager.startNewBasicLevel(Game.i.basicLevelManager.getLevel(this.basicLevelName), selectedAbilitiesConfiguration);
                });
                return;
            }
        }
        if (this.gameMode == GameMode.USER_MAPS) {
            if (Game.i.userMapManager.getUserMap(this.userMapId).map.getTargetTileOrThrow().isDisableAbilities() || !Game.i.abilityManager.isAnyAbilityOpened()) {
                Game.i.screenManager.startNewUserLevel(Game.i.userMapManager.getUserMap(this.userMapId), null);
            } else {
                AbilitySelectionOverlay.i().show(() -> {
                }, selectedAbilitiesConfiguration2 -> {
                    Game.i.screenManager.startNewUserLevel(Game.i.userMapManager.getUserMap(this.userMapId), selectedAbilitiesConfiguration2);
                });
            }
        }
    }

    public final int getApproxStateHash() {
        int state = ((((((((31 + ((int) this.l.getState(0))) * 31) + ((int) this.l.getState(1))) * 31) + this.g) * 31) + ((int) this.e)) * 31) + this.f;
        for (int i = 0; i < this.S.map.spawnedEnemies.size; i++) {
            Enemy enemy = this.S.map.spawnedEnemies.items[i].enemy;
            if (enemy != null) {
                state = (((((((((((((state * 31) + enemy.type.ordinal()) * 31) + enemy.id) * 31) + enemy.sideShiftIndex) * 31) + ((int) (enemy.angle * 1000.0f))) * 31) + ((int) (enemy.passedTiles * 1000.0f))) * 31) + ((int) (enemy.getPosition().x * 1000.0f))) * 31) + ((int) (enemy.getPosition().y * 1000.0f));
            }
        }
        return state;
    }

    public final void setSnapshotSavesEnabled(boolean z) {
        this.snapshotSavesEnabled = z;
    }

    private void a(boolean z) {
        Output output = this.x;
        NetworkManager.KryoForState fullKryo = Game.i.networkManager.getFullKryo();
        if (this.z == null) {
            output.reset();
            fullKryo.writeObject(output, this.difficultyMode);
            output.writeLong(this.d);
            output.writeVarInt(this.modeDifficultyMultiplier, true);
            fullKryo.writeObject(output, this.gameMode);
            fullKryo.writeObjectOrNull(output, this.startingAbilitiesConfiguration, AbilitySelectionOverlay.SelectedAbilitiesConfiguration.class);
            output.writeBoolean(this.canLootCases);
            output.writeBoolean(this.lootBoostEnabled);
            output.writeBoolean(this.rarityBoostEnabled);
            if (GameMode.isBasicLevel(this.gameMode)) {
                output.writeString(this.basicLevelName);
            } else if (this.gameMode == GameMode.USER_MAPS) {
                output.writeString(this.userMapId);
                output.writeInt(this.userMapOriginalSeed);
                fullKryo.writeObjectOrNull(output, this.S.gameState.allowedBossesForCustomMaps, BossType[].class);
            }
            output.writeLong(this.gameStartTimestamp);
            fullKryo.writeObject(output, this.gameStartProgressSnapshot);
            fullKryo.writeObject(output, this.S.loot.inventoryStatistics);
            fullKryo.writeObjectOrNull(output, this.S.gameState.dailyQuestLevel, DailyQuestManager.DailyQuestLevel.class);
            this.z = output.toBytes();
        }
        output.reset();
        output.writeVarInt(208, true);
        output.writeBytes(this.z);
        output.writeFloat(this.playRealTime);
        output.writeVarInt(this.updateNumber, true);
        output.writeInt(getApproxStateHash());
        output.writeVarInt(this.f3062b.size, true);
        while (this.A < this.f3062b.size) {
            fullKryo.writeObject(this.B, this.f3062b.items[this.A]);
            this.A++;
        }
        output.writeBytes(this.B.getBuffer(), 0, this.B.position());
        if (z) {
            try {
                this.y.reset();
                this.S.serialize(this.y);
                output.write(this.y.getBuffer(), 0, this.y.position());
            } catch (Exception e) {
                c.e("failed to serialize state, forcing regular save", e);
                CrashHandler.report("failed to serialize state", e);
                setSnapshotSavesEnabled(false);
            }
        }
    }

    public final Output getStateBytes() {
        a(false);
        return this.x;
    }

    public final String getStateStr(boolean z) {
        a(z);
        return StringFormatter.toCompactBase64(this.x.getBuffer(), 0, this.x.position());
    }

    public final void saveGame() {
        if (this.S.CFG.headless || this.replayMode) {
            return;
        }
        if (this.gameSavesDisabled) {
            c.e("game saves disabled manually", new Object[0]);
            return;
        }
        if (this.S.gameState.isFastForwarding()) {
            c.e("game is fast-forwarding, save skipped", new Object[0]);
            return;
        }
        if (this.S.gameValue.getBooleanValue(GameValueType.GAME_SAVES) && !this.q && this.updateNumber != this.p) {
            long realTickCount = Game.getRealTickCount();
            this.p = this.updateNumber;
            a(this.snapshotSavesEnabled);
            if (this.C == null || this.C.length < this.x.position()) {
                this.C = new byte[MathUtils.nextPowerOfTwo(this.x.position())];
            }
            synchronized (this.E) {
                System.arraycopy(this.x.getBuffer(), 0, this.C, 0, this.x.position());
                this.D = this.x.position();
                c.i("state: " + this.x.position() + " bytes", new Object[0]);
            }
            Thread thread = new Thread(() -> {
                try {
                    FileHandle local = Gdx.files.local(PreferencesManager.getSavedGameFilePath());
                    synchronized (this.E) {
                        local.writeBytes(this.C, 0, this.D, false);
                    }
                } catch (Exception e) {
                    c.e("failed to save game state", e);
                }
            }, "SaveGame");
            thread.setDaemon(true);
            thread.start();
            CrashHandler.handleThreadExceptionsForgiving(thread);
            c.i("game saved in " + (((float) (Game.getRealTickCount() - realTickCount)) / 1000.0f) + "ms for update #" + this.updateNumber, new Object[0]);
        }
    }

    public static boolean savedGameExists() {
        FileHandle local = Gdx.files.local(PreferencesManager.getSavedGameFilePath());
        if (!local.exists()) {
            return false;
        }
        try {
            byte[] readBytes = local.readBytes();
            F.setPosition(0);
            F.setBuffer(readBytes);
            return Config.isCompatibleWithBuild(F.readVarInt(true));
        } catch (Exception e) {
            c.e("savedGameExists - parsing failed, cleared saved game", e);
            deleteSavedGame();
            return false;
        }
    }

    @Null
    public static ReplayManager.ReplayHeader getSavedGameInfo() {
        FileHandle local = Gdx.files.local(PreferencesManager.getSavedGameFilePath());
        if (!local.exists()) {
            return null;
        }
        try {
            byte[] readBytes = local.readBytes();
            Input input = F;
            input.setPosition(0);
            input.setBuffer(readBytes);
            ReplayManager.ReplayHeader fromBytes = ReplayManager.ReplayHeader.fromBytes(input);
            if (!Config.isCompatibleWithBuild(fromBytes.build)) {
                return null;
            }
            return fromBytes;
        } catch (Exception e) {
            c.e("savedGameExists - parsing failed, cleared saved game", e);
            deleteSavedGame();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v129, types: [int] */
    /* JADX WARN: Type inference failed for: r0v130, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v151, types: [com.prineside.tdi2.screens.GameScreen] */
    /* JADX WARN: Type inference failed for: r0v16, types: [boolean, java.lang.Exception] */
    public static ContinueGameStatus continueSavedGame() {
        ?? r0;
        if (savedGameExists()) {
            FileHandle local = Gdx.files.local(PreferencesManager.getSavedGameFilePath());
            try {
                Game.i.researchManager.updateAndValidateStarBranch();
                byte[] readBytes = local.readBytes();
                Input input = F;
                input.setPosition(0);
                input.setBuffer(readBytes);
                ReplayManager.ReplayHeader fromBytes = ReplayManager.ReplayHeader.fromBytes(input);
                ?? isCompatibleWithBuild = Config.isCompatibleWithBuild(fromBytes.build);
                if (isCompatibleWithBuild == 0) {
                    c.e("Game is from build " + fromBytes.build + ", not compatible with 208", new Object[0]);
                    return ContinueGameStatus.GAME_FROM_PREVIOUS_BUILD;
                }
                try {
                    GameSystemProvider unserialize = GameSystemProvider.unserialize(input);
                    c.i("restored GameSystemProvider", new Object[0]);
                    ProgressManager.ProgressSnapshotForState createProgressSnapshotForState = Game.i.progressManager.createProgressSnapshotForState();
                    if (!fromBytes.progressSnapshot.sameAs(createProgressSnapshotForState)) {
                        c.e("continueSavedGame - progress snapshots don't match", new Object[0]);
                        return ContinueGameStatus.GAME_VALUES_CHANGED;
                    }
                    c.i("game values are fine", new Object[0]);
                    unserialize.createAndSetupNonStateAffectingSystemsAfterDeserialization();
                    GameScreen gameScreen = new GameScreen(unserialize, fromBytes.gameStartTs);
                    Game.i.screenManager.setScreen(gameScreen);
                    boolean z = fromBytes.basicLevelName != null;
                    if (!Config.isHeadless() && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_SYNC_VALIDATION) != 0.0d && z) {
                        gameScreen.validationS = new GameSystemProvider(new GameSystemProvider.SystemsConfig(GameSystemProvider.SystemsConfig.Setup.GAME, true));
                        gameScreen.validationS.createSystems();
                        GameScreen.configureSystemsBeforeSetup(gameScreen.validationS, fromBytes.abilitiesConfiguration, fromBytes.canLootCases, fromBytes.lootBoostEnabled, fromBytes.rarityBoostEnabled, fromBytes.gameStartTs, Game.i.basicLevelManager.getLevel(fromBytes.basicLevelName), null, fromBytes.difficultyMode, fromBytes.modeDifficultyMultiplier, fromBytes.gameMode, null, createProgressSnapshotForState, fromBytes.inventoryStatistics, fromBytes.dailyQuestLevel);
                        gameScreen.validationS.setupSystems();
                        gameScreen.validationS.postSetupSystems();
                        for (int i = 0; i < fromBytes.actions.size; i++) {
                            StateSystem.ActionUpdatePair actionUpdatePair = fromBytes.actions.items[i];
                            gameScreen.validationS.gameState.pushAction(actionUpdatePair.action, actionUpdatePair.update);
                        }
                        unserialize.gameState.duplicateActionsTo = gameScreen.validationS.gameState;
                        while (true) {
                            r0 = gameScreen.validationS.gameState.updateNumber;
                            if (r0 != unserialize.gameState.updateNumber) {
                                gameScreen.validationS.gameState.f3061a.get(gameScreen.validationS.gameState.updateNumber);
                                gameScreen.validationS.updateSystems();
                            } else {
                                try {
                                    break;
                                } catch (Exception e) {
                                    r0.printStackTrace();
                                    c.e("Sync check - exception", e);
                                    CrashHandler.report("Sync check - failed on continue", e);
                                    gameScreen.validationS = null;
                                }
                            }
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        gameScreen.validationS.compareSync(unserialize, stringBuilder, false);
                        if (stringBuilder.length != 0) {
                            c.e(stringBuilder.toString(), new Object[0]);
                            if (!Config.isHeadless()) {
                                CrashHandler.report("Sync check - desync on continue\n" + ((Object) stringBuilder));
                                Notifications.i().add("Desynchronization spotted!", Game.i.assetManager.getDrawable("icon-exclamation-triangle"), MaterialColor.ORANGE.P800, StaticSoundType.WARNING);
                                r0 = gameScreen;
                                r0.validationS = null;
                            }
                        }
                        c.i("validation S synchronized", new Object[0]);
                        Notifications.i().add("Synchronization check enabled, thanks for helping us to make Infinitode 2 better!", Game.i.assetManager.getDrawable("icon-check"), MaterialColor.GREEN.P800, null);
                        Timer.schedule(new Timer.Task() { // from class: com.prineside.tdi2.systems.GameStateSystem.1
                            @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                            public void run() {
                                Notifications.i().add("It affects performance and can be turned off in Settings -> Advanced -> \"Desync check\".", Game.i.assetManager.getDrawable("icon-check"), MaterialColor.LIGHT_BLUE.P800, null);
                            }
                        }, 3.0f);
                    }
                    unserialize.gameState.p = unserialize.gameState.updateNumber;
                    return ContinueGameStatus.SUCCESS;
                } catch (Exception e2) {
                    isCompatibleWithBuild.printStackTrace();
                    c.e("failed to load the game from the snapshot, running full frame by frame loading", new Object[0]);
                    GameScreen gameScreen2 = null;
                    if (fromBytes.gameMode == GameMode.BASIC_LEVELS) {
                        BasicLevel level = Game.i.basicLevelManager.getLevel(fromBytes.basicLevelName);
                        gameScreen2 = new GameScreen(level, fromBytes.difficultyMode, fromBytes.modeDifficultyMultiplier, fromBytes.abilitiesConfiguration, fromBytes.canLootCases, fromBytes.lootBoostEnabled, fromBytes.rarityBoostEnabled, fromBytes.gameStartTs, null, fromBytes.inventoryStatistics, fromBytes.dailyQuestLevel);
                        if (level.dailyQuest) {
                            DailyQuestManager.DailyQuestLevel dailyQuestLevelCache = Game.i.dailyQuestManager.getDailyQuestLevelCache();
                            if (dailyQuestLevelCache.getLevelName().equals(level.name)) {
                                c.i("continue daily quest", new Object[0]);
                                gameScreen2.S.gameState.dailyQuestLevel = dailyQuestLevelCache;
                            }
                        }
                    } else if (fromBytes.gameMode == GameMode.USER_MAPS) {
                        UserMap userMap = Game.i.userMapManager.getUserMap(fromBytes.userMapId);
                        if (userMap != null) {
                            if (fromBytes.userMapSeed == userMap.map.generateSeed()) {
                                gameScreen2 = new GameScreen(userMap, fromBytes.difficultyMode, fromBytes.modeDifficultyMultiplier, fromBytes.abilitiesConfiguration, fromBytes.gameStartTs, fromBytes.customMapBossTypes, null, fromBytes.inventoryStatistics);
                            } else {
                                c.e("continueSavedGame - user map seeds differ", new Object[0]);
                                return ContinueGameStatus.MAP_CHANGED;
                            }
                        } else {
                            c.e("continueSavedGame - user map not exists", new Object[0]);
                            return ContinueGameStatus.MAP_NOT_FOUND;
                        }
                    }
                    if (gameScreen2 != null) {
                        GameStateSystem gameStateSystem = gameScreen2.S.gameState;
                        gameStateSystem.continuedGameApproxStateHash = fromBytes.continuedGameApproxStateHash;
                        if (!gameScreen2.S.gameState.gameStartProgressSnapshot.sameAs(Game.i.progressManager.createProgressSnapshotForState())) {
                            c.e("continueSavedGame - progress snapshots don't match", new Object[0]);
                            gameScreen2.dispose();
                            return ContinueGameStatus.GAME_VALUES_CHANGED;
                        }
                        if (gameStateSystem.getSeed() != fromBytes.seed) {
                            c.e("continueSavedGame - seeds don't match: " + gameStateSystem.getSeed() + SequenceUtils.SPACE + fromBytes.seed, new Object[0]);
                            gameScreen2.dispose();
                            return ContinueGameStatus.MAP_CHANGED;
                        }
                        gameStateSystem.gameIsContinued = true;
                        gameStateSystem.playRealTime = fromBytes.playRealTime;
                        for (int i2 = 0; i2 < fromBytes.actions.size; i2++) {
                            StateSystem.ActionUpdatePair actionUpdatePair2 = fromBytes.actions.items[i2];
                            gameStateSystem.pushAction(actionUpdatePair2.action, actionUpdatePair2.update);
                        }
                        gameStateSystem.startFastForwarding((int) fromBytes.updateNumber);
                        Game.i.screenManager.setScreen(gameScreen2);
                        gameStateSystem.p = gameStateSystem.updateNumber;
                        return ContinueGameStatus.SUCCESS;
                    }
                    c.e("screen is null", new Object[0]);
                    return ContinueGameStatus.OTHER_ERROR;
                }
            } catch (Exception e3) {
                c.e("continueSavedGame - parsing failed, cleared saved game", e3);
                return ContinueGameStatus.OTHER_ERROR;
            }
        }
        c.e("saved game not exists", new Object[0]);
        return ContinueGameStatus.OTHER_ERROR;
    }

    public static void startReplay(ReplayManager.ReplayRecord replayRecord) {
        startReplayAsRealRun(replayRecord, false);
    }

    public static void startReplayAsRealRun(ReplayManager.ReplayRecord replayRecord, boolean z) {
        if (!Config.isCompatibleWithBuild(replayRecord.build)) {
            Dialog.i().showAlert("Game is from build " + replayRecord.build + ", not compatible with 208");
            return;
        }
        try {
            byte[] stateBytes = replayRecord.getStateBytes();
            if (stateBytes != null) {
                Input input = F;
                input.setPosition(0);
                input.setBuffer(stateBytes);
                ReplayManager.ReplayHeader fromBytes = ReplayManager.ReplayHeader.fromBytes(input);
                GameScreen gameScreen = null;
                if (fromBytes.gameMode == GameMode.BASIC_LEVELS) {
                    BasicLevel level = Game.i.basicLevelManager.getLevel(fromBytes.basicLevelName);
                    if (level != null) {
                        gameScreen = new GameScreen(level, fromBytes.difficultyMode, fromBytes.modeDifficultyMultiplier, fromBytes.abilitiesConfiguration, fromBytes.canLootCases, fromBytes.lootBoostEnabled, fromBytes.rarityBoostEnabled, fromBytes.gameStartTs, fromBytes.progressSnapshot, fromBytes.inventoryStatistics, fromBytes.dailyQuestLevel);
                    } else {
                        Dialog.i().showAlert("Basic map not exists");
                    }
                } else if (fromBytes.gameMode == GameMode.USER_MAPS) {
                    UserMap userMap = Game.i.userMapManager.getUserMap(fromBytes.userMapId);
                    if (userMap != null) {
                        if (fromBytes.userMapSeed == userMap.map.generateSeed() || z) {
                            gameScreen = new GameScreen(userMap, fromBytes.difficultyMode, fromBytes.modeDifficultyMultiplier, fromBytes.abilitiesConfiguration, fromBytes.gameStartTs, fromBytes.customMapBossTypes, fromBytes.progressSnapshot, fromBytes.inventoryStatistics);
                        } else {
                            Dialog.i().showAlert("User map seeds differ");
                            return;
                        }
                    } else {
                        Dialog.i().showAlert("User map not exists");
                        return;
                    }
                }
                if (gameScreen != null) {
                    GameStateSystem gameStateSystem = gameScreen.S.gameState;
                    if (gameStateSystem.getSeed() != fromBytes.seed) {
                        Dialog.i().showAlert("Seeds don't match: " + gameStateSystem.getSeed() + SequenceUtils.SPACE + fromBytes.seed);
                        gameScreen.dispose();
                        return;
                    }
                    gameStateSystem.playRealTime = fromBytes.playRealTime;
                    for (int i = 0; i < fromBytes.actionsCount; i++) {
                        StateSystem.ActionUpdatePair actionUpdatePair = fromBytes.actions.get(i);
                        gameStateSystem.pushAction(actionUpdatePair.action, actionUpdatePair.update);
                    }
                    if (!z) {
                        gameStateSystem.replayMode = true;
                        gameStateSystem.replayRecord = replayRecord;
                        gameStateSystem.replayFrameCount = fromBytes.updateNumber;
                    }
                    c.i("successfully started the game", new Object[0]);
                    Game.i.screenManager.setScreen(gameScreen);
                    return;
                }
                Dialog.i().showAlert("Screen is null");
                return;
            }
            c.e("Replay has no state", new Object[0]);
            Dialog.i().showAlert("Replay has no state, only basic data");
        } catch (Exception e) {
            c.e("Parsing failed", e);
            Dialog.i().showAlert("Parsing failed");
        }
    }

    public static void deleteSavedGame() {
        if (Config.isHeadless()) {
            return;
        }
        try {
            if (Gdx.files.local(PreferencesManager.getSavedGameFilePath()).exists()) {
                Gdx.files.local(PreferencesManager.getSavedGameFilePath()).delete();
            }
        } catch (Exception e) {
            c.e("failed to delete saved game", e);
        }
    }

    public final void animateSpeed(float f, float f2) {
        this.G = true;
        this.I = f2;
        this.J = 0.0f;
        this.H = f;
    }

    public final float getNonAnimatedGameSpeed() {
        return this.n;
    }

    public final float getGameSpeed() {
        float f;
        if (this.G) {
            float f2 = this.J / this.I;
            float f3 = f2;
            if (f2 >= 1.0f) {
                this.G = false;
                f3 = 1.0f;
            }
            f = this.n + ((this.H - this.n) * (1.0f - f3));
        } else {
            f = this.n;
        }
        return Math.max(f, 0.0f);
    }

    public final void setGameSpeed(float f) {
        if (this.n != f) {
            this.G = false;
            this.n = f;
            this.S.events.trigger(new GameSpeedChange());
        }
    }

    public final void higherGameSpeed() {
        float f = this.n * 2.0f;
        if (!this.S.gameState.replayMode && f > 4.0f) {
            f = 4.0f;
        }
        if (f > 0.95f && f < 1.05f) {
            f = 1.0f;
        } else if (f > 1.95f && f < 2.05f) {
            f = 2.0f;
        } else if (f > 3.95f && f < 4.05f) {
            f = 4.0f;
        }
        setGameSpeed(f);
    }

    public final void lowerGameSpeed() {
        float f = this.n * 0.5f;
        float f2 = f;
        if (f > 0.95f && f2 < 1.05f) {
            f2 = 1.0f;
        } else if (f2 > 1.95f && f2 < 2.05f) {
            f2 = 2.0f;
        } else if (f2 > 3.95f && f2 < 4.05f) {
            f2 = 4.0f;
        }
        setGameSpeed(f2);
    }

    public final void switchGameSpeed() {
        float f;
        if (StrictMath.abs(this.n - 1.0f) < 0.1d) {
            f = 2.0f;
        } else if (StrictMath.abs(this.n - 2.0f) < 0.1d) {
            f = 4.0f;
        } else {
            f = 1.0f;
        }
        setGameSpeed(f);
    }

    public final boolean isGameRealTimePasses() {
        if (this.S.wave.status == WaveSystem.Status.NOT_STARTED) {
            return false;
        }
        if (this.difficultyMode == DifficultyMode.EASY && this.S.wave.status != WaveSystem.Status.SPAWNING && this.S.wave.getTimeToNextWave() <= 0.0f && !this.S.wave.allWavesSpawned()) {
            return false;
        }
        return true;
    }

    public final void setMoney(int i) {
        int i2 = this.g;
        this.g = i;
        this.S.events.trigger(new CoinsChange(i2, false));
    }

    public final int addMoney(float f, boolean z) {
        checkGameplayUpdateAllowed();
        int i = (int) f;
        float f2 = f - i;
        if (f2 > 0.001f && randomFloat() < f2) {
            i++;
        }
        if (i > 0) {
            int i2 = this.g;
            this.g = PMath.addWithoutOverflow(this.g, i);
            this.S.events.trigger(new CoinsChange(i2, z));
        }
        return i;
    }

    public final boolean removeMoney(int i) {
        if (i <= 0) {
            return true;
        }
        checkGameplayUpdateAllowed();
        if (this.g >= i) {
            int i2 = this.g;
            this.g -= i;
            this.S.events.trigger(new CoinsChange(i2, false));
            return true;
        }
        return false;
    }

    public final int getMoney() {
        return this.g;
    }

    public final void addCompletedQuestIssuedPrizes(IssuedItems issuedItems, float f, float f2, int i) {
        if (!issuedItems.addedToIssuedItemsArray) {
            this.k.add(issuedItems);
            issuedItems.addedToIssuedItemsArray = true;
            float f3 = 0.0f;
            for (int i2 = 0; i2 < issuedItems.items.size; i2++) {
                ItemStack itemStack = issuedItems.items.items[i2];
                if (itemStack.getItem() != Item.D.GREEN_PAPER && !(itemStack.getItem() instanceof StarItem) && itemStack.getItem() != Item.D.ACCELERATOR) {
                    itemStack.setCovered(true);
                }
                this.S.events.trigger(new IssuedItemsAdd(issuedItems, itemStack, f + f3, f2, i));
                f3 += 108.4f;
            }
        }
    }

    public final void addLootIssuedPrizesArray(Array<ItemStack> array, float f, float f2) {
        float f3 = array.size * 24.0f;
        for (int i = 0; i < array.size; i++) {
            float f4 = i / (array.size - 1.0f);
            addLootIssuedPrizes(array.get(i), (f - (f3 * 0.5f)) + (f3 * f4), f2 + (MathUtils.sin(f4 * 3.1415927f) * 40.0f), 2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0132  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void addLootIssuedPrizes(com.prineside.tdi2.ItemStack r10, float r11, float r12, int r13) {
        /*
            Method dump skipped, instructions count: 348
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.systems.GameStateSystem.addLootIssuedPrizes(com.prineside.tdi2.ItemStack, float, float, int):void");
    }

    public final Array<IssuedItems> getQuestsIssuedPrizes() {
        return this.k;
    }

    public final void addCompletedQuest(String str) {
        this.j.add(str);
    }

    public final Array<String> getCompletedQuests() {
        return this.j;
    }

    public final void setResources(ResourceType resourceType, int i) {
        checkGameplayUpdateAllowed();
        int i2 = this.h[resourceType.ordinal()];
        this.h[resourceType.ordinal()] = i;
        this.S.events.trigger(new MinedResourcesChange(resourceType, i2, false));
    }

    public final int addResources(ResourceType resourceType, float f) {
        checkGameplayUpdateAllowed();
        int i = (int) f;
        float f2 = f - i;
        if (f2 > 0.001f && randomFloat() < f2) {
            i++;
        }
        int i2 = this.h[resourceType.ordinal()];
        this.h[resourceType.ordinal()] = PMath.addWithoutOverflow(this.h[resourceType.ordinal()], i);
        this.S.events.trigger(new MinedResourcesChange(resourceType, i2, true));
        return i;
    }

    public final boolean removeResources(ResourceType resourceType, int i) {
        checkGameplayUpdateAllowed();
        if (this.h[resourceType.ordinal()] >= i) {
            int i2 = this.h[resourceType.ordinal()];
            int[] iArr = this.h;
            int ordinal = resourceType.ordinal();
            iArr[ordinal] = iArr[ordinal] - i;
            this.S.events.trigger(new MinedResourcesChange(resourceType, i2, false));
            return true;
        }
        return false;
    }

    public final int getResources(ResourceType resourceType) {
        return this.h[resourceType.ordinal()];
    }

    public final void setHealth(int i) {
        checkGameplayUpdateAllowed();
        int i2 = this.f;
        this.f = i;
        this.S.events.trigger(new BaseHealthChange(i2));
    }

    public final void addHealth(int i) {
        checkGameplayUpdateAllowed();
        int i2 = this.f;
        this.f = PMath.addWithoutOverflow(this.f, i);
        this.S.events.trigger(new BaseHealthChange(i2));
    }

    public final void removeHealth(int i) {
        checkGameplayUpdateAllowed();
        int i2 = this.f;
        this.f -= i;
        this.S.events.trigger(new BaseHealthChange(i2));
    }

    public final int getHealth() {
        return this.f;
    }

    public final void setScore(long j) {
        checkGameplayUpdateAllowed();
        long j2 = this.e;
        this.e = j;
        if (!isMaxEndlessReplayTimeReached()) {
            this.scoreWithEndlessTimeLimit = j;
        }
        this.S.events.trigger(new ScoreChange(j2, false, null));
    }

    public final float getScoreMultiplier(StatisticsType statisticsType) {
        switch (statisticsType) {
            case SG_EK:
                return (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.SCORE_ENEMIES_KILLED);
            case SG_WCA:
                return (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.SCORE_WAVE_CALLS);
            case SG_RM:
                return (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.SCORE_MINING);
            case SG_WCL:
                return (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.SCORE_CLEARED_WAVES);
            default:
                return 1.0f;
        }
    }

    public final long calculateFinalScore(long j, StatisticsType statisticsType) {
        long round = Math.round(getScoreMultiplier(statisticsType) * ((float) j));
        float f = 1.0f;
        if (this.averageDifficulty < 100) {
            f = this.averageDifficulty * 0.01f;
        } else if (this.averageDifficulty > 100) {
            f = 1.0f + ((this.averageDifficulty - 100) / 400.0f);
        }
        return ((float) round) * ((float) (f * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.SCORE)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long addScore(long j, StatisticsType statisticsType) {
        checkGameplayUpdateAllowed();
        long calculateFinalScore = calculateFinalScore(j, statisticsType);
        if (statisticsType <= 0) {
            return 0L;
        }
        long j2 = this.e;
        this.e += calculateFinalScore;
        if (!isMaxEndlessReplayTimeReached()) {
            this.scoreWithEndlessTimeLimit = this.e;
        }
        this.S.events.trigger(new ScoreChange(j2, true, statisticsType));
        return calculateFinalScore;
    }

    public final long getScore() {
        return this.e;
    }

    public final void pauseGame() {
        if (this.m || this.q) {
            return;
        }
        c.i("pausing " + this, new Object[0]);
        this.m = true;
        this.o = this.n;
        this.n = 0.0f;
        this.G = false;
        saveGame();
        this.S.events.trigger(new GamePaused());
    }

    public final void resumeGame() {
        if (this.m) {
            this.m = false;
            this.n = this.o;
            this.S.events.trigger(new GameResumed());
        }
    }

    public final boolean isPaused() {
        return this.m;
    }

    public final void togglePauseMenu() {
        if (this.m) {
            resumeGame();
        } else {
            pauseGame();
        }
    }

    public final void triggerGameOver(GameOverReason gameOverReason) {
        if (this.q) {
            return;
        }
        c.i("game over triggered", new Object[0]);
        this.q = true;
        this.gameOverReason = gameOverReason;
        this.S.events.trigger(new GameOver());
        if (this.basicLevelName != null && this.basicLevelName.startsWith("0.")) {
            if (gameOverReason == GameOverReason.QUEST_FAILED || gameOverReason == GameOverReason.ZERO_BASE_HEALTH) {
                Game.i.achievementManager.setProgress(AchievementType.FAIL_TUTORIAL, 1);
            }
        }
    }

    public final boolean isGameOver() {
        return this.q;
    }

    public final void realUpdate(float f) {
        if (f < 0.0f || Float.isInfinite(f) || Float.isNaN(f)) {
            c.e("realDeltaTime is " + f + ", reset to 0", new Object[0]);
            f = 0.0f;
        } else if (f > 10.0f) {
            f = 10.0f;
        }
        if (this.L == null) {
            this.L = new _LifecycleListener(this, (byte) 0);
            Gdx.app.addLifecycleListener(this.L);
        }
        if (!isPaused() && getGameSpeed() > 0.0f) {
            this.playRealTime += f;
            this.J += f;
        }
    }

    public final void registerPlayerActivity() {
        this.s = this.r;
    }

    public final boolean isMaxEndlessReplayTimeReached() {
        return DifficultyMode.isEndless(this.S.gameState.difficultyMode) && this.S.statistics.getStatistic(StatisticsType.PT) > 2700.0d;
    }

    public final int getPxpLastActionFrame() {
        return this.s;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        this.l.nextLong();
        if (isGameRealTimePasses()) {
            this.w -= f;
            if (this.w < 0.0f) {
                this.w = 0.0f;
            }
            if (this.pxpExperience < 1333 && !isMaxEndlessReplayTimeReached()) {
                this.r++;
                if (this.r - this.s > 13500) {
                    this.t = this.r;
                } else {
                    if (this.r - this.t == (DifficultyMode.isEndless(this.difficultyMode) ? Config.PLAYER_XP_ISSUE_INTERVAL_ENDLESS : Config.PLAYER_XP_ISSUE_INTERVAL)) {
                        this.pxpExperience++;
                        this.t = this.r;
                    }
                }
            }
            float floatValue = this.S.gameValue.getFloatValue(GameValueType.COINS_GENERATION) / 60.0f;
            float f2 = floatValue;
            if (floatValue > 0.0f) {
                this.v += f;
                float f3 = 1.0f;
                if (isDoubleSpeedActive()) {
                    f2 *= 2.0f;
                    f3 = 0.5f;
                }
                this.u += f * f2;
                if (this.v >= f3) {
                    this.v -= f3;
                    if (this.u >= 1.0f) {
                        int i = (int) this.u;
                        addMoney(i, true);
                        this.S.statistics.addStatistic(StatisticsType.CG_PG, i);
                        this.u -= i;
                    }
                }
            }
        }
        this.S.events.getListeners(GameStateTick.class).trigger(new GameStateTick(f));
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 < this.S.map.spawnedEnemies.size) {
                Enemy enemy = this.S.map.spawnedEnemies.items[i2].enemy;
                if (enemy == null || enemy.ignoredOnGameOverNoEnemies) {
                    i2++;
                } else {
                    z = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (this.S.wave.status == WaveSystem.Status.ENDED && !z) {
            triggerGameOver(GameOverReason.NO_ENEMIES_LEFT);
        }
        if (this.headlessValidatedReplayRecord != null && isMaxEndlessReplayTimeReached()) {
            triggerGameOver(GameOverReason.MANUAL);
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean profileUpdate() {
        return false;
    }

    @Override // com.prineside.tdi2.systems.StateSystem, com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        if (this.L != null) {
            Gdx.app.removeLifecycleListener(this.L);
        }
        this.dailyQuestLevel = null;
        this.startingAbilitiesConfiguration = null;
        this.i = null;
        this.gameStartProgressSnapshot = null;
        this.headlessValidatedReplayRecord = null;
        super.dispose();
    }

    @Override // com.prineside.tdi2.systems.StateSystem, com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "GameState";
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameStateSystem$OnMinerResourceChange.class */
    public static class OnMinerResourceChange implements KryoSerializable, Listener<MinerResourceChange> {

        /* renamed from: a, reason: collision with root package name */
        private GameSystemProvider f2949a;

        /* synthetic */ OnMinerResourceChange(GameSystemProvider gameSystemProvider, byte b2) {
            this(gameSystemProvider);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObjectOrNull(output, this.f2949a, GameSystemProvider.class);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.f2949a = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        }

        @Deprecated
        private OnMinerResourceChange() {
        }

        private OnMinerResourceChange(GameSystemProvider gameSystemProvider) {
            this.f2949a = gameSystemProvider;
        }

        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(MinerResourceChange minerResourceChange) {
            if (minerResourceChange.isMined() && minerResourceChange.getDelta() > 0) {
                long resourceMinedRawScore = this.f2949a.miner.getResourceMinedRawScore(minerResourceChange.getResourceType()) * minerResourceChange.getDelta();
                if (minerResourceChange.getMiner().loopAbilityResourceBuffer > 0) {
                    resourceMinedRawScore /= 2;
                }
                minerResourceChange.getMiner().totalScoreGained += this.f2949a.gameState.addScore(resourceMinedRawScore, StatisticsType.SG_RM);
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameStateSystem$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<GameStateSystem> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(GameStateSystem gameStateSystem) {
            this.f1759a = gameStateSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            GameSystemProvider gameSystemProvider = ((GameStateSystem) this.f1759a).S;
            DamageRecord lastDamage = enemyDie.getLastDamage();
            Enemy enemy = lastDamage.getEnemy();
            Tower tower = lastDamage.getTower();
            gameSystemProvider.gameState.addScore(MathUtils.round(1.75f * enemy.getKillScore()), StatisticsType.SG_EK);
            float f = enemy.bounty;
            if (tower != null) {
                if (tower.bountyModifiersNearby != 0 && !gameSystemProvider.gameValue.getBooleanValue(GameValueType.MODIFIER_BOUNTY_NO_HARM_TO_TOWERS)) {
                    tower.bonusCoinsBrought -= f;
                    f = 0.0f;
                } else if (tower.getTile() != null && tower.getTile().bonusType == SpaceTileBonusType.BONUS_COINS && tower.getTile().bonusLevel > 0) {
                    float effect = SpaceTileBonus.getEffect(SpaceTileBonusType.BONUS_COINS, tower.getTile().bonusLevel);
                    tower.bonusCoinsBrought += f * (effect - 1.0f);
                    f *= effect;
                }
            }
            if (lastDamage.getAbility() != null) {
                f *= lastDamage.getAbility().getKilledEnemiesCoinMultiplier();
            }
            if (f != 0.0f) {
                int addMoney = gameSystemProvider.gameState.addMoney(f, true);
                gameSystemProvider.statistics.addStatistic(StatisticsType.CG_EK, addMoney);
                if (addMoney == 0 || gameSystemProvider._particle == null) {
                    return;
                }
                gameSystemProvider._particle.addCoinParticle(enemy.getPosition().x, enemy.getPosition().y, addMoney);
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameStateSystem$OnEnemyReachTarget.class */
    public static final class OnEnemyReachTarget implements KryoSerializable, Listener<EnemyReachTarget> {

        /* renamed from: a, reason: collision with root package name */
        private GameSystemProvider f2948a;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f2948a);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f2948a = (GameSystemProvider) kryo.readObject(input, GameSystemProvider.class);
        }

        private OnEnemyReachTarget() {
        }

        public OnEnemyReachTarget(GameSystemProvider gameSystemProvider) {
            this.f2948a = gameSystemProvider;
        }

        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyReachTarget enemyReachTarget) {
            if (enemyReachTarget.getEnemy().getCurrentTile() instanceof TargetTile) {
                if (this.f2948a._gameUi != null) {
                    this.f2948a.map.getMap().getTargetTileOrThrow().showHitEffect(enemyReachTarget.getEnemy().getPosition());
                    this.f2948a._gameUi.mainUi.showHealthDelta(enemyReachTarget.getFactDamage());
                }
                this.f2948a.gameState.lastEnemyReachedTarget = enemyReachTarget.getEnemy().type;
                if (enemyReachTarget.getFactDamage() > 0) {
                    this.f2948a.gameState.removeHealth(enemyReachTarget.getFactDamage());
                }
                if (this.f2948a.gameState.f <= 0) {
                    this.f2948a.gameState.triggerGameOver(GameOverReason.ZERO_BASE_HEALTH);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NAGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameStateSystem$_LifecycleListener.class */
    public class _LifecycleListener implements LifecycleListener {
        private _LifecycleListener() {
        }

        /* synthetic */ _LifecycleListener(GameStateSystem gameStateSystem, byte b2) {
            this();
        }

        @Override // com.badlogic.gdx.LifecycleListener
        public void pause() {
            if (!GameStateSystem.this.S.gameState.q) {
                GameStateSystem.this.pauseGame();
            }
        }

        @Override // com.badlogic.gdx.LifecycleListener
        public void resume() {
        }

        @Override // com.badlogic.gdx.LifecycleListener
        public void dispose() {
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameStateSystem$OnNextWaveForce.class */
    public static final class OnNextWaveForce extends SerializableListener<GameStateSystem> implements Listener<NextWaveForce> {
        /* synthetic */ OnNextWaveForce(GameStateSystem gameStateSystem, byte b2) {
            this(gameStateSystem);
        }

        private OnNextWaveForce() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnNextWaveForce(GameStateSystem gameStateSystem) {
            this.f1759a = gameStateSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(NextWaveForce nextWaveForce) {
            if (nextWaveForce.getTime() > 0.0f) {
                GameStateSystem.a((GameStateSystem) this.f1759a, nextWaveForce.getTime());
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameStateSystem$ReplayValidationResult.class */
    public static class ReplayValidationResult {
        public Result result;
        public float timeSpent;
        public int updatesPerSecond;
        public int realWaves;
        public long realScore;
        public int updates;
        public int xp;
        public int resourcesMined;
        public int enemiesKilled;
        public String cheatingReason;
        public ReplayManager.ReplayRecord replayRecord;
        public GameSystemProvider S;

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameStateSystem$ReplayValidationResult$Result.class */
        public enum Result {
            INVALID_RECORD,
            MODIFIED_GAME,
            VALID,
            INVALID
        }

        public ReplayValidationResult(Result result, float f, int i, int i2, int i3, long j, ReplayManager.ReplayRecord replayRecord) {
            this.result = result;
            this.timeSpent = f;
            this.updatesPerSecond = i;
            this.updates = i2;
            this.replayRecord = replayRecord;
            this.realWaves = i3;
            this.realScore = j;
        }

        public ReplayValidationResult(Result result, float f, int i, int i2, int i3, long j, ReplayManager.ReplayRecord replayRecord, String str) {
            this(result, f, i, i2, i3, j, replayRecord);
            this.cheatingReason = str;
        }
    }
}
