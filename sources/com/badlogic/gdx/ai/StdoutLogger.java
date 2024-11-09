package com.badlogic.gdx.ai;

import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/StdoutLogger.class */
public class StdoutLogger implements Logger {
    @Override // com.badlogic.gdx.ai.Logger
    public void debug(String str, String str2) {
        println("DEBUG", str, str2);
    }

    @Override // com.badlogic.gdx.ai.Logger
    public void debug(String str, String str2, Throwable th) {
        println("DEBUG", str, str2, th);
    }

    @Override // com.badlogic.gdx.ai.Logger
    public void info(String str, String str2) {
        println("INFO", str, str2);
    }

    @Override // com.badlogic.gdx.ai.Logger
    public void info(String str, String str2, Throwable th) {
        println("INFO", str, str2, th);
    }

    @Override // com.badlogic.gdx.ai.Logger
    public void error(String str, String str2) {
        println("ERROR", str, str2);
    }

    @Override // com.badlogic.gdx.ai.Logger
    public void error(String str, String str2, Throwable th) {
        println("ERROR", str, str2, th);
    }

    private void println(String str, String str2, String str3) {
        System.out.println(str + SequenceUtils.SPACE + str2 + ": " + str3);
    }

    private void println(String str, String str2, String str3, Throwable th) {
        println(str, str2, str3);
        th.printStackTrace();
    }
}
