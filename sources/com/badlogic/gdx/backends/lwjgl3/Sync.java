package com.badlogic.gdx.backends.lwjgl3;

import org.lwjgl.glfw.GLFW;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Sync.class */
public class Sync {
    private static final long NANOS_IN_SECOND = 1000000000;
    private long nextFrame = 0;
    private boolean initialised = false;
    private RunningAvg sleepDurations = new RunningAvg(10);
    private RunningAvg yieldDurations = new RunningAvg(10);

    /* JADX WARN: Type inference failed for: r0v19, types: [long, com.badlogic.gdx.backends.lwjgl3.Sync$RunningAvg] */
    /* JADX WARN: Type inference failed for: r0v23, types: [long, com.badlogic.gdx.backends.lwjgl3.Sync$RunningAvg] */
    public void sync(int i) {
        if (i <= 0) {
            return;
        }
        if (!this.initialised) {
            initialise();
        }
        try {
            long time = getTime();
            while (this.nextFrame - time > this.sleepDurations.avg()) {
                Thread.sleep(1L);
                ?? r0 = this.sleepDurations;
                long time2 = getTime();
                r0.add(r0 - time);
                time = time2;
            }
            this.sleepDurations.dampenForLowResTicker();
            long time3 = getTime();
            while (this.nextFrame - time3 > this.yieldDurations.avg()) {
                Thread.yield();
                ?? r02 = this.yieldDurations;
                long time4 = getTime();
                r02.add(r02 - time3);
                time3 = time4;
            }
        } catch (InterruptedException unused) {
        }
        this.nextFrame = Math.max(this.nextFrame + (NANOS_IN_SECOND / i), getTime());
    }

    private void initialise() {
        this.initialised = true;
        this.sleepDurations.init(1000000L);
        this.yieldDurations.init((int) ((-(getTime() - getTime())) * 1.333d));
        this.nextFrame = getTime();
        if (System.getProperty("os.name").startsWith("Win")) {
            Thread thread = new Thread(new Runnable() { // from class: com.badlogic.gdx.backends.lwjgl3.Sync.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (Exception unused) {
                    }
                }
            });
            thread.setName("LWJGL3 Timer");
            thread.setDaemon(true);
            thread.start();
        }
    }

    private long getTime() {
        return (long) (GLFW.glfwGetTime() * 1.0E9d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Sync$RunningAvg.class */
    public class RunningAvg {
        private final long[] slots;
        private int offset = 0;
        private static final long DAMPEN_THRESHOLD = 10000000;
        private static final float DAMPEN_FACTOR = 0.9f;

        public RunningAvg(int i) {
            this.slots = new long[i];
        }

        public void init(long j) {
            while (this.offset < this.slots.length) {
                long[] jArr = this.slots;
                int i = this.offset;
                this.offset = i + 1;
                jArr[i] = j;
            }
        }

        public void add(long j) {
            long[] jArr = this.slots;
            int i = this.offset;
            this.offset = i + 1;
            jArr[i % this.slots.length] = j;
            this.offset %= this.slots.length;
        }

        public long avg() {
            long j = 0;
            for (int i = 0; i < this.slots.length; i++) {
                j += this.slots[i];
            }
            return j / this.slots.length;
        }

        public void dampenForLowResTicker() {
            if (avg() > DAMPEN_THRESHOLD) {
                for (int i = 0; i < this.slots.length; i++) {
                    this.slots[i] = ((float) r0[r1]) * DAMPEN_FACTOR;
                }
            }
        }
    }
}
