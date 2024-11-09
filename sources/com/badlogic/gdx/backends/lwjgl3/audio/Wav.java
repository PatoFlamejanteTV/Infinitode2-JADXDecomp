package com.badlogic.gdx.backends.lwjgl3.audio;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/Wav.class */
public class Wav {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/Wav$Music.class */
    public static class Music extends OpenALMusic {
        private WavInputStream input;

        public Music(OpenALLwjgl3Audio openALLwjgl3Audio, FileHandle fileHandle) {
            super(openALLwjgl3Audio, fileHandle);
            this.input = new WavInputStream(fileHandle);
            if (openALLwjgl3Audio.noDevice) {
                return;
            }
            setup(this.input.channels, this.input.bitDepth, this.input.sampleRate);
        }

        @Override // com.badlogic.gdx.backends.lwjgl3.audio.OpenALMusic
        public int read(byte[] bArr) {
            if (this.input == null) {
                this.input = new WavInputStream(this.file);
                setup(this.input.channels, this.input.bitDepth, this.input.sampleRate);
            }
            try {
                return this.input.read(bArr);
            } catch (IOException e) {
                throw new GdxRuntimeException("Error reading WAV file: " + this.file, e);
            }
        }

        @Override // com.badlogic.gdx.backends.lwjgl3.audio.OpenALMusic
        public void reset() {
            StreamUtils.closeQuietly(this.input);
            this.input = null;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/Wav$Sound.class */
    public static class Sound extends OpenALSound {
        public Sound(OpenALLwjgl3Audio openALLwjgl3Audio, FileHandle fileHandle) {
            super(openALLwjgl3Audio);
            if (openALLwjgl3Audio.noDevice) {
                return;
            }
            try {
                try {
                    WavInputStream wavInputStream = new WavInputStream(fileHandle);
                    if (wavInputStream.type == 85) {
                        setType("mp3");
                        StreamUtils.closeQuietly(wavInputStream);
                    } else {
                        setup(StreamUtils.copyStreamToByteArray(wavInputStream, wavInputStream.dataRemaining), wavInputStream.channels, wavInputStream.bitDepth, wavInputStream.sampleRate);
                        StreamUtils.closeQuietly(wavInputStream);
                    }
                } catch (IOException e) {
                    throw new GdxRuntimeException("Error reading WAV file: " + fileHandle, e);
                }
            } catch (Throwable th) {
                StreamUtils.closeQuietly(null);
                throw th;
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/Wav$WavInputStream.class */
    public static class WavInputStream extends FilterInputStream {
        public int channels;
        public int bitDepth;
        public int sampleRate;
        public int dataRemaining;
        public int type;

        public WavInputStream(FileHandle fileHandle) {
            super(fileHandle.read());
            try {
                if (read() != 82 || read() != 73 || read() != 70 || read() != 70) {
                    throw new GdxRuntimeException("RIFF header not found: " + fileHandle);
                }
                skipFully(4);
                if (read() != 87 || read() != 65 || read() != 86 || read() != 69) {
                    throw new GdxRuntimeException("Invalid wave file header: " + fileHandle);
                }
                int seekToChunk = seekToChunk('f', 'm', 't', ' ');
                this.type = (read() & 255) | ((read() & 255) << 8);
                if (this.type == 85) {
                    return;
                }
                if (this.type != 1 && this.type != 3) {
                    throw new GdxRuntimeException("WAV files must be PCM, unsupported format: " + getCodecName(this.type) + " (" + this.type + ")");
                }
                this.channels = (read() & 255) | ((read() & 255) << 8);
                this.sampleRate = (read() & 255) | ((read() & 255) << 8) | ((read() & 255) << 16) | ((read() & 255) << 24);
                skipFully(6);
                this.bitDepth = (read() & 255) | ((read() & 255) << 8);
                if (this.type == 1) {
                    if (this.bitDepth != 8 && this.bitDepth != 16) {
                        throw new GdxRuntimeException("PCM WAV files must be 8 or 16-bit: " + this.bitDepth);
                    }
                } else if (this.type == 3 && this.bitDepth != 32 && this.bitDepth != 64) {
                    throw new GdxRuntimeException("Floating-point WAV files must be 32 or 64-bit: " + this.bitDepth);
                }
                skipFully(seekToChunk - 16);
                this.dataRemaining = seekToChunk('d', 'a', 't', 'a');
            } catch (Throwable th) {
                StreamUtils.closeQuietly(this);
                throw new GdxRuntimeException("Error reading WAV file: " + fileHandle, th);
            }
        }

        private int seekToChunk(char c, char c2, char c3, char c4) {
            while (true) {
                boolean z = (read() == c) & (read() == c2) & (read() == c3) & (read() == c4);
                int read = (read() & 255) | ((read() & 255) << 8) | ((read() & 255) << 16) | ((read() & 255) << 24);
                if (read == -1) {
                    throw new IOException("Chunk not found: " + c + c2 + c3 + c4);
                }
                if (z) {
                    return read;
                }
                skipFully(read);
            }
        }

        private void skipFully(int i) {
            while (i > 0) {
                long skip = this.in.skip(i);
                if (skip <= 0) {
                    throw new EOFException("Unable to skip.");
                }
                i = (int) (i - skip);
            }
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr) {
            if (this.dataRemaining == 0) {
                return -1;
            }
            int i = 0;
            do {
                int min = Math.min(super.read(bArr, i, bArr.length - i), this.dataRemaining);
                if (min != -1) {
                    i += min;
                    this.dataRemaining -= min;
                } else {
                    if (i > 0) {
                        return i;
                    }
                    return -1;
                }
            } while (i < bArr.length);
            return i;
        }

        private String getCodecName(int i) {
            switch (i) {
                case 2:
                    return "Microsoft ADPCM";
                case 6:
                    return "ITU-T G.711 A-law";
                case 7:
                    return "ITU-T G.711 u-law";
                case 17:
                    return "IMA ADPCM";
                case 34:
                    return "DSP Group TrueSpeech";
                case 49:
                    return "Microsoft GSM 6.10";
                case 64:
                    return "Antex G.721 ADPCM";
                case 112:
                    return "Lernout & Hauspie CELP 4.8kbps";
                case 114:
                    return "Lernout & Hauspie CBS 12kbps";
                case 65534:
                    return "Extensible";
                default:
                    return "Unknown";
            }
        }
    }
}
