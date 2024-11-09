package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/FPSLogger.class */
public class FPSLogger {
    long startTime;
    int bound;

    public FPSLogger() {
        this(Integer.MAX_VALUE);
    }

    public FPSLogger(int i) {
        this.bound = i;
        this.startTime = TimeUtils.nanoTime();
    }

    public void log() {
        int framesPerSecond;
        long nanoTime = TimeUtils.nanoTime();
        if (nanoTime - this.startTime > 1000000000 && (framesPerSecond = Gdx.graphics.getFramesPerSecond()) < this.bound) {
            Gdx.app.log("FPSLogger", "fps: " + framesPerSecond);
            this.startTime = nanoTime;
        }
    }
}
