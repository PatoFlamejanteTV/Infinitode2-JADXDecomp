package com.prineside.tdi2.utils.errorhandling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.ObjectMap;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.ActionResolver;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.HeadlessReplayValidationGame;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.screens.GameScreen;
import com.prineside.tdi2.utils.FileIntegrityChecker;
import com.prineside.tdi2.utils.logging.Logger;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.WeakHashMap;
import net.bytebuddy.description.type.TypeDescription;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/errorhandling/CrashHandler.class */
public final class CrashHandler {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3922a = TLog.forClass(CrashHandler.class);

    /* renamed from: b, reason: collision with root package name */
    private static long f3923b = -1000000;
    private static final Set<Thread> c = Collections.newSetFromMap(new WeakHashMap());
    private static ActionResolver d;

    public static void setActionResolver(ActionResolver actionResolver) {
        Preconditions.checkNotNull(actionResolver);
        d = actionResolver;
        handleThreadExceptions(Thread.currentThread());
    }

    public static boolean isThreadExceptionsHandled(Thread thread) {
        return c.contains(thread);
    }

    public static void handleThreadExceptions(Thread thread) {
        a(thread, true);
    }

    public static void handleThreadExceptionsForgiving(Thread thread) {
        a(thread, false);
    }

    private static void a(Thread thread, boolean z) {
        if (isThreadExceptionsHandled(thread)) {
            return;
        }
        if (d == null) {
            throw new IllegalStateException("actionResolver not set yet");
        }
        d.handleThreadExceptions(thread);
        c.add(thread);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = thread.getUncaughtExceptionHandler();
        thread.setUncaughtExceptionHandler((thread2, th) -> {
            a(thread2, th);
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread2, th);
            }
            if (z) {
                Game.exit();
            }
        });
    }

    private static void a(Thread thread, Throwable th) {
        try {
            Json json = new Json(JsonWriter.OutputType.json);
            StringWriter stringWriter = new StringWriter();
            json.setWriter(stringWriter);
            json.writeObjectStart();
            json.writeValue("type", th.getClass().getName());
            json.writeValue("thread", thread.getName());
            json.writeValue("message", th.getMessage());
            StringWriter stringWriter2 = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter2));
            json.writeValue("stacktrace", stringWriter2.toString());
            json.writeObjectEnd();
            Gdx.files.local("cache/crash-report.json").writeString(stringWriter.toString(), false, "UTF-8");
            f3922a.i("Crash report saved", new Object[0]);
        } catch (Exception e) {
            f3922a.e("Failed to write crash report", e);
        }
        f3922a.e("Uncaught exception in thread " + thread.getName() + " (" + th.getMessage() + ")", th);
        try {
            PrintWriter printWriter = new PrintWriter(Gdx.files.local("exceptions.log").write(true));
            printWriter.append((CharSequence) new Date().toString()).append(SequenceUtils.EOL);
            printWriter.append("Exception in thread ").append((CharSequence) thread.getName()).append(":\n");
            th.printStackTrace(printWriter);
            if (Game.i instanceof HeadlessReplayValidationGame) {
                printWriter.append("\nReplay: ").append((CharSequence) ((HeadlessReplayValidationGame) Game.i).currentReplay);
            }
            printWriter.append("\n\n");
            printWriter.close();
        } catch (Exception unused) {
        }
        if (!Config.isHeadless()) {
            report("UE in " + thread.getName() + ": " + th.getMessage(), th);
        }
    }

    public static void report(String str) {
        report(str, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v154, types: [com.badlogic.gdx.utils.Json] */
    /* JADX WARN: Type inference failed for: r0v155, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v157, types: [com.badlogic.gdx.utils.Json] */
    /* JADX WARN: Type inference failed for: r0v158, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v160, types: [com.badlogic.gdx.utils.Json] */
    /* JADX WARN: Type inference failed for: r0v161, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v172, types: [com.badlogic.gdx.utils.Json] */
    /* JADX WARN: Type inference failed for: r0v177, types: [com.badlogic.gdx.utils.Json] */
    /* JADX WARN: Type inference failed for: r0v181, types: [boolean] */
    public static void report(String str, Throwable th) {
        if (Game.getRealTickCount() - f3923b < 500000) {
            f3922a.i("Cancelled report - too frequent", new Object[0]);
            return;
        }
        if (Game.i != null && Game.i.actionResolver != null && Game.i.actionResolver.isAppModified()) {
            f3922a.i("Cancelled report - app is modified", new Object[0]);
            return;
        }
        if (Config.isModdingMode()) {
            f3922a.i("Cancelled report - modding mode", new Object[0]);
            return;
        }
        if (Config.isHeadless()) {
            f3922a.i("Cancelled report - headless mode", new Object[0]);
            return;
        }
        if (Game.isLoaded() && !Game.i.settingsManager.isBugReportsEnabled()) {
            f3922a.i("Cancelled report - disabled", new Object[0]);
            return;
        }
        f3923b = Game.getRealTickCount();
        Json json = new Json(JsonWriter.OutputType.json);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        json.writeValue("current", Thread.currentThread().getName());
        json.writeArrayStart("threads");
        try {
            for (Thread thread : Thread.getAllStackTraces().keySet()) {
                if (isThreadExceptionsHandled(thread)) {
                    json.writeObjectStart();
                    json.writeValue(Attribute.NAME_ATTR, thread.getName());
                    json.writeValue("state", thread.getState().name());
                    json.writeArrayStart("trace");
                    int i = 0;
                    for (StackTraceElement stackTraceElement : thread.getStackTrace()) {
                        json.writeValue(stackTraceElement.toString());
                        i++;
                        if (i == 5) {
                            break;
                        }
                    }
                    json.writeArrayEnd();
                    json.writeObjectEnd();
                }
            }
        } catch (Exception unused) {
        }
        json.writeObjectEnd();
        json.writeObjectEnd();
        try {
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.LOGGER_REPORT_URL);
            HashMap hashMap = new HashMap();
            String str2 = TypeDescription.Generic.OfWildcardType.SYMBOL;
            Object obj = "unknown";
            StringBuilder sb = new StringBuilder();
            if (Game.i.isInMainThread()) {
                try {
                    try {
                        Array<String> runTheTest = FileIntegrityChecker.runTheTest();
                        sb.append("File integrity: ");
                        if (runTheTest.size == 0) {
                            obj = "valid";
                            sb.append("valid");
                        } else {
                            obj = "invalid";
                            sb.append("invalid");
                            for (int i2 = 0; i2 < runTheTest.size; i2++) {
                                sb.append("- ").append(runTheTest.get(i2)).append(SequenceUtils.EOL);
                            }
                        }
                        sb.append(SequenceUtils.EOL);
                    } catch (Exception unused2) {
                    }
                    try {
                        sb.append("App is modified: ").append(Game.i.actionResolver.isAppModified()).append(SequenceUtils.EOL);
                        sb.append("App modified info: ").append(Game.i.actionResolver.getAppModifiedInfo()).append(SequenceUtils.EOL);
                    } catch (Exception unused3) {
                    }
                    try {
                        sb.append("Screen: ").append(Game.i.screenManager.getCurrentScreen().getClass().getSimpleName()).append(SequenceUtils.EOL);
                        str2 = Game.i.screenManager.getCurrentScreen().getClass().getSimpleName();
                    } catch (Exception unused4) {
                    }
                    try {
                        if (Game.i.screenManager.getCurrentScreen() instanceof GameScreen) {
                            GameScreen gameScreen = (GameScreen) Game.i.screenManager.getCurrentScreen();
                            sb.append("Game mode: ").append(gameScreen.S.gameState.gameMode.name()).append(SequenceUtils.EOL);
                            sb.append("Difficulty mode: ").append(gameScreen.S.gameState.difficultyMode.name()).append(SequenceUtils.EOL);
                            sb.append("Replay ID: ").append(gameScreen.S.gameState.replayId).append(SequenceUtils.EOL);
                            sb.append("Basic level name: ").append(gameScreen.S.gameState.basicLevelName).append(SequenceUtils.EOL);
                        }
                    } catch (Throwable unused5) {
                    }
                    try {
                        sb.append("Resolution: ").append(Gdx.graphics.getWidth()).append(":").append(Gdx.graphics.getHeight()).append(SequenceUtils.EOL);
                    } catch (Throwable unused6) {
                    }
                    try {
                        sb.append("GL version: ").append(Gdx.gl.glGetString(7938)).append(SequenceUtils.EOL);
                    } catch (Throwable unused7) {
                    }
                    try {
                        sb.append("GL vendor: ").append(Gdx.gl.glGetString(7936)).append(SequenceUtils.EOL);
                    } catch (Throwable unused8) {
                    }
                    try {
                        sb.append("GL renderer: ").append(Gdx.gl.glGetString(7937)).append(SequenceUtils.EOL);
                    } catch (Throwable unused9) {
                    }
                    try {
                        sb.append("Sync check: ").append(Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_SYNC_VALIDATION)).append(SequenceUtils.EOL);
                    } catch (Throwable unused10) {
                    }
                } catch (Throwable unused11) {
                }
            } else {
                sb.append("Not on main thread");
            }
            hashMap.put("log", Logger.getLastLogLines(200) + "\n\n" + ((Object) sb));
            hashMap.put("currentScreen", str2);
            hashMap.put("fileIntegrity", obj);
            hashMap.put("reason", str);
            hashMap.put("os", Gdx.app == null ? "unknown" : Gdx.app.getType().name());
            if (Game.isLoaded()) {
                hashMap.put("playerid", Game.i.authManager.getPlayerIdCached());
            } else {
                hashMap.put("playerid", "G-0000-0000-000000");
            }
            if (Game.isLoaded()) {
                ObjectMap<String, String> deviceInfo = Game.i.actionResolver.getDeviceInfo();
                Json json2 = new Json(JsonWriter.OutputType.json);
                StringWriter stringWriter2 = new StringWriter();
                json2.setWriter(stringWriter2);
                json2.writeObjectStart();
                if (Game.i.localeManager != null) {
                    try {
                        json2.writeValue("g.locale", Charset.defaultCharset() + "," + Game.i.localeManager.getLocale() + "," + Game.i.localeManager.i18n.getLocale());
                    } catch (Exception unused12) {
                    }
                }
                ObjectMap.Entries<String, String> it = deviceInfo.iterator();
                while (it.hasNext()) {
                    ObjectMap.Entry next = it.next();
                    json2.writeValue((String) next.key, next.value);
                }
                ?? r0 = json2;
                r0.writeObjectStart("s.properties");
                try {
                    Properties properties = System.getProperties();
                    Enumeration<?> propertyNames = properties.propertyNames();
                    while (true) {
                        r0 = propertyNames.hasMoreElements();
                        if (r0 == 0) {
                            break;
                        }
                        Object nextElement = propertyNames.nextElement();
                        Object obj2 = properties.get(nextElement);
                        if (obj2 != null) {
                            json2.writeValue(nextElement.toString(), ((String) obj2).trim());
                        }
                    }
                } catch (Exception e) {
                    r0.printStackTrace();
                }
                json2.writeObjectEnd();
                ?? r02 = json2;
                r02.writeObjectStart("s.runtime");
                try {
                    Runtime runtime = Runtime.getRuntime();
                    json2.writeValue("proc_av", Integer.valueOf(runtime.availableProcessors()));
                    json2.writeValue("mem_free", Long.valueOf(runtime.freeMemory()));
                    json2.writeValue("mem_max", Long.valueOf(runtime.maxMemory()));
                    r02 = json2;
                    r02.writeValue("mem_total", Long.valueOf(runtime.totalMemory()));
                } catch (Exception e2) {
                    r02.printStackTrace();
                }
                json2.writeObjectEnd();
                ?? r03 = json2;
                r03.writeObjectStart("s.graphics");
                try {
                    json2.writeValue("type", Gdx.graphics.getGLVersion().getType().toString());
                    json2.writeValue("version", Gdx.graphics.getGLVersion().getMajorVersion() + "." + Gdx.graphics.getGLVersion().getMinorVersion() + "." + Gdx.graphics.getGLVersion().getReleaseVersion());
                    json2.writeValue("renderer", Gdx.graphics.getGLVersion().getRendererString());
                    json2.writeValue("vendor", Gdx.graphics.getGLVersion().getVendorString());
                    json2.writeValue("bb_size", Gdx.graphics.getBackBufferWidth() + "x" + Gdx.graphics.getBackBufferHeight());
                    json2.writeValue("density", Float.valueOf(Gdx.graphics.getDensity()));
                    r03 = json2;
                    r03.writeValue("max_txt_size", Integer.valueOf(Config.getMaxTextureSize()));
                } catch (Exception e3) {
                    r03.printStackTrace();
                }
                json2.writeObjectEnd();
                json2.writeObjectEnd();
                hashMap.put("device", stringWriter2.toString());
            }
            hashMap.put("threads", stringWriter.toString());
            if (th != null) {
                StringWriter stringWriter3 = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter3));
                hashMap.put("stacktrace", stringWriter3.toString());
                hashMap.put("exception", String.valueOf(th.getMessage()));
            }
            httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
            final boolean[] zArr = new boolean[1];
            Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.utils.errorhandling.CrashHandler.1
                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    try {
                        System.out.println("Logger.report: " + httpResponse.getResultAsString());
                    } catch (Exception e4) {
                        System.out.println("Logger.report: Exception: " + e4.getMessage());
                        e4.printStackTrace();
                    }
                    zArr[0] = true;
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void failed(Throwable th2) {
                    System.out.println("Logger.report: Error sending report: " + (th2 == null ? "null" : th2.getMessage()));
                    zArr[0] = true;
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void cancelled() {
                    System.out.println("Logger.report: cancelled");
                    zArr[0] = true;
                }
            });
            f3922a.i("Sent report", new Object[0]);
            for (int i3 = 0; i3 < 40; i3++) {
                if (!zArr[0]) {
                    Thread.sleep(50L);
                } else {
                    return;
                }
            }
        } catch (Exception e4) {
            f3922a.e("Failed to report", e4);
        }
    }
}
