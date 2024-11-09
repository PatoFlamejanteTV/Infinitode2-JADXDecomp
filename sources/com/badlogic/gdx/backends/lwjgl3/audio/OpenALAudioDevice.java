package com.badlogic.gdx.backends.lwjgl3.audio;

import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/OpenALAudioDevice.class */
public class OpenALAudioDevice implements AudioDevice {
    private static final int bytesPerSample = 2;
    private final OpenALLwjgl3Audio audio;
    private final int channels;
    private IntBuffer buffers;
    private int format;
    private int sampleRate;
    private boolean isPlaying;
    private float renderedSeconds;
    private float secondsPerBuffer;
    private byte[] bytes;
    private final int bufferSize;
    private final int bufferCount;
    private final ByteBuffer tempBuffer;
    private int sourceID = -1;
    private float volume = 1.0f;

    public OpenALAudioDevice(OpenALLwjgl3Audio openALLwjgl3Audio, int i, boolean z, int i2, int i3) {
        this.audio = openALLwjgl3Audio;
        this.channels = z ? 1 : 2;
        this.bufferSize = i2;
        this.bufferCount = i3;
        this.format = this.channels > 1 ? 4355 : 4353;
        this.sampleRate = i;
        this.secondsPerBuffer = ((i2 / 2.0f) / this.channels) / i;
        this.tempBuffer = BufferUtils.createByteBuffer(i2);
    }

    @Override // com.badlogic.gdx.audio.AudioDevice
    public void writeSamples(short[] sArr, int i, int i2) {
        if (this.bytes == null || this.bytes.length < (i2 << 1)) {
            this.bytes = new byte[i2 << 1];
        }
        int min = Math.min(i + i2, sArr.length);
        int i3 = 0;
        for (int i4 = i; i4 < min; i4++) {
            short s = sArr[i4];
            int i5 = i3;
            int i6 = i3 + 1;
            this.bytes[i5] = (byte) s;
            i3 = i6 + 1;
            this.bytes[i6] = (byte) (s >> 8);
        }
        writeSamples(this.bytes, 0, i2 << 1);
    }

    @Override // com.badlogic.gdx.audio.AudioDevice
    public void writeSamples(float[] fArr, int i, int i2) {
        if (this.bytes == null || this.bytes.length < (i2 << 1)) {
            this.bytes = new byte[i2 << 1];
        }
        int min = Math.min(i + i2, fArr.length);
        int i3 = 0;
        for (int i4 = i; i4 < min; i4++) {
            int clamp = (int) (MathUtils.clamp(fArr[i4], -1.0f, 1.0f) * 32767.0f);
            int i5 = i3;
            int i6 = i3 + 1;
            this.bytes[i5] = (byte) clamp;
            i3 = i6 + 1;
            this.bytes[i6] = (byte) (clamp >> 8);
        }
        writeSamples(this.bytes, 0, i2 << 1);
    }

    public void writeSamples(byte[] bArr, int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("length cannot be < 0.");
        }
        if (this.sourceID == -1) {
            this.sourceID = this.audio.obtainSource(true);
            if (this.sourceID == -1) {
                return;
            }
            if (this.buffers == null) {
                this.buffers = BufferUtils.createIntBuffer(this.bufferCount);
                AL10.alGetError();
                AL10.alGenBuffers(this.buffers);
                if (AL10.alGetError() != 0) {
                    throw new GdxRuntimeException("Unabe to allocate audio buffers.");
                }
            }
            AL10.alSourcei(this.sourceID, 4103, 0);
            AL10.alSourcef(this.sourceID, AL10.AL_GAIN, this.volume);
            for (int i3 = 0; i3 < this.bufferCount; i3++) {
                int i4 = this.buffers.get(i3);
                int min = Math.min(this.bufferSize, i2);
                this.tempBuffer.clear();
                this.tempBuffer.put(bArr, i, min).flip();
                AL10.alBufferData(i4, this.format, this.tempBuffer, this.sampleRate);
                AL10.alSourceQueueBuffers(this.sourceID, i4);
                i2 -= min;
                i += min;
            }
            AL10.alSourcePlay(this.sourceID);
            this.isPlaying = true;
        }
        while (i2 > 0) {
            int fillBuffer = fillBuffer(bArr, i, i2);
            i2 -= fillBuffer;
            i += fillBuffer;
        }
    }

    private int fillBuffer(byte[] bArr, int i, int i2) {
        int alSourceUnqueueBuffers;
        int min = Math.min(this.bufferSize, i2);
        while (true) {
            if (AL10.alGetSourcei(this.sourceID, AL10.AL_BUFFERS_PROCESSED) > 0 && (alSourceUnqueueBuffers = AL10.alSourceUnqueueBuffers(this.sourceID)) != 40963) {
                break;
            }
            try {
                Thread.sleep(1000.0f * this.secondsPerBuffer);
            } catch (InterruptedException unused) {
            }
        }
        this.renderedSeconds += this.secondsPerBuffer;
        this.tempBuffer.clear();
        this.tempBuffer.put(bArr, i, min).flip();
        AL10.alBufferData(alSourceUnqueueBuffers, this.format, this.tempBuffer, this.sampleRate);
        AL10.alSourceQueueBuffers(this.sourceID, alSourceUnqueueBuffers);
        if (!this.isPlaying || AL10.alGetSourcei(this.sourceID, 4112) != 4114) {
            AL10.alSourcePlay(this.sourceID);
            this.isPlaying = true;
        }
        return min;
    }

    public void stop() {
        if (this.sourceID == -1) {
            return;
        }
        this.audio.freeSource(this.sourceID);
        this.sourceID = -1;
        this.renderedSeconds = 0.0f;
        this.isPlaying = false;
    }

    public boolean isPlaying() {
        if (this.sourceID == -1) {
            return false;
        }
        return this.isPlaying;
    }

    @Override // com.badlogic.gdx.audio.AudioDevice
    public void setVolume(float f) {
        this.volume = f;
        if (this.sourceID != -1) {
            AL10.alSourcef(this.sourceID, AL10.AL_GAIN, f);
        }
    }

    public float getPosition() {
        if (this.sourceID == -1) {
            return 0.0f;
        }
        return this.renderedSeconds + AL10.alGetSourcef(this.sourceID, 4132);
    }

    public void setPosition(float f) {
        this.renderedSeconds = f;
    }

    public int getChannels() {
        return this.format == 4355 ? 2 : 1;
    }

    public int getRate() {
        return this.sampleRate;
    }

    @Override // com.badlogic.gdx.audio.AudioDevice, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.buffers == null) {
            return;
        }
        if (this.sourceID != -1) {
            this.audio.freeSource(this.sourceID);
            this.sourceID = -1;
        }
        AL10.alDeleteBuffers(this.buffers);
        this.buffers = null;
    }

    @Override // com.badlogic.gdx.audio.AudioDevice
    public boolean isMono() {
        return this.channels == 1;
    }

    @Override // com.badlogic.gdx.audio.AudioDevice
    public int getLatency() {
        return (int) (((this.bufferSize / 2.0f) / this.channels) * this.bufferCount);
    }

    @Override // com.badlogic.gdx.audio.AudioDevice
    public void pause() {
    }

    @Override // com.badlogic.gdx.audio.AudioDevice
    public void resume() {
    }
}
