package com.badlogic.gdx.backends.lwjgl3.audio;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.BufferUtils;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.openal.AL10;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/OpenALSound.class */
public class OpenALSound implements Sound {
    private int bufferID = -1;
    private final OpenALLwjgl3Audio audio;
    private float duration;
    private int sampleRate;
    private int channels;
    private String type;

    public OpenALSound(OpenALLwjgl3Audio openALLwjgl3Audio) {
        this.audio = openALLwjgl3Audio;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setup(byte[] bArr, int i, int i2, int i3) {
        int length = bArr.length - (bArr.length % (i * (i2 >> 3)));
        ByteBuffer newByteBuffer = BufferUtils.newByteBuffer(length);
        newByteBuffer.put(bArr, 0, length);
        newByteBuffer.flip();
        setup(newByteBuffer.asShortBuffer(), i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setup(ShortBuffer shortBuffer, int i, int i2, int i3) {
        this.channels = i;
        this.sampleRate = i3;
        this.duration = (((shortBuffer.limit() << 1) / (i2 >> 3)) / i) / i3;
        if (this.bufferID == -1) {
            this.bufferID = AL10.alGenBuffers();
            AL10.alBufferData(this.bufferID, OpenALUtils.determineFormat(i, i2), shortBuffer, i3);
        }
    }

    @Override // com.badlogic.gdx.audio.Sound
    public long play() {
        return play(1.0f);
    }

    @Override // com.badlogic.gdx.audio.Sound
    public long play(float f) {
        if (this.audio.noDevice) {
            return 0L;
        }
        int obtainSource = this.audio.obtainSource(false);
        int i = obtainSource;
        if (obtainSource == -1) {
            this.audio.retain(this, true);
            i = this.audio.obtainSource(false);
        } else {
            this.audio.retain(this, false);
        }
        if (i == -1) {
            return -1L;
        }
        long soundId = this.audio.getSoundId(i);
        AL10.alSourcei(i, 4105, this.bufferID);
        AL10.alSourcei(i, 4103, 0);
        AL10.alSourcef(i, AL10.AL_GAIN, f);
        AL10.alSourcePlay(i);
        return soundId;
    }

    @Override // com.badlogic.gdx.audio.Sound
    public long loop() {
        return loop(1.0f);
    }

    @Override // com.badlogic.gdx.audio.Sound
    public long loop(float f) {
        if (this.audio.noDevice) {
            return 0L;
        }
        int obtainSource = this.audio.obtainSource(false);
        if (obtainSource == -1) {
            return -1L;
        }
        long soundId = this.audio.getSoundId(obtainSource);
        AL10.alSourcei(obtainSource, 4105, this.bufferID);
        AL10.alSourcei(obtainSource, 4103, 1);
        AL10.alSourcef(obtainSource, AL10.AL_GAIN, f);
        AL10.alSourcePlay(obtainSource);
        return soundId;
    }

    @Override // com.badlogic.gdx.audio.Sound
    public void stop() {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.stopSourcesWithBuffer(this.bufferID);
    }

    @Override // com.badlogic.gdx.audio.Sound, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.audio.noDevice || this.bufferID == -1) {
            return;
        }
        this.audio.freeBuffer(this.bufferID);
        AL10.alDeleteBuffers(this.bufferID);
        this.bufferID = -1;
        this.audio.forget(this);
    }

    @Override // com.badlogic.gdx.audio.Sound
    public void stop(long j) {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.stopSound(j);
    }

    @Override // com.badlogic.gdx.audio.Sound
    public void pause() {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.pauseSourcesWithBuffer(this.bufferID);
    }

    @Override // com.badlogic.gdx.audio.Sound
    public void pause(long j) {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.pauseSound(j);
    }

    @Override // com.badlogic.gdx.audio.Sound
    public void resume() {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.resumeSourcesWithBuffer(this.bufferID);
    }

    @Override // com.badlogic.gdx.audio.Sound
    public void resume(long j) {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.resumeSound(j);
    }

    @Override // com.badlogic.gdx.audio.Sound
    public void setPitch(long j, float f) {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.setSoundPitch(j, f);
    }

    @Override // com.badlogic.gdx.audio.Sound
    public void setVolume(long j, float f) {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.setSoundGain(j, f);
    }

    @Override // com.badlogic.gdx.audio.Sound
    public void setLooping(long j, boolean z) {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.setSoundLooping(j, z);
    }

    @Override // com.badlogic.gdx.audio.Sound
    public void setPan(long j, float f, float f2) {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.setSoundPan(j, f, f2);
    }

    @Override // com.badlogic.gdx.audio.Sound
    public long play(float f, float f2, float f3) {
        long play = play();
        setPitch(play, f2);
        setPan(play, f3, f);
        return play;
    }

    @Override // com.badlogic.gdx.audio.Sound
    public long loop(float f, float f2, float f3) {
        long loop = loop();
        setPitch(loop, f2);
        setPan(loop, f3, f);
        return loop;
    }

    public float duration() {
        return this.duration;
    }

    public int getRate() {
        return this.sampleRate;
    }

    public int getChannels() {
        return this.channels;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getType() {
        return this.type;
    }
}
