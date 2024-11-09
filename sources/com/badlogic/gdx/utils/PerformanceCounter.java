package com.badlogic.gdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.FloatCounter;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/PerformanceCounter.class */
public class PerformanceCounter {
    private static final float nano2seconds = 1.0E-9f;
    private long startTime;
    private long lastTick;
    public final FloatCounter time;
    public final FloatCounter load;
    public final String name;
    public float current;
    public boolean valid;

    public PerformanceCounter(String str) {
        this(str, 5);
    }

    public PerformanceCounter(String str, int i) {
        this.startTime = 0L;
        this.lastTick = 0L;
        this.current = 0.0f;
        this.valid = false;
        this.name = str;
        this.time = new FloatCounter(i);
        this.load = new FloatCounter(1);
    }

    public void tick() {
        long nanoTime = TimeUtils.nanoTime();
        if (this.lastTick > 0) {
            tick(((float) (nanoTime - this.lastTick)) * 1.0E-9f);
        }
        this.lastTick = nanoTime;
    }

    public void tick(float f) {
        if (!this.valid) {
            Gdx.app.error("PerformanceCounter", "Invalid data, check if you called PerformanceCounter#stop()");
            return;
        }
        this.time.put(this.current);
        float f2 = f == 0.0f ? 0.0f : this.current / f;
        this.load.put(f > 1.0f ? f2 : (f * f2) + ((1.0f - f) * this.load.latest));
        this.current = 0.0f;
        this.valid = false;
    }

    public void start() {
        this.startTime = TimeUtils.nanoTime();
        this.valid = false;
    }

    public void stop() {
        if (this.startTime > 0) {
            this.current += ((float) (TimeUtils.nanoTime() - this.startTime)) * 1.0E-9f;
            this.startTime = 0L;
            this.valid = true;
        }
    }

    public void reset() {
        this.time.reset();
        this.load.reset();
        this.startTime = 0L;
        this.lastTick = 0L;
        this.current = 0.0f;
        this.valid = false;
    }

    public String toString() {
        return toString(new StringBuilder()).toString();
    }

    public StringBuilder toString(StringBuilder stringBuilder) {
        stringBuilder.append(this.name).append(": [time: ").append(this.time.value).append(", load: ").append(this.load.value).append("]");
        return stringBuilder;
    }
}
