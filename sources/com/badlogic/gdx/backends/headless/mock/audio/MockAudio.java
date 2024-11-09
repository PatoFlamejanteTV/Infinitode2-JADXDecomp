package com.badlogic.gdx.backends.headless.mock.audio;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/headless/mock/audio/MockAudio.class */
public class MockAudio implements Audio {
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
}
