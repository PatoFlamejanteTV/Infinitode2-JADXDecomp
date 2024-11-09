package com.badlogic.gdx.audio;

import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/audio/AudioDevice.class */
public interface AudioDevice extends Disposable {
    boolean isMono();

    void writeSamples(short[] sArr, int i, int i2);

    void writeSamples(float[] fArr, int i, int i2);

    int getLatency();

    @Override // com.badlogic.gdx.utils.Disposable
    void dispose();

    void setVolume(float f);

    void pause();

    void resume();
}
