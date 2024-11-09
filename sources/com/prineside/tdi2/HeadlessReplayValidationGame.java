package com.prineside.tdi2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryo.io.Input;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.configs.HeadlessConfig;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.managers.DailyQuestManager;
import com.prineside.tdi2.managers.HttpManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.ReplayManager;
import com.prineside.tdi2.screens.GameScreen;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.systems.StateSystem;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.SystemOutPlatformLogger;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Random;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/HeadlessReplayValidationGame.class */
public class HeadlessReplayValidationGame extends Game {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1724a = TLog.forClass(HeadlessReplayValidationGame.class);
    public String currentReplay;
    public short instanceNumber;

    /* renamed from: b, reason: collision with root package name */
    private FileHandle f1725b;
    private int c;
    private final int d;

    public HeadlessReplayValidationGame(short s, FileHandle fileHandle) {
        super(ActionResolver.createDummy(fileHandle, new SystemOutPlatformLogger(false, false)), null);
        this.d = new Random().nextInt(HeadlessConfig.REPORT_INTERVAL);
        System.out.println("instance: " + ((int) s));
        this.instanceNumber = s;
        this.c = Game.getTimestampSeconds();
    }

    public boolean checkServerHalted() {
        if (Gdx.files.local("halt.txt").exists()) {
            f1724a.i("halt.txt found, exiting", new Object[0]);
            writeServerStatus("halted");
            Game.exit();
            return true;
        }
        if (Game.getTimestampSeconds() > this.c + 43200 + this.d) {
            f1724a.i("automatic restart", new Object[0]);
            writeServerStatus("autoRestart");
            Game.exit();
            return true;
        }
        return false;
    }

    public synchronized void writeServerStatus(String str) {
        if (this.f1725b == null) {
            this.f1725b = Gdx.files.local("status-" + ((int) this.instanceNumber) + ".txt");
        }
        this.f1725b.writeString(Game.getTimestampSeconds() + "|" + ((int) ((((float) Runtime.getRuntime().totalMemory()) / 1024.0f) / 1024.0f)) + "," + ((int) ((((float) Runtime.getRuntime().freeMemory()) / 1024.0f) / 1024.0f)) + "," + ((int) ((((float) Runtime.getRuntime().maxMemory()) / 1024.0f) / 1024.0f)) + "|" + str, false, "UTF-8");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.prineside.tdi2.utils.logging.TLog] */
    @Override // com.prineside.tdi2.Game, com.badlogic.gdx.ApplicationListener
    public void create() {
        super.create();
        f1724a.i("started loading...", new Object[0]);
        while (this.gameSyncLoader.iterate()) {
            InterruptedException interruptedException = f1724a;
            interruptedException.i("loading: " + ((Object) StringFormatter.compactNumberWithPrecision(this.gameSyncLoader.getProgress() * 100.0f, 1)) + "%", new Object[0]);
            try {
                interruptedException = 1;
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                interruptedException.printStackTrace();
            }
        }
        f1724a.i("fully loaded!", new Object[0]);
        writeServerStatus("loaded");
        b();
    }

    private void b() {
        if (checkServerHalted()) {
            return;
        }
        a(str -> {
            if (str == null || str.length() == 0) {
                writeServerStatus("emptyReplayPushMainThread");
                writeServerStatus("noReplay");
                InterruptedException interruptedException = f1724a;
                interruptedException.i("...", new Object[0]);
                try {
                    interruptedException = 5000;
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    interruptedException.printStackTrace();
                }
                b();
                return;
            }
            writeServerStatus("notEmptyReplayPushMainThread");
            try {
                ReplayManager.ReplayRecord fromCompactString = ReplayManager.ReplayRecord.fromCompactString(str);
                f1724a.i("", new Object[0]);
                Runnable runnable = () -> {
                    writeServerStatus("startingValidation");
                    headlessValidateGame(fromCompactString, replayValidationResult -> {
                        writeServerStatus("finishedValidation");
                        f1724a.i("End of game validation (" + replayValidationResult.replayRecord.id + SequenceUtils.SPACE + replayValidationResult.replayRecord.gameMode.name() + SequenceUtils.SPACE + replayValidationResult.replayRecord.difficultyMode + SequenceUtils.SPACE + replayValidationResult.replayRecord.modeDifficultyMultiplier + SequenceUtils.SPACE + String.valueOf(replayValidationResult.replayRecord.levelName) + ", status: " + replayValidationResult.result.name() + " (" + replayValidationResult.updates + " updates), time elapsed: " + replayValidationResult.timeSpent + "s. (" + ((Object) StringFormatter.commaSeparatedNumber(replayValidationResult.updatesPerSecond)) + " u/s), real waves: " + replayValidationResult.realWaves + ", score: " + replayValidationResult.realScore, new Object[0]);
                        ObjectMap<String, String> objectMap = new ObjectMap<>();
                        objectMap.put(Attribute.ID_ATTR, replayValidationResult.replayRecord.id);
                        String str = "r:" + replayValidationResult.result.name() + ",u:" + replayValidationResult.updates + ",ts:" + replayValidationResult.timeSpent + ",ups:" + replayValidationResult.updatesPerSecond + ",rw:" + replayValidationResult.realWaves + ",rs:" + replayValidationResult.realScore + ",i:" + ((int) this.instanceNumber) + ",sw:" + fromCompactString.defeatedWaves + ",ss:" + fromCompactString.score;
                        if (replayValidationResult.result == GameStateSystem.ReplayValidationResult.Result.MODIFIED_GAME) {
                            str = str + ",chr:" + replayValidationResult.cheatingReason;
                        }
                        objectMap.put("log", str);
                        objectMap.put("valid", replayValidationResult.result == GameStateSystem.ReplayValidationResult.Result.VALID ? "true" : "false");
                        objectMap.put("xp", new StringBuilder().append(replayValidationResult.xp).toString());
                        objectMap.put("enemiesKilled", new StringBuilder().append(replayValidationResult.enemiesKilled).toString());
                        objectMap.put("resourcesMined", new StringBuilder().append(replayValidationResult.resourcesMined).toString());
                        try {
                            objectMap.put("gameMode", replayValidationResult.S.gameState.gameMode.name());
                            objectMap.put("difficultyMode", replayValidationResult.S.gameState.difficultyMode.name());
                            objectMap.put("modeDifficultyMultiplier", new StringBuilder().append(replayValidationResult.S.gameState.modeDifficultyMultiplier).toString());
                            if (replayValidationResult.S.gameState.basicLevelName != null) {
                                objectMap.put("basicLevelName", replayValidationResult.S.gameState.basicLevelName);
                            }
                        } catch (Exception unused) {
                        }
                        String stop = HeadlessReplayReportGenerator.stop(replayValidationResult.S);
                        if (stop != null) {
                            objectMap.put("report", stop);
                        }
                        Game.i.httpManager.post(Config.SITE_URL + "/?m=api&a=submitReplayValidation&v=208").params(objectMap).listener((z, httpResponse, z2, th) -> {
                            if (z) {
                                f1724a.i("Validation result for record " + replayValidationResult.replayRecord.id + " sent " + httpResponse.getResultAsString(), new Object[0]);
                            } else {
                                f1724a.e("Failed to send validation result for record " + replayValidationResult.replayRecord.id, th);
                            }
                        }).send();
                        b();
                    });
                };
                if (fromCompactString.gameMode == GameStateSystem.GameMode.BASIC_LEVELS && fromCompactString.levelName.startsWith(DailyQuestManager.LEVEL_NAME_PREFIX)) {
                    int parseInt = Integer.parseInt(fromCompactString.levelName.substring(2));
                    writeServerStatus("requestDailyQuestHash");
                    Game.i.dailyQuestManager.getDailyQuestHashFromServer(parseInt, str -> {
                        writeServerStatus("gotDailyQuestHash_" + (str != null));
                        if (str != null) {
                            Game.i.dailyQuestManager.removeLoadedDailyQuestMapIfMd5HashDiffers(parseInt, str);
                        }
                        if (Game.i.basicLevelManager.getLevel(fromCompactString.levelName) == null) {
                            writeServerStatus("loadDailyQuest");
                            Game.i.dailyQuestManager.loadAndStoreDailyQuestFromServer(parseInt, basicLevel -> {
                                writeServerStatus("saveDailyQuest");
                                f1724a.i("loaded " + fromCompactString.levelName + " from server", new Object[0]);
                                runnable.run();
                            });
                        } else {
                            runnable.run();
                        }
                    });
                    return;
                }
                runnable.run();
            } catch (Exception e2) {
                writeServerStatus("replayParseException");
                Game.i.httpManager.post(Config.SITE_URL + "/?m=api&a=submitReplayValidation&v=208").param(Attribute.ID_ATTR, this.currentReplay).param("log", "r:" + GameStateSystem.ReplayValidationResult.Result.INVALID_RECORD.name() + ",u:0,ts:0,ups:0,rw:0,rs:0,i:" + ((int) this.instanceNumber) + ",failed to parse json").param("valid", "false").send();
                f1724a.e("failed to parse record json for " + this.currentReplay + ":" + e2.getMessage(), e2);
                b();
            }
        });
    }

    private void a(ObjectConsumer<String> objectConsumer) {
        long timestampMillis = Game.getTimestampMillis();
        writeServerStatus("sendingReplayRequest");
        HttpManager.PreparedRequest timeOut = Game.i.httpManager.post(Config.SITE_URL + "/?m=api&a=getReplayForValidationV2&v=208").timeOut(5000);
        timeOut.param("protocol", "208");
        timeOut.param("validatorInstance", new StringBuilder().append((int) this.instanceNumber).toString());
        if (Gdx.files.local("endless.txt").exists()) {
            timeOut.param("endless", "true");
        }
        timeOut.listener((z, httpResponse, z2, th) -> {
            writeServerStatus("replayResponseReceived_" + z);
            if (z) {
                String resultAsString = httpResponse.getResultAsString();
                f1724a.i("got from server in: " + ((Object) StringFormatter.compactNumber(((float) (Game.getTimestampMillis() - timestampMillis)) * 0.001f, true)) + "s, " + resultAsString.length() + " chars", new Object[0]);
                try {
                    writeServerStatus("parsingReplay");
                    JsonValue parse = new JsonReader().parse(resultAsString);
                    writeServerStatus("parsedReplay");
                    if (parse.getString("status").equals("success")) {
                        this.currentReplay = parse.getString(Attribute.ID_ATTR);
                        f1724a.i("---- starting replay " + this.currentReplay, new Object[0]);
                        objectConsumer.accept(parse.getString("replay"));
                        return;
                    }
                    writeServerStatus("replayResponseInvalidStatus_" + parse.getString("status"));
                    throw new IllegalStateException("Status is not success: " + parse.getString("status"));
                } catch (Exception unused) {
                    writeServerStatus("replayResponseException");
                    objectConsumer.accept(null);
                    return;
                }
            }
            f1724a.e("Failed", th);
            writeServerStatus("replayResponseFailure");
            objectConsumer.accept(null);
        });
        timeOut.send();
    }

    public static void headlessValidateGame(ReplayManager.ReplayRecord replayRecord, ObjectConsumer<GameStateSystem.ReplayValidationResult> objectConsumer) {
        GameStateSystem.ReplayValidationResult.Result result;
        if (!Config.isCompatibleWithBuild(replayRecord.build)) {
            f1724a.i("Game is from build " + replayRecord.build + ", not compatible with 208", new Object[0]);
            objectConsumer.accept(new GameStateSystem.ReplayValidationResult(GameStateSystem.ReplayValidationResult.Result.INVALID_RECORD, 0.0f, 0, 0, 0, 0L, replayRecord));
            return;
        }
        try {
            ReplayManager.ReplayHeader fromBytes = ReplayManager.ReplayHeader.fromBytes(new Input(replayRecord.getStateBytes()));
            if (fromBytes.difficultyMode == DifficultyMode.NORMAL) {
                fromBytes.modeDifficultyMultiplier = 100;
            }
            fromBytes.progressSnapshot.validate();
            GameSystemProvider gameSystemProvider = new GameSystemProvider(new GameSystemProvider.SystemsConfig(GameSystemProvider.SystemsConfig.Setup.GAME, true));
            gameSystemProvider.createSystems();
            if (fromBytes.gameMode == GameStateSystem.GameMode.BASIC_LEVELS) {
                BasicLevel level = Game.i.basicLevelManager.getLevel(fromBytes.basicLevelName);
                if (level == null) {
                    throw new IllegalArgumentException("Level not found: " + fromBytes.basicLevelName);
                }
                if (!level.hasLeaderboards) {
                    f1724a.i("headlessValidateGame - basic level doesn't have leaderboards", new Object[0]);
                    objectConsumer.accept(new GameStateSystem.ReplayValidationResult(GameStateSystem.ReplayValidationResult.Result.INVALID_RECORD, 0.0f, 0, 0, 0, 0L, replayRecord));
                    return;
                } else {
                    if (level.forcedDifficulty != null && level.forcedDifficulty != fromBytes.difficultyMode) {
                        f1724a.i("headlessValidateGame - forced difficulty mismatch (" + level.forcedDifficulty + ", " + fromBytes.difficultyMode + ")", new Object[0]);
                        objectConsumer.accept(new GameStateSystem.ReplayValidationResult(GameStateSystem.ReplayValidationResult.Result.INVALID_RECORD, 0.0f, 0, 0, 0, 0L, replayRecord));
                        return;
                    }
                    GameScreen.configureSystemsBeforeSetup(gameSystemProvider, fromBytes.abilitiesConfiguration, fromBytes.canLootCases, fromBytes.lootBoostEnabled, fromBytes.rarityBoostEnabled, fromBytes.gameStartTs, level, null, fromBytes.difficultyMode, fromBytes.modeDifficultyMultiplier, GameStateSystem.GameMode.BASIC_LEVELS, null, fromBytes.progressSnapshot, fromBytes.inventoryStatistics, fromBytes.dailyQuestLevel);
                }
            } else if (fromBytes.gameMode == GameStateSystem.GameMode.USER_MAPS) {
                throw new IllegalArgumentException("User map validation not supported");
            }
            gameSystemProvider.setupSystems();
            gameSystemProvider.postSetupSystems();
            GameStateSystem gameStateSystem = gameSystemProvider.gameState;
            int clampModeDifficulty = ProgressManager.clampModeDifficulty(gameStateSystem.difficultyMode, fromBytes.modeDifficultyMultiplier, gameStateSystem.basicLevelName == null ? null : Game.i.basicLevelManager.getLevel(gameStateSystem.basicLevelName), gameSystemProvider.map.getMap().getTargetTileOrThrow().isUseStockGameValues(), gameSystemProvider.gameState.userMapId != null, fromBytes.progressSnapshot);
            if (clampModeDifficulty != fromBytes.modeDifficultyMultiplier) {
                f1724a.i("headlessValidateGame - mode difficulty incorrect: " + fromBytes.modeDifficultyMultiplier + " should be " + clampModeDifficulty, new Object[0]);
                objectConsumer.accept(new GameStateSystem.ReplayValidationResult(GameStateSystem.ReplayValidationResult.Result.INVALID_RECORD, 0.0f, 0, 0, 0, 0L, replayRecord));
                return;
            }
            if (Game.i.researchManager.getUnusedStarsCount() < 0) {
                f1724a.i("headlessValidateGame - unused research stars " + Game.i.researchManager.getUnusedStarsCount(), new Object[0]);
                objectConsumer.accept(new GameStateSystem.ReplayValidationResult(GameStateSystem.ReplayValidationResult.Result.INVALID_RECORD, 0.0f, 0, 0, 0, 0L, replayRecord));
                return;
            }
            if (gameStateSystem.getSeed() != fromBytes.seed) {
                f1724a.i("headlessValidateGame - seeds don't match: " + gameStateSystem.getSeed() + SequenceUtils.SPACE + fromBytes.seed, new Object[0]);
                objectConsumer.accept(new GameStateSystem.ReplayValidationResult(GameStateSystem.ReplayValidationResult.Result.INVALID_RECORD, 0.0f, 0, 0, 0, 0L, replayRecord));
                return;
            }
            gameStateSystem.playRealTime = fromBytes.playRealTime;
            gameStateSystem.headlessValidatedReplayRecord = replayRecord;
            gameStateSystem.validationStartTimestamp = Game.getTimestampMillis();
            gameStateSystem.validationLastUpdateNumber = (int) fromBytes.updateNumber;
            for (int i = 0; i < fromBytes.actionsCount; i++) {
                StateSystem.ActionUpdatePair actionUpdatePair = fromBytes.actions.get(i);
                gameStateSystem.pushAction(actionUpdatePair.action, actionUpdatePair.update);
            }
            f1724a.i("replay is " + fromBytes.updateNumber + " frames long, " + replayRecord.defeatedWaves + " waves and " + replayRecord.score + " score", new Object[0]);
            HeadlessReplayReportGenerator.start(gameSystemProvider);
            while (!gameSystemProvider.gameState.isGameOver() && gameSystemProvider.gameState.updateNumber < fromBytes.updateNumber) {
                try {
                    gameSystemProvider.updateSystems();
                } catch (Exception e) {
                    f1724a.i("headlessValidateGame - simulation failed", e);
                    float timestampMillis = ((float) (Game.getTimestampMillis() - gameSystemProvider.gameState.validationStartTimestamp)) * 0.001f;
                    objectConsumer.accept(new GameStateSystem.ReplayValidationResult(GameStateSystem.ReplayValidationResult.Result.INVALID_RECORD, timestampMillis, (int) (gameSystemProvider.gameState.updateNumber / timestampMillis), gameSystemProvider.gameState.updateNumber, gameSystemProvider.wave.getCompletedWavesCount(), gameSystemProvider.gameState.getScore(), replayRecord));
                    return;
                }
            }
            f1724a.i("finished successfully", new Object[0]);
            float timestampMillis2 = ((float) (Game.getTimestampMillis() - gameSystemProvider.gameState.validationStartTimestamp)) * 0.001f;
            int i2 = (int) (gameSystemProvider.gameState.updateNumber / timestampMillis2);
            if (!gameSystemProvider.gameState.isMaxEndlessReplayTimeReached()) {
                if (gameSystemProvider.gameState.headlessValidatedReplayRecord.defeatedWaves != gameSystemProvider.wave.getCompletedWavesCount()) {
                    result = GameStateSystem.ReplayValidationResult.Result.INVALID;
                } else if (gameSystemProvider.gameState.headlessValidatedReplayRecord.score != gameSystemProvider.gameState.getScore()) {
                    result = GameStateSystem.ReplayValidationResult.Result.INVALID;
                } else if (gameSystemProvider.gameState.validationFingerprintMismatchPrinted) {
                    result = GameStateSystem.ReplayValidationResult.Result.INVALID;
                }
                GameStateSystem.ReplayValidationResult replayValidationResult = new GameStateSystem.ReplayValidationResult(result, timestampMillis2, i2, gameSystemProvider.gameState.updateNumber, gameSystemProvider.wave.getCompletedWavesCount(), gameSystemProvider.gameState.getScore(), gameSystemProvider.gameState.headlessValidatedReplayRecord);
                replayValidationResult.xp = gameSystemProvider.gameState.pxpExperience;
                replayValidationResult.enemiesKilled = (int) gameSystemProvider.statistics.getStatistic(StatisticsType.EK);
                replayValidationResult.resourcesMined = (int) gameSystemProvider.statistics.getStatistic(StatisticsType.RG);
                replayValidationResult.S = gameSystemProvider;
                objectConsumer.accept(replayValidationResult);
            }
            f1724a.i("max duration of endless replay reached", new Object[0]);
            result = GameStateSystem.ReplayValidationResult.Result.VALID;
            GameStateSystem.ReplayValidationResult replayValidationResult2 = new GameStateSystem.ReplayValidationResult(result, timestampMillis2, i2, gameSystemProvider.gameState.updateNumber, gameSystemProvider.wave.getCompletedWavesCount(), gameSystemProvider.gameState.getScore(), gameSystemProvider.gameState.headlessValidatedReplayRecord);
            replayValidationResult2.xp = gameSystemProvider.gameState.pxpExperience;
            replayValidationResult2.enemiesKilled = (int) gameSystemProvider.statistics.getStatistic(StatisticsType.EK);
            replayValidationResult2.resourcesMined = (int) gameSystemProvider.statistics.getStatistic(StatisticsType.RG);
            replayValidationResult2.S = gameSystemProvider;
            objectConsumer.accept(replayValidationResult2);
        } catch (Exception e2) {
            f1724a.i("headlessValidateGame - parsing failed", e2);
            objectConsumer.accept(new GameStateSystem.ReplayValidationResult(GameStateSystem.ReplayValidationResult.Result.INVALID_RECORD, 0.0f, 0, 0, 0, 0L, replayRecord));
        }
    }
}
