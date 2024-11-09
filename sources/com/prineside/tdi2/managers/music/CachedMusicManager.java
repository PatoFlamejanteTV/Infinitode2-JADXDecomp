package com.prineside.tdi2.managers.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.ibxm.IBXM;
import com.prineside.tdi2.ibxm.Instrument;
import com.prineside.tdi2.ibxm.Module;
import com.prineside.tdi2.ibxm.WavInputStream;
import com.prineside.tdi2.managers.MusicManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.preferences.categories.SettingsPrefs;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.errorhandling.CrashHandler;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.OutputStream;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/music/CachedMusicManager.class */
public abstract class CachedMusicManager extends MusicManager {
    private Status f = Status.IDLE;
    private Module g;
    private String h;
    private Notifications.Notification i;
    private Thread j;
    private float k;
    private static final TLog e = TLog.forClass(CachedMusicManager.class);
    private static final byte[] l = new byte[WavInputStream.header.length];

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/music/CachedMusicManager$Status.class */
    public enum Status {
        IDLE,
        WAITING_CACHE_GENERATION,
        CACHE_GENERATED,
        PLAYING;

        public static final Status[] values = values();
    }

    protected abstract void a(Module module);

    public CachedMusicManager() {
        e.i("initializing", new Object[0]);
    }

    @Override // com.prineside.tdi2.managers.MusicManager, com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        Game.i.screenManager.addListener(() -> {
            b();
        });
        super.setup();
    }

    public static double getWavDuration(FileHandle fileHandle) {
        fileHandle.readBytes(l, 0, l.length);
        return (WavInputStream.readInt32(l, 40) / WavInputStream.readInt32(l, 24)) / 4.0d;
    }

    private static void b(Module module) {
        String c = c(module);
        Array<CacheStatus> array = SettingsPrefs.i().music.cacheStatuses;
        for (int i = 0; i < array.size; i++) {
            if (array.items[i].songFileName.equals(c)) {
                array.items[i].lastPlayTimestamp = Game.getTimestampSeconds();
                SettingsPrefs.i().requireSave();
                return;
            }
        }
        CacheStatus cacheStatus = new CacheStatus();
        cacheStatus.songFileName = c;
        cacheStatus.lastPlayTimestamp = Game.getTimestampSeconds();
        array.add(cacheStatus);
        SettingsPrefs.i().requireSave();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b() {
        if (this.j != null) {
            return;
        }
        long j = 0;
        FileHandle local = Gdx.files.local("cache/music/");
        if (local.exists() && local.isDirectory()) {
            for (FileHandle fileHandle : local.list()) {
                try {
                    if (fileHandle.name().endsWith(".wav")) {
                        j += fileHandle.length();
                    }
                } catch (Exception e2) {
                    e.e("cleanupCache failed to get size for " + fileHandle.name(), e2);
                }
            }
            int i = (int) ((j / 1024) / 1024);
            int customValue = (int) Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MUSIC_CACHE_MAX_SIZE);
            if (i > customValue) {
                e.i("music cache limit exceeded, cleaning up (" + i + "/" + customValue + " Mb)", new Object[0]);
                Array array = new Array(FileCacheStatus.class);
                for (FileHandle fileHandle2 : local.list()) {
                    try {
                        if (fileHandle2.name().endsWith(".wav")) {
                            FileCacheStatus fileCacheStatus = new FileCacheStatus(this, (byte) 0);
                            fileCacheStatus.f2499a = fileHandle2;
                            fileCacheStatus.f2500b = fileHandle2.length();
                            Array<CacheStatus> array2 = SettingsPrefs.i().music.cacheStatuses;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= array2.size) {
                                    break;
                                }
                                CacheStatus cacheStatus = array2.items[i2];
                                if (!fileHandle2.name().startsWith(cacheStatus.songFileName)) {
                                    i2++;
                                } else {
                                    fileCacheStatus.c = cacheStatus;
                                    break;
                                }
                            }
                            array.add(fileCacheStatus);
                        }
                    } catch (Exception e3) {
                        e.e("cleanupCache failed for " + fileHandle2.name(), e3);
                    }
                }
                for (int i3 = array.size - 1; i3 >= 0; i3--) {
                    if (((FileCacheStatus[]) array.items)[i3].c == null) {
                        FileCacheStatus fileCacheStatus2 = (FileCacheStatus) array.removeIndex(i3);
                        if (this.h == null || !fileCacheStatus2.f2499a.name().startsWith(this.h)) {
                            try {
                                fileCacheStatus2.f2499a.delete();
                                j -= fileCacheStatus2.f2500b;
                            } catch (Exception e4) {
                                e.e("failed to delete file with unknown cache " + fileCacheStatus2.f2499a.name(), e4);
                            }
                        }
                    }
                }
                array.sort((fileCacheStatus3, fileCacheStatus4) -> {
                    return Integer.compare(fileCacheStatus3.c.lastPlayTimestamp, fileCacheStatus4.c.lastPlayTimestamp);
                });
                for (int i4 = 0; i4 < array.size && ((int) ((j / 1024) / 1024)) >= customValue; i4++) {
                    FileCacheStatus fileCacheStatus5 = ((FileCacheStatus[]) array.items)[i4];
                    if (!fileCacheStatus5.c.songFileName.equals(this.h)) {
                        if (SettingsPrefs.i().music.cacheStatuses.removeValue(fileCacheStatus5.c, true)) {
                            SettingsPrefs.i().requireSave();
                        }
                        try {
                            fileCacheStatus5.f2499a.delete();
                            j -= ((FileCacheStatus[]) array.items)[i4].f2500b;
                        } catch (Exception e5) {
                            e.e("failed to delete file " + fileCacheStatus5.f2499a.name(), e5);
                        }
                    }
                }
            }
        }
    }

    private static String c(Module module) {
        String hexString;
        int i = 1;
        for (int i2 = 0; i2 < module.sequence.length; i2++) {
            i = (i * 31) + module.sequence[i2];
        }
        int length = (i * 31) + module.patterns.length;
        for (int i3 = 0; i3 < module.songName.length(); i3++) {
            length = (length * 31) + module.songName.charAt(i3);
        }
        for (Instrument instrument : module.instruments) {
            if (instrument.name.length() != 0) {
                for (int i4 = 0; i4 < instrument.name.length(); i4++) {
                    length = (length * 31) + instrument.name.charAt(i4);
                }
            }
        }
        if (length < 0) {
            hexString = "n" + Integer.toHexString(-length);
        } else {
            hexString = Integer.toHexString(length);
        }
        return module.songName.replaceAll("[^A-Za-z0-9]", "").toLowerCase(Locale.ENGLISH) + "-" + hexString.toLowerCase(Locale.ENGLISH);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String a(Module module, boolean z) {
        return "cache/music/" + c(module) + (z ? "-l" : "") + ".wav";
    }

    public static boolean isMusicCached(Module module) {
        if (module.restartPos != 0 && !Gdx.files.local(a(module, false)).exists()) {
            return false;
        }
        return Gdx.files.local(a(module, true)).exists();
    }

    @Override // com.prineside.tdi2.managers.MusicManager, com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void preRender(float f) {
        super.preRender(f);
        if (this.c) {
            if (this.i != null) {
                this.i.showProgress(this.k, Color.GREEN);
            }
            StringBuilder registerValue = Game.i.debugManager.registerValue("XM cached music status");
            if (registerValue != null) {
                registerValue.append(this.f.name());
            }
        }
    }

    private void d(Module module) {
        stopMusic();
        this.f = Status.PLAYING;
        this.g = module;
        this.h = c(module);
        b(module);
        a(module);
        setVolumeToStartNewTrack();
    }

    private void c() {
        if (this.j != null) {
            this.j.interrupt();
            this.j = null;
            e.i("interrupted caching thread", new Object[0]);
        }
    }

    @Override // com.prineside.tdi2.managers.MusicManager
    public void playMusic(Module module) {
        if (module == this.g) {
            return;
        }
        if (module == null) {
            stopMusic();
        }
        this.f2385a = false;
        this.g = module;
        this.h = c(module);
        if (this.i != null) {
            this.i.hide(0.0f);
            this.i = null;
        }
        this.f2386b = module.getVolumeMultiplierFromInstrumentNames();
        if (isMusicCached(module)) {
            d(module);
            showSongNotification(module, 7.0f);
            return;
        }
        this.i = showSongNotification(module, 30.0f);
        this.k = 0.0f;
        this.f = Status.WAITING_CACHE_GENERATION;
        c();
        this.j = new Thread(() -> {
            e.i("caching thread started", new Object[0]);
            IBXM ibxm = new IBXM(module, 44100);
            ibxm.setInterpolation(getInterpolation());
            byte[] bArr = new byte[4096];
            if (module.restartPos != 0 && !Thread.currentThread().isInterrupted()) {
                WavInputStream wavInputStream = new WavInputStream(ibxm, 0, WavInputStream.Mode.INTRO_PART);
                String a2 = a(module, false);
                OutputStream write = Gdx.files.local(a2).write(false);
                int i = 0;
                while (true) {
                    try {
                        int read = wavInputStream.read(bArr, 0, 4096);
                        if (read <= 0) {
                            break;
                        }
                        i += read;
                        write.write(bArr, 0, read);
                        this.k = (i / (i + wavInputStream.getRemain())) * 0.5f;
                    } catch (Exception unused) {
                        e.e("failed to write" + a2, new Object[0]);
                    }
                }
                this.k = 0.5f;
                e.i("prepared intro cache for " + a2, new Object[0]);
            }
            if (!Thread.currentThread().isInterrupted()) {
                WavInputStream wavInputStream2 = new WavInputStream(ibxm, 0, WavInputStream.Mode.LOOPING_PART);
                String a3 = a(module, true);
                OutputStream write2 = Gdx.files.local(a3).write(false);
                int i2 = 0;
                while (true) {
                    try {
                        int read2 = wavInputStream2.read(bArr, 0, 4096);
                        if (read2 <= 0) {
                            break;
                        }
                        i2 += read2;
                        write2.write(bArr, 0, read2);
                        this.k = i2 / (i2 + wavInputStream2.getRemain());
                        if (module.restartPos != 0) {
                            this.k = 0.5f + (this.k * 0.5f);
                        }
                    } catch (Exception unused2) {
                        e.e("failed to write" + a3, new Object[0]);
                    }
                }
                this.k = 1.0f;
                e.i("prepared looping cache for " + a3, new Object[0]);
            }
            if (!Thread.currentThread().isInterrupted() && this.f == Status.WAITING_CACHE_GENERATION) {
                this.f = Status.CACHE_GENERATED;
                Threads.i().runOnMainThread(() -> {
                    if (this.h != null && this.h.equals(c(module))) {
                        setVolumeToStartNewTrack();
                        d(module);
                        if (this.i != null) {
                            this.i.showProgress(0.0f, Color.GOLD);
                            this.i.hide(5.0f);
                            this.i = null;
                        }
                        b();
                    }
                });
            }
            e.i("caching thread ended", new Object[0]);
            this.j = null;
        });
        this.j.setDaemon(true);
        this.j.start();
        CrashHandler.handleThreadExceptionsForgiving(this.j);
    }

    @Override // com.prineside.tdi2.managers.MusicManager
    public Module getPlayingMusic() {
        return this.g;
    }

    @Override // com.prineside.tdi2.managers.MusicManager
    public void stopMusic() {
        this.g = null;
        this.f = Status.IDLE;
        this.h = null;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/music/CachedMusicManager$CacheStatus.class */
    public static class CacheStatus {
        public String songFileName;
        public int lastPlayTimestamp;

        public static CacheStatus fromJson(JsonValue jsonValue) {
            CacheStatus cacheStatus = new CacheStatus();
            cacheStatus.songFileName = jsonValue.getString("sfn", "");
            cacheStatus.lastPlayTimestamp = jsonValue.getInt("lpt", 0);
            return cacheStatus;
        }

        public void toJson(Json json) {
            json.writeValue("sfn", this.songFileName);
            json.writeValue("lpt", Integer.valueOf(this.lastPlayTimestamp));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/music/CachedMusicManager$FileCacheStatus.class */
    public class FileCacheStatus {

        /* renamed from: a, reason: collision with root package name */
        FileHandle f2499a;

        /* renamed from: b, reason: collision with root package name */
        long f2500b;
        CacheStatus c;

        private FileCacheStatus(CachedMusicManager cachedMusicManager) {
        }

        /* synthetic */ FileCacheStatus(CachedMusicManager cachedMusicManager, byte b2) {
            this(cachedMusicManager);
        }
    }
}
