package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.ByteArray;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.events.EventListeners;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.global.PostRender;
import com.prineside.tdi2.managers.ScreenManager;
import com.prineside.tdi2.managers.preferences.LegacyPreferences;
import com.prineside.tdi2.managers.preferences.RegularPrefMap;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.managers.preferences.categories.SettingsPrefs;
import com.prineside.tdi2.screens.GameScreen;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.HashMap;
import java.util.Map;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/PreferencesManager.class */
public class PreferencesManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2396a = TLog.forClass(PreferencesManager.class);
    public static final String PROGRESS_PREFS_FILE_PATH_DEV = "cache/saves_dev/progress.txt";
    public static final String SETTINGS_PREFS_FILE_PATH_DEV = "cache/saves_dev/settings.txt";
    private boolean d;

    @Null
    private Long m;

    /* renamed from: b, reason: collision with root package name */
    private boolean f2397b = false;
    private final DelayedRemovalArray<PreferencesManagerListener> c = new DelayedRemovalArray<>(false, 1);
    private ProgressPrefs f = new ProgressPrefs();
    private SettingsPrefs g = new SettingsPrefs();
    private final RegularPrefMap h = new RegularPrefMap((byte) 1);
    private final RegularPrefMap i = new RegularPrefMap((byte) 2);
    private volatile RegularPrefMap j = new RegularPrefMap((byte) 1);
    private volatile RegularPrefMap k = new RegularPrefMap((byte) 2);
    private int l = 0;
    private final LegacyPreferences e = new LegacyPreferences();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/PreferencesManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<PreferencesManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public PreferencesManager read() {
            return Game.i.preferencesManager;
        }
    }

    public static String getSettingsPrefsFilePath() {
        if (Config.isModdingMode()) {
            return "saves_mod/settings.sav";
        }
        return "saves/settings.sav";
    }

    public static String getProgressPrefsFilePath() {
        if (Config.isModdingMode()) {
            return "saves_mod/progress.sav";
        }
        return "saves/progress.sav";
    }

    public static String getSavesDirPath() {
        if (Config.isModdingMode()) {
            return "saves_mod/";
        }
        return "saves/";
    }

    public static String getSavedGameFilePath() {
        return getSavesDirPath() + "saved-game.bin";
    }

    public static String getIssuedItemsFilePath() {
        return getSavesDirPath() + "issued-items-log.json";
    }

    public static String getReplaysDirPath() {
        return getSavesDirPath() + "replays/";
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        this.d = true;
        FileHandle local = Gdx.files.local(getSettingsPrefsFilePath());
        FileHandle local2 = Gdx.files.local(getProgressPrefsFilePath());
        if (local.exists() || local2.exists()) {
            if (local.exists()) {
                byte[] readBytes = local.readBytes();
                try {
                    this.i.fromBytes(readBytes, 0, readBytes.length);
                    this.g.load(this.i);
                    this.i.clear();
                } catch (Exception e) {
                    f2396a.e("Failed to load settings preferences from file, skipping it", e);
                }
            }
            if (local2.exists()) {
                byte[] readBytes2 = local2.readBytes();
                try {
                    this.h.fromBytes(readBytes2, 0, readBytes2.length);
                    this.f.load(this.h);
                    this.h.clear();
                } catch (Exception e2) {
                    f2396a.e("Failed to load progress preferences from file, skipping it", e2);
                }
            }
        } else {
            Array<ObjectPair<String, HashMap<String, String>>> locallyStoredPrefs = this.e.getLocallyStoredPrefs();
            if (locallyStoredPrefs != null) {
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= locallyStoredPrefs.size) {
                        break;
                    }
                    ObjectPair<String, HashMap<String, String>> objectPair = locallyStoredPrefs.get(i);
                    if (!objectPair.first.equals("Progress")) {
                        i++;
                    } else if ("true".equals(objectPair.second.get("_migrated_1_9_0"))) {
                        z = true;
                    }
                }
                if (!z) {
                    try {
                        f2396a.i("found " + locallyStoredPrefs.size + " legacy preference categories in local storage, migrating them...", new Object[0]);
                        fromLegacy(locallyStoredPrefs, true);
                        f2396a.i("successfully migrated legacy local preferences, adding a migration tag", new Object[0]);
                        LegacyPreferences.SafePreferences propertiesInstance = this.e.getPropertiesInstance(Config.LEGACY_PREFERENCES_NAME_PROGRESS);
                        propertiesInstance.set("_migrated_1_9_0", "true");
                        propertiesInstance.flush();
                    } catch (Exception e3) {
                        f2396a.e("failed to migrate legacy preferences", e3);
                    }
                } else {
                    f2396a.i("found a migration tag in the legacy preferences, skipping migration", new Object[0]);
                }
            } else {
                f2396a.i("no preferences found and no legacy preferences to migrate - clean run", new Object[0]);
            }
        }
        Game.i.screenManager.addListener(new ScreenManager.ScreenManagerListener() { // from class: com.prineside.tdi2.managers.PreferencesManager.1
            @Override // com.prineside.tdi2.managers.ScreenManager.ScreenManagerListener
            public void screenChanged() {
                PreferencesManager.this.saveNowIfRequired();
            }
        });
        Gdx.app.addLifecycleListener(new LifecycleListener() { // from class: com.prineside.tdi2.managers.PreferencesManager.2
            @Override // com.badlogic.gdx.LifecycleListener
            public void pause() {
                PreferencesManager.f2396a.i("pause", new Object[0]);
                PreferencesManager.this.saveNowIfRequired();
            }

            @Override // com.badlogic.gdx.LifecycleListener
            public void resume() {
                PreferencesManager.f2396a.i("resume", new Object[0]);
            }

            @Override // com.badlogic.gdx.LifecycleListener
            public void dispose() {
                PreferencesManager.f2396a.i("dispose", new Object[0]);
                PreferencesManager.this.saveNowIfRequired();
            }
        });
        Game.EVENTS.getListeners(PostRender.class).addWithPriority(new Listener<PostRender>() { // from class: com.prineside.tdi2.managers.PreferencesManager.3
            @Override // com.prineside.tdi2.events.Listener
            public void handleEvent(PostRender postRender) {
                if (!PreferencesManager.this.f.isSaveRequired() && !PreferencesManager.this.g.isSaveRequired()) {
                    PreferencesManager.this.m = null;
                    return;
                }
                long j = 2000;
                if (Game.i.screenManager.getCurrentScreen() instanceof GameScreen) {
                    j = 30000;
                }
                if (PreferencesManager.this.m == null) {
                    PreferencesManager.this.m = Long.valueOf(Game.getTimestampMillis());
                    return;
                }
                if (Game.getTimestampMillis() - PreferencesManager.this.m.longValue() > j) {
                    PreferencesManager.f2396a.i("triggering regular save (by timer)", Long.valueOf(Game.getTimestampMillis() - PreferencesManager.this.m.longValue()), Long.valueOf(j));
                    PreferencesManager.this.m = null;
                    long realTickCount = Game.getRealTickCount();
                    PreferencesManager.this.saveNowIfRequired();
                    PreferencesManager.f2396a.i("--- saved in: " + ((Object) StringFormatter.compactNumberWithPrecision(((float) (Game.getRealTickCount() - realTickCount)) / 1000.0f, 1)) + "ms", new Object[0]);
                }
            }
        }, EventListeners.PRIORITY_LOWEST).setName("PreferencesManager - handles auto save").setDescription("Must be called last to make sure nothing will mutate preferences and cause a lock");
    }

    public LegacyPreferences getLegacyPreferences() {
        return this.e;
    }

    @Null
    public RegularPrefMap getPrefMapForProgressSaveFile() {
        RegularPrefMap regularPrefMap = new RegularPrefMap((byte) 1);
        FileHandle local = Gdx.files.local(getProgressPrefsFilePath());
        if (local.exists()) {
            byte[] readBytes = local.readBytes();
            try {
                regularPrefMap.fromBytes(readBytes, 0, readBytes.length);
                return regularPrefMap;
            } catch (Exception e) {
                f2396a.e("getPrefMapForProgressSaveFile: failed to load progress preferences from file", e);
                return null;
            }
        }
        f2396a.i("getPrefMapForProgressSaveFile - save file " + getProgressPrefsFilePath() + " not exists", new Object[0]);
        return null;
    }

    @Null
    public RegularPrefMap getPrefMapForSettingsSaveFile() {
        f2396a.e("getPrefMapForSettingsSaveFile disabled", new Object[0]);
        return null;
    }

    public ProgressPrefs getProgressPrefs() {
        return this.f;
    }

    public SettingsPrefs getSettingsPrefs() {
        return this.g;
    }

    public void fromLegacy(Array<ObjectPair<String, HashMap<String, String>>> array, boolean z) {
        f2396a.i("fromLegacy called", new Object[0]);
        RegularPrefMap regularPrefMap = new RegularPrefMap((byte) 1);
        RegularPrefMap regularPrefMap2 = z ? new RegularPrefMap((byte) 2) : null;
        HashMap<String, String> hashMap = null;
        HashMap<String, String> hashMap2 = null;
        HashMap<String, String> hashMap3 = null;
        HashMap<String, String> hashMap4 = null;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < array.size; i3++) {
            ObjectPair<String, HashMap<String, String>> objectPair = array.get(i3);
            if ("Progress".equals(objectPair.first)) {
                hashMap = objectPair.second;
                for (Map.Entry<String, String> entry : objectPair.second.entrySet()) {
                    regularPrefMap.set(entry.getKey(), entry.getValue());
                    i++;
                }
            } else if ("Settings".equals(objectPair.first)) {
                hashMap2 = objectPair.second;
                if (z) {
                    for (Map.Entry<String, String> entry2 : objectPair.second.entrySet()) {
                        regularPrefMap2.set(entry2.getKey(), entry2.getValue());
                        i2++;
                    }
                } else {
                    f2396a.i("ignoring legacy settings", new Object[0]);
                }
            } else if ("Statistics".equals(objectPair.first)) {
                hashMap3 = objectPair.second;
            } else if ("UserMaps".equals(objectPair.first)) {
                hashMap4 = objectPair.second;
            } else {
                f2396a.e("skipping unknown legacy properties " + objectPair.first, new Object[0]);
            }
        }
        if (hashMap2 != null) {
            regularPrefMap.set("gameStartGameVersion", hashMap2.get("gameStartGameVersion"));
            regularPrefMap.set("gameStartTimestamp", hashMap2.get("gameStartTimestamp"));
            regularPrefMap.set("gameStartHash", hashMap2.get("gameStartHash"));
        } else {
            f2396a.i("no legacy settings - skipped migration of individual fields", new Object[0]);
        }
        if (hashMap3 != null) {
            regularPrefMap.set("statsAllTime", hashMap3.get("allTime"));
            regularPrefMap.set("statsMaxOneGame", hashMap3.get("maxOneGame"));
            String str = hashMap3.get("issuedPrizes");
            if (str != null) {
                Gdx.files.local(getIssuedItemsFilePath()).writeString(str, false, "UTF-8");
            }
        } else {
            f2396a.i("no legacy statistics - skipped migration of individual fields", new Object[0]);
        }
        if (hashMap4 != null) {
            regularPrefMap.set("userMaps", hashMap4.get("slots"));
        } else {
            f2396a.i("no legacy statistics - skipped migration of individual fields", new Object[0]);
        }
        if (regularPrefMap2 != null && hashMap != null) {
            regularPrefMap2.set("sentGameReplaysToServer", hashMap.get("sentGameReplaysToServer"));
        }
        this.f.load(regularPrefMap);
        this.f.requireSave();
        if (z) {
            this.g.load(regularPrefMap2);
            this.g.requireSave();
        }
        f2396a.i("fromLegacy finished:", new Object[0]);
        f2396a.i("- " + i + " progress properties migrated", new Object[0]);
        f2396a.i("- " + i2 + " settings properties migrated", new Object[0]);
        f2396a.i("- " + array.size + " legacy property categories", new Object[0]);
        saveNowIfRequired();
    }

    public void saveNowIfRequired() {
        if (!this.d) {
            f2396a.i("skipped save - manager not set up yet", new Object[0]);
            return;
        }
        if (this.f.isSaveRequired()) {
            long realTickCount = Game.getRealTickCount();
            RegularPrefMap regularPrefMap = this.j;
            this.j = null;
            if (regularPrefMap != null) {
                regularPrefMap.clear();
                this.f.saveAsync(regularPrefMap, () -> {
                    Threads.i().runAsync(() -> {
                        ByteArray bytes = regularPrefMap.toBytes();
                        Gdx.files.local(getProgressPrefsFilePath()).writeBytes(bytes.items, 0, bytes.size, false);
                        this.j = regularPrefMap;
                        f2396a.i("progress properties saved to disk", new Object[0]);
                        this.l = 0;
                    });
                }, exc -> {
                    Threads.i().runOnMainThread(() -> {
                        this.l++;
                        if (this.l < 3) {
                            this.j = regularPrefMap;
                            this.f.requireSave();
                            f2396a.e("failed to save progress prefs, trying again", exc);
                            return;
                        }
                        throw new IllegalStateException("too many failed attempts to save preferences, exiting");
                    });
                });
                this.f.setSaveRequired(false);
                f2396a.i("real lag progress: " + ((Object) StringFormatter.compactNumberWithPrecision(((float) (Game.getRealTickCount() - realTickCount)) / 1000.0f, 2)) + "ms", new Object[0]);
            } else {
                f2396a.i("skipped progress save - already saving", new Object[0]);
            }
        }
        if (this.g.isSaveRequired()) {
            long realTickCount2 = Game.getRealTickCount();
            RegularPrefMap regularPrefMap2 = this.k;
            this.k = null;
            if (regularPrefMap2 != null) {
                regularPrefMap2.clear();
                this.g.saveAsync(regularPrefMap2, () -> {
                    Threads.i().runAsync(() -> {
                        ByteArray bytes = regularPrefMap2.toBytes();
                        Gdx.files.local(getSettingsPrefsFilePath()).writeBytes(bytes.items, 0, bytes.size, false);
                        this.k = regularPrefMap2;
                        f2396a.i("settings properties saved to disk", new Object[0]);
                    });
                }, exc2 -> {
                    Threads.i().runOnMainThread(() -> {
                        this.l++;
                        if (this.l < 3) {
                            this.k = regularPrefMap2;
                            this.g.requireSave();
                            f2396a.e("failed to save settings prefs, trying again", exc2);
                            return;
                        }
                        throw new IllegalStateException("too many failed attempts to save preferences, exiting");
                    });
                });
                this.g.setSaveRequired(false);
                f2396a.i("real lag settings: " + ((Object) StringFormatter.compactNumberWithPrecision(((float) (Game.getRealTickCount() - realTickCount2)) / 1000.0f, 2)) + "ms", new Object[0]);
                return;
            }
            f2396a.i("skipped settings save - already saving", new Object[0]);
        }
    }

    public void loadFromUrl(String str, final ObjectConsumer<Boolean> objectConsumer) {
        f2396a.i("loading preferences from " + str, new Object[0]);
        try {
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(str);
            Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.managers.PreferencesManager.4
                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    String resultAsString = httpResponse.getResultAsString();
                    PreferencesManager.f2396a.i(resultAsString, new Object[0]);
                    Threads i = Threads.i();
                    ObjectConsumer objectConsumer2 = objectConsumer;
                    i.runOnMainThread(() -> {
                        try {
                            JsonValue parse = new JsonReader().parse(resultAsString);
                            int i2 = parse.getInt("build");
                            if (i2 > 208) {
                                PreferencesManager.f2396a.e("backup is build " + i2, new Object[0]);
                                Notifications.i().add(Game.i.localeManager.i18n.get("cant_load_from_cloud_need_update"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                                if (objectConsumer2 != null) {
                                    objectConsumer2.accept(Boolean.FALSE);
                                    return;
                                }
                                return;
                            }
                            PreferencesManager.this.fromBase64(parse.getString("progress"));
                            if (objectConsumer2 != null) {
                                objectConsumer2.accept(Boolean.TRUE);
                            }
                        } catch (Exception e) {
                            PreferencesManager.f2396a.e("failed to load backup", e);
                            if (objectConsumer2 != null) {
                                objectConsumer2.accept(Boolean.FALSE);
                            }
                        }
                    });
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void failed(Throwable th) {
                    PreferencesManager.f2396a.e("failed to send backup request", th);
                    if (objectConsumer != null) {
                        Threads i = Threads.i();
                        ObjectConsumer objectConsumer2 = objectConsumer;
                        i.runOnMainThread(() -> {
                            objectConsumer2.accept(Boolean.FALSE);
                        });
                    }
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void cancelled() {
                    PreferencesManager.f2396a.e("canceled to send backup request", new Object[0]);
                    if (objectConsumer != null) {
                        Threads i = Threads.i();
                        ObjectConsumer objectConsumer2 = objectConsumer;
                        i.runOnMainThread(() -> {
                            objectConsumer2.accept(Boolean.FALSE);
                        });
                    }
                }
            });
        } catch (Exception e) {
            f2396a.e("failed to send backup request", e);
            if (objectConsumer != null) {
                Threads.i().runOnMainThread(() -> {
                    objectConsumer.accept(Boolean.FALSE);
                });
            }
        }
    }

    public void fromBase64(String str) {
        byte[] decode = Base64Coder.decode(str);
        fromBytes(decode, 0, decode.length);
    }

    public void fromBytes(byte[] bArr, int i, int i2) {
        RegularPrefMap.BinarySaveInfo binarySaveInfo = RegularPrefMap.getBinarySaveInfo(bArr, i, i2);
        if (binarySaveInfo.valid) {
            f2396a.i("fromBytes - detected new save format: " + binarySaveInfo, new Object[0]);
            if (binarySaveInfo.type == 1) {
                f2396a.i("detected progress preferences", new Object[0]);
                this.h.clear();
                try {
                    this.h.fromBytes(bArr, i, i2);
                    this.f.load(this.h);
                    this.f.requireSave();
                    reapplyAllPreferences();
                    saveNowIfRequired();
                    return;
                } catch (Exception e) {
                    f2396a.e("fromBytes failed (progress)", e);
                    return;
                }
            }
            if (binarySaveInfo.type != 2) {
                f2396a.e("fromBytes - new preferences format but unknown type: " + binarySaveInfo, new Object[0]);
                return;
            }
            f2396a.i("detected settings preferences", new Object[0]);
            this.i.clear();
            try {
                this.i.fromBytes(bArr, i, i2);
                this.g.load(this.i);
                this.g.requireSave();
                reapplyAllPreferences();
                saveNowIfRequired();
                return;
            } catch (Exception e2) {
                f2396a.e("fromBytes failed (settings)", e2);
                return;
            }
        }
        f2396a.i("fromBytes - not a new save format, trying legacy: " + binarySaveInfo, new Object[0]);
        fromLegacy(this.e.fromBytes(bArr, i, i2), false);
    }

    public String progressPrefsToBase64() {
        this.h.clear();
        getProgressPrefs().save(this.h);
        return this.h.toBase64();
    }

    public String settingsPrefsToBase64() {
        this.i.clear();
        getProgressPrefs().save(this.i);
        return this.i.toBase64();
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        saveNowIfRequired();
    }

    public void addListener(PreferencesManagerListener preferencesManagerListener) {
        if (preferencesManagerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.c.contains(preferencesManagerListener, true)) {
            this.c.add(preferencesManagerListener);
        }
    }

    public void removeListener(PreferencesManagerListener preferencesManagerListener) {
        if (preferencesManagerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.c.removeValue(preferencesManagerListener, true);
    }

    public void reapplyAllPreferences() {
        if (this.f2397b) {
            throw new IllegalStateException("Calling reapplyAllPreferences from PreferencesManagerListener.reloaded");
        }
        try {
            this.f2397b = true;
            this.c.begin();
            int i = this.c.size;
            for (int i2 = 0; i2 < i; i2++) {
                this.c.get(i2).reloaded();
            }
            this.c.end();
            this.f2397b = false;
        } finally {
            this.f2397b = false;
        }
    }

    public void resetProgress() {
        Game.i.progressManager.removeIssuedItemsLog();
        this.f = new ProgressPrefs();
        Gdx.files.local(getProgressPrefsFilePath()).delete();
        Gdx.files.local(PROGRESS_PREFS_FILE_PATH_DEV).delete();
        Game.i.replayManager.deleteAllReplays();
        SettingsPrefs.i().auth.setNoCloudSaveSlot();
        SettingsPrefs.i().requireSave();
        f2396a.i("all progress preferences removed", new Object[0]);
        reapplyAllPreferences();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/PreferencesManager$PreferencesManagerListener.class */
    public interface PreferencesManagerListener {
        void reloaded();

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/PreferencesManager$PreferencesManagerListener$PreferencesManagerListenerAdapter.class */
        public static class PreferencesManagerListenerAdapter implements PreferencesManagerListener {
            @Override // com.prineside.tdi2.managers.PreferencesManager.PreferencesManagerListener
            public void reloaded() {
            }
        }
    }
}
