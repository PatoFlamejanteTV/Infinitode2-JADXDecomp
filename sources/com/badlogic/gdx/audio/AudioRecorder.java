package com.badlogic.gdx.audio;

import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/audio/AudioRecorder.class */
public interface AudioRecorder extends Disposable {
    void read(short[] sArr, int i, int i2);

    @Override // com.badlogic.gdx.utils.Disposable
    void dispose();
}
