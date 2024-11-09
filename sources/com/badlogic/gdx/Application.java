package com.badlogic.gdx;

import com.badlogic.gdx.utils.Clipboard;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/Application.class */
public interface Application {
    public static final int LOG_NONE = 0;
    public static final int LOG_DEBUG = 3;
    public static final int LOG_INFO = 2;
    public static final int LOG_ERROR = 1;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Application$ApplicationType.class */
    public enum ApplicationType {
        Android,
        Desktop,
        HeadlessDesktop,
        Applet,
        WebGL,
        iOS
    }

    ApplicationListener getApplicationListener();

    Graphics getGraphics();

    Audio getAudio();

    Input getInput();

    Files getFiles();

    Net getNet();

    void log(String str, String str2);

    void log(String str, String str2, Throwable th);

    void error(String str, String str2);

    void error(String str, String str2, Throwable th);

    void debug(String str, String str2);

    void debug(String str, String str2, Throwable th);

    void setLogLevel(int i);

    int getLogLevel();

    void setApplicationLogger(ApplicationLogger applicationLogger);

    ApplicationLogger getApplicationLogger();

    ApplicationType getType();

    int getVersion();

    long getJavaHeap();

    long getNativeHeap();

    Preferences getPreferences(String str);

    Clipboard getClipboard();

    void postRunnable(Runnable runnable);

    void exit();

    void addLifecycleListener(LifecycleListener lifecycleListener);

    void removeLifecycleListener(LifecycleListener lifecycleListener);
}
