package com.badlogic.gdx.utils;

import com.badlogic.gdx.Gdx;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Logger.class */
public class Logger {
    public static final int NONE = 0;
    public static final int ERROR = 1;
    public static final int INFO = 2;
    public static final int DEBUG = 3;
    private final String tag;
    private int level;

    public Logger(String str) {
        this(str, 1);
    }

    public Logger(String str, int i) {
        this.tag = str;
        this.level = i;
    }

    public void debug(String str) {
        if (this.level >= 3) {
            Gdx.app.debug(this.tag, str);
        }
    }

    public void debug(String str, Exception exc) {
        if (this.level >= 3) {
            Gdx.app.debug(this.tag, str, exc);
        }
    }

    public void info(String str) {
        if (this.level >= 2) {
            Gdx.app.log(this.tag, str);
        }
    }

    public void info(String str, Exception exc) {
        if (this.level >= 2) {
            Gdx.app.log(this.tag, str, exc);
        }
    }

    public void error(String str) {
        if (this.level > 0) {
            Gdx.app.error(this.tag, str);
        }
    }

    public void error(String str, Throwable th) {
        if (this.level > 0) {
            Gdx.app.error(this.tag, str, th);
        }
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getLevel() {
        return this.level;
    }
}
