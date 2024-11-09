package com.badlogic.gdx;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ApplicationLogger.class */
public interface ApplicationLogger {
    void log(String str, String str2);

    void log(String str, String str2, Throwable th);

    void error(String str, String str2);

    void error(String str, String str2, Throwable th);

    void debug(String str, String str2);

    void debug(String str, String str2, Throwable th);
}
