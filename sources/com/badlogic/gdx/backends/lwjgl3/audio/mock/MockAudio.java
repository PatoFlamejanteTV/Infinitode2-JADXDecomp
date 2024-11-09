package com.badlogic.gdx.backends.lwjgl3.audio.mock;

import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl3.audio.Lwjgl3Audio;
import com.badlogic.gdx.files.FileHandle;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/mock/MockAudio.class */
public class MockAudio implements Lwjgl3Audio {
    @Override // com.badlogic.gdx.Audio
    public AudioDevice newAudioDevice(int i, boolean z) {
        return new MockAudioDevice();
    }

    @Override // com.badlogic.gdx.Audio
    public AudioRecorder newAudioRecorder(int i, boolean z) {
        return new MockAudioRecorder();
    }

    @Override // com.badlogic.gdx.Audio
    public Sound newSound(FileHandle fileHandle) {
        return new MockSound();
    }

    @Override // com.badlogic.gdx.Audio
    public Music newMusic(FileHandle fileHandle) {
        return new MockMusic();
    }

    @Override // com.badlogic.gdx.Audio
    public boolean switchOutputDevice(String str) {
        return true;
    }

    @Override // com.badlogic.gdx.Audio
    public String[] getAvailableOutputDevices() {
        return new String[0];
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.audio.Lwjgl3Audio
    public void update() {
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }
}
