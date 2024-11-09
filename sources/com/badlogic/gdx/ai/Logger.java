package com.badlogic.gdx.ai;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/Logger.class */
public interface Logger {
    void debug(String str, String str2);

    void debug(String str, String str2, Throwable th);

    void info(String str, String str2);

    void info(String str, String str2, Throwable th);

    void error(String str, String str2);

    void error(String str, String str2, Throwable th);
}
