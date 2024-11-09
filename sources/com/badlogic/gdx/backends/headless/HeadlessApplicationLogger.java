package com.badlogic.gdx.backends.headless;

import com.badlogic.gdx.ApplicationLogger;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/headless/HeadlessApplicationLogger.class */
public class HeadlessApplicationLogger implements ApplicationLogger {
    @Override // com.badlogic.gdx.ApplicationLogger
    public void log(String str, String str2) {
        System.out.println("[" + str + "] " + str2);
    }

    @Override // com.badlogic.gdx.ApplicationLogger
    public void log(String str, String str2, Throwable th) {
        System.out.println("[" + str + "] " + str2);
        th.printStackTrace(System.out);
    }

    @Override // com.badlogic.gdx.ApplicationLogger
    public void error(String str, String str2) {
        System.err.println("[" + str + "] " + str2);
    }

    @Override // com.badlogic.gdx.ApplicationLogger
    public void error(String str, String str2, Throwable th) {
        System.err.println("[" + str + "] " + str2);
        th.printStackTrace(System.err);
    }

    @Override // com.badlogic.gdx.ApplicationLogger
    public void debug(String str, String str2) {
        System.out.println("[" + str + "] " + str2);
    }

    @Override // com.badlogic.gdx.ApplicationLogger
    public void debug(String str, String str2, Throwable th) {
        System.out.println("[" + str + "] " + str2);
        th.printStackTrace(System.out);
    }
}