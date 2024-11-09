package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntFloatMap;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.kryo.FixedInput;
import com.prineside.kryo.FixedOutput;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.managers.AuthManager;
import com.prineside.tdi2.managers.DailyQuestManager;
import com.prineside.tdi2.managers.NetworkManager;
import com.prineside.tdi2.managers.PreferencesManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.preferences.categories.SettingsPrefs;
import com.prineside.tdi2.managers.preferences.categories.settings.SP_Replay;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.systems.StateSystem;
import com.prineside.tdi2.systems.StatisticsSystem;
import com.prineside.tdi2.ui.shared.AbilitySelectionOverlay;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Iterator;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ReplayManager.class */
public class ReplayManager extends Manager.ManagerAdapter {

    /* renamed from: b, reason: collision with root package name */
    private final ObjectMap<String, SoftReference<ReplayRecord>> f2428b = new ObjectMap<>();
    private int c = 1;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2427a = TLog.forClass(ReplayManager.class);
    public static FixedOutput helperOutput = new FixedOutput(65536, -1);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ReplayManager$LeaderboardsMode.class */
    public enum LeaderboardsMode {
        score,
        waves;

        public static final LeaderboardsMode[] values = values();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ReplayManager$ReplaySendStatus.class */
    public static class ReplaySendStatus {
        public int regularXpGained;
        public int bonusXpGained;
        public boolean bonusXpLeft;
        public boolean regularXpLeft;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ReplayManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<ReplayManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public ReplayManager read() {
            return Game.i.replayManager;
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        if (!Config.isHeadless()) {
            Game.i.preferencesManager.addListener(new PreferencesManager.PreferencesManagerListener.PreferencesManagerListenerAdapter() { // from class: com.prineside.tdi2.managers.ReplayManager.1
                @Override // com.prineside.tdi2.managers.PreferencesManager.PreferencesManagerListener.PreferencesManagerListenerAdapter, com.prineside.tdi2.managers.PreferencesManager.PreferencesManagerListener
                public void reloaded() {
                    ReplayManager.this.b();
                }
            });
            Game.i.authManager.getNews(newsResponse -> {
                if (newsResponse != null) {
                    this.c = newsResponse.networkRequiredVersion;
                }
                sendUnsentReplaysToTheServer();
            });
        }
        b();
    }

    public void deleteAllReplays() {
        Array<String> allRecordIds = getAllRecordIds();
        for (int i = 0; i < allRecordIds.size; i++) {
            Gdx.files.local(PreferencesManager.getReplaysDirPath() + allRecordIds.items[i] + ".rpl").delete();
        }
        SettingsPrefs.i().replay.sentReplayIds.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.f2428b.clear();
    }

    private void c() {
        Array<String> allRecordIds = getAllRecordIds();
        ObjectSet objectSet = new ObjectSet();
        objectSet.addAll(allRecordIds);
        Array<String> array = SettingsPrefs.i().replay.sentReplayIds;
        Array array2 = new Array(true, 1, String.class);
        f2427a.i("checking " + array.size + " replay records for existence on disk", new Object[0]);
        f2427a.i(array.toString(", "), new Object[0]);
        for (int i = 0; i < array.size; i++) {
            String str = array.get(i);
            if (!objectSet.contains(str)) {
                array2.add(str);
            }
        }
        if (array2.size != 0) {
            f2427a.i("removing " + array2.size + " replay IDs from the list of sent replays - no replay file exists on disk", new Object[0]);
            for (int i2 = 0; i2 < array2.size; i2++) {
                array.removeValue((String) array2.get(i2), false);
            }
            SettingsPrefs.i().requireSave();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void d() {
        Array array = new Array(String.class);
        Array<String> allRecordIds = getAllRecordIds();
        IntMap intMap = new IntMap();
        for (int i = 0; i < allRecordIds.size; i++) {
            ReplayRecord record = getRecord(allRecordIds.items[i]);
            if (record != null && Config.isCompatibleWithBuild(record.build)) {
                int ordinal = (((record.difficultyMode.ordinal() * 31) + record.gameMode.ordinal()) * 31) + (record.levelName == null ? 0 : record.levelName.hashCode());
                ObjectPair objectPair = (ObjectPair) intMap.get(ordinal, null);
                if (objectPair == null || ((Long) objectPair.second).longValue() < record.score) {
                    intMap.put(ordinal, new ObjectPair(record.id, Long.valueOf(record.score)));
                }
            }
        }
        Iterator it = intMap.iterator();
        while (it.hasNext()) {
            array.add((String) ((ObjectPair) ((IntMap.Entry) it.next()).value).first);
        }
        f2427a.i("preserving " + array.size + " replays", new Object[0]);
        Array array2 = new Array(ObjectPair.class);
        for (int i2 = 0; i2 < allRecordIds.size; i2++) {
            ReplayRecord record2 = getRecord(allRecordIds.items[i2]);
            if (!array.contains(record2.id, true)) {
                array2.add(new ObjectPair(record2.id, Long.valueOf(record2.saveTimestamp)));
            }
        }
        array2.sort((objectPair2, objectPair3) -> {
            return Long.compare(((Long) objectPair3.second).longValue(), ((Long) objectPair2.second).longValue());
        });
        for (int i3 = 20; i3 < array2.size; i3++) {
            String str = (String) ((ObjectPair[]) array2.items)[i3].first;
            this.f2428b.remove(str);
            try {
                Gdx.files.local(PreferencesManager.getReplaysDirPath() + str + ".rpl").delete();
                f2427a.i("removed " + str + " as obsolete", new Object[0]);
            } catch (Exception unused) {
            }
        }
    }

    public void loadAndStoreBestReplayFromServer(final String str, final ObjectConsumer<ReplayRecord> objectConsumer) {
        f2427a.i("requesting best replay from server for level " + str, new Object[0]);
        if (Game.i.authManager.getSignInStatus() != AuthManager.SignInStatus.SIGNED_IN) {
            f2427a.w("player is not signed in, skipping best replay request", new Object[0]);
            return;
        }
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.GET_BEST_GAME_REPLAY_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("map", str);
        hashMap.put("sessionid", Game.i.authManager.getSessionId());
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.managers.ReplayManager.2
            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                try {
                    String resultAsString = httpResponse.getResultAsString();
                    JsonValue parse = new JsonReader().parse(resultAsString);
                    if (!parse.getString("status", "error").equals("success")) {
                        ReplayManager.f2427a.w("Failed to load best replay from server: %s", resultAsString);
                        return;
                    }
                    Threads i = Threads.i();
                    String str2 = str;
                    ObjectConsumer objectConsumer2 = objectConsumer;
                    i.runOnMainThread(() -> {
                        try {
                            SP_Replay sP_Replay = SettingsPrefs.i().replay;
                            ReplayRecord fromCompactString = ReplayRecord.fromCompactString(parse.getString("replay"));
                            if (ReplayManager.this.getRecord(fromCompactString.id) != null) {
                                ReplayManager.f2427a.i("skilled loading best replay from server (" + str2 + ", " + fromCompactString.id + ") - already stored locally", new Object[0]);
                                return;
                            }
                            if (fromCompactString.chartFrames.version != 2) {
                                ReplayManager.f2427a.i("skilled loading best replay from server (" + str2 + ", " + fromCompactString.id + ") - chartFrames version differ (" + fromCompactString.chartFrames.version + ", 2)", new Object[0]);
                                return;
                            }
                            fromCompactString.saveLocally();
                            boolean z = false;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= sP_Replay.sentReplayIds.size) {
                                    break;
                                }
                                if (!sP_Replay.sentReplayIds.items[i2].equals(fromCompactString.id)) {
                                    i2++;
                                } else {
                                    z = true;
                                    break;
                                }
                            }
                            if (!z) {
                                sP_Replay.sentReplayIds.add(fromCompactString.id);
                                SettingsPrefs.i().requireSave();
                            }
                            if (objectConsumer2 != null) {
                                objectConsumer2.accept(fromCompactString);
                            }
                        } catch (Exception e) {
                            ReplayManager.f2427a.e("failed to load best replay from server", e);
                        }
                    });
                } catch (Exception e) {
                    ReplayManager.f2427a.e("Exception: " + e.getMessage(), e);
                }
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void failed(Throwable th) {
                ReplayManager.f2427a.e("Failed", th);
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void cancelled() {
                ReplayManager.f2427a.e(Transaction.REVERSAL_TEXT_CANCELLED, new Object[0]);
            }
        });
    }

    public String saveReplay(GameSystemProvider gameSystemProvider) {
        ReplayRecord fromState = ReplayRecord.fromState(gameSystemProvider);
        fromState.saveLocally();
        this.f2428b.put(fromState.id, new SoftReference<>(fromState));
        return fromState.id;
    }

    public void sendReplayToServer(final String str, final ObjectConsumer<ReplaySendStatus> objectConsumer) {
        if (!Game.i.authManager.isSignedIn()) {
            f2427a.w("replay won't be sent - not signed in", new Object[0]);
            return;
        }
        if (Game.i.progressManager.isDeveloperModeEnabled()) {
            f2427a.i("skipped sendReplayToServer - dev mode", new Object[0]);
            return;
        }
        ReplayRecord record = getRecord(str);
        if (record == null) {
            f2427a.e("can't get record " + str, new Object[0]);
            return;
        }
        if (record.getStateBytes() == null) {
            f2427a.e(str + " is cleaned up and no longer has a state", new Object[0]);
            return;
        }
        if (record.gameMode == GameStateSystem.GameMode.USER_MAPS) {
            f2427a.e(str + " is a custom map", new Object[0]);
            return;
        }
        if (record.difficultyMode != DifficultyMode.NORMAL && record.difficultyMode != DifficultyMode.ENDLESS_I) {
            f2427a.e(str + " has " + record.difficultyMode + " difficulty, won't be sent to the server", new Object[0]);
            SettingsPrefs.i().replay.sentReplayIds.add(str);
            SettingsPrefs.i().requireSave();
            return;
        }
        if (GameStateSystem.GameMode.isBasicLevel(record.gameMode)) {
            try {
                if (!Game.i.basicLevelManager.getLevel(record.levelName).hasLeaderboards) {
                    f2427a.e(str + " hasn't leaderboards, skipping", new Object[0]);
                    SettingsPrefs.i().replay.sentReplayIds.add(str);
                    SettingsPrefs.i().requireSave();
                    return;
                }
            } catch (Exception e) {
                f2427a.e("failed to get level " + record.levelName, e);
                SettingsPrefs.i().replay.sentReplayIds.add(str);
                SettingsPrefs.i().requireSave();
                return;
            }
        }
        f2427a.i(str + " is not sent yet", new Object[0]);
        try {
            if (record.build < this.c) {
                f2427a.i(str + " has too low version, skipping", new Object[0]);
                SettingsPrefs.i().replay.sentReplayIds.add(str);
                SettingsPrefs.i().requireSave();
                return;
            }
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.GAME_REPLAY_REPORT_URL);
            Json json = new Json(JsonWriter.OutputType.json);
            StringWriter stringWriter = new StringWriter();
            json.setWriter(stringWriter);
            json.writeObjectStart();
            json.writeValue("build", Integer.valueOf(record.build));
            json.writeValue(Attribute.ID_ATTR, record.id);
            json.writeValue("os", Gdx.app.getType().name());
            json.writeValue("playRealTime", Integer.valueOf(record.playRealTime));
            json.writeValue("gameMode", record.gameMode.name());
            json.writeValue("difficultyMode", record.difficultyMode.name());
            json.writeValue("modeDifficultyMultiplier", Integer.valueOf(record.modeDifficultyMultiplier));
            if (record.levelName != null) {
                json.writeValue("levelName", record.levelName);
            }
            json.writeValue("profileXp", Integer.valueOf(record.profileXp));
            json.writeValue("defeatedWaves", Integer.valueOf(record.defeatedWaves));
            json.writeValue("score", Long.valueOf(record.score));
            json.writeValue("record", record.toCompactString());
            json.writeObjectEnd();
            HashMap hashMap = new HashMap();
            hashMap.put("report", stringWriter.toString());
            hashMap.put("sessionid", Game.i.authManager.getSessionId());
            httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
            Game.getRealTickCount();
            Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.managers.ReplayManager.3
                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    try {
                        String resultAsString = httpResponse.getResultAsString();
                        ReplayManager.f2427a.i(resultAsString, new Object[0]);
                        JsonValue parse = new JsonReader().parse(resultAsString);
                        if (!parse.getString("status", "error").equals("success")) {
                            ReplayManager.f2427a.e("Error: " + resultAsString, new Object[0]);
                            return;
                        }
                        Threads i = Threads.i();
                        String str2 = str;
                        ObjectConsumer objectConsumer2 = objectConsumer;
                        i.runOnMainThread(() -> {
                            ReplayManager.f2427a.i("Success: " + resultAsString, new Object[0]);
                            SettingsPrefs.i().replay.sentReplayIds.add(str2);
                            SettingsPrefs.i().requireSave();
                            if (objectConsumer2 != null && parse.get("xpGained") != null) {
                                ReplaySendStatus replaySendStatus = new ReplaySendStatus();
                                replaySendStatus.regularXpGained = parse.get("xpGained").getInt("regular", 0);
                                replaySendStatus.bonusXpGained = parse.get("xpGained").getInt("bonus", 0);
                                replaySendStatus.bonusXpLeft = parse.get("xpGained").getBoolean("bonusLeft", false);
                                replaySendStatus.regularXpLeft = parse.get("xpGained").getBoolean("regularLeft", true);
                                objectConsumer2.accept(replaySendStatus);
                            }
                            ReplayManager.this.d();
                        });
                    } catch (Exception e2) {
                        ReplayManager.f2427a.e("Exception: " + e2.getMessage(), e2);
                    }
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void failed(Throwable th) {
                    ReplayManager.f2427a.e("Failed", th);
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void cancelled() {
                    ReplayManager.f2427a.e(Transaction.REVERSAL_TEXT_CANCELLED, new Object[0]);
                }
            });
        } catch (Exception e2) {
            f2427a.i("Failed to send replay to the server (" + e2.getMessage() + ")", new Object[0]);
        }
    }

    @Null
    public ReplayRecord getRecord(String str) {
        if (this.f2428b.containsKey(str)) {
            ReplayRecord replayRecord = this.f2428b.get(str).get();
            if (replayRecord != null) {
                return replayRecord;
            }
            this.f2428b.remove(str);
        }
        if (str.contains(".")) {
            throw new IllegalArgumentException("Replay ID should not contain dots, " + str + " given");
        }
        FileHandle local = Gdx.files.local(PreferencesManager.getReplaysDirPath() + str + ".rpl");
        if (local.exists() && !local.isDirectory()) {
            try {
                byte[] readBytes = local.readBytes();
                if (readBytes.length == 0) {
                    throw new IllegalStateException("zero bytes read");
                }
                ReplayRecord fromBytes = ReplayRecord.fromBytes(readBytes, 0, readBytes.length);
                this.f2428b.put(str, new SoftReference<>(fromBytes));
                return fromBytes;
            } catch (Exception e) {
                f2427a.e("failed to load replay record - removing", e);
                this.f2428b.remove(str);
                try {
                    local.delete();
                    return null;
                } catch (Exception unused) {
                    return null;
                }
            }
        }
        return null;
    }

    public Array<String> getAllRecordIds() {
        Array<String> array = new Array<>(String.class);
        FileHandle local = Gdx.files.local(PreferencesManager.getReplaysDirPath());
        if (local.exists() && local.isDirectory()) {
            for (FileHandle fileHandle : local.list()) {
                if (fileHandle.name().endsWith(".rpl")) {
                    array.add(fileHandle.name().substring(0, fileHandle.name().length() - 4));
                }
            }
        }
        return array;
    }

    public void sendUnsentReplaysToTheServer() {
        if (Game.i.progressManager.isDeveloperModeEnabled()) {
            f2427a.i("skipped sendUnsentReplaysToTheServer - dev mode", new Object[0]);
            return;
        }
        if (Config.isHeadless() || Game.i.actionResolver.isAppModified() || Config.isModdingMode() || !Game.i.authManager.isSignedIn()) {
            return;
        }
        f2427a.i("sending unsent replays...", new Object[0]);
        int i = 0;
        Array<String> allRecordIds = getAllRecordIds();
        for (int i2 = 0; i2 < allRecordIds.size; i2++) {
            String str = allRecordIds.get(i2);
            if (!SettingsPrefs.i().replay.sentReplayIds.contains(str, false)) {
                i++;
                sendReplayToServer(str, null);
            }
            if (i == 100) {
                break;
            }
        }
        d();
        c();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ReplayManager$ReplayHeader.class */
    public static class ReplayHeader {
        public int build;
        public DifficultyMode difficultyMode;
        public long seed;
        public int modeDifficultyMultiplier;
        public GameStateSystem.GameMode gameMode;
        public AbilitySelectionOverlay.SelectedAbilitiesConfiguration abilitiesConfiguration;
        public boolean canLootCases;
        public boolean lootBoostEnabled;
        public boolean rarityBoostEnabled;

        @Null
        public String basicLevelName;

        @Null
        public String userMapId;

        @Null
        public BossType[] customMapBossTypes;
        public long gameStartTs;
        public ProgressManager.ProgressSnapshotForState progressSnapshot;
        public ProgressManager.InventoryStatistics inventoryStatistics;

        @Null
        public DailyQuestManager.DailyQuestLevel dailyQuestLevel;
        public float playRealTime;
        public long updateNumber;
        public int continuedGameApproxStateHash;
        public int actionsCount;
        public int userMapSeed = 0;
        public Array<StateSystem.ActionUpdatePair> actions = new Array<>(true, 1, StateSystem.ActionUpdatePair.class);

        private ReplayHeader() {
        }

        public static ReplayHeader fromBytes(Input input) {
            ReplayHeader replayHeader = new ReplayHeader();
            try {
                input.setPosition(0);
                NetworkManager.KryoForState fullKryo = Game.i.networkManager.getFullKryo();
                replayHeader.build = input.readVarInt(true);
                replayHeader.difficultyMode = (DifficultyMode) fullKryo.readObject(input, DifficultyMode.class);
                replayHeader.seed = input.readLong();
                replayHeader.modeDifficultyMultiplier = input.readVarInt(true);
                replayHeader.gameMode = (GameStateSystem.GameMode) fullKryo.readObject(input, GameStateSystem.GameMode.class);
                replayHeader.abilitiesConfiguration = (AbilitySelectionOverlay.SelectedAbilitiesConfiguration) fullKryo.readObjectOrNull(input, AbilitySelectionOverlay.SelectedAbilitiesConfiguration.class);
                replayHeader.canLootCases = input.readBoolean();
                replayHeader.lootBoostEnabled = input.readBoolean();
                replayHeader.rarityBoostEnabled = input.readBoolean();
                replayHeader.basicLevelName = null;
                replayHeader.userMapId = null;
                replayHeader.customMapBossTypes = null;
                if (replayHeader.gameMode == GameStateSystem.GameMode.BASIC_LEVELS) {
                    replayHeader.basicLevelName = input.readString();
                } else if (replayHeader.gameMode == GameStateSystem.GameMode.USER_MAPS) {
                    replayHeader.userMapId = input.readString();
                    replayHeader.userMapSeed = input.readInt();
                    replayHeader.customMapBossTypes = (BossType[]) fullKryo.readObjectOrNull(input, BossType[].class);
                }
                replayHeader.gameStartTs = input.readLong();
                replayHeader.progressSnapshot = (ProgressManager.ProgressSnapshotForState) fullKryo.readObject(input, ProgressManager.ProgressSnapshotForState.class);
                replayHeader.inventoryStatistics = (ProgressManager.InventoryStatistics) fullKryo.readObject(input, ProgressManager.InventoryStatistics.class);
                replayHeader.dailyQuestLevel = (DailyQuestManager.DailyQuestLevel) fullKryo.readObjectOrNull(input, DailyQuestManager.DailyQuestLevel.class);
                replayHeader.playRealTime = input.readFloat();
                replayHeader.updateNumber = input.readVarInt(true);
                replayHeader.continuedGameApproxStateHash = input.readInt();
                replayHeader.actionsCount = input.readVarInt(true);
                for (int i = 0; i < replayHeader.actionsCount; i++) {
                    replayHeader.actions.add((StateSystem.ActionUpdatePair) fullKryo.readObject(input, StateSystem.ActionUpdatePair.class));
                }
                return replayHeader;
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid replay format", e);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ReplayManager$ReplayRecord.class */
    public static class ReplayRecord {
        public byte version;
        public int build;
        public String id;
        public GameStateSystem.GameMode gameMode;
        public DifficultyMode difficultyMode = DifficultyMode.NORMAL;
        public int modeDifficultyMultiplier;
        public String levelName;
        public int playRealTime;
        public int defeatedWaves;
        public long score;
        public long startTimestamp;
        public long saveTimestamp;
        public int profileXp;
        public IntFloatMap statistics;
        public ChartFrames chartFrames;

        /* renamed from: a, reason: collision with root package name */
        private byte[] f2435a;

        public static ReplayRecord fromState(GameSystemProvider gameSystemProvider) {
            ReplayRecord replayRecord = new ReplayRecord();
            replayRecord.build = 208;
            replayRecord.id = gameSystemProvider.gameState.replayId;
            replayRecord.gameMode = gameSystemProvider.gameState.gameMode;
            replayRecord.difficultyMode = gameSystemProvider.gameState.difficultyMode;
            replayRecord.modeDifficultyMultiplier = gameSystemProvider.gameState.modeDifficultyMultiplier;
            if (GameStateSystem.GameMode.isBasicLevel(gameSystemProvider.gameState.gameMode)) {
                replayRecord.levelName = gameSystemProvider.gameState.basicLevelName;
            } else if (gameSystemProvider.gameState.gameMode == GameStateSystem.GameMode.USER_MAPS) {
                replayRecord.levelName = gameSystemProvider.gameState.userMapId;
            }
            replayRecord.playRealTime = (int) gameSystemProvider.gameState.playRealTime;
            replayRecord.defeatedWaves = gameSystemProvider.wave.getCompletedWavesCount();
            replayRecord.score = gameSystemProvider.gameState.getScore();
            replayRecord.startTimestamp = gameSystemProvider.gameState.gameStartTimestamp;
            replayRecord.saveTimestamp = Game.getTimestampMillis();
            replayRecord.profileXp = gameSystemProvider.gameState.pxpExperience;
            replayRecord.statistics = new IntFloatMap();
            double[] currentGameStatistics = gameSystemProvider.statistics.getCurrentGameStatistics();
            for (StatisticsType statisticsType : StatisticsType.values) {
                if (currentGameStatistics[statisticsType.ordinal()] != 0.0d) {
                    replayRecord.statistics.put(statisticsType.ordinal(), (float) currentGameStatistics[statisticsType.ordinal()]);
                }
            }
            replayRecord.chartFrames = gameSystemProvider.statistics.getCurrentReplayChart().cpy();
            replayRecord.f2435a = gameSystemProvider.gameState.getStateBytes().toBytes();
            return replayRecord;
        }

        public byte[] toBytes() {
            FixedOutput fixedOutput = ReplayManager.helperOutput;
            fixedOutput.clear();
            fixedOutput.writeInt(152630033);
            fixedOutput.writeByte(4);
            fixedOutput.writeVarInt(this.build, true);
            fixedOutput.writeString(this.id);
            fixedOutput.writeVarInt(this.gameMode.ordinal(), true);
            fixedOutput.writeVarInt(this.difficultyMode.ordinal(), true);
            fixedOutput.writeVarInt(this.modeDifficultyMultiplier, true);
            fixedOutput.writeString(this.levelName);
            fixedOutput.writeVarInt(this.playRealTime, true);
            fixedOutput.writeVarInt(this.defeatedWaves, true);
            fixedOutput.writeVarLong(this.score, true);
            fixedOutput.writeLong(this.startTimestamp);
            fixedOutput.writeLong(this.saveTimestamp);
            fixedOutput.writeVarInt(this.profileXp, true);
            fixedOutput.writeVarInt(this.statistics.size, true);
            IntFloatMap.Keys keys = this.statistics.keys();
            while (keys.hasNext) {
                int next = keys.next();
                fixedOutput.writeVarInt(next, true);
                fixedOutput.writeFloat(this.statistics.get(next, 0.0f));
            }
            this.chartFrames.write(fixedOutput);
            fixedOutput.writeVarInt(this.f2435a.length, true);
            fixedOutput.writeBytes(this.f2435a);
            return fixedOutput.toBytes();
        }

        private void a(FixedInput fixedInput) {
            int position = fixedInput.position();
            byte b2 = 1;
            if (fixedInput.readInt() == 152630033) {
                b2 = fixedInput.readByte();
            } else {
                fixedInput.setPosition(position);
            }
            this.build = fixedInput.readVarInt(true);
            this.id = fixedInput.readString();
            this.gameMode = GameStateSystem.GameMode.values[fixedInput.readVarInt(true)];
            this.difficultyMode = DifficultyMode.values[fixedInput.readVarInt(true)];
            this.modeDifficultyMultiplier = b2 >= 2 ? fixedInput.readVarInt(true) : 100;
            this.levelName = fixedInput.readString();
            this.playRealTime = fixedInput.readVarInt(true);
            this.defeatedWaves = fixedInput.readVarInt(true);
            this.score = fixedInput.readVarLong(true);
            this.startTimestamp = fixedInput.readLong();
            this.saveTimestamp = fixedInput.readLong();
            this.profileXp = fixedInput.readVarInt(true);
            int readVarInt = fixedInput.readVarInt(true);
            this.statistics = new IntFloatMap(readVarInt);
            for (int i = 0; i < readVarInt; i++) {
                this.statistics.put(fixedInput.readVarInt(true), fixedInput.readFloat());
            }
            this.chartFrames = ChartFrames.fromBytes(fixedInput);
            this.f2435a = fixedInput.readBytes(fixedInput.readVarInt(true));
        }

        public static ReplayRecord fromBytes(byte[] bArr, int i, int i2) {
            ReplayRecord replayRecord = new ReplayRecord();
            replayRecord.a(new FixedInput(bArr, i, i2));
            return replayRecord;
        }

        public static ReplayRecord fromCompactString(String str) {
            ReplayRecord replayRecord = new ReplayRecord();
            replayRecord.a(new FixedInput(StringFormatter.fromCompactBase64(str)));
            return replayRecord;
        }

        public byte[] getStateBytes() {
            return this.f2435a;
        }

        public void saveLocally() {
            Gdx.files.local(PreferencesManager.getReplaysDirPath() + this.id + ".rpl").writeBytes(toBytes(), false);
            ReplayManager.f2427a.i("saved " + this.id + " locally", new Object[0]);
        }

        public String toCompactString() {
            byte[] bytes = toBytes();
            return StringFormatter.toCompactBase64(bytes, 0, bytes.length);
        }

        @REGS
        /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ReplayManager$ReplayRecord$ChartFrames.class */
        public static class ChartFrames implements KryoSerializable {
            public static final int MAX_FRAME_COUNT = 2000;
            public int version = 2;
            public Array<FrameValues> frames = new Array<>(true, 1, FrameValues.class);

            /* renamed from: a, reason: collision with root package name */
            private static final FixedOutput f2436a = new FixedOutput(4096, -1);

            /* renamed from: b, reason: collision with root package name */
            private static final FixedOutput f2437b = new FixedOutput(1024, -1);
            private static final FixedInput c = new FixedInput();
            private static final FixedInput d = new FixedInput();

            public void write(FixedOutput fixedOutput) {
                fixedOutput.writeVarInt(this.version, true);
                f2437b.clear();
                f2437b.writeVarInt(this.frames.size, true);
                for (int i = 0; i < this.frames.size; i++) {
                    this.frames.items[i].write(f2437b);
                }
                fixedOutput.writeVarInt(f2437b.position(), true);
                fixedOutput.writeBytes(f2437b.getBuffer(), 0, f2437b.position());
            }

            public void read(FixedInput fixedInput) {
                this.version = fixedInput.readVarInt(true);
                byte[] readBytes = fixedInput.readBytes(fixedInput.readVarInt(true));
                this.frames.clear();
                if (this.version != 2) {
                    ReplayManager.f2427a.i("skipped reading replay ChartFrames data - version mismatch (" + this.version + ", 2)", new Object[0]);
                    return;
                }
                c.setBuffer(readBytes);
                int readVarInt = c.readVarInt(true);
                for (int i = 0; i < readVarInt; i++) {
                    FrameValues frameValues = new FrameValues();
                    frameValues.read(c);
                    this.frames.add(frameValues);
                }
            }

            @Override // com.esotericsoftware.kryo.KryoSerializable
            public void write(Kryo kryo, Output output) {
                f2436a.clear();
                write(f2436a);
                output.writeVarInt(f2436a.position(), true);
                output.writeBytes(f2436a.getBuffer(), 0, f2436a.position());
            }

            @Override // com.esotericsoftware.kryo.KryoSerializable
            public void read(Kryo kryo, Input input) {
                d.setBuffer(input.readBytes(input.readVarInt(true)));
                read(d);
            }

            public static ChartFrames fromBytes(FixedInput fixedInput) {
                ChartFrames chartFrames = new ChartFrames();
                try {
                    chartFrames.read(fixedInput);
                } catch (Exception e) {
                    ReplayManager.f2427a.e("failed to load chart frames, fallback", e);
                    chartFrames = new ChartFrames();
                }
                return chartFrames;
            }

            public ChartFrames cpy() {
                ChartFrames chartFrames = new ChartFrames();
                chartFrames.version = this.version;
                for (int i = 0; i < this.frames.size; i++) {
                    chartFrames.frames.add(this.frames.items[i].cpy());
                }
                return chartFrames;
            }

            public void addFrame(GameSystemProvider gameSystemProvider) {
                if (this.frames.size < 2000) {
                    this.frames.add(generateFrameValues(gameSystemProvider).cpy());
                }
            }

            public static FrameValues generateFrameValues(GameSystemProvider gameSystemProvider) {
                StatisticsSystem statisticsSystem = gameSystemProvider.statistics;
                FrameValues frameValues = new FrameValues();
                frameValues.cBounties = (int) statisticsSystem.getCurrentGameStatistic(StatisticsType.CG_B);
                frameValues.cKilledEnemies = (int) statisticsSystem.getCurrentGameStatistic(StatisticsType.CG_EK);
                frameValues.cWaveCalls = (int) statisticsSystem.getCurrentGameStatistic(StatisticsType.CG_WC);
                frameValues.cOther = ((int) statisticsSystem.getCurrentGameStatistic(StatisticsType.CG_PG)) + ((int) statisticsSystem.getCurrentGameStatistic(StatisticsType.CG_U));
                frameValues.sKilledEnemies = (long) statisticsSystem.getCurrentGameStatistic(StatisticsType.SG_EK);
                frameValues.sMining = (long) statisticsSystem.getCurrentGameStatistic(StatisticsType.SG_RM);
                frameValues.sWaveCalls = (long) statisticsSystem.getCurrentGameStatistic(StatisticsType.SG_WCA);
                frameValues.sWavesCleared = (long) statisticsSystem.getCurrentGameStatistic(StatisticsType.SG_WCL);
                frameValues.wave = gameSystemProvider.wave.wave == null ? 1 : gameSystemProvider.wave.wave.waveNumber;
                frameValues.mdps = (float) gameSystemProvider.damage.getTowersMaxDps();
                return frameValues;
            }

            /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ReplayManager$ReplayRecord$ChartFrames$FrameValues.class */
            public static class FrameValues {
                public int cBounties;
                public int cKilledEnemies;
                public int cWaveCalls;
                public int cOther;
                public long sKilledEnemies;
                public long sMining;
                public long sWaveCalls;
                public long sWavesCleared;
                public int wave;
                public float mdps;

                public void write(FixedOutput fixedOutput) {
                    fixedOutput.writeVarInt(this.cBounties, true);
                    fixedOutput.writeVarInt(this.cKilledEnemies, true);
                    fixedOutput.writeVarInt(this.cWaveCalls, true);
                    fixedOutput.writeVarInt(this.cOther, true);
                    fixedOutput.writeVarLong(this.sKilledEnemies, true);
                    fixedOutput.writeVarLong(this.sMining, true);
                    fixedOutput.writeVarLong(this.sWaveCalls, true);
                    fixedOutput.writeVarLong(this.sWavesCleared, true);
                    fixedOutput.writeVarInt(this.wave, true);
                    fixedOutput.writeFloat(this.mdps);
                }

                public void read(FixedInput fixedInput) {
                    this.cBounties = fixedInput.readVarInt(true);
                    this.cKilledEnemies = fixedInput.readVarInt(true);
                    this.cWaveCalls = fixedInput.readVarInt(true);
                    this.cOther = fixedInput.readVarInt(true);
                    this.sKilledEnemies = fixedInput.readVarLong(true);
                    this.sMining = fixedInput.readVarLong(true);
                    this.sWaveCalls = fixedInput.readVarLong(true);
                    this.sWavesCleared = fixedInput.readVarLong(true);
                    this.wave = fixedInput.readVarInt(true);
                    this.mdps = fixedInput.readFloat();
                }

                public FrameValues cpy() {
                    FrameValues frameValues = new FrameValues();
                    frameValues.cBounties = this.cBounties;
                    frameValues.cKilledEnemies = this.cKilledEnemies;
                    frameValues.cWaveCalls = this.cWaveCalls;
                    frameValues.cOther = this.cOther;
                    frameValues.sKilledEnemies = this.sKilledEnemies;
                    frameValues.sMining = this.sMining;
                    frameValues.sWaveCalls = this.sWaveCalls;
                    frameValues.sWavesCleared = this.sWavesCleared;
                    frameValues.wave = this.wave;
                    frameValues.mdps = this.mdps;
                    return frameValues;
                }
            }
        }
    }
}
