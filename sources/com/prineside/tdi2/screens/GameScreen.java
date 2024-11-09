package com.prineside.tdi2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.Timer;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.BasicLevelQuestConfig;
import com.prineside.tdi2.CameraController;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueConfig;
import com.prineside.tdi2.HeadlessReplayReportGenerator;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.MapPrestigeConfig;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.UserMap;
import com.prineside.tdi2.configs.HeadlessConfig;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.game.ForceWaveAvailabilityChange;
import com.prineside.tdi2.events.game.Render;
import com.prineside.tdi2.managers.DailyQuestManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.ReplayManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.systems.WaveSystem;
import com.prineside.tdi2.tiles.BossTile;
import com.prineside.tdi2.tiles.TargetTile;
import com.prineside.tdi2.ui.shared.AbilitySelectionOverlay;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.LoadingOverlay;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.ui.shared.ProfileSummary;
import com.prineside.tdi2.utils.GameSyncLoader;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.WaveBossSupplier;
import com.prineside.tdi2.utils.errorhandling.CrashHandler;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/GameScreen.class */
public class GameScreen extends Screen {
    public GameSystemProvider S;
    public GameSystemProvider validationS;
    public final GameSyncLoader loader;

    /* renamed from: b, reason: collision with root package name */
    private boolean f2752b;
    private float c;
    private boolean d;
    private float g;
    private float[] h;
    private int i;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2751a = TLog.forClass(GameScreen.class);
    private static final StringBuilder e = new StringBuilder();
    private static final StringBuilder f = new StringBuilder();

    public static void configureSystemsBeforeSetup(GameSystemProvider gameSystemProvider, AbilitySelectionOverlay.SelectedAbilitiesConfiguration selectedAbilitiesConfiguration, boolean z, boolean z2, boolean z3, long j, BasicLevel basicLevel, UserMap userMap, DifficultyMode difficultyMode, int i, GameStateSystem.GameMode gameMode, BossType[] bossTypeArr, ProgressManager.ProgressSnapshotForState progressSnapshotForState, ProgressManager.InventoryStatistics inventoryStatistics, @Null DailyQuestManager.DailyQuestLevel dailyQuestLevel) {
        Map cpy;
        if (progressSnapshotForState == null) {
            throw new IllegalStateException("progressSnapshot not specified");
        }
        gameSystemProvider.gameState.inUpdateStage = true;
        gameSystemProvider.gameState.startingAbilitiesConfiguration = selectedAbilitiesConfiguration;
        gameSystemProvider.gameState.canLootCases = z;
        gameSystemProvider.gameState.lootBoostEnabled = z2;
        gameSystemProvider.gameState.rarityBoostEnabled = z3;
        gameSystemProvider.gameState.gameStartTimestamp = j;
        gameSystemProvider.gameState.difficultyMode = difficultyMode;
        gameSystemProvider.gameState.gameMode = gameMode;
        gameSystemProvider.gameState.basicLevelName = basicLevel == null ? null : basicLevel.name;
        gameSystemProvider.gameState.userMapId = userMap == null ? null : userMap.id;
        gameSystemProvider.gameState.userMapOriginalSeed = userMap == null ? 0 : userMap.map.generateSeed();
        gameSystemProvider.gameState.dailyQuestLevel = dailyQuestLevel;
        if (Game.i.settingsManager != null) {
            gameSystemProvider.wave.instantWaveCallsEnabled = Game.i.settingsManager.isInstantAutoWaveCallEnabled();
        }
        if (basicLevel != null) {
            cpy = basicLevel.getMap().cpy();
        } else if (userMap != null) {
            cpy = userMap.map.cpy();
        } else {
            throw new IllegalArgumentException("Both BasicLevel and UserMap not specified");
        }
        gameSystemProvider.gameState.modeDifficultyMultiplier = ProgressManager.clampModeDifficulty(difficultyMode, i, basicLevel, cpy.getTargetTileOrThrow().isUseStockGameValues(), userMap != null, progressSnapshotForState);
        if (basicLevel != null) {
            if (basicLevel.enemyWaves != null) {
                gameSystemProvider.wave.mode = WaveSystem.Mode.PREDEFINED;
                gameSystemProvider.wave.predefinedWaveTemplates = basicLevel.enemyWaves;
            }
            gameSystemProvider.gameState.setSeed(basicLevel.seed);
            cpy.multiplyPortalsDifficulty(gameSystemProvider.gameState.modeDifficultyMultiplier * 0.01f);
            gameSystemProvider.gameState.averageDifficulty = cpy.getAverageDifficulty();
            gameSystemProvider.map.setMap(cpy);
            gameSystemProvider.wave.setDifficultyExpectedPlaytime(basicLevel.getDifficultyExpectedPlaytime());
            WaveBossSupplier bossWaves = cpy.getBossWaves();
            if (bossWaves != null) {
                gameSystemProvider.wave.setBossWaves(bossWaves.cpy());
            }
            if (basicLevel.bonusStagesConfig != null) {
                gameSystemProvider.bonus.setStagesConfig(basicLevel.bonusStagesConfig);
            }
            if (!Config.isHeadless()) {
                ProgressPrefs i2 = ProgressPrefs.i();
                i2.basicLevel.addLevelGameStartsCount(basicLevel.name, 1);
                i2.requireSave();
            }
            gameSystemProvider.statistics.addStatistic(StatisticsType.GS, 1.0d);
        } else if (userMap != null) {
            cpy.multiplyPortalsDifficulty(gameSystemProvider.gameState.modeDifficultyMultiplier * 0.01f);
            gameSystemProvider.wave.setDifficultyExpectedPlaytime(cpy.getDifficultyExpectedPlaytime());
            WaveBossSupplier bossWaves2 = cpy.getBossWaves();
            WaveBossSupplier waveBossSupplier = bossWaves2;
            if (bossWaves2 == null && bossTypeArr != null) {
                Array array = new Array(true, 1, BossTile.BossTypeWavePair.class);
                int i3 = 0;
                for (BossType bossType : bossTypeArr) {
                    array.add(new BossTile.BossTypeWavePair(i3, bossType));
                    i3 += 20;
                }
                waveBossSupplier = new WaveBossSupplier.Procedural(new BossTile.BossWavesConfig(bossTypeArr.length * 20, 0, 40, array));
                gameSystemProvider.gameState.allowedBossesForCustomMaps = bossTypeArr;
            }
            if (waveBossSupplier != null) {
                gameSystemProvider.wave.setBossWaves(waveBossSupplier.cpy());
            }
            gameSystemProvider.map.setMap(cpy);
            gameSystemProvider.gameState.averageDifficulty = cpy.getAverageDifficulty();
            gameSystemProvider.gameState.setSeed(cpy.generateSeed());
            gameSystemProvider.statistics.addStatistic(StatisticsType.GS, 1.0d);
            gameSystemProvider.statistics.addStatistic(StatisticsType.GSUM, 1.0d);
        }
        gameSystemProvider.gameState.gameStartProgressSnapshot = progressSnapshotForState;
        if (inventoryStatistics == null) {
            f2751a.i("inventoryStatistics not specified, generating with manager", new Object[0]);
            inventoryStatistics = Game.i.progressManager.getInventoryStatistics();
        }
        gameSystemProvider.loot.inventoryStatistics = inventoryStatistics;
        if (DifficultyMode.isEndless(difficultyMode) && basicLevel != null && basicLevel.hasLeaderboards) {
            gameSystemProvider.map.getMap().getTargetTileOrThrow().getGameValues().add(new GameValueConfig(GameValueType.ENEMIES_WALK_ON_PLATFORMS, 1.0d, true, false));
            gameSystemProvider.map.getMap().getTargetTileOrThrow().getGameValues().add(new GameValueConfig(GameValueType.ENEMIES_MAX_PATH_SEARCHES, 2.0d, true, false));
        }
    }

    private void a(AbilitySelectionOverlay.SelectedAbilitiesConfiguration selectedAbilitiesConfiguration, boolean z, boolean z2, boolean z3, long j, BasicLevel basicLevel, UserMap userMap, DifficultyMode difficultyMode, int i, GameStateSystem.GameMode gameMode, BossType[] bossTypeArr, ProgressManager.ProgressSnapshotForState progressSnapshotForState, ProgressManager.InventoryStatistics inventoryStatistics, @Null DailyQuestManager.DailyQuestLevel dailyQuestLevel) {
        if (progressSnapshotForState == null) {
            f2751a.i("progressSnapshot not specified, generating with manager", new Object[0]);
            Game.i.researchManager.updateAndValidateStarBranch();
            progressSnapshotForState = Game.i.progressManager.createProgressSnapshotForState();
        }
        this.S = new GameSystemProvider(new GameSystemProvider.SystemsConfig(GameSystemProvider.SystemsConfig.Setup.GAME, Config.isHeadless()));
        this.S.createSystems();
        configureSystemsBeforeSetup(this.S, selectedAbilitiesConfiguration, z, z2, z3, j, basicLevel, userMap, difficultyMode, i, gameMode, bossTypeArr, progressSnapshotForState, inventoryStatistics, dailyQuestLevel);
        boolean z4 = basicLevel != null && basicLevel.hasLeaderboards;
        if (!Config.isHeadless() && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_SYNC_VALIDATION) != 0.0d && z4) {
            this.validationS = new GameSystemProvider(new GameSystemProvider.SystemsConfig(GameSystemProvider.SystemsConfig.Setup.GAME, true));
            this.validationS.createSystems();
            this.S.gameState.duplicateActionsTo = this.validationS.gameState;
            configureSystemsBeforeSetup(this.validationS, selectedAbilitiesConfiguration, z, z2, z3, j, basicLevel, userMap, difficultyMode, i, gameMode, bossTypeArr, progressSnapshotForState, inventoryStatistics, dailyQuestLevel);
            this.validationS.setupSystems();
            this.validationS.postSetupSystems();
            Notifications.i().add("Synchronization check enabled, thanks for helping us to make Infinitode 2 better!", Game.i.assetManager.getDrawable("icon-check"), MaterialColor.GREEN.P800, null);
            Timer.schedule(new Timer.Task(this) { // from class: com.prineside.tdi2.screens.GameScreen.1
                @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                public void run() {
                    Notifications.i().add("It affects performance and can be turned off in Settings -> Advanced -> \"Desync check\".", Game.i.assetManager.getDrawable("icon-check"), MaterialColor.LIGHT_BLUE.P800, null);
                }
            }, 3.0f);
        }
        Config.isHeadless();
        if (!Config.isHeadless()) {
            if (this.S.map.getMap().getMusicTile() != null && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.IGNORE_MAP_MUSIC) == 0.0d) {
                Game.i.musicManager.setVolume(0.0f, 1.0f, true);
            }
            if (Game.i.uiManager != null) {
                Game.i.uiManager.hideAllComponents();
                LoadingOverlay.i().show();
            }
        }
        this.S.setupSystems();
        this.loader.addTask(new GameSyncLoader.Task("Initialization", () -> {
            if (!this.S.gameState.isFastForwarding() && Game.i.uiManager != null) {
                LoadingOverlay.i().hide();
            }
            if (this.S._gameUi != null) {
                if (this.S.gameValue.getBooleanValue(GameValueType.MANUAL_GAME_SPEED)) {
                    this.S._gameUi.getMainUi().showGameSpeedButton(false, null);
                } else {
                    this.S._gameUi.getMainUi().hideGameSpeedButton();
                }
            }
            this.S.postSetupSystems();
        }));
        this.loader.addListener(new GameSyncLoader.SyncExecutionListener() { // from class: com.prineside.tdi2.screens.GameScreen.2
            @Override // com.prineside.tdi2.utils.GameSyncLoader.SyncExecutionListener
            public void startedTask(GameSyncLoader.Task task, GameSyncLoader.Task task2) {
            }

            @Override // com.prineside.tdi2.utils.GameSyncLoader.SyncExecutionListener
            public void done() {
                if (GameScreen.this.S.gameState.basicLevelName != null && Game.i.basicLevelManager.getLevel(GameScreen.this.S.gameState.basicLevelName).fastForwardFrame > 0) {
                    GameScreen.this.S.events.getListeners(ForceWaveAvailabilityChange.class).add(forceWaveAvailabilityChange -> {
                        if (GameScreen.this.S.gameState.basicLevelName != null && Game.i.basicLevelManager.getLevel(GameScreen.this.S.gameState.basicLevelName).fastForwardFrame > GameScreen.this.S.gameState.updateNumber && GameScreen.this.S.wave.isForceWaveAvailable()) {
                            GameScreen.this.S.wave.forceNextWaveAction();
                        }
                    });
                    GameScreen.this.S.wave.startNextWave();
                    GameScreen.this.S.gameState.startFastForwarding(Game.i.basicLevelManager.getLevel(GameScreen.this.S.gameState.basicLevelName).fastForwardFrame);
                }
            }
        });
        Game.i.analyticsManager.logLevelStarted(basicLevel != null ? "basic" : "custom", basicLevel != null ? basicLevel.name : "custom");
    }

    public GameScreen(BasicLevel basicLevel, DifficultyMode difficultyMode, int i, @Null AbilitySelectionOverlay.SelectedAbilitiesConfiguration selectedAbilitiesConfiguration, long j, ProgressManager.ProgressSnapshotForState progressSnapshotForState) {
        this(basicLevel, difficultyMode, i, basicLevel.getMap().getTargetTileOrThrow().isDisableAbilities() ? basicLevel.getMap().getMaxedAbilitiesConfiguration() : selectedAbilitiesConfiguration, true, Game.i.progressManager.getLootBoostTimeLeft() > 0.0f, Game.i.progressManager.getItemsCount(Item.D.RARITY_BOOST) > 0, j, progressSnapshotForState, null, null);
    }

    public GameScreen(DailyQuestManager.DailyQuestLevel dailyQuestLevel) {
        this(Game.i.basicLevelManager.getLevel(dailyQuestLevel.getLevelName()), DifficultyMode.NORMAL, 100, Game.i.basicLevelManager.getLevel(dailyQuestLevel.getLevelName()).getMap().getMaxedAbilitiesConfiguration(), false, false, false, -1L, null, null, dailyQuestLevel);
    }

    public GameScreen(GameSystemProvider gameSystemProvider, long j) {
        this.loader = new GameSyncLoader();
        new Output(65536, -1);
        this.f2752b = false;
        this.d = false;
        this.h = new float[600];
        Game.i.uiManager.hideAllComponents();
        this.S = gameSystemProvider;
        gameSystemProvider.gameState.gameStartTimestamp = j;
        gameSystemProvider.gameState.setGameSpeed(0.0f);
    }

    public GameScreen(BasicLevel basicLevel, DifficultyMode difficultyMode, int i, @Null AbilitySelectionOverlay.SelectedAbilitiesConfiguration selectedAbilitiesConfiguration, boolean z, boolean z2, boolean z3, long j, ProgressManager.ProgressSnapshotForState progressSnapshotForState, ProgressManager.InventoryStatistics inventoryStatistics, @Null DailyQuestManager.DailyQuestLevel dailyQuestLevel) {
        this.loader = new GameSyncLoader();
        new Output(65536, -1);
        this.f2752b = false;
        this.d = false;
        this.h = new float[600];
        j = j == -1 ? Game.getTimestampMillis() : j;
        difficultyMode = basicLevel.forcedDifficulty != null ? basicLevel.forcedDifficulty : difficultyMode;
        f2751a.i("starting level " + basicLevel.name + SequenceUtils.SPACE + difficultyMode.name(), new Object[0]);
        a(selectedAbilitiesConfiguration, z, z2, z3, j, basicLevel, null, difficultyMode, i, GameStateSystem.GameMode.BASIC_LEVELS, null, progressSnapshotForState, inventoryStatistics, dailyQuestLevel);
    }

    public GameScreen(UserMap userMap, DifficultyMode difficultyMode, int i, AbilitySelectionOverlay.SelectedAbilitiesConfiguration selectedAbilitiesConfiguration, long j, BossType[] bossTypeArr, ProgressManager.ProgressSnapshotForState progressSnapshotForState, ProgressManager.InventoryStatistics inventoryStatistics) {
        this.loader = new GameSyncLoader();
        new Output(65536, -1);
        this.f2752b = false;
        this.d = false;
        this.h = new float[600];
        j = j == -1 ? Game.getTimestampMillis() : j;
        Preconditions.checkNotNull(userMap, "UserMap is null");
        boolean z = Game.i.progressManager.getItemsCount(Item.D.RARITY_BOOST) > 0;
        a(userMap.map.getTargetTileOrThrow().isDisableAbilities() ? userMap.map.getMaxedAbilitiesConfiguration() : selectedAbilitiesConfiguration, true, Game.i.progressManager.getLootBoostTimeLeft() > 0.0f, z, j, null, userMap, difficultyMode, i, GameStateSystem.GameMode.USER_MAPS, bossTypeArr, progressSnapshotForState, inventoryStatistics, null);
        this.S.achievement.setProgress(AchievementType.HUNDRED_TILE_CUSTOM_MAP, userMap.map.getAllTiles().size);
        this.S.achievement.setProgress(AchievementType.FIVE_HUNDRED_TILE_CUSTOM_MAP, userMap.map.getAllTiles().size);
    }

    public void updateDraw(float f2, float f3) {
        if (this.S.CFG.headless) {
            return;
        }
        long realTickCount = Game.getRealTickCount();
        if (Float.isNaN(f3)) {
            f3 = 0.0f;
        } else if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        this.S.events.trigger(new Render(f3, f2));
        if (!this.S.CFG.headless && !Config.isHeadless() && !this.S.gameState.isFastForwarding() && !this.S.gameState.isGameOver()) {
            this.g += f3;
            float customValue = ((float) Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.STATE_AUTO_SAVE_INTERVAL)) * 60.0f;
            if (customValue > 0.0f && this.g > customValue) {
                this.S.gameState.saveGame();
                this.g = 0.0f;
            }
        }
        float realTickCount2 = ((float) (Game.getRealTickCount() - realTickCount)) * 0.001f;
        this.h[this.i] = realTickCount2;
        this.i++;
        if (this.i == this.h.length) {
            this.i = 0;
        }
        if (Game.i.debugManager.isEnabled()) {
            float f4 = 0.0f;
            for (float f5 : this.h) {
                f4 += f5;
            }
            Game.i.debugManager.registerValue("Drawing time").append(StringFormatter.compactNumberWithPrecision(realTickCount2, 2)).append("ms / ").append(StringFormatter.compactNumberWithPrecision(f4 / this.h.length, 2)).append("ms");
        }
        Game.i.renderingManager.stopAnyBatchDrawing();
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void show() {
        if (Game.i.uiManager != null) {
            Game.i.uiManager.stage.setScrollFocus(null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v18, types: [com.badlogic.gdx.utils.StringBuilder] */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v62, types: [com.prineside.tdi2.ui.shared.Notifications$Notification] */
    public void updateSystems() {
        this.S.syncChecking = this.validationS != null;
        this.S.syncCheckLog.clear();
        this.S.updateSystems();
        if (this.validationS != null) {
            this.validationS.syncChecking = true;
            this.validationS.syncCheckLog.clear();
            this.validationS.updateSystems();
            ?? r0 = f;
            r0.setLength(0);
            try {
                long realTickCount = Game.getRealTickCount();
                this.validationS.compareSync(this.S, f, false);
                Game.i.debugManager.registerFrameJob("SyncCheck", Game.getRealTickCount() - realTickCount);
                if (f.length != 0) {
                    f.append("\nLog A:\n").append(this.S.syncCheckLog.toString(SequenceUtils.EOL));
                    f.append("\nLog B:\n").append(this.validationS.syncCheckLog.toString(SequenceUtils.EOL));
                    f2751a.e(f.toString(), new Object[0]);
                    this.S.gameState.gameSavesDisabled = true;
                    Gdx.files.local("desync-report-" + (this.S.gameState.headlessValidatedReplayRecord == null ? new StringBuilder().append(Game.getTimestampSeconds()).toString() : this.S.gameState.headlessValidatedReplayRecord.id) + ".txt").writeString(f.toString(), false, "UTF-8");
                    this.validationS.dispose();
                    this.validationS = null;
                    if (!Config.isHeadless()) {
                        CrashHandler.report("Sync check - desync\n" + f.toString());
                        r0 = Notifications.i().add("Desynchronization spotted!", Game.i.assetManager.getDrawable("icon-exclamation-triangle"), MaterialColor.ORANGE.P800, StaticSoundType.WARNING);
                    }
                }
            } catch (Exception e2) {
                r0.printStackTrace();
                this.validationS = null;
                if (!Config.isHeadless()) {
                    CrashHandler.report("Sync check - exception", e2);
                    Notifications.i().add("Synchronization check disabled", Game.i.assetManager.getDrawable("icon-exclamation-triangle"), MaterialColor.ORANGE.P800, StaticSoundType.WARNING);
                }
            }
        }
        Game.i.debugManager.registerGameStateUpdate();
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f2) {
        boolean z = !this.S.CFG.headless;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (z) {
            Color color = Game.i.assetManager.getColor("game_background");
            Gdx.gl.glClearColor(color.r, color.g, color.f888b, color.f889a);
            Gdx.gl.glClear(16640);
        }
        float f3 = 0.0f;
        if (!this.loader.isDone()) {
            this.loader.iterate();
            return;
        }
        if (z && Game.i.settingsManager.isEscButtonJustPressed() && ((this.S == null || !this.S.gameState.isGameOver()) && this.S != null)) {
            this.S.gameState.togglePauseMenu();
        }
        float f4 = 0.0f;
        if (f2 > 0.2f) {
            f2 = 0.2f;
        }
        if (this.S.gameState.isFastForwarding()) {
            long realTickCount = Game.getRealTickCount();
            this.S.gameState.setGameSpeed(1.0f);
            do {
                if (this.S.gameState.updateNumber < this.S.gameState.getFastForwardUpdateNumber()) {
                    updateSystems();
                    if (!z) {
                        if (this.S.gameState.updateNumber % HeadlessConfig.REPORT_INTERVAL == 0) {
                            HeadlessReplayReportGenerator.interval(this.S);
                        }
                        if (this.S.gameState.updateNumber == this.S.gameState.validationLastUpdateNumber) {
                            f2751a.i("reached last validation frame", new Object[0]);
                            this.S.gameState.triggerGameOver(GameStateSystem.GameOverReason.MANUAL);
                        }
                        if (Game.getTimestampMillis() - this.S.gameState.validationStartTimestamp > 10800000) {
                            f2751a.i("validation running for too long, aborting", new Object[0]);
                            this.S.gameState.triggerGameOver(GameStateSystem.GameOverReason.MANUAL);
                        }
                    }
                    if (this.S.gameState.isGameOver()) {
                        this.S.gameState.stopFastForwarding();
                    }
                }
                if (this.S.gameState.updateNumber >= this.S.gameState.getFastForwardUpdateNumber()) {
                    this.S.gameState.stopFastForwarding();
                    if (this.S.gameState.basicLevelName != null && Game.i.basicLevelManager.getLevel(this.S.gameState.basicLevelName).fastForwardFrame > 0) {
                        this.S.gameState.setGameSpeed(1.0f);
                    } else {
                        this.S.gameState.setGameSpeed(0.0f);
                    }
                    if (z) {
                        LoadingOverlay.i().hide();
                    }
                    f2751a.i("fast forward to update " + this.S.gameState.getFastForwardUpdateNumber() + " done, frame " + this.S.gameState.updateNumber + ", continued game state hash " + (this.S.gameState.getApproxStateHash() == this.S.gameState.continuedGameApproxStateHash ? "matches :)" : "differs :( " + this.S.gameState.getApproxStateHash() + SequenceUtils.SPACE + this.S.gameState.continuedGameApproxStateHash), new Object[0]);
                }
            } while (Game.getRealTickCount() - realTickCount <= Config.MAX_UPDATES_DURATION);
            if (z) {
                if (!this.S.gameState.canSkipMediaUpdate()) {
                    updateDraw(33333.0f, 33333.0f);
                }
                e.setLength(0);
                e.append("Frame ").append(this.S.gameState.updateNumber).append("/").append(this.S.gameState.getFastForwardUpdateNumber());
                LoadingOverlay.i().showWithBar(this.S.gameState.updateNumber / this.S.gameState.getFastForwardUpdateNumber(), e);
                return;
            }
            return;
        }
        this.S.resetSystemsFrameProfiling();
        if (this.S.gameState.isGameOver()) {
            b();
        } else {
            int i = 0;
            float gameSpeed = this.S.gameState.getGameSpeed();
            float f5 = gameSpeed * f2;
            if (f5 < 0.0f) {
                throw new IllegalStateException("expectedDeltaTime is " + f5 + " game speed " + gameSpeed + " real delta " + f2);
            }
            this.c += f5;
            if (this.S.gameState.getGameSpeed() != 0.0f) {
                long realTickCount2 = Game.getRealTickCount();
                float tickRateDeltaTime = this.S.gameValue.getTickRateDeltaTime();
                if (this.S.gameState.updateRequired && this.c < tickRateDeltaTime) {
                    this.c = tickRateDeltaTime;
                }
                f4 = gameSpeed * f2;
                while (true) {
                    if (this.c < tickRateDeltaTime) {
                        break;
                    }
                    updateSystems();
                    this.c -= tickRateDeltaTime;
                    i++;
                    if (this.S.gameState.replayMode && this.S.gameState.updateNumber == this.S.gameState.replayFrameCount) {
                        this.S.gameState.triggerGameOver(GameStateSystem.GameOverReason.MANUAL);
                    }
                    if (this.S.gameState.isGameOver()) {
                        break;
                    } else if (Game.getRealTickCount() - realTickCount2 > Config.MAX_UPDATES_DURATION) {
                        f4 = tickRateDeltaTime * i;
                        break;
                    }
                }
                f3 = ((float) (Game.getRealTickCount() - realTickCount2)) * 0.001f;
            } else if (this.S.gameState.updateRequired) {
                updateSystems();
                i = 0 + 1;
                f4 = this.S.gameValue.getTickRateDeltaTime();
            }
            if (this.c > 2.0f) {
                this.c = 2.0f;
            }
            this.S.gameState.updateRequired = false;
            if (Game.i.debugManager.isEnabled()) {
                Game.i.debugManager.registerValue("Game updates last frame").append(i);
            }
            if (Game.i.debugManager.isEnabled()) {
                this.S.flushSystemsFrameProfiling();
            }
        }
        if (this.d) {
            return;
        }
        this.S.gameState.realUpdate(f2);
        if (f4 < 0.0f || f4 > 3600.0f) {
            f4 = 0.0f;
        }
        updateDraw(f4, f2);
        if (Game.i.debugManager.isEnabled()) {
            Game.i.debugManager.registerValue("Game speed").append((int) (this.S.gameState.getGameSpeed() * 100.0f)).append('%');
            Game.i.debugManager.registerValue("Updates time").append((int) f3).append("ms");
        }
    }

    private void b() {
        if ((this.S.CFG.headless && this.S.gameState.headlessValidatedReplayRecord == null) || this.f2752b) {
            return;
        }
        this.f2752b = true;
        this.S.gameState.inUpdateStage = true;
        if (this.S._quest != null) {
            this.S._quest.update(this.S.gameValue.getTickRateDeltaTime());
        }
        if (this.S.gameState.headlessValidatedReplayRecord != null) {
            return;
        }
        if (!this.S.gameState.replayMode) {
            GameStateSystem.deleteSavedGame();
        }
        if (this.S.wave.status == WaveSystem.Status.NOT_STARTED) {
            Game.i.screenManager.goToMainMenu();
        } else {
            f2751a.i("Game Over ========", new Object[0]);
            f2751a.i("Reason: " + this.S.gameState.gameOverReason.name(), new Object[0]);
            f2751a.i("Updates count: " + this.S.gameState.updateNumber, new Object[0]);
            f2751a.i("Random state: " + this.S.gameState.getRandomState(0) + SequenceUtils.SPACE + this.S.gameState.getRandomState(1), new Object[0]);
            if (this.S.gameState.isPaused() && this.S._gameUi != null) {
                this.S._gameUi.pauseMenu.setVisible(false);
            }
            Dialog.i().hide();
            final GameSystemProvider gameSystemProvider = this.S;
            gameSystemProvider._render.gameOverGameSpeed = gameSystemProvider.gameState.getNonAnimatedGameSpeed();
            if (gameSystemProvider._render.gameOverGameSpeed > 4.0f) {
                gameSystemProvider._render.gameOverGameSpeed = 4.0f;
            }
            if (gameSystemProvider._render.gameOverGameSpeed < 1.0f) {
                gameSystemProvider._render.gameOverGameSpeed = 1.0f;
            }
            gameSystemProvider.gameState.setGameSpeed(0.0f);
            gameSystemProvider._input.enableOnlyStage();
            Game.i.musicManager.setVolume(0.0f, 1.0f, true);
            gameSystemProvider._gameUi.fadeOutUi();
            if (gameSystemProvider.gameState.gameOverReason != GameStateSystem.GameOverReason.NO_ENEMIES_LEFT && gameSystemProvider.gameState.gameOverReason != GameStateSystem.GameOverReason.MANUAL) {
                gameSystemProvider.map.getMap().getTargetTileOrThrow().startExplosionEffect();
            }
            float f2 = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.5f : 0.0f;
            Timer.schedule(new Timer.Task(this) { // from class: com.prineside.tdi2.screens.GameScreen.3
                @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                public void run() {
                    if (gameSystemProvider._input == null) {
                        return;
                    }
                    gameSystemProvider._input.cameraController.animate(new CameraController.BasicAnimation(gameSystemProvider.map.getMap().getTargetTileOrThrow().center.x, gameSystemProvider.map.getMap().getTargetTileOrThrow().center.y, 1.0d, Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f, Interpolation.pow2));
                    gameSystemProvider._gameUi.tooltip.show(Game.i.localeManager.i18n.get("game_over_reason_" + gameSystemProvider.gameState.gameOverReason.name()));
                    Game.i.soundManager.playStatic(StaticSoundType.GAME_OVER);
                }
            }, 0.4f * f2);
            Timer.schedule(new Timer.Task(this) { // from class: com.prineside.tdi2.screens.GameScreen.4
                @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                public void run() {
                    int i;
                    if (gameSystemProvider.gameState == null) {
                        GameScreen.f2751a.w("Stopping gameOver timer task - S.gameState is null", new Object[0]);
                        return;
                    }
                    if (gameSystemProvider.gameState.replayMode) {
                        gameSystemProvider._gameUi.gameOverOverlay.show(new Array<>(ItemStack.class), null);
                        return;
                    }
                    Array<IssuedItems> array = new Array<>(gameSystemProvider.gameState.getQuestsIssuedPrizes());
                    IssuedItems gameLootIssuedItems = gameSystemProvider.gameState.getGameLootIssuedItems();
                    array.add(gameLootIssuedItems);
                    int i2 = 0;
                    while (true) {
                        int allTime = (int) Game.i.statisticsManager.getAllTime(StatisticsType.AFPTG);
                        if (((int) Game.i.statisticsManager.getAllTime(StatisticsType.PRT)) < 600 + (allTime * (600 + (StrictMath.min(allTime, 20) * 15)))) {
                            break;
                        }
                        Game.i.statisticsManager.registerDelta(StatisticsType.AFPTG, 1.0d);
                        i2++;
                    }
                    if (i2 != 0) {
                        ProgressManager.addItemToStacksArray(gameLootIssuedItems.items, Item.D.ACCELERATOR, i2);
                    }
                    float f3 = 1.0f;
                    if (gameSystemProvider.gameState.isDailyQuestAndNotCompleted()) {
                        f3 = 0.1f;
                    }
                    int calculatePrizeGreenPapers = (int) (gameSystemProvider.gameState.calculatePrizeGreenPapers() * f3);
                    if (calculatePrizeGreenPapers > 0) {
                        ProgressManager.addItemToStacksArray(gameLootIssuedItems.items, Item.D.GREEN_PAPER, calculatePrizeGreenPapers);
                    }
                    for (ResourceType resourceType : ResourceType.values) {
                        int resources = (int) (gameSystemProvider.gameState.getResources(resourceType) * f3);
                        if (resources != 0) {
                            ProgressManager.addItemToStacksArray(gameLootIssuedItems.items, Item.D.F_RESOURCE.create(resourceType), resources);
                        }
                    }
                    if (gameSystemProvider.gameState.basicLevelName != null) {
                        Game.i.basicLevelManager.handleGameOverBonusLoot(gameSystemProvider, gameSystemProvider.gameState.basicLevelName, array);
                    }
                    Game.i.progressManager.handleGameOverShopOffersRotation(gameSystemProvider);
                    Array<ItemStack> array2 = new Array<>(ItemStack.class);
                    boolean isDoubleGainEnabled = Game.i.progressManager.isDoubleGainEnabled();
                    for (int i3 = 0; i3 < array.size; i3++) {
                        IssuedItems issuedItems = array.get(i3);
                        for (int i4 = 0; i4 < issuedItems.items.size; i4++) {
                            ItemStack itemStack = issuedItems.items.get(i4);
                            boolean z = isDoubleGainEnabled && itemStack.getItem().isAffectedByDoubleGain();
                            boolean z2 = z;
                            if (z) {
                                itemStack.setCount(PMath.multiplyWithoutOverflow(itemStack.getCount(), 2));
                            }
                            boolean z3 = false;
                            int i5 = 0;
                            while (true) {
                                if (i5 >= array2.size) {
                                    break;
                                }
                                ItemStack itemStack2 = array2.get(i5);
                                if (itemStack2.isCovered() != itemStack.isCovered() || itemStack2.isFromRandomEncounter() != itemStack.isFromRandomEncounter() || itemStack2.isDoubled() != z2 || !itemStack2.getItem().sameAs(itemStack.getItem())) {
                                    i5++;
                                } else {
                                    array2.get(i5).setCount(PMath.addWithoutOverflow(itemStack2.getCount(), itemStack.getCount()));
                                    z3 = true;
                                    break;
                                }
                            }
                            if (!z3) {
                                ItemStack itemStack3 = new ItemStack(itemStack);
                                if (z2) {
                                    itemStack3.markDoubled(true);
                                }
                                array2.add(itemStack3);
                            }
                        }
                    }
                    gameSystemProvider.statistics.addStatistic(StatisticsType.GPG, Game.i.progressManager.isDoubleGainEnabled() ? calculatePrizeGreenPapers << 1 : calculatePrizeGreenPapers);
                    float f4 = gameSystemProvider.gameState.playRealTime;
                    float f5 = f4;
                    if (f4 < 0.0f || Float.isNaN(f5) || Float.isInfinite(f5)) {
                        f5 = 0.0f;
                    } else if (f5 > 86400.0f) {
                        f5 = 86400.0f;
                    }
                    gameSystemProvider.statistics.addStatistic(StatisticsType.PRT, f5);
                    for (int i6 = 0; i6 < array.size; i6++) {
                        IssuedItems issuedItems2 = array.get(i6);
                        for (int i7 = 0; i7 < issuedItems2.items.size; i7++) {
                            Game.i.progressManager.addItemStack(issuedItems2.items.get(i7), "game");
                        }
                    }
                    if (gameSystemProvider.gameState.basicLevelName != null && gameSystemProvider.gameState.difficultyMode == DifficultyMode.NORMAL) {
                        Game.i.authManager.localXpPlayedLevels.add(gameSystemProvider.gameState.basicLevelName);
                    }
                    String saveReplay = Game.i.replayManager.saveReplay(gameSystemProvider);
                    ReplayManager replayManager = Game.i.replayManager;
                    GameSystemProvider gameSystemProvider2 = gameSystemProvider;
                    replayManager.sendReplayToServer(saveReplay, replaySendStatus -> {
                        if (replaySendStatus.regularXpGained > 0 && gameSystemProvider2._gameUi != null && gameSystemProvider2._gameUi.gameOverOverlay != null && !gameSystemProvider2._gameUi.gameOverOverlay.isDisposed()) {
                            ProfileSummary.i().showXpGained(replaySendStatus.regularXpGained, replaySendStatus.bonusXpGained, replaySendStatus.bonusXpLeft, replaySendStatus.regularXpLeft);
                        }
                        Game.i.replayManager.sendUnsentReplaysToTheServer();
                    });
                    MapPrestigeConfig mapPrestigeConfig = null;
                    if (gameSystemProvider.gameState.gameMode == GameStateSystem.GameMode.USER_MAPS && Game.i.gameValueManager.getSnapshot().getBooleanValue(GameValueType.PRESTIGE_MODE)) {
                        boolean z4 = true;
                        int[] iArr = gameSystemProvider.ability.abilitiesUsed;
                        int length = iArr.length;
                        int i8 = 0;
                        while (true) {
                            if (i8 >= length) {
                                break;
                            }
                            if (iArr[i8] == 0) {
                                i8++;
                            } else {
                                z4 = false;
                                break;
                            }
                        }
                        TargetTile targetTileOrThrow = gameSystemProvider.map.getMap().getTargetTileOrThrow();
                        mapPrestigeConfig = new MapPrestigeConfig(gameSystemProvider.gameState.userMapId, gameSystemProvider.map.getMap().getPrestigeScore() * Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.PRESTIGE_DUST_DROP_RATE), gameSystemProvider.gameState.averageDifficulty, z4, targetTileOrThrow.isUseStockGameValues(), targetTileOrThrow.isWalkableTiles(), gameSystemProvider.modifier.modifiersBuiltByTypeAllTime[ModifierType.BOUNTY.ordinal()] == 0, gameSystemProvider.statistics.getStatistic(StatisticsType.MB) == 0.0d, gameSystemProvider.gameState.getScore());
                    }
                    Array<String> completedQuests = gameSystemProvider.gameState.getCompletedQuests();
                    if (completedQuests.size > 0) {
                        if (gameSystemProvider.gameState.basicLevelName != null) {
                            for (int i9 = 0; i9 < completedQuests.size; i9++) {
                                String str = completedQuests.get(i9);
                                int i10 = 0;
                                while (true) {
                                    if (i10 >= Game.i.basicLevelManager.getLevel(gameSystemProvider.gameState.basicLevelName).quests.size) {
                                        break;
                                    }
                                    BasicLevelQuestConfig basicLevelQuestConfig = Game.i.basicLevelManager.getLevel(gameSystemProvider.gameState.basicLevelName).quests.items[i10];
                                    if (!basicLevelQuestConfig.id.equals(str)) {
                                        i10++;
                                    } else {
                                        basicLevelQuestConfig.setCompleted(true);
                                        GameScreen.f2751a.i("saving quest " + str, new Object[0]);
                                        break;
                                    }
                                }
                                int i11 = 0;
                                while (true) {
                                    if (i11 < Game.i.basicLevelManager.getLevel(gameSystemProvider.gameState.basicLevelName).waveQuests.size) {
                                        BasicLevel.WaveQuest waveQuest = Game.i.basicLevelManager.getLevel(gameSystemProvider.gameState.basicLevelName).waveQuests.items[i11];
                                        if (!waveQuest.id.equals(str)) {
                                            i11++;
                                        } else {
                                            waveQuest.setCompleted(true);
                                            GameScreen.f2751a.i("saving quest " + str, new Object[0]);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (gameSystemProvider.gameState.dailyQuestLevel != null) {
                            GameScreen.f2751a.i("saved today's daily quest as completed", new Object[0]);
                            gameSystemProvider.gameState.dailyQuestLevel.setCompleted();
                        }
                        String dailyLootCurrentQuest = Game.i.dailyQuestManager.getDailyLootCurrentQuest();
                        int i12 = 0;
                        while (true) {
                            if (i12 >= completedQuests.size) {
                                break;
                            }
                            if (!completedQuests.items[i12].equals(dailyLootCurrentQuest)) {
                                i12++;
                            } else {
                                IssuedItems dailyLootQuestCompleted = Game.i.dailyQuestManager.setDailyLootQuestCompleted();
                                if (dailyLootQuestCompleted != null) {
                                    for (int i13 = 0; i13 < dailyLootQuestCompleted.items.size; i13++) {
                                        ItemStack itemStack4 = new ItemStack(dailyLootQuestCompleted.items.items[i13]);
                                        itemStack4.markFromDailyLoot(true);
                                        array2.add(itemStack4);
                                    }
                                }
                            }
                        }
                    }
                    gameSystemProvider._gameUi.gameOverOverlay.show(array2, mapPrestigeConfig);
                    Game.i.analyticsManager.logLevelFinished(gameSystemProvider.gameState.basicLevelName != null ? "basic" : "custom", gameSystemProvider.gameState.basicLevelName != null ? gameSystemProvider.gameState.basicLevelName : "custom", (int) gameSystemProvider.gameState.playRealTime, (int) (gameSystemProvider.gameState.updateNumber * gameSystemProvider.gameValue.getTickRateDeltaTime()));
                    if (gameSystemProvider.gameState.gameMode == GameStateSystem.GameMode.BASIC_LEVELS) {
                        ProgressPrefs i14 = ProgressPrefs.i();
                        int completedWavesCount = gameSystemProvider.wave.getCompletedWavesCount();
                        if (completedWavesCount > i14.basicLevel.getLevelMaxReachedWave(gameSystemProvider.gameState.basicLevelName)) {
                            i14.basicLevel.setLevelMaxReachedWave(gameSystemProvider.gameState.basicLevelName, completedWavesCount);
                            i14.requireSave();
                        }
                        if (gameSystemProvider.gameState.getScore() > i14.basicLevel.getLevelMaxScore(gameSystemProvider.gameState.basicLevelName)) {
                            i14.basicLevel.setLevelMaxScore(gameSystemProvider.gameState.basicLevelName, gameSystemProvider.gameState.getScore());
                            i14.requireSave();
                        }
                        int currentGameStatistic = (int) gameSystemProvider.statistics.getCurrentGameStatistic(StatisticsType.PRT);
                        if (currentGameStatistic > i14.basicLevel.getLevelMaxPlayingTime(gameSystemProvider.gameState.basicLevelName)) {
                            i14.basicLevel.setLevelMaxPlayingTime(gameSystemProvider.gameState.basicLevelName, currentGameStatistic);
                            i14.requireSave();
                        }
                        gameSystemProvider._quest.saveBasicLevelQuestValues();
                    }
                    gameSystemProvider.statistics.flushStatistics();
                    Game.i.researchManager.checkResearchesStatus(true);
                    for (int i15 = 0; i15 < array.size; i15++) {
                        Array<? extends ItemStack> array3 = new Array<>();
                        Array<ItemStack> array4 = array.get(i15).items;
                        for (int i16 = 0; i16 < array4.size; i16++) {
                            ItemStack itemStack5 = array4.get(i16);
                            boolean z5 = false;
                            int i17 = 0;
                            while (true) {
                                if (i17 >= array3.size) {
                                    break;
                                }
                                if (!array3.get(i17).getItem().sameAs(itemStack5.getItem())) {
                                    i17++;
                                } else {
                                    array3.get(i17).setCount(PMath.addWithoutOverflow(array3.get(i17).getCount(), itemStack5.getCount()));
                                    z5 = true;
                                    break;
                                }
                            }
                            if (!z5) {
                                array3.add(new ItemStack(itemStack5));
                            }
                        }
                        array.get(i15).items.clear();
                        array.get(i15).items.addAll(array3);
                        Game.i.progressManager.addIssuedPrizes(array.get(i15), true);
                    }
                    if (gameSystemProvider.gameState.dailyQuestLevel == null && !gameSystemProvider.map.getMap().getTargetTileOrThrow().isDisableAbilities()) {
                        AbilitySelectionOverlay.SelectedAbilitiesConfiguration selectedAbilitiesConfiguration = gameSystemProvider.ability.abilitiesConfiguration;
                        for (int i18 = 0; i18 < selectedAbilitiesConfiguration.slots.length; i18++) {
                            if (selectedAbilitiesConfiguration.slots[i18] != null && (i = -gameSystemProvider.ability.abilitiesUsed[i18]) < 0 && !Game.i.progressManager.removeAbilities(selectedAbilitiesConfiguration.slots[i18], -i)) {
                                GameScreen.f2751a.e("removeAbilities false " + (-i), new Object[0]);
                            }
                        }
                        if (gameSystemProvider.gameState.rarityBoostEnabled) {
                            Game.i.analyticsManager.logCurrencySpent("used", "rarity_boost", 1);
                            Game.i.progressManager.removeItems(Item.D.RARITY_BOOST, 1);
                        }
                    }
                    Game.i.achievementManager.updateGlobal();
                    Game.i.preferencesManager.saveNowIfRequired();
                    Game.i.authManager.handleAutoSave();
                }
            }, 1.5f * f2);
        }
        Game.i.researchManager.checkResearchesStatus(true);
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        super.dispose();
        if (this.S != null) {
            this.S.dispose();
            this.S = null;
        }
        if (this.validationS != null) {
            this.validationS.dispose();
            this.validationS = null;
        }
        Game game = Game.i;
        this.d = true;
    }
}
