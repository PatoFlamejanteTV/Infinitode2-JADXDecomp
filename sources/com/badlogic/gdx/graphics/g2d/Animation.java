package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ArrayReflection;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/Animation.class */
public class Animation<T> {
    T[] keyFrames;
    private float frameDuration;
    private float animationDuration;
    private int lastFrameNumber;
    private float lastStateTime;
    private PlayMode playMode;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/Animation$PlayMode.class */
    public enum PlayMode {
        NORMAL,
        REVERSED,
        LOOP,
        LOOP_REVERSED,
        LOOP_PINGPONG,
        LOOP_RANDOM
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Animation(float f, Array<? extends T> array) {
        this.playMode = PlayMode.NORMAL;
        this.frameDuration = f;
        Object[] objArr = (Object[]) ArrayReflection.newInstance(array.items.getClass().getComponentType(), array.size);
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = array.get(i2);
        }
        setKeyFrames(objArr);
    }

    public Animation(float f, Array<? extends T> array, PlayMode playMode) {
        this(f, array);
        setPlayMode(playMode);
    }

    public Animation(float f, T... tArr) {
        this.playMode = PlayMode.NORMAL;
        this.frameDuration = f;
        setKeyFrames(tArr);
    }

    public T getKeyFrame(float f, boolean z) {
        PlayMode playMode = this.playMode;
        if (z && (this.playMode == PlayMode.NORMAL || this.playMode == PlayMode.REVERSED)) {
            if (this.playMode != PlayMode.NORMAL) {
                this.playMode = PlayMode.LOOP_REVERSED;
            }
            this.playMode = PlayMode.LOOP;
        } else if (!z && this.playMode != PlayMode.NORMAL && this.playMode != PlayMode.REVERSED) {
            if (this.playMode == PlayMode.LOOP_REVERSED) {
                this.playMode = PlayMode.REVERSED;
            }
            this.playMode = PlayMode.LOOP;
        }
        T keyFrame = getKeyFrame(f);
        this.playMode = playMode;
        return keyFrame;
    }

    public T getKeyFrame(float f) {
        return this.keyFrames[getKeyFrameIndex(f)];
    }

    public int getKeyFrameIndex(float f) {
        if (this.keyFrames.length == 1) {
            return 0;
        }
        int i = (int) (f / this.frameDuration);
        switch (this.playMode) {
            case NORMAL:
                i = Math.min(this.keyFrames.length - 1, i);
                break;
            case LOOP:
                i %= this.keyFrames.length;
                break;
            case LOOP_PINGPONG:
                int length = i % ((this.keyFrames.length << 1) - 2);
                i = length;
                if (length >= this.keyFrames.length) {
                    i = (this.keyFrames.length - 2) - (i - this.keyFrames.length);
                    break;
                }
                break;
            case LOOP_RANDOM:
                if (((int) (this.lastStateTime / this.frameDuration)) != i) {
                    i = MathUtils.random(this.keyFrames.length - 1);
                    break;
                } else {
                    i = this.lastFrameNumber;
                    break;
                }
            case REVERSED:
                i = Math.max((this.keyFrames.length - i) - 1, 0);
                break;
            case LOOP_REVERSED:
                i = (this.keyFrames.length - (i % this.keyFrames.length)) - 1;
                break;
        }
        this.lastFrameNumber = i;
        this.lastStateTime = f;
        return i;
    }

    public T[] getKeyFrames() {
        return this.keyFrames;
    }

    protected void setKeyFrames(T... tArr) {
        this.keyFrames = tArr;
        this.animationDuration = tArr.length * this.frameDuration;
    }

    public PlayMode getPlayMode() {
        return this.playMode;
    }

    public void setPlayMode(PlayMode playMode) {
        this.playMode = playMode;
    }

    public boolean isAnimationFinished(float f) {
        return this.keyFrames.length - 1 < ((int) (f / this.frameDuration));
    }

    public void setFrameDuration(float f) {
        this.frameDuration = f;
        this.animationDuration = this.keyFrames.length * f;
    }

    public float getFrameDuration() {
        return this.frameDuration;
    }

    public float getAnimationDuration() {
        return this.animationDuration;
    }
}
