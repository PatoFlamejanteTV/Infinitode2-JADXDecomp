package com.badlogic.gdx.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/input/GestureDetector.class */
public class GestureDetector extends InputAdapter {
    final GestureListener listener;
    private float tapRectangleWidth;
    private float tapRectangleHeight;
    private long tapCountInterval;
    private float longPressSeconds;
    private long maxFlingDelay;
    private boolean inTapRectangle;
    private int tapCount;
    private long lastTapTime;
    private float lastTapX;
    private float lastTapY;
    private int lastTapButton;
    private int lastTapPointer;
    boolean longPressFired;
    private boolean pinching;
    private boolean panning;
    private final VelocityTracker tracker;
    private float tapRectangleCenterX;
    private float tapRectangleCenterY;
    private long touchDownTime;
    Vector2 pointer1;
    private final Vector2 pointer2;
    private final Vector2 initialPointer1;
    private final Vector2 initialPointer2;
    private final Timer.Task longPressTask;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/input/GestureDetector$GestureListener.class */
    public interface GestureListener {
        boolean touchDown(float f, float f2, int i, int i2);

        boolean tap(float f, float f2, int i, int i2);

        boolean longPress(float f, float f2);

        boolean fling(float f, float f2, int i);

        boolean pan(float f, float f2, float f3, float f4);

        boolean panStop(float f, float f2, int i, int i2);

        boolean zoom(float f, float f2);

        boolean pinch(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24);

        void pinchStop();
    }

    public GestureDetector(GestureListener gestureListener) {
        this(20.0f, 0.4f, 1.1f, 2.1474836E9f, gestureListener);
    }

    public GestureDetector(float f, float f2, float f3, float f4, GestureListener gestureListener) {
        this(f, f, f2, f3, f4, gestureListener);
    }

    public GestureDetector(float f, float f2, float f3, float f4, float f5, GestureListener gestureListener) {
        this.tracker = new VelocityTracker();
        this.pointer1 = new Vector2();
        this.pointer2 = new Vector2();
        this.initialPointer1 = new Vector2();
        this.initialPointer2 = new Vector2();
        this.longPressTask = new Timer.Task() { // from class: com.badlogic.gdx.input.GestureDetector.1
            @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
            public void run() {
                if (!GestureDetector.this.longPressFired) {
                    GestureDetector.this.longPressFired = GestureDetector.this.listener.longPress(GestureDetector.this.pointer1.x, GestureDetector.this.pointer1.y);
                }
            }
        };
        if (gestureListener == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        this.tapRectangleWidth = f;
        this.tapRectangleHeight = f2;
        this.tapCountInterval = f3 * 1.0E9f;
        this.longPressSeconds = f4;
        this.maxFlingDelay = f5 * 1.0E9f;
        this.listener = gestureListener;
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean touchDown(int i, int i2, int i3, int i4) {
        return touchDown(i, i2, i3, i4);
    }

    public boolean touchDown(float f, float f2, int i, int i2) {
        if (i > 1) {
            return false;
        }
        if (i == 0) {
            this.pointer1.set(f, f2);
            this.touchDownTime = Gdx.input.getCurrentEventTime();
            this.tracker.start(f, f2, this.touchDownTime);
            if (Gdx.input.isTouched(1)) {
                this.inTapRectangle = false;
                this.pinching = true;
                this.initialPointer1.set(this.pointer1);
                this.initialPointer2.set(this.pointer2);
                this.longPressTask.cancel();
            } else {
                this.inTapRectangle = true;
                this.pinching = false;
                this.longPressFired = false;
                this.tapRectangleCenterX = f;
                this.tapRectangleCenterY = f2;
                if (!this.longPressTask.isScheduled()) {
                    Timer.schedule(this.longPressTask, this.longPressSeconds);
                }
            }
        } else {
            this.pointer2.set(f, f2);
            this.inTapRectangle = false;
            this.pinching = true;
            this.initialPointer1.set(this.pointer1);
            this.initialPointer2.set(this.pointer2);
            this.longPressTask.cancel();
        }
        return this.listener.touchDown(f, f2, i, i2);
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean touchDragged(int i, int i2, int i3) {
        return touchDragged(i, i2, i3);
    }

    public boolean touchDragged(float f, float f2, int i) {
        if (i > 1 || this.longPressFired) {
            return false;
        }
        if (i == 0) {
            this.pointer1.set(f, f2);
        } else {
            this.pointer2.set(f, f2);
        }
        if (this.pinching) {
            return this.listener.zoom(this.initialPointer1.dst(this.initialPointer2), this.pointer1.dst(this.pointer2)) || this.listener.pinch(this.initialPointer1, this.initialPointer2, this.pointer1, this.pointer2);
        }
        this.tracker.update(f, f2, Gdx.input.getCurrentEventTime());
        if (this.inTapRectangle && !isWithinTapRectangle(f, f2, this.tapRectangleCenterX, this.tapRectangleCenterY)) {
            this.longPressTask.cancel();
            this.inTapRectangle = false;
        }
        if (!this.inTapRectangle) {
            this.panning = true;
            return this.listener.pan(f, f2, this.tracker.deltaX, this.tracker.deltaY);
        }
        return false;
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean touchUp(int i, int i2, int i3, int i4) {
        return touchUp(i, i2, i3, i4);
    }

    public boolean touchUp(float f, float f2, int i, int i2) {
        float f3;
        if (i > 1) {
            return false;
        }
        if (this.inTapRectangle) {
            f3 = this.tapRectangleCenterX;
            if (!isWithinTapRectangle(f, f2, f3, this.tapRectangleCenterY)) {
                this.inTapRectangle = false;
            }
        }
        boolean z = this.panning;
        this.panning = false;
        this.longPressTask.cancel();
        if (this.longPressFired) {
            return false;
        }
        if (this.inTapRectangle) {
            if (this.lastTapButton != i2 || this.lastTapPointer != i || TimeUtils.nanoTime() - this.lastTapTime > this.tapCountInterval || !isWithinTapRectangle(f, f2, this.lastTapX, this.lastTapY)) {
                this.tapCount = 0;
            }
            this.tapCount++;
            this.lastTapTime = TimeUtils.nanoTime();
            this.lastTapX = f;
            this.lastTapY = f2;
            this.lastTapButton = i2;
            this.lastTapPointer = i;
            this.touchDownTime = 0L;
            return this.listener.tap(f, f2, this.tapCount, i2);
        }
        if (this.pinching) {
            this.pinching = false;
            this.listener.pinchStop();
            this.panning = true;
            if (i == 0) {
                this.tracker.start(this.pointer2.x, this.pointer2.y, Gdx.input.getCurrentEventTime());
                return false;
            }
            this.tracker.start(this.pointer1.x, this.pointer1.y, Gdx.input.getCurrentEventTime());
            return false;
        }
        boolean z2 = false;
        float f4 = f3;
        if (z) {
            f4 = f3;
            if (!this.panning) {
                int i3 = i;
                z2 = this.listener.panStop(f, f2, i3, i2);
                f4 = i3;
            }
        }
        long currentEventTime = Gdx.input.getCurrentEventTime();
        if (currentEventTime - this.touchDownTime <= this.maxFlingDelay) {
            this.tracker.update(f, f2, currentEventTime);
            z2 = this.listener.fling(this.tracker.getVelocityX(), this.tracker.getVelocityY(), i2) || z2;
        }
        this.touchDownTime = 0L;
        return z2;
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean touchCancelled(int i, int i2, int i3, int i4) {
        cancel();
        return super.touchCancelled(i, i2, i3, i4);
    }

    public void cancel() {
        this.longPressTask.cancel();
        this.longPressFired = true;
    }

    public boolean isLongPressed() {
        return isLongPressed(this.longPressSeconds);
    }

    public boolean isLongPressed(float f) {
        return this.touchDownTime != 0 && TimeUtils.nanoTime() - this.touchDownTime > ((long) (f * 1.0E9f));
    }

    public boolean isPanning() {
        return this.panning;
    }

    public void reset() {
        this.longPressTask.cancel();
        this.touchDownTime = 0L;
        this.panning = false;
        this.inTapRectangle = false;
        this.tracker.lastTime = 0L;
    }

    private boolean isWithinTapRectangle(float f, float f2, float f3, float f4) {
        return Math.abs(f - f3) < this.tapRectangleWidth && Math.abs(f2 - f4) < this.tapRectangleHeight;
    }

    public void invalidateTapSquare() {
        this.inTapRectangle = false;
    }

    public void setTapSquareSize(float f) {
        setTapRectangleSize(f, f);
    }

    public void setTapRectangleSize(float f, float f2) {
        this.tapRectangleWidth = f;
        this.tapRectangleHeight = f2;
    }

    public void setTapCountInterval(float f) {
        this.tapCountInterval = f * 1.0E9f;
    }

    public void setLongPressSeconds(float f) {
        this.longPressSeconds = f;
    }

    public void setMaxFlingDelay(long j) {
        this.maxFlingDelay = j;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/input/GestureDetector$GestureAdapter.class */
    public static class GestureAdapter implements GestureListener {
        @Override // com.badlogic.gdx.input.GestureDetector.GestureListener
        public boolean touchDown(float f, float f2, int i, int i2) {
            return false;
        }

        @Override // com.badlogic.gdx.input.GestureDetector.GestureListener
        public boolean tap(float f, float f2, int i, int i2) {
            return false;
        }

        @Override // com.badlogic.gdx.input.GestureDetector.GestureListener
        public boolean longPress(float f, float f2) {
            return false;
        }

        @Override // com.badlogic.gdx.input.GestureDetector.GestureListener
        public boolean fling(float f, float f2, int i) {
            return false;
        }

        @Override // com.badlogic.gdx.input.GestureDetector.GestureListener
        public boolean pan(float f, float f2, float f3, float f4) {
            return false;
        }

        @Override // com.badlogic.gdx.input.GestureDetector.GestureListener
        public boolean panStop(float f, float f2, int i, int i2) {
            return false;
        }

        @Override // com.badlogic.gdx.input.GestureDetector.GestureListener
        public boolean zoom(float f, float f2) {
            return false;
        }

        @Override // com.badlogic.gdx.input.GestureDetector.GestureListener
        public boolean pinch(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
            return false;
        }

        @Override // com.badlogic.gdx.input.GestureDetector.GestureListener
        public void pinchStop() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/input/GestureDetector$VelocityTracker.class */
    public static class VelocityTracker {
        float lastX;
        float lastY;
        float deltaX;
        float deltaY;
        long lastTime;
        int numSamples;
        int sampleSize = 10;
        float[] meanX = new float[this.sampleSize];
        float[] meanY = new float[this.sampleSize];
        long[] meanTime = new long[this.sampleSize];

        VelocityTracker() {
        }

        public void start(float f, float f2, long j) {
            this.lastX = f;
            this.lastY = f2;
            this.deltaX = 0.0f;
            this.deltaY = 0.0f;
            this.numSamples = 0;
            for (int i = 0; i < this.sampleSize; i++) {
                this.meanX[i] = 0.0f;
                this.meanY[i] = 0.0f;
                this.meanTime[i] = 0;
            }
            this.lastTime = j;
        }

        public void update(float f, float f2, long j) {
            this.deltaX = f - this.lastX;
            this.deltaY = f2 - this.lastY;
            this.lastX = f;
            this.lastY = f2;
            long j2 = j - this.lastTime;
            this.lastTime = j;
            int i = this.numSamples % this.sampleSize;
            this.meanX[i] = this.deltaX;
            this.meanY[i] = this.deltaY;
            this.meanTime[i] = j2;
            this.numSamples++;
        }

        public float getVelocityX() {
            float average = getAverage(this.meanX, this.numSamples);
            float average2 = ((float) getAverage(this.meanTime, this.numSamples)) / 1.0E9f;
            if (average2 == 0.0f) {
                return 0.0f;
            }
            return average / average2;
        }

        public float getVelocityY() {
            float average = getAverage(this.meanY, this.numSamples);
            float average2 = ((float) getAverage(this.meanTime, this.numSamples)) / 1.0E9f;
            if (average2 == 0.0f) {
                return 0.0f;
            }
            return average / average2;
        }

        private float getAverage(float[] fArr, int i) {
            int min = Math.min(this.sampleSize, i);
            float f = 0.0f;
            for (int i2 = 0; i2 < min; i2++) {
                f += fArr[i2];
            }
            return f / min;
        }

        private long getAverage(long[] jArr, int i) {
            int min = Math.min(this.sampleSize, i);
            long j = 0;
            for (int i2 = 0; i2 < min; i2++) {
                j += jArr[i2];
            }
            if (min == 0) {
                return 0L;
            }
            return j / min;
        }

        private float getSum(float[] fArr, int i) {
            int min = Math.min(this.sampleSize, i);
            float f = 0.0f;
            for (int i2 = 0; i2 < min; i2++) {
                f += fArr[i2];
            }
            if (min == 0) {
                return 0.0f;
            }
            return f;
        }
    }
}
