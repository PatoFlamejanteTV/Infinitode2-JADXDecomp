package com.badlogic.gdx.backends.lwjgl3.audio.mock;

import com.badlogic.gdx.audio.AudioDevice;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/mock/MockAudioDevice.class */
public class MockAudioDevice implements AudioDevice {
    @Override // com.badlogic.gdx.audio.AudioDevice
    public boolean isMono() {
        return false;
    }

    @Override // com.badlogic.gdx.audio.AudioDevice
    public void writeSamples(short[] sArr, int i, int i2) {
    }

    @Override // com.badlogic.gdx.audio.AudioDevice
    public void writeSamples(float[] fArr, int i, int i2) {
    }

    @Override // com.badlogic.gdx.audio.AudioDevice
    public int getLatency() {
        return 0;
    }

    @Override // com.badlogic.gdx.audio.AudioDevice, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    @Override // com.badlogic.gdx.audio.AudioDevice
    public void setVolume(float f) {
    }

    @Override // com.badlogic.gdx.audio.AudioDevice
    public void pause() {
    }

    @Override // com.badlogic.gdx.audio.AudioDevice
    public void resume() {
    }
}
