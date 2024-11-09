package com.prineside.tdi2.utils.logging;

import com.badlogic.gdx.ApplicationLogger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.prineside.tdi2.ActionResolver;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.events.global.GameDispose;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.FileUtils;
import com.prineside.tdi2.utils.MaterialColor;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/logging/Logger.class */
public final class Logger {
    public static final String DEFAULT_LOG_FILE_NAME = "log.txt";
    public static final int MAX_TAG_SIZE = 20;

    /* renamed from: a, reason: collision with root package name */
    static volatile FileHandle f3928a;

    /* renamed from: b, reason: collision with root package name */
    static volatile Writer f3929b;
    private static final TLog f = TLog.forClass(Logger.class);
    public static final Object logWriterSyncObject = new Object();
    private static PlatformLogger g = new SystemOutPlatformLogger(false, false);
    private static final Array<LogListener> h = new Array<>(true, 1, LogListener.class);
    static final AtomicBoolean c = new AtomicBoolean();
    static final ConcurrentLinkedQueue<LogEntry> d = new ConcurrentLinkedQueue<>();
    static final Array<Runnable> e = new Array<>(true, 1, Runnable.class);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/logging/Logger$LogListener.class */
    public interface LogListener {
        void onNewLogEntry(LogEntry logEntry);
    }

    private Logger() {
    }

    public static TLog forClass(Class<?> cls) {
        return TLog.forClass(cls);
    }

    public static TLog forTag(String str) {
        return TLog.forTag(str);
    }

    public static void init(ActionResolver actionResolver) {
        Preconditions.checkNotNull(actionResolver, "actionResolver can not be null");
        h.clear();
        c.set(false);
        Game.EVENTS.getListeners(GameDispose.class).add(gameDispose -> {
            c();
        });
        setLogFile(actionResolver.getLogFile());
        b();
        PlatformLogger createPlatformLogger = actionResolver.createPlatformLogger();
        g = createPlatformLogger;
        if (createPlatformLogger == null) {
            throw new IllegalArgumentException("actionResolver.createPlatformLogger returned null");
        }
        if (Gdx.app == null) {
            f.w("Gdx.app not set, skipping AppLogger swap", new Object[0]);
        } else if (Gdx.app.getApplicationLogger() instanceof ProxyAppLogger) {
            f.w("app logger already set to ProxyAppLogger, skipping", new Object[0]);
        } else {
            Gdx.app.setApplicationLogger(new ProxyAppLogger((byte) 0));
        }
        Thread thread = new Thread(new LogWriter(), "Log file writer");
        thread.setDaemon(true);
        thread.start();
        try {
            Thread thread2 = new Thread(Logger::c, "Logger dispose thread");
            thread2.setDaemon(false);
            Runtime.getRuntime().addShutdownHook(thread2);
            a((byte) 0, "Logger", "added shutdown hook for dispose", new Object[0]);
        } catch (Throwable th) {
            a((byte) 2, "Logger", "failed to set up a shutdown hook", th);
        }
    }

    public static void forceLogFileFlushAndRun(Runnable runnable) {
        synchronized (e) {
            e.add(runnable);
        }
    }

    public static void addLogListener(LogListener logListener) {
        synchronized (h) {
            if (!h.contains(logListener, true)) {
                h.add(logListener);
            }
        }
    }

    public static void removeLogListener(LogListener logListener) {
        synchronized (h) {
            h.removeValue(logListener, true);
        }
    }

    public static void setLogFile(@Null FileHandle fileHandle) {
        System.out.println("Setting log file: " + fileHandle);
        f3928a = fileHandle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a() {
        synchronized (logWriterSyncObject) {
            if (f3929b != null) {
                try {
                    Writer writer = f3929b;
                    f3929b = null;
                    writer.close();
                } catch (Throwable unused) {
                }
            }
        }
    }

    public static PlatformLogger getPlatformLogger() {
        return g;
    }

    public static void setPlatformLogger(PlatformLogger platformLogger) {
        Preconditions.checkNotNull(platformLogger, "platformLogger can not be null. Use dummy object here if you don't need logging");
        g = platformLogger;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(byte b2, String str, String str2, Object... objArr) {
        if (objArr.length != 0 && (objArr[objArr.length - 1] instanceof Throwable)) {
            StringBuilder append = new StringBuilder(str2).append(SequenceUtils.EOL);
            StringWriter stringWriter = new StringWriter();
            ((Throwable) objArr[objArr.length - 1]).printStackTrace(new PrintWriter(stringWriter));
            append.append(stringWriter.getBuffer());
            str2 = append.toString();
            Object[] objArr2 = new Object[objArr.length - 1];
            if (objArr2.length != 0) {
                System.arraycopy(objArr, 0, objArr2, 0, objArr2.length);
                objArr = objArr2;
            }
        }
        String lenientFormat = Strings.lenientFormat(str2, objArr);
        switch (b2) {
            case 0:
                g.debug(str, lenientFormat);
                break;
            case 1:
                g.info(str, lenientFormat);
                break;
            case 2:
                g.warn(str, lenientFormat);
                break;
            case 3:
                g.error(str, lenientFormat);
                break;
        }
        LogEntry logEntry = new LogEntry(b2, Game.getTimestampMillis(), str, lenientFormat);
        int writerStatus = LogWriter.getWriterStatus();
        if (writerStatus != 2 && writerStatus != 0) {
            d.add(logEntry);
        }
        if (h.size != 0) {
            synchronized (h) {
                for (int i = 0; i < h.size; i++) {
                    try {
                        h.get(i).onNewLogEntry(logEntry);
                    } catch (Exception e2) {
                        f.e("Failed to call listener " + h.get(i) + ", it will be removed and other listeners won't be notified", e2);
                        h.removeIndex(i);
                    }
                }
            }
        }
        if (b2 >= 3) {
            try {
                if (Game.isLoaded() && Game.i.isInMainThread() && Game.i.settingsManager.isInDebugMode()) {
                    Notifications.i().add(str + ":" + str2 + SequenceUtils.EOL + lenientFormat, new TextureRegionDrawable(Game.i.assetManager.getTextureRegions("enemy-type-boss-metaphor-creep").first()), MaterialColor.RED.P900, StaticSoundType.ENEMY_REACHED);
                }
            } catch (Exception unused) {
            }
        }
    }

    public static String getLastLogLines(int i) {
        if (f3928a == null) {
            return "(no logFile set)";
        }
        try {
            return FileUtils.tail(f3928a.file(), i).toString(SequenceUtils.EOL);
        } catch (Exception e2) {
            return "(failed to read log: " + e2.getMessage() + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.PrintStream] */
    /* JADX WARN: Type inference failed for: r0v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Exception] */
    public static void b() {
        if (f3928a == null) {
            System.out.println("rotateLogs skipped - no log file");
            return;
        }
        ?? r0 = System.out;
        r0.println("Rotating logs");
        try {
            a();
            String name = f3928a.name();
            if (f3928a.sibling(name + ".1").exists()) {
                FileUtils.fileToZip(f3928a.sibling(name + ".1").file(), f3928a.sibling(name + ".1.zip").file(), name);
            }
            for (int i = 4; i > 0; i--) {
                String str = name + "." + i + ".zip";
                String str2 = name + "." + (i + 1) + ".zip";
                if (f3928a.sibling(str).exists()) {
                    f3928a.sibling(str).file().renameTo(f3928a.sibling(str2).file());
                }
            }
            if (f3928a.exists()) {
                r0 = f3928a.file().renameTo(f3928a.sibling(name + ".1").file());
            }
        } catch (Exception e2) {
            r0.printStackTrace(System.out);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c() {
        System.out.println("Logger dispose called");
        c.set(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/logging/Logger$ProxyAppLogger.class */
    public static final class ProxyAppLogger implements ApplicationLogger {
        private ProxyAppLogger() {
        }

        /* synthetic */ ProxyAppLogger(byte b2) {
            this();
        }

        @Override // com.badlogic.gdx.ApplicationLogger
        public final void log(String str, String str2) {
            Logger.a((byte) 1, str, str2, new Object[0]);
        }

        @Override // com.badlogic.gdx.ApplicationLogger
        public final void log(String str, String str2, Throwable th) {
            Logger.a((byte) 1, str, str2, th);
        }

        @Override // com.badlogic.gdx.ApplicationLogger
        public final void error(String str, String str2) {
            Logger.a((byte) 3, str, str2, new Object[0]);
        }

        @Override // com.badlogic.gdx.ApplicationLogger
        public final void error(String str, String str2, Throwable th) {
            Logger.a((byte) 3, str, str2, th);
        }

        @Override // com.badlogic.gdx.ApplicationLogger
        public final void debug(String str, String str2) {
            Logger.a((byte) 0, str, str2, new Object[0]);
        }

        @Override // com.badlogic.gdx.ApplicationLogger
        public final void debug(String str, String str2, Throwable th) {
            Logger.a((byte) 0, str, str2, th);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/logging/Logger$LogEntry.class */
    public static final class LogEntry {
        public final long timestampMs;
        public final byte logLevel;
        public final String tag;
        public final String message;

        public LogEntry(byte b2, long j, String str, String str2) {
            this.timestampMs = j;
            this.logLevel = b2;
            this.tag = str;
            this.message = str2;
        }
    }
}
