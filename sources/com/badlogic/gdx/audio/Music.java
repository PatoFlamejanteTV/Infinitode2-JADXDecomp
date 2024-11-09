package com.badlogic.gdx.audio;

import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/audio/Music.class */
public interface Music extends Disposable {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/audio/Music$OnCompletionListener.class */
    public interface OnCompletionListener {
        void onCompletion(Music music);
    }

    void play();

    void pause();

    void stop();

    boolean isPlaying();

    void setLooping(boolean z);

    boolean isLooping();

    void setVolume(float f);

    float getVolume();

    void setPan(float f, float f2);

    void setPosition(float f);

    float getPosition();

    @Override // com.badlogic.gdx.utils.Disposable
    void dispose();

    void setOnCompletionListener(OnCompletionListener onCompletionListener);
}
