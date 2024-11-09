package com.badlogic.gdx.backends.headless;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.ApplicationLogger;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.headless.mock.audio.MockAudio;
import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics;
import com.badlogic.gdx.backends.headless.mock.input.MockInput;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/headless/HeadlessApplication.class */
public class HeadlessApplication implements Application {
    protected final ApplicationListener listener;
    protected Thread mainLoopThread;
    protected final HeadlessFiles files;

    /* renamed from: net, reason: collision with root package name */
    protected final HeadlessNet f884net;
    protected final MockAudio audio;
    protected final MockInput input;
    protected final MockGraphics graphics;
    protected boolean running;
    protected final Array<Runnable> runnables;
    protected final Array<Runnable> executedRunnables;
    protected final Array<LifecycleListener> lifecycleListeners;
    protected int logLevel;
    protected ApplicationLogger applicationLogger;
    private String preferencesdir;
    ObjectMap<String, Preferences> preferences;

    public HeadlessApplication(ApplicationListener applicationListener) {
        this(applicationListener, null);
    }

    public HeadlessApplication(ApplicationListener applicationListener, HeadlessApplicationConfiguration headlessApplicationConfiguration) {
        this.running = true;
        this.runnables = new Array<>();
        this.executedRunnables = new Array<>();
        this.lifecycleListeners = new Array<>();
        this.logLevel = 2;
        this.preferences = new ObjectMap<>();
        headlessApplicationConfiguration = headlessApplicationConfiguration == null ? new HeadlessApplicationConfiguration() : headlessApplicationConfiguration;
        HeadlessNativesLoader.load();
        setApplicationLogger(new HeadlessApplicationLogger());
        this.listener = applicationListener;
        this.files = new HeadlessFiles();
        this.f884net = new HeadlessNet(headlessApplicationConfiguration);
        this.graphics = new MockGraphics();
        this.graphics.setForegroundFPS(headlessApplicationConfiguration.updatesPerSecond);
        this.audio = new MockAudio();
        this.input = new MockInput();
        this.preferencesdir = headlessApplicationConfiguration.preferencesDirectory;
        Gdx.app = this;
        Gdx.files = this.files;
        Gdx.f881net = this.f884net;
        Gdx.audio = this.audio;
        Gdx.graphics = this.graphics;
        Gdx.input = this.input;
        initialize();
    }

    private void initialize() {
        this.mainLoopThread = new Thread("HeadlessApplication") { // from class: com.badlogic.gdx.backends.headless.HeadlessApplication.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                HeadlessApplication headlessApplication;
                try {
                    headlessApplication = HeadlessApplication.this;
                    headlessApplication.mainLoop();
                } catch (Throwable th) {
                    if (!(headlessApplication instanceof RuntimeException)) {
                        throw new GdxRuntimeException(th);
                    }
                    throw ((RuntimeException) th);
                }
            }
        };
        this.mainLoopThread.start();
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x003a, code lost:            r0 = r9 - r0;        java.lang.Thread.sleep(r0 / 1000000, (int) (r0 % 1000000));     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0024, code lost:            if (((float) r7.graphics.getTargetRenderInterval()) >= 0.0f) goto L4;     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x002b, code lost:            if (r7.running == false) goto L36;     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x002e, code lost:            r0 = com.badlogic.gdx.utils.TimeUtils.nanoTime();     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0037, code lost:            if (r9 <= r0) goto L11;     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0060, code lost:            r9 = r0 + r7.graphics.getTargetRenderInterval();     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void mainLoop() {
        /*
            Method dump skipped, instructions count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.backends.headless.HeadlessApplication.mainLoop():void");
    }

    public boolean executeRunnables() {
        synchronized (this.runnables) {
            for (int i = this.runnables.size - 1; i >= 0; i--) {
                this.executedRunnables.add(this.runnables.get(i));
            }
            this.runnables.clear();
        }
        if (this.executedRunnables.size == 0) {
            return false;
        }
        for (int i2 = this.executedRunnables.size - 1; i2 >= 0; i2--) {
            this.executedRunnables.removeIndex(i2).run();
        }
        return true;
    }

    @Override // com.badlogic.gdx.Application
    public ApplicationListener getApplicationListener() {
        return this.listener;
    }

    @Override // com.badlogic.gdx.Application
    public Graphics getGraphics() {
        return this.graphics;
    }

    @Override // com.badlogic.gdx.Application
    public Audio getAudio() {
        return this.audio;
    }

    @Override // com.badlogic.gdx.Application
    public Input getInput() {
        return this.input;
    }

    @Override // com.badlogic.gdx.Application
    public Files getFiles() {
        return this.files;
    }

    @Override // com.badlogic.gdx.Application
    public Net getNet() {
        return this.f884net;
    }

    @Override // com.badlogic.gdx.Application
    public Application.ApplicationType getType() {
        return Application.ApplicationType.HeadlessDesktop;
    }

    @Override // com.badlogic.gdx.Application
    public int getVersion() {
        return 0;
    }

    @Override // com.badlogic.gdx.Application
    public long getJavaHeap() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    @Override // com.badlogic.gdx.Application
    public long getNativeHeap() {
        return getJavaHeap();
    }

    @Override // com.badlogic.gdx.Application
    public Preferences getPreferences(String str) {
        if (this.preferences.containsKey(str)) {
            return this.preferences.get(str);
        }
        HeadlessPreferences headlessPreferences = new HeadlessPreferences(str, this.preferencesdir);
        this.preferences.put(str, headlessPreferences);
        return headlessPreferences;
    }

    @Override // com.badlogic.gdx.Application
    public Clipboard getClipboard() {
        return null;
    }

    @Override // com.badlogic.gdx.Application
    public void postRunnable(Runnable runnable) {
        synchronized (this.runnables) {
            this.runnables.add(runnable);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void debug(String str, String str2) {
        if (this.logLevel >= 3) {
            getApplicationLogger().debug(str, str2);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void debug(String str, String str2, Throwable th) {
        if (this.logLevel >= 3) {
            getApplicationLogger().debug(str, str2, th);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void log(String str, String str2) {
        if (this.logLevel >= 2) {
            getApplicationLogger().log(str, str2);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void log(String str, String str2, Throwable th) {
        if (this.logLevel >= 2) {
            getApplicationLogger().log(str, str2, th);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void error(String str, String str2) {
        if (this.logLevel > 0) {
            getApplicationLogger().error(str, str2);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void error(String str, String str2, Throwable th) {
        if (this.logLevel > 0) {
            getApplicationLogger().error(str, str2, th);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void setLogLevel(int i) {
        this.logLevel = i;
    }

    @Override // com.badlogic.gdx.Application
    public int getLogLevel() {
        return this.logLevel;
    }

    @Override // com.badlogic.gdx.Application
    public void setApplicationLogger(ApplicationLogger applicationLogger) {
        this.applicationLogger = applicationLogger;
    }

    @Override // com.badlogic.gdx.Application
    public ApplicationLogger getApplicationLogger() {
        return this.applicationLogger;
    }

    @Override // com.badlogic.gdx.Application
    public void exit() {
        postRunnable(new Runnable() { // from class: com.badlogic.gdx.backends.headless.HeadlessApplication.2
            @Override // java.lang.Runnable
            public void run() {
                HeadlessApplication.this.running = false;
            }
        });
    }

    @Override // com.badlogic.gdx.Application
    public void addLifecycleListener(LifecycleListener lifecycleListener) {
        synchronized (this.lifecycleListeners) {
            this.lifecycleListeners.add(lifecycleListener);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void removeLifecycleListener(LifecycleListener lifecycleListener) {
        synchronized (this.lifecycleListeners) {
            this.lifecycleListeners.removeValue(lifecycleListener, true);
        }
    }
}
