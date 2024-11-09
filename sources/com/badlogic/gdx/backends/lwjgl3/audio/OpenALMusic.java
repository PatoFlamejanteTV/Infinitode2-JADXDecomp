package com.badlogic.gdx.backends.lwjgl3.audio;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/OpenALMusic.class */
public abstract class OpenALMusic implements Music {
    private static final int bufferSize = 40960;
    private static final int bufferCount = 3;
    private static final byte[] tempBytes = new byte[40960];
    private static final ByteBuffer tempBuffer = BufferUtils.createByteBuffer(40960);
    private final OpenALLwjgl3Audio audio;
    private IntBuffer buffers;
    private int format;
    private int sampleRate;
    private boolean isLooping;
    private boolean isPlaying;
    private float renderedSeconds;
    private float maxSecondsPerBuffer;
    protected final FileHandle file;
    private FloatArray renderedSecondsQueue = new FloatArray(3);
    private int sourceID = -1;
    private float volume = 1.0f;
    private float pan = 0.0f;
    private Music.OnCompletionListener onCompletionListener = null;

    public abstract int read(byte[] bArr);

    public abstract void reset();

    public OpenALMusic(OpenALLwjgl3Audio openALLwjgl3Audio, FileHandle fileHandle) {
        this.audio = openALLwjgl3Audio;
        this.file = fileHandle;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setup(int i, int i2, int i3) {
        this.format = OpenALUtils.determineFormat(i, i2);
        this.sampleRate = i3;
        this.maxSecondsPerBuffer = 40960.0f / (((i2 >> 3) * i) * i3);
    }

    @Override // com.badlogic.gdx.audio.Music
    public void play() {
        if (this.audio.noDevice) {
            return;
        }
        if (this.sourceID == -1) {
            this.sourceID = this.audio.obtainSource(true);
            if (this.sourceID == -1) {
                return;
            }
            this.audio.music.add(this);
            if (this.buffers == null) {
                this.buffers = BufferUtils.createIntBuffer(3);
                AL10.alGetError();
                AL10.alGenBuffers(this.buffers);
                int alGetError = AL10.alGetError();
                if (alGetError != 0) {
                    throw new GdxRuntimeException("Unable to allocate audio buffers. AL Error: " + alGetError);
                }
            }
            AL10.alSourcei(this.sourceID, 4103, 0);
            setPan(this.pan, this.volume);
            AL10.alGetError();
            boolean z = false;
            for (int i = 0; i < 3; i++) {
                int i2 = this.buffers.get(i);
                if (!fill(i2)) {
                    break;
                }
                z = true;
                AL10.alSourceQueueBuffers(this.sourceID, i2);
            }
            if (!z && this.onCompletionListener != null) {
                this.onCompletionListener.onCompletion(this);
            }
            if (AL10.alGetError() != 0) {
                stop();
                return;
            }
        }
        if (!this.isPlaying) {
            AL10.alSourcePlay(this.sourceID);
            this.isPlaying = true;
        }
    }

    @Override // com.badlogic.gdx.audio.Music
    public void stop() {
        if (this.audio.noDevice || this.sourceID == -1) {
            return;
        }
        this.audio.music.removeValue(this, true);
        reset();
        this.audio.freeSource(this.sourceID);
        this.sourceID = -1;
        this.renderedSeconds = 0.0f;
        this.renderedSecondsQueue.clear();
        this.isPlaying = false;
    }

    @Override // com.badlogic.gdx.audio.Music
    public void pause() {
        if (this.audio.noDevice) {
            return;
        }
        if (this.sourceID != -1) {
            AL10.alSourcePause(this.sourceID);
        }
        this.isPlaying = false;
    }

    @Override // com.badlogic.gdx.audio.Music
    public boolean isPlaying() {
        if (this.audio.noDevice || this.sourceID == -1) {
            return false;
        }
        return this.isPlaying;
    }

    @Override // com.badlogic.gdx.audio.Music
    public void setLooping(boolean z) {
        this.isLooping = z;
    }

    @Override // com.badlogic.gdx.audio.Music
    public boolean isLooping() {
        return this.isLooping;
    }

    @Override // com.badlogic.gdx.audio.Music
    public void setVolume(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("volume cannot be < 0: " + f);
        }
        this.volume = f;
        if (!this.audio.noDevice && this.sourceID != -1) {
            AL10.alSourcef(this.sourceID, AL10.AL_GAIN, f);
        }
    }

    @Override // com.badlogic.gdx.audio.Music
    public float getVolume() {
        return this.volume;
    }

    @Override // com.badlogic.gdx.audio.Music
    public void setPan(float f, float f2) {
        this.volume = f2;
        this.pan = f;
        if (this.audio.noDevice || this.sourceID == -1) {
            return;
        }
        AL10.alSource3f(this.sourceID, 4100, MathUtils.cos((f - 1.0f) * 1.5707964f), 0.0f, MathUtils.sin((f + 1.0f) * 1.5707964f));
        AL10.alSourcef(this.sourceID, AL10.AL_GAIN, f2);
    }

    @Override // com.badlogic.gdx.audio.Music
    public void setPosition(float f) {
        int read;
        if (this.audio.noDevice || this.sourceID == -1) {
            return;
        }
        boolean z = this.isPlaying;
        this.isPlaying = false;
        AL10.alSourceStop(this.sourceID);
        AL10.alSourceUnqueueBuffers(this.sourceID, this.buffers);
        while (this.renderedSecondsQueue.size > 0) {
            this.renderedSeconds = this.renderedSecondsQueue.pop();
        }
        if (f <= this.renderedSeconds) {
            reset();
            this.renderedSeconds = 0.0f;
        }
        while (this.renderedSeconds < f - this.maxSecondsPerBuffer && (read = read(tempBytes)) > 0) {
            this.renderedSeconds += (this.maxSecondsPerBuffer * read) / 40960.0f;
        }
        this.renderedSecondsQueue.add(this.renderedSeconds);
        boolean z2 = false;
        for (int i = 0; i < 3; i++) {
            int i2 = this.buffers.get(i);
            if (!fill(i2)) {
                break;
            }
            z2 = true;
            AL10.alSourceQueueBuffers(this.sourceID, i2);
        }
        this.renderedSecondsQueue.pop();
        if (!z2) {
            stop();
            if (this.onCompletionListener != null) {
                this.onCompletionListener.onCompletion(this);
            }
        }
        AL10.alSourcef(this.sourceID, 4132, f - this.renderedSeconds);
        if (z) {
            AL10.alSourcePlay(this.sourceID);
            this.isPlaying = true;
        }
    }

    @Override // com.badlogic.gdx.audio.Music
    public float getPosition() {
        if (this.audio.noDevice || this.sourceID == -1) {
            return 0.0f;
        }
        return this.renderedSeconds + AL10.alGetSourcef(this.sourceID, 4132);
    }

    protected void loop() {
        reset();
    }

    public int getChannels() {
        return this.format == 4355 ? 2 : 1;
    }

    public int getRate() {
        return this.sampleRate;
    }

    public void update() {
        int alSourceUnqueueBuffers;
        if (this.audio.noDevice || this.sourceID == -1) {
            return;
        }
        boolean z = false;
        int alGetSourcei = AL10.alGetSourcei(this.sourceID, AL10.AL_BUFFERS_PROCESSED);
        while (true) {
            int i = alGetSourcei;
            alGetSourcei--;
            if (i <= 0 || (alSourceUnqueueBuffers = AL10.alSourceUnqueueBuffers(this.sourceID)) == 40963) {
                break;
            }
            if (this.renderedSecondsQueue.size > 0) {
                this.renderedSeconds = this.renderedSecondsQueue.pop();
            }
            if (!z) {
                if (fill(alSourceUnqueueBuffers)) {
                    AL10.alSourceQueueBuffers(this.sourceID, alSourceUnqueueBuffers);
                } else {
                    z = true;
                }
            }
        }
        if (z && AL10.alGetSourcei(this.sourceID, AL10.AL_BUFFERS_QUEUED) == 0) {
            stop();
            if (this.onCompletionListener != null) {
                this.onCompletionListener.onCompletion(this);
            }
        }
        if (!this.isPlaying || AL10.alGetSourcei(this.sourceID, 4112) == 4114) {
            return;
        }
        AL10.alSourcePlay(this.sourceID);
    }

    private boolean fill(int i) {
        tempBuffer.clear();
        int read = read(tempBytes);
        int i2 = read;
        if (read <= 0) {
            if (this.isLooping) {
                loop();
                int read2 = read(tempBytes);
                i2 = read2;
                if (read2 <= 0) {
                    return false;
                }
                if (this.renderedSecondsQueue.size > 0) {
                    this.renderedSecondsQueue.set(0, 0.0f);
                }
            } else {
                return false;
            }
        }
        this.renderedSecondsQueue.insert(0, (this.renderedSecondsQueue.size > 0 ? this.renderedSecondsQueue.first() : 0.0f) + ((this.maxSecondsPerBuffer * i2) / 40960.0f));
        tempBuffer.put(tempBytes, 0, i2).flip();
        AL10.alBufferData(i, this.format, tempBuffer, this.sampleRate);
        return true;
    }

    @Override // com.badlogic.gdx.audio.Music, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        stop();
        if (this.audio.noDevice || this.buffers == null) {
            return;
        }
        AL10.alDeleteBuffers(this.buffers);
        this.buffers = null;
        this.onCompletionListener = null;
    }

    @Override // com.badlogic.gdx.audio.Music
    public void setOnCompletionListener(Music.OnCompletionListener onCompletionListener) {
        this.onCompletionListener = onCompletionListener;
    }

    public int getSourceId() {
        return this.sourceID;
    }
}
