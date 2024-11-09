package com.prineside.tdi2.utils.logging;

import com.badlogic.gdx.files.FileHandle;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.Logger;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/logging/LogWriter.class */
public final class LogWriter implements Runnable {
    public static final int LOG_FILE_WRITER_STATUS_NOT_STARTED = 0;
    public static final int LOG_FILE_WRITER_STATUS_STARTING = 1;
    public static final int LOG_FILE_WRITER_STATUS_STOPPED = 2;
    public static final int LOG_FILE_WRITER_STATUS_SLEEP_NO_FILE = 3;
    public static final int LOG_FILE_WRITER_STATUS_SLEEP_NO_ENTRIES = 4;
    public static final int LOG_FILE_WRITER_STATUS_WRITING = 5;
    private static long d;
    private static long e;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3926a = TLog.forClass(LogWriter.class);
    public static long LOG_ROTATE_AFTER_CHARACTERS = -1;
    public static final Pattern LOG_FILE_ENTRY_REGEX = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\.\\d{3})\\s([DEIW])\\s([a-zA-Z0-9_\\-./]+)\\s");
    public static final SimpleDateFormat LOG_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);

    /* renamed from: b, reason: collision with root package name */
    private static final SimpleDateFormat f3927b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
    private static final AtomicInteger c = new AtomicInteger(0);

    public static int getWriterStatus() {
        return c.get();
    }

    private static void a(Writer writer, Logger.LogEntry logEntry) {
        char shortName = LogLevel.getShortName(logEntry.logLevel);
        writer.append((CharSequence) f3927b.format(new Date(logEntry.timestampMs))).append(' ').append(shortName).append(' ').append(logEntry.tag).append(' ').append(StringFormatter.stripTerminalColors(logEntry.message)).append(SequenceUtils.EOL);
        e += r0.length() + 3 + logEntry.tag.length() + 1 + r0.length() + 1;
    }

    private static void a() {
        synchronized (Logger.logWriterSyncObject) {
            if (Logger.f3929b == null) {
                return;
            }
            while (true) {
                Logger.LogEntry poll = Logger.d.poll();
                if (poll != null) {
                    a(Logger.f3929b, poll);
                } else {
                    Logger.f3929b.flush();
                    return;
                }
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Writer writer;
        int size;
        c.set(1);
        FileHandle fileHandle = null;
        while (!Logger.c.get()) {
            try {
                if (Logger.f3928a != fileHandle) {
                    try {
                        a();
                    } catch (Exception e2) {
                        System.out.println("/!\\ failed to write all entries on log file change: " + e2.getMessage());
                    }
                    Logger.a();
                    fileHandle = Logger.f3928a;
                }
                synchronized (Logger.logWriterSyncObject) {
                    if (Logger.f3929b == null && Logger.f3928a != null) {
                        Logger.f3929b = new BufferedWriter(Logger.f3928a.writer(false, "UTF-8"));
                    }
                    writer = Logger.f3929b;
                }
                if (writer != null) {
                    try {
                        if (Logger.d.peek() == null) {
                            c.set(4);
                            Thread.sleep(31L);
                        } else {
                            c.set(5);
                            a();
                            if (LOG_ROTATE_AFTER_CHARACTERS > 0 && e > LOG_ROTATE_AFTER_CHARACTERS) {
                                Logger.b();
                                e = 0L;
                            }
                        }
                    } catch (IOException e3) {
                        Logger.setLogFile(null);
                        writer = null;
                        f3926a.e("failed to write log: " + e3.getMessage(), new Object[0]);
                    }
                    if (writer != null) {
                        long timestampMillis = Game.getTimestampMillis();
                        if (timestampMillis - d > 3000) {
                            try {
                                writer.flush();
                            } catch (Exception unused) {
                            }
                            d = timestampMillis;
                        }
                        if (Logger.e.size != 0) {
                            try {
                                writer.flush();
                            } catch (Exception unused2) {
                            }
                            d = timestampMillis;
                            synchronized (Logger.e) {
                                for (int i = 0; i < Logger.e.size; i++) {
                                    try {
                                        Logger.e.items[i].run();
                                    } catch (Throwable unused3) {
                                    }
                                }
                                Logger.e.clear();
                            }
                        }
                    }
                } else {
                    c.set(3);
                    Thread.sleep(101L);
                    if (Logger.f3928a == null && (size = Logger.d.size()) > 500) {
                        int i2 = size - 50;
                        System.out.println("Still no log file, purging " + i2 + " queued lines");
                        for (int i3 = 0; i3 < i2; i3++) {
                            Logger.d.poll();
                        }
                    }
                }
            } catch (InterruptedException unused4) {
                System.out.println("Log file writer interrupted");
                c.set(2);
                try {
                    a();
                } catch (Exception e4) {
                    System.out.println("failed to write all entries on thread interrupt: " + e4.getMessage());
                }
                Logger.a();
                Logger.setLogFile(null);
                return;
            }
        }
        System.out.println("Logger is disposed - closing the writer and stopping the log writer thread");
        try {
            a();
        } catch (Exception e5) {
            System.out.println("failed to write all entries on dispose: " + e5.getMessage());
        }
        Logger.a();
        Logger.setLogFile(null);
    }
}
