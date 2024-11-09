package com.badlogic.gdx.utils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/PerformanceCounters.class */
public class PerformanceCounters {
    private static final float nano2seconds = 1.0E-9f;
    private long lastTick = 0;
    public final Array<PerformanceCounter> counters = new Array<>();

    public PerformanceCounter add(String str, int i) {
        PerformanceCounter performanceCounter = new PerformanceCounter(str, i);
        this.counters.add(performanceCounter);
        return performanceCounter;
    }

    public PerformanceCounter add(String str) {
        PerformanceCounter performanceCounter = new PerformanceCounter(str);
        this.counters.add(performanceCounter);
        return performanceCounter;
    }

    public void tick() {
        long nanoTime = TimeUtils.nanoTime();
        if (this.lastTick > 0) {
            tick(((float) (nanoTime - this.lastTick)) * 1.0E-9f);
        }
        this.lastTick = nanoTime;
    }

    public void tick(float f) {
        for (int i = 0; i < this.counters.size; i++) {
            this.counters.get(i).tick(f);
        }
    }

    public StringBuilder toString(StringBuilder stringBuilder) {
        stringBuilder.setLength(0);
        for (int i = 0; i < this.counters.size; i++) {
            if (i != 0) {
                stringBuilder.append("; ");
            }
            this.counters.get(i).toString(stringBuilder);
        }
        return stringBuilder;
    }
}
