package com.badlogic.gdx.backends.lwjgl3.audio;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.stb.STBVorbis;
import org.lwjgl.system.MemoryStack;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/Ogg.class */
public class Ogg {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/Ogg$Music.class */
    public static class Music extends OpenALMusic {
        private OggInputStream input;
        private OggInputStream previousInput;

        public Music(OpenALLwjgl3Audio openALLwjgl3Audio, FileHandle fileHandle) {
            super(openALLwjgl3Audio, fileHandle);
            if (openALLwjgl3Audio.noDevice) {
                return;
            }
            this.input = new OggInputStream(fileHandle.read());
            setup(this.input.getChannels(), 16, this.input.getSampleRate());
        }

        @Override // com.badlogic.gdx.backends.lwjgl3.audio.OpenALMusic
        public int read(byte[] bArr) {
            if (this.input == null) {
                this.input = new OggInputStream(this.file.read(), this.previousInput);
                setup(this.input.getChannels(), 16, this.input.getSampleRate());
                this.previousInput = null;
            }
            return this.input.read(bArr);
        }

        @Override // com.badlogic.gdx.backends.lwjgl3.audio.OpenALMusic
        public void reset() {
            StreamUtils.closeQuietly(this.input);
            this.previousInput = null;
            this.input = null;
        }

        @Override // com.badlogic.gdx.backends.lwjgl3.audio.OpenALMusic
        protected void loop() {
            StreamUtils.closeQuietly(this.input);
            this.previousInput = this.input;
            this.input = null;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/Ogg$Sound.class */
    public static class Sound extends OpenALSound {
        public Sound(OpenALLwjgl3Audio openALLwjgl3Audio, FileHandle fileHandle) {
            super(openALLwjgl3Audio);
            if (openALLwjgl3Audio.noDevice) {
                return;
            }
            byte[] readBytes = fileHandle.readBytes();
            ByteBuffer newByteBuffer = BufferUtils.newByteBuffer(readBytes.length);
            newByteBuffer.put(readBytes);
            newByteBuffer.flip();
            MemoryStack stackPush = MemoryStack.stackPush();
            try {
                IntBuffer mallocInt = stackPush.mallocInt(1);
                IntBuffer mallocInt2 = stackPush.mallocInt(1);
                ShortBuffer stb_vorbis_decode_memory = STBVorbis.stb_vorbis_decode_memory(newByteBuffer, mallocInt, mallocInt2);
                int i = mallocInt.get(0);
                int i2 = mallocInt2.get(0);
                if (stb_vorbis_decode_memory == null) {
                    throw new GdxRuntimeException("Error decoding OGG file: " + fileHandle);
                }
                setup(stb_vorbis_decode_memory, i, 16, i2);
                if (stackPush != null) {
                    stackPush.close();
                }
            } catch (Throwable th) {
                if (stackPush != null) {
                    try {
                        stackPush.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
    }
}
