package com.badlogic.gdx.backends.lwjgl3.audio.mock;

import com.badlogic.gdx.audio.Music;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/mock/MockMusic.class */
public class MockMusic implements Music {
    @Override // com.badlogic.gdx.audio.Music
    public void play() {
    }

    @Override // com.badlogic.gdx.audio.Music
    public void pause() {
    }

    @Override // com.badlogic.gdx.audio.Music
    public void stop() {
    }

    @Override // com.badlogic.gdx.audio.Music
    public boolean isPlaying() {
        return false;
    }

    @Override // com.badlogic.gdx.audio.Music
    public void setLooping(boolean z) {
    }

    @Override // com.badlogic.gdx.audio.Music
    public boolean isLooping() {
        return false;
    }

    @Override // com.badlogic.gdx.audio.Music
    public void setVolume(float f) {
    }

    @Override // com.badlogic.gdx.audio.Music
    public float getVolume() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.audio.Music
    public void setPan(float f, float f2) {
    }

    @Override // com.badlogic.gdx.audio.Music
    public void setPosition(float f) {
    }

    @Override // com.badlogic.gdx.audio.Music
    public float getPosition() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.audio.Music, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    @Override // com.badlogic.gdx.audio.Music
    public void setOnCompletionListener(Music.OnCompletionListener onCompletionListener) {
    }
}
