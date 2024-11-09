package com.badlogic.gdx.ai;

import com.badlogic.gdx.Gdx;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/GdxAI.class */
public final class GdxAI {
    private static Timepiece timepiece = new DefaultTimepiece();
    private static Logger logger;
    private static FileSystem fileSystem;

    private GdxAI() {
    }

    static {
        logger = Gdx.app == null ? new NullLogger() : new GdxLogger();
        fileSystem = Gdx.files == null ? new StandaloneFileSystem() : new GdxFileSystem();
    }

    public static Timepiece getTimepiece() {
        return timepiece;
    }

    public static void setTimepiece(Timepiece timepiece2) {
        timepiece = timepiece2;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger2) {
        logger = logger2;
    }

    public static FileSystem getFileSystem() {
        return fileSystem;
    }

    public static void setFileSystem(FileSystem fileSystem2) {
        fileSystem = fileSystem2;
    }
}
