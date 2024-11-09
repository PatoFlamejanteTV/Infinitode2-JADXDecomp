package com.badlogic.gdx;

import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.NumberUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/InputEventQueue.class */
public class InputEventQueue {
    private static final int SKIP = -1;
    private static final int KEY_DOWN = 0;
    private static final int KEY_UP = 1;
    private static final int KEY_TYPED = 2;
    private static final int TOUCH_DOWN = 3;
    private static final int TOUCH_UP = 4;
    private static final int TOUCH_DRAGGED = 5;
    private static final int MOUSE_MOVED = 6;
    private static final int SCROLLED = 7;
    private final IntArray queue = new IntArray();
    private final IntArray processingQueue = new IntArray();
    private long currentEventTime;

    public void drain(@Null InputProcessor inputProcessor) {
        synchronized (this) {
            if (inputProcessor == null) {
                this.queue.clear();
                return;
            }
            this.processingQueue.addAll(this.queue);
            this.queue.clear();
            int[] iArr = this.processingQueue.items;
            int i = 0;
            int i2 = this.processingQueue.size;
            while (i < i2) {
                int i3 = i;
                int i4 = iArr[i3];
                long j = iArr[r11] << 32;
                int i5 = i + 1 + 1 + 1;
                this.currentEventTime = j | (iArr[r11] & 4294967295L);
                switch (i4) {
                    case -1:
                        i = i5 + iArr[i5];
                        break;
                    case 0:
                        i = i5 + 1;
                        inputProcessor.keyDown(iArr[i5]);
                        break;
                    case 1:
                        i = i5 + 1;
                        inputProcessor.keyUp(iArr[i5]);
                        break;
                    case 2:
                        i = i5 + 1;
                        inputProcessor.keyTyped((char) iArr[i5]);
                        break;
                    case 3:
                        int i6 = i5 + 1;
                        int i7 = iArr[i5];
                        int i8 = i6 + 1;
                        int i9 = iArr[i6];
                        int i10 = i8 + 1;
                        int i11 = iArr[i8];
                        i = i10 + 1;
                        inputProcessor.touchDown(i7, i9, i11, iArr[i10]);
                        break;
                    case 4:
                        int i12 = i5 + 1;
                        int i13 = iArr[i5];
                        int i14 = i12 + 1;
                        int i15 = iArr[i12];
                        int i16 = i14 + 1;
                        int i17 = iArr[i14];
                        i = i16 + 1;
                        inputProcessor.touchUp(i13, i15, i17, iArr[i16]);
                        break;
                    case 5:
                        int i18 = i5 + 1;
                        int i19 = iArr[i5];
                        int i20 = i18 + 1;
                        int i21 = iArr[i18];
                        i = i20 + 1;
                        inputProcessor.touchDragged(i19, i21, iArr[i20]);
                        break;
                    case 6:
                        int i22 = i5 + 1;
                        int i23 = iArr[i5];
                        i = i22 + 1;
                        inputProcessor.mouseMoved(i23, iArr[i22]);
                        break;
                    case 7:
                        int i24 = i5 + 1;
                        float intBitsToFloat = NumberUtils.intBitsToFloat(iArr[i5]);
                        i = i24 + 1;
                        inputProcessor.scrolled(intBitsToFloat, NumberUtils.intBitsToFloat(iArr[i24]));
                        break;
                    default:
                        throw new RuntimeException();
                }
            }
            this.processingQueue.clear();
        }
    }

    private synchronized int next(int i, int i2) {
        int[] iArr = this.queue.items;
        int i3 = this.queue.size;
        while (i2 < i3) {
            int i4 = iArr[i2];
            if (i4 == i) {
                return i2;
            }
            int i5 = i2 + 3;
            switch (i4) {
                case -1:
                    i2 = i5 + iArr[i5];
                    break;
                case 0:
                    i2 = i5 + 1;
                    break;
                case 1:
                    i2 = i5 + 1;
                    break;
                case 2:
                    i2 = i5 + 1;
                    break;
                case 3:
                    i2 = i5 + 4;
                    break;
                case 4:
                    i2 = i5 + 4;
                    break;
                case 5:
                    i2 = i5 + 3;
                    break;
                case 6:
                    i2 = i5 + 2;
                    break;
                case 7:
                    i2 = i5 + 2;
                    break;
                default:
                    throw new RuntimeException();
            }
        }
        return -1;
    }

    private void queueTime(long j) {
        this.queue.add((int) (j >> 32));
        this.queue.add((int) j);
    }

    public synchronized boolean keyDown(int i, long j) {
        this.queue.add(0);
        queueTime(j);
        this.queue.add(i);
        return false;
    }

    public synchronized boolean keyUp(int i, long j) {
        this.queue.add(1);
        queueTime(j);
        this.queue.add(i);
        return false;
    }

    public synchronized boolean keyTyped(char c, long j) {
        this.queue.add(2);
        queueTime(j);
        this.queue.add(c);
        return false;
    }

    public synchronized boolean touchDown(int i, int i2, int i3, int i4, long j) {
        this.queue.add(3);
        queueTime(j);
        this.queue.add(i);
        this.queue.add(i2);
        this.queue.add(i3);
        this.queue.add(i4);
        return false;
    }

    public synchronized boolean touchUp(int i, int i2, int i3, int i4, long j) {
        this.queue.add(4);
        queueTime(j);
        this.queue.add(i);
        this.queue.add(i2);
        this.queue.add(i3);
        this.queue.add(i4);
        return false;
    }

    public synchronized boolean touchDragged(int i, int i2, int i3, long j) {
        int next = next(5, 0);
        while (true) {
            int i4 = next;
            if (i4 >= 0) {
                if (this.queue.get(i4 + 5) == i3) {
                    this.queue.set(i4, -1);
                    this.queue.set(i4 + 3, 3);
                }
                next = next(5, i4 + 6);
            } else {
                this.queue.add(5);
                queueTime(j);
                this.queue.add(i);
                this.queue.add(i2);
                this.queue.add(i3);
                return false;
            }
        }
    }

    public synchronized boolean mouseMoved(int i, int i2, long j) {
        int next = next(6, 0);
        while (true) {
            int i3 = next;
            if (i3 >= 0) {
                this.queue.set(i3, -1);
                this.queue.set(i3 + 3, 2);
                next = next(6, i3 + 5);
            } else {
                this.queue.add(6);
                queueTime(j);
                this.queue.add(i);
                this.queue.add(i2);
                return false;
            }
        }
    }

    public synchronized boolean scrolled(float f, float f2, long j) {
        this.queue.add(7);
        queueTime(j);
        this.queue.add(NumberUtils.floatToIntBits(f));
        this.queue.add(NumberUtils.floatToIntBits(f2));
        return false;
    }

    public long getCurrentEventTime() {
        return this.currentEventTime;
    }
}
